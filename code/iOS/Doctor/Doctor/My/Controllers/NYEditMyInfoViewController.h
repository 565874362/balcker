//
//  NYEditMyInfoViewController.h
//  Doctor
//
//  Created by niuyao on 2018/12/5.
//  Copyright © 2018 joymates. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@class NYMyInfoDetailModel;

@interface NYEditMyInfoViewController : UIViewController

@property (nonatomic,strong) NYMyInfoDetailModel * infoModel;

@property (nonatomic,copy) void(^ChangDoctorInfoSuccessed)(void);
@end

NS_ASSUME_NONNULL_END
