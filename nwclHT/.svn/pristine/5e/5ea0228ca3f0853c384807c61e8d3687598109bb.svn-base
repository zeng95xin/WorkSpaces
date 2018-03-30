package com.bola.nwcl.biz.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.bola.nwcl.api.controller.pay.WebhooksModel;
import com.bola.nwcl.biz.AppPayManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.global.Configure;
import com.bola.nwcl.common.util.DateUtils;
import com.bola.nwcl.common.util.IDUtils;
import com.bola.nwcl.common.util.ServletRequestUtil;
import com.bola.nwcl.dal.http.impl.climb.ClimbClientImpl;
import com.bola.nwcl.dal.mybatis.mapper.MaintenanceMapper;
import com.bola.nwcl.dal.mybatis.mapper.MaintenanceOfferMapper;
import com.bola.nwcl.dal.mybatis.mapper.PayOrderMapper;
import com.bola.nwcl.dal.mybatis.mapper.PropertyPayMentMapper;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.Maintenance;
import com.bola.nwcl.dal.mybatis.model.MaintenanceOffer;
import com.bola.nwcl.dal.mybatis.model.MaintenanceOfferExample;
import com.bola.nwcl.dal.mybatis.model.PayOrder;
import com.bola.nwcl.dal.mybatis.model.PayOrderExample;
import com.bola.nwcl.dal.mybatis.model.PropertyPayMent;
import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.APIConnectionException;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.InvalidRequestException;
import com.pingplusplus.model.Charge;

@Service
public class AppPayManagerImpl implements AppPayManager{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PayOrderMapper payOrderMapper;
	
	@Autowired 
	private MaintenanceOfferMapper maintenanceOfferMapper;
	
	@Autowired 
	private MaintenanceMapper maintenanceMapper;
	
	@Autowired 
	private PropertyPayMentMapper propertyPayMentMapper;
	
	@Autowired
	private ClimbClientImpl climbClientImpl;
	
	/*private static final String pingppAppId  = "app_iz1OG84inLq5q1SC";
	private static final String pingppApiKey = "sk_test_LaP8qL4GGqD4mvD0uHmTKavL";*/

	@Override
	public Charge updateGetCharge(HttpServletRequest request, BizUser buser,
			String idStr, String orderType, String channel, String result_url) throws Exception {
		Charge c = null;
		if("BXD".equals(orderType)){
			long id = Long.valueOf(idStr);
			Maintenance m = maintenanceMapper.selectByPrimaryKey(id);
			if(m == null){
				throw new BusinessValidateException("报修信息不存在");
			}
			if(m.getStatus().intValue()!=4){
				throw new BusinessValidateException("报修单还不能支付");
			}
			if(m.getBuserId().longValue() != buser.getId().longValue()){
				throw new BusinessValidateException("只能支付自己的报修单");
			}
			c = getMaintenanceChage(request, buser, m, channel, result_url);
		}else if("WY".equals(orderType)){
			String[] array_idStr = idStr.split(",");
			List<Long> list_id = new ArrayList<Long>();
			List<PropertyPayMent> list_p = new ArrayList<PropertyPayMent>();
			for(String idStr_ : array_idStr){
				Long id = Long.valueOf(idStr_);
				list_id.add(id);
			}
			Collections.sort(list_id);
			String climb_id = "";
			for(int i = 0; i < list_id.size(); i++){
				PropertyPayMent p = propertyPayMentMapper.selectByPrimaryKey(list_id.get(i));
				if(p == null){
					throw new BusinessValidateException("物业缴费单不存在");
				}
				if(p.getReceivableStatus().intValue() == 1){
					throw new BusinessValidateException("物业缴费单,"+p.getReceivableDispname()+"已经缴过费了");
				}
				climb_id += p.getReceivableId();
				climb_id += "|";
				list_p.add(p);
			}
			if(climb_id.endsWith("|")){
				climb_id = climb_id.substring(0, climb_id.length()-1);
			}
			try {
				c = getPropertyPayMentChage(climb_id, request, buser, list_p, channel, result_url);
			} catch (AuthenticationException | InvalidRequestException
					| APIConnectionException | APIException e) {
				e.printStackTrace();
				logger.warn("生成缴费订单出错,错误信息:{},{}",e.getMessage(),e);
				throw new BusinessDealException("生成缴费订单出错,请联系管理员");
			}
		}
		return c;
	}
	
	private Charge getPropertyPayMentChage(String climb_id, HttpServletRequest request, BizUser buser, List<PropertyPayMent> list_p, String channel, String result_url) throws Exception{
		Charge ch = null;
		PayOrder p = null;
		PayOrderExample example_order = new PayOrderExample();
		example_order.setOrderByClause(" raw_add_time desc ");
		Date now = new Date();
		long price = 0l;
		String dataId = "";
		for(int i = 0; i < list_p.size(); i++){
			PropertyPayMent temp = list_p.get(i);
			String idStr = temp.getId()+"";
			dataId = temp.getId()+",";
			example_order.or().andOrderTypeEqualTo("WY")
					.andDataIdLike("%" + idStr + "%")
					.andPayStatusEqualTo("NOT_PAY")
					.andTimeExpireGreaterThan(now);
			price += temp.getReceivableAmount();
		}
		double PPayAmount = price/100;
		climbClientImpl.CheckUnitPayment(climb_id, String.valueOf(PPayAmount));
		
		
		List<PayOrder> list_order = payOrderMapper.selectByExample(example_order);
		if(list_order != null && list_order.size() == 1){
			p = list_order.get(0);
			if(p.getUserId().longValue() != buser.getId().longValue()){
				if(p.getTimeExpire().getTime() > System.currentTimeMillis()){
					long diff = DateUtils.getMinutesOfTowDiffDate(now, p.getTimeExpire());
					logger.info("用户{},{},准备支付一个其他人正在支付的订单",buser.getId(),buser.getName());
					throw new BusinessValidateException("当前有其他人正在支付这个订单,请"+diff+"分钟后再试");
				}else{
					p.setPayStatus("TIME_EXPIRE");
					payOrderMapper.updateByPrimaryKeySelective(p);
				}
			}else{
				if(p.getTimeExpire().getTime() > (System.currentTimeMillis()+1000*60*2)){
					ch = Charge.retrieve(p.getChargeId());
					return ch;
				}else{
					p.setPayStatus("TIME_EXPIRE");
					payOrderMapper.updateByPrimaryKeySelective(p);
				}
			}
		}else if(list_order != null && list_order.size() > 1){
			for(PayOrder po : list_order){
				p = po;
				if(p.getUserId().longValue() != buser.getId().longValue()){
					if(p.getTimeExpire().getTime() > System.currentTimeMillis()){
						long diff = DateUtils.getMinutesOfTowDiffDate(now, p.getTimeExpire());
						logger.info("用户{},{},准备支付一个其他人正在支付的订单",buser.getId(),buser.getName());
						throw new BusinessValidateException("当前有其他人正在支付这个订单,请"+diff+"分钟后再试");
					}else{
						p.setPayStatus("TIME_EXPIRE");
						payOrderMapper.updateByPrimaryKeySelective(p);
					}
				}else{
					p.setPayStatus("TIME_EXPIRE");
					payOrderMapper.updateByPrimaryKeySelective(p);
				}
			}
		}
		
		String serial = dataId;
		String orderNo = "WY"+IDUtils.getInstanse().getUID();
		String clinetIp=ServletRequestUtil.getIpAddr(request);//客户端IP
		Pingpp.apiKey = Configure.getPingppApiKey();
		Map<String, Object> chargeParams = new HashMap<String, Object>();
	    chargeParams.put("order_no",  orderNo);
	    chargeParams.put("amount", price);
	    Map<String, String> app = new HashMap<String, String>();
	    app.put("id", Configure.getPingppAppId());
	    chargeParams.put("app",app);
	    chargeParams.put("channel",channel);
	    chargeParams.put("currency","cny");
	    chargeParams.put("client_ip",clinetIp);
    	
    	chargeParams.put("subject","物业缴费");
 		chargeParams.put("body","缴费单号："+serial);
 		chargeParams.put("time_expire",(System.currentTimeMillis()/1000+ 1*60*10));
 		
		if(StringUtils.isNotBlank(result_url)){
			Map<String,String> extra=new HashMap<String, String>();
			extra.put("result_url", result_url);
			chargeParams.put("extra",extra);
		}
		ch = Charge.create(chargeParams);
		Date timeExpire = new Date(ch.getTimeExpire()*1000);
		PayOrder p2 = new PayOrder();
		p2.setChargeId(ch.getId());
		p2.setNumber(1l);
		p2.setOrderNo(orderNo);
		p2.setOrderType("WY");
		p2.setPayChannel(channel);
		p2.setPayDirections("支付说明:物业缴费");
		p2.setPayMoney(price);
		p2.setPayStatus("NOT_PAY");
		p2.setUserId(buser.getId());
		p2.setDataId(dataId);
		p2.setTimeExpire(timeExpire);
		payOrderMapper.insert(p2);
		
		return ch;
	}
	
	private Charge getMaintenanceChage(HttpServletRequest request, BizUser buser, Maintenance m, String channel, String result_url){
		PayOrderExample example_order = new PayOrderExample();
		example_order.or().andOrderTypeEqualTo("BXD").andDataIdEqualTo(m.getId()+"").andPayStatusEqualTo("NOT_PAY");
		example_order.setOrderByClause(" raw_add_time desc ");
		List<PayOrder> list_order = payOrderMapper.selectByExample(example_order);
		PayOrder p = null;
		if(list_order != null && list_order.size() > 0){
			p = list_order.get(0);
		}
		Charge ch = null;
		if(p == null){
			MaintenanceOfferExample example_offer = new MaintenanceOfferExample();
			example_offer.or().andMaintenanceIdEqualTo(m.getId());
			List<MaintenanceOffer> list = maintenanceOfferMapper.selectByExample(example_offer);
			long price = 0l;
			for(MaintenanceOffer offer : list){
				price += offer.getProjectPrice();
			}
			String serial = m.getSerial();
			String orderNo = "BXD"+IDUtils.getInstanse().getUID();
			String clinetIp=ServletRequestUtil.getIpAddr(request);//客服端IP
			Pingpp.apiKey = Configure.getPingppApiKey();
			Map<String, Object> chargeParams = new HashMap<String, Object>();
		    chargeParams.put("order_no",  orderNo);
		    chargeParams.put("amount", price);
		    Map<String, String> app = new HashMap<String, String>();
		    app.put("id", Configure.getPingppAppId());
		    chargeParams.put("app",app);
		    chargeParams.put("channel",channel);
		    chargeParams.put("currency","cny");
		    chargeParams.put("client_ip",clinetIp);
	    	
	    	chargeParams.put("subject","维修单缴费");
	 		chargeParams.put("body","单号："+serial);
	 		chargeParams.put("time_expire",(System.currentTimeMillis()/1000+ 1*60*60*2));
	 		
			if(StringUtils.isNotBlank(result_url)){
				Map<String,String> extra=new HashMap<String, String>();
				extra.put("result_url", result_url);
				chargeParams.put("extra",extra);
			}
			try {
				ch = Charge.create(chargeParams);
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessDealException("生成缴费订单出错,请联系管理员");
			}
			Date timeExpire = new Date(ch.getTimeExpire()*1000);
			p = new PayOrder();
			p.setChargeId(ch.getId());
			p.setNumber(1l);
			p.setOrderNo(orderNo);
			p.setOrderType("BXD");
			p.setPayChannel(channel);
			p.setPayDirections("支付说明:报修单缴费");
			p.setPayMoney(price);
			p.setPayStatus("NOT_PAY");
			p.setUserId(buser.getId());
			p.setDataId(m.getId()+"");
			p.setTimeExpire(timeExpire);
			payOrderMapper.insert(p);
		}else{
			try {
				ch = Charge.retrieve(p.getChargeId());
				if(ch.getTimeExpire()*1000 < System.currentTimeMillis()+1000*60*10){
					MaintenanceOfferExample example_offer = new MaintenanceOfferExample();
					example_offer.or().andMaintenanceIdEqualTo(m.getId());
					List<MaintenanceOffer> list = maintenanceOfferMapper.selectByExample(example_offer);
					long price = 0l;
					for(MaintenanceOffer offer : list){
						price += offer.getProjectPrice();
					}
					String serial = m.getSerial();
					String orderNo = "BXD"+IDUtils.getInstanse().getUID();
					String clinetIp=ServletRequestUtil.getIpAddr(request);//客服端IP
					Pingpp.apiKey = Configure.getPingppApiKey();
					Map<String, Object> chargeParams = new HashMap<String, Object>();
				    chargeParams.put("order_no",  orderNo);
				    chargeParams.put("amount", price);
				    Map<String, String> app = new HashMap<String, String>();
				    app.put("id", Configure.getPingppAppId());
				    chargeParams.put("app",app);
				    chargeParams.put("channel",channel);
				    chargeParams.put("currency","cny");
				    chargeParams.put("client_ip",clinetIp);
			    	
			    	chargeParams.put("subject","维修单缴费");
			 		chargeParams.put("body","单号："+serial);
			 		chargeParams.put("time_expire",(System.currentTimeMillis()/1000+ 1*60*60*2));
			 		
					if(StringUtils.isNotBlank(result_url)){
						Map<String,String> extra=new HashMap<String, String>();
						extra.put("result_url", result_url);
						chargeParams.put("extra",extra);
					}
					ch = Charge.create(chargeParams);
					PayOrder p2 = new PayOrder();
					Date timeExpire = new Date(ch.getTimeExpire()*1000);
					p2.setChargeId(ch.getId());
					p2.setNumber(1l);
					p2.setOrderNo(orderNo);
					p2.setOrderType("BXD");
					p2.setPayChannel(channel);
					p2.setPayDirections("支付说明:报修单缴费");
					p2.setPayMoney(price);
					p2.setPayStatus("NOT_PAY");
					p2.setUserId(buser.getId());
					p2.setDataId(m.getId()+"");
					p2.setTimeExpire(timeExpire);
					payOrderMapper.insert(p2);
					p.setPayStatus("TIME_EXPIRE");
					payOrderMapper.updateByPrimaryKeySelective(p);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessDealException("生成缴费订单出错,请联系管理员");
			}
		}
		return ch;
	}


	@Override
	public void updateReceiveWebhooks(HttpServletRequest request, HttpServletResponse response,
			WebhooksModel webhook) throws Exception {
		String orderNo=webhook.getData().getObject().getOrder_no();//订单id
	    String chid=webhook.getData().getObject().getId();//chid
		if ("charge.succeeded".equals(webhook.getType())) {// 如果为支付成功通知
			// 判断是否已经支付
			PayOrder p = payOrderMapper.selectByPrimaryKey(orderNo);
			if(p == null){
				logger.warn("平台不存在的订单id ，orderId：{}", orderNo);
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("data", "不存在的订单");
				dataMap.put("code", "200");
				String callbackJSON = JSON.toJSONString(dataMap).toString();
				response.getWriter().write(callbackJSON);
				return;
			}
			if(!("NOT_PAY".equals(p.getPayStatus()))){
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("data", "已经支付完成");
				dataMap.put("code", "200");
				String callbackJSON = JSON.toJSONString(dataMap).toString();
				response.getWriter().write(callbackJSON);
				return;
			}
			Pingpp.apiKey = Configure.getPingppApiKey();
			Charge ch = Charge.retrieve(chid);
			System.out.println("webhooks 查询ping++结果："+JSON.toJSONString(ch));
			//如果支付成功了 
			if(null==ch || !ch.getPaid()){
				throw new BusinessValidateException("支付未成功");
			}
			p.setPayStatus("ALREADY_PAY");
			p.setPayTime(new Date());
			payOrderMapper.updateByPrimaryKeySelective(p);
			if("BXD".equals(p.getOrderType())){
				Maintenance maintenance = maintenanceMapper.selectByPrimaryKey(Long.valueOf(p.getDataId()));
				if (null == maintenance) {
					Map<String, Object> dataMap = new HashMap<String, Object>();
					dataMap.put("data", "已经支付完成");
					dataMap.put("code", "200");
					String callbackJSON = JSON.toJSONString(dataMap).toString();
					response.getWriter().write(callbackJSON);
					logger.error("支付完成，但是报修单已经不存在了,chare_id:{}",ch.getId());
					return;
				}
				if(maintenance.getStatus().intValue() == 4){
					maintenance.setStatus(5);
					maintenanceMapper.updateByPrimaryKeySelective(maintenance);
					MaintenanceOfferExample example_offer = new MaintenanceOfferExample();
					example_offer.or().andMaintenanceIdEqualTo(maintenance.getId());
					MaintenanceOffer offer = new MaintenanceOffer();
					offer.setStatus(1);
					maintenanceOfferMapper.updateByExampleSelective(offer, example_offer);
				}else{
					logger.error("维修订单状态异常,订单id:{},当前状态:{},异常时间{}",maintenance.getId(),maintenance.getStatus(),new Date());
				}
			}else if("WY".equals(p.getOrderType())){
				String dataId = p.getDataId();
				String[] array_id = dataId.split(",");
				String climb_id = "";
				for(String idStr : array_id){
					Long id = Long.valueOf(idStr);
					PropertyPayMent ppm = propertyPayMentMapper.selectByPrimaryKey(id);
					if(ppm != null){
						climb_id += ppm.getReceivableId();
						climb_id += "|";
						ppm.setReceivableStatus(1);
						propertyPayMentMapper.updateByPrimaryKeySelective(ppm);
					}
				}
				if(climb_id.endsWith("|")){
					climb_id = climb_id.substring(0, climb_id.length()-1);
				}
				double PPayAmount = p.getPayMoney()/100;
				climbClientImpl.SendUnitPayment(climb_id, String.valueOf(PPayAmount), p.getPayChannel(), p.getOrderNo());
				/*PropertyPayMentExample example_p = new PropertyPayMentExample();
				example_p.or().andIdIn(list_id);
				List<PropertyPayMent> list_ppm = propertyPayMentMapper.selectByExample(example)
				PropertyPayMent ppm = new PropertyPayMent();
				ppm.setReceivableStatus(1);
				propertyPayMentMapper.updateByExampleSelective(ppm, example_p);*/
			}
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("data", "已经支付完成");
			dataMap.put("code", "200");
			String callbackJSON = JSON.toJSONString(dataMap).toString();
			response.getWriter().write(callbackJSON);
	    }
	}


	@Override
	public void updatePaySuccess(HttpServletRequest request, BizUser buser,
			String orderNo) throws Exception {
		PayOrder p = payOrderMapper.selectByPrimaryKey(orderNo);
		if(p == null){
			logger.warn("平台不存在的订单id ，orderNo：{}", orderNo);
			throw new BusinessValidateException("订单号不存在");
		}
		if(!("NOT_PAY".equals(p.getPayStatus()))){
			logger.warn("订单已经支付完成 ，orderNo：{}", orderNo);
			throw new BusinessValidateException("订单已经支付完成");
		}
		Pingpp.apiKey = Configure.getPingppApiKey();
		Charge ch;
		try {
			ch = Charge.retrieve(p.getChargeId());
		} catch (AuthenticationException | InvalidRequestException
				| APIConnectionException | APIException e) {
			e.printStackTrace();
			logger.warn("获取订单信息 失败，orderNo：{},错误信息", e.getMessage());
			throw new BusinessValidateException("暂时不能获取订单信息");
		}
		logger.info("webhooks 查询ping++结果："+JSON.toJSONString(ch));
		if(null==ch || !ch.getPaid()){
			throw new BusinessValidateException("支付未成功");
		}
		//如果支付成功了 
		p.setPayStatus("ALREADY_PAY");
		p.setPayTime(new Date());
		payOrderMapper.updateByPrimaryKeySelective(p);
		if("BXD".equals(p.getOrderType())){
			Maintenance maintenance = maintenanceMapper.selectByPrimaryKey(Long.valueOf(p.getDataId()));
			if (null == maintenance) {
				return;
			}
			if(maintenance.getStatus().intValue() == 4){
				maintenance.setStatus(5);
				maintenanceMapper.updateByPrimaryKeySelective(maintenance);
				MaintenanceOfferExample example_offer = new MaintenanceOfferExample();
				example_offer.or().andMaintenanceIdEqualTo(maintenance.getId());
				MaintenanceOffer offer = new MaintenanceOffer();
				offer.setStatus(1);
				maintenanceOfferMapper.updateByExampleSelective(offer, example_offer);
			}else{
				logger.error("维修订单状态异常,订单id:{},当前状态:{},异常时间{}",maintenance.getId(),maintenance.getStatus(),new Date());
			}
		}else if("WY".equals(p.getOrderType())){
			String dataId = p.getDataId();
			String[] array_id = dataId.split(",");
			String climb_id = "";
			for(String idStr : array_id){
				Long id = Long.valueOf(idStr);
				PropertyPayMent ppm = propertyPayMentMapper.selectByPrimaryKey(id);
				if(ppm != null){
					climb_id += ppm.getReceivableId();
					climb_id += "|";
					ppm.setReceivableStatus(1);
					propertyPayMentMapper.updateByPrimaryKeySelective(ppm);
				}
			}
			if(climb_id.endsWith("|")){
				climb_id = climb_id.substring(0, climb_id.length()-1);
			}
			double PPayAmount = p.getPayMoney()/100;
			climbClientImpl.SendUnitPayment(climb_id, String.valueOf(PPayAmount), p.getPayChannel(), p.getOrderNo());
			/*String dataId = p.getDataId();
			String[] array_id = dataId.split(",");
			List<Long> list_id = new ArrayList<Long>(array_id.length);
			for(String idStr : array_id){
				Long id = Long.valueOf(idStr);
				list_id.add(id);
			}
			PropertyPayMentExample example_p = new PropertyPayMentExample();
			example_p.or().andIdIn(list_id);
			PropertyPayMent ppm = new PropertyPayMent();
			ppm.setReceivableStatus(1);
			propertyPayMentMapper.updateByExampleSelective(ppm, example_p);*/
		}
	}
	
}