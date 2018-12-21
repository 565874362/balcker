package com.baihua.yayayisheng.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Author:byd
 * Time:20/12/2018 11:51
 * Description: DiagnoseDateEntity
 */
public class DiagnoseDateEntity implements Serializable {

    private List<DiagnoseDatesBean> diagnoseDates;

    public List<DiagnoseDatesBean> getDiagnoseDates() {
        return diagnoseDates;
    }

    public void setDiagnoseDates(List<DiagnoseDatesBean> diagnoseDates) {
        this.diagnoseDates = diagnoseDates;
    }

    public static class DiagnoseDatesBean implements Serializable {
        /**
         * date : 2018-12-24
         * week : 周一
         * monthDay : 12-24
         */

        private String date;
        private String week;
        private String monthDay;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getMonthDay() {
            return monthDay;
        }

        public void setMonthDay(String monthDay) {
            this.monthDay = monthDay;
        }
    }
}
