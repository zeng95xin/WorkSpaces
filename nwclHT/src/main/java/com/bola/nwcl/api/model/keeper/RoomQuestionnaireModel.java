package com.bola.nwcl.api.model.keeper;

import java.util.List;

import com.bola.nwcl.dal.mybatis.model.RoomQuestionnaire;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class RoomQuestionnaireModel extends RoomQuestionnaire{
	
	private String title;
	private List<QuestionModel> questions;
	
	@ApiModelProperty(value = "问卷标题")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@ApiModelProperty(value = "问卷问题")
	public List<QuestionModel> getQuestions() {
		return questions;
	}
	public void setQuestions(List<QuestionModel> questions) {
		this.questions = questions;
	}
	
	
}
