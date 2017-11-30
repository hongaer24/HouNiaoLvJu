package cn.houno.houniaolvju.bean;

import java.util.List;

/**
 * 项目名称：Houniaolvju
 * 类描述：
 * 创建人：zc358
 * 创建时间：2016/12/15 19:29
 * 修改人：zc358
 * 修改时间：2016/12/15 19:29
 * 修改备注：
 */
public class SportListBean {

    /**
     * id : 11
     * img : http://pic.houno.cn/Uploads/OffActi/20161118/170014582ec31ee9de8.png
     * title : 海口周边一日游
     * price : 198.0000
     * ksdate : 2016-11-18 11:00:00.000
     * jsdate : 2016-12-23 11:00:00.000
     * row_number : 1
     */

    private List<SportsBean> sports;

    public List<SportsBean> getSports() {
        return sports;
    }

    public void setSports(List<SportsBean> sports) {
        this.sports = sports;
    }

    public static class SportsBean {
        private String id;
        private String img;
        private String title;
        private String price;
        private String ksdate;
        private String jsdate;
        private String row_number;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getKsdate() {
            return ksdate;
        }

        public void setKsdate(String ksdate) {
            this.ksdate = ksdate;
        }

        public String getJsdate() {
            return jsdate;
        }

        public void setJsdate(String jsdate) {
            this.jsdate = jsdate;
        }

        public String getRow_number() {
            return row_number;
        }

        public void setRow_number(String row_number) {
            this.row_number = row_number;
        }
    }
}
