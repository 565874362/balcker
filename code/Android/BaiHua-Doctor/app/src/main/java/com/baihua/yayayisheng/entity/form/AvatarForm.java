package com.baihua.yayayisheng.entity.form;

import java.io.Serializable;

/**
 * Author:byd
 * Time:18/12/2018 14:21
 * Description: AvatarForm
 */
public class AvatarForm implements Serializable {

    public AvatarForm() {
    }

    public AvatarForm(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    /**
     * photoUrl :
     */

    private String photoUrl;

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
