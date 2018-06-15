package cn.zhyocean.controller;

import cn.zhyocean.model.ManagerApply;
import cn.zhyocean.model.User;
import cn.zhyocean.model.YBUser;
import cn.zhyocean.service.ActivityApplyService;
import cn.zhyocean.service.ManagerApplyService;
import cn.zhyocean.service.UserService;
import cn.zhyocean.utils.FileUtil;
import net.sf.json.JSONArray;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: zhangocean
 * @Date: 2018/6/11 15:37
 * Describe:
 */
@Controller
public class VolunteerControl {

    @Autowired
    ManagerApplyService managerApplyService;
    @Autowired
    AuthControl authControl;
    @Autowired
    ActivityApplyService activityApplyService;
    @Autowired
    UserService userService;

    /**
     * 申请成为发布者
     * @param request
     * @param file
     * @return
     */
    @PostMapping("/applyformanager")
    @ResponseBody
    public int applyformanager(HttpServletRequest request,
                               @RequestParam("evidence") MultipartFile file){

        String applyName = request.getParameter("applyName");
        String superior = request.getParameter("superior");


        String username = (String) request.getSession().getAttribute("username");

        FileUtil fileUtil = new FileUtil();
        String filrURL = fileUtil.uploadFile(file, "申请成为管理员/" + username);
        int status = 0;

        ManagerApply managerApply = new ManagerApply(applyName, superior, filrURL, username, status);

        int insertResult = managerApplyService.insertApply(managerApply);

        return insertResult;
    }


    /**
     * 获得我的活动
     * @param request
     * @return
     */
    @GetMapping("/getmyactivitys")
    @ResponseBody
    public JSONArray getMyActivitys(HttpServletRequest request){

        String token = (String) request.getSession().getAttribute("token");
        YBUser ybUser = authControl.getUserInfo(token);
        System.out.println("当前登录易班用户信息" + ybUser);
        int userId = Integer.parseInt(ybUser.getUserId());

        List<Integer> activityIds = activityApplyService.getAllActivityByYbId(userId);
        return activityApplyService.getAllActivityByActivityId(activityIds);
    }

    /**
     * 获得申请信息
     * @param request
     * @return
     */
    @GetMapping("/getapplyinfo")
    @ResponseBody
    public String getApplyInfo(HttpServletRequest request){
        String username = (String) request.getSession().getAttribute("username");
        int result = managerApplyService.getStatusByProposer(username);
        System.out.println(result);
        if(result == 0){
            return "0";
        } else if(result == 1){
            return "1";
        } else if(result == -1){
            return "2";
        } else if (result == 2){
            return "0";
        }
        return "0";
    }

    @PostMapping("/applyforactivity")
    @ResponseBody
    public int applyForActivity(User user,
                                      @Param("activityName") String activityName,
                                      HttpServletRequest request){
        String username = (String) request.getSession().getAttribute("username");
        String token = (String) request.getSession().getAttribute("token");
        YBUser ybUser = authControl.getUserInfo(token);
        int userId = Integer.parseInt(ybUser.getUserId());
        //检查提交报名用户是否为登录用户
        if(!username.equals(user.getStuName())){
            return 0;
        }
        int applyResult = userService.applyForActivity(userId, user, activityName);
        return  applyResult;
    }

}
