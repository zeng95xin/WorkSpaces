package com.bola.nwcl.api.model.keeper;

import java.util.List;

import com.bola.nwcl.api.model.BorrowThingModel;
import com.bola.nwcl.dal.mybatis.model.Borrow;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class KeeperBorrowModel extends Borrow{
	
	private String identity;
	private String roomStr;
	private String nickname;
	private String headPortrait;
	private List<BorrowThingModel> borrowThings;
	
	@ApiModelProperty(value = "身份")
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	@ApiModelProperty(value = "房号")
	public String getRoomStr() {
		return roomStr;
	}
	public void setRoomStr(String roomStr) {
		this.roomStr = roomStr;
	}
	@ApiModelProperty(value = "物品清单")
	public List<BorrowThingModel> getBorrowThings() {
		return borrowThings;
	}
	public void setThings(List<BorrowThingModel> borrowThings) {
		this.borrowThings = borrowThings;
	}
	@ApiModelProperty(value = "借用人姓名")
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public void setBorrowThings(List<BorrowThingModel> borrowThings) {
		this.borrowThings = borrowThings;
	}
	@ApiModelProperty(value = "借用人头像")
	public String getHeadPortrait() {
		return headPortrait;
	}
	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}
	
}
