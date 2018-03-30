package com.bola.nwcl.biz;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bola.nwcl.api.model.market.OwnersMarketModel;
import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.OwnersMarket;
import com.bola.nwcl.dal.mybatis.model.OwnersMarketExample;
import com.bola.nwcl.web.model.LikeShareWebModel;

public interface OwnersMarketManager extends Manager<OwnersMarket, OwnersMarketExample, Long>{
	
	void addOwnersMarket(String saveDir, BizUser buser, HttpServletRequest request, String title, String content, MultipartFile[] imgs) throws Exception;
	Page<OwnersMarketModel> getAllOwnersMarket(BizUser buser, String isGetCurrentUser, int page, int rows);
	OwnersMarketModel updateGetOwnersMarketDetail(BizUser buser, long id, int ratingCount);
	void deleteOwnersMarket(BizUser buser, long id);
	
	void updateZanOwnersMarket(long id, long buserId);
	void updateCancelZanOwnersMarket(long id, long buserId);
	
	void updateReportOwnersMarket(long id, long buserId);
	void edit(HttpServletRequest request,LikeShareWebModel likeShareWebModel,@RequestParam("file") MultipartFile[] file) throws Exception;
	void delete(Long id ) throws Exception;
}
