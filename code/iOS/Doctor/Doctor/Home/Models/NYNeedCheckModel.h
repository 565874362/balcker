//
//  NYNeedCheckModel.h
//  Doctor
//
//  Created by niuyao on 2018/12/10.
//  Copyright © 2018 joymates. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface NYNeedCheckModel : NSObject

@property (nonatomic,copy) NSString * name;
@property (nonatomic,copy) NSString * price;
@property (nonatomic,copy) NSString * sort;


@property (nonatomic,copy) NSString * id;
@property (nonatomic,copy) NSString * gmtCreate;
@property (nonatomic,copy) NSString * gmtModified;
@property (nonatomic,copy) NSString * isDel;

@property (nonatomic,assign) BOOL isSeleted;

@end

NS_ASSUME_NONNULL_END
