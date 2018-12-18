//
//  NYRegisterInputPhoneCell.m
//  Doctor
//
//  Created by niuyao on 2018/12/4.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYRegisterInputPhoneCell.h"

@implementation NYRegisterInputPhoneCell

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
    UILabel * typeLB = [[UILabel alloc] init];
    typeLB.textColor = COLOR_BIG;
    typeLB.textAlignment = NSTextAlignmentLeft;
    [self.contentView addSubview:typeLB];
    
    typeLB.sd_layout
    .leftSpaceToView(self.contentView, 15)
    .topSpaceToView(self.contentView, 0)
    .bottomSpaceToView(self.contentView, 0)
    .widthIs(80);
    
    typeLB.text = @"手机号";

    
    //获取验证码
    JKCountDownButton * codeButton = [JKCountDownButton buttonWithType:UIButtonTypeCustom];
    self.codeButton = codeButton;
    [codeButton setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
    codeButton.titleLabel.font = FONT(13);
    [codeButton setTitle:@"获取验证码" forState:UIControlStateNormal];
    [self.contentView addSubview:codeButton];
    codeButton.sd_layout
    .centerYEqualToView(self.contentView)
    .rightSpaceToView(self.contentView, 10)
    .heightIs(35)
    .widthIs(80);
//    [codeButton setupAutoSizeWithHorizontalPadding:10 buttonHeight:35];
    [codeButton addTarget:self action:@selector(sendCode:) forControlEvents:(UIControlEventTouchUpInside)];
    
    
    
    //竖线
    UIView * lineView = [[UIView alloc] init];
    lineView.backgroundColor = [UIColor blackColor];
    [self.contentView addSubview:lineView];
    lineView.sd_layout
    .centerYEqualToView(self.contentView)
    .widthIs(1.5)
    .heightIs(15)
    .rightSpaceToView(codeButton, 5);
    
    //手机号输入框
    HHTextField * phoneTF = [[HHTextField alloc] init];
    phoneTF.clearButtonMode = UITextFieldViewModeWhileEditing;
    phoneTF.keyboardType = UIKeyboardTypeNumberPad;
    _phoneTF = phoneTF;
    [self.contentView addSubview:phoneTF];
    phoneTF.sd_layout
    .leftSpaceToView(typeLB, 10)
    .topSpaceToView(self.contentView, 0)
    .rightSpaceToView(lineView, 5)
    .bottomSpaceToView(self.contentView, 0);
    phoneTF.placeholder = @"请输入手机号";

}

#pragma mark - 获取验证码
- (void)sendCode:(JKCountDownButton *)button
{
    if (_clickGetCodeButton) {
        _clickGetCodeButton();
    }
}

@end
