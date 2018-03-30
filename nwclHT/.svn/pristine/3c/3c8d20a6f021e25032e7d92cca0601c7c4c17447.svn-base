package com.bola.nwcl.api.model.keeper;

import com.bola.nwcl.dal.mybatis.model.QuestionItem;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class QuestionItemAnswerModel extends QuestionItem{
	
	private String answer;

	@ApiModelProperty(value = "问题答案,如果是选中题,并且选中,则是1,没有选中为0,填空题则是对应答案")
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}
