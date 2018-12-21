//
//  NYHomeHuanZheInfoImgCell.h
//  Doctor
//
//  Created by niuyao on 2018/12/5.
//  Copyright © 2018 joymates. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface NYHomeHuanZheInfoImgCell : UITableViewCell

@property (nonatomic,copy) void(^clickImg)(NSInteger index,UIImageView *imageView);

@property (nonatomic,strong) NSArray * imageArray;
@end

NS_ASSUME_NONNULL_END
