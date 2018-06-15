package cn.zhyocean.controller;

import cn.zhyocean.model.Activity;
import cn.zhyocean.service.ActivityApplyService;
import cn.zhyocean.service.ActivityService;
import cn.zhyocean.service.UserService;
import cn.zhyocean.utils.FileUtil;
import cn.zhyocean.utils.TimeUtil;
import net.sf.json.JSONArray;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @Date: 2018/6/10 14:53
 * Describe:
 */
@Controller
public class PublisherControl {

    private final Logger logger = LoggerFactory.getLogger(PublisherControl.class);

    @Autowired
    ActivityService activityService;
    @Autowired
    ActivityApplyService activityApplyService;
    @Autowired
    UserService userService;

    @PostMapping("/publisher")
    @ResponseBody
    public int publisher(@RequestParam("title") String title,
                            @RequestParam("act_time") String activityDate,
                            @RequestParam("place") String place,
                            @RequestParam("deadline") String deadLine,
                            @RequestParam("msg") String msg,
                            @RequestParam("type") String category,
                            @RequestParam("img") MultipartFile file,
                            HttpServletRequest request){
        int personalLimit = Integer.parseInt(request.getParameter("limit"));

        int remain = personalLimit;
        Activity activity = new Activity(title,category,activityDate,msg,place,deadLine,personalLimit);

        //获得发布者、发布时间、上传到阿里云OSS的路径
        String publisher = (String) request.getSession().getAttribute("username");
        TimeUtil timeUtil = new TimeUtil();
        String publishDate = timeUtil.getNowDate();
        System.out.println("publishDate is " + publishDate);
        try {
            FileUtil fileUtil = new FileUtil();
            String img = fileUtil.uploadFile(file, "公益活动图片/" + publisher);

            activity.setPublisher(publisher);
            activity.setImg(img);
            activity.setPublishDate(publishDate);
            activity.setRemain(remain);

            activityService.insertActivity(activity);
            return 1;
        } catch (Exception e){
            logger.error("上传失败！");
        }

        return 0;
    }

    @GetMapping("/getAllActivity")
    @ResponseBody
    public JSONArray getAllActivityByPublisher(HttpServletRequest request){
        String publisher = (String) request.getSession().getAttribute("username");
        return activityService.getAllActivityByPublisher(publisher);
    }

    @GetMapping("/getActivityNames")
    @ResponseBody
    public JSONArray getActivityNamesByPublisher(HttpServletRequest request){
        String publisher = (String) request.getSession().getAttribute("username");

        return activityService.getActivityNamesByPublisher(publisher);
    }

    @PostMapping("/getpersonalinfo")
    @ResponseBody
    public JSONArray getPersonalInfo(@Param("activityName") String activityName,
                                     HttpServletRequest request){
        if("无".equals(activityName)){
            return new JSONArray();
        }
        String publisher = (String) request.getSession().getAttribute("username");
        int activityId = activityService.getIdByTitleAndPublisher(activityName, publisher);
        List<Integer> userIds = activityApplyService.getYbIdByActivityId(activityId);
        System.out.println(userIds);
        return userService.getByYbIds(userIds);

    }


}
