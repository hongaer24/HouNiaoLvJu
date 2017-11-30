package cn.houno.houniaolvju.activity.train;

import java.io.Serializable;



public class TrainCityBean implements Serializable {

    private int id;
    private String name;
    private String code;
    private String alias;
    private String cityen;
    private String jianpin;
    private boolean ishot;
    private String status;
    private int row_number;

    public TrainCityBean(){}

    public TrainCityBean(String name,String code){
        this.name=name;
        this.code=code;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCityen() {
        return cityen;
    }

    public void setCityen(String cityen) {
        this.cityen = cityen;
    }

    public String getJianpin() {
        return jianpin;
    }

    public void setJianpin(String jianpin) {
        this.jianpin = jianpin;
    }

    public boolean ishot() {
        return ishot;
    }

    public void setIshot(boolean ishot) {
        this.ishot = ishot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRow_number() {
        return row_number;
    }

    public void setRow_number(int row_number) {
        this.row_number = row_number;
    }

    @Override
    public String toString() {
        return "[城市名称:"+name+",城市三字码:"+code+"]";
    }
}
