//
//  NYDoctorCommentCell.m
//  User
//
//  Created by niuyao on 2018/12/7.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYDoctorCommentCell.h"
#import "NYCommentModel.h"

@interface NYDoctorCommentCell ()
{
    UIImageView * _headerImg;
    UILabel * _phoneLB;
    UILabel * _timeLB;
    UILabel * _contentLB;
    UIView * _lineView;
}
@end

@implementation NYDoctorCommentCell

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
    //头像
    _headerImg = [[UIImageView alloc] init];
    _headerImg.image = [UIImage imageNamed:@"placeholderImage"];
    _headerImg.clipsToBounds = YES;
    [self.contentView addSubview:_headerImg];
    
    _headerImg.sd_layout
    .leftSpaceToView(self.contentView, 30)
    .topSpaceToView(self.contentView, 20)
    .widthIs(60)
    .heightEqualToWidth();
    
    _headerImg.sd_cornerRadius = @30;

    //手机号
    _phoneLB = [[UILabel alloc] init];
    _phoneLB.textAlignment = NSTextAlignmentLeft;
    _phoneLB.textColor = COLOR_BIG;
    [self.contentView addSubview:_phoneLB];
    
    _phoneLB.sd_layout
    .leftSpaceToView(_headerImg, 10)
    .topSpaceToView(self.contentView, 30)
    .heightIs(25);
    
    [_phoneLB setSingleLineAutoResizeWithMaxWidth:NYScreenW*0.5];
    
    //时间
    _timeLB = [[UILabel alloc] init];
    _timeLB.textAlignment = NSTextAlignmentRight;
    _timeLB.font = FONT(15);
    _timeLB.textColor = COLOR_BIG;
    [self.contentView addSubview:_timeLB];
    
    _timeLB.sd_layout
    .rightSpaceToView(self.contentView, 30)
    .topSpaceToView(self.contentView, 30)
    .heightIs(25);
    
    [_timeLB setSingleLineAutoResizeWithMaxWidth:NYScreenW*0.5];

    //内容
    _contentLB = [[UILabel alloc] init];
    _contentLB.textAlignment = NSTextAlignmentLeft;
    _contentLB.numberOfLines = 0;
    _contentLB.textColor = COLOR_BIG;
    [self.contentView addSubview:_contentLB];
    
    _contentLB.sd_layout
    .leftSpaceToView(_headerImg, 10)
    .topSpaceToView(_phoneLB, 10)
    .rightSpaceToView(self.contentView, 30)
    .autoHeightRatio(0);
    
    
    _lineView = [[UIView alloc] init];
    _lineView.backgroundColor = BGCOLOR;
    [self.contentView addSubview:_lineView];
    
    _lineView.sd_layout
    .leftSpaceToView(self.contentView, 30)
    .topSpaceToView(_contentLB, 15)
    .rightSpaceToView(self.contentView, 30)
    .heightIs(1.5);
    
    
    [self setupAutoHeightWithBottomViewsArray:@[_lineView] bottomMargin:10];
    
}

- (void)setCommModel:(NYCommentModel *)commModel
{
    _commModel = commModel;
    
    _phoneLB.text = [commModel.patientAccount stringByReplacingCharactersInRange:NSMakeRange(3, 4) withString:@"****"];
    
    _timeLB.text = [commModel.gmtCreate substringWithRange:NSMakeRange(0, 10)];

    _contentLB.text = commModel.content;
}
@end
