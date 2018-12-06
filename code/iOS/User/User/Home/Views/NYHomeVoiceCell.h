//
//  NYHomeVoiceCell.h
//  User
//
//  Created by niuyao on 2018/12/6.
//  Copyright © 2018 joymates. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@class D3RecordButton;

@interface NYHomeVoiceCell : UITableViewCell

@property (nonatomic,strong) D3RecordButton * voiceBtn;

@property (nonatomic, strong) UILabel * voiceLabel;

@property (nonatomic,strong) UIView * voiceView;

@property (nonatomic,strong) UIButton * deleBtn;

@property (nonatomic,copy) void (^clickVoiceButton)(void);
@property (nonatomic,copy) void (^clickDeletedVoiceButton)(void);

@end

NS_ASSUME_NONNULL_END
