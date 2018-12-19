package com.baihua.baihuamedical.modules.service.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baihua.baihuamedical.common.utils.PageUtils;
import com.baihua.baihuamedical.common.utils.R;
import com.baihua.baihuamedical.modules.service.entity.SerAdeptEntity;
import com.baihua.baihuamedical.modules.service.service.SerAdeptService;



/**
 * 擅长领域
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-06 10:17:17
 */
@RestController
@RequestMapping("/rest/seradept")
public class SerAdeptController {
    @Autowired
    private SerAdeptService serAdeptService;

    /**
     * 列表
     */
    @RequestMapping("/list")

    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = serAdeptService.queryPage(params);

        return R.success().addResData("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")

    public R info(@PathVariable("id") Long id){
			SerAdeptEntity serAdept = serAdeptService.getById(id);

        return R.success().addResData("serAdept", serAdept);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")

    public R save(@RequestBody SerAdeptEntity serAdept){
			serAdeptService.save(serAdept);

        return R.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")

    public R update(@RequestBody SerAdeptEntity serAdept){
			serAdeptService.updateById(serAdept);

        return R.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")

    public R delete(@RequestBody Long[] ids){
			serAdeptService.removeByIds(Arrays.asList(ids));

        return R.success();
    }

}
