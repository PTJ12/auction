package xpit.top.action.utils;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间戳转化为简单时间格式
 * @author Pu Tongjiao
 * @date 2022/9/27 15:03
 */
public class DateUtils {
    public static String timestampToDate(BigInteger date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateFormat =simpleDateFormat.format(new Date(Long.parseLong(String.valueOf(date))));
        return dateFormat;
    }

    public static BigInteger dateToTimestampe(String date) throws ParseException {
        long time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date).getTime();
        return BigInteger.valueOf(time);
    }
}
