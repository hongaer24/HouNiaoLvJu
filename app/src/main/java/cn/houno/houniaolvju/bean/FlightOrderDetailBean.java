package cn.houno.houniaolvju.bean;

import java.util.List;

/**
 * Created by Administrator on 2017-10-19.
 */

public class FlightOrderDetailBean {


    /**
     * status : 0
     * data : {"orderno":"20171019171706560950448","linkman":"邱志超","linkphone":"18876819913","info":{"arrDate":"2017-11-16","airlines":"中国东方航空","seatMsg":"经济舱","solutionId":"9","cabinCode":"Z","parPrice":"482","meal":"","externalInfo":"eyJ0aWNrZXRpbmdDYXJyaWVyIjoiTVUiLCJleHRyYUluZm8iOiJNVS0zTXVTaG9wUmVzdWx0RXh0cmFfODI2MDE1MDBjNTJkOTFiOGUyODc5ZDRhYzQ1ZmI4YWEiLCJhZHVsdFRheCI6NTAsImFkdWx0RnVlbCI6MCwiY2hpbGRUYXgiOjAsImNoaWxkRnVlbCI6MCwidGlja2V0VHlwZSI6MCwiYWlyQ21wQWR1bHRQcmljZSI6NDgwLCJhaXJDbXBDaGlsZFByaWNlIjoyNTAsImFpckNtcEJhYnlQcmljZSI6MH0=","orgJetquay":"首都国际机场T2","name":"邱志超","totalPrice":"532","dstJetquay":"虹桥机场T2","arrName":"上海","airportTax":"50","depName":"北京","resId":"105189051","fullPrice":"1240","tel":"18876819913","vendorPolicyId":"","linkman":"邱志超"},"otherlinkmethod":"","policyid":"0","passengerinfo":[{"passengerName":"邱志超","tel":"18876819913","identityNo":"460028199201120838","personType":"1","identityType":"1","birthday":"1992-01-12"}],"p_number":"1","segments":"0","flightno":"MU5160","orgcity":"首都国际机场T2","dstcity":"虹桥机场T2","depcode":"BJS","arrcode":"SHA","depname":"北京","arrname":"上海","airlines":"中国东方航空","seatmsg":"经济舱","seatclass":"Y","depdate":"2017-11-16","arrdate":"2017-11-16","deptime":"2145","arrtime":"2355","planemodel":"333(大)","parprice":"482.00","fueltax":".00","airporttax":"50.00","status":"0","createdat":"1508404632","commisionpoin":"","seatdiscount":"","settleprice":"482.00","price":"532.00","totalparprice":"482.00","totalfueltax":".00","totalairporttax":"50.00","pay_status":"0","ticket_status":"0","ticket_message":"","pnrno":"0","outorderno":"1049304458","meal":"","ticketnos":"","changestatus":"0","changereason":"0","changetime":"","changeprice":".00","row_number":"1","returnMessage":"待支付"}
     */

    private int status;
    private DataBean data;

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

    public static class DataBean {
        /**
         * orderno : 20171019171706560950448
         * linkman : 邱志超
         * linkphone : 18876819913
         * info : {"arrDate":"2017-11-16","airlines":"中国东方航空","seatMsg":"经济舱","solutionId":"9","cabinCode":"Z","parPrice":"482","meal":"","externalInfo":"eyJ0aWNrZXRpbmdDYXJyaWVyIjoiTVUiLCJleHRyYUluZm8iOiJNVS0zTXVTaG9wUmVzdWx0RXh0cmFfODI2MDE1MDBjNTJkOTFiOGUyODc5ZDRhYzQ1ZmI4YWEiLCJhZHVsdFRheCI6NTAsImFkdWx0RnVlbCI6MCwiY2hpbGRUYXgiOjAsImNoaWxkRnVlbCI6MCwidGlja2V0VHlwZSI6MCwiYWlyQ21wQWR1bHRQcmljZSI6NDgwLCJhaXJDbXBDaGlsZFByaWNlIjoyNTAsImFpckNtcEJhYnlQcmljZSI6MH0=","orgJetquay":"首都国际机场T2","name":"邱志超","totalPrice":"532","dstJetquay":"虹桥机场T2","arrName":"上海","airportTax":"50","depName":"北京","resId":"105189051","fullPrice":"1240","tel":"18876819913","vendorPolicyId":"","linkman":"邱志超"}
         * otherlinkmethod :
         * policyid : 0
         * passengerinfo : [{"passengerName":"邱志超","tel":"18876819913","identityNo":"460028199201120838","personType":"1","identityType":"1","birthday":"1992-01-12"}]
         * p_number : 1
         * segments : 0
         * flightno : MU5160
         * orgcity : 首都国际机场T2
         * dstcity : 虹桥机场T2
         * depcode : BJS
         * arrcode : SHA
         * depname : 北京
         * arrname : 上海
         * airlines : 中国东方航空
         * seatmsg : 经济舱
         * seatclass : Y
         * depdate : 2017-11-16
         * arrdate : 2017-11-16
         * deptime : 2145
         * arrtime : 2355
         * planemodel : 333(大)
         * parprice : 482.00
         * fueltax : .00
         * airporttax : 50.00
         * status : 0
         * createdat : 1508404632
         * commisionpoin :
         * seatdiscount :
         * settleprice : 482.00
         * price : 532.00
         * totalparprice : 482.00
         * totalfueltax : .00
         * totalairporttax : 50.00
         * pay_status : 0
         * ticket_status : 0
         * ticket_message :
         * pnrno : 0
         * outorderno : 1049304458
         * meal :
         * ticketnos :
         * changestatus : 0
         * changereason : 0
         * changetime :
         * changeprice : .00
         * row_number : 1
         * returnMessage : 待支付
         */

        private String orderno;
        private String linkman;
        private String linkphone;
        private InfoBean info;
        private String otherlinkmethod;
        private String policyid;
        private String p_number;
        private String segments;
        private String flightno;
        private String orgcity;
        private String dstcity;
        private String depcode;
        private String arrcode;
        private String depname;
        private String arrname;
        private String airlines;
        private String seatmsg;
        private String seatclass;
        private String depdate;
        private String arrdate;
        private String deptime;
        private String arrtime;
        private String planemodel;
        private String parprice;
        private String fueltax;
        private String airporttax;
        private String status;
        private String createdat;
        private String commisionpoin;
        private String seatdiscount;
        private String settleprice;
        private String price;
        private String totalparprice;
        private String totalfueltax;
        private String totalairporttax;
        private String pay_status;
        private String ticket_status;
        private String ticket_message;
        private String pnrno;
        private String outorderno;
        private String meal;
        private String ticketnos;
        private String changestatus;
        private String changereason;
        private String changetime;
        private String changeprice;
        private String row_number;
        private String returnMessage;
        private List<PassengerinfoBean> passengerinfo;

        public String getOrderno() {
            return orderno;
        }

        public void setOrderno(String orderno) {
            this.orderno = orderno;
        }

        public String getLinkman() {
            return linkman;
        }

        public void setLinkman(String linkman) {
            this.linkman = linkman;
        }

        public String getLinkphone() {
            return linkphone;
        }

        public void setLinkphone(String linkphone) {
            this.linkphone = linkphone;
        }

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public String getOtherlinkmethod() {
            return otherlinkmethod;
        }

        public void setOtherlinkmethod(String otherlinkmethod) {
            this.otherlinkmethod = otherlinkmethod;
        }

        public String getPolicyid() {
            return policyid;
        }

        public void setPolicyid(String policyid) {
            this.policyid = policyid;
        }

        public String getP_number() {
            return p_number;
        }

        public void setP_number(String p_number) {
            this.p_number = p_number;
        }

        public String getSegments() {
            return segments;
        }

        public void setSegments(String segments) {
            this.segments = segments;
        }

        public String getFlightno() {
            return flightno;
        }

        public void setFlightno(String flightno) {
            this.flightno = flightno;
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

        public String getAirlines() {
            return airlines;
        }

        public void setAirlines(String airlines) {
            this.airlines = airlines;
        }

        public String getSeatmsg() {
            return seatmsg;
        }

        public void setSeatmsg(String seatmsg) {
            this.seatmsg = seatmsg;
        }

        public String getSeatclass() {
            return seatclass;
        }

        public void setSeatclass(String seatclass) {
            this.seatclass = seatclass;
        }

        public String getDepdate() {
            return depdate;
        }

        public void setDepdate(String depdate) {
            this.depdate = depdate;
        }

        public String getArrdate() {
            return arrdate;
        }

        public void setArrdate(String arrdate) {
            this.arrdate = arrdate;
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

        public String getPlanemodel() {
            return planemodel;
        }

        public void setPlanemodel(String planemodel) {
            this.planemodel = planemodel;
        }

        public String getParprice() {
            return parprice;
        }

        public void setParprice(String parprice) {
            this.parprice = parprice;
        }

        public String getFueltax() {
            return fueltax;
        }

        public void setFueltax(String fueltax) {
            this.fueltax = fueltax;
        }

        public String getAirporttax() {
            return airporttax;
        }

        public void setAirporttax(String airporttax) {
            this.airporttax = airporttax;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreatedat() {
            return createdat;
        }

        public void setCreatedat(String createdat) {
            this.createdat = createdat;
        }

        public String getCommisionpoin() {
            return commisionpoin;
        }

        public void setCommisionpoin(String commisionpoin) {
            this.commisionpoin = commisionpoin;
        }

        public String getSeatdiscount() {
            return seatdiscount;
        }

        public void setSeatdiscount(String seatdiscount) {
            this.seatdiscount = seatdiscount;
        }

        public String getSettleprice() {
            return settleprice;
        }

        public void setSettleprice(String settleprice) {
            this.settleprice = settleprice;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getTotalparprice() {
            return totalparprice;
        }

        public void setTotalparprice(String totalparprice) {
            this.totalparprice = totalparprice;
        }

        public String getTotalfueltax() {
            return totalfueltax;
        }

        public void setTotalfueltax(String totalfueltax) {
            this.totalfueltax = totalfueltax;
        }

        public String getTotalairporttax() {
            return totalairporttax;
        }

        public void setTotalairporttax(String totalairporttax) {
            this.totalairporttax = totalairporttax;
        }

        public String getPay_status() {
            return pay_status;
        }

        public void setPay_status(String pay_status) {
            this.pay_status = pay_status;
        }

        public String getTicket_status() {
            return ticket_status;
        }

        public void setTicket_status(String ticket_status) {
            this.ticket_status = ticket_status;
        }

        public String getTicket_message() {
            return ticket_message;
        }

        public void setTicket_message(String ticket_message) {
            this.ticket_message = ticket_message;
        }

        public String getPnrno() {
            return pnrno;
        }

        public void setPnrno(String pnrno) {
            this.pnrno = pnrno;
        }

        public String getOutorderno() {
            return outorderno;
        }

        public void setOutorderno(String outorderno) {
            this.outorderno = outorderno;
        }

        public String getMeal() {
            return meal;
        }

        public void setMeal(String meal) {
            this.meal = meal;
        }

        public String getTicketnos() {
            return ticketnos;
        }

        public void setTicketnos(String ticketnos) {
            this.ticketnos = ticketnos;
        }

        public String getChangestatus() {
            return changestatus;
        }

        public void setChangestatus(String changestatus) {
            this.changestatus = changestatus;
        }

        public String getChangereason() {
            return changereason;
        }

        public void setChangereason(String changereason) {
            this.changereason = changereason;
        }

        public String getChangetime() {
            return changetime;
        }

        public void setChangetime(String changetime) {
            this.changetime = changetime;
        }

        public String getChangeprice() {
            return changeprice;
        }

        public void setChangeprice(String changeprice) {
            this.changeprice = changeprice;
        }

        public String getRow_number() {
            return row_number;
        }

        public void setRow_number(String row_number) {
            this.row_number = row_number;
        }

        public String getReturnMessage() {
            return returnMessage;
        }

        public void setReturnMessage(String returnMessage) {
            this.returnMessage = returnMessage;
        }

        public List<PassengerinfoBean> getPassengerinfo() {
            return passengerinfo;
        }

        public void setPassengerinfo(List<PassengerinfoBean> passengerinfo) {
            this.passengerinfo = passengerinfo;
        }

        public static class InfoBean {
            /**
             * arrDate : 2017-11-16
             * airlines : 中国东方航空
             * seatMsg : 经济舱
             * solutionId : 9
             * cabinCode : Z
             * parPrice : 482
             * meal :
             * externalInfo : eyJ0aWNrZXRpbmdDYXJyaWVyIjoiTVUiLCJleHRyYUluZm8iOiJNVS0zTXVTaG9wUmVzdWx0RXh0cmFfODI2MDE1MDBjNTJkOTFiOGUyODc5ZDRhYzQ1ZmI4YWEiLCJhZHVsdFRheCI6NTAsImFkdWx0RnVlbCI6MCwiY2hpbGRUYXgiOjAsImNoaWxkRnVlbCI6MCwidGlja2V0VHlwZSI6MCwiYWlyQ21wQWR1bHRQcmljZSI6NDgwLCJhaXJDbXBDaGlsZFByaWNlIjoyNTAsImFpckNtcEJhYnlQcmljZSI6MH0=
             * orgJetquay : 首都国际机场T2
             * name : 邱志超
             * totalPrice : 532
             * dstJetquay : 虹桥机场T2
             * arrName : 上海
             * airportTax : 50
             * depName : 北京
             * resId : 105189051
             * fullPrice : 1240
             * tel : 18876819913
             * vendorPolicyId :
             * linkman : 邱志超
             */

            private String arrDate;
            private String airlines;
            private String seatMsg;
            private String solutionId;
            private String cabinCode;
            private String parPrice;
            private String meal;
            private String externalInfo;
            private String orgJetquay;
            private String name;
            private String totalPrice;
            private String dstJetquay;
            private String arrName;
            private String airportTax;
            private String depName;
            private String resId;
            private String fullPrice;
            private String tel;
            private String vendorPolicyId;
            private String linkman;

            public String getdPrice() {
                return dPrice;
            }

            public void setdPrice(String dPrice) {
                this.dPrice = dPrice;
            }

            private String dPrice;

            public String getArrDate() {
                return arrDate;
            }

            public void setArrDate(String arrDate) {
                this.arrDate = arrDate;
            }

            public String getAirlines() {
                return airlines;
            }

            public void setAirlines(String airlines) {
                this.airlines = airlines;
            }

            public String getSeatMsg() {
                return seatMsg;
            }

            public void setSeatMsg(String seatMsg) {
                this.seatMsg = seatMsg;
            }

            public String getSolutionId() {
                return solutionId;
            }

            public void setSolutionId(String solutionId) {
                this.solutionId = solutionId;
            }

            public String getCabinCode() {
                return cabinCode;
            }

            public void setCabinCode(String cabinCode) {
                this.cabinCode = cabinCode;
            }

            public String getParPrice() {
                return parPrice;
            }

            public void setParPrice(String parPrice) {
                this.parPrice = parPrice;
            }

            public String getMeal() {
                return meal;
            }

            public void setMeal(String meal) {
                this.meal = meal;
            }

            public String getExternalInfo() {
                return externalInfo;
            }

            public void setExternalInfo(String externalInfo) {
                this.externalInfo = externalInfo;
            }

            public String getOrgJetquay() {
                return orgJetquay;
            }

            public void setOrgJetquay(String orgJetquay) {
                this.orgJetquay = orgJetquay;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTotalPrice() {
                return totalPrice;
            }

            public void setTotalPrice(String totalPrice) {
                this.totalPrice = totalPrice;
            }

            public String getDstJetquay() {
                return dstJetquay;
            }

            public void setDstJetquay(String dstJetquay) {
                this.dstJetquay = dstJetquay;
            }

            public String getArrName() {
                return arrName;
            }

            public void setArrName(String arrName) {
                this.arrName = arrName;
            }

            public String getAirportTax() {
                return airportTax;
            }

            public void setAirportTax(String airportTax) {
                this.airportTax = airportTax;
            }

            public String getDepName() {
                return depName;
            }

            public void setDepName(String depName) {
                this.depName = depName;
            }

            public String getResId() {
                return resId;
            }

            public void setResId(String resId) {
                this.resId = resId;
            }

            public String getFullPrice() {
                return fullPrice;
            }

            public void setFullPrice(String fullPrice) {
                this.fullPrice = fullPrice;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getVendorPolicyId() {
                return vendorPolicyId;
            }

            public void setVendorPolicyId(String vendorPolicyId) {
                this.vendorPolicyId = vendorPolicyId;
            }

            public String getLinkman() {
                return linkman;
            }

            public void setLinkman(String linkman) {
                this.linkman = linkman;
            }
        }

        public static class PassengerinfoBean {
            /**
             * passengerName : 邱志超
             * tel : 18876819913
             * identityNo : 460028199201120838
             * personType : 1
             * identityType : 1
             * birthday : 1992-01-12
             */

            private String passengerName;
            private String tel;
            private String identityNo;
            private String personType;
            private String identityType;
            private String birthday;

            public String getPassengerName() {
                return passengerName;
            }

            public void setPassengerName(String passengerName) {
                this.passengerName = passengerName;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getIdentityNo() {
                return identityNo;
            }

            public void setIdentityNo(String identityNo) {
                this.identityNo = identityNo;
            }

            public String getPersonType() {
                return personType;
            }

            public void setPersonType(String personType) {
                this.personType = personType;
            }

            public String getIdentityType() {
                return identityType;
            }

            public void setIdentityType(String identityType) {
                this.identityType = identityType;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }
        }
    }
}
