package cn.houno.houniaolvju.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：带边框的TextView
 * 创建人：qzc
 * 创建时间：2016/11/1 11:09
 * 修改人：qzc
 * 修改时间：2016/11/1 11:09
 * 修改备注：
 */
public class BorderTextView extends TextView {
    private Paint paint = null;
    private int color;
    private String colorStr;

    public BorderTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BorderTextView(Context context, String colorStr) {
        super(context);
        this.colorStr = colorStr;
    }


    //设置边框颜色
    public void setPaintColor(int color) {
        this.color = color;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = new Paint();
        //给边框设置颜色
        if (colorStr == null) {
            paint.setColor(Color.parseColor("#ff0099cc"));
        } else {
            paint.setColor(Color.parseColor(colorStr));
        }
        //上
        canvas.drawLine(0, 0, this.getWidth() - 1, 0, paint);
        //左
        canvas.drawLine(0, 0, 0, this.getHeight() - 1, paint);
        //下
        canvas.drawLine(0, this.getHeight() - 1, this.getWidth() - 1, this.getHeight() - 1, paint);
        //右
        canvas.drawLine(this.getWidth() - 1, 0, this.getWidth() - 1, this.getHeight() - 1, paint);
    }
}
