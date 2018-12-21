//
//  NYWenZhenDetailYiZhuCell.m
//  User
//
//  Created by niuyao on 2018/12/13.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYWenZhenDetailYiZhuCell.h"
#import "NYHuanZheDetailModel.h"
#import "NYDoctorModel.h"
#import "NYMyWenZhenModel.h"

@implementation NYWenZhenDetailYiZhuCell
{
    UILabel * _chuBuZhenDuanLB;
    UILabel * _timeLB;
    UIButton * _jiuZhenBtn;
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
    
    //就诊
    UIButton * jiuZhenButton = [UIButton buttonWithType:UIButtonTypeCustom];
    _jiuZhenBtn = jiuZhenButton;
    [jiuZhenButton setTitle:@"就诊(0.00元)" forState:UIControlStateNormal];
    jiuZhenButton.titleLabel.font = FONT(15);
    [jiuZhenButton setBackgroundColor:MAINCOLOR];
    [jiuZhenButton setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    [self.contentView addSubview:jiuZhenButton];
    
    jiuZhenButton.sd_layout
    .centerYEqualToView(_timeLB)
    .rightSpaceToView(self.contentView, 15)
    .heightIs(35);
    
    jiuZhenButton.sd_cornerRadius = @5;
    jiuZhenButton.layer.borderColor = MAINCOLOR.CGColor;
    jiuZhenButton.layer.borderWidth = 1;
    [jiuZhenButton setupAutoSizeWithHorizontalPadding:5 buttonHeight:35];
    
    [jiuZhenButton addTarget:self action:@selector(clickJiuZhenButton:) forControlEvents:UIControlEventTouchUpInside];

    //咨询
    UIButton * doneButton = [UIButton buttonWithType:UIButtonTypeCustom];
    [doneButton setTitle:@"咨询" forState:UIControlStateNormal];
    doneButton.titleLabel.font = FONT(15);
//    [doneButton setBackgroundColor:[UIColor whiteColor]];
    [doneButton setTitleColor:COLOR_RED forState:UIControlStateNormal];
    [self.contentView addSubview:doneButton];
    
    doneButton.sd_layout
    .rightSpaceToView(jiuZhenButton, 10)
    .centerYEqualToView(jiuZhenButton)
    .heightIs(35)
    .widthIs(80);
    
    doneButton.sd_cornerRadius = @5;
    doneButton.layer.borderColor = COLOR_RED.CGColor;
    doneButton.layer.borderWidth = 1;
//    [doneButton setupAutoSizeWithHorizontalPadding:15 buttonHeight:35];
    
    [doneButton addTarget:self action:@selector(clickZiXunButton:) forControlEvents:UIControlEventTouchUpInside];
    

    
    [self setupAutoHeightWithBottomViewsArray:@[_timeLB,jiuZhenButton,doneButton] bottomMargin:30];

}

#pragma mark - 点击就诊
- (void)clickJiuZhenButton:(UIButton *)button
{
    if (_clickJiuZhenBlock) {
        _clickJiuZhenBlock();
    }
}

#pragma mark - 点击咨询
- (void)clickZiXunButton:(UIButton *)button
{
    if (_clickZiXunBlock) {
        _clickZiXunBlock();
    }
}

- (void)setContent:(NSString *)content
{    
    NSString *text = @"温馨医嘱：这几天我家宝宝嘴里起了好多小泡。哭的特别厉害也不敢吃东西，后来去诊所，医生说这是小儿疱疹性口炎，我想问一下！！！这几天我家宝宝嘴里起了好多小泡。哭的特别厉害也不敢吃东西，后来去诊所，医生说这是小儿疱疹性口炎，我想问一下！！！";
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

    [_jiuZhenBtn setTitle:[NSString stringWithFormat:@"就诊(%.2f元)",[huanZheDetailModel.doctor.registrationFee doubleValue]] forState:UIControlStateNormal];

}

@end
