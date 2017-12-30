package cn.houno.houniaolvju.bean;

import java.util.List;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：
 * 创建人：qzc
 * 创建时间：2017/1/3 11:41
 * 修改人：qzc
 * 修改时间：2017/1/3 11:41
 * 修改备注：
 */
public class ScenicIndexBean {

    /**
     * status : 0
     * adsdata : [{"type":"image","name":"广告","url":"http://apis.houno.cn#464","img":"http://pic.houno.cn/Uploads/Images/20161021/1715185809dca665410.jpg","ordid":"255","row_number":"1"}]
     * main : [{"scenicname":"观澜湖新城","id":"1026","scenicid":"3532855","opentime":"10:00-22:00。","scenicaddress":"海南省海口观澜湖新城9栋三楼2304、2305号铺（兰桂坊楼上）反弹蹦床公园。","defaultpic":"http://m.tuniucdn.com/fb2/t1/G1/M00/CE/03/Cii9EVjmGRWIOSJbAAFoJQWDFZwAAJYQgOggZYAAWg9863_w240_h135_c1_t0.jpg","hits":"1772","saleprice":"88.0","webprice":"98.0","row_number":"1","url":"/index.php/LjbScenic/shows.php"},{"scenicname":"海口泰迪熊博物馆","id":"605","scenicid":"3089612","opentime":"10:00-19:00（周一-周五）,10:00-21:00(周六-周日)。","scenicaddress":"观澜湖新城东区皇后大道。","defaultpic":"http://m.tuniucdn.com/fb2/t1/G2/M00/0C/FA/Cii-T1gue-OIP1PVAAGL0k_5A20AAEhpQP5-NQAAYvq658_w240_h135_c1_t0.jpg","hits":"1772","saleprice":"75.0","webprice":"90.0","row_number":"2","url":"/index.php/LjbScenic/shows.php"}]
     * local : [{"scenicname":"观澜湖华谊冯小刚电影公社","id":"1933","scenicid":"1799896","opentime":"9:00-19:00。","scenicaddress":"海南省海口市观澜湖大道（西线高速往观澜湖温泉1.8公里处）。","defaultpic":"http://m.tuniucdn.com/filebroker/cdn/olb/f5/5a/f55af960ef266798e8ffd02a98bbf9e8_w240_h135_c1_t0.jpg","hits":"1681","saleprice":"128.0","webprice":"148.0","row_number":"1","url":"/index.php/LjbScenic/shows.php"},{"scenicname":"海口火山口地质公园","id":"2092","scenicid":"51259","opentime":"8:00-18:00。","scenicaddress":"海南省海口市石山镇火山群世界地质公园。","defaultpic":"http://m.tuniucdn.com/fb2/t1/G2/M00/63/CA/Cii-TFjeIZSIbKXJAAGreb2Wq78AAI7vgPsEWkAAauR186_w240_h135_c1_t0.jpg","hits":"1681","saleprice":"30.0","webprice":"30.0","row_number":"2","url":"/index.php/LjbScenic/shows.php"},{"scenicname":"海南热带野生动植物园","id":"2569","scenicid":"19940","opentime":"8:00-18:00。","scenicaddress":"海南省海口市秀英区东山镇（海榆中线27公里处）。","defaultpic":"http://m.tuniucdn.com/filebroker/cdn/olb/ed/95/ed95936ef2c3abc93e29897bc703af2a_w240_h135_c1_t0.jpg","hits":"1681","saleprice":"55.0","webprice":"64.0","row_number":"3","url":"/index.php/LjbScenic/shows.php"}]
     * msg : 数据获取成功
     */

    private int status;
    private String msg;
    private List<AdsdataBean> adsdata;
    private List<MainBean> main;
    private List<LocalBean> local;

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

    public List<AdsdataBean> getAdsdata() {
        return adsdata;
    }

    public void setAdsdata(List<AdsdataBean> adsdata) {
        this.adsdata = adsdata;
    }

    public List<MainBean> getMain() {
        return main;
    }

    public void setMain(List<MainBean> main) {
        this.main = main;
    }

    public List<LocalBean> getLocal() {
        return local;
    }

    public void setLocal(List<LocalBean> local) {
        this.local = local;
    }

    public static class AdsdataBean {
        /**
         * type : image
         * name : 广告
         * url : http://apis.houno.cn#464
         * img : http://pic.houno.cn/Uploads/Images/20161021/1715185809dca665410.jpg
         * ordid : 255
         * row_number : 1
         */

        private String type;
        private String name;
        private String url;
        private String img;
        private String ordid;
        private String row_number;

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
    }

    public static class MainBean {
        /**
         * scenicname : 观澜湖新城
         * id : 1026
         * scenicid : 3532855
         * opentime : 10:00-22:00。
         * scenicaddress : 海南省海口观澜湖新城9栋三楼2304、2305号铺（兰桂坊楼上）反弹蹦床公园。
         * defaultpic : http://m.tuniucdn.com/fb2/t1/G1/M00/CE/03/Cii9EVjmGRWIOSJbAAFoJQWDFZwAAJYQgOggZYAAWg9863_w240_h135_c1_t0.jpg
         * hits : 1772
         * saleprice : 88.0
         * webprice : 98.0
         * row_number : 1
         * url : /index.php/LjbScenic/shows.php
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
        private String url;

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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class LocalBean {
        /**
         * scenicname : 观澜湖华谊冯小刚电影公社
         * id : 1933
         * scenicid : 1799896
         * opentime : 9:00-19:00。
         * scenicaddress : 海南省海口市观澜湖大道（西线高速往观澜湖温泉1.8公里处）。
         * defaultpic : http://m.tuniucdn.com/filebroker/cdn/olb/f5/5a/f55af960ef266798e8ffd02a98bbf9e8_w240_h135_c1_t0.jpg
         * hits : 1681
         * saleprice : 128.0
         * webprice : 148.0
         * row_number : 1
         * url : /index.php/LjbScenic/shows.php
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
        private String url;

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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
