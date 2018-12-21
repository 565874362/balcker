//
//  NYHomeHuanZheInfoVoiceCell.m
//  Doctor
//
//  Created by niuyao on 2018/12/5.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYHomeHuanZheInfoVoiceCell.h"

@implementation NYHomeHuanZheInfoVoiceCell

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
    UIView * bgView = [[UIView alloc] init];
    bgView.backgroundColor = BGCOLOR;
    bgView.userInteractionEnabled = YES;
    [self.contentView addSubview:bgView];
    
    bgView.sd_layout
    .leftSpaceToView(self.contentView, 15)
    .topSpaceToView(self.contentView, 5)
    .bottomSpaceToView(self.contentView, 5)
    .widthRatioToView(self.contentView, 0.4);
    
    bgView.sd_cornerRadius = @20;
    
    [bgView addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(clickVoice:)]];

    UIImageView * voiceImg = [[UIImageView alloc] init];
    voiceImg.image = [UIImage imageNamed:@"voice"];
    [bgView addSubview:voiceImg];
    
    voiceImg.sd_layout
    .leftSpaceToView(bgView, 18)
    .centerYEqualToView(bgView)
    .widthIs(30)
    .heightEqualToWidth();
    
}

- (void)clickVoice:(UITapGestureRecognizer *)tap
{
    if (_clickVoiceButton) {
        _clickVoiceButton();
    }
}

@end
