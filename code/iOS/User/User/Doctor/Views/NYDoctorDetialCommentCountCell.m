//
//  NYDoctorDetialCommentCountCell.m
//  User
//
//  Created by niuyao on 2018/12/6.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYDoctorDetialCommentCountCell.h"

@implementation NYDoctorDetialCommentCountCell

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
    _titleLB = [[UILabel alloc] init];
    _titleLB.textColor = COLOR_BIG;
    _titleLB.textAlignment = NSTextAlignmentLeft;
    _titleLB.text = @"评价（56）";
    [self.contentView addSubview:_titleLB];
    
    _titleLB.sd_layout
    .leftSpaceToView(self.contentView, 30)
    .topSpaceToView(self.contentView, 0)
    .rightSpaceToView(self.contentView, 15)
    .bottomSpaceToView(self.contentView, 0);
    
}

@end
