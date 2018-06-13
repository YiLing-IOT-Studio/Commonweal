package cn.zhyocean.controller;

import cn.yiban.open.Authorize;
import cn.zhyocean.constant.Status;
import cn.zhyocean.constant.YBOpen;
import cn.zhyocean.model.YBUser;
import cn.zhyocean.utils.TimeUtil;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: zhangocean
 * @Date: 2018/6/9 10:32
 * Describe: 易班授权
 */
@Controller
public class AuthControl {

    private Logger logger = LoggerFactory.getLogger(AuthControl.class);

    /**
     * 请求授权认证
     */
    @GetMapping("/auth1")
    public void auth1(HttpServletResponse response) throws IOException {
        Authorize authorize = new Authorize(YBOpen.APP_ID, YBOpen.APP_SECRET);
        String url = authorize.forwardurl(YBOpen.REDIRECT_URI,"test",Authorize.DISPLAY_TAG_T.WEB);
        response.sendRedirect(url);
    }

    /**
     * 认证成功回调地址
     * @param request
     * @param model
     * @return "redirect:/" 认证成功重定向到/页面
     */
    @GetMapping("/auth")
    public String auth(HttpServletRequest request, Model model){
        String code = request.getParameter("code");
        Authorize authorize = new Authorize(YBOpen.APP_ID, YBOpen.APP_SECRET);
        String text = authorize.querytoken(code, YBOpen.REDIRECT_URI);
        System.out.println("回调信息：" + text);
        JSONObject jsonObject = JSONObject.fromObject(text);
        if(jsonObject.has("access_token")){
            //保存授权信息
            String token = (String) jsonObject.get("access_token");
            request.getSession().setAttribute("token",token);
            YBUser YBUser = getUserInfo(token);
            request.getSession().setAttribute("username", YBUser.getUsername());
            return "redirect:/";
        }else {
            model.addAttribute("auth_error","对不起，授权验证失败，请重试！");
            return "fail";
        }
    }

    /**
     * 获得用户易班信息
     * @param token 用户凭证
     * @return 用户信息
     */
    public YBUser getUserInfo(String token){
        cn.yiban.open.common.User yiBanUser = new cn.yiban.open.common.User(token);
        String userResult = yiBanUser.me();
        JSONObject returnObject = JSONObject.fromObject(userResult);
        Object info = returnObject.get("info");
        JSONObject infoObject = JSONObject.fromObject(info);
        String ybUserId = (String) infoObject.get("yb_userid");
        String ybUsername = (String) infoObject.get("yb_username");
        String ybUsernick = (String) infoObject.get("yb_usernick");
        String ybSex = (String) infoObject.get("yb_sex");
        String ybMoney = (String) infoObject.get("yb_money");
        String ybSchoolId = (String) infoObject.get("yb_schoolid");
        String ybSchoolName = (String) infoObject.get("yb_schoolname");
        YBUser YBUser = new YBUser(ybUserId, ybUsername, ybUsernick, ybSex, ybMoney, ybSchoolId, ybSchoolName);
        return YBUser;
    }

    /**
     * 验证token是否过期
     * @param token 用户授权凭证
     * @return true--没有过期    false--过期
     */
    public boolean isAuth(String token){
        int tokenExpireIn = getTokenTime(token);
        TimeUtil timeUtil = new TimeUtil();
        System.out.println("token剩余秒数:" + timeUtil.longToStrTime(tokenExpireIn));
        return tokenExpireIn > 0;
    }

    /**
     * 获得token过期时间
     * @param token 用户授权凭证
     * @return token过期时间
     */
    public int getTokenTime(String token){
        Authorize authorize = new Authorize(YBOpen.APP_ID, YBOpen.APP_SECRET);
        String tokenInfo = authorize.getManInstance(token).query();
        JSONObject jsonObject = JSONObject.fromObject(tokenInfo);
        int tokenExpireIn = (int) jsonObject.get("expire_in");
        return tokenExpireIn;
    }

    /**
     * 注销易班token
     * @param token 用户授权凭证
     * @return 注销状态码
     */
    public String logoutToken(String token, HttpServletRequest request){

        Authorize authorize = new Authorize(YBOpen.APP_ID, YBOpen.APP_SECRET);
        String status = authorize.getManInstance(token).revoke();
        request.getSession().removeAttribute("token");
        request.getSession().removeAttribute("username");
        JSONObject jsonObject = JSONObject.fromObject(status);
        if(jsonObject.get("status").equals(Status.STATUS200)){
            logger.info("token " + token + "注销成功");
            return "200";
        }else {
            logger.info("token " + token + "注销失败");
        }
        return "500";

    }

}
