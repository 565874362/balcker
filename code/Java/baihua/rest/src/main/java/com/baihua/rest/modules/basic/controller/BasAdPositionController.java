package com.baihua.rest.modules.basic.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baihua.core.common.utils.PageUtils;
import com.baihua.core.common.utils.R;
import com.baihua.core.modules.basic.entity.BasAdPositionEntity;
import com.baihua.rest.modules.basic.service.BasAdPositionService;



/**
 * 广告位
 *
 * @author zhaodongdong
 * @email 
 * @date 2018-12-14 13:14:18
 */
@RestController
@RequestMapping("/rest/basadposition")
public class BasAdPositionController {
    @Autowired
    private BasAdPositionService basAdPositionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = basAdPositionService.queryPage(params);
        return R.success().addResData("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        BasAdPositionEntity basAdPosition = basAdPositionService.getById(id);
        return R.success().addResData("basAdPosition", basAdPosition);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BasAdPositionEntity basAdPosition){
        basAdPositionService.save(basAdPosition);
        return R.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody BasAdPositionEntity basAdPosition){
        basAdPositionService.updateById(basAdPosition);
        return R.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        basAdPositionService.removeByIds(Arrays.asList(ids));
        return R.success();
    }
}
