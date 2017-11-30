package cn.houno.houniaolvju.bean;

import java.util.List;

/**
 * 实时搜索
 * Created by Administrator on 2017/1/12.
 */

public class GetIndexSearchBean {


    /**
     * status : 0
     * data : [{"id":"20969","title":"西安冯缘酒店","model":"hotel","row_number":"1"},{"id":"176","title":"海口观澜湖华谊冯小刚电影公社","model":"scenic","row_number":"1"}]
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
         * id : 20969
         * title : 西安冯缘酒店
         * model : hotel
         * row_number : 1
         */

        private String id;
        private String title;
        private String model;
        private String row_number;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getRow_number() {
            return row_number;
        }

        public void setRow_number(String row_number) {
            this.row_number = row_number;
        }
    }
}
