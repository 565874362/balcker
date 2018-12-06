//
//  NYHomeSexAndPhoneCell.h
//  User
//
//  Created by niuyao on 2018/12/6.
//  Copyright © 2018 joymates. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN
@class RankButton;

@interface NYHomeSexAndPhoneCell : UITableViewCell

@property (nonatomic,strong) HHTextField * phoneTF;

@property (nonatomic,copy) void(^clickWomanButton)(RankButton * setBtn);

@property (nonatomic,copy) void(^clickManButton)(RankButton * setBtn);

@end

NS_ASSUME_NONNULL_END
