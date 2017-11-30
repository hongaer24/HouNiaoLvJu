package cn.houno.houniaolvju.bean;

import java.util.ArrayList;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：我的钱包-返现列表
 * 创建人：qzc
 * 创建时间：2016/12/8 16:04
 * 修改人：qzc
 * 修改时间：2016/12/8 16:04
 * 修改备注：
 */
public class WalletBackRecordBean {


    /**
     * status : 0
     * count : 2
     * data : [{"id":"26","uid":"386","username":"Android测试","amout_type":"团购返现","pre_amount":"20.00","amount":"20.00","last_amount":"40.00","add_time":"2016-12-01","row_number":"1"},{"id":"24","uid":"386","username":"Android测试","amout_type":"团购返现","pre_amount":".00","amount":"20.00","last_amount":"20.00","add_time":"2016-12-01","row_number":"2"}]
     * msg : 数据获取成功
     */

    private int status;
    private String count;
    private String msg;
    /**
     * id : 26
     * uid : 386
     * username : Android测试
     * amout_type : 团购返现
     * pre_amount : 20.00
     * amount : 20.00
     * last_amount : 40.00
     * add_time : 2016-12-01
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
        private String uid;
        private String username;
        private String amout_type;
        private String pre_amount;
        private String amount;
        private String last_amount;
        private String add_time;
        private String row_number;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAmout_type() {
            return amout_type;
        }

        public void setAmout_type(String amout_type) {
            this.amout_type = amout_type;
        }

        public String getPre_amount() {
            return pre_amount;
        }

        public void setPre_amount(String pre_amount) {
            this.pre_amount = pre_amount;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getLast_amount() {
            return last_amount;
        }

        public void setLast_amount(String last_amount) {
            this.last_amount = last_amount;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getRow_number() {
            return row_number;
        }

        public void setRow_number(String row_number) {
            this.row_number = row_number;
        }
    }
}
