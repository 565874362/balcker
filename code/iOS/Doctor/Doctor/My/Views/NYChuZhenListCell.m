//
//  NYChuZhenListCell.m
//  Doctor
//
//  Created by niuyao on 2018/12/3.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYChuZhenListCell.h"
#import "NYChuZhenListModel.h"

@interface NYChuZhenListCell ()
{
    UILabel * _weekLB;
    UILabel * _dayLB;
    
    UILabel * _timeLB;
    UILabel * _countLB;
    
}
@end

@implementation NYChuZhenListCell

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
    //星期
    _weekLB = [[UILabel alloc] init];
    _weekLB.textAlignment = NSTextAlignmentLeft;
    _weekLB.font = FONT(18);
    _weekLB.textColor = COLOR_BIG;
    [self.contentView addSubview:_weekLB];
    
    _weekLB.sd_layout
    .leftSpaceToView(self.contentView, 15)
    .topSpaceToView(self.contentView, 20)
    .widthIs(50)
    .heightIs(25);
    
    //上午 下午
    _dayLB = [[UILabel alloc] init];
    _dayLB.textAlignment = NSTextAlignmentLeft;
    _dayLB.font = FONT(18);
    _dayLB.textColor = COLOR_BIG;
    [self.contentView addSubview:_dayLB];
    
    _dayLB.sd_layout
    .leftSpaceToView(self.contentView, 15)
    .topSpaceToView(_weekLB, 5)
    .widthIs(50)
    .heightIs(25);

    //分割线
    UIView * lineView = [[UIView alloc] init];
    lineView.backgroundColor = BGCOLOR;
    [self.contentView addSubview:lineView];
    
    lineView.sd_layout
    .leftSpaceToView(_weekLB, 5)
    .topSpaceToView(self.contentView, 20)
    .widthIs(1.5)
    .bottomSpaceToView(self.contentView, 20);
    
    
    //时间
    _timeLB = [[UILabel alloc] init];
    _timeLB.textAlignment = NSTextAlignmentLeft;
    _timeLB.font = FONT(17);
    _timeLB.textColor = COLOR_BIG;
    [self.contentView addSubview:_timeLB];
    
    _timeLB.sd_layout
    .leftSpaceToView(lineView, 15)
    .topSpaceToView(self.contentView, 20)
    .rightSpaceToView(self.contentView, 15)
    .heightIs(25);
    
    //就诊人数
    _countLB = [[UILabel alloc] init];
    _countLB.textAlignment = NSTextAlignmentLeft;
    _countLB.font = FONT(15);
    _countLB.textColor = COLOR_LOW;
    [self.contentView addSubview:_countLB];
    
    _countLB.sd_layout
    .leftSpaceToView(lineView, 15)
    .topSpaceToView(_timeLB, 5)
    .rightSpaceToView(self.contentView, 15)
    .heightIs(25);
}

- (void)setChuZhenListModel:(NYChuZhenListModel *)chuZhenListModel
{
    _chuZhenListModel = chuZhenListModel;
    
    _weekLB.text = @"周一";
    
    _dayLB.text = @"上午";
    
    _timeLB.text = @"08:30-11:30";
    
    _countLB.text = @"就诊人数：80人";
}
@end
