package cn.zhyocean.controller;

import cn.yiban.open.Authorize;
import cn.zhyocean.constant.Status;
import cn.zhyocean.constant.YBOpen;
import cn.zhyocean.model.User;
import cn.zhyocean.service.ActiviteService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: zhangocean
 * @Date: 2018/5/3 15:49
 * Describe:
 */
@Controller
public class IndexControl {

    private Logger logger = LoggerFactory.getLogger(IndexControl.class);

    @Autowired
    AuthControl authControl;
    @Autowired
    ActiviteService activiteService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model){
        String token = (String) request.getSession().getAttribute("token");
//        如果没有token，则重定向到auth获取易班token
        if(token == null || token.isEmpty()){
            return "redirect:/auth1";
        }
        Authorize authorize = new Authorize(YBOpen.APP_ID, YBOpen.APP_SECRET);
        boolean tokenTime = authControl.isAuth(token);
        if(!tokenTime){
            return "redirect:/auth1";
        }
        model.addAttribute("username",request.getSession().getAttribute("username"));
        System.out.println("session过期设置时间："+ request.getSession().getMaxInactiveInterval());

        System.out.println("username is " + request.getSession().getAttribute("username"));
        return "index";
    }

    /**
     * 注销用户
     * @param request
     * @param response
     * @return 注销状态码
     * @throws IOException
     */
    @GetMapping("/logout")
    @ResponseBody
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {

        Authorize authorize = new Authorize(YBOpen.APP_ID, YBOpen.APP_SECRET);
        String token = (String) request.getSession().getAttribute("token");
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

    public String logoutBySystem(String username,HttpServletRequest request){
        System.out.println("logogutbysystem 的username:" + username);
        String token = (String) request.getSession().getAttribute("token");
        System.out.println("logogutbysystem 的token:" + token);
        if(!authControl.isAuth(token)){
            return "redirect:/auth1";
        }
        if(username == null){
            return "redirect:/auth1";
        }
        return "yes";
    }

    @ResponseBody
    @GetMapping("/getActivite")
    public JSONArray getActivite(@RequestParam("tag") String tag,
                                 @RequestParam("rows") String rows,
                                 @RequestParam("pageNo") String pageNo){
        return activiteService.findActiviteByTag(tag, rows, pageNo);
    }

    @GetMapping("/publisher")
    public String publisher(HttpServletRequest request){
        String usernameIsTimeOut = logoutBySystem((String) request.getSession().getAttribute("username"), request);
        System.out.println("username is " + request.getSession().getAttribute("username"));
        return "publisher";
    }

    @GetMapping("/volunteer")
    public String volunteer(HttpServletRequest request){
        String usernameIsTimeOut = logoutBySystem((String) request.getSession().getAttribute("username"), request);
        System.out.println("username is " + request.getSession().getAttribute("username"));

        return "volunteer";
    }

    @GetMapping("/manager")
    public String manager(HttpServletRequest request){
        String usernameIsTimeOut = logoutBySystem((String) request.getSession().getAttribute("username"), request);
        System.out.println("username is " + request.getSession().getAttribute("username"));

        return "manager";
    }


}
