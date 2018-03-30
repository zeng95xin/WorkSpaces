package com.bola.nwcl.biz.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bola.nwcl.biz.QuestionItemManager;
import com.bola.nwcl.biz.QuestionManager;
import com.bola.nwcl.biz.QuestionnaireManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.mybatis.mapper.QuestionMapper;
import com.bola.nwcl.dal.mybatis.model.Question;
import com.bola.nwcl.dal.mybatis.model.QuestionExample;
import com.bola.nwcl.dal.mybatis.model.QuestionItem;
import com.bola.nwcl.dal.mybatis.model.QuestionItemExample;

@Service
public class QuestionManagerImpl  extends ManagerImpl<Question, QuestionExample, Long> implements QuestionManager{

	@SuppressWarnings("unused")
	private QuestionMapper questionMapper;
	@Autowired QuestionnaireManager questionnaireManager;
	@Autowired QuestionItemManager questionItemManager;
	
	@Autowired
	public QuestionManagerImpl(QuestionMapper questionMapper) {
		this.mapper = questionMapper;
		this.questionMapper = questionMapper;
	}

	@Override
	public void insertGenId(Question question) throws Exception {
		questionMapper.insertGenId(question);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void add(HttpServletRequest request, Integer type, String title,String[] content, String contentsss, Long id) throws Exception {
		Question model=new Question();
		model.setContent(title);
		model.setType(type);
		model.setQuestionnaireId(id);
		questionMapper.insertGenId(model);
		if (type==3) {
			QuestionItem questionItem=new QuestionItem();
			questionItem.setQuestionId(model.getId());
			questionItem.setContent(contentsss.trim());
			questionItemManager.insert(questionItem);
		}else{
			for (int i = 0; i < content.length; i++) {
				QuestionItem questionItem=new QuestionItem();
				questionItem.setQuestionId(model.getId());
				questionItem.setContent(content[i].trim());
				questionItemManager.insert(questionItem);
			}
		}

	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void edit(HttpServletRequest request, Integer type, String title,
			String[] content, String contentsss, Long id) throws Exception {
		Question model=new Question();
		model.setContent(title);
		model.setType(type);
		model.setId(id);
		questionMapper.updateByPrimaryKeySelective(model);
		if (type==3) {
			QuestionItemExample example=new QuestionItemExample();
			example.or().andQuestionIdEqualTo(id);
			questionItemManager.deleteByExample(example);

			QuestionItem questionItem=new QuestionItem();
			questionItem.setQuestionId(id);
			questionItem.setContent(contentsss.trim());
			questionItemManager.insert(questionItem);
		}else{
			for (int i = 0; i < content.length; i++) {
				QuestionItemExample example=new QuestionItemExample();
				example.or().andQuestionIdEqualTo(id);
				questionItemManager.deleteByExample(example);
				QuestionItem questionItem=new QuestionItem();
				questionItem.setQuestionId(model.getId());
				questionItem.setContent(content[i].trim());
				questionItemManager.insert(questionItem);
			}
		}

	}

}
