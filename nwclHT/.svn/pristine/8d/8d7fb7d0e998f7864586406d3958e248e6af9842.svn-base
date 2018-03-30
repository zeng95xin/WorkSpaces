package com.bola.nwcl.api.model.keeper;

import org.springframework.data.domain.Page;

import com.bola.nwcl.api.model.RoomBuserModel;
import com.bola.nwcl.dal.mybatis.model.RoomQuestionnaire;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class RoomQuestionnaireWithUserInfoModel {
	
	private Page<RoomQuestionnaire> pageData;
	
	private RoomBuserModel roomBuserModel;

	@ApiModelProperty(value = "问卷列表")
	public Page<RoomQuestionnaire> getPageData() {
		return pageData;
	}

	@ApiModelProperty(value = "房间用户信息")
	public RoomBuserModel getRoomBuserModel() {
		return roomBuserModel;
	}

	public void setPageData(Page<RoomQuestionnaire> pageData) {
		this.pageData = pageData;
	}

	public void setRoomBuserModel(RoomBuserModel roomBuserModel) {
		this.roomBuserModel = roomBuserModel;
	}
	
	
}
