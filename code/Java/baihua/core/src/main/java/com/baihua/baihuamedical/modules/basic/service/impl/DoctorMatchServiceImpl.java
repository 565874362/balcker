package com.baihua.baihuamedical.modules.basic.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baihua.baihuamedical.common.enums.Constants;
import com.baihua.baihuamedical.modules.basic.dao.BasKeywordDao;
import com.baihua.baihuamedical.modules.basic.entity.BasKeywordEntity;
import com.baihua.baihuamedical.modules.basic.service.IDoctorMatchService;
import com.baihua.baihuamedical.modules.service.dao.SerAdeptDao;
import com.baihua.baihuamedical.modules.service.entity.SerAdeptEntity;
import com.baihua.baihuamedical.modules.user.dao.UsDoctorDao;
import com.baihua.baihuamedical.modules.user.entity.UsDoctorEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.hutool.dfa.SensitiveUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhaodongdong
 * @version V1.0
 * @Title DoctorMatchServiceImpl.java
 * @Package com.baihua.baihuamedical.modules.basic.service.impl
 * @date 2018年12月21日 00:43:52
 */
@Service
@Slf4j
public class DoctorMatchServiceImpl implements IDoctorMatchService {


	private final String endPoint = "$$";

	/**
	 * 关键词 - 医生
	 */
	private ConcurrentMap<String, CopyOnWriteArrayList<UsDoctorEntity>> keywordDoctorsMap = new ConcurrentHashMap<>();


	@Autowired
	private BasKeywordDao keywordDao;

	@Autowired
	private UsDoctorDao usDoctorDao;

	@Autowired
	private SerAdeptDao serAdeptDao;

	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	/**
	 * 系统启动时将关键词刷新到缓存，并构建关键词树
	 */
	@PostConstruct
	public void initKeyWordToMem() {
		log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>开始加载关键词到内存关键词树>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		long current = 1;
		long size = 500;
		Page<BasKeywordEntity> basKeywordEntityPage = new Page<>(current, size);

		List<BasKeywordEntity> keywordEntityList = keywordDao.selectPage(basKeywordEntityPage, new QueryWrapper<BasKeywordEntity>().lambda().eq(BasKeywordEntity::getStatus, Constants.KeywordStatus.valid.getCode())).getRecords();
		while (!keywordEntityList.isEmpty()) {
			for (BasKeywordEntity temp : keywordEntityList) {
				keywordDoctorsMap.put(temp.getWord(), new CopyOnWriteArrayList<>());
			}
			basKeywordEntityPage.setCurrent(current++);
			keywordEntityList = keywordDao.selectPage(basKeywordEntityPage, null).getRecords();
		}
		SensitiveUtil.init(keywordDoctorsMap.keySet(), true);

		updateAllDoctor();
	}

	/**
	 * 删除树上的关键词
	 *
	 * @param keywordEntity
	 */
	public void deleteKeyWord(BasKeywordEntity keywordEntity) {
		keywordDoctorsMap.remove(keywordEntity.getWord());
		SensitiveUtil.init(keywordDoctorsMap.keySet(), true);
	}

	/**
	 * 更新树上的关键词
	 *
	 * @param oldBasKeywordEntity
	 * @param newBasKeywordEntity
	 */
	public void updateKeyWord(BasKeywordEntity oldBasKeywordEntity, BasKeywordEntity newBasKeywordEntity) {
		keywordDoctorsMap.remove(oldBasKeywordEntity.getWord());
		keywordDoctorsMap.put(newBasKeywordEntity.getWord(), new CopyOnWriteArrayList<>());
		SensitiveUtil.init(keywordDoctorsMap.keySet(), true);
		updateAllDoctor();
	}

	@Override
	public void updateDoctorInfo(Long doctorId) {
		UsDoctorEntity usDoctorEntity = usDoctorDao.selectById(doctorId);
		List<SerAdeptEntity> serAdeptEntities = serAdeptDao.selectList(new QueryWrapper<SerAdeptEntity>().lambda().eq(SerAdeptEntity::getDocId, doctorId));
		usDoctorEntity.setAdeptEntities(serAdeptEntities);
		parseDoctorKeywords(usDoctorEntity);
		log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>解析编号为【{}】的医生信息关联到关键词树>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>",usDoctorEntity.getId());
	}

	@Override
	public synchronized void updateAllDoctor() {
		List<UsDoctorEntity> allDoctorEntities = usDoctorDao.selectList(null);
		List<SerAdeptEntity> allAdeptEntities = serAdeptDao.selectList(null);
		Map<Long, List<SerAdeptEntity>> doctorAdepts = new HashMap<>();
		if(!allAdeptEntities.isEmpty()){
			doctorAdepts.putAll(allAdeptEntities.stream().collect(Collectors.groupingBy(SerAdeptEntity::getDocId)));
		}

		allDoctorEntities.forEach(eachDoctor -> {
			eachDoctor.setAdeptEntities(doctorAdepts.get(eachDoctor.getId()));
			parseDoctorKeywords(eachDoctor);
		});
		log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>解析所有医生信息关联到关键词树>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}

	private void parseDoctorKeywords(UsDoctorEntity doctor){
		keywordDoctorsMap.values().forEach(e -> e.remove(doctor));

		List<SerAdeptEntity> eachDoctorAdeptEntities = doctor.getAdeptEntities();

		StringBuilder allMatchText = new StringBuilder();
		allMatchText.append(doctor.getHosName() + endPoint);
		allMatchText.append(doctor.getOffName() + endPoint);
		if(eachDoctorAdeptEntities != null && !eachDoctorAdeptEntities.isEmpty()){
			eachDoctorAdeptEntities.forEach(eachAdept -> allMatchText.append(eachAdept.getName() + endPoint + eachAdept.getDescribe() + endPoint));
		}

		List<String> doctorMatchedAllKeywords = SensitiveUtil.getFindedAllSensitive(allMatchText.toString());

		doctorMatchedAllKeywords.forEach(eachKeyword -> {
			CopyOnWriteArrayList<UsDoctorEntity> matchDoctorEntities = keywordDoctorsMap.get(eachKeyword);
			if(matchDoctorEntities == null){
				matchDoctorEntities = new CopyOnWriteArrayList();
				keywordDoctorsMap.put(eachKeyword, matchDoctorEntities);
			}
			matchDoctorEntities.add(doctor);
		});
	}

	/**
	 * 匹配内容里的关键词
	 *
	 * @param content
	 * @return
	 */
	public List<MatchDoctor> findKeyword(String content) {
		SortedSet<MatchDoctor> result = new TreeSet<>((a,b) -> Integer.compare(b.getMatchNum(),a.getMatchNum()));

		Map<UsDoctorEntity,Integer> doctorMatches = new HashMap<>();
		if (StringUtils.isEmpty(content)) {
			return new ArrayList<>(result);
		}
		List<String> matchKeywords = SensitiveUtil.getFindedAllSensitive(content);
		for (String eachKeyword : matchKeywords) {
			CopyOnWriteArrayList<UsDoctorEntity> usDoctorEntities = keywordDoctorsMap.get(eachKeyword);

			usDoctorEntities.forEach(e -> {
				Integer matchNum = doctorMatches.get(e);
				if(matchNum == null){
					matchNum = 0;
				}
				doctorMatches.put(e,matchNum + 1);
			});
		}

		doctorMatches.forEach((doctor,matchNum) -> {
			MatchDoctor matchDoctor = new MatchDoctor();
			matchDoctor.setDoctorEntity(doctor);
			matchDoctor.setMatchNum(matchNum);
			result.add(matchDoctor);
		});
		return new ArrayList<>(result);
	}

}

