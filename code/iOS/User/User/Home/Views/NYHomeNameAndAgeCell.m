//
//  NYHomeNameAndAgeCell.m
//  User
//
//  Created by niuyao on 2018/12/6.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYHomeNameAndAgeCell.h"

@implementation NYHomeNameAndAgeCell

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
    UIView * bgView1 = [[UIView alloc] init];
    bgView1.backgroundColor = [UIColor whiteColor];
    [self.contentView addSubview:bgView1];
    
    bgView1.sd_layout
    .leftSpaceToView(self.contentView, 0)
    .topSpaceToView(self.contentView, 0)
    .bottomSpaceToView(self.contentView, 0)
    .widthRatioToView(self.contentView, 0.5);
    
    
    UILabel * nameLB = [[UILabel alloc] init];
    nameLB.textColor = COLOR_BIG;
    nameLB.textAlignment = NSTextAlignmentLeft;
    [bgView1 addSubview:nameLB];
    
    nameLB.sd_layout
    .leftSpaceToView(bgView1, 15)
    .topSpaceToView(bgView1, 0)
    .bottomSpaceToView(bgView1, 0)
    .widthIs(50);
    
    nameLB.text = @"姓名";
    
    //姓名输入
    _nameTF = [[HHTextField alloc] init];
    _nameTF.returnKeyType = UIReturnKeyDone;
    _nameTF.textColor = COLOR_BIG;
    _nameTF.keyboardType = UIKeyboardTypeDefault;
    [bgView1 addSubview:_nameTF];
    
    _nameTF.sd_layout
    .leftSpaceToView(nameLB, 5)
    .topSpaceToView(bgView1, 0)
    .rightSpaceToView(bgView1, 5)
    .bottomSpaceToView(bgView1, 0);
    
    _nameTF.placeholder = @"患者姓名";
    
    UIView * bgView2 = [[UIView alloc] init];
    bgView2.backgroundColor = [UIColor whiteColor];
    [self.contentView addSubview:bgView2];
    
    bgView2.sd_layout
    .leftSpaceToView(bgView1, 0)
    .topSpaceToView(self.contentView, 0)
    .bottomSpaceToView(self.contentView, 0)
    .widthRatioToView(self.contentView, 0.5);

    //年龄
    UILabel * ageLB = [[UILabel alloc] init];
    ageLB.textColor = COLOR_BIG;
    ageLB.textAlignment = NSTextAlignmentLeft;
    [bgView2 addSubview:ageLB];
    
    ageLB.sd_layout
    .leftSpaceToView(bgView2, 5)
    .topSpaceToView(bgView2, 0)
    .bottomSpaceToView(bgView2, 0)
    .widthIs(50);
    
    ageLB.text = @"年龄";
    
    //姓名输入
    _ageTF = [[HHTextField alloc] init];
    _ageTF.returnKeyType = UIReturnKeyDone;
    _ageTF.keyboardType = UIKeyboardTypeNumberPad;
    _ageTF.textColor = COLOR_BIG;
    [bgView2 addSubview:_ageTF];
    
    _ageTF.sd_layout
    .leftSpaceToView(ageLB, 5)
    .topSpaceToView(bgView2, 0)
    .rightSpaceToView(bgView2, 5)
    .bottomSpaceToView(bgView2, 0);

    _ageTF.placeholder = @"实际年龄";
}
@end
