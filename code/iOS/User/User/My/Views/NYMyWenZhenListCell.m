//
//  NYHomeListCell.m
//  Doctor
//
//  Created by niuyao on 2018/12/3.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYMyWenZhenListCell.h"
#import "NYMyWenZhenModel.h"

@interface NYMyWenZhenListCell ()
{
    UILabel * _nameLB;
    UILabel * _sexAndAgeLB;
    UILabel * _contentLB;
    UILabel * _timeLB;
    UILabel * _stateLB;
}
@end

@implementation NYMyWenZhenListCell

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
    .topSpaceToView(self.contentView, 25)
    .heightIs(20);
    
    [_nameLB setSingleLineAutoResizeWithMaxWidth:NYScreenW*0.3];
    
    //性别和年龄
    _sexAndAgeLB = [[UILabel alloc] init];
    _sexAndAgeLB.textAlignment = NSTextAlignmentLeft;
    _sexAndAgeLB.font = FONT(15);
    _sexAndAgeLB.textColor = COLOR_BIG;
    [self.contentView addSubview:_sexAndAgeLB];
    _sexAndAgeLB.sd_layout
    .leftSpaceToView(_nameLB, 10)
    .topSpaceToView(self.contentView, 25)
    .heightIs(20);
    
    [_sexAndAgeLB setSingleLineAutoResizeWithMaxWidth:NYScreenW*0.3];
    
    //内容
    _contentLB = [[UILabel alloc] init];
    _contentLB.textAlignment = NSTextAlignmentLeft;
    _contentLB.numberOfLines = 0;
    _contentLB.font = FONT(15);
    _contentLB.textColor = COLOR_LOW;
    [self.contentView addSubview:_contentLB];
    
    _contentLB.sd_layout
    .leftSpaceToView(self.contentView, 15)
    .topSpaceToView(_nameLB, 15)
    .rightSpaceToView(self.contentView, 15)
    .autoHeightRatio(0);
    
    //时间
    _timeLB = [[UILabel alloc] init];
    _timeLB.textAlignment = NSTextAlignmentLeft;
    _timeLB.font = FONT(15);
    _timeLB.textColor = COLOR_LOW;
    [self.contentView addSubview:_timeLB];
    
    _timeLB.sd_layout
    .topSpaceToView(_contentLB, 15)
    .leftSpaceToView(self.contentView, 15)
    .heightIs(25);
    
    [_timeLB setSingleLineAutoResizeWithMaxWidth:NYScreenW*0.5];
    
    //状态
    _stateLB = [[UILabel alloc] init];
    _stateLB.textAlignment = NSTextAlignmentCenter;
    _stateLB.font = FONT(15);
    [self.contentView addSubview:_stateLB];
    
    _stateLB.sd_layout
    .rightSpaceToView(self.contentView, 15)
    .centerYEqualToView(_timeLB)
    .heightIs(25)
    .widthIs(70);
    
    
    [self setupAutoHeightWithBottomView:_timeLB bottomMargin:20];
}

- (void)setWenZhenListModel:(NYMyWenZhenModel *)wenZhenListModel
{
    _wenZhenListModel = wenZhenListModel;
    
    //状态
    if ([wenZhenListModel.state integerValue] == 1) {
        _stateLB.text = @"已解答";
        _stateLB.textColor = [UIColor whiteColor];
        _stateLB.backgroundColor = MAINCOLOR;
        
    }else{
        _stateLB.text = @"未解答";
        _stateLB.textColor = [UIColor whiteColor];
        _stateLB.backgroundColor = COLOR_RED;
        
    }
    
    _nameLB.text = @"赵小强";
    
    _sexAndAgeLB.text = @"男  5岁";
    
    _contentLB.text = @"这几天我家宝宝嘴里起了好多小泡。哭的特别厉害也不敢吃东西，后来去诊所，医生说这是小儿疱疹性口炎，我想问一下！！！这几天我家宝宝嘴里起了好多小泡。哭的特别厉害也不敢吃东西，后来去诊所，医生说这是小儿疱疹性口炎，我想问一下！！！";
    
    _timeLB.text = @"2018-11-22 10:10";
}

@end
