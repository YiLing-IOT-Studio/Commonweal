package cn.zhyocean.utils;

/**
 * @author: zhangocean
 * @Date: 2018/6/9 14:49
 * Describe:
 */
public class TimeUtil {

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
