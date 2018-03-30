package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bola.nwcl.biz.LectureShareItemManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.LectureShareItemMapper;
import com.bola.nwcl.dal.mybatis.model.LectureShareItem;
import com.bola.nwcl.dal.mybatis.model.LectureShareItemExample;


@Service
public class LectureShareItemManagerImpl  extends ManagerImpl< LectureShareItem,  LectureShareItemExample, Long> implements  LectureShareItemManager{
	
	@SuppressWarnings("unused")
	private  LectureShareItemMapper  lectureShareItemMapper;
	
	@Autowired
	public LectureShareItemManagerImpl(LectureShareItemMapper lectureShareItemMapper) {
		this.mapper = lectureShareItemMapper;
		this.lectureShareItemMapper = lectureShareItemMapper;
	}

	

}
