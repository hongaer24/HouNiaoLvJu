package cn.houno.houniaolvju.view;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：自定义Gridview
 * 创建人：qzc
 * 创建时间：2016/10/5 22:52
 * 修改人：qzc
 * 修改时间：2016/10/5 22:52
 * 修改备注：
 */
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

public class InnerGridView extends GridView {
    public InnerGridView(Context context) {
        super(context);

    }

    public InnerGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            return true;  //禁止GridView滑动
        }
        return super.dispatchTouchEvent(ev);
    }
}