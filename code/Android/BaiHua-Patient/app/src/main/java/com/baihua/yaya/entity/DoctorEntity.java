package com.baihua.yaya.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Author:byd
 * Time:17/12/2018 11:01
 * Description: DoctorEntity
 */
public class DoctorEntity implements Serializable {

    /**
     * page : {"records":[{"id":1,"name":"李方华","gender":1,"offId":1,"offName":"血液科","positionId":14,"positionName":"主任医师","hosId":1,"hosName":"西京医院","photo":"/files/","physicianLicence":"/physician_licence","identityCard":"610202197311031346","major":"血癌","experience":"治疗过癌症","registrationFee":100,"appointmentNum":0,"status":2,"gmtCreate":"2018-12-14 17:17:39","gmtModified":"2018-12-14 17:17:39","isDel":0}],"total":1,"size":10,"current":1,"searchCount":true,"pages":1}
     */

    private PageBean page;

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public static class PageBean {
        /**
         * records : [{"id":1,"name":"李方华","gender":1,"offId":1,"offName":"血液科","positionId":14,"positionName":"主任医师","hosId":1,"hosName":"西京医院","photo":"/files/","physicianLicence":"/physician_licence","identityCard":"610202197311031346","major":"血癌","experience":"治疗过癌症","registrationFee":100,"appointmentNum":0,"status":2,"gmtCreate":"2018-12-14 17:17:39","gmtModified":"2018-12-14 17:17:39","isDel":0}]
         * total : 1
         * size : 10
         * current : 1
         * searchCount : true
         * pages : 1
         */

        private int total;
        private int size;
        private int current;
        private boolean searchCount;
        private int pages;
        private List<RecordsBean> records;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public boolean isSearchCount() {
            return searchCount;
        }

        public void setSearchCount(boolean searchCount) {
            this.searchCount = searchCount;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public List<RecordsBean> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsBean> records) {
            this.records = records;
        }

        public static class RecordsBean {
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
        }
    }
}
