//
//  NYDoctorDetailModelBtnCell.h
//  User
//
//  Created by niuyao on 2018/12/7.
//  Copyright © 2018 joymates. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface NYDoctorDetailModelBtnCell : UITableViewCell

@property (nonatomic,strong) void(^clickShowModelButton)(void);

@end

NS_ASSUME_NONNULL_END
