//
//  NYPayWayModel.h
//  User
//
//  Created by niuyao on 2018/12/18.
//  Copyright © 2018 joymates. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface NYPayWayModel : NSObject

@property (nonatomic,copy) NSString * typeName;

@property (nonatomic,copy) NSString * imageName;

@property (nonatomic,assign) NSInteger payType;

@property (nonatomic,assign) BOOL isSeleted;

@end

NS_ASSUME_NONNULL_END
