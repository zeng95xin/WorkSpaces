package com.bola.nwcl.api.model.keeper;

import java.util.List;

import com.bola.nwcl.dal.mybatis.model.Question;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class QuestionModel extends Question{
	private List<QuestionItemAnswerModel> items;

	@ApiModelProperty(value = "问题选项")
	public List<QuestionItemAnswerModel> getItems() {
		return items;
	}

	public void setItems(List<QuestionItemAnswerModel> items) {
		this.items = items;
	}
}
