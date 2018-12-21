package com.baihua.yayayisheng.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Author:byd
 * Time:20/12/2018 16:46
 * Description: DiagnoseDateListEntity
 */
public class DiagnoseDateListEntity implements Serializable {

    private List<DiagnoseListBean> diagnoseList;

    public List<DiagnoseListBean> getDiagnoseList() {
        return diagnoseList;
    }

    public void setDiagnoseList(List<DiagnoseListBean> diagnoseList) {
        this.diagnoseList = diagnoseList;
    }

    public static class DiagnoseListBean implements Serializable {
        /**
         * id : 1075327723771015200
         * date : 周二
         * timepart : 下午
         * beginTime : 05:00
         * endTime : 12:00
         * totalNumber : 10
         */

        private String id;
        private String date;
        private String timepart;
        private String beginTime;
        private String endTime;
        private String totalNumber;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTimepart() {
            return timepart;
        }

        public void setTimepart(String timepart) {
            this.timepart = timepart;
        }

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getTotalNumber() {
            return totalNumber;
        }

        public void setTotalNumber(String totalNumber) {
            this.totalNumber = totalNumber;
        }
    }
}
