//
//  NYHomeHuanZheInfoDetailViewController.h
//  Doctor
//
//  Created by niuyao on 2018/12/5.
//  Copyright © 2018 joymates. All rights reserved.
//  患者信息

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface NYHomeHuanZheInfoDetailViewController : UIViewController

@property (nonatomic,copy) NSString * detailID;

@property (nonatomic,copy) void(^clickQiangDanSuccessed)(void);

@property (nonatomic,copy) void(^TiJiaoSuccessed)(void);

@end

NS_ASSUME_NONNULL_END
