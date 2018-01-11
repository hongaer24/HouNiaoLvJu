package cn.houno.houniaolvju.global;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：接口地址
 * 创建人：qzc
 * 创建时间：2016/9/29 11:42
 * 修改人：qzc
 * 修改时间：2016/9/29 11:42
 * 修改备注：
 */
public class Constants {
    public static final String PUBLIC_USER_ID="417";
    //根据定位获取城市ID
    public static final String HOME_GET_CITY_ID = "http://apis.houno.cn/getCityId";
    //首页轮播图数据地址
    public static final String HOME_TOP_PICTURE = "http://apis.houno.cn/getAds";
    //首页拼团酒店、活动酒店、活动景点数据
    public static final String HOME_INDEX = "http://apis.houno.cn/getIndex.php";
    //首页分权楼盘列表数据
    public static final String HOME_FENQUAN = "http://fqapi.houno.cn/fqlist.php";
    //实时搜索
    public static final String GET_INDEX_SEARCH = "http://apis.houno.cn/getIndexSearch.php";
    //首页推荐景点
    public static final String HOME_RCMD_SCENIC = "http://apis.houno.cn/getRcmdScenic";
    //酒店搜索轮播图
    public static final String SEARCH_TOP_PIC = "http://apis.houno.cn/getHotelAds";
    //周边酒店
    public static final String AMBITUS_HOTEL = "http://webapi.houno.cn/ambitushotel.php";
    //国内酒店列表
    public static final String HOTEL_LIST = "http://apis.houno.cn/getHotelList";
    //国内酒店详情
    public static final String HOTEL_DETAIL = "http://apis.houno.cn/getHotelDetail";
    //国际酒店列表
    public static final String FOREIGN_HOTEL_LIST = "http://webapi.houno.cn/foreignList.php";
    //国外酒店详情
    public static final String FOREIGN_HOTEL_DETAIL = "http://webapi.houno.cn/foreignDetail.php";
    //房间收藏
    public static final String ADD_FAVOR = "http://webapi.houno.cn/addfavor.php";
    //酒店评论列表
    public static final String HOTEL_COMMENT_LIST = "http://apis.houno.cn/getHotelComment.php";
    //酒店下单
    public static final String HOTEL_ORDER_URL = "http://apis.houno.cn/addorder";
    //团购订单详情 userid 用户ID orderno 订单号
    public static final String ORDER_TG = "http://webapi.houno.cn/sure/";
    //活动多间成团列表
    public static final String ACTI_HOTEL_TG_LIST = "http://apis.houno.cn/actihoteltglist";
    //活动景点列表
    //public static final String ACTI_SCENIC_LIST = "http://apis.houno.cn/actisceniclist";
    public static final String ACTI_SCENIC_LIST = "http://apis.houno.cn/getActtourssceniclist.php";
    //活动特价酒店列表
    public static final String ACTI_SPC_HOTEL_LIST = "http://apis.houno.cn/actihotellist";
    //限时抢购列表(post p页数)
    public static final String FLASH_SALE_LIST = "http://apis.houno.cn/flashsaleList.php";
    //限时抢购详情(post  id)
    public static final String FLASH_SALE_DETAIL = "http://apis.houno.cn/flashsaleShows";
    //限时抢购下单
    public static final String FLASH_SALE_ADD_ORDER = "http://apis.houno.cn/addActiOrder.php";
    //分权度假详情
    public static final String FENQUAN_DETAIL = "http://fqapi.houno.cn/fqshows.php";
    //国内城市列表
    public static final String HOME_CITY_LIST = "http://apis.houno.cn/getCity";
    //国外城市列表
    public static final String FOREIGN_CITY_LIST = "http://webapi.houno.cn/getForeignCity.php";
    //景点轮播图
    public static final String SCENIC_TOP_PIC = "http://apis.houno.cn/getScenicAds";
    //景点分类
    public static final String SCENIC_INDEX_CATE = "http://apis.houno.cn/getScenicCate";
    //人气景点和猜你喜欢
   // public static final String SCENIC_INDEX = "http://apis.houno.cn/getIndexScenic";
    public static final String SCENIC_INDEX = "http://apis.houno.cn/getljbIndexToursScenic";
    //景点列表
    //public static final String SCENIC_LIST = "http://apis.houno.cn/getScenicList.php";
    public static final String SCENIC_LIST = "http://apis.houno.cn/getToursScenicList";
    //景点详情
    //public static final String SCENIC_DETAIL = "http://apis.houno.cn/getScenicDetail.php";
    public static final String SCENIC_DETAIL = "http://apis.houno.cn/getToursScenicDetail";
    //景点评论列表
    public static final String SCENIC_COMMENT_LIST = "http://apis.houno.cn/getScenicComment.php";
    //景点订单
   // public static final String SCENIC_ORDER_URL = "http://apis.houno.cn/addScenicOrder";
     public static final String SCENIC_ORDER_URL = "http://apis.houno.cn/addToursScenicOrder";
   //景点获取可否退款
   public static final String SCENIC_REFUND_URL = "http://apis.houno.cn/hounosceniccanRefund";



    //获取游客信息
    public static final String GET_TOURIST_URL = "http://apis.houno.cn/GetScenicPassenger";
    //添加游客信息
    public static final String ADD_TOURIST_URL = "http://apis.houno.cn/AddScenicPassenger";
    //编辑游客信息
    public static final String EDIT_TOURIST_URL = "http://apis.houno.cn/EditScenicPassenger";

    //异地养老首页轮播图
    public static final String GET_YD_INDEX_TOP = "http://apis.houno.cn/getYdAds.php";
    //异地养老首页数据
    public static final String GET_YD_INDEX = "http://apis.houno.cn/getYdIndex";
    //异地养老列表
    public static final String GET_YD_LIST = "http://apis.houno.cn/getYdHotelList.php";
    //异养详情 id 机构ID rid 房型ID
    public static final String GET_YD_DETAIL = "http://apis.houno.cn/getYdHotelDetail";
    //异养下单
    public static final String YYHOTEL_ORDER = "http://apis.houno.cn/addYdOrder.php";
    //支付宝支付
    public static final String A_LI_PAY = "http://apis.houno.cn/alipay.php";
    //微信支付
    public static final String WX_PAY = "http://apis.houno.cn/wxpay.php";
    //银联支付
    public static final String UNION_PAY = "http://apis.houno.cn/unionpay.php";
    //申请退款
   // public static final String APPLY_REFUND = "http://apis.houno.cn/refundOrder.php";
     public static final String APPLY_REFUND = "http://apis.houno.cn/hounoScenicOrderRefund";
    //支付成功回调接口
    public static final String GET_PAY_STATUS = "http://apis.houno.cn/getpaystatus.php";
    //登录
    public static final String LOGIN_URL = "http://webapi.houno.cn/userLogin.php";
    //关于我们
    public static final String ABOUTUS_URL = "http://apis.houno.cn/getabout.php";
    //我的收藏
    public static final String COLLECTION_URL = "http://webapi.houno.cn/favorlist.php";
    //注册成功
    //public static final String REGISTER_URL = "http://webapi.houno.cn/reg.php";
    public static final String REGISTER_URL = "http://apis.houno.cn/reg.php";
    //注册获取验证码
    //public static final String GETCODE_URL = "http://webapi.houno.cn/regcode.php";
    public static final String GETCODE_URL = "http://apis.houno.cn/regcode.php";
    //找回密码
    public static final String BACKPSW_URL = "http://webapi.houno.cn/editpwd.php";
    //找回密码验证码
    public static final String BACKPSWGETYZM_URL = "http://webapi.houno.cn/getcode.php";
    //修改密码
    public static final String SETPASSWORD_URL = "http://webapi.houno.cn/changepwd.php";
    //个人中心主页
    public static final String PERSONAL_URL = "http://apis.houno.cn/my.php";
    //修改个人资料
    public static final String SETPERSONAL_URL = "http://apis.houno.cn/editinfo.php";
    //获取个人信息
    public static final String GETPERSONALDATA_URL = "http://webapi.houno.cn/getuserinfo.php";
    //取消收藏
    public static final String CANCELSC_URL = "http://webapi.houno.cn/cancelfav.php";
    //版本地址（安装包）
    public static final String VERSION_URL = "http://webapi.houno.cn/hounoandroid";
    //我的订单
    public static final String MYORDER_URL = "http://apis.houno.cn/myneworder.php";
    //订单详细
   // public static final String ORDER_DETAIL_URL = "http://apis.houno.cn/orderdetail.php";
    //public static final String ORDER_DETAIL_URL = "http://apis.houno.cn/orderdetail";
    public static final String ORDER_DETAIL_URL = "http://apis.houno.cn/orderdetail";
    //取消订单
    public static final String CANCEL_ORDER_URL = "http://apis.houno.cn/cancelorder.php";

   //景点取消订单
    public static final String CANCEL_SCENICORDER_URL = "http://apis.houno.cn/hounoscenicOrderCancel ";

    //获取城市id接口
    public static final String GET_CITY_ID = "http://apis.houno.cn/getCityId.php";
    //前台确认订单
    public static final String RECEPTION_URL = "http://webapi.houno.cn/spotpay";
    //发布评论
    public static final String RELEASE_COMMENT_URL = "http://apis.houno.cn/ordercomment.php";
    //第三方登录接口
    public static final String THIRD_PAR_LOGIN = "http://apis.houno.cn/thirdParLogin.php";


    //====================================机票===============================

    //机票城市列表
    public static final String FLIGHT_CITY = "http://apis.houno.cn/getFlightCity";
    //特价机票列表
    public static final String SPECIAL_FLIGHT = "http://apis.houno.cn/v2/getSalePrice";
    //机票列表(dep_city=HAK&arr_city=CSX&dep_date=2017-02-18)
    public static final String FLIGHT_LIST = "http://apis.houno.cn/v2/getFlightList";
    //获取乘机人列表(userid 用户ID)
    public static final String PASSENGER_LIST = "http://apis.houno.cn/getPassenger";
    //添加乘机人(userid  用户ID info[name] 姓名 info[identityType] 证件类型 info[identityNo] 证件号码)
    public static final String ADD_PASSENGER = "http://apis.houno.cn/addPassenger";
    //修改乘机人信息
    public static final String EDIT_PASSENGER = "http://apis.houno.cn/editPassenger";
    //机票提交订单
    public static final String FLIGHT_SUBMIT_ORDER = "http://apis.houno.cn/v2/addFlightOrder.php";
    //退改签费用说明接口
    public static final String GET_MODIFY_REFUND = "http://apis.houno.cn/v2/getModifyAndRefund";
    //机票取消订单
    //public static final String FLIGHT_CANCEL_ORDER = "http://apis.houno.cn/v2/ljbFlightOrderCancel";
    public static final String FLIGHT_CANCEL_ORDER = "http://apis.houno.cn/flightOrderCancel.php";

    //退票接口   orderno
    public static final String FLIGHT_REFUND = "http://apis.houno.cn/flightRefund";
    //机票改签
    public static final String FLIGHT_CHANGE = "http://apis.houno.cn/flightChange";
    //改签支付之后状态获取接口
    public static final String GET_CHANGE_PAY_STATUS = "http://apis.houno.cn/getChangeStatus ";

    //====================================火车票===================================

    //火车票搜索城市
    public static final String TRAIN_CITY_SELECT="http://apis.houno.cn/getTrainCity";

    //火车票列表
    public static final String TRAIN_LIST="http://apis.houno.cn/getTrainList";

    //火车票提交订单
    public static final String TRAIN_ORDER="http://apis.houno.cn/addTrainOrder";

    //火车票获取乘车人
    public static final String TRAIN_PASSENGERS_SELECT="http://apis.houno.cn/getTrainPassenger";

    //火车票添加乘车人
    public static final String TRAIN_PASSENGERS_ADD="http://apis.houno.cn/addTrainPassenger";

    //火车票修改乘车人
    public static final String TRAIN_PASSENGERS_EDIT ="http://apis.houno.cn/editTrainPassenger";

    //火车票订单取消
    public static final String TRAIN_ORDER_CANCEL="http://apis.houno.cn/trainOrderCancel";

    //火车票申请退票
    public static final String TRAIN_RETURN_TICKET="http://apis.houno.cn/trainOrderRefund";

    /**
     * 火车票刷新订单详情
     * */
    public static final String TRAIN_ORDER_DETAIL="http://apis.houno.cn/orderdetail.php";

    //====================旅居卡接口====================-===========

    /**
    * 旅居卡绑定获取验证码接口
    * cardno 卡号
    * mobile 手机号
    * */
    public static final String GET_CARD_CODE = "http://webapi.houno.cn/getcardcode";
    /**
    * 绑定旅居卡接口
    * userid 会员id
    * cardno 会员卡号
    * username 会员姓名
    * mobile 手机号
    * code 验证码
    * */
    public static final String BIND_MEMBER_CARD = "http://webapi.houno.cn/bindmembercard";

    /**
    * 旅居卡信息接口
    * userid 会员id
    * */
    public static final String MEMBER_CARD_INFO = "http://webapi.houno.cn/membercardinfo";
    /**
    * 充值记录接口
    * userid 会员id
    * */
    public static final String CARD_RECHARGE_RECORD = "http://webapi.houno.cn/rechargerecord";


    /**
    * 消费记录接口
    * userid 会员id
    * */
    public static final String CARD_CONSUME_RECORD = "http://webapi.houno.cn/consumerecord";


    /**
     * 支付宝充值
     * userid 会员id
     * type 类型 (旅居卡 card 钱包 acct)
     * money 金额
     * */
    public static final String ALI_RECHARGE = "http://webapi.houno.cn/alirchg";
    /**
     * 微信充值
     * userid 会员id
     * type 类型 (旅居卡 card 钱包 acct)
     * money 金额
     * */
    public static final String WX_RECHARGE = "http://webapi.houno.cn/wxrchg";

    /**
     * 银联充值
     * userid 会员id
     * type 类型 (旅居卡 card 钱包 acct)
     * money 金额
     * */
    public static final String UNION_RECHARGE = "http://webapi.houno.cn/unionrchg";

    /**
     * 充值完成获取充值状态
     * orderno 订单号
     * type 类型 (旅居卡 card 钱包 acct)
     * */
    public static final String GET_RCHG_STATUS = "http://webapi.houno.cn/getrchgstatus";

    /**
    * 修改旅居卡消费密码
    * userid 用户id
    * oldpwd 旧密码
    * password 新密码
    * */
    public static final String MODIFY_CARD_PWD = "http://webapi.houno.cn/editcardpass.php";

    //==================================我的钱包=========================================

    /**
    * 钱包首页
    * userid 用户id
    * */
    public static final String WALLET_INDEX = "http://webapi.houno.cn/burseindex";
    /**
    * 充值记录
    * userid 用户id
    * */
    public static final String WALLET_RECHARGE_RECORD = "http://webapi.houno.cn/mbrechg";
    /**
    * 返现记录
    * userid 用户id
    * */
    public static final String WALLET_BACK_RECORD = "http://webapi.houno.cn/backrecord";
    /**
     * 提现-获取银行卡类型
     * */
    public static final String GET_BANK_TYPE = "http://webapi.houno.cn/getbanktype";

    /**
     *提现接口
     *userid 用户ID
     *info[bank_type] 银行种类ID
     *info[card_account] 卡号
     *info[username] 姓名
     *info[phone]  手机
     *info[withdrawals] 金额
     *info[remark] 备注
     * */
    public static final String WALLET_WITH_DRAW = "http://webapi.houno.cn/withdraw";

    /**
     * 提现记录
     * userid 用户id
     * */
    public static final String WALLET_DRAW_RECORD = "http://webapi.houno.cn/drawals";

    //=========================优惠券=====================

    /**
    * 优惠劵领取页面接口
    * id 优惠劵ID
    * */
    public static final String COUPON = "http://apis.houno.cn/coupon";

    /**
    * 优惠劵领取页面接口
    * userid 用户ID
    * id 优惠劵ID
    * */
    public static final String ADD_COUPON = "http://apis.houno.cn/addcoupon";
    /**
    *优惠劵列表
    *userid 用户ID
    * status 状态 1、未使用 2、已使用 0、已失效
    * */
    public static final String COUPON_LIST = "http://apis.houno.cn/couponlist";


}
