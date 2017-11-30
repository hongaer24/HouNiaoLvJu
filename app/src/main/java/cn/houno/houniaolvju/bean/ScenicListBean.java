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
     * count : 1739
     * data : [{"id":"50","title":"蜈支洲岛","img":"http://pic.houno.cn/Uploads/Scenic/20161009/12025057f9c16a4ecbd.jpg","cate_id":"7","city":"445","hits":"8","address":"海南省三亚市海棠区林旺镇后海村蜈支洲岛","sell":"0","star":"2A以下景区","dianzang":"0","ziying":"1","webprice":"300.00","row_number":"1","cityname":"海棠湾","cate_name":"岛屿潜水","favorite":0},{"id":"80","title":"成都欢乐谷","img":"http://pic.houno.cn/Uploads/Scenic/20161017/1606345804868aef1fb.png","cate_id":"3","city":"97","hits":"17","address":"四川省成都市金牛区西华大道16号","sell":"0","star":"2A以下景区","dianzang":"0","ziying":"1","webprice":"215.00","row_number":"2","cityname":"成都","cate_name":"主题乐园","favorite":0},{"id":"99","title":" 张家界宝峰湖","img":"http://pic.houno.cn/Uploads/Scenic/20161018/1349005805b7cceace3.jpg","cate_id":"1","city":"394","hits":"9","address":"湖南省张家界市武陵源区索溪镇宝峰路8号","sell":"0","star":"2A以下景区","dianzang":"0","ziying":"1","webprice":"85.00","row_number":"3","cityname":"张家界","cate_name":"自然风景","favorite":0},{"id":"150","title":"高淳老街","img":"http://pic.houno.cn/Uploads/Scenic/20161019/1135315806ea03a9b30.jpg","cate_id":"2","city":"317","hits":"1","address":"","sell":"0","star":"4A级景区","dianzang":"0","ziying":"1","webprice":"25.00","row_number":"4","cityname":"南京","cate_name":"人文古迹","favorite":0},{"id":"180","title":"南湾猴岛","img":"http://pic.houno.cn/Uploads/Scenic/20161203/15073358426f3582d54.jpg","cate_id":"1","city":"441","hits":"4","address":"     海南省陵水县南约14公里的南湾半岛","sell":"0","star":"4A级景区","dianzang":"0","ziying":"1","webprice":"143.00","row_number":"5","cityname":"陵水","cate_name":"自然风景","favorite":0},{"id":"199","title":"何园","img":"http://pic.houno.cn/Uploads/Scenic/20161019/1657005807355cb6f2f.jpg","cate_id":"1","city":"329","hits":"2","address":"江苏省扬州市广陵区徐凝门街66号","sell":"0","star":"4A级景区","dianzang":"0","ziying":"1","webprice":"40.00","row_number":"6","cityname":"扬州","cate_name":"自然风景","favorite":0},{"id":"250","title":"锦里沟","img":"http://pic.houno.cn/Uploads/Scenic/20161020/141527580860ff27b9b.jpg","cate_id":"2","city":"383","hits":"5","address":"湖北省武汉市黄陂区蔡店乡道士冲村 ","sell":"0","star":"2A以下景区","dianzang":"0","ziying":"1","webprice":"38.00","row_number":"7","cityname":"武汉","cate_name":"人文古迹","favorite":0},{"id":"280","title":" 神农谷国家森林公园","img":"http://pic.houno.cn/Uploads/Scenic/20161020/164807580884c7a3bca.jpg","cate_id":"13","city":"453","hits":"7","address":"湖南省株洲市炎陵县 ","sell":"0","star":"2A以下景区","dianzang":"0","ziying":"1","webprice":"64.00","row_number":"8","cityname":"株洲","cate_name":"园林","favorite":0},{"id":"299","title":" 袁家寨子","img":"http://pic.houno.cn/Uploads/Scenic/20161021/09314758097003b52a9.jpg","cate_id":"2","city":"394","hits":"14","address":"湖南省张家界市武陵源区陵源风景名胜区内","sell":"0","star":"2A以下景区","dianzang":"0","ziying":"1","webprice":"200.00","row_number":"9","cityname":"张家界","cate_name":"人文古迹","favorite":0},{"id":"350","title":"自贡燊海井","img":"http://pic.houno.cn/Uploads/Scenic/20161022/141108580b02fcb2a23.jpg","cate_id":"2","city":"100","hits":"3","address":"     自贡市大安区大安街道大高路社区大安街262号","sell":"0","star":"2A以下景区","dianzang":"0","ziying":"1","webprice":"17.00","row_number":"10","cityname":"自贡","cate_name":"人文古迹","favorite":0}]
     */

    private int status;
    private String count;
    /**
     * id : 50
     * title : 蜈支洲岛
     * img : http://pic.houno.cn/Uploads/Scenic/20161009/12025057f9c16a4ecbd.jpg
     * cate_id : 7
     * city : 445
     * hits : 8
     * address : 海南省三亚市海棠区林旺镇后海村蜈支洲岛
     * sell : 0
     * star : 2A以下景区
     * dianzang : 0
     * ziying : 1
     * webprice : 300.00
     * row_number : 1
     * cityname : 海棠湾
     * cate_name : 岛屿潜水
     * favorite : 0
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        //id
        private String scenicid;
        //原title
        private String scenicname;
         //img
        private String defaultpic;
        private String cate_id;
        private String city;
        private String hits;
        //address
        private String scenicaddress;
        private String sell;
        private String star;
        private String dianzang;
        private String ziying;
        private String webprice;
        private String row_number;
        private String cityname;
        private String cate_name;
        private int favorite;

        public String getScenicid() {
            return scenicid;
        }

        public void setId(String id) {
            this.scenicid = id;
        }

        public String getScenicname() {
            return scenicname;
        }

        public void setTitle(String title) {
            this.scenicname = title;
        }

        public String getDefaultpic() {
            return  defaultpic;
        }

        public void setDefaultpic(String img) {
            this. defaultpic = img;
        }

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getHits() {
            return hits;
        }

        public void setHits(String hits) {
            this.hits = hits;
        }

        public String getScenicaddress() {
            return scenicaddress;
        }

        public void setScenicaddress(String address) {
            this.scenicaddress = address;
        }

        public String getSell() {
            return sell;
        }

        public void setSell(String sell) {
            this.sell = sell;
        }

        public String getStar() {
            return star;
        }

        public void setStar(String star) {
            this.star = star;
        }

        public String getDianzang() {
            return dianzang;
        }

        public void setDianzang(String dianzang) {
            this.dianzang = dianzang;
        }

        public String getZiying() {
            return ziying;
        }

        public void setZiying(String ziying) {
            this.ziying = ziying;
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

        public String getCityname() {
            return cityname;
        }

        public void setCityname(String cityname) {
            this.cityname = cityname;
        }

        public String getCate_name() {
            return cate_name;
        }

        public void setCate_name(String cate_name) {
            this.cate_name = cate_name;
        }

        public int getFavorite() {
            return favorite;
        }

        public void setFavorite(int favorite) {
            this.favorite = favorite;
        }
    }
}
