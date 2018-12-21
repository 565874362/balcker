//
//  NYHuanZheDetailModel.h
//  Doctor
//
//  Created by niuyao on 2018/12/20.
//  Copyright © 2018 joymates. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN


@class NYHomeListModel;
@class NYMyInfoDetailModel;
@class NYHuanZheDetialCheckModel;

@interface NYHuanZheDetailModel : NSObject

@property (nonatomic,strong) NYHomeListModel * info;
@property (nonatomic,strong) NYMyInfoDetailModel * doctor;
@property (nonatomic,strong) NSArray<NYHuanZheDetialCheckModel *> * healthExaminations;

@end

NS_ASSUME_NONNULL_END
