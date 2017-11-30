package cn.houno.houniaolvju.activity.train;

import java.io.Serializable;


public class TrainPassengersBean implements Serializable{

    private String id;
    private String passengersename;
    private String piaotype;
    private String piaotypename;
    private String passporttypeseid;
    private String passporttypeseidname;
    private String passportseno;
    private String row_number;


    private String passengerid;
    private String price;
    private String zwcode;
    private String zwname;
    private String ticket_no;
    private String cxin;
    private String reason;

    public String getPassengerid() {
        return passengerid;
    }

    public void setPassengerid(String passengerid) {
        this.passengerid = passengerid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getZwcode() {
        return zwcode;
    }

    public void setZwcode(String zwcode) {
        this.zwcode = zwcode;
    }

    public String getZwname() {
        return zwname;
    }

    public void setZwname(String zwname) {
        this.zwname = zwname;
    }

    public String getTicket_no() {
        return ticket_no;
    }

    public void setTicket_no(String ticket_no) {
        this.ticket_no = ticket_no;
    }

    public String getCxin() {
        return cxin;
    }

    public void setCxin(String cxin) {
        this.cxin = cxin;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


    public TrainPassengersBean(){}

    public TrainPassengersBean(String passengersename,String passportseno){
        this.passengersename=passengersename;
        this.passportseno=passportseno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassengersename() {
        return passengersename;
    }

    public void setPassengersename(String passengersename) {
        this.passengersename = passengersename;
    }

    public String getPiaotype() {
        return piaotype;
    }

    public void setPiaotype(String piaotype) {
        this.piaotype = piaotype;
    }

    public String getPiaotypename() {
        return piaotypename;
    }

    public void setPiaotypename(String piaotypename) {
        this.piaotypename = piaotypename;
    }

    public String getPassporttypeseid() {
        return passporttypeseid;
    }

    public void setPassporttypeseid(String passporttypeseid) {
        this.passporttypeseid = passporttypeseid;
    }

    public String getPassporttypeseidname() {
        return passporttypeseidname;
    }

    public void setPassporttypeseidname(String passporttypeseidname) {
        this.passporttypeseidname = passporttypeseidname;
    }

    public String getPassportseno() {
        return passportseno;
    }

    public void setPassportseno(String passportseno) {
        this.passportseno = passportseno;
    }

    public String getRow_number() {
        return row_number;
    }

    public void setRow_number(String row_number) {
        this.row_number = row_number;
    }

    @Override
    public String toString() {
        return "TrainPassengersBean{" +
                "id='" + id + '\'' +
                ", passengersename='" + passengersename + '\'' +
                ", piaotype='" + piaotype + '\'' +
                ", piaotypename='" + piaotypename + '\'' +
                ", passporttypeseid='" + passporttypeseid + '\'' +
                ", passporttypeseidname='" + passporttypeseidname + '\'' +
                ", passportseno='" + passportseno + '\'' +
                ", row_number='" + row_number + '\'' +
                '}';
    }
}
