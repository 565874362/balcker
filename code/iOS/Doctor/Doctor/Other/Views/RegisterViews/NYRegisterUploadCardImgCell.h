//
//  NYRegisterUploadCardImgCell.h
//  Doctor
//
//  Created by niuyao on 2018/12/4.
//  Copyright © 2018 joymates. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface NYRegisterUploadCardImgCell : UITableViewCell

@property (nonatomic,strong) UIImageView * pictureIMG;

@property (nonatomic,copy) void(^clickChoicePictureBtn)(void);

@end

NS_ASSUME_NONNULL_END