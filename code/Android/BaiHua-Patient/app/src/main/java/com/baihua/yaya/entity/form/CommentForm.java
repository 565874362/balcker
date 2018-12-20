package com.baihua.yaya.entity.form;

import java.io.Serializable;

/**
 * Author:byd
 * Time:17/12/2018 15:09
 * Description: CommentForm
 */
public class CommentForm implements Serializable {

    public CommentForm() {
    }

    public CommentForm(int current, int doctorId, int size) {
        this.current = current;
        this.doctorId = doctorId;
        this.size = size;
    }

    /**
     * current : 1
     * doctorId : 1
     * size : 1
     */


    private int current;
    private int doctorId;
    private int size;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}