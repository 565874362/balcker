package com.baihua.core.modules.user.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;

import javax.xml.transform.Result;

/**
 * 患者
 * 
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2018-12-06 10:17:17
 */
@TableName("us_patient")
@ApiModel(value = "患者")
public class UsPatientEntity implements Serializable {
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
	 * 个人照片
	 */
	private String photo;
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
	 * 设置：个人照片
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	/**
	 * 获取：个人照片
	 */
	public String getPhoto() {
		return photo;
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
