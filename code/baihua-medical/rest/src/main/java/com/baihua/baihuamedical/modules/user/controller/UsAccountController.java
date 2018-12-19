package com.baihua.baihuamedical.modules.user.controller;

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
import com.baihua.baihuamedical.modules.user.entity.UsAccountEntity;
import com.baihua.baihuamedical.modules.user.service.UsAccountService;



/**
 * 账号
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-06 10:17:17
 */
@RestController
@RequestMapping("/rest/usaccount")
public class UsAccountController {

    @Autowired
    private UsAccountService usAccountService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = usAccountService.queryPage(params);
        return R.success().addResData("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        UsAccountEntity usAccount = usAccountService.getById(id);
        return R.success().addResData("usAccount", usAccount);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody UsAccountEntity usAccount){
        usAccountService.save(usAccount);
        return R.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody UsAccountEntity usAccount){
        usAccountService.updateById(usAccount);
        return R.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        usAccountService.removeByIds(Arrays.asList(ids));
        return R.success();
    }

}
