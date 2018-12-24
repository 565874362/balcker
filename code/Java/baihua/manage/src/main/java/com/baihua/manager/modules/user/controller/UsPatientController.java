package com.baihua.manager.modules.user.controller;

import com.baihua.core.common.utils.PageQuery;
import com.baihua.core.common.utils.R;
import com.baihua.core.common.validator.ValidatorUtils;
import com.baihua.core.modules.service.entity.SerInquiryEntity;
import com.baihua.core.modules.user.entity.UsPatientEntity;
import com.baihua.manager.modules.service.service.SerInquiryService;
import com.baihua.manager.modules.user.service.UsPatientService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 后台患者
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-06 10:17:17
 */
@Api(tags ="后台患者")
@RestController
@RequestMapping("/user/patient")
public class UsPatientController {
    @Autowired
    private UsPatientService usPatientService;

    @Autowired
    private SerInquiryService inquiryService;

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
        IPage<Map<String,Object>>  page=  usPatientService.queryList(patientSearchInput.getPage(),patientSearchInput.getStartDate(),patientSearchInput.getEndsDate());
        return R.success().addResData("page", page);
    }

    /**
     * 后台患者状态修改
     */
    @ApiOperation("后台患者状态修改")
    @PostMapping("/updateIsdel")
    public R updateIsdel(@PathVariable UpdateIsDelInput updateIsDelInput){
        UsPatientEntity usPatientEntity = new UsPatientEntity();
        usPatientEntity.setId(updateIsDelInput.getPatientId());
        usPatientEntity.setIsDel(updateIsDelInput.getState());
        usPatientService.updateById(usPatientEntity);
        return R.success();
    }
    /**
     * 后台患者删除
     */
    @ApiOperation("后台患者删除")
    @PostMapping("/delPatient")
    public R delPatient(@PathVariable UpdateIsDelInput updateIsDelInput){
       LambdaQueryWrapper<SerInquiryEntity> batchquery = new LambdaQueryWrapper<SerInquiryEntity>()
               .eq(SerInquiryEntity::getPatientId,updateIsDelInput.getPatientId());
                inquiryService.remove(batchquery);
        return R.success();
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




    @ApiModel("查询参数")
    @Data
    private static class PatientSearchInput extends PageQuery<UsPatientEntity> {

        @ApiModelProperty("开始时间")
        private String startDate;

        @ApiModelProperty("结束时间")
        private String endsDate;
    }
    @ApiModel("患者状态修改")
    @Data
    private static class UpdateIsDelInput{

        @ApiModelProperty("患者id")
        private Long patientId;

        @ApiModelProperty("状态")
        private Integer state;
    }

}
