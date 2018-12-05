//
//  NYRegisterChoicePictureCell.m
//  Doctor
//
//  Created by niuyao on 2018/12/4.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYRegisterChoicePictureCell.h"

@implementation NYRegisterChoicePictureCell

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
    _pictureIMG = [[UIImageView alloc] init];
    _pictureIMG.image = [UIImage imageNamed:@"placeholderImage"];
    _pictureIMG.userInteractionEnabled = YES;
    _pictureIMG.contentMode = UIViewContentModeScaleAspectFill;
    _pictureIMG.clipsToBounds = YES;
    [self.contentView addSubview:_pictureIMG];
    _pictureIMG.sd_layout
    .leftSpaceToView(self.contentView, 15)
    .centerYEqualToView(self.contentView)
    .widthIs(150)
    .heightEqualToWidth();
    
    _pictureIMG.sd_cornerRadius = @5;
    
    [_pictureIMG addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(clickChoiceImg:)]];
}

- (void)clickChoiceImg:(UIImageView *)img
{
    if (_clickChoicePictureImg) {
        _clickChoicePictureImg();
    }
}
@end
