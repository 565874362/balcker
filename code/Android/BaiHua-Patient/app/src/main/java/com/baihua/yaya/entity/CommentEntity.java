package com.baihua.yaya.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Author:byd
 * Time:17/12/2018 15:15
 * Description: CommentEntity
 */
public class CommentEntity implements Serializable {

    /**
     * page : {"records":[{"id":1,"content":"很好","patientId":1073498376609280000,"patientPhoto":"","patientAccount":"13474370876","doctorId":1,"status":2,"gmtCreate":"2018-12-17 09:30:19","gmtModified":"2018-12-17 09:30:22","isDel":0}],"total":1,"size":10,"current":1,"searchCount":true,"pages":1}
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
         * records : [{"id":1,"content":"很好","patientId":1073498376609280000,"patientPhoto":"","patientAccount":"13474370876","doctorId":1,"status":2,"gmtCreate":"2018-12-17 09:30:19","gmtModified":"2018-12-17 09:30:22","isDel":0}]
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
             * content : 很好
             * patientId : 1073498376609280000
             * patientPhoto :
             * patientAccount : 13474370876
             * doctorId : 1
             * status : 2
             * gmtCreate : 2018-12-17 09:30:19
             * gmtModified : 2018-12-17 09:30:22
             * isDel : 0
             */

            private String id;
            private String content;
            private String patientId;
            private String patientPhoto;
            private String patientAccount;
            private String doctorId;
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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getPatientId() {
                return patientId;
            }

            public void setPatientId(String patientId) {
                this.patientId = patientId;
            }

            public String getPatientPhoto() {
                return patientPhoto;
            }

            public void setPatientPhoto(String patientPhoto) {
                this.patientPhoto = patientPhoto;
            }

            public String getPatientAccount() {
                return patientAccount;
            }

            public void setPatientAccount(String patientAccount) {
                this.patientAccount = patientAccount;
            }

            public String getDoctorId() {
                return doctorId;
            }

            public void setDoctorId(String doctorId) {
                this.doctorId = doctorId;
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
