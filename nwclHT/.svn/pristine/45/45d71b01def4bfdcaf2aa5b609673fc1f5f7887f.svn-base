package com.bola.nwcl.web.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.bola.nwcl.common.global.Configure;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.web.model.SessionInfo;
public class SessionValidate implements Filter {
	Set<String> urlSet = new HashSet<String>();
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String urls = filterConfig.getInitParameter("urls");
		String[] urlArray = urls.replace(" ", "").split(",");
		for (String url : urlArray) {
			if (StringUtils.isNotBlank(url)) {
				urlSet.add(url);
			}
		}
	}
	
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		chain.doFilter(request, response);
//		HttpServletResponse resp = (HttpServletResponse) response;
//		resp.setHeader("Access-Control-Allow-Origin", "*");
//		resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//		resp.setHeader("Access-Control-Allow-Headers", "x-requested-with,jsessionid");
//		resp.setHeader("Access-Control-Expose-Headers", "x-requested-with,Content-Length,ETag,Last-Modified,Access-Control-Expose-Headers,Set-Cookie"); 
//		resp.setHeader("Access-Control-Allow-Credentials", "true");
//		
//	}
	
	
	@Override
	public void destroy() {

	}
	
	public void test(ServletRequest request, ServletResponse response, FilterChain chain) throws Exception{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		//获取请求的地址 将URI地址中的   /**/ 替换成   / 
		String uri = req.getRequestURI().trim().replaceAll("/{2,10}", "/"); 
		
		//获取项目路径
		String path = req.getContextPath();
		String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path+"/";
		
		request.setAttribute("basePath", basePath);
		request.setAttribute("rootUrl", Configure.getRootUrl());
		
		//处理跨域
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		resp.setHeader("Access-Control-Allow-Headers", "x-requested-with,jsessionid");
		resp.setHeader("Access-Control-Expose-Headers", "x-requested-with,Content-Length,ETag,Last-Modified,Access-Control-Expose-Headers,Set-Cookie"); 
		resp.setHeader("Access-Control-Allow-Credentials", "true"); 
		
		//1、如果为含（非root下发布 含有项目名在前面）/login,/doLogin,/loginOut,/loginCodeServlet直接放行
		for(String s : urlSet){
			if(uri.contains(s)){
				chain.doFilter(request, response);
				return;
			}
		}
		//在不是指定的特殊里面情况 做判断
		if(	uri.startsWith(path+"/api") 
			|| uri.startsWith(path+"/images") 
			|| uri.startsWith(path+"/js") 
			|| uri.startsWith(path+"/css") 
			|| uri.startsWith(path+"/resources")
			|| uri.startsWith(path+"/style")
			|| uri.startsWith(path+"/upload")
			|| uri.startsWith(path+"/swf")){//资源文件和api的直接放行  通过注解@AppLoginAuth判断接口是否登录访问
			chain.doFilter(request, response);
			return;
		}
		//管理员没登录 去登录页面
		if(req.getSession().getAttribute("adminLoginStats")!=null){
			
			chain.doFilter(request, response);
			return;
		}
		resp.sendRedirect(basePath+"login");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		//获取请求的地址 将URI地址中的   /**/ 替换成   / 
		String uri = req.getRequestURI().trim().replaceAll("/{2,10}", "/"); 
		
		//获取项目路径
		String path = req.getContextPath();
		String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path+"/";
		
		request.setAttribute("basePath", basePath);
		request.setAttribute("rootUrl", Configure.getRootUrl());
		
		//处理跨域
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		resp.setHeader("Access-Control-Allow-Headers", "x-requested-with,jsessionid");
		resp.setHeader("Access-Control-Expose-Headers", "x-requested-with,Content-Length,ETag,Last-Modified,Access-Control-Expose-Headers,Set-Cookie"); 
		resp.setHeader("Access-Control-Allow-Credentials", "true"); 
		
		//1、如果为含（非root下发布 含有项目名在前面）/login,/doLogin,/loginOut,/loginCodeServlet直接放行
		for(String s : urlSet){
			if(uri.contains(s)){
				chain.doFilter(request, response);
				return;
			}
		}
		//在不是指定的特殊里面情况 做判断
		if(	uri.startsWith(path+"/api") 
			|| uri.startsWith(path+"/images") 
			|| uri.startsWith(path+"/js") 
			|| uri.startsWith(path+"/css") 
			|| uri.startsWith(path+"/resources")
			|| uri.startsWith(path+"/attached")
			|| uri.startsWith(path+"/style")
			|| uri.startsWith(path+"/upload")
			|| uri.startsWith(path+"/swf")|| uri.startsWith(path+"/layout")){//资源文件和api的直接放行  通过注解@AppLoginAuth判断接口是否登录访问
			chain.doFilter(request, response);
			return;
		}
		//管理员没登录 去登录页面
		if(req.getSession().getAttribute("adminLoginStats")!=null){
			SessionInfo sessionInfo=(SessionInfo)req.getSession().getAttribute("sessionInfo");
			/*if(!sessionInfo.getResourceList().contains(uri)){*/
			if(!isContainsList(sessionInfo.getResourceList(), uri,path)){
				PrintWriter out = null;  
				response.setCharacterEncoding("UTF-8");  
				response.setContentType("application/json; charset=utf-8");  
				try {
					out = response.getWriter(); 
					String msg="<script type='text/javascript'>";
					msg+="$(function() {parent.$.messager.progress('close');})";
					msg+="</script>";
					msg+="您没有访问此资源的权限！<br/>请联系超管赋予您<br/>[" + uri + "]<br/>的资源访问权限！";
					out.write(msg);
				} catch (Exception e) {
					 e.printStackTrace();
				}finally{
					if (out != null) {  
			            out.close();  
			     }  
				}
				return;
			}else{
				chain.doFilter(request, response);
			}
			
			
			return;
		}else{
			resp.sendRedirect(basePath+"login");
		}
		
	}
	
	/**
	 * String 是否contains List中的一个值
	 * 遍历List 如果 str.contains(list中的值) 返回true
	 * return boolan
	 */
	private static boolean isContainsList(List<String> list ,String str,String path){
		if(null==list || list.size()==0){
			return false;
		}
		if(str.replace("/", "").equals(path.replace("/", ""))){
			return true;
		}
		for (String string : list) {
			if(string!=null){
				if(org.springframework.util.StringUtils.isEmpty(string)){
					continue;
				}
				if(str.contains(string)){
					return true;
				}
			}
			
		}
		return false;
	}
}
