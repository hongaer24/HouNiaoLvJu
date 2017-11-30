package cn.houno.houniaolvju.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.FlightCity;
import cn.houno.houniaolvju.utils.PinyinUtils;
import cn.houno.houniaolvju.view.InnerGridView;

/**
 * 机票城市
 * Created by qzc on 2017/1/26.
 */
public class FlightCityListAdapter extends BaseAdapter {
    private static final int VIEW_TYPE_COUNT = 2;

    private Context mContext;
    private LayoutInflater inflater;
    private List<FlightCity> mHotCities;
    private List<FlightCity> mCities;
    private HashMap<String, Integer> letterIndexes;
    private String[] sections;
    private OnCityClickListener onCityClickListener;

    public FlightCityListAdapter(Context mContext, List<FlightCity> hotCitys, List<FlightCity> cities) {
        this.mContext = mContext;
        this.mHotCities = hotCitys;
        this.mCities = cities;
        inflater = LayoutInflater.from(mContext);

        int size = mCities.size();
        letterIndexes = new HashMap<>();
        sections = new String[size];
        for (int index = 0; index < size; index++) {
            //当前城市拼音首字母
            String currentLetter = PinyinUtils.getFirstLetter(mCities.get(index).getJianpin());
            //上个首字母，如果不存在设为""
            String previousLetter = index >= 1 ? PinyinUtils.getFirstLetter(mCities.get(index - 1).getJianpin()) : "";
            if (!TextUtils.equals(currentLetter, previousLetter)) {
                letterIndexes.put(currentLetter, index);
                sections[index] = currentLetter;
            }
        }

    }



    /**
     * 获取字母索引的位置
     *
     * @param letter
     * @return
     */
    public int getLetterPosition(String letter) {
        Integer integer = letterIndexes.get(letter);
        return integer == null ? -1 : integer;
    }


    public void setData(List<FlightCity> hotCitys, List<FlightCity> cities) {
        this.mHotCities = hotCitys;
        mCities = cities;
        notifyDataSetChanged();

        int size = mCities.size();
        letterIndexes = new HashMap<>();
        sections = new String[size];
        for (int index = 0; index < size; index++) {
            //当前城市拼音首字母
            String currentLetter = PinyinUtils.getFirstLetter(mCities.get(index).getJianpin());
            //上个首字母，如果不存在设为""
            String previousLetter = index >= 1 ? PinyinUtils.getFirstLetter(mCities.get(index - 1).getJianpin()) : "";
            if (!TextUtils.equals(currentLetter, previousLetter)) {
                letterIndexes.put(currentLetter, index);
                sections[index] = currentLetter;
            }
        }
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        return position < VIEW_TYPE_COUNT - 1 ? position : VIEW_TYPE_COUNT - 1;
    }

    @Override
    public int getCount() {
        return mCities == null ? 0 : mCities.size();
    }

    @Override
    public Object getItem(int position) {
        return mCities == null ? null : mCities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        CityViewHolder holder;
        int viewType = getItemViewType(position);
        switch (viewType) {

            case 0:     //热门
                view = inflater.inflate(R.layout.cp_view_hot_city, parent, false);
                InnerGridView gridView = (InnerGridView) view.findViewById(R.id.gridview_hot_city);
                HotCityGridAdapter hotCityGridAdapter = new HotCityGridAdapter(mContext, mHotCities);
                gridView.setAdapter(hotCityGridAdapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (onCityClickListener != null) {
                            onCityClickListener.onCityClick(mHotCities.get(position));
                        }
                    }
                });
                break;
            case 1:     //所有
                if (view == null) {
                    view = inflater.inflate(R.layout.cp_item_city_listview, parent, false);
                    holder = new CityViewHolder();
                    holder.letter = (TextView) view.findViewById(R.id.tv_item_city_listview_letter);
                    holder.name = (TextView) view.findViewById(R.id.tv_item_city_listview_name);
                    view.setTag(holder);
                } else {
                    holder = (CityViewHolder) view.getTag();
                }
                String cityName = mCities.get(position).getAreaname();
                holder.name.setText(cityName);
                String currentLetter = PinyinUtils.getFirstLetter(mCities.get(position).getJianpin());
                String previousLetter = position >= 1 ? PinyinUtils.getFirstLetter(mCities.get(position - 1).getJianpin()) : "";
                if (!TextUtils.equals(currentLetter, previousLetter)) {
                    holder.letter.setVisibility(View.VISIBLE);
                    holder.letter.setText(currentLetter);
                } else {
                    holder.letter.setVisibility(View.GONE);
                }
                holder.name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onCityClickListener != null) {
                            onCityClickListener.onCityClick(mCities.get(position));
                        }
                    }
                });

                break;
        }
        return view;
    }

    public static class CityViewHolder {
        TextView letter;
        TextView name;
    }

    public void setOnCityClickListener(OnCityClickListener listener) {
        this.onCityClickListener = listener;
    }

    public interface OnCityClickListener {
        void onCityClick(FlightCity city);

    }
}

