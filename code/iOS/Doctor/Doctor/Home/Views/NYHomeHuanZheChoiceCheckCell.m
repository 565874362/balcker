//
//  NYHomeHuanZheChoiceCheckCell.m
//  Doctor
//
//  Created by niuyao on 2018/12/7.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYHomeHuanZheChoiceCheckCell.h"

@implementation NYHomeHuanZheChoiceCheckCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    if (self == [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
//        self.selectionStyle = UITableViewCellSelectionStyleNone;
        self.accessoryType = UITableViewCellAccessoryDisclosureIndicator;
        [self setup];
    }
    return self;
}

- (void)setup
{
    UILabel * infoLB = [[UILabel alloc] init];
    infoLB.textAlignment = NSTextAlignmentLeft;
    infoLB.textColor = COLOR_LOW;
    [self.contentView addSubview:infoLB];
    
    infoLB.sd_layout
    .leftSpaceToView(self.contentView, 15)
    .topSpaceToView(self.contentView, 0)
    .bottomSpaceToView(self.contentView, 0)
    .widthIs(100);
    
    infoLB.text = @"需做检查:";
    
}
@end
