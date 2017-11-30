package cn.houno.houniaolvju.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

/**
 * 图片简单处理工具类
 */
public class ImageUtils {

    /**
     * 屏幕宽
     *
     * @param context
     * @return
     */
    public static int getWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 屏幕高
     *
     * @param context
     * @return
     */
    public static int getHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    /*
    *从网络获取图片
    * */
    public static Bitmap getBitmapFromUrl(String url) {
        Bitmap bm = null;
        try {
            URL iconUrl = new URL(url);
            URLConnection conn = iconUrl.openConnection();
            HttpURLConnection http = (HttpURLConnection) conn;

            int length = http.getContentLength();

            conn.connect();
            // 获得图像的字符流
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is, length);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();// 关闭流
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bm;
    }

    /**
     * 质量压缩方法
     *
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            //第一个参数 ：图片格式 ，第二个参数： 图片质量，100为最高，0为最差  ，第三个参数：保存压缩后的数据的流
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    /*
    * 候鸟旅居通过拍照或从相册选取图片之后各种编码各种转换再之后上传
    * */
    public static String encode(Bitmap bm) throws UnsupportedEncodingException {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();  //字节数组输出流
            bm.compress(Bitmap.CompressFormat.PNG, 100, baos);  //图片转流
            baos.close();   //关闭输出流
            byte[] img = baos.toByteArray();    //流转字节数组
            String be = "data:image/png;base64,";   //前缀
            String str1 = Base64.encodeToString(img, 0, img.length, Base64.NO_WRAP); // 进行base64编码，使用无换行
            String str2 = "\"" + be + str1 + "\"";  //拼接(这里卡了很久！)
            String[] images = {str2};   //字符串放数组里
            String str3 = Arrays.asList(images).toString();//(我感觉str3和str4差不多)
            String str4 = new String(str3.getBytes(), "UTF-8"); //字符串转流utf-8编码
            return str4;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 印象太极头像编码
     */
    public static String encodeHeader(Bitmap bm) throws UnsupportedEncodingException {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();  //字节数组输出流
            bm.compress(Bitmap.CompressFormat.PNG, 100, baos);  //图片转流
            baos.close();   //关闭输出流
            byte[] img = baos.toByteArray();    //流转字节数组
            String be = "data:image/png;base64,";   //前缀
            String str1 = Base64.encodeToString(img, 0, img.length, Base64.NO_WRAP); // 进行base64编码，使用无换行
            String str2 =   be + str1 ;  //拼接(这里卡了很久！)
//            String[] images = {str2};   //字符串放数组里
//            String str3 = Arrays.asList(images).toString();//(我感觉str3和str4差不多)
            String str4 = new String(str2.getBytes(), "UTF-8"); //字符串转流utf-8编码
            return str4;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }



    public static Bitmap createWaterMaskImage(Bitmap src, Bitmap watermark) {

        String tag = "createBitmap";
        Log.d(tag, "create a new bitmap");
        if (src == null) {
            return null;
        }
        int w = src.getWidth();
        int h = src.getHeight();
        int ww = watermark.getWidth();
        int wh = watermark.getHeight();
        // create the new blank bitmap
        Bitmap newb = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
        Canvas cv = new Canvas(newb);
        // draw src into
        cv.drawBitmap(src, 0, 0, null);// 在 0，0坐标开始画入src
        // draw watermark into
        cv.drawBitmap(watermark, w - ww -10, h - wh -10, null);// 在src的右下角画入水印
        // save all clip
        cv.save(Canvas.ALL_SAVE_FLAG);// 保存
        // store
        cv.restore();// 存储
        return newb;
    }


    /**
     * 印象太极视频编码
     */
    public static String encodeVideo(String filePath) throws UnsupportedEncodingException {
        byte[] buffer;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1024*10];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
            String be = "data:video/mp4;base64,";   //前缀
            String str1 = Base64.encodeToString(buffer, 0, buffer.length, Base64.NO_WRAP); // 进行base64编码，使用无换行
            String str2 = "\"" + be + str1 + "\"";  //拼接(这里卡了很久！)
            String[] images = {str2};   //字符串放数组里
            String str3 = Arrays.asList(images).toString();//(我感觉str3和str4差不多)
            String str4 = new String(str3.getBytes(), "UTF-8"); //字符串转流utf-8编码
            return str4;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }


    //计算图片的缩放值
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {


        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        return inSampleSize;
    }


    //根据路径获得图片并压缩，返回bitmap用于显示
    public static Bitmap getSmallBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        options.inSampleSize = calculateInSampleSize(options, 480, 800);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

}
