package cn.houno.houniaolvju.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 机票列表
 * Created by Administrator on 2017/2/14.
 */

public class FlightListBean implements Serializable{




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

    public static class DataBean implements  Serializable{


        private Object preDay;
        private Object selDay;
        private Object nextDay;
        private String depDate;
        private String depWeek;
        private List<FlightsBean> flights;

        public Object getPreDay() {
            return preDay;
        }

        public void setPreDay(Object preDay) {
            this.preDay = preDay;
        }

        public Object getSelDay() {
            return selDay;
        }

        public void setSelDay(int selDay) {
            this.selDay = selDay;
        }

        public Object getNextDay() {
            return nextDay;
        }

        public void setNextDay(int nextDay) {
            this.nextDay = nextDay;
        }

        public String getDepDate() {
            return depDate;
        }

        public void setDepDate(String depDate) {
            this.depDate = depDate;
        }

        public String getDepWeek() {
            return depWeek;
        }

        public void setDepWeek(String depWeek) {
            this.depWeek = depWeek;
        }

        public List<FlightsBean> getFlights() {
            return flights;
        }

        public void setFlights(List<FlightsBean> flights) {
            this.flights = flights;
        }

        public static class FlightsBean implements Serializable{


            private String depTime;
            private String arriTime;
            private String orgJetquay;
            private String dstJetquay;
            private String flightNo;
            private String param1;
            private String planeType;
            private String depName;
            private String arrName;
            private String depDate;
            private String depWeek;
            private String depAirport;
            private String arrAirport;
            private Object aviLogo;
            private String aviName;
            private String elapsedTime;
            private int parPrice;
            private int Price;
            private int airportTax;
            private List<SeatItemsBean> seatItems;

            public String getDepTime() {
                return depTime;
            }

            public void setDepTime(String depTime) {
                this.depTime = depTime;
            }

            public String getArriTime() {
                return arriTime;
            }

            public void setArriTime(String arriTime) {
                this.arriTime = arriTime;
            }

            public String getOrgJetquay() {
                return orgJetquay;
            }

            public void setOrgJetquay(String orgJetquay) {
                this.orgJetquay = orgJetquay;
            }

            public String getDstJetquay() {
                return dstJetquay;
            }

            public void setDstJetquay(String dstJetquay) {
                this.dstJetquay = dstJetquay;
            }

            public String getFlightNo() {
                return flightNo;
            }

            public void setFlightNo(String flightNo) {
                this.flightNo = flightNo;
            }

            public String getParam1() {
                return param1;
            }

            public void setParam1(String param1) {
                this.param1 = param1;
            }

            public String getPlaneType() {
                return planeType;
            }

            public void setPlaneType(String planeType) {
                this.planeType = planeType;
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

            public String getDepDate() {
                return depDate;
            }

            public void setDepDate(String depDate) {
                this.depDate = depDate;
            }

            public String getDepWeek() {
                return depWeek;
            }

            public void setDepWeek(String depWeek) {
                this.depWeek = depWeek;
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

            public Object getAviLogo() {
                return aviLogo;
            }

            public void setAviLogo(Object aviLogo) {
                this.aviLogo = aviLogo;
            }

            public String getAviName() {
                return aviName;
            }

            public void setAviName(String aviName) {
                this.aviName = aviName;
            }

            public String getElapsedTime() {
                return elapsedTime;
            }

            public void setElapsedTime(String elapsedTime) {
                this.elapsedTime = elapsedTime;
            }

            public int getParPrice() {
                return parPrice;
            }

            public void setParPrice(int parPrice) {
                this.parPrice = parPrice;
            }

            public int getPrice() {
                return Price;
            }

            public void setPrice(int Price) {
                this.Price = Price;
            }

            public int getAirportTax() {
                return airportTax;
            }

            public void setAirportTax(int airportTax) {
                this.airportTax = airportTax;
            }

            public List<SeatItemsBean> getSeatItems() {
                return seatItems;
            }

            public void setSeatItems(List<SeatItemsBean> seatItems) {
                this.seatItems = seatItems;
            }

            public static class SeatItemsBean implements  Serializable{


                private int mealFlag;
                private String airlineCompany;
                private String arriveCityIataCode;
                private String arriveAirportCode;
                private int stopNum;
                private int fullPrice;
                private String arriveCityName;
                private String shareFlightNo;
                private String arriveTime;
                private String duration;
                private String stopInformation;
                private String arriveAirportName;
                private String departAirportCode;
                private String departCityName;
                private String departTime;
                private String arriveDate;
                private String craftType;
                private String shareAirlineName;
                private int fullPriceF;
                private String departAirportTerminal;
                private Object shareAirline;
                private String airlineIataCode;
                private int fullPriceC;
                private String departCityIataCode;
                private Object meal;
                private String flightNo;
                private String arriveAirportTerminal;
                private int isShare;
                private String departDate;
                private Object stopPointList;
                private String departAirportName;
                private String discount;
                private int parPrice;
                private String param2;
                private String cabinCode;
                private String seatCode;
                private String seatMsg;
                private String seatStatus;
                private int seatType;
                private int settlePrice;
                private int price;
                private Object airCompany;
                private String policyId;
                private String vendorPolicyId;
                private int solutionId;
                private String resId;
                private String externalInfo;
                private List<?> policyData;

                public int getMealFlag() {
                    return mealFlag;
                }

                public void setMealFlag(int mealFlag) {
                    this.mealFlag = mealFlag;
                }

                public String getAirlineCompany() {
                    return airlineCompany;
                }

                public void setAirlineCompany(String airlineCompany) {
                    this.airlineCompany = airlineCompany;
                }

                public String getArriveCityIataCode() {
                    return arriveCityIataCode;
                }

                public void setArriveCityIataCode(String arriveCityIataCode) {
                    this.arriveCityIataCode = arriveCityIataCode;
                }

                public String getArriveAirportCode() {
                    return arriveAirportCode;
                }

                public void setArriveAirportCode(String arriveAirportCode) {
                    this.arriveAirportCode = arriveAirportCode;
                }

                public int getStopNum() {
                    return stopNum;
                }

                public void setStopNum(int stopNum) {
                    this.stopNum = stopNum;
                }

                public int getFullPrice() {
                    return fullPrice;
                }

                public void setFullPrice(int fullPrice) {
                    this.fullPrice = fullPrice;
                }

                public String getArriveCityName() {
                    return arriveCityName;
                }

                public void setArriveCityName(String arriveCityName) {
                    this.arriveCityName = arriveCityName;
                }

                public String getShareFlightNo() {
                    return shareFlightNo;
                }

                public void setShareFlightNo(String shareFlightNo) {
                    this.shareFlightNo = shareFlightNo;
                }

                public String getArriveTime() {
                    return arriveTime;
                }

                public void setArriveTime(String arriveTime) {
                    this.arriveTime = arriveTime;
                }

                public String getDuration() {
                    return duration;
                }

                public void setDuration(String duration) {
                    this.duration = duration;
                }

                public String getStopInformation() {
                    return stopInformation;
                }

                public void setStopInformation(String stopInformation) {
                    this.stopInformation = stopInformation;
                }

                public String getArriveAirportName() {
                    return arriveAirportName;
                }

                public void setArriveAirportName(String arriveAirportName) {
                    this.arriveAirportName = arriveAirportName;
                }

                public String getDepartAirportCode() {
                    return departAirportCode;
                }

                public void setDepartAirportCode(String departAirportCode) {
                    this.departAirportCode = departAirportCode;
                }

                public String getDepartCityName() {
                    return departCityName;
                }

                public void setDepartCityName(String departCityName) {
                    this.departCityName = departCityName;
                }

                public String getDepartTime() {
                    return departTime;
                }

                public void setDepartTime(String departTime) {
                    this.departTime = departTime;
                }

                public String getArriveDate() {
                    return arriveDate;
                }

                public void setArriveDate(String arriveDate) {
                    this.arriveDate = arriveDate;
                }

                public String getCraftType() {
                    return craftType;
                }

                public void setCraftType(String craftType) {
                    this.craftType = craftType;
                }

                public String getShareAirlineName() {
                    return shareAirlineName;
                }

                public void setShareAirlineName(String shareAirlineName) {
                    this.shareAirlineName = shareAirlineName;
                }

                public int getFullPriceF() {
                    return fullPriceF;
                }

                public void setFullPriceF(int fullPriceF) {
                    this.fullPriceF = fullPriceF;
                }

                public String getDepartAirportTerminal() {
                    return departAirportTerminal;
                }

                public void setDepartAirportTerminal(String departAirportTerminal) {
                    this.departAirportTerminal = departAirportTerminal;
                }

                public Object getShareAirline() {
                    return shareAirline;
                }

                public void setShareAirline(Object shareAirline) {
                    this.shareAirline = shareAirline;
                }

                public String getAirlineIataCode() {
                    return airlineIataCode;
                }

                public void setAirlineIataCode(String airlineIataCode) {
                    this.airlineIataCode = airlineIataCode;
                }

                public int getFullPriceC() {
                    return fullPriceC;
                }

                public void setFullPriceC(int fullPriceC) {
                    this.fullPriceC = fullPriceC;
                }

                public String getDepartCityIataCode() {
                    return departCityIataCode;
                }

                public void setDepartCityIataCode(String departCityIataCode) {
                    this.departCityIataCode = departCityIataCode;
                }

                public Object getMeal() {
                    return meal;
                }

                public void setMeal(Object meal) {
                    this.meal = meal;
                }

                public String getFlightNo() {
                    return flightNo;
                }

                public void setFlightNo(String flightNo) {
                    this.flightNo = flightNo;
                }

                public String getArriveAirportTerminal() {
                    return arriveAirportTerminal;
                }

                public void setArriveAirportTerminal(String arriveAirportTerminal) {
                    this.arriveAirportTerminal = arriveAirportTerminal;
                }

                public int getIsShare() {
                    return isShare;
                }

                public void setIsShare(int isShare) {
                    this.isShare = isShare;
                }

                public String getDepartDate() {
                    return departDate;
                }

                public void setDepartDate(String departDate) {
                    this.departDate = departDate;
                }

                public Object getStopPointList() {
                    return stopPointList;
                }

                public void setStopPointList(Object stopPointList) {
                    this.stopPointList = stopPointList;
                }

                public String getDepartAirportName() {
                    return departAirportName;
                }

                public void setDepartAirportName(String departAirportName) {
                    this.departAirportName = departAirportName;
                }

                public String getDiscount() {
                    return discount;
                }

                public void setDiscount(String discount) {
                    this.discount = discount;
                }

                public int getParPrice() {
                    return parPrice;
                }

                public void setParPrice(int parPrice) {
                    this.parPrice = parPrice;
                }

                public String getParam2() {
                    return param2;
                }

                public void setParam2(String param2) {
                    this.param2 = param2;
                }

                public String getCabinCode() {
                    return cabinCode;
                }

                public void setCabinCode(String cabinCode) {
                    this.cabinCode = cabinCode;
                }

                public String getSeatCode() {
                    return seatCode;
                }

                public void setSeatCode(String seatCode) {
                    this.seatCode = seatCode;
                }

                public String getSeatMsg() {
                    return seatMsg;
                }

                public void setSeatMsg(String seatMsg) {
                    this.seatMsg = seatMsg;
                }

                public String getSeatStatus() {
                    return seatStatus;
                }

                public void setSeatStatus(String seatStatus) {
                    this.seatStatus = seatStatus;
                }

                public int getSeatType() {
                    return seatType;
                }

                public void setSeatType(int seatType) {
                    this.seatType = seatType;
                }

                public int getSettlePrice() {
                    return settlePrice;
                }

                public void setSettlePrice(int settlePrice) {
                    this.settlePrice = settlePrice;
                }

                public int getPrice() {
                    return price;
                }

                public void setPrice(int price) {
                    this.price = price;
                }

                public Object getAirCompany() {
                    return airCompany;
                }

                public void setAirCompany(Object airCompany) {
                    this.airCompany = airCompany;
                }

                public String getPolicyId() {
                    return policyId;
                }

                public void setPolicyId(String policyId) {
                    this.policyId = policyId;
                }

                public String getVendorPolicyId() {
                    return vendorPolicyId;
                }

                public void setVendorPolicyId(String vendorPolicyId) {
                    this.vendorPolicyId = vendorPolicyId;
                }

                public int getSolutionId() {
                    return solutionId;
                }

                public void setSolutionId(int solutionId) {
                    this.solutionId = solutionId;
                }

                public String getResId() {
                    return resId;
                }

                public void setResId(String resId) {
                    this.resId = resId;
                }

                public String getExternalInfo() {
                    return externalInfo;
                }

                public void setExternalInfo(String externalInfo) {
                    this.externalInfo = externalInfo;
                }

                public List<?> getPolicyData() {
                    return policyData;
                }

                public void setPolicyData(List<?> policyData) {
                    this.policyData = policyData;
                }
            }
        }
    }
}
