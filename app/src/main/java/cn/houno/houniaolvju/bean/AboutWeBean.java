package cn.houno.houniaolvju.bean;

/**
 * 作 者：陈亮
 * <p/>
 * 版本1.0
 * <p/>
 * 描述：
 * <p/>
 * 修订历史:
 */
public class AboutWeBean {


    /**
     * status : 0
     * msg : 数据获取成功
     * data : {"cate_id":"4","cate_alias":"guanyuwomen","title":"候鸟旅居网","info":"<p style=\"text-align: center;\"><img src=\"http://web.houno.cn/ueditor/php/upload/image/20160826/1472181461879784.jpg\" title=\"1472181461879784.jpg\" alt=\"简介.jpg\"/><\/p><p><br/><\/p><p style=\"text-align: center;\"><span style=\"font-size: 20px;\"><strong>候鸟旅居网<\/strong><\/span><\/p><p><span style=\"font-size: 20px;\"><strong><br/><\/strong><\/span><\/p><p>&nbsp; &nbsp; &nbsp; &nbsp; 是集旅游、度假、养老于一体的综合性服务平台。于2016年7月1日正式上线。<\/p><p><br/><\/p><p>目前，电商平台正在汇聚全国近10000家设在旅游度假风景区内、交通便利、设施相对完备却又不乏人文景观特色的旅游度假酒店，以供国内外客户在线预订；同时，再优选出各有特色的108家作为线下\u201c印象太极\u201d体验基地，供旅居度假爱好者体验、分享。旅居度假、习练太极两不误，真正达到养眼、养身、养心的效果，送给人们多一个度假的理由。<\/p><p>&nbsp;<\/p><ul style=\"list-style-type: square;\" class=\" list-paddingleft-2\"><li><p><strong>选择我们<\/strong><\/p><\/li><\/ul><p>\u201c轻轻滑动手指，带您想去任何想去的地方度假\u201d。候鸟旅居网正通过互联网技术，向客户提供更精准、更增值的服务。<\/p><p><br/><\/p><ul style=\"list-style-type: square;\" class=\" list-paddingleft-2\"><li><p><strong>选择我们<\/strong><\/p><\/li><\/ul><p>\u201c线上候鸟旅居，线下印象太极\u201d。候鸟旅居网把旅居文化与传统文化结合在一起，提高消费者的旅居度假生活质量。<\/p><p><br/><\/p><ul style=\"list-style-type: square;\" class=\" list-paddingleft-2\"><li><p><strong>选择我们<\/strong><\/p><\/li><\/ul><p>就等于选择了别样的度假生活；选择了一种新的生活方式；选择了与众不同的人生体验。<\/p><p>&nbsp;<\/p><p>&nbsp;<\/p>","img":null,"seo_title":"","seo_keys":"","seo_desc":"","site_id":"1","row_number":"1"}
     */

    private int status;
    private String msg;
    /**
     * cate_id : 4
     * cate_alias : guanyuwomen
     * title : 候鸟旅居网
     * info : <p style="text-align: center;"><img src="http://web.houno.cn/ueditor/php/upload/image/20160826/1472181461879784.jpg" title="1472181461879784.jpg" alt="简介.jpg"/></p><p><br/></p><p style="text-align: center;"><span style="font-size: 20px;"><strong>候鸟旅居网</strong></span></p><p><span style="font-size: 20px;"><strong><br/></strong></span></p><p>&nbsp; &nbsp; &nbsp; &nbsp; 是集旅游、度假、养老于一体的综合性服务平台。于2016年7月1日正式上线。</p><p><br/></p><p>目前，电商平台正在汇聚全国近10000家设在旅游度假风景区内、交通便利、设施相对完备却又不乏人文景观特色的旅游度假酒店，以供国内外客户在线预订；同时，再优选出各有特色的108家作为线下“印象太极”体验基地，供旅居度假爱好者体验、分享。旅居度假、习练太极两不误，真正达到养眼、养身、养心的效果，送给人们多一个度假的理由。</p><p>&nbsp;</p><ul style="list-style-type: square;" class=" list-paddingleft-2"><li><p><strong>选择我们</strong></p></li></ul><p>“轻轻滑动手指，带您想去任何想去的地方度假”。候鸟旅居网正通过互联网技术，向客户提供更精准、更增值的服务。</p><p><br/></p><ul style="list-style-type: square;" class=" list-paddingleft-2"><li><p><strong>选择我们</strong></p></li></ul><p>“线上候鸟旅居，线下印象太极”。候鸟旅居网把旅居文化与传统文化结合在一起，提高消费者的旅居度假生活质量。</p><p><br/></p><ul style="list-style-type: square;" class=" list-paddingleft-2"><li><p><strong>选择我们</strong></p></li></ul><p>就等于选择了别样的度假生活；选择了一种新的生活方式；选择了与众不同的人生体验。</p><p>&nbsp;</p><p>&nbsp;</p>
     * img : null
     * seo_title :
     * seo_keys :
     * seo_desc :
     * site_id : 1
     * row_number : 1
     */

    private DataEntity data;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {
        private String cate_id;
        private String cate_alias;
        private String title;
        private String info;
        private Object img;
        private String seo_title;
        private String seo_keys;
        private String seo_desc;
        private String site_id;
        private String row_number;

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }

        public void setCate_alias(String cate_alias) {
            this.cate_alias = cate_alias;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public void setImg(Object img) {
            this.img = img;
        }

        public void setSeo_title(String seo_title) {
            this.seo_title = seo_title;
        }

        public void setSeo_keys(String seo_keys) {
            this.seo_keys = seo_keys;
        }

        public void setSeo_desc(String seo_desc) {
            this.seo_desc = seo_desc;
        }

        public void setSite_id(String site_id) {
            this.site_id = site_id;
        }

        public void setRow_number(String row_number) {
            this.row_number = row_number;
        }

        public String getCate_id() {
            return cate_id;
        }

        public String getCate_alias() {
            return cate_alias;
        }

        public String getTitle() {
            return title;
        }

        public String getInfo() {
            return info;
        }

        public Object getImg() {
            return img;
        }

        public String getSeo_title() {
            return seo_title;
        }

        public String getSeo_keys() {
            return seo_keys;
        }

        public String getSeo_desc() {
            return seo_desc;
        }

        public String getSite_id() {
            return site_id;
        }

        public String getRow_number() {
            return row_number;
        }
    }
}
