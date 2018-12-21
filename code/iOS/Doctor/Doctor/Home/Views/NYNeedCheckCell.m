//
//  NYNeedCheckCell.m
//  Doctor
//
//  Created by niuyao on 2018/12/10.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYNeedCheckCell.h"
#import "NYNeedCheckModel.h"

@implementation NYNeedCheckCell
{
    UIImageView * _leftIMG;
    UILabel * _typeLB;
    UILabel * _priceLB;
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
    _leftIMG = [[UIImageView alloc] init];
    _leftIMG.contentMode = UIViewContentModeScaleAspectFill;
    _leftIMG.image = [UIImage imageNamed:@"radio"];
    _leftIMG.clipsToBounds = YES;
    [self.contentView addSubview:_leftIMG];
    
    _leftIMG.sd_layout
    .leftSpaceToView(self.contentView, 20)
    .centerYEqualToView(self.contentView)
    .widthIs(20)
    .heightEqualToWidth();
    
    _typeLB = [[UILabel alloc] init];
    _typeLB.textAlignment = NSTextAlignmentLeft;
    _typeLB.adjustsFontSizeToFitWidth = YES;
    _typeLB.textColor = COLOR_BIG;
    _typeLB.font = FONT(16);
    [self.contentView addSubview:_typeLB];
    
    _typeLB.sd_layout
    .leftSpaceToView(_leftIMG, 10)
    .topSpaceToView(self.contentView, 0)
    .bottomSpaceToView(self.contentView, 0);
    
    [_typeLB setSingleLineAutoResizeWithMaxWidth:NYScreenW*0.5];
    
    _typeLB.text = @"口腔检查";
    
    _priceLB = [[UILabel alloc] init];
    _priceLB.textAlignment = NSTextAlignmentRight;
    _priceLB.adjustsFontSizeToFitWidth = YES;
    _priceLB.textColor = COLOR_BIG;
    _priceLB.font = FONT(16);
    [self.contentView addSubview:_priceLB];
    
    _priceLB.sd_layout
    .rightSpaceToView(self.contentView, 15)
    .topSpaceToView(self.contentView, 0)
    .bottomSpaceToView(self.contentView, 0);
    
    [_priceLB setSingleLineAutoResizeWithMaxWidth:NYScreenW*0.3];
    
    _priceLB.text = @"25.00元";
}

- (void)setCheckModel:(NYNeedCheckModel *)checkModel
{
    _checkModel = checkModel;
    
    _typeLB.text = checkModel.name;
    
    _priceLB.text = [NSString stringWithFormat:@"%.2f元",[checkModel.price doubleValue]];
    
    if (checkModel.isSeleted) {
        _leftIMG.image = [UIImage imageNamed:@"register_pact_hover"];
    }else{
        _leftIMG.image = [UIImage imageNamed:@"radio"];
    }
}
@end
