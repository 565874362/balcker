package com.baihua.yayayisheng.entity;

import java.io.Serializable;

/**
 * Author:byd
 * Time:14/12/2018 16:42
 * Description: TokenEntity
 */
public class TokenEntity implements Serializable {


    /**
     * token : c2294641d1040d97cd89b44006020ad4
     * info : {"id":1075774356870684681,"name":"白小黑","gender":1,"offId":2,"offName":"骨科","positionId":12,"positionName":"主治医师","hosId":1,"hosName":"西京医院","photo":"http://192.168.2.96:8080/files/1545380978351/1545380870975350.webp","physicianLicence":"http://192.168.2.96:8080/files/1545380978370/1545380888567218.jpg","identityCard":"http://192.168.2.96:8080/files/1545380978380/1545380913845272.png,http://192.168.2.96:8080/files/1545380978392/1545380936701449.png","major":null,"experience":null,"registrationFee":80,"appointmentNum":0,"status":2,"gmtCreate":"2018-12-21 16:29:39","gmtModified":"2018-12-21 16:30:39","isDel":0,"adeptEntities":null}
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
         * id : 1075774356870684681
         * name : 白小黑
         * gender : 1
         * offId : 2
         * offName : 骨科
         * positionId : 12
         * positionName : 主治医师
         * hosId : 1
         * hosName : 西京医院
         * photo : http://192.168.2.96:8080/files/1545380978351/1545380870975350.webp
         * physicianLicence : http://192.168.2.96:8080/files/1545380978370/1545380888567218.jpg
         * identityCard : http://192.168.2.96:8080/files/1545380978380/1545380913845272.png,http://192.168.2.96:8080/files/1545380978392/1545380936701449.png
         * major : null
         * experience : null
         * registrationFee : 80
         * appointmentNum : 0
         * status : 2
         * gmtCreate : 2018-12-21 16:29:39
         * gmtModified : 2018-12-21 16:30:39
         * isDel : 0
         * adeptEntities : null
         */

        private String id;
        private String name;
        private int gender;
        private int offId;
        private String offName;
        private int positionId;
        private String positionName;
        private int hosId;
        private String hosName;
        private String photo;
        private String physicianLicence;
        private String identityCard;
        private Object major;
        private Object experience;
        private int registrationFee;
        private int appointmentNum;
        private int status;
        private String gmtCreate;
        private String gmtModified;
        private int isDel;
        private Object adeptEntities;

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

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public int getOffId() {
            return offId;
        }

        public void setOffId(int offId) {
            this.offId = offId;
        }

        public String getOffName() {
            return offName;
        }

        public void setOffName(String offName) {
            this.offName = offName;
        }

        public int getPositionId() {
            return positionId;
        }

        public void setPositionId(int positionId) {
            this.positionId = positionId;
        }

        public String getPositionName() {
            return positionName;
        }

        public void setPositionName(String positionName) {
            this.positionName = positionName;
        }

        public int getHosId() {
            return hosId;
        }

        public void setHosId(int hosId) {
            this.hosId = hosId;
        }

        public String getHosName() {
            return hosName;
        }

        public void setHosName(String hosName) {
            this.hosName = hosName;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getPhysicianLicence() {
            return physicianLicence;
        }

        public void setPhysicianLicence(String physicianLicence) {
            this.physicianLicence = physicianLicence;
        }

        public String getIdentityCard() {
            return identityCard;
        }

        public void setIdentityCard(String identityCard) {
            this.identityCard = identityCard;
        }

        public Object getMajor() {
            return major;
        }

        public void setMajor(Object major) {
            this.major = major;
        }

        public Object getExperience() {
            return experience;
        }

        public void setExperience(Object experience) {
            this.experience = experience;
        }

        public int getRegistrationFee() {
            return registrationFee;
        }

        public void setRegistrationFee(int registrationFee) {
            this.registrationFee = registrationFee;
        }

        public int getAppointmentNum() {
            return appointmentNum;
        }

        public void setAppointmentNum(int appointmentNum) {
            this.appointmentNum = appointmentNum;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
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

        public Object getAdeptEntities() {
            return adeptEntities;
        }

        public void setAdeptEntities(Object adeptEntities) {
            this.adeptEntities = adeptEntities;
        }
    }
}
