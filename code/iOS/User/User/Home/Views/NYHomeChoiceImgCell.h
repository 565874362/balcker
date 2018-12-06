//
//  NYHomeChoiceImgCell.h
//  User
//
//  Created by niuyao on 2018/12/6.
//  Copyright © 2018 joymates. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface NYHomeChoiceImgCell : UITableViewCell

@property (nonatomic,strong) NSArray * imageArray;

@property (nonatomic,copy) void (^clickChoiceImage)(void);

@property (nonatomic,copy) void (^clickDeleteImage)(NSInteger tag);

@end

NS_ASSUME_NONNULL_END
