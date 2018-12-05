//
//  NYRegisterShanChangCell.m
//  Doctor
//
//  Created by niuyao on 2018/12/4.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYRegisterShanChangCell.h"
#import "BRPlaceholderTextView.h"

@implementation NYRegisterShanChangCell

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
    //备注
    _infoTextView = [[BRPlaceholderTextView alloc] init];
    _infoTextView.maxTextLength = 1000;
    _infoTextView.returnKeyType = UIReturnKeyDone;
    _infoTextView.font = [UIFont systemFontOfSize:17];
    [_infoTextView setPlaceholderFont:[UIFont systemFontOfSize:17]];
    [self.contentView addSubview:_infoTextView];
    _infoTextView.placeholder = @"临床症状表现";
    _infoTextView.sd_layout
    .leftSpaceToView(self.contentView, 10)
    .topSpaceToView(self.contentView, 15)
    .rightSpaceToView(self.contentView, 15)
    .bottomSpaceToView(self.contentView, 15);
}
@end
