//
//  NYRegisterUploadCardCell.m
//  Doctor
//
//  Created by niuyao on 2018/12/4.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYRegisterUploadCardCell.h"
#import "UIImage+ZDExtension.h"

@implementation NYRegisterUploadCardCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    if (self == [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        self.selectionStyle = UITableViewCellSelectionStyleNone;
        [self setup];
    }
    return self;
}

- (void)setup
{
    _pictureIMG1 = [[UIImageView alloc] init];
    _pictureIMG1.image = [UIImage imageNamed:@"placeholderImage"];
    _pictureIMG1.contentMode = UIViewContentModeScaleAspectFill;
    _pictureIMG1.clipsToBounds = YES;
    [self.contentView addSubview:_pictureIMG1];
    _pictureIMG1.sd_layout
    .leftSpaceToView(self.contentView, 15)
    .topSpaceToView(self.contentView, 20)
    .widthIs((NYScreenW-45)*0.5)
    .heightIs(150);
    
    _pictureIMG1.sd_cornerRadius = @5;
    
    
    UIView * bottomV = [[UIView alloc] initWithFrame:CGRectMake(15, 190, (NYScreenW-45)*0.5, 50)];
    bottomV.layer.cornerRadius = 3;
    bottomV.clipsToBounds = YES;
    bottomV.backgroundColor = [UIColor whiteColor];
    [self.contentView addSubview:bottomV];
    
    
    [self addBorderToLayer:bottomV];
    
    //点击按钮
    UIButton * addBtn = [UIButton buttonWithType:UIButtonTypeCustom];
    [addBtn setTitleColor:COLOR_LOW forState:UIControlStateNormal];
    [addBtn setTitle:@"+上传身份证正面照" forState:UIControlStateNormal];
    addBtn.titleLabel.font = FONT(15);
    [bottomV addSubview:addBtn];
    
    addBtn.sd_layout
    .leftSpaceToView(bottomV, 5)
    .topSpaceToView(bottomV, 5)
    .rightSpaceToView(bottomV, 5)
    .bottomSpaceToView(bottomV, 5);
    
    [addBtn addTarget:self action:@selector(clickAddCarButton1:) forControlEvents:UIControlEventTouchUpInside];
    
    
    
    
    _pictureIMG2 = [[UIImageView alloc] init];
    _pictureIMG2.image = [UIImage imageNamed:@"placeholderImage"];
    _pictureIMG2.contentMode = UIViewContentModeScaleAspectFill;
    _pictureIMG2.clipsToBounds = YES;
    [self.contentView addSubview:_pictureIMG2];
    _pictureIMG2.sd_layout
    .leftSpaceToView(_pictureIMG1, 15)
    .topSpaceToView(self.contentView, 20)
    .rightSpaceToView(self.contentView, 15)
    .heightIs(150);
    
    _pictureIMG2.sd_cornerRadius = @5;
    
    
    UIView * bottomV2 = [[UIView alloc] initWithFrame:CGRectMake(15+(NYScreenW-45)*0.5+15, 190, (NYScreenW-45)*0.5, 50)];
    bottomV2.layer.cornerRadius = 3;
    bottomV2.clipsToBounds = YES;
    bottomV2.backgroundColor = [UIColor whiteColor];
    [self.contentView addSubview:bottomV2];
    
    
    [self addBorderToLayer:bottomV2];
    
    //点击按钮
    UIButton * addBtn2 = [UIButton buttonWithType:UIButtonTypeCustom];
    [addBtn2 setTitleColor:COLOR_LOW forState:UIControlStateNormal];
    [addBtn2 setTitle:@"+上传身份证反面照" forState:UIControlStateNormal];
    addBtn2.titleLabel.font = FONT(15);
    [bottomV2 addSubview:addBtn2];
    
    addBtn2.sd_layout
    .leftSpaceToView(bottomV2, 5)
    .topSpaceToView(bottomV2, 5)
    .rightSpaceToView(bottomV2, 5)
    .bottomSpaceToView(bottomV2, 5);
    
    [addBtn2 addTarget:self action:@selector(clickAddCarButton2:) forControlEvents:UIControlEventTouchUpInside];

}

- (void)clickAddCarButton1:(UIButton *)button
{
    if (_clickChoicePictureBtn1) {
        _clickChoicePictureBtn1();
    }
}
- (void)clickAddCarButton2:(UIButton *)button
{
    if (_clickChoicePictureBtn2) {
        _clickChoicePictureBtn2();
    }
}

#pragma mark - view边框加虚线
- (void)addBorderToLayer:(UIView *)view
{
    CAShapeLayer *border = [CAShapeLayer layer];
    //  线条颜色
    border.strokeColor = COLOR_LOW.CGColor;
    
    border.fillColor = nil;
    
    border.path = [UIBezierPath bezierPathWithRect:view.bounds].CGPath;
    
    border.frame = view.bounds;
    
    // 不要设太大 不然看不出效果
    border.lineWidth = 1;
    
    border.lineCap = @"square";
    
    //  第一个是 线条长度   第二个是间距    nil时为实线
    border.lineDashPattern = @[@4, @4];
    [view.layer addSublayer:border];
}

@end
