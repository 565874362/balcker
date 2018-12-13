//
//  NYNameInputCell.m
//  Doctor
//
//  Created by niuyao on 2018/12/4.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYNameInputCell.h"

@implementation NYNameInputCell

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
    _typeLB.textColor = COLOR_BIG;
    _typeLB.textAlignment = NSTextAlignmentLeft;
    [self.contentView addSubview:_typeLB];
    
    _typeLB.sd_layout
    .leftSpaceToView(self.contentView, 15)
    .topSpaceToView(self.contentView, 0)
    .bottomSpaceToView(self.contentView, 0)
    .widthIs(80);
    
    //输入
    _inputTF = [[HHTextField alloc] init];
    _inputTF.returnKeyType = UIReturnKeyDone;
    [self.contentView addSubview:_inputTF];
    
    _inputTF.sd_layout
    .leftSpaceToView(_typeLB, 10)
    .topSpaceToView(self.contentView, 0)
    .rightSpaceToView(self.contentView, 10)
    .bottomSpaceToView(self.contentView, 0);
    
}
@end
