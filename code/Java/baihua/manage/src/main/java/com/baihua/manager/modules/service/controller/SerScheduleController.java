package com.baihua.manager.modules.service.controller;

import com.baihua.core.common.utils.PageUtils;
import com.baihua.core.common.utils.R;
import com.baihua.core.modules.service.entity.SerScheduleEntity;
import com.baihua.manager.modules.service.service.SerScheduleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.*;


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
