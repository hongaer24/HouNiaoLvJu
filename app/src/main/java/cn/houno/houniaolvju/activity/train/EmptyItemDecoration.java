package cn.houno.houniaolvju.activity.train;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;



public class EmptyItemDecoration extends RecyclerView.ItemDecoration {

    private static final String TAG = EmptyItemDecoration.class.getSimpleName();
    private int left, right, top, botoom, outLeft, outRight, outTop, outBotoom;
    private boolean div;

    public EmptyItemDecoration(int left, int right, int top, int botoom) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.botoom = botoom;
    }

    public EmptyItemDecoration(int left, int right, int top, int botoom, boolean div,
                               int outLeft, int outRight, int outTop, int outBotoom) {
        this.div = div;
        this.left = left;
        this.right = right;
        this.top = top;
        this.botoom = botoom;
        this.outLeft = outLeft;
        this.outRight = outRight;
        this.outTop = outTop;
        this.outBotoom = outBotoom;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        int itemCount = parent.getAdapter().getItemCount();
        int position = parent.getLayoutManager().getPosition(view);

        if (div) {
            RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {

                int spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
                int rowCount = 0;
                if (itemCount % spanCount == 0) {
                    rowCount = itemCount / spanCount;
                } else {
                    rowCount = itemCount / spanCount + 1;
                }


                if (position < spanCount) {
                    outRect.top = outTop;
                } else {
                    outRect.top = top;
                }
                if (position % spanCount == 0) {
                    outRect.left = outLeft;
                } else {
                    outRect.left = left;
                }
                if (position % spanCount == spanCount - 1) {
                    outRect.right = outRight;
                } else {
                    outRect.right = right;
                }
                if (position / spanCount == rowCount - 1) {
                    outRect.bottom = outBotoom;
                } else {
                    outRect.bottom = botoom;
                }
            }

        } else {
            outRect.left = left;
            outRect.right = right;
            outRect.bottom = botoom;
            outRect.top = top;
        }

    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }
}

