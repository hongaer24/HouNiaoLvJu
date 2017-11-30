package cn.houno.houniaolvju.bean;

import java.io.Serializable;
import java.util.List;

import cn.houno.houniaolvju.activity.train.TrainPassengersBean;

/**
 * 作 者：陈亮
 * <p/>
 * 版本1.0
 * <p/>
 * 描述：
 * <p/>
 * 修订历史:
 */
public class OrderListBean implements Serializable{

    /**
     * status : 0
     * count : 25
     * data : [{"id":"2393","uid":"357","orderno":"20170222144706546910357","hid":"16346","rid":"689458","type":"hotel","model":"room                ","add_time":"1487746026","checkin":"2017-02-22","checkout":"2017-02-23","price":"1379.00","status":"0","pay_status":"0","num":"1","days":1,"detail":{"roomname":"豪华海景客房","img":"http://pic.houno.cn/Uploads/ctrip/5052633/200w070000002hjty0FF1_R_550_412.jpg","hid":"16346","bed_num":"2","people_num":"2","roomtype":"32963","payfs":"0","row_number":"1","id":"16346","title":"海口朗廷酒店","address":"龙华区滨海大道77号，近万绿园，紧邻京华城，宜欣城，中餐厅为香港米其林唐阁姐妹餐厅。 （ 国贸市中心）","city":"8","telphone":""}},{"id":"41","uid":"357","orderno":"20170221181916712500357","hid":"0","rid":"0","type":"Flight","model":"","add_time":"1487672360","checkin":"2017-03-31","checkout":"2017-03-31","price":"830.00","status":"3","pay_status":"1","num":"1","detail":{"id":"41","orderno":"20170221181916712500357","passengerinfo":[{"name":"何声武","type":"0","identityno":"460006198911190918","identitytype":"1"}],"depcode":"PEK","arrcode":"DLC","seatclass":"A","parprice":"780.00","depname":"北京","arrname":"大连","deptime":"1020","arrtime":"1150","flightno":"CA1605","airlines":"中国国航","linkphone":"13389892635","seatmsg":"头等舱","price":"830.00","orgcity":"北京首都机场T3","dstcity":"大连国际机场","meal":"false","planemodel":"73K","ticket_status":"0","row_number":"1"}},{"id":"39","uid":"357","orderno":"20170221174443770670357","hid":"0","rid":"0","type":"Flight","model":"","add_time":"1487670285","checkin":"2017-05-17","checkout":"2017-05-17","price":"2150.00","status":"1","pay_status":"1","num":"1","detail":{"id":"39","orderno":"20170221174443770670357","passengerinfo":[{"name":"何声武","type":"0","identityno":"460006198911190918","identitytype":"1"}],"depcode":"FOC","arrcode":"NKG","seatclass":"F","parprice":"2100.00","depname":"福州","arrname":"南京","deptime":"1430","arrtime":"1555","flightno":"ZH9708","airlines":"深圳航空公司","linkphone":"13389892635","seatmsg":"头等舱","price":"2150.00","orgcity":"福州长乐机场","dstcity":"南京禄口机场","meal":"true","planemodel":"320","ticket_status":"0","row_number":"1"}},{"id":"38","uid":"357","orderno":"20170221170751704510357","hid":"0","rid":"0","type":"Flight","model":"","add_time":"1487668073","checkin":"2017-05-17","checkout":"2017-05-17","price":"2150.00","status":"0","pay_status":"1","num":"1","detail":{"id":"38","orderno":"20170221170751704510357","passengerinfo":[{"name":"何声武","type":"0","identityno":"460006198911190918","identitytype":"1"}],"depcode":"FOC","arrcode":"NKG","seatclass":"F","parprice":"2100.00","depname":"福州","arrname":"南京","deptime":"1430","arrtime":"1555","flightno":"ZH9708","airlines":"深圳航空公司","linkphone":"13389892635","seatmsg":"头等舱","price":"2150.00","orgcity":"福州长乐机场","dstcity":"南京禄口机场","meal":"true","planemodel":"320","ticket_status":"0","row_number":"1"}},{"id":"37","uid":"357","orderno":"20170221170541411390357","hid":"0","rid":"0","type":"Flight","model":"","add_time":"1487667944","checkin":"2017-05-17","checkout":"2017-05-17","price":"2150.00","status":"0","pay_status":"1","num":"1","detail":{"id":"37","orderno":"20170221170541411390357","passengerinfo":[{"name":"何声武","type":"0","identityno":"460006198911190918","identitytype":"1"}],"depcode":"FOC","arrcode":"NKG","seatclass":"F","parprice":"2100.00","depname":"福州","arrname":"南京","deptime":"1430","arrtime":"1555","flightno":"ZH9708","airlines":"深圳航空公司","linkphone":"13389892635","seatmsg":"头等舱","price":"2150.00","orgcity":"福州长乐机场","dstcity":"南京禄口机场","meal":"true","planemodel":"320","ticket_status":"0","row_number":"1"}},{"id":"36","uid":"357","orderno":"20170221165606606860357","hid":"0","rid":"0","type":"Flight","model":"","add_time":"1487667369","checkin":"2017-05-17","checkout":"2017-05-17","price":"2150.00","status":"0","pay_status":"1","num":"1","detail":{"id":"36","orderno":"20170221165606606860357","passengerinfo":[{"name":"何声武","type":"0","identityno":"460006198911190918","identitytype":"1"}],"depcode":"NKG","arrcode":"FOC","seatclass":"F","parprice":"2100.00","depname":"南京","arrname":"福州","deptime":"1450","arrtime":"1610","flightno":"ZH9577","airlines":"深圳航空公司","linkphone":"13389892635","seatmsg":"头等舱","price":"2150.00","orgcity":"南京禄口机场","dstcity":"福州长乐机场","meal":"true","planemodel":"738","ticket_status":"0","row_number":"1"}},{"id":"30","uid":"357","orderno":"20170221113734005670357","hid":"0","rid":"0","type":"Flight","model":"","add_time":"1487648262","checkin":"2017-04-06","checkout":"2017-04-06","price":"1290.00","status":"0","pay_status":"0","num":"1","detail":{"id":"30","orderno":"20170221113734005670357","passengerinfo":[{"name":"何声武","type":"0","identityno":"460006198911190918","identitytype":"1"}],"depcode":"PEK","arrcode":"SHA","seatclass":"Y","parprice":"1240.00","depname":"北京","arrname":"上海","deptime":"0625","arrtime":"0840","flightno":"CZ6412","airlines":"南方航空","linkphone":"13389892635","seatmsg":"经济舱","price":"1290.00","orgcity":"北京首都机场T2","dstcity":"上海虹桥机场T2","meal":"true","planemodel":"321","ticket_status":"0","row_number":"1"}},{"id":"401","uid":"357","orderno":"20170219150550726130","hid":"11","rid":"0","type":"Acti","model":"","add_time":"1487487950","checkin":"2017-02-19","checkout":"2017-02-20","price":"198.00","status":"0","pay_status":"0","num":"1","detail":{"img":"http://pic.houno.cn/Uploads/OffActi/20161118/170014582ec31ee9de8.png","title":"海口周边一日游","row_number":"1"}},{"id":"293","uid":"357","orderno":"20170206150100338380","hid":"34","rid":"73","type":"Scenic","model":"ScenicTicket","add_time":"1486364460","checkin":"2017-02-06","checkout":"2017-02-07","price":".01","status":"1","pay_status":"1","num":"1","detail":{"img":"http://pic.houno.cn/Uploads/Scenic/20161008/15545257f8a64c84fb0.jpg","title":"海口观澜湖温泉","address":"海南省海口市龙华区龙桥镇观澜湖大道1号海口观澜湖旅游度假区","roomname":"成人票","row_number":"1"}},{"id":"292","uid":"357","orderno":"20170206145743543470","hid":"34","rid":"73","type":"Scenic","model":"ScenicTicket","add_time":"1486364263","checkin":"2017-02-06","checkout":"2017-02-07","price":"128.00","status":"1","pay_status":"0","num":"1","detail":{"img":"http://pic.houno.cn/Uploads/Scenic/20161008/15545257f8a64c84fb0.jpg","title":"海口观澜湖温泉","address":"海南省海口市龙华区龙桥镇观澜湖大道1号海口观澜湖旅游度假区","roomname":"成人票","row_number":"1"}}]
     * msg : 数据获取成功
     */

    private int status;
    private String count;
    private String msg;
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

    public static class DataBean implements Serializable{
        /**
         * id : 2393
         * uid : 357
         * orderno : 20170222144706546910357
         * hid : 16346
         * rid : 689458
         * type : hotel
         * model : room
         * add_time : 1487746026
         * checkin : 2017-02-22
         * checkout : 2017-02-23
         * price : 1379.00
         * status : 0
         * pay_status : 0
         * num : 1
         * days : 1
         * detail : {"roomname":"豪华海景客房","img":"http://pic.houno.cn/Uploads/ctrip/5052633/200w070000002hjty0FF1_R_550_412.jpg","hid":"16346","bed_num":"2","people_num":"2","roomtype":"32963","payfs":"0","row_number":"1","id":"16346","title":"海口朗廷酒店","address":"龙华区滨海大道77号，近万绿园，紧邻京华城，宜欣城，中餐厅为香港米其林唐阁姐妹餐厅。 （ 国贸市中心）","city":"8","telphone":""}
         */

        private String id;
        private String uid;
        private String orderno;
        private String hid;
        private String rid;
        private String type;
        private String model;
        private String add_time;
        private String checkin;
        private String checkout;
        private String price;
        private String status;
        private String pay_status;
        private String num;
        private int days;
        private DetailBean detail;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getOrderno() {
            return orderno;
        }

        public void setOrderno(String orderno) {
            this.orderno = orderno;
        }

        public String getHid() {
            return hid;
        }

        public void setHid(String hid) {
            this.hid = hid;
        }

        public String getRid() {
            return rid;
        }

        public void setRid(String rid) {
            this.rid = rid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getCheckin() {
            return checkin;
        }

        public void setCheckin(String checkin) {
            this.checkin = checkin;
        }

        public String getCheckout() {
            return checkout;
        }

        public void setCheckout(String checkout) {
            this.checkout = checkout;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPay_status() {
            return pay_status;
        }

        public void setPay_status(String pay_status) {
            this.pay_status = pay_status;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public int getDays() {
            return days;
        }

        public void setDays(int days) {
            this.days = days;
        }

        public DetailBean getDetail() {
            return detail;
        }

        public void setDetail(DetailBean detail) {
            this.detail = detail;
        }

        public static class DetailBean implements Serializable{
            /**
             * roomname : 豪华海景客房
             * img : http://pic.houno.cn/Uploads/ctrip/5052633/200w070000002hjty0FF1_R_550_412.jpg
             * hid : 16346
             * bed_num : 2
             * people_num : 2
             * roomtype : 32963
             * payfs : 0
             * row_number : 1
             * id : 16346
             * title : 海口朗廷酒店
             * address : 龙华区滨海大道77号，近万绿园，紧邻京华城，宜欣城，中餐厅为香港米其林唐阁姐妹餐厅。 （ 国贸市中心）
             * city : 8
             * telphone :
             */

            private String roomname;
            private String img;
            private String hid;
            private String bed_num;
            private String people_num;
            private String roomtype;
            private String payfs;
            private String row_number;
            private String id;
            private String title;
            private String address;
            private String city;
            private String telphone;
            //机票
            /**
             * id:39
             * orderno:20170221174443770670357
             * passengerinfobean:
             * depcode : FOC
             * arrcode : NKG
             * seatclass : F
             * parprice : 2100.00
             * depname : 福州
             * arrname : 南京
             * deptime : 1430
             * arrtime : 1555
             * flightno : ZH9708
             * airlines : 深圳航空公司
             * linkphone : 13389892635
             * seatmsg : 头等舱
             * price : 2150.00
             * orgcity : 福州长乐机场
             * dstcity : 南京禄口机场
             * meal : true
             * planemodel : 320
             * ticket_status : 0
             */
            private String orderno;

            private List<PassengerinfoBean> passengerinfo;


            private String depcode;
            private String arrcode;
            private String seatclass;
            private String parprice;
            private String depname;
            private String arrname;
            private String deptime;
            private String arrtime;
            private String flightno;
            private String airlines;
            private String linkphone;
            private String seatmsg;
            private String price;
            private String orgcity;
            private String dstcity;
            private String meal;
            private String planemodel;
            private String ticket_status;
            private String totalparprice;
            private String totalairporttax;


            //火车票
            private String from_station_name;
            private String to_station_name;
            private String train_code;
            private String status;
            private String pay_status;
            private String train_date;
            private String start_time;
            private String arrive_time;
            List<TrainPassengersBean> passengers;


            public String getFrom_station_name() {
                return from_station_name;
            }

            public void setFrom_station_name(String from_station_name) {
                this.from_station_name = from_station_name;
            }

            public String getTo_station_name() {
                return to_station_name;
            }

            public void setTo_station_name(String to_station_name) {
                this.to_station_name = to_station_name;
            }

            public String getTrain_code() {
                return train_code;
            }

            public void setTrain_code(String train_code) {
                this.train_code = train_code;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getPay_status() {
                return pay_status;
            }

            public void setPay_status(String pay_status) {
                this.pay_status = pay_status;
            }

            public String getTrain_date() {
                return train_date;
            }

            public void setTrain_date(String train_date) {
                this.train_date = train_date;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getArrive_time() {
                return arrive_time;
            }

            public void setArrive_time(String arrive_time) {
                this.arrive_time = arrive_time;
            }

            public List<TrainPassengersBean> getPassengers() {
                return passengers;
            }

            public void setPassengers(List<TrainPassengersBean> passengers) {
                this.passengers = passengers;
            }

            public String getRoomname() {
                return roomname;
            }

            public void setRoomname(String roomname) {
                this.roomname = roomname;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getHid() {
                return hid;
            }

            public void setHid(String hid) {
                this.hid = hid;
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

            public String getPayfs() {
                return payfs;
            }

            public void setPayfs(String payfs) {
                this.payfs = payfs;
            }

            public String getRow_number() {
                return row_number;
            }

            public void setRow_number(String row_number) {
                this.row_number = row_number;
            }

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

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getTelphone() {
                return telphone;
            }

            public void setTelphone(String telphone) {
                this.telphone = telphone;
            }

            public String getOrderno() {
                return orderno;
            }

            public void setOrderno(String orderno) {
                this.orderno = orderno;
            }

            public List<PassengerinfoBean> getPassengerinfo() {
                return passengerinfo;
            }

            public void setPassengerinfo(List<PassengerinfoBean> passengerinfo) {
                this.passengerinfo = passengerinfo;
            }

            public String getDepcode() {
                return depcode;
            }

            public void setDepcode(String depcode) {
                this.depcode = depcode;
            }

            public String getArrcode() {
                return arrcode;
            }

            public void setArrcode(String arrcode) {
                this.arrcode = arrcode;
            }

            public String getSeatclass() {
                return seatclass;
            }

            public void setSeatclass(String seatclass) {
                this.seatclass = seatclass;
            }

            public String getParprice() {
                return parprice;
            }

            public void setParprice(String parprice) {
                this.parprice = parprice;
            }

            public String getDepname() {
                return depname;
            }

            public void setDepname(String depname) {
                this.depname = depname;
            }

            public String getArrname() {
                return arrname;
            }

            public void setArrname(String arrname) {
                this.arrname = arrname;
            }

            public String getDeptime() {
                return deptime;
            }

            public void setDeptime(String deptime) {
                this.deptime = deptime;
            }

            public String getArrtime() {
                return arrtime;
            }

            public void setArrtime(String arrtime) {
                this.arrtime = arrtime;
            }

            public String getFlightno() {
                return flightno;
            }

            public void setFlightno(String flightno) {
                this.flightno = flightno;
            }

            public String getAirlines() {
                return airlines;
            }

            public void setAirlines(String airlines) {
                this.airlines = airlines;
            }

            public String getLinkphone() {
                return linkphone;
            }

            public void setLinkphone(String linkphone) {
                this.linkphone = linkphone;
            }

            public String getSeatmsg() {
                return seatmsg;
            }

            public void setSeatmsg(String seatmsg) {
                this.seatmsg = seatmsg;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getOrgcity() {
                return orgcity;
            }

            public void setOrgcity(String orgcity) {
                this.orgcity = orgcity;
            }

            public String getDstcity() {
                return dstcity;
            }

            public void setDstcity(String dstcity) {
                this.dstcity = dstcity;
            }

            public String getMeal() {
                return meal;
            }

            public void setMeal(String meal) {
                this.meal = meal;
            }

            public String getPlanemodel() {
                return planemodel;
            }

            public void setPlanemodel(String planemodel) {
                this.planemodel = planemodel;
            }

            public String getTicket_status() {
                return ticket_status;
            }

            public void setTicket_status(String ticket_status) {
                this.ticket_status = ticket_status;
            }

            public String getTotalparprice() {
                return totalparprice;
            }

            public void setTotalparprice(String totalparprice) {
                this.totalparprice = totalparprice;
            }

            public String getTotalairporttax() {
                return totalairporttax;
            }

            public void setTotalairporttax(String totalairporttax) {
                this.totalairporttax = totalairporttax;
            }

            public static class PassengerinfoBean implements Serializable{
                /**
                 * name : 何声武
                 * type : 0
                 * identityno : 460006198911190918
                 * identitytype : 1
                 */

                private String passengername;
                private String name;
                private String type;
                private String identityno;
                private String identitytype;


                public String getPassengername() {
                    return passengername;
                }

                public void setPassengername(String passengername) {
                    this.passengername = passengername;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }


                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getIdentityno() {
                    return identityno;
                }

                public void setIdentityno(String identityno) {
                    this.identityno = identityno;
                }

                public String getIdentitytype() {
                    return identitytype;
                }

                public void setIdentitytype(String identitytype) {
                    this.identitytype = identitytype;
                }
            }
        }
    }
}
