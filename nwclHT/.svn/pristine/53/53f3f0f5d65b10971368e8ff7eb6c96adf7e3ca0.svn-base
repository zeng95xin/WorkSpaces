package com.bola.nwcl.web.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SessionInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;// 用户ID
	private String name;// 用户登录名
	private String ip;// 用户IP
	
	private Date loginTime = new Date();// 用户IP

	private List<String> resourceList;// 用户可以访问的资源地址列表

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}   

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public List<String> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<String> resourceList) {
		this.resourceList = resourceList;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
}
