//
//  NYWenZhenDetailTopCell.m
//  User
//
//  Created by niuyao on 2018/12/12.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYWenZhenDetailTopCell.h"

@interface NYWenZhenDetailTopCell ()
{
    UILabel * _suggestLB; //建议
    UIImageView * _headerImg; //头像
    UILabel * _nameLB;
    UILabel * _zhiChengLB; //职称
    UILabel * _yiyuanLB;
    UILabel * _keshiLB;
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
    //医生建议
    _suggestLB = [[UILabel alloc] init];
    _suggestLB.backgroundColor = BGCOLOR;
    _suggestLB.textColor = COLOR_BIG;
    _suggestLB.numberOfLines = 2;
    _suggestLB.textAlignment = NSTextAlignmentCenter;
    [self.contentView addSubview:_suggestLB];
    
    _suggestLB.sd_layout
    .leftSpaceToView(self.contentView, 0)
    .topSpaceToView(self.contentView, 0)
    .rightSpaceToView(self.contentView, 0)
    .heightIs(60);
    
    _suggestLB.text = @"医生回答仅为建议,有疑问请前往医院就诊";
    
    //头像
    _headerImg = [[UIImageView alloc] init];
    _headerImg.image = [UIImage imageNamed:@"placeholderImage"];
    _headerImg.clipsToBounds = YES;
    [self.contentView addSubview:_headerImg];
    
    _headerImg.sd_layout
    .leftSpaceToView(self.contentView, 15)
    .topSpaceToView(_suggestLB, 20)
    .widthIs(70)
    .heightEqualToWidth();
    
    _headerImg.sd_cornerRadius = @35;
    
    //姓名
    _nameLB = [[UILabel alloc] init];
    _nameLB.textAlignment = NSTextAlignmentLeft;
    _nameLB.font = [UIFont boldSystemFontOfSize:18];
    _nameLB.textColor = COLOR_BIG;
    [self.contentView addSubview:_nameLB];
    
    _nameLB.sd_layout
    .leftSpaceToView(_headerImg, 10)
    .topEqualToView(_headerImg)
    .heightIs(30);
    
    [_nameLB setSingleLineAutoResizeWithMaxWidth:NYScreenW*0.5];
    
    //职能
    _zhiChengLB = [[UILabel alloc] init];
    _zhiChengLB.textAlignment = NSTextAlignmentLeft;
    _zhiChengLB.font = FONT(16);
    _zhiChengLB.textColor = COLOR_BIG;
    [self.contentView addSubview:_zhiChengLB];
    
    _zhiChengLB.sd_layout
    .leftSpaceToView(_nameLB, 15)
    .topEqualToView(_nameLB)
    .heightIs(30);
    
    [_zhiChengLB setSingleLineAutoResizeWithMaxWidth:NYScreenW*0.3];
    
    //医院名称
    _yiyuanLB = [[UILabel alloc] init];
    _yiyuanLB.textAlignment = NSTextAlignmentLeft;
    _yiyuanLB.font = FONT(16);
    _yiyuanLB.textColor = COLOR_BIG;
    [self.contentView addSubview:_yiyuanLB];
    
    _yiyuanLB.sd_layout
    .leftSpaceToView(_headerImg, 10)
    .topSpaceToView(_nameLB, 5)
    .heightIs(30);
    
    [_yiyuanLB setSingleLineAutoResizeWithMaxWidth:NYScreenW*0.5];
    
    //科室
    _keshiLB = [[UILabel alloc] init];
    _keshiLB.textAlignment = NSTextAlignmentLeft;
    _keshiLB.font = FONT(16);
    _keshiLB.textColor = COLOR_BIG;
    [self.contentView addSubview:_keshiLB];
    
    _keshiLB.sd_layout
    .leftSpaceToView(_yiyuanLB, 15)
    .topSpaceToView(_nameLB, 5)
    .heightIs(30);
    
    [_keshiLB setSingleLineAutoResizeWithMaxWidth:NYScreenW*0.3];

    
    //初步诊断
    _chuBuZhenDuanLB = [[UILabel alloc] init];
    _chuBuZhenDuanLB.textAlignment = NSTextAlignmentLeft;
    _chuBuZhenDuanLB.numberOfLines = 0;
    _chuBuZhenDuanLB.font = FONT(16);
    _chuBuZhenDuanLB.textColor = COLOR_LOW;
    [self.contentView addSubview:_chuBuZhenDuanLB];
    
    _chuBuZhenDuanLB.sd_layout
    .leftSpaceToView(self.contentView, 15)
    .topSpaceToView(_headerImg, 15)
    .rightSpaceToView(self.contentView, 15)
    .autoHeightRatio(0);
    
    _chuBuZhenDuanLB.isAttributedContent = YES;
    
    
    //需要做的检查
    _needCheckTitleLB = [[UILabel alloc] init];
    _needCheckTitleLB.textAlignment = NSTextAlignmentLeft;
    _needCheckTitleLB.font = [UIFont boldSystemFontOfSize:17];
    _needCheckTitleLB.textColor = COLOR_BIG;
    [self.contentView addSubview:_needCheckTitleLB];
    
    _needCheckTitleLB.sd_layout
    .leftSpaceToView(self.contentView, 15)
    .topSpaceToView(_chuBuZhenDuanLB, 15)
    .heightIs(30)
    .widthIs(200);

    _needCheckTitleLB.text = @"需做检查:";
    
    [self setupAutoHeightWithBottomView:_needCheckTitleLB bottomMargin:15];
    
    
    _nameLB.text = @"刘江华";
    
    _zhiChengLB.text = @"医学院院长";
    
    _yiyuanLB.text = @"解放军总医院";
    
    _keshiLB.text = @"妇产科";

    
}

- (void)setContent:(NSString *)content
{
    _content = content;
    
    NSString *text = @"初步诊断：这几天我家宝宝嘴里起了好多小泡。哭的特别厉害也不敢吃东西，后来去诊所，医生说这是小儿疱疹性口炎，我想问一下！！！这几天我家宝宝嘴里起了好多小泡。哭的特别厉害也不敢吃东西，后来去诊所，医生说这是小儿疱疹性口炎，我想问一下！！！";
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
