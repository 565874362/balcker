package com.baihua.core.modules.user.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 
 * 
 * @author zhaodongdong
 * @email 
 * @date 2018-12-14 09:38:26
 */
@TableName("us_token")
public class UsTokenEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@TableId
	private Long id;
	/**
	 * token
	 */
	private String token;
	/**
	 * 用户账号
	 */
	private Long accountId;
	/**
	 * 过期时间
	 */
	private Date expireTime;
	/**
	 * 最后访问时间
	 */
	private Date lastTime;

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
	 * 设置：token
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * 获取：token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * 设置：用户账号
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	/**
	 * 获取：用户账号
	 */
	public Long getAccountId() {
		return accountId;
	}
	/**
	 * 设置：过期时间
	 */
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	/**
	 * 获取：过期时间
	 */
	public Date getExpireTime() {
		return expireTime;
	}
	/**
	 * 设置：最后访问时间
	 */
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	/**
	 * 获取：最后访问时间
	 */
	public Date getLastTime() {
		return lastTime;
	}
}
