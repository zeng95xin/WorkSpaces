package com.bola.nwcl.dal.http.api.lingling;

import java.util.List;

public interface LingLingClient {
	/**
	 * 添加工程人员账号  
	 * 参数说明
	 *  account String 是 工程人员登陆账号
		name String 是 工程人员显示名称
		password String 是 工程人员登陆密码
		mobilePhone String 是 用于工程人员修改、找回密码
		返回参数
		名称 类型 说明
		userId String 工程人员账号ID（工程人员标志位，后续接口使用）
		//返回json
		{
		"statusCode":"1",
		"methodName":"addManager",
		"responseResult":
			{
			"userId":"xxx"
			}
		}
	 * @throws Exception
	 */
	public String addManager(String account,String name,String password,String mobilePhone )throws Exception; 
	
	/**
	 * 工程人员登陆接口（在1.1.3 版本已过时，不建议使用）
	 * 参数说明
		account String 是 工程人员登陆账号
		password String 是 工程人员登陆密码
		返回示例
		{
		"statusCode":"1",
		"methodName":"managerLogin",
		"responseResult":
			{
			"deptId":"xxx",
			"userId":"xxx",
			"mobilePhone":"xxxx",
			"name":"xxx",
			"account":"xxx"
			}
		}
	 * @throws Exception
	 */
	public String managerLogin(String account,String password)throws Exception;
	
	/**
	 *  添加小区
	 *  参数说明
	 *  userId String 是 工程人员ID
		cityCode String 否 城市（没有传0,最大不要超过4 位数）
		residentialName String 是 小区名称
		返回
		{
			"statusCode":"1",
			"methodName":"addResidential",
			"responseResult":xxx
		}
		返回说明 ： responseResult int 小区id（小区标志位，添加设备时作为参数上传）
	 * @throws Exception
	 */
	public String addResidential(String userId,String cityCode,String residentialName)throws Exception;
	
	/**
	 * 添加设备
	 * 参数说明
	 * userId String 是 工程人员ID
		name String 是 设备名称
		residentialId String 是 小区ID
		customType String 是 安装的类型（1 为单元门，2 为公共门）
		deviceType String 否 设备类型（在1.1.3 版本中可以不填）
		deviceCode String 是 设备串码
		lockType String 是 锁的类型（0 为电磁锁，1 为电插锁）
		lockInterval String 是 开门后多少秒关闭
		
		返回
		{
			"statusCode":"1",
			"methodName":"addDevice",
			"responseResult":"xxx"
		}
		返回说明：responseResult String 设备id（设备标志位，添加业主时使用）
	 * @throws Exception
	 */
	public String addDevice(String userId,String name,String residentialId,String customType,
		String deviceType,String deviceCode,String lockType,String lockInterval	)throws Exception; 
	
	/**
	 *  添加业主
	 *  参数
	 *  userId String 是 工程人员ID
		ownerName String 是 业主名称
		ownerTelephone String 否 业主电话号码
		mac String 是 业主密码（建议唯一的mac）
		deviceIds Array 是 关联的设备（授权业主的设备id，数组）
		residentialId String 是 小区ID（添加小区时返回的小区id）
		返回
		{
			"statusCode":"1",
			"methodName":"addOwner",
			"responseResult": "["{
				"deviceType":"xxx",
				"ownerName":"xxx",
				"ownerTelephone":"xxx",
				"residentialName":"xxx",
				"regcode":"xxx",
				"name":"xxx",
				"encryptStr":"xxx",
				"ownerId":"xxx",
				"deviceId":"xxx"
			}"]"
		}
		返回值说明 
		regcode String 业主注册码
		ownerId String 业主ID（用于访客二维码的生成）
		ownerName String 业主名称
		ownerTelephone String 业主电话
		residentialName String 小区名
		deviceId String 设备ID
		name String 设备名称
		deviceType String 设备类型（1 为BT，2 为WIFI，3 为WIFI+BT，4为开门按钮）
		encryptStr String 开门用的key
	 */
	public String addOwner(String userId,String ownerName,String ownerTelephone,
			String mac,List<String> deviceIds,String residentialId )throws Exception; 
	/**
	 *  重新获取电子钥匙
	 *  参数
	 *  loginUser String 是 业主注册码
		mac String 是 业主密码
		返回值
		{
			"statusCode":"1",
			"methodName":"ownerLogin",
			"responseResult":"[{
				"deviceType":"xxx",
				"ownerName":"xxx",
				"ownerTelephone":"xxx",
				"residentialName":"xxx",
				"regcode":"xxx","name":"xxx",
				"encryptStr":"xxx",
				"ownerId":"xxx",
				"deviceId":"xxx"
			}]"
		}
		返回值说明：
		regcode String 注册码
		ownerId String 业主ID
		ownerName String 业主名称
		ownerTelephone String 业主电话
		residentialName String 小区名
		deviceId String 设备ID
		name String 设备名称
		deviceType String 设备类型（1 为BT，2 为WIFI，3 为WIFI+BT，4为开门按钮）
		encryptStr String 开门用的key
	 */
	public String ownerLogin(String loginUser,String mac)throws Exception; 
	
	/**
	 *  延续失效业主
	 *  参数：
	 *  ownerId String 是 业主Id
		userId String 是 用户Id
		mac String 是 业主密码
		continueDay String 是 延续时间（天数）
		返回值
		{
			"statusCode":"1",
			"methodName":"continueOwner ",
			"responseResult": "[
				{ "deviceType":"xxx",
					"ownerName":"xxx",
					"ownerTelephone":"xxx",
					"residentialName":"xxx",
					"regcode":"xxx",
					"name":"xxx",
					"encryptStr":"xxx",
					"ownerId":"xxx",
					"deviceId":"xxx"
				},
				{ "deviceType":"xxx",
					"ownerName":"xxx",
					"ownerTelephone":"xxx",
					"residentialName":"xxx",
					"regcode":"xxx",
					"name":"xxx",
					"encryptStr":"xxx",
					"ownerId":"xxx",
					"deviceId":"xxx"
				}
			]"
		}
		返回参数说明：
		regcode String 业主注册码
		ownerId String 业主ID（用于访客二维码的生成）
		ownerName String 业主名称
		ownerTelephone String 业主电话
		residentialName String 小区名
		deviceId String 设备ID
		name String 设备名称
		deviceType String 设备类型（1 为BT，2 为WIFI，3 为WIFI+BT，4为开门按钮）
		encryptStr String 开门用的key
	 */
	public String continueOwner(String ownerId,String userId,String mac,String continueDay)throws Exception; 
	
	/**
	 *  修改业主开门权限
	 *  参数：
	 *  ownerId String 是 业主Id
		deviceIds Array 是 关联的设备
		mac String 是 业主密码
		返回值
		{
			"statusCode":"1",
			"methodName":"updateOwnerDevice ",
			"responseResult": "[
				{
					"deviceType":"xxx",
					"ownerName":"xxx",
					"ownerTelephone":"xxx",
					"residentialName":"xxx",
					"regcode":"xxx",
					"name":"xxx",
					"encryptStr":"xxx",
					"ownerId":"xxx",
					"deviceId":"xxx"
				},
				{
					"deviceType":"xxx",
					"ownerName":"xxx",
					"ownerTelephone":"xxx",
					"residentialName":"xxx",
					"regcode":"xxx",
					"name":"xxx",
					"encryptStr":"xxx",
					"ownerId":"xxx",
					"deviceId":"xxx"
				}
			]"
		}
		返回值说明：
		regcode String 业主注册码
		ownerId String 业主ID（用于访客二维码的生成）
		ownerName String 业主名称
		ownerTelephone String 业主电话
		residentialName String 小区名
		deviceId String 设备ID
		name String 设备名称
		deviceType String 设备类型（1 为BT，2 为WIFI，3 为WIFI+BT，4为开门按钮）
		encryptStr String 开门用的key
	 */
	public String updateOwnerDevice(String ownerId,List<String> deviceIds,String mac)throws Exception; 
	
	/**
	 * 查询设备服务器地址
	 * 参数
	 * ownerId String 是 业主Id
		version String 是 版本号（默认传入V2.0）
		返回值
		{
			"statusCode":"1",
			"methodName":"queryServerAddr",
			"responseResult":"{
				"addr":"xxx",
				"openToken":"xxx"
			}"
		}
		返回值说明：addr String 服务器地址
		openToken String 访问服务器地址的令牌
	 */
	public String queryServerAddr(String ownerId,String version)throws Exception; 
	
}
