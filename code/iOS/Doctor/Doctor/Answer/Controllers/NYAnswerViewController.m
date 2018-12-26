//
//  NYAnswerViewController.m
//  Doctor
//
//  Created by niuyao on 2018/11/13.
//  Copyright © 2018年 joymates. All rights reserved.
//

#import "NYAnswerViewController.h"
#import "NYBaseNavViewController.h"
#import "NYLoginViewController.h"

@interface NYAnswerViewController ()<RCIMConnectionStatusDelegate,RCIMUserInfoDataSource>

@end

@implementation NYAnswerViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = [UIColor whiteColor];
    self.navigationItem.title = @"咨询";
    [self setDisplayConversationTypes:@[@(ConversationType_PRIVATE)]];
    self.conversationListTableView.tableFooterView = [UIView new];
    self.showConnectingStatusOnNavigatorBar = YES;
    
    UIView * emptyV = [[UIView alloc] init];
    emptyV.backgroundColor = [UIColor clearColor];
    emptyV.frame = CGRectMake(0, 0, 100, 100);
    emptyV.center = CGPointMake(self.view.center.x, self.view.center.y-32);
    
    self.emptyConversationView = emptyV;
    
    [RCIM sharedRCIM].userInfoDataSource = self; //设置代理
    [[RCIM sharedRCIM] setConnectionStatusDelegate:self];

//    self.navigationItem.rightBarButtonItem = [[UIBarButtonItem alloc] initWithTitle:@"聊天" style:UIBarButtonItemStylePlain target:self action:@selector(clickChatItem:)];
    
    
}

- (void)viewWillAppear:(BOOL)animated
{
    [super viewWillAppear:animated];
    
    if ([UserInfo getRongYunToken].length == 0) {
        [self getToken];
    }else{
        [self connectRongYun];
    }
}

#pragma mark - 连接融云
- (void)connectRongYun{
    //连接融云 --- token从app服务器获取
    [[RCIM sharedRCIM] connectWithToken:[UserInfo getRongYunToken] success:^(NSString *userId) {
        NSLog(@"登陆成功。当前登录的用户ID：%@", userId);
        
        RCUserInfo * userInfo = [[RCUserInfo alloc] initWithUserId:userId name:[UserInfo getName] portrait:[UserInfo getPic]];
        
        [[RCIM sharedRCIM] setCurrentUserInfo:userInfo];
        [RCIM sharedRCIM].enableMessageRecall = YES;
        [RCIM sharedRCIM].enableMessageAttachUserInfo = YES;;
        
        
        [self refreshConversationTableViewIfNeeded];
        
    } error:^(RCConnectErrorCode status) {
        NSLog(@"登陆的错误码为:%zi", status);
    } tokenIncorrect:^{
        //token过期或者不正确。
        //如果设置了token有效期并且token过期，请重新请求您的服务器获取新的token
        //如果没有设置token有效期却提示token错误，请检查您客户端和服务器的appkey是否匹配，还有检查您获取token的流程。
        NSLog(@"token错误");
        
        [self getToken];
    }];

}

#pragma mark - 获取token
- (void)getToken
{
    [PPHTTPRequest GetTokenInfoWithParameters:nil success:^(id response) {
        
        if ([response[@"code"] integerValue] == 0) {
            
            [UserInfo setRongYunToken:response[@"data"][@"token"]];
            
            [self connectRongYun];
            
        }else{
            MYALERT(@"连接融云失败");
        }
    } failure:^(NSError *error) {
        MYALERT(@"连接融云失败");
    }];
}

//- (void)clickChatItem:(UIBarButtonItem *)item
//{
//    RCConversationViewController * vc = [[RCConversationViewController alloc] initWithConversationType:ConversationType_PRIVATE targetId:@"10003"];
//    vc.displayUserNameInCell = NO;
//    vc.title = @"患者";
//    vc.hidesBottomBarWhenPushed = YES;
//    [self.navigationController pushViewController:vc animated:YES];
//}

//- (void)getUserInfoWithUserId:(NSString *)userId completion:(void (^)(RCUserInfo *))completion{
//
//    RCUserInfo * userInfo = [[RCUserInfo alloc] initWithUserId:userId name:@"患者" portrait:@"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1542022493925&di=09edf06bdb022029bf79b0de676c43df&imgtype=0&src=http%3A%2F%2Fpic.pptbz.com%2F201506%2F2015070581208537.JPG"];
//
//    completion(userInfo);
//
//
//}

- (void)getUserInfoWithUserId:(NSString *)userId completion:(void (^)(RCUserInfo *))completion{
    
    [PPHTTPRequest GetUserDetailInfoWithParameters:userId success:^(id response) {
        if ([response[@"code"] integerValue] == 0) {
            
            NSString * nameString = response[@"data"][@"info"][@"name"];
            NSString * picString = response[@"data"][@"info"][@"photo"];
            
            if ([NSObject isNilOrNull:nameString]) {
                nameString = @"患者";
            }
            
            if ([NSObject isNilOrNull:picString]) {
                picString = @"";
            }
            
#warning 先判断本地是否保存该用户信息，再请求APP服务器获取用户信息
            RCUserInfo * userInfo = [[RCUserInfo alloc] initWithUserId:userId name:nameString portrait:picString];
            
            completion(userInfo);
        }else{
            //            MYALERT(@"请求失败");
        }
    } failure:^(NSError *error) {
        //        MYALERT(@"请求失败");
    }];
}


- (void)onRCIMConnectionStatusChanged:(RCConnectionStatus)status
{
    if(status == ConnectionStatus_KICKED_OFFLINE_BY_OTHER_CLIENT)
    {
        [[NSUserDefaults standardUserDefaults] setBool:NO forKey:@"ISLOGIN"];
        [[NSUserDefaults standardUserDefaults] synchronize];
        
        [UserInfo setToken:nil];
        [UserInfo setRongYunToken:nil];

        //账号在另外设备登录
        NSLog(@"账号在另外设备上面登录");
        UIAlertController *alert=[UIAlertController alertControllerWithTitle:@"账号在另外设备上面登录" message:nil preferredStyle:UIAlertControllerStyleAlert];
        [alert addAction:[UIAlertAction actionWithTitle:@"确定" style:0 handler:^(UIAlertAction * _Nonnull action) {
        }]];
        [self presentViewController:alert animated:YES completion:nil];

    
        [UIApplication sharedApplication].delegate.window.rootViewController = [[NYBaseNavViewController alloc] initWithRootViewController:[[NYLoginViewController alloc] init]];

    }
}


//重写RCConversationListViewController的onSelectedTableRow事件
- (void)onSelectedTableRow:(RCConversationModelType)conversationModelType
         conversationModel:(RCConversationModel *)model
               atIndexPath:(NSIndexPath *)indexPath {
    RCConversationViewController *conversationVC = [[RCConversationViewController alloc]init];
    conversationVC.hidesBottomBarWhenPushed = YES;
    conversationVC.displayUserNameInCell = NO;
    conversationVC.conversationType = model.conversationType;
    conversationVC.targetId = model.targetId;
    conversationVC.title = model.conversationTitle;
    [self.navigationController pushViewController:conversationVC animated:YES];
}
    
@end
