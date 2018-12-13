//
//  NYAlreadyChoiceCheckAllPriceCell.m
//  Doctor
//
//  Created by niuyao on 2018/12/10.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYAlreadyChoiceCheckAllPriceCell.h"

@implementation NYAlreadyChoiceCheckAllPriceCell

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
    
    _priceLB.text = @"预估费用合计 110.00元";
    
    _lineView = [[UIView alloc] init];
    _lineView.backgroundColor = BGCOLOR;
    [self.contentView addSubview:_lineView];
    
    _lineView.sd_layout
    .leftSpaceToView(self.contentView, 5)
    .bottomSpaceToView(self.contentView, 0)
    .rightSpaceToView(self.contentView, 5)
    .heightIs(1);

}

@end
