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
 * 现金流水
 * 
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2018-12-06 10:17:17
 */
@TableName("ser_cash_flow")
public class SerCashFlowEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@TableId
	private Long id;
	/**
	 * 金额
	 */
	private BigDecimal amount;
	/**
	 * 付款方式 1 微信 2 支付宝
	 */
	private Integer type;
	/**
	 * 挂号编号
	 */
	private Long regId;
	/**
	 * 医院编号
	 */
	private Long hosId;
	/**
	 * 患者编号
	 */
	private Long patId;
	/**
	 * 医生编号
	 */
	private Long docId;
	/**
	 * 第三方支付编号
	 */
	private String thirdId;
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
	 * 设置：金额
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 * 获取：金额
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	/**
	 * 设置：付款方式 1 微信 2 支付宝
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：付款方式 1 微信 2 支付宝
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：挂号编号
	 */
	public void setRegId(Long regId) {
		this.regId = regId;
	}
	/**
	 * 获取：挂号编号
	 */
	public Long getRegId() {
		return regId;
	}
	/**
	 * 设置：医院编号
	 */
	public void setHosId(Long hosId) {
		this.hosId = hosId;
	}
	/**
	 * 获取：医院编号
	 */
	public Long getHosId() {
		return hosId;
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
	 * 设置：第三方支付编号
	 */
	public void setThirdId(String thirdId) {
		this.thirdId = thirdId;
	}
	/**
	 * 获取：第三方支付编号
	 */
	public String getThirdId() {
		return thirdId;
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
