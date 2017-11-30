package cn.houno.houniaolvju.utils;

import android.content.Context;
import android.util.TypedValue;

import org.xutils.image.ImageOptions;

import cn.houno.houniaolvju.R;


public class DisplayUtil {

    static ImageOptions options;
    static ImageOptions fOptions;

    public static ImageOptions getImageOptions() {
        if (options == null) {
            options = new ImageOptions.Builder().setFadeIn(true)
                    .setLoadingDrawableId(R.drawable.loading_default_img)
                    .build();
        }
        return options;
    }

    public static ImageOptions getCircularHeaderOptions() {
        if (fOptions == null) {
            fOptions = new ImageOptions.Builder().setFadeIn(true)
                    .setFailureDrawableId(R.drawable.icon_header_failure)
                    .setCircular(true)
                    .build();
        }
        return fOptions;
    }

    /**
     * 根据dip值转化成px值
     *
     * @param context
     * @param dip
     * @return
     */
    public static int dipToPix(Context context, int dip) {
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, context.getResources().getDisplayMetrics());
        return size;
    }
}
