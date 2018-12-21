package com.baihua.yayayisheng.entity.form;

import java.util.List;

/**
 * Author:byd
 * Time:20/12/2018 12:03
 * Description: DiagnoseForm
 */
public class DiagnoseForm {


    /**
     * afternoonBegin : string
     * afternoonEnd : string
     * afternoonNum : 0
     * dates : ["string"]
     * moringBegin : string
     * moringEnd : string
     * morningNum : 0
     */

    private String afternoonBegin; // 下午开始时间
    private String afternoonEnd; // 下午结束时间
    private String afternoonNum; // 下午接诊人数
    private String moringBegin; // 早上开始时间 ,
    private String moringEnd; // 早上结束时间
    private String morningNum; // 早上接诊人数
    private List<String> dates; // 选中日期

    public String getAfternoonBegin() {
        return afternoonBegin;
    }

    public void setAfternoonBegin(String afternoonBegin) {
        this.afternoonBegin = afternoonBegin;
    }

    public String getAfternoonEnd() {
        return afternoonEnd;
    }

    public void setAfternoonEnd(String afternoonEnd) {
        this.afternoonEnd = afternoonEnd;
    }

    public String getAfternoonNum() {
        return afternoonNum;
    }

    public void setAfternoonNum(String afternoonNum) {
        this.afternoonNum = afternoonNum;
    }

    public String getMoringBegin() {
        return moringBegin;
    }

    public void setMoringBegin(String moringBegin) {
        this.moringBegin = moringBegin;
    }

    public String getMoringEnd() {
        return moringEnd;
    }

    public void setMoringEnd(String moringEnd) {
        this.moringEnd = moringEnd;
    }

    public String getMorningNum() {
        return morningNum;
    }

    public void setMorningNum(String morningNum) {
        this.morningNum = morningNum;
    }

    public List<String> getDates() {
        return dates;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }
}
