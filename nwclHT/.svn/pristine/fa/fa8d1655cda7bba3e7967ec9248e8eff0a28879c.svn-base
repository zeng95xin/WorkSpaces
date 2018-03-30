package com.bola.nwcl.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bola.nwcl.api.model.RoomBuserModel;
import com.bola.nwcl.api.model.RoomDetailModel;
import com.bola.nwcl.api.model.keeper.QuestionItemAnswerModel;
import com.bola.nwcl.api.model.keeper.QuestionModel;
import com.bola.nwcl.api.model.keeper.RoomQuestionnaireModel;
import com.bola.nwcl.api.model.keeper.RoomQuestionnaireWithUserInfoModel;
import com.bola.nwcl.biz.KeeperQuestionnaireManager;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.dal.mybatis.mapper.BizUserRoomMapper;
import com.bola.nwcl.dal.mybatis.mapper.QuestionItemMapper;
import com.bola.nwcl.dal.mybatis.mapper.QuestionMapper;
import com.bola.nwcl.dal.mybatis.mapper.QuestionnaireMapper;
import com.bola.nwcl.dal.mybatis.mapper.RoomMapper;
import com.bola.nwcl.dal.mybatis.mapper.RoomQuestionnaireAnswerMapper;
import com.bola.nwcl.dal.mybatis.mapper.RoomQuestionnaireMapper;
import com.bola.nwcl.dal.mybatis.model.Question;
import com.bola.nwcl.dal.mybatis.model.QuestionExample;
import com.bola.nwcl.dal.mybatis.model.Questionnaire;
import com.bola.nwcl.dal.mybatis.model.RoomQuestionnaire;
import com.bola.nwcl.dal.mybatis.model.RoomQuestionnaireAnswer;
import com.bola.nwcl.dal.mybatis.model.RoomQuestionnaireExample;

@Service
public class KeeperQuestionnaireManagerImpl implements KeeperQuestionnaireManager {
	
	@Autowired
	private RoomMapper roomMapper;
	
	@Autowired
	private BizUserRoomMapper bizUserRoomMapper;
	
	@Autowired
	private RoomQuestionnaireMapper roomQuestionnaireMapper;
	
	@Autowired
	private QuestionnaireMapper questionnaireMapper;
	
	@Autowired
	private QuestionMapper questionMapper;
	
	@Autowired
	private QuestionItemMapper questionItemMapper;
	
	@Autowired
	private RoomQuestionnaireAnswerMapper roomQuestionnaireAnswerMapper;
	
	@Override
	public List<RoomDetailModel> searchRoom(String keyword) {
		Map<String, Object> condition = new HashMap<>();
		condition.put("keyword", keyword);
		return roomMapper.searchRoom(condition);
	}

	@Override
	public RoomQuestionnaireWithUserInfoModel getRoomQuestionnaire(long id, int page, int rows) {
		
		PageRequest pager = new PageRequest(page - 1, rows);
		
		RoomQuestionnaireExample example = new RoomQuestionnaireExample();
		example.or().andRoomIdEqualTo(id);
		
		int count = roomQuestionnaireMapper.countByExample(example);
		
		example.setLimit(rows);
		example.setOffset(pager.getOffset());
		List<RoomQuestionnaire> list = roomQuestionnaireMapper.selectByExample(example);
		
		
		Page<RoomQuestionnaire> pageData = new PageImpl<RoomQuestionnaire>(list, pager, count);
		
		RoomQuestionnaireWithUserInfoModel model = new RoomQuestionnaireWithUserInfoModel();
		
		model.setPageData(pageData);
		Map<String,Object> map = new HashMap<String, Object>(2);
		map.put("roomId", id);
		map.put("type", "业主");
		List<RoomBuserModel> list2 = bizUserRoomMapper.getAllRoomUser(map);
		RoomBuserModel roomBuserModel = list2==null||list.size()<1 ? null : list2.get(0);
		model.setRoomBuserModel(roomBuserModel);;
		
		return model;
	}

	@Override
	public RoomQuestionnaireModel getRoomQuestionnaireDetail(long id) {
		
		RoomQuestionnaire r = roomQuestionnaireMapper.selectByPrimaryKey(id);
		if(r == null){
			throw new BusinessValidateException("该问卷调查不存在");
		}
		
		RoomQuestionnaireModel m = new RoomQuestionnaireModel();
		BeanUtils.copyProperties(r, m);
		
		Questionnaire qe = questionnaireMapper.selectByPrimaryKey(r.getQuestionnaireId());
		m.setTitle(qe.getTitle());
		
		QuestionExample example_question = new QuestionExample();
		example_question.or().andQuestionnaireIdEqualTo(m.getQuestionnaireId());
		List<Question> questions = questionMapper.selectByExample(example_question);
		
		List<QuestionModel> list_questionModel = new ArrayList<QuestionModel>(questions.size());
		
		for(Question q : questions){
			QuestionModel qm = new QuestionModel();
			BeanUtils.copyProperties(q, qm);
			
			List<QuestionItemAnswerModel> items = roomQuestionnaireAnswerMapper.getAnswerByQuestion(q.getId());
			
			qm.setItems(items);
			
		}
		
		m.setQuestions(list_questionModel);
		
		return m;
	}

	@Override
	public void addRoomQuestionnaire(Long questionnaireId, Long roomId, String answer) {
		RoomQuestionnaire rq = new RoomQuestionnaire();
		rq.setRoomId(roomId);
		rq.setQuestionnaireId(questionnaireId);
		roomQuestionnaireMapper.insertAndGetId(rq);
		
		String[] ans = answer.split(";");
		for(String str : ans){
			String[] temp = str.split(":");
			long questionId = Long.valueOf(temp[0]);
			RoomQuestionnaireAnswer rqa = new RoomQuestionnaireAnswer();
			rqa.setAnswer(temp[1]);
			rqa.setQuestionId(questionId);
			rqa.setRoomQuestionnaireId(rq.getId());
			roomQuestionnaireAnswerMapper.insert(rqa);
		}
		
	}

}
