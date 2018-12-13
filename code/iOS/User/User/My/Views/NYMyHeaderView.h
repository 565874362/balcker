//
//  NYMyHeaderView.h
//  Doctor
//
//  Created by niuyao on 2018/12/4.
//  Copyright © 2018 joymates. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface NYMyHeaderView : UITableViewHeaderFooterView

@property (nonatomic,strong) UIImageView * headerImg;

@property (nonatomic,copy) void(^clickHeader)(void);

@end

NS_ASSUME_NONNULL_END
