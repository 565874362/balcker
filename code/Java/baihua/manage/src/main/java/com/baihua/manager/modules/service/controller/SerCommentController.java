package com.baihua.manager.modules.service.controller;

import com.baihua.core.common.utils.PageQuery;
import com.baihua.core.common.utils.PageUtils;
import com.baihua.core.common.utils.R;
import com.baihua.core.modules.service.entity.SerCommentEntity;
import com.baihua.manager.modules.service.service.SerCommentService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Map;

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
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = serCommentService.queryPage(params);
        return R.success().addResData("page", page);
    }
    /**
     * 评论列表
     */
    @ApiOperation("评论列表")
    @PostMapping("/commentlist")
    public R commentlist(@RequestBody CommentInput commentInput){
        IPage<Map<String,Object>> list = serCommentService.commentlist(commentInput.getPage(),commentInput.getOffid(),commentInput.getHosname(),commentInput.getStartDate(),commentInput.getEndDate());
        return R.success().addResData("data",list);
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

    @Data
    @ApiModel("评论列表参数")
    private static class CommentInput extends PageQuery<SerCommentEntity>{
        @ApiModelProperty("科室id")
        private long offid;
        @ApiModelProperty("单位名称")
        private String hosname;
        @ApiModelProperty("开始时间")
        private String startDate;
        @ApiModelProperty("开始时间")
        private String endDate;
    }
}
