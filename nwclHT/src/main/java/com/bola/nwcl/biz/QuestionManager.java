package com.bola.nwcl.biz;

import javax.servlet.http.HttpServletRequest;

import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.mybatis.model.Question;
import com.bola.nwcl.dal.mybatis.model.QuestionExample;

public interface QuestionManager extends Manager<Question, QuestionExample, Long>{
	void insertGenId(Question question)throws Exception;
	void add(HttpServletRequest request,Integer type,String title,String []content,String contentsss,Long id)throws Exception;
	void edit(HttpServletRequest request,Integer type,String title,String []content,String contentsss,Long id)throws Exception; 
}
