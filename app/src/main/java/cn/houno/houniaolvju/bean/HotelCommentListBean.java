package cn.houno.houniaolvju.bean;

import java.util.List;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：酒店评论列表
 * 创建人：qzc
 * 创建时间：2016/12/27 19:27
 * 修改人：qzc
 * 修改时间：2016/12/27 19:27
 * 修改备注：
 */
public class HotelCommentListBean {


    /**
     * status : 0
     * count : 14
     * data : [{"id":"61","rid":"34408","hid":"9940","oid":"","type":"hotel               ","model":"room                ","contents":"第三次入住了，感觉一如往常的好。房间大，床舒服，会议安排的自助餐选择很多。一街之隔的长安商场超市的北京特产超级多。离地铁一号线近，出行方便。","replay":"","add_time":"1481683640","replay_time":"0","fuwu_point":"5","jiaotong_point":"5","wei_point":"5","canyin_point":"5","sheshi_point":"5","price_point":"5","average":"5","tag":"","uid":"159","checkin":"2016-12-26","checkout":"2016-12-28","title":"北京中国职工之家饭店-B座单人间                                            ","status":"1","mer_id":"0","username":"13072662517","head_img":"","row_number":"1"},{"id":"60","rid":"34406","hid":"9940","oid":"","type":"hotel               ","model":"room                ","contents":"酒店的服务和品质自然不必说，每年都入住全国\u201c两会\u201d的代表。位置非常好，紧邻长安商场，周边商业设施和交通也特别便利。吃饭的地方也很多。到西单两站地铁就到。","replay":"","add_time":"1481683507","replay_time":"0","fuwu_point":"5","jiaotong_point":"5","wei_point":"5","canyin_point":"5","sheshi_point":"5","price_point":"5","average":"5","tag":"","uid":"159","checkin":"2016-12-06","checkout":"2016-12-07","title":"北京中国职工之家饭店-A座致臻商务间                                          ","status":"1","mer_id":"0","username":"13072662517","head_img":"","row_number":"2"},{"id":"59","rid":"34403","hid":"9940","oid":"","type":"hotel               ","model":"room                ","contents":"酒店服务总体不错，设施齐全，卫生环境佳，周边交通方便，距地铁一号线约10分钟","replay":"","add_time":"1481683478","replay_time":"0","fuwu_point":"5","jiaotong_point":"5","wei_point":"5","canyin_point":"5","sheshi_point":"5","price_point":"5","average":"5","tag":"","uid":"159","checkin":"2016-12-10","checkout":"2016-12-11","title":"北京中国职工之家饭店-A座特惠间                                            ","status":"1","mer_id":"0","username":"13072662517","head_img":"","row_number":"3"},{"id":"58","rid":"34408","hid":"9940","oid":"","type":"hotel               ","model":"room                ","contents":"中国职工之家地理位置非常优越，紧邻长安街，离地铁口很近，出行太方便。","replay":"","add_time":"1481683400","replay_time":"0","fuwu_point":"5","jiaotong_point":"5","wei_point":"5","canyin_point":"5","sheshi_point":"5","price_point":"5","average":"5","tag":"","uid":"159","checkin":"2016-12-19","checkout":"2016-12-20","title":"北京中国职工之家饭店-B座单人间                                            ","status":"1","mer_id":"0","username":"13072662517","head_img":"","row_number":"4"},{"id":"57","rid":"34403","hid":"9940","oid":"","type":"hotel               ","model":"room                ","contents":"很不错的酒店，首先地理位置很好，到北京各景点都有公交和地铁。到天安门只有6，7站路，坐52路车。还有酒店很干净卫生，服务员态度也很好。这个价位在北京算是很划算的了。","replay":"","add_time":"1481683330","replay_time":"0","fuwu_point":"5","jiaotong_point":"5","wei_point":"5","canyin_point":"5","sheshi_point":"5","price_point":"5","average":"5","tag":"","uid":"159","checkin":"2016-12-23","checkout":"2016-12-25","title":"北京中国职工之家饭店-A座特惠间                                            ","status":"1","mer_id":"0","username":"13072662517","head_img":"","row_number":"5"},{"id":"50","rid":"34403","hid":"9940","oid":"","type":"hotel               ","model":"room                ","contents":"多次预订这家酒店，都是在这个平台订了，候鸟旅居网，给客户预订的，客户反馈很好！！！","replay":"","add_time":"1481428452","replay_time":"0","fuwu_point":"5","jiaotong_point":"5","wei_point":"5","canyin_point":"5","sheshi_point":"5","price_point":"5","average":"5","tag":"","uid":"507","checkin":"2016-12-11","checkout":"2016-12-12","title":"北京中国职工之家饭店-A座特惠间                                            ","status":"1","mer_id":"0","username":"张昆","head_img":"http://pic.houno.cn/Public/Home/images/default.jpg","row_number":"6"},{"id":"45","rid":"34403","hid":"9940","oid":"","type":"hotel               ","model":"room                ","contents":"候鸟旅居网非常不错，价格美丽，住的也美丽，下次订房还来这里预订，nice！","replay":"","add_time":"1480487683","replay_time":"0","fuwu_point":"5","jiaotong_point":"5","wei_point":"5","canyin_point":"5","sheshi_point":"5","price_point":"5","average":"5","tag":"","uid":"507","checkin":"2016-12-23","checkout":"2016-12-25","title":"北京中国职工之家饭店-A座特惠间                                            ","status":"1","mer_id":"0","username":"张昆","head_img":"http://pic.houno.cn/Public/Home/images/default.jpg","row_number":"7"},{"id":"42","rid":"34410","hid":"9940","oid":"","type":"hotel               ","model":"room                ","contents":"酒店整体很好，前台很有礼貌，客房里面很干净，住过的酒店挺多的，这家服务可以！位置也很好！平台还很优惠喔~~~","replay":"","add_time":"1479974625","replay_time":"0","fuwu_point":"5","jiaotong_point":"5","wei_point":"5","canyin_point":"5","sheshi_point":"5","price_point":"5","average":"5","tag":"","uid":"507","checkin":"2016-11-23","checkout":"2016-11-24","title":"北京中国职工之家饭店-B座豪华标准间                                          ","status":"1","mer_id":"0","username":"张昆","head_img":"http://pic.houno.cn/Public/Home/images/default.jpg","row_number":"8"},{"id":"41","rid":"34410","hid":"9940","oid":"","type":"hotel               ","model":"room                ","contents":"平台很好，酒店很好，没的说，客户入住之后很满意，赞一个！","replay":"","add_time":"1479974535","replay_time":"0","fuwu_point":"5","jiaotong_point":"5","wei_point":"5","canyin_point":"5","sheshi_point":"5","price_point":"5","average":"5","tag":"","uid":"507","checkin":"2016-11-22","checkout":"2016-11-24","title":"北京中国职工之家饭店-B座豪华标准间                                          ","status":"1","mer_id":"0","username":"张昆","head_img":"http://pic.houno.cn/Public/Home/images/default.jpg","row_number":"9"},{"id":"40","rid":"34410","hid":"9940","oid":"","type":"hotel               ","model":"room                ","contents":"听朋友说的这个平台挺不错，就过来看一下，酒店很多，正好帮朋友预定了一间房，在北京能预定到这样的酒店，这个价位真的是可以了，不错，优惠很大，酒店品味很高，下次还来！！！","replay":"","add_time":"1479974470","replay_time":"0","fuwu_point":"5","jiaotong_point":"5","wei_point":"5","canyin_point":"5","sheshi_point":"5","price_point":"5","average":"5","tag":"","uid":"507","checkin":"2016-11-22","checkout":"2016-11-24","title":"北京中国职工之家饭店-B座豪华标准间                                          ","status":"1","mer_id":"0","username":"张昆","head_img":"http://pic.houno.cn/Public/Home/images/default.jpg","row_number":"10"}]
     * msg : 数据获取成功
     */

    private int status;
    private String count;
    private String rate;
    private String msg;
    /**
     * id : 61
     * rid : 34408
     * hid : 9940
     * oid :
     * type : hotel
     * model : room
     * contents : 第三次入住了，感觉一如往常的好。房间大，床舒服，会议安排的自助餐选择很多。一街之隔的长安商场超市的北京特产超级多。离地铁一号线近，出行方便。
     * replay :
     * add_time : 1481683640
     * replay_time : 0
     * fuwu_point : 5
     * jiaotong_point : 5
     * wei_point : 5
     * canyin_point : 5
     * sheshi_point : 5
     * price_point : 5
     * average : 5
     * tag :
     * uid : 159
     * checkin : 2016-12-26
     * checkout : 2016-12-28
     * title : 北京中国职工之家饭店-B座单人间
     * status : 1
     * mer_id : 0
     * username : 13072662517
     * head_img :
     * row_number : 1
     */

    private List<DataBean> data;

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

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
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
        private String id;
        private String rid;
        private String hid;
        private String oid;
        private String type;
        private String model;
        private String contents;
        private String replay;
        private String add_time;
        private String replay_time;
        private String fuwu_point;
        private String jiaotong_point;
        private String wei_point;
        private String canyin_point;
        private String sheshi_point;
        private String price_point;
        private String average;
        private String tag;
        private String uid;
        private String checkin;
        private String checkout;
        private String title;
        private String status;
        private String mer_id;
        private String username;
        private String head_img;
        private String row_number;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRid() {
            return rid;
        }

        public void setRid(String rid) {
            this.rid = rid;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getContents() {
            return contents;
        }

        public void setContents(String contents) {
            this.contents = contents;
        }

        public String getReplay() {
            return replay;
        }

        public void setReplay(String replay) {
            this.replay = replay;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getReplay_time() {
            return replay_time;
        }

        public void setReplay_time(String replay_time) {
            this.replay_time = replay_time;
        }

        public String getFuwu_point() {
            return fuwu_point;
        }

        public void setFuwu_point(String fuwu_point) {
            this.fuwu_point = fuwu_point;
        }

        public String getJiaotong_point() {
            return jiaotong_point;
        }

        public void setJiaotong_point(String jiaotong_point) {
            this.jiaotong_point = jiaotong_point;
        }

        public String getWei_point() {
            return wei_point;
        }

        public void setWei_point(String wei_point) {
            this.wei_point = wei_point;
        }

        public String getCanyin_point() {
            return canyin_point;
        }

        public void setCanyin_point(String canyin_point) {
            this.canyin_point = canyin_point;
        }

        public String getSheshi_point() {
            return sheshi_point;
        }

        public void setSheshi_point(String sheshi_point) {
            this.sheshi_point = sheshi_point;
        }

        public String getPrice_point() {
            return price_point;
        }

        public void setPrice_point(String price_point) {
            this.price_point = price_point;
        }

        public String getAverage() {
            return average;
        }

        public void setAverage(String average) {
            this.average = average;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getCheckin() {
            return checkin;
        }

        public void setCheckin(String checkin) {
            this.checkin = checkin;
        }

        public String getCheckout() {
            return checkout;
        }

        public void setCheckout(String checkout) {
            this.checkout = checkout;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMer_id() {
            return mer_id;
        }

        public void setMer_id(String mer_id) {
            this.mer_id = mer_id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getHead_img() {
            return head_img;
        }

        public void setHead_img(String head_img) {
            this.head_img = head_img;
        }

        public String getRow_number() {
            return row_number;
        }

        public void setRow_number(String row_number) {
            this.row_number = row_number;
        }
    }
}
