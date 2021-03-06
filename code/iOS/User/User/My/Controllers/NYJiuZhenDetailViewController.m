//
//  NYJiuZhenDetailViewController.m
//  User
//
//  Created by niuyao on 2018/12/6.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYJiuZhenDetailViewController.h"
#import "NYChuZhenTitleCell.h"
#import "NYJieZhenDetailCell.h"
#import "NYJieZhenDoctorDetailCell.h"

#import "NYPayInfoModel.h"
#import "NYYuYueJiuZhenModel.h"

#import "ChatKeyBoard.h"

@interface NYJiuZhenDetailViewController ()<UITableViewDelegate,UITableViewDataSource,ChatToolBarDelegate,ChatKeyBoardDelegate, ChatKeyBoardDataSource>
{
    NSString *_content; // 评论的文字
}
@property (nonatomic, strong) UITableView *tableView;

/** 聊天键盘 */
@property (nonatomic, strong) ChatKeyBoard *chatKeyBoard;

@end

@implementation NYJiuZhenDetailViewController

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
        _tableView.tableFooterView = [UIView new];
        _tableView.separatorStyle = UITableViewCellSeparatorStyleNone;
        _tableView.showsVerticalScrollIndicator = YES;
        _tableView.showsHorizontalScrollIndicator = NO;
    }
    return _tableView;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = [UIColor whiteColor];
    self.navigationItem.title = @"就诊详情";
    
    [self.tableView registerClass:[UITableViewCell class] forCellReuseIdentifier:@"Cell"];
    [self.tableView registerClass:[NYChuZhenTitleCell class] forCellReuseIdentifier:@"TitleCell"];
    [self.tableView registerClass:[NYJieZhenDetailCell class] forCellReuseIdentifier:@"JieZhenDetailCellID"];
    [self.tableView registerClass:[NYJieZhenDoctorDetailCell class] forCellReuseIdentifier:@"JieZhenDoctorDetailCellID"];
    
    [self initChatKeyBoard];
}

- (void)initChatKeyBoard
{
    self.chatKeyBoard = [ChatKeyBoard keyBoard];
    self.chatKeyBoard.placeHolder = @"输入评价内容";
    self.chatKeyBoard.delegate = self;
    self.chatKeyBoard.dataSource = self;
    self.chatKeyBoard.chatToolBar.delegate = self;
    self.chatKeyBoard.allowFace = NO;
    self.chatKeyBoard.allowMore = NO;
    self.chatKeyBoard.allowVoice = NO;
    self.chatKeyBoard.associateTableView = nil;
    [self.view addSubview:self.chatKeyBoard];
    
}

#pragma mark - 滚动隐藏键盘
- (void)scrollViewWillBeginDragging:(UIScrollView *)scrollView
{
    [self.chatKeyBoard keyboardDown];
}

#pragma mark - ChatToolBarDelegate

- (void)chatToolBarTextViewDidBeginEditing:(UITextView *)textView
{
}
- (void)chatToolBarSendText:(NSString *)text
{
    [self sendComment];
}
- (void)chatToolBarTextViewDidChange:(UITextView *)textView
{
    _content = [textView.text stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
}
- (void)chatToolBarTextViewDeleteBackward:(RFTextView *)textView
{
}

#pragma mark -  发送评论

- (void)sendComment {
    
    if (_content.length == 0) {
        [SVProgressHUD showInfoWithStatus:@"评论内容不能为空"];
        return;
    }
    
    NSDictionary * dict =@{
                           @"content":_content,
                           @"doctorId":_jiuZhenModel.doctorId,
                           @"regId":_jiuZhenModel.id
                           };
    
    [SVProgressHUD showWithStatus:nil];
    [SVProgressHUD setDefaultMaskType:(SVProgressHUDMaskTypeClear)];

    [PPHTTPRequest postUserCommentDoctorInfoWithParameters:dict success:^(id response) {
        [SVProgressHUD dismiss];
        if ([response[@"code"] integerValue] == 0) {
            MYALERT(@"评论成功");
            [self.navigationController popViewControllerAnimated:YES];
        }else {
            MYALERT(response[@"msg"]);
        }
    } failure:^(NSError *error) {
        [SVProgressHUD dismiss];
        MYALERT(@"评价失败");
    }];
}


- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 3;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    if (section == 0) {
        return 2;
    }else if (section == 1){
        return 6;
    }else if (section == 2){
        return 3;
    }
    return 0;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (indexPath.section == 0) {
        if (indexPath.row == 0) {
            return 50;
        }else if (indexPath.row == 1){
            return 120;
        }
    }else if (indexPath.section == 1){
        return 50;
    }else if (indexPath.section == 2){
        return 50;
    }
    return 0;
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section
{
    return 0.0001f;
}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section
{
    if (section == 2) {
        return 120;
    }
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
            NYChuZhenTitleCell * cell = [tableView dequeueReusableCellWithIdentifier:@"TitleCell"];
            cell.titleLB.text = @"医生信息";
            return cell;
        }else if (indexPath.row == 1){
            NYJieZhenDoctorDetailCell * cell = [tableView dequeueReusableCellWithIdentifier:@"JieZhenDoctorDetailCellID"];
            cell.jiuZhenModel = self.jiuZhenModel;
            return cell;
        }
    }else if (indexPath.section == 1) {
        if (indexPath.row == 0) {
            NYChuZhenTitleCell * cell = [tableView dequeueReusableCellWithIdentifier:@"TitleCell"];
            cell.titleLB.text = @"患者信息";
            return cell;
        }else{
            NYJieZhenDetailCell * cell = [tableView dequeueReusableCellWithIdentifier:@"JieZhenDetailCellID"];
            if (indexPath.row == 1) {
                cell.typeLB.text = @"患者姓名";
                cell.infoLB.text = self.jiuZhenModel.name;
            }else if (indexPath.row == 2){
                cell.typeLB.text = @"年龄";
                cell.infoLB.text = [NSString stringWithFormat:@"%@岁",self.jiuZhenModel.age];
            }else if (indexPath.row == 3){
                cell.typeLB.text = @"性别";
                cell.infoLB.text = [self.jiuZhenModel.gender integerValue]==0?@"女":@"男";
            }else if (indexPath.row == 4){
                cell.typeLB.text = @"联系电话";
                cell.infoLB.text = self.jiuZhenModel.phone;
            }else if (indexPath.row == 5){
                cell.typeLB.text = @"就诊时间";
                cell.infoLB.text = [NSString stringWithFormat:@"%@ %@",self.jiuZhenModel.visitTime,[self.jiuZhenModel.timePart integerValue]==0?@"上午":@"下午"];
            }
            return cell;
        }
    }else if (indexPath.section == 2){
        if (indexPath.row == 0) {
            NYChuZhenTitleCell * cell = [tableView dequeueReusableCellWithIdentifier:@"TitleCell"];
            cell.titleLB.text = @"支付信息";
            return cell;
        }else{
            NYJieZhenDetailCell * cell = [tableView dequeueReusableCellWithIdentifier:@"JieZhenDetailCellID"];
            if (indexPath.row == 1) {
                cell.typeLB.text = @"支付方式";
                cell.infoLB.text = [self.jiuZhenModel.payInfo.type integerValue] == 1?@"微信":@"支付宝";
            }else if (indexPath.row == 2){
                cell.typeLB.text = @"支付时间";
                cell.infoLB.text = self.jiuZhenModel.payInfo.gmtCreate;
            }
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
