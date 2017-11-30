package cn.houno.houniaolvju.bean;

import java.util.List;

/**
 * 国际酒店列表
 * Created by Administrator on 2017/1/13.
 */

public class ForeignHotelListBean {

    /**
     * status : 0
     * countInfo : {"total":"14","totalpage":2,"cityname":"全国"}
     * data : [{"id":"79","title":"苏梅岛万丽度假酒店","img":"http://pic.houno.cn/Uploads/Foreign/20160708/154212577f5954246e4.jpg","cate_id":"1","city":"苏梅岛","scenic":"山海","hits":"2860","address":"208/1 Moo 4, T. Maret, Lamai Beach · Koh Samui, 84310 Thailand ","sell":"1","row_number":"1","cate_name":"酒店","roomInfo":{"id":"214","title":"豪华园景房","area":"51.0","bed_num":"","people_num":"2","roomtype":"","webprice":"1670","marketprice":"0","row_number":"1","discount":"10"}},{"id":"76","title":"苏梅岛巴安达灵洲际度假酒店","img":"http://pic.houno.cn/Uploads/Foreign/20160708/141148577f4424e9a67.jpg","cate_id":"1","city":"苏梅岛","scenic":"山海","hits":"291","address":"295, Moo 3, Taling Ngam Beach,","sell":"0","row_number":"2","cate_name":"酒店","roomInfo":{"id":"200","title":"海景房","area":"67.0","bed_num":"","people_num":"2","roomtype":"","webprice":"4038","marketprice":"0","row_number":"1","discount":"10"}},{"id":"75","title":"苏梅岛W酒店","img":"http://pic.houno.cn/Uploads/Foreign/20160708/111717577f1b3d4de70.jpg","cate_id":"1","city":"苏梅岛","scenic":"山海","hits":"177","address":"Moo 1, Tambol Maenam, 4/1-84330 Ko Samui, Mae Nam","sell":"0","row_number":"3","cate_name":"酒店","roomInfo":{"id":"193","title":"丛林绿洲私人 泳池别墅","area":"163.0","bed_num":"","people_num":"2","roomtype":"","webprice":"5589","marketprice":"0","row_number":"1","discount":"10"}},{"id":"74","title":"巴厘岛库塔喜来登度假酒店","img":"http://pic.houno.cn/Uploads/Foreign/20160708/102729577f0f91c7454.jpg","cate_id":"1","city":"巴厘岛","scenic":"山海","hits":"256","address":"Jalan Pantai Kuta · Kuta, IDB 80361 · Indonesia","sell":"0","row_number":"4","cate_name":"酒店","roomInfo":{"id":"191","title":"海洋景豪华房 (限时特惠)","area":"46.0","bed_num":"","people_num":"2","roomtype":"","webprice":"1578","marketprice":"0","row_number":"1","discount":"10"}},{"id":"73","title":"巴厘岛金巴兰四季度假村","img":"http://pic.houno.cn/Uploads/Foreign/20160708/095244577f076cd4648.jpg","cate_id":"4","city":"巴厘岛","scenic":"山海","hits":"948","address":"KAWASAN BUKIT PERMAI, JIMBARAN, BALI 80361, INDONESIA","sell":"0","row_number":"5","cate_name":"度假村","roomInfo":{"id":"183","title":"一卧室别墅","area":"200.0","bed_num":"","people_num":"2","roomtype":"","webprice":"2946","marketprice":"0","row_number":"1","discount":"10"}},{"id":"71","title":"巴厘岛悦榕庄度假酒店","img":"http://pic.houno.cn/Uploads/Foreign/20160706/153519577cb4b76899e.jpg","cate_id":"1","city":"巴厘岛","scenic":"山海","hits":"531","address":"Jalan Melasti, Banjar Kelod, Ungasan, Bali,indonesia","sell":"0","row_number":"6","cate_name":"酒店","roomInfo":{"id":"172","title":"园景泳池别墅","area":"403.0","bed_num":"","people_num":"2","roomtype":"","webprice":"3988","marketprice":"0","row_number":"1","discount":"10"}},{"id":"70","title":"巴厘岛丽思卡尔顿酒店","img":"http://pic.houno.cn/Uploads/Foreign/20160706/145951577cac671104f.jpg","cate_id":"1","city":"巴厘岛","scenic":"山海","hits":"136","address":"Jalan Raya Nusa Dua Lot #3,Banjar Sawangan","sell":"1","row_number":"7","cate_name":"酒店","roomInfo":{"id":"168","title":"普通套房 (限时抢购)","area":"99.0","bed_num":"","people_num":"2","roomtype":"","webprice":"2088","marketprice":"0","row_number":"1","discount":"10"}},{"id":"67","title":"普吉岛纳卡岛豪华精选度假酒店","img":"http://pic.houno.cn/Uploads/Foreign/20160706/115918577c82163add5.jpg","cate_id":"1","city":"普吉岛","scenic":"山海","hits":"85","address":"32 Moo 5,Tambol Paklok Amphur Thalang, Naka Yai Island Phu","sell":"0","row_number":"8","cate_name":"酒店","roomInfo":{"id":"158","title":"热带泳池别墅","area":"450.0","bed_num":"","people_num":"2","roomtype":"","webprice":"3028","marketprice":"0","row_number":"1","discount":"10"}},{"id":"66","title":"普吉岛艾美海滩度假村","img":"http://pic.houno.cn/Uploads/Foreign/20160706/111152577c76f86f2ab.jpg","cate_id":"4","city":"普吉岛","scenic":"山海","hits":"227","address":"29 Soi Karon Nui, Tambon Karon Amphur Muang Phuket, Thaila","sell":"0","row_number":"9","cate_name":"度假村","roomInfo":{"id":"154","title":"豪华园景房","area":"37.0","bed_num":"","people_num":"2","roomtype":"","webprice":"1478","marketprice":"0","row_number":"1","discount":"10"}},{"id":"65","title":"普吉岛希尔顿阿卡迪亚度假村","img":"http://pic.houno.cn/Uploads/Foreign/20160706/103034577c6d4ac23e9.jpg","cate_id":"4","city":"普吉岛","scenic":"山海","hits":"258","address":"333 Patak Road, Karon Beach, Muang, Phuket, Thailand","sell":"0","row_number":"10","cate_name":"度假村","roomInfo":{"id":"148","title":"豪华园景房","area":"44.0","bed_num":"","people_num":"2","roomtype":"","webprice":"1266","marketprice":"0","row_number":"1","discount":"10"}}]
     */

    private int status;
    private CountInfoBean countInfo;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public CountInfoBean getCountInfo() {
        return countInfo;
    }

    public void setCountInfo(CountInfoBean countInfo) {
        this.countInfo = countInfo;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class CountInfoBean {
        /**
         * total : 14
         * totalpage : 2
         * cityname : 全国
         */

        private String total;
        private int totalpage;
        private String cityname;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public int getTotalpage() {
            return totalpage;
        }

        public void setTotalpage(int totalpage) {
            this.totalpage = totalpage;
        }

        public String getCityname() {
            return cityname;
        }

        public void setCityname(String cityname) {
            this.cityname = cityname;
        }
    }

    public static class DataBean {
        /**
         * id : 79
         * title : 苏梅岛万丽度假酒店
         * img : http://pic.houno.cn/Uploads/Foreign/20160708/154212577f5954246e4.jpg
         * cate_id : 1
         * city : 苏梅岛
         * scenic : 山海
         * hits : 2860
         * address : 208/1 Moo 4, T. Maret, Lamai Beach · Koh Samui, 84310 Thailand
         * sell : 1
         * row_number : 1
         * cate_name : 酒店
         * roomInfo : {"id":"214","title":"豪华园景房","area":"51.0","bed_num":"","people_num":"2","roomtype":"","webprice":"1670","marketprice":"0","row_number":"1","discount":"10"}
         */

        private String id;
        private String title;
        private String img;
        private String cate_id;
        private String city;
        private String scenic;
        private String hits;
        private String address;
        private String sell;
        private String row_number;
        private String cate_name;
        private RoomInfoBean roomInfo;

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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
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

        public String getScenic() {
            return scenic;
        }

        public void setScenic(String scenic) {
            this.scenic = scenic;
        }

        public String getHits() {
            return hits;
        }

        public void setHits(String hits) {
            this.hits = hits;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getSell() {
            return sell;
        }

        public void setSell(String sell) {
            this.sell = sell;
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

        public RoomInfoBean getRoomInfo() {
            return roomInfo;
        }

        public void setRoomInfo(RoomInfoBean roomInfo) {
            this.roomInfo = roomInfo;
        }

        public static class RoomInfoBean {
            /**
             * id : 214
             * title : 豪华园景房
             * area : 51.0
             * bed_num :
             * people_num : 2
             * roomtype :
             * webprice : 1670
             * marketprice : 0
             * row_number : 1
             * discount : 10
             */

            private String id;
            private String title;
            private String area;
            private String bed_num;
            private String people_num;
            private String roomtype;
            private String webprice;
            private String marketprice;
            private String row_number;
            private String discount;

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

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getBed_num() {
                return bed_num;
            }

            public void setBed_num(String bed_num) {
                this.bed_num = bed_num;
            }

            public String getPeople_num() {
                return people_num;
            }

            public void setPeople_num(String people_num) {
                this.people_num = people_num;
            }

            public String getRoomtype() {
                return roomtype;
            }

            public void setRoomtype(String roomtype) {
                this.roomtype = roomtype;
            }

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

            public String getRow_number() {
                return row_number;
            }

            public void setRow_number(String row_number) {
                this.row_number = row_number;
            }

            public String getDiscount() {
                return discount;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
            }
        }
    }
}
