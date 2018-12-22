//
//  UserInfo.m
//  TLWUser
//
//  Created by BlueWind on 2017/12/27.
//  Copyright © 2017年 joymates. All rights reserved.
//

#import "UserInfo.h"

@implementation UserInfo


+ (NSString *)getAccount {
    return [[NSUserDefaults standardUserDefaults] objectForKey:@"account"];
}
+ (void)setAccount:(NSString *)account {
    [[NSUserDefaults standardUserDefaults] setObject:account forKey:@"account"];
    [[NSUserDefaults standardUserDefaults] synchronize];
}


+ (NSString *)getName {
    return [[NSUserDefaults standardUserDefaults] objectForKey:@"name"];
}
+ (void)setName:(NSString *)name {
    [[NSUserDefaults standardUserDefaults] setObject:name forKey:@"name"];
    [[NSUserDefaults standardUserDefaults] synchronize];
}

+ (NSString *)getNickName {
    return [[NSUserDefaults standardUserDefaults] objectForKey:@"nickName"];
}
+ (void)setNickName:(NSString *)nickName {
    [[NSUserDefaults standardUserDefaults] setObject:nickName forKey:@"nickName"];
    [[NSUserDefaults standardUserDefaults] synchronize];
}

+ (NSString *)getPhone {
    return [[NSUserDefaults standardUserDefaults] objectForKey:@"phone"];
}
+ (void)setPhone:(NSString *)phone {
    [[NSUserDefaults standardUserDefaults] setObject:phone forKey:@"phone"];
    [[NSUserDefaults standardUserDefaults] synchronize];
}

+ (NSString *)getSex {
     return [[NSUserDefaults standardUserDefaults] objectForKey:@"sex"];
}
+ (void)setSex:(NSString *)sex {
    [[NSUserDefaults standardUserDefaults] setObject:sex forKey:@"sex"];
    [[NSUserDefaults standardUserDefaults] synchronize];
}

+ (NSString *)getBirthday {
    return [[NSUserDefaults standardUserDefaults] objectForKey:@"birthday"];
}
+ (void)setBirthday:(NSString *)birthday {
    [[NSUserDefaults standardUserDefaults] setObject:birthday forKey:@"birthday"];
    [[NSUserDefaults standardUserDefaults] synchronize];
}

+ (NSString *)getToken {
    return [[NSUserDefaults standardUserDefaults] objectForKey:@"token"];
}
+ (void)setToken:(NSString *)token {
    [[NSUserDefaults standardUserDefaults] setObject:token forKey:@"token"];
    [[NSUserDefaults standardUserDefaults] synchronize];
}

/**
 获取 RongYuntoken
 */
+ (NSString *)getRongYunToken
{
    return [[NSUserDefaults standardUserDefaults] objectForKey:@"RongYuntoken"];
}
+ (void)setRongYunToken:(NSString *)token
{
    [[NSUserDefaults standardUserDefaults] setObject:token forKey:@"RongYuntoken"];
    [[NSUserDefaults standardUserDefaults] synchronize];
}


+ (NSString *)getPic
{
    return [[NSUserDefaults standardUserDefaults] objectForKey:@"pic"];
}
+ (void)setPic:(NSString *)pic
{
    [[NSUserDefaults standardUserDefaults] setObject:pic forKey:@"pic"];
    [[NSUserDefaults standardUserDefaults] synchronize];
}

+ (NSString *)getMid {
    return [[NSUserDefaults standardUserDefaults] objectForKey:@"mid"];
}
+ (void)setMid:(NSString *)mid {
    [[NSUserDefaults standardUserDefaults] setObject:mid forKey:@"mid"];
    [[NSUserDefaults standardUserDefaults] synchronize];
}

/**
 获取美洽clientId
 */
+ (NSString *)getClientId
{
    return [[NSUserDefaults standardUserDefaults] objectForKey:@"clientId"];
}
+ (void)setClientId:(NSString *)clientId
{
    [[NSUserDefaults standardUserDefaults] setObject:clientId forKey:@"clientId"];
    [[NSUserDefaults standardUserDefaults] synchronize];
}

@end
