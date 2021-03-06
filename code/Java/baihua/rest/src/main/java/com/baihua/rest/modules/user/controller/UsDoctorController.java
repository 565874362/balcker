package com.baihua.rest.modules.user.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baihua.core.common.enums.Constants;
import com.baihua.core.common.utils.PageQuery;
import com.baihua.core.common.utils.R;
import com.baihua.core.common.validator.ValidatorUtils;
import com.baihua.rest.modules.login.annotation.LoginDoctor;
import com.baihua.rest.modules.login.annotation.LoginIgnore;
import com.baihua.core.modules.service.entity.SerAdeptEntity;
import com.baihua.rest.modules.service.service.SerAdeptService;
import com.baihua.core.modules.user.entity.UsAccountEntity;
import com.baihua.core.modules.user.entity.UsDoctorEntity;
import com.baihua.rest.modules.user.service.UsAccountService;
import com.baihua.rest.modules.user.service.UsDoctorService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;


/**
 * 医生
 *
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2018-12-06 10:17:17
 */
@Api(tags = "医生")
@RestController
@RequestMapping("/rest/usdoctor")
public class UsDoctorController {

    @Autowired
    private UsAccountService accountService;

    @Autowired
    private UsDoctorService usDoctorService;

    @Autowired
    private SerAdeptService adeptService;

    /**
     * 列表
     */
    @ApiOperation("医生列表")
    @PostMapping("/list")
    @LoginIgnore
    public R list(@RequestBody QueryInput queryInput){
        ValidatorUtils.validateEntity(queryInput);
        LambdaQueryWrapper<UsDoctorEntity> queryWrapper = new QueryWrapper<UsDoctorEntity>().lambda();
        queryWrapper.like(!StringUtils.isEmpty(queryInput.getSearch()), UsDoctorEntity::getMajor, queryInput.getSearch())
         .or().like(!StringUtils.isEmpty(queryInput.getSearch()), UsDoctorEntity::getOffName, queryInput.getSearch())
         .or().like(!StringUtils.isEmpty(queryInput.getSearch()), UsDoctorEntity::getHosName, queryInput.getSearch());
        IPage<UsDoctorEntity> page = usDoctorService.page(queryInput.getPage(), queryWrapper);
        List<Long> doctorIds = page.getRecords().stream().map((e) -> e.getId()).collect(Collectors.toList());
        if(doctorIds != null && !doctorIds.isEmpty()){
            List<SerAdeptEntity> SerAdeptEntitys = adeptService.list(new QueryWrapper<SerAdeptEntity>().lambda()
                    .select(SerAdeptEntity::getName,SerAdeptEntity::getDocId)
                    .in(SerAdeptEntity::getDocId, doctorIds)
                    .orderByAsc(SerAdeptEntity::getOrdered));
            Map<Long, List<SerAdeptEntity>> adeptEntitiesByDoctorId = SerAdeptEntitys.stream().collect(Collectors.groupingBy(SerAdeptEntity::getDocId));
            page.getRecords().forEach(e -> e.setAdeptEntities(adeptEntitiesByDoctorId.get(e.getId())));
        }
        return R.success().addResData("page", page);
    }


    /**
     * 信息
     */
    @ApiOperation("医生信息")
    @GetMapping("/info/{id}")
    @LoginIgnore
    public R info(@PathVariable("id") Long id){
        UsDoctorEntity usDoctor = usDoctorService.getById(id);
        if(usDoctor != null){
            List<SerAdeptEntity> SerAdeptEntitys = adeptService.list(new QueryWrapper<SerAdeptEntity>().lambda()
                    .select(SerAdeptEntity::getName,SerAdeptEntity::getDescribe)
                    .eq(SerAdeptEntity::getDocId, usDoctor.getId())
                    .orderByAsc(SerAdeptEntity::getOrdered));
            usDoctor.setAdeptEntities(SerAdeptEntitys);

            UsAccountEntity account = accountService.getOne(new QueryWrapper<UsAccountEntity>().lambda()
                    .select(UsAccountEntity::getId)
                    .eq(UsAccountEntity::getSId, usDoctor.getId())
                    .eq(UsAccountEntity::getType, Constants.AccountType.doctor.getCode()));
            if(account != null){
                usDoctor.setAccountId(account.getId());
            }
        }
        return R.success().addResData("info", usDoctor);
    }

    @ApiOperation("医生个人信息 [医生]")
    @GetMapping("/info")
    public R info(@ApiIgnore @LoginDoctor UsDoctorEntity doctorEntity){
            List<SerAdeptEntity> SerAdeptEntitys = adeptService.list(new QueryWrapper<SerAdeptEntity>().lambda()
                    .select(SerAdeptEntity::getName,SerAdeptEntity::getDescribe)
                    .eq(SerAdeptEntity::getDocId, doctorEntity.getId())
                    .orderByAsc(SerAdeptEntity::getOrdered));
        doctorEntity.setAdeptEntities(SerAdeptEntitys);
        return R.success().addResData("info", doctorEntity);
    }


    /**
     *
     */
    @ApiOperation("医生信息编辑 [医生]")
    @PostMapping("/update")
    public R update(@RequestBody DoctorUpdater doctorUpdater, @LoginDoctor UsDoctorEntity doctorEntity){
        ValidatorUtils.validateEntity(doctorUpdater);
        BeanUtils.copyProperties(doctorUpdater,doctorEntity);
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

        doctorEntity.setId(doctorEntity.getId());
        usDoctorService.update(doctorEntity,serAdeptEntities);
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

    @ApiModel("查询参数")
    @Data
    private static class QueryInput extends PageQuery<UsDoctorEntity> {
        @ApiModelProperty("查询参数")
        private String search;
    }


    @ApiModel("医生信息编辑")
    @Data
    private static class DoctorUpdater {
        /**
         * 名称
         */
        @ApiModelProperty("姓名")
        @NotEmpty(message = "姓名不能为空")
        private String name;
        /**
         * 性别:1 男 0 女
         */
        @ApiModelProperty("性别 1 男 0 女")
        @NotNull(message = "性别不能为空")
        private Integer gender;
        /**
         * 科室编号
         */
        @ApiModelProperty("科室编号")
        @NotNull(message = "科室编号不能为空")
        private Long offId;
        /**
         * 科室名称
         */
        @ApiModelProperty("科室名称")
        @NotEmpty(message = "科室名称不能为空")
        private String offName;

        /**
         * 职位编号
         */
        @ApiModelProperty("职位编号")
        @NotNull(message = "职位编号不能为空")
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
        @NotNull(message = "医院编号不能为空")
        private Long hosId;
        /**
         * 医院名称
         */
        @ApiModelProperty("医院名称")
        @NotEmpty(message = "医院名称不能为空")
        private String hosName;
        /**
         * 个人照片
         */
        @ApiModelProperty("个人照片")
        @NotEmpty(message = "个人照片不能为空")
        private String photo;
        /**
         * 医师资格证
         */
        @ApiModelProperty("医师资格证")
        @NotEmpty(message = "医师资格证不能为空")
        private String physicianLicence;
        /**
         * 身份证 正反以,分割
         */
        @ApiModelProperty("身份证")
        @NotEmpty(message = "身份证不能为空")
        private String identityCard;
        /**
         * 挂号费
         */
        @ApiModelProperty("挂号费")
        @NotNull(message = "挂号费不能为空")
        private BigDecimal registrationFee;

        @ApiModelProperty("擅长")
        @NotNull(message = "擅长不能为空")
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
