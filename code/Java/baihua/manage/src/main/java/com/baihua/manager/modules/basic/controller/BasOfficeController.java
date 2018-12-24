package com.baihua.manager.modules.basic.controller;

import com.baihua.core.common.utils.R;
import com.baihua.core.modules.basic.entity.BasOfficeEntity;
import com.baihua.manager.modules.basic.service.BasOfficeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


/**
 * 科室
 *
 * @author zhaodongdong
 * @email
 * @date 2018-12-14 13:14:17
 */
@Api(tags = "科室")
@RestController
@RequestMapping("/sys/basoffice")
public class BasOfficeController {

	@Autowired
	private BasOfficeService basOfficeService;

	/**
	 * 列表
	 */
	@GetMapping("/list")
	@ApiOperation("列表 [医生]")
	public R list() {
		List<BasOfficeEntity> list = basOfficeService.list();
		return R.success().addResData("list", list);
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Long id) {
		BasOfficeEntity basOffice = basOfficeService.getById(id);
		return R.success().addResData("basOffice", basOffice);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody BasOfficeEntity basOffice) {
		basOfficeService.save(basOffice);
		return R.success();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody BasOfficeEntity basOffice) {
		basOfficeService.updateById(basOffice);
		return R.success();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Long[] ids) {
		basOfficeService.removeByIds(Arrays.asList(ids));
		return R.success();
	}

}
