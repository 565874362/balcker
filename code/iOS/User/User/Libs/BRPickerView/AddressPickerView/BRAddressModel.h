//
//  BRAddressModel.h
//  BRPickerViewDemo
//
//  Created by 任波 on 2017/8/11.
//  Copyright © 2017年 renb. All rights reserved.
//

#import <Foundation/Foundation.h>

@class BRProvinceModel, BRCityModel, BRAreaModel;

/// 省
@interface BRProvinceModel : NSObject
/** 省对应的code或id */
@property (nonatomic, copy) NSString *code;
/** 省的名称 */
@property (nonatomic, copy) NSString *name;
/** 省的索引 */
@property (nonatomic, assign) NSInteger index;
/** 城市数组 */
@property (nonatomic, strong) NSArray *citylist;

@end

/// 市
@interface BRCityModel : NSObject
/** 市对应的code或id */
@property (nonatomic, copy) NSString *code;
/** 市的名称 */
@property (nonatomic, copy) NSString *name;
/** 市的索引 */
@property (nonatomic, assign) NSInteger index;
/** 地区数组 */
@property (nonatomic, strong) NSArray *arealist;

@end

/// 区
@interface BRAreaModel : NSObject
/** 区对应的code或id */
@property (nonatomic, copy) NSString *code;
/** 区的名称 */
@property (nonatomic, copy) NSString *name;
/** 区的索引 */
@property (nonatomic, assign) NSInteger index;

@end