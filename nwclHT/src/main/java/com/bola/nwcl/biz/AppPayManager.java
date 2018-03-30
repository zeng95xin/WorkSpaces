package com.bola.nwcl.biz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bola.nwcl.api.controller.pay.WebhooksModel;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.pingplusplus.model.Charge;


public interface AppPayManager {
	Charge updateGetCharge(HttpServletRequest request, BizUser buser, String idStr, String orderType, String channel, String result_url) throws Exception;
	
	void updatePaySuccess(HttpServletRequest request, BizUser buser, String orderNo) throws Exception;
	
	void updateReceiveWebhooks(HttpServletRequest request, HttpServletResponse response, WebhooksModel webhook) throws Exception;
}
