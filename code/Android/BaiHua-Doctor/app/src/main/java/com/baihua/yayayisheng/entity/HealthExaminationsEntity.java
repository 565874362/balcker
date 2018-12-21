package com.baihua.yayayisheng.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Author:byd
 * Time:21/12/2018 16:24
 * Description: HealthExaminationsEntity
 */
public class HealthExaminationsEntity implements Serializable {

    /**
     * id : 1
     * name : 口腔检查
     * price : 25
     * sort : 1
     * gmtCreate : 2018-12-14 16:47:18
     * gmtModified : 2018-12-14 16:47:18
     * isDel : 0
     */

    private String id;
    private String name;
    private BigDecimal price;
    private int sort;
    private String gmtCreate;
    private String gmtModified;
    private int isDel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(String gmtModified) {
        this.gmtModified = gmtModified;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }
}
