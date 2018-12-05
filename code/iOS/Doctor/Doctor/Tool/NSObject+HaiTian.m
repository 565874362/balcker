//
//  NSObject+HaiTian.m
//  bbkids
//
//  Created by hong tianjun on 15/4/14.
//  Copyright (c) 2015年 Haitian. All rights reserved.
//

#import "NSObject+HaiTian.h"
#import <objc/runtime.h>

static const void * HHObjectUserInfoKey = &HHObjectUserInfoKey;

@implementation NSObject(HaiTian)

+(BOOL)isNilOrNull:(id)obj{
    BOOL isNull = NO;
    
    if (obj == nil || [obj isKindOfClass:[NSNull class]]) {
        isNull = YES;
    }
    else {
        if ([obj isKindOfClass:[NSString class]] || [obj isKindOfClass:[NSMutableString class]]) {
            if ([[(NSString *)obj stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]] length] == 0) {
                isNull = YES;
            }
        }
    }
    
    return isNull;
}

+ (void) performInMainThread:(void (^)(void))block {
    dispatch_async(dispatch_get_main_queue(), block);
}

+ (void) performInThread:(void (^)(void))block {
    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), block);
}

- (void)setAssociateObject:(id)associateObject {
    objc_setAssociatedObject(self, HHObjectUserInfoKey, associateObject, OBJC_ASSOCIATION_ASSIGN);
}

- (id)associateObject {
    return objc_getAssociatedObject(self,HHObjectUserInfoKey);
}
@end
