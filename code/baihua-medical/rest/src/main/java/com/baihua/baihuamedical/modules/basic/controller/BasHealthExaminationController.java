package com.baihua.baihuamedical.modules.basic.controller;

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
import com.baihua.baihuamedical.modules.basic.entity.BasHealthExaminationEntity;
import com.baihua.baihuamedical.modules.basic.service.BasHealthExaminationService;


/**
 * 检查
 *
 * @author zhaodongdong
 * @email 
 * @date 2018-12-14 13:14:17
 */
@RestController
@RequestMapping("/rest/bashealthexamination")
public class BasHealthExaminationController {
    @Autowired
    private BasHealthExaminationService basHealthExaminationService;

    /**
     * 列表
     */
    @RequestMapping("/list")

    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = basHealthExaminationService.queryPage(params);

        return R.success().addResData("page", page);
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
