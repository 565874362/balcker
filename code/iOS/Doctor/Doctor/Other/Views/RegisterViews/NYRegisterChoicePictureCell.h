//
//  NYRegisterChoicePictureCell.h
//  Doctor
//
//  Created by niuyao on 2018/12/4.
//  Copyright © 2018 joymates. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface NYRegisterChoicePictureCell : UITableViewCell

@property (nonatomic,strong) UIImageView * pictureIMG;

@property (nonatomic,copy) void(^clickChoicePictureImg)(void);

@end

NS_ASSUME_NONNULL_END
