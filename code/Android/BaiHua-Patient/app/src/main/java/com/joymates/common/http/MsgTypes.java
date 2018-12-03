package com.joymates.common.http;

/**
 * MsgType
 *
 * @author Administrator
 */
public interface MsgTypes {

    /**
     * 如果什么也不做配置该msg
     */
    int DONOTHING = -1; // 什么也不做

    int DIALOG_GOTO_OK = -2;// 点击提示登陆的按钮

    int DIALOG_UPDATE = -3;// 强制更新

    int DIALOG_EXIT_LOGIN = -4;// 退出登录

    int PPT_SWITCH = -5;// 广告幻灯轮播

    int DIALOG_ALTER_PWD = -6;// 修改密码提示

    int DIALOG_ALTER_LOGIN_ID = -7;// 修改登录账号提示

    int DIALOG_COMPATIBLE_COMPLETE = -8;// 数据迁移完成

    int DIALOG_CANCEL_ORDER = -9;// 取消订单提示框

    // ********通用模块 登陆，注册，重置密码，忘记密码等
    int COMMON_LOGIN_SUCCESS = 0;// 登陆成功
    int COMMON_LOGIN_FAILED = 1;// 登陆失败

    int COMMON_LOGOFF_SUCCESS = 0;// 退出登陆成功
    int COMMON_LOGOFF_FAILED = 1;// 退出登陆失败

    int THIRD_LOGIN_SUCCESS = 5;// 第三方账号登录成功
    int THIRD_LOGIN_FAILED = 6;// 第三方账号登录失败

    int COMMON_REG_SUCCESS = 10;// 注册成功
    int COMMON_REG_FAILED = 11;// 注册失败

    int COMMON_ALTER_PASSWORD_SUCCESS = 20;// 修改密码成功
    int COMMON_ALTER_PASSWORD_FAILED = 21;// 修改密码失败

    int COMMON_ADD_PWD_PROTECTED_SUCCESS = 30; // 设置密保成功
    int COMMON_ADD_PWD_PROTECTED_FAILED = 31; // 设置密保失败

    /**
     * 忘记密码后验证密保成功
     */
    int COMMON_FIND_PWD_SUCCESS = 40; // 忘记密码后验证密保成功
    int COMMON_FIND_PWD_FAILED = 41; // 忘记密码后验证密保失败

    int COMMON_LOG_LOGIN_SUCCESS = 50;// 登录日志添加成功
    int COMMON_LOG_LOGIN_FAILED = 51;// 登录日志添加失败

    int COMMON_LOG_EXCEPTION_UPLOAD_SUCCESS = 60; // 异常日志上传成功
    int COMMON_LOG_EXCEPTION_UPLOAD_FAILED = 61; // 异常日志上传失败

    int COMMON_JPUSH_REG_SUCCESS = 70;// jpush注册成功通知服务器成功
    int COMMON_JPUSH_REG_FAILED = 71;// jpush注册成功通知服务器失败

    int COMMON_GET_USERID_SUCCESS = 80;// 获取userid成功
    int COMMON_GET_USERID_FAILED = 81;// 获取userid失败

    int FORGET_PWD_SUCCESS = 90;// 忘记密码成功
    int FORGET_PWD_FAILED = 91;//

    int RESET_PWD_SUCCESS = 95;// 重置成功
    int RESET_PWD_FAILED = 96;//


    int VALIDATE_PHONE_SUCCESS = 110;// 验证手机号码是否已经存在
    int VALIDATE_PHONE_FAILED = 111;//

    int INFO_BY_PHONE_SUCCESS = 112;// 根据电话查询会员信息
    int INFO_BY_PHONE_FAILED = 113;//

}
