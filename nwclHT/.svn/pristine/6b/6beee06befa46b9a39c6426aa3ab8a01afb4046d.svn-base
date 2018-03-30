package com.bola.nwcl.biz;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.bola.nwcl.dal.mybatis.model.MarketConference;


public interface MarketManager{
	Page<MarketConference> getAllMarket(int page, int rows);
	void addApplyBooth(String saveDir,Long buserId, String title, String userDetail, String detail, String name, String phone, MultipartFile[] imgs) throws Exception;
}
