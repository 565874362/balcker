package com.baihua.yayayisheng.entity.form;

import java.io.Serializable;

/**
 * Author:byd
 * Time:18/12/2018 10:28
 * Description: PatientListForm
 */
public class PatientListForm implements Serializable {

    public PatientListForm() {
    }

    public PatientListForm(int current, int size) {
        this.current = current;
        this.size = size;
    }

    /**
     * current : 0
     * size : 0
     */

    private int current;
    private int size;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
