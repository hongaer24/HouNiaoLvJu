package cn.houno.houniaolvju.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

/**
 * 项目名称：Houniaolvju
 * 类描述：
 * 创建人：qzc
 * 创建时间：2016/12/26 10:17
 * 修改人：qzc
 * 修改时间：2016/12/26 10:17
 * 修改备注：
 */
public class InnerExpandableListView extends ExpandableListView {

    public InnerExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,

                MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
