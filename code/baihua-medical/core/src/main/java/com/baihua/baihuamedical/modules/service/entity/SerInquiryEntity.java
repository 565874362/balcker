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
 * 问诊
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-06 10:17:17
 */
@TableName("ser_inquiry")
public class SerInquiryEntity implements Serializable {
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
	 * 性别:1 男 0 女
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
	 * 血型
	 */
	private Integer bloodType;
	/**
	 * 饮食
	 */
	private Integer diet;
	/**
	 * 睡眠
	 */
	private Integer sleep;
	/**
	 * 文字描述
	 */
	private String characterDescribe;
	/**
	 * 语音描述
	 */
	private String voiceDescribe;
	/**
	 * 照片
	 */
	private String image;
	/**
	 * 患者编号
	 */
	private Long patientId;
	/**
	 * 医生编号
	 */
	private Long doctorId;
	/**
	 * 诊断结果
	 */
	private String response;
	/**
	 * 医嘱
	 */
	private String advice;
	/**
	 * 检查项编号 以,隔开
	 */
	private String exaIds;
	/**
	 * 检查项内容 (json)
	 */
	private String exaContent;
	/**
	 * 检查总价
	 */
	private BigDecimal exaFee;
	/**
	 * 问诊状态 1 等待抢单 2 已抢单 3 已回复
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
	 *
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
	 * 设置：性别:1 男 0 女
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	/**
	 * 获取：性别:1 男 0 女
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
	 * 设置：血型
	 */
	public void setBloodType(Integer bloodType) {
		this.bloodType = bloodType;
	}
	/**
	 * 获取：血型
	 */
	public Integer getBloodType() {
		return bloodType;
	}
	/**
	 * 设置：饮食
	 */
	public void setDiet(Integer diet) {
		this.diet = diet;
	}
	/**
	 * 获取：饮食
	 */
	public Integer getDiet() {
		return diet;
	}
	/**
	 * 设置：睡眠
	 */
	public void setSleep(Integer sleep) {
		this.sleep = sleep;
	}
	/**
	 * 获取：睡眠
	 */
	public Integer getSleep() {
		return sleep;
	}
	/**
	 * 设置：文字描述
	 */
	public void setCharacterDescribe(String characterDescribe) {
		this.characterDescribe = characterDescribe;
	}
	/**
	 * 获取：文字描述
	 */
	public String getCharacterDescribe() {
		return characterDescribe;
	}
	/**
	 * 设置：语音描述
	 */
	public void setVoiceDescribe(String voiceDescribe) {
		this.voiceDescribe = voiceDescribe;
	}
	/**
	 * 获取：语音描述
	 */
	public String getVoiceDescribe() {
		return voiceDescribe;
	}
	/**
	 * 设置：照片
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * 获取：照片
	 */
	public String getImage() {
		return image;
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
	 * 设置：诊断结果
	 */
	public void setResponse(String response) {
		this.response = response;
	}
	/**
	 * 获取：诊断结果
	 */
	public String getResponse() {
		return response;
	}
	/**
	 * 设置：医嘱
	 */
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	/**
	 * 获取：医嘱
	 */
	public String getAdvice() {
		return advice;
	}
	/**
	 * 设置：检查项编号 以,隔开
	 */
	public void setExaIds(String exaIds) {
		this.exaIds = exaIds;
	}
	/**
	 * 获取：检查项编号 以,隔开
	 */
	public String getExaIds() {
		return exaIds;
	}
	/**
	 * 设置：检查项内容 (json)
	 */
	public void setExaContent(String exaContent) {
		this.exaContent = exaContent;
	}
	/**
	 * 获取：检查项内容 (json)
	 */
	public String getExaContent() {
		return exaContent;
	}
	/**
	 * 设置：检查总价
	 */
	public void setExaFee(BigDecimal exaFee) {
		this.exaFee = exaFee;
	}
	/**
	 * 获取：检查总价
	 */
	public BigDecimal getExaFee() {
		return exaFee;
	}
	/**
	 * 设置：问诊状态 1 等待抢单 2 已抢单 3 已回复
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：问诊状态 1 等待抢单 2 已抢单 3 已回复
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
