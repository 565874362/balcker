package com.baihua.baihuamedical.modules.basic.controller;

import com.baihua.baihuamedical.common.utils.R;
import com.baihua.baihuamedical.modules.basic.entity.BasAdEntity;
import com.baihua.baihuamedical.modules.basic.service.BasAdService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 广告
 *
 * @author zhaodongdong
 * @email 
 * @date 2018-12-14 13:14:18
 */
@Api(tags = "广告")
@RestController
@RequestMapping("/rest/basad")
public class BasAdController {

    @Autowired
    private BasAdService basAdService;

    @ApiOperation("加载广告图片列表")
    @PostMapping("/list")
    public R listAd(@RequestBody ListAdInput adInput){
        LambdaQueryWrapper<BasAdEntity> addQuery = new QueryWrapper<BasAdEntity>().lambda();
        addQuery.eq(BasAdEntity::getPosId, adInput.positionId);
        addQuery.eq(BasAdEntity::getTerminal, adInput.terminal);
        List<BasAdEntity> list = basAdService.list(addQuery);
        return R.success().addResData("adImages",list.stream().map((e) -> e.getImage()).collect(Collectors.toList()));
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        BasAdEntity basAd = basAdService.getById(id);
        return R.success().addResData("basAd", basAd);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BasAdEntity basAd){
        basAdService.save(basAd);
        return R.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody BasAdEntity basAd){
        basAdService.updateById(basAd);
        return R.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        basAdService.removeByIds(Arrays.asList(ids));
        return R.success();
    }

    @ApiModel("广告列表")
    @Data
    private static class ListAdInput {
        @ApiModelProperty("位置编号 1 首页广告")
        private int positionId;
        @ApiModelProperty("端 1 医生 2 患者")
        private int terminal;
    }
}
