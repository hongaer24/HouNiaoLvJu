package cn.houno.houniaolvju.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：Houniaolvju
 * 类描述： 首页数据实体类
 * 创建人：qzc
 * 创建时间：2016/12/15 19:12
 * 修改人：qzc
 * 修改时间：2016/12/15 19:12
 * 修改备注：
 */
public class HomeIndexDataBean implements Serializable {


    /**
     * status : 0
     * data : {"sports":[{"id":"13","img":"http://pic.houno.cn/Uploads/OffActi/20170426/09463158fffbf7d9fdb.jpg","title":"220元限时抢！海口三弦印象·家酒店","market_price":"298.0000","price":"220.0000","ksdate":"2017-04-25","jsdate":"2017-06-30","row_number":"1","now_date":"2017-12-25 21:18:45"}],"tgHotel":[{"id":"25604","cate_id":"1","commentnum":"9","average":"5.0","img":"http://pic.houno.cn/Uploads/Hotel/20170118/170327587f2f5fe3443.jpg","map":"18.7528850000|110.2272510000","lat":"18.7528850000","lng":"110.2272510000","title":"兴隆曼特宁温泉度假酒店","star":"9","is_sale":"0","is_recommend":"0","is_hot":"0","waptj":"3","is_wifi":"1","is_network":"1","is_breakfast":"1","is_restaurant":"1","is_more_room":"0","is_big_bed":"1","is_tow_bed":"0","is_park":"1","is_swimming":"1","is_waiter":"0","is_gym":"0","is_meeting":"0","is_business":"0","is_bus":"0","is_newopen":"1","is_reception":"0","is_pet":"0","row_number":"1","catename":"酒店","street":"温泉大道","room":{"id":"268385","title":"豪华景观房","area":"56","bed_num":"2","people_num":"2","roomtype":"高级双床房","webprice":"220","marketprice":"0","row_number":"1"},"tg":{"id":"36","rid":"268385","title":"5间团购","num":"5","price":"","discount_money":"30","remark":"","status":"1","addtime":"2017-06-08 09:32:27.000","start_time":"2016-12-07 00:00:00.000","end_time":"2017-11-30 00:00:00.000","mer_id":"103","row_number":"1"}}],"actiScenic":[],"actiToursScenic":[{"scenicname":"观澜湖新城","id":"1026","scenicid":"3532855","opentime":"10:00-22:00。","scenicaddress":"海南省海口观澜湖新城9栋三楼2304、2305号铺（兰桂坊楼上）反弹蹦床公园。","defaultpic":"http://m.tuniucdn.com/fb2/t1/G1/M00/CE/03/Cii9EVjmGRWIOSJbAAFoJQWDFZwAAJYQgOggZYAAWg9863_w240_h135_c1_t0.jpg","hits":"6997","saleprice":"88.0","webprice":"98.0","row_number":"1"}],"mainHotel":[{"id":"33421","cate_id":"1","commentnum":"0","average":"5.0","img":"http://pic.houno.cn/Uploads/ctrip/5910043/200408000000374gxB4B4_R_550_412.jpg","map":"39.862312|116.413506","lat":"39.8623120","lng":"116.4135060","title":"麗枫酒店（北京南站木樨园店）","star":"4","is_sale":"0","is_recommend":"0","is_hot":"0","waptj":"1","is_wifi":"0","is_network":"0","is_breakfast":"0","is_restaurant":"0","is_more_room":"0","is_big_bed":"0","is_tow_bed":"0","is_park":"0","is_swimming":"0","is_waiter":"0","is_gym":"0","is_meeting":"0","is_business":"0","is_bus":"0","is_newopen":"0","is_reception":"0","is_pet":"0","row_number":"1","catename":"酒店","street":"东罗园路","room":{"id":"959625","title":"高级大床房（无窗）","area":"20","bed_num":"1","people_num":"2","roomtype":"高级大床房（无窗）","webprice":"456","marketprice":"0","row_number":"1"}}],"mainTours":[{"id":"6753","productname":"<埃及-迪拜10日游>阿航成都直飞，马车巡游卢克索神庙，帆船游香蕉岛，2晚红海，棕榈岛单轨列车，全程WIFI，性价比连游，金字塔，狮身人面像","classbrandname":"常规跟团","price":8599,"img":"http://m.tuniucdn.com/fb2/t1/G1/M00/1A/E5/Cii9EVkeyDaIPFFHABzmDlqfWo0AAKWFwI1HKEAHOYm573_w450_h300_c1_t0.jpg","hits":"877","row_number":"1"},{"id":"7","productname":"<张家界森林公园-天门山-大峡谷-玻璃桥-凤凰古城双飞5日游>直飞张家界、玻璃桥栈道、玻璃桥、回程贴心安排接机、省时省心","classbrandname":"常规跟团","price":3190,"img":"http://m.tuniucdn.com/filebroker/cdn/online/50/29/5029c46d_w450_h300_c1_t0.jpg","hits":"74","row_number":"2"},{"id":"5487","productname":"<云南-普洱-西双版纳-丛林亲子派对3飞6日游>小熊猫庄园独栋木屋，菩提岛露天泳池，热带植物园科普，澜沧江快艇，野象谷家庭合影，丛林舞蹈晚宴","classbrandname":"常规跟团","price":3074,"img":"http://m.tuniucdn.com/fb2/t1/G1/M00/C2/2C/Cii9EVjcmQOIXcL2ACEEWXMI5_wAAJOVwMu0LoAIQRx362_w450_h300_c1_t0.jpg","hits":"64","row_number":"3"}]}
     * msg : 数据获取成功
     */

    private int status;
    private DataBean data;
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        private List<SportsBean> sports;
        private List<TgHotelBean> tgHotel;
        private List<?> actiScenic;
        private List<ActiToursScenicBean> actiToursScenic;
        private List<MainHotelBean> mainHotel;
        private List<MainToursBean> mainTours;

        public List<SportsBean> getSports() {
            return sports;
        }

        public void setSports(List<SportsBean> sports) {
            this.sports = sports;
        }

        public List<TgHotelBean> getTgHotel() {
            return tgHotel;
        }

        public void setTgHotel(List<TgHotelBean> tgHotel) {
            this.tgHotel = tgHotel;
        }

        public List<?> getActiScenic() {
            return actiScenic;
        }

        public void setActiScenic(List<?> actiScenic) {
            this.actiScenic = actiScenic;
        }

        public List<ActiToursScenicBean> getActiToursScenic() {
            return actiToursScenic;
        }

        public void setActiToursScenic(List<ActiToursScenicBean> actiToursScenic) {
            this.actiToursScenic = actiToursScenic;
        }

        public List<MainHotelBean> getMainHotel() {
            return mainHotel;
        }

        public void setMainHotel(List<MainHotelBean> mainHotel) {
            this.mainHotel = mainHotel;
        }

        public List<MainToursBean> getMainTours() {
            return mainTours;
        }

        public void setMainTours(List<MainToursBean> mainTours) {
            this.mainTours = mainTours;
        }

        public static class SportsBean {
            /**
             * id : 13
             * img : http://pic.houno.cn/Uploads/OffActi/20170426/09463158fffbf7d9fdb.jpg
             * title : 220元限时抢！海口三弦印象·家酒店
             * market_price : 298.0000
             * price : 220.0000
             * ksdate : 2017-04-25
             * jsdate : 2017-06-30
             * row_number : 1
             * now_date : 2017-12-25 21:18:45
             */

            private String id;
            private String img;
            private String title;
            private String market_price;
            private String price;
            private String ksdate;
            private String jsdate;
            private String row_number;
            private String now_date;

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

            public String getMarket_price() {
                return market_price;
            }

            public void setMarket_price(String market_price) {
                this.market_price = market_price;
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

            public String getNow_date() {
                return now_date;
            }

            public void setNow_date(String now_date) {
                this.now_date = now_date;
            }
        }

        public static class TgHotelBean implements Serializable {
            /**
             * id : 25604
             * cate_id : 1
             * commentnum : 9
             * average : 5.0
             * img : http://pic.houno.cn/Uploads/Hotel/20170118/170327587f2f5fe3443.jpg
             * map : 18.7528850000|110.2272510000
             * lat : 18.7528850000
             * lng : 110.2272510000
             * title : 兴隆曼特宁温泉度假酒店
             * star : 9
             * is_sale : 0
             * is_recommend : 0
             * is_hot : 0
             * waptj : 3
             * is_wifi : 1
             * is_network : 1
             * is_breakfast : 1
             * is_restaurant : 1
             * is_more_room : 0
             * is_big_bed : 1
             * is_tow_bed : 0
             * is_park : 1
             * is_swimming : 1
             * is_waiter : 0
             * is_gym : 0
             * is_meeting : 0
             * is_business : 0
             * is_bus : 0
             * is_newopen : 1
             * is_reception : 0
             * is_pet : 0
             * row_number : 1
             * catename : 酒店
             * street : 温泉大道
             * room : {"id":"268385","title":"豪华景观房","area":"56","bed_num":"2","people_num":"2","roomtype":"高级双床房","webprice":"220","marketprice":"0","row_number":"1"}
             * tg : {"id":"36","rid":"268385","title":"5间团购","num":"5","price":"","discount_money":"30","remark":"","status":"1","addtime":"2017-06-08 09:32:27.000","start_time":"2016-12-07 00:00:00.000","end_time":"2017-11-30 00:00:00.000","mer_id":"103","row_number":"1"}
             */

            private String id;
            private String cate_id;
            private String commentnum;
            private String average;
            private String img;
            private String map;
            private String lat;
            private String lng;
            private String title;
            private String star;
            private String is_sale;
            private String is_recommend;
            private String is_hot;
            private String waptj;
            private String is_wifi;
            private String is_network;
            private String is_breakfast;
            private String is_restaurant;
            private String is_more_room;
            private String is_big_bed;
            private String is_tow_bed;
            private String is_park;
            private String is_swimming;
            private String is_waiter;
            private String is_gym;
            private String is_meeting;
            private String is_business;
            private String is_bus;
            private String is_newopen;
            private String is_reception;
            private String is_pet;
            private String row_number;
            private String catename;
            private String street;
            private RoomBean room;
            private TgBean tg;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCate_id() {
                return cate_id;
            }

            public void setCate_id(String cate_id) {
                this.cate_id = cate_id;
            }

            public String getCommentnum() {
                return commentnum;
            }

            public void setCommentnum(String commentnum) {
                this.commentnum = commentnum;
            }

            public String getAverage() {
                return average;
            }

            public void setAverage(String average) {
                this.average = average;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getMap() {
                return map;
            }

            public void setMap(String map) {
                this.map = map;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getStar() {
                return star;
            }

            public void setStar(String star) {
                this.star = star;
            }

            public String getIs_sale() {
                return is_sale;
            }

            public void setIs_sale(String is_sale) {
                this.is_sale = is_sale;
            }

            public String getIs_recommend() {
                return is_recommend;
            }

            public void setIs_recommend(String is_recommend) {
                this.is_recommend = is_recommend;
            }

            public String getIs_hot() {
                return is_hot;
            }

            public void setIs_hot(String is_hot) {
                this.is_hot = is_hot;
            }

            public String getWaptj() {
                return waptj;
            }

            public void setWaptj(String waptj) {
                this.waptj = waptj;
            }

            public String getIs_wifi() {
                return is_wifi;
            }

            public void setIs_wifi(String is_wifi) {
                this.is_wifi = is_wifi;
            }

            public String getIs_network() {
                return is_network;
            }

            public void setIs_network(String is_network) {
                this.is_network = is_network;
            }

            public String getIs_breakfast() {
                return is_breakfast;
            }

            public void setIs_breakfast(String is_breakfast) {
                this.is_breakfast = is_breakfast;
            }

            public String getIs_restaurant() {
                return is_restaurant;
            }

            public void setIs_restaurant(String is_restaurant) {
                this.is_restaurant = is_restaurant;
            }

            public String getIs_more_room() {
                return is_more_room;
            }

            public void setIs_more_room(String is_more_room) {
                this.is_more_room = is_more_room;
            }

            public String getIs_big_bed() {
                return is_big_bed;
            }

            public void setIs_big_bed(String is_big_bed) {
                this.is_big_bed = is_big_bed;
            }

            public String getIs_tow_bed() {
                return is_tow_bed;
            }

            public void setIs_tow_bed(String is_tow_bed) {
                this.is_tow_bed = is_tow_bed;
            }

            public String getIs_park() {
                return is_park;
            }

            public void setIs_park(String is_park) {
                this.is_park = is_park;
            }

            public String getIs_swimming() {
                return is_swimming;
            }

            public void setIs_swimming(String is_swimming) {
                this.is_swimming = is_swimming;
            }

            public String getIs_waiter() {
                return is_waiter;
            }

            public void setIs_waiter(String is_waiter) {
                this.is_waiter = is_waiter;
            }

            public String getIs_gym() {
                return is_gym;
            }

            public void setIs_gym(String is_gym) {
                this.is_gym = is_gym;
            }

            public String getIs_meeting() {
                return is_meeting;
            }

            public void setIs_meeting(String is_meeting) {
                this.is_meeting = is_meeting;
            }

            public String getIs_business() {
                return is_business;
            }

            public void setIs_business(String is_business) {
                this.is_business = is_business;
            }

            public String getIs_bus() {
                return is_bus;
            }

            public void setIs_bus(String is_bus) {
                this.is_bus = is_bus;
            }

            public String getIs_newopen() {
                return is_newopen;
            }

            public void setIs_newopen(String is_newopen) {
                this.is_newopen = is_newopen;
            }

            public String getIs_reception() {
                return is_reception;
            }

            public void setIs_reception(String is_reception) {
                this.is_reception = is_reception;
            }

            public String getIs_pet() {
                return is_pet;
            }

            public void setIs_pet(String is_pet) {
                this.is_pet = is_pet;
            }

            public String getRow_number() {
                return row_number;
            }

            public void setRow_number(String row_number) {
                this.row_number = row_number;
            }

            public String getCatename() {
                return catename;
            }

            public void setCatename(String catename) {
                this.catename = catename;
            }

            public String getStreet() {
                return street;
            }

            public void setStreet(String street) {
                this.street = street;
            }

            public RoomBean getRoom() {
                return room;
            }

            public void setRoom(RoomBean room) {
                this.room = room;
            }

            public TgBean getTg() {
                return tg;
            }

            public void setTg(TgBean tg) {
                this.tg = tg;
            }

            public static class RoomBean implements Serializable {
                /**
                 * id : 268385
                 * title : 豪华景观房
                 * area : 56
                 * bed_num : 2
                 * people_num : 2
                 * roomtype : 高级双床房
                 * webprice : 220
                 * marketprice : 0
                 * row_number : 1
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
            }

            public static class TgBean implements Serializable {
                /**
                 * id : 36
                 * rid : 268385
                 * title : 5间团购
                 * num : 5
                 * price :
                 * discount_money : 30
                 * remark :
                 * status : 1
                 * addtime : 2017-06-08 09:32:27.000
                 * start_time : 2016-12-07 00:00:00.000
                 * end_time : 2017-11-30 00:00:00.000
                 * mer_id : 103
                 * row_number : 1
                 */

                private String id;
                private String rid;
                private String title;
                private String num;
                private String price;
                private String discount_money;
                private String remark;
                private String status;
                private String addtime;
                private String start_time;
                private String end_time;
                private String mer_id;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getRid() {
                    return rid;
                }

                public void setRid(String rid) {
                    this.rid = rid;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getNum() {
                    return num;
                }

                public void setNum(String num) {
                    this.num = num;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getDiscount_money() {
                    return discount_money;
                }

                public void setDiscount_money(String discount_money) {
                    this.discount_money = discount_money;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getAddtime() {
                    return addtime;
                }

                public void setAddtime(String addtime) {
                    this.addtime = addtime;
                }

                public String getStart_time() {
                    return start_time;
                }

                public void setStart_time(String start_time) {
                    this.start_time = start_time;
                }

                public String getEnd_time() {
                    return end_time;
                }

                public void setEnd_time(String end_time) {
                    this.end_time = end_time;
                }

                public String getMer_id() {
                    return mer_id;
                }

                public void setMer_id(String mer_id) {
                    this.mer_id = mer_id;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }
        }

        public static class ActiToursScenicBean implements Serializable {
            /**
             * scenicname : 观澜湖新城
             * id : 1026
             * scenicid : 3532855
             * opentime : 10:00-22:00。
             * scenicaddress : 海南省海口观澜湖新城9栋三楼2304、2305号铺（兰桂坊楼上）反弹蹦床公园。
             * defaultpic : http://m.tuniucdn.com/fb2/t1/G1/M00/CE/03/Cii9EVjmGRWIOSJbAAFoJQWDFZwAAJYQgOggZYAAWg9863_w240_h135_c1_t0.jpg
             * hits : 6997
             * saleprice : 88.0
             * webprice : 98.0
             * row_number : 1
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
        }

        public static class MainHotelBean {
            /**
             * id : 33421
             * cate_id : 1
             * commentnum : 0
             * average : 5.0
             * img : http://pic.houno.cn/Uploads/ctrip/5910043/200408000000374gxB4B4_R_550_412.jpg
             * map : 39.862312|116.413506
             * lat : 39.8623120
             * lng : 116.4135060
             * title : 麗枫酒店（北京南站木樨园店）
             * star : 4
             * is_sale : 0
             * is_recommend : 0
             * is_hot : 0
             * waptj : 1
             * is_wifi : 0
             * is_network : 0
             * is_breakfast : 0
             * is_restaurant : 0
             * is_more_room : 0
             * is_big_bed : 0
             * is_tow_bed : 0
             * is_park : 0
             * is_swimming : 0
             * is_waiter : 0
             * is_gym : 0
             * is_meeting : 0
             * is_business : 0
             * is_bus : 0
             * is_newopen : 0
             * is_reception : 0
             * is_pet : 0
             * row_number : 1
             * catename : 酒店
             * street : 东罗园路
             * room : {"id":"959625","title":"高级大床房（无窗）","area":"20","bed_num":"1","people_num":"2","roomtype":"高级大床房（无窗）","webprice":"456","marketprice":"0","row_number":"1"}
             */

            private String id;
            private String cate_id;
            private String commentnum;
            private String average;
            private String img;
            private String map;
            private String lat;
            private String lng;
            private String title;
            private String star;
            private String is_sale;
            private String is_recommend;
            private String is_hot;
            private String waptj;
            private String is_wifi;
            private String is_network;
            private String is_breakfast;
            private String is_restaurant;
            private String is_more_room;
            private String is_big_bed;
            private String is_tow_bed;
            private String is_park;
            private String is_swimming;
            private String is_waiter;
            private String is_gym;
            private String is_meeting;
            private String is_business;
            private String is_bus;
            private String is_newopen;
            private String is_reception;
            private String is_pet;
            private String row_number;
            private String catename;
            private String street;
            private RoomBeanX room;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCate_id() {
                return cate_id;
            }

            public void setCate_id(String cate_id) {
                this.cate_id = cate_id;
            }

            public String getCommentnum() {
                return commentnum;
            }

            public void setCommentnum(String commentnum) {
                this.commentnum = commentnum;
            }

            public String getAverage() {
                return average;
            }

            public void setAverage(String average) {
                this.average = average;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getMap() {
                return map;
            }

            public void setMap(String map) {
                this.map = map;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getStar() {
                return star;
            }

            public void setStar(String star) {
                this.star = star;
            }

            public String getIs_sale() {
                return is_sale;
            }

            public void setIs_sale(String is_sale) {
                this.is_sale = is_sale;
            }

            public String getIs_recommend() {
                return is_recommend;
            }

            public void setIs_recommend(String is_recommend) {
                this.is_recommend = is_recommend;
            }

            public String getIs_hot() {
                return is_hot;
            }

            public void setIs_hot(String is_hot) {
                this.is_hot = is_hot;
            }

            public String getWaptj() {
                return waptj;
            }

            public void setWaptj(String waptj) {
                this.waptj = waptj;
            }

            public String getIs_wifi() {
                return is_wifi;
            }

            public void setIs_wifi(String is_wifi) {
                this.is_wifi = is_wifi;
            }

            public String getIs_network() {
                return is_network;
            }

            public void setIs_network(String is_network) {
                this.is_network = is_network;
            }

            public String getIs_breakfast() {
                return is_breakfast;
            }

            public void setIs_breakfast(String is_breakfast) {
                this.is_breakfast = is_breakfast;
            }

            public String getIs_restaurant() {
                return is_restaurant;
            }

            public void setIs_restaurant(String is_restaurant) {
                this.is_restaurant = is_restaurant;
            }

            public String getIs_more_room() {
                return is_more_room;
            }

            public void setIs_more_room(String is_more_room) {
                this.is_more_room = is_more_room;
            }

            public String getIs_big_bed() {
                return is_big_bed;
            }

            public void setIs_big_bed(String is_big_bed) {
                this.is_big_bed = is_big_bed;
            }

            public String getIs_tow_bed() {
                return is_tow_bed;
            }

            public void setIs_tow_bed(String is_tow_bed) {
                this.is_tow_bed = is_tow_bed;
            }

            public String getIs_park() {
                return is_park;
            }

            public void setIs_park(String is_park) {
                this.is_park = is_park;
            }

            public String getIs_swimming() {
                return is_swimming;
            }

            public void setIs_swimming(String is_swimming) {
                this.is_swimming = is_swimming;
            }

            public String getIs_waiter() {
                return is_waiter;
            }

            public void setIs_waiter(String is_waiter) {
                this.is_waiter = is_waiter;
            }

            public String getIs_gym() {
                return is_gym;
            }

            public void setIs_gym(String is_gym) {
                this.is_gym = is_gym;
            }

            public String getIs_meeting() {
                return is_meeting;
            }

            public void setIs_meeting(String is_meeting) {
                this.is_meeting = is_meeting;
            }

            public String getIs_business() {
                return is_business;
            }

            public void setIs_business(String is_business) {
                this.is_business = is_business;
            }

            public String getIs_bus() {
                return is_bus;
            }

            public void setIs_bus(String is_bus) {
                this.is_bus = is_bus;
            }

            public String getIs_newopen() {
                return is_newopen;
            }

            public void setIs_newopen(String is_newopen) {
                this.is_newopen = is_newopen;
            }

            public String getIs_reception() {
                return is_reception;
            }

            public void setIs_reception(String is_reception) {
                this.is_reception = is_reception;
            }

            public String getIs_pet() {
                return is_pet;
            }

            public void setIs_pet(String is_pet) {
                this.is_pet = is_pet;
            }

            public String getRow_number() {
                return row_number;
            }

            public void setRow_number(String row_number) {
                this.row_number = row_number;
            }

            public String getCatename() {
                return catename;
            }

            public void setCatename(String catename) {
                this.catename = catename;
            }

            public String getStreet() {
                return street;
            }

            public void setStreet(String street) {
                this.street = street;
            }

            public RoomBeanX getRoom() {
                return room;
            }

            public void setRoom(RoomBeanX room) {
                this.room = room;
            }

            public static class RoomBeanX {
                /**
                 * id : 959625
                 * title : 高级大床房（无窗）
                 * area : 20
                 * bed_num : 1
                 * people_num : 2
                 * roomtype : 高级大床房（无窗）
                 * webprice : 456
                 * marketprice : 0
                 * row_number : 1
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
            }
        }

        public static class MainToursBean {
            /**
             * id : 6753
             * productname : <埃及-迪拜10日游>阿航成都直飞，马车巡游卢克索神庙，帆船游香蕉岛，2晚红海，棕榈岛单轨列车，全程WIFI，性价比连游，金字塔，狮身人面像
             * classbrandname : 常规跟团
             * price : 8599
             * img : http://m.tuniucdn.com/fb2/t1/G1/M00/1A/E5/Cii9EVkeyDaIPFFHABzmDlqfWo0AAKWFwI1HKEAHOYm573_w450_h300_c1_t0.jpg
             * hits : 877
             * row_number : 1
             */

            private String id;
            private String productname;
            private String classbrandname;
            private int price;
            private String img;
            private String hits;
            private String row_number;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getProductname() {
                return productname;
            }

            public void setProductname(String productname) {
                this.productname = productname;
            }

            public String getClassbrandname() {
                return classbrandname;
            }

            public void setClassbrandname(String classbrandname) {
                this.classbrandname = classbrandname;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
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
        }
    }
}
