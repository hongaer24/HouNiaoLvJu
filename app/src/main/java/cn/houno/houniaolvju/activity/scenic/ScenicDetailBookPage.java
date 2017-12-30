package cn.houno.houniaolvju.activity.scenic;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.adapter.ScenicOtherAdapter;
import cn.houno.houniaolvju.adapter.ScenicTicketAdapter;
import cn.houno.houniaolvju.bean.ScenicDetailBean;
import cn.houno.houniaolvju.view.InnerGridView;
import cn.houno.houniaolvju.view.InnerListView;

/**
 * Created by 123 on 2017/12/18.
 */

public class ScenicDetailBookPage extends Fragment {
    @Bind(R.id.lv_ticket)
    InnerListView lvTicket;
    @Bind(R.id.tv_scenic_info)
    TextView tvScenicInfo;
    @Bind(R.id.gv_scenic_other)
    InnerGridView gvScenicOther;
    private View mView;
    private List<ScenicDetailBean.DataBean.InfoBean.TicketlistBean> data;
    private List<ScenicDetailBean.DataBean.InfoBean.TicketlistBean> newDataList =new ArrayList<>();

    private String title;
    private String address;
    private ScenicTicketAdapter mTicketadapter;
    private List<ScenicDetailBean.DataBean.OtherBean> others;
    private ScenicOtherAdapter mOtherAdapter;
    private String id;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.page_scennic_detail_book, container, false);
        ButterKnife.bind(this, mView);
        initData();
        return mView;
    }

    private void initData() {
        Bundle arguments = getArguments();
        data = (List<ScenicDetailBean.DataBean.InfoBean.TicketlistBean>) arguments.getSerializable("data");
        title = arguments.getString("title");
        id= arguments.getString("id");
        Log.i("00", "id===" + id);

        address = arguments.getString("address");
        others = (List<ScenicDetailBean.DataBean.OtherBean>) arguments.getSerializable("others");

        if (data != null&&data.size()>0) {
            for(int i=0;i<data.size();i++){
                if(data.get(i).getTicketlistinfo()!=null){
                    newDataList.add(data.get(i));
                }
            }
            setPageData(newDataList);
        }
        if (others != null) {
            setOthersData(others);
        }
    }

    private void setOthersData(List<ScenicDetailBean.DataBean.OtherBean> others) {
        if (mOtherAdapter == null) {
            mOtherAdapter = new ScenicOtherAdapter(getActivity(), others);
            gvScenicOther.setAdapter(mOtherAdapter);
        } else {
            mOtherAdapter.setData(others);
        }
    }

    public void setPageData(List<ScenicDetailBean.DataBean.InfoBean.TicketlistBean> data) {
        if (mTicketadapter == null) {
            mTicketadapter = new ScenicTicketAdapter(getActivity(), data, title, address);
            lvTicket.setAdapter(mTicketadapter);
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.tv_scenic_info)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_scenic_info:
                Intent intent1 = new Intent(getActivity(), ScenicInfoActivity.class);
                intent1.putExtra("id", id);
                startActivity(intent1);
                break;
        }
    }


    @Override
   public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}