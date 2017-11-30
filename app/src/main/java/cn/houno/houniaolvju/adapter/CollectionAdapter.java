package cn.houno.houniaolvju.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.CollectionBean.DataEntity;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.PrefUtils;

/**
 * 作 者：陈亮
 * <p/>
 * 版本1.0
 * <p/>
 * 描述：
 * <p/>
 * 修订历史:
 */
public class CollectionAdapter extends BaseAdapter {


    private LayoutInflater mInflater;
    private ArrayList<DataEntity> list;
    private Context context;
    private DataEntity datainfo;


    public  CollectionAdapter(Context context, ArrayList<DataEntity> list) {
        this.context = context;
        this.list = list;
        mInflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<DataEntity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.listitem_collection, parent, false);
            holder = new ViewHolder();
            holder.ivscimg = (ImageView) convertView.findViewById(R.id.iv_collection_img);
            holder.tvscname = (TextView) convertView.findViewById(R.id.tv_jdname);
            holder.tvjgday = (TextView) convertView.findViewById(R.id.tv_jgday);
            holder.tvjgdh = (TextView) convertView.findViewById(R.id.tv_sc_dh);
            holder.tvsite = (TextView) convertView.findViewById(R.id.tv_site);
            holder.btnqx = (Button) convertView.findViewById(R.id.btn_sc_qx);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        datainfo = list.get(position);

        x.image().bind(holder.ivscimg, datainfo.getInfo().getImg());
        holder.tvscname.setText(datainfo.getInfo().getTitle());
        holder.tvjgday.setText(datainfo.getInfo().getWebprice());
        holder.tvjgdh.setText(datainfo.getInfo().getTelphone());
        holder.tvsite.setText(datainfo.getInfo().getAddress());


        holder.btnqx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHttpData(position);

            }
        });
        return convertView;
    }

    public void getHttpData(final int position) {
        datainfo = list.get(position);
        String userid = PrefUtils.getString(context, "userid", "");
        String favid = datainfo.getId();
        System.out.println("favid" + favid);
        RequestParams params = new RequestParams(Constants.CANCELSC_URL);
        params.addBodyParameter("userid", userid);
        params.addBodyParameter("favid", favid);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println(result);
                try {
                    JSONObject json = new JSONObject(result);
                    int status = json.getInt("status");
                    String msg = json.getString("msg");
                    if (status == 1) {
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                    } else if (status == 0) {
                        list.remove(position);
                        setData(list);

                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
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

    }

    static class ViewHolder {
        ImageView ivscimg;
        TextView tvscname;
        TextView tvjgday;
        TextView tvjgdh;
        TextView tvsite;
        Button btnqx;
    }
}
