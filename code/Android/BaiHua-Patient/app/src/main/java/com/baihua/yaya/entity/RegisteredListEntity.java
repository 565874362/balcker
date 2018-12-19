package com.baihua.yaya.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Author:byd
 * Time:19/12/2018 9:11
 * Description: RegisteredListEntity
 */
public class RegisteredListEntity implements Serializable {

    /**
     * page : {"records":[{"gmtModified":"2018-12-18 17:57:41","gender":0,"patientId":1073503060334030800,"fee":100,"timePart":1,"gmtCreate":"2018-12-18 17:57:41","doctor":{"id":1,"name":"李方华","gender":1,"offId":1,"offName":"血液科","positionId":14,"positionName":"主任医师","hosId":1,"hosName":"西京医院","photo":"/files/","physicianLicence":"/physician_licence","identityCard":"610202197311031346","major":"血癌","experience":"治疗过癌症","registrationFee":100,"appointmentNum":11,"status":2,"gmtCreate":"2018-12-14 17:17:39","gmtModified":"2018-12-18 18:21:06","isDel":0,"adeptEntities":null},"visitTime":"2018-12-18 周二","phone":"13666544567","doctorId":1,"name":"小倩","id":1074966923529281500,"isDel":0,"payInfo":null,"age":3,"scheduleId":1,"status":1},{"gmtModified":"2018-12-18 18:21:07","gender":0,"patientId":1073503060334030800,"fee":100,"timePart":1,"gmtCreate":"2018-12-18 18:21:07","doctor":{"id":1,"name":"李方华","gender":1,"offId":1,"offName":"血液科","positionId":14,"positionName":"主任医师","hosId":1,"hosName":"西京医院","photo":"/files/","physicianLicence":"/physician_licence","identityCard":"610202197311031346","major":"血癌","experience":"治疗过癌症","registrationFee":100,"appointmentNum":11,"status":2,"gmtCreate":"2018-12-14 17:17:39","gmtModified":"2018-12-18 18:21:06","isDel":0,"adeptEntities":null},"visitTime":"2018-12-18 周二","phone":"13474370878","doctorId":1,"name":"小虾","id":1074972818254377000,"isDel":0,"payInfo":null,"age":6,"scheduleId":1,"status":1}],"total":0,"size":10,"current":1,"searchCount":true,"pages":0}
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
         * records : [{"gmtModified":"2018-12-18 17:57:41","gender":0,"patientId":1073503060334030800,"fee":100,"timePart":1,"gmtCreate":"2018-12-18 17:57:41","doctor":{"id":1,"name":"李方华","gender":1,"offId":1,"offName":"血液科","positionId":14,"positionName":"主任医师","hosId":1,"hosName":"西京医院","photo":"/files/","physicianLicence":"/physician_licence","identityCard":"610202197311031346","major":"血癌","experience":"治疗过癌症","registrationFee":100,"appointmentNum":11,"status":2,"gmtCreate":"2018-12-14 17:17:39","gmtModified":"2018-12-18 18:21:06","isDel":0,"adeptEntities":null},"visitTime":"2018-12-18 周二","phone":"13666544567","doctorId":1,"name":"小倩","id":1074966923529281500,"isDel":0,"payInfo":null,"age":3,"scheduleId":1,"status":1},{"gmtModified":"2018-12-18 18:21:07","gender":0,"patientId":1073503060334030800,"fee":100,"timePart":1,"gmtCreate":"2018-12-18 18:21:07","doctor":{"id":1,"name":"李方华","gender":1,"offId":1,"offName":"血液科","positionId":14,"positionName":"主任医师","hosId":1,"hosName":"西京医院","photo":"/files/","physicianLicence":"/physician_licence","identityCard":"610202197311031346","major":"血癌","experience":"治疗过癌症","registrationFee":100,"appointmentNum":11,"status":2,"gmtCreate":"2018-12-14 17:17:39","gmtModified":"2018-12-18 18:21:06","isDel":0,"adeptEntities":null},"visitTime":"2018-12-18 周二","phone":"13474370878","doctorId":1,"name":"小虾","id":1074972818254377000,"isDel":0,"payInfo":null,"age":6,"scheduleId":1,"status":1}]
         * total : 0
         * size : 10
         * current : 1
         * searchCount : true
         * pages : 0
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

        public static class RecordsBean implements Serializable {
            /**
             * gmtModified : 2018-12-18 17:57:41
             * gender : 0
             * patientId : 1073503060334030800
             * fee : 100
             * timePart : 1
             * gmtCreate : 2018-12-18 17:57:41
             * doctor : {"id":1,"name":"李方华","gender":1,"offId":1,"offName":"血液科","positionId":14,"positionName":"主任医师","hosId":1,"hosName":"西京医院","photo":"/files/","physicianLicence":"/physician_licence","identityCard":"610202197311031346","major":"血癌","experience":"治疗过癌症","registrationFee":100,"appointmentNum":11,"status":2,"gmtCreate":"2018-12-14 17:17:39","gmtModified":"2018-12-18 18:21:06","isDel":0,"adeptEntities":null}
             * visitTime : 2018-12-18 周二
             * phone : 13666544567
             * doctorId : 1
             * name : 小倩
             * id : 1074966923529281500
             * isDel : 0
             * payInfo : null
             * age : 3
             * scheduleId : 1
             * status : 1
             */

            private String gmtModified;
            private String gender;
            private long patientId;
            private String fee;
            private String timePart;
            private String gmtCreate;
            private DoctorBean doctor;
            private String visitTime;
            private String phone;
            private String doctorId;
            private String name;
            private long id;
            private String isDel;
            private Object payInfo;
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

            public long getPatientId() {
                return patientId;
            }

            public void setPatientId(long patientId) {
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

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getIsDel() {
                return isDel;
            }

            public void setIsDel(String isDel) {
                this.isDel = isDel;
            }

            public Object getPayInfo() {
                return payInfo;
            }

            public void setPayInfo(Object payInfo) {
                this.payInfo = payInfo;
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
                 * appointmentNum : 11
                 * status : 2
                 * gmtCreate : 2018-12-14 17:17:39
                 * gmtModified : 2018-12-18 18:21:06
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

                public Object getAdeptEntities() {
                    return adeptEntities;
                }

                public void setAdeptEntities(Object adeptEntities) {
                    this.adeptEntities = adeptEntities;
                }
            }
        }
    }
}
