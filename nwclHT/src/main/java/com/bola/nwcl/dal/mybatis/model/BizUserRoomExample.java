package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.ModelExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BizUserRoomExample extends ModelExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table n_biz_user_room
	 * @mbggenerated
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table n_biz_user_room
	 * @mbggenerated
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table n_biz_user_room
	 * @mbggenerated
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_biz_user_room
	 * @mbggenerated
	 */
	public BizUserRoomExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_biz_user_room
	 * @mbggenerated
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_biz_user_room
	 * @mbggenerated
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_biz_user_room
	 * @mbggenerated
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_biz_user_room
	 * @mbggenerated
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_biz_user_room
	 * @mbggenerated
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_biz_user_room
	 * @mbggenerated
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_biz_user_room
	 * @mbggenerated
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_biz_user_room
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
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_biz_user_room
	 * @mbggenerated
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_biz_user_room
	 * @mbggenerated
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_biz_user_room
	 * @mbggenerated
	 */
	public Criteria getCriteria() {
		if (oredCriteria.size() == 0) {
			createCriteria();
		}
		return oredCriteria.get(0);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_biz_user_room
	 * @mbggenerated
	 */
	public void addCriteria(List<Criterion> criteria) {
		getCriteria().getCriteria().addAll(criteria);
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table n_biz_user_room
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

		public Criteria andBizUserIdIsNull() {
			addCriterion("biz_user_id is null");
			return (Criteria) this;
		}

		public Criteria andBizUserIdIsNotNull() {
			addCriterion("biz_user_id is not null");
			return (Criteria) this;
		}

		public Criteria andBizUserIdEqualTo(Long value) {
			addCriterion("biz_user_id =", value, "bizUserId");
			return (Criteria) this;
		}

		public Criteria andBizUserIdNotEqualTo(Long value) {
			addCriterion("biz_user_id <>", value, "bizUserId");
			return (Criteria) this;
		}

		public Criteria andBizUserIdGreaterThan(Long value) {
			addCriterion("biz_user_id >", value, "bizUserId");
			return (Criteria) this;
		}

		public Criteria andBizUserIdGreaterThanOrEqualTo(Long value) {
			addCriterion("biz_user_id >=", value, "bizUserId");
			return (Criteria) this;
		}

		public Criteria andBizUserIdLessThan(Long value) {
			addCriterion("biz_user_id <", value, "bizUserId");
			return (Criteria) this;
		}

		public Criteria andBizUserIdLessThanOrEqualTo(Long value) {
			addCriterion("biz_user_id <=", value, "bizUserId");
			return (Criteria) this;
		}

		public Criteria andBizUserIdIn(List<Long> values) {
			addCriterion("biz_user_id in", values, "bizUserId");
			return (Criteria) this;
		}

		public Criteria andBizUserIdNotIn(List<Long> values) {
			addCriterion("biz_user_id not in", values, "bizUserId");
			return (Criteria) this;
		}

		public Criteria andBizUserIdBetween(Long value1, Long value2) {
			addCriterion("biz_user_id between", value1, value2, "bizUserId");
			return (Criteria) this;
		}

		public Criteria andBizUserIdNotBetween(Long value1, Long value2) {
			addCriterion("biz_user_id not between", value1, value2, "bizUserId");
			return (Criteria) this;
		}

		public Criteria andAuthorizeUserIdIsNull() {
			addCriterion("authorize_user_id is null");
			return (Criteria) this;
		}

		public Criteria andAuthorizeUserIdIsNotNull() {
			addCriterion("authorize_user_id is not null");
			return (Criteria) this;
		}

		public Criteria andAuthorizeUserIdEqualTo(Long value) {
			addCriterion("authorize_user_id =", value, "authorizeUserId");
			return (Criteria) this;
		}

		public Criteria andAuthorizeUserIdNotEqualTo(Long value) {
			addCriterion("authorize_user_id <>", value, "authorizeUserId");
			return (Criteria) this;
		}

		public Criteria andAuthorizeUserIdGreaterThan(Long value) {
			addCriterion("authorize_user_id >", value, "authorizeUserId");
			return (Criteria) this;
		}

		public Criteria andAuthorizeUserIdGreaterThanOrEqualTo(Long value) {
			addCriterion("authorize_user_id >=", value, "authorizeUserId");
			return (Criteria) this;
		}

		public Criteria andAuthorizeUserIdLessThan(Long value) {
			addCriterion("authorize_user_id <", value, "authorizeUserId");
			return (Criteria) this;
		}

		public Criteria andAuthorizeUserIdLessThanOrEqualTo(Long value) {
			addCriterion("authorize_user_id <=", value, "authorizeUserId");
			return (Criteria) this;
		}

		public Criteria andAuthorizeUserIdIn(List<Long> values) {
			addCriterion("authorize_user_id in", values, "authorizeUserId");
			return (Criteria) this;
		}

		public Criteria andAuthorizeUserIdNotIn(List<Long> values) {
			addCriterion("authorize_user_id not in", values, "authorizeUserId");
			return (Criteria) this;
		}

		public Criteria andAuthorizeUserIdBetween(Long value1, Long value2) {
			addCriterion("authorize_user_id between", value1, value2,
					"authorizeUserId");
			return (Criteria) this;
		}

		public Criteria andAuthorizeUserIdNotBetween(Long value1, Long value2) {
			addCriterion("authorize_user_id not between", value1, value2,
					"authorizeUserId");
			return (Criteria) this;
		}

		public Criteria andRoomIdIsNull() {
			addCriterion("room_id is null");
			return (Criteria) this;
		}

		public Criteria andRoomIdIsNotNull() {
			addCriterion("room_id is not null");
			return (Criteria) this;
		}

		public Criteria andRoomIdEqualTo(Long value) {
			addCriterion("room_id =", value, "roomId");
			return (Criteria) this;
		}

		public Criteria andRoomIdNotEqualTo(Long value) {
			addCriterion("room_id <>", value, "roomId");
			return (Criteria) this;
		}

		public Criteria andRoomIdGreaterThan(Long value) {
			addCriterion("room_id >", value, "roomId");
			return (Criteria) this;
		}

		public Criteria andRoomIdGreaterThanOrEqualTo(Long value) {
			addCriterion("room_id >=", value, "roomId");
			return (Criteria) this;
		}

		public Criteria andRoomIdLessThan(Long value) {
			addCriterion("room_id <", value, "roomId");
			return (Criteria) this;
		}

		public Criteria andRoomIdLessThanOrEqualTo(Long value) {
			addCriterion("room_id <=", value, "roomId");
			return (Criteria) this;
		}

		public Criteria andRoomIdIn(List<Long> values) {
			addCriterion("room_id in", values, "roomId");
			return (Criteria) this;
		}

		public Criteria andRoomIdNotIn(List<Long> values) {
			addCriterion("room_id not in", values, "roomId");
			return (Criteria) this;
		}

		public Criteria andRoomIdBetween(Long value1, Long value2) {
			addCriterion("room_id between", value1, value2, "roomId");
			return (Criteria) this;
		}

		public Criteria andRoomIdNotBetween(Long value1, Long value2) {
			addCriterion("room_id not between", value1, value2, "roomId");
			return (Criteria) this;
		}

		public Criteria andTypeIsNull() {
			addCriterion("type is null");
			return (Criteria) this;
		}

		public Criteria andTypeIsNotNull() {
			addCriterion("type is not null");
			return (Criteria) this;
		}

		public Criteria andTypeEqualTo(String value) {
			addCriterion("type =", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotEqualTo(String value) {
			addCriterion("type <>", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThan(String value) {
			addCriterion("type >", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThanOrEqualTo(String value) {
			addCriterion("type >=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThan(String value) {
			addCriterion("type <", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThanOrEqualTo(String value) {
			addCriterion("type <=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLike(String value) {
			addCriterion("type like", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotLike(String value) {
			addCriterion("type not like", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeIn(List<String> values) {
			addCriterion("type in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotIn(List<String> values) {
			addCriterion("type not in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeBetween(String value1, String value2) {
			addCriterion("type between", value1, value2, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotBetween(String value1, String value2) {
			addCriterion("type not between", value1, value2, "type");
			return (Criteria) this;
		}

		public Criteria andStatusIsNull() {
			addCriterion("status is null");
			return (Criteria) this;
		}

		public Criteria andStatusIsNotNull() {
			addCriterion("status is not null");
			return (Criteria) this;
		}

		public Criteria andStatusEqualTo(Integer value) {
			addCriterion("status =", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotEqualTo(Integer value) {
			addCriterion("status <>", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThan(Integer value) {
			addCriterion("status >", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
			addCriterion("status >=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThan(Integer value) {
			addCriterion("status <", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThanOrEqualTo(Integer value) {
			addCriterion("status <=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusIn(List<Integer> values) {
			addCriterion("status in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotIn(List<Integer> values) {
			addCriterion("status not in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusBetween(Integer value1, Integer value2) {
			addCriterion("status between", value1, value2, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotBetween(Integer value1, Integer value2) {
			addCriterion("status not between", value1, value2, "status");
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
     * This class corresponds to the database table n_biz_user_room
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}