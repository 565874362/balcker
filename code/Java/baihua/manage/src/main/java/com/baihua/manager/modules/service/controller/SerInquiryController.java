package com.baihua.manager.modules.service.controller;

import com.alibaba.fastjson.JSON;
import com.baihua.core.common.enums.Constants;
import com.baihua.core.common.exception.ParameterException;
import com.baihua.core.common.utils.PageQuery;
import com.baihua.core.common.utils.R;
import com.baihua.core.modules.basic.entity.BasHealthExaminationEntity;
import com.baihua.core.modules.service.entity.SerInquiryEntity;
import com.baihua.core.modules.user.entity.UsDoctorEntity;
import com.baihua.manager.modules.service.service.SerInquiryService;
import com.baihua.manager.modules.user.service.UsDoctorService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;


/**
 * 后台问诊
 *
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2018-12-06 10:17:17
 */
@Api(tags = "后台问诊")
@RestController
@RequestMapping("/service/serinquiry")
public class SerInquiryController {

	@Autowired
	private SerInquiryService serInquiryService;

	@Autowired
	private UsDoctorService doctorService;





	/**
	 * 患者会话历史
	 */
    @ApiOperation("患者会话历史")
	@PostMapping("/list")
	public R list(@RequestBody QueryInquiry queryInquiry) {
    	IPage<Map<String,Object>> list= serInquiryService.queryInquiry(queryInquiry.getPage(),queryInquiry.getOfficeid(),queryInquiry.getOfficeid(),queryInquiry.getHospitalName(),queryInquiry.getStartDate(),queryInquiry.getEndDate());
		return R.success().addResData("page", list);
	}

	/**
	 * 信息
	 */
	@ApiOperation("用户问诊详细 [患者医生共用]")
	@GetMapping("/info/{id}")
	public R info(@PathVariable("id") Long id) {
		SerInquiryEntity serInquiry = serInquiryService.getById(id);
		if (serInquiry == null) {
			throw new ParameterException("问诊不存在");
		}
		Map<String, Object> data = new HashMap<>();
		data.put("info", serInquiry);
		if (serInquiry.getStatus().intValue() != Constants.InquiryStatus.waitcheck.getCode()) {
			data.put("doctor", doctorService.getOne(new QueryWrapper<UsDoctorEntity>()
					.lambda()
					.select(UsDoctorEntity::getId, UsDoctorEntity::getName, UsDoctorEntity::getGender, UsDoctorEntity::getOffName, UsDoctorEntity::getPhoto, UsDoctorEntity::getPositionName)
					.eq(UsDoctorEntity::getId, serInquiry.getDoctorId())));
			List<BasHealthExaminationEntity> basHealthExaminationEntities = JSON.parseArray(serInquiry.getExaContent(), BasHealthExaminationEntity.class);
			data.put("healthExaminations", basHealthExaminationEntities);
		}
		return R.success().addResData(data);
	}

	/**
	 * 病情列表
	 */
	@ApiOperation("病情列表")
	@PostMapping("/inqueryList")
	public R inqueryList(@RequestBody InquiryListInput listInput) {
		IPage<Map<String,Object>> list = serInquiryService.inqueryList(listInput.getPage(),listInput.getGender(),listInput.getStartDate(),listInput.getEndDate());
		return R.success().addResData("data",list);
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody SerInquiryEntity serInquiry) {
		serInquiryService.updateById(serInquiry);
		return R.success();
	}

	/**
	 * 删除
	 */
	@ApiOperation("问诊删除")
	@GetMapping("/delete/{id}")
	public R delete(@PathVariable Long id) {
		serInquiryService.delById(id);
		return R.success();
	}


	@ApiModel("问诊查询")
	@Data
	private static class QueryInquiry extends PageQuery<SerInquiryEntity> {

		/**
		 * 患者id
		 */
		@ApiModelProperty("患者id")
		private long inquiryid;
		/**
		 * 科室id
		 */
		@ApiModelProperty("科室id")
		private Long officeid;

		/**
		 * 单位名称
		 */
		@ApiModelProperty("单位名称")
		private String hospitalName;
		/**
		 * 开始时间
		 */
		@ApiModelProperty("开始时间")
		private String startDate;
		/**
		 * 结束时间
		 */
		@ApiModelProperty("结束时间")
		private String endDate;
	}

	@ApiModel("病情列表查看")
	@Data
	private static class InquiryListInput extends PageQuery<SerInquiryEntity> {
		/**
		 * 患者性别
		 */
		@ApiModelProperty("患者性别")
		private Integer gender;

		/**
		 * 开始时间
		 */
		@ApiModelProperty("开始时间")
		private String startDate;
		/**
		 * 结束时间
		 */
		@ApiModelProperty("结束时间")
		private String endDate;

	}
}
