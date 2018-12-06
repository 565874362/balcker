//
//  NYTiShiSuccessedCell.m
//  User
//
//  Created by niuyao on 2018/12/6.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYTiShiSuccessedCell.h"

@implementation NYTiShiSuccessedCell
{
    UIImageView * _headerImg;
}
- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    if (self == [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        [self setup];
        self.selectionStyle = UITableViewCellSelectionStyleNone;
    }
    return self;
}

- (void)setup
{
    
    //头像
    _headerImg = [[UIImageView alloc] init];
    _headerImg.image = [UIImage imageNamed:@"placeholderImage"];
    _headerImg.clipsToBounds = YES;
    [self.contentView addSubview:_headerImg];
    
    _headerImg.sd_layout
    .centerXEqualToView(self.contentView)
    .topSpaceToView(self.contentView, 20)
    .widthIs(80)
    .heightEqualToWidth();
    
    _headerImg.sd_cornerRadius = @40;

    
    UILabel * infoLabel = [[UILabel alloc] init];
    infoLabel.textAlignment = NSTextAlignmentCenter;
    infoLabel.numberOfLines = 0;
    infoLabel.font = [UIFont systemFontOfSize:17];
    infoLabel.textColor = COLOR_LOW;
    [self.contentView addSubview:infoLabel];
    
    infoLabel.sd_layout
    .leftSpaceToView(self.contentView,15)
    .topSpaceToView(_headerImg,15)
    .rightSpaceToView(self.contentView,15)
    .heightIs(80);
    
    infoLabel.text = @"您的病情信息已经成功提交，吖吖医生将把您的病情信息推送给以下医生，请及时关注系统提示。";
    
    NSString *labelText = infoLabel.text;
    NSMutableAttributedString *attributedString = [[NSMutableAttributedString alloc] initWithString:labelText];
    NSMutableParagraphStyle *paragraphStyle = [[NSMutableParagraphStyle alloc] init];
    [paragraphStyle setLineSpacing:5.0];
    [attributedString addAttribute:NSParagraphStyleAttributeName value:paragraphStyle range:NSMakeRange(0, [labelText length])];
    infoLabel.attributedText = attributedString;
    [infoLabel sizeToFit];

    
}

@end
