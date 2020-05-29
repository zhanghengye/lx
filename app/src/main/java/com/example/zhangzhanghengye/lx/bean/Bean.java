package com.example.zhangzhanghengye.lx.bean;

import java.util.List;

/******************************************
 * 类名称：Bean
 * 类描述：
 ******************************************/
public class Bean {

    /**
     * code : 0
     * msg : success
     * data : [{"comment_id":1,"goods_id":171,"email":"","content":"测试商品非常好","img":["/resource/upload/image/2019/07/11/33009e1a0bdeee6a8cd682783b5bc8ae13c1a75a_1111_834.jpg","/resource/upload/image/2019/07/11/1e4faee7ca1cd15bcaa0ddd2f495ea3add761845_1080_1619.jpg"],"order_id":571,"deliver_rank":4,"goods_rank":4,"service_rank":4,"zan_num":0,"is_anonymous":"1","user_name":"17301390350","user_header":"0","user_profession":null,"order_goods_type":["500g","绿色"],"order_total_amount":"0.01","order_goods_number":1,"goods_name":"洛川富士苹果","goods_subheading":"味美甘甜","goods_price":"100.00","goods_album":"/resource/upload/image/2019/07/11/e72e9b6b63502abc0340c425f4ec532c_500_500.jpg","comment_count":0,"add_time":1571716626},{"comment_id":2,"goods_id":167,"email":"","content":"一般","img":[],"order_id":571,"deliver_rank":4,"goods_rank":4,"service_rank":4,"zan_num":0,"is_anonymous":"1","user_name":"17301390350","user_header":"0","user_profession":null,"order_goods_type":["1000g","绿色"],"order_total_amount":"12.00","order_goods_number":1,"goods_name":"123321","goods_subheading":"12","goods_price":"12.00","goods_album":"/resource/upload/image/2019/07/11/e6e1620babd060aeed7fa57d92291a08_250_172.jpg","comment_count":0,"add_time":1571716726}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
        /**
         * comment_id : 1
         * goods_id : 171
         * email :
         * content : 测试商品非常好
         * img : ["/resource/upload/image/2019/07/11/33009e1a0bdeee6a8cd682783b5bc8ae13c1a75a_1111_834.jpg","/resource/upload/image/2019/07/11/1e4faee7ca1cd15bcaa0ddd2f495ea3add761845_1080_1619.jpg"]
         * order_id : 571
         * deliver_rank : 4
         * goods_rank : 4
         * service_rank : 4
         * zan_num : 0
         * is_anonymous : 1
         * user_name : 17301390350
         * user_header : 0
         * user_profession : null
         * order_goods_type : ["500g","绿色"]
         * order_total_amount : 0.01
         * order_goods_number : 1
         * goods_name : 洛川富士苹果
         * goods_subheading : 味美甘甜
         * goods_price : 100.00
         * goods_album : /resource/upload/image/2019/07/11/e72e9b6b63502abc0340c425f4ec532c_500_500.jpg
         * comment_count : 0
         * add_time : 1571716626
         */

        private int comment_id;
        private int goods_id;
        private String email;
        private String content;
        private int order_id;
        private int deliver_rank;
        private int goods_rank;
        private int service_rank;
        private int zan_num;
        private String is_anonymous;
        private String user_name;
        private String user_header;
        private Object user_profession;
        private String order_total_amount;
        private int order_goods_number;
        private String goods_name;
        private String goods_subheading;
        private String goods_price;
        private String goods_album;
        private int comment_count;
        private int add_time;
        private List<String> img;
        private List<String> order_goods_type;

        public int getComment_id() {
            return comment_id;
        }

        public void setComment_id(int comment_id) {
            this.comment_id = comment_id;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public int getDeliver_rank() {
            return deliver_rank;
        }

        public void setDeliver_rank(int deliver_rank) {
            this.deliver_rank = deliver_rank;
        }

        public int getGoods_rank() {
            return goods_rank;
        }

        public void setGoods_rank(int goods_rank) {
            this.goods_rank = goods_rank;
        }

        public int getService_rank() {
            return service_rank;
        }

        public void setService_rank(int service_rank) {
            this.service_rank = service_rank;
        }

        public int getZan_num() {
            return zan_num;
        }

        public void setZan_num(int zan_num) {
            this.zan_num = zan_num;
        }

        public String getIs_anonymous() {
            return is_anonymous;
        }

        public void setIs_anonymous(String is_anonymous) {
            this.is_anonymous = is_anonymous;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_header() {
            return user_header;
        }

        public void setUser_header(String user_header) {
            this.user_header = user_header;
        }

        public Object getUser_profession() {
            return user_profession;
        }

        public void setUser_profession(Object user_profession) {
            this.user_profession = user_profession;
        }

        public String getOrder_total_amount() {
            return order_total_amount;
        }

        public void setOrder_total_amount(String order_total_amount) {
            this.order_total_amount = order_total_amount;
        }

        public int getOrder_goods_number() {
            return order_goods_number;
        }

        public void setOrder_goods_number(int order_goods_number) {
            this.order_goods_number = order_goods_number;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_subheading() {
            return goods_subheading;
        }

        public void setGoods_subheading(String goods_subheading) {
            this.goods_subheading = goods_subheading;
        }

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }

        public String getGoods_album() {
            return goods_album;
        }

        public void setGoods_album(String goods_album) {
            this.goods_album = goods_album;
        }

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public int getAdd_time() {
            return add_time;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
        }

        public List<String> getImg() {
            return img;
        }

        public void setImg(List<String> img) {
            this.img = img;
        }

        public List<String> getOrder_goods_type() {
            return order_goods_type;
        }

        public void setOrder_goods_type(List<String> order_goods_type) {
            this.order_goods_type = order_goods_type;
        }
    }
}
