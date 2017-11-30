package cn.houno.houniaolvju.bean;

import java.util.ArrayList;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：轮播图bean
 * 创建人：qzc
 * 创建时间：2016/9/29 11:25
 * 修改人：qzc
 * 修改时间：2016/9/29 11:25
 * 修改备注：
 */
public class TopPicBean {

    private int status;

    private ArrayList<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String type;
        private String name;
        private String url;
        private String img;
        private String content;
        /**
         * bgcolor :
         */

        private DataBean data;
        private String ordid;
        private String row_number;
        private String width;
        private String height;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public String getOrdid() {
            return ordid;
        }

        public void setOrdid(String ordid) {
            this.ordid = ordid;
        }

        public String getRow_number() {
            return row_number;
        }

        public void setRow_number(String row_number) {
            this.row_number = row_number;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public static class BgDataBean {
            private String bgcolor;

            public String getBgcolor() {
                return bgcolor;
            }

            public void setBgcolor(String bgcolor) {
                this.bgcolor = bgcolor;
            }
        }
    }
}
