//
//  NYHomeViewController.m
//  Doctor
//
//  Created by niuyao on 2018/11/13.
//  Copyright © 2018年 joymates. All rights reserved.
//

#import "NYHomeViewController.h"
#import <SDCycleScrollView.h>
#import "NYHomeScrollViewCell.h"
#import "NYHomeNameAndAgeCell.h"
#import "NYHomeSexAndPhoneCell.h"
#import "RankButton.h"
#import "NYHomeEatAndSleepCell.h"
#import "NYHomeInfoTitleCell.h"
#import "NYChuZhenTitleCell.h"
#import "NYHomeInfoCell.h"
#import "NYHomeImgInfoCell.h"
#import "BRPlaceholderTextView.h"
#import "NYHomeVoiceCell.h"
#import "D3RecordButton.h"
#import "NYHomeChoiceImgCell.h"
#import "NYHomeOnlineWenZhenSuccessedViewController.h"
#import "NYLoginViewController.h"
#import "NYBaseNavViewController.h"

#import <TZImageManager.h>
#import <TZVideoPlayerController.h>
#import <TZPhotoPreviewController.h>
#import <TZGifPhotoPreviewController.h>
#import <TZImagePickerController.h>
#import <AssetsLibrary/AssetsLibrary.h>
#define KChoicePicCount 9  //最多选择多少张照片
#define KLineShowPicCount 4 //每一行显示多少张照片


@interface NYHomeViewController ()<UITableViewDelegate,UITableViewDataSource,SDCycleScrollViewDelegate,UITextFieldDelegate,UITextViewDelegate,D3RecordDelegate,AVAudioPlayerDelegate,TZImagePickerControllerDelegate,UIImagePickerControllerDelegate,UINavigationControllerDelegate>
{
    NSMutableArray * _imageUrlStrings; //广告图片数组
    BRPlaceholderTextView * _textView; //症状描述
    
    AVAudioPlayer * _play;
    D3RecordButton * _voiceBtn;
    UILabel * _voiceLabel;

    NSData * _voiceData;
    float  _voiceTime;
    RankButton * _playVoiceBtn;
    
    UIView * _voiceView;
    UIButton * _deletedBtn;

    
    NSMutableArray *_selectedPhotos;
    NSMutableArray *_selectedAssets;
    NSArray * _seletedImgArr;
    UIImage * _nomalImg;

    NSInteger _sex; // 1男  0女
    
    NSArray * _imageUrlArray;
}
@property (nonatomic, strong) UITableView *tableView;
@property (nonatomic,strong) SDCycleScrollView * cycleScrollView;

@property (nonatomic,strong) HHTextField * nameTF;
@property (nonatomic,strong) HHTextField * ageTF;
@property (nonatomic,strong) HHTextField * phoneTF;

@property (nonatomic,strong) HHTextField * eatTF;
@property (nonatomic,strong) NSArray * eatArray;

@property (nonatomic,strong) HHTextField * sleepTF;
@property (nonatomic,strong) NSArray * sleepArray;

@property (nonatomic, strong) UIImagePickerController *imagePickerVc;

@end

@implementation NYHomeViewController

- (SDCycleScrollView *)cycleScrollView
{
    if (_cycleScrollView == nil) {
        _cycleScrollView = [SDCycleScrollView cycleScrollViewWithFrame:CGRectMake(0, 0, NYScreenW, NYScreenW*0.5) delegate:self placeholderImage:[UIImage imageNamed:@"placeholderImage"]];
        _cycleScrollView.delegate = self;
        _cycleScrollView.currentPageDotColor = MAINCOLOR;
        _cycleScrollView.pageDotColor = COLOR_LOW;
        _cycleScrollView.pageControlStyle = SDCycleScrollViewPageContolStyleClassic;
        _cycleScrollView.scrollDirection = UICollectionViewScrollDirectionHorizontal;
        _cycleScrollView.bannerImageViewContentMode = UIViewContentModeScaleAspectFill;
    }
    return _cycleScrollView;
}

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
        _tableView.showsVerticalScrollIndicator = YES;
        _tableView.showsHorizontalScrollIndicator = NO;
    }
    return _tableView;
}
    
- (void)viewDidLoad {
    [super viewDidLoad];
    _imageUrlStrings = [NSMutableArray array];
    _sex = 0;
//    [_imageUrlStrings addObject:@"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1544073545161&di=3c514205ba7563bfaf7b6df56218d2b2&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F00e93901213fb80e9044dd233cd12f2eb83894a9.jpg"];
//    [_imageUrlStrings addObject:@"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1544668347&di=16c9e49c4575733631bb0fd5e67096da&imgtype=jpg&er=1&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F0b7b02087bf40ad15cc629ca5c2c11dfa9eccefd.jpg"];
//    [_imageUrlStrings addObject:@"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1544073628200&di=6fef2a9a620992759aa7bd09f84a331d&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3D4f726ef28013632701e0ca70f9e6ca99%2F63d0f703918fa0ec565796232c9759ee3d6ddb08.jpg"];
    
    self.view.backgroundColor = [UIColor whiteColor];
    self.navigationItem.title = @"吖吖医生";
    [self.tableView registerClass:[UITableViewCell class] forCellReuseIdentifier:@"Cell"];
    [self.tableView registerClass:[NYHomeScrollViewCell class] forCellReuseIdentifier:@"HomeScrollViewCellID"];
    [self.tableView registerClass:[NYHomeNameAndAgeCell class] forCellReuseIdentifier:@"NYHomeNameAndAgeCellID"];
    [self.tableView registerClass:[NYHomeSexAndPhoneCell class] forCellReuseIdentifier:@"NYHomeSexAndPhoneCellID"];
    [self.tableView registerClass:[NYHomeEatAndSleepCell class] forCellReuseIdentifier:@"NYHomeEatAndSleepCellID"];
    [self.tableView registerClass:[NYHomeInfoTitleCell class] forCellReuseIdentifier:@"NYHomeInfoTitleCellID"];
    [self.tableView registerClass:[NYChuZhenTitleCell class] forCellReuseIdentifier:@"TitleCell"];
    [self.tableView registerClass:[NYHomeInfoCell class] forCellReuseIdentifier:@"NYHomeInfoCellID"];
    [self.tableView registerClass:[NYHomeImgInfoCell class] forCellReuseIdentifier:@"NYHomeImgInfoCellID"];
    [self.tableView registerClass:[NYHomeVoiceCell class] forCellReuseIdentifier:@"NYHomeVoiceCellID"];
    [self.tableView registerClass:[NYHomeChoiceImgCell class] forCellReuseIdentifier:@"NYHomeChoiceImgCellID"];
    
    _selectedPhotos = [NSMutableArray array];
    _selectedAssets = [NSMutableArray array];
    _nomalImg = [UIImage imageNamed:@"添加图片"];
    _seletedImgArr = @[_nomalImg];

    
//    [self getAdInfo]; //获取广告图片
    
    [self setupRefresh];
    [self.tableView.mj_header beginRefreshing];

}

//集成刷新控件
- (void)setupRefresh{
    // 1.下拉刷新
    MJRefreshNormalHeader * header = [MJRefreshNormalHeader headerWithRefreshingTarget:self refreshingAction:@selector(getAdInfo)];
    header.lastUpdatedTimeLabel.hidden = YES;
    self.tableView.mj_header = header;
}


#pragma mark - 获取广告图片
- (void)getAdInfo
{
    WEAKSELF
    NSDictionary * dict =@{
                           @"positionId":@"1",
                           @"terminal":@"2",
                           };
//    [SVProgressHUD showWithStatus:nil];
    [PPHTTPRequest postGetAdListInfoWithParameters:dict success:^(id response) {
//        [SVProgressHUD dismiss];
        [self.tableView.mj_header endRefreshing];
        if ([response[@"code"] integerValue] == 0) {
            _imageUrlStrings = response[@"data"][@"adImages"];
            [weakSelf.tableView reloadData];
        }else {
            MYALERT(response[@"msg"]);
        }
    } failure:^(NSError *error) {
//        [SVProgressHUD dismiss];
        [self.tableView.mj_header endRefreshing];
        MYALERT(@"请求失败");
    }];

}
    
- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 5;
}
    
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    if (section == 0) {
        if (_imageUrlStrings.count == 0) {
            return 0;
        }
        return 1;
    }else if (section == 1){
        return 2;
    }else if (section == 2){
        return 1;
    }else if (section == 3){
        return 4;
    }else if (section == 4){
        return 3;
    }
    return 1;
}
    
- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (indexPath.section == 0) {
        if (indexPath.row == 0) {
            return NYScreenW*0.5;
        }
    }else if (indexPath.section == 1){
        return 50;
    }else if (indexPath.section == 2){
        return 50;
    }else if (indexPath.section == 3){
        if (indexPath.row == 0 || indexPath.row == 2) {
            return 50;
        }else if (indexPath.row == 1){
            return 120;
        }else if (indexPath.row == 3){
            return 200;
        }
    }else if (indexPath.section == 4){
        if (indexPath.row == 0) {
            return 50;
        }else if (indexPath.row == 1){
            CGFloat PIC_WIDTH = (NYScreenW-(15*4))*0.33;
            if (_seletedImgArr.count == 10) {
                NSInteger row = _seletedImgArr.count / 3;
                return (PIC_WIDTH +15) * row + 15;
            }else{
                NSInteger row = _seletedImgArr.count / 3;
                if (_seletedImgArr.count % 3 == 0) {
                    return (PIC_WIDTH+15)*row+15;
                }else{
                    return (PIC_WIDTH+15)*(row+1)+15;
                }
            }
        }else if (indexPath.row == 2){
            return 80;
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
    if (section == 4) {
        return 120;
    }
    return 8;
}
    
-(UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section {
    return [UIView new];
}
    
- (UIView *)tableView:(UITableView *)tableView viewForFooterInSection:(NSInteger)section {
    if (section == 4) {
        UIView * bgView = [[UIView alloc] init];
        bgView.backgroundColor = [UIColor clearColor];
        
        UIButton * doneButton = [UIButton buttonWithType:UIButtonTypeCustom];
        [doneButton setTitle:@"提交" forState:UIControlStateNormal];
        [doneButton setBackgroundColor:MAINCOLOR];
        [doneButton setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
        [bgView addSubview:doneButton];
        
        doneButton.sd_layout
        .leftSpaceToView(bgView, 30)
        .topSpaceToView(bgView, 30)
        .rightSpaceToView(bgView, 30)
        .heightIs(50);
        doneButton.sd_cornerRadius = @5;
        [doneButton addTarget:self action:@selector(clickDoneButton:) forControlEvents:UIControlEventTouchUpInside];
        
        return bgView;
    }
    return [UIView new];
}

#pragma mark - 点击提交按钮

- (void)clickDoneButton:(UIButton *)button
{
    
    if (!ISLOGIN) {
        NYLoginViewController * loginVC = [[NYLoginViewController alloc] init];
        NYBaseNavViewController * vc = [[NYBaseNavViewController alloc] initWithRootViewController:loginVC];
        [self presentViewController:vc animated:YES completion:^{
        }];
        return;
    }

    
    NSString * nameString = [self.nameTF.text stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];

    NSString * ageString = [self.ageTF.text stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];

    NSString * phoneString = [self.phoneTF.text stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];

    NSString * eatString = [self.eatTF.text stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];

    NSString * sleepString = [self.sleepTF.text stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
    //症状描述
    NSString * zhengZhuangString = [_textView.text stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];

    if (nameString.length == 0) {
        MYALERT(@"请输入患者姓名");
        return;
    }
    if (ageString.length == 0) {
        MYALERT(@"请输入患者年龄");
        return;
    }
    if (phoneString.length == 0) {
        MYALERT(@"请输入联系电话");
        return;
    }
    if (phoneString.length != 11) {
        MYALERT(@"手机格式不正确");
        return;
    }
    if (eatString.length == 0) {
        MYALERT(@"请选择饮食情况");
        return;
    }
    if (sleepString.length == 0) {
        MYALERT(@"请选择睡眠情况");
        return;
    }
    if (zhengZhuangString.length == 0) {
        MYALERT(@"请输入症状描述");
        return;
    }

    NSInteger eatCount = 0;
    for (NSDictionary * obj in self.eatArray) {
        if ([obj[@"name"] isEqualToString:eatString]) {
            eatCount = [obj[@"id"] integerValue];
        }
    }
    
    NSInteger sleepCount = 0;
    for (NSDictionary * obj in self.sleepArray) {
        if ([obj[@"name"] isEqualToString:sleepString]) {
            sleepCount = [obj[@"id"] integerValue];
        }
    }

    
    if (_selectedPhotos.count == 0) { //没有图片
        
        NSString * base64String = @"";
        if (_voiceData != nil) {
            base64String = [_voiceData base64EncodedStringWithOptions:NSDataBase64Encoding64CharacterLineLength];
        }
        
        NSDictionary * dict = @{@"age":@([ageString integerValue]),
                                @"characterDescribe":zhengZhuangString,
                                @"diet":@(eatCount),
                                @"gender":@(_sex),
                                @"image":@"",
                                @"name":nameString,
                                @"phone":phoneString,
                                @"sleep":@(sleepCount),
                                @"voiceDescribe":base64String,
                                };
        [SVProgressHUD showWithStatus:nil];
        [SVProgressHUD setDefaultMaskType:(SVProgressHUDMaskTypeClear)];
        
        [PPHTTPRequest postAddWenZhenInfoWithParameters:dict success:^(id response) {
            [SVProgressHUD dismiss];
            if ([response[@"code"] integerValue] == 0) {
                
                //清空
                _nameTF.text = @"";
                _ageTF.text = @"";
                _sex = 0;
                _phoneTF.text = @"";
                _eatTF.text = @"";
                _sleepTF.text = @"";
                _textView.text = @"";
                _voiceData = nil;
                [_selectedPhotos removeAllObjects];
                [_selectedAssets removeAllObjects];
                _seletedImgArr = @[_nomalImg];
                
                
                NYHomeOnlineWenZhenSuccessedViewController * vc = [[NYHomeOnlineWenZhenSuccessedViewController alloc] init];
                vc.hidesBottomBarWhenPushed = YES;
                [self.navigationController pushViewController:vc animated:YES];
            }else{
                MYALERT(response[@"msg"]);
            }
            [self.tableView reloadData];
        } failure:^(NSError *error) {
            [SVProgressHUD dismiss];
            MYALERT(@"提交失败");
        }];
    }else{ //有图片
        [SVProgressHUD showWithStatus:nil];
        [SVProgressHUD setDefaultMaskType:(SVProgressHUDMaskTypeClear)];
        
        WEAKSELF
        [PPHTTPRequest postUploadWithparameters:nil images:_selectedPhotos success:^(id response) {
            if ([response[@"code"] integerValue] == 0) {
                _imageUrlArray = response[@"data"][@"urls"];
                
                NSMutableString * imgString = [NSMutableString string];
                for (int i = 0; i < _imageUrlArray.count; i++) {
                    NSString * imgUrl = _imageUrlArray[i];
                    if (i == (_imageUrlArray.count-1)) {
                        [imgString appendString:imgUrl];
                    }else{
                        [imgString appendString:[NSString stringWithFormat:@"%@,",imgUrl]];
                    }
                }
                
                NSString * base64String = @"";
                if (_voiceData != nil) {
                    base64String = [_voiceData base64EncodedStringWithOptions:NSDataBase64Encoding64CharacterLineLength];
                }

                NSDictionary * dict = @{@"age":@([ageString integerValue]),
                                        @"characterDescribe":zhengZhuangString,
                                        @"diet":@(eatCount),
                                        @"gender":@(_sex),
                                        @"image":imgString,
                                        @"name":nameString,
                                        @"phone":phoneString,
                                        @"sleep":@(sleepCount),
                                        @"voiceDescribe":base64String,
                                        };
                
                [PPHTTPRequest postAddWenZhenInfoWithParameters:dict success:^(id response) {
                    [SVProgressHUD dismiss];
                    if ([response[@"code"] integerValue] == 0) {
                        
                        
                        //清空
                        _nameTF.text = @"";
                        _ageTF.text = @"";
                        _sex = 0;
                        _phoneTF.text = @"";
                        _eatTF.text = @"";
                        _sleepTF.text = @"";
                        _textView.text = nil;
                        _voiceData = nil;
                        [_selectedPhotos removeAllObjects];
                        [_selectedAssets removeAllObjects];
                        _seletedImgArr = @[_nomalImg];
                        
                        
                        NYHomeOnlineWenZhenSuccessedViewController * vc = [[NYHomeOnlineWenZhenSuccessedViewController alloc] init];
                        vc.hidesBottomBarWhenPushed = YES;
                        [self.navigationController pushViewController:vc animated:YES];
                    }else{
                        MYALERT(response[@"msg"]);
                    }
                    [self.tableView reloadData];
                } failure:^(NSError *error) {
                    [SVProgressHUD dismiss];
                    MYALERT(@"提交失败");
                }];
            }else{
                [SVProgressHUD dismiss];
                MYALERT(@"提交失败");
            }
        } failure:^(NSError *error) {
            [SVProgressHUD dismiss];
            MYALERT(@"提交失败");
        }];
    }
    
    

    
    
    
}

    
- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    WEAKSELF
    if (indexPath.section == 0) {
        if (indexPath.row == 0) {
            NYHomeScrollViewCell * cell = [tableView dequeueReusableCellWithIdentifier:@"HomeScrollViewCellID"];
            if (_imageUrlStrings.count != 0) {
                if (_cycleScrollView == nil) {
                    [cell.contentView addSubview:self.cycleScrollView];
                }
                _cycleScrollView.imageURLStringsGroup = [_imageUrlStrings copy];
            }
            return cell;
        }
    }else if (indexPath.section == 1){
        if (indexPath.row == 0) {
            NYHomeNameAndAgeCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYHomeNameAndAgeCellID"];
            self.nameTF = cell.nameTF;
            self.ageTF = cell.ageTF;
            return cell;
        }else if (indexPath.row == 1){
            NYHomeSexAndPhoneCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYHomeSexAndPhoneCellID"];
            self.phoneTF = cell.phoneTF;
            cell.clickManButton = ^(RankButton * _Nonnull setBtn) {
                _sex = 1;
                if (!setBtn.isSelected) {
                    setBtn.selected = !setBtn.selected;
                }
            };
            cell.clickWomanButton = ^(RankButton * _Nonnull setBtn) {
                _sex = 0;
                if (!setBtn.isSelected) {
                    setBtn.selected = !setBtn.selected;
                }
            };
            return cell;
        }
    }else if (indexPath.section == 2){
        if (indexPath.row == 0) {
            NYHomeEatAndSleepCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYHomeEatAndSleepCellID"];
            _eatTF = cell.eatTF;
            _eatTF.delegate = self;

            _sleepTF = cell.sleepTF;
            _sleepTF.delegate = self;
            
            return cell;
        }
    }else if (indexPath.section == 3){
        if (indexPath.row == 0) {
            NYChuZhenTitleCell * cell = [tableView dequeueReusableCellWithIdentifier:@"TitleCell"];
            cell.titleLB.text = @"症状描述";
            return cell;
        }else if (indexPath.row == 1){
            NYHomeInfoCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYHomeInfoCellID"];
            _textView = cell.infoTextView;
            _textView.delegate = self;
            return cell;
        }else if (indexPath.row == 2){
            NYHomeInfoTitleCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYHomeInfoTitleCellID"];
            cell.typeLB.text = @"语音描述（语音描述病情,促进医生诊断）";
            cell.leftIMG.image = [UIImage imageNamed:@"voice_red"];
            return cell;
        }else if (indexPath.row == 3){
            NYHomeVoiceCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYHomeVoiceCellID"];
            _voiceBtn = cell.voiceBtn;
            _voiceLabel = cell.voiceLabel;
            [_voiceBtn initRecord:self maxtime:60];
            
            _voiceView = cell.voiceView;
            _deletedBtn = cell.deleBtn;
            
            if (_voiceData == nil) {
                _voiceView.hidden = YES;
                _deletedBtn.hidden = YES;
            }else{
                _voiceView.hidden = NO;
                _deletedBtn.hidden = NO;
            }
            
            cell.clickVoiceButton = ^{
                [weakSelf clickPlayVoiceBtn];
            };
            cell.clickDeletedVoiceButton = ^{
                _voiceData = nil;
                
                _voiceView.hidden = YES;
                _deletedBtn.hidden = YES;
            };
            return cell;
        }
    }else if (indexPath.section == 4){
        if (indexPath.row == 0) {
            NYChuZhenTitleCell * cell = [tableView dequeueReusableCellWithIdentifier:@"TitleCell"];
            cell.titleLB.text = @"以前检查结果";
            return cell;
        }else if (indexPath.row == 1){
            NYHomeChoiceImgCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYHomeChoiceImgCellID"];
            cell.imageArray = _seletedImgArr;
            cell.clickChoiceImage = ^{
                [weakSelf choiceImg];
            };
            cell.clickDeleteImage = ^(NSInteger tag) {
                [_selectedPhotos removeObjectAtIndex:tag];
                _seletedImgArr = [_selectedPhotos copy];
                NSMutableArray * muArr = [_seletedImgArr mutableCopy];
                [muArr addObject:_nomalImg];
                _seletedImgArr = [muArr copy];
                [weakSelf.tableView reloadData];
            };

            return cell;
        }else if (indexPath.row == 2){
            NYHomeImgInfoCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYHomeImgInfoCellID"];
            return cell;
        }
    }
    
    UITableViewCell * cell = [tableView dequeueReusableCellWithIdentifier:@"Cell"];
    return cell;
}

#pragma mark - AVAudioPlayerDelegate
- (void)audioPlayerDidFinishPlaying:(AVAudioPlayer *)player successfully:(BOOL)flag
{
    
}

#pragma mark - 播放语音
- (void)clickPlayVoiceBtn
{
    NSError *error;
    _play = [[AVAudioPlayer alloc]initWithData:_voiceData error:&error];
    _play.delegate = self;
    NSLog(@"%@",error);
    _play.volume = 1.0f;
    [_play play];
    NSLog(@"yesssssssssss..........%f",_play.duration);
}

#pragma mark - 录音代理方法

- (void)recording:(float) recordTime
{
    _voiceTime = recordTime;
}

-(void)endRecord:(NSData *)voiceData{
    _voiceData = voiceData;
    _voiceLabel.text = @"按住说话";
    
    _voiceView.hidden = NO;
    _deletedBtn.hidden = NO;
}

//不改btn的话这些就不要了
-(void)dragExit{
    _voiceLabel.text = @"按住说话";
}


-(void)dragEnter{
    //    [RecordHUD setImage:@"xiangji.png"];
    _voiceLabel.text = @"送开发送";
}


- (void)textViewDidChange:(UITextView *)textView{
    
    UITextRange *selectedRange = [textView markedTextRange];
    NSString * newText = [textView textInRange:selectedRange]; //获取高亮部分
    if(newText.length>0)
        return;
    
    NSMutableParagraphStyle *paragraphStyle = [[NSMutableParagraphStyle alloc] init];
    paragraphStyle.lineSpacing = 5;// 字体的行间距
    NSDictionary *attributes = @{NSFontAttributeName:[UIFont systemFontOfSize:17],NSParagraphStyleAttributeName:paragraphStyle};
    textView.attributedText = [[NSAttributedString alloc] initWithString:textView.text attributes:attributes];
    
}

- (BOOL)textFieldShouldBeginEditing:(UITextField *)textField
{
    [_nameTF resignFirstResponder];
    [_ageTF resignFirstResponder];
    [_phoneTF resignFirstResponder];
    [_textView resignFirstResponder];

    
    
    if (textField == _eatTF) {
        WEAKSELF
        
        [PPHTTPRequest GetDictionaryDataInfoWithParameters:@"2" success:^(id response) {
            [SVProgressHUD dismiss];
            if ([response[@"code"] integerValue] == 0) {
                self.eatArray = response[@"data"][@"dictionaries"];
                
                NSMutableArray * muArr = [NSMutableArray array];
                for (NSDictionary * dict in self.eatArray) {
                    [muArr addObject:dict[@"name"]];
                }
                
                // 自定义单列字符串
                NSArray *dataSource = [muArr copy];
                [BRStringPickerView showStringPickerWithTitle:@"选择饮食" dataSource:dataSource defaultSelValue:_eatTF.text isAutoSelect:YES themeColor:nil resultBlock:^(id selectValue) {
                    weakSelf.eatTF.text = selectValue;
                } cancelBlock:^{
                    NSLog(@"点击了背景视图或取消按钮");
                }];

            }else{
                MYALERT(response[@"msg"]);
            }
        } failure:^(NSError *error) {
            [SVProgressHUD dismiss];
            MYALERT(@"请求失败");
        }];
        
        
    }else if (textField == _sleepTF) {
        WEAKSELF
        
        [PPHTTPRequest GetDictionaryDataInfoWithParameters:@"1" success:^(id response) {
            [SVProgressHUD dismiss];
            if ([response[@"code"] integerValue] == 0) {
                self.sleepArray = response[@"data"][@"dictionaries"];
                
                NSMutableArray * muArr = [NSMutableArray array];
                for (NSDictionary * dict in self.sleepArray) {
                    [muArr addObject:dict[@"name"]];
                }
                
                // 自定义单列字符串
                NSArray *dataSource = [muArr copy];
                [BRStringPickerView showStringPickerWithTitle:@"选择睡眠" dataSource:dataSource defaultSelValue:_sleepTF.text isAutoSelect:YES themeColor:nil resultBlock:^(id selectValue) {
                    weakSelf.sleepTF.text = selectValue;
                } cancelBlock:^{
                    NSLog(@"点击了背景视图或取消按钮");
                }];

            }else{
                MYALERT(response[@"msg"]);
            }
        } failure:^(NSError *error) {
            [SVProgressHUD dismiss];
            MYALERT(@"请求失败");
        }];

    }
    return NO;
}


#pragma mark - SDCycleScrollViewDelegate
- (void)cycleScrollView:(SDCycleScrollView *)cycleScrollView didSelectItemAtIndex:(NSInteger)index
{
    
}


- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
}


#pragma mark - 选中上传图片
- (void)choiceImg
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
        alert.tag = 1;
        alert.delegate = self;
        [alert show];
    }
    //    else if ([TZImageManager authorizationStatus] == 0) { // 正在弹框询问用户是否允许访问相册，监听权限状态
    //        dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(0.1 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
    //            return [self takePhoto];
    //        });
    //    }
    else { // 调用相机
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
    TZImagePickerController *imagePickerVc = [[TZImagePickerController alloc] initWithMaxImagesCount:KChoicePicCount-_selectedPhotos.count columnNumber:KLineShowPicCount delegate:self pushPhotoPickerVc:YES];
    
#pragma mark - 四类个性化设置，这些参数都可以不传，此时会走默认设置
    imagePickerVc.isSelectOriginalPhoto = NO;
    
    if (KChoicePicCount > 1) {
        // 1.设置目前已经选中的图片数组
        //        imagePickerVc.selectedAssets = _selectedAssets; // 目前已经选中的图片数组
    }
    imagePickerVc.allowTakePicture = YES; // 在内部显示拍照按钮
    
    // 3. 设置是否可以选择视频/图片/原图
    imagePickerVc.allowPickingVideo = NO;
    imagePickerVc.allowPickingImage = YES;
    imagePickerVc.allowPickingOriginalPhoto = NO;
    imagePickerVc.allowPickingGif = NO;
    
    // 4. 照片排列按修改时间升序
    imagePickerVc.sortAscendingByModificationDate = YES;
    
    /// 5. 单选模式,maxImagesCount为1时才生效
    imagePickerVc.showSelectBtn = YES;
    imagePickerVc.allowCrop = NO;
    imagePickerVc.needCircleCrop = NO;
    imagePickerVc.circleCropRadius = 100;
    
    [self presentViewController:imagePickerVc animated:YES completion:nil];
}

- (void)imagePickerController:(UIImagePickerController*)picker didFinishPickingMediaWithInfo:(NSDictionary *)info {
    [picker dismissViewControllerAnimated:YES completion:nil];
    NSString *type = [info objectForKey:UIImagePickerControllerMediaType];
    if ([type isEqualToString:@"public.image"]) {
        
        UIImage *image = [info objectForKey:UIImagePickerControllerOriginalImage];
        //        [_selectedAssets addObject:assetModel.asset];
        [_selectedPhotos addObject:image];
        
        _seletedImgArr = [_selectedPhotos copy];
        NSMutableArray * muArr = [_seletedImgArr mutableCopy];
        [muArr addObject:_nomalImg];
        _seletedImgArr = [muArr copy];
        
        [self.tableView reloadData];
        
    }
}

#pragma mark - TZImagePickerControllerDelegate

// 这个照片选择器会自己dismiss，当选择器dismiss的时候，会执行下面的代理方法
- (void)imagePickerController:(TZImagePickerController *)picker didFinishPickingPhotos:(NSArray *)photos sourceAssets:(NSArray *)assets isSelectOriginalPhoto:(BOOL)isSelectOriginalPhoto {
    
    for (id obj in photos) {
        [_selectedPhotos addObject:obj];
    }
    _seletedImgArr = [_selectedPhotos copy];
    NSMutableArray * muArr = [_seletedImgArr mutableCopy];
    [muArr addObject:_nomalImg];
    _seletedImgArr = [muArr copy];
    
    
    [self.tableView reloadData];
    
}

#pragma mark - 上传图片
- (void)uploadImage{
    
    [SVProgressHUD showWithStatus:nil];
    [SVProgressHUD setDefaultMaskType:(SVProgressHUDMaskTypeClear)];
    
    WEAKSELF
    [PPHTTPRequest postUploadWithparameters:nil images:_selectedPhotos success:^(id response) {
        [SVProgressHUD dismiss];
        if ([response[@"code"] integerValue] == 0) {
            _imageUrlArray = response[@"data"][@"urls"];
        }
    } failure:^(NSError *error) {
        [SVProgressHUD dismiss];
    }];
}

@end
