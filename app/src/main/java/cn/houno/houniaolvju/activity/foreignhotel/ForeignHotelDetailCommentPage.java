package cn.houno.houniaolvju.activity.foreignhotel;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dinuscxj.progressbar.CircleProgressBar;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.activity.hotel.CommentListActivity;
import cn.houno.houniaolvju.bean.HotelDetailBean.DataBean.CommentBean;

/**
 * 项目名称：Houniaolvju
 * 类描述：酒店详情-住客点评
 * 创建人：qzc
 * 创建时间：2016/12/23 17:20
 * 修改人：qzc
 * 修改时间：2016/12/23 17:20
 * 修改备注：
 */
public class ForeignHotelDetailCommentPage extends Fragment {


    @Bind(R.id.rl_comment_total)
    RelativeLayout mRlCommentTotal;
    @Bind(R.id.tv_comment_username)
    TextView mTvCommentUsername;
    @Bind(R.id.tv_comment_contents)
    TextView mTvCommentContents;
    @Bind(R.id.tv_comment_total)
    TextView mTvCommentTotal;
    @Bind(R.id.iv_comment_more_img)
    ImageView mIvCommentMoreImg;
    @Bind(R.id.cpb_comment)
    CircleProgressBar mCpbComment;


    private View mView;
    private List<CommentBean> commentList;
    private String mHid;
    private int mAverage = 100;
    private String mCommentnum = "0";
    private ValueAnimator mAnimator;
    private boolean isFirstIn = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.page_hoteldetail_comment, container, false);
        ButterKnife.bind(this, mView);
        initData();
        return mView;
    }

    private void initData() {

        Bundle arguments = getArguments();
        commentList = (List<CommentBean>) arguments.getSerializable("data");
        if (commentList != null) {
            setPageData();
        }

        simulateProgress();
    }


    @Override
    public void onResume() {
        super.onResume();

    }


    private void setPageData() {
        if (commentList.size() != 0) {
            mHid = commentList.get(0).getHid();
            mTvCommentUsername.setText(commentList.get(0).getUsername());
            mTvCommentContents.setText(commentList.get(0).getContents());
            mCommentnum = commentList.get(0).getCommentnum();

            mAverage = (int) (Float.parseFloat(commentList.get(0).getAverage()) / 5.0 * 100);
            if (!mCommentnum.equals("0")) {
                mTvCommentTotal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.putExtra("from", "hotel");
                        intent.putExtra("hid", mHid);
                        intent.putExtra("average", mAverage);
                        intent.putExtra("commentnum", mCommentnum);
                        intent.setClass(getActivity(), CommentListActivity.class);
                        startActivity(intent);
                    }
                });
            }
        }

        mTvCommentTotal.setText("查看全部" + mCommentnum + "条评论");
        //  if (mAverage != -1) {

        //  }
    }

    private void simulateProgress() {
        mAnimator = ValueAnimator.ofInt(0, mAverage);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int progress = (int) animation.getAnimatedValue();
                mCpbComment.setProgress(progress);
            }

        });
        mAnimator.setRepeatCount(0);
        mAnimator.setDuration(1500);
        mAnimator.start();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAnimator != null) {
            mAnimator.cancel();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
