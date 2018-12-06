//
//  NYHomeImgInfoCell.m
//  User
//
//  Created by niuyao on 2018/12/6.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYHomeImgInfoCell.h"

@implementation NYHomeImgInfoCell

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
    UILabel * infoLB = [[UILabel alloc] init];
    infoLB.textAlignment = NSTextAlignmentLeft;
    infoLB.textColor = COLOR_LOW;
    infoLB.numberOfLines = 0;
    [self.contentView addSubview:infoLB];
    
    infoLB.sd_layout
    .spaceToSuperView(UIEdgeInsetsMake(5, 15, 5, 15));
    
    [infoLB setMaxNumberOfLinesToShow:0];
    infoLB.isAttributedContent = YES;
    
    infoLB.text = @"上传检查单、患处、病例等图片，以便得到医生更好的答复，照片仅本人与医生可见";
    
    NSString *labelText = infoLB.text;
    NSMutableAttributedString *attributedString = [[NSMutableAttributedString alloc] initWithString:labelText];
    NSMutableParagraphStyle *paragraphStyle = [[NSMutableParagraphStyle alloc] init];
    [paragraphStyle setLineSpacing:5.0];
    [attributedString addAttribute:NSParagraphStyleAttributeName value:paragraphStyle range:NSMakeRange(0, [labelText length])];
    infoLB.attributedText = attributedString;
    [infoLB sizeToFit];
    
}
@end
