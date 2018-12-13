package com.baihua.common.rx;

/**
 * Created by JohnsonFan on 2017/9/29.
 */

public class Response<T> {

	public int ret;//约定  -1为server返回数据异常  200为正常范围
	public String msg;
	public T data;

	public int getRet() {
		return ret;
	}

	public void setRet(int ret) {
		this.ret = ret;
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
