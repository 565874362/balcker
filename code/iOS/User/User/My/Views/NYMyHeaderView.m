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
    imgView.image = [UIImage imageNamed:@"huanzhe_my_bj"];
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
    _headerImg.userInteractionEnabled = YES;
    _headerImg.layer.masksToBounds = YES;
    _headerImg.clipsToBounds = YES;
    _headerImg.layer.borderColor = RGBA(48, 167, 251, 1).CGColor;
    _headerImg.layer.borderWidth = 2.5;
    _headerImg.image = [UIImage imageNamed:@"no_image"];
    [self addSubview:_headerImg];
    
    _headerImg.sd_layout
    .topSpaceToView(self, 20+64)
    .centerXEqualToView(self)
    .widthIs(80)
    .heightEqualToWidth();

    _headerImg.sd_cornerRadius = @40;
    
    [_headerImg addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(clickHeaderImg:)]];
    
}

- (void)clickHeaderImg:(UITapGestureRecognizer *)tap
{
    if (_clickHeader) {
        _clickHeader();
    }
}

@end
