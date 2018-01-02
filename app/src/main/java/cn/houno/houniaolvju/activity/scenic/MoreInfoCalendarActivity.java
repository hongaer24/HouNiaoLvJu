package cn.houno.houniaolvju.activity.scenic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.ScenicDetailBean;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.StringUtils;

public class MoreInfoCalendarActivity extends AppCompatActivity {

    private  CommonCalendarView calendarView;
    private Map<String,List> mYearMonthMap = new HashMap<>();
    private List<ScenicDetailBean.DataBean.InfoBean.TicketlistBean.TicketlistinfoBean.PricecalendarBean> pricecalendarBeanList=new ArrayList<>();
    private int position;
    private Context context;
    private MoreInfoCalendarActivity mActivity;
    public static final int RESULT_CODE = 300;
    private String mScenicTitle;
    private String mScenicAddress;
    private String mTicketTitle;
    private int price;
    private int allPrice;
    private String mScenicId;
    private String mTicketId;
  //  private Serializable mPricecalendar;
    private  List<ScenicDetailBean.DataBean.InfoBean.TicketlistBean.TicketlistinfoBean.PricecalendarBean>  Pricecalendarlist=new ArrayList<>();

    //private List<PricecalendarBean> dataPrice;
    // private List<DataPrice.DataBean.InfoBean.TicketlistBean.TicketlistinfoBean.PricecalendarBean> dataPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info_calendar);
        mActivity = MoreInfoCalendarActivity.this;
        initData();
        Intent intent = getIntent();
        position=intent.getIntExtra("position",0);
       /* mScenicTitle = intent.getStringExtra("scenicTitle");
        mScenicAddress = intent.getStringExtra("scenicAddress");
        mTicketTitle = intent.getStringExtra("ticketTitle");
        price = intent.getIntExtra("price",0);
        allPrice = intent.getIntExtra(" allPrice",0);*/


      /*  List<ProductDatePrice> mDatePriceList = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {//构造12个月每天的价格数据
            for (int j = 1; j <= 28; j++) {
                ProductDatePrice price = new ProductDatePrice();
                price.setPriceDate(String.format("2018-%s-%s", StringUtils.leftPad(String.valueOf(i), 2, "0"), StringUtils.leftPad(String.valueOf(j), 2, "0")));
                price.setPrice(RandomUtils.nextInt(1000));
                mDatePriceList.add(price);
            }
        }*/


    }
    private void initData() {
        Intent intent = getIntent();
        mScenicId = intent.getStringExtra("sid");
        mTicketId = intent.getStringExtra("tid");
        Pricecalendarlist= (List<ScenicDetailBean.DataBean.InfoBean.TicketlistBean.TicketlistinfoBean.PricecalendarBean>) intent.getSerializableExtra("pricecalendars");
        addData(Pricecalendarlist);
        //getDataFromServer();
    }
    private void addData(List<ScenicDetailBean.DataBean.InfoBean.TicketlistBean.TicketlistinfoBean.PricecalendarBean> pricecalendarBeanList) {
        for (ScenicDetailBean.DataBean.InfoBean.TicketlistBean.TicketlistinfoBean.PricecalendarBean productDatePrice : pricecalendarBeanList) {//把价格数据改为同一个月的list 在一个key value里，减少渲染界面时循环判断数量
            productDatePrice.getDepartDate();
            String yearMonth = TextUtils.substring(productDatePrice.getDepartDate(), 0, TextUtils.lastIndexOf(productDatePrice.getDepartDate(), '-'));
            List list = mYearMonthMap.get(yearMonth);
            if (list == null) {
                list = new ArrayList();
                list.add(productDatePrice);
                mYearMonthMap.put(yearMonth, list);
            } else {
                list.add(productDatePrice);
            }
        }

        this.calendarView = (CommonCalendarView) findViewById(R.id.calendarView);
        this.calendarView.init(new CommonCalendarView.DatePickerController() {
            public String nowData;

            @Override
            public int getMaxYear() {
                return 2018;
            }

            @Override
            public void onDayOfMonthSelected(int year, int month, int day) {
              /*  Toast.makeText(MoreInfoCalendarActivity.this, String.format("%s-%s-%s", year,StringUtils.leftPad(String.valueOf(month),2,"0"),
                        StringUtils.leftPad(String.valueOf(day),2,"0")), Toast.LENGTH_SHORT).show();*/


            }

            @Override
            public void onDayOfMonthAndDataSelected(int year, int month, int day, List obj) {
                if (obj==null){
                    return;
                }
                String priceDate = String.format("%s-%s-%s", year,
                        StringUtils.leftPad(month + "", 2, "0"), StringUtils.leftPad(String.valueOf(day), 2, "0"));
                for (int i = 0; i < obj.size(); i++) {
                    ScenicDetailBean.DataBean.InfoBean.TicketlistBean.TicketlistinfoBean.PricecalendarBean datePrice = (ScenicDetailBean .DataBean.InfoBean.TicketlistBean.TicketlistinfoBean.PricecalendarBean) obj.get(i);
                    if (datePrice==null){
                        continue;}
                    if (TextUtils.equals(datePrice.getDepartDate(),priceDate)){

                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putString("nowData", datePrice.getDepartDate());
                        bundle.putString("nowPrice", String.valueOf(datePrice.getSalePrice()));
                        intent.putExtra("bundle", bundle);
                        setResult(RESULT_CODE, intent);
                        finish();
                        return;
                        // Intent intent1 = new Intent(mActivity, FillInScenicOrderActivity.class);
                       // intent1.putExtra("nowData",datePrice.getDepartDate());
                        //intent1.putExtra("nowPrice",datePrice.getSalePrice());
                       /* intent1.putExtra("scenicTitle",  mScenicTitle);
                        intent1.putExtra("scenicAddress", mScenicAddress);
                        intent1.putExtra("ticketTitle", mTicketTitle);
                        intent1.putExtra("ticketTitle", mTicketTitle);
                        intent1.putExtra("price", price);
                        intent1.putExtra("allPrice", allPrice);*/

                        // Toast.makeText(MoreInfoCalendarActivity.this, datePrice.toString(), Toast.LENGTH_SHORT).show();
                    }

                }
                // finish();
            }

            @Override
            public void showOtherFields(Object obj, View view, int gridItemYear, int gridItemMonth, int gridItemDay) {
                //当你设置了数据源之后，界面渲染会循环调用showOtherFields方法，在该方法中实现同一日期设置界面显示效果。
                ScenicDetailBean.DataBean.InfoBean.TicketlistBean.TicketlistinfoBean.PricecalendarBean productDatePrice  = (ScenicDetailBean.DataBean.InfoBean.TicketlistBean.TicketlistinfoBean.PricecalendarBean) obj;
                if (TextUtils.equals(productDatePrice.getDepartDate(), String.format("%s-%s-%s", gridItemYear,
                        StringUtils.leftPad(gridItemMonth + "", 2, "0"), StringUtils.leftPad(String.valueOf(gridItemDay), 2, "0")))) {
                    CommonCalendarView.GridViewHolder viewHolder = (CommonCalendarView.GridViewHolder) view.getTag();
                    viewHolder.mPriceTv.setText(String.format("¥ %s", productDatePrice.getSalePrice()));
                    view.setEnabled(true);
                    viewHolder.mTextView.setEnabled(true);
                }
            }

            @Override
            public Map<String, List> getDataSource() {
                return mYearMonthMap;
            }
        });
    }



  /*  private void getDataFromServer() {

       *//* RequestParams params = new RequestParams(Constant.SCENIC_LIST);
        params.addBodyParameter("id", "19940");
        params.addBodyParameter("UserID","1117");*//*
        RequestParams params=new RequestParams(Constants.SCENIC_DETAIL);
        params.addBodyParameter("id", "19940");
        params.addBodyParameter("UserID","1117");
        x.http().post(params, new org.xutils.common.Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                try {
                    Log.i("999", "result==="+result);
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");

                    if (status == 0) {
                        parseData(result);

                    } else {
                        //Toast.makeText(mActivity, obj.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
      *//*  x.http().post(params, new Callback.CommonCallback<String>() {


            @Override
            public void onSuccess(String result) {
                try {
                    Log.i("999", "result==="+result);
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");

                    if (status == 0) {
                        parseData(result);

                    } else {
                        //Toast.makeText(mActivity, obj.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
               *//**//* pbLoading.setVisibility(View.GONE);
                tvLoading.setText("加载失败");*//**//*
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                //rfvScenicDetail.stopRefresh();
            }
        });*//*
    }
*/
   /* private void parseData(String result) {
        Gson gson = new Gson();
        ScenicDetailBean databaseList = gson.fromJson(result, ScenicDetailBean.class);
        List<ScenicDetailBean.DataBean.InfoBean.TicketlistBean> data = databaseList.getData().getInfo().getTicketlist();
        pricecalendarBeanList= data.get(position).getTicketlistinfo().getPricecalendar();
        addData(pricecalendarBeanList);

    }*/



}
