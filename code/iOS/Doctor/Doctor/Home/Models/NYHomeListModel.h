//
//  NYHomeListModel.h
//  Doctor
//
//  Created by niuyao on 2018/12/3.
//  Copyright © 2018 joymates. All rights reserved.
//

#import <Foundation/Foundation.h>

//NS_ASSUME_NONNULL_BEGIN

@interface NYHomeListModel : NSObject

@property (nonatomic,copy) NSString * advice;
@property (nonatomic,copy) NSString * age;
@property (nonatomic,copy) NSString * bloodType;
@property (nonatomic,copy) NSString * characterDescribe;
@property (nonatomic,copy) NSString * diet;
@property (nonatomic,copy) NSString * doctorId;
@property (nonatomic,copy) NSString * exaContent;
@property (nonatomic,copy) NSString * exaFee;
@property (nonatomic,copy) NSString * exaIds;
@property (nonatomic,copy) NSString * gender;
@property (nonatomic,copy) NSString * gmtCreate;
@property (nonatomic,copy) NSString * gmtModified;
@property (nonatomic,copy) NSString * id;
@property (nonatomic,copy) NSString * image;
@property (nonatomic,copy) NSString * isDel;
@property (nonatomic,copy) NSString * name;
@property (nonatomic,copy) NSString * patientId;
@property (nonatomic,copy) NSString * phone;
@property (nonatomic,copy) NSString * response;
@property (nonatomic,copy) NSString * sleep;
@property (nonatomic,copy) NSString * status;  //1待接诊 2待回复 3已回复
@property (nonatomic,copy) NSString * voiceDescribe;

@end

//NS_ASSUME_NONNULL_END
