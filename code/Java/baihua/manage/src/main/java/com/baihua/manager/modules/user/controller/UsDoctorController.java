package com.baihua.manager.modules.user.controller;

import com.baihua.core.common.enums.Constants;
import com.baihua.core.common.utils.PageQuery;
import com.baihua.core.common.utils.R;

import com.baihua.core.modules.basic.service.IDoctorMatchService;
import com.baihua.core.modules.service.entity.SerAdeptEntity;
import com.baihua.manager.modules.service.service.SerAdeptService;
import com.baihua.core.modules.user.entity.UsAccountEntity;
import com.baihua.core.modules.user.entity.UsDoctorEntity;
import com.baihua.manager.modules.user.service.UsAccountService;
import com.baihua.manager.modules.user.service.UsDoctorService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.*;


/**
 * 医生
 *
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2018-12-06 10:17:17
 */
@Api(tags = "后台医生")
@RestController
@RequestMapping("/sys/usdoctor")
public class UsDoctorController {

    @Autowired
    private UsDoctorService usDoctorService;

    @Autowired
    private SerAdeptService adeptService;

    @Autowired
    private UsAccountService usAccountService;

    @Autowired
    private IDoctorMatchService doctorMatchService;

    /**
     * 后台医生列表
     */
    @ApiOperation("后台医生列表")
    @PostMapping("/querylist")
    public R querylist(@RequestBody FindInput findInput){
        LambdaQueryWrapper<UsDoctorEntity> queryWrapper = new QueryWrapper<UsDoctorEntity>().lambda();
        queryWrapper.eq(UsDoctorEntity::getStatus, findInput.getState())
         .or().like(!StringUtils.isEmpty(findInput.getHosname()), UsDoctorEntity::getHosName, findInput.getHosname())
                .or().between(!StringUtils.isEmpty(findInput.getStartDate()),UsDoctorEntity::getGmtCreate,findInput.getStartDate(),findInput.getEndDate());
        IPage<UsDoctorEntity> page = usDoctorService.page(findInput.getPage(), queryWrapper);
        return R.success().addResData("page", page);
    }


    /**
     * 信息
     */
    @ApiOperation("医生信息")
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        UsDoctorEntity usDoctor = usDoctorService.getById(id);
        if(usDoctor != null){
            List<SerAdeptEntity> SerAdeptEntitys = adeptService.list(new QueryWrapper<SerAdeptEntity>().lambda()
                    .select(SerAdeptEntity::getName,SerAdeptEntity::getDescribe)
                    .eq(SerAdeptEntity::getDocId, usDoctor.getId())
                    .orderByAsc(SerAdeptEntity::getOrdered));
            usDoctor.setAdeptEntities(SerAdeptEntitys);
        }
        return R.success().addResData("info", usDoctor);
    }
    /**
     * 后台医生审核
     */
    @ApiOperation("后台医生审核")
    @GetMapping("/check/{id}")
    public R check(@PathVariable("id") Long id){
        UsDoctorEntity usDoctorEntity = new UsDoctorEntity();
        usDoctorEntity.setId(id);
        usDoctorEntity.setStatus(Constants.DoctorStatus.checked.getCode());
        usDoctorService.updateById(usDoctorEntity);
        UsAccountEntity usAccountEntity = new UsAccountEntity();
        usAccountEntity.setStatus(Constants.AccountStatus.normal.getCode());
        usAccountService.update(usAccountEntity,new QueryWrapper<UsAccountEntity>().lambda()
            .eq(UsAccountEntity::getSId,id)
            .eq(UsAccountEntity::getType,Constants.AccountType.doctor));
        doctorMatchService.updateDoctorInfo(id);
        return R.success();
    }


   /**
     *
     */
    @ApiOperation("医生信息编辑 [医生]")
    @PostMapping("/update")
    public R update(@RequestBody DoctorUpdater doctorUpdater){
        UsDoctorEntity usDoctorEntity = usDoctorService.getById(doctorUpdater.getId());
        BeanUtils.copyProperties(doctorUpdater,usDoctorEntity);
        List<Adept> adepts = doctorUpdater.getAdepts();
        List<SerAdeptEntity> serAdeptEntities = new ArrayList<>();
        Iterator<Adept> iterator = adepts.iterator();
        while (iterator.hasNext()){
            Adept object = iterator.next();
            if(object == null || org.apache.commons.lang.StringUtils.isEmpty(object.getDescribe()) || org.apache.commons.lang.StringUtils.isEmpty(object.getName()) ){
                iterator.remove();
                continue;
            }
            SerAdeptEntity serAdeptEntity = new SerAdeptEntity();
            BeanUtils.copyProperties(object,serAdeptEntity);
            serAdeptEntities.add(serAdeptEntity);
        }

        usDoctorEntity.setId(usDoctorEntity.getId());
        usDoctorService.update(usDoctorEntity,serAdeptEntities);
        return R.success();
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody UsDoctorEntity usDoctor){
        usDoctorService.save(usDoctor);
        return R.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        usDoctorService.removeByIds(Arrays.asList(ids));
        return R.success();
    }


    @ApiModel("查询")
    @Data
    private static class FindInput extends PageQuery<UsDoctorEntity>{
        @ApiModelProperty("医生状态")
        private Integer state;
        @ApiModelProperty("单位名称")
        private String hosname;
        @ApiModelProperty("查询开始时间")
        private String startDate;
        @ApiModelProperty("查询结束时间")
        private String endDate;
    }


    @ApiModel("医生信息编辑")
    @Data
    private static class DoctorUpdater {
        /**
         * 医生ID
         */
        @ApiModelProperty("医生ID")
        private long id;
        /**
         * 名称
         */
        @ApiModelProperty("姓名")
        private String name;
        /**
         * 性别:1 男 0 女
         */
        @ApiModelProperty("性别 1 男 0 女")
        private Integer gender;
        /**
         * 科室编号
         */
        @ApiModelProperty("科室编号")
        private Long offId;
        /**
         * 科室名称
         */
        @ApiModelProperty("科室名称")
        private String offName;

        /**
         * 职位编号
         */
        @ApiModelProperty("职位编号")
        private Long positionId;
        /**
         * 职位名称
         */
        @ApiModelProperty("职位名称")
        @NotEmpty(message = "职位名称不能为空")
        private String positionName;
        /**
         * 医院编号
         */
        @ApiModelProperty("医院编号")
        private Long hosId;
        /**
         * 医院名称
         */
        @ApiModelProperty("医院名称")
        private String hosName;
        /**
         * 个人照片
         */
        @ApiModelProperty("个人照片")
        private String photo;
        /**
         * 医师资格证
         */
        @ApiModelProperty("医师资格证")
        private String physicianLicence;
        /**
         * 身份证 正反以,分割
         */
        @ApiModelProperty("身份证")
        private String identityCard;
        /**
         * 挂号费
         */
        @ApiModelProperty("挂号费")
        private BigDecimal registrationFee;

        @ApiModelProperty("擅长")
        private List<Adept> adepts = new ArrayList<>();
    }

    @ApiModel("擅长")
    @Data
    private static class Adept {
        @ApiModelProperty("名称")
        private String name;
        @ApiModelProperty("描述")
        private String describe;
    }
}
