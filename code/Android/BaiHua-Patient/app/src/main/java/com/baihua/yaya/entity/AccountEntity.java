package com.baihua.yaya.entity;

import java.io.Serializable;

/**
 * Author:byd
 * Time:22/12/2018 12:19
 * Description: AccountEntity
 */
public class AccountEntity implements Serializable {

    /**
     * info : {"name":"牛医生","photo":"http://192.168.2.96:8080/files/1545383805554/201812211716440.png"}
     */

    private InfoBean info;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean implements Serializable{
        /**
         * name : 牛医生
         * photo : http://192.168.2.96:8080/files/1545383805554/201812211716440.png
         */

        private String name;
        private String photo;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }
    }
}
