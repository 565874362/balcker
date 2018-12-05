//
//  NYHomeHuanZheInfoVoiceCell.m
//  Doctor
//
//  Created by niuyao on 2018/12/5.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYHomeHuanZheInfoVoiceCell.h"

@implementation NYHomeHuanZheInfoVoiceCell

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
    UIView * bgView = [[UIView alloc] init];
    bgView.backgroundColor = BGCOLOR;
    [self.contentView addSubview:bgView];
    
    bgView.sd_layout
    .leftSpaceToView(self.contentView, 15)
    .topSpaceToView(self.contentView, 5)
    .bottomSpaceToView(self.contentView, 5)
    .widthRatioToView(self.contentView, 0.4);
    
    bgView.sd_cornerRadius = @20;
    
}
@end
