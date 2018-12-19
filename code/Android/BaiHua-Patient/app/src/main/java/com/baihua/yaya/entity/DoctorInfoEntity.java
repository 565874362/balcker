package com.baihua.yaya.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Author:byd
 * Time:17/12/2018 14:40
 * Description: DoctorInfoEntity
 */
public class DoctorInfoEntity implements Serializable {

    /**
     * info : {"id":1,"name":"李方华","gender":1,"offId":1,"offName":"血液科","positionId":14,"positionName":"主任医师","hosId":1,"hosName":"西京医院","photo":"/files/","physicianLicence":"/physician_licence","identityCard":"610202197311031346","major":"血癌","experience":"治疗过癌症","registrationFee":100,"appointmentNum":0,"status":2,"gmtCreate":"2018-12-14 17:17:39","gmtModified":"2018-12-14 17:17:39","isDel":0,"adeptEntities":[{"id":null,"name":"咳嗽","describe":"过敏性咳嗽","ordered":null,"docId":null,"gmtCreate":null,"gmtModified":null,"isDel":null}]}
     */

    private InfoBean info;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * id : 1
         * name : 李方华
         * gender : 1
         * offId : 1
         * offName : 血液科
         * positionId : 14
         * positionName : 主任医师
         * hosId : 1
         * hosName : 西京医院
         * photo : /files/
         * physicianLicence : /physician_licence
         * identityCard : 610202197311031346
         * major : 血癌
         * experience : 治疗过癌症
         * registrationFee : 100
         * appointmentNum : 0
         * status : 2
         * gmtCreate : 2018-12-14 17:17:39
         * gmtModified : 2018-12-14 17:17:39
         * isDel : 0
         * adeptEntities : [{"id":null,"name":"咳嗽","describe":"过敏性咳嗽","ordered":null,"docId":null,"gmtCreate":null,"gmtModified":null,"isDel":null}]
         */

        private String id;
        private String name;
        private String gender;
        private String offId;
        private String offName;
        private String positionId;
        private String positionName;
        private String hosId;
        private String hosName;
        private String photo;
        private String physicianLicence;
        private String identityCard;
        private String major;
        private String experience;
        private String registrationFee;
        private String appointmentNum;
        private String status;
        private String gmtCreate;
        private String gmtModified;
        private String isDel;
        private List<AdeptEntitiesBean> adeptEntities;

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

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getOffId() {
            return offId;
        }

        public void setOffId(String offId) {
            this.offId = offId;
        }

        public String getOffName() {
            return offName;
        }

        public void setOffName(String offName) {
            this.offName = offName;
        }

        public String getPositionId() {
            return positionId;
        }

        public void setPositionId(String positionId) {
            this.positionId = positionId;
        }

        public String getPositionName() {
            return positionName;
        }

        public void setPositionName(String positionName) {
            this.positionName = positionName;
        }

        public String getHosId() {
            return hosId;
        }

        public void setHosId(String hosId) {
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

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public String getExperience() {
            return experience;
        }

        public void setExperience(String experience) {
            this.experience = experience;
        }

        public String getRegistrationFee() {
            return registrationFee;
        }

        public void setRegistrationFee(String registrationFee) {
            this.registrationFee = registrationFee;
        }

        public String getAppointmentNum() {
            return appointmentNum;
        }

        public void setAppointmentNum(String appointmentNum) {
            this.appointmentNum = appointmentNum;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
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

        public String getIsDel() {
            return isDel;
        }

        public void setIsDel(String isDel) {
            this.isDel = isDel;
        }

        public List<AdeptEntitiesBean> getAdeptEntities() {
            return adeptEntities;
        }

        public void setAdeptEntities(List<AdeptEntitiesBean> adeptEntities) {
            this.adeptEntities = adeptEntities;
        }

        public static class AdeptEntitiesBean implements Serializable{
            /**
             * id : null
             * name : 咳嗽
             * describe : 过敏性咳嗽
             * ordered : null
             * docId : null
             * gmtCreate : null
             * gmtModified : null
             * isDel : null
             */

            private String id;
            private String name;
            private String describe;
            private String ordered;
            private String docId;
            private String gmtCreate;
            private String gmtModified;
            private String isDel;

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

            public String getDescribe() {
                return describe;
            }

            public void setDescribe(String describe) {
                this.describe = describe;
            }

            public String getOrdered() {
                return ordered;
            }

            public void setOrdered(String ordered) {
                this.ordered = ordered;
            }

            public String getDocId() {
                return docId;
            }

            public void setDocId(String docId) {
                this.docId = docId;
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

            public String getIsDel() {
                return isDel;
            }

            public void setIsDel(String isDel) {
                this.isDel = isDel;
            }
        }
    }
}
