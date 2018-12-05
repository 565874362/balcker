//
//  PPInterfacedConst.h
//  PPNetworkHelper
//
//  Created by AndyPang on 2017/4/10.
//  Copyright © 2017年 AndyPang. All rights reserved.
//

#import <Foundation/Foundation.h>
/*
 
 将项目中所有的接口写在这里,方便统一管理,降低耦合

 这里通过宏定义来切换你当前的服务器类型,
 将你要切换的服务器类型宏后面置为真(即>0即可),其余为假(置为0)
 如下:现在的状态为测试服务器
 这样做切换方便,不用来回每个网络请求修改请求域名,降低出错事件
 */

#ifndef PPInterfacedConst_h
#define PPInterfacedConst_h


#define DevelopSever 0
#define TestSever    0
#define ProductSever 1
#define XKSSever     0


#if DevelopSever
/** 新加坡测试服务地址*/
NSString *const kApiPrefix = @"http://www.somatech.com.sg";
//NSString *const kApiPrefix = @"http://47.74.250.52:8081/soma-api";
#elif TestSever
/** 黄明超服务*/
NSString *const kApiPrefix = @"http://192.168.2.72:8080/soma-api";
#elif ProductSever
/** 测试服务*/
NSString *const kApiPrefix = @"http://47.74.222.115:8091/soma-api";
#elif XKSSever
/** 准生产服务*/
NSString *const kApiPrefix = @"http://114.215.192.125:8084/soma-api";

#endif

#pragma mark - 详细接口地址

/** 获取短信验证码*/
NSString *const getCode = @"/app/sms/send";

/** 校验验证码*/
NSString *const checkCode = @"/app/sms/check";

/** 再次购买*/
NSString *const orderBuyAgain = @"/app/corder/buyAgain";

/** 添加信用卡*/
NSString *const addUserCard = @"/app/ccustomerCard/save";

/** 获取用户信用卡列表*/
NSString *const getUserCardList = @"/app/ccustomerCard/list";

/** 删除卡片*/
NSString *const deletedCard = @"/app/ccustomerCard/delete";


/** 用户注册*/
NSString *const regist = @"/app/register";

/** 忘记密码*/
NSString *const forgetPwd = @"/app/forgetPwd";

/** 用户登录*/
NSString *const login = @"/app/login";

/** 第三方登录*/
NSString *const ThirdLogin = @"/app/login/other";

/** 获取用户信息*/
NSString *const userInfo = @"/app/userInfo";

/** 获取商户信息列表*/
NSString *const merchantList = @"/app/merchant/list";

/** 根据商户id查询商户下所有分类*/
NSString *const getMerchantCategory = @"/app/ccategory/getMerchantCategory";

/** 根据分类id 获取产品信息列表*/
NSString *const getProductList = @"/app/cproduct/list";

/** 根据商品id 获取产品信息详情*/
NSString *const getProductDetail = @"/app/cproduct/details";

/** 根据套餐id 获取套餐信息详情*/
NSString *const getTaoCanDetail = @"/app/cproduct/getPackagedetails";

/** 根据商户id 获取商户信息对象*/
NSString *const getMerchantInfo = @"/app/merchant/info";

/** 用户信息修改*/
NSString *const changeCustomerInfo = @"/app/customer/update";

/** 修改密码*/
NSString *const changePassword = @"/app/customer/updatePassword";

/** 创建订单*/
NSString *const createOrderDetail = @"/app/corder/create";

/** 获取订单列表*/
NSString *const getOrderList = @"/app/corder/getList";

/** 查看订单详情*/
NSString *const lookOrderDetail = @"/app/corder/info";

/** 取消订单*/
NSString *const cancelOrder = @"/app/stripe/refund";
/** paypal取消订单*/
NSString *const paypalCancelOrder = @"/app/paypal/refund";

/** 根据商户id获取商户营业时间*/
NSString *const getMerchantBusinessTime = @"/app/merchant/getBusinessTime";

/** 获取用户的优惠券列表信息*/
NSString *const getUserDiscountList = @"/app/cdiscount/getDiscount";

/** 获取用户可用的优惠券*/
NSString *const getGoodCanUseDiscount = @"/app/cdiscount/canUseDiscount";

/** 根据商户id查询所有产品信息*/
NSString *const getAllProductList = @"/app/cproduct/getProductList";

/**=====================================收藏========================================*/
/** 取消收藏*/
NSString *const cancelCollection = @"/app/ccollection/cancelCollection";

/** 用户收藏商户*/
NSString *const collectionMerchant = @"/app/ccollection/collect";

/** 获取用户的收藏列表*/
NSString *const getUserCollectionList = @"/app/ccollection/getCollection";

/** 判断某一商户是否被用户收藏*/
NSString *const hasCollection = @"/app/ccollection/hasCollect";

/** 更新头像*/
NSString *const upDateUserImg = @"/oss/upload";

/** 店铺就餐等待时间*/
NSString *const orderWaitingTime = @"/app/corder/orderWaitingTime";


/** Stripe支付订单*/
NSString *const stripePay = @"/app/stripe/pay";


/** 获取消息列表*/
NSString *const getPushMsgList = @"/app/customer/getPushMsg";


/** 获取运营地区列表*/
NSString *const getSearchAreaList = @"/app/merchant/searchArea";

/** 根据qrCode获取商户信息对象*/
NSString *const getMerchantInfoWithQrCode = @"/app/merchant/infoQrCode";

/**=====================================预约========================================*/

/** 根据id获取商户预定时间折扣*/
NSString *const postZheKouTimeList = @"/app/cmerchantReserved/getReservedTime";

/** 创建预约订单*/
NSString *const postCreatYuYueOrder = @"/app/cmerchantReserved/save";

/** 我的预约列表接口*/
NSString *const YuYueOrderList = @"/app/cReservedOrder/getReservedWait";

/** 取消预约*/
NSString *const cancelReservedYuYueOrder = @"/app/cReservedOrder/cancelReserved";

/** 判断用户是否可预约*/
NSString *const personIsYuYueOrder = @"/app/cmerchantReserved/info";

/** 获取邮件*/
NSString *const getEmail = @"/app/corder/getEmail";

/** PayPal支付回调接口*/
NSString *const paypalVerifyPayment = @"/app/paypal/verifyPayment";


#endif
