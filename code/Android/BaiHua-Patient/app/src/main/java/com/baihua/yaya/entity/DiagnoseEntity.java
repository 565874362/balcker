package com.baihua.yaya.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Author:byd
 * Time:18/12/2018 16:25
 * Description: DiagnoseEntity
 */
public class DiagnoseEntity implements Serializable {

    private List<DiagnoseListBean> diagnoseList;

    public List<DiagnoseListBean> getDiagnoseList() {
        return diagnoseList;
    }

    public void setDiagnoseList(List<DiagnoseListBean> diagnoseList) {
        this.diagnoseList = diagnoseList;
    }

    public static class DiagnoseListBean implements Serializable {
        /**
         * id : 1
         * fullDate : 2018-12-19 周三
         * date : 12-19 周三
         * morningRemainNum : 0
         * afternoonRemainNum : 30
         */

        private int id;
        private String fullDate;
        private String date;
        private int morningRemainNum;
        private int afternoonRemainNum;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFullDate() {
            return fullDate;
        }

        public void setFullDate(String fullDate) {
            this.fullDate = fullDate;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getMorningRemainNum() {
            return morningRemainNum;
        }

        public void setMorningRemainNum(int morningRemainNum) {
            this.morningRemainNum = morningRemainNum;
        }

        public int getAfternoonRemainNum() {
            return afternoonRemainNum;
        }

        public void setAfternoonRemainNum(int afternoonRemainNum) {
            this.afternoonRemainNum = afternoonRemainNum;
        }
    }
}
