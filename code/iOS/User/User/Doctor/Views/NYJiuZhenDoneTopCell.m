//
//  NYJiuZhenDoneTopCell.m
//  User
//
//  Created by niuyao on 2018/12/18.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYJiuZhenDoneTopCell.h"
#import "NYYuYueJiuZhenModel.h"
#import "NYDoctorModel.h"

@interface NYJiuZhenDoneTopCell ()
{
    UILabel * _nameLeftLB;
    UILabel * _nameRightLB;
    
    UILabel * _danWeiLeftLB;
    UILabel * _danWeiRightLB;
    
    UILabel * _priceLeftLB;
    UILabel * _priceRightLB;
    
    UILabel * _yiyuanNameLB;
    
}

@end

@implementation NYJiuZhenDoneTopCell

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
    //医院名称
    _yiyuanNameLB = [[UILabel alloc] init];
    _yiyuanNameLB.textAlignment = NSTextAlignmentCenter;
    _yiyuanNameLB.font = FONT(20);
    _yiyuanNameLB.textColor = COLOR_BIG;
    [self.contentView addSubview:_yiyuanNameLB];
    
    _yiyuanNameLB.sd_layout
    .leftSpaceToView(self.contentView, 10)
    .topSpaceToView(self.contentView, 20)
    .heightIs(30)
    .rightSpaceToView(self.contentView, 10);
    
    _yiyuanNameLB.text = @"西安儿童医院";

    //就诊单
    UILabel * jiuZhenDanLB = [[UILabel alloc] init];
    jiuZhenDanLB.textAlignment = NSTextAlignmentCenter;
    jiuZhenDanLB.font = [UIFont boldSystemFontOfSize:16];
    jiuZhenDanLB.textColor = COLOR_BIG;
    [self.contentView addSubview:jiuZhenDanLB];
    
    jiuZhenDanLB.sd_layout
    .leftSpaceToView(self.contentView, 10)
    .topSpaceToView(_yiyuanNameLB, 10)
    .heightIs(30)
    .rightSpaceToView(self.contentView, 10);
    
    jiuZhenDanLB.text = @"就诊单";

    //粗线
    UIView * lineView1 = [[UIView alloc] init];
    lineView1.backgroundColor = BGCOLOR;
    [self.contentView addSubview:lineView1];
    
    lineView1.sd_layout
    .leftSpaceToView(self.contentView, 40)
    .topSpaceToView(jiuZhenDanLB, 10)
    .rightSpaceToView(self.contentView, 40)
    .heightIs(3);
    
    //细线
    UIView * lineView2 = [[UIView alloc] init];
    lineView2.backgroundColor = BGCOLOR;
    [self.contentView addSubview:lineView2];
    
    lineView2.sd_layout
    .leftSpaceToView(self.contentView, 40)
    .topSpaceToView(lineView1, 3)
    .rightSpaceToView(self.contentView, 40)
    .heightIs(1);

    
    //姓名
    _nameLeftLB = [[UILabel alloc] init];
    _nameLeftLB.textAlignment = NSTextAlignmentLeft;
//    _nameLeftLB.font = FONT(16);
    _nameLeftLB.textColor = COLOR_LOW;
    [self.contentView addSubview:_nameLeftLB];
    
    _nameLeftLB.sd_layout
    .leftSpaceToView(self.contentView, 40)
    .topSpaceToView(lineView2, 20)
    .heightIs(30)
    .widthIs(90);
    
    _nameLeftLB.text = @"就诊科室";
    
    
    _nameRightLB = [[UILabel alloc] init];
    _nameRightLB.textAlignment = NSTextAlignmentLeft;
//    _nameRightLB.font = FONT(16);
    _nameRightLB.textColor = COLOR_BIG;
    [self.contentView addSubview:_nameRightLB];
    
    _nameRightLB.sd_layout
    .leftSpaceToView(_nameLeftLB, 10)
    .topEqualToView(_nameLeftLB)
    .heightIs(30)
    .rightSpaceToView(self.contentView, 10);
    
    _nameRightLB.text = @"儿科";
    
    //医院名称
    _danWeiLeftLB = [[UILabel alloc] init];
    _danWeiLeftLB.textAlignment = NSTextAlignmentLeft;
//    _danWeiLeftLB.font = FONT(16);
    _danWeiLeftLB.textColor = COLOR_LOW;
    [self.contentView addSubview:_danWeiLeftLB];
    
    _danWeiLeftLB.sd_layout
    .leftSpaceToView(self.contentView, 40)
    .topSpaceToView(_nameLeftLB, 15)
    .heightIs(30)
    .widthIs(90);
    
    _danWeiLeftLB.text = @"就诊医生";
    
    _danWeiRightLB = [[UILabel alloc] init];
    _danWeiRightLB.textAlignment = NSTextAlignmentLeft;
//    _danWeiRightLB.font = FONT(16);
    _danWeiRightLB.textColor = COLOR_BIG;
    [self.contentView addSubview:_danWeiRightLB];
    
    _danWeiRightLB.sd_layout
    .leftSpaceToView(_danWeiLeftLB, 10)
    .topSpaceToView(_nameLeftLB, 15)
    .heightIs(30)
    .rightSpaceToView(self.contentView, 10);
    
    _danWeiRightLB.text = @"刘振环";
    
    //预约费
    _priceLeftLB = [[UILabel alloc] init];
    _priceLeftLB.textAlignment = NSTextAlignmentLeft;
//    _priceLeftLB.font = FONT(17);
    _priceLeftLB.textColor = COLOR_LOW;
    [self.contentView addSubview:_priceLeftLB];
    
    _priceLeftLB.sd_layout
    .leftSpaceToView(self.contentView, 40)
    .topSpaceToView(_danWeiLeftLB, 15)
    .heightIs(30)
    .widthIs(90);
    
    _priceLeftLB.text = @"预约费";
    
    _priceRightLB = [[UILabel alloc] init];
    _priceRightLB.textAlignment = NSTextAlignmentLeft;
//    _priceRightLB.font = FONT(16);
    _priceRightLB.textColor = COLOR_RED;
    [self.contentView addSubview:_priceRightLB];
    
    _priceRightLB.sd_layout
    .leftSpaceToView(_priceLeftLB, 10)
    .topSpaceToView(_danWeiLeftLB, 15)
    .heightIs(30)
    .rightSpaceToView(self.contentView, 10);
    
    _priceRightLB.text = @"20.0元";
}

- (void)setJiuZhenModel:(NYYuYueJiuZhenModel *)jiuZhenModel
{
    _jiuZhenModel = jiuZhenModel;
    
    //医院
    _yiyuanNameLB.text = jiuZhenModel.doctor.hosName;
    
    //就诊科室
    _nameRightLB.text = jiuZhenModel.doctor.offName;
    
    //就诊医生
    _danWeiRightLB.text = jiuZhenModel.doctor.name;
    
    //预约费用
    _priceRightLB.text = [NSString stringWithFormat:@"%.2f元",[jiuZhenModel.fee doubleValue]];
    
}

@end
