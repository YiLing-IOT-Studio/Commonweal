package cn.zhyocean.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: zhangocean
 * @Date: 2018/6/9 14:49
 * Describe: 时间工具
 */
public class TimeUtil {

    /**
     * 获得当前时间
     * @return eg:2018/6/10
     */
    public String getNowDate(){
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return simpleDateFormat.format(now);
    }

    /**
     * 将long型时间拼接成 *天*时*分*秒 的形式
     * @param time long型的秒级数
     * @return 拼接后的数
     */
    public String longToStrTime(long time){
        String strTime = "";
        long theTime = 0;
        if(time >= 86400){
            theTime = time/86400;
//            System.out.println("time > 86400 的theTime is " + theTime);
            strTime += theTime + "天";
            time -= 86400*theTime;
        }
        if(time >= 3600){
            theTime = time/3600;
//            System.out.println("time > 3600 的theTime is " + theTime);
            strTime += theTime + "时";
            time -= 3600*theTime;
        }
        if(time >= 60) {
            theTime = time/60;
//            System.out.println("time > 60 的theTime is " + theTime);
            strTime += theTime + "分";
            time -= 60*theTime;
        }
        if(time != 0){
            strTime += time + "秒";
        }
        return strTime;
    }

}
