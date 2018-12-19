package com.baihua.yayayisheng.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Author:byd
 * Time:17/12/2018 17:38
 * Description: DicEntity
 */
public class DicEntity implements Serializable {

    private List<DictionariesBean> dictionaries;

    public List<DictionariesBean> getDictionaries() {
        return dictionaries;
    }

    public void setDictionaries(List<DictionariesBean> dictionaries) {
        this.dictionaries = dictionaries;
    }

    public static class DictionariesBean {
        /**
         * id : 1
         * name : 睡眠增加
         * groupId : 1
         * gmtCreate : 2018-12-14 12:23:42
         * gmtModified : 2018-12-14 12:23:42
         * isDel : 0
         */

        private String id;
        private String name;
        private String groupId;
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

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
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
