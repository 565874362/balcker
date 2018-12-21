package com.baihua.yayayisheng.entity.form;

import java.io.Serializable;

/**
 * Author:byd
 * Time:20/12/2018 16:34
 * Description: ListForm
 */
public class ListForm implements Serializable {

    public ListForm(int current, int size) {
        this.current = current;
        this.size = size;
    }

    /**
     * current : 1
     * size : 10
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
