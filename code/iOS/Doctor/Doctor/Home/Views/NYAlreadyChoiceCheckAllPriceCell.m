//
//  NYAlreadyChoiceCheckAllPriceCell.m
//  Doctor
//
//  Created by niuyao on 2018/12/10.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYAlreadyChoiceCheckAllPriceCell.h"
#import "NYNeedCheckModel.h"
#import "NYHuanZheDetailModel.h"
#import "NYMyInfoDetailModel.h"
#import "NYHomeListModel.h"

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
    
}

- (void)setChoiceCheckArr:(NSArray *)choiceCheckArr
{
    _choiceCheckArr = choiceCheckArr;
    
    double allPrice = 0.0;
    for (NYNeedCheckModel * model in choiceCheckArr) {
        if (model.isSeleted) {
            double pric = [model.price doubleValue];
            allPrice += pric;
        }
    }
    
    _priceLB.text = [NSString stringWithFormat:@"预估费用合计 %.2f元",allPrice];

    
    // 1.创建NSMutableAttributedString实例
    NSMutableAttributedString *fontAttributeNameStr = [[NSMutableAttributedString alloc]initWithString:_priceLB.text];
    
    // 2.添加属性
    [fontAttributeNameStr addAttribute:NSFontAttributeName value:[UIFont boldSystemFontOfSize:17] range:NSMakeRange(6, _priceLB.text.length-6)];
    [fontAttributeNameStr addAttribute:NSForegroundColorAttributeName value:COLOR_RED range:NSMakeRange(6, _priceLB.text.length-6)];
    
    // 3.给label赋值
    _priceLB.attributedText = fontAttributeNameStr;

    
}

- (void)setHuanZheDetailModel:(NYHuanZheDetailModel *)huanZheDetailModel
{
    _huanZheDetailModel = huanZheDetailModel;
    
    _priceLB.text = [NSString stringWithFormat:@"预估费用合计 %.2f元",[huanZheDetailModel.info.exaFee doubleValue]];
    
    // 1.创建NSMutableAttributedString实例
    NSMutableAttributedString *fontAttributeNameStr = [[NSMutableAttributedString alloc]initWithString:_priceLB.text];
    
    // 2.添加属性
    [fontAttributeNameStr addAttribute:NSFontAttributeName value:[UIFont boldSystemFontOfSize:17] range:NSMakeRange(6, _priceLB.text.length-6)];
    [fontAttributeNameStr addAttribute:NSForegroundColorAttributeName value:COLOR_RED range:NSMakeRange(6, _priceLB.text.length-6)];
    
    // 3.给label赋值
    _priceLB.attributedText = fontAttributeNameStr;

}

@end
