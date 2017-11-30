package cn.houno.houniaolvju.bean;

import java.util.List;

/**
 * 项目名称：Houniaolvju
 * 类描述：团购列表实体类
 * 创建人：qzc
 * 创建时间：2016/12/15 19:26
 * 修改人：qzc
 * 修改时间：2016/12/15 19:26
 * 修改备注：
 */
public class TgHotelListBean {


    /**
     * id : 25604
     * cate_id : 1
     * img : http://pic.houno.cn/Uploads/Hotel/20161206/102614584621c6ee81c.jpg
     * map : 18.7528850000|110.2272510000
     * lat : 18.7528850000
     * lng : 110.2272510000
     * title : 兴隆曼特宁温泉度假酒店
     * star : 1
     * is_sale : 0
     * is_recommend : 0
     * is_hot : 0
     * waptj : 3
     * is_wifi : 1
     * is_network : 1
     * is_breakfast : 0
     * is_restaurant : 0
     * is_more_room : 0
     * is_big_bed : 0
     * is_tow_bed : 0
     * is_park : 1
     * is_swimming : 1
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
     * street : 温泉大道
     * merName : {"id":"268384","title":"高级园景房","area":"56","bed_num":"2","people_num":"2","roomtype":"","webprice":"198","marketprice":"0","row_number":"1"}
     * tg : {"id":"37","rid":"268384","title":"5间团购","num":"5","price":"","discount_money":"30","remark":"","status":"1","addtime":"2016-12-07 17:35:53.000","start_time":"2016-12-07 00:00:00.000","end_time":"2017-01-10 00:00:00.000","mer_id":"0","row_number":"1"}
     */

    private List<TgHotelBean> tgHotel;

    public List<TgHotelBean> getTgHotel() {
        return tgHotel;
    }

    public void setTgHotel(List<TgHotelBean> tgHotel) {
        this.tgHotel = tgHotel;
    }

    public static class TgHotelBean {
        private String id;
        private String cate_id;
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
        /**
         * id : 268384
         * title : 高级园景房
         * area : 56
         * bed_num : 2
         * people_num : 2
         * roomtype :
         * webprice : 198
         * marketprice : 0
         * row_number : 1
         */

        private RoomBean room;
        /**
         * id : 37
         * rid : 268384
         * title : 5间团购
         * num : 5
         * price :
         * discount_money : 30
         * remark :
         * status : 1
         * addtime : 2016-12-07 17:35:53.000
         * start_time : 2016-12-07 00:00:00.000
         * end_time : 2017-01-10 00:00:00.000
         * mer_id : 0
         * row_number : 1
         */

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

        public static class RoomBean {
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

        public static class TgBean {
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
}
