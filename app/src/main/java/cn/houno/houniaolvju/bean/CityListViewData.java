package cn.houno.houniaolvju.bean;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：城市列表首字母
 * 创建人：qzc
 * 创建时间：2016/10/6 0:16
 * 修改人：qzc
 * 修改时间：2016/10/6 0:16
 * 修改备注：
 */

import java.util.ArrayList;

public class CityListViewData {
    private String name;
    private ArrayList<CityGridViewData> gridData;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<CityGridViewData> getGridData() {
        return gridData;
    }

    public void setGridData(ArrayList<CityGridViewData> gridData) {
        this.gridData = gridData;
    }

}

