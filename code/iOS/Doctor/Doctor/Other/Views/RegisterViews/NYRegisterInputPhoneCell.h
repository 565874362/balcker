//
//  NYRegisterInputPhoneCell.h
//  Doctor
//
//  Created by niuyao on 2018/12/4.
//  Copyright © 2018 joymates. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface NYRegisterInputPhoneCell : UITableViewCell

@property (nonatomic,strong) HHTextField * phoneTF;

@property (nonatomic,copy) void(^clickGetCodeButton)(void);

@end

NS_ASSUME_NONNULL_END
