package com.baihua.manager.modules.service.controller;

import com.baihua.core.common.utils.PageUtils;
import com.baihua.core.common.utils.R;
import com.baihua.core.modules.service.entity.SerSessionEntity;
import com.baihua.manager.modules.service.service.SerSessionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 会话历史
 *
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2018-12-06 10:17:18
 */
@RestController
@RequestMapping("/rest/sersession")
public class SerSessionController {
    @Autowired
    private SerSessionService serSessionService;

    /**
     * 列表
     */
    @RequestMapping("/list")

    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = serSessionService.queryPage(params);

        return R.success().addResData("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")

    public R info(@PathVariable("id") Long id){
			SerSessionEntity serSession = serSessionService.getById(id);

        return R.success().addResData("serSession", serSession);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")

    public R save(@RequestBody SerSessionEntity serSession){
			serSessionService.save(serSession);

        return R.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")

    public R update(@RequestBody SerSessionEntity serSession){
			serSessionService.updateById(serSession);

        return R.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")

    public R delete(@RequestBody Long[] ids){
			serSessionService.removeByIds(Arrays.asList(ids));

        return R.success();
    }

}
