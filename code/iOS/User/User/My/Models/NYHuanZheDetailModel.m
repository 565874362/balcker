//
//  NYHuanZheDetailModel.m
//  Doctor
//
//  Created by niuyao on 2018/12/20.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYHuanZheDetailModel.h"
#import "NYHuanZheDetialCheckModel.h"

@implementation NYHuanZheDetailModel

+ (NSDictionary *)objectClassInArray{
    return @{@"healthExaminations" : [NYHuanZheDetialCheckModel class]};
}

@end
