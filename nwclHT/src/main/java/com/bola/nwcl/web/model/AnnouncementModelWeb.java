package com.bola.nwcl.web.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class AnnouncementModelWeb implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String imgPath;
	@NotBlank(message = "标题不能为空")
	@Length(max = 200 , message = "标题长度不能超过200个字符" )
	private String title;
	@NotBlank(message = "内容不能为空")
	private String detail;
	private Integer isTop;
	private String recipient;
	@NotBlank(message = "简介不能为空")
	private String summary;
	private Long communtiyId;
	
	private Integer push;
	private String pushContent;
	
	
	
	public Integer getPush() {
		return push;
	}
	public void setPush(Integer push) {
		this.push = push;
	}
	public String getPushContent() {
		return pushContent;
	}
	public void setPushContent(String pushContent) {
		this.pushContent = pushContent;
	}
	public Integer getIsTop() {
		return isTop;
	}
	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}
	public Long getCommuntiyId() {
		return communtiyId;
	}
	public void setCommuntiyId(Long communtiyId) {
		this.communtiyId = communtiyId;
	}
	public String getDetail() {
		return detail;
	}
	public Long getId() {
		return id;
	}
	public String getImgPath() {
		return imgPath;
	}
	public String getRecipient() {
		return recipient;
	}
	public String getSummary() {
		return summary;
	}
	public String getTitle() {
		return title;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public void setTitle(String title) {
		this.title = title;
	}	
}
