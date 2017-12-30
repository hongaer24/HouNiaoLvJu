package cn.houno.houniaolvju.bean;

import java.util.List;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：景点列表
 * 创建人：qzc
 * 创建时间：2017/1/4 10:20
 * 修改人：qzc
 * 修改时间：2017/1/4 10:20
 * 修改备注：
 */
public class ScenicListBean {


    /**
     * status : 0
     * count : 9
     * data : [{"scenicname":"海口观澜湖度假区","id":"3603","scenicid":"789243","opentime":"五大洲矿温泉： 亚洲、大洋洲区：13:00-24:00； 欧洲区：13:00-22:00； 中东、美洲区：17:00-23:00 （各区域时间以园区开放为准）, 福星闯天关主题乐园营业时间：8:00-24:00, 户外泳池/懒人河：8:00--19:00。","scenicaddress":"海南省海口市观澜湖大道1号。","defaultpic":"http://m.tuniucdn.com/filebroker/cdn/olb/bf/a2/bfa282df3968ff14b62cb025dbe6f622_w240_h135_c1_t0.jpg","hits":"6955","saleprice":"128.0","webprice":"198.0","row_number":"1","favorite":0},{"scenicname":"观澜湖水上乐园","id":"4118","scenicid":"1801183","opentime":"8：00-22：00","scenicaddress":"海南省海口市观澜湖大道1号","defaultpic":"http://m.tuniucdn.com/filebroker/cdn/olb/75/db/75db290a71d203ef8c7ffc8d021b7420_w240_h135_c1_t0.jpg","hits":"6955","saleprice":"100.0","webprice":"150.0","row_number":"2","favorite":0},{"scenicname":"观澜湖华谊冯小刚电影公社","id":"1933","scenicid":"1799896","opentime":"9:00-19:00。","scenicaddress":"海南省海口市观澜湖大道（西线高速往观澜湖温泉1.8公里处）。","defaultpic":"http://m.tuniucdn.com/filebroker/cdn/olb/f5/5a/f55af960ef266798e8ffd02a98bbf9e8_w240_h135_c1_t0.jpg","hits":"6955","saleprice":"128.0","webprice":"148.0","row_number":"3","favorite":0},{"scenicname":"海口观澜湖温泉","id":"8","scenicid":"1833530","opentime":"13:00-23:00(五大洲矿温泉中东区开放时间)  17:00-23:00(五大洲矿温泉美洲区开放时间)  8:00-22:00(五大洲矿温泉欧洲区开放时间)  13:00-24:00(五大洲矿温泉亚洲及大洋洲区开放时间)  8:00-22:00(水上乐园冰海冒险、懒人河丛林漂流、欧洲区沙滩冲浪开放时间)","scenicaddress":"海南省海口市龙华区龙桥镇观澜湖大道1号海口观澜湖旅游度假区","defaultpic":"http://m.tuniucdn.com/fb2/t1/G1/M00/1D/1F/Cii9EFZULQCIYHWlAA4HNoKCAjwAAAmdwEqm7MADgdO109.png","hits":"7046","saleprice":"100.0","webprice":"105.0","row_number":"4","favorite":0},{"scenicname":"观澜湖新城","id":"1026","scenicid":"3532855","opentime":"10:00-22:00。","scenicaddress":"海南省海口观澜湖新城9栋三楼2304、2305号铺（兰桂坊楼上）反弹蹦床公园。","defaultpic":"http://m.tuniucdn.com/fb2/t1/G1/M00/CE/03/Cii9EVjmGRWIOSJbAAFoJQWDFZwAAJYQgOggZYAAWg9863_w240_h135_c1_t0.jpg","hits":"7046","saleprice":"88.0","webprice":"98.0","row_number":"5","favorite":0},{"scenicname":"海口泰迪熊博物馆","id":"605","scenicid":"3089612","opentime":"10:00-19:00（周一-周五）,10:00-21:00(周六-周日)。","scenicaddress":"观澜湖新城东区皇后大道。","defaultpic":"http://m.tuniucdn.com/fb2/t1/G2/M00/0C/FA/Cii-T1gue-OIP1PVAAGL0k_5A20AAEhpQP5-NQAAYvq658_w240_h135_c1_t0.jpg","hits":"7046","saleprice":"75.0","webprice":"90.0","row_number":"6","favorite":0},{"scenicname":"海南热带野生动植物园","id":"2569","scenicid":"19940","opentime":"8:00-18:00。","scenicaddress":"海南省海口市秀英区东山镇（海榆中线27公里处）。","defaultpic":"http://m.tuniucdn.com/filebroker/cdn/olb/ed/95/ed95936ef2c3abc93e29897bc703af2a_w240_h135_c1_t0.jpg","hits":"6955","saleprice":"55.0","webprice":"64.0","row_number":"7","favorite":0},{"scenicname":"海口火山口地质公园","id":"2092","scenicid":"51259","opentime":"8:00-18:00。","scenicaddress":"海南省海口市石山镇火山群世界地质公园。","defaultpic":"http://m.tuniucdn.com/fb2/t1/G2/M00/63/CA/Cii-TFjeIZSIbKXJAAGreb2Wq78AAI7vgPsEWkAAauR186_w240_h135_c1_t0.jpg","hits":"6955","saleprice":"30.0","webprice":"30.0","row_number":"8","favorite":0},{"scenicname":"海口五公祠","id":"3565","scenicid":"416","opentime":"8:00-18:00。","scenicaddress":"海南省海口市琼山区海府路169号。","defaultpic":"http://m.tuniucdn.com/filebroker/cdn/snc/dc/9e/dc9eabdd12580e58957bb6a52e434e1b_w240_h135_c1_t0.jpg","hits":"6955","saleprice":"15.0","webprice":"17.0","row_number":"9","favorite":0}]
     */

    private int status;
    private String count;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * scenicname : 海口观澜湖度假区
         * id : 3603
         * scenicid : 789243
         * opentime : 五大洲矿温泉： 亚洲、大洋洲区：13:00-24:00； 欧洲区：13:00-22:00； 中东、美洲区：17:00-23:00 （各区域时间以园区开放为准）, 福星闯天关主题乐园营业时间：8:00-24:00, 户外泳池/懒人河：8:00--19:00。
         * scenicaddress : 海南省海口市观澜湖大道1号。
         * defaultpic : http://m.tuniucdn.com/filebroker/cdn/olb/bf/a2/bfa282df3968ff14b62cb025dbe6f622_w240_h135_c1_t0.jpg
         * hits : 6955
         * saleprice : 128.0
         * webprice : 198.0
         * row_number : 1
         * favorite : 0
         */

        private String scenicname;
        private String id;
        private String scenicid;
        private String opentime;
        private String scenicaddress;
        private String defaultpic;
        private String hits;
        private String saleprice;
        private String webprice;
        private String row_number;
        private int favorite;

        public String getScenicname() {
            return scenicname;
        }

        public void setScenicname(String scenicname) {
            this.scenicname = scenicname;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getScenicid() {
            return scenicid;
        }

        public void setScenicid(String scenicid) {
            this.scenicid = scenicid;
        }

        public String getOpentime() {
            return opentime;
        }

        public void setOpentime(String opentime) {
            this.opentime = opentime;
        }

        public String getScenicaddress() {
            return scenicaddress;
        }

        public void setScenicaddress(String scenicaddress) {
            this.scenicaddress = scenicaddress;
        }

        public String getDefaultpic() {
            return defaultpic;
        }

        public void setDefaultpic(String defaultpic) {
            this.defaultpic = defaultpic;
        }

        public String getHits() {
            return hits;
        }

        public void setHits(String hits) {
            this.hits = hits;
        }

        public String getSaleprice() {
            return saleprice;
        }

        public void setSaleprice(String saleprice) {
            this.saleprice = saleprice;
        }

        public String getWebprice() {
            return webprice;
        }

        public void setWebprice(String webprice) {
            this.webprice = webprice;
        }

        public String getRow_number() {
            return row_number;
        }

        public void setRow_number(String row_number) {
            this.row_number = row_number;
        }

        public int getFavorite() {
            return favorite;
        }

        public void setFavorite(int favorite) {
            this.favorite = favorite;
        }
    }
}
