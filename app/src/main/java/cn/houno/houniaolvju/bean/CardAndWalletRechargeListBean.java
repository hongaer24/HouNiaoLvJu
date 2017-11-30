package cn.houno.houniaolvju.bean;

import java.util.ArrayList;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述： 充值记录列表
 * 创建人：qzc
 * 创建时间：2016/11/25 10:25
 * 修改人：qzc
 * 修改时间：2016/11/25 10:25
 * 修改备注：
 */
public class CardAndWalletRechargeListBean {

    /**
     * status : 0
     * count : 6
     * data : [{"id":"48","orderno":"20161118163922342520386","uid":"386","add_time":"1479458469","update_time":"1479458469","premoney":".14","money":"0.01","lastmoney":".15","pay_status":"1","paytype":"支付宝","trade_no":"201611181639226413208","row_number":"1"},{"id":"47","orderno":"20161118162207365190386","uid":"386","add_time":"1479457338","update_time":"1479457338","premoney":".13","money":"0.01","lastmoney":".14","pay_status":"1","paytype":"支付宝","trade_no":"2016111821001004070244872050","row_number":"2"},{"id":"46","orderno":"20161118162117621540386","uid":"386","add_time":"1479457313","update_time":"1479457313","premoney":".12","money":"0.01","lastmoney":".13","pay_status":"1","paytype":"支付宝","trade_no":"2016111821001004070244254574","row_number":"3"},{"id":"45","orderno":"20161118161329338140386","uid":"386","add_time":"1479456823","update_time":"1479456823","premoney":".11","money":"0.01","lastmoney":".12","pay_status":"1","paytype":"支付宝","trade_no":"2016111821001004070244342336","row_number":"4"},{"id":"44","orderno":"20161118161154263170386","uid":"386","add_time":"1479456730","update_time":"1479456730","premoney":".01","money":"0.10","lastmoney":".11","pay_status":"1","paytype":"支付宝","trade_no":"2016111821001004070244342277","row_number":"5"},{"id":"43","orderno":"20161118153406981170386","uid":"386","add_time":"1479454466","update_time":"1479454466","premoney":".00","money":"0.01","lastmoney":".01","pay_status":"1","paytype":"支付宝","trade_no":"2016111821001004070254040025","row_number":"6"}]
     * msg : 数据获取成功
     */

    private int status;
    private String count;
    private String msg;
    /**
     * id : 48
     * orderno : 20161118163922342520386
     * uid : 386
     * add_time : 1479458469
     * update_time : 1479458469
     * premoney : .14
     * money : 0.01
     * lastmoney : .15
     * pay_status : 1
     * paytype : 支付宝
     * trade_no : 201611181639226413208
     * row_number : 1
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
        private String orderno;
        private String uid;
        private String add_time;
        private String update_time;
        private String premoney;
        private String money;
        private String lastmoney;
        private String pay_status;
        private String paytype;
        private String trade_no;
        private String row_number;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrderno() {
            return orderno;
        }

        public void setOrderno(String orderno) {
            this.orderno = orderno;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getPremoney() {
            return premoney;
        }

        public void setPremoney(String premoney) {
            this.premoney = premoney;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getLastmoney() {
            return lastmoney;
        }

        public void setLastmoney(String lastmoney) {
            this.lastmoney = lastmoney;
        }

        public String getPay_status() {
            return pay_status;
        }

        public void setPay_status(String pay_status) {
            this.pay_status = pay_status;
        }

        public String getPaytype() {
            return paytype;
        }

        public void setPaytype(String paytype) {
            this.paytype = paytype;
        }

        public String getTrade_no() {
            return trade_no;
        }

        public void setTrade_no(String trade_no) {
            this.trade_no = trade_no;
        }

        public String getRow_number() {
            return row_number;
        }

        public void setRow_number(String row_number) {
            this.row_number = row_number;
        }
    }
}
