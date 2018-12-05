//
//  NYLoginViewController.m
//  Doctor
//
//  Created by niuyao on 2018/11/13.
//  Copyright © 2018年 joymates. All rights reserved.
//

#import "NYLoginViewController.h"
#import "NYCustomTabBarViewController.h"

@interface NYLoginViewController ()

@end

@implementation NYLoginViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = [UIColor whiteColor];
    self.navigationItem.title = @"登录";
    
    
    UIButton * loginButton = [UIButton buttonWithType:UIButtonTypeCustom];
    [loginButton setTitle:@"登录" forState:UIControlStateNormal];
    [loginButton setTitleColor:[UIColor orangeColor] forState:UIControlStateNormal];
    [self.view addSubview:loginButton];
    
    loginButton.sd_layout
    .centerXEqualToView(self.view)
    .centerYEqualToView(self.view)
    .widthIs(120)
    .heightIs(35);
    
    [loginButton addTarget:self action:@selector(clickLoginButton:) forControlEvents:UIControlEventTouchUpInside];

    
}

#pragma mark - 点击登录按钮
- (void)clickLoginButton:(UIButton *)button
{
    [UIApplication sharedApplication].delegate.window.rootViewController = [[NYCustomTabBarViewController alloc] init];        
}
    
@end
