package com.baihua.baihuamedical.common.utils;

import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhaodongdong
 * @version V1.0
 * @Title PageQuery.java
 * @Package com.baihua.baihuamedical.common.utils
 * @date 2018年12月14日 17:54:01
 */
@Data
public class PageQuery<T> {
	@ApiModelProperty("页下标 从1开始")
	@NotNull(message = "分页页数不能为空")
	private long current;
	@ApiModelProperty("页容量")
	@NotNull(message = "分页页容量不能为空")
	private long size;

	@ApiModelProperty(hidden = true)
	public IPage<T> getPage(){
		Page<T> page = new Page<T>();
		BeanUtils.copyProperties(this,page);
		return page;
	}
}

