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


#define DevelopSever 1
#define TestSever    0
#define ProductSever 0
#define XKSSever     0


#if DevelopSever
/** 东东开发地址*/
NSString *const kApiPrefix = @"http://192.168.2.96:8080";
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

//====================================登录================================

/** 获取短信验证码*/
NSString *const getCode = @"/rest/login/verification";

/** 注册*/
NSString *const Register = @"/rest/login/doctor/register";

/** 登录*/
NSString *const login = @"/rest/login/doctor";

//====================================医院================================

/** 医院列表*/
NSString *const getHospitalListInfo = @"/rest/bashospital/list";

//====================================科室================================

/** 科室列表*/
NSString *const getKeShiListInfo = @"/rest/basoffice/list";

//====================================字典================================

/** 获取字典*/
NSString *const getDictionaryDataInfo = @"/rest/basdictionary/list";

//====================================上传图片================================

/** 上传图片*/
NSString *const uploadImage = @"/rest/upload";

#endif
