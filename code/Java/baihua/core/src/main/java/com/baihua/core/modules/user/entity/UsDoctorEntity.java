package com.baihua.core.modules.user.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.baihua.core.modules.service.entity.SerAdeptEntity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Getter;
import lombok.Setter;

/**
 * 医生
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-06 10:17:17
 */
@TableName("us_doctor")
public class UsDoctorEntity implements Serializable {
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
	 * 科室编号
	 */
	private Long offId;
	/**
	 * 科室名称
	 */
	private String offName;
	/**
	 * 职位编号
	 */
	private Long positionId;

	/**
	 * 职位名称
	 */
	private String positionName;

	/**
	 * 医院编号
	 */
	private Long hosId;

	/**
	 * 医院名称
	 */
	private String hosName;
	/**
	 * 个人照片
	 */
	private String photo;
	/**
	 * 医师资格证
	 */
	private String physicianLicence;
	/**
	 * 身份证 正反以,分割
	 */
	private String identityCard;
	/**
	 * 主治
	 */
	private String major;
	/**
	 * 治疗经历
	 */
	private String experience;
	/**
	 * 挂号费
	 */
	private BigDecimal registrationFee;
	/**
	 * 预约数量
	 */
	private Integer appointmentNum;
	/**
	 * 状态 1 未审核 2 审核
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
	 * 传值用
	 */
	@TableField(exist = false)
	private List<SerAdeptEntity> adeptEntities;

	@Getter
	@Setter
	@TableField(exist = false)
	private Long accountId;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof UsDoctorEntity)) return false;

		UsDoctorEntity that = (UsDoctorEntity) o;

		return id != null ? id.equals(that.id) : that.id == null;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

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
	 * 设置：科室编号
	 */
	public void setOffId(Long offId) {
		this.offId = offId;
	}
	/**
	 * 获取：科室编号
	 */
	public Long getOffId() {
		return offId;
	}
	/**
	 * 设置：科室名称
	 */
	public void setOffName(String offName) {
		this.offName = offName;
	}
	/**
	 * 获取：科室名称
	 */
	public String getOffName() {
		return offName;
	}
	/**
	 * 设置：职位名称
	 */
	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}
	/**
	 * 获取：职位名称
	 */
	public Long getPositionId() {
		return this.positionId;
	}

	/**
	 * 设置：职位名称
	 */
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	/**
	 * 获取：职位名称
	 */
	public String getPositionName() {
		return this.positionName;
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
	 * 设置：医院名称
	 */
	public void setHosName(String hosName) {
		this.hosName = hosName;
	}
	/**
	 * 获取：医院名称
	 */
	public String getHosName() {
		return hosName;
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
	 * 设置：医师资格证
	 */
	public void setPhysicianLicence(String physicianLicence) {
		this.physicianLicence = physicianLicence;
	}
	/**
	 * 获取：医师资格证
	 */
	public String getPhysicianLicence() {
		return physicianLicence;
	}
	/**
	 * 设置：身份证 正反以,分割
	 */
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	/**
	 * 获取：身份证 正反以,分割
	 */
	public String getIdentityCard() {
		return identityCard;
	}
	/**
	 * 设置：主治
	 */
	public void setMajor(String major) {
		this.major = major;
	}
	/**
	 * 获取：主治
	 */
	public String getMajor() {
		return major;
	}
	/**
	 * 设置：治疗经历
	 */
	public void setExperience(String experience) {
		this.experience = experience;
	}
	/**
	 * 获取：治疗经历
	 */
	public String getExperience() {
		return experience;
	}
	/**
	 * 设置：挂号费
	 */
	public void setRegistrationFee(BigDecimal registrationFee) {
		this.registrationFee = registrationFee;
	}
	/**
	 * 获取：挂号费
	 */
	public BigDecimal getRegistrationFee() {
		return registrationFee;
	}
	/**
	 * 设置：预约数量
	 */
	public void setAppointmentNum(Integer appointmentNum) {
		this.appointmentNum = appointmentNum;
	}
	/**
	 * 获取：预约数量
	 */
	public Integer getAppointmentNum() {
		return appointmentNum;
	}
	/**
	 * 设置：状态 1 未审核 2 审核
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态 1 未审核 2 审核
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

	public List<SerAdeptEntity> getAdeptEntities() {
		return adeptEntities;
	}

	public void setAdeptEntities(List<SerAdeptEntity> adeptEntities) {
		this.adeptEntities = adeptEntities;
	}
}
