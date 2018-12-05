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

@property (nonatomic,strong) UILabel * nameLB;

@property (nonatomic,strong) UILabel * typeLB;

@property (nonatomic,strong) UILabel * leaveLB;

@end

NS_ASSUME_NONNULL_END
