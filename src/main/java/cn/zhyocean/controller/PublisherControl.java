package cn.zhyocean.controller;

import cn.zhyocean.model.Activite;
import cn.zhyocean.service.ActiviteService;
import cn.zhyocean.utils.FileUtil;
import cn.zhyocean.utils.TimeUtil;
import net.sf.json.JSONArray;
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

/**
 * @author: zhangocean
 * @Date: 2018/6/10 14:53
 * Describe:
 */
@Controller
public class PublisherControl {

    private final Logger logger = LoggerFactory.getLogger(PublisherControl.class);

    @Autowired
    ActiviteService activiteService;

    @PostMapping("/publisher")
    @ResponseBody
    public int publisher(@RequestParam("title") String title,
                            @RequestParam("act_time") String activiteDate,
                            @RequestParam("place") String place,
                            @RequestParam("deadline") String deadLine,
                            @RequestParam("msg") String msg,
                            @RequestParam("type") String category,
                            @RequestParam("img") MultipartFile file,
                            HttpServletRequest request){
        int personalLimit = Integer.parseInt(request.getParameter("limit"));

        int remain = personalLimit;
        Activite activite = new Activite(title,category,activiteDate,msg,place,deadLine,personalLimit);

        //获得发布者、发布时间、上传到阿里云OSS的路径
        String publisher = (String) request.getSession().getAttribute("username");
        TimeUtil timeUtil = new TimeUtil();
        String publishDate = timeUtil.getNowDate();
        try {
            FileUtil fileUtil = new FileUtil();
            String img = fileUtil.uploadFile(file, "公益活动图片/" + publisher);

            activite.setPublisher(publisher);
            activite.setImg(img);
            activite.setPublishDate(publishDate);
            activite.setRemain(remain);

            activiteService.insertActivite(activite);
            return 1;
        } catch (Exception e){
            logger.error("上传失败！");
        }

        return 0;
    }

    @GetMapping("/getAllActivite")
    @ResponseBody
    public JSONArray getAllActiviteByPublisher(HttpServletRequest request){
        String publisher = (String) request.getSession().getAttribute("username");
        return activiteService.getAllActiviteByPublisher(publisher);
    }

//    public JSONArray getApply


}