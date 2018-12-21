//
//  NYMyInfoDetailModel.h
//  Doctor
//
//  Created by niuyao on 2018/12/5.
//  Copyright © 2018 joymates. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@class NYAdeptEntitiesModel;


@interface NYMyInfoDetailModel : NSObject

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

@property (nonatomic,strong) NSArray<NYAdeptEntitiesModel *> * adeptEntities;

@end

NS_ASSUME_NONNULL_END
