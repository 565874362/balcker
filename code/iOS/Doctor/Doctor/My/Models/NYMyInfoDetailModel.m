//
//  NYMyInfoDetailModel.m
//  Doctor
//
//  Created by niuyao on 2018/12/5.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYMyInfoDetailModel.h"
#import "NYAdeptEntitiesModel.h"

@implementation NYMyInfoDetailModel

+ (NSDictionary *)objectClassInArray{
    return @{@"adeptEntities" : [NYAdeptEntitiesModel class]};
}

@end
