package com.joymates.common;

import android.os.Environment;

import java.io.File;


public interface Constants {

    String PACKET_NAME = "cn.joymates.golf";
    String SD_CARD_FOLDER_PATH = Environment.getExternalStorageDirectory()
            + File.separator + PACKET_NAME;// 在sd卡上建立的文件夹
    String IMAGE_CACHE_FOLDER = "/img_cache";// 图片缓存文
    String IMAGE_RES_FOLDER = "/img_res";// 图片资源文件
    String VIDEO_RES_DOWNLOAD = "/video";// 视频下载文件
    String INSTRUCTIONS_DOWNLOAD = "/book";// 说明书下载文件

    String VIDEO_COMPRESS_FILE = SD_CARD_FOLDER_PATH + "/compress";//压缩视频放置的位置

    String UPLOAD_PATH = SD_CARD_FOLDER_PATH + "/upload";


    /***************************************团乐*************************************************/

    int PAYMENT_ALIPAY = 1;//支付宝
    int PAYMENT_WECHAT = 2;//微信
    int PAYMENT_CARD = 3;//银行卡

    //地区选择  0.省 1.市 2.区
    int GRADE_PROVINCE = 0;
    int GRADE_CITY = 1;
    int GRADE_COUNTY = 2;
    int IS_DEFAULT = 1; // 是否默认（0 默认 1 非默认）

    String PARTITION_STRING_TYPE_CASH = "CASH";//现金
    String PARTITION_STRING_TYPE_CASH_STORE = "CASH+STORE";//现金+积分
    String PARTITION_STRING_TYPE_STORE = "STORE";//积分
    String PARTITION_STRING_TYPE_ALL = "ALL";//全部

    String PARTITION_NUM_TYPE_All = "0";//全部
    String PARTITION_NUM_TYPE_CASH = "1";//现金
    String PARTITION_NUM_TYPE_CASH_STORE = "2";//现金+积分
    String PARTITION_NUM_TYPE_STORE = "3";//积分


    /**
     * 首页各个状态
     */
    //首页
    String HMME_TYPE_URL_HOME = "1001";
    //现金购物区
    String HMME_TYPE_URL_CASH = "2001";
    //现金+购物券
    String HMME_TYPE_URL_CASH_VOUCHER = "2002";
    //购物券兑换区
    String HMME_TYPE_URL_VOUCHER = "2003";
    //异业联盟
    String HMME_TYPE_URL_IPC = "3001";
    //定向采购
    String HMME_TYPE_URL_DINGXIANG = "4001";
    //二手市场
    String HMME_TYPE_URL_ERSHOU = "5001";

    /**
     * 栏目页每个导航类型
     */
    //原生页面
    int HMME_NAV_HOME = 1;
    //商品分类列表
    int HMME_NAV_GOODS_TYPE = 2;
    //团购分类列表
    int HMME_NAV_IPC_TYPE = 3;

    /*团购订单状态*/
    int PENDING_PAY_MENT = 1; // 待付款
    int PENDING_USED = 2; // 待使用
    int PENDING_EVALUATION = 3; // 待评价

    // 价格为零显示方式
    String PRICE_STYLE = "0.0";

    // 昵称IntentCode
    int REQUEST_CODE_NICK = 0x110;
    // 性别
    int REQUEST_CODE_GENDER = 0x120;


    //收藏类型
    int COLLECT_TYPE_GOODS=1;//商品（电商）
    int COLLECT_TYPE_MERCHANT=2;//店铺（电商）
    int COLLECT_TYPE_MERCHANT_YIYE=3;//商家（团购）


    //订单来源
    int ORDER_SOURCE_TYPE_COMMON=1;//普通
    int ORDER_SOURCE_TYPE_SNAPUP=2;//促销
    int ORDER_SOURCE_TYPE_DINGXIANG=3;//定采

    // 支付方式
    String PAY_METHOD_WECHAT = "WECHAT";
    String PAY_METHOD_ALIPAY = "ALIPAY";
    String PAY_METHOD_UNIONPAY = "UNIONPAY";

    /*
      用户
     1、订单已发货；
     2、退货申请通过；
     3、退货申请驳回；
     4、退款成功；
     5、采购申请通过；
     6、采购申通驳回；
     7、采购单报价；

     商户
     11、退货通知；
     */
    int MSG_TYPE_ORDER_DELIVERED = 1;
    int MSG_TYPE_REFUND_APPLY_PASS = 2;
    int MSG_TYPE_REFUND_APPLY_REJECTED = 3;
    int MSG_TYPE_REFUND_SUCCESS = 4;
    int MSG_TYPE_PROCUREMENT_APPLY_PASS = 5;
    int MSG_TYPE_PROCUREMENT_APPLY_REJECTED = 6;
    int MSG_TYPE_PROCUREMENT_OFFER = 7;


    // 数字+字母密码正则表达式
    String REGEX_PWD = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,15}";

    // 蒲公英
    String PGY_APPID = "79ccfc25da861f703ed502071928536f";

}
