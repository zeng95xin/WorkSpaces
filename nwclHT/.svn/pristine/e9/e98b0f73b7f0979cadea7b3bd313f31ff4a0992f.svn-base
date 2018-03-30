package com.bola.nwcl.dal.http.impl.lingling;

import java.util.List;

import org.apache.commons.httpclient.NameValuePair;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.util.lingling.util.HttpClientJson;
import com.bola.nwcl.common.util.lingling.util.JsonUtil;
import com.bola.nwcl.dal.http.api.lingling.LingLingClient;
import com.bola.nwcl.dal.http.api.lingling.LinglingFKClient;
@Service
public class LinglingFKClientImpl implements LinglingFKClient {
	//@Resource LingLingClient lingLingClient;
	/**
	 * 下发访客二维码
	 * @return
	 * @throws Exception
	 * * 参数
	 * ownerId String 是 业主ID
		visitorName String 是 访客名称
		startTime String 是 二维码有效开始时间（针对在线设备，毫秒）
		endTime String 是 二维码有效结束时间（针对在线设备，毫秒）
		effecNumber String 是 二维码有效次数
		keys Array 是 sdk 开门使用的key
		visitorTelephone String 否 访客电话
		visitorGender String 否 访客性别（0 为女，1 为男）
		isDrive String 否 是否驾车（0 为否 1 为是）
		visitorPurpose String 否 访客目的
		makeReason String 否 访客原因
		返回
		{
			"statusCode":"1",
			35
			"methodName":"SetVisitorPassport",
			"responseResult":
			{
				"codeId":"xxxx",
				"key:"xxx"
			}
		}
		返回值说明
		codeId String 二维码ID
		key String 生成二维码数据
	 */
	@Override
	public JSONObject setVisitorPassport(String ownerId, String visitorName,String startTime, String endTime, 
			String effecNumber,List<String> keys, String visitorTelephone, String visitorGender,String isDrive, 
			String visitorPurpose, String makeReason)
			throws Exception {
		//获取服务器地址
		String url=this.getAddrUrl("SetVisitorPassport", ownerId);
		System.out.println("服务器地址为:"+url);
		//生成二维码
		HttpClientJson client = new HttpClientJson();
		JSONObject requestParam = new JSONObject();
		requestParam.put("ownerId", ownerId);
		requestParam.put("visitorName", visitorName);
		requestParam.put("startTime", startTime);
		requestParam.put("endTime", endTime);
		requestParam.put("effecNumber", effecNumber);
		requestParam.put("keys", keys);
		requestParam.put("visitorTelephone", visitorTelephone);
		requestParam.put("isDrive", isDrive);
		requestParam.put("visitorPurpose", visitorPurpose);
		requestParam.put("makeReason", makeReason);
		JSONObject result = client.doPostJson(url,
				new NameValuePair[] {new NameValuePair("MESSAGE", JsonUtil.makeMessage(requestParam))});
		return result;
	}
	/**
	 *  删除访客二维码
	 * @return
	 * @throws Exception
	 * 参数
	 * ownerId String 是 业主ID
	   codeId String 是 二维码ID
	   返回
	   {
			"statusCode":"1",
			"methodName":"DelVisitorPassport",
			"responseResult":{"in the server delete success"}
		}
	 */
	@Override
	public JSONObject delVisitorPassport(String ownerId, String codeId)
			throws Exception {
		//获取服务器地址
		String url=this.getAddrUrl("DelVisitorPassport", ownerId);
		//删除访客二维码
		HttpClientJson client = new HttpClientJson();
		JSONObject requestParam = new JSONObject();
		requestParam.put("ownerId", ownerId);
		requestParam.put("codeId", codeId);
		JSONObject result = client.doPostJson(url,
				new NameValuePair[] {new NameValuePair("MESSAGE", JsonUtil.makeMessage(requestParam))});
		return result;
	}
	/**
	 *  查询访客二维码
	 * @return
	 * @throws Exception
	 * 参数
	 * ownerId String 是 业主ID
	 * 返回
	 * {
		"statusCode":"1",
		"methodName":"SetVisitorPassport",
		38
		"responseResult":
			{
				[
				"codeId":"xxx",
				"ownerId":"xxx",
				"visitorName":"xxx",
				"startTime":"xxx",
				"endTime":"xxx",
				"effecNumber":"xxx",
				"key":"xxx",
				"visitorTelephone":"xxx",
				"visitorGender":"xxx",
				"isDrive":"xxx",
				"visitorPurpose":"xxx",
				"makeReason":"xxx"
				],
				[
				"codeId":"xxx",
				"ownerId":"xxx",
				"visitorName":"xxx",
				"startTime":"xxx",
				"endTime":"xxx",
				"effecNumber":"xxx",
				"key":"xxx",
				"visitorTelephone":"xxx",
				"visitorGender":"xxx",
				"isDrive":"xxx",
				"visitorPurpose":"xxx",
				"makeReason":"xxx"
				]	
			}
		}
		
		返回参数说明：
		codeId String 二维码ID
		ownerId String 业主ID
		visitorName String 访客名称
		startTime String 二维码有效开始时间（针对在线设备）
		endTime String 二维码有效结束时间（针对在线设备）
		effecNumber String 二维码有效次数
		key String sdk 开门使用的key
		visitorTelephone String 访客电话
		visitorGender String 访客性别（0 为女，1 为男）
		isDrive String 是否驾车（0 为否 1 为是）
		visitorPurpose String 访客目的
		makeReason String 访客原因
	 */
	@Override
	public JSONObject getVisitorPassport(String ownerId) throws Exception {
		//获取服务器地址
		String url=this.getAddrUrl("GetVisitorPassport", ownerId);
		//查询访客二维码
		HttpClientJson client = new HttpClientJson();
		JSONObject requestParam = new JSONObject();
		requestParam.put("ownerId", ownerId);
		JSONObject result = client.doPostJson(url,
				new NameValuePair[] {new NameValuePair("MESSAGE", JsonUtil.makeMessage(requestParam))});
		return result;
	}
	/**
	 *   业主开门
	 * @return
	 * @throws Exception
	 * 参数
	 * ownerId String 是 业主ID
		key String 是 sdk 开门使用的key
		makeReason String 否 访客原因
	返回
	{
	"statusCode":"1",
	"methodName":"SetVisitorPassport",
	"responseResult":
		{
		"request command sent success"
		}
	}
	 */
	@Override
	public JSONObject userOpenDoor(String ownerId, String key, String makeReason)
			throws Exception {
		//获取服务器地址
		String url=this.getAddrUrl("UserOpenDoor", ownerId);
		//业主开门
		HttpClientJson client = new HttpClientJson();
		JSONObject requestParam = new JSONObject();
		requestParam.put("ownerId", ownerId);
		requestParam.put("key", key);
		requestParam.put("makeReason", makeReason);
		JSONObject result = client.doPostJson(url,
				new NameValuePair[] {new NameValuePair("MESSAGE", JsonUtil.makeMessage(requestParam))});
		return result;
	}
	
	/**
	 * 获取服务器地址
	 * @param JKName
	 * @param ownerId
	 * @return
	 * @throws Exception
	 */
	public String getAddrUrl(String JKName,String ownerId)throws Exception{
		LingLingClient lingling=new LingLingClientImpl();
		String result=lingling.queryServerAddr(ownerId, "V2.0");
		//String result=lingLingClient.queryServerAddr(ownerId, "V2.0");
		//{"statusCode":"1","methodName":"queryServerAddr","responseResult":{"openToken":"773D54B12A03E9CE82E2EA3A456758B8","addr":"http://device.izhihuicheng.net/Open/Visitor/"}}
		//addr+接口名+openToken
		String statusCode=JSON.parseObject(result).getString("statusCode");
		if(!"1".equals(statusCode)){
			throw new BusinessValidateException("获取服务地址失败");
		}
		String responseResult=JSON.parseObject(result).getString("responseResult");
		String openToken=JSON.parseObject(responseResult).getString("openToken");
		String addr=JSON.parseObject(responseResult).getString("addr");
		return addr+JKName+"/"+openToken;
		
	}
	
	public static void main(String[] args) {
		try {
		LinglingFKClientImpl dasd=new LinglingFKClientImpl();
		String sbc=dasd.getAddrUrl("UserOpenDoor","826");
		HttpClientJson client = new HttpClientJson();
		JSONObject requestParam = new JSONObject();
		requestParam.put("ownerId", 826);
		requestParam.put("key", "´X¨yriffehknhkinglWZÝÊÇ¢Õr­­©hmj[­ËµÙÔØ¨¨ÆXoymkmmdÃÍ®Ö¥¤È ´ÂÎXX±´¨qolWZÈÍÖªÑ¥ÍÇÔÔ°¥ÔWZ§ljllZÎpWl sj¥pwr¬¦pfbWÉÌÂÖ¥ª¯rsf§peªromhjdÓÆ Ñrqllgl[ÈÈ|ÇÕÆ[pjejlifeZã96b65e8faaa");
		requestParam.put("makeReason", "aaa");
		JSONObject result = client.doPostJson(sbc,
				new NameValuePair[] {new NameValuePair("MESSAGE", JsonUtil.makeMessage(requestParam))});
		System.out.println(result);
		/*LinglingFKClient fs=new LinglingFKClientImpl();
		List<String> keys=new ArrayList<String>();
		keys.add("121");
		keys.add("549");
		try {
			JSONObject result=fs.setVisitorPassport("1", "向世明", "1456847457342", "1465164366432", "3", keys, "13628420127", "1", "1", "目的招人", "原因招人耍");
			//System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
