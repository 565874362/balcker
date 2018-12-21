//
//  NYPayInfoModel.h
//  User
//
//  Created by niuyao on 2018/12/18.
//  Copyright © 2018 joymates. All rights reserved.
//

#import <Foundation/Foundation.h>

/*
 "id": null,
 "amount": 100,
 "type": 1,
 "regId": 1074570494281281500,
 "hosId": null,
 "patId": null,
 "docId": null,
 "thirdId": null,
 "gmtCreate": "2018-12-17 16:36:01",
 "isDel": null
 */

NS_ASSUME_NONNULL_BEGIN

@interface NYPayInfoModel : NSObject

@property (nonatomic,copy) NSString * id;
@property (nonatomic,copy) NSString * amount;
@property (nonatomic,copy) NSString * type;
@property (nonatomic,copy) NSString * regId;
@property (nonatomic,copy) NSString * hosId;
@property (nonatomic,copy) NSString * patId;
@property (nonatomic,copy) NSString * docId;
@property (nonatomic,copy) NSString * thirdId;
@property (nonatomic,copy) NSString * gmtCreate;
@property (nonatomic,copy) NSString * isDel;


@end

NS_ASSUME_NONNULL_END
