//
//  NYMyJieZhenListViewController.m
//  Doctor
//
//  Created by niuyao on 2018/12/5.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYMyJieZhenListViewController.h"
#import "NYHomeListCell.h"
#import "NYHomeListModel.h"
#import "NYJieZhenDetailViewController.h"
#import "NYMyJieZhenModel.h"
#import "NYYuYueJiuZhenJiLvCell.h"

@interface NYMyJieZhenListViewController ()<UITableViewDelegate,UITableViewDataSource,DZNEmptyDataSetSource,DZNEmptyDataSetDelegate>
{
    NSInteger _page; //分页
    NSInteger _count; //每页多少数据
    NSMutableArray * _dataArray;
    
}

@property (nonatomic, strong) UITableView *tableView;
@property (nonatomic, strong) MJRefreshAutoFooter *footer;


@end

@implementation NYMyJieZhenListViewController

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

    }
    return _tableView;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = [UIColor whiteColor];
    self.navigationItem.title = @"我的接诊";
    [self.tableView registerClass:[NYHomeListCell class] forCellReuseIdentifier:@"Cell"];
    [self.tableView registerClass:[NYYuYueJiuZhenJiLvCell class] forCellReuseIdentifier:@"NYYuYueJiuZhenJiLvCellID"];
    
    _dataArray = [NSMutableArray array];
    _page = 1;
    _count = 10;
    [self setupRefresh];
    [self.tableView.mj_header beginRefreshing];

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
    [PPHTTPRequest postDoctorJieZhenListInfoWithParameters:dict success:^(id response) {
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
                NYMyJieZhenModel *doctorModel = [NYMyJieZhenModel mj_objectWithKeyValues:datc];
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
    [PPHTTPRequest postDoctorJieZhenListInfoWithParameters:dict success:^(id response) {
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
                NYMyJieZhenModel *doctorModel = [NYMyJieZhenModel mj_objectWithKeyValues:datc];
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
    return 135;
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
    NYYuYueJiuZhenJiLvCell * cell = [tableView dequeueReusableCellWithIdentifier:@"NYYuYueJiuZhenJiLvCellID"];
    NYMyJieZhenModel * model = _dataArray[indexPath.section];
    cell.jieZhenModel = model;
    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
    NYMyJieZhenModel * model = _dataArray[indexPath.section];

    NYJieZhenDetailViewController * vc = [[NYJieZhenDetailViewController alloc] init];
    vc.jieZhenModel = model;
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


@end
