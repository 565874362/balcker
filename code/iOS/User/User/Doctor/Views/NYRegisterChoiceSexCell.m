//
//  NYRegisterChoiceSexCell.m
//  Doctor
//
//  Created by niuyao on 2018/12/4.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYRegisterChoiceSexCell.h"
#import "RankButton.h"

@implementation NYRegisterChoiceSexCell
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
    UILabel * typeLB = [[UILabel alloc] init];
    typeLB.textColor = COLOR_BIG;
    typeLB.textAlignment = NSTextAlignmentLeft;
    [self.contentView addSubview:typeLB];
    
    typeLB.sd_layout
    .leftSpaceToView(self.contentView, 15)
    .topSpaceToView(self.contentView, 0)
    .bottomSpaceToView(self.contentView, 0)
    .widthIs(80);
    
    typeLB.text = @"性别";
 
    //女
    RankButton * womanBtn = [RankButton buttonWithType:UIButtonTypeCustom];
    _womanBtn = womanBtn;
    [self.contentView addSubview:womanBtn];
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
    .leftSpaceToView(typeLB, 10)
    .centerYEqualToView(self.contentView)
    .heightIs(30)
    .widthIs(60);
    [womanBtn addTarget:self action:@selector(clickWomanButton:) forControlEvents:UIControlEventTouchUpInside];

    
    //男
    RankButton * manBtn = [RankButton buttonWithType:UIButtonTypeCustom];
    _manBtn = manBtn;
    [self.contentView addSubview:manBtn];
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
    .widthIs(60);
    [manBtn addTarget:self action:@selector(clickManButton:) forControlEvents:UIControlEventTouchUpInside];

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
