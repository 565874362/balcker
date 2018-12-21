//
//  NYHuanZheDetailModel.h
//  Doctor
//
//  Created by niuyao on 2018/12/20.
//  Copyright © 2018 joymates. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN


@class NYMyWenZhenModel;
@class NYDoctorModel;
@class NYHuanZheDetialCheckModel;

@interface NYHuanZheDetailModel : NSObject

@property (nonatomic,strong) NYMyWenZhenModel * info;
@property (nonatomic,strong) NYDoctorModel * doctor;
@property (nonatomic,strong) NSArray<NYHuanZheDetialCheckModel *> * healthExaminations;

@end

NS_ASSUME_NONNULL_END
