package com.baihua.yaya.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Author:byd
 * Time:21/12/2018 16:51
 * Description: MatchDoctorsEntity
 */
public class MatchDoctorsEntity implements Serializable {


    private List<MatchDoctorsBean> matchDoctors;

    public List<MatchDoctorsBean> getMatchDoctors() {
        return matchDoctors;
    }

    public void setMatchDoctors(List<MatchDoctorsBean> matchDoctors) {
        this.matchDoctors = matchDoctors;
    }

    public static class MatchDoctorsBean implements Serializable{
        /**
         * id : 1075774356870684680
         * name : 牛医生
         * gender : 1
         * offId : 2
         * offName : 骨科
         * positionId : 13
         * positionName : 副主任医师
         * hosId : 1
         * hosName : 西京医院
         * photo : http://192.168.2.96:8080/files/1545380786298/201812211626250.png
         * physicianLicence : http://192.168.2.96:8080/files/1545380786305/201812211626251.png
         * identityCard : http://192.168.2.96:8080/files/1545380786310/201812211626252.png,http://192.168.2.96:8080/files/1545380786316/201812211626253.png
         * major : null
         * experience : null
         * registrationFee : 140
         * appointmentNum : 0
         * status : 2
         * gmtCreate : 2018-12-21 16:26:27
         * gmtModified : 2018-12-21 16:28:43
         * isDel : 0
         * adeptEntities : [{"id":1075774356925210636,"name":"小二营养不良","describe":"小儿肠炎，气胸，热伤风，支气管哮喘，心律不齐","ordered":1,"docId":1075774356870684680,"gmtCreate":"2018-12-21 16:26:27","gmtModified":"2018-12-21 16:26:27","isDel":0},{"id":1075774356925210637,"name":"心功能不全","describe":"热感冒，脑损伤，心肌梗死，新生儿肺炎","ordered":2,"docId":1075774356870684680,"gmtCreate":"2018-12-21 16:26:27","gmtModified":"2018-12-21 16:26:27","isDel":0},{"id":1075774356925210638,"name":"高血压","describe":"肺炎，鼻炎，咽炎，","ordered":3,"docId":1075774356870684680,"gmtCreate":"2018-12-21 16:26:27","gmtModified":"2018-12-21 16:26:27","isDel":0}]
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

        public List<AdeptEntitiesBean> getAdeptEntities() {
            return adeptEntities;
        }

        public void setAdeptEntities(List<AdeptEntitiesBean> adeptEntities) {
            this.adeptEntities = adeptEntities;
        }

        public static class AdeptEntitiesBean implements Serializable{
            /**
             * id : 1075774356925210636
             * name : 小二营养不良
             * describe : 小儿肠炎，气胸，热伤风，支气管哮喘，心律不齐
             * ordered : 1
             * docId : 1075774356870684680
             * gmtCreate : 2018-12-21 16:26:27
             * gmtModified : 2018-12-21 16:26:27
             * isDel : 0
             */

            private String id;
            private String name;
            private String describe;
            private int ordered;
            private String docId;
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

            public String getDescribe() {
                return describe;
            }

            public void setDescribe(String describe) {
                this.describe = describe;
            }

            public int getOrdered() {
                return ordered;
            }

            public void setOrdered(int ordered) {
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

            public int getIsDel() {
                return isDel;
            }

            public void setIsDel(int isDel) {
                this.isDel = isDel;
            }
        }
    }
}
