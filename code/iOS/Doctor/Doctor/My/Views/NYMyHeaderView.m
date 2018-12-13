//
//  NYMyHeaderView.m
//  Doctor
//
//  Created by niuyao on 2018/12/4.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYMyHeaderView.h"

@implementation NYMyHeaderView

- (instancetype)initWithReuseIdentifier:(NSString *)reuseIdentifier
{
    if (self = [super initWithReuseIdentifier:reuseIdentifier]) {
        [self setup];
    }
    return self;
}

- (void)setup
{
    UIImageView * imgView = [[UIImageView alloc] init];
    imgView.image = [UIImage imageNamed:@"yisheng_my_bj"];
    imgView.contentMode = UIViewContentModeScaleAspectFill;
    imgView.clipsToBounds = YES;
    [self addSubview:imgView];
    imgView.sd_layout
    .leftSpaceToView(self, 0)
    .rightSpaceToView(self, 0)
    .bottomSpaceToView(self, 0)
    .topSpaceToView(self, 0);
    
    //头像
    _headerImg = [[UIImageView alloc] init];
    _headerImg.contentMode = UIViewContentModeScaleAspectFill;
    _headerImg.layer.masksToBounds = YES;
    _headerImg.clipsToBounds = YES;
    _headerImg.layer.borderColor = RGBA(47, 168, 251, 1).CGColor;
    _headerImg.layer.borderWidth = 2.5;
    _headerImg.image = [UIImage imageNamed:@"no_image"];
    [self addSubview:_headerImg];
    
    _headerImg.sd_layout
    .topSpaceToView(self, 15+64)
    .centerXEqualToView(self)
    .widthIs(70)
    .heightEqualToWidth();

    _headerImg.sd_cornerRadius = @35;
    
    //姓名
    _nameLB = [[UILabel alloc] init];
    _nameLB.font = FONT(18);
    _nameLB.textAlignment = NSTextAlignmentCenter;
    _nameLB.textColor = [UIColor whiteColor];
    [self addSubview:_nameLB];
    
    _nameLB.sd_layout
    .leftSpaceToView(self, 15)
    .topSpaceToView(_headerImg, 15)
    .rightSpaceToView(self, 15)
    .heightIs(25);

    //职称
    _typeLB = [[UILabel alloc] init];
    _typeLB.font = FONT(16);
    _typeLB.textAlignment = NSTextAlignmentCenter;
    _typeLB.textColor = [UIColor whiteColor];
    [self addSubview:_typeLB];
    
    _typeLB.sd_layout
    .leftSpaceToView(self, 15)
    .topSpaceToView(_nameLB, 15)
    .rightSpaceToView(self, 15)
    .heightIs(25);
    
    //职称
    _leaveLB = [[UILabel alloc] init];
    _leaveLB.font = FONT(16);
    _leaveLB.textAlignment = NSTextAlignmentCenter;
    _leaveLB.textColor = [UIColor whiteColor];
    [self addSubview:_leaveLB];
    
    _leaveLB.sd_layout
    .leftSpaceToView(self, 15)
    .topSpaceToView(_typeLB, 15)
    .rightSpaceToView(self, 15)
    .heightIs(25);

}
@end
