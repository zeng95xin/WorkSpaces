package com.bola.nwcl.api.model.jpush;

public class JPushChatModel {
	private String type;
	private String title;
	private String content;
	private String sendUserId;
	private String url;

	public String getType()
	{
	  return this.type;
	}
	public void setType(String type) {
	  this.type = type;
	}
	public String getTitle() {
	  return this.title;
	}
	public void setTitle(String title) {
	  this.title = title;
	}
	public String getContent() {
	  return this.content;
	}
	public void setContent(String content) {
	  this.content = content;
	}

	public String getSendUserId() {
	  return this.sendUserId;
	}
	public void setSendUserId(String sendUserId) {
	  this.sendUserId = sendUserId;
	}
	public String getUrl() {
	  return this.url;
	}
	public void setUrl(String url) {
	  this.url = url;
	}
}
