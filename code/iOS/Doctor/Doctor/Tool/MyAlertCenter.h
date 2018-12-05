//
//  MyAlertCenter.h
//  PushTest
//
//  Created by Mac on 16/3/22.
//  Copyright © 2016年 Mac. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
@interface MyAlert : UIView

-(id)init;
- (void) setMessageText:(NSString *)message;
@end



@interface MyAlertCenter : NSObject{
    
    MyAlert *myAlertView;//alertView
    BOOL active;//当前是否在用
}

+ (MyAlertCenter *) defaultCenter;//单例 生成alert控制器
- (void) postAlertWithMessage:(NSString*)message;//弹出文字
@end
