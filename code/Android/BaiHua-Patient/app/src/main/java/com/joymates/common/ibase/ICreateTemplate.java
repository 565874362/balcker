package com.joymates.common.ibase;

public interface ICreateTemplate {

	/**
	 * 方法的执行顺序
	 */
	public void dealSequence();


	/**
	 * 设置布局
	 */
	public abstract void setLayout();
	
	/**
	 * 设置handler
	 */
	public abstract void setHandler();
	
	/**
	 * 初始化布局
	 */
	public abstract void initMember();
	
	/**
	 * 设置监听
	 */
	public abstract void setListener();
}
