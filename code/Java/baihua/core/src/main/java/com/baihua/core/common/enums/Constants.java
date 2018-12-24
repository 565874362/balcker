package com.baihua.core.common.enums;

/**
 * @author zhaodongdong
 * @version V1.0
 * @Title Constants.java
 * @Package com.baihua.baihuamedical.common.enums
 * @date 2018年12月13日 17:29:30
 */
public interface Constants {

	/**
	 * 删除状态
	 */
	enum DelStatus {
		deleted(1),
		normal(0);

		private int code;

		DelStatus(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}
	}

	/**
	 * 性别
	 */
	enum GenderStatus {

		male(1),
		female(0);

		private int code;

		GenderStatus(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}
	}

	/**
	 * 账户类型
	 */
	enum AccountType {

		doctor(1),
		patient(2);

		private int code;

		AccountType(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}
	}

	/**
	 * 账户状态
	 */
	enum AccountStatus {

		waitactive(1),
		normal(2),
		freeze(3);

		private int code;

		AccountStatus(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}

		public static AccountStatus valueOf(int code){
			for (AccountStatus each : values()) {
				if(each.getCode() == code){
					return each;
				}
			}
			return null;
		}
	}

	/**
	 * 广告位置
	 */
	enum AdPosition {

		front(1);

		private int code;

		AdPosition(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}
	}

	/**
	 * 问诊状态
	 */
	enum InquiryStatus {

		waitcheck(1),
		checked(2),
		replied(3);

		private int code;

		InquiryStatus(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}
	}

	/**
	 * 字典类别
	 */
	enum DictionaryType {

		sleep(1),
		appetite(2);

		private int code;

		DictionaryType(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}
	}

	/**
	 * 医生状态
	 */
	enum DoctorStatus {

		uncheck(1),

		checked(2);

		private int code;

		DoctorStatus(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}
	}

	/**
	 * 接诊时间类型
	 */
	enum ScheduleType {

		morning(0,"上午"),
		afternoon(1,"下午");

		private int code;

		private String msg;

		ScheduleType(int code,String msg) {
			this.code = code;
			this.msg = msg;
		}

		public String getMsg() {
			return msg;
		}

		public int getCode() {
			return code;
		}

		public static ScheduleType valueOf(int code){
			for (ScheduleType each : values()) {
				if(each.getCode() == code){
					return each;
				}
			}
			return null;
		}
	}

	/**
	 * 挂号状态
	 */
	enum RegistrationStatus {

		unpaid(1),
		paid(2),
		reception(3);

		private int code;

		RegistrationStatus(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}

	}

	/**
	 * 支付方式
	 */
	enum PayType {

		weixin(1),
		alipay(2);

		private int code;

		PayType(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}
	}

	/**
	 * 敏感词状态
	 */
	enum KeywordStatus {

		valid(1),
		invalid(0);

		private int code;

		KeywordStatus(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}
	}
}

