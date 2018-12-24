package com.baihua.rest.modules.user.controller;

import java.util.Arrays;
import java.util.Map;

import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baihua.core.common.utils.PageUtils;
import com.baihua.core.common.utils.R;
import com.baihua.core.common.validator.ValidatorUtils;
import com.baihua.rest.modules.login.annotation.LoginPatient;
import com.baihua.core.modules.user.entity.UsPatientEntity;
import com.baihua.rest.modules.user.service.UsPatientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;


/**
 * 患者
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-06 10:17:17
 */
@Api(tags ="患者")
@RestController
@RequestMapping("/rest/patient")
public class UsPatientController {
    @Autowired
    private UsPatientService usPatientService;

    @ApiOperation("获取图片")
    @GetMapping("/photo")
    public R getPhoto(@ApiIgnore @LoginPatient UsPatientEntity patientEntity){
        return R.success().addResData("photo",patientEntity.getPhoto());
    }

    @ApiOperation("设置图片")
    @PostMapping("/phone")
    public R setPhoto(@RequestBody PhotoInput photoInput,@ApiIgnore @LoginPatient UsPatientEntity patientEntity){
        ValidatorUtils.validateEntity(photoInput);
        patientEntity.setPhoto(photoInput.getPhotoUrl());
        usPatientService.updateById(patientEntity);
        return R.success();
    }

    @PostMapping(value = "/register")
    public R register(){
        return R.success();
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = usPatientService.queryPage(params);
        return R.success().addResData("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        UsPatientEntity usPatient = usPatientService.getById(id);
        return R.success().addResData("usPatient", usPatient);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody UsPatientEntity usPatient){
        usPatientService.save(usPatient);
        return R.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody UsPatientEntity usPatient){
        usPatientService.updateById(usPatient);
        return R.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        usPatientService.removeByIds(Arrays.asList(ids));
        return R.success();
    }

    @ApiModel("照片路径参数")
    @Data
    private static class PhotoInput{
        @ApiModelProperty("照片路径")
        @NotEmpty(message = "照片路径不能为空")
        private String photoUrl;
    }
}
