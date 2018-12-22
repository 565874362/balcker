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

//====================================登录================================

/** 获取短信验证码*/
+ (NSURLSessionTask *)postGetCodeWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 用户登录*/
+ (NSURLSessionTask *)postLoginWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

//====================================广告================================

/** 首页广告滚动*/
+ (NSURLSessionTask *)postGetAdListInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

//====================================医生================================
/** 医生列表*/
+ (NSURLSessionTask *)postGetDoctorListInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 医生信息*/
+ (NSURLSessionTask *)GetDoctorDetailInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

//====================================评论================================

/** 根据医生加载评论*/
+ (NSURLSessionTask *)postGetDoctorCommentListInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 患者评价*/
+ (NSURLSessionTask *)postUserCommentDoctorInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

//====================================问诊================================

/** 用户问诊列表*/
+ (NSURLSessionTask *)postGetWenZhenListInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 用户问诊详情*/
+ (NSURLSessionTask *)GetWenZhenDetailInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 增加问诊*/
+ (NSURLSessionTask *)postAddWenZhenInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

//====================================上传图片================================

/** 上传图片*/
+ (NSURLSessionTask *)postUploadWithparameters:(id)parameter images:(NSArray *)imagesArray success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;


//====================================字典================================

/** 获取字典*/
+ (NSURLSessionTask *)GetDictionaryDataInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

//====================================患者================================

/** 获取图片*/
+ (NSURLSessionTask *)GetUserPictureInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 设置图片*/
+ (NSURLSessionTask *)postUserPictureInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

//====================================挂号================================

/** 预约就诊列表*/
+ (NSURLSessionTask *)postyYuYueJiuZhenListInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 预约挂号*/
+ (NSURLSessionTask *)postYuYueGuaHaoInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 挂号详情*/
+ (NSURLSessionTask *)GetGuaHaoDetialInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;


//====================================出诊时间表================================

/** 出诊时间表*/
+ (NSURLSessionTask *)GetChuZhenTimeListInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;


//====================================聊天================================

/** 获取token*/
+ (NSURLSessionTask *)GetTokenInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 获取用户信息*/
+ (NSURLSessionTask *)GetUserDetailInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

@end
