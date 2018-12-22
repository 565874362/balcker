package com.baihua.yayayisheng.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Author:byd
 * Time:22/12/2018 10:27
 * Description: DoctorRegistrationListEntity
 */
public class DoctorRegistrationListEntity implements Serializable {

    /**
     * page : {"records":[{"gmtModified":"2018-12-22 10:01:43","gender":1,"patientId":8,"fee":150,"timePart":1,"gmtCreate":"2018-12-22 10:01:43","visitTime":"2018-12-22 周六","phone":"13474370876","doctorId":1075774356870684682,"name":"白小黑","id":10,"isDel":0,"payInfo":null,"age":8,"scheduleId":37,"status":2},{"gmtModified":"2018-12-22 09:58:48","gender":1,"patientId":8,"fee":150,"timePart":0,"gmtCreate":"2018-12-22 09:58:48","visitTime":"2018-12-22 周六","phone":"13474370876","doctorId":1075774356870684682,"name":"白小黑","id":9,"isDel":0,"payInfo":null,"age":8,"scheduleId":37,"status":2},{"gmtModified":"2018-12-22 09:46:54","gender":1,"patientId":8,"fee":150,"timePart":1,"gmtCreate":"2018-12-22 09:46:54","visitTime":"2018-12-22 周六","phone":"13474370876","doctorId":1075774356870684682,"name":"白小白","id":8,"isDel":0,"payInfo":null,"age":6,"scheduleId":38,"status":2}],"total":0,"size":10,"current":1,"searchCount":true,"pages":0}
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
         * records : [{"gmtModified":"2018-12-22 10:01:43","gender":1,"patientId":8,"fee":150,"timePart":1,"gmtCreate":"2018-12-22 10:01:43","visitTime":"2018-12-22 周六","phone":"13474370876","doctorId":1075774356870684682,"name":"白小黑","id":10,"isDel":0,"payInfo":null,"age":8,"scheduleId":37,"status":2},{"gmtModified":"2018-12-22 09:58:48","gender":1,"patientId":8,"fee":150,"timePart":0,"gmtCreate":"2018-12-22 09:58:48","visitTime":"2018-12-22 周六","phone":"13474370876","doctorId":1075774356870684682,"name":"白小黑","id":9,"isDel":0,"payInfo":null,"age":8,"scheduleId":37,"status":2},{"gmtModified":"2018-12-22 09:46:54","gender":1,"patientId":8,"fee":150,"timePart":1,"gmtCreate":"2018-12-22 09:46:54","visitTime":"2018-12-22 周六","phone":"13474370876","doctorId":1075774356870684682,"name":"白小白","id":8,"isDel":0,"payInfo":null,"age":6,"scheduleId":38,"status":2}]
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

        public static class RecordsBean implements Serializable{
            /**
             * gmtModified : 2018-12-22 10:01:43
             * gender : 1
             * patientId : 8
             * fee : 150
             * timePart : 1
             * gmtCreate : 2018-12-22 10:01:43
             * visitTime : 2018-12-22 周六
             * phone : 13474370876
             * doctorId : 1075774356870684682
             * name : 白小黑
             * id : 10
             * isDel : 0
             * payInfo : null
             * age : 8
             * scheduleId : 37
             * status : 2
             */

            private String gmtModified;
            private String gender;
            private String patientId;
            private String fee;
            private String timePart;
            private String gmtCreate;
            private String visitTime;
            private String phone;
            private String doctorId;
            private String name;
            private String id;
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
        }
    }
}
