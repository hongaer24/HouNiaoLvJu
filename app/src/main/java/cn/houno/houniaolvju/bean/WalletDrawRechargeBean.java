package cn.houno.houniaolvju.bean;

import java.util.ArrayList;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：我的钱包-提现记录
 * 创建人：qzc
 * 创建时间：2016/12/9 20:43
 * 修改人：qzc
 * 修改时间：2016/12/9 20:43
 * 修改备注：
 */
public class WalletDrawRechargeBean {


    /**
     * status : 0
     * count : 4
     * data : [{"id":"34","uid":"386","user_name":"e","apply_time":"1481285381","draw_type":"银联","bank_type":"44404","card_account":"www","phone":"the","balance":"8806.00","withdrawals":"123.00","with_time":"0","status":"1","remark":"","sort":"0","operation_id":"0","with_num":"0","row_number":"1","amout_type":"余额提现"},{"id":"32","uid":"386","user_name":"the","apply_time":"1481280909","draw_type":"银联","bank_type":"44404","card_account":"the","phone":"the","balance":"8917.00","withdrawals":"111.00","with_time":"0","status":"1","remark":"","sort":"0","operation_id":"0","with_num":"0","row_number":"2","amout_type":"余额提现"},{"id":"31","uid":"386","user_name":"the","apply_time":"1481280845","draw_type":"银联","bank_type":"44401","card_account":"the","phone":"the","balance":"9039.00","withdrawals":"122.00","with_time":"0","status":"1","remark":"","sort":"0","operation_id":"0","with_num":"0","row_number":"3","amout_type":"余额提现"},{"id":"30","uid":"386","user_name":"the","apply_time":"1481280777","draw_type":"银联","bank_type":"44401","card_account":"the","phone":"the","balance":"9161.00","withdrawals":"122.00","with_time":"0","status":"1","remark":"","sort":"0","operation_id":"0","with_num":"0","row_number":"4","amout_type":"余额提现"},{"id":"29","uid":"386","user_name":"1","apply_time":"1481278779","draw_type":"银联","bank_type":"44402","card_account":"1","phone":"1","balance":"9272.00","withdrawals":"111.00","with_time":"0","status":"1","remark":"","sort":"0","operation_id":"0","with_num":"0","row_number":"5","amout_type":"余额提现"}]
     * msg : 数据获取成功
     */

    private int status;
    private int count;
    private String msg;
    /**
     * id : 34
     * uid : 386
     * user_name : e
     * apply_time : 1481285381
     * draw_type : 银联
     * bank_type : 44404
     * card_account : www
     * phone : the
     * balance : 8806.00
     * withdrawals : 123.00
     * with_time : 0
     * status : 1
     * remark :
     * sort : 0
     * operation_id : 0
     * with_num : 0
     * row_number : 1
     * amout_type : 余额提现
     */

    private ArrayList<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
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
        private String user_name;
        private String apply_time;
        private String draw_type;
        private String bank_type;
        private String card_account;
        private String phone;
        private String balance;
        private String withdrawals;
        private String with_time;
        private String status;
        private String remark;
        private String sort;
        private String operation_id;
        private String with_num;
        private String row_number;
        private String amout_type;

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

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getApply_time() {
            return apply_time;
        }

        public void setApply_time(String apply_time) {
            this.apply_time = apply_time;
        }

        public String getDraw_type() {
            return draw_type;
        }

        public void setDraw_type(String draw_type) {
            this.draw_type = draw_type;
        }

        public String getBank_type() {
            return bank_type;
        }

        public void setBank_type(String bank_type) {
            this.bank_type = bank_type;
        }

        public String getCard_account() {
            return card_account;
        }

        public void setCard_account(String card_account) {
            this.card_account = card_account;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getWithdrawals() {
            return withdrawals;
        }

        public void setWithdrawals(String withdrawals) {
            this.withdrawals = withdrawals;
        }

        public String getWith_time() {
            return with_time;
        }

        public void setWith_time(String with_time) {
            this.with_time = with_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getOperation_id() {
            return operation_id;
        }

        public void setOperation_id(String operation_id) {
            this.operation_id = operation_id;
        }

        public String getWith_num() {
            return with_num;
        }

        public void setWith_num(String with_num) {
            this.with_num = with_num;
        }

        public String getRow_number() {
            return row_number;
        }

        public void setRow_number(String row_number) {
            this.row_number = row_number;
        }

        public String getAmout_type() {
            return amout_type;
        }

        public void setAmout_type(String amout_type) {
            this.amout_type = amout_type;
        }
    }
}
