/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.baihua.baihuamedical.modules.sys.dao;


import org.apache.ibatis.annotations.Mapper;

import com.baihua.baihuamedical.modules.sys.entity.SysLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * 系统日志
 * 
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2017-03-08 10:40:56
 */
@Mapper
public interface SysLogDao extends BaseMapper<SysLogEntity> {
	
}
