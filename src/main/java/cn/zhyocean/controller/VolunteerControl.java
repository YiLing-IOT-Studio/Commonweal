package cn.zhyocean.controller;

import cn.zhyocean.model.ManagerApply;
import cn.zhyocean.model.YBUser;
import cn.zhyocean.service.ActiviteApplyService;
import cn.zhyocean.service.ManagerApplyService;
import cn.zhyocean.utils.FileUtil;
import net.sf.json.JSONArray;
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
    ActiviteApplyService activiteApplyService;

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


    @GetMapping("/getmyactivites")
    @ResponseBody
    public JSONArray getMyActivites(HttpServletRequest request){

        String token = (String) request.getSession().getAttribute("token");
        YBUser ybUser = authControl.getUserInfo(token);
        System.out.println("当前登录易班用户信息" + ybUser);
        int userId = Integer.parseInt(ybUser.getUserId());

        List<Integer> activiteIds = activiteApplyService.getAllActivitesByUserIdAndApplyStatus(userId, 1);
        return activiteApplyService.getAllActivitesByActiviteId(activiteIds);
    }

    @GetMapping("/getapplyinfo")
    @ResponseBody
    public String getApplyInfo(HttpServletRequest request){
        String username = (String) request.getSession().getAttribute("username");
        int result = managerApplyService.getStatusByProposer(username);
        System.out.println(result);
        if(result == 0){
            return "";
        } else if(result == 1){
            return "同意";
        } else if(result == -1){
            return "拒绝";
        }
        return "";
    }

}
