//
//  NYAlreadyChoiceCheckCell.m
//  Doctor
//
//  Created by niuyao on 2018/12/10.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYAlreadyChoiceCheckCell.h"
#import "NYHuanZheDetialCheckModel.h"

@implementation NYAlreadyChoiceCheckCell

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
    
    _typeLB.text = @"口腔检查";
    
    
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
    
    _priceLB.text = @"25.00元";

    _lineView = [[UIView alloc] init];
    _lineView.backgroundColor = BGCOLOR;
    [self.contentView addSubview:_lineView];
    
    _lineView.sd_layout
    .leftSpaceToView(self.contentView, 5)
    .bottomSpaceToView(self.contentView, 0)
    .rightSpaceToView(self.contentView, 5)
    .heightIs(1);
    
}

- (void)setCheckModel:(NYHuanZheDetialCheckModel *)checkModel
{
    _checkModel = checkModel;
    
    _typeLB.text = checkModel.name;
    
    _priceLB.text = [NSString stringWithFormat:@"%.2f元",[checkModel.price doubleValue]];
    
}
@end
