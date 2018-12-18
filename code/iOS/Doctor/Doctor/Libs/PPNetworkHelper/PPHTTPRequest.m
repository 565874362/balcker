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

//====================================登录================================


/** 获取短信验证码*/
+ (NSURLSessionTask *)postGetCodeWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString * url = [NSString stringWithFormat:@"%@%@/%@",kApiPrefix,getCode,parameters];
    return [self requestWithURL:url parameters:parameters success:success failure:failure];
}

/** 注册*/
+ (NSURLSessionTask *)postRegisterInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url = [NSString stringWithFormat:@"%@%@", kApiPrefix,Register];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];
}

/** 用户登录*/
+ (NSURLSessionTask *)postLoginWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure {
    NSString *url = [NSString stringWithFormat:@"%@%@", kApiPrefix,login];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];
}

//====================================医院================================

/** 医院列表*/
+ (NSURLSessionTask *)GetHospitalListInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString * url = [NSString stringWithFormat:@"%@%@",kApiPrefix,getHospitalListInfo];
    return [self requestWithURL:url parameters:parameters success:success failure:failure];
}

//====================================科室================================

/** 科室列表*/
+ (NSURLSessionTask *)GetKeShiListInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString * url = [NSString stringWithFormat:@"%@%@",kApiPrefix,getKeShiListInfo];
    return [self requestWithURL:url parameters:parameters success:success failure:failure];
}

//====================================字典================================

/** 获取字典*/
+ (NSURLSessionTask *)GetDictionaryDataInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString * url = [NSString stringWithFormat:@"%@%@/%@",kApiPrefix,getDictionaryDataInfo,parameters];
    return [self requestWithURL:url parameters:parameters success:success failure:failure];
}

//====================================上传图片================================

/** 上传图片*/
+ (NSURLSessionTask *)postUploadWithparameters:(id)parameter images:(NSArray *)imagesArray success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url = [NSString stringWithFormat:@"%@%@", kApiPrefix,uploadImage];
    return [self postUploadURL:url parameters:parameter images:imagesArray success:success failure:failure];
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
