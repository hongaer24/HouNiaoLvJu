package cn.houno.houniaolvju.bean;

import java.util.List;

/**
 * 优惠券列表
 * Created by Administrator on 2017/1/20.
 */

public class CouponListBean {

    /**
     * status : 0
     * data : [{"id":"12","uid":"386","cid":"2","hid":"15449","oid":"0","hotelname":"西双版纳万达文华度假酒店","status":"2","startdate":"2016-11-16","enddate":"2017-12-29","add_time":"1484704475","admin":"","ctitle":"20元优惠券","pics":"http://pic.houno.cn/Uploads/Coupon/20161116/115306582bd8222e60a.jpg","price":"20.0000","row_number":"1"},{"id":"14","uid":"386","cid":"2","hid":"15449","oid":"0","hotelname":"西双版纳万达文华度假酒店","status":"2","startdate":"2016-11-16","enddate":"2017-12-29","add_time":"1484704475","admin":"","ctitle":"20元优惠券","pics":"http://pic.houno.cn/Uploads/Coupon/20161116/115306582bd8222e60a.jpg","price":"20.0000","row_number":"2"},{"id":"15","uid":"386","cid":"2","hid":"15449","oid":"0","hotelname":"西双版纳万达文华度假酒店","status":"0","startdate":"2016-11-16","enddate":"2017-12-29","add_time":"1484704475","admin":"","ctitle":"20元优惠券","pics":"http://pic.houno.cn/Uploads/Coupon/20161116/115306582bd8222e60a.jpg","price":"20.0000","row_number":"3"},{"id":"13","uid":"386","cid":"2","hid":"15449","oid":"0","hotelname":"西双版纳万达文华度假酒店","status":"0","startdate":"2016-11-16","enddate":"2017-12-29","add_time":"1484704475","admin":"","ctitle":"20元优惠券","pics":"http://pic.houno.cn/Uploads/Coupon/20161116/115306582bd8222e60a.jpg","price":"20.0000","row_number":"4"}]
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

    public static class DataBean {
        /**
         * id : 12
         * uid : 386
         * cid : 2
         * hid : 15449
         * oid : 0
         * hotelname : 西双版纳万达文华度假酒店
         * status : 2
         * startdate : 2016-11-16
         * enddate : 2017-12-29
         * add_time : 1484704475
         * admin :
         * ctitle : 20元优惠券
         * pics : http://pic.houno.cn/Uploads/Coupon/20161116/115306582bd8222e60a.jpg
         * price : 20.0000
         * row_number : 1
         */

        private String id;
        private String uid;
        private String cid;
        private String hid;
        private String oid;
        private String hotelname;
        private String status;
        private String startdate;
        private String enddate;
        private String add_time;
        private String admin;
        private String ctitle;
        private String pics;
        private String price;
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

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getHid() {
            return hid;
        }

        public void setHid(String hid) {
            this.hid = hid;
        }

        public String getOid() {
            return oid;
        }

        public void setOid(String oid) {
            this.oid = oid;
        }

        public String getHotelname() {
            return hotelname;
        }

        public void setHotelname(String hotelname) {
            this.hotelname = hotelname;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStartdate() {
            return startdate;
        }

        public void setStartdate(String startdate) {
            this.startdate = startdate;
        }

        public String getEnddate() {
            return enddate;
        }

        public void setEnddate(String enddate) {
            this.enddate = enddate;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getAdmin() {
            return admin;
        }

        public void setAdmin(String admin) {
            this.admin = admin;
        }

        public String getCtitle() {
            return ctitle;
        }

        public void setCtitle(String ctitle) {
            this.ctitle = ctitle;
        }

        public String getPics() {
            return pics;
        }

        public void setPics(String pics) {
            this.pics = pics;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getRow_number() {
            return row_number;
        }

        public void setRow_number(String row_number) {
            this.row_number = row_number;
        }
    }
}
