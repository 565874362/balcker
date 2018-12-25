package com.baihua.core.modules.service.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;

import com.baihua.core.modules.service.entity.SerCommentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 评论
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-06 10:17:17
 */
@Mapper
public interface SerCommentDao extends BaseMapper<SerCommentEntity> {

    IPage<Map<String,Object>> commentlist(@Param("page") IPage<SerCommentEntity> page, @Param("boid") Long boid, @Param("hosname") String hosname, @Param("startDate") String startDate, @Param("endDate") String endDate);

    Long commentlistTotal(@Param("boid") Long boid, @Param("hosname") String hosname, @Param("startDate") String startDate, @Param("endDate") String endDate);
}
