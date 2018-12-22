//
//  NYDoctorModel.h
//  User
//
//  Created by niuyao on 2018/12/5.
//  Copyright © 2018 joymates. All rights reserved.
//

#import <Foundation/Foundation.h>

/*
 "id": 1,
 "name": "李方华",
 "gender": 1,
 "offId": 1,
 "offName": "血液科",
 "positionId": 14,
 "positionName": "主任医师",
 "hosId": 1,
 "hosName": "西京医院",
 "photo": "/files/",
 "physicianLicence": "/physician_licence",
 "identityCard": "610202197311031346",
 "major": "血癌",
 "experience": "治疗过癌症",
 "registrationFee": 100,
 "appointmentNum": 0,
 "status": 2,
 "gmtCreate": "2018-12-14 17:17:39",
 "gmtModified": "2018-12-14 17:17:39",
 "isDel": 0
 */

NS_ASSUME_NONNULL_BEGIN

@class NYAdeptEntitiesModel;


@interface NYDoctorModel : NSObject

@property (nonatomic,copy) NSString * content;

@property (nonatomic,copy) NSString * yiyuanName;
@property (nonatomic,copy) NSString * shanChang1;
@property (nonatomic,copy) NSString * shanChang2;
@property (nonatomic,copy) NSString * shanChang3;


@property (nonatomic,copy) NSString * id;
@property (nonatomic,copy) NSString * name;
@property (nonatomic,copy) NSString * gender;
@property (nonatomic,copy) NSString * offId;
@property (nonatomic,copy) NSString * offName;
@property (nonatomic,copy) NSString * positionId;
@property (nonatomic,copy) NSString * positionName;
//@property (nonatomic,copy) NSString * position;
@property (nonatomic,copy) NSString * hosId;
@property (nonatomic,copy) NSString * hosName;
@property (nonatomic,copy) NSString * photo;
@property (nonatomic,copy) NSString * physicianLicence;
@property (nonatomic,copy) NSString * identityCard;
@property (nonatomic,copy) NSString * major;
@property (nonatomic,copy) NSString * experience;
@property (nonatomic,copy) NSString * registrationFee;
@property (nonatomic,copy) NSString * appointmentNum;
@property (nonatomic,copy) NSString * status;
@property (nonatomic,copy) NSString * gmtCreate;
@property (nonatomic,copy) NSString * gmtModified;
@property (nonatomic,copy) NSString * isDel;
@property (nonatomic,copy) NSString * accountId;

@property (nonatomic,strong) NSArray<NYAdeptEntitiesModel *> * adeptEntities;

@end

NS_ASSUME_NONNULL_END
