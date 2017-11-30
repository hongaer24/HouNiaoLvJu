package cn.houno.houniaolvju.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 123 on 2017/11/29.
 */

public class GetScenicPassengerBean implements Serializable {



    /**
     * status : 0
     * data : [{"id":"11","uid":"1117","ljbuid":"","name":"吴冠鸿","identitytype":"1","identityno":"460103199107020011","phone":"18689683808","email":"foxuzi@qq.com","sex":"1","source":"0","row_number":"1"},{"id":"10","uid":"1117","ljbuid":"","name":"33","identitytype":"1","identityno":"13022519800908273X","phone":"18689683808","email":"foxuzi@qq.com","sex":"1","source":"0","row_number":"2"}]
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

    public static class DataBean implements Serializable  {
        /**
         * id : 11
         * uid : 1117
         * ljbuid :
         * name : 吴冠鸿
         * identitytype : 1
         * identityno : 460103199107020011
         * phone : 18689683808
         * email : foxuzi@qq.com
         * sex : 1
         * source : 0
         * row_number : 1
         */

        private String id;
        private String uid;
        private String ljbuid;
        private String name;
        private String identitytype;
        private String identityno;
        private String phone;
        private String email;
        private String sex;
        private String source;
        private String row_number;
        protected boolean isChoosed;

        public boolean isChoosed() {
            return isChoosed;
        }

        public void setChoosed(boolean choosed) {
            isChoosed = choosed;
        }

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

        public String getLjbuid() {
            return ljbuid;
        }

        public void setLjbuid(String ljbuid) {
            this.ljbuid = ljbuid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", uid='" + uid + '\'' +
                    ", ljbuid='" + ljbuid + '\'' +
                    ", name='" + name + '\'' +
                    ", identitytype='" + identitytype + '\'' +
                    ", identityno='" + identityno + '\'' +
                    ", phone='" + phone + '\'' +
                    ", email='" + email + '\'' +
                    ", sex='" + sex + '\'' +
                    ", source='" + source + '\'' +
                    ", row_number='" + row_number + '\'' +
                    ", isChoosed=" + isChoosed +
                    '}';
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }


        public String getRow_number() {
            return row_number;
        }

        public void setRow_number(String row_number) {
            this.row_number = row_number;
        }
    }
}
