package com.baihua.baihuamedical.modules.user.controller;

import com.baihua.baihuamedical.common.utils.PageQuery;
import com.baihua.baihuamedical.common.utils.R;
import com.baihua.baihuamedical.common.validator.ValidatorUtils;
import com.baihua.baihuamedical.modules.user.entity.UsPatientEntity;
import com.baihua.baihuamedical.modules.user.service.UsPatientService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;



/**
 * 后台患者
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-06 10:17:17
 */
@Api(tags ="后台患者")
@RestController
@RequestMapping("/sys/patient")
public class UsPatientController {
    @Autowired
    private UsPatientService usPatientService;

   /* @ApiOperation("获取图片")
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
*/
    @PostMapping(value = "/register")
    public R register(){
        return R.success();
    }

    /**
     * 后台患者列表
     */
    @ApiOperation("后台患者列表")
    @PostMapping("/list")
    public R list(@RequestBody PatientSearchInput patientSearchInput){
        ValidatorUtils.validateEntity(patientSearchInput);
        LambdaQueryWrapper<UsPatientEntity> queryWrapper = new QueryWrapper<UsPatientEntity>().lambda()
                .between(UsPatientEntity::getGmtCreate,patientSearchInput.getStartDate(),patientSearchInput.getEndsDate());
        IPage content = usPatientService.page(patientSearchInput.getPage(), queryWrapper);
        return R.success().addResData("page", content);
    }

/*
    @ApiOperation("后台患者管理列表 [患者]")
    @PostMapping("/patientList")
    public R userList(@RequestBody PageQuery<SerInquiryEntity> pageQuery, @ApiIgnore @LoginPatient UsPatientEntity patientEntity) {
        ValidatorUtils.validateEntity(pageQuery);
        LambdaQueryWrapper<SerInquiryEntity> queryWrapper = new QueryWrapper<SerInquiryEntity>().lambda()
                .eq(SerInquiryEntity::getPatientId, patientEntity.getId())
                .select(SerInquiryEntity::getId, SerInquiryEntity::getName, SerInquiryEntity::getAge, SerInquiryEntity::getCharacterDescribe, SerInquiryEntity::getGender, SerInquiryEntity::getStatus, SerInquiryEntity::getGmtModified)
                .orderByDesc(SerInquiryEntity::getGmtModified);
        IPage content = serInquiryService.page(pageQuery.getPage(), queryWrapper);
        return R.success().addResData("page", content);
    }
*/

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




    @ApiModel("查询参数")
    @Data
    private static class PatientSearchInput extends PageQuery<UsPatientEntity>{

        @ApiModelProperty("开始时间")
        private String startDate;

        @ApiModelProperty("结束时间")
        private String endsDate;
    }

}
