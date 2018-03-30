package com.bola.nwcl.biz.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bola.nwcl.biz.MarketManager;
import com.bola.nwcl.common.util.file.FileUploadDeleteUtil;
import com.bola.nwcl.dal.mybatis.mapper.ApplyBoothImgMapper;
import com.bola.nwcl.dal.mybatis.mapper.ApplyBoothMapper;
import com.bola.nwcl.dal.mybatis.mapper.IdlethingImgMapper;
import com.bola.nwcl.dal.mybatis.mapper.MarketConferenceMapper;
import com.bola.nwcl.dal.mybatis.model.ApplyBooth;
import com.bola.nwcl.dal.mybatis.model.ApplyBoothImg;
import com.bola.nwcl.dal.mybatis.model.MarketConference;
import com.bola.nwcl.dal.mybatis.model.MarketConferenceExample;

@Service
public class MarketManagerImpl  implements MarketManager{
	
	@Autowired
	private MarketConferenceMapper marketConferenceMapper;
	
	@Autowired
	private ApplyBoothMapper applyBoothMapper;
	
	@Autowired
	private ApplyBoothImgMapper applyBoothImgMapper;

	@Override
	public Page<MarketConference> getAllMarket(int page, int rows) {
		PageRequest pager = new PageRequest(page-1, rows);
		int count = marketConferenceMapper.countByExample(null);
		MarketConferenceExample example = new MarketConferenceExample();
		example.setLimit(pager.getPageSize());
		example.setOffset(pager.getOffset());
		List<MarketConference> list = marketConferenceMapper.selectByExample(example);
		Page<MarketConference> pageData = new PageImpl<>(list, pager, count);
		return pageData;
	}

	@Override
	public void addApplyBooth(String saveDir, Long buserId, String title, String userDetail, String detail, String name, String phone, MultipartFile[] imgs) throws Exception {
		ApplyBooth a = new ApplyBooth();
		a.setBuserId(buserId);
		a.setDetail(detail);
		a.setName(name);
		a.setPhone(phone);
		a.setTitle(title);
		a.setUserDetail(userDetail);
		applyBoothMapper.insertAndGetId(a);
		String todayStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
		for(MultipartFile img : imgs){
			if(!img.isEmpty()){
				String fileName=UUID.randomUUID().toString().substring(0,12);
				fileName = todayStr + fileName;
				String[] paths = FileUploadDeleteUtil.upload2(img, null, saveDir, fileName);
				ApplyBoothImg rimg = new ApplyBoothImg();
				rimg.setImgPath(paths[0]);
				rimg.setImgPathThumbnail(paths[1]);
				rimg.setApplyBoothId(a.getId());
				applyBoothImgMapper.insert(rimg);
			}
		}
	}
	
}
