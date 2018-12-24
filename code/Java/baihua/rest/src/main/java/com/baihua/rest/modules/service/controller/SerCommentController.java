package com.baihua.rest.modules.service.controller;

import java.util.Arrays;
import java.util.Map;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baihua.core.common.utils.PageQuery;
import com.baihua.core.common.utils.PageUtils;
import com.baihua.core.common.utils.R;
import com.baihua.core.common.validator.ValidatorUtils;
import com.baihua.rest.modules.login.annotation.LoginAccount;
import com.baihua.rest.modules.login.annotation.LoginIgnore;
import com.baihua.rest.modules.login.annotation.LoginPatient;
import com.baihua.core.modules.service.entity.SerCommentEntity;
import com.baihua.rest.modules.service.service.SerCommentService;
import com.baihua.core.modules.user.entity.UsAccountEntity;
import com.baihua.core.modules.user.entity.UsPatientEntity;
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
 * 评论
 *
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2018-12-06 10:17:17
 */
@Api(tags = "评论")
@RestController
@RequestMapping("/rest/sercomment")
public class SerCommentController {
    @Autowired
    private SerCommentService serCommentService;

    /**
     *
     *
     * @return
     */
    @ApiOperation("根据医生加载评论")
    @PostMapping("/doctorList")
    @LoginIgnore
    public R doctorList(@RequestBody DoctorListInput doctorListInput){
        ValidatorUtils.validateEntity(doctorListInput);
        LambdaQueryWrapper<SerCommentEntity> queryWrapper = new QueryWrapper<SerCommentEntity>().lambda();
        queryWrapper.eq(SerCommentEntity::getDoctorId, doctorListInput.getDoctorId());
        IPage page = serCommentService.page(doctorListInput.getPage(), queryWrapper);
        return R.success().addResData("page",page);
    }

    @ApiOperation("患者评论")
    @PostMapping("/patientCommit")
    public R patientCommit(@RequestBody PatientCommentInput patientCommentInput, @ApiIgnore @LoginAccount UsAccountEntity accountEntity, @ApiIgnore @LoginPatient UsPatientEntity patientEntity){
        ValidatorUtils.validateEntity(patientCommentInput);
        SerCommentEntity serCommentEntity = new SerCommentEntity();
        BeanUtils.copyProperties(patientCommentInput,serCommentEntity);
        serCommentEntity.setPatientAccount(accountEntity.getAccount());
        serCommentEntity.setPatientId(patientEntity.getId());
        serCommentEntity.setPatientPhoto(patientEntity.getPhoto());
        serCommentService.save(serCommentEntity);
        return R.success();
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = serCommentService.queryPage(params);
        return R.success().addResData("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        SerCommentEntity serComment = serCommentService.getById(id);
        return R.success().addResData("serComment", serComment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SerCommentEntity serComment){
        serCommentService.save(serComment);
        return R.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SerCommentEntity serComment){
        serCommentService.updateById(serComment);
        return R.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        serCommentService.removeByIds(Arrays.asList(ids));
        return R.success();
    }

    @Data
    @ApiModel("根据医生加载评论列表参数")
    private static class DoctorListInput extends PageQuery<SerCommentEntity> {
        @ApiModelProperty("医生编号")
        private Long doctorId;
    }

    @Data
    @ApiModel("评论信息")
    private static class PatientCommentInput {
        @ApiModelProperty("挂号编号")
        @NotNull(message = "挂号编号不能为空")
        private long regId;
        @ApiModelProperty("医生编号")
        @NotNull(message = "医生编号不能为空")
        private long doctorId;
        @ApiModelProperty("评论信息")
        @NotEmpty(message = "评论信息不能为空")
        private String content;

    }
}
