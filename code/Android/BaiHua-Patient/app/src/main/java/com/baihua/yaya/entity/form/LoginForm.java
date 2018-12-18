package com.baihua.yaya.entity.form;

import java.io.Serializable;

/**
 * Author:byd
 * Time:14/12/2018 16:25
 * Description: LoginForm
 */
public class LoginForm implements Serializable {

    /**
     * account : string
     * captchaCode : string
     * captchaId : string
     */

    private String account; // 账号 ,
    private String captchaCode; // 验证码 ,
    private String captchaId; // 验证码编号

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }

    public String getCaptchaId() {
        return captchaId;
    }

    public void setCaptchaId(String captchaId) {
        this.captchaId = captchaId;
    }
}
