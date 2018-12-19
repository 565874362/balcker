package com.baihua.yayayisheng.entity.form;

import java.io.Serializable;
import java.util.List;

/**
 * Author:byd
 * Time:19/12/2018 11:47
 * Description: RegisterForm
 */
public class RegisterForm implements Serializable {

    /**
     * account : string
     * adepts : [{"describe":"string","name":"string"}]
     * captchaCode : string
     * captchaId : string
     * gender : 0
     * hosId : 0
     * hosName : string
     * identityCard : string
     * name : string
     * offId : 0
     * offName : string
     * photo : string
     * physicianLicence : string
     * positionId : 0
     * positionName : string
     * registrationFee : 0
     */

    private String account; // 账号
    private String captchaCode; //验证码
    private String captchaId; // 验证码编号
    private String gender; // 性别 1 男 0 女
    private String hosId; // 医院编号
    private String hosName; // 医院名称 ,
    private String identityCard; // 身份证 ,
    private String name; // 姓名
    private String offId; // 科室编号
    private String offName; // 科室名称
    private String photo; // 个人照片
    private String physicianLicence; // 医师资格证 ,
    private String positionId; // 职位编号 ,
    private String positionName; // 职位名称 ,
    private String registrationFee; // 挂号费
    private List<AdeptsBean> adepts; // 擅长

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHosId() {
        return hosId;
    }

    public void setHosId(String hosId) {
        this.hosId = hosId;
    }

    public String getHosName() {
        return hosName;
    }

    public void setHosName(String hosName) {
        this.hosName = hosName;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOffId() {
        return offId;
    }

    public void setOffId(String offId) {
        this.offId = offId;
    }

    public String getOffName() {
        return offName;
    }

    public void setOffName(String offName) {
        this.offName = offName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhysicianLicence() {
        return physicianLicence;
    }

    public void setPhysicianLicence(String physicianLicence) {
        this.physicianLicence = physicianLicence;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getRegistrationFee() {
        return registrationFee;
    }

    public void setRegistrationFee(String registrationFee) {
        this.registrationFee = registrationFee;
    }

    public List<AdeptsBean> getAdepts() {
        return adepts;
    }

    public void setAdepts(List<AdeptsBean> adepts) {
        this.adepts = adepts;
    }

    public static class AdeptsBean {
        /**
         * describe : string
         * name : string
         */

        private String describe;
        private String name;

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
