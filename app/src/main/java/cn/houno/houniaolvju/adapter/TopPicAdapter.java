package cn.houno.houniaolvju.adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;

import cn.houno.houniaolvju.R;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：轮播图
 * 创建人：qzc
 * 创建时间：2016/9/29 15:54
 * 修改人：qzc
 * 修改时间：2016/9/29 15:54
 * 修改备注：
 */
public class TopPicAdapter extends LoopPagerAdapter {


    private ArrayList<String> imgs;
    private final ImageOptions options;

    public void setImgs(ArrayList<String> imgs) {
        this.imgs = imgs;
        notifyDataSetChanged();
    }


    public TopPicAdapter(RollPagerView viewPager) {
        super(viewPager);
        options = new ImageOptions.Builder()
                .setLoadingDrawableId(R.drawable.loading_default_img)
                .build();
    }

    public TopPicAdapter(RollPagerView viewPager,ArrayList<String> imgs) {
        super(viewPager);
        this.imgs = imgs;
        options = new ImageOptions.Builder()
                .setLoadingDrawableId(R.drawable.loading_default_img)
                .build();
    }

    @Override
    public View getView(ViewGroup container, int position) {
        Log.i("RollViewPager", "getView:" + imgs.get(position));

        ImageView view = new ImageView(container.getContext());
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i("RollViewPager", "onClick");
//            }
//        });
        view.setBackgroundColor(Color.rgb(236, 236, 236));
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        x.image().bind(view, imgs.get(position), options);
        return view;
    }

    @Override
    public int getRealCount() {
        return imgs == null ? 0 : imgs.size();
    }
}
