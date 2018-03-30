package com.bola.nwcl.common.util.huanxin;

import java.util.HashMap;
import java.util.Map;

public class HeaderWrapper {
	
	private static final String HEADER_CONTENT_TYPE = "Content-Type";
	
	private static final String HEADER_AUTH = "Authorization";

//	private static final String RESTRICT_ACCESS = "restrict-access";
//
//	private static final String THUMBNAIL = "thumbnail";
//
//	private static final String SHARE_SECRET = "share-secret";
//
//	private static final String ACCEPT = "Accept";
	
	public static Map<String, String> map_json = new HashMap<String, String>();
	private static Map<String, String> map_authorization = new HashMap<String, String>();
	static{
		map_json.put(HEADER_CONTENT_TYPE, "application/json");
		map_authorization.put(HEADER_CONTENT_TYPE, "application/json");
		HuanXinUtil.getToken();
	}
	
	public static void setToken(String token){
		map_authorization.put(HEADER_AUTH, "Bearer " + token);
	}
	
	public static Map<String, String> getMapAuthorization(){
		HuanXinUtil.getToken();
		return map_authorization;
	}
	
	
}
