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

@property (nonatomic,copy) NSString * content;

@property (nonatomic,copy) NSString * state; //0待抢单 1未回复 2 已回复
@end

//NS_ASSUME_NONNULL_END
