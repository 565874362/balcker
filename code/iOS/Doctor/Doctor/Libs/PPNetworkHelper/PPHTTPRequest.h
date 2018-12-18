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

@end
