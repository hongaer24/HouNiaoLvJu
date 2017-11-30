package cn.houno.houniaolvju.bean;

import java.util.List;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：
 * 创建人：qzc
 * 创建时间：2017/1/3 10:28
 * 修改人：qzc
 * 修改时间：2017/1/3 10:28
 * 修改备注：
 */
public class ScenicCateBean {

    /**
     * status : 0
     * data : [{"id":"1","name":"自然风景","alias":"ziranfengjing","row_number":"1"},{"id":"2","name":"人文古迹","alias":"renwenguji","row_number":"2"},{"id":"3","name":"主题乐园","alias":"zhutileyuan","row_number":"3"},{"id":"4","name":"水上世界","alias":"shuishangshijie","row_number":"4"},{"id":"5","name":"温泉水疗","alias":"wenquanshuiliao","row_number":"5"},{"id":"6","name":"漂流探险","alias":"piaoliutanxian","row_number":"6"},{"id":"7","name":"岛屿潜水","alias":"daoyuqianshui","row_number":"7"},{"id":"8","name":"动植物园","alias":"dongzhiwuyuan","row_number":"8"},{"id":"9","name":"田园度假","alias":"TYDJ","row_number":"9"},{"id":"10","name":"民俗文化","alias":"minsuwenhua","row_number":"10"},{"id":"11","name":"游乐场","alias":"youlechang","row_number":"11"},{"id":"12","name":"博物馆","alias":"bowuguan","row_number":"12"},{"id":"13","name":"园林","alias":"yuanlin","row_number":"13"},{"id":"14","name":"都市观光","alias":"dushiguanguang","row_number":"14"},{"id":"15","name":"体验基地","alias":"tiyanjidi","row_number":"15"},{"id":"16","name":"都市风光","alias":"dushifengguang","row_number":"16"},{"id":"17","name":"民俗文化","alias":"minsuwenhua","row_number":"17"},{"id":"18","name":"演艺，场馆","alias":"yanyichangguan","row_number":"18"}]
     * msg : 数据获取成功
     */

    private int status;
    private String msg;
    /**
     * id : 1
     * name : 自然风景
     * alias : ziranfengjing
     * row_number : 1
     */

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
        private String id;
        private String name;
        private String alias;
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

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getRow_number() {
            return row_number;
        }

        public void setRow_number(String row_number) {
            this.row_number = row_number;
        }
    }
}
