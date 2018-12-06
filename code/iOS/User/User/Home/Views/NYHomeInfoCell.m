//
//  NYHomeInfoCell.m
//  User
//
//  Created by niuyao on 2018/12/6.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYHomeInfoCell.h"
#import "BRPlaceholderTextView.h"

@implementation NYHomeInfoCell

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
    _infoTextView.placeholder = @"请描述你的问题,包括身体状况、疾病和症状等,医生会根据您的描述给出专业的意见。";
    _infoTextView.sd_layout
    .leftSpaceToView(self.contentView, 10)
    .topSpaceToView(self.contentView, 10)
    .rightSpaceToView(self.contentView, 15)
    .bottomSpaceToView(self.contentView, 10);
}

@end
