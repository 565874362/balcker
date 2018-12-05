//
//  NYMyTypeCell.m
//  Doctor
//
//  Created by niuyao on 2018/12/3.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYMyTypeCell.h"

@implementation NYMyTypeCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    if (self == [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        [self setup];
    }
    return self;
}

- (void)setup
{
    _leftIMG = [[UIImageView alloc] init];
    _leftIMG.contentMode = UIViewContentModeScaleAspectFill;
    _leftIMG.image = [UIImage imageNamed:@"placeholderImage"];
    _leftIMG.clipsToBounds = YES;
    [self.contentView addSubview:_leftIMG];
    
    _leftIMG.sd_layout
    .leftSpaceToView(self.contentView, 15)
    .centerYEqualToView(self.contentView)
    .widthIs(30)
    .heightEqualToWidth();
    
    _typeLB = [[UILabel alloc] init];
    _typeLB.textAlignment = NSTextAlignmentLeft;
    _typeLB.textColor = COLOR_BIG;
    _typeLB.font = FONT(16);
    [self.contentView addSubview:_typeLB];
    
    _typeLB.sd_layout
    .leftSpaceToView(_leftIMG, 8)
    .topSpaceToView(self.contentView, 0)
    .bottomSpaceToView(self.contentView, 0)
    .widthIs(200);
}
@end
