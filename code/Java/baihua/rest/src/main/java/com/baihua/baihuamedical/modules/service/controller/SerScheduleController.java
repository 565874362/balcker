package com.baihua.baihuamedical.modules.service.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baihua.baihuamedical.common.utils.DateUtils;
import com.baihua.baihuamedical.common.utils.PageUtils;
import com.baihua.baihuamedical.common.utils.R;
import com.baihua.baihuamedical.common.validator.ValidatorUtils;
import com.baihua.baihuamedical.modules.login.annotation.LoginDoctor;
import com.baihua.baihuamedical.modules.login.annotation.LoginIgnore;
import com.baihua.baihuamedical.modules.service.entity.SerScheduleEntity;
import com.baihua.baihuamedical.modules.service.service.SerScheduleService;
import com.baihua.baihuamedical.modules.user.entity.UsDoctorEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;


/**
 * 出诊时间表
 *
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2018-12-06 10:17:18
 */
@Api(tags = "出诊时间表")
@RestController
@RequestMapping("/rest/serschedule")
public class SerScheduleController {

    @Autowired
    private SerScheduleService serScheduleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = serScheduleService.queryPage(params);
        return R.success().addResData("page", page);
    }

    @LoginIgnore
    @ApiOperation("诊断时间")
    @GetMapping("/diagnoseList/{doctorId}")
    public R diagnoseList(@PathVariable Long doctorId){
        List<SerScheduleEntity> list = serScheduleService.list(new QueryWrapper<SerScheduleEntity>().lambda()
                .eq(SerScheduleEntity::getDoctorId, doctorId)
                .gt(SerScheduleEntity::getDate, new Date())
                .orderByAsc(SerScheduleEntity::getDate)
                .last("limit 7"));
        List<Map<String,Object>> result = new ArrayList<>();
        list.forEach((entity) -> {
            Map<String, Object> each = new LinkedHashMap<>();
            each.put("id", entity.getId());
            each.put("fullDate", DateUtils.getYearDateWeek(entity.getDate()));
            each.put("date", DateUtils.getDateWeek(entity.getDate()));
            each.put("morningRemainNum",entity.getMorningRemainNum());
            each.put("afternoonRemainNum",entity.getAfternoonRemainNum());
            result.add(each);
        });
        return R.success().addResData("diagnoseList",result);
    }

    @ApiOperation("接诊时间 [医生]")
    @GetMapping("/diagnoseList")
    public R diagnoseListByDoctor(@ApiIgnore @LoginDoctor UsDoctorEntity doctorEntity){
        Date[] curentWeek = DateUtils.getCurentWeek();
        List<SerScheduleEntity> list = serScheduleService.list(new QueryWrapper<SerScheduleEntity>().lambda()
                .eq(SerScheduleEntity::getDoctorId, doctorEntity.getId())
                .ge(SerScheduleEntity::getDate, curentWeek[0])
                .le(SerScheduleEntity::getDate, curentWeek[1])
                .orderByAsc(SerScheduleEntity::getDate));
        List<Map<String,Object>> result = new ArrayList<>();
        list.forEach((entity) -> {
            String week = DateUtils.getWeek(entity.getDate());
            if(entity.getMorningBegin() != null){
                Map<String, Object> each = new LinkedHashMap<>();
                each.put("id", entity.getId());
                each.put("date", week);
                each.put("timepart", "上午");
                each.put("beginTime", DateUtils.getTime(entity.getMorningBegin()));
                each.put("endTime", DateUtils.getTime(entity.getMonringEnd()));
                each.put("totalNumber", entity.getMorningTotalNum());
                result.add(each);
            }
            if(entity.getAfternoonBegin() != null){
                Map<String, Object> each = new LinkedHashMap<>();
                each.put("id", entity.getId());
                each.put("date", week);
                each.put("timepart", "下午");
                each.put("beginTime", DateUtils.getTime(entity.getAfternoonBegin()));
                each.put("endTime", DateUtils.getTime(entity.getAfternoonEnd()));
                each.put("totalNumber", entity.getAfternoonTotalNum());
                result.add(each);
            }
        });
        return R.success().addResData("diagnoseList",result);
    }

    @ApiOperation("获取接诊周期 [医生]")
    @GetMapping("/diagnoseDates")
    public R diagnoseDates(){
        LocalDate[] nextWeekDays = DateUtils.getNextWeekDays(new Date());
        List<Map<String, Object>> result = new ArrayList<>();

        for (LocalDate eachDay : nextWeekDays) {
            Map<String, Object> each = new HashMap<>();
            each.put("date", DateUtils.getYearMonthDate(eachDay));
            each.put("monthDay",DateUtils.getMonthDate(eachDay));
            each.put("week",DateUtils.getWeek(eachDay));
            result.add(each);
        }
        return R.success().addResData("diagnoseDates",result);
    }

    @ApiOperation("增加接诊 [医生]")
    @PostMapping("/addDiagnose")
    public R addDiagnoseDates(@RequestBody ScheduleInput scheduleInput,@ApiIgnore @LoginDoctor UsDoctorEntity doctorEntity){
        ValidatorUtils.validateEntity(scheduleInput);
        String[] dates = scheduleInput.getDates();
        Set<String> dateSet = new HashSet<>(Arrays.asList(dates));
        List<SerScheduleEntity> serScheduleEntities = new ArrayList<>();
        for (String date : dateSet) {
            SerScheduleEntity serScheduleEntity = new SerScheduleEntity();
            String datePattern = "yyyy-MM-dd";
            String timePattern = "HH:mm";
            if(!StringUtils.isEmpty(scheduleInput.getMoringBegin())){
                serScheduleEntity.setMorningBegin(DateUtils.stringToTime(scheduleInput.getMoringBegin(),timePattern));
                serScheduleEntity.setMonringEnd(DateUtils.stringToTime(scheduleInput.getMoringEnd(),timePattern));
                serScheduleEntity.setMorningRemainNum(scheduleInput.getMorningNum());
                serScheduleEntity.setMorningTotalNum(scheduleInput.getMorningNum());
            }
            if(!StringUtils.isEmpty(scheduleInput.getAfternoonBegin())){
                serScheduleEntity.setAfternoonBegin(DateUtils.stringToTime(scheduleInput.getAfternoonBegin(),timePattern));
                serScheduleEntity.setAfternoonEnd(DateUtils.stringToTime(scheduleInput.getAfternoonEnd(),timePattern));
                serScheduleEntity.setAfternoonRemainNum(scheduleInput.getAfternoonNum());
                serScheduleEntity.setAfternoonTotalNum(scheduleInput.getAfternoonNum());
            }
            serScheduleEntity.setDate(DateUtils.stringToDate(date,datePattern));
            serScheduleEntity.setDoctorId(doctorEntity.getId());
            serScheduleEntities.add(serScheduleEntity);
        }
        serScheduleService.saveBatch(serScheduleEntities);
        return R.success();
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        SerScheduleEntity serSchedule = serScheduleService.getById(id);
        return R.success().addResData("serSchedule", serSchedule);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SerScheduleEntity serSchedule){
        serScheduleService.save(serSchedule);
        return R.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SerScheduleEntity serSchedule){
        serScheduleService.updateById(serSchedule);
        return R.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        serScheduleService.removeByIds(Arrays.asList(ids));
        return R.success();
    }

    @ApiModel("添加时间表输入")
    @Data
    private static class ScheduleInput {

        @ApiModelProperty("选中日期")
        @NotNull(message = "必须选中日期")
        private String[] dates;

        @ApiModelProperty("早上开始时间")
        private String moringBegin;

        @ApiModelProperty("早上结束时间")
        private String moringEnd;

        @ApiModelProperty("早上接诊人数")
        private Integer morningNum;

        @ApiModelProperty("下午开始时间")
        private String afternoonBegin;

        @ApiModelProperty("下午结束时间")
        private String afternoonEnd;

        @ApiModelProperty("下午接诊人数")
        private Integer afternoonNum;
    }
}
