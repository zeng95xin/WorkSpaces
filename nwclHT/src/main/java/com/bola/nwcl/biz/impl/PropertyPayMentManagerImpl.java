package com.bola.nwcl.biz.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bola.nwcl.api.model.PropertyPayMentModel;
import com.bola.nwcl.biz.PropertyPayMentManager;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.common.util.DateUtils;
import com.bola.nwcl.common.util.property.PropertyValidate;
import com.bola.nwcl.dal.mybatis.mapper.PropertyPayMentMapper;
import com.bola.nwcl.dal.mybatis.mapper.RoomMapper;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.PropertyPayMent;
import com.bola.nwcl.dal.mybatis.model.PropertyPayMentExample;
import com.bola.nwcl.dal.mybatis.model.Room;

@Service
public class PropertyPayMentManagerImpl  extends ManagerImpl<PropertyPayMent, PropertyPayMentExample, Long> implements PropertyPayMentManager{
	
	private PropertyPayMentMapper propertyPayMentMapper;
	
	@Autowired
	private RoomMapper roomMapper;
	
	@Autowired
	public PropertyPayMentManagerImpl(PropertyPayMentMapper propertyPayMentMapper) {
		this.mapper = propertyPayMentMapper;
		this.propertyPayMentMapper = propertyPayMentMapper;
	}

	@Override
	public List<PropertyPayMentModel> updateGetPagePropertyPayMent(
			HttpServletRequest request, BizUser buser) {
		Room room = roomMapper.selectByPrimaryKey(buser.getCurrentRoomId());
		
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, c.get(Calendar.MONTH)+1);
		List<PropertyPayMentModel> list_ps = new ArrayList<PropertyPayMentModel>(12);
		SimpleDateFormat sdf = new SimpleDateFormat("yy年M月");
		for(int i=0; i< 12; i++){
			
			PropertyPayMentModel model = new PropertyPayMentModel();
			model.setStatus(1);
			List<PropertyPayMent> propertys = new ArrayList<PropertyPayMent>();
			
			Calendar c2 = Calendar.getInstance();
			c2.setTime(c.getTime());
			c2.set(Calendar.MONTH, c2.get(Calendar.MONTH)-i);
			Date end = c2.getTime();
			Calendar c3 = Calendar.getInstance();
			c3.setTime(c.getTime());
			c3.set(Calendar.MONTH, c3.get(Calendar.MONTH)-i-1);
			c3.set(Calendar.DAY_OF_MONTH, 1);
			c3.set(Calendar.HOUR_OF_DAY, 0);
			Date start = c3.getTime();
			String startStr = DateUtils.DateToStr(DateUtils.YYYYMM, start);
			String endStr = DateUtils.DateToStr(DateUtils.YYYYMM, end);
			start = DateUtils.strToDate(startStr, DateUtils.YYYYMM);
			String dateStr = sdf.format(start);
			model.setDateStr(dateStr);;
			String res = PropertyValidate.getUnitPayment(room.getUnitNo(), startStr, endStr);
			JSONArray j_array = JSONArray.parseArray(res);
			/*{
		        "ReceivableID": 1,
		        "CustomerName": "莫裕培",
		        "UnitNO": "ZQ-A2-0702",
		        "ReceivableDispName": "电费",
		        "ReceivableDate": "/Date(1398873600000)/",
		        "ReceivableAmount": 929.52,
		        "CurrAmount": 0,
		        "ReceivableStatus": 0
		    }*/
			model.setPropertys(propertys);
			list_ps.add(model);
			if(j_array == null){
				continue;
			}
			for (int h = 0; i < j_array.size(); i++) {
				JSONObject j = j_array.getJSONObject(h);
				Long receivableId = j.getLong("ReceivableID");
				String customerName = j.getString("CustomerName");
				String unitNO = j.getString("UnitNO");
				String receivableDispName = j.getString("ReceivableDispName");
				String receivableDate_str = j.getString("ReceivableDate");
				long receivableDate_long = Long.valueOf(receivableDate_str.substring(6,receivableDate_str.length()-2));
				Date receivableDate = new Date(receivableDate_long);
				double receivableAmount_d = j.getDoubleValue("ReceivableAmount");
				int receivableAmount = (int) (receivableAmount_d*100);
				double currAmount_d = j.getDoubleValue("CurrAmount");
				int currAmount = (int) (currAmount_d*100);
				int receivableStatus = j.getIntValue("ReceivableStatus");
				PropertyPayMentExample example = new PropertyPayMentExample();
				/*example.or().andCustomerNameEqualTo(customerName).andUnitNoEqualTo(unitNO).andReceivableDispnameEqualTo(receivableDispName)
				.andReceivableDateEqualTo(receivableDate).andReceivableAmountEqualTo(receivableAmount);*/
				example.or().andReceivableIdEqualTo(receivableId);
				List<PropertyPayMent> list = propertyPayMentMapper.selectByExample(example);
				PropertyPayMent p = null;
				if(list == null || list.size() < 1){
					p = new PropertyPayMent();
					p.setReceivableId(receivableId);
					p.setCurrAmount(currAmount);
					p.setCustomerName(customerName);
					p.setReceivableAmount(receivableAmount);
					p.setReceivableDate(receivableDate);
					p.setReceivableDispname(receivableDispName);
					p.setReceivableStatus(receivableStatus);
					p.setRoomId(room.getId());
					p.setUnitNo(unitNO);
					p.setPropertyDate(start);
					propertyPayMentMapper.insert(p);
				}else{
					if(list.size()>1){
						throw new BusinessValidateException("物业系统出现重复的物业数据,请联系物管部");
					}
					p = list.get(0);
					p.setCurrAmount(currAmount);
					if(p.getReceivableStatus().intValue() == 0 && receivableStatus == 1){
						p.setReceivableStatus(receivableStatus);
					}
					propertyPayMentMapper.updateByPrimaryKeySelective(p);
				}
				if(p.getReceivableStatus().intValue()==0){
					model.setStatus(0);
				}
				propertys.add(p);
			}
		}
		return list_ps;
	}
	

}
