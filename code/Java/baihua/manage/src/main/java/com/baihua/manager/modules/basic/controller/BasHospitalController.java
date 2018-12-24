package com.baihua.manager.modules.basic.controller;

import com.baihua.core.common.utils.R;
import com.baihua.core.modules.basic.entity.BasHospitalEntity;
import com.baihua.manager.modules.basic.service.BasHospitalService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


/**
 * 医院
 *
 * @author zhaodongdong
 * @email 
 * @date 2018-12-14 13:14:17
 */
@Api(tags = "医院")
@RestController
@RequestMapping("/rest/bashospital")
public class BasHospitalController {
    @Autowired
    private BasHospitalService basHospitalService;

    /**
     * 列表
     */
    @ApiOperation("医院列表 [医生]")
    @GetMapping("/list")
    public R list(){
        List<BasHospitalEntity> list = basHospitalService.list(new QueryWrapper<BasHospitalEntity>().lambda()
                .select(BasHospitalEntity::getId, BasHospitalEntity::getName));
        return R.success().addResData("list", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")

    public R info(@PathVariable("id") Long id){
			BasHospitalEntity basHospital = basHospitalService.getById(id);

        return R.success().addResData("basHospital", basHospital);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")

    public R save(@RequestBody BasHospitalEntity basHospital){
			basHospitalService.save(basHospital);

        return R.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")

    public R update(@RequestBody BasHospitalEntity basHospital){
			basHospitalService.updateById(basHospital);

        return R.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")

    public R delete(@RequestBody Long[] ids){
			basHospitalService.removeByIds(Arrays.asList(ids));

        return R.success();
    }

}
