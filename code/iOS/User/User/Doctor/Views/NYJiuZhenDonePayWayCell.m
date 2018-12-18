//
//  NYJiuZhenDonePayWayCell.m
//  User
//
//  Created by niuyao on 2018/12/18.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYJiuZhenDonePayWayCell.h"

@implementation NYJiuZhenDonePayWayCell

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
    _leftIMG = [[UIImageView alloc] init];
    _leftIMG.contentMode = UIViewContentModeScaleAspectFill;
    _leftIMG.image = [UIImage imageNamed:@"placeholderImage"];
    _leftIMG.clipsToBounds = YES;
    [self.contentView addSubview:_leftIMG];
    
    _leftIMG.sd_layout
    .leftSpaceToView(self.contentView, 15)
    .centerYEqualToView(self.contentView)
    .widthIs(30)
    .heightEqualToWidth();
    
    _typeLB = [[UILabel alloc] init];
    _typeLB.textAlignment = NSTextAlignmentLeft;
    _typeLB.textColor = COLOR_BIG;
    _typeLB.font = FONT(16);
    [self.contentView addSubview:_typeLB];
    
    _typeLB.sd_layout
    .leftSpaceToView(_leftIMG, 10)
    .topSpaceToView(self.contentView, 0)
    .bottomSpaceToView(self.contentView, 0)
    .widthIs(200);
    
    _rightIMG = [[UIImageView alloc] init];
    _rightIMG.contentMode = UIViewContentModeScaleAspectFill;
    _rightIMG.image = [UIImage imageNamed:@"register_pact"];
    _rightIMG.clipsToBounds = YES;
    [self.contentView addSubview:_rightIMG];
    
    _rightIMG.sd_layout
    .rightSpaceToView(self.contentView, 15)
    .centerYEqualToView(self.contentView)
    .widthIs(20)
    .heightEqualToWidth();

    _lineView = [[UIView alloc] init];
    _lineView.backgroundColor = BGCOLOR;
    [self.contentView addSubview:_lineView];
    _lineView.sd_layout
    .leftSpaceToView(self.contentView, 0)
    .rightSpaceToView(self.contentView, 0)
    .bottomSpaceToView(self.contentView, 0)
    .heightIs(1);
}

@end
