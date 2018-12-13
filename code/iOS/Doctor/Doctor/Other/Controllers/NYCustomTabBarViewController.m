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
    homeNav.tabBarItem.title = @"患者信息";
    
    
    NYAnswerViewController * cardVC = [[NYAnswerViewController alloc] init];
//    cardVC.tabBarItem.imageInsets=UIEdgeInsetsMake(5, 0, -5,0);
    cardVC.tabBarItem.image=[UIImage imageNamed:@"consulting"];
    cardVC.tabBarItem.selectedImage=[UIImage imageNamed:@"consulting_fille"];
    
    NYBaseNavViewController * cardNav=[[NYBaseNavViewController alloc] initWithRootViewController:cardVC];
    cardNav.tabBarItem.tag = 1;
    cardNav.tabBarItem.title = @"咨询";
    
    
    NYMineViewController * mineVC =[[NYMineViewController alloc] init];
    mineVC.tabBarItem.image=[UIImage imageNamed:@"people"];
//    mineVC.tabBarItem.imageInsets=UIEdgeInsetsMake(5, 0, -5,0);
    mineVC.tabBarItem.selectedImage=[UIImage imageNamed:@"people_fill"];
    
    NYBaseNavViewController *mineNav=[[NYBaseNavViewController alloc] initWithRootViewController:mineVC];
    mineNav.tabBarItem.tag = 4;
    mineNav.tabBarItem.title = @"我的";
    
    self.viewControllers=@[homeNav,cardNav,mineNav];
    
}


@end
