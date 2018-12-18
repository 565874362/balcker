//
//  NYHomeHuanZheInfoTimeCell.m
//  Doctor
//
//  Created by niuyao on 2018/12/5.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYHomeHuanZheInfoTimeCell.h"
#import "NYMyWenZhenModel.h"

@interface NYHomeHuanZheInfoTimeCell ()
{
    UILabel * _timeLB;
}
@end

@implementation NYHomeHuanZheInfoTimeCell

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
    //时间
    _timeLB = [[UILabel alloc] init];
    _timeLB.textAlignment = NSTextAlignmentLeft;
    _timeLB.font = FONT(15);
    _timeLB.textColor = COLOR_LOW;
    [self.contentView addSubview:_timeLB];
    
    _timeLB.sd_layout
    .leftSpaceToView(self.contentView, 15)
    .topSpaceToView(self.contentView, 0)
    .rightSpaceToView(self.contentView, 15)
    .bottomSpaceToView(self.contentView, 0);    
}

- (void)setWenZhenModel:(NYMyWenZhenModel *)wenZhenModel
{
    _wenZhenModel = wenZhenModel;
    
    _timeLB.text = [wenZhenModel.gmtCreate substringWithRange:NSMakeRange(0, 16)];

}



@end
