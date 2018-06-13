package cn.zhyocean.controller;

import cn.zhyocean.model.ManagerApply;
import cn.zhyocean.service.ManagerApplyService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: 2018/6/11 16:47
 * Describe:
 */
@Controller
public class ManagerControl {

    @Autowired
    ManagerApplyService managerApplyService;

    /**
     * 获得已有组织
     * @return
     */
    @GetMapping("/getTissue")
    @ResponseBody
    public JSONArray getTissueByStatus(){
        int status = 1;
        List<ManagerApply> getTissueByStatus = managerApplyService.getTissueByStatus(status);
        System.out.println("已有组织有：" + JSONArray.fromObject(getTissueByStatus));
        return JSONArray.fromObject(getTissueByStatus);
    }

    /**
     * 查看申请的组织
     * @return
     */
    @GetMapping("/checkApplyTissue")
    @ResponseBody
    public JSONArray checkApplyTissue(){
        int status = 0;
        List<ManagerApply> getTissueByStatus = managerApplyService.getTissueByStatus(status);
        System.out.println("申请中的组织有：" + JSONArray.fromObject(getTissueByStatus));
        return JSONArray.fromObject(getTissueByStatus);
    }

    /**
     * 同意组织
     * @param applyName 组织名
     * @return
     */
    @PostMapping("/agreeForTissue")
    @ResponseBody
    public int agreeForTissue(@RequestParam("applyName") String applyName,
                              @RequestParam("signal") String signal){
        int updateResult = 0;
        if("同意加入".equals(signal)){
            updateResult = managerApplyService.updateApplyStatus(1, applyName);
        }
        if("拒绝".equals(signal)){
            updateResult = managerApplyService.updateApplyStatus(-1,applyName);
        }
        return updateResult;
    }

    /**
     * 删除组织
     * @param applyName 组织名
     * @return
     */
    @PostMapping("/deleteOrgByApplyName")
    @ResponseBody
    public int deleteOrgByApplyName(@RequestParam("cancelName") String applyName){
        return managerApplyService.deleteOrgByApplyName(applyName);
    }

}
