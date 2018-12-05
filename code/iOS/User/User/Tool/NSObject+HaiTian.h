//
//  NSObject+HaiTian.h
//  bbkids
//
//  Created by hong tianjun on 15/4/14.
//  Copyright (c) 2015年 Haitian. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface NSObject(HaiTian)

@property (nonatomic,assign) id associateObject;

+(BOOL)isNilOrNull:(id)obj;

+ (void) performInMainThread:(void (^)(void))block;

+ (void) performInThread:(void (^)(void))block;
@end
