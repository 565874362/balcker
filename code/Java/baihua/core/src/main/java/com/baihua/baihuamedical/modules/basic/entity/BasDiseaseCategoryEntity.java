package com.baihua.baihuamedical.modules.basic.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;


/**
 * 疾病分类
 * 
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2018-12-06 10:17:17
 */
@TableName("bas_disease_category")
public class BasDiseaseCategoryEntity implements Serializable {
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
	 * 描述
	 */
	private String describe;
	/**
	 * 父级编号
	 */
	private Long pId;
	/**
	 * 父级名称
	 */
	private String pName;
	/**
	 * 所有父级编号，从下而上以,分割
	 */
	private String pIds;
	/**
	 * 所有父级名称，从下而上以,分割
	 */
	private String pNames;
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
	 * 设置：描述
	 */
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	/**
	 * 获取：描述
	 */
	public String getDescribe() {
		return describe;
	}
	/**
	 * 设置：父级编号
	 */
	public void setPId(Long pId) {
		this.pId = pId;
	}
	/**
	 * 获取：父级编号
	 */
	public Long getPId() {
		return pId;
	}
	/**
	 * 设置：父级名称
	 */
	public void setPName(String pName) {
		this.pName = pName;
	}
	/**
	 * 获取：父级名称
	 */
	public String getPName() {
		return pName;
	}
	/**
	 * 设置：所有父级编号，从下而上以,分割
	 */
	public void setPIds(String pIds) {
		this.pIds = pIds;
	}
	/**
	 * 获取：所有父级编号，从下而上以,分割
	 */
	public String getPIds() {
		return pIds;
	}
	/**
	 * 设置：所有父级名称，从下而上以,分割
	 */
	public void setPNames(String pNames) {
		this.pNames = pNames;
	}
	/**
	 * 获取：所有父级名称，从下而上以,分割
	 */
	public String getPNames() {
		return pNames;
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
