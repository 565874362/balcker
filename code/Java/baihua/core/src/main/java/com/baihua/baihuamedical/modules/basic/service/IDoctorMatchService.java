package com.baihua.baihuamedical.modules.basic.service;

import java.util.List;

import com.baihua.baihuamedical.modules.basic.entity.BasKeywordEntity;
import com.baihua.baihuamedical.modules.user.entity.UsDoctorEntity;

import lombok.Data;

/**
 * 关键词服务
 *
 * @author zhaodongdong
 * @version V1.0
 * @Title IKeywordService.java
 * @Package com.baihua.baihuamedical.modules.basic.service
 * @date 2018年12月21日 00:43:03
 */
public interface IDoctorMatchService {

	/**
	 * 删除关键词
	 *
	 * @param keywordEntity
	 */
	void deleteKeyWord(BasKeywordEntity keywordEntity);

	/**
	 * 更新关键词
	 *
	 * @param oldBasKeywordEntity
	 * @param newBasKeywordEntity
	 */
	void updateKeyWord(BasKeywordEntity oldBasKeywordEntity,BasKeywordEntity newBasKeywordEntity);

	/**
	 * 更新医生信息
	 */
	void updateDoctorInfo(Long doctorId);

	/**
	 * 更新所有医生信息
	 */
	void updateAllDoctor();

	/**
	 * 匹配内容里的关键词
	 * @param content
	 * @return
	 */
	List<MatchDoctor> findKeyword(String content);

	@Data
	class MatchDoctor implements Comparable<Integer>{

		private UsDoctorEntity doctorEntity;
		private Integer matchNum;

		@Override
		public int compareTo(Integer o) {
			return matchNum.compareTo(o);
		}
	}

}