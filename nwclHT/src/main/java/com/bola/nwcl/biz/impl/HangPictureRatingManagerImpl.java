package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.HangPictureRatingManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.HangPictureRatingMapper;
import com.bola.nwcl.dal.mybatis.model.HangPictureRating;
import com.bola.nwcl.dal.mybatis.model.HangPictureRatingExample;

@Service
public class HangPictureRatingManagerImpl  extends ManagerImpl<HangPictureRating, HangPictureRatingExample, Long> implements HangPictureRatingManager{
	
	@SuppressWarnings("unused")
	private HangPictureRatingMapper hangPictureRatingMapper;
	
	@Autowired
	public HangPictureRatingManagerImpl(HangPictureRatingMapper hangPictureRatingMapper) {
		this.mapper = hangPictureRatingMapper;
		this.hangPictureRatingMapper = hangPictureRatingMapper;
	}

}
