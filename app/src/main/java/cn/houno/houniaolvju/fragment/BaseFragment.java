package cn.houno.houniaolvju.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import cn.houno.houniaolvju.MainActivity;

/**
 * Fragment基类封装
 */
public abstract class BaseFragment extends Fragment {
    public MainActivity mActivity;

    /**
     * 此方法可以得到上下文对象
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    /*
     * 返回一个需要展示的View
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mActivity = (MainActivity) getActivity();
        View view = initView(inflater,container);
        initFindViewById(view);

        return view;
    }


    /**
     * 子类可以复写此方法初始化事件
     */
    protected void initEvent() {

    }

    /**
     * 子类可以复写此方法初始化事件
     */
    protected void ToastShort(String text) {
        Toast.makeText(mActivity, text, Toast.LENGTH_SHORT).show();
    }

    /*
     * 当Activity初始化之后可以在这里进行一些数据的初始化操作
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initEvent();
    }

    /**
     * 子类实现此抽象方法返回View进行展示
     *
     * @return
     */
    public abstract View initView(LayoutInflater inflater,ViewGroup container);

    /**
     * 初始化控件
     */
    protected abstract void initFindViewById(View view);

    /**
     * 子类在此方法中实现数据的初始化
     */
    public abstract void initData();
}
