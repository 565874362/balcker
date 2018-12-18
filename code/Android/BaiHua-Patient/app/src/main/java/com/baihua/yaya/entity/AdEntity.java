package com.baihua.yaya.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Author:byd
 * Time:17/12/2018 10:15
 * Description: AdEntity
 */
public class AdEntity implements Serializable {

    private List<String> adImages;

    public List<String> getAdImages() {
        return adImages;
    }

    public void setAdImages(List<String> adImages) {
        this.adImages = adImages;
    }
}
