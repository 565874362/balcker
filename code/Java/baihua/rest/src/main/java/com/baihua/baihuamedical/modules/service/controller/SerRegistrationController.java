package com.baihua.baihuamedical.modules.service.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baihua.baihuamedical.common.enums.Constants;
import com.baihua.baihuamedical.common.utils.DateUtils;
import com.baihua.baihuamedical.common.utils.PageQuery;
import com.baihua.baihuamedical.common.utils.R;
import com.baihua.baihuamedical.common.validator.ValidatorUtils;
import com.baihua.baihuamedical.modules.login.annotation.LoginPatient;
import com.baihua.baihuamedical.modules.service.entity.SerCashFlowEntity;
import com.baihua.baihuamedical.modules.service.entity.SerRegistrationEntity;
import com.baihua.baihuamedical.modules.service.service.SerCashFlowService;
import com.baihua.baihuamedical.modules.service.service.SerRegistrationService;
import com.baihua.baihuamedical.modules.user.entity.UsDoctorEntity;
import com.baihua.baihuamedical.modules.user.entity.UsPatientEntity;
import com.baihua.baihuamedical.modules.user.service.UsDoctorService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;


/**
 * 挂号
 *
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2018-12-06 10:17:18
 */
@Api(tags = "挂号")
@RestController
@RequestMapping("/rest/serregistration")
public class SerRegistrationController {

    @Autowired
    private SerRegistrationService serRegistrationService;

    @Autowired
    private UsDoctorService doctorService;

    @Autowired
    private SerCashFlowService cashFlowService;

    /**
     * 患者列表
     */
    @ApiOperation("患者列表")
    @PostMapping("/patientList")
    public R patientList(@RequestBody PatientListInput patientListInput,@ApiIgnore @LoginPatient UsPatientEntity patientEntity){
        ValidatorUtils.validateEntity(patientListInput);
        IPage<SerRegistrationEntity> page = serRegistrationService.page(patientListInput.getPage(),
                new QueryWrapper<SerRegistrationEntity>().lambda()
                .eq(SerRegistrationEntity::getPatientId, patientEntity.getId()));
        if(page.getTotal() == 0){
            return R.success().addResData("page", page);
        }
        List<Long> doctorIds = page.getRecords().stream()
                .map(e -> e.getDoctorId())
                .collect(Collectors.toList());
        List<Long> registrationIds = page.getRecords().stream()
                .map(e -> e.getId())
                .collect(Collectors.toList());
        Map<Long, UsDoctorEntity> doctorIdMap = doctorService.listByIds(doctorIds)
                .stream()
                .collect(Collectors.toMap(UsDoctorEntity::getId, e -> e));
        Map<Long, SerCashFlowEntity> cashFlowIdMap = cashFlowService.list(new QueryWrapper<SerCashFlowEntity>().lambda()
                .select(SerCashFlowEntity::getRegId,SerCashFlowEntity::getAmount,SerCashFlowEntity::getType,SerCashFlowEntity::getGmtCreate)
                .in(SerCashFlowEntity::getRegId,registrationIds))
                .stream()
                .collect(Collectors.toMap(SerCashFlowEntity::getRegId, e -> e));
        List<Map<String, Object>> content = new ArrayList<>();
        page.getRecords().forEach(e -> {
            Map<String, Object> each = BeanUtil.beanToMap(e);
            content.add(each);
            each.put("visitTime",DateUtils.getYearDateWeek(e.getVisitTime()));
            each.put("doctor",doctorIdMap.get(e.getDoctorId()));
            each.put("payInfo",cashFlowIdMap.get(e.getId()));
        });
        IPage pageResult = new Page<>();
        BeanUtils.copyProperties(page,pageResult);
        pageResult.setRecords(content);
        return R.success().addResData("page", pageResult);
    }

    /**
     * 患者列表
     */
    @ApiOperation("医生接诊列表 [医生]")
    @PostMapping("/doctorList")
    public R doctorList(@RequestBody PatientListInput patientListInput,@ApiIgnore @LoginPatient UsPatientEntity patientEntity){
        ValidatorUtils.validateEntity(patientListInput);
        IPage<SerRegistrationEntity> page = serRegistrationService.page(patientListInput.getPage(),
                new QueryWrapper<SerRegistrationEntity>().lambda()
                        .eq(SerRegistrationEntity::getPatientId, patientEntity.getId()));
        if(page.getTotal() == 0){
            return R.success().addResData("page", page);
        }
        List<Long> doctorIds = page.getRecords().stream()
                .map(e -> e.getDoctorId())
                .collect(Collectors.toList());
        List<Long> registrationIds = page.getRecords().stream()
                .map(e -> e.getId())
                .collect(Collectors.toList());
        Map<Long, UsDoctorEntity> doctorIdMap = doctorService.listByIds(doctorIds)
                .stream()
                .collect(Collectors.toMap(UsDoctorEntity::getId, e -> e));
        Map<Long, SerCashFlowEntity> cashFlowIdMap = cashFlowService.list(new QueryWrapper<SerCashFlowEntity>().lambda()
                .select(SerCashFlowEntity::getRegId,SerCashFlowEntity::getAmount,SerCashFlowEntity::getType,SerCashFlowEntity::getGmtCreate)
                .in(SerCashFlowEntity::getRegId,registrationIds))
                .stream()
                .collect(Collectors.toMap(SerCashFlowEntity::getRegId, e -> e));
        List<Map<String, Object>> content = new ArrayList<>();
        page.getRecords().forEach(e -> {
            Map<String, Object> each = BeanUtil.beanToMap(e);
            content.add(each);
            each.put("visitTime",DateUtils.getYearDateWeek(e.getVisitTime()));
            each.put("doctor",doctorIdMap.get(e.getDoctorId()));
            each.put("payInfo",cashFlowIdMap.get(e.getId()));
        });
        IPage pageResult = new Page<>();
        BeanUtils.copyProperties(page,pageResult);
        pageResult.setRecords(content);
        return R.success().addResData("page", pageResult);
    }

    /**
     * 挂号信息
     */
    @ApiOperation("挂号信息")
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        SerRegistrationEntity serRegistration = serRegistrationService.getById(id);
        UsDoctorEntity doctor = doctorService.getById(serRegistration.getDoctorId());
        Map<String, Object> serRegistrationData = BeanUtil.beanToMap(serRegistration);
        serRegistrationData.put("visitTime", DateUtils.getYearDateWeek(serRegistration.getVisitTime()) + " " + Constants.ScheduleType.valueOf(serRegistration.getTimePart().intValue()).getMsg());
        serRegistrationData.put("doctor", doctor);
        return R.success().addResData("info", serRegistrationData);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody SerRegistrationEntity serRegistration){
        serRegistrationService.save(serRegistration);
        return R.success();
    }

    /**
     * 挂号
     */
    @PostMapping("/registration")
    @ApiOperation("挂号")
    public R update(@RequestBody RegistrationInput registrationInput, @ApiIgnore @LoginPatient UsPatientEntity patientEntity){
        ValidatorUtils.validateEntity(registrationInput);
        SerRegistrationEntity serRegistrationEntity = new SerRegistrationEntity();
        BeanUtils.copyProperties(registrationInput,serRegistrationEntity);
        serRegistrationEntity.setPatientId(patientEntity.getId());
        serRegistrationEntity.setVisitTime(DateUtils.stringToDate(registrationInput.getVisitTime(),"yyyy-MM-dd"));
        serRegistrationService.registrate(serRegistrationEntity);
        return R.success().addResData("registrationId",serRegistrationEntity.getId());
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        serRegistrationService.removeByIds(Arrays.asList(ids));
        return R.success();
    }

    @ApiModel("挂号输入")
    @Data
    private static class RegistrationInput {
        /**
         * 名称
         */
        @ApiModelProperty("患者名称")
        @NotEmpty(message = "患者名称不能为空")
        private String name;
        /**
         * 性别:1 男 2 女
         */
        @ApiModelProperty("性别 1 男 0 女")
        @NotNull(message = "性别不能为空")
        private Integer gender;
        /**
         * 年龄
         */
        @ApiModelProperty("年龄")
        @NotNull(message = "年龄不能为空")
        private Integer age;
        /**
         * 电话
         */
        @ApiModelProperty("电话")
        @NotEmpty(message = "电话不能为空")
        private String phone;
        /**
         * 挂号时间
         */
        @ApiModelProperty("挂号时间 yyyy-MM-dd")
        @NotNull(message = "挂号时间不能为空")
        private String visitTime;
        /**
         * 时间区间   0 上午 1 下午
         */
        @ApiModelProperty("时间区间   0 上午 1 下午")
        @NotNull(message = "时间区间不能为空")
        private Integer timePart;
        /**
         * 出诊时间表编号
         */
        @ApiModelProperty("出诊时间表编号")
        @NotNull(message = "出诊时间表编号不能为空")
        private Long scheduleId;
        /**
         * 医生编号
         */
        @ApiModelProperty("医生编号")
        @NotNull(message = "医生编号不能为空")
        private Long doctorId;
    }

    @ApiModel("患者挂号列表")
    private static class PatientListInput extends PageQuery<SerRegistrationEntity> {
    }
}
