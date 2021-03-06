//
//  NYMyInfoDetailCell.m
//  Doctor
//
//  Created by niuyao on 2018/12/5.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYMyInfoDetailCell.h"
#import "NYMyInfoDetailModel.h"
#import "NYAdeptEntitiesModel.h"

@interface NYMyInfoDetailCell ()
{
    UIImageView * _headerImg; //头像
    UILabel * _keshiLB; //科室
    UILabel * _nameLB; //姓名
    UILabel * _zhichengLB; //职称
    UILabel * _yiyuanNameLB; //医院名称
    UILabel * _shanChangLB; //擅长标签
    UILabel * _shanChangInfoLB1; //擅长1
    UILabel * _shanChangInfoLB2; //擅长2
    UILabel * _shanChangInfoLB3; //擅长3
    UILabel * _zigeCardLB; //医生资格证
    UIImageView * _zigeCardImg; //资格证图片
    UILabel * _shenfenCardLB;//身份证
    UIImageView * _shenfenCardImg1; //身份证1
    UIImageView * _shenfenCardImg2; //身份证2
}
@end

@implementation NYMyInfoDetailCell

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
    //用户头像
    _headerImg = [[UIImageView alloc] init];
    _headerImg.image = [UIImage imageNamed:@"placeholderImage"];
    _headerImg.contentMode = UIViewContentModeScaleAspectFill;
    _headerImg.clipsToBounds = YES;
    [self.contentView addSubview:_headerImg];
    _headerImg.sd_layout
    .leftSpaceToView(self.contentView, 0)
    .rightSpaceToView(self.contentView, 0)
    .topSpaceToView(self.contentView, 0)
    .heightIs(NYScreenW);
    
    //突出的白色
    UIView * bgView = [[UIView alloc] init];
    bgView.backgroundColor = [UIColor whiteColor];
    
    [self.contentView addSubview:bgView];
    
    bgView.sd_layout
    .leftSpaceToView(self.contentView, 15)
    .rightSpaceToView(self.contentView, 15)
    .heightIs(60)
    .topSpaceToView(self.contentView, NYScreenW-40);
    
    bgView.sd_cornerRadius = @5;
    
    //科室
    _keshiLB = [[UILabel alloc] init];
    _keshiLB.textAlignment = NSTextAlignmentRight;
    _keshiLB.font = FONT(15);
    _keshiLB.textColor = COLOR_RED;
    [bgView addSubview:_keshiLB];
    
    _keshiLB.sd_layout
    .rightSpaceToView(bgView, 15)
    .topSpaceToView(bgView, 20)
    .heightIs(20);
    
    [_keshiLB setSingleLineAutoResizeWithMaxWidth:NYScreenW*0.33];
    
    //姓名
    _nameLB = [[UILabel alloc] init];
    _nameLB.textAlignment = NSTextAlignmentLeft;
    _nameLB.textColor = COLOR_BIG;
    _nameLB.font = [UIFont boldSystemFontOfSize:18];
    [bgView addSubview:_nameLB];
    
    _nameLB.sd_layout
    .leftSpaceToView(bgView, 15)
    .topSpaceToView(bgView, 20)
    .heightIs(20);
    [_nameLB setSingleLineAutoResizeWithMaxWidth:NYScreenW  *0.33];
    
    //职称
    _zhichengLB = [[UILabel alloc] init];
    _zhichengLB.textAlignment = NSTextAlignmentLeft;
    _zhichengLB.font = FONT(15);
    _zhichengLB.textColor = COLOR_BIG;
    [bgView addSubview:_zhichengLB];
    
    _zhichengLB.sd_layout
    .leftSpaceToView(_nameLB, 10)
    .topSpaceToView(bgView, 20)
    .heightIs(20);
    
    [_zhichengLB setSingleLineAutoResizeWithMaxWidth:NYScreenW*0.33];
    
    //医院名称
    _yiyuanNameLB = [[UILabel alloc] init];
    _yiyuanNameLB.textColor = COLOR_BIG;
    _yiyuanNameLB.textAlignment = NSTextAlignmentLeft;
    _yiyuanNameLB.font = FONT(15);
    [self.contentView addSubview:_yiyuanNameLB];
    
    _yiyuanNameLB.sd_layout
    .leftSpaceToView(self.contentView, 30)
    .topSpaceToView(bgView, 10)
    .rightSpaceToView(self.contentView, 30)
    .autoHeightRatio(0);
    [_yiyuanNameLB setMaxNumberOfLinesToShow:0];

    //擅长
    _shanChangLB = [[UILabel alloc] init];
    _shanChangLB.textAlignment = NSTextAlignmentLeft;
    _shanChangLB.font = FONT(15);
    _shanChangLB.textColor = COLOR_BIG;
    [self.contentView addSubview:_shanChangLB];
    
    _shanChangLB.sd_layout
    .leftSpaceToView(self.contentView, 30)
    .topSpaceToView(_yiyuanNameLB, 10)
    .heightIs(20)
    .rightSpaceToView(self.contentView, 30);
    
    //擅长1
    _shanChangInfoLB1 = [[UILabel alloc] init];
    _shanChangInfoLB1.textColor = COLOR_BIG;
    _shanChangInfoLB1.textAlignment = NSTextAlignmentLeft;
    _shanChangInfoLB1.font = FONT(15);
    [self.contentView addSubview:_shanChangInfoLB1];
    
    _shanChangInfoLB1.sd_layout
    .leftSpaceToView(self.contentView, 30)
    .topSpaceToView(_shanChangLB, 10)
    .rightSpaceToView(self.contentView, 30)
    .autoHeightRatio(0);
    [_shanChangInfoLB1 setMaxNumberOfLinesToShow:0];

    //擅长2
    _shanChangInfoLB2 = [[UILabel alloc] init];
    _shanChangInfoLB2.textColor = COLOR_BIG;
    _shanChangInfoLB2.textAlignment = NSTextAlignmentLeft;
    _shanChangInfoLB2.font = FONT(15);
    [self.contentView addSubview:_shanChangInfoLB2];
    
    _shanChangInfoLB2.sd_layout
    .leftSpaceToView(self.contentView, 30)
    .topSpaceToView(_shanChangInfoLB1, 10)
    .rightSpaceToView(self.contentView, 30)
    .autoHeightRatio(0);
    [_shanChangInfoLB2 setMaxNumberOfLinesToShow:0];

    //擅长3
    _shanChangInfoLB3 = [[UILabel alloc] init];
    _shanChangInfoLB3.textColor = COLOR_BIG;
    _shanChangInfoLB3.textAlignment = NSTextAlignmentLeft;
    _shanChangInfoLB3.font = FONT(15);
    [self.contentView addSubview:_shanChangInfoLB3];
    
    _shanChangInfoLB3.sd_layout
    .leftSpaceToView(self.contentView, 30)
    .topSpaceToView(_shanChangInfoLB2, 10)
    .rightSpaceToView(self.contentView, 30)
    .autoHeightRatio(0);
    [_shanChangInfoLB3 setMaxNumberOfLinesToShow:0];
    
    //医生资格证书
    _zigeCardLB = [[UILabel alloc] init];
    _zigeCardLB.textAlignment = NSTextAlignmentLeft;
    _zigeCardLB.font = FONT(15);
    _zigeCardLB.textColor = COLOR_BIG;
    [self.contentView addSubview:_zigeCardLB];
    
    _zigeCardLB.sd_layout
    .leftSpaceToView(self.contentView, 30)
    .topSpaceToView(_shanChangInfoLB3, 10)
    .heightIs(20)
    .rightSpaceToView(self.contentView, 30);

    //医生资格证书图片
    _zigeCardImg = [[UIImageView alloc] init];
    _zigeCardImg.image = [UIImage imageNamed:@"placeholderImage"];
    _zigeCardImg.contentMode = UIViewContentModeScaleAspectFill;
    _zigeCardImg.clipsToBounds = YES;
    [self.contentView addSubview:_zigeCardImg];
    _zigeCardImg.sd_layout
    .leftSpaceToView(self.contentView, 30)
    .rightSpaceToView(self.contentView, 30)
    .topSpaceToView(_zigeCardLB, 10)
    .heightIs(NYScreenW*0.5);
    
    //身份证
    _shenfenCardLB = [[UILabel alloc] init];
    _shenfenCardLB.textAlignment = NSTextAlignmentLeft;
    _shenfenCardLB.font = FONT(15);
    _shenfenCardLB.textColor = COLOR_BIG;
    [self.contentView addSubview:_shenfenCardLB];
    
    _shenfenCardLB.sd_layout
    .leftSpaceToView(self.contentView, 30)
    .topSpaceToView(_zigeCardImg, 10)
    .heightIs(20)
    .rightSpaceToView(self.contentView, 30);

    //身份证图片1
    _shenfenCardImg1 = [[UIImageView alloc] init];
    _shenfenCardImg1.image = [UIImage imageNamed:@"placeholderImage"];
    _shenfenCardImg1.contentMode = UIViewContentModeScaleAspectFill;
    _shenfenCardImg1.clipsToBounds = YES;
    [self.contentView addSubview:_shenfenCardImg1];
    _shenfenCardImg1.sd_layout
    .leftSpaceToView(self.contentView, 30)
    .rightSpaceToView(self.contentView, 30)
    .topSpaceToView(_shenfenCardLB, 10)
    .heightIs(NYScreenW*0.5);
    
    //身份证图片2
    _shenfenCardImg2 = [[UIImageView alloc] init];
    _shenfenCardImg2.image = [UIImage imageNamed:@"placeholderImage"];
    _shenfenCardImg2.contentMode = UIViewContentModeScaleAspectFill;
    _shenfenCardImg2.clipsToBounds = YES;
    [self.contentView addSubview:_shenfenCardImg2];
    _shenfenCardImg2.sd_layout
    .leftSpaceToView(self.contentView, 30)
    .rightSpaceToView(self.contentView, 30)
    .topSpaceToView(_shenfenCardImg1, 10)
    .heightIs(NYScreenW*0.5);

    
    [self setupAutoHeightWithBottomView:_shenfenCardImg2 bottomMargin:30];
    
}

- (void)setMyInfoModel:(NYMyInfoDetailModel *)myInfoModel
{
    _myInfoModel = myInfoModel;
    
    [_headerImg sd_setImageWithURL:[NSURL URLWithString:myInfoModel.photo] placeholderImage:[UIImage imageNamed:@"placeholderImage"]];
    
    //科室
    _keshiLB.text = myInfoModel.offName;
    //姓名
    _nameLB.text = myInfoModel.name;
    //职称
    _zhichengLB.text = myInfoModel.positionName;
    //医院名称
    _yiyuanNameLB.text = myInfoModel.hosName;
    //擅长
    _shanChangLB.text = @"擅长:";
    
    NSArray * shanChangArr = myInfoModel.adeptEntities;
    
    for (int i = 0; i < shanChangArr.count; i++) {
        NYAdeptEntitiesModel * adModel = shanChangArr[i];
        if (i == 0) {
            _shanChangInfoLB1.text = [NSString stringWithFormat:@"%@：%@",adModel.name,adModel.describe];
        }else if (i == 1){
            _shanChangInfoLB2.text = [NSString stringWithFormat:@"%@：%@",adModel.name,adModel.describe];
        }else if (i == 2){
            _shanChangInfoLB3.text = [NSString stringWithFormat:@"%@：%@",adModel.name,adModel.describe];
        }
    }
    //医生资格证
    _zigeCardLB.text = @"医生资格证";
    [_zigeCardImg sd_setImageWithURL:[NSURL URLWithString:myInfoModel.physicianLicence] placeholderImage:[UIImage imageNamed:@"placeholderImage"]];
    
    //身份证
    _shenfenCardLB.text = @"身份证图片";
    NSArray * carImgArr = [myInfoModel.identityCard componentsSeparatedByString:@","];
    [_shenfenCardImg1 sd_setImageWithURL:[NSURL URLWithString:carImgArr[0]] placeholderImage:[UIImage imageNamed:@"placeholderImage"]];
    [_shenfenCardImg2 sd_setImageWithURL:[NSURL URLWithString:carImgArr[1]] placeholderImage:[UIImage imageNamed:@"placeholderImage"]];

}
@end
