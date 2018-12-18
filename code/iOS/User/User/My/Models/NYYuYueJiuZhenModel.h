//
//  NYYuYueJiuZhenModel.h
//  User
//
//  Created by niuyao on 2018/12/5.
//  Copyright © 2018 joymates. All rights reserved.
//

#import <Foundation/Foundation.h>

/*
 {
 age = 5;
 doctor =                     {
 adeptEntities = "<null>";
 appointmentNum = 2;
 experience = "\U6cbb\U7597\U8fc7\U764c\U75c7";
 gender = 1;
 gmtCreate = "2018-12-14 17:17:39";
 gmtModified = "2018-12-18 10:17:39";
 hosId = 1;
 hosName = "\U897f\U4eac\U533b\U9662";
 id = 1;
 identityCard = 610202197311031346;
 isDel = 0;
 major = "\U8840\U764c";
 name = "\U674e\U65b9\U534e";
 offId = 1;
 offName = "\U8840\U6db2\U79d1";
 photo = "/files/";
 physicianLicence = "/physician_licence";
 positionId = 14;
 positionName = "\U4e3b\U4efb\U533b\U5e08";
 registrationFee = 100;
 status = 2;
 };
 doctorId = 1;
 fee = 100;
 gender = 1;
 gmtCreate = "2018-12-18 10:17:39";
 gmtModified = "2018-12-18 10:17:39";
 id = 1074851153503653889;
 isDel = 0;
 name = "\U725b\U903c";
 patientId = 1074487974525190145;
 payInfo = "<null>";
 phone = 15596679811;
 scheduleId = 1;
 status = 1;
 timePart = 0;
 visitTime = "2018-12-18 \U5468\U4e8c";
 }
 */

NS_ASSUME_NONNULL_BEGIN

@class NYDoctorModel;
@class NYPayInfoModel;
@interface NYYuYueJiuZhenModel : NSObject

@property (nonatomic,copy) NSString * age;
@property (nonatomic,strong) NYDoctorModel * doctor;
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
