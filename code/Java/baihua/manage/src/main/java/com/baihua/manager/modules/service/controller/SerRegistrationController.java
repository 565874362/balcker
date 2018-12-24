package com.baihua.manager.modules.service.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baihua.core.common.enums.Constants;
import com.baihua.core.common.utils.DateUtils;
import com.baihua.core.common.utils.PageQuery;
import com.baihua.core.common.utils.R;
import com.baihua.core.modules.service.entity.SerRegistrationEntity;
import com.baihua.manager.modules.service.service.SerRegistrationService;
import com.baihua.core.modules.user.entity.UsDoctorEntity;
import com.baihua.manager.modules.user.service.UsDoctorService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Map;


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
     * 对应医生挂号列表
     */
    @PostMapping("/queryDoctor")
    @ApiOperation("对应医生挂号列表")
    public R queryDoctor(@RequestBody QueryByDoctor queryByDoctor){
        LambdaQueryWrapper<SerRegistrationEntity> queryWrapper = new LambdaQueryWrapper<SerRegistrationEntity>()
                .eq(SerRegistrationEntity::getDoctorId,queryByDoctor.getDoctorId())
                .in(SerRegistrationEntity::getStatus,2,3);
        IPage content = serRegistrationService.page(queryByDoctor.getPage(), queryWrapper);
        return R.success().addResData("data",content);
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
    @ApiModel("挂号输入")
    @Data
    private static  class  QueryByDoctor extends PageQuery<SerRegistrationEntity>{
        @ApiModelProperty("医生编号")
        private Long doctorId;
    }

    @ApiModel("患者挂号列表")
    private static class PatientListInput extends PageQuery<SerRegistrationEntity> {
    }
}
