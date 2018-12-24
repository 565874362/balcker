package com.baihua.manager.modules.service.service.impl;

import com.baihua.core.common.utils.PageUtils;
import com.baihua.core.common.utils.Query;
import com.baihua.core.modules.service.dao.SerCommentDao;
import com.baihua.core.modules.service.entity.SerCommentEntity;
import com.baihua.manager.modules.service.service.SerCommentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("serCommentService")
public class SerCommentServiceImpl extends ServiceImpl<SerCommentDao, SerCommentEntity> implements SerCommentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SerCommentEntity> page = this.page(
                new Query<SerCommentEntity>(params).getPage(),
                new QueryWrapper<SerCommentEntity>()
        );

        return new PageUtils(page);
    }

}
