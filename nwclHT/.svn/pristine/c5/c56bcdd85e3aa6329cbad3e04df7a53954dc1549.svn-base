package com.bola.nwcl.common.util.lingling.impl;

import org.springframework.stereotype.Service;

import com.bola.nwcl.dal.mybatis.model.LinglingOwner;
import com.bola.nwcl.dal.mybatis.model.LinglingResidential;

@Service
public class LingLingUtil {
	
	
	//{"statusCode":"0","methodName":"addManager","responseResult":"account already exists"}
	public void addManager(){
		/*try {
			linglingManager = linglingManagerMapper.selectByPrimaryKey(1L);
			if(linglingManager != null){
				isInitial = true;
				return;
			}
			JSONObject j = null;
			JSONObject requestParam = new JSONObject();
			requestParam.put("account", manager_account);
			requestParam.put("name", manager_name);
			requestParam.put("password", manager_password);
			requestParam.put("mobilePhone", manager_mobilePhone);
			String message = JsonUtil.makeMessage(requestParam);
			System.out.println(message);
			String result = p.addManager(message);
			System.out.println(result);
			j = JSONObject.parseObject(result);
			String userId = null;
			if(j.getString("statusCode").equals("0")){
				userId = "339";
			}else{
				JSONObject responseResult = j.getJSONObject("responseResult");
				if(j == null || responseResult == null || responseResult.getString("userId") == null){
					throw new BusinessDealException("获取门禁数据出现异常,请联系管理员");
				}
				userId = responseResult.getString("userId");
			}
			
			linglingManager = new LinglingManager();
			
			linglingManager.setAccount(manager_account);
			linglingManager.setName(manager_name);
			linglingManager.setPassword(manager_password);
			linglingManager.setMobilephone(manager_mobilePhone);
			linglingManager.setUserid(userId);
			linglingManager.setId(1L);
			
			linglingManagerMapper.insert(linglingManager);
			
			isInitial = true;
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
	
	public LinglingResidential addResidential(long id) throws Exception{
		return null;
		/*initial();
		Community c = communityMapper.selectByPrimaryKey(id);
		if(c == null){
			throw new BusinessDealException("小区不存在");
		}
		String userId = linglingManager.getUserid();
		String cityCode = "0";
		String residentialName = c.getSerial();
		JSONObject j = null;
		JSONObject requestParam = new JSONObject();
		requestParam.put("userId", userId);
		requestParam.put("cityCode", cityCode);
		requestParam.put("residentialName", residentialName);
		String message = JsonUtil.makeMessage(requestParam);
		
		String result = p.addResidential(message);
		j = JSONObject.parseObject(result);
		String responseResult = j.getString("responseResult");
		if(j == null || responseResult == null || responseResult == null){
			throw new BusinessDealException("门禁数据出现异常,请联系管理员");
		}
		
		LinglingResidential r = new LinglingResidential();
		r.setCitycode(cityCode);
		r.setCommunityId(id);
		r.setResidentialid(responseResult);
		r.setResidentialname(residentialName);
		r.setUserid(userId);
		r.setCitycode(cityCode);
		linglingResidentialMapper.insert(r);
		
		//addDevice(id, null, residentialName+"小区公共门", "2", null, "70714534729", "2", "10");
		
		return r;*/
	}
	
	public void addDeviceAfterAddResidential(){
		
	}
	
	/**
	 * @param communityId 小区id
	 * @param buildId	楼栋id
	 * @param name	设备名称
	 * @param customType	安装的类型（1为单元门，2 为公共门）
	 * @param deviceType	设备类型（在1.1.3版本中可以不填）
	 * @param deviceCode	设备串码
	 * @param lockType		锁的类型（0为电磁锁，1为电插锁）
	 * @param lockInterval	开门后多少秒关闭
	 * @throws Exception 
	 */
	public void addDevice(long communityId, Long buildId, String name, String customType, String deviceType, String deviceCode, String lockType, String lockInterval) throws Exception{
	  /*  `name` varchar(20) NOT NULL COMMENT '设备名称',
		  `customType` varchar(50) NOT NULL COMMENT '安装的类型（1为单元门，2 为公共门）',
		  `deviceType` varchar(50) DEFAULT NULL COMMENT '设备类型（在1.1.3版本中可以不填）',
		  `deviceCode` varchar(300) NOT NULL COMMENT '设备串码 ',
		  `lockType` varchar(50) NOT NULL COMMENT '锁的类型（0为电磁锁，1为电插锁）',
		  `lockInterval` varchar(50) NOT NULL COMMENT '开门后多少秒关闭', */
		/*initial();
		Community c = communityMapper.selectByPrimaryKey(communityId);
		Building b = null;
		if (buildId != null) {
			buildingMapper.selectByPrimaryKey(buildId);
		}
		if(c == null){
			throw new BusinessDealException("小区不存在");
		}
		if("1".equals(customType) && b == null){
			throw new BusinessDealException("楼栋不存在");
		}
		LinglingResidentialExample example_resid = new LinglingResidentialExample();
		example_resid.or().andCommunityIdEqualTo(communityId);
		List<LinglingResidential> list_resid = linglingResidentialMapper.selectByExample(example_resid);
		LinglingResidential resid = null;
		if(list_resid == null || list_resid.size() < 0){
			resid = addResidential(communityId);
		}else{
			resid = list_resid.get(0);
		}
		
		String userId = linglingManager.getUserid();
		
		
		JSONObject j = null;
		JSONObject requestParam = new JSONObject();
		requestParam.put("userId", userId);
		requestParam.put("name", name);
		requestParam.put("residentialId", resid.getResidentialid());
		requestParam.put("customType", customType);
		//requestParam.put("deviceType", deviceType);
		requestParam.put("deviceCode", deviceCode);
		requestParam.put("lockType", lockType);
		requestParam.put("lockInterval", lockInterval);
		String message = JsonUtil.makeMessage(requestParam);
		System.out.println(message);
		
		String result = p.addDevice(message);
		System.out.println(result);
		j = JSONObject.parseObject(result);
		String responseResult = j.getString("responseResult");
		if(j == null || responseResult == null || responseResult == null){
			throw new BusinessDealException("门禁数据出现异常,请联系管理员");
		}
		
		LinglingDevice device = new LinglingDevice();
		device.setCustomtype(customType);
		device.setDevicecode(deviceCode);
		device.setDevicetype(deviceType);
		device.setLockinterval(lockInterval);
		device.setLocktype(lockType);
		device.setName(name);
		device.setDeviceid(responseResult);
		device.setResidentialid(resid.getResidentialid());
		device.setCommunityId(communityId);
		device.setBuildingId(buildId);
		linglingDeviceMapper.insert(device);*/
		
	}
	
	public LinglingOwner addOwner(long buserId, String ownerName, String ownerTelephone, String mac, String[] deviceIds, String residentialId){
		  /*参数
		  `userId` varchar(300) NOT NULL COMMENT '工程人员id',
		  `ownerName` varchar(200) NOT NULL COMMENT '业主名称',
		  `ownerTelephone` varchar(50) DEFAULT NULL COMMENT '业主电话号码',
		  `mac` varchar(200) NOT NULL COMMENT '业主密码（建议唯一的mac）',
		  `deviceIds` varchar(900) NOT NULL COMMENT '关联的设备（授权业主的设备id，数组）  ',
		  `residentialId` varchar(300) NOT NULL COMMENT '小区 ID',*/
		/*initial();
		
		String[] device_Ids = {"2122"};
		String residential_Id = "456";
		
		String userId = linglingManager.getUserid();
		
		JSONObject requestParam = new JSONObject();
		requestParam.put("userId", userId);
		requestParam.put("ownerName", ownerName);
		requestParam.put("ownerTelephone", ownerTelephone);
		requestParam.put("mac", mac);
		//requestParam.put("deviceIds", deviceIds);
		requestParam.put("deviceIds", device_Ids);
		//requestParam.put("residentialId", residentialId);
		requestParam.put("residentialId", residential_Id);
		String message = JsonUtil.makeMessage(requestParam);
		System.out.println(message);
		String result = p.addOwner(message);
		System.out.println(result);
		JSONObject j = null;
		j = JSONObject.parseObject(result);
		JSONObject j_result = JSONObject.parseObject(result);
		
		JSONArray j_array = j_result.getJSONArray("responseResult");
		
		JSONObject j = j_array.getJSONObject(0);
		
		if(j_result == null || j_array == null || j == null){
			throw new BusinessDealException("门禁数据出现异常,请联系管理员");
		}
		
		String regcode = j.getString("regcode");
		String ownerId = j.getString("ownerId");
		String deviceType = j.getString("deviceType");
		@SuppressWarnings("unused")
		String ownerName_res = j.getString("ownerName");
		@SuppressWarnings("unused")
		String ownerTelephone_res = j.getString("ownerTelephone");
		String residentialName = j.getString("residentialName");
		String name = j.getString("name");
		String encryptStr = j.getString("encryptStr");
		String deviceId = j.getString("deviceId");
		
		LinglingOwner owner = new LinglingOwner();
		owner.setBuserId(buserId);
		owner.setDeviceid(deviceId);
		//owner.setDeviceids(Arrays.toString(deviceIds));
		owner.setDeviceids(Arrays.toString(device_Ids));
		owner.setDevicetype(deviceType);
		owner.setEncryptstr(encryptStr);
		owner.setMac(mac);
		owner.setName(name);
		owner.setOwnerid(ownerId);
		owner.setOwnername(ownerName);
		owner.setOwnertelephone(ownerTelephone);
		owner.setRegcode(regcode);
		//owner.setResidentialid(residentialId);
		owner.setResidentialid(residential_Id);
		owner.setResidentialname(residentialName);
		owner.setUserid(userId);
		linglingOwnerMapper.insertAndGetId(owner);
		queryServerAddr(owner);*/
		return null;
	}
	
	public LinglingOwner addOwner(long buserId, long communityId, long buildingId, String mac) throws Exception{
		/*BizUser buser = bizUserMapper.selectByPrimaryKey(buserId);
		if(buser == null){
			throw new BusinessValidateException("用户不存在");
		}
		LinglingResidentialExample example_resid = new LinglingResidentialExample();
		example_resid.or().andCommunityIdEqualTo(communityId);
		List<LinglingResidential> list_resid = linglingResidentialMapper.selectByExample(example_resid);
		LinglingResidential resid = null;
		if(list_resid == null || list_resid.size() < 0){
			resid = addResidential(communityId);
		}else{
			resid = list_resid.get(0);
		}
		String residentialId = resid.getResidentialid();
		String ownerName = buser.getName();
		String ownerTelephone = buser.getPhone();
		
		LinglingDeviceExample example_device = new LinglingDeviceExample();
		example_device.or().andCommunityIdEqualTo(communityId).andCustomtypeEqualTo("2");
		example_device.or().andCommunityIdEqualTo(communityId).andBuildingIdEqualTo(buildingId);
		List<LinglingDevice> list_device = linglingDeviceMapper.selectByExample(example_device);
		String[] deviceIds = new String[list_device.size()];
		for(int i = 0; i < list_device.size(); i++){
			deviceIds[i] = list_device.get(i).getDeviceid();
		}
		LinglingOwner owner = addOwner(buserId, ownerName, ownerTelephone, mac, deviceIds, residentialId);
		queryServerAddr(owner);*/
		return null;
	}
	
	public void queryServerAddr(LinglingOwner owner){
		
		/*String version = "V2.0";
		String ownerId = owner.getOwnerid();
		
		JSONObject j = null;
		JSONObject requestParam = new JSONObject();
		requestParam.put("ownerId", ownerId);
		requestParam.put("version", version);
		String message = JsonUtil.makeMessage(requestParam);
		System.out.println(message);
		String result = p.queryServerAddr(message);
		System.out.println(result);
		j = JSONObject.parseObject(result);
		JSONObject responseResult = j.getJSONObject("responseResult");
		if(j == null || responseResult == null){
			throw new BusinessDealException("门禁数据出现异常,请联系管理员");
		}
		
		String addr = responseResult.getString("addr");
		String openToken = responseResult.getString("openToken");
		owner.setAddr(addr);
		owner.setOpentoken(openToken);
		linglingOwnerMapper.updateByPrimaryKeySelective(owner);*/
	}
	
	public LinglingOwner ownerLogin(long buserId){
		/*LinglingOwnerExample example_owner = new LinglingOwnerExample();
		example_owner.or().andBuserIdEqualTo(buserId);
		List<LinglingOwner> list_owner = linglingOwnerMapper.selectByExample(example_owner);
		if(list_owner == null || list_owner.size() < 1){
			throw new BusinessValidateException("用户不存在");
		}
		LinglingOwner owner = list_owner.get(0);
		
		JSONObject j = null;
		JSONObject requestParam = new JSONObject();
		requestParam.put("loginUser", owner.getRegcode());
		requestParam.put("mac", owner.getMac());
		String message = JsonUtil.makeMessage(requestParam);
		System.out.println(message);
		String result = p.ownerLogin(message);
		System.out.println(result);
		j = JSONObject.parseObject(result);
		JSONArray responseResult = j.getJSONArray("responseResult");
		if(j == null || responseResult == null){
			throw new BusinessDealException("门禁数据出现异常,请联系管理员");
		}
		JSONObject json = responseResult.getJSONObject(0);
		String encryptStr = json.getString("encryptStr");
		String regcode = json.getString("regcode");
		owner.setEncryptstr(encryptStr);
		owner.setRegcode(regcode);
		linglingOwnerMapper.updateByPrimaryKeySelective(owner);*/
		return null;
	}
	
	
	private void initial(){
	}
} 
