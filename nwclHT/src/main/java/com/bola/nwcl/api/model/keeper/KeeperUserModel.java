package com.bola.nwcl.api.model.keeper;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class KeeperUserModel{
	private Long id;
	private String name;
	private String nickname;
	private String headPortrait;
	private String sex;
	private String phone;
	private String detail;
	private int status;
	@ApiModelProperty(value = "管家id")
	public Long getId() {
		return id;
	}
	@ApiModelProperty(value = "姓名")
	public String getName() {
		return name;
	}
	@ApiModelProperty(value = "昵称")
	public String getNickname() {
		return nickname;
	}
	@ApiModelProperty(value = "头像")
	public String getHeadPortrait() {
		return headPortrait;
	}
	@ApiModelProperty(value = "性别")
	public String getSex() {
		return sex;
	}
	@ApiModelProperty(value = "手机")
	public String getPhone() {
		return phone;
	}
	@ApiModelProperty(value = "详情介绍")
	public String getDetail() {
		return detail;
	}
	@ApiModelProperty(value = "状态，0下班了，1值班中，2离职")
	public int getStatus() {
		return status;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
