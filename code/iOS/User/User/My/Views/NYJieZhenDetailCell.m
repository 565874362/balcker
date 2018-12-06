//
//  NYJieZhenDetailCell.m
//  Doctor
//
//  Created by niuyao on 2018/12/5.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYJieZhenDetailCell.h"

@implementation NYJieZhenDetailCell

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
    _typeLB = [[UILabel alloc] init];
    _typeLB.textAlignment = NSTextAlignmentLeft;
    _typeLB.textColor = COLOR_LOW;
    [self.contentView addSubview:_typeLB];
    
    _typeLB.sd_layout
    .leftSpaceToView(self.contentView, 40)
    .topSpaceToView(self.contentView, 0)
    .bottomSpaceToView(self.contentView, 0)
    .widthIs(90);
    
    
    _infoLB = [[UILabel alloc] init];
    _infoLB.textAlignment = NSTextAlignmentLeft;
    _infoLB.textColor = COLOR_BIG;
    [self.contentView addSubview:_infoLB];
    
    _infoLB.sd_layout
    .leftSpaceToView(_typeLB, 10)
    .topSpaceToView(self.contentView, 0)
    .bottomSpaceToView(self.contentView, 0)
    .rightSpaceToView(self.contentView, 10);

}
@end
