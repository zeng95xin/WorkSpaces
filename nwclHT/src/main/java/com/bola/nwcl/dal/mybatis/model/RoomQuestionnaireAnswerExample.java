package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.ModelExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoomQuestionnaireAnswerExample extends ModelExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table n_room_questionnaire_answer
	 * @mbggenerated
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table n_room_questionnaire_answer
	 * @mbggenerated
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table n_room_questionnaire_answer
	 * @mbggenerated
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_room_questionnaire_answer
	 * @mbggenerated
	 */
	public RoomQuestionnaireAnswerExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_room_questionnaire_answer
	 * @mbggenerated
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_room_questionnaire_answer
	 * @mbggenerated
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_room_questionnaire_answer
	 * @mbggenerated
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_room_questionnaire_answer
	 * @mbggenerated
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_room_questionnaire_answer
	 * @mbggenerated
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_room_questionnaire_answer
	 * @mbggenerated
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_room_questionnaire_answer
	 * @mbggenerated
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_room_questionnaire_answer
	 * @mbggenerated
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_room_questionnaire_answer
	 * @mbggenerated
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_room_questionnaire_answer
	 * @mbggenerated
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_room_questionnaire_answer
	 * @mbggenerated
	 */
	public Criteria getCriteria() {
		if (oredCriteria.size() == 0) {
			createCriteria();
		}
		return oredCriteria.get(0);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_room_questionnaire_answer
	 * @mbggenerated
	 */
	public void addCriteria(List<Criterion> criteria) {
		getCriteria().getCriteria().addAll(criteria);
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table n_room_questionnaire_answer
	 * @mbggenerated
	 */
	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;
		private boolean ignoreNull;

		private boolean isIgnoreNull() {
			return ignoreNull;
		}

		public Criteria ignoreNull() {
			this.ignoreNull = true;
			return (Criteria) this;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				if (!isIgnoreNull()) {
					throw new RuntimeException(
							"Value for condition cannot be null");
				}
			} else {
				criteria.add(new Criterion(condition));
			}
		}

		protected void addCriterion(String condition, Object value,
				String property) {
			if (value == null) {
				if (!isIgnoreNull()) {
					throw new RuntimeException("Value for " + property
							+ " cannot be null");
				}
			} else {
				criteria.add(new Criterion(condition, value));
			}
		}

		protected void addCriterion(String condition, Object value1,
				Object value2, String property) {
			if (value1 == null || value2 == null) {
				if (!isIgnoreNull()) {
					throw new RuntimeException("Between values for " + property
							+ " cannot be null");
				}
			} else {
				criteria.add(new Criterion(condition, value1, value2));
			}
		}

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		public Criteria andIdIsNull() {
			addCriterion("id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Long value) {
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Long value) {
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Long value) {
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Long value) {
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Long value) {
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Long value) {
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Long> values) {
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Long> values) {
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Long value1, Long value2) {
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Long value1, Long value2) {
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andRoomQuestionnaireIdIsNull() {
			addCriterion("room_questionnaire_id is null");
			return (Criteria) this;
		}

		public Criteria andRoomQuestionnaireIdIsNotNull() {
			addCriterion("room_questionnaire_id is not null");
			return (Criteria) this;
		}

		public Criteria andRoomQuestionnaireIdEqualTo(Long value) {
			addCriterion("room_questionnaire_id =", value,
					"roomQuestionnaireId");
			return (Criteria) this;
		}

		public Criteria andRoomQuestionnaireIdNotEqualTo(Long value) {
			addCriterion("room_questionnaire_id <>", value,
					"roomQuestionnaireId");
			return (Criteria) this;
		}

		public Criteria andRoomQuestionnaireIdGreaterThan(Long value) {
			addCriterion("room_questionnaire_id >", value,
					"roomQuestionnaireId");
			return (Criteria) this;
		}

		public Criteria andRoomQuestionnaireIdGreaterThanOrEqualTo(Long value) {
			addCriterion("room_questionnaire_id >=", value,
					"roomQuestionnaireId");
			return (Criteria) this;
		}

		public Criteria andRoomQuestionnaireIdLessThan(Long value) {
			addCriterion("room_questionnaire_id <", value,
					"roomQuestionnaireId");
			return (Criteria) this;
		}

		public Criteria andRoomQuestionnaireIdLessThanOrEqualTo(Long value) {
			addCriterion("room_questionnaire_id <=", value,
					"roomQuestionnaireId");
			return (Criteria) this;
		}

		public Criteria andRoomQuestionnaireIdIn(List<Long> values) {
			addCriterion("room_questionnaire_id in", values,
					"roomQuestionnaireId");
			return (Criteria) this;
		}

		public Criteria andRoomQuestionnaireIdNotIn(List<Long> values) {
			addCriterion("room_questionnaire_id not in", values,
					"roomQuestionnaireId");
			return (Criteria) this;
		}

		public Criteria andRoomQuestionnaireIdBetween(Long value1, Long value2) {
			addCriterion("room_questionnaire_id between", value1, value2,
					"roomQuestionnaireId");
			return (Criteria) this;
		}

		public Criteria andRoomQuestionnaireIdNotBetween(Long value1,
				Long value2) {
			addCriterion("room_questionnaire_id not between", value1, value2,
					"roomQuestionnaireId");
			return (Criteria) this;
		}

		public Criteria andQuestionIdIsNull() {
			addCriterion("question_id is null");
			return (Criteria) this;
		}

		public Criteria andQuestionIdIsNotNull() {
			addCriterion("question_id is not null");
			return (Criteria) this;
		}

		public Criteria andQuestionIdEqualTo(Long value) {
			addCriterion("question_id =", value, "questionId");
			return (Criteria) this;
		}

		public Criteria andQuestionIdNotEqualTo(Long value) {
			addCriterion("question_id <>", value, "questionId");
			return (Criteria) this;
		}

		public Criteria andQuestionIdGreaterThan(Long value) {
			addCriterion("question_id >", value, "questionId");
			return (Criteria) this;
		}

		public Criteria andQuestionIdGreaterThanOrEqualTo(Long value) {
			addCriterion("question_id >=", value, "questionId");
			return (Criteria) this;
		}

		public Criteria andQuestionIdLessThan(Long value) {
			addCriterion("question_id <", value, "questionId");
			return (Criteria) this;
		}

		public Criteria andQuestionIdLessThanOrEqualTo(Long value) {
			addCriterion("question_id <=", value, "questionId");
			return (Criteria) this;
		}

		public Criteria andQuestionIdIn(List<Long> values) {
			addCriterion("question_id in", values, "questionId");
			return (Criteria) this;
		}

		public Criteria andQuestionIdNotIn(List<Long> values) {
			addCriterion("question_id not in", values, "questionId");
			return (Criteria) this;
		}

		public Criteria andQuestionIdBetween(Long value1, Long value2) {
			addCriterion("question_id between", value1, value2, "questionId");
			return (Criteria) this;
		}

		public Criteria andQuestionIdNotBetween(Long value1, Long value2) {
			addCriterion("question_id not between", value1, value2,
					"questionId");
			return (Criteria) this;
		}

		public Criteria andAnswerIsNull() {
			addCriterion("answer is null");
			return (Criteria) this;
		}

		public Criteria andAnswerIsNotNull() {
			addCriterion("answer is not null");
			return (Criteria) this;
		}

		public Criteria andAnswerEqualTo(String value) {
			addCriterion("answer =", value, "answer");
			return (Criteria) this;
		}

		public Criteria andAnswerNotEqualTo(String value) {
			addCriterion("answer <>", value, "answer");
			return (Criteria) this;
		}

		public Criteria andAnswerGreaterThan(String value) {
			addCriterion("answer >", value, "answer");
			return (Criteria) this;
		}

		public Criteria andAnswerGreaterThanOrEqualTo(String value) {
			addCriterion("answer >=", value, "answer");
			return (Criteria) this;
		}

		public Criteria andAnswerLessThan(String value) {
			addCriterion("answer <", value, "answer");
			return (Criteria) this;
		}

		public Criteria andAnswerLessThanOrEqualTo(String value) {
			addCriterion("answer <=", value, "answer");
			return (Criteria) this;
		}

		public Criteria andAnswerLike(String value) {
			addCriterion("answer like", value, "answer");
			return (Criteria) this;
		}

		public Criteria andAnswerNotLike(String value) {
			addCriterion("answer not like", value, "answer");
			return (Criteria) this;
		}

		public Criteria andAnswerIn(List<String> values) {
			addCriterion("answer in", values, "answer");
			return (Criteria) this;
		}

		public Criteria andAnswerNotIn(List<String> values) {
			addCriterion("answer not in", values, "answer");
			return (Criteria) this;
		}

		public Criteria andAnswerBetween(String value1, String value2) {
			addCriterion("answer between", value1, value2, "answer");
			return (Criteria) this;
		}

		public Criteria andAnswerNotBetween(String value1, String value2) {
			addCriterion("answer not between", value1, value2, "answer");
			return (Criteria) this;
		}

		public Criteria andRowAddTimeIsNull() {
			addCriterion("row_add_time is null");
			return (Criteria) this;
		}

		public Criteria andRowAddTimeIsNotNull() {
			addCriterion("row_add_time is not null");
			return (Criteria) this;
		}

		public Criteria andRowAddTimeEqualTo(Date value) {
			addCriterion("row_add_time =", value, "rowAddTime");
			return (Criteria) this;
		}

		public Criteria andRowAddTimeNotEqualTo(Date value) {
			addCriterion("row_add_time <>", value, "rowAddTime");
			return (Criteria) this;
		}

		public Criteria andRowAddTimeGreaterThan(Date value) {
			addCriterion("row_add_time >", value, "rowAddTime");
			return (Criteria) this;
		}

		public Criteria andRowAddTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("row_add_time >=", value, "rowAddTime");
			return (Criteria) this;
		}

		public Criteria andRowAddTimeLessThan(Date value) {
			addCriterion("row_add_time <", value, "rowAddTime");
			return (Criteria) this;
		}

		public Criteria andRowAddTimeLessThanOrEqualTo(Date value) {
			addCriterion("row_add_time <=", value, "rowAddTime");
			return (Criteria) this;
		}

		public Criteria andRowAddTimeIn(List<Date> values) {
			addCriterion("row_add_time in", values, "rowAddTime");
			return (Criteria) this;
		}

		public Criteria andRowAddTimeNotIn(List<Date> values) {
			addCriterion("row_add_time not in", values, "rowAddTime");
			return (Criteria) this;
		}

		public Criteria andRowAddTimeBetween(Date value1, Date value2) {
			addCriterion("row_add_time between", value1, value2, "rowAddTime");
			return (Criteria) this;
		}

		public Criteria andRowAddTimeNotBetween(Date value1, Date value2) {
			addCriterion("row_add_time not between", value1, value2,
					"rowAddTime");
			return (Criteria) this;
		}

		public Criteria andRowUpdateTimeIsNull() {
			addCriterion("row_update_time is null");
			return (Criteria) this;
		}

		public Criteria andRowUpdateTimeIsNotNull() {
			addCriterion("row_update_time is not null");
			return (Criteria) this;
		}

		public Criteria andRowUpdateTimeEqualTo(Date value) {
			addCriterion("row_update_time =", value, "rowUpdateTime");
			return (Criteria) this;
		}

		public Criteria andRowUpdateTimeNotEqualTo(Date value) {
			addCriterion("row_update_time <>", value, "rowUpdateTime");
			return (Criteria) this;
		}

		public Criteria andRowUpdateTimeGreaterThan(Date value) {
			addCriterion("row_update_time >", value, "rowUpdateTime");
			return (Criteria) this;
		}

		public Criteria andRowUpdateTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("row_update_time >=", value, "rowUpdateTime");
			return (Criteria) this;
		}

		public Criteria andRowUpdateTimeLessThan(Date value) {
			addCriterion("row_update_time <", value, "rowUpdateTime");
			return (Criteria) this;
		}

		public Criteria andRowUpdateTimeLessThanOrEqualTo(Date value) {
			addCriterion("row_update_time <=", value, "rowUpdateTime");
			return (Criteria) this;
		}

		public Criteria andRowUpdateTimeIn(List<Date> values) {
			addCriterion("row_update_time in", values, "rowUpdateTime");
			return (Criteria) this;
		}

		public Criteria andRowUpdateTimeNotIn(List<Date> values) {
			addCriterion("row_update_time not in", values, "rowUpdateTime");
			return (Criteria) this;
		}

		public Criteria andRowUpdateTimeBetween(Date value1, Date value2) {
			addCriterion("row_update_time between", value1, value2,
					"rowUpdateTime");
			return (Criteria) this;
		}

		public Criteria andRowUpdateTimeNotBetween(Date value1, Date value2) {
			addCriterion("row_update_time not between", value1, value2,
					"rowUpdateTime");
			return (Criteria) this;
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table n_room_questionnaire_answer
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}