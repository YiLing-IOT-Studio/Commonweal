package cn.zhyocean.utils;

import cn.zhyocean.constant.OSSClientConstants;
import com.aliyun.oss.OSSClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author: zhangocean
 * @Date: 2018/6/10 17:06
 * Describe: 文件上传工具
 */
public class FileUtil {

    /**
     * 上传文件到阿里云OSS
     * @param file 文件流
     * @return 返回文件URL
     */
    public String uploadFile(MultipartFile file, String subcatalog){

        //初始化OSSClient
        OSSClient ossClient = AliYunOSSClientUtil.getOSSClient();

        File f = null;
        if(file.equals("") || file.getSize() <= 0){
            file = null;
        }else {
            try {
                InputStream ins = file.getInputStream();
                f = new File(file.getOriginalFilename());
                System.out.println("上传的文件名：" + f.getName());
                inputToFile(ins, f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String md5Key = AliYunOSSClientUtil.uploadObject2OSS(ossClient, f, OSSClientConstants.BACKET_NAME,
                                                                                OSSClientConstants.FOLDER + subcatalog + "/");
        String url = AliYunOSSClientUtil.getUrl(ossClient, md5Key);

        String picUrl = "https://" + OSSClientConstants.BACKET_NAME + "." + OSSClientConstants.ENDPOINT +
                "/" + OSSClientConstants.FOLDER + subcatalog + "/" + f.getName();

        System.out.println("图片上传到阿里云OSS成功！路径URL为：" +picUrl);

        //删除临时生成的文件
        File deleteFile = new File(f.toURI());
        deleteFile.delete();

        return picUrl;

    }

    private void inputToFile(InputStream ins, File file){
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
