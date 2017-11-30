package cn.houno.houniaolvju.bean;

import java.util.List;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：景点评论列表
 * 创建人：qzc
 * 创建时间：2017/1/7 17:14
 * 修改人：qzc
 * 修改时间：2017/1/7 17:14
 * 修改备注：
 */
public class ScenicCommentListBean {


    /**
     * status : 0
     * count : 10
     * data : [{"id":"44","sid":"34","tid":"73","orderid":"246","orderno":"20161231163857570620","popularity":"5.0","scale":"5.0","sightseeing":"5.0","service":"5.0","average":"5.0","info":"Happy new year,it's very good","uid":"305","add_time":"1483412779","username":"蒋公子","head_img":"http://pic.houno.cn/Uploads/User/20161208/2016_12_08_152439701673.png","row_number":"1"},{"id":"40","sid":"34","tid":"73","orderid":"228","orderno":"20161221124830468760","popularity":"5.0","scale":"5.0","sightseeing":"5.0","service":"5.0","average":"5.0","info":"好玩。，。","uid":"386","add_time":"1482295751","username":"android","head_img":"http://pic.houno.cn/Uploads/User/20161222/2016_12_22_152500198251.png","row_number":"2"},{"id":"38","sid":"34","tid":"73","orderid":"207","orderno":"20161206111312557360","popularity":"5.0","scale":"5.0","sightseeing":"5.0","service":"5.0","average":"5.0","info":"好！","uid":"386","add_time":"1481182836","username":"android","head_img":"http://pic.houno.cn/Uploads/User/20161222/2016_12_22_152500198251.png","row_number":"3"},{"id":"37","sid":"34","tid":"73","orderid":"218","orderno":"20161207151647997180","popularity":"5.0","scale":"5.0","sightseeing":"5.0","service":"5.0","average":"5.0","info":"可以不错很好玩可以不错很好玩可以不错很好玩可以不错很好玩可以不错很好玩可以不错很好玩可以不错很好玩可以不错很好玩","uid":"386","add_time":"1481182407","username":"android","head_img":"http://pic.houno.cn/Uploads/User/20161222/2016_12_22_152500198251.png","row_number":"4"},{"id":"36","sid":"34","tid":"73","orderid":"207","orderno":"20161206111312557360","popularity":"5.0","scale":"5.0","sightseeing":"5.0","service":"5.0","average":"5.0","info":"好！","uid":"386","add_time":"1481182836","username":"android","head_img":"http://pic.houno.cn/Uploads/User/20161222/2016_12_22_152500198251.png","row_number":"5"},{"id":"35","sid":"34","tid":"73","orderid":"218","orderno":"20161207151647997180","popularity":"5.0","scale":"5.0","sightseeing":"5.0","service":"5.0","average":"5.0","info":"很好玩！","uid":"386","add_time":"1481182407","username":"android","head_img":"http://pic.houno.cn/Uploads/User/20161222/2016_12_22_152500198251.png","row_number":"6"},{"id":"34","sid":"34","tid":"73","orderid":"207","orderno":"20161206111312557360","popularity":"5.0","scale":"5.0","sightseeing":"5.0","service":"5.0","average":"5.0","info":"好！","uid":"386","add_time":"1481182836","username":"android","head_img":"http://pic.houno.cn/Uploads/User/20161222/2016_12_22_152500198251.png","row_number":"7"},{"id":"33","sid":"34","tid":"73","orderid":"218","orderno":"20161207151647997180","popularity":"5.0","scale":"5.0","sightseeing":"5.0","service":"5.0","average":"5.0","info":"很好玩！","uid":"386","add_time":"1481182407","username":"android","head_img":"http://pic.houno.cn/Uploads/User/20161222/2016_12_22_152500198251.png","row_number":"8"},{"id":"31","sid":"34","tid":"73","orderid":"207","orderno":"20161206111312557360","popularity":"5.0","scale":"5.0","sightseeing":"5.0","service":"5.0","average":"5.0","info":"好！","uid":"386","add_time":"1481182836","username":"android","head_img":"http://pic.houno.cn/Uploads/User/20161222/2016_12_22_152500198251.png","row_number":"9"},{"id":"29","sid":"34","tid":"73","orderid":"218","orderno":"20161207151647997180","popularity":"5.0","scale":"5.0","sightseeing":"5.0","service":"5.0","average":"5.0","info":"很好玩！很好玩！很好玩！很好玩！很好玩！很好玩！很好玩！很好玩！","uid":"386","add_time":"1481182407","username":"android","head_img":"http://pic.houno.cn/Uploads/User/20161222/2016_12_22_152500198251.png","row_number":"10"}]
     * msg : 数据获取成功
     */

    private int status;
    private String count;
    private String msg;
    /**
     * id : 44
     * sid : 34
     * tid : 73
     * orderid : 246
     * orderno : 20161231163857570620
     * popularity : 5.0
     * scale : 5.0
     * sightseeing : 5.0
     * service : 5.0
     * average : 5.0
     * info : Happy new year,it's very good
     * uid : 305
     * add_time : 1483412779
     * username : 蒋公子
     * head_img : http://pic.houno.cn/Uploads/User/20161208/2016_12_08_152439701673.png
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
        private String sid;
        private String tid;
        private String orderid;
        private String orderno;
        private String popularity;
        private String scale;
        private String sightseeing;
        private String service;
        private String average;
        private String info;
        private String uid;
        private String add_time;
        private String username;
        private String head_img;
        private String row_number;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getOrderno() {
            return orderno;
        }

        public void setOrderno(String orderno) {
            this.orderno = orderno;
        }

        public String getPopularity() {
            return popularity;
        }

        public void setPopularity(String popularity) {
            this.popularity = popularity;
        }

        public String getScale() {
            return scale;
        }

        public void setScale(String scale) {
            this.scale = scale;
        }

        public String getSightseeing() {
            return sightseeing;
        }

        public void setSightseeing(String sightseeing) {
            this.sightseeing = sightseeing;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public String getAverage() {
            return average;
        }

        public void setAverage(String average) {
            this.average = average;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
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
