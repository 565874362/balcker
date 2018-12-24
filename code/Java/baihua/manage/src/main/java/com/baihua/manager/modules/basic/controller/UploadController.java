package com.baihua.manager.modules.basic.controller;

import io.swagger.annotations.Api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaodongdong
 * @version V1.0
 * @Title UploadController.java
 * @Package com.baihua.baihuamedical.modules.basic.controller
 * @date 2018年09月27日 16:39:36
 */
@RestController
@RequestMapping("/rest/upload")
@Api(tags = "上传")
public class UploadController {

	public static final String VISIT_PREFIX = "/files";
/*
	@Autowired
	private ConfigProperties configProperties;

	*//**
	 * 上传文件
	 *//*
	@PostMapping
	@ApiOperation(value = "上传文件 IOS端")
	public R iosUpload(@RequestParam("file") MultipartFile[] files) throws Exception {
		if (files == null || files.length == 0) {
			throw new ParameterException("上传文件不能为空");
		}
		List<String> visitUrls = new ArrayList<>();
		for (MultipartFile each : files) {
			String visitUrl = FileUploadUtils.upload(each, configProperties.getUploadDirectory());
			visitUrls.add(configProperties.getFilePrex() + VISIT_PREFIX + visitUrl);
		}
		return R.success().addResData("urls",visitUrls);
	}

	*//**
	 * 上传文件
	 *//*
	@PostMapping("/android")
	@LoginIgnore
	@ApiOperation(value = "上传文件 安卓端")
	public R upload(MultipartFile[] files) throws Exception {
		if (files == null || files.length == 0) {
			throw new ParameterException("上传文件不能为空");
		}
		List<String> visitUrls = new ArrayList<>();
		for (MultipartFile each : files) {
			String visitUrl = FileUploadUtils.upload(each, configProperties.getUploadDirectory());
			visitUrls.add(configProperties.getFilePrex() + VISIT_PREFIX + visitUrl);
		}
		return R.success().addResData("urls",visitUrls);
	}*/
}

