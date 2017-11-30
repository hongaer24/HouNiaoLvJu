package cn.houno.houniaolvju.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.xutils.x;

import java.util.List;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.activity.hotel.FillInHotelOrderActivity;
import cn.houno.houniaolvju.bean.HotelDetailBean.DataBean.InfoBean.AllTodayRoomBean;
import cn.houno.houniaolvju.fragment.myinfo.LoginActivity;
import cn.houno.houniaolvju.utils.DisplayUtil;
import cn.houno.houniaolvju.utils.MyText2Utils;
import cn.houno.houniaolvju.utils.PrefUtils;

/**
 * 项目名称：Houniaolvju
 * 类描述：酒店房间价格列表
 * 创建人：qzc
 * 创建时间：2016/12/24 16:50
 * 修改人：qzc
 * 修改时间：2016/12/24 16:50
 * 修改备注：
 */
public class HotelRoomListAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<AllTodayRoomBean> groupArray;
    private String hotelTitle;
    private String hotelAddress;
    private String checkIn;
    private String checkOut;

    public HotelRoomListAdapter(Context context
            , List<AllTodayRoomBean> groupArray) {
        mContext = context;
        this.groupArray = groupArray;
    }

    public void setData(List<AllTodayRoomBean> groupArray, String hotelTitle, String hotelAddress) {
        this.groupArray = groupArray;
        this.hotelTitle = hotelTitle;
        this.hotelAddress = hotelAddress;
        notifyDataSetChanged();
    }


    public void setCheckDate(String checkIn, String checkOut) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    @Override
    public int getGroupCount() {
        return groupArray.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groupArray.get(groupPosition).getRoom().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupArray.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groupArray.get(groupPosition).getRoom().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return getCombinedChildId(groupPosition, childPosition);
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder groupHolder;
        if (convertView == null) {
            groupHolder = new GroupHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listitem_room_parent, parent, false);
            groupHolder.img = (ImageView) convertView.findViewById(R.id.iv_room_parent_img);
            groupHolder.title = (TextView) convertView.findViewById(R.id.tv_room_parent_title);
            groupHolder.price = (TextView) convertView.findViewById(R.id.tv_room_parent_price);
            groupHolder.info = (TextView) convertView.findViewById(R.id.tv_room_parent_info);
            groupHolder.more = (ImageView) convertView.findViewById(R.id.iv_room_parent_more);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag();
        }
        x.image().bind(groupHolder.img, groupArray.get(groupPosition).getImg(), DisplayUtil.getImageOptions());

        groupHolder.title.setText(groupArray.get(groupPosition).getTitle());
        String webprice = groupArray.get(groupPosition).getRoom().get(0).getWebprice();
        if (webprice != null && !TextUtils.isEmpty(webprice)) {
            MyText2Utils.formatQiPrice(mContext, groupHolder.price, webprice);
        } else {
            groupHolder.price.setText("");
        }
        String info = groupArray.get(groupPosition).getRoom().get(0).getBed_type().trim() + "  "
                + groupArray.get(groupPosition).getRoom().get(0).getAdsl().trim()
                + "  可入住" + groupArray.get(groupPosition).getRoom().get(0).getPeople_num().trim()
                + "人\n" + groupArray.get(groupPosition).getRoom().get(0).getArea().trim() + "平方";
        groupHolder.info.setText(info);

        //判断是否已经打开列表
        if (isExpanded) {
            groupHolder.more.setImageResource(R.drawable.icon_room_parent_up);
        } else {
            groupHolder.more.setImageResource(R.drawable.icon_room_parent_down);
        }
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder childHolder;
        if (convertView == null) {
            childHolder = new ChildHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listitem_room_child, parent, false);
            childHolder.merName = (TextView) convertView.findViewById(R.id.tv_room_child_mername);
            childHolder.subName = (TextView) convertView.findViewById(R.id.tv_room_child_subname);
            childHolder.breakfast = (TextView) convertView.findViewById(R.id.tv_room_child_breakfast);
            childHolder.llTgRule = (LinearLayout) convertView.findViewById(R.id.tv_tg_rule);
            childHolder.tvTgPrice = (TextView) convertView.findViewById(R.id.tv_tg_price);

            childHolder.price = (TextView) convertView.findViewById(R.id.tv_room_child_price);
            childHolder.rlBook = (RelativeLayout) convertView.findViewById(R.id.rl_room_child_book);
            childHolder.book = (TextView) convertView.findViewById(R.id.tv_room_child_book);
            childHolder.payfs = (TextView) convertView.findViewById(R.id.tv_room_child_payfs);
            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) convertView.getTag();
        }

        childHolder.merName.setText(groupArray.get(groupPosition).getRoom().get(childPosition).getMerName());
        childHolder.subName.setText(groupArray.get(groupPosition).getRoom().get(childPosition).getSubName());
        childHolder.breakfast.setText(groupArray.get(groupPosition).getRoom().get(childPosition).getBreakfast());

        String payfs = groupArray.get(groupPosition).getRoom().get(childPosition).getPayfs();
        childHolder.payfs.setText(payfs);


        String webprice = groupArray.get(groupPosition).getRoom().get(childPosition).getWebprice();
        final String isTg, stock;
        if (webprice != null && !TextUtils.isEmpty(webprice)) {
            //有价格
            childHolder.payfs.setVisibility(View.VISIBLE);
            childHolder.book.setText("订");
            MyText2Utils.formatTicketPrice(mContext, childHolder.price, webprice);

            //库存-大于0有库存 否则没库存
            stock = groupArray.get(groupPosition).getRoom().get(childPosition).getStock();
            if (stock == null || TextUtils.isEmpty(stock) || stock.equals("0")) {
                //stock=null或者stoc=""或者stock=0都是没有库存
                childHolder.payfs.setVisibility(View.GONE);
                childHolder.book.setText("无房");
                childHolder.price.setText("");
            } else {
                childHolder.payfs.setVisibility(View.VISIBLE);
                childHolder.book.setText("订");
                MyText2Utils.formatTicketPrice(mContext, childHolder.price, webprice);

                isTg = groupArray.get(groupPosition).getRoom().get(childPosition).getIstg();
                if (isTg.equals("1")) {
                    //团购
                    String discount_money = groupArray.get(groupPosition)
                            .getRoom().get(childPosition).getTg().getDiscount_money();
                    childHolder.llTgRule.setVisibility(View.VISIBLE);
                    childHolder.tvTgPrice.setText("¥" + discount_money + "元/间夜");
                } else {
                    childHolder.llTgRule.setVisibility(View.GONE);
                }

                childHolder.rlBook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent();
                        intent.putExtra("title", hotelTitle);
                        intent.putExtra("address", hotelAddress);
                        intent.putExtra("hid", groupArray.get(groupPosition).getHid());
                        intent.putExtra("rid", groupArray.get(groupPosition).getRoom().get(childPosition).getRid());
                        intent.putExtra("model", "hotel");  //区分国内酒店还是国外酒店
                        if (isTg.equals("1")) {
                            intent.putExtra("type", "2");
                            intent.putExtra("starttime", groupArray.get(groupPosition).getRoom().get(childPosition).getTg().getStart_time());
                            intent.putExtra("endtime", groupArray.get(groupPosition).getRoom().get(childPosition).getTg().getEnd_time());
                        } else {
                            intent.putExtra("type", "0");
                        }
                        intent.putExtra("ouid", groupArray.get(groupPosition).getRoom().get(childPosition).getOuid());
                        intent.putExtra("price", Integer.parseInt(
                                MyText2Utils.getIntPrice(groupArray.get(
                                        groupPosition).getRoom().get(childPosition).getWebprice())));
                        intent.putExtra("room", groupArray.get(groupPosition).getTitle());
                        intent.putExtra("payfs", groupArray.get(groupPosition).getRoom().get(childPosition).getPayfs());
                        intent.putExtra("checkin",checkIn);
                        intent.putExtra("checkout", checkOut);
                        intent.setClass(mContext, FillInHotelOrderActivity.class);
                        mContext.startActivity(intent);

                    }
                });
            }

        } else {
            //没有价格
            childHolder.payfs.setVisibility(View.GONE);
            childHolder.book.setText("无房");
            childHolder.price.setText("");
            childHolder.rlBook.setClickable(false);
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    class GroupHolder {
        public ImageView img;
        public TextView title;
        public TextView price;
        public TextView info;
        public ImageView more;
    }

    class ChildHolder {
        public TextView merName;
        public TextView subName;
        public TextView breakfast;
        public LinearLayout llTgRule;
        public TextView tvTgPrice;
        public TextView price;
        public RelativeLayout rlBook;
        public TextView book;
        public TextView payfs;

    }
}
