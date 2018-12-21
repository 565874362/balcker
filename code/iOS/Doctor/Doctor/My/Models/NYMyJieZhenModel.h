//
//  NYMyJieZhenModel.h
//  Doctor
//
//  Created by niuyao on 2018/12/20.
//  Copyright © 2018 joymates. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN
@class NYMyInfoDetailModel;
@class NYPayInfoModel;


@interface NYMyJieZhenModel : NSObject

@property (nonatomic,copy) NSString * content;

@property (nonatomic,copy) NSString * age;
@property (nonatomic,strong) NYMyInfoDetailModel * doctor;
@property (nonatomic,copy) NSString * doctorId;
@property (nonatomic,copy) NSString * fee;
@property (nonatomic,copy) NSString * gender;
@property (nonatomic,copy) NSString * gmtCreate;
@property (nonatomic,copy) NSString * gmtModified;
@property (nonatomic,copy) NSString * id;
@property (nonatomic,copy) NSString * isDel;
@property (nonatomic,copy) NSString * name;
@property (nonatomic,copy) NSString * patientId;
@property (nonatomic,strong) NYPayInfoModel * payInfo;
@property (nonatomic,copy) NSString * phone;
@property (nonatomic,copy) NSString * scheduleId;
@property (nonatomic,copy) NSString * status;
@property (nonatomic,copy) NSString * timePart;
@property (nonatomic,copy) NSString * visitTime;


@end

NS_ASSUME_NONNULL_END
