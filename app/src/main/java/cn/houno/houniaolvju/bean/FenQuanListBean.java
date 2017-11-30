package cn.houno.houniaolvju.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 分权列表
 * Created by Administrator on 2017/1/11.
 */

public class FenQuanListBean implements Serializable {


    /**
     * status : 0
     * data : [{"title":"曼特宁温泉公寓式酒店","tel":"400-0898-183","id":"2","project":"花园洋房,舒适软装,养生避暑","img":"http://fq.houno.cn/Uploads/OffActi/20161107/1725475820489b4cc34.jpg","price":"69800.0000","add_time":"1477556079","row_number":"1"}]
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

    public static class DataBean implements Serializable {
        /**
         * title : 曼特宁温泉公寓式酒店
         * tel : 400-0898-183
         * id : 2
         * project : 花园洋房,舒适软装,养生避暑
         * img : http://fq.houno.cn/Uploads/OffActi/20161107/1725475820489b4cc34.jpg
         * price : 69800.0000
         * add_time : 1477556079
         * row_number : 1
         */

        private String title;
        private String tel;
        private String id;
        private String project;
        private String img;
        private String price;
        private String add_time;
        private String row_number;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProject() {
            return project;
        }

        public void setProject(String project) {
            this.project = project;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
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
