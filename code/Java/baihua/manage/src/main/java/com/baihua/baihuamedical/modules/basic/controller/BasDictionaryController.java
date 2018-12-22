package com.baihua.baihuamedical.modules.basic.controller;

import com.baihua.baihuamedical.common.utils.R;
import com.baihua.baihuamedical.modules.basic.entity.BasDictionaryEntity;
import com.baihua.baihuamedical.modules.basic.service.BasDictionaryService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


/**
 * 字典
 *
 * @author zhaodongdong
 * @email 
 * @date 2018-12-14 13:14:17
 */
@Api(tags = "字典")
@RestController
@RequestMapping("/rest/basdictionary")
public class BasDictionaryController {

    @Autowired
    private BasDictionaryService basDictionaryService;

    @ApiOperation("获取字典 1 睡眠 2 食欲 3 职位")
    @GetMapping("/list/{groupId}")
    public R list(@PathVariable Integer groupId){
        LambdaQueryWrapper<BasDictionaryEntity> dictionWrapper = new QueryWrapper<BasDictionaryEntity>().lambda();
        dictionWrapper.eq(BasDictionaryEntity::getGroupId,groupId);
        List<BasDictionaryEntity> list = basDictionaryService.list(dictionWrapper);
        return R.success().addResData("dictionaries", list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")

    public R info(@PathVariable("id") Long id){
			BasDictionaryEntity basDictionary = basDictionaryService.getById(id);

        return R.success().addResData("basDictionary", basDictionary);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")

    public R save(@RequestBody BasDictionaryEntity basDictionary){
			basDictionaryService.save(basDictionary);

        return R.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")

    public R update(@RequestBody BasDictionaryEntity basDictionary){
			basDictionaryService.updateById(basDictionary);

        return R.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")

    public R delete(@RequestBody Long[] ids){
			basDictionaryService.removeByIds(Arrays.asList(ids));

        return R.success();
    }

}
