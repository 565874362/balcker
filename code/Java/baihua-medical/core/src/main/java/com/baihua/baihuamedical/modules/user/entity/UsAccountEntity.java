package com.baihua.baihuamedical.modules.user.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;


/**
 * 账号
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-06 10:17:17
 */
@TableName("us_account")
public class UsAccountEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@TableId
	private Long id;
	/**
	 * 账号
	 */
	private String account;
	/**
	 * 密码
	 */
	private String pwd;
	/**
	 * 盐
	 */
	private String salt;
	/**
	 * 账户类型:1 医生 2 患者
	 */
	private Integer type;
	/**
	 * 账户主体编号
	 */
	private Long sId;
	/**
	 * 账户主体名称
	 */
	private String sName;
	/**
	 * 账户状态 1 待激活 2 正常 3 冻结
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
	@TableLogic
	@TableField(fill = FieldFill.INSERT)
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
	 * 设置：账号
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	/**
	 * 获取：账号
	 */
	public String getAccount() {
		return account;
	}
	/**
	 * 设置：密码
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	/**
	 * 获取：密码
	 */
	public String getPwd() {
		return pwd;
	}
	/**
	 * 设置：盐
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}
	/**
	 * 获取：盐
	 */
	public String getSalt() {
		return salt;
	}
	/**
	 * 设置：账户类型:1 医生 2 患者
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：账户类型:1 医生 2 患者
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：账户主体编号
	 */
	public void setSId(Long sId) {
		this.sId = sId;
	}
	/**
	 * 获取：账户主体编号
	 */
	public Long getSId() {
		return sId;
	}
	/**
	 * 设置：账户主体名称
	 */
	public void setSName(String sName) {
		this.sName = sName;
	}
	/**
	 * 获取：账户主体名称
	 */
	public String getSName() {
		return sName;
	}
	/**
	 * 设置：账户状态 1 待激活 2 正常 3 冻结
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：账户状态 1 待激活 2 正常 3 冻结
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
