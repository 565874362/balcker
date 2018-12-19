package com.baihua.yayayisheng.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Author:byd
 * Time:18/12/2018 10:33
 * Description: PatientListEntity
 */
public class PatientListEntity implements Serializable {

    /**
     * page : {"records":[{"id":1073521292302397400,"name":"赵冬冬","gender":0,"age":28,"phone":null,"bloodType":null,"diet":null,"sleep":null,"characterDescribe":"哈哈","voiceDescribe":null,"image":null,"patientId":null,"doctorId":null,"response":null,"advice":null,"exaIds":null,"exaContent":null,"exaFee":null,"status":2,"gmtCreate":"2018-12-14 18:13:16","gmtModified":null,"isDel":null},{"id":1074594403449851900,"name":"白诗诗","gender":0,"age":5,"phone":null,"bloodType":null,"diet":null,"sleep":null,"characterDescribe":"发烧，咳嗽","voiceDescribe":null,"image":null,"patientId":null,"doctorId":null,"response":null,"advice":null,"exaIds":null,"exaContent":null,"exaFee":null,"status":1,"gmtCreate":"2018-12-17 17:17:25","gmtModified":null,"isDel":null},{"id":1074608581371781100,"name":"小黑","gender":1,"age":4,"phone":null,"bloodType":null,"diet":null,"sleep":null,"characterDescribe":"发烧，咳嗽","voiceDescribe":null,"image":null,"patientId":null,"doctorId":null,"response":null,"advice":null,"exaIds":null,"exaContent":null,"exaFee":null,"status":1,"gmtCreate":"2018-12-17 18:13:46","gmtModified":null,"isDel":null},{"id":1074608976584269800,"name":"小黑","gender":1,"age":4,"phone":null,"bloodType":null,"diet":null,"sleep":null,"characterDescribe":"发烧，咳嗽","voiceDescribe":null,"image":null,"patientId":null,"doctorId":null,"response":null,"advice":null,"exaIds":null,"exaContent":null,"exaFee":null,"status":1,"gmtCreate":"2018-12-17 18:15:20","gmtModified":null,"isDel":null},{"id":1074837789088018400,"name":"小黑","gender":0,"age":6,"phone":null,"bloodType":null,"diet":null,"sleep":null,"characterDescribe":"咳嗽","voiceDescribe":null,"image":null,"patientId":null,"doctorId":null,"response":null,"advice":null,"exaIds":null,"exaContent":null,"exaFee":null,"status":1,"gmtCreate":"2018-12-18 09:24:33","gmtModified":null,"isDel":null},{"id":1074839493674135600,"name":"小小","gender":0,"age":3,"phone":null,"bloodType":null,"diet":null,"sleep":null,"characterDescribe":"发烧","voiceDescribe":null,"image":null,"patientId":null,"doctorId":null,"response":null,"advice":null,"exaIds":null,"exaContent":null,"exaFee":null,"status":1,"gmtCreate":"2018-12-18 09:31:20","gmtModified":null,"isDel":null},{"id":1074846751006916600,"name":"猪小弟","gender":0,"age":1,"phone":null,"bloodType":null,"diet":null,"sleep":null,"characterDescribe":"嗜睡","voiceDescribe":null,"image":null,"patientId":null,"doctorId":null,"response":null,"advice":null,"exaIds":null,"exaContent":null,"exaFee":null,"status":1,"gmtCreate":"2018-12-18 10:00:10","gmtModified":null,"isDel":null},{"id":1074846886646517800,"name":"猪小弟","gender":0,"age":1,"phone":null,"bloodType":null,"diet":null,"sleep":null,"characterDescribe":"嗜睡","voiceDescribe":null,"image":null,"patientId":null,"doctorId":null,"response":null,"advice":null,"exaIds":null,"exaContent":null,"exaFee":null,"status":1,"gmtCreate":"2018-12-18 10:00:42","gmtModified":null,"isDel":null},{"id":1074851191885729800,"name":"小家伙","gender":0,"age":3,"phone":null,"bloodType":null,"diet":null,"sleep":null,"characterDescribe":"失眠","voiceDescribe":null,"image":null,"patientId":null,"doctorId":null,"response":null,"advice":null,"exaIds":null,"exaContent":null,"exaFee":null,"status":1,"gmtCreate":"2018-12-18 10:17:49","gmtModified":null,"isDel":null},{"id":1074851269622960100,"name":"小家伙","gender":0,"age":3,"phone":null,"bloodType":null,"diet":null,"sleep":null,"characterDescribe":"失眠","voiceDescribe":null,"image":null,"patientId":null,"doctorId":null,"response":null,"advice":null,"exaIds":null,"exaContent":null,"exaFee":null,"status":1,"gmtCreate":"2018-12-18 10:18:07","gmtModified":null,"isDel":null}],"total":12,"size":10,"current":1,"searchCount":true,"pages":2}
     */

    private PageBean page;

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public static class PageBean implements Serializable{
        /**
         * records : [{"id":1073521292302397400,"name":"赵冬冬","gender":0,"age":28,"phone":null,"bloodType":null,"diet":null,"sleep":null,"characterDescribe":"哈哈","voiceDescribe":null,"image":null,"patientId":null,"doctorId":null,"response":null,"advice":null,"exaIds":null,"exaContent":null,"exaFee":null,"status":2,"gmtCreate":"2018-12-14 18:13:16","gmtModified":null,"isDel":null},{"id":1074594403449851900,"name":"白诗诗","gender":0,"age":5,"phone":null,"bloodType":null,"diet":null,"sleep":null,"characterDescribe":"发烧，咳嗽","voiceDescribe":null,"image":null,"patientId":null,"doctorId":null,"response":null,"advice":null,"exaIds":null,"exaContent":null,"exaFee":null,"status":1,"gmtCreate":"2018-12-17 17:17:25","gmtModified":null,"isDel":null},{"id":1074608581371781100,"name":"小黑","gender":1,"age":4,"phone":null,"bloodType":null,"diet":null,"sleep":null,"characterDescribe":"发烧，咳嗽","voiceDescribe":null,"image":null,"patientId":null,"doctorId":null,"response":null,"advice":null,"exaIds":null,"exaContent":null,"exaFee":null,"status":1,"gmtCreate":"2018-12-17 18:13:46","gmtModified":null,"isDel":null},{"id":1074608976584269800,"name":"小黑","gender":1,"age":4,"phone":null,"bloodType":null,"diet":null,"sleep":null,"characterDescribe":"发烧，咳嗽","voiceDescribe":null,"image":null,"patientId":null,"doctorId":null,"response":null,"advice":null,"exaIds":null,"exaContent":null,"exaFee":null,"status":1,"gmtCreate":"2018-12-17 18:15:20","gmtModified":null,"isDel":null},{"id":1074837789088018400,"name":"小黑","gender":0,"age":6,"phone":null,"bloodType":null,"diet":null,"sleep":null,"characterDescribe":"咳嗽","voiceDescribe":null,"image":null,"patientId":null,"doctorId":null,"response":null,"advice":null,"exaIds":null,"exaContent":null,"exaFee":null,"status":1,"gmtCreate":"2018-12-18 09:24:33","gmtModified":null,"isDel":null},{"id":1074839493674135600,"name":"小小","gender":0,"age":3,"phone":null,"bloodType":null,"diet":null,"sleep":null,"characterDescribe":"发烧","voiceDescribe":null,"image":null,"patientId":null,"doctorId":null,"response":null,"advice":null,"exaIds":null,"exaContent":null,"exaFee":null,"status":1,"gmtCreate":"2018-12-18 09:31:20","gmtModified":null,"isDel":null},{"id":1074846751006916600,"name":"猪小弟","gender":0,"age":1,"phone":null,"bloodType":null,"diet":null,"sleep":null,"characterDescribe":"嗜睡","voiceDescribe":null,"image":null,"patientId":null,"doctorId":null,"response":null,"advice":null,"exaIds":null,"exaContent":null,"exaFee":null,"status":1,"gmtCreate":"2018-12-18 10:00:10","gmtModified":null,"isDel":null},{"id":1074846886646517800,"name":"猪小弟","gender":0,"age":1,"phone":null,"bloodType":null,"diet":null,"sleep":null,"characterDescribe":"嗜睡","voiceDescribe":null,"image":null,"patientId":null,"doctorId":null,"response":null,"advice":null,"exaIds":null,"exaContent":null,"exaFee":null,"status":1,"gmtCreate":"2018-12-18 10:00:42","gmtModified":null,"isDel":null},{"id":1074851191885729800,"name":"小家伙","gender":0,"age":3,"phone":null,"bloodType":null,"diet":null,"sleep":null,"characterDescribe":"失眠","voiceDescribe":null,"image":null,"patientId":null,"doctorId":null,"response":null,"advice":null,"exaIds":null,"exaContent":null,"exaFee":null,"status":1,"gmtCreate":"2018-12-18 10:17:49","gmtModified":null,"isDel":null},{"id":1074851269622960100,"name":"小家伙","gender":0,"age":3,"phone":null,"bloodType":null,"diet":null,"sleep":null,"characterDescribe":"失眠","voiceDescribe":null,"image":null,"patientId":null,"doctorId":null,"response":null,"advice":null,"exaIds":null,"exaContent":null,"exaFee":null,"status":1,"gmtCreate":"2018-12-18 10:18:07","gmtModified":null,"isDel":null}]
         * total : 12
         * size : 10
         * current : 1
         * searchCount : true
         * pages : 2
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

        public static class RecordsBean implements Serializable{
            /**
             * id : 1073521292302397400
             * name : 赵冬冬
             * gender : 0
             * age : 28
             * phone : null
             * bloodType : null
             * diet : null
             * sleep : null
             * characterDescribe : 哈哈
             * voiceDescribe : null
             * image : null
             * patientId : null
             * doctorId : null
             * response : null
             * advice : null
             * exaIds : null
             * exaContent : null
             * exaFee : null
             * status : 2
             * gmtCreate : 2018-12-14 18:13:16
             * gmtModified : null
             * isDel : null
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
            private String patientId;
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

            public String getPatientId() {
                return patientId;
            }

            public void setPatientId(String patientId) {
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
}
