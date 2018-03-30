/**   
 * @Title: HttpClientUtil.java 
 * @Package com.yqsapp.sso.utils 
 * @Description: 
 * @date 2014-6-23 下午3:18:38   
 */
package com.bola.nwcl.common.util.huanxin;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;

import com.bola.nwcl.common.util.MySSLSocketFactory;

/**
 * 
 * <p>
 * HttpClient工具类
 * <p>
 * <p>
 * 实现基本的表单提交操作
 * </p>
 * 
 * @version 2014年12月8日 下午3:55:58
 * 
 */
public class HuanXinHttpClientUtil {

	/**
	 * 使用POST方式调用HTTP请求
	 * 
	 * @param url
	 *            请求地址的URL
	 * @param requestBody
	 *            请求体内容
	 * @param contentType
	 *            请求体内容类型
	 * @param headerMap
	 *            请求的头信息，必须转化为字符串的键值对
	 * @return 返回响应的结果字符串
	 * @since 1.0.0
	 */
	private static HttpClient httpclient = new HttpClient();
	
	public static String post(String url, String requestBody, String contentType, Map<String, String> headerMap) {
		String ret=null;
		PostMethod postMethod = null;
		try {
			// 创建post方法
			postMethod = new PostMethod(url);

			// 封装请求头参数
			if (null != headerMap) {
				Set<String> headerNames = headerMap.keySet();
				for (String headerName : headerNames) {
					String headerValue = headerMap.get(headerName);
					postMethod.addRequestHeader(headerName, headerValue);
				}
			}
			// 封装请求参数
			RequestEntity requestEntity = new StringRequestEntity(requestBody, contentType, "utf-8");
			postMethod.setRequestEntity(requestEntity);

			//postMethod.setRequestBody(requestBody);

			// 设置参数请求的编码
			postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");

			int statusCode = httpclient.executeMethod(postMethod);

			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + postMethod.getStatusLine());
			}
			byte[] resp = postMethod.getResponseBody();
			ret = new String(resp, "UTF-8");
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (postMethod != null) {
				postMethod.releaseConnection();
			}
		}
		return ret;
	}

	/**
	 * 使用POST方式调用HTTPS请求
	 * 
	 * @param url
	 *            请求地址的URL
	 * @param requestBody
	 *            请求体内容
	 * @param contentType
	 *            请求体内容类型
	 * @param headerMap
	 *            请求的头信息，必须转化为字符串的键值对
	 * @return 返回响应的结果字符串
	 * @since 1.0.0
	 */
	public static String postHttps(String url, String requestBody, String contentType, Map<String, String> headerMap) {
		//https 增加下面两行代码  
        Protocol myhttps = new Protocol("https", new MySSLSocketFactory(), 443);  
        Protocol.registerProtocol("https", myhttps);  
		
		String ret=null;
		PostMethod postMethod = null;
		try {
			// 创建post方法
			postMethod = new PostMethod(url);

			// 封装请求头参数
			if (null != headerMap) {
				Set<String> headerNames = headerMap.keySet();
				for (String headerName : headerNames) {
					String headerValue = headerMap.get(headerName);
					postMethod.addRequestHeader(headerName, headerValue);
				}
			}
			// 封装请求参数
			RequestEntity requestEntity = new StringRequestEntity(requestBody, contentType, "utf-8");
			postMethod.setRequestEntity(requestEntity);

			//postMethod.setRequestBody(requestBody);

			// 设置参数请求的编码
			postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");

			int statusCode = httpclient.executeMethod(postMethod);

			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + postMethod.getStatusLine());
			}
			byte[] resp = postMethod.getResponseBody();
			ret = new String(resp, "UTF-8");
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (postMethod != null) {
				postMethod.releaseConnection();
			}
		}
		return ret;
	}
	
}
