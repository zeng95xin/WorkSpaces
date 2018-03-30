package com.bola.nwcl.web.controller.property;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bola.nwcl.biz.BizUserManager;
import com.bola.nwcl.biz.CommunityManager;
import com.bola.nwcl.biz.PayOrderManager;
import com.bola.nwcl.biz.PropertyPayMentManager;
import com.bola.nwcl.biz.RoomManager;
import com.bola.nwcl.common.util.DateUtils;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.BizUserExample;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.PayOrder;
import com.bola.nwcl.dal.mybatis.model.PayOrderExample;
import com.bola.nwcl.dal.mybatis.model.PayOrderExample.Criteria;
import com.bola.nwcl.dal.mybatis.model.PropertyPayMent;
import com.bola.nwcl.dal.mybatis.model.Room;
import com.bola.nwcl.dal.mybatis.model.RoomExample;
import com.bola.nwcl.web.model.DataGridPay;
import com.bola.nwcl.web.model.PropertyPayWebModel;

/**
 * 物业缴费
 * 
 */
@Controller
@RequestMapping("web/PropertyPayWebController")
public class PropertyPayWebController {
	@Autowired BizUserManager bizUserManager;
	@Autowired PayOrderManager payOrderManager;
	@Autowired PropertyPayMentManager propertyPayMentManager;
	@Autowired RoomManager roomManager;
	@Autowired CommunityManager communityManager;
	
	/**
	 * 跳转到物业缴费页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		List<Community> community=communityManager.selectByExample(null);
		request.setAttribute("community", community);
		return "/property/propertyPay";
	}
	
	//得到当月时间
	private Date getMonth(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//当月
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		String s = format.format(c.getTime());
		Date time = DateUtils.strToDate(s, "yyyy-MM-dd");
		return time;
	}
	
	//得到当天时间
	private Date getDay(){
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String ss = format.format(date);
		Date time2 = DateUtils.strToDate(ss, "yyyy-MM-dd");
		return time2;
	}
		
	private Long getMoney(Date time,Date time2){
		PayOrderExample example = new PayOrderExample();
		example.or().andPayTimeBetween(time,new Date(time2.getTime() + 1000 * 60 * 60 * 24)).andPayStatusEqualTo("ALREADY_PAY");
		example.or().andPayTimeBetween(time,new Date(time2.getTime() + 1000 * 60 * 60 * 24)).andPayStatusEqualTo("OTHER_PAY");
		List<PayOrder> payOrder = payOrderManager.selectByExample(example);
		long money = 0;
		for (PayOrder payOrder2 : payOrder) {
			money+=payOrder2.getPayMoney();
			
		}
		return money;
	}
	
	private Long getMoney(Long communityId,Date time,Date time2){
		PayOrderExample example = new PayOrderExample();
		example.or().andPayTimeBetween(time,new Date(time2.getTime() + 1000 * 60 * 60 * 24)).andPayStatusEqualTo("ALREADY_PAY").andCommunityIdEqualTo(communityId);
		example.or().andPayTimeBetween(time,new Date(time2.getTime() + 1000 * 60 * 60 * 24)).andPayStatusEqualTo("OTHER_PAY").andCommunityIdEqualTo(communityId);
		List<PayOrder> payOrder = payOrderManager.selectByExample(example);
		long money = 0;
		for (PayOrder payOrder2 : payOrder) {
			money+=payOrder2.getPayMoney();
			
		}
		return money;
	}
	
	/**
	 * 获取数据表格
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGridPay dataGrid(PageHelper ph,HttpServletRequest request,PropertyPayWebModel pp){	    
		DataGridPay dg = new DataGridPay();
		PayOrderExample example = new PayOrderExample();
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage() - 1) * ph.getRows());
		example.setOrderByClause("raw_add_time DESC");
		Criteria c = example.or();
		c.andOrderTypeEqualTo("WY");
		// 得到用户对象
		Admin user = (Admin) request.getSession().getAttribute("loginUser");
		// 判断是普通管理员还是admin(普通管理员给出当前小区id)
		if (user.getRoleId() != 1) {
			@SuppressWarnings("unchecked")
			List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
			c.andCommunityIdIn(communityIds);
		}
		Date timeMonth = getMonth();
		Date timeDay = getDay();
		// 通过小区查询
		if (pp.getCommunity() != null && pp.getCommunity() != -1) {
			c.andCommunityIdEqualTo(pp.getCommunity());
			Long money = getMoney(pp.getCommunity(), timeMonth, timeDay);
			Long money2 = getMoney(pp.getCommunity(), timeDay, timeDay);
			dg.setMoney(money);
			dg.setMoney2(money2);
		}else{
			Long money = getMoney(timeMonth, timeDay);
			Long money2 = getMoney(timeDay, timeDay);
			dg.setMoney(money);
			dg.setMoney2(money2);
		}
		if(pp.getTime()!=null && pp.getTime2()!=null){
			if(pp.getTime().getTime() > pp.getTime2().getTime()){
				c.andPayTimeBetween(pp.getTime2(),new Date(pp.getTime().getTime() + 1000 * 60 * 60 * 24));
			}else {
				c.andPayTimeBetween(pp.getTime(),new Date(pp.getTime2().getTime() + 1000 * 60 * 60 * 24));
			}
		}
		if(StringUtils.isNotBlank(pp.getTime3())){
			int aa=0;
			if("1".equals(pp.getTime3())){
				aa=-1;
			}
			if("2".equals(pp.getTime3())){
				aa=-2;
			}
			if("3".equals(pp.getTime3())){
				aa=-3;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, aa);
			String s = sdf.format(calendar.getTime());
			Date addTime = DateUtils.strToDate(s, "yyyy-MM-dd");
			
			String s2 = sdf.format(date);
			Date time2 = DateUtils.strToDate(s2, "yyyy-MM-dd");
			c.andPayTimeBetween(addTime,new Date(time2.getTime() + 1000 * 60 * 60 * 24));
		}
		if(StringUtils.isNotBlank(pp.getKey()) && pp.getKey().equals("1")){
			BizUserExample bizUserExample = new BizUserExample();
			bizUserExample.or().andPhoneLike("%"+pp.getValue()+"%");
			List<BizUser> bizUser = bizUserManager.selectByExample(bizUserExample);
			List<Long> list = new ArrayList<Long>();
			if (bizUser != null) {
				for (BizUser bz : bizUser) {
					list.add(bz.getId());
				}
			}
			if(list.size() > 0){
				c.andUserIdIn(list);
			}else{
				c.andUserIdEqualTo(-555L);
			}
		}
		
		if(StringUtils.isNotBlank(pp.getKey()) && pp.getKey().equals("2")){
			RoomExample roomExample = new RoomExample();
			roomExample.or().andUnitNoLike("%"+pp.getValue()+"%");
			List<Room> room = roomManager.selectByExample(roomExample);
			List<Long> list = new ArrayList<Long>();
			if(room != null && room.size() > 0){
				for (Room room2 : room) {
					BizUserExample bizUserExample = new BizUserExample();
					bizUserExample.or().andCurrentRoomIdEqualTo(room2.getId());
					List<BizUser> bizUser = bizUserManager.selectByExample(bizUserExample);
					if (bizUser != null) {
						for (BizUser bz : bizUser) {
							list.add(bz.getId());
						}
					}
				}
			}
			if(list.size() > 0){
				c.andUserIdIn(list);
			}else{
				c.andUserIdEqualTo(-555L);
			}
		}
		if(StringUtils.isNotBlank(pp.getPay()) && pp.getPay().equals("1")){
			c.andPayChannelEqualTo("wx");
		}
		if(StringUtils.isNotBlank(pp.getPay()) && pp.getPay().equals("2")){
			c.andPayChannelEqualTo("alipay");
		}
		List<PayOrder> list = payOrderManager.selectByExample(example);
		if(list != null && list.size() > 0){
			for (PayOrder payOrder : list) {
				if(payOrder.getUserId() != null){
					BizUser bizUser = bizUserManager.selectByPrimaryKey(payOrder.getUserId());
					if(bizUser != null){
						payOrder.setPhone(bizUser.getPhone());
						if(bizUser.getCurrentRoomId() != null){
							Room room = roomManager.selectByPrimaryKey(bizUser.getCurrentRoomId());
							if(room != null){
								payOrder.setUnitNo(room.getUnitNo());
							}
						}
					}
				}
			}
		}
		dg.setRows(list);
		dg.setTotal(payOrderManager.countByExample(example));
		return dg; 
	      		
	}
	
	/**
	 * 跳转到物业缴费详情页面
	 * 
	 * @return
	 */
	@RequestMapping("/seePropertyPay")
	public String seePropertyPayPage(HttpServletRequest request,String id) {
		PayOrder payOrder = payOrderManager.selectByPrimaryKey(id);
		request.setAttribute("payOrder", payOrder);
		BizUser bizUser = new BizUser();
		Room room = new Room();
		if(payOrder.getUserId() != null){
			bizUser = bizUserManager.selectByPrimaryKey(payOrder.getUserId());
			if(bizUser.getCurrentRoomId() != null){
				room = roomManager.selectByPrimaryKey(bizUser.getCurrentRoomId());
			}
		}
		request.setAttribute("bizUser", bizUser);
		request.setAttribute("room", room);
		List<PropertyPayMent> list = new ArrayList<PropertyPayMent>();
		if(payOrder.getDataId() != null){
		String [] propertyPayMentId = payOrder.getDataId().split(",");
		for (String pId : propertyPayMentId) {
			PropertyPayMent pm = propertyPayMentManager.selectByPrimaryKey(Long.parseLong(pId));
			list.add(pm);
		}
		}
		request.setAttribute("propertyPayMent", list);
		return "/property/seePropertyPay";
	}
}
