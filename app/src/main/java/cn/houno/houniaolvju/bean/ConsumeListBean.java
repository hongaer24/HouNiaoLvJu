package cn.houno.houniaolvju.bean;

import java.util.ArrayList;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：消费记录列表bean
 * 创建人：qzc
 * 创建时间：2016/11/25 13:45
 * 修改人：qzc
 * 修改时间：2016/11/25 13:45
 * 修改备注：
 */
public class ConsumeListBean {


    /**
     * status : 0
     * count : 3
     * data : [{"id":"15","l_id":"20161102000001","c_id":"21","l_flag":"2","l_cardno":"800520","l_xlno":"","l_name":"","l_moneyin":"20.0000","l_moneyout":"30.0000","l_consumptionsum":".0000","l_consumeproject":"景点消费","l_createdate":"2016-11-02 14:57:13.000","l_remark":"充值","row_number":"2"},{"id":"16","l_id":"20161108000001","c_id":"21","l_flag":"2","l_cardno":"800520","l_xlno":"","l_name":"","l_moneyin":"30.0000","l_moneyout":"40.0000","l_consumptionsum":".0000","l_consumeproject":"活动消费","l_createdate":"2016-11-08 17:18:52.000","l_remark":"充值","row_number":"3"}]
     * msg : 数据获取成功
     */

    private int status;
    private String count;
    private String msg;
    /**
     * id : 15
     * l_id : 20161102000001
     * c_id : 21
     * l_flag : 2
     * l_cardno : 800520
     * l_xlno :
     * l_name :
     * l_moneyin : 20.0000
     * l_moneyout : 30.0000
     * l_consumptionsum : .0000
     * l_consumeproject : 景点消费
     * l_createdate : 2016-11-02 14:57:13.000
     * l_remark : 充值
     * row_number : 2
     */

    private ArrayList<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String l_id;
        private String c_id;
        private String l_flag;
        private String l_cardno;
        private String l_xlno;
        private String l_name;
        private String l_moneyin;
        private String l_moneyout;
        private String l_consumptionsum;
        private String l_consumeproject;
        private String l_createdate;
        private String l_remark;
        private String row_number;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getL_id() {
            return l_id;
        }

        public void setL_id(String l_id) {
            this.l_id = l_id;
        }

        public String getC_id() {
            return c_id;
        }

        public void setC_id(String c_id) {
            this.c_id = c_id;
        }

        public String getL_flag() {
            return l_flag;
        }

        public void setL_flag(String l_flag) {
            this.l_flag = l_flag;
        }

        public String getL_cardno() {
            return l_cardno;
        }

        public void setL_cardno(String l_cardno) {
            this.l_cardno = l_cardno;
        }

        public String getL_xlno() {
            return l_xlno;
        }

        public void setL_xlno(String l_xlno) {
            this.l_xlno = l_xlno;
        }

        public String getL_name() {
            return l_name;
        }

        public void setL_name(String l_name) {
            this.l_name = l_name;
        }

        public String getL_moneyin() {
            return l_moneyin;
        }

        public void setL_moneyin(String l_moneyin) {
            this.l_moneyin = l_moneyin;
        }

        public String getL_moneyout() {
            return l_moneyout;
        }

        public void setL_moneyout(String l_moneyout) {
            this.l_moneyout = l_moneyout;
        }

        public String getL_consumptionsum() {
            return l_consumptionsum;
        }

        public void setL_consumptionsum(String l_consumptionsum) {
            this.l_consumptionsum = l_consumptionsum;
        }

        public String getL_consumeproject() {
            return l_consumeproject;
        }

        public void setL_consumeproject(String l_consumeproject) {
            this.l_consumeproject = l_consumeproject;
        }

        public String getL_createdate() {
            return l_createdate;
        }

        public void setL_createdate(String l_createdate) {
            this.l_createdate = l_createdate;
        }

        public String getL_remark() {
            return l_remark;
        }

        public void setL_remark(String l_remark) {
            this.l_remark = l_remark;
        }

        public String getRow_number() {
            return row_number;
        }

        public void setRow_number(String row_number) {
            this.row_number = row_number;
        }
    }
}
