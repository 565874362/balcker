package com.baihua.baihuamedical.modules.service.entity;


import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 出诊时间表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-06 10:17:18
 */
@TableName("ser_schedule")
public class SerScheduleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@TableId
	private Long id;
	/**
	 * 出诊日期
	 */
	private Date date;
	/**
	 * 上午开始时间
	 */
	private Date morningBegin;
	/**
	 * 上午结束时间
	 */
	private Date monringEnd;
	/**
	 * 上午接诊人数
	 */
	private Integer morningTotalNum;
	/**
	 * 上午接诊剩余人数
	 */
	private Integer morningRemainNum;
	/**
	 * 下午开始时间
	 */
	private Date afternoonBegin;
	/**
	 * 下午结束时间
	 */
	private Date afternoonEnd;
	/**
	 * 下午接诊人数
	 */
	private Integer afternoonTotalNum;
	/**
	 * 下午接诊剩余人数
	 */
	private Integer afternoonRemainNum;
	/**
	 * 医生编号
	 */
	private Long doctorId;
	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date gmtCreate;
	/**
	 * 修改时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date gmtModified;
	/**
	 * 删除状态 1 已删除 0 正常
	 */
	@TableField(fill = FieldFill.INSERT)
	@TableLogic
	private Integer isDel;

	/**
	 * 设置：编号
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：编号
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：出诊日期
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * 获取：出诊日期
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * 设置：上午开始时间
	 */
	public void setMorningBegin(Date morningBegin) {
		this.morningBegin = morningBegin;
	}
	/**
	 * 获取：上午开始时间
	 */
	public Date getMorningBegin() {
		return morningBegin;
	}
	/**
	 * 设置：上午结束时间
	 */
	public void setMonringEnd(Date monringEnd) {
		this.monringEnd = monringEnd;
	}
	/**
	 * 获取：上午结束时间
	 */
	public Date getMonringEnd() {
		return monringEnd;
	}
	/**
	 * 设置：上午接诊人数
	 */
	public void setMorningTotalNum(Integer morningTotalNum) {
		this.morningTotalNum = morningTotalNum;
	}
	/**
	 * 获取：上午接诊人数
	 */
	public Integer getMorningTotalNum() {
		return morningTotalNum;
	}
	/**
	 * 设置：上午接诊剩余人数
	 */
	public void setMorningRemainNum(Integer morningRemainNum) {
		this.morningRemainNum = morningRemainNum;
	}
	/**
	 * 获取：上午接诊剩余人数
	 */
	public Integer getMorningRemainNum() {
		return morningRemainNum;
	}
	/**
	 * 设置：下午开始时间
	 */
	public void setAfternoonBegin(Date afternoonBegin) {
		this.afternoonBegin = afternoonBegin;
	}
	/**
	 * 获取：下午开始时间
	 */
	public Date getAfternoonBegin() {
		return afternoonBegin;
	}
	/**
	 * 设置：下午结束时间
	 */
	public void setAfternoonEnd(Date afternoonEnd) {
		this.afternoonEnd = afternoonEnd;
	}
	/**
	 * 获取：下午结束时间
	 */
	public Date getAfternoonEnd() {
		return afternoonEnd;
	}
	/**
	 * 设置：下午接诊人数
	 */
	public void setAfternoonTotalNum(Integer afternoonTotalNum) {
		this.afternoonTotalNum = afternoonTotalNum;
	}
	/**
	 * 获取：下午接诊人数
	 */
	public Integer getAfternoonTotalNum() {
		return afternoonTotalNum;
	}
	/**
	 * 设置：下午接诊剩余人数
	 */
	public void setAfternoonRemainNum(Integer afternoonRemainNum) {
		this.afternoonRemainNum = afternoonRemainNum;
	}
	/**
	 * 获取：下午接诊剩余人数
	 */
	public Integer getAfternoonRemainNum() {
		return afternoonRemainNum;
	}
	/**
	 * 设置：医生编号
	 */
	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}
	/**
	 * 获取：医生编号
	 */
	public Long getDoctorId() {
		return doctorId;
	}
	/**
	 * 设置：创建时间
	 */
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getGmtCreate() {
		return gmtCreate;
	}
	/**
	 * 设置：修改时间
	 */
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getGmtModified() {
		return gmtModified;
	}
	/**
	 * 设置：删除状态 1 已删除 0 正常
	 */
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	/**
	 * 获取：删除状态 1 已删除 0 正常
	 */
	public Integer getIsDel() {
		return isDel;
	}
}
