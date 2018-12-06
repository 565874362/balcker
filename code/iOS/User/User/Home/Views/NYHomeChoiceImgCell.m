//
//  NYHomeChoiceImgCell.m
//  User
//
//  Created by niuyao on 2018/12/6.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYHomeChoiceImgCell.h"

@implementation NYHomeChoiceImgCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    if (self == [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        self.selectionStyle = UITableViewCellSelectionStyleNone;
    }
    return self;
}

- (void)setImageArray:(NSArray *)imageArray
{
    _imageArray = imageArray;
    
    for (id obj in self.contentView.subviews) {
        if ([obj isKindOfClass:[UIView class]]) {
            [obj removeFromSuperview];
        }
    }
    
    CGFloat PIC_WIDTH = (NYScreenW-(15*4))*0.33;
    CGFloat PIC_HEIGHT = (NYScreenW-(15*4))*0.33;
    NSInteger COL_COUNT = 3;
    // 间距
    CGFloat margin = 15;
    
    for (int i = 0; i < imageArray.count; i++) {
        if (i == 9) {
            return;
        }
        // 图片所在行
        NSInteger row = i / COL_COUNT;
        // 图片所在列
        NSInteger col = i % COL_COUNT;
        // PointX
        CGFloat picX = 15 + (PIC_WIDTH + margin) * col;
        // PointY
        CGFloat picY = (PIC_HEIGHT + margin) * row + margin;
        
        UIView * bgView = [[UIView alloc] init];
        bgView.frame = CGRectMake(picX, picY, PIC_WIDTH, PIC_HEIGHT);
        [self.contentView addSubview:bgView];
        
        UIImageView * imageView = [[UIImageView alloc] init];
        imageView.contentMode = UIViewContentModeScaleAspectFill;
        imageView.clipsToBounds = YES;
        imageView.frame = CGRectMake(0, 0, PIC_WIDTH, PIC_HEIGHT);
        imageView.image = imageArray[i];
        [bgView addSubview:imageView];
        
        if (i == imageArray.count-1) {
            imageView.userInteractionEnabled = YES;
            [imageView addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(clickAddImageView:)]];
        }else{
            UIButton * deleteBtn = [UIButton buttonWithType:UIButtonTypeCustom];
            deleteBtn.tag = i;
            deleteBtn.frame = CGRectMake(PIC_WIDTH-30, 5, 25, 25);
            [bgView addSubview:deleteBtn];
            [deleteBtn setBackgroundImage:[UIImage imageNamed:@"Common_Close_gray_transparent_n"] forState:UIControlStateNormal];
            [deleteBtn addTarget:self action:@selector(clickDeleteBtn:) forControlEvents:UIControlEventTouchUpInside];
        }
    }
}

#pragma mark - 点击选择照片
- (void)clickAddImageView:(UITapGestureRecognizer *)tap
{
    if (_clickChoiceImage) {
        _clickChoiceImage();
    }
}

#pragma mark - 点击删除按钮
- (void)clickDeleteBtn:(UIButton *)button
{
    if (_clickDeleteImage) {
        _clickDeleteImage(button.tag);
    }
}

@end
