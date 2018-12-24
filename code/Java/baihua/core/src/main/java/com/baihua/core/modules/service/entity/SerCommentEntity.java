package com.baihua.core.modules.service.entity;


import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 评论
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-06 10:17:17
 */
@TableName("ser_comment")
public class SerCommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@TableId
	private Long id;
	/**
	 * 评论内容
	 */
	private String content;
	/**
	 * 评论人
	 */
	private Long patientId;
	/**
	 * 评论人照片
	 */
	private String patientPhoto;
	/**
	 * 评论人账号
	 */
	private String patientAccount;
	/**
	 * 医生编号
	 */
	private Long doctorId;

	/**
	 * 挂号编号
	 */
	private Long regId;

	/**
	 * 审核状态 1 未审核 2 已通过 3 拒绝
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
	 * 设置：评论内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：评论内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：评论人
	 */
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	/**
	 * 获取：评论人
	 */
	public Long getPatientId() {
		return patientId;
	}
	/**
	 * 设置：评论人照片
	 */
	public void setPatientPhoto(String patientPhoto) {
		this.patientPhoto = patientPhoto;
	}
	/**
	 * 获取：评论人照片
	 */
	public String getPatientPhoto() {
		return patientPhoto;
	}
	/**
	 * 设置：评论人账号
	 */
	public void setPatientAccount(String patientAccount) {
		this.patientAccount = patientAccount;
	}
	/**
	 * 获取：评论人账号
	 */
	public String getPatientAccount() {
		return patientAccount;
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

	public Long getRegId() {
		return regId;
	}

	public void setRegId(Long regId) {
		this.regId = regId;
	}
	/**
	 * 设置：审核状态 1 未审核 2 已通过 3 拒绝
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：审核状态 1 未审核 2 已通过 3 拒绝
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
