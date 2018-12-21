//
//  NYAnswerViewController.m
//  Doctor
//
//  Created by niuyao on 2018/11/13.
//  Copyright © 2018年 joymates. All rights reserved.
//

#import "NYAnswerViewController.h"

@interface NYAnswerViewController ()<RCIMUserInfoDataSource,RCIMConnectionStatusDelegate>

@end

@implementation NYAnswerViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = [UIColor whiteColor];
    self.navigationItem.title = @"我的咨询";
    [self setDisplayConversationTypes:@[@(ConversationType_PRIVATE)]];
    self.conversationListTableView.tableFooterView = [UIView new];
    self.showConnectingStatusOnNavigatorBar = YES;
    
    UIView * emptyV = [[UIView alloc] init];
    emptyV.backgroundColor = [UIColor purpleColor];
    emptyV.frame = CGRectMake(0, 0, 100, 100);
    emptyV.center = CGPointMake(self.view.center.x, self.view.center.y-32);
    
    self.emptyConversationView = emptyV;
    
    [RCIM sharedRCIM].userInfoDataSource = self; //设置代理
    [[RCIM sharedRCIM] setConnectionStatusDelegate:self];
    
    //连接融云 --- token从app服务器获取
    [[RCIM sharedRCIM] connectWithToken:@"0tCp+F+Zk29fmmFZTZE2EI74w7tEqV9jM6ZEef70RyJDnKoQBCReyMvqHX7b/9PhysRrkJLgLL7M+fo3EVGajA=="     success:^(NSString *userId) {
        NSLog(@"登陆成功。当前登录的用户ID：%@", userId);
        
        RCUserInfo * userInfo = [[RCUserInfo alloc] initWithUserId:userId name:@"患者" portrait:@"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1542022493925&di=09edf06bdb022029bf79b0de676c43df&imgtype=0&src=http%3A%2F%2Fpic.pptbz.com%2F201506%2F2015070581208537.JPG"];
        
        [[RCIM sharedRCIM] setCurrentUserInfo:userInfo];
        
        [RCIM sharedRCIM].enableMessageRecall = YES;
        [RCIM sharedRCIM].enableMessageAttachUserInfo = YES;//消息携带用户信息
        
    } error:^(RCConnectErrorCode status) {
        NSLog(@"登陆的错误码为:%zi", status);
    } tokenIncorrect:^{
        //token过期或者不正确。
        //如果设置了token有效期并且token过期，请重新请求您的服务器获取新的token
        //如果没有设置token有效期却提示token错误，请检查您客户端和服务器的appkey是否匹配，还有检查您获取token的流程。
        NSLog(@"token错误");
    }];
    
    
    self.navigationItem.rightBarButtonItem = [[UIBarButtonItem alloc] initWithTitle:@"聊天" style:UIBarButtonItemStylePlain target:self action:@selector(clickChatItem:)];
}
    
- (void)clickChatItem:(UIBarButtonItem *)item
{
    
    RCUserInfo * userInfo = [[RCUserInfo alloc] initWithUserId:@"10003" name:@"小病患者" portrait:@"https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2395369128,1437498474&fm=175&app=25&f=JPEG?w=640&h=640&s=DEA02DC5060614EC4915E892030030D3"];
    [[RCIM sharedRCIM] refreshUserInfoCache:userInfo withUserId:@"10003"];
    
    
    
    RCConversationViewController * vc = [[RCConversationViewController alloc] initWithConversationType:ConversationType_PRIVATE targetId:@"10004"];
    vc.displayUserNameInCell = NO;
    vc.title = @"咨询";
    vc.hidesBottomBarWhenPushed = YES;
    [self.navigationController pushViewController:vc animated:YES];
}
    
- (void)getUserInfoWithUserId:(NSString *)userId completion:(void (^)(RCUserInfo *))completion{

#warning 先判断本地是否保存该用户信息，再请求APP服务器获取用户信息
    RCUserInfo * userInfo = [[RCUserInfo alloc] initWithUserId:userId name:@"医生" portrait:@"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1542020711693&di=95bb6baad5a16c7a5d7879b32d77dea5&imgtype=0&src=http%3A%2F%2Fediterupload.eepw.com.cn%2F201809%2F61001537857032.jpg"];

    completion(userInfo);
}
    
- (void)onRCIMConnectionStatusChanged:(RCConnectionStatus)status
{
    if(status == ConnectionStatus_KICKED_OFFLINE_BY_OTHER_CLIENT)
    {
        //账号在另外设备登录
        NSLog(@"账号在另外设备上面登录");
        UIAlertController *alert=[UIAlertController alertControllerWithTitle:@"账号在另外设备上面登录" message:nil preferredStyle:UIAlertControllerStyleAlert];
        [alert addAction:[UIAlertAction actionWithTitle:@"确定" style:0 handler:^(UIAlertAction * _Nonnull action) {
        }]];
        [self presentViewController:alert animated:YES completion:nil];
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
