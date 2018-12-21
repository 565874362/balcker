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

/** 用户登录*/
+ (NSURLSessionTask *)postLoginWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure {
    NSString *url = [NSString stringWithFormat:@"%@%@", kApiPrefix,login];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];
}

//====================================广告================================

/** 首页广告滚动*/
+ (NSURLSessionTask *)postGetAdListInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url = [NSString stringWithFormat:@"%@%@", kApiPrefix,AdListInfo];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];

}

//====================================医生================================
/** 医生列表*/
+ (NSURLSessionTask *)postGetDoctorListInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url = [NSString stringWithFormat:@"%@%@", kApiPrefix,DoctorListInfo];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];
}

/** 医生信息*/
+ (NSURLSessionTask *)GetDoctorDetailInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString * url = [NSString stringWithFormat:@"%@%@/%@",kApiPrefix,DoctorDetailInfo,parameters];
    return [self requestWithURL:url parameters:parameters success:success failure:failure];
}

/** 获取推荐医生*/
+ (NSURLSessionTask *)postGetTuiJianDoctorListInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url = [NSString stringWithFormat:@"%@%@", kApiPrefix,getTuiJianDoctorListInfo];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];

}
//====================================评论================================

/** 根据医生加载评论*/
+ (NSURLSessionTask *)postGetDoctorCommentListInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url = [NSString stringWithFormat:@"%@%@", kApiPrefix,DoctorCommentListInfo];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];

}

/** 患者评价*/
+ (NSURLSessionTask *)postUserCommentDoctorInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url = [NSString stringWithFormat:@"%@%@", kApiPrefix,UserCommentDoctorInfo];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];
}

//====================================问诊================================

/** 用户问诊列表*/
+ (NSURLSessionTask *)postGetWenZhenListInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url = [NSString stringWithFormat:@"%@%@", kApiPrefix,wenZhenListInfo];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];
}

/** 用户问诊详情*/
+ (NSURLSessionTask *)GetWenZhenDetailInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString * url = [NSString stringWithFormat:@"%@%@/%@",kApiPrefix,wenZhenDetailInfo,parameters];
    return [self requestWithURL:url parameters:parameters success:success failure:failure];
}

/** 增加问诊*/
+ (NSURLSessionTask *)postAddWenZhenInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url = [NSString stringWithFormat:@"%@%@", kApiPrefix,addWenZhenInfo];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];
}



//====================================上传图片================================

/** 上传图片*/
+ (NSURLSessionTask *)postUploadWithparameters:(id)parameter images:(NSArray *)imagesArray success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url = [NSString stringWithFormat:@"%@%@", kApiPrefix,uploadImage];
    return [self postUploadURL:url parameters:parameter images:imagesArray success:success failure:failure];
}

//====================================字典================================

/** 获取字典*/
+ (NSURLSessionTask *)GetDictionaryDataInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString * url = [NSString stringWithFormat:@"%@%@/%@",kApiPrefix,getDictionaryDataInfo,parameters];
    return [self requestWithURL:url parameters:parameters success:success failure:failure];
}

//====================================患者================================

/** 获取图片*/
+ (NSURLSessionTask *)GetUserPictureInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString * url = [NSString stringWithFormat:@"%@%@",kApiPrefix,getUserPicture];
    return [self requestWithURL:url parameters:parameters success:success failure:failure];
}

/** 设置图片*/
+ (NSURLSessionTask *)postUserPictureInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url = [NSString stringWithFormat:@"%@%@", kApiPrefix,setUserPict];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];
}

//====================================挂号================================

/** 预约就诊列表*/
+ (NSURLSessionTask *)postyYuYueJiuZhenListInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url = [NSString stringWithFormat:@"%@%@", kApiPrefix,yuYueJiuZhenListInfo];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];
}

/** 预约挂号*/
+ (NSURLSessionTask *)postYuYueGuaHaoInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString *url = [NSString stringWithFormat:@"%@%@", kApiPrefix,yuYueGuaHao];
    return [self postRequestWithURL:url parameters:[parameters mj_JSONString] success:success failure:failure];
}

/** 挂号详情*/
+ (NSURLSessionTask *)GetGuaHaoDetialInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString * url = [NSString stringWithFormat:@"%@%@/%@",kApiPrefix,getGuaHaoDetail,parameters];
    return [self requestWithURL:url parameters:parameters success:success failure:failure];
}

//====================================出诊时间表================================

/** 出诊时间表*/
+ (NSURLSessionTask *)GetChuZhenTimeListInfoWithParameters:(id)parameters success:(PPRequestSuccess)success failure:(PPRequestFailure)failure
{
    NSString * url = [NSString stringWithFormat:@"%@%@/%@",kApiPrefix,ChuZhenTimeListInfo,parameters];
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
    if ([UserInfo getToken]) {
        [PPNetworkHelper setValue:[UserInfo getToken] forHTTPHeaderField:@"token"];
    }
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
    if ([UserInfo getToken]) {
        [PPNetworkHelper setValue:[UserInfo getToken] forHTTPHeaderField:@"token"];
    }

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
    if ([UserInfo getToken]) {
        [PPNetworkHelper setValue:[UserInfo getToken] forHTTPHeaderField:@"token"];
    }

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
     if ([UserInfo getToken]) {
         [PPNetworkHelper setValue:[UserInfo getToken] forHTTPHeaderField:@"token"];
     }

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
    
    if ([UserInfo getToken]) {
        [PPNetworkHelper setValue:[UserInfo getToken] forHTTPHeaderField:@"token"];
    }

    return [PPNetworkHelper uploadImagesWithURL:URL parameters:parameter name:@"file" images:imagesArray fileNames:nil imageScale:0.5 imageType:@"png" progress:^(NSProgress *progress) {
        
    } success:^(id responseObject) {
        success(responseObject);
    } failure:^(NSError *error) {
        failure(error);
    }];
}

@end
