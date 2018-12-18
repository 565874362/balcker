//
//  NYHomeVoiceCell.m
//  User
//
//  Created by niuyao on 2018/12/6.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYHomeVoiceCell.h"
#import "D3RecordButton.h"

@implementation NYHomeVoiceCell

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
    
    UIView * bgView = [[UIView alloc] init];
    bgView.backgroundColor = BGCOLOR;
    bgView.userInteractionEnabled = YES;
    [self.contentView addSubview:bgView];
    
    self.voiceView = bgView;
    
    bgView.sd_layout
    .leftSpaceToView(self.contentView, 15)
    .topSpaceToView(self.contentView, 10)
    .heightIs(40)
    .widthRatioToView(self.contentView, 0.3);
    
    bgView.sd_cornerRadius = @20;

    [bgView addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(clickVoice:)]];
    
    //删除按钮
    UIButton * deleteBtn = [UIButton buttonWithType:UIButtonTypeCustom];
    self.deleBtn = deleteBtn;
    [deleteBtn setBackgroundImage:[UIImage imageNamed:@"Common_Close_gray_transparent_n"] forState:UIControlStateNormal];
    [self.contentView addSubview:deleteBtn];
    
    deleteBtn.sd_layout
    .leftSpaceToView(self.contentView,5+NYScreenW*0.3)
    .topSpaceToView(self.contentView, 5)
    .widthIs(22)
    .heightIs(22);
    [deleteBtn addTarget:self action:@selector(clickDeletedBtn:) forControlEvents:UIControlEventTouchUpInside];
    
    D3RecordButton * voiceBtn = [D3RecordButton buttonWithType:UIButtonTypeCustom];
    [voiceBtn setBackgroundImage:[UIImage imageNamed:@"voice_blue"] forState:UIControlStateNormal];
    voiceBtn.adjustsImageWhenHighlighted = NO;
    self.voiceBtn = voiceBtn;
    [self.contentView addSubview:voiceBtn];
    voiceBtn.sd_layout
    .centerXEqualToView(self.contentView)
    .topSpaceToView(self.contentView,45)
    .widthIs(90)
    .heightIs(90);
    
    
    UILabel * voiceLabel = [[UILabel alloc] init];
    _voiceLabel = voiceLabel;
    voiceLabel.textAlignment = NSTextAlignmentCenter;
    voiceLabel.text = @"按住说话";
    voiceLabel.font = [UIFont systemFontOfSize:17];
    voiceLabel.textColor = COLOR_BIG;
    [self.contentView addSubview:voiceLabel];
    
    voiceLabel.sd_layout
    .leftSpaceToView(self.contentView,0)
    .topSpaceToView(voiceBtn,5)
    .rightSpaceToView(self.contentView,0)
    .heightIs(25);
    
}

- (void)clickVoice:(UITapGestureRecognizer *)tap
{
    if (_clickVoiceButton) {
        _clickVoiceButton();
    }
}

#pragma mark - 点击删除按钮
- (void)clickDeletedBtn:(UIButton *)button
{
    if (_clickDeletedVoiceButton) {
        _clickDeletedVoiceButton();
    }
}
@end
