package com.baihua.yayayisheng.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Author:byd
 * Time:19/12/2018 18:26
 * Description: DoctorInfoEntity
 */
public class DoctorInfoEntity implements Serializable {

    /**
     * info : {"id":1075318232237264900,"name":"路易斯","gender":0,"offId":1,"offName":"血液科","positionId":11,"positionName":"住院医师","hosId":1,"hosName":"西京医院","photo":"http://192.168.2.96:8080/files/1545210819404/1545210759698957.webp","physicianLicence":"http://192.168.2.96:8080/files/1545210819411/1545210770762297.jpg","identityCard":"http://192.168.2.96:8080/files/1545210819420/1545210782996124.webp,http://192.168.2.96:8080/files/1545210819425/1545210791894760.webp","major":null,"experience":null,"registrationFee":99,"appointmentNum":null,"status":2,"gmtCreate":"2018-12-19 17:13:40","gmtModified":"2018-12-19 17:13:40","isDel":0,"adeptEntities":[{"id":null,"name":"伤心","describe":"伤心小剑","ordered":null,"docId":null,"gmtCreate":null,"gmtModified":null,"isDel":null},{"id":null,"name":"水龙吟","describe":"水龙吟","ordered":null,"docId":null,"gmtCreate":null,"gmtModified":null,"isDel":null},{"id":null,"name":"不算子","describe":"不算子","ordered":null,"docId":null,"gmtCreate":null,"gmtModified":null,"isDel":null}]}
     */

    private InfoBean info;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean implements Serializable {
        /**
         * id : 1075318232237264900
         * name : 路易斯
         * gender : 0
         * offId : 1
         * offName : 血液科
         * positionId : 11
         * positionName : 住院医师
         * hosId : 1
         * hosName : 西京医院
         * photo : http://192.168.2.96:8080/files/1545210819404/1545210759698957.webp
         * physicianLicence : http://192.168.2.96:8080/files/1545210819411/1545210770762297.jpg
         * identityCard : http://192.168.2.96:8080/files/1545210819420/1545210782996124.webp,http://192.168.2.96:8080/files/1545210819425/1545210791894760.webp
         * major : null
         * experience : null
         * registrationFee : 99
         * appointmentNum : null
         * status : 2
         * gmtCreate : 2018-12-19 17:13:40
         * gmtModified : 2018-12-19 17:13:40
         * isDel : 0
         * adeptEntities : [{"id":null,"name":"伤心","describe":"伤心小剑","ordered":null,"docId":null,"gmtCreate":null,"gmtModified":null,"isDel":null},{"id":null,"name":"水龙吟","describe":"水龙吟","ordered":null,"docId":null,"gmtCreate":null,"gmtModified":null,"isDel":null},{"id":null,"name":"不算子","describe":"不算子","ordered":null,"docId":null,"gmtCreate":null,"gmtModified":null,"isDel":null}]
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

        public static class AdeptEntitiesBean implements Serializable {
            /**
             * id : null
             * name : 伤心
             * describe : 伤心小剑
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
