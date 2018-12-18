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
{
    UITextField * _codeTF;
    JKCountDownButton * _codeButton;
    UITextField * _phoneTF;
    
    NSString * _captchaId;
}

@end

@implementation NYLoginViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = BGCOLOR;
    self.navigationItem.title = @"登录";
    self.edgesForExtendedLayout = UIRectEdgeNone;
    
    _captchaId = @""; //验证码编号
    
    //第一个输入框
    UIView * bgView1 = [[UIView alloc] init];
    bgView1.backgroundColor = [UIColor whiteColor];
    [self.view addSubview:bgView1];
    
    bgView1.sd_layout
    .leftSpaceToView(self.view, 0)
    .topSpaceToView(self.view, 10)
    .rightSpaceToView(self.view, 0)
    .heightIs(50);
    
    //获取验证码
    JKCountDownButton * codeButton = [JKCountDownButton buttonWithType:UIButtonTypeCustom];
    _codeButton = codeButton;
    [codeButton setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
    codeButton.titleLabel.font = FONT(13);
    [codeButton setTitle:@"获取验证码" forState:UIControlStateNormal];
    [bgView1 addSubview:codeButton];
    codeButton.sd_layout
    .centerYEqualToView(bgView1)
    .rightSpaceToView(bgView1, 10)
    .heightIs(35)
    .widthIs(80);
    //    [codeButton setupAutoSizeWithHorizontalPadding:10 buttonHeight:35];
    [codeButton addTarget:self action:@selector(sendCode:) forControlEvents:(UIControlEventTouchUpInside)];
    
    
    
    //竖线
    UIView * lineView = [[UIView alloc] init];
    lineView.backgroundColor = [UIColor blackColor];
    [bgView1 addSubview:lineView];
    lineView.sd_layout
    .centerYEqualToView(bgView1)
    .widthIs(1.5)
    .heightIs(15)
    .rightSpaceToView(codeButton, 5);
    
    //手机号输入框
    UITextField * phoneTF = [[UITextField alloc] init];
    //    phoneTF.clearButtonMode = UITextFieldViewModeWhileEditing;
    phoneTF.keyboardType = UIKeyboardTypeNumberPad;
    _phoneTF = phoneTF;
    [bgView1 addSubview:phoneTF];
    phoneTF.sd_layout
    .leftSpaceToView(bgView1, 15)
    .topSpaceToView(bgView1, 0)
    .rightSpaceToView(lineView, 5)
    .bottomSpaceToView(bgView1, 0);
    phoneTF.placeholder = @"手机号码";
    
    
    //第二个输入框
    UIView * bgView2 = [[UIView alloc] init];
    bgView2.backgroundColor = [UIColor whiteColor];
    [self.view addSubview:bgView2];
    
    bgView2.sd_layout
    .leftSpaceToView(self.view, 0)
    .topSpaceToView(bgView1, 2)
    .rightSpaceToView(self.view, 0)
    .heightIs(50);
    
    //验证码输入
    UITextField * codeTF = [[UITextField alloc] init];
    codeTF.clearButtonMode = UITextFieldViewModeWhileEditing;
    codeTF.keyboardType = UIKeyboardTypeNumberPad;
    _codeTF = codeTF;
    [bgView2 addSubview:codeTF];
    codeTF.sd_layout
    .leftSpaceToView(bgView2, 15)
    .topSpaceToView(bgView2, 0)
    .rightSpaceToView(bgView2, 15)
    .bottomSpaceToView(bgView2, 0);
    codeTF.placeholder = @"输入验证码";
    
    
    UIButton * loginButton = [UIButton buttonWithType:UIButtonTypeCustom];
    [loginButton setTitle:@"登录" forState:UIControlStateNormal];
    [loginButton setBackgroundColor:MAINCOLOR];
    [loginButton setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    [self.view addSubview:loginButton];
    
    loginButton.sd_layout
    .leftSpaceToView(self.view, 30)
    .topSpaceToView(bgView2, 50)
    .rightSpaceToView(self.view, 30)
    .heightIs(50);
    [loginButton addTarget:self action:@selector(clickLoginButton:) forControlEvents:UIControlEventTouchUpInside];
    
    UIBarButtonItem * leftItem = [[UIBarButtonItem alloc] initWithImage:[UIImage imageNamed:@"Common_back_n"] style:UIBarButtonItemStylePlain target:self action:@selector(clickLeftBackItem:)];
    self.navigationItem.leftBarButtonItem = leftItem;
    
}

- (void)clickLeftBackItem:(UIBarButtonItem *)item
{
    [self dismissViewControllerAnimated:YES completion:^{
    }];
}
#pragma mark - 点击登录按钮
- (void)clickLoginButton:(UIButton *)button
{
    [_phoneTF resignFirstResponder];
    [_codeTF resignFirstResponder];
    WEAKSELF
    NSString * phone = [_phoneTF.text stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
    if (phone.length == 0) {
        MYALERT(@"请输入手机号");
        return;
    }
    NSString * code = [_codeTF.text stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
    if (code.length == 0) {
        MYALERT(@"请输入验证码");
        return;
    }
    NSDictionary * dict =@{
                           @"account":phone,
                           @"captchaCode":code,
                           @"captchaId":_captchaId
                           };
    [SVProgressHUD showWithStatus:nil];
    [PPHTTPRequest postLoginWithParameters:dict success:^(id response) {
        [SVProgressHUD dismiss];
        if ([response[@"code"] integerValue] == 0) {
            MYALERT(@"登录成功");
            [[NSUserDefaults standardUserDefaults] setBool:YES forKey:@"ISLOGIN"];
            [[NSUserDefaults standardUserDefaults] synchronize];
            
            if (![NSObject isNilOrNull:response[@"data"][@"token"]]) {
                [UserInfo setToken:response[@"data"][@"token"]];
            }

            
            [weakSelf dismissViewControllerAnimated:YES completion:^{
            }];
        }else {
            MYALERT(response[@"msg"]);
        }
    } failure:^(NSError *error) {
        [SVProgressHUD dismiss];
        MYALERT(@"登录失败");
    }];
}

#pragma mark - 获取验证码
- (void)sendCode:(JKCountDownButton *)button
{
    NSString * phone = [_phoneTF.text stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
    if (phone.length == 0) {
        [SVProgressHUD showInfoWithStatus:@"手机号不能为空"];
        return;
    }else if (phone.length != 11){
        [SVProgressHUD showInfoWithStatus:@"手机号不正确"];
        return;
    }
    
    [SVProgressHUD showWithStatus:nil];
    [SVProgressHUD setDefaultMaskType:(SVProgressHUDMaskTypeClear)];
    
    [PPHTTPRequest postGetCodeWithParameters:phone success:^(id response) {
        [SVProgressHUD dismiss];
        if ([response[@"code"] integerValue] == 0) {
            [SVProgressHUD showSuccessWithStatus:@"验证码已发送"];
            
            _captchaId = response[@"data"][@"captchaId"]; //验证码编号
            
            // 发送验证码
            _codeButton.enabled = NO;
            [_codeButton startCountDownWithSecond:60];
            [_codeButton countDownChanging:^NSString *(JKCountDownButton *countDownButton,NSUInteger second) {
                NSString *title = [NSString stringWithFormat:@"%zd",second];
                return title;
            }];
            [_codeButton countDownFinished:^NSString *(JKCountDownButton *countDownButton, NSUInteger second) {
                _codeButton.enabled = YES;
                return @"重新获取";
            }];
        }else{
            [SVProgressHUD showErrorWithStatus:@"请求失败"];
        }
        
    } failure:^(NSError *error) {
        [SVProgressHUD dismiss];
        [SVProgressHUD showErrorWithStatus:@"请求失败"];
    }];
    
}

@end
