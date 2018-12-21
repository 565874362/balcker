//
//  NYWenZhenDetailYiZhuCell.m
//  User
//
//  Created by niuyao on 2018/12/13.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYWenZhenDetailYiZhuCell.h"
#import "NYHuanZheDetailModel.h"
#import "NYMyInfoDetailModel.h"
#import "NYHomeListModel.h"

@implementation NYWenZhenDetailYiZhuCell
{
    UILabel * _chuBuZhenDuanLB;
    UILabel * _timeLB;
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
    //初步诊断
    _chuBuZhenDuanLB = [[UILabel alloc] init];
    _chuBuZhenDuanLB.textAlignment = NSTextAlignmentLeft;
    _chuBuZhenDuanLB.numberOfLines = 0;
    _chuBuZhenDuanLB.font = FONT(16);
    _chuBuZhenDuanLB.textColor = COLOR_LOW;
    [self.contentView addSubview:_chuBuZhenDuanLB];
    
    _chuBuZhenDuanLB.sd_layout
    .leftSpaceToView(self.contentView, 15)
    .topSpaceToView(self.contentView, 15)
    .rightSpaceToView(self.contentView, 15)
    .autoHeightRatio(0);
    
    _chuBuZhenDuanLB.isAttributedContent = YES;
    
    
    //时间
    _timeLB = [[UILabel alloc] init];
    _timeLB.textAlignment = NSTextAlignmentLeft;
    _timeLB.font = FONT(15);
    _timeLB.textColor = COLOR_LOW;
    [self.contentView addSubview:_timeLB];
    
    _timeLB.sd_layout
    .leftSpaceToView(self.contentView, 15)
    .topSpaceToView(_chuBuZhenDuanLB, 30)
    .heightIs(30);
    
    [_timeLB setSingleLineAutoResizeWithMaxWidth:NYScreenW*0.5];
    
    _timeLB.text = @"2018-10-26 12:16";

    
    [self setupAutoHeightWithBottomViewsArray:@[_timeLB] bottomMargin:30];

}


//- (void)setContent:(NSString *)content
//{    
//    NSString *text = @"温馨医嘱：这几天我家宝宝嘴里起了好多小泡。哭的特别厉害也不敢吃东西，后来去诊所，医生说这是小儿疱疹性口炎，我想问一下！！！这几天我家宝宝嘴里起了好多小泡。哭的特别厉害也不敢吃东西，后来去诊所，医生说这是小儿疱疹性口炎，我想问一下！！！";
//    // 1.创建NSMutableAttributedString实例
//    NSMutableAttributedString *fontAttributeNameStr = [[NSMutableAttributedString alloc]initWithString:text];
//    
//    // 2.添加属性
//    [fontAttributeNameStr addAttribute:NSFontAttributeName value:[UIFont boldSystemFontOfSize:17] range:NSMakeRange(0, 5)];
//    [fontAttributeNameStr addAttribute:NSForegroundColorAttributeName value:COLOR_BIG range:NSMakeRange(0, 5)];
//    
//    NSMutableParagraphStyle *paragraphStyle = [[NSMutableParagraphStyle alloc] init];
//    [paragraphStyle setLineSpacing:5.0];
//    [fontAttributeNameStr addAttribute:NSParagraphStyleAttributeName value:paragraphStyle range:NSMakeRange(0, [text length])];
//
//    // 3.给label赋值
//    _chuBuZhenDuanLB.attributedText = fontAttributeNameStr;
//    
//}

- (void)setHuanZheDetailModel:(NYHuanZheDetailModel *)huanZheDetailModel
{
    _huanZheDetailModel = huanZheDetailModel;
    
    NSString *text = @"";
    if (huanZheDetailModel.info.advice.length == 0) {
        text = @"温馨医嘱：无";
    }else{
        text = [NSString stringWithFormat:@"温馨医嘱：%@",huanZheDetailModel.info.advice];
    }
    // 1.创建NSMutableAttributedString实例
    NSMutableAttributedString *fontAttributeNameStr = [[NSMutableAttributedString alloc]initWithString:text];
    
    // 2.添加属性
    [fontAttributeNameStr addAttribute:NSFontAttributeName value:[UIFont boldSystemFontOfSize:17] range:NSMakeRange(0, 5)];
    [fontAttributeNameStr addAttribute:NSForegroundColorAttributeName value:COLOR_BIG range:NSMakeRange(0, 5)];
    
    NSMutableParagraphStyle *paragraphStyle = [[NSMutableParagraphStyle alloc] init];
    [paragraphStyle setLineSpacing:5.0];
    [fontAttributeNameStr addAttribute:NSParagraphStyleAttributeName value:paragraphStyle range:NSMakeRange(0, [text length])];
    
    // 3.给label赋值
    _chuBuZhenDuanLB.attributedText = fontAttributeNameStr;

}

@end
