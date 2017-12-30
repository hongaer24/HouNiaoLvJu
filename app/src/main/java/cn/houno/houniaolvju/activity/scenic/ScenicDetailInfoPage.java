package cn.houno.houniaolvju.activity.scenic;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.utils.PrefUtils;

/**
 * Created by 123 on 2017/12/18.
 */

public class ScenicDetailInfoPage extends Fragment {
    @Bind(R.id.wv_scenic_detail_info)
    WebView wvScenicDetailInfo;
    private View mView;
    private String scenicDetailInfo;
    private Context mActivity;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.page_scennic_detail_info, container, false);
        ButterKnife.bind(this, mView);
        initData();
        return mView;
    }

    private void initData() {

        Bundle arguments = getArguments();
        scenicDetailInfo = arguments.getString("info");
        PrefUtils.setString(getActivity(), "info", scenicDetailInfo);
        if (scenicDetailInfo != null) {
            /*WebSettings webSettings = wvScenicDetailInfo.getSettings();
            webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
            webSettings.setLoadWithOverviewMode(true);*/ // 缩放至屏幕的大小
            wvScenicDetailInfo.loadDataWithBaseURL(null, scenicDetailInfo, "text/html", "utf-8", null);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
