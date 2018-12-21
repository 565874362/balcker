package com.baihua.baihuamedical.modules.service.entity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 挂号
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-06 10:17:18
 */
@TableName("ser_registration")
public class SerRegistrationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@TableId
	private Long id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 性别:1 男 2 女
	 */
	private Integer gender;
	/**
	 * 年龄
	 */
	private Integer age;
	/**
	 * 电话
	 */
	private String phone;
	/**
	 * 挂号时间
	 */
	private Date visitTime;
	/**
	 * 时间区间   0 上午 1 下午
	 */
	private Integer timePart;
	/**
	 * 患者编号
	 */
	private Long patientId;
	/**
	 * 医生编号
	 */
	private Long doctorId;
	/**
	 * 出诊时间表编号
	 */
	private Long scheduleId;
	/**
	 * 费用
	 */
	private BigDecimal fee;
	/**
	 * 状态 1 未支付 2 已支付
	 */
	private Integer status;
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
	 * 设置：名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：性别:1 男 2 女
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	/**
	 * 获取：性别:1 男 2 女
	 */
	public Integer getGender() {
		return gender;
	}
	/**
	 * 设置：年龄
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	/**
	 * 获取：年龄
	 */
	public Integer getAge() {
		return age;
	}
	/**
	 * 设置：电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：电话
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：挂号时间
	 */
	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}
	/**
	 * 获取：挂号时间
	 */
	public Date getVisitTime() {
		return visitTime;
	}
	/**
	 * 设置：时间区间   0 上午 1 下午
	 */
	public void setTimePart(Integer timePart) {
		this.timePart = timePart;
	}
	/**
	 * 获取：时间区间   0 上午 1 下午
	 */
	public Integer getTimePart() {
		return timePart;
	}
	/**
	 * 设置：患者编号
	 */
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	/**
	 * 获取：患者编号
	 */
	public Long getPatientId() {
		return patientId;
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
	 * 设置：出诊时间表编号
	 */
	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}
	/**
	 * 获取：出诊时间表编号
	 */
	public Long getScheduleId() {
		return scheduleId;
	}
	/**
	 * 设置：费用
	 */
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	/**
	 * 获取：费用
	 */
	public BigDecimal getFee() {
		return fee;
	}
	/**
	 * 设置：状态 1 未支付 2 已支付
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态 1 未支付 2 已支付
	 */
	public Integer getStatus() {
		return status;
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
