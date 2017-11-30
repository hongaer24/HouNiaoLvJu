package cn.houno.houniaolvju.utils;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.widget.TextView;

import java.util.regex.Pattern;

import cn.houno.houniaolvju.R;

/**
 * 项目名称：Houniaolvju
 * 类描述：
 * 创建人：qzc
 * 创建时间：2016/12/16 11:09
 * 修改人：qzc
 * 修改时间：2016/12/16 11:09
 * 修改备注：
 */
public class MyText2Utils {


    //star：1 2 经济型 3舒适型 4 四星级(高档型) 5五星级(豪华型)

    /*
    * 是否是url
    * */
    public static boolean isUrl(String str) {
        Pattern pattern = Pattern
                .compile("^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+$");
        boolean isUrl = pattern.matcher(str).matches();
        return isUrl;
    }

    /*
    * 是否是空
    * */
    public static boolean isEmpty(String str) {
        return str == null || TextUtils.isEmpty(str);
    }


    public static String replaceSubString(String str, int start, int end) {
        if (!TextUtils.isEmpty(str) && str.length() > end) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (i >= start && i <= end) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
        return "";
    }


    public static String replaceName(String str) {
        if (!TextUtils.isEmpty(str)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (i == 1) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
        return "";
    }


    /*
    * 获取酒店星级
    * */
    public static String getHotelStar(String star) {
        String star2 = "";
        if ("1".equals(star) || "2".equals(star)) {
            star2 = "经济型";
        } else if ("3".equals(star)) {
            star2 = "舒适型";
        } else if ("4".equals(star)) {
            star2 = "四星级";
        } else if ("5".equals(star)) {
            star2 = "五星级";
        }
        return star2;
    }

    /**
     * 定义常量
     **/
    public static final int PRICE_FULL = 1; // ¥180元
    public static final int PRICE_END = 2;    //180元
    public static final int PRICE_START = 3;    //¥180

    /*
   * 获取酒店价格
   * */
    public static String getHotelPrice(String price) {
        return getHotelPrice(price, 1);

    }


    public static String getScenicPrice(String price) {
        return getHotelPrice(price, 3);

    }

    /*
    * 获取酒店价格
    * */
    public static String getHotelPrice(String price, int type) {

        if (price != null || !TextUtils.isEmpty(price)) {
            if (type == PRICE_FULL) {
                return "¥" + getIntPrice(price) + "元";
            } else if (type == PRICE_END) {
                return getIntPrice(price) + "元";
            } else if (type == PRICE_START) {
                return "¥" + getIntPrice(price);
            }

        } else {
            return null;
        }

        return price;
    }

    /*
   * 获取酒店价格
   * */

    public static String getQiPrice(String price) {
        return getQiPrice(price, PRICE_FULL);
    }


    public static String getQiPrice(String price, int type) {

        if (price != null || !TextUtils.isEmpty(price)) {
            if (type == PRICE_FULL) {
                return "¥" + getIntPrice(price) + "起";
            } else if (type == PRICE_END) {
                return getIntPrice(price) + "起";
            } else if (type == PRICE_START) {
                return "¥" + getIntPrice(price);
            }
        } else {
            return null;
        }
        return price;
    }

    public static String getIntPrice(String price) {
        if (price.indexOf(".") > 0) {
            //正则表达
            price = price.replaceAll("0+?$", "");//去掉后面无用的零
            price = price.replaceAll("[.]$", "");//如小数点后面全是零则去掉小数点
        }
        return price;
    }

    public static void formatQiPrice(Context context, TextView textView, String price) {
        if (price == null || TextUtils.isEmpty(price)) {
            textView.setText("暂无价格");
            textView.setTextSize(14);
        } else {
            int txtLength = getQiPrice(price).length();
            SpannableString styledText = new SpannableString(getQiPrice(price));
            styledText.setSpan(new TextAppearanceSpan(context, R.style.textStyle00), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            styledText.setSpan(new TextAppearanceSpan(context, R.style.textStyle01), 1, txtLength - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            styledText.setSpan(new TextAppearanceSpan(context, R.style.textStyle02), txtLength - 1, txtLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            textView.setText(styledText, TextView.BufferType.SPANNABLE);
        }
    }

    public static void formatYuanPrice(Context context, TextView textView, String price) {
        if (price == null || TextUtils.isEmpty(price)) {
            textView.setText("暂无价格");
            textView.setTextSize(14);
        } else {
            int txtLength = getHotelPrice(price).length();
            SpannableString styledText = new SpannableString(getHotelPrice(price));
            styledText.setSpan(new TextAppearanceSpan(context, R.style.textStyle00), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            styledText.setSpan(new TextAppearanceSpan(context, R.style.textStyle01), 1, txtLength - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            styledText.setSpan(new TextAppearanceSpan(context, R.style.textStyle00), txtLength - 1, txtLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            textView.setText(styledText, TextView.BufferType.SPANNABLE);
        }
    }

    public static void formatSearchDate(Context context, TextView textView, String date) {

        int txtLength = date.length();
        SpannableString styledText = new SpannableString(date);
        styledText.setSpan(new TextAppearanceSpan(context, R.style.textStyle01), 0, txtLength - 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new TextAppearanceSpan(context, R.style.textStyle04), txtLength - 2, txtLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(styledText, TextView.BufferType.SPANNABLE);

    }

    public static void formatFenQuanDate(Context context, TextView textView, String date) {
        if (date == null || TextUtils.isEmpty(date)) {
            textView.setText("暂无价格");
            textView.setTextSize(14);
        } else {
            int price = (int) Double.parseDouble(date);
            String date1 = "¥" + price + "/套";
            int txtLength = date1.length();
            SpannableString styledText = new SpannableString(date1);
            styledText.setSpan(new TextAppearanceSpan(context, R.style.textStyle01), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            styledText.setSpan(new TextAppearanceSpan(context, R.style.textStyle05), 1, txtLength - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            styledText.setSpan(new TextAppearanceSpan(context, R.style.textStyle01), txtLength - 1, txtLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            textView.setText(styledText, TextView.BufferType.SPANNABLE);
        }
    }


    public static void formatTicketPrice(Context context, TextView textView, String price) {
        if (price == null || TextUtils.isEmpty(price)) {
            textView.setText("暂无价格");
            textView.setTextSize(14);
        } else {
            int txtLength = getScenicPrice(price).length();
            SpannableString styledText = new SpannableString(getScenicPrice(price));
            styledText.setSpan(new TextAppearanceSpan(context, R.style.textStyle00), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            styledText.setSpan(new TextAppearanceSpan(context, R.style.textStyle01), 1, txtLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            textView.setText(styledText, TextView.BufferType.SPANNABLE);
        }
    }

    public static void formatCouponPrice(Context context, TextView textView, String price) {
        if (price == null || TextUtils.isEmpty(price)) {
            textView.setText("暂无价格");
            textView.setTextSize(14);
        } else {
            int txtLength = getHotelPrice(price, PRICE_START).length();
            SpannableString styledText = new SpannableString(getScenicPrice(price));
            styledText.setSpan(new TextAppearanceSpan(context, R.style.textStyle00), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            styledText.setSpan(new TextAppearanceSpan(context, R.style.textStyle04), 1, txtLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            textView.setText(styledText, TextView.BufferType.SPANNABLE);
        }
    }

    public static String getStar(String star) {
        int starInt = Integer.parseInt(star);
        if (starInt == 3 || starInt == 8) {
            return "舒适型";
        } else if (starInt == 4 || starInt == 9) {
            return "四星级";
        } else if (starInt == 5 || starInt == 10) {
            return "五星级";
        } else if (starInt == 6) {
            return "六星级";
        } else if (starInt == 7) {
            return "七星级";
        } else {
            return "经济型";
        }
    }

    /**
     * 对字符串处理:将指定位置到指定位置的字符以星号代替
     *
     * @param content 传入的字符串
     * @param begin   开始位置
     * @param end     结束位置
     * @return
     */
    public static String getStarString(String content, int begin, int end) {

        if (begin >= content.length() || begin < 0) {
            return content;
        }
        if (end >= content.length() || end < 0) {
            return content;
        }
        if (begin >= end) {
            return content;
        }
        String starStr = "";
        for (int i = begin; i < end; i++) {
            starStr = starStr + "*";
        }
        return content.substring(0, begin) + starStr + content.substring(end, content.length());

    }

    /**
     * 对字符加星号处理：除前面几位和后面几位外，其他的字符以星号代替
     *
     * @param content  传入的字符串
     * @param frontNum 保留前面字符的位数
     * @param endNum   保留后面字符的位数
     * @return 带星号的字符串
     */

    public static String getStarString2(String content, int frontNum, int endNum) {

        if (frontNum >= content.length() || frontNum < 0) {
            return content;
        }
        if (endNum >= content.length() || endNum < 0) {
            return content;
        }
        if (frontNum + endNum >= content.length()) {
            return content;
        }
        String starStr = "";
        for (int i = 0; i < (content.length() - frontNum - endNum); i++) {
            starStr = starStr + "*";
        }
        return content.substring(0, frontNum) + starStr
                + content.substring(content.length() - endNum, content.length());

    }

    public static String insertString(String s1, String s2, int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(s1).insert(i, s2);
        return sb.toString();
    }

}
