package com.bola.nwcl.dal.http.api.lingling;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

public interface LinglingFKClient {
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
	public JSONObject setVisitorPassport(String ownerId,String visitorName,String startTime,String endTime,String effecNumber,List<String> keys,
			String visitorTelephone,String visitorGender,String isDrive ,String visitorPurpose,String makeReason)throws Exception;
	
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
	public JSONObject delVisitorPassport(String ownerId,String codeId)throws Exception;
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
	public JSONObject getVisitorPassport (String ownerId)throws Exception;
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
	public JSONObject userOpenDoor (String ownerId,String key,String makeReason)throws Exception;
	
}
