//
//  NYYuYueJiuZhenJiLvCell.m
//  User
//
//  Created by niuyao on 2018/12/5.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYYuYueJiuZhenJiLvCell.h"
#import "NYYuYueJiuZhenModel.h"

@interface NYYuYueJiuZhenJiLvCell ()
{
    UILabel * _nameLB;
    UILabel * _keshiLB;
    UILabel * _moneyLB;
    
    UILabel * _timeLeftLB;
    UILabel * _timeRightLB;
    
    UILabel * _userNameLeftLB;
    UILabel * _userNameRightLB;
    
    UILabel * _userAgeLeftLB;
    UILabel * _userAgeRightLB;
    
}
@end

@implementation NYYuYueJiuZhenJiLvCell

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
    //姓名
    _nameLB = [[UILabel alloc] init];
    _nameLB.textAlignment = NSTextAlignmentLeft;
    _nameLB.font = [UIFont boldSystemFontOfSize:18];
    _nameLB.textColor = COLOR_BIG;
    [self.contentView addSubview:_nameLB];
    
    _nameLB.sd_layout
    .leftSpaceToView(self.contentView, 15)
    .topSpaceToView(self.contentView, 20)
    .heightIs(20);
    
    [_nameLB setSingleLineAutoResizeWithMaxWidth:NYScreenW*0.3];
    
    //科室
    _keshiLB = [[UILabel alloc] init];
    _keshiLB.textAlignment = NSTextAlignmentLeft;
    _keshiLB.font = FONT(15);
    _keshiLB.textColor = COLOR_BIG;
    [self.contentView addSubview:_keshiLB];
    _keshiLB.sd_layout
    .leftSpaceToView(_nameLB, 10)
    .topSpaceToView(self.contentView, 20)
    .heightIs(20);
    
    [_keshiLB setSingleLineAutoResizeWithMaxWidth:NYScreenW*0.3];
    
    //金钱
    _moneyLB = [[UILabel alloc] init];
    _moneyLB.textAlignment = NSTextAlignmentRight;
    _moneyLB.font = [UIFont boldSystemFontOfSize:15];
    _moneyLB.textColor = COLOR_RED;
    [self.contentView addSubview:_moneyLB];
    _moneyLB.sd_layout
    .rightSpaceToView(self.contentView, 15)
    .topSpaceToView(self.contentView, 20)
    .heightIs(20);
    
    [_moneyLB setSingleLineAutoResizeWithMaxWidth:NYScreenW*0.3];

    //分割线
    UIView * lineV = [[UIView alloc] init];
    lineV.backgroundColor = BGCOLOR;
    [self.contentView addSubview:lineV];
    
    lineV.sd_layout
    .leftSpaceToView(self.contentView, 0)
    .topSpaceToView(_nameLB, 20)
    .rightSpaceToView(self.contentView, 0)
    .heightIs(2);
    
    //就诊时间
    _timeLeftLB = [[UILabel alloc] init];
    _timeLeftLB.textAlignment = NSTextAlignmentLeft;
    _timeLeftLB.textColor = COLOR_LOW;
    [self.contentView addSubview:_timeLeftLB];
    
    _timeLeftLB.sd_layout
    .leftSpaceToView(self.contentView, 15)
    .topSpaceToView(lineV, 15)
    .heightIs(25)
    .widthIs(90);
    
    
    _timeRightLB = [[UILabel alloc] init];
    _timeRightLB.textAlignment = NSTextAlignmentLeft;
    _timeRightLB.textColor = COLOR_BIG;
    [self.contentView addSubview:_timeRightLB];
    
    _timeRightLB.sd_layout
    .leftSpaceToView(_timeLeftLB, 10)
    .topSpaceToView(lineV, 15)
    .heightIs(25)
    .rightSpaceToView(self.contentView, 10);

    //患者姓名
    _userNameLeftLB = [[UILabel alloc] init];
    _userNameLeftLB.textAlignment = NSTextAlignmentLeft;
    _userNameLeftLB.textColor = COLOR_LOW;
    [self.contentView addSubview:_userNameLeftLB];
    
    _userNameLeftLB.sd_layout
    .leftSpaceToView(self.contentView, 15)
    .topSpaceToView(_timeLeftLB, 15)
    .heightIs(25)
    .widthIs(90);
    
    
    _userNameRightLB = [[UILabel alloc] init];
    _userNameRightLB.textAlignment = NSTextAlignmentLeft;
    _userNameRightLB.textColor = COLOR_BIG;
    [self.contentView addSubview:_userNameRightLB];
    
    _userNameRightLB.sd_layout
    .leftSpaceToView(_userNameLeftLB, 10)
    .topSpaceToView(_timeLeftLB, 15)
    .heightIs(25)
    .rightSpaceToView(self.contentView, 10);

    //患者年龄
    _userAgeLeftLB = [[UILabel alloc] init];
    _userAgeLeftLB.textAlignment = NSTextAlignmentLeft;
    _userAgeLeftLB.textColor = COLOR_LOW;
    [self.contentView addSubview:_userAgeLeftLB];
    
    _userAgeLeftLB.sd_layout
    .leftSpaceToView(self.contentView, 15)
    .topSpaceToView(_userNameLeftLB, 15)
    .heightIs(25)
    .widthIs(90);
    
    
    _userAgeRightLB = [[UILabel alloc] init];
    _userAgeRightLB.textAlignment = NSTextAlignmentLeft;
    _userAgeRightLB.textColor = COLOR_BIG;
    [self.contentView addSubview:_userAgeRightLB];
    
    _userAgeRightLB.sd_layout
    .leftSpaceToView(_userAgeLeftLB, 10)
    .topSpaceToView(_userNameLeftLB, 15)
    .heightIs(25)
    .rightSpaceToView(self.contentView, 10);

}

- (void)setJiuZhenJiLvModel:(NYYuYueJiuZhenModel *)jiuZhenJiLvModel
{
    _jiuZhenJiLvModel = jiuZhenJiLvModel;
    
    _nameLB.text = @"丁国芳";
    
    _keshiLB.text = @"(儿科)";
    
    _moneyLB.text = @"20.0元";
    
    _timeLeftLB.text = @"就诊时间";
    _timeRightLB.text = @"2018-10-26 周五 上午";
    
    _userNameLeftLB.text = @"患者姓名";
    _userNameRightLB.text = @"赵小强";

    _userAgeLeftLB.text = @"年龄";
    _userAgeRightLB.text = @"5岁";

}


@end
