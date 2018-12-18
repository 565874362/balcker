package com.baihua.yaya.entity.form;

import java.io.Serializable;

/**
 * Author:byd
 * Time:17/12/2018 11:00
 * Description: DoctorForm
 */
public class DoctorForm implements Serializable {

    public DoctorForm() {
    }

    public DoctorForm(int current, String search, int size) {
        this.current = current;
        this.search = search;
        this.size = size;
    }

    /**
     * current : 1
     * search :
     * size : 10
     */


    private int current;
    private String search;
    private int size;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
