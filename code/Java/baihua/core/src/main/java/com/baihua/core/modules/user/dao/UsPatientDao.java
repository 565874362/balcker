package com.baihua.core.modules.user.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

import com.baihua.core.modules.user.entity.UsPatientEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 患者
 * 
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2018-12-06 10:17:17
 */
@Mapper
public interface UsPatientDao extends BaseMapper<UsPatientEntity> {

    IPage<Map<String,Object>> queryList(Page<UsPatientEntity> page,@Param("startDate") String startDate, @Param("endDate") String endDate);

    long queryListTotal(@Param("startDate") String startDate, @Param("endDate") String endDate);

}
