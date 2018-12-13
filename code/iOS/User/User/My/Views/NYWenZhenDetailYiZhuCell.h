//
//  NYWenZhenDetailYiZhuCell.h
//  User
//
//  Created by niuyao on 2018/12/13.
//  Copyright © 2018 joymates. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface NYWenZhenDetailYiZhuCell : UITableViewCell

@property (nonatomic,copy) NSString * content;

@property (nonatomic,copy) void(^clickZiXunBlock)(void);

@property (nonatomic,copy) void(^clickJiuZhenBlock)(void);

@end

NS_ASSUME_NONNULL_END
