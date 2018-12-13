//
//  NYAlreadyChoiceCheckTitleCell.m
//  Doctor
//
//  Created by niuyao on 2018/12/10.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYAlreadyChoiceCheckTitleCell.h"

@implementation NYAlreadyChoiceCheckTitleCell
{
    UILabel * _typeLB;
    UILabel * _priceLB;
}
- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    if (self == [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        self.selectionStyle = UITableViewCellSelectionStyleNone;
        self.contentView.backgroundColor = BGCOLOR;
        [self setup];
    }
    return self;
}

- (void)setup
{
    _typeLB = [[UILabel alloc] init];
    _typeLB.textAlignment = NSTextAlignmentLeft;
    _typeLB.adjustsFontSizeToFitWidth = YES;
    _typeLB.textColor = COLOR_BIG;
    _typeLB.font = FONT(16);
    [self.contentView addSubview:_typeLB];
    
    _typeLB.sd_layout
    .leftSpaceToView(self.contentView, 15)
    .topSpaceToView(self.contentView, 0)
    .bottomSpaceToView(self.contentView, 0);
    
    [_typeLB setSingleLineAutoResizeWithMaxWidth:NYScreenW*0.5];
    
    _typeLB.text = @"检查项目";
    
    
    _priceLB = [[UILabel alloc] init];
    _priceLB.textAlignment = NSTextAlignmentCenter;
    _priceLB.adjustsFontSizeToFitWidth = YES;
    _priceLB.textColor = COLOR_BIG;
    _priceLB.font = FONT(16);
    [self.contentView addSubview:_priceLB];
    
    _priceLB.sd_layout
    .rightSpaceToView(self.contentView, 15)
    .topSpaceToView(self.contentView, 0)
    .bottomSpaceToView(self.contentView, 0)
    .widthIs(200);
    
    _priceLB.text = @"费用";
}

@end
