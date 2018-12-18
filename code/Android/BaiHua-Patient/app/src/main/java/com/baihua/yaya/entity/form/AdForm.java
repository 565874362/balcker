package com.baihua.yaya.entity.form;

import java.io.Serializable;

/**
 * Author:byd
 * Time:17/12/2018 9:59
 * Description: AdForm
 */
public class AdForm implements Serializable {
    private int positionId; // 位置编号 1 首页广告
    private int terminal; // 端 1 医生 2 患者

    public AdForm() {
    }

    public AdForm(int positionId, int terminal) {
        this.positionId = positionId;
        this.terminal = terminal;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public int getTerminal() {
        return terminal;
    }

    public void setTerminal(int terminal) {
        this.terminal = terminal;
    }
}
