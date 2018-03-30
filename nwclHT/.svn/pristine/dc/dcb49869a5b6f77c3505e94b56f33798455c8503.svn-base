package com.bola.nwcl.common.global;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public final class Configure {
	private static final Logger logger = LoggerFactory.getLogger(Configure.class);
	/**
	 * 配置参数集合
	 */
	private static Map<String, String> allConfigMap = new HashMap<String, String>();
	
	//上传文件的路径和URL配置
	private static final String rootPath = "rootPath";
	private static final String rootUrl = "rootUrl";
	//极光推送
	private static final String jPushAppKey="JPushAppKey";
	private static final String jPushMasterSecret="JPushMasterSecret";
	private static final String jPushApiUrl = "JPushApiUrl";
	private static final String jPushsetApnsProduction = "JPushsetApnsProduction";
	//短信配置
	private static final String smsUrl="smsUrl";
	private static final String textSmsUser="textSmsUser";
	private static final String textSmsPwd="textSmsPwd";
	private static final String audioSmsUser="audioSmsUser";
	private static final String audioSmsPwd="audioSmsPwd";
	//令令门禁
	private static final String linglingSignature="linglingSignature";
	private static final String linglingToken="linglingToken";
	private static final String linglingWsdlUrl="linglingWsdlUrl";
	//物业_业主验证
	private static final String propertyValidateUrl="propertyValidateUrl";
	private static final String propertyGetUnitPaymentList="propertyGetUnitPaymentList";
	//阿里大鱼短信服务
	private static final String aliSmsUrl="aliSmsUrl";
	private static final String aliSmsAppKey="aliSmsAppKey";
	private static final String aliSmsAppSecret="aliSmsAppSecret";
	//支付
	private static final String pingppAppId="pingppAppId";
	private static final String pingppApiKey="pingppApiKey";
	//环信
	private static final String huanXinTokenUrl="huanXinTokenUrl";
	private static final String url_base="url_base";
	private static final String org_name="org_name";
	private static final String app_name="app_name";
	private static final String client_id="client_id";
	private static final String client_secret="client_secret";
	
	static {
		String profiles = System.getProperty("spring.profiles.active", "dev");
		logger.info("=========================>>>>在什么环境启动？===>"+profiles);
		String fileName = "config/config-" + profiles +  ".properties";
		allConfigMap = PropertiesUtil.getPropMap(fileName);
	}
	/**
	 * @return the configMap
	 */
	public static Map<String, String> getConfigMap() {
		return allConfigMap;
	}
	
	public static String getValue(String key){
		return allConfigMap.get(key);
	}
	
	/**
	 * 获取所有配置文件参数集合
	 */
	public static void setAllConfigMap(Map<String, String> paramsMap) {
		allConfigMap = paramsMap;
	}

	/**
	 * 获取所有配置Map集合
	 * @return Map<String, String>
	 */
	public static Map<String, String> getAllConfigMap() {
		return allConfigMap;
	}
	//上传文件的路径和URL配置
	public static String getRootPath() {
		return PropertiesUtil.getValue(rootPath);
	}
	public static String getRootUrl() {
		return PropertiesUtil.getValue(rootUrl);
	}
	//极光推送
	public static String getJPushAppKey(){
		return PropertiesUtil.getValue(jPushAppKey);
	}
	public static String getJPushMasterSecret(){
		return PropertiesUtil.getValue(jPushMasterSecret);
	}
	public static String getJPushApiUrl(){
		return PropertiesUtil.getValue(jPushApiUrl);
	}
	public static boolean getJPushsetApnsProduction(){
		String jPushsetApnsProduction_str = PropertiesUtil.getValue(jPushsetApnsProduction);
		if("true".equals(jPushsetApnsProduction_str)){
			return true;
		}else{
			return false;
		}
	}
	//短信配置
	public static String getSmsUrl(){
		return PropertiesUtil.getValue(smsUrl);
	}
	public static String getTextSmsUser(){
		return PropertiesUtil.getValue(textSmsUser);
	}
	public static String getTextSmsPwd(){
		return PropertiesUtil.getValue(textSmsPwd);
	}
	public static String getAudioSmsUser(){
		return PropertiesUtil.getValue(audioSmsUser);
	}
	public static String getAudioSmsPwd(){
		return PropertiesUtil.getValue(audioSmsPwd);
	}
	//令令门禁
	public static String getLinglingSignature(){
		return PropertiesUtil.getValue(linglingSignature);
	}
	public static String getLinglingToken(){
		return PropertiesUtil.getValue(linglingToken);
	}
	public static String getLinglingWsdlUrl(){
		return PropertiesUtil.getValue(linglingWsdlUrl);
	}
	//物业_业主验证
	public static String getPropertyValidateUrl(){
		return PropertiesUtil.getValue(propertyValidateUrl);
	}
	public static String getPropertyGetUnitPaymentList(){
		return PropertiesUtil.getValue(propertyGetUnitPaymentList);
	}
	//阿里大鱼短信服务
	public static String getAliSmsUrl(){
		return PropertiesUtil.getValue(aliSmsUrl);
	}
	public static String getAliSmsAppKey(){
		return PropertiesUtil.getValue(aliSmsAppKey);
	}
	public static String getAliSmsAppSecret(){
		return PropertiesUtil.getValue(aliSmsAppSecret);
	}
	//ping++
	public static String getPingppAppId(){
		return PropertiesUtil.getValue(pingppAppId);
	}
	public static String getPingppApiKey(){
		return PropertiesUtil.getValue(pingppApiKey);
	}
	//环信
	public static String getHuanXinTokenUrl(){
		return PropertiesUtil.getValue(huanXinTokenUrl);
	}
	public static String getUrl_base(){
		return PropertiesUtil.getValue(url_base);
	}
	public static String getOrg_name(){
		return PropertiesUtil.getValue(org_name);
	}
	public static String getApp_name(){
		return PropertiesUtil.getValue(app_name);
	}
	public static String getClient_id(){
		return PropertiesUtil.getValue(client_id);
	}
	public static String getClient_secret(){
		return PropertiesUtil.getValue(client_secret);
	}
	
}
