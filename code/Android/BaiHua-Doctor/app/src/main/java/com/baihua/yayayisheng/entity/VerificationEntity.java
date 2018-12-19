package com.baihua.yayayisheng.entity;

import java.io.Serializable;

/**
 * Author:byd
 * Time:14/12/2018 16:37
 * Description: VerificationEntity
 */
public class VerificationEntity implements Serializable {

    /**
     * verificationCode : 123456
     * captchaId : 9fb34f46a09ec36cd9f806fa3250a6b5
     */

    private String verificationCode;
    private String captchaId;

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getCaptchaId() {
        return captchaId;
    }

    public void setCaptchaId(String captchaId) {
        this.captchaId = captchaId;
    }
}
