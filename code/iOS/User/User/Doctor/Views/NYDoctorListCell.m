//
//  NYDoctorListCell.m
//  User
//
//  Created by niuyao on 2018/12/5.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYDoctorListCell.h"

@interface NYDoctorListCell ()
{
    UIImageView * _headerImg; //头像
    UILabel * _nameLB;
    UILabel * _zhiChengLB; //职称
    UILabel * _yiyuanLB;
    UILabel * _keshiLB;
    UILabel * _shanChangLB;
}
@end
@implementation NYDoctorListCell

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
    _nameLB = [[UILabel alloc] init];
    _nameLB.textAlignment = NSTextAlignmentLeft;
    _nameLB.font = [UIFont boldSystemFontOfSize:18];
    _nameLB.textColor = COLOR_BIG;
    [self.contentView addSubview:_nameLB];
    
    _nameLB.sd_layout
    .leftSpaceToView(_headerImg, 10)
    .topEqualToView(_headerImg)
    .heightIs(25);
    
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
    .heightIs(25);
    
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
    .heightIs(25);
    
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
    .heightIs(25);
    
    [_keshiLB setSingleLineAutoResizeWithMaxWidth:NYScreenW*0.3];
    
    //擅长
    _shanChangLB = [[UILabel alloc] init];
    _shanChangLB.textAlignment = NSTextAlignmentLeft;
    _shanChangLB.numberOfLines = 0;
    _shanChangLB.font = FONT(16);
    _shanChangLB.textColor = COLOR_LOW;
    [self.contentView addSubview:_shanChangLB];
    
    _shanChangLB.sd_layout
    .leftSpaceToView(_headerImg, 10)
    .topSpaceToView(_yiyuanLB, 5)
    .rightSpaceToView(self.contentView, 15)
    .autoHeightRatio(0);
    
    [_shanChangLB setMaxNumberOfLinesToShow:1];

    [self setupAutoHeightWithBottomViewsArray:@[_headerImg,_shanChangLB] bottomMargin:20];
}

- (void)setDoctorModel:(NYDoctorModel *)doctorModel
{
    _doctorModel = doctorModel;
    
    _nameLB.text = @"刘江华";
    
    _zhiChengLB.text = @"医学院院长";
    
    _yiyuanLB.text = @"解放军总医院";
    
    _keshiLB.text = @"妇产科";
    
    _shanChangLB.text = @"擅长：孕前检查、不孕不育、反复流产、孕前检查、不孕不育、反复流产、孕前检查、不孕不育、反复流产、孕前检查、不孕不育、反复流产、孕前检查、不孕不育、反复流产、孕前检查、不孕不育、反复流产";
    
}
@end
