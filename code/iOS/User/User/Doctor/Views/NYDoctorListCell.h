//
//  NYDoctorListCell.h
//  User
//
//  Created by niuyao on 2018/12/5.
//  Copyright © 2018 joymates. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@class NYDoctorModel;

@interface NYDoctorListCell : UITableViewCell

@property (nonatomic, strong) NYDoctorModel * doctorModel;
@end

NS_ASSUME_NONNULL_END
