//
//  NYDoctorDetailModelBtnCell.m
//  User
//
//  Created by niuyao on 2018/12/7.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYDoctorDetailModelBtnCell.h"

@implementation NYDoctorDetailModelBtnCell

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
    UIButton * moreBtn = [UIButton buttonWithType:UIButtonTypeCustom];
    [moreBtn setBackgroundColor:[UIColor whiteColor]];
    [moreBtn setTitle:@"查看更多" forState:UIControlStateNormal];
    [moreBtn setTitleColor:MAINCOLOR forState:UIControlStateNormal];
    moreBtn.layer.cornerRadius = 5;
    moreBtn.layer.borderColor = MAINCOLOR.CGColor;
    moreBtn.layer.borderWidth = 1;
    [self.contentView addSubview:moreBtn];
    
    moreBtn.sd_layout
    .centerXEqualToView(self.contentView)
    .centerYEqualToView(self.contentView)
    .widthRatioToView(self.contentView, 0.5)
    .heightIs(50);
    
    [moreBtn addTarget:self action:@selector(clickMoreButton:) forControlEvents:UIControlEventTouchUpInside];
}

- (void)clickMoreButton:(UIButton *)button
{
    if (_clickShowModelButton) {
        _clickShowModelButton();
    }
}
@end
