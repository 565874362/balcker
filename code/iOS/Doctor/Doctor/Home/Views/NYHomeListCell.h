//
//  NYHomeListCell.h
//  Doctor
//
//  Created by niuyao on 2018/12/3.
//  Copyright © 2018 joymates. All rights reserved.
//

#import <UIKit/UIKit.h>

//NS_ASSUME_NONNULL_BEGIN

@class NYHomeListModel;
@class NYMyJieZhenModel;
@interface NYHomeListCell : UITableViewCell

@property(nonatomic,strong) UILabel * stateLB;
@property(nonatomic,strong) UIView * pointView;


@property (nonatomic,strong) NYHomeListModel * homeListModel;

@property (nonatomic,strong) NYMyJieZhenModel * jieZhenModel;
@end

//NS_ASSUME_NONNULL_END
