package com.baihua.yaya.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Author:byd
 * Time:18/12/2018 14:52
 * Description: VisitDetailsEntity
 */
public class VisitDetailsEntity implements Serializable {

    /**
     * doctor : {"id":null,"name":"李方华","gender":1,"offId":null,"offName":"血液科","positionId":null,"positionName":"主任医师","hosId":null,"hosName":null,"photo":"/files/","physicianLicence":null,"identityCard":null,"major":null,"experience":null,"registrationFee":null,"appointmentNum":null,"status":null,"gmtCreate":null,"gmtModified":null,"isDel":null,"adeptEntities":null}
     * healthExaminations : null
     * info : {"id":1073521292302397442,"name":"赵冬冬","gender":0,"age":28,"phone":"18502940483","bloodType":null,"diet":1,"sleep":1,"characterDescribe":"哈哈","voiceDescribe":"","image":"/sdfds","patientId":1073503060334030850,"doctorId":1,"response":"心情好","advice":"好好喝水","exaIds":"1,2,3,4,5","exaContent":null,"exaFee":500,"status":2,"gmtCreate":"2018-12-14 18:13:16","gmtModified":"2018-12-14 18:13:16","isDel":0}
     */

    private DoctorBean doctor;
    private List<HealthExaminationsEntity> healthExaminations;
    private InfoBean info;

    public DoctorBean getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorBean doctor) {
        this.doctor = doctor;
    }

    public List<HealthExaminationsEntity> getHealthExaminations() {
        return healthExaminations;
    }

    public void setHealthExaminations(List<HealthExaminationsEntity> healthExaminations) {
        this.healthExaminations = healthExaminations;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class DoctorBean {
        /**
         * id : null
         * name : 李方华
         * gender : 1
         * offId : null
         * offName : 血液科
         * positionId : null
         * positionName : 主任医师
         * hosId : null
         * hosName : null
         * photo : /files/
         * physicianLicence : null
         * identityCard : null
         * major : null
         * experience : null
         * registrationFee : null
         * appointmentNum : null
         * status : null
         * gmtCreate : null
         * gmtModified : null
         * isDel : null
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

    public static class InfoBean {
        /**
         * id : 1073521292302397442
         * name : 赵冬冬
         * gender : 0
         * age : 28
         * phone : 18502940483
         * bloodType : null
         * diet : 1
         * sleep : 1
         * characterDescribe : 哈哈
         * voiceDescribe :
         * image : /sdfds
         * patientId : 1073503060334030850
         * doctorId : 1
         * response : 心情好
         * advice : 好好喝水
         * exaIds : 1,2,3,4,5
         * exaContent : null
         * exaFee : 500
         * status : 2
         * gmtCreate : 2018-12-14 18:13:16
         * gmtModified : 2018-12-14 18:13:16
         * isDel : 0
         */

        private long id;
        private String name;
        private String gender;
        private String age;
        private String phone;
        private String bloodType;
        private String diet;
        private String sleep;
        private String characterDescribe;
        private String voiceDescribe;
        private String image;
        private long patientId;
        private String doctorId;
        private String response;
        private String advice;
        private String exaIds;
        private String exaContent;
        private String exaFee;
        private String status;
        private String gmtCreate;
        private String gmtModified;
        private String isDel;

        public long getId() {
            return id;
        }

        public void setId(long id) {
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

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getBloodType() {
            return bloodType;
        }

        public void setBloodType(String bloodType) {
            this.bloodType = bloodType;
        }

        public String getDiet() {
            return diet;
        }

        public void setDiet(String diet) {
            this.diet = diet;
        }

        public String getSleep() {
            return sleep;
        }

        public void setSleep(String sleep) {
            this.sleep = sleep;
        }

        public String getCharacterDescribe() {
            return characterDescribe;
        }

        public void setCharacterDescribe(String characterDescribe) {
            this.characterDescribe = characterDescribe;
        }

        public String getVoiceDescribe() {
            return voiceDescribe;
        }

        public void setVoiceDescribe(String voiceDescribe) {
            this.voiceDescribe = voiceDescribe;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public long getPatientId() {
            return patientId;
        }

        public void setPatientId(long patientId) {
            this.patientId = patientId;
        }

        public String getDoctorId() {
            return doctorId;
        }

        public void setDoctorId(String doctorId) {
            this.doctorId = doctorId;
        }

        public String getResponse() {
            return response;
        }

        public void setResponse(String response) {
            this.response = response;
        }

        public String getAdvice() {
            return advice;
        }

        public void setAdvice(String advice) {
            this.advice = advice;
        }

        public String getExaIds() {
            return exaIds;
        }

        public void setExaIds(String exaIds) {
            this.exaIds = exaIds;
        }

        public String getExaContent() {
            return exaContent;
        }

        public void setExaContent(String exaContent) {
            this.exaContent = exaContent;
        }

        public String getExaFee() {
            return exaFee;
        }

        public void setExaFee(String exaFee) {
            this.exaFee = exaFee;
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
    }
}
