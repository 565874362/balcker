//
//  NYDoctorListViewController.m
//  User
//
//  Created by niuyao on 2018/11/13.
//  Copyright © 2018年 joymates. All rights reserved.
//

#import "NYDoctorListViewController.h"
#import "NYDoctorListCell.h"
#import "NYDoctorModel.h"
#import "NYDoctorInfoDetailViewController.h"
#import "NYDoctorSearchViewController.h"
#import "NYBaseNavViewController.h"

@interface NYDoctorListViewController ()<UITableViewDelegate,UITableViewDataSource,DZNEmptyDataSetSource,DZNEmptyDataSetDelegate>
{
    NSInteger _page; //分页
    NSInteger _count; //每页多少数据
    NSMutableArray * _dataArray;

}
@property (nonatomic, strong) UITableView *tableView;

@property (nonatomic, strong) MJRefreshAutoFooter *footer;


@end

@implementation NYDoctorListViewController

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
    self.navigationItem.title = @"医生列表";
    [self.tableView registerClass:[NYDoctorListCell class] forCellReuseIdentifier:@"Cell"];
    
    [self setSearchView];
    
    
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
                            @"search":@""
                            };
    [PPHTTPRequest postGetDoctorListInfoWithParameters:dict success:^(id response) {
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
                NYDoctorModel *doctorModel = [NYDoctorModel mj_objectWithKeyValues:datc];
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
                            @"search":@""
                            };
    [PPHTTPRequest postGetDoctorListInfoWithParameters:dict success:^(id response) {
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
                NYDoctorModel *doctorModel = [NYDoctorModel mj_objectWithKeyValues:datc];
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



- (void)setSearchView
{
    UIView * topView = [[UIView alloc] init];
    topView.frame = CGRectMake(0, 0, NYScreenW, 50);
    topView.backgroundColor = [UIColor whiteColor];
    self.tableView.tableHeaderView = topView;

    UIView * bgView = [[UIView alloc] init];
    bgView.userInteractionEnabled = YES;
    bgView.backgroundColor = BGCOLOR;
    [topView addSubview:bgView];
    
    bgView.sd_layout
    .spaceToSuperView(UIEdgeInsetsMake(5, 15, 5, 15));
    
    bgView.sd_cornerRadius = @20;
    [bgView addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(clickSearchBgView:)]];
    
    
    
    UIImageView * searchIMG = [[UIImageView alloc] init];
    [bgView addSubview:searchIMG];
    
    searchIMG.sd_layout
    .leftSpaceToView(bgView, 20)
    .centerYEqualToView(bgView)
    .widthIs(22)
    .heightEqualToWidth();
    
    searchIMG.image = [UIImage imageNamed:@"seach"];
    
    UILabel * textLB = [[UILabel alloc] init];
    textLB.textAlignment = NSTextAlignmentLeft;
    textLB.textColor = COLOR_LOW;
    [bgView addSubview:textLB];
    textLB.sd_layout
    .leftSpaceToView(searchIMG, 8)
    .topSpaceToView(bgView, 0)
    .rightSpaceToView(bgView, 10)
    .bottomSpaceToView(bgView, 0);
    
    textLB.text = @"医院、科室、擅长";

}

#pragma mark - 点击搜索
- (void)clickSearchBgView:(UITapGestureRecognizer *)tap
{
    NYDoctorSearchViewController * vc = [[NYDoctorSearchViewController alloc] init];
    NYBaseNavViewController * nav = [[NYBaseNavViewController alloc] initWithRootViewController:vc];
    [self presentViewController:nav animated:NO completion:^{
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
    NYDoctorModel * model = _dataArray[indexPath.section];
    return [self.tableView cellHeightForIndexPath:indexPath model:model keyPath:@"doctorModel" cellClass:[NYDoctorListCell class] contentViewWidth:[UIScreen mainScreen].bounds.size.width];
}
    
- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section
{
    if (section == 0) {
        return 8;
    }
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
    NYDoctorListCell * cell = [tableView dequeueReusableCellWithIdentifier:@"Cell"];
    cell.doctorModel = _dataArray[indexPath.section];
    return cell;
}
    
- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
    NYDoctorInfoDetailViewController * vc = [[NYDoctorInfoDetailViewController alloc] init];
    vc.hidesBottomBarWhenPushed = YES;
    NYDoctorModel * model = _dataArray[indexPath.section];
    vc.doctorID = model.id;
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
