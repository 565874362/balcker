//
//  NYNeedCheckViewController.h
//  Doctor
//
//  Created by niuyao on 2018/12/10.
//  Copyright © 2018 joymates. All rights reserved.
//  需要坐的检查

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface NYNeedCheckViewController : UIViewController

@property (nonatomic,copy) void(^clickDoneChoicCheck)(NSArray * choiceArr);

@property (nonatomic,strong) NSArray * alreadyChoiceArr;
@end

NS_ASSUME_NONNULL_END
