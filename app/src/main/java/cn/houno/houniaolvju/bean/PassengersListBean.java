package cn.houno.houniaolvju.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 乘机人列表
 * Created by qzc on 2017/2/17.
 */

public class PassengersListBean implements Serializable {

    /**
     * status : 0
     * data : [{"id":"3","uid":"386","name":"你好","type":"0","identitytype":"1","identityno":"460006198911190918","birthday":"","parprice":".00","taxation":".00","phone":"","row_number":"1"},{"id":"4","uid":"386","name":"姓名","type":"0","identitytype":"1","identityno":"0000000000","birthday":"","parprice":".00","taxation":".00","phone":"","row_number":"2"},{"id":"5","uid":"386","name":"测试","type":"0","identitytype":"1","identityno":"530423198201160235","birthday":"","parprice":".00","taxation":".00","phone":"","row_number":"3"}]
     * msg : 数据获取成功
     */

    private int status;
    private String msg;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * id : 3
         * uid : 386
         * name : 你好
         * type : 0
         * identitytype : 1
         * identityno : 460006198911190918
         * birthday :
         * parprice : .00
         * taxation : .00
         * phone :
         * row_number : 1
         */

        private String id;
        private String uid;
        private String name;
        private String type;
        private String identitytype;
        private String identityno;
        private String birthday;
        private String parprice;
        private String taxation;
        private String phone;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIdentitytype() {
            return identitytype;
        }

        public void setIdentitytype(String identitytype) {
            this.identitytype = identitytype;
        }

        public String getIdentityno() {
            return identityno;
        }

        public void setIdentityno(String identityno) {
            this.identityno = identityno;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getParprice() {
            return parprice;
        }

        public void setParprice(String parprice) {
            this.parprice = parprice;
        }

        public String getTaxation() {
            return taxation;
        }

        public void setTaxation(String taxation) {
            this.taxation = taxation;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getRow_number() {
            return row_number;
        }

        public void setRow_number(String row_number) {
            this.row_number = row_number;
        }
    }
}
