package cn.houno.houniaolvju.bean;

import java.util.List;

/**
 * 特价机票
 * Created by qzc on 2017/2/22.
 */

public class FlightSpecialSaleBean {


    /**
     * status : 0
     * data : [{"departureTime":"20:00","systemId":53,"orgCityCode":902,"seatStatus":"A","airCompanyName":"首都航空","week":2,"dstCityIata":null,"dstAirportIataCode":"CSX","dstCityName":"长沙","orgAirportIataCode":"HAK","discount":1,"lowest":false,"seatType":"Y","dstCityCode":1502,"flightNo":"JD5319","orgCityIata":null,"price":120,"seatCode":"V2","airCompany":"JD","orgCityName":"海口","id":"53_902_1502_2017-10-17_Y","departureDate":"2017-10-17","comLogo":null,"comName":null,"depName":"海口","arrName":"长沙","ticketPrice":120,"depDate":"2017-10-17","depAirport":"HAK","arrAirport":"CSX"},{"departureTime":"18:50","systemId":53,"orgCityCode":902,"seatStatus":"A","airCompanyName":"中国南方航空","week":2,"dstCityIata":null,"dstAirportIataCode":"WUH","dstCityName":"武汉","orgAirportIataCode":"HAK","discount":2,"lowest":false,"seatType":"Y","dstCityCode":1402,"flightNo":"CZ3342","orgCityIata":null,"price":280,"seatCode":"T","airCompany":"CZ","orgCityName":"海口","id":"53_902_1402_2017-10-17_Y","departureDate":"2017-10-17","comLogo":null,"comName":null,"depName":"海口","arrName":"武汉","ticketPrice":280,"depDate":"2017-10-17","depAirport":"HAK","arrAirport":"WUH"},{"departureTime":"16:55","systemId":53,"orgCityCode":902,"seatStatus":"A","airCompanyName":"天津航空","week":2,"dstCityIata":null,"dstAirportIataCode":"HFE","dstCityName":"合肥","orgAirportIataCode":"HAK","discount":1.9,"lowest":false,"seatType":"Y","dstCityCode":102,"flightNo":"GS6533","orgCityIata":null,"price":340,"seatCode":"Z","airCompany":"GS","orgCityName":"海口","id":"53_902_102_2017-10-17_Y","departureDate":"2017-10-17","comLogo":null,"comName":null,"depName":"海口","arrName":"合肥","ticketPrice":340,"depDate":"2017-10-17","depAirport":"HAK","arrAirport":"HFE"},{"departureTime":"11:10","systemId":53,"orgCityCode":902,"seatStatus":"5","airCompanyName":"九元航空","week":2,"dstCityIata":null,"dstAirportIataCode":"HRB","dstCityName":"哈尔滨","orgAirportIataCode":"HAK","discount":1.1,"lowest":false,"seatType":"Y","dstCityCode":1102,"flightNo":"AQ1039","orgCityIata":null,"price":369,"seatCode":"Z5","airCompany":"AQ","orgCityName":"海口","id":"53_902_1102_2017-10-17_Y","departureDate":"2017-10-17","comLogo":null,"comName":null,"depName":"海口","arrName":"哈尔滨","ticketPrice":369,"depDate":"2017-10-17","depAirport":"HAK","arrAirport":"HRB"},{"departureTime":"10:00","systemId":53,"orgCityCode":902,"seatStatus":"A","airCompanyName":"海南航空","week":2,"dstCityIata":null,"dstAirportIataCode":"SJW","dstCityName":"石家庄","orgAirportIataCode":"HAK","discount":2.5,"lowest":false,"seatType":"Y","dstCityCode":1002,"flightNo":"HU7627","orgCityIata":null,"price":555,"seatCode":"T","airCompany":"HU","orgCityName":"海口","id":"53_902_1002_2017-10-17_Y","departureDate":"2017-10-17","comLogo":null,"comName":null,"depName":"海口","arrName":"石家庄","ticketPrice":555,"depDate":"2017-10-17","depAirport":"HAK","arrAirport":"SJW"},{"departureTime":"18:20","systemId":53,"orgCityCode":902,"seatStatus":"A","airCompanyName":"东海航空","week":2,"dstCityIata":null,"dstAirportIataCode":"CGO","dstCityName":"郑州","orgAirportIataCode":"HAK","discount":3.8,"lowest":false,"seatType":"Y","dstCityCode":1202,"flightNo":"DZ6304","orgCityIata":null,"price":705,"seatCode":"W","airCompany":"DZ","orgCityName":"海口","id":"53_902_1202_2017-10-17_Y","departureDate":"2017-10-17","comLogo":null,"comName":null,"depName":"海口","arrName":"郑州","ticketPrice":705,"depDate":"2017-10-17","depAirport":"HAK","arrAirport":"CGO"}]
     * msg : 数据获取成功
     */

    private int status;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * departureTime : 20:00
         * systemId : 53
         * orgCityCode : 902
         * seatStatus : A
         * airCompanyName : 首都航空
         * week : 2
         * dstCityIata : null
         * dstAirportIataCode : CSX
         * dstCityName : 长沙
         * orgAirportIataCode : HAK
         * discount : 1
         * lowest : false
         * seatType : Y
         * dstCityCode : 1502
         * flightNo : JD5319
         * orgCityIata : null
         * price : 120
         * seatCode : V2
         * airCompany : JD
         * orgCityName : 海口
         * id : 53_902_1502_2017-10-17_Y
         * departureDate : 2017-10-17
         * comLogo : null
         * comName : null
         * depName : 海口
         * arrName : 长沙
         * ticketPrice : 120
         * depDate : 2017-10-17
         * depAirport : HAK
         * arrAirport : CSX
         */

        private String departureTime;
        private int systemId;
        private int orgCityCode;
        private String seatStatus;
        private String airCompanyName;
        private int week;
        private Object dstCityIata;
        private String dstAirportIataCode;
        private String dstCityName;
        private String orgAirportIataCode;
        private double discount;
        private boolean lowest;
        private String seatType;
        private int dstCityCode;
        private String flightNo;
        private Object orgCityIata;
        private int price;
        private String seatCode;
        private String airCompany;
        private String orgCityName;
        private String id;
        private String departureDate;
        private Object comLogo;
        private Object comName;
        private String depName;
        private String arrName;
        private int ticketPrice;
        private String depDate;
        private String depAirport;
        private String arrAirport;

        public String getDepartureTime() {
            return departureTime;
        }

        public void setDepartureTime(String departureTime) {
            this.departureTime = departureTime;
        }

        public int getSystemId() {
            return systemId;
        }

        public void setSystemId(int systemId) {
            this.systemId = systemId;
        }

        public int getOrgCityCode() {
            return orgCityCode;
        }

        public void setOrgCityCode(int orgCityCode) {
            this.orgCityCode = orgCityCode;
        }

        public String getSeatStatus() {
            return seatStatus;
        }

        public void setSeatStatus(String seatStatus) {
            this.seatStatus = seatStatus;
        }

        public String getAirCompanyName() {
            return airCompanyName;
        }

        public void setAirCompanyName(String airCompanyName) {
            this.airCompanyName = airCompanyName;
        }

        public int getWeek() {
            return week;
        }

        public void setWeek(int week) {
            this.week = week;
        }

        public Object getDstCityIata() {
            return dstCityIata;
        }

        public void setDstCityIata(Object dstCityIata) {
            this.dstCityIata = dstCityIata;
        }

        public String getDstAirportIataCode() {
            return dstAirportIataCode;
        }

        public void setDstAirportIataCode(String dstAirportIataCode) {
            this.dstAirportIataCode = dstAirportIataCode;
        }

        public String getDstCityName() {
            return dstCityName;
        }

        public void setDstCityName(String dstCityName) {
            this.dstCityName = dstCityName;
        }

        public String getOrgAirportIataCode() {
            return orgAirportIataCode;
        }

        public void setOrgAirportIataCode(String orgAirportIataCode) {
            this.orgAirportIataCode = orgAirportIataCode;
        }

        public double getDiscount() {
            return discount;
        }

        public void setDiscount(double discount) {
            this.discount = discount;
        }

        public boolean isLowest() {
            return lowest;
        }

        public void setLowest(boolean lowest) {
            this.lowest = lowest;
        }

        public String getSeatType() {
            return seatType;
        }

        public void setSeatType(String seatType) {
            this.seatType = seatType;
        }

        public int getDstCityCode() {
            return dstCityCode;
        }

        public void setDstCityCode(int dstCityCode) {
            this.dstCityCode = dstCityCode;
        }

        public String getFlightNo() {
            return flightNo;
        }

        public void setFlightNo(String flightNo) {
            this.flightNo = flightNo;
        }

        public Object getOrgCityIata() {
            return orgCityIata;
        }

        public void setOrgCityIata(Object orgCityIata) {
            this.orgCityIata = orgCityIata;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getSeatCode() {
            return seatCode;
        }

        public void setSeatCode(String seatCode) {
            this.seatCode = seatCode;
        }

        public String getAirCompany() {
            return airCompany;
        }

        public void setAirCompany(String airCompany) {
            this.airCompany = airCompany;
        }

        public String getOrgCityName() {
            return orgCityName;
        }

        public void setOrgCityName(String orgCityName) {
            this.orgCityName = orgCityName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDepartureDate() {
            return departureDate;
        }

        public void setDepartureDate(String departureDate) {
            this.departureDate = departureDate;
        }

        public Object getComLogo() {
            return comLogo;
        }

        public void setComLogo(Object comLogo) {
            this.comLogo = comLogo;
        }

        public Object getComName() {
            return comName;
        }

        public void setComName(Object comName) {
            this.comName = comName;
        }

        public String getDepName() {
            return depName;
        }

        public void setDepName(String depName) {
            this.depName = depName;
        }

        public String getArrName() {
            return arrName;
        }

        public void setArrName(String arrName) {
            this.arrName = arrName;
        }

        public int getTicketPrice() {
            return ticketPrice;
        }

        public void setTicketPrice(int ticketPrice) {
            this.ticketPrice = ticketPrice;
        }

        public String getDepDate() {
            return depDate;
        }

        public void setDepDate(String depDate) {
            this.depDate = depDate;
        }

        public String getDepAirport() {
            return depAirport;
        }

        public void setDepAirport(String depAirport) {
            this.depAirport = depAirport;
        }

        public String getArrAirport() {
            return arrAirport;
        }

        public void setArrAirport(String arrAirport) {
            this.arrAirport = arrAirport;
        }
    }
}
