package cn.houno.houniaolvju.bean;

import java.util.ArrayList;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：银行卡类型
 * 创建人：qzc
 * 创建时间：2016/12/9 11:46
 * 修改人：qzc
 * 修改时间：2016/12/9 11:46
 * 修改备注：
 */
public class BankTypeBean {


    /**
     * status : 0
     * data : [{"id":"44401","name":"中国银行","row_number":"1"},{"id":"44402","name":"中国工商银行","row_number":"2"},{"id":"44403","name":"中国建设银行","row_number":"3"},{"id":"44404","name":"中国农业银行","row_number":"4"},{"id":"44405","name":"中国交通银行","row_number":"5"},{"id":"44406","name":"招商银行","row_number":"6"},{"id":"44407","name":"光大银行","row_number":"7"},{"id":"44408","name":"兴业银行","row_number":"8"},{"id":"44409","name":"华夏银行","row_number":"9"},{"id":"44410","name":"平安银行","row_number":"10"},{"id":"44411","name":"中国邮政储蓄银行","row_number":"11"}]
     * msg : 数据获取成功
     */

    private int status;
    private String msg;
    /**
     * id : 44401
     * name : 中国银行
     * row_number : 1
     */

    private ArrayList<DataBean> data;

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

    public ArrayList<DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String name;
        private String row_number;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRow_number() {
            return row_number;
        }

        public void setRow_number(String row_number) {
            this.row_number = row_number;
        }
    }
}
