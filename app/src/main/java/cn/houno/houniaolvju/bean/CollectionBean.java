package cn.houno.houniaolvju.bean;

import java.util.ArrayList;

/**
 * 作 者：陈亮
 * <p/>
 * 版本1.0
 * <p/>
 * 描述：
 * <p/>
 * 修订历史:
 */
public class CollectionBean {


    /**
     * status : 0
     * data : [{"id":"213","aid":"15512","model":"room","uid":"448","type":"hotel","row_number":"1","info":{"id":"15512","title":"三亚阳光大酒店","address":"中国 海南省三亚市三亚湾路196号","city":"448","img":"http://web.houno.cn//Uploads/Hotel/20160629/140527577365272d731.jpg","telphone":"0898- 8859 9999","row_number":"1","webprice":"508","rid":"30534"}}]
     * count : 1
     * msg : 数据获取成功
     */

    private int status;
    private String count;
    private String msg;
    /**
     * id : 213
     * aid : 15512
     * model : room
     * uid : 448
     * type : hotel
     * row_number : 1
     * info : {"id":"15512","title":"三亚阳光大酒店","address":"中国 海南省三亚市三亚湾路196号","city":"448","img":"http://web.houno.cn//Uploads/Hotel/20160629/140527577365272d731.jpg","telphone":"0898- 8859 9999","row_number":"1","webprice":"508","rid":"30534"}
     */

    private ArrayList<DataEntity> data;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(ArrayList<DataEntity> data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public String getCount() {
        return count;
    }

    public String getMsg() {
        return msg;
    }

    public ArrayList<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String id;
        private String aid;
        private String model;
        private String uid;
        private String type;
        private String row_number;
        /**
         * id : 15512
         * title : 三亚阳光大酒店
         * address : 中国 海南省三亚市三亚湾路196号
         * city : 448
         * img : http://web.houno.cn//Uploads/Hotel/20160629/140527577365272d731.jpg
         * telphone : 0898- 8859 9999
         * row_number : 1
         * webprice : 508
         * rid : 30534
         */

        private InfoEntity info;

        public void setId(String id) {
            this.id = id;
        }

        public void setAid(String aid) {
            this.aid = aid;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setRow_number(String row_number) {
            this.row_number = row_number;
        }

        public void setInfo(InfoEntity info) {
            this.info = info;
        }

        public String getId() {
            return id;
        }

        public String getAid() {
            return aid;
        }

        public String getModel() {
            return model;
        }

        public String getUid() {
            return uid;
        }

        public String getType() {
            return type;
        }

        public String getRow_number() {
            return row_number;
        }

        public InfoEntity getInfo() {
            return info;
        }

        public static class InfoEntity {
            private String id;
            private String title;
            private String address;
            private String city;
            private String img;
            private String telphone;
            private String row_number;
            private String webprice;
            private String rid;

            public void setId(String id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public void setTelphone(String telphone) {
                this.telphone = telphone;
            }

            public void setRow_number(String row_number) {
                this.row_number = row_number;
            }

            public void setWebprice(String webprice) {
                this.webprice = webprice;
            }

            public void setRid(String rid) {
                this.rid = rid;
            }

            public String getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }

            public String getAddress() {
                return address;
            }

            public String getCity() {
                return city;
            }

            public String getImg() {
                return img;
            }

            public String getTelphone() {
                return telphone;
            }

            public String getRow_number() {
                return row_number;
            }

            public String getWebprice() {
                return webprice;
            }

            public String getRid() {
                return rid;
            }
        }
    }
}
