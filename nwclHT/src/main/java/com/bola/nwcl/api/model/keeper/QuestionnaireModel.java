package com.bola.nwcl.api.model.keeper;

import java.util.List;

import com.bola.nwcl.dal.mybatis.model.Questionnaire;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class QuestionnaireModel extends Questionnaire{
	
	private List<QuestionModel> questions;

	@ApiModelProperty(value = "问卷问题")
	public List<QuestionModel> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionModel> questions) {
		this.questions = questions;
	}

}
