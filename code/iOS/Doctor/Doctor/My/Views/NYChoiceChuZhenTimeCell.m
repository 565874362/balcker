//
//  NYChoiceChuZhenTimeCell.m
//  Doctor
//
//  Created by niuyao on 2018/12/3.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYChoiceChuZhenTimeCell.h"

@implementation NYChoiceChuZhenTimeCell

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
    
    titleLB.text = @"出诊时间";
    
    _startTimeTF = [[HHTextField alloc] init];
    [self.contentView addSubview:_startTimeTF];
    
    _startTimeTF.sd_layout
    .leftSpaceToView(titleLB, 10)
    .topSpaceToView(self.contentView, 0)
    .bottomSpaceToView(self.contentView, 0)
    .widthIs(80);
    
    _startTimeTF.placeholder = @"开始时间";

    //至
    UILabel * lb = [[UILabel alloc] init];
    lb.textAlignment = NSTextAlignmentCenter;
    [self.contentView addSubview:lb];
    
    lb.sd_layout
    .leftSpaceToView(_startTimeTF, 10)
    .topSpaceToView(self.contentView, 0)
    .bottomSpaceToView(self.contentView, 0)
    .widthIs(30);
    
    lb.text = @"至";
    
    _endTimeTF = [[HHTextField alloc] init];
    [self.contentView addSubview:_endTimeTF];
    
    _endTimeTF.sd_layout
    .leftSpaceToView(lb, 20)
    .topSpaceToView(self.contentView, 0)
    .bottomSpaceToView(self.contentView, 0)
    .rightSpaceToView(self.contentView, 10);
    
    _endTimeTF.placeholder = @"结束时间";

}

@end
