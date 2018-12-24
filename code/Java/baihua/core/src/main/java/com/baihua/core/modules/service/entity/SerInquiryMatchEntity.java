package com.baihua.core.modules.service.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 问诊匹配
 * 
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2018-12-06 10:17:18
 */
@TableName("ser_inquiry_match")
public class SerInquiryMatchEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@TableId
	private Long id;
	/**
	 * 患者编号
	 */
	private Long patId;
	/**
	 * 医生编号
	 */
	private Long docId;
	/**
	 * 问诊编号
	 */
	private Long inquiryId;

	/**
	 * 匹配度
	 */
	private Integer matchNum;
	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date gmtCreate;
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
	 * 设置：患者编号
	 */
	public void setPatId(Long patId) {
		this.patId = patId;
	}
	/**
	 * 获取：患者编号
	 */
	public Long getPatId() {
		return patId;
	}
	/**
	 * 设置：医生编号
	 */
	public void setDocId(Long docId) {
		this.docId = docId;
	}
	/**
	 * 获取：医生编号
	 */
	public Long getDocId() {
		return docId;
	}
	/**
	 * 设置：问诊编号
	 */
	public void setInquiryId(Long inquiryId) {
		this.inquiryId = inquiryId;
	}
	/**
	 * 获取：问诊编号
	 */
	public Long getInquiryId() {
		return this.inquiryId;
	}

	public Integer getMatchNum() {
		return matchNum;
	}

	public void setMatchNum(Integer matchNum) {
		this.matchNum = matchNum;
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
