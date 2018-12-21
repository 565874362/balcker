package com.baihua.yayayisheng.entity.form;

import java.util.List;

/**
 * Author:byd
 * Time:20/12/2018 17:43
 * Description: ResponseForm
 */
public class ResponseForm {

    /**
     * advice : string
     * exaIds : [0]
     * id : 0
     * response : string
     */

    private String advice; // 医嘱
    private int id; //诊断编号 ,
    private String response; // 诊断结果
    private List<Integer> exaIds; // 检查项编号

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<Integer> getExaIds() {
        return exaIds;
    }

    public void setExaIds(List<Integer> exaIds) {
        this.exaIds = exaIds;
    }
}
