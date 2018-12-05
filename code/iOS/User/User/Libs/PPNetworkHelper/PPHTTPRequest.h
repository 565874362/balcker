//
//  PPHTTPRequest.h
//  PPNetworkHelper
//
//  Created by AndyPang on 2017/4/10.
//  Copyright © 2017年 AndyPang. All rights reserved.
//

#import <Foundation/Foundation.h>

/*
 以下Block的参数你根据自己项目中的需求来指定, 这里仅仅是一个演示的例子
 */

/**
 请求成功的block
 
 @param response 响应体数据
 */
typedef void(^PPRequestSuccess)(id response);
/**
 请求失败的block
 
 @param error 扩展信息
 */
typedef void(^PPRequestFailure)(NSError *error);

/**
 缓存的信息
 
 @param responseCache 缓存的信息
 */
typedef void(^cacheMessage)(id responseCache);


@interface PPHTTPRequest : NSObject

/** 获取短信验证码*/
+ (NSURLSessionTask *)postGetCodeWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 校验验证码*/
+ (NSURLSessionTask *)postCheckCodeWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 忘记密码*/
+ (NSURLSessionTask *)postForgetPwdWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 用户登录*/
+ (NSURLSessionTask *)postLoginWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 第三方登录*/
+ (NSURLSessionTask *)postThirdLoginWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 用户注册*/
+ (NSURLSessionTask *)postUserRegistWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 根据商户id查询所有产品信息*/
+ (NSURLSessionTask *)getAllProductListWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 获取消息列表*/
+ (NSURLSessionTask *)getPushMsgListWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 获取用户信息*/
+ (NSURLSessionTask *)getUserInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 获取商户信息列表*/
+ (NSURLSessionTask *)postMerchantListWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 根据商户id查询商户下所有分类*/
+ (NSURLSessionTask *)getMerchantCategoryWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 根据分类id 获取产品信息列表*/
+ (NSURLSessionTask *)postProductListWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 根据商品id 获取产品信息详情*/
+ (NSURLSessionTask *)getProductDetailWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 根据套餐id 获取套餐信息详情*/
+ (NSURLSessionTask *)getTaoCanDetailWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;


/** 根据商户id 获取商户信息对象*/
+ (NSURLSessionTask *)getMerchantInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 用户信息修改*/
+ (NSURLSessionTask *)postChangeCustomerInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 修改密码*/
+ (NSURLSessionTask *)postChangeCustomerPasswordWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 创建订单*/
+ (NSURLSessionTask *)postCreateOrderDetailWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 获取订单列表*/
+ (NSURLSessionTask *)getOrderListWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 取消订单*/
+ (NSURLSessionTask *)postCancelOrderWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** paypal取消订单*/
+ (NSURLSessionTask *)postPaypalCancelOrderWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 查看订单详情*/
+ (NSURLSessionTask *)getOrderDetialWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 根据商户id获取商户营业时间*/
+ (NSURLSessionTask *)getMerchantBusinessTimeWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 获取用户的优惠券列表信息*/
+ (NSURLSessionTask *)getUserDiscountListWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 获取用户信用卡列表*/
+ (NSURLSessionTask *)getUserCardListWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;


/** 获取用户可用的优惠券*/
+ (NSURLSessionTask *)postCanUseDiscountWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 更新用户头像*/
+ (NSURLSessionTask *)postUploadWithparameters:(id)parameter images:(NSArray *)imagesArray success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/**=====================================收藏========================================*/
/** 取消收藏*/
+ (NSURLSessionTask *)postCancelCollectionWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 用户收藏商户*/
+ (NSURLSessionTask *)postUserCollectionMerchantWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 获取用户的收藏列表*/
+ (NSURLSessionTask *)postCollectionListWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 判断某一商户是否被用户收藏*/
+ (NSURLSessionTask *)postUserHasCollectionWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 店铺就餐等待时间*/
+ (NSURLSessionTask *)postOrderWaitingTimeWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** Stripe支付订单*/
+ (NSURLSessionTask *)postStripePayWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 添加用户信用卡*/
+ (NSURLSessionTask *)postAddUserCardWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 再次购买*/
+ (NSURLSessionTask *)postOrderBuyAgainPayWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;


/** 获取运营地区列表*/
+ (NSURLSessionTask *)getSearchAreaListWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 根据QrCode获取商户信息*/
+ (NSURLSessionTask *)getMerchantInfoWithQrCodeWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;


/** 地理位置搜索*/
+ (NSURLSessionTask *)getLocationListWithCity:(id)city WithText:(id)text success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 删除卡片*/
+ (NSURLSessionTask *)postDeletedCardPayWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;


/**=====================================预约========================================*/

/** 根据id获取商户预定时间折扣*/
+ (NSURLSessionTask *)postGetZheKouTimeListPayWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 创建预约订单*/
+ (NSURLSessionTask *)postCreatZheKouOrderPayWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 获取用户预约列表数据*/
+ (NSURLSessionTask *)postGetYuYueOrderListPayWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 取消预约订单*/
+ (NSURLSessionTask *)postCancelYuYueOrderPayWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 判断用户是否可预约*/
+ (NSURLSessionTask *)getPersonIsYuYueOrderWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 获取邮件账号*/
+ (NSURLSessionTask *)getEmailWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** PayPal支付回调接口*/
+ (NSURLSessionTask *)getPayPalVerifyPaymentWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

@end
