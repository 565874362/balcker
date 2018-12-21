//
//  NYAdeptEntitiesModel.h
//  User
//
//  Created by niuyao on 2018/12/17.
//  Copyright © 2018 joymates. All rights reserved.
//

#import <Foundation/Foundation.h>

/*
 "id": null,
 "name": "咳嗽",
 "describe": "过敏性咳嗽",
 "ordered": null,
 "docId": null,
 "gmtCreate": null,
 "gmtModified": null,
 "isDel": null
 */

NS_ASSUME_NONNULL_BEGIN

@interface NYAdeptEntitiesModel : NSObject

@property (nonatomic,copy) NSString * id;
@property (nonatomic,copy) NSString * name;
@property (nonatomic,copy) NSString * describe;
@property (nonatomic,copy) NSString * ordered;
@property (nonatomic,copy) NSString * docId;
@property (nonatomic,copy) NSString * gmtCreate;
@property (nonatomic,copy) NSString * gmtModified;
@property (nonatomic,copy) NSString * isDel;

@end

NS_ASSUME_NONNULL_END
