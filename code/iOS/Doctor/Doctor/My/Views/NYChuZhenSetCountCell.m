//
//  NYChuZhenSetCountCell.m
//  Doctor
//
//  Created by niuyao on 2018/12/3.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYChuZhenSetCountCell.h"

@implementation NYChuZhenSetCountCell

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
    UILabel * titleLB = [[UILabel alloc] init];
    titleLB.textColor = COLOR_BIG;
    [self.contentView addSubview:titleLB];
    
    titleLB.sd_layout
    .leftSpaceToView(self.contentView, 15)
    .topSpaceToView(self.contentView, 0)
    .bottomSpaceToView(self.contentView, 0);
    
    [titleLB setSingleLineAutoResizeWithMaxWidth:NYScreenW*0.5];
    
    titleLB.text = @"接诊人数";
    
    _countTF = [[HHTextField alloc] init];
    _countTF.keyboardType = UIKeyboardTypeNumberPad;
    _countTF.returnKeyType = UIReturnKeyDone;
    [self.contentView addSubview:_countTF];
    
    _countTF.sd_layout
    .leftSpaceToView(titleLB, 10)
    .topSpaceToView(self.contentView, 0)
    .rightSpaceToView(self.contentView, 10)
    .bottomSpaceToView(self.contentView, 0);
    
    _countTF.placeholder = @"请输入接诊人数";

    
    
}
@end
