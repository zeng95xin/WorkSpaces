package com.bola.nwcl.biz;

import java.util.List;

import com.bola.nwcl.api.model.RoomDetailModel;
import com.bola.nwcl.api.model.keeper.RoomQuestionnaireModel;
import com.bola.nwcl.api.model.keeper.RoomQuestionnaireWithUserInfoModel;

public interface KeeperQuestionnaireManager {
	List<RoomDetailModel> searchRoom(String keyword);
	
	RoomQuestionnaireWithUserInfoModel getRoomQuestionnaire(long id, int page, int rows);
	
	RoomQuestionnaireModel getRoomQuestionnaireDetail(long id);
	
	void addRoomQuestionnaire(Long questionnaireId, Long roomId, String answer);
}
