//
//  NYCustomTabBarViewController.m
//  Soma
//
//  Created by niuyao on 2018/6/7.
//  Copyright © 2018年 joymates. All rights reserved.
//

#import "NYCustomTabBarViewController.h"
#import "NYBaseNavViewController.h"
#import "NYHomeViewController.h"
#import "NYAnswerViewController.h"
#import "NYMineViewController.h"
#import "NYDoctorListViewController.h"


@interface NYCustomTabBarViewController ()

@end

@implementation NYCustomTabBarViewController

-(instancetype)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil {
    if (self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil]) {
        [self initViewControllers];
    }
    return self;
}

- (void)viewDidLoad {
    [super viewDidLoad];
}

#pragma mark - setvc
-(void)initViewControllers {
    
    NYHomeViewController * homevc = [[NYHomeViewController alloc] init];
    homevc.tabBarItem.image=[UIImage imageNamed:@"home"];
    homevc.tabBarItem.selectedImage=[UIImage imageNamed:@"home_fille"];
//    homevc.tabBarItem.imageInsets=UIEdgeInsetsMake(5, 0, -5,0);
    
    NYBaseNavViewController * homeNav=[[NYBaseNavViewController alloc] initWithRootViewController:homevc];
    homeNav.tabBarItem.tag = 0;
    homeNav.tabBarItem.title = @"首页";
    
    
    NYAnswerViewController * cardVC = [[NYAnswerViewController alloc] init];
//    cardVC.tabBarItem.imageInsets=UIEdgeInsetsMake(5, 0, -5,0);
    cardVC.tabBarItem.image=[UIImage imageNamed:@"menu_coupon"];
    cardVC.tabBarItem.selectedImage=[UIImage imageNamed:@"menu_coupon_hover"];
    
    NYBaseNavViewController * cardNav=[[NYBaseNavViewController alloc] initWithRootViewController:cardVC];
    cardNav.tabBarItem.tag = 1;
    cardNav.tabBarItem.title = @"问答";
    
    NYDoctorListViewController * doctorVC = [[NYDoctorListViewController alloc] init];
//    doctorVC.tabBarItem.imageInsets=UIEdgeInsetsMake(5, 0, -5,0);
    doctorVC.tabBarItem.image=[UIImage imageNamed:@"doctor"];
    doctorVC.tabBarItem.selectedImage=[UIImage imageNamed:@"doctor_fille"];
    
    NYBaseNavViewController * doctorNav=[[NYBaseNavViewController alloc] initWithRootViewController:doctorVC];
    doctorNav.tabBarItem.tag = 2;
    doctorNav.tabBarItem.title = @"医生";

    
    NYMineViewController * mineVC =[[NYMineViewController alloc] init];
    mineVC.tabBarItem.image=[UIImage imageNamed:@"people"];
//    mineVC.tabBarItem.imageInsets=UIEdgeInsetsMake(5, 0, -5,0);
    mineVC.tabBarItem.selectedImage=[UIImage imageNamed:@"people_fill"];
    
    NYBaseNavViewController *mineNav=[[NYBaseNavViewController alloc] initWithRootViewController:mineVC];
    mineNav.tabBarItem.tag = 3;
    mineNav.tabBarItem.title = @"我的";
    
    self.viewControllers=@[homeNav,doctorNav,mineNav];
    
}


@end
