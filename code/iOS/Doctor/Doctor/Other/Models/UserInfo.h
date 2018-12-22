//
//  UserInfo.h
//  TLWUser
//
//  Created by BlueWind on 2017/12/27.
//  Copyright © 2017年 joymates. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface UserInfo : NSObject


/**
 获取用户的 account
 */
+ (NSString *)getAccount;
+ (void)setAccount:(NSString *)account;

/**
 获取用户名称
 */
+ (NSString *)getName;
+ (void)setName:(NSString *)name;

/**
 获取昵称
 */
+ (NSString *)getNickName;
+ (void)setNickName:(NSString *)nickName;

/** 获取手机号*/
+ (NSString *)getPhone;
+ (void)setPhone:(NSString *)phone;


/**
 获取性别
 */
+ (NSString *)getSex;
+ (void)setSex:(NSString *)sex;

/**
 获取生日
 */
+ (NSString *)getBirthday;
+ (void)setBirthday:(NSString *)birthday;


/**
 获取 token
 */
+ (NSString *)getToken;
+ (void)setToken:(NSString *)token;

/**
 获取 RongYuntoken
 */
+ (NSString *)getRongYunToken;
+ (void)setRongYunToken:(NSString *)token;

/**
 获取 头像
 */
+ (NSString *)getPic;
+ (void)setPic:(NSString *)pic;

/**
 获取 mid
 */
+ (NSString *)getMid;
+ (void)setMid:(NSString *)mid;

/**
 获取美洽clientId
 */
+ (NSString *)getClientId;
+ (void)setClientId:(NSString *)clientId;


@end
