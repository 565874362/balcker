package com.baihua.baihuamedical.modules.service.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baihua.baihuamedical.common.utils.DateUtils;
import com.baihua.baihuamedical.common.utils.PageUtils;
import com.baihua.baihuamedical.common.utils.R;
import com.baihua.baihuamedical.modules.login.annotation.LoginIgnore;
import com.baihua.baihuamedical.modules.service.entity.SerScheduleEntity;
import com.baihua.baihuamedical.modules.service.service.SerScheduleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 出诊时间表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
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
}
