package com.baihua.yayayisheng.entity;

import java.io.Serializable;

/**
 * Author:byd
 * Time:18/12/2018 11:14
 * Description: AvatarEntity
 */
public class AvatarEntity implements Serializable {

    /**
     * photo : null
     */

    private String photo; // 用户头像

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
