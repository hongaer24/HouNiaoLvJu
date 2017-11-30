package cn.houno.houniaolvju.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 项目名称：Houniaolvju
 * 类描述：
 * 创建人：qzc
 * 创建时间：2016/12/15 20:54
 * 修改人：qzc
 * 修改时间：2016/12/15 20:54
 * 修改备注：
 */
public class DateUtil {


    /**
     * 定义常量
     **/
    public static final String DATE_JFP_STR = "yyyyMM";
    public static final String DATE_FULL_STR = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_SECOND_STR = "yyyy-MM-dd HH:mm";
    public static final String DATE_SMALL_STR = "yyyy-MM-dd";
    public static final String DATE_KEY_STR = "yyMMddHHmmss";
    public static final String DATE_MONTH_DAY = "MM-dd";
    public static final String DATE_MONTH_DAY_CHAINA = "MM月dd";
    public static final String DATE_DAY_CHAINA = "yyyy年MM月dd日";


    public static String getDateStr(String dateStr) {
        if (dateStr.length() == 10) {
            dateStr = dateStr + " 00:00:00";

        } else if (dateStr.length() == 16) {
            dateStr = dateStr + ":00";

        }
        return dateStr;
    }

    //获得时间差，返回毫秒数
    public static long getCompareMilliSecond(String startDate, String endDate) {

        if (endDate.length() == 10) {
            endDate = endDate + " 00:00:00";

        } else if (endDate.length() == 16) {
            endDate = endDate + ":00";

        }

        if (startDate.length() == 10) {
            startDate = startDate + " 00:00:00";

        } else if (startDate.length() == 16) {
            startDate = startDate + ":00";

        }
        long l;
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FULL_STR, Locale.CHINA);
        try {
            Date date1 = formatter.parse(startDate);
            Date date2 = formatter.parse(endDate);
            l = date2.getTime() - date1.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
        return l;
    }

    /**
     * 使用预设格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @return
     */
    public static Date parse(String strDate) {
        return parse(strDate, DATE_FULL_STR);
    }

    /**
     * 使用用户格式提取字符串日期
     * 11-11转成11月11日
     *
     * @param dateStr 日期字符串
     * @return
     */
    public static String getOtherDateStr(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        try {
            // 用parse方法，可能会异常，所以要try-catch
            Date date = format.parse(dateStr);
            // 获取日期实例
            Calendar calendar = Calendar.getInstance();
            // 将日历设置为指定的时间
            calendar.setTime(date);
            // 获取年
            int year = calendar.get(Calendar.YEAR);
            // 这里要注意，月份是从0开始。
            int month = calendar.get(Calendar.MONTH) + 1;
            String monthStr;
            if (month < 10) {
                monthStr = "0" + month;
            } else {
                monthStr = month + "";
            }
            // 获取天
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            String dayStr;
            if (day < 10) {
                dayStr = "0" + day;
            } else {
                dayStr = day + "";
            }
            return monthStr + "月" + dayStr;
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 两个日期格式转换
     *
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return
     */

    public static Date parse(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern, Locale.CHINA);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 两个时间比较
     *
     * @param date1 指定时间 date2 当前时间
     * @return
     */
    public static int compareDateWithNow(Date date1) {
        Date date2 = new Date();
        int rnum = date1.compareTo(date2);
        return rnum;
    }


    /**
     * 与当前时间比较早晚
     * isNotEnd
     *
     * @param time 需要比较的时间
     * @return 输入的时间比现在时间晚则返回true
     */
    public static boolean compareDate(String nowTime, String time) {
        boolean isDayu = false;

        nowTime = getDateStr(nowTime);
        time = getDateStr(time);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

        try {
            Date parse = dateFormat.parse(time);
            Date parse1 = dateFormat.parse(nowTime);

            long diff = parse1.getTime() - parse.getTime();
            if (diff <= 0) {
                isDayu = true;
            } else {
                isDayu = false;
            }
        } catch (ParseException e) {

            e.printStackTrace();
        }

        return isDayu;
    }


    /**
     * 获取系统当前时间
     *
     * @return
     */
    public static String getNowTime() {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FULL_STR, Locale.CHINA);
        return df.format(new Date());
    }


    /**
     * 获取系统当前时间
     *
     * @return
     */
    public static String getNowTime(String type) {
        SimpleDateFormat df = new SimpleDateFormat(type, Locale.CHINA);
        return df.format(new Date());
    }

    /**
     * 获取明天时间
     *
     * @return
     */
    public static String getTomorrowTime(String toDay) {
        return getTomorrowTime(toDay, null);
    }

    /**
     * 获取明天时间
     *
     * @return
     */
    public static String getTomorrowTime(String toDay, String type) {
        if (type == null || TextUtils.isEmpty(type)) {
            type = DATE_SMALL_STR;
        }
        SimpleDateFormat sf = new SimpleDateFormat(type, Locale.CHINESE);
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sf.parse(toDay));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.add(Calendar.DAY_OF_YEAR, +1);
        String nextDate_1 = sf.format(cal.getTime());
        return nextDate_1;
    }

    /**
     * 获取昨天日期
     *
     * @return
     */
    public static String getYesterdayTime(String toDay) {
        return getYesterdayTime(toDay, null);
    }


    /**
     * 获取昨天日期
     *
     * @return
     */
    public static String getYesterdayTime(String toDay, String type) {
        if (type == null || TextUtils.isEmpty(type)) {
            type = DATE_SMALL_STR;
        }
        SimpleDateFormat sf = new SimpleDateFormat(type, Locale.CHINESE);
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sf.parse(toDay));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.add(Calendar.DAY_OF_YEAR, -1);
        String nextDate_1 = sf.format(cal.getTime());
        return nextDate_1;
    }

    /**
     * 获取系统当前计费期
     *
     * @return
     */
    public static String getJFPTime() {
        SimpleDateFormat df = new SimpleDateFormat(DATE_JFP_STR);
        return df.format(new Date());
    }

    /**
     * 将指定的日期转换成Unix时间戳
     *
     * @param date String 需要转换的日期 yyyy-MM-dd HH:mm:ss
     * @return long 时间戳
     */
    public static long dateToUnixTimestamp(String date) {
        long timestamp = 0;
        try {
            timestamp = new SimpleDateFormat(DATE_FULL_STR).parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    /**
     * 将指定的日期转换成Unix时间戳
     *
     * @param date String 需要转换的日期 yyyy-MM-dd
     * @return long 时间戳
     */
    public static long dateToUnixTimestamp(String date, String dateFormat) {
        long timestamp = 0;
        try {
            timestamp = new SimpleDateFormat(dateFormat).parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    /**
     * 将当前日期转换成Unix时间戳
     *
     * @return long 时间戳
     */
    public static long dateToUnixTimestamp() {
        long timestamp = new Date().getTime();
        return timestamp;
    }


    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到秒的字符串
     * @param format  Str
     * @return
     */
    public static String timeStamp2Date(String seconds, String format) {
        if (seconds.contains("-") || seconds.contains(":")) {
            return seconds;
        } else {
            if (seconds.isEmpty() || seconds.equals("null")) {
                return "";
            }
            if (format == null || format.isEmpty()) {
                format = "yyyy-MM-dd HH:mm:ss";
            }
            SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
            return sdf.format(new Date(Long.valueOf(seconds + "000")));
        }
    }

    /**
     * 获取两个日期之间的间隔天数
     *
     * @return 天数
     */
    public static int getGapCount(Date startDate, Date endDate) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(startDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);

        return (int) ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
    }

    /*
    * 取得指定日期是星期几
    * */
    public static String getWeek(String dateStr) {
        String week;
        Date date = parse(dateStr, DATE_SMALL_STR);
        Date nowDate = new Date();
        double diff = date.getTime() - nowDate.getTime();
        double days = diff / (1000 * 60 * 60 * 24);
        if (-1 < days && days < 0) {
            week = "今天";
        } else if (days >= 0 && days < 1) {
            week = "明天";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.CHINA);
            week = sdf.format(date);
        }
        return week;
    }

    /*
   * 取得指定日期是周几(不是星期几)
   * */
    public static String getEWeek(String dateStr) {
        String week;
        Date date = parse(dateStr, DATE_SMALL_STR);
        Date nowDate = new Date();
        double diff = date.getTime() - nowDate.getTime();
        double days = diff / (1000 * 60 * 60 * 24);
        if (-1 < days && days < 0) {
            week = "今天";
        } else if (days >= 0 && days < 1) {
            week = "明天";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("E", Locale.CHINA);
            week = sdf.format(date);
        }
        return week;
    }

    public static int getDays(String startDay, String endDay) {
        return getDays(startDay, endDay, null);
    }

    public static int getDays(String startDay, String endDay, String dateFormat) {
        if (dateFormat == null || TextUtils.isEmpty(dateFormat)) {
            dateFormat = DATE_SMALL_STR;
        }
        Date startDate = parse(startDay, dateFormat);
        Date endDate = parse(endDay, dateFormat);
        return getGapCount(startDate, endDate);
    }


    /*
  * 获取时间
  * */
    public static String getDate(int i) {
        Date date = new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, i);//把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String dateString = formatter.format(date);
        return dateString;
    }

    /*
   * 获取某个时间
   * */
    public static String getTheDate(String time, int i) {

        SimpleDateFormat formatter;
        Date date;
        formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        try {
            date = formatter.parse(time);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(calendar.DATE, i);//把日期往后增加一天.整数往后推,负数往前移动
            date = calendar.getTime(); //这个时间就是日期往后推一天的结果
            //  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(date);
            return dateString;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
    * 获取时间差  2h30m
    * */
    public static DateBean getDifferenceTime(String startTime, String EndTime) {
        long timeDiffrence = getCompareMilliSecond(startTime, EndTime);
        long day = timeDiffrence / (24 * 60 * 60 * 1000);
        long hour = (timeDiffrence / (60 * 60 * 1000) - day * 24);
        long min = ((timeDiffrence / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeDiffrence / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        System.out.println("" + day + "天" + hour + "小时" + min + "分" + s + "秒");

        return new DateBean(day, hour, min, s);
    }

    public static String addZero(int n) {
        if (n < 10) {
            return "0" + n;
        }
        return n + "";
    }

    public static class DateBean {

        long day;  //天
        long hour; //时
        long min;   //分
        long s;     //秒

        public DateBean(long day, long hour, long min, long s) {
            this.day = day;
            this.hour = hour;
            this.min = min;
            this.s = s;
        }

        public long getDay() {
            return day;
        }

        public void setDay(long day) {
            this.day = day;
        }

        public long getHour() {
            return hour;
        }

        public void setHour(long hour) {
            this.hour = hour;
        }

        public long getMin() {
            return min;
        }

        public void setMin(long min) {
            this.min = min;
        }

        public long getS() {
            return s;
        }

        public void setS(long s) {
            this.s = s;
        }


    }

}
