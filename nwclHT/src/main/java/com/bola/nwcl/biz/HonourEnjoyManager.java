package com.bola.nwcl.biz;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;

import com.bola.nwcl.api.model.BizUserCouponModel;
import com.bola.nwcl.api.model.honour.HonourEnjoyDetailModel;
import com.bola.nwcl.api.model.honour.HonourEnjoyRatingModel;
import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoy;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyExample;
import com.bola.nwcl.web.model.HonourEnjoyInfoModel;

public interface HonourEnjoyManager extends Manager<HonourEnjoy, HonourEnjoyExample, Long>{
	HonourEnjoyDetailModel getHonourEnjoyDetail(Long id, long buserId, Integer ratingCount);
	Page<HonourEnjoyRatingModel> getHonourEnjoyRating(Long id, long buserId, int page, int rows);
	HonourEnjoyRatingModel addHonourEnjoyRating(Long buserId, Long id, Long replyId, String content);
	BizUserCouponModel addCoupon(long couponId, long buserId);
	
	//web端
	DataGrid dataGrid(HonourEnjoyInfoModel h, PageHelper ph, HttpServletRequest request);
	HonourEnjoyInfoModel getInfo(Long id);
	
	void insertAndGetId(HonourEnjoy honourEnjoy);
	void deleteHonorEnjoy(Long id) throws Exception ;
}
