package cn.houno.houniaolvju.activity.train;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.activity.ydhotel.YdDatePickerActivity;
import cn.houno.houniaolvju.utils.DateUtil;
import cn.houno.houniaolvju.utils.StatusBarUtils;
import cn.houno.houniaolvju.view.MyCalendar;


public class TrainDatePickerActivity extends Activity implements MyCalendar.OnDaySelectListener {

    public static final int RESULT_CODE = 300;
    ImageView ivBack;
    LinearLayout ll;
    MyCalendar c1;
    String nowday;
    long nd = 1000 * 24L * 60L * 60L;//一天的毫秒数
    SimpleDateFormat simpleDateFormat, sd1, sd2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_date_picker);
        StatusBarUtils.setWindowStatusBarColor(TrainDatePickerActivity.this, R.color.app_theme_green);
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        nowday = simpleDateFormat.format(new Date());
        sd1 = new SimpleDateFormat("yyyy");
        sd2 = new SimpleDateFormat("dd");
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ll = (LinearLayout) findViewById(R.id.ll);
        //findViewById(R.id.tv_toast).setVisibility(View.GONE);
        init();
    }

    private void init() {
        List<String> listDate = getDateList();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < listDate.size(); i++) {
            c1 = new MyCalendar(this);
            c1.setLayoutParams(params);
            Date date = null;
            try {
                date = simpleDateFormat.parse(listDate.get(i));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            c1.setTheDay(date);
            c1.setOnDaySelectListener(this);
            ll.addView(c1);
        }
    }

    @Override
    public void onDaySelectListener(View view, String date) {
        System.out.println(date);
        //若日历日期小于当前日期，或日历日期-当前日期超过三个月，则不能点击
        long selectDay=0L;
        long nowDay=0L;
        long dayxc=0L;
        try {
            if (simpleDateFormat.parse(date).getTime() < simpleDateFormat.parse(nowday).getTime()) {
                return;
            }
            dayxc = (simpleDateFormat.parse(date).getTime() - simpleDateFormat.parse(nowday).getTime()) / nd;
            if (dayxc > 90) {
                return;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        view.setBackgroundResource(R.drawable.shape_calendar_check_in);
        Intent intent = new Intent();

//        if(dayxc==1L){
//            intent.putExtra("week","明天");
//        }else if(dayxc>1L){
//            intent.putExtra("week",DateUtil.getEWeek(date));
//        }else{
//            intent.putExtra("week","今天");
//        }
        intent.putExtra("date",date);
        setResult(RESULT_CODE, intent);
        finish();
    }

    //根据当前日期，向后数三个月（若当前day不是1号，为满足至少90天，则需要向后数4个月）
    @SuppressLint("SimpleDateFormat")
    public List<String> getDateList() {
        List<String> list = new ArrayList<>();
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        int nowMon = calendar.get(Calendar.MONTH) + 1;
        //int nowMon = date.getMonth() + 1;
        String yyyy = sd1.format(date);
        String dd = sd2.format(date);
        if (nowMon == 9) {
            list.add(simpleDateFormat.format(date));
            list.add(yyyy + "-10-" + getMonthDays(yyyy+"-10"));
            list.add(yyyy + "-11-" + getMonthDays(yyyy+"-11"));
            if (!dd.equals("01")) {
                list.add(yyyy + "-12-" + getMonthDays(yyyy+"-12"));
            }
        } else if (nowMon == 10) {
            list.add(yyyy + "-10-" + getMonthDays(yyyy+"-10"));
            list.add(yyyy + "-11-" + getMonthDays(yyyy+"-11"));
            list.add(yyyy + "-12-" + getMonthDays(yyyy+"-12"));
            if (!dd.equals("01")) {
                list.add((Integer.parseInt(yyyy) + 1) + "-01-" + getMonthDays((Integer.parseInt(yyyy) + 1) + "-01" ));
            }
        } else if (nowMon == 11) {
            list.add(yyyy + "-11-" + getMonthDays(yyyy + "-11" ));
            list.add(yyyy + "-12-" + getMonthDays(yyyy + "-12" ));
            list.add((Integer.parseInt(yyyy) + 1) + "-01-" + getMonthDays((Integer.parseInt(yyyy) + 1) + "-01" ));
            if (!dd.equals("01")) {
                list.add((Integer.parseInt(yyyy) + 1) + "-02-" + getMonthDays((Integer.parseInt(yyyy) + 1)+ "-02" ));
            }
        } else if (nowMon == 12) {
            list.add(yyyy + "-12-" + dd);
            list.add((Integer.parseInt(yyyy) + 1) + "-01-" + getMonthDays((Integer.parseInt(yyyy) + 1) + "-01" ));
            list.add((Integer.parseInt(yyyy) + 1) + "-02-" + getMonthDays((Integer.parseInt(yyyy) + 1) + "-02" ));
            if (!dd.equals("01")) {
                list.add((Integer.parseInt(yyyy) + 1) + "-03-" + getMonthDays((Integer.parseInt(yyyy) + 1) + "-03" ));
            }
        } else {
            list.add(yyyy + "-" + getMon(nowMon) + "-" + getMonthDays(yyyy + "-" + getMon(nowMon)));
            list.add(yyyy + "-" + getMon((nowMon + 1)) + "-" + getMonthDays(yyyy + "-" + getMon(nowMon+1)));
            list.add(yyyy + "-" + getMon((nowMon + 2)) + "-" + getMonthDays(yyyy + "-" + getMon(nowMon+2)));
            if (!dd.equals("01")) {
                list.add(yyyy + "-" + getMon((nowMon + 3)) + "-" + getMonthDays(yyyy + "-" + getMon(nowMon+3)));
            }
        }
        return list;
    }

    public String getMon(int mon) {
        String month;
        if (mon < 10) {
            month = "0" + mon;
        } else {
            month = "" + mon;
        }
        return month;
    }
    private int  getMonthDays(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar calendar = new GregorianCalendar();
        Date date1 = null;
        try {
            date1 = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date1); //放入你的日期
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

}
