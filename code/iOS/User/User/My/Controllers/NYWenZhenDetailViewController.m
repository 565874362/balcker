//
//  NYWenZhenDetailViewController.m
//  User
//
//  Created by niuyao on 2018/12/7.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYWenZhenDetailViewController.h"
#import "NYMyWenZhenModel.h"
#import "NYHomeHuanZheInfoCell.h"
#import "NYHomeHuanZheInfoTimeCell.h"
#import "NYHomeHuanZheInfoImgCell.h"
#import "NYHomeHuanZheInfoVoiceCell.h"
#import "HUPhotoBrowser.h"

#import "NYWenZhenDetailTopCell.h"
#import "NYAlreadyChoiceCheckCell.h"
#import "NYAlreadyChoiceCheckTitleCell.h"
#import "NYAlreadyChoiceCheckAllPriceCell.h"
#import "NYWenZhenDetailYiZhuCell.h"
#import "NYYuYueJiuZhenViewController.h"

@interface NYWenZhenDetailViewController ()<UITableViewDelegate,UITableViewDataSource>
{
    NYMyWenZhenModel * _model;
}
@property (nonatomic, strong) UITableView *tableView;


@end

@implementation NYWenZhenDetailViewController

- (UITableView *)tableView {
    if (!_tableView) {
        _tableView = [[UITableView alloc] initWithFrame:CGRectZero style:UITableViewStyleGrouped];
        [self.view addSubview:_tableView];
        _tableView.sd_layout
        .topSpaceToView(self.view, 0)
        .rightSpaceToView(self.view, 0)
        .leftSpaceToView(self.view, 0)
        .bottomSpaceToView(self.view, 0);
        _tableView.delegate = self;
        _tableView.dataSource = self;
        _tableView.separatorInset = UIEdgeInsetsMake(0, 0, 0, 0);
        _tableView.separatorStyle = UITableViewCellSeparatorStyleNone;
        _tableView.tableFooterView = [UIView new];
        _tableView.showsVerticalScrollIndicator = YES;
        _tableView.showsHorizontalScrollIndicator = NO;
    }
    return _tableView;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = [UIColor whiteColor];
    self.navigationItem.title = @"问诊详情";
    [self.tableView registerClass:[UITableViewCell class] forCellReuseIdentifier:@"Cell"];
    [self.tableView registerClass:[NYHomeHuanZheInfoCell class] forCellReuseIdentifier:@"HomeHuanZheInfoCellID"];
    [self.tableView registerClass:[NYHomeHuanZheInfoTimeCell class] forCellReuseIdentifier:@"HomeHuanZheInfoTimeCellID"];
    [self.tableView registerClass:[NYHomeHuanZheInfoImgCell class] forCellReuseIdentifier:@"HomeHuanZheInfoImgCellID"];
    [self.tableView registerClass:[NYHomeHuanZheInfoVoiceCell class] forCellReuseIdentifier:@"HomeHuanZheInfoVoiceCellID"];
    [self.tableView registerClass:[NYWenZhenDetailTopCell class] forCellReuseIdentifier:@"NYWenZhenDetailTopCellID"];
    [self.tableView registerClass:[NYAlreadyChoiceCheckCell class] forCellReuseIdentifier:@"NYAlreadyChoiceCheckCellID"];
    [self.tableView registerClass:[NYAlreadyChoiceCheckTitleCell class] forCellReuseIdentifier:@"NYAlreadyChoiceCheckTitleCellID"];
    [self.tableView registerClass:[NYAlreadyChoiceCheckAllPriceCell class] forCellReuseIdentifier:@"NYAlreadyChoiceCheckAllPriceCellID"];
    [self.tableView registerClass:[NYWenZhenDetailYiZhuCell class] forCellReuseIdentifier:@"NYWenZhenDetailYiZhuCellID"];

    
    _model = [[NYMyWenZhenModel alloc] init];
    _model.state = @"0";
    _model.content = @"这几天我家宝宝嘴里起了好多小泡。哭的特别厉害也不敢吃东西，后来去诊所，医生说这是小儿疱疹性口炎，我想问一下！！！这几天我家宝宝嘴里起了好多小泡。哭的特别厉害也不敢吃东西，后来去诊所，医生说这是小儿疱疹性口炎，我想问一下！！！";
    
}



- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 2;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    if (section == 0) {
        return 4;
    }else if (section == 1){
        return 7;
    }
    return 0;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (indexPath.section == 0) {
        if (indexPath.row == 0) {
            return [self.tableView cellHeightForIndexPath:indexPath model:_model keyPath:@"wenZhenModel" cellClass:[NYHomeHuanZheInfoCell class] contentViewWidth:[UIScreen mainScreen].bounds.size.width];
        }else if (indexPath.row == 1){
            return 50;
        }else if (indexPath.row == 2){
            return (NYScreenW-15*4)*0.33+30;
        }else if (indexPath.row == 3){
            return 50;
        }
    }else if (indexPath.section == 1){
        if (indexPath.row == 0) {
            NSString * content = @"初步诊断：这几天我家宝宝嘴里起了好多小泡。哭的特别厉害也不敢吃东西，后来去诊所，医生说这是小儿疱疹性口炎，我想问一下！！！这几天我家宝宝嘴里起了好多小泡。哭的特别厉害也不敢吃东西，后来去诊所，医生说这是小儿疱疹性口炎，我想问一下！！！";
            return [self.tableView cellHeightForIndexPath:indexPath model:content keyPath:@"content" cellClass:[NYWenZhenDetailTopCell class] contentViewWidth:[UIScreen mainScreen].bounds.size.width];
        }else if (indexPath.row == 6){
            NSString * content = @"温馨医嘱：这几天我家宝宝嘴里起了好多小泡。哭的特别厉害也不敢吃东西，后来去诊所，医生说这是小儿疱疹性口炎，我想问一下！！！这几天我家宝宝嘴里起了好多小泡。哭的特别厉害也不敢吃东西，后来去诊所，医生说这是小儿疱疹性口炎，我想问一下！！！";
            return [self.tableView cellHeightForIndexPath:indexPath model:content keyPath:@"content" cellClass:[NYWenZhenDetailYiZhuCell class] contentViewWidth:[UIScreen mainScreen].bounds.size.width];
        }else{
            return 50;
        }
    }

    return 0;
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section
{
    return 0.0001f;
}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section
{
    return 8;
}

-(UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section {
    return [UIView new];
}

- (UIView *)tableView:(UITableView *)tableView viewForFooterInSection:(NSInteger)section {
    return [UIView new];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (indexPath.section == 0) {
        if (indexPath.row == 0) {
            NYHomeHuanZheInfoCell * cell = [tableView dequeueReusableCellWithIdentifier:@"HomeHuanZheInfoCellID"];
            cell.wenZhenModel = _model;
            return cell;
        }else if (indexPath.row == 1){
            NYHomeHuanZheInfoVoiceCell * cell = [tableView dequeueReusableCellWithIdentifier:@"HomeHuanZheInfoVoiceCellID"];
            return cell;
        }else if (indexPath.row == 2){
            NYHomeHuanZheInfoImgCell * cell = [tableView dequeueReusableCellWithIdentifier:@"HomeHuanZheInfoImgCellID"];
            cell.clickImg = ^(NSInteger index, UIImageView * _Nonnull imageView) {
                [HUPhotoBrowser showFromImageView:imageView withImages:@[imageView.image] atIndex:0];
            };
            return cell;
        }else if (indexPath.row == 3){
            NYHomeHuanZheInfoTimeCell * cell = [tableView dequeueReusableCellWithIdentifier:@"HomeHuanZheInfoTimeCellID"];
            cell.wenZhenModel = _model;
            return cell;
        }
    }else if (indexPath.section == 1){
        if (indexPath.row == 0) {
            NYWenZhenDetailTopCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYWenZhenDetailTopCellID"];
            cell.content = nil;
            return cell;
        }else if (indexPath.row == 1){
            NYAlreadyChoiceCheckTitleCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYAlreadyChoiceCheckTitleCellID"];
            return cell;
        }else if (indexPath.row == 5){
            NYAlreadyChoiceCheckAllPriceCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYAlreadyChoiceCheckAllPriceCellID"];
            return cell;
        }else if (indexPath.row == 6){
            NYWenZhenDetailYiZhuCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYWenZhenDetailYiZhuCellID"];
            cell.content = nil;
            cell.clickZiXunBlock = ^{
                RCUserInfo * userInfo = [[RCUserInfo alloc] initWithUserId:@"10003" name:@"小病患者" portrait:@"https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2395369128,1437498474&fm=175&app=25&f=JPEG?w=640&h=640&s=DEA02DC5060614EC4915E892030030D3"];
                [[RCIM sharedRCIM] refreshUserInfoCache:userInfo withUserId:@"10003"];
                
                
                RCConversationViewController * vc = [[RCConversationViewController alloc] initWithConversationType:ConversationType_PRIVATE targetId:@"10004"];
                vc.displayUserNameInCell = NO;
                vc.title = @"医生";
                [self.navigationController pushViewController:vc animated:YES];
            };
            cell.clickJiuZhenBlock = ^{
                NYYuYueJiuZhenViewController * vc = [[NYYuYueJiuZhenViewController alloc] init];
                [self.navigationController pushViewController:vc animated:YES];
            };
            return cell;
        }else{
            NYAlreadyChoiceCheckCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYAlreadyChoiceCheckCellID"];
            return cell;
        }
    }
    
    UITableViewCell * cell = [tableView dequeueReusableCellWithIdentifier:@"Cell"];
    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
}

@end
