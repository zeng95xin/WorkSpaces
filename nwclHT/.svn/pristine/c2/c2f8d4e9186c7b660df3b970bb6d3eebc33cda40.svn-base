package com.bola.nwcl.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.HangPictureImgManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.HangPictureImgMapper;
import com.bola.nwcl.dal.mybatis.model.HangPictureImg;
import com.bola.nwcl.dal.mybatis.model.HangPictureImgExample;

@Service
public class HangPictureImgManagerImpl  extends ManagerImpl<HangPictureImg, HangPictureImgExample, Long> implements HangPictureImgManager{
	
	@SuppressWarnings("unused")
	private HangPictureImgMapper hangPictureImgMapper;
	
	@Autowired
	public HangPictureImgManagerImpl(HangPictureImgMapper hangPictureImgMapper) {
		this.mapper = hangPictureImgMapper;
		this.hangPictureImgMapper = hangPictureImgMapper;
	}

}
