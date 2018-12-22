package com.baihua.yaya.entity;

import java.io.Serializable;

/**
 * Author:byd
 * Time:22/12/2018 9:47
 * Description: 就诊详情
 */
public class RegistrationDetailsEntity implements Serializable {

    /**
     * info : {"gmtModified":"2018-12-22 09:46:54","gender":1,"patientId":8,"fee":150,"timePart":1,"gmtCreate":"2018-12-22 09:46:54","doctor":{"id":1075774356870684682,"name":"闲东东","gender":1,"offId":1,"offName":"血液科","positionId":11,"positionName":"住院医师","hosId":1,"hosName":"西京医院","photo":"http://192.168.2.96:8080/files/1545383513781/1545383450000743.webp","physicianLicence":"http://192.168.2.96:8080/files/1545383513787/1545383463338171.webp","identityCard":"http://192.168.2.96:8080/files/1545383513794/1545383485629593.jpg,http://192.168.2.96:8080/files/1545383513799/1545383499043887.png","major":null,"experience":null,"registrationFee":150,"appointmentNum":3,"status":2,"gmtCreate":"2018-12-21 17:11:54","gmtModified":"2018-12-22 09:46:54","isDel":0,"adeptEntities":null},"visitTime":"2018-12-22 周六 下午","phone":"13474370876","doctorId":1075774356870684682,"name":"白小白","id":8,"isDel":0,"age":6,"scheduleId":38,"status":1}
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
         * gmtModified : 2018-12-22 09:46:54
         * gender : 1
         * patientId : 8
         * fee : 150
         * timePart : 1
         * gmtCreate : 2018-12-22 09:46:54
         * doctor : {"id":1075774356870684682,"name":"闲东东","gender":1,"offId":1,"offName":"血液科","positionId":11,"positionName":"住院医师","hosId":1,"hosName":"西京医院","photo":"http://192.168.2.96:8080/files/1545383513781/1545383450000743.webp","physicianLicence":"http://192.168.2.96:8080/files/1545383513787/1545383463338171.webp","identityCard":"http://192.168.2.96:8080/files/1545383513794/1545383485629593.jpg,http://192.168.2.96:8080/files/1545383513799/1545383499043887.png","major":null,"experience":null,"registrationFee":150,"appointmentNum":3,"status":2,"gmtCreate":"2018-12-21 17:11:54","gmtModified":"2018-12-22 09:46:54","isDel":0,"adeptEntities":null}
         * visitTime : 2018-12-22 周六 下午
         * phone : 13474370876
         * doctorId : 1075774356870684682
         * name : 白小白
         * id : 8
         * isDel : 0
         * age : 6
         * scheduleId : 38
         * status : 1
         */

        private String gmtModified;
        private String gender;
        private String patientId;
        private String fee;
        private String timePart;
        private String gmtCreate;
        private DoctorBean doctor;
        private String visitTime;
        private String phone;
        private String doctorId;
        private String name;
        private String id;
        private String isDel;
        private String age;
        private String scheduleId;
        private String status;

        public String getGmtModified() {
            return gmtModified;
        }

        public void setGmtModified(String gmtModified) {
            this.gmtModified = gmtModified;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getPatientId() {
            return patientId;
        }

        public void setPatientId(String patientId) {
            this.patientId = patientId;
        }

        public String getFee() {
            return fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
        }

        public String getTimePart() {
            return timePart;
        }

        public void setTimePart(String timePart) {
            this.timePart = timePart;
        }

        public String getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(String gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public DoctorBean getDoctor() {
            return doctor;
        }

        public void setDoctor(DoctorBean doctor) {
            this.doctor = doctor;
        }

        public String getVisitTime() {
            return visitTime;
        }

        public void setVisitTime(String visitTime) {
            this.visitTime = visitTime;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getDoctorId() {
            return doctorId;
        }

        public void setDoctorId(String doctorId) {
            this.doctorId = doctorId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIsDel() {
            return isDel;
        }

        public void setIsDel(String isDel) {
            this.isDel = isDel;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getScheduleId() {
            return scheduleId;
        }

        public void setScheduleId(String scheduleId) {
            this.scheduleId = scheduleId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public static class DoctorBean implements Serializable {
            /**
             * id : 1075774356870684682
             * name : 闲东东
             * gender : 1
             * offId : 1
             * offName : 血液科
             * positionId : 11
             * positionName : 住院医师
             * hosId : 1
             * hosName : 西京医院
             * photo : http://192.168.2.96:8080/files/1545383513781/1545383450000743.webp
             * physicianLicence : http://192.168.2.96:8080/files/1545383513787/1545383463338171.webp
             * identityCard : http://192.168.2.96:8080/files/1545383513794/1545383485629593.jpg,http://192.168.2.96:8080/files/1545383513799/1545383499043887.png
             * major : null
             * experience : null
             * registrationFee : 150
             * appointmentNum : 3
             * status : 2
             * gmtCreate : 2018-12-21 17:11:54
             * gmtModified : 2018-12-22 09:46:54
             * isDel : 0
             * adeptEntities : null
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
            private String adeptEntities;

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

            public String getAdeptEntities() {
                return adeptEntities;
            }

            public void setAdeptEntities(String adeptEntities) {
                this.adeptEntities = adeptEntities;
            }
        }
    }
}
