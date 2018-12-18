package com.baihua.yaya.entity.form;

import java.io.Serializable;

/**
 * Author:byd
 * Time:18/12/2018 14:11
 * Description: 挂号
 */
public class RegisteredForm implements Serializable {

    public RegisteredForm() {
    }

    public RegisteredForm(String age, String doctorId, String gender, String name, String phone, String scheduleId, String timePart, String visitTime) {
        this.age = age;
        this.doctorId = doctorId;
        this.gender = gender;
        this.name = name;
        this.phone = phone;
        this.scheduleId = scheduleId;
        this.timePart = timePart;
        this.visitTime = visitTime;
    }

    /**
     * age : 0
     * doctorId : 0
     * gender : 0
     * name : string
     * phone : string
     * scheduleId : 0
     * timePart : 0
     * visitTime : string
     */

    private String age; // 年龄 ,
    private String doctorId; //  医生编号 ,
    private String gender; // 性别 1 男 0 女 ,
    private String name; // 患者名称 ,
    private String phone; // 电话 ,
    private String scheduleId; // 出诊时间表编号 ,
    private String timePart; // 时间区间 0 上午 1 下午 ,
    private String visitTime; // 挂号时间 yyyy-MM-dd

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getTimePart() {
        return timePart;
    }

    public void setTimePart(String timePart) {
        this.timePart = timePart;
    }

    public String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }
}
