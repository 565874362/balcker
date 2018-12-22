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


//====================================医生================================

/** 获取医生信息*/
NSString *const getDoctorDetailInfo = @"/rest/usdoctor/info";

/** 编辑医生信息*/
NSString *const EditDoctorDetailInfo = @"/rest/usdoctor/update";


//====================================出诊时间表================================

/** 获取接诊周期*/
NSString *const getJieZhenZhouQiInfo = @"/rest/serschedule/diagnoseDates";

/** 接诊时间*/
NSString *const GetJieZhenTimeInfo = @"/rest/serschedule/diagnoseList";

/** 增加接诊时间*/
NSString *const AddJieZhenTimeInfo = @"/rest/serschedule/addDiagnose";

//====================================问诊================================

/** 我的患者-列表*/
NSString *const getDoctorWenZhenListInfo = @"/rest/serinquiry/doctorList";


/** 首页患者-列表*/
NSString *const getHomeHuanZheListInfo = @"/rest/serinquiry/doctorIndex";

/** 用户问诊详情*/
NSString *const wenZhenDetailInfo = @"/rest/serinquiry/info";

/** 医生点击接诊*/
NSString *const DoctorJieZhenInfo = @"/rest/serinquiry/accept";

/** 医生回复*/
NSString *const DoctorHuiFuWenZhenInfo = @"/rest/serinquiry/response";

//====================================健康检查================================

/** 健康检查列表*/
NSString *const JianKangCheckInfo = @"/rest/bashealthexamination/list";

//====================================挂号================================

/** 医生接诊列表*/
NSString *const DoctorJieZhenListInfo = @"/rest/serregistration/doctorListRegistration";


//====================================聊天================================

/** 获取token*/
NSString *const getToken = @"/rest/chat/token";

/** 获取用户信息*/
NSString *const getUserInfo = @"/rest/chat/info";

#endif
