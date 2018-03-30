package com.bola.nwcl.biz.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bola.nwcl.biz.VisitorPassportRecordManager;
import com.bola.nwcl.biz.VisitorsManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.DoorControlMapper;
import com.bola.nwcl.dal.mybatis.mapper.LingLingOwnerv3Mapper;
import com.bola.nwcl.dal.mybatis.mapper.LingLingVisitiorv3Mapper;
import com.bola.nwcl.dal.mybatis.mapper.VisitorPassportRecordMapper;
import com.bola.nwcl.dal.mybatis.model.DoorControl;
import com.bola.nwcl.dal.mybatis.model.DoorControlExample;
import com.bola.nwcl.dal.mybatis.model.LingLingOwnerv3;
import com.bola.nwcl.dal.mybatis.model.LingLingOwnerv3Example;
import com.bola.nwcl.dal.mybatis.model.LingLingVisitiorv3;
import com.bola.nwcl.dal.mybatis.model.LingLingVisitiorv3Example;
import com.bola.nwcl.dal.mybatis.model.VisitorPassportRecord;
import com.bola.nwcl.dal.mybatis.model.VisitorPassportRecordExample;

@Service
public class VisitorPassportRecordManagerImpl extends
		ManagerImpl<VisitorPassportRecord, VisitorPassportRecordExample, Long> implements VisitorPassportRecordManager {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired private VisitorsManager visitorsManager;
	@Autowired private VisitorPassportRecordMapper visitorPassportRecordMapper;
	@Autowired private LingLingVisitiorv3Mapper lingLingVisitiorv3Mapper;
	@Autowired private LingLingOwnerv3Mapper lingLingOwnerv3Mapper;
	@Autowired private DoorControlMapper doorControlMapper;

	@Autowired
	public VisitorPassportRecordManagerImpl(VisitorPassportRecordMapper visitorPassportRecordMapper) {
		this.mapper = visitorPassportRecordMapper;
		this.visitorPassportRecordMapper = visitorPassportRecordMapper;
	}
	
	@Override
	public void updateReviceNotice(String noticeMsg) {
		try {
			logger.info("接受门禁通知,noticeMsg:" + noticeMsg);
			JSONObject notice = JSONObject.parseObject(noticeMsg);
			if(null == notice){
				logger.info("接受门禁通知,noticeMsg内容为空");
				return;
			}
			String noticeName = notice.getString("noticeName");
			JSONObject msg = notice.getJSONObject("msg");
			if(null == msg){
				logger.info("接受门禁通知,msg内容为空");
				return;
			}
			String lingLingId = msg.getString("lingLingId");
			String deviceCode = msg.getString("deviceCode");
			String deviceId = msg.getString("deviceId");
			String openTime = msg.getString("openTime");
			String qrcodeKey = msg.getString("qrcodeKey");
			int type = 0;
			long buserId = -1l;
			long visitorId = -1l;
			long doorControllId = -1l;
			long communityId = -1l;
			if("ownerQrcodeOpen".equals(noticeName)){
				type = 1;
				LingLingOwnerv3Example example_v3 = new LingLingOwnerv3Example();
				example_v3.or().andLingLingIdEqualTo(lingLingId);
				List<LingLingOwnerv3> list_owners = lingLingOwnerv3Mapper.selectByExample(example_v3);
				if(list_owners.size() > 0){
					buserId = list_owners.get(0).getBuserId();
				}
			}else if("visitorQrcodeOpen".equals(noticeName)){
				type = 2;
				LingLingVisitiorv3Example example_v3 = new LingLingVisitiorv3Example();
				example_v3.or().andQrcodeKeyEqualTo(qrcodeKey);
				List<LingLingVisitiorv3> list_visitiors = lingLingVisitiorv3Mapper.selectByExample(example_v3);
				if(list_visitiors.size() > 0){
					visitorId = list_visitiors.get(0).getVisitorId();
				}
			}
			
			DoorControlExample example_door = new DoorControlExample();
			example_door.or().andDeviceEqualTo(deviceCode);
			List<DoorControl> list_doors = doorControlMapper.selectByExample(example_door);
			if(list_doors.size() > 0){
				doorControllId = list_doors.get(0).getId();
				communityId = list_doors.get(0).getCommunityId();
			}
			Date openTime_data = new Date(Long.valueOf(openTime));
			
			VisitorPassportRecord visitorPassportRecord = new VisitorPassportRecord();
			visitorPassportRecord.setBuserId(buserId);
			visitorPassportRecord.setDeviceCode(deviceCode);
			visitorPassportRecord.setDeviceId(deviceId);
			visitorPassportRecord.setDoorControllId(doorControllId);
			visitorPassportRecord.setLingLingId(lingLingId);
			visitorPassportRecord.setOpenTime(openTime_data);
			visitorPassportRecord.setOpenTimeStr(openTime);
			visitorPassportRecord.setQrcodeKey(qrcodeKey);
			visitorPassportRecord.setVisitorId(visitorId);
			visitorPassportRecord.setType(type);
			visitorPassportRecord.setCommunityId(communityId);
			
			visitorPassportRecordMapper.insert(visitorPassportRecord);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统异常，{}", e.getMessage());
		}
	}

}
