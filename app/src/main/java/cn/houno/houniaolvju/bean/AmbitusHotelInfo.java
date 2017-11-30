package cn.houno.houniaolvju.bean;

import java.io.Serializable;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：周边酒店
 * 创建人：qzc
 * 创建时间：2016/10/10 13:01
 * 修改人：qzc
 * 修改时间：2016/10/10 13:01
 * 修改备注：
 */
public class AmbitusHotelInfo implements Serializable {

    private String id;   //酒店Id
    private String title;   //酒店
    private String imgUrl;  //图片url
    private String telphone;   //电话号码
    private String address;     //地址
    private double latitude;    //纬度
    private double longitude;   //经度
    private String rid;   //房间id

    public AmbitusHotelInfo(String id,String rid,String title, String imgUrl, String telphone
            , String address, double latitude, double longitude) {
        this.id = id;
        this.title = title;
        this.imgUrl = imgUrl;
        this.telphone = telphone;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rid = rid;
    }

    public String getId(){
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getRid() {
        return rid;
    }
}
