package cn.houno.houniaolvju.bean;

import java.util.ArrayList;

/**
 * 项目名称：Houniaolvju
 * 类描述：分权楼盘
 * 创建人：qzc
 * 创建时间：2016/12/21 16:39
 * 修改人：qzc
 * 修改时间：2016/12/21 16:39
 * 修改备注：
 */
public class SharePropertyBean {


    /**
     * status : 0
     * data : [{"id":"2","title":"曼特宁温泉公寓式酒店","img":"/Uploads/OffActi/20161107/1725475820489b4cc34.jpg","description":"曼特宁温泉公寓式酒店是海南候鸟旅居旅游地产股份有限公司实力打造的极具地域特色并时具备较高投资价值的品质之作。是兴隆具备大型商业配套的酒店式公寓，项目总占地19825.30平方，总建筑面积26027.12平方（商业楼8000多平方，公寓楼12000多平方，余下4000平方为地下室）。规划有商业和小户型公寓，并引进特产超市、特色餐厅、博物馆作为将来的大商业配套。让您足不出户坐享齐全的生活、娱乐及商业配套。","price":"69800.0000","region":"","address":"海南兴隆旅游区温泉大道南侧","project":"花园洋房,舒适软装,养生避暑","property_type":"公寓式酒店","kaipan_time":"2012年5月1日","row_number":"1"}]
     * msg : 数据请求成功
     */

    private int status;
    private String msg;
    /**
     * id : 2
     * title : 曼特宁温泉公寓式酒店
     * img : /Uploads/OffActi/20161107/1725475820489b4cc34.jpg
     * description : 曼特宁温泉公寓式酒店是海南候鸟旅居旅游地产股份有限公司实力打造的极具地域特色并时具备较高投资价值的品质之作。是兴隆具备大型商业配套的酒店式公寓，项目总占地19825.30平方，总建筑面积26027.12平方（商业楼8000多平方，公寓楼12000多平方，余下4000平方为地下室）。规划有商业和小户型公寓，并引进特产超市、特色餐厅、博物馆作为将来的大商业配套。让您足不出户坐享齐全的生活、娱乐及商业配套。
     * price : 69800.0000
     * region :
     * address : 海南兴隆旅游区温泉大道南侧
     * project : 花园洋房,舒适软装,养生避暑
     * property_type : 公寓式酒店
     * kaipan_time : 2012年5月1日
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
        private String title;
        private String img;
        private String description;
        private String price;
        private String region;
        private String address;
        private String project;
        private String property_type;
        private String kaipan_time;
        private String row_number;

        public String getId() {
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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getProject() {
            return project;
        }

        public void setProject(String project) {
            this.project = project;
        }

        public String getProperty_type() {
            return property_type;
        }

        public void setProperty_type(String property_type) {
            this.property_type = property_type;
        }

        public String getKaipan_time() {
            return kaipan_time;
        }

        public void setKaipan_time(String kaipan_time) {
            this.kaipan_time = kaipan_time;
        }

        public String getRow_number() {
            return row_number;
        }

        public void setRow_number(String row_number) {
            this.row_number = row_number;
        }
    }
}
