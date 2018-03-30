package com.bola.nwcl.common.util.jsonhelper;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bola.nwcl.common.exception.ApiException;

/**
 * do some json things
 */
public final class JsonHelper {
    public final static String EMPTY = "";
    public static final String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private JsonHelper() {
    }

    /**
     * g构建具备content-type为application/json的headers
     *
     * @return
     */
    public static HttpHeaders buildJsonHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }


    /**
     * 业务相关的json生成方法
     *
     * @param start 起始元素
     * @param count 请求结果列表数量
     * @param total
     * @param list  list for targets property,list contains具备fastjson的@JSONField标注的对象
     */
    public static String buildJsonString(int start, int count, int total, List<?> list) {
        JSONObject json = new JSONObject();
        json.put("start", start);
        json.put("count", count);
        json.put("total", total);
        json.put("targets", list);
        return JSON.toJSONStringWithDateFormat(json, DEFAULT_TIME_FORMAT);
    }

    /**
     * 业务相关的json生成方法
     *
     * @param obj 具备fastjson的@JSONField标注的对象
     */
    public static String buildJsonString(Object obj) {
    	if(obj == null) {
    		return "{}";
    	}
    	return JsonFilter.toSpecialJSONStringWithDateFormat(obj, DEFAULT_TIME_FORMAT, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty);
//        return JSON.toJSONStringWithDateFormat(obj, DEFAULT_TIME_FORMAT, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty);
    }
    
    /**
     * 业务相关的json生成方法，如果obj==null，返回{}
     *
     * @param obj 具备fastjson的@JSONField标注的对象
     */
    public static String buildJsonStringOrEmpty(Object obj) {
    	if (obj != null) {
    		return buildJsonString(obj);
    	}
    	
        return "{}";
    }
    
    public static ResponseEntity<String> buildJsonResponseEntity(Object obj) {
		return new ResponseEntity<String>(
				buildJsonStringOrEmpty(obj),
				buildJsonHeaders(), HttpStatus.OK);
	}

	public static ResponseEntity<String>  buildJsonResponseEntity(Object object, HttpStatus status){
		return new ResponseEntity<String>(JsonHelper.buildJsonString(object),
				JsonHelper.buildJsonHeaders(), status);
	}
	
    /**
     * 业务相关的json生成方法
     *
     * @param exception Api业务错误
     * @param method    RequestMethod {GET, PUT, DELETE, POST}
     * @param uri       RequestURI e.g "/v1/fake"
     */
    public static String buildJsonString(ApiException exception, String method, String uri) {
        JSONObject json = new JSONObject();
        json.put("request_uri", method + ' ' + uri);
        json.put("error", exception.getError());
        json.put("description", exception.getDescription());
        json.put("error_code", exception.getErrorCode());
        return json.toString();
    }
}
