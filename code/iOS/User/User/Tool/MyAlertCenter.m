//
//  MyAlertCenter.m
//  PushTest
//
//  Created by Mac on 16/3/22.
//  Copyright © 2016年 Mac. All rights reserved.
//
#import "MyAlertCenter.h"
#define MAINSCREENWIDTH  [UIScreen mainScreen].bounds.size.width
#define FONT1 [UIFont systemFontOfSize:14]
@implementation MyAlertCenter
+ (MyAlertCenter *) defaultCenter{
    static MyAlertCenter *defaultCenter;
    defaultCenter=[[MyAlertCenter alloc]init];
    return defaultCenter;
}

- (id) init{
    if(!(self=[super init])) return nil;
    myAlertView = [[MyAlert alloc] init];
    myAlertView.alpha = 0.0f;
    active = NO;
    [[UIApplication sharedApplication].keyWindow addSubview:myAlertView];
    return self;
}

- (void) postAlertWithMessage:(NSString*)message{
    //判断当前是否在使用中
    if (!active) {
        [self showAlerts:message];
    }
}

- (void) showAlerts:(NSString *) str {
    //开始使用，设置当前为使用状态
    active = YES;
    myAlertView.alpha = 0;
    [[UIApplication sharedApplication].keyWindow addSubview:myAlertView];
    [myAlertView setMessageText:str];
    myAlertView.center = [UIApplication sharedApplication].keyWindow.center;

    //设置动画
    [UIView beginAnimations:nil context:nil];
    [UIView setAnimationDuration:1];
    [UIView setAnimationDelegate:self];
    [UIView setAnimationDidStopSelector:@selector(animationStep2)];
    myAlertView.alpha = 0.8;
    [UIView commitAnimations];
}

- (void) animationStep2{
    [UIView beginAnimations:nil context:nil];
    [UIView setAnimationDelay:1];
    [UIView setAnimationDelegate:self];
    [UIView setAnimationDidStopSelector:@selector(animationStep3)];
    myAlertView.alpha = 0;
    [UIView commitAnimations];
}


- (void) animationStep3{
    [myAlertView removeFromSuperview];
    active=NO;
}
@end




@implementation MyAlert

CGRect messageRect;
NSString *text;

-(id)init{
    
    self=[super initWithFrame:CGRectMake(0, 0,MAINSCREENWIDTH-40, 10)];
    if (self) {
        messageRect =CGRectInset(self.bounds, MAINSCREENWIDTH-40, 10);
    }
    return self;
}
-(void)setMessageText:(NSString *)message{
    text=message;
    messageRect.size = [message boundingRectWithSize:CGSizeMake(MAINSCREENWIDTH-40, MAXFLOAT)
                                             options:NSStringDrawingTruncatesLastVisibleLine | NSStringDrawingUsesFontLeading | NSStringDrawingUsesLineFragmentOrigin
                                          attributes:@{NSFontAttributeName:FONT1}
                                             context:nil].size;
    self.bounds = CGRectMake(0, 0, messageRect.size.width+20, messageRect.size.height+30);
    messageRect.size.height += 5;
    messageRect.origin.x = 10;
    messageRect.origin.y = 15;
    self.clipsToBounds=YES;
    self.layer.cornerRadius=3;
    [self setNeedsLayout];
    [self setNeedsDisplay];
}

-(void)drawRect:(CGRect)rect
{
    NSDictionary* attrs =@{NSForegroundColorAttributeName:[UIColor whiteColor]
                           ,NSFontAttributeName:FONT1
                           };
    [text drawInRect:messageRect withAttributes:attrs]; //给文本限制个矩形边界，防止矩形拉伸；
}

@end
