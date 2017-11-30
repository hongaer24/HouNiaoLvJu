package cn.houno.houniaolvju.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.x;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.activity.scenic.ScenicDetailActivity;
import cn.houno.houniaolvju.bean.HomeIndexDataBean.DataBean.ActiScenicBean;
import cn.houno.houniaolvju.utils.DisplayUtil;
import cn.houno.houniaolvju.utils.MyText2Utils;

/**
 * 项目名称：Houniaolvju
 * 类描述：首页-景点门票
 * 创建人：qzc
 * 创建时间：2016/12/13 14:09
 * 修改人：qzc
 * 修改时间：2016/12/13 14:09
 * 修改备注：
 */

public class ScenicTicketPage extends Fragment {

    public View mView;
    private ImageView ivImg;
    private TextView tvTitle;
    private TextView tvStar;
    private TextView tvCate;
    private TextView tvHits;
    private TextView tvPrice;

    private String id;  //景点id

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.listitem_scenic, container, false);
        initView(mView);
        initData();
        initEvent();
        return mView;
    }

    private void initView(View view) {
        ivImg = (ImageView) view.findViewById(R.id.iv_scenic_img);
        tvTitle = (TextView) view.findViewById(R.id.tv_scenic_title);
        tvStar = (TextView) view.findViewById(R.id.tv_scenic_star);
        tvCate = (TextView) view.findViewById(R.id.tv_scenic_cate);
        tvHits = (TextView) view.findViewById(R.id.tv_scenic_hits);
        tvPrice = (TextView) view.findViewById(R.id.tv_scenic_price);
    }

    private void initEvent() {
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), ScenicDetailActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        Bundle arguments = getArguments();
        ActiScenicBean data = (ActiScenicBean) arguments.getSerializable("data");
        if (data != null) {
            setPageData(data);
        }
    }

    public void setPageData(ActiScenicBean data) {
        if (null != data) {
            mView.setVisibility(View.VISIBLE);
            id = data.getId();
            x.image().bind(ivImg, data.getImg(), DisplayUtil.getImageOptions());
            tvTitle.setText(data.getTitle());
            tvStar.setText(data.getStar());
            tvCate.setText(data.getCate_name());
            tvHits.setText(data.getHits());
            MyText2Utils.formatYuanPrice(getActivity(), tvPrice, data.getPrice().getWebprice());
        }else {
            mView.setVisibility(View.GONE);
        }
    }

}
