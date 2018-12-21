//
//  NYYuYueJiuZhenJiLvCell.m
//  User
//
//  Created by niuyao on 2018/12/5.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYYuYueJiuZhenJiLvCell.h"
#import "NYMyJieZhenModel.h"

@interface NYYuYueJiuZhenJiLvCell ()
{
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
    //就诊时间
    _timeLeftLB = [[UILabel alloc] init];
    _timeLeftLB.textAlignment = NSTextAlignmentLeft;
    _timeLeftLB.textColor = COLOR_LOW;
    [self.contentView addSubview:_timeLeftLB];
    
    _timeLeftLB.sd_layout
    .leftSpaceToView(self.contentView, 15)
    .topSpaceToView(self.contentView, 15)
    .heightIs(25)
    .widthIs(90);
    
    
    _timeRightLB = [[UILabel alloc] init];
    _timeRightLB.textAlignment = NSTextAlignmentLeft;
    _timeRightLB.textColor = COLOR_BIG;
    [self.contentView addSubview:_timeRightLB];
    
    _timeRightLB.sd_layout
    .leftSpaceToView(_timeLeftLB, 10)
    .topSpaceToView(self.contentView, 15)
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

- (void)setJieZhenModel:(NYMyJieZhenModel *)jieZhenModel
{
    _jieZhenModel = jieZhenModel;
    
    _timeLeftLB.text = @"就诊时间";
    _timeRightLB.text = [NSString stringWithFormat:@"%@ %@",jieZhenModel.visitTime,[jieZhenModel.timePart integerValue]==0?@"上午":@"下午"];
    
    _userNameLeftLB.text = @"患者姓名";
    _userNameRightLB.text = jieZhenModel.name;
    
    _userAgeLeftLB.text = @"年龄";
    _userAgeRightLB.text = [NSString stringWithFormat:@"%@岁",jieZhenModel.age];

}


@end
