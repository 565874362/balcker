package com.baihua.common.rx;

/**
 * Created by JohnsonFan on 2017/9/29.
 */

public class Response<T> {

	public int code;//约定  0为server返回数据正常  200为正常范围
	public String msg;
	public T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
