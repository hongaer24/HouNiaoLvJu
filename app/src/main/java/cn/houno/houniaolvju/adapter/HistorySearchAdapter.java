package cn.houno.houniaolvju.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import cn.houno.houniaolvju.R;

/**
 * 历史搜索适配器
 * <p>
 * Created by Administrator on 2017/1/17.
 */

public class HistorySearchAdapter extends SimpleCursorAdapter {

    private Cursor m_cursor;
    private Context m_context;
    private LayoutInflater mInflater;

    public HistorySearchAdapter(Context context, int layout, Cursor c
            , String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        m_context = context;
        m_cursor = c;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        ViewHolder holder = new ViewHolder();
        View inflate = mInflater.inflate(R.layout.listitem_search_history, null);
        holder.tvHistory = (TextView) inflate.findViewById(R.id.tv_history_title);
        inflate.setTag(holder);
        return inflate;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();
        String name = cursor.getString(cursor.getColumnIndex("name"));
        holder.tvHistory.setText(name);
    }

    class ViewHolder {
        TextView tvHistory;
    }
}
