package com.baihua.yaya.entity.form;

import java.io.Serializable;

/**
 * Author:byd
 * Time:17/12/2018 16:25
 * Description: 问诊提交参数
 */
public class VisitForm implements Serializable {

    public VisitForm() {
    }

    public VisitForm(String age, String characterDescribe, String diet, String gender, String image, String name, String phone, String sleep, String voiceDescribe) {
        this.age = age;
        this.characterDescribe = characterDescribe;
        this.diet = diet;
        this.gender = gender;
        this.image = image;
        this.name = name;
        this.phone = phone;
        this.sleep = sleep;
        this.voiceDescribe = voiceDescribe;
    }

    /**
     * age : 0
     * characterDescribe : string
     * diet : 0
     * gender : 0
     * image : string
     * name : string
     * phone : string
     * sleep : 0
     * voiceDescribe : string
     */


    private String age; // 年龄
    private String characterDescribe; // 症状描述
    private String diet; // diet
    private String gender; // 性别 1 男 0 女
    private String image; // 以前检查结果
    private String name; // 患者名称
    private String phone; // 电话
    private String sleep; // 睡眠
    private String voiceDescribe; // 语音描述

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCharacterDescribe() {
        return characterDescribe;
    }

    public void setCharacterDescribe(String characterDescribe) {
        this.characterDescribe = characterDescribe;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSleep() {
        return sleep;
    }

    public void setSleep(String sleep) {
        this.sleep = sleep;
    }

    public String getVoiceDescribe() {
        return voiceDescribe;
    }

    public void setVoiceDescribe(String voiceDescribe) {
        this.voiceDescribe = voiceDescribe;
    }
}
