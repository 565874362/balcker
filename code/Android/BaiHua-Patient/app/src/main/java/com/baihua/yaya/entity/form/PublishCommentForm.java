package com.baihua.yaya.entity.form;

import java.io.Serializable;

/**
 * Author:byd
 * Time:19/12/2018 10:37
 * Description: PublishCommentForm
 */
public class PublishCommentForm implements Serializable {

    public PublishCommentForm() {
    }

    public PublishCommentForm(String content, String doctorId, String regId) {
        this.content = content;
        this.doctorId = doctorId;
        this.regId = regId;
    }

    /**
     * content : 医生评论
     * doctorId : 1
     * regId : 0
     */

    private String content; // 评论信息
    private String doctorId; // 医生编号
    private String regId; // 挂号编号

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }
}
