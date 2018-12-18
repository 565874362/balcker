//
//  NYCommentModel.h
//  User
//
//  Created by niuyao on 2018/12/7.
//  Copyright © 2018 joymates. All rights reserved.
//  评价模型

#import <Foundation/Foundation.h>

/*
 "id": 1,
 "content": "很好",
 "patientId": 1073498376609280002,
 "patientPhoto": "",
 "patientAccount": "13474370876",
 "doctorId": 1,
 "status": 2,
 "gmtCreate": "2018-12-17 09:30:19",
 "gmtModified": "2018-12-17 09:30:22",
 "isDel": 0
 */
NS_ASSUME_NONNULL_BEGIN

@interface NYCommentModel : NSObject

@property (nonatomic, copy) NSString * id;
@property (nonatomic, copy) NSString * content;
@property (nonatomic, copy) NSString * patientId;
@property (nonatomic, copy) NSString * patientPhoto;
@property (nonatomic, copy) NSString * patientAccount;
@property (nonatomic, copy) NSString * doctorId;
@property (nonatomic, copy) NSString * status;
@property (nonatomic, copy) NSString * gmtCreate;
@property (nonatomic, copy) NSString * gmtModified;
@property (nonatomic, copy) NSString * isDel;

@end

NS_ASSUME_NONNULL_END
