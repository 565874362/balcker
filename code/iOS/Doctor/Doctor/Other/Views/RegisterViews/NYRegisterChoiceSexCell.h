//
//  NYRegisterChoiceSexCell.h
//  Doctor
//
//  Created by niuyao on 2018/12/4.
//  Copyright © 2018 joymates. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@class RankButton;
@interface NYRegisterChoiceSexCell : UITableViewCell

@property (nonatomic,copy) void(^clickWomanButton)(RankButton * setBtn);

@property (nonatomic,copy) void(^clickManButton)(RankButton * setBtn);

@property (nonatomic,strong) RankButton * manBtn;
@property (nonatomic,strong) RankButton * womanBtn;

@end

NS_ASSUME_NONNULL_END
