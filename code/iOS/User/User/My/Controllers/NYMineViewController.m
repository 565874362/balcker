//
//  NYMineViewController.m
//  Doctor
//
//  Created by niuyao on 2018/11/13.
//  Copyright © 2018年 joymates. All rights reserved.
//

#import "NYMineViewController.h"
#import "NYMyTypeCell.h"
#import "UINavigationBar+Awesome.h"
#import "NYCustomTabBarViewController.h"
#import "NYMyHeaderView.h"
#import "NYMyWenZhenListViewController.h"
#import "NYYuYueJiuZhenListViewController.h"
#import "NYAnswerViewController.h"

#import <TZImagePickerController.h>
#import <TZImageManager.h>

#define NAVBAR_CHANGE_POINT 50

@interface NYMineViewController ()<UITableViewDelegate,UITableViewDataSource,UIImagePickerControllerDelegate, UINavigationControllerDelegate,TZImagePickerControllerDelegate,UIAlertViewDelegate>
{
    UIImageView * _headerImageView;
}
@property (nonatomic, strong) UITableView *tableView;
@property (nonatomic, strong) UIImagePickerController *imagePickerVc;


@end

@implementation NYMineViewController

- (void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    
    [self scrollViewDidScroll:self.tableView];
    [self.navigationController.navigationBar setShadowImage:[UIImage new]];
    
}
- (void)viewWillDisappear:(BOOL)animated{
    [super viewWillDisappear:animated];
    [self.navigationController.navigationBar lt_reset];
    
}

- (void)scrollViewDidScroll:(UIScrollView *)scrollView
{
    NSLog(@"%f",scrollView.contentOffset.y);
    if (scrollView.contentOffset.y <= 0) {
        scrollView.contentOffset = CGPointMake(0, 0);
    }
    
    
    UIColor * color = MAINCOLOR;
    CGFloat offsetY = scrollView.contentOffset.y;
    if (offsetY > NAVBAR_CHANGE_POINT) {
        CGFloat alpha = MIN(1, 1 - ((NAVBAR_CHANGE_POINT + 64 - offsetY) / 64));
        [self.navigationController.navigationBar lt_setBackgroundColor:[color colorWithAlphaComponent:alpha]];
    } else {
        [self.navigationController.navigationBar lt_setBackgroundColor:[color colorWithAlphaComponent:0]];
    }
}

- (UITableView *)tableView {
    if (!_tableView) {
        _tableView = [[UITableView alloc] initWithFrame:CGRectZero style:UITableViewStyleGrouped];
        [self.view addSubview:_tableView];
        _tableView.sd_layout
        .topSpaceToView(self.view, -64)
        .rightSpaceToView(self.view, 0)
        .leftSpaceToView(self.view, 0)
        .bottomSpaceToView(self.view, 0);
        _tableView.delegate = self;
        _tableView.dataSource = self;
        _tableView.separatorInset = UIEdgeInsetsMake(0, 0, 0, 0);
        _tableView.tableFooterView = [UIView new];
        _tableView.showsVerticalScrollIndicator = NO;
        _tableView.showsHorizontalScrollIndicator = NO;
    }
    return _tableView;
}
    
- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = [UIColor whiteColor];
    self.edgesForExtendedLayout = UIRectEdgeNone;
    self.navigationItem.title = @"我的中心";

    [self.tableView registerClass:[NYMyTypeCell class] forCellReuseIdentifier:@"Cell"];
    [self.tableView registerClass:[NYMyHeaderView class] forHeaderFooterViewReuseIdentifier:@"HeaderViewID"];

    [self.navigationController.navigationBar lt_setBackgroundColor:[UIColor clearColor]];

    [self getUserPicture]; //获取用户信息

}

#pragma mark - 获取用户信息
- (void)getUserPicture
{
    [PPHTTPRequest GetUserPictureInfoWithParameters:nil success:^(id response) {
        [SVProgressHUD dismiss];
        if ([response[@"code"] integerValue] == 0) {
            [UserInfo setPic:response[@"data"][@"photo"]];
        }
    } failure:^(NSError *error) {
    }];
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 2;
}
    
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    if (section == 0) {
        return 0;
    }else if (section == 1 ){
        return 3;
    }
    return 0;
}
    
- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
    {
        return 50;
}
    
- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section
{
    if (section == 0) {
        return NYScreenW*0.5;
    }
    return 0.0001f;
}
    
- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section
{
    if (section == 1) {
        return 150;
    }
    return 8;
}
    
-(UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section {
    WEAKSELF
    if (section == 0) {
        NYMyHeaderView * headerView = [tableView dequeueReusableHeaderFooterViewWithIdentifier:@"HeaderViewID"];
        _headerImageView = headerView.headerImg;
        headerView.clickHeader = ^{
            [weakSelf clickHeaderImg];
        };
        return headerView;
    }
    return [UIView new];
}
    
- (UIView *)tableView:(UITableView *)tableView viewForFooterInSection:(NSInteger)section {
    
    if (section == 1) {
        UIView * bgView = [[UIView alloc] init];
        bgView.backgroundColor = [UIColor clearColor];
        
        UIButton * outLogBtn = [UIButton buttonWithType:UIButtonTypeCustom];
        [outLogBtn setBackgroundColor:MAINCOLOR];
        [outLogBtn setTitle:@"退出当前账户" forState:UIControlStateNormal];
        [bgView addSubview:outLogBtn];
        
        outLogBtn.sd_layout
        .leftSpaceToView(bgView, 20)
        .topSpaceToView(bgView, 50)
        .rightSpaceToView(bgView, 20)
        .heightIs(50);
        
        outLogBtn.sd_cornerRadius = @5;
        [outLogBtn addTarget:self action:@selector(clickOutLoginBtn:) forControlEvents:UIControlEventTouchUpInside];
        
        return bgView;
    }

    return [UIView new];
}

#pragma mark - 点击退出登录
- (void)clickOutLoginBtn:(UIButton *)button
{
    
    UIAlertController *alert=[UIAlertController alertControllerWithTitle:@"确认要退出登录吗？" message:nil preferredStyle:UIAlertControllerStyleAlert];
    [alert addAction:[UIAlertAction actionWithTitle:@"取消" style:UIAlertActionStyleCancel handler:^(UIAlertAction * _Nonnull action) {
    }]];
    [alert addAction:[UIAlertAction actionWithTitle:@"确定" style:0 handler:^(UIAlertAction * _Nonnull action) {
        
        [[NSUserDefaults standardUserDefaults] setBool:NO forKey:@"ISLOGIN"];
        [[NSUserDefaults standardUserDefaults] synchronize];
        
        [UserInfo setToken:nil];

        [self.tabBarController setSelectedIndex:0];
        [self.navigationController popToRootViewControllerAnimated:NO];
        
        
        
    }]];
    [self presentViewController:alert animated:YES completion:nil];

//    [UIApplication sharedApplication].delegate.window.rootViewController = [[NYCustomTabBarViewController alloc] init];
}


- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
    {
        NYMyTypeCell * cell = [tableView dequeueReusableCellWithIdentifier:@"Cell"];
        cell.accessoryType = UITableViewCellAccessoryDisclosureIndicator;
        if (indexPath.section == 1) {
            if (indexPath.row == 0) {
                cell.typeLB.text = @"我的问诊";
                cell.leftIMG.image = [UIImage imageNamed:@"visits"];
            }else if (indexPath.row == 1) {
                cell.typeLB.text = @"预约就诊";
                cell.leftIMG.image = [UIImage imageNamed:@"accepts"];
            }else if (indexPath.row == 2) {
                cell.typeLB.text = @"我的咨询";
                cell.leftIMG.image = [UIImage imageNamed:@"consulting_green"];
            }
        }
        return cell;
    }
    
- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
    if (indexPath.section == 1) {
        if (indexPath.row == 0) {
            NYMyWenZhenListViewController * vc = [[NYMyWenZhenListViewController alloc] init];
            vc.hidesBottomBarWhenPushed = YES;
            [self.navigationController pushViewController:vc animated:YES];
        }else if (indexPath.row == 1){
            NYYuYueJiuZhenListViewController * vc = [[NYYuYueJiuZhenListViewController alloc] init];
            vc.hidesBottomBarWhenPushed = YES;
            [self.navigationController pushViewController:vc animated:YES];
        }else if (indexPath.row == 2){
            NYAnswerViewController * vc = [[NYAnswerViewController alloc] init];
            vc.hidesBottomBarWhenPushed = YES;
            [self.navigationController pushViewController:vc animated:YES];
        }
    }
}


#pragma mark - 选择头像
- (void)clickHeaderImg
{
    [self pushImagePickerController];

//    UIAlertController *alert=[UIAlertController alertControllerWithTitle:nil message:nil preferredStyle:UIAlertControllerStyleActionSheet];
//    [alert addAction:[UIAlertAction actionWithTitle:@"相册" style:0 handler:^(UIAlertAction * _Nonnull action) {
//        [self pushImagePickerController];
//    }]];
//    [alert addAction:[UIAlertAction actionWithTitle:@"相机" style:0 handler:^(UIAlertAction * _Nonnull action) {
//        [self takePhoto];
//    }]];
//    [alert addAction:[UIAlertAction actionWithTitle:@"取消" style:UIAlertActionStyleCancel handler:^(UIAlertAction * _Nonnull action) {
//    }]];
//    [self presentViewController:alert animated:YES completion:nil];
}

- (UIImagePickerController *)imagePickerVc {
    if (_imagePickerVc == nil) {
        _imagePickerVc = [[UIImagePickerController alloc] init];
        _imagePickerVc.delegate = self;
        // set appearance / 改变相册选择页的导航栏外观
        _imagePickerVc.navigationBar.barTintColor = self.navigationController.navigationBar.barTintColor;
        _imagePickerVc.navigationBar.tintColor = self.navigationController.navigationBar.tintColor;
        UIBarButtonItem *tzBarItem, *BarItem;
        if (iOS9Later) {
            tzBarItem = [UIBarButtonItem appearanceWhenContainedInInstancesOfClasses:@[[TZImagePickerController class]]];
            BarItem = [UIBarButtonItem appearanceWhenContainedInInstancesOfClasses:@[[UIImagePickerController class]]];
        } else {
            tzBarItem = [UIBarButtonItem appearanceWhenContainedIn:[TZImagePickerController class], nil];
            BarItem = [UIBarButtonItem appearanceWhenContainedIn:[UIImagePickerController class], nil];
        }
        NSDictionary *titleTextAttributes = [tzBarItem titleTextAttributesForState:UIControlStateNormal];
        [BarItem setTitleTextAttributes:titleTextAttributes forState:UIControlStateNormal];
    }
    return _imagePickerVc;
}

- (void)imagePickerController:(TZImagePickerController *)picker didFinishPickingPhotos:(NSArray<UIImage *> *)photos sourceAssets:(NSArray *)assets isSelectOriginalPhoto:(BOOL)isSelectOriginalPhoto infos:(NSArray<NSDictionary *> *)infos {
    UIImage *image = photos[0];
//    _headerImageView.image = image;
    [picker dismissViewControllerAnimated:YES completion:nil];
    
        [self uploadHeadImage:image];
}

- (void)imagePickerController:(UIImagePickerController *)picker didFinishPickingMediaWithInfo:(NSDictionary *)info
{
    UIImage *image = [info objectForKey:UIImagePickerControllerOriginalImage];
//    _headerImageView.image = image;
    [picker dismissViewControllerAnimated:YES completion:nil];
    
        [self uploadHeadImage:image];
}
#pragma mark - 拍照
- (void)takePhoto {
    AVAuthorizationStatus authStatus = [AVCaptureDevice authorizationStatusForMediaType:AVMediaTypeVideo];
    if ((authStatus == AVAuthorizationStatusRestricted || authStatus == AVAuthorizationStatusDenied) && iOS7Later) {
        // 无相机权限 做一个友好的提示
        UIAlertView * alert = [[UIAlertView alloc]initWithTitle:@"无法使用相机" message:@"请在iPhone的""设置-隐私-相机""中允许访问相机" delegate:self cancelButtonTitle:@"取消" otherButtonTitles:@"设置", nil];
        alert.delegate = self;
        [alert show];
        // 拍照之前还需要检查相册权限
    } else if ([TZImageManager authorizationStatus] == 2) { // 已被拒绝，没有相册权限，将无法保存拍的照片
        UIAlertView * alert = [[UIAlertView alloc]initWithTitle:@"无法访问相册" message:@"请在iPhone的""设置-隐私-相册""中允许访问相册" delegate:self cancelButtonTitle:@"取消" otherButtonTitles:@"设置", nil];
        alert.delegate = self;
        alert.tag = 1;
        [alert show];
    } else { // 调用相机
        UIImagePickerControllerSourceType sourceType = UIImagePickerControllerSourceTypeCamera;
        if ([UIImagePickerController isSourceTypeAvailable: UIImagePickerControllerSourceTypeCamera]) {
            self.imagePickerVc.sourceType = sourceType;
            if(iOS8Later) {
                _imagePickerVc.modalPresentationStyle = UIModalPresentationOverCurrentContext;
            }
            [self presentViewController:_imagePickerVc animated:YES completion:nil];
        } else {
            NSLog(@"模拟器中无法打开照相机,请在真机中使用");
        }
    }
}
#pragma mark - 选择照片
- (void)pushImagePickerController {
    
    TZImagePickerController *pvc = [[TZImagePickerController alloc] initWithMaxImagesCount:1 columnNumber:3 delegate:self];
    pvc.allowPickingVideo = NO;
    pvc.allowPickingImage = YES;
    pvc.allowPickingOriginalPhoto = NO;
    pvc.allowPickingGif = NO;
    pvc.delegate = self;
    pvc.showSelectBtn = NO;
    pvc.allowCrop = YES;
    pvc.cropRect = CGRectMake(0, (NYScreenH - NYScreenW)/2, NYScreenW, NYScreenW);
    [self presentViewController:pvc animated:YES completion:nil];
}

#pragma mark - 上传图片
- (void)uploadHeadImage:(UIImage *)image {
    
    [SVProgressHUD showWithStatus:nil];
    [SVProgressHUD setDefaultMaskType:(SVProgressHUDMaskTypeClear)];
    WEAKSELF
    [PPHTTPRequest postUploadWithparameters:nil images:@[image] success:^(id response) {
        if ([response[@"code"] integerValue] == 0) {
            NSDictionary * dict = @{
                                    @"photoUrl":[response[@"data"][@"urls"] firstObject],
                                    };
            [PPHTTPRequest postUserPictureInfoWithParameters:dict success:^(id response) {
                [SVProgressHUD dismiss];
                if ([response[@"code"] integerValue] == 0) {
                    _headerImageView.image = image;
                    [UserInfo setPic:[response[@"data"][@"urls"] firstObject]];
                }else{
                    [SVProgressHUD dismiss];
                    MYALERT(@"上传失败");
                }
            } failure:^(NSError *error) {
                [SVProgressHUD dismiss];
                MYALERT(@"上传失败");
            }];
        }else{
            [SVProgressHUD dismiss];
            MYALERT(@"上传失败");
        }
    } failure:^(NSError *error) {
        [SVProgressHUD dismiss];
        MYALERT(@"上传失败");
    }];
}

@end
