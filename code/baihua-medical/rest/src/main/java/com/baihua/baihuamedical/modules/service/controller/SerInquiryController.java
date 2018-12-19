package com.baihua.baihuamedical.modules.service.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.baihua.baihuamedical.common.enums.Constants;
import com.baihua.baihuamedical.common.exception.ParameterException;
import com.baihua.baihuamedical.common.utils.PageQuery;
import com.baihua.baihuamedical.common.utils.PageUtils;
import com.baihua.baihuamedical.common.utils.R;
import com.baihua.baihuamedical.common.validator.ValidatorUtils;
import com.baihua.baihuamedical.modules.basic.entity.BasHealthExaminationEntity;
import com.baihua.baihuamedical.modules.login.annotation.LoginPatient;
import com.baihua.baihuamedical.modules.service.entity.SerInquiryEntity;
import com.baihua.baihuamedical.modules.service.service.SerInquiryService;
import com.baihua.baihuamedical.modules.user.entity.UsDoctorEntity;
import com.baihua.baihuamedical.modules.user.entity.UsPatientEntity;
import com.baihua.baihuamedical.modules.user.service.UsDoctorService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;


/**
 * 问诊
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-06 10:17:17
 */
@Api(tags = "问诊")
@RestController
@RequestMapping("/rest/serinquiry")
public class SerInquiryController {

    @Autowired
    private SerInquiryService serInquiryService;

    @Autowired
    private UsDoctorService doctorService;

    @ApiOperation("用户问诊列表 [患者]")
    @PostMapping("/patientList")
    public R userList(@RequestBody PageQuery pageQuery,@ApiIgnore @LoginPatient UsPatientEntity patientEntity) {
        ValidatorUtils.validateEntity(pageQuery);
        Page<SerInquiryEntity> page = new Page<>();
        BeanUtils.copyProperties(pageQuery,page);
        LambdaQueryWrapper<SerInquiryEntity> queryWrapper = new QueryWrapper<SerInquiryEntity>().lambda();
        queryWrapper.eq(SerInquiryEntity::getPatientId, patientEntity.getId());
        queryWrapper.select(SerInquiryEntity::getId,SerInquiryEntity::getName,SerInquiryEntity::getAge,SerInquiryEntity::getCharacterDescribe,SerInquiryEntity::getGender,SerInquiryEntity::getStatus,SerInquiryEntity::getGmtCreate);
        IPage content = serInquiryService.page(page, queryWrapper);
        return R.success().addResData("page",content);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = serInquiryService.queryPage(params);
        return R.success().addResData("page", page);
    }

    /**
     * 信息
     */
    @ApiOperation("用户问诊详细 [患者]")
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        SerInquiryEntity serInquiry = serInquiryService.getById(id);
        if(serInquiry == null){
            throw new ParameterException("问诊不存在");
        }
        Map<String,Object> data = new HashMap<>();
        data.put("info", serInquiry);
        if(serInquiry.getStatus().intValue() != Constants.InquiryStatus.waitcheck.getCode()){
            data.put("doctor",doctorService.getOne(new QueryWrapper<UsDoctorEntity>()
                    .lambda()
                    .select(UsDoctorEntity::getName,UsDoctorEntity::getGender,UsDoctorEntity::getOffName,UsDoctorEntity::getPhoto,UsDoctorEntity::getPositionName)
                    .eq(UsDoctorEntity::getId,serInquiry.getDoctorId())));
            List<BasHealthExaminationEntity> basHealthExaminationEntities = JSON.parseArray(serInquiry.getExaContent(), BasHealthExaminationEntity.class);
            data.put("healthExaminations",basHealthExaminationEntities);
        }
        return R.success().addResData(data);
    }

    /**
     * 保存
     */
    @ApiOperation("增加问诊 [患者]")
    @PostMapping("/save")
    public R save(@RequestBody SaveInput serInquiry, @ApiIgnore @LoginPatient UsPatientEntity patientEntity){
        ValidatorUtils.validateEntity(serInquiry);
        SerInquiryEntity entity = new SerInquiryEntity();
        BeanUtils.copyProperties(serInquiry, entity);
        entity.setPatientId(patientEntity.getId());
        entity.setStatus(Constants.InquiryStatus.waitcheck.getCode());
        serInquiryService.save(entity);
        return R.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SerInquiryEntity serInquiry){
        serInquiryService.updateById(serInquiry);
        return R.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        serInquiryService.removeByIds(Arrays.asList(ids));
        return R.success();
    }


    @ApiModel("添加实体")
    @Data
    private static class SaveInput {
        /**
         * 名称
         */
        @NotEmpty
        @ApiModelProperty("患者名称")
        private String name;
        /**
         * 性别:1 男 0 女
         */
        @ApiModelProperty("性别 1 男 0 女")
        @NotNull
        private Integer gender;
        /**
         * 年龄
         */
        @NotNull
        @ApiModelProperty("年龄")
        private Integer age;
        /**
         * 电话
         */
        @NotEmpty
        @ApiModelProperty("电话")
        private String phone;
        /**
         * 饮食
         */
        @NotNull
        @ApiModelProperty("饮食")
        private Integer diet;
        /**
         * 睡眠
         */
        @NotNull
        @ApiModelProperty("睡眠")
        private Integer sleep;
        /**
         * 文字描述
         */
        @ApiModelProperty("症状描述")
        @NotEmpty
        private String characterDescribe;
        /**
         * 语音描述
         */
        @ApiModelProperty("语音描述")
        private String voiceDescribe;
        /**
         * 照片
         */
        @ApiModelProperty("以前检查结果")
        private String image;
    }
}
