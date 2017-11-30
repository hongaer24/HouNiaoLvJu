package cn.houno.houniaolvju.activity.ydhotel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.utils.DateUtil;
import cn.houno.houniaolvju.utils.StatusBarUtils;
import cn.houno.houniaolvju.view.MyCalendar;
import cn.houno.houniaolvju.view.MyCalendar.OnDaySelectListener;

/**
 * 项目名称：Houniaolvju
 * 类描述：
 * 创建人：qzc
 * 创建时间：2016/01/15 17:15
 * 修改人：qzc
 * 修改时间：2016/01/15 17:15
 * 修改备注：
 */
@SuppressLint("SimpleDateFormat")
public class YdDatePickerActivity extends Activity implements OnDaySelectListener {

    public static final int RESULT_CODE = 300;
    ImageView ivBack;
    LinearLayout ll;
    MyCalendar c1;
    Date date;
    String nowday;
    long nd = 1000 * 24L * 60L * 60L;//一天的毫秒数
    SimpleDateFormat simpleDateFormat, sd1, sd2;

    private String inday, outday;
    private int mRzdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_date_picker);
        StatusBarUtils.setWindowStatusBarColor(YdDatePickerActivity.this, R.color.app_theme_green);

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

        Intent intent = getIntent();
        mRzdate = intent.getIntExtra("rzdate", 1);

        init();
    }

    private void init() {
        List<String> listDate = getDateList();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
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
        try {
            if (simpleDateFormat.parse(date).getTime() < simpleDateFormat.parse(nowday).getTime()) {
                return;
            }
            long dayxc = (simpleDateFormat.parse(date).getTime() - simpleDateFormat.parse(nowday).getTime()) / nd;
            if (dayxc > 90) {
                return;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String dateDay = date.split("-")[2];
        if (Integer.parseInt(dateDay) < 10) {
            dateDay = date.split("-")[2].replace("0", "");
        }
        TextView textDayView = (TextView) view.findViewById(R.id.tv_calendar_day);
        TextView textView = (TextView) view.findViewById(R.id.tv_calendar);

        //	view.setBackgroundColor(Color.parseColor("#009a44"));

        textDayView.setTextColor(Color.WHITE);
        if (null == inday || inday.equals("")) {
            view.setBackgroundResource(R.drawable.shape_calendar_check_in);
            if (!textDayView.getText().toString().equals("今天")) {    //如果是今天就不改变号码
                textDayView.setText(dateDay);
            }
            textView.setText("入住");
            inday = date;
            if (mRzdate != 1) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("dateIn", inday);
                bundle.putString("dateInWeek", DateUtil.getWeek(inday));
                intent.putExtras(bundle);
                setResult(RESULT_CODE, intent);

                finish();
            }
        } else {
            if (inday.equals(date)) {
                //	 view.setBackgroundColor(Color.WHITE);
                view.setBackgroundResource(R.drawable.shape_normal_check);
                if (textDayView.getText().toString().equals("今天")) {    //如果是今天就不改变号码
                    textDayView.setText("今天");
                    textDayView.setTextColor(Color.parseColor("#FF6600"));
                } else {
                    textDayView.setText(dateDay);
                    textDayView.setTextColor(Color.BLACK);
                }
                textView.setText("");
                inday = "";

            } else {
                try {
                    if (simpleDateFormat.parse(date).getTime() < simpleDateFormat.parse(inday).getTime()) {
                        //		view.setBackgroundColor(Color.WHITE);
                        //view.setBackgroundResource(R.drawable.selector_bg);
                        if (textDayView.getText().toString().equals("今天")) {    //如果是今天就不改变号码
                            textDayView.setTextColor(Color.parseColor("#FF6600"));
                        } else {
                            textDayView.setTextColor(Color.BLACK);
                        }
                        Toast.makeText(YdDatePickerActivity.this, "离开日期不能小于入住日期", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                view.setBackgroundResource(R.drawable.shape_calendar_check_out);
                textDayView.setText(dateDay);
                textView.setText("离开");
                outday = date;
                int days = DateUtil.getDays(inday, outday);
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("dateIn", inday);
                bundle.putString("dateInWeek", DateUtil.getWeek(inday));
                bundle.putString("dateOut", outday);
                bundle.putString("dateOutWeek", DateUtil.getWeek(outday));
                bundle.putString("days", days + "");
                intent.putExtras(bundle);
                setResult(RESULT_CODE, intent);
                finish();
            }
        }
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
            list.add(yyyy + "-10-" + dd);
            list.add(yyyy + "-11-" + dd);
            if (!dd.equals("01")) {
                list.add(yyyy + "-12-" + dd);
            }
        } else if (nowMon == 10) {
            list.add(yyyy + "-10-" + dd);
            list.add(yyyy + "-11-" + dd);
            list.add(yyyy + "-12-" + dd);
            if (!dd.equals("01")) {
                list.add((Integer.parseInt(yyyy) + 1) + "-01-" + dd);
            }
        } else if (nowMon == 11) {
            list.add(yyyy + "-11-" + dd);
            list.add(yyyy + "-12-" + dd);
            list.add((Integer.parseInt(yyyy) + 1) + "-01-" + dd);
            if (!dd.equals("01")) {
                list.add((Integer.parseInt(yyyy) + 1) + "-02-" + dd);
            }
        } else if (nowMon == 12) {
            list.add(yyyy + "-12-" + dd);
            list.add((Integer.parseInt(yyyy) + 1) + "-01-" + dd);
            list.add((Integer.parseInt(yyyy) + 1) + "-02-" + dd);
            if (!dd.equals("01")) {
                list.add((Integer.parseInt(yyyy) + 1) + "-03-" + dd);
            }
        } else {
            list.add(yyyy + "-" + getMon(nowMon) + "-" + dd);
            list.add(yyyy + "-" + getMon((nowMon + 1)) + "-" + dd);
            list.add(yyyy + "-" + getMon((nowMon + 2)) + "-" + dd);
            if (!dd.equals("01")) {
                list.add(yyyy + "-" + getMon((nowMon + 3)) + "-" + dd);
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


}
