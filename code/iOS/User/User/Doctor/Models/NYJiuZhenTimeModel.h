//
//  NYJiuZhenTimeModel.h
//  User
//
//  Created by niuyao on 2018/12/18.
//  Copyright © 2018 joymates. All rights reserved.
//

#import <Foundation/Foundation.h>

/*
 "id": 1,
 "fullDate": "2018-12-19 星期三",
 "date": "12-19 星期三",
 "morningRemainNum": 18,
 "afternoonRemainNum": 30
 */

NS_ASSUME_NONNULL_BEGIN

@interface NYJiuZhenTimeModel : NSObject

@property (nonatomic,copy) NSString * id;

@property (nonatomic,copy) NSString * fullDate;

@property (nonatomic,copy) NSString * date;

@property (nonatomic,copy) NSString * morningRemainNum;

@property (nonatomic,copy) NSString * afternoonRemainNum;

@end

NS_ASSUME_NONNULL_END
