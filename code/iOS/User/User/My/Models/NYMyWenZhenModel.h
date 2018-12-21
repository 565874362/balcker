//
//  NYMyWenZhenModel.h
//  User
//
//  Created by niuyao on 2018/12/5.
//  Copyright © 2018 joymates. All rights reserved.
//

#import <Foundation/Foundation.h>

/*
 advice = "<null>";
 age = 5;
 bloodType = "<null>";
 characterDescribe = "\U8eab\U4f53\U4e0d\U8212\U670d\Uff0c\U5168\U8eab\U4e0d\U8212\U670d\Uff0c\Uff0c\Uff0c\U8eab\U4f53\U4e0d\U8212\U670d\Uff0c\U5168\U8eab\U4e0d\U8212\U670d\Uff0c\Uff0c\Uff0c\U8eab\U4f53\U4e0d\U8212\U670d\Uff0c\U5168\U8eab\U4e0d\U8212\U670d\Uff0c\Uff0c\Uff0c\U8eab\U4f53\U4e0d\U8212\U670d\Uff0c\U5168\U8eab\U4e0d\U8212\U670d\Uff0c\Uff0c\Uff0c\U8eab\U4f53\U4e0d\U8212\U670d\Uff0c\U5168\U8eab\U4e0d\U8212\U670d\Uff0c\Uff0c\Uff0c\Uff0c\U8eab\U4f53\U4e0d\U8212\U670d\Uff0c\U5168\U8eab\U4e0d\U8212\U670d\Uff0c\Uff0c\Uff0c\Uff0c\U8eab\U4f53\U4e0d\U8212\U670d\Uff0c\U5168\U8eab\U4e0d\U8212\U670d\Uff0c\Uff0c\Uff0c\Uff0c\U8eab\U4f53\U4e0d\U8212\U670d\Uff0c\U5168\U8eab\U4e0d\U8212\U670d";
 diet = "<null>";
 doctorId = "<null>";
 exaContent = "<null>";
 exaFee = "<null>";
 exaIds = "<null>";
 gender = 0;
 gmtCreate = "2018-12-17 16:11:10";
 gmtModified = "<null>";
 id = 1074577730412011522;
 image = "<null>";
 isDel = "<null>";
 name = "\U97e9\U6885\U6885";
 patientId = "<null>";
 phone = "<null>";
 response = "<null>";
 sleep = "<null>";
 status = 1;
 voiceDescribe = "<null>";
 */

NS_ASSUME_NONNULL_BEGIN

@interface NYMyWenZhenModel : NSObject

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
@property (nonatomic,copy) NSString * status;  //1待接诊 2待回复 3已回复（医生状态） //1未解答 2未解答 3已解答（患者状态）
@property (nonatomic,copy) NSString * voiceDescribe;

@end

NS_ASSUME_NONNULL_END
