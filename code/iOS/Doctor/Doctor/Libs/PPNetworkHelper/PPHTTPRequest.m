//
//  PPHTTPRequest.m
//  PPNetworkHelper
//
//  Created by AndyPang on 2017/4/10.
//  Copyright © 2017年 AndyPang. All rights reserved.
//

#import "PPHTTPRequest.h"
#import "PPInterfacedConst.h"
#import "PPNetworkHelper.h"
@implementation PPHTTPRequest

/** 获取短信验证码*/
+ (NSURLSessionTask *)postGetCodeWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url = [NSString stringWithFormat:@"%@%@", kApiPrefix,getCode];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];
}

/** 校验验证码*/
+ (NSURLSessionTask *)postCheckCodeWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url = [NSString stringWithFormat:@"%@%@", kApiPrefix,checkCode];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];
}

/** 忘记密码*/
+ (NSURLSessionTask *)postForgetPwdWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url = [NSString stringWithFormat:@"%@%@", kApiPrefix,forgetPwd];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];
}

/** 用户登录*/
+ (NSURLSessionTask *)postLoginWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure {
    NSString *url = [NSString stringWithFormat:@"%@%@", kApiPrefix,login];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];
}

/** 第三方登录*/
+ (NSURLSessionTask *)postThirdLoginWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url = [NSString stringWithFormat:@"%@%@", kApiPrefix,ThirdLogin];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];
}

/** 用户注册*/
+ (NSURLSessionTask *)postUserRegistWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure {
    NSString *url= [NSString stringWithFormat:@"%@%@",kApiPrefix,regist];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];
}

/** 根据商户id查询所有产品信息*/
+ (NSURLSessionTask *)getAllProductListWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString * url = [NSString stringWithFormat:@"%@%@/%@",kApiPrefix,getAllProductList,parameters];
    return [self requestWithURL:url parameters:parameters success:success failure:failure];
}

/** 获取消息列表*/
+ (NSURLSessionTask *)getPushMsgListWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString * url = [NSString stringWithFormat:@"%@%@/%@",kApiPrefix,getPushMsgList,parameters];
    return [self requestWithURL:url parameters:parameters success:success failure:failure];
}

/** 获取用户信息*/
+ (NSURLSessionTask *)getUserInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString * url = [NSString stringWithFormat:@"%@%@",kApiPrefix,userInfo];
    return [self requestWithURL:url parameters:parameters success:success failure:failure];
}

/** 获取商户信息列表*/
+ (NSURLSessionTask *)postMerchantListWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url= [NSString stringWithFormat:@"%@%@",kApiPrefix,merchantList];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];
}

/** 根据商户id查询商户下所有分类*/
+ (NSURLSessionTask *)getMerchantCategoryWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString * url = [NSString stringWithFormat:@"%@%@/%@",kApiPrefix,getMerchantCategory,parameters];
    return [self requestWithURL:url parameters:parameters success:success failure:failure];
}

/** 根据分类id 获取产品信息列表*/
+ (NSURLSessionTask *)postProductListWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url= [NSString stringWithFormat:@"%@%@",kApiPrefix,getProductList];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];
}

/** 根据商品id 获取产品信息详情*/
+ (NSURLSessionTask *)getProductDetailWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString * url = [NSString stringWithFormat:@"%@%@/%@",kApiPrefix,getProductDetail,parameters];
    return [self requestWithURL:url parameters:parameters success:success failure:failure];
}

/** 根据套餐id 获取套餐信息详情*/
+ (NSURLSessionTask *)getTaoCanDetailWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString * url = [NSString stringWithFormat:@"%@%@/%@",kApiPrefix,getTaoCanDetail,parameters];
    return [self requestWithURL:url parameters:parameters success:success failure:failure];
}

/** 根据分类id 获取商户信息对象*/
+ (NSURLSessionTask *)getMerchantInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString * url = [NSString stringWithFormat:@"%@%@/%@",kApiPrefix,getMerchantInfo,parameters];
    return [self requestWithURL:url parameters:parameters success:success failure:failure];
}

/** 用户信息修改*/
+ (NSURLSessionTask *)postChangeCustomerInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url= [NSString stringWithFormat:@"%@%@",kApiPrefix,changeCustomerInfo];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];
}

/** 修改密码*/
+ (NSURLSessionTask *)postChangeCustomerPasswordWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url= [NSString stringWithFormat:@"%@%@",kApiPrefix,changePassword];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];
}

/** 创建订单*/
+ (NSURLSessionTask *)postCreateOrderDetailWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url= [NSString stringWithFormat:@"%@%@",kApiPrefix,createOrderDetail];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];
}

/** 获取订单列表*/
+ (NSURLSessionTask *)getOrderListWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url= [NSString stringWithFormat:@"%@%@",kApiPrefix,getOrderList];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];
}

/** 取消订单*/
+ (NSURLSessionTask *)postCancelOrderWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url= [NSString stringWithFormat:@"%@%@",kApiPrefix,cancelOrder];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];
}

/** paypal取消订单*/
+ (NSURLSessionTask *)postPaypalCancelOrderWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url= [NSString stringWithFormat:@"%@%@",kApiPrefix,paypalCancelOrder];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];
}


/** 查看订单详情*/
+ (NSURLSessionTask *)getOrderDetialWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString * url = [NSString stringWithFormat:@"%@%@/%@",kApiPrefix,lookOrderDetail,parameters];
    return [self requestWithURL:url parameters:parameters success:success failure:failure];
}

/** 根据商户id获取商户营业时间*/
+ (NSURLSessionTask *)getMerchantBusinessTimeWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString * url = [NSString stringWithFormat:@"%@%@/%@",kApiPrefix,getMerchantBusinessTime,parameters];
    return [self requestWithURL:url parameters:parameters success:success failure:failure];
}

/** 获取用户的优惠券列表信息*/
+ (NSURLSessionTask *)getUserDiscountListWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url= [NSString stringWithFormat:@"%@%@",kApiPrefix,getUserDiscountList];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];
}

/** 获取用户信用卡列表*/
+ (NSURLSessionTask *)getUserCardListWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url= [NSString stringWithFormat:@"%@%@/%@",kApiPrefix,getUserCardList,parameters];
    return [self requestWithURL:url parameters:parameters success:success failure:failure];
}
/** 更新用户头像*/
+ (NSURLSessionTask *)postUploadWithparameters:(id)parameter images:(NSArray *)imagesArray success:(PPRequestSuccess)success failure:(PPRequestFailure)failure {
    NSString *url = [NSString stringWithFormat:@"%@%@", kApiPrefix,upDateUserImg];
    return [self postUploadURL:url parameters:parameter images:imagesArray success:success failure:failure];
}
/** 获取用户可用的优惠券*/
+ (NSURLSessionTask *)postCanUseDiscountWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url= [NSString stringWithFormat:@"%@%@",kApiPrefix,getGoodCanUseDiscount];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];
}
/**=====================================收藏========================================*/
/** 取消收藏*/
+ (NSURLSessionTask *)postCancelCollectionWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url= [NSString stringWithFormat:@"%@%@",kApiPrefix,cancelCollection];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];
}

/** 用户收藏商户*/
+ (NSURLSessionTask *)postUserCollectionMerchantWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url= [NSString stringWithFormat:@"%@%@",kApiPrefix,collectionMerchant];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];
}

/** 获取用户的收藏列表*/
+ (NSURLSessionTask *)postCollectionListWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url= [NSString stringWithFormat:@"%@%@",kApiPrefix,getUserCollectionList];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];
}

/** 判断某一商户是否被用户收藏*/
+ (NSURLSessionTask *)postUserHasCollectionWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url= [NSString stringWithFormat:@"%@%@",kApiPrefix,hasCollection];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];
}

/** 店铺就餐等待时间*/
+ (NSURLSessionTask *)postOrderWaitingTimeWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString * url = [NSString stringWithFormat:@"%@%@/%@",kApiPrefix,orderWaitingTime,parameters];
    return [self requestWithURL:url parameters:parameters success:success failure:failure];
}

/** Stripe支付订单*/
+ (NSURLSessionTask *)postStripePayWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url= [NSString stringWithFormat:@"%@%@",kApiPrefix,stripePay];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];
}

/** 添加用户信用卡*/
+ (NSURLSessionTask *)postAddUserCardWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url= [NSString stringWithFormat:@"%@%@",kApiPrefix,addUserCard];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];
}
/** 再次购买*/
+ (NSURLSessionTask *)postOrderBuyAgainPayWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url= [NSString stringWithFormat:@"%@%@",kApiPrefix,orderBuyAgain];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];
}

/** 删除卡片*/
+ (NSURLSessionTask *)postDeletedCardPayWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url= [NSString stringWithFormat:@"%@%@",kApiPrefix,deletedCard];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];
}

/** 获取运营地区列表*/
+ (NSURLSessionTask *)getSearchAreaListWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString * url = [NSString stringWithFormat:@"%@%@",kApiPrefix,getSearchAreaList];
    return [self requestWithURL:url parameters:parameters success:success failure:failure];
}

/** 根据QrCode获取商户信息*/
+ (NSURLSessionTask *)getMerchantInfoWithQrCodeWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString * url = [NSString stringWithFormat:@"%@%@/%@",kApiPrefix,getMerchantInfoWithQrCode,parameters];
    url = [url stringByAddingPercentEncodingWithAllowedCharacters:[NSCharacterSet URLQueryAllowedCharacterSet]];

    return [self requestWithURL:url parameters:parameters success:success failure:failure];
}

/** 地理位置搜索*/
+ (NSURLSessionTask *)getLocationListWithCity:(id)city WithText:(id)text success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString * url = [[NSString alloc] initWithFormat:@"http://api.map.baidu.com/place_abroad/v1/suggestion?query=%@&region=%@&output=json&ak=dfWhbupYrtEFBTCKlSdpyRiiSdhmzWID",text,city];
    return [self requestWithURL:url parameters:nil success:success failure:failure];
}

/**=====================================预约========================================*/

/** 预约时间折扣接口*/
+ (NSURLSessionTask *)postGetZheKouTimeListPayWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url= [NSString stringWithFormat:@"%@%@",kApiPrefix,postZheKouTimeList];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];
}

/** 创建预约订单*/
+ (NSURLSessionTask *)postCreatZheKouOrderPayWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url= [NSString stringWithFormat:@"%@%@",kApiPrefix,postCreatYuYueOrder];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];
}

/** 获取用户预约列表数据*/
+ (NSURLSessionTask *)postGetYuYueOrderListPayWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url= [NSString stringWithFormat:@"%@%@",kApiPrefix,YuYueOrderList];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];
}

/** 取消预约订单*/
+ (NSURLSessionTask *)postCancelYuYueOrderPayWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url= [NSString stringWithFormat:@"%@%@",kApiPrefix,cancelReservedYuYueOrder];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];
}

/** 判断用户是否可预约*/
+ (NSURLSessionTask *)getPersonIsYuYueOrderWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString * url = [NSString stringWithFormat:@"%@%@/%@",kApiPrefix,personIsYuYueOrder,parameters];
    return [self postRequestWithURL:url parameters:nil success:success failure:failure];
}

/** 获取邮件账号*/
+ (NSURLSessionTask *)getEmailWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString * url = [NSString stringWithFormat:@"%@%@",kApiPrefix,getEmail];
    return [self requestWithURL:url parameters:parameters success:success failure:failure];
}

/** PayPal支付回调接口*/
+ (NSURLSessionTask *)getPayPalVerifyPaymentWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString * url = [NSString stringWithFormat:@"%@%@/%@",kApiPrefix,paypalVerifyPayment,parameters];
    return [self requestWithURL:url parameters:parameters success:success failure:failure];
}

/*
 配置好PPNetworkHelper各项请求参数,封装成一个公共方法,给以上方法调用,
 相比在项目中单个分散的使用PPNetworkHelper/其他网络框架请求,可大大降低耦合度,方便维护
 在项目的后期, 你可以在公共请求方法内任意更换其他的网络请求工具,切换成本小
 */

#pragma mark - 请求的公共方法
+ (NSURLSessionTask *)requestWithURL:(NSString *)URL parameters:(NSDictionary *)parameter success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    // 在请求之前你可以统一配置你请求的相关参数 ,设置请求头, 请求参数的格式, 返回数据的格式....这样你就不需要每次请求都要设置一遍相关参数
    // 设置请求头
    //[PPNetworkHelper setValue:@"9" forHTTPHeaderField:@"fromType"];
    [PPNetworkHelper setRequestSerializer:PPRequestSerializerJSON];
    // 发起请求
    return [PPNetworkHelper GET:URL parameters:parameter success:^(id responseObject) {
        // 在这里你可以根据项目自定义其他一些重复操作,比如加载页面时候的等待效果, 提醒弹窗....
        success(responseObject);
    } failure:^(NSError *error) {
        // 同上
        failure(error);
    }];
}

#pragma mark - GET 有缓存的公共方法
+ (NSURLSessionTask *)requestWithURL:(NSString *)URL parameters:(NSDictionary *)parameter cache:(cacheMessage)cache success:(PPRequestSuccess)success failure:(PPRequestFailure)failure {
    [PPNetworkHelper setRequestSerializer:PPRequestSerializerJSON];
    return [PPNetworkHelper GET:URL parameters:parameter responseCache:^(id responseCache) {
        cache(responseCache);
    } success:^(id responseObject) {
        success(responseObject);
    } failure:^(NSError *error) {
        failure(error);
    }];
}

#pragma mark - POST请求的公共方法
+ (NSURLSessionTask *)postRequestWithURL:(NSString *)URL parameters:(id)parameter success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    // 在请求之前你可以统一配置你请求的相关参数 ,设置请求头, 请求参数的格式, 返回数据的格式....这样你就不需要每次请求都要设置一遍相关参数
    // 设置请求头
    //[PPNetworkHelper setValue:@"9" forHTTPHeaderField:@"fromType"];
    [PPNetworkHelper setRequestSerializer:PPRequestSerializerJSON];
    
    // 发起请求
    return [PPNetworkHelper POST:URL parameters:parameter success:^(id responseObject) {
        // 在这里你可以根据项目自定义其他一些重复操作,比如加载页面时候的等待效果, 提醒弹窗....
        success(responseObject);
    } failure:^(NSError *error) {
        // 同上
        failure(error);
    }];
}

#pragma mark - POST 有缓存
 + (NSURLSessionTask *)postRequestWithURL:(NSString *)URL parameters:(id)parameter cache:(cacheMessage)cache success:(PPRequestSuccess)success failure:(PPRequestFailure)failure {
     [PPNetworkHelper setRequestSerializer:PPRequestSerializerJSON];
     return [PPNetworkHelper POST:URL parameters:parameter responseCache:^(id responseCache) {
         cache(responseCache);
     } success:^(id responseObject) {
         success(responseObject);
     } failure:^(NSError *error) {
         failure(error);
     }];
}

#pragma mark - 上传
+ (NSURLSessionTask *)postUploadURL:(NSString *)URL parameters:(id)parameter images:(NSArray *)imagesArray success:(PPRequestSuccess)success failure:(PPRequestFailure)failure {
    
    return [PPNetworkHelper uploadImagesWithURL:URL parameters:parameter name:@"file" images:imagesArray fileNames:nil imageScale:0.5 imageType:@"png/jpeg" progress:^(NSProgress *progress) {
        
    } success:^(id responseObject) {
        success(responseObject);
    } failure:^(NSError *error) {
        failure(error);
    }];
}

@end
