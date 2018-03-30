package com.bola.nwcl.biz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.api.model.VisitorsModel;
import com.bola.nwcl.api.model.VisitorsStatisticsModel;
import com.bola.nwcl.biz.VisitorsManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.common.util.file.SerialUtil;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.mybatis.mapper.VisitorPassportRecordMapper;
import com.bola.nwcl.dal.mybatis.mapper.VisitorsMapper;
import com.bola.nwcl.dal.mybatis.mapper.VisitorsStatisticsDetailMapper;
import com.bola.nwcl.dal.mybatis.mapper.VisitorsStatisticsMapper;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.Visitors;
import com.bola.nwcl.dal.mybatis.model.VisitorsExample;
import com.bola.nwcl.dal.mybatis.model.VisitorsStatistics;
import com.bola.nwcl.dal.mybatis.model.VisitorsStatisticsDetail;
import com.bola.nwcl.dal.mybatis.model.VisitorsStatisticsDetailExample;
import com.bola.nwcl.dal.mybatis.model.VisitorsStatisticsExample;

@Service
public class VisitorsManagerImpl  extends ManagerImpl<Visitors, VisitorsExample, Long> implements VisitorsManager{
	
	private VisitorsMapper visitorsMapper;
	
	@Autowired
	private VisitorsStatisticsMapper visitorsStatisticsMapper;
	
	@Autowired
	private VisitorsStatisticsDetailMapper visitorsStatisticsDetailMapper;
	
	@Autowired
	public VisitorsManagerImpl(VisitorsMapper visitorsMapper) {
		this.mapper = visitorsMapper;
		this.visitorsMapper = visitorsMapper;
	}

	@Override
	public void insertAndGetId(Visitors visitors) {
		visitorsMapper.insertAndGetId(visitors);
	}



	@Override
	public VisitorsModel addVisitorsByBuser(BizUser buser, String[] namePhones, Date expectArriveTime, Date expectLeaveTime) throws Exception {
		Visitors v = new Visitors();
		v.setBuserId(buser.getId());
		v.setExpectArriveTime(expectArriveTime);
		v.setEffectiveArriveTime(new Date(expectArriveTime.getTime() - 1000*60*60*3));
		v.setEffectiveLeaveTime(new Date(expectLeaveTime.getTime() + 1000*60*60*3));
		v.setExpectLeaveTime(expectLeaveTime);
		boolean isRepeat = true;
		do{
			String serial = SerialUtil.buildSerialNum();
			VisitorsExample example_v = new VisitorsExample();
			example_v.or().andSerialEqualTo(serial);
			example_v.setLimit(1);
			example_v.setOffset(0);
			List<Visitors> list = visitorsMapper.selectByExample(example_v);
			if (list.size() > 0) {
			}else{
				isRepeat = false;
				v.setSerial(serial);
			}
		}while(isRepeat);
		v.setRoomId(buser.getCurrentRoomId());
		visitorsMapper.insertAndGetId(v);
		
		List<VisitorsStatisticsModel> list_vs_model = new ArrayList<VisitorsStatisticsModel>(namePhones.length); 
		for(String namePhone : namePhones){
			String[] tempArray = namePhone.split(":");
			String phone = tempArray[0];
			String name = tempArray[1];
			VisitorsStatistics vs = new VisitorsStatistics();
			vs.setName(name);
			vs.setPhone(phone);
			vs.setVisitorsId(v.getId());
			vs.setStatus(0);
			visitorsStatisticsMapper.insertAndGetId(vs);
			VisitorsStatisticsModel vs_model = new VisitorsStatisticsModel();
			BeanUtils.copyProperties(vs, vs_model);
			list_vs_model.add(vs_model);
		}
		
		VisitorsModel v_model = new VisitorsModel();
		BeanUtils.copyProperties(v, v_model);
		
		v_model.setDetails(list_vs_model);
		return v_model;
	}

	@Override
	public VisitorsModel updateVisitorsByBuser(Visitors v, BizUser buser, String[] namePhones, Date expectArriveTime, Date expectLeaveTime) throws Exception {
		
		if (expectArriveTime != null) {
			v.setExpectArriveTime(expectArriveTime);
			v.setEffectiveArriveTime(new Date(expectArriveTime.getTime() - 1000 * 60 * 60 * 3));
		}
		if (expectLeaveTime != null) {
			v.setExpectLeaveTime(expectLeaveTime);
			v.setEffectiveLeaveTime(new Date(expectLeaveTime.getTime() + 1000 * 60 * 60 * 3));
		}
		visitorsMapper.updateByPrimaryKeySelective(v);
		
		VisitorsModel v_model = new VisitorsModel();
		BeanUtils.copyProperties(v, v_model);
		List<VisitorsStatisticsModel> list_vs_model = null;
		if(namePhones != null){
			VisitorsStatisticsExample example_vs = new VisitorsStatisticsExample();
			example_vs.or().andVisitorsIdEqualTo(v.getId());
			visitorsStatisticsMapper.deleteByExample(example_vs);
			list_vs_model = new ArrayList<VisitorsStatisticsModel>(namePhones.length); 
			for(String namePhone : namePhones){
				String[] tempArray = namePhone.split(":");
				String phone = tempArray[0];
				String name = tempArray[1];
				VisitorsStatistics vs = new VisitorsStatistics();
				vs.setName(name);
				vs.setPhone(phone);
				vs.setVisitorsId(v.getId());
				vs.setStatus(0);
				visitorsStatisticsMapper.insertAndGetId(vs);
				VisitorsStatisticsModel vs_model = new VisitorsStatisticsModel();
				BeanUtils.copyProperties(vs, vs_model);
				list_vs_model.add(vs_model);
			}
		}else{
			VisitorsStatisticsExample example_vs = new VisitorsStatisticsExample();
			example_vs.or().andVisitorsIdEqualTo(v.getId());
			List<VisitorsStatistics> list_vs = visitorsStatisticsMapper.selectByExample(example_vs);
			list_vs_model = new ArrayList<VisitorsStatisticsModel>(list_vs.size()); 
			for(VisitorsStatistics vs : list_vs){
				VisitorsStatisticsModel vs_model = new VisitorsStatisticsModel();
				BeanUtils.copyProperties(vs, vs_model);
				list_vs_model.add(vs_model);
				VisitorsStatisticsDetailExample example_vs_detail = new VisitorsStatisticsDetailExample();
				example_vs_detail.or().andVisitorsStatisticsEqualTo(vs.getId());
				List<VisitorsStatisticsDetail> vs_details = visitorsStatisticsDetailMapper.selectByExample(example_vs_detail);
				vs_model.setDetails(vs_details);
			}
		}
		v_model.setDetails(list_vs_model);
		return v_model;
	}

	@Override
	public void deleteByBuser(Visitors v) {
		VisitorsStatisticsExample example_vs = new VisitorsStatisticsExample();
		example_vs.or().andVisitorsIdEqualTo(v.getId());
		visitorsStatisticsMapper.deleteByExample(example_vs);
		visitorsMapper.deleteByPrimaryKey(v.getId());
		VisitorsStatisticsDetailExample example_detail = new VisitorsStatisticsDetailExample();
		example_detail.or().andVisitorsIdEqualTo(v.getId());
		visitorsStatisticsDetailMapper.deleteByExample(example_detail);
	}
	
	@Autowired private VisitorPassportRecordMapper visitorPassportRecordMapper;
	
	@Override
	public DataGrid dataGridVisitorPassportRecord(Map<String,Object> params){
		DataGrid dg = new DataGrid();
		int count = dataGridVisitorPassportRecordCount(params);
		List<?> rows = visitorPassportRecordMapper.dataGridVisitorPassportRecord(params);
		dg.setRows(rows);
		dg.setTotal(count);
		return dg;
	}
	@Override
	public int dataGridVisitorPassportRecordCount(Map<String,Object> params){
		return visitorPassportRecordMapper.dataGridVisitorPassportRecordCount(params);
	}
	
	@Override
	public void deleteVisitorPassportRecord(Long id) {
		visitorPassportRecordMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void deleteBatchVisitorPassportRecord(String ids) {
		if (ids != null && ids.length() > 0) {
			for (String id : ids.split(",")) {
				if (id != null) {
					this.deleteVisitorPassportRecord(Long.parseLong(id));
				}
			}
		}
		
	}

}
