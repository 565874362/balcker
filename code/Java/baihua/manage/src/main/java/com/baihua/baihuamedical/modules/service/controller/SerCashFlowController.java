package com.baihua.baihuamedical.modules.service.controller;

import com.baihua.baihuamedical.common.utils.PageUtils;
import com.baihua.baihuamedical.common.utils.R;
import com.baihua.baihuamedical.modules.service.entity.SerCashFlowEntity;
import com.baihua.baihuamedical.modules.service.service.SerCashFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 现金流水
 *
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2018-12-06 10:17:17
 */
@RestController
@RequestMapping("/rest/sercashflow")
public class SerCashFlowController {
    @Autowired
    private SerCashFlowService serCashFlowService;

    /**
     * 列表
     */
    @RequestMapping("/list")

    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = serCashFlowService.queryPage(params);

        return R.success().addResData("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")

    public R info(@PathVariable("id") Long id){
			SerCashFlowEntity serCashFlow = serCashFlowService.getById(id);

        return R.success().addResData("serCashFlow", serCashFlow);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")

    public R save(@RequestBody SerCashFlowEntity serCashFlow){
			serCashFlowService.save(serCashFlow);

        return R.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")

    public R update(@RequestBody SerCashFlowEntity serCashFlow){
			serCashFlowService.updateById(serCashFlow);

        return R.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")

    public R delete(@RequestBody Long[] ids){
			serCashFlowService.removeByIds(Arrays.asList(ids));

        return R.success();
    }

}
