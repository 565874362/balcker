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

/** 用户登录*/
NSString *const login = @"/rest/login/patient";

//====================================广告================================

/** 首页广告滚动*/
NSString *const AdListInfo = @"/rest/basad/list";


//====================================医生================================

/** 医生列表*/
NSString *const DoctorListInfo = @"/rest/usdoctor/list";

/** 医生信息*/
NSString *const DoctorDetailInfo = @"/rest/usdoctor/info";

//====================================评论================================

/** 根据医生加载评论*/
NSString *const DoctorCommentListInfo = @"/rest/sercomment/doctorList";

/** 患者评价*/
NSString *const UserCommentDoctorInfo = @"/rest/sercomment/patientCommit";


//====================================问诊================================

/** 用户问诊列表*/
NSString *const wenZhenListInfo = @"/rest/serinquiry/patientList";

/** 用户问诊详情*/
NSString *const wenZhenDetailInfo = @"/rest/serinquiry/info";

/** 增加问诊*/
NSString *const addWenZhenInfo = @"/rest/serinquiry/save";


//====================================上传图片================================

/** 上传图片*/
NSString *const uploadImage = @"/rest/upload";


//====================================字典================================

/** 获取字典*/
NSString *const getDictionaryDataInfo = @"/rest/basdictionary/list";


//====================================患者================================

/** 获取图片*/
NSString *const getUserPicture = @"/rest/patient/photo";

/** 设置图片*/
NSString *const setUserPict = @"/rest/patient/phone";


//====================================挂号================================

/** 预约就诊列表*/
NSString *const yuYueJiuZhenListInfo = @"/rest/serregistration/patientList";

/** 预约挂号*/
NSString *const yuYueGuaHao = @"/rest/serregistration/registration";

/** 挂号详情*/
NSString *const getGuaHaoDetail = @"/rest/serregistration/info";


//====================================出诊时间表================================

/** 出诊时间表*/
NSString *const ChuZhenTimeListInfo = @"/rest/serschedule/diagnoseList";

//====================================聊天================================

/** 获取token*/
NSString *const getToken = @"/rest/chat/token";

/** 获取用户信息*/
NSString *const getUserInfo = @"/rest/chat/info";

#endif
