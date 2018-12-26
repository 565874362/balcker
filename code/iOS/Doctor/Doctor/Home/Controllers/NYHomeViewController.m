//
//  NYHomeViewController.m
//  Doctor
//
//  Created by niuyao on 2018/11/13.
//  Copyright © 2018年 joymates. All rights reserved.
//

#import "NYHomeViewController.h"
#import "NYHomeListCell.h"
#import "NYHomeListModel.h"
#import "NYHomeHuanZheInfoDetailViewController.h"

@interface NYHomeViewController ()<UITableViewDelegate,UITableViewDataSource,DZNEmptyDataSetSource,DZNEmptyDataSetDelegate>
{
    NSInteger _page; //分页
    NSInteger _count; //每页多少数据
    NSMutableArray * _dataArray;
    
}

@property (nonatomic, strong) UITableView *tableView;
@property (nonatomic, strong) MJRefreshAutoFooter *footer;


@end

@implementation NYHomeViewController

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
        _tableView.emptyDataSetSource = self;
        _tableView.emptyDataSetDelegate = self;
        _tableView.estimatedRowHeight = 0;
        _tableView.estimatedSectionHeaderHeight = 0;
        _tableView.estimatedSectionFooterHeight = 0;    }
    return _tableView;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = [UIColor whiteColor];
    self.navigationItem.title = @"患者信息";
    [self.tableView registerClass:[NYHomeListCell class] forCellReuseIdentifier:@"Cell"];
    
    _dataArray = [NSMutableArray array];
    _page = 1;
    _count = 10;
    [self setupRefresh];
    [self.tableView.mj_header beginRefreshing];

    [self checkVersion];
}


//集成刷新控件
- (void)setupRefresh{
    // 1.下拉刷新
    MJRefreshNormalHeader * header = [MJRefreshNormalHeader headerWithRefreshingTarget:self refreshingAction:@selector(loadData)];
    header.lastUpdatedTimeLabel.hidden = YES;
    self.tableView.mj_header = header;
    
    //上拉刷新
    self.footer = [MJRefreshAutoNormalFooter footerWithRefreshingTarget:self refreshingAction:@selector(loadMoreData)];
}

#pragma mark - 获取数据
- (void)loadData
{
    _page = 1;
    NSDictionary * dict = @{@"size":@(_count),
                            @"current":@(_page),
                            };
    [PPHTTPRequest postHomeHuanZheListInfoWithParameters:dict success:^(id response) {
        [self.tableView.mj_header endRefreshing];
        
        if ([response[@"code"] integerValue] == 0) {
            NSArray * listArr = response[@"data"][@"page"][@"records"];
            if (listArr.count >= _count) {
                _page++;
                self.tableView.mj_footer = self.footer;
            }else{
                self.tableView.mj_footer = nil;
            }
            
            [_dataArray removeAllObjects];
            for (NSDictionary *datc in listArr) {
                NYHomeListModel *doctorModel = [NYHomeListModel mj_objectWithKeyValues:datc];
                [_dataArray addObject:doctorModel];
            }
        }else{
            self.tableView.mj_footer = nil;
            MYALERT(@"暂无数据");
        }
        [self.tableView reloadData];
    } failure:^(NSError *error) {
        [self.tableView.mj_header endRefreshing];
        MYALERT(@"获取失败");
    }];
}

#pragma mark - 加载更多数据
- (void)loadMoreData
{
    NSDictionary * dict = @{@"size":@(_count),
                            @"current":@(_page),
                            };
    [PPHTTPRequest postHomeHuanZheListInfoWithParameters:dict success:^(id response) {
        [self.tableView.mj_footer endRefreshing];
        
        if ([response[@"code"] integerValue] == 0) {
            NSArray * listArr = response[@"data"][@"page"][@"records"];
            if (listArr.count >= _count) {
                _page++;
                self.tableView.mj_footer = self.footer;
            }else{
                self.tableView.mj_footer = nil;
            }
            
            for (NSDictionary *datc in listArr) {
                NYHomeListModel *doctorModel = [NYHomeListModel mj_objectWithKeyValues:datc];
                [_dataArray addObject:doctorModel];
            }
        }else{
            self.tableView.mj_footer = nil;
            MYALERT(@"暂无数据");
        }
        [self.tableView reloadData];
    } failure:^(NSError *error) {
        [self.tableView.mj_footer endRefreshing];
        MYALERT(@"获取失败");
    }];
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return _dataArray.count;
}
    
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return 1;
}
    
- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    NYHomeListModel * model = _dataArray[indexPath.section];
    return [self.tableView cellHeightForIndexPath:indexPath model:model keyPath:@"homeListModel" cellClass:[NYHomeListCell class] contentViewWidth:[UIScreen mainScreen].bounds.size.width];
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
    NYHomeListCell * cell = [tableView dequeueReusableCellWithIdentifier:@"Cell"];
    cell.homeListModel = _dataArray[indexPath.section];
    return cell;
}
    
- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
    NYHomeHuanZheInfoDetailViewController * vc = [[NYHomeHuanZheInfoDetailViewController alloc] init];
    NYHomeListModel * model = _dataArray[indexPath.section];
    vc.detailID = model.id;
    vc.hidesBottomBarWhenPushed = YES;
    vc.clickQiangDanSuccessed = ^{
        model.status = @"2";
        [self.tableView reloadData];
    };
    vc.TiJiaoSuccessed = ^{
        model.status = @"3";
        [self.tableView reloadData];
    };
    [self.navigationController pushViewController:vc animated:YES];
    
}

#pragma mark - 空数据代理方法
//- (UIImage *)imageForEmptyDataSet:(UIScrollView *)scrollView
//{
//    return [UIImage imageNamed:@"ic_launcher"];
//}

- (NSAttributedString *)buttonTitleForEmptyDataSet:(UIScrollView *)scrollView forState:(UIControlState)state {
    NSString *text = @"暂无数据";
    NSDictionary *attributes = @{NSFontAttributeName: [UIFont boldSystemFontOfSize:17.0f],
                                 NSForegroundColorAttributeName: [UIColor lightGrayColor]};
    return [[NSAttributedString alloc] initWithString:text attributes:attributes];
}

- (BOOL)emptyDataSetShouldAllowScroll:(UIScrollView *)scrollView
{
    return YES;
}



#pragma mark - 检查版本号
- (void)checkVersion
{
    // 获取本地版本号
    NSString * currentVersion = [NSBundle mainBundle].infoDictionary[@"CFBundleShortVersionString"];
    // 网络请求获取最新版本
    [PPHTTPRequest getCheckAppVersionWithParameters:nil success:^(id response) {
        NSArray * results = response[@"results"];
        if (results && results.count>0)
        {
            NSDictionary * dic = results.firstObject;
            NSString * lineVersion = dic[@"version"];//版本号
            NSString * releaseNotes = dic[@"releaseNotes"];//更新说明
            //NSString * trackViewUrl = dic[@"trackViewUrl"];//链接
            //把版本号转换成数值
            NSArray * array1 = [currentVersion componentsSeparatedByString:@"."];
            NSInteger currentVersionInt = 0;
            if (array1.count == 3)//默认版本号1.0.0类型
            {
                currentVersionInt = [array1[0] integerValue]*100 + [array1[1] integerValue]*10 + [array1[2] integerValue];
            }
            NSArray * array2 = [lineVersion componentsSeparatedByString:@"."];
            NSInteger lineVersionInt = 0;
            if (array2.count == 3)
            {
                lineVersionInt = [array2[0] integerValue]*100 + [array2[1] integerValue]*10 + [array2[2] integerValue];
            }
            if (lineVersionInt > currentVersionInt)//线上版本大于本地版本
            {
                UIAlertController * alert = [UIAlertController alertControllerWithTitle:[NSString stringWithFormat:@"%@%@",@"发现新版本",lineVersion] message:releaseNotes preferredStyle:UIAlertControllerStyleAlert];
                UIAlertAction * ok = [UIAlertAction actionWithTitle:@"取消" style:UIAlertActionStyleDefault handler:nil];
                UIAlertAction * update = [UIAlertAction actionWithTitle:@"更新" style:UIAlertActionStyleDefault handler:^(UIAlertAction * _Nonnull action) {
                    //跳转到App Store
                    NSString *urlStr = [NSString stringWithFormat:@"itms-apps://itunes.apple.com/cn/app/id%@?mt=8",BaiHuaAppID];
                    [[UIApplication sharedApplication] openURL:[NSURL URLWithString:urlStr]];
                }];
                [alert addAction:ok];
                [alert addAction:update];
                [self presentViewController:alert animated:YES completion:nil];
            }
        }
    } failure:^(NSError *error) {
        NSLog(@"版本更新出错，%@",error.description);
    }];
    
}

@end
