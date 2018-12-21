//
//  NYAlreadyChoiceCheckCell.h
//  Doctor
//
//  Created by niuyao on 2018/12/10.
//  Copyright © 2018 joymates. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@class NYNeedCheckModel;

@interface NYAlreadyChoiceCheckCell : UITableViewCell

@property (nonatomic,strong) UILabel * typeLB;

@property (nonatomic,strong) UILabel * priceLB;

@property (nonatomic,strong) UIView * lineView;

@property (nonatomic,strong) NYNeedCheckModel * checkModel;

@end

NS_ASSUME_NONNULL_END
