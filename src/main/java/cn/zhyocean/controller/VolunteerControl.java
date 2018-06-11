package cn.zhyocean.controller;

import cn.zhyocean.model.ManagerApply;
import cn.zhyocean.service.ManagerApplyService;
import cn.zhyocean.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: zhangocean
 * @Date: 2018/6/11 15:37
 * Describe:
 */
@Controller
public class VolunteerControl {

    @Autowired
    ManagerApplyService managerApplyService;

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

}
