package com.baihua.yaya.entity;

import java.io.Serializable;

/**
 * Author:byd
 * Time:14/12/2018 16:42
 * Description: Token
 */
public class Token implements Serializable {


    /**
     * token : 6b9fa5f0cc672052f81b6ebc09625f81
     * info : {"id":8,"name":null,"gender":null,"photo":null,"gmtCreate":"2018-12-22 09:29:39","gmtModified":"2018-12-22 09:29:39","isDel":0}
     */

    private String token;
    private InfoBean info;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * id : 8
         * name : null
         * gender : null
         * photo : null
         * gmtCreate : 2018-12-22 09:29:39
         * gmtModified : 2018-12-22 09:29:39
         * isDel : 0
         */

        private int id;
        private String name;
        private String gender;
        private String photo;
        private String gmtCreate;
        private String gmtModified;
        private int isDel;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
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
}
