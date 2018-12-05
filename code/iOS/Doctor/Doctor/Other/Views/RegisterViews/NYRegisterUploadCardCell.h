//
//  NYRegisterUploadCardCell.h
//  Doctor
//
//  Created by niuyao on 2018/12/4.
//  Copyright © 2018 joymates. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface NYRegisterUploadCardCell : UITableViewCell

@property (nonatomic,strong) UIImageView * pictureIMG1;

@property (nonatomic,copy) void(^clickChoicePictureBtn1)(void);

@property (nonatomic,strong) UIImageView * pictureIMG2;

@property (nonatomic,copy) void(^clickChoicePictureBtn2)(void);

@end

NS_ASSUME_NONNULL_END
