package cn.houno.houniaolvju.bean;

import java.util.ArrayList;

/**
 * 项目名称：Houniaolvju
 * 类描述：首页-景区门票
 * 创建人：qzc
 * 创建时间：2016/12/17 10:05
 * 修改人：qzc
 * 修改时间：2016/12/17 10:05
 * 修改备注：
 */
public class HomeRcmdScenicBean {


    /**
     * status : 0
     * count : 4
     * data : [{"img":"http://pic.houno.cn/Uploads/Scenic/20161008/15545257f8a64c84fb0.jpg","title":"海口观澜湖温泉","id":"34","address":"海南省海口市龙华区龙桥镇观澜湖大道1号海口观澜湖旅游度假区","cate_id":"6","star":"2A以下景区","hits":"8","row_number":"1","cate_name":"漂流探险","price":{"webprice":"128.00","marketprice":"198.00","price":".00","userprice":".00","stock":"500","row_number":"1"}},{"img":"http://pic.houno.cn/Uploads/Scenic/20161020/09382458082010d1775.png","title":"海口观澜湖华谊冯小刚电影公社","id":"176","address":"海南省海口市观澜湖大道1号观澜湖度假区内","cate_id":"3","star":"2A以下景区","hits":"10","row_number":"2","cate_name":"主题乐园","price":{"webprice":"88.00","marketprice":"138.00","price":".00","userprice":".00","stock":"500","row_number":"1"}},{"img":"http://pic.houno.cn/Uploads/Scenic/20161019/17315358073d894a3ae.png","title":"海口火山口公园","id":"192","address":"海南省海口市秀英区石山镇","cate_id":"6","star":"2A以下景区","hits":"9","row_number":"3","cate_name":"漂流探险","price":{"webprice":"48.00","marketprice":"60.00","price":".00","userprice":".00","stock":"500","row_number":"1"}},{"img":"http://pic.houno.cn/Uploads/Scenic/20161019/1708595807382b881a0.png","title":"五公祠","id":"200","address":"海南省海口市秀英区海府路169号","cate_id":"2","star":"2A以下景区","hits":"1","row_number":"4","cate_name":"人文古迹","price":{"webprice":"17.00","marketprice":"20.00","price":".00","userprice":".00","stock":"500","row_number":"1"}}]
     * msg : 数据获取成功
     */

    private int status;
    private String count;
    private String msg;
    /**
     * img : http://pic.houno.cn/Uploads/Scenic/20161008/15545257f8a64c84fb0.jpg
     * title : 海口观澜湖温泉
     * id : 34
     * address : 海南省海口市龙华区龙桥镇观澜湖大道1号海口观澜湖旅游度假区
     * cate_id : 6
     * star : 2A以下景区
     * hits : 8
     * row_number : 1
     * cate_name : 漂流探险
     * price : {"webprice":"128.00","marketprice":"198.00","price":".00","userprice":".00","stock":"500","row_number":"1"}
     */

    private ArrayList<DataBean> data;

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

    public ArrayList<DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String img;
        private String title;
        private String id;
        private String address;
        private String cate_id;
        private String star;
        private String hits;
        private String row_number;
        private String cate_name;
        /**
         * webprice : 128.00
         * marketprice : 198.00
         * price : .00
         * userprice : .00
         * stock : 500
         * row_number : 1
         */

        private PriceBean price;

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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }

        public String getStar() {
            return star;
        }

        public void setStar(String star) {
            this.star = star;
        }

        public String getHits() {
            return hits;
        }

        public void setHits(String hits) {
            this.hits = hits;
        }

        public String getRow_number() {
            return row_number;
        }

        public void setRow_number(String row_number) {
            this.row_number = row_number;
        }

        public String getCate_name() {
            return cate_name;
        }

        public void setCate_name(String cate_name) {
            this.cate_name = cate_name;
        }

        public PriceBean getPrice() {
            return price;
        }

        public void setPrice(PriceBean price) {
            this.price = price;
        }

        public static class PriceBean {
            private String webprice;
            private String marketprice;
            private String price;
            private String userprice;
            private String stock;
            private String row_number;

            public String getWebprice() {
                return webprice;
            }

            public void setWebprice(String webprice) {
                this.webprice = webprice;
            }

            public String getMarketprice() {
                return marketprice;
            }

            public void setMarketprice(String marketprice) {
                this.marketprice = marketprice;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getUserprice() {
                return userprice;
            }

            public void setUserprice(String userprice) {
                this.userprice = userprice;
            }

            public String getStock() {
                return stock;
            }

            public void setStock(String stock) {
                this.stock = stock;
            }

            public String getRow_number() {
                return row_number;
            }

            public void setRow_number(String row_number) {
                this.row_number = row_number;
            }
        }
    }
}
