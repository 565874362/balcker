//
//  NYJieZhenDoctorDetailCell.m
//  User
//
//  Created by niuyao on 2018/12/6.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYJieZhenDoctorDetailCell.h"
#import "NYYuYueJiuZhenModel.h"
#import "NYDoctorModel.h"

@interface NYJieZhenDoctorDetailCell ()
{
    UIImageView * _headerImg; //头像
    
    UILabel * _nameLeftLB;
    UILabel * _nameRightLB;
    
    UILabel * _danWeiLeftLB;
    UILabel * _danWeiRightLB;
    
    UILabel * _priceLeftLB;
    UILabel * _priceRightLB;
}
@end

@implementation NYJieZhenDoctorDetailCell

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
    //头像
    _headerImg = [[UIImageView alloc] init];
    _headerImg.image = [UIImage imageNamed:@"placeholderImage"];
    _headerImg.clipsToBounds = YES;
    [self.contentView addSubview:_headerImg];
    
    _headerImg.sd_layout
    .leftSpaceToView(self.contentView, 20)
    .topSpaceToView(self.contentView, 20)
    .widthIs(80)
    .heightEqualToWidth();
    
    _headerImg.sd_cornerRadius = @40;
    
    //姓名
    _nameLeftLB = [[UILabel alloc] init];
    _nameLeftLB.textAlignment = NSTextAlignmentLeft;
    _nameLeftLB.font = FONT(16);
    _nameLeftLB.textColor = COLOR_LOW;
    [self.contentView addSubview:_nameLeftLB];
    
    _nameLeftLB.sd_layout
    .leftSpaceToView(_headerImg, 10)
    .topEqualToView(_headerImg)
    .heightIs(25)
    .widthIs(80);
    
    _nameLeftLB.text = @"就诊医生";
    
    
    _nameRightLB = [[UILabel alloc] init];
    _nameRightLB.textAlignment = NSTextAlignmentLeft;
    _nameRightLB.font = FONT(16);
    _nameRightLB.textColor = COLOR_BIG;
    [self.contentView addSubview:_nameRightLB];
    
    _nameRightLB.sd_layout
    .leftSpaceToView(_nameLeftLB, 10)
    .topEqualToView(_nameLeftLB)
    .heightIs(25)
    .rightSpaceToView(self.contentView, 10);
    
    _nameRightLB.text = @"刘振环";
    
    //医院名称
    _danWeiLeftLB = [[UILabel alloc] init];
    _danWeiLeftLB.textAlignment = NSTextAlignmentLeft;
    _danWeiLeftLB.font = FONT(16);
    _danWeiLeftLB.textColor = COLOR_LOW;
    [self.contentView addSubview:_danWeiLeftLB];
    
    _danWeiLeftLB.sd_layout
    .leftSpaceToView(_headerImg, 10)
    .topSpaceToView(_nameLeftLB, 5)
    .heightIs(25)
    .widthIs(80);
    
    _danWeiLeftLB.text = @"单位";
    
    _danWeiRightLB = [[UILabel alloc] init];
    _danWeiRightLB.textAlignment = NSTextAlignmentLeft;
    _danWeiRightLB.font = FONT(16);
    _danWeiRightLB.textColor = COLOR_BIG;
    [self.contentView addSubview:_danWeiRightLB];
    
    _danWeiRightLB.sd_layout
    .leftSpaceToView(_danWeiLeftLB, 10)
    .topSpaceToView(_nameLeftLB, 5)
    .heightIs(25)
    .rightSpaceToView(self.contentView, 10);
    
    _danWeiRightLB.text = @"西安市儿童医院";
    
    //预约费
    _priceLeftLB = [[UILabel alloc] init];
    _priceLeftLB.textAlignment = NSTextAlignmentLeft;
    _priceLeftLB.font = FONT(16);
    _priceLeftLB.textColor = COLOR_LOW;
    [self.contentView addSubview:_priceLeftLB];
    
    _priceLeftLB.sd_layout
    .leftSpaceToView(_headerImg, 10)
    .topSpaceToView(_danWeiLeftLB, 5)
    .heightIs(25)
    .widthIs(80);
    
    _priceLeftLB.text = @"预约费";
    
    _priceRightLB = [[UILabel alloc] init];
    _priceRightLB.textAlignment = NSTextAlignmentLeft;
    _priceRightLB.font = FONT(16);
    _priceRightLB.textColor = COLOR_RED;
    [self.contentView addSubview:_priceRightLB];
    
    _priceRightLB.sd_layout
    .leftSpaceToView(_priceLeftLB, 10)
    .topSpaceToView(_danWeiLeftLB, 5)
    .heightIs(25)
    .rightSpaceToView(self.contentView, 10);

    _priceRightLB.text = @"20.0元";
}

- (void)setJiuZhenModel:(NYYuYueJiuZhenModel *)jiuZhenModel
{
    _jiuZhenModel = jiuZhenModel;
    
    //头像
    [_headerImg sd_setImageWithURL:[NSURL URLWithString:jiuZhenModel.doctor.photo] placeholderImage:[UIImage imageNamed:@"placeholderImage"]];
    
    //姓名
    _nameRightLB.text = jiuZhenModel.doctor.name;
    
    //单位
    _danWeiRightLB.text = jiuZhenModel.doctor.hosName;
    
    //预约费用
    _priceRightLB.text = [NSString stringWithFormat:@"%.2f元",[jiuZhenModel.fee doubleValue]];
    
}
@end
