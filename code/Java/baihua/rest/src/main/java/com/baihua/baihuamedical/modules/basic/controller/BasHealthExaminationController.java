package com.baihua.baihuamedical.modules.basic.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baihua.baihuamedical.common.utils.R;
import com.baihua.baihuamedical.modules.basic.entity.BasHealthExaminationEntity;
import com.baihua.baihuamedical.modules.basic.service.BasHealthExaminationService;
import com.baihua.baihuamedical.modules.login.annotation.LoginIgnore;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 检查
 *
 * @author zhaodongdong
 * @email 
 * @date 2018-12-14 13:14:17
 */
@Api(tags = "健康检查")
@RestController
@RequestMapping("/rest/bashealthexamination")
public class BasHealthExaminationController {

    @Autowired
    private BasHealthExaminationService basHealthExaminationService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation("健康检查列表")
    @LoginIgnore
    public R list(){
        List<BasHealthExaminationEntity> list = basHealthExaminationService.list(new QueryWrapper<BasHealthExaminationEntity>().lambda()
                .orderByAsc(BasHealthExaminationEntity::getSort));
        return R.success().addResData("list", list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")

    public R info(@PathVariable("id") Long id){
			BasHealthExaminationEntity basHealthExamination = basHealthExaminationService.getById(id);

        return R.success().addResData("basHealthExamination", basHealthExamination);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")

    public R save(@RequestBody BasHealthExaminationEntity basHealthExamination){
			basHealthExaminationService.save(basHealthExamination);

        return R.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")

    public R update(@RequestBody BasHealthExaminationEntity basHealthExamination){
			basHealthExaminationService.updateById(basHealthExamination);

        return R.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")

    public R delete(@RequestBody Long[] ids){
			basHealthExaminationService.removeByIds(Arrays.asList(ids));

        return R.success();
    }

}
