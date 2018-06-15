package cn.zhyocean.controller;

import cn.zhyocean.service.ActivityService;
import cn.zhyocean.service.RoleService;
import net.sf.json.JSONArray;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    ActivityService activityService;
    @Autowired
    RoleService roleService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model){
        String token = (String) request.getSession().getAttribute("token");
       //如果没有token，则重定向到auth获取易班token
        if(token == null || token.isEmpty()){
            return "redirect:/auth1";
        }

        String username = (String) request.getSession().getAttribute("username");

        String usernameIsTimeOut = logoutBySystem(username, request);
        if("no".equals(usernameIsTimeOut)){
            return "redirect:/auth1";
        }

        System.out.println("session'time is " + request.getSession().getMaxInactiveInterval());

        model.addAttribute("username",username);
        model.addAttribute("role",roleService.getRoleByUsername(username));

        return "index";
    }

    /**
     * 注销用户
     * @param request
     * @return 注销状态码
     * @throws IOException
     */
    @GetMapping("/logout")
    @ResponseBody
    public String logout(HttpServletRequest request) throws IOException {

        String token = (String) request.getSession().getAttribute("token");
        return authControl.logoutToken(token, request);
    }

    public String logoutBySystem(String username,HttpServletRequest request){
        String token = (String) request.getSession().getAttribute("token");
        //判断本地session中token或username是否过期
        if(username == null || username.isEmpty() || token == null || token.isEmpty()){
            String logoutYbToken = authControl.logoutToken(token, request);
            System.out.println("注销易班状态码：" + logoutYbToken);
            return "no";
        }
        if(!authControl.isAuth(token)){
            return "no";
        }
        return "yes";
    }

    @ResponseBody
    @GetMapping("/getActivity")
    public JSONArray getActivity(@RequestParam("tag") String tag,
                                 @RequestParam("rows") String rows,
                                 @RequestParam("pageNo") String pageNo){
        return activityService.findActivityByTag(tag, rows, pageNo);
    }

    @GetMapping("/publisher")
    public String publisher(HttpServletRequest request,
                            Model model){
        String username = (String) request.getSession().getAttribute("username");
        String usernameIsTimeOut = logoutBySystem(username, request);
        if("no".equals(usernameIsTimeOut)){
            return "redirect:/auth1";
        }
        model.addAttribute("username",username);
        model.addAttribute("role",roleService.getRoleByUsername(username));
        return "publisher";
    }

    @GetMapping("/volunteer")
    public String volunteer(HttpServletRequest request,
                            Model model){
        String username = (String) request.getSession().getAttribute("username");
        String usernameIsTimeOut = logoutBySystem(username, request);
        if("no".equals(usernameIsTimeOut)){
            return "redirect:/auth1";
        }
        model.addAttribute("username",username);
        model.addAttribute("role",roleService.getRoleByUsername(username));
        return "volunteer";
    }

    @GetMapping("/manager")
    public String manager(HttpServletRequest request,
                          Model model){
        String username = (String) request.getSession().getAttribute("username");
        String usernameIsTimeOut = logoutBySystem(username, request);
        if("no".equals(usernameIsTimeOut)){
            return "redirect:/auth1";
        }
        model.addAttribute("username",username);
        model.addAttribute("role",roleService.getRoleByUsername(username));
        return "manager";
    }


}
