package com.baihua.yayayisheng.entity;

import java.io.Serializable;

/**
 * Author:byd
 * Time:5/12/2018 14:25
 * Description: Tag
 */
public class Tag implements Serializable {
    private String tagDate;
    private String tagWeek;

    public String getTagDate() {
        return tagDate;
    }

    public void setTagDate(String tagDate) {
        this.tagDate = tagDate;
    }

    public String getTagWeek() {
        return tagWeek;
    }

    public void setTagWeek(String tagWeek) {
        this.tagWeek = tagWeek;
    }
}
