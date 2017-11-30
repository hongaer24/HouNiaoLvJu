package cn.houno.houniaolvju.bean;


import java.io.Serializable;

/**
 * author zaaach on 2016/1/26.
 */

public class FlightCity implements Serializable {

    /**
     * id : 140
     * areaname : 昭通
     * sajm : ZAT
     * country : 中国
     * cjm : CN
     * sijm : ZPZT
     * airport : 昭通机场
     * enname : ZHAOTONG
     * areaen : zhaotong
     * simpleen : zt
     * jianpin : Z
     * ishot :
     * status : 1
     * remark :
     * row_number : 161
     */

    public FlightCity() {

    }

    public FlightCity(String id, String areaname, String sajm,
                      String country,
                      String cjm,
                      String sijm,
                      String airport,
                      String enname,
                      String areaen,
                      String simpleen,
                      String jianpin,
                      String ishot,
                      String status,
                      String remark,
                      String row_number) {
        this.id = id;
        this.areaname = areaname;
        this.sajm = sajm;
        this.country = country;
        this.cjm = cjm;
        this.sijm = sijm;
        this.airport = airport;
        this.enname = enname;
        this.areaen = areaen;
        this.simpleen = simpleen;
        this.jianpin = jianpin;
        this.ishot = ishot;
        this.status = status;
        this.remark = remark;
        this.row_number = row_number;
    }

    private String id;

    private String areaname;

    private String sajm;

    private String country;

    private String cjm;

    private String sijm;

    private String airport;

    private String enname;

    private String areaen;

    private String simpleen;

    private String jianpin;

    private String ishot;

    private String status;

    private String remark;

    private String row_number;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public String getSajm() {
        return sajm;
    }

    public void setSajm(String sajm) {
        this.sajm = sajm;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCjm() {
        return cjm;
    }

    public void setCjm(String cjm) {
        this.cjm = cjm;
    }

    public String getSijm() {
        return sijm;
    }

    public void setSijm(String sijm) {
        this.sijm = sijm;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getEnname() {
        return enname;
    }

    public void setEnname(String enname) {
        this.enname = enname;
    }

    public String getAreaen() {
        return areaen;
    }

    public void setAreaen(String areaen) {
        this.areaen = areaen;
    }

    public String getSimpleen() {
        return simpleen;
    }

    public void setSimpleen(String simpleen) {
        this.simpleen = simpleen;
    }

    public String getJianpin() {
        return jianpin;
    }

    public void setJianpin(String jianpin) {
        this.jianpin = jianpin;
    }

    public String getIshot() {
        return ishot;
    }

    public void setIshot(String ishot) {
        this.ishot = ishot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRow_number() {
        return row_number;
    }

    public void setRow_number(String row_number) {
        this.row_number = row_number;
    }


    @Override
    public String toString() {
        return "[\nid:" + id + ",areaname:" + areaname
                + "\nareaname:" + areaname
                + "\nsajm:" + sajm
                + "\ncountry:" + country
                + "\ncjm:" + cjm
                + "\nsijm:" + sijm
                + "\nairport:" + airport
                + "\nenname:" + enname
                + "\nareaen:" + areaen
                + "\nsimpleen:" + simpleen
                + "\njianpin:" + jianpin
                + "\nishot:" + ishot
                + "\nstatus:" + status
                + "\nremark:" + remark
                + "\nrow_number:" + row_number + "\n]";
    }
}
