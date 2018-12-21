//
//  NYWenZhenDetailTopCell.m
//  User
//
//  Created by niuyao on 2018/12/12.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYWenZhenDetailTopCell.h"
#import "NYHuanZheDetailModel.h"
#import "NYMyInfoDetailModel.h"
#import "NYHomeListModel.h"


@interface NYWenZhenDetailTopCell ()
{
    UILabel * _chuBuZhenDuanLB; //初步诊断
    UILabel * _needCheckTitleLB; //需做检查标题
}
@end

@implementation NYWenZhenDetailTopCell

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
    
    
    //需要做的检查
    _needCheckTitleLB = [[UILabel alloc] init];
    _needCheckTitleLB.textAlignment = NSTextAlignmentLeft;
    _needCheckTitleLB.font = FONT(16);
    _needCheckTitleLB.textColor = COLOR_LOW;
    [self.contentView addSubview:_needCheckTitleLB];
    
    _needCheckTitleLB.sd_layout
    .leftSpaceToView(self.contentView, 15)
    .topSpaceToView(_chuBuZhenDuanLB, 15)
    .heightIs(30)
    .widthIs(200);

    
    [self setupAutoHeightWithBottomView:_needCheckTitleLB bottomMargin:15];
    
    
}

//- (void)setContent:(NSString *)content
//{
//    NSString *text = @"初步诊断：这几天我家宝宝嘴里起了好多小泡。哭的特别厉害也不敢吃东西，后来去诊所，医生说这是小儿疱疹性口炎，我想问一下！！！这几天我家宝宝嘴里起了好多小泡。哭的特别厉害也不敢吃东西，后来去诊所，医生说这是小儿疱疹性口炎，我想问一下！！！";
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
//
//    // 3.给label赋值
//    _chuBuZhenDuanLB.attributedText = fontAttributeNameStr;
//
//}

- (void)setHuanZheDetailModel:(NYHuanZheDetailModel *)huanZheDetailModel
{
    _huanZheDetailModel = huanZheDetailModel;
    
    //初步诊断
    NSString *text = @"";
    if (huanZheDetailModel.info.response.length == 0) {
        text = @"初步诊断：无";
    }else{
        text = [NSString stringWithFormat:@"初步诊断：%@",huanZheDetailModel.info.response];
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

    
    
    
    NSString * checkStr = @"";
    if (huanZheDetailModel.healthExaminations.count == 0) {
        checkStr = @"需做检查：无";
    }else{
        checkStr = @"需做检查：";
    }
    
    // 1.创建NSMutableAttributedString实例
    NSMutableAttributedString *fontAttributeNameStr2 = [[NSMutableAttributedString alloc]initWithString:checkStr];
    
    // 2.添加属性
    [fontAttributeNameStr2 addAttribute:NSFontAttributeName value:[UIFont boldSystemFontOfSize:17] range:NSMakeRange(0, 5)];
    [fontAttributeNameStr2 addAttribute:NSForegroundColorAttributeName value:COLOR_BIG range:NSMakeRange(0, 5)];
    
    NSMutableParagraphStyle *paragraphStyle2 = [[NSMutableParagraphStyle alloc] init];
    [paragraphStyle2 setLineSpacing:5.0];
    [fontAttributeNameStr2 addAttribute:NSParagraphStyleAttributeName value:paragraphStyle2 range:NSMakeRange(0, [checkStr length])];
    
    
    // 3.给label赋值
    _needCheckTitleLB.attributedText = fontAttributeNameStr2;
}

@end
