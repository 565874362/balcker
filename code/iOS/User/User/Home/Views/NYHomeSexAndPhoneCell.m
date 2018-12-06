//
//  NYHomeSexAndPhoneCell.m
//  User
//
//  Created by niuyao on 2018/12/6.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYHomeSexAndPhoneCell.h"
#import "RankButton.h"

@implementation NYHomeSexAndPhoneCell
{
    RankButton * _manBtn;
    RankButton * _womanBtn;
}

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
    UIView * bgView1 = [[UIView alloc] init];
    bgView1.backgroundColor = [UIColor whiteColor];
    [self.contentView addSubview:bgView1];
    
    bgView1.sd_layout
    .leftSpaceToView(self.contentView, 0)
    .topSpaceToView(self.contentView, 0)
    .bottomSpaceToView(self.contentView, 0)
    .widthRatioToView(self.contentView, 0.5);
    
    
    UILabel * sexLB = [[UILabel alloc] init];
    sexLB.textColor = COLOR_BIG;
    sexLB.textAlignment = NSTextAlignmentLeft;
    [bgView1 addSubview:sexLB];
    
    sexLB.sd_layout
    .leftSpaceToView(bgView1, 15)
    .topSpaceToView(bgView1, 0)
    .bottomSpaceToView(bgView1, 0)
    .widthIs(50);
    
    sexLB.text = @"性别";
    
    
    //女
    RankButton * womanBtn = [RankButton buttonWithType:UIButtonTypeCustom];
    _womanBtn = womanBtn;
    [bgView1 addSubview:womanBtn];
    womanBtn.type = buttonTypeNormal;
    womanBtn.alignType = buttonAlignWithPic;
    womanBtn.picTileRange = 3;
    womanBtn.picToViewRange = 0;
    [womanBtn setTitle:@"女" forState:UIControlStateNormal];
    [womanBtn setImage:[UIImage imageNamed:@"register_pact"] forState:UIControlStateNormal];
    [womanBtn setImage:[UIImage imageNamed:@"register_pact_hover"] forState:UIControlStateSelected];
    womanBtn.selected = YES;
    
    womanBtn.titleLabel.font = [UIFont systemFontOfSize:17];
    womanBtn.titleLabel.adjustsFontSizeToFitWidth = YES;
    womanBtn.sd_layout
    .leftSpaceToView(sexLB, 5)
    .centerYEqualToView(self.contentView)
    .heightIs(30)
    .widthIs(50);
    [womanBtn addTarget:self action:@selector(clickWomanButton:) forControlEvents:UIControlEventTouchUpInside];
    
    
    //男
    RankButton * manBtn = [RankButton buttonWithType:UIButtonTypeCustom];
    _manBtn = manBtn;
    [bgView1 addSubview:manBtn];
    manBtn.type = buttonTypeNormal;
    manBtn.alignType = buttonAlignWithPic;
    manBtn.picTileRange = 3;
    manBtn.picToViewRange = 0;
    [manBtn setTitle:@"男" forState:UIControlStateNormal];
    [manBtn setImage:[UIImage imageNamed:@"register_pact"] forState:UIControlStateNormal];
    [manBtn setImage:[UIImage imageNamed:@"register_pact_hover"] forState:UIControlStateSelected];
    
    manBtn.titleLabel.font = [UIFont systemFontOfSize:16];
    manBtn.titleLabel.adjustsFontSizeToFitWidth = YES;
    manBtn.sd_layout
    .leftSpaceToView(womanBtn, 10)
    .centerYEqualToView(self.contentView)
    .heightIs(30)
    .widthIs(50);
    [manBtn addTarget:self action:@selector(clickManButton:) forControlEvents:UIControlEventTouchUpInside];

    //电话
    
    UIView * bgView2 = [[UIView alloc] init];
    bgView2.backgroundColor = [UIColor whiteColor];
    [self.contentView addSubview:bgView2];
    
    bgView2.sd_layout
    .leftSpaceToView(bgView1, 0)
    .topSpaceToView(self.contentView, 0)
    .bottomSpaceToView(self.contentView, 0)
    .widthRatioToView(self.contentView, 0.5);
    
    //电话
    UILabel * phoneLB = [[UILabel alloc] init];
    phoneLB.textColor = COLOR_BIG;
    phoneLB.textAlignment = NSTextAlignmentLeft;
    [bgView2 addSubview:phoneLB];
    
    phoneLB.sd_layout
    .leftSpaceToView(bgView2, 5)
    .topSpaceToView(bgView2, 0)
    .bottomSpaceToView(bgView2, 0)
    .widthIs(50);
    
    phoneLB.text = @"电话";
    
    //电话输入
    _phoneTF = [[HHTextField alloc] init];
    _phoneTF.returnKeyType = UIReturnKeyDone;
    _phoneTF.keyboardType = UIKeyboardTypeNumberPad;
    _phoneTF.textColor = COLOR_BIG;
    [bgView2 addSubview:_phoneTF];
    
    _phoneTF.sd_layout
    .leftSpaceToView(phoneLB, 5)
    .topSpaceToView(bgView2, 0)
    .rightSpaceToView(bgView2, 5)
    .bottomSpaceToView(bgView2, 0);
    
    _phoneTF.placeholder = @"联系电话";
}

- (void)clickWomanButton:(RankButton *)button
{
    _manBtn.selected = NO;
    if (_clickWomanButton) {
        _clickWomanButton(button);
    }
}

- (void)clickManButton:(RankButton *)button
{
    _womanBtn.selected = NO;
    if (_clickManButton) {
        _clickManButton(button);
    }
}

@end
