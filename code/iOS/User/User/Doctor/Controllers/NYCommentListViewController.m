//
//  NYCommentListViewController.m
//  User
//
//  Created by niuyao on 2018/12/7.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYCommentListViewController.h"
#import "NYDoctorDetialCommentCountCell2.h"
#import "NYDoctorCommentCell2.h"
#import "NYDoctorModel.h"
#import "NYCommentModel.h"

@interface NYCommentListViewController ()<UITableViewDelegate,UITableViewDataSource,DZNEmptyDataSetSource,DZNEmptyDataSetDelegate>
{
    NSInteger _page; //分页
    NSInteger _count; //每页多少数据
    NSMutableArray * _dataArray;
    
    NSInteger _totalCommentCount;
}

@property (nonatomic, strong) UITableView *tableView;
@property (nonatomic, strong) MJRefreshAutoFooter *footer;



@end

@implementation NYCommentListViewController

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
        _tableView.emptyDataSetSource = self;
        _tableView.emptyDataSetDelegate = self;

    }
    return _tableView;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = [UIColor whiteColor];
    self.navigationItem.title = @"评价";
    [self.tableView registerClass:[UITableViewCell class] forCellReuseIdentifier:@"Cell"];
    [self.tableView registerClass:[NYDoctorDetialCommentCountCell2 class] forCellReuseIdentifier:@"NYDoctorDetialCommentCountCellID"];
    [self.tableView registerClass:[NYDoctorCommentCell2 class] forCellReuseIdentifier:@"NYDoctorCommentCellID"];
    
    _totalCommentCount = 0;
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
                            @"doctorId":self.doctorID
                            };
    [PPHTTPRequest postGetDoctorCommentListInfoWithParameters:dict success:^(id response) {
        [self.tableView.mj_header endRefreshing];
        
        if ([response[@"code"] integerValue] == 0) {
            
            _totalCommentCount = [response[@"data"][@"page"][@"total"] integerValue];

            
            NSArray * listArr = response[@"data"][@"page"][@"records"];
            if (listArr.count >= _count) {
                _page++;
                self.tableView.mj_footer = self.footer;
            }else{
                self.tableView.mj_footer = nil;
            }
            
            [_dataArray removeAllObjects];
            for (NSDictionary *datc in listArr) {
                NYCommentModel *commModel = [NYCommentModel mj_objectWithKeyValues:datc];
                [_dataArray addObject:commModel];
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
                            @"doctorId":self.doctorID
                            };
    [PPHTTPRequest postGetDoctorCommentListInfoWithParameters:dict success:^(id response) {
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
                NYCommentModel *commModel = [NYCommentModel mj_objectWithKeyValues:datc];
                [_dataArray addObject:commModel];
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
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    if (_dataArray.count == 0) {
        return 0;
    }
    return _dataArray.count+1;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (indexPath.section == 0){
        if (indexPath.row == 0) {
            return 50;
        }else{
            NYDoctorModel * model = _dataArray[indexPath.row-1];
            return [self.tableView cellHeightForIndexPath:indexPath model:model keyPath:@"commModel" cellClass:[NYDoctorCommentCell2 class] contentViewWidth:[UIScreen mainScreen].bounds.size.width];
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
    if (section == 0) {
        return 0;
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
    if (indexPath.section == 0){
        if (indexPath.row == 0) {
            NYDoctorDetialCommentCountCell2 * cell = [tableView dequeueReusableCellWithIdentifier:@"NYDoctorDetialCommentCountCellID"];
            cell.titleLB.text = [NSString stringWithFormat:@"评价(%zi)",_totalCommentCount];
            return cell;
        }else{
            NYDoctorCommentCell2 * cell = [tableView dequeueReusableCellWithIdentifier:@"NYDoctorCommentCellID"];
            cell.commModel = _dataArray[indexPath.row-1];
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
