package cn.houno.houniaolvju.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.TreeSet;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.houniaolvju.R;

/**
 * 项目名称：Houniaolvju
 * 类描述：星级价格弹窗
 * 创建人：qzc
 * 创建时间：2016/12/23 9:35
 * 修改人：qzc
 * 修改时间：2016/12/23 9:35
 * 修改备注：
 */
public class PriceLevelPopupWindow extends PopupWindow implements CompoundButton.OnCheckedChangeListener {

    private Context mContext;

    private static final String TAG = "PriceLevelPopupWindow";
    @Bind(R.id.rb_price0)
    RadioButton mRbPrice0;
    @Bind(R.id.rb_price1)
    RadioButton mRbPrice1;
    @Bind(R.id.rb_price2)
    RadioButton mRbPrice2;
    @Bind(R.id.rb_price3)
    RadioButton mRbPrice3;
    @Bind(R.id.rb_price4)
    RadioButton mRbPrice4;
    @Bind(R.id.rb_price5)
    RadioButton mRbPrice5;
    @Bind(R.id.rg_price)
    RadioGroup mRgPrice;
    @Bind(R.id.cb_level0)
    CheckBox mCbLevel0;
    @Bind(R.id.cb_level1)
    CheckBox mCbLevel1;
    @Bind(R.id.cb_level2)
    CheckBox mCbLevel2;
    @Bind(R.id.cb_level3)
    CheckBox mCbLevel3;
    @Bind(R.id.cb_level4)
    CheckBox mCbLevel4;
    @Bind(R.id.tv_clear)
    TextView mTvClear;
    @Bind(R.id.tv_confirm)
    TextView mTvConfirm;
    @Bind(R.id.ll_price_level)
    LinearLayout mLlPriceLevel;

    private View mView;

    private int price;
    private TreeSet<String> level = new TreeSet<>();

    //定义一个接口
    public interface OnCheckClickListener {
        public void OnCheckClick(int price,TreeSet<String> level);
    }

    private OnCheckClickListener mListener;

    //写一个设置接口监听的方法
    public void setOnCheckClickListener(OnCheckClickListener listener) {
        mListener = listener;
    }

    public PriceLevelPopupWindow(Context context) {
        super(context);
        mContext = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.layout_hotellist_price_level, null);
        ButterKnife.bind(this, mView);
        initData();
        initEvent();
    }


    private void initData() {

        //设置PopupWindow的View
        this.setContentView(mView);
        //设置PopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置PopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        this.setTouchable(true);
        this.setOutsideTouchable(true);
        this.setBackgroundDrawable(new BitmapDrawable(mContext.getResources(), (Bitmap) null));
        this.setAnimationStyle(R.style.popup_anim);

        //设置PopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.popup_anim);
//        //实例化一个ColorDrawable颜色为半透明
//        ColorDrawable dw = new ColorDrawable(0xb0000000);
//        //设置SelectPicPopupWindow弹出窗体的背景
//        this.setBackgroundDrawable(dw);

        backgroundAlpha((Activity) mContext, 0.5f);//0.0-1.0
        this.setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss() {

                backgroundAlpha((Activity) mContext, 1f);
            }
        });

        mRbPrice0.setChecked(true);
        mCbLevel0.setChecked(true);
    }

    private void initEvent() {

        mRgPrice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_price0:
                        price = 0;
                        break;
                    case R.id.rb_price1:
                        price = 1;
                        break;
                    case R.id.rb_price2:
                        price = 2;
                        break;
                    case R.id.rb_price3:
                        price = 3;
                        break;
                    case R.id.rb_price4:
                        price = 4;
                        break;
                    case R.id.rb_price5:
                        price = 5;
                        break;
                }
                Log.i("RadioGroup", price + "");
            }
        });

        mCbLevel0.setOnCheckedChangeListener(this);
        mCbLevel1.setOnCheckedChangeListener(this);
        mCbLevel2.setOnCheckedChangeListener(this);
        mCbLevel3.setOnCheckedChangeListener(this);
        mCbLevel4.setOnCheckedChangeListener(this);

        mTvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.OnCheckClick(price,level);
                dismiss();
            }
        });

        mTvClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllCheck();
            }
        });


    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }

    private void clearAllCheck() {
        if (mRbPrice0.isChecked()) {
            mRbPrice0.setChecked(false);
        }
        if (mRbPrice1.isChecked()) {
            mRbPrice1.setChecked(false);
        }
        if (mRbPrice2.isChecked()) {
            mRbPrice2.setChecked(false);
        }
        if (mRbPrice3.isChecked()) {
            mRbPrice3.setChecked(false);
        }
        if (mRbPrice4.isChecked()) {
            mRbPrice4.setChecked(false);
        }
        if (mRbPrice5.isChecked()) {
            mRbPrice5.setChecked(false);
        }

        if (mCbLevel0.isChecked()) {
            mCbLevel0.setChecked(false);
        }
        if (mCbLevel1.isChecked()) {
            mCbLevel1.setChecked(false);
        }
        if (mCbLevel2.isChecked()) {
            mCbLevel2.setChecked(false);
        }
        if (mCbLevel3.isChecked()) {
            mCbLevel3.setChecked(false);
        }
        if (mCbLevel4.isChecked()) {
            mCbLevel4.setChecked(false);
        }

    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();
        if (id == R.id.cb_level0) {  //不限被选中
            if (isChecked) {
                if (mCbLevel1.isChecked()) {
                    mCbLevel1.setChecked(false);
                }
                if (mCbLevel2.isChecked()) {
                    mCbLevel2.setChecked(false);
                }
                if (mCbLevel3.isChecked()) {
                    mCbLevel3.setChecked(false);
                }
                if (mCbLevel4.isChecked()) {
                    mCbLevel4.setChecked(false);
                }
                mCbLevel0.setChecked(true);
                level.clear();
                level.add("0");
            } else {
                mCbLevel0.setChecked(false);
                level.remove("0");
            }

        } else {
            //移除 不限
            if (mCbLevel0.isChecked()) {
                mCbLevel0.setChecked(false);
                level.remove("0");
            }
            if (id == R.id.cb_level1) {
                if (mCbLevel1.isChecked()) {
                    mCbLevel1.setChecked(true);
                    level.add("1");
                } else {
                    mCbLevel1.setChecked(false);
                    level.remove("1");
                }
            }
            if (id == R.id.cb_level2) {
                if (mCbLevel2.isChecked()) {
                    mCbLevel2.setChecked(true);
                    level.add("2");
                } else {
                    mCbLevel2.setChecked(false);
                    level.remove("2");
                }
            }
            if (id == R.id.cb_level3) {
                if (mCbLevel3.isChecked()) {
                    mCbLevel3.setChecked(true);
                    level.add("3");
                } else {
                    mCbLevel3.setChecked(false);
                    level.remove("3");
                }
            }
            if (id == R.id.cb_level4) {
                if (mCbLevel4.isChecked()) {
                    mCbLevel4.setChecked(true);
                    level.add("4");
                } else {
                    mCbLevel4.setChecked(false);
                    level.remove("4");
                }
            }
            if (mCbLevel1.isChecked() && mCbLevel2.isChecked()
                    && mCbLevel3.isChecked() && mCbLevel4.isChecked()) {
                mCbLevel1.setChecked(false);
                mCbLevel2.setChecked(false);
                mCbLevel3.setChecked(false);
                mCbLevel4.setChecked(false);
                mCbLevel0.setChecked(true);
            }
        }
        Log.i("level", level.toString());
    }
}
