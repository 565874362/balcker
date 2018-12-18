//
//  NYDoctorSearchViewController.m
//  User
//
//  Created by niuyao on 2018/12/13.
//  Copyright © 2018 joymates. All rights reserved.
//

#import "NYDoctorSearchViewController.h"
#import "NYDoctorListCell.h"
#import "NYDoctorModel.h"
#import "NYDoctorInfoDetailViewController.h"


@interface NYDoctorSearchViewController ()<UITableViewDelegate,UITableViewDataSource,UITextFieldDelegate,DZNEmptyDataSetSource,DZNEmptyDataSetDelegate>
{
    NSInteger _page; //分页
    NSInteger _count; //每页多少数据
    NSMutableArray * _dataArray;
    
}

@property (nonatomic, strong) UITableView *tableView;
@property (nonatomic,strong) HHTextField * searchTF;

@property (nonatomic, strong) MJRefreshAutoFooter *footer;

@end

@implementation NYDoctorSearchViewController

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
    [self.tableView registerClass:[NYDoctorListCell class] forCellReuseIdentifier:@"Cell"];
    
    [self setSearchView];
    
    _dataArray = [NSMutableArray array];
    _page = 1;
    _count = 10000;
//    [self setupRefresh];
//    [self.tableView.mj_header beginRefreshing];

}

#pragma mark - 获取数据
- (void)loadData
{
    NSString * text = [self.searchTF.text stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
    if (text.length == 0) {
        return;
    }
    
    [self.searchTF resignFirstResponder];
    
    _page = 1;
    NSDictionary * dict = @{@"size":@(_count),
                            @"current":@(_page),
                            @"search":text
                            };
    [PPHTTPRequest postGetDoctorListInfoWithParameters:dict success:^(id response) {
        if ([response[@"code"] integerValue] == 0) {
            NSArray * listArr = response[@"data"][@"page"][@"records"];
            [_dataArray removeAllObjects];
            for (NSDictionary *datc in listArr) {
                NYDoctorModel *doctorModel = [NYDoctorModel mj_objectWithKeyValues:datc];
                [_dataArray addObject:doctorModel];
            }
        }else{
            MYALERT(@"暂无数据");
        }
        [self.tableView reloadData];
    } failure:^(NSError *error) {
        MYALERT(@"获取失败");
    }];
}


////集成刷新控件
//- (void)setupRefresh{
//    // 1.下拉刷新
//    MJRefreshNormalHeader * header = [MJRefreshNormalHeader headerWithRefreshingTarget:self refreshingAction:@selector(loadData)];
//    header.lastUpdatedTimeLabel.hidden = YES;
//    self.tableView.mj_header = header;
//
//    //上拉刷新
//    self.footer = [MJRefreshAutoNormalFooter footerWithRefreshingTarget:self refreshingAction:@selector(loadMoreData)];
//}
//
//#pragma mark - 获取数据
//- (void)loadData
//{
//    _page = 1;
//    NSDictionary * dict = @{@"size":@(_count),
//                            @"current":@(_page),
//                            @"search":@""
//                            };
//    [PPHTTPRequest postGetDoctorListInfoWithParameters:dict success:^(id response) {
//        [self.tableView.mj_header endRefreshing];
//
//        if ([response[@"code"] integerValue] == 0) {
//            NSArray * listArr = response[@"data"][@"page"][@"records"];
//            if (listArr.count >= _count) {
//                _page++;
//                self.tableView.mj_footer = self.footer;
//            }else{
//                self.tableView.mj_footer = nil;
//            }
//
//            [_dataArray removeAllObjects];
//            for (NSDictionary *datc in listArr) {
//                NYDoctorModel *doctorModel = [NYDoctorModel mj_objectWithKeyValues:datc];
//                [_dataArray addObject:doctorModel];
//            }
//        }else{
//            self.tableView.mj_footer = nil;
//            MYALERT(@"暂无数据");
//        }
//        [self.tableView reloadData];
//    } failure:^(NSError *error) {
//        [self.tableView.mj_header endRefreshing];
//        MYALERT(@"获取失败");
//    }];
//}
//
//#pragma mark - 加载更多数据
//- (void)loadMoreData
//{
//    NSDictionary * dict = @{@"size":@(_count),
//                            @"current":@(_page),
//                            @"search":@""
//                            };
//    [PPHTTPRequest postGetDoctorListInfoWithParameters:dict success:^(id response) {
//        [self.tableView.mj_footer endRefreshing];
//
//        if ([response[@"code"] integerValue] == 0) {
//            NSArray * listArr = response[@"data"][@"page"][@"records"];
//            if (listArr.count >= _count) {
//                _page++;
//                self.tableView.mj_footer = self.footer;
//            }else{
//                self.tableView.mj_footer = nil;
//            }
//
//            for (NSDictionary *datc in listArr) {
//                NYDoctorModel *doctorModel = [NYDoctorModel mj_objectWithKeyValues:datc];
//                [_dataArray addObject:doctorModel];
//            }
//        }else{
//            self.tableView.mj_footer = nil;
//            MYALERT(@"暂无数据");
//        }
//        [self.tableView reloadData];
//    } failure:^(NSError *error) {
//        [self.tableView.mj_footer endRefreshing];
//        MYALERT(@"获取失败");
//    }];
//}

#pragma mark - 点击取消搜索
- (void)clickCancelItem:(UIBarButtonItem *)item
{
    [self dismissViewControllerAnimated:NO completion:^{
    }];
}

- (void)setSearchView
{
    //取消按钮
    UIButton * cancelBtn = [UIButton buttonWithType:UIButtonTypeCustom];
    cancelBtn.frame = CGRectMake(0, 0, 40, 35);
    cancelBtn.titleLabel.font = FONT(16);
    cancelBtn.titleLabel.adjustsFontSizeToFitWidth = YES;
    [cancelBtn setTitle:@"取消" forState:UIControlStateNormal];
    [cancelBtn setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    [cancelBtn addTarget:self action:@selector(clickCancelItem:) forControlEvents:UIControlEventTouchUpInside];
    self.navigationItem.rightBarButtonItem = [[UIBarButtonItem alloc] initWithCustomView:cancelBtn];

    //背景色
    UIView * bgView = [[UIView alloc] init];
    bgView.backgroundColor = [UIColor whiteColor];
    bgView.frame = CGRectMake(0, 0, NYScreenW, 34);
    self.navigationItem.titleView = bgView;
    bgView.layer.cornerRadius = 17;
    
    
    
    UIImageView * searchIMG = [[UIImageView alloc] init];
    [bgView addSubview:searchIMG];
    
    searchIMG.sd_layout
    .leftSpaceToView(bgView, 15)
    .centerYEqualToView(bgView)
    .widthIs(22)
    .heightEqualToWidth();
    
    searchIMG.image = [UIImage imageNamed:@"seach"];
    
    
    self.searchTF = [[HHTextField alloc] init];
    self.searchTF.returnKeyType = UIReturnKeySearch;
    self.searchTF.delegate = self;
    self.searchTF.tintColor = MAINCOLOR;
    self.searchTF.font = FONT(16);
    [bgView addSubview:self.searchTF];
    
    self.searchTF.sd_layout
    .leftSpaceToView(searchIMG, 5)
    .topSpaceToView(bgView, 0)
    .rightSpaceToView(bgView, 17)
    .bottomSpaceToView(bgView, 0);
    
    self.searchTF.placeholder = @"医院、科室、擅长";
    
    [self.searchTF becomeFirstResponder];
}

- (BOOL)textFieldShouldReturn:(UITextField *)textField
{
    [self loadData];
    return YES;
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
