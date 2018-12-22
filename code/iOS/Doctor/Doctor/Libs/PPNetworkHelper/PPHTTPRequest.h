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

/** 注册*/
+ (NSURLSessionTask *)postRegisterInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 登录*/
+ (NSURLSessionTask *)postLoginWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;


//====================================医院================================

/** 医院列表*/
+ (NSURLSessionTask *)GetHospitalListInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

//====================================科室================================

/** 科室列表*/
+ (NSURLSessionTask *)GetKeShiListInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

//====================================字典================================

/** 获取字典*/
+ (NSURLSessionTask *)GetDictionaryDataInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

//====================================上传图片================================

/** 上传图片*/
+ (NSURLSessionTask *)postUploadWithparameters:(id)parameter images:(NSArray *)imagesArray success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

//====================================医生================================

/** 获取医生信息*/
+ (NSURLSessionTask *)GetDoctorDetailInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 编辑医生信息*/
+ (NSURLSessionTask *)postEditDoctorDetailInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

//====================================出诊时间表================================

/** 获取接诊周期*/
+ (NSURLSessionTask *)GetJieZhenZhouQiInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 接诊时间*/
+ (NSURLSessionTask *)GetJieZhenTimeInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 增加接诊时间*/
+ (NSURLSessionTask *)postAddJieZhenTimeInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

//====================================问诊================================

/** 我的患者-列表*/
+ (NSURLSessionTask *)postGetDoctorWenZhenListInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 用户问诊详情*/
+ (NSURLSessionTask *)GetWenZhenDetailInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 首页患者-列表*/
+ (NSURLSessionTask *)postHomeHuanZheListInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 医生点击接诊*/
+ (NSURLSessionTask *)GetDoctorClickJieZhenInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 医生回复*/
+ (NSURLSessionTask *)postDoctorHuiFuWenZhenInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;


//====================================健康检查================================

/** 健康检查列表*/
+ (NSURLSessionTask *)GetJianKangCheckInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

//====================================挂号================================

/** 医生接诊列表*/
+ (NSURLSessionTask *)postDoctorJieZhenListInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

//====================================聊天================================

/** 获取token*/
+ (NSURLSessionTask *)GetTokenInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

/** 获取用户信息*/
+ (NSURLSessionTask *)GetUserDetailInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure;

@end
