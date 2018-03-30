package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.ModelExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VisitorsExample extends ModelExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table n_visitors
	 * @mbggenerated
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table n_visitors
	 * @mbggenerated
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table n_visitors
	 * @mbggenerated
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_visitors
	 * @mbggenerated
	 */
	public VisitorsExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_visitors
	 * @mbggenerated
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_visitors
	 * @mbggenerated
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_visitors
	 * @mbggenerated
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_visitors
	 * @mbggenerated
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_visitors
	 * @mbggenerated
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_visitors
	 * @mbggenerated
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_visitors
	 * @mbggenerated
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_visitors
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
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_visitors
	 * @mbggenerated
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_visitors
	 * @mbggenerated
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_visitors
	 * @mbggenerated
	 */
	public Criteria getCriteria() {
		if (oredCriteria.size() == 0) {
			createCriteria();
		}
		return oredCriteria.get(0);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_visitors
	 * @mbggenerated
	 */
	public void addCriteria(List<Criterion> criteria) {
		getCriteria().getCriteria().addAll(criteria);
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table n_visitors
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

		public Criteria andSerialIsNull() {
			addCriterion("serial is null");
			return (Criteria) this;
		}

		public Criteria andSerialIsNotNull() {
			addCriterion("serial is not null");
			return (Criteria) this;
		}

		public Criteria andSerialEqualTo(String value) {
			addCriterion("serial =", value, "serial");
			return (Criteria) this;
		}

		public Criteria andSerialNotEqualTo(String value) {
			addCriterion("serial <>", value, "serial");
			return (Criteria) this;
		}

		public Criteria andSerialGreaterThan(String value) {
			addCriterion("serial >", value, "serial");
			return (Criteria) this;
		}

		public Criteria andSerialGreaterThanOrEqualTo(String value) {
			addCriterion("serial >=", value, "serial");
			return (Criteria) this;
		}

		public Criteria andSerialLessThan(String value) {
			addCriterion("serial <", value, "serial");
			return (Criteria) this;
		}

		public Criteria andSerialLessThanOrEqualTo(String value) {
			addCriterion("serial <=", value, "serial");
			return (Criteria) this;
		}

		public Criteria andSerialLike(String value) {
			addCriterion("serial like", value, "serial");
			return (Criteria) this;
		}

		public Criteria andSerialNotLike(String value) {
			addCriterion("serial not like", value, "serial");
			return (Criteria) this;
		}

		public Criteria andSerialIn(List<String> values) {
			addCriterion("serial in", values, "serial");
			return (Criteria) this;
		}

		public Criteria andSerialNotIn(List<String> values) {
			addCriterion("serial not in", values, "serial");
			return (Criteria) this;
		}

		public Criteria andSerialBetween(String value1, String value2) {
			addCriterion("serial between", value1, value2, "serial");
			return (Criteria) this;
		}

		public Criteria andSerialNotBetween(String value1, String value2) {
			addCriterion("serial not between", value1, value2, "serial");
			return (Criteria) this;
		}

		public Criteria andBuserIdIsNull() {
			addCriterion("buser_id is null");
			return (Criteria) this;
		}

		public Criteria andBuserIdIsNotNull() {
			addCriterion("buser_id is not null");
			return (Criteria) this;
		}

		public Criteria andBuserIdEqualTo(Long value) {
			addCriterion("buser_id =", value, "buserId");
			return (Criteria) this;
		}

		public Criteria andBuserIdNotEqualTo(Long value) {
			addCriterion("buser_id <>", value, "buserId");
			return (Criteria) this;
		}

		public Criteria andBuserIdGreaterThan(Long value) {
			addCriterion("buser_id >", value, "buserId");
			return (Criteria) this;
		}

		public Criteria andBuserIdGreaterThanOrEqualTo(Long value) {
			addCriterion("buser_id >=", value, "buserId");
			return (Criteria) this;
		}

		public Criteria andBuserIdLessThan(Long value) {
			addCriterion("buser_id <", value, "buserId");
			return (Criteria) this;
		}

		public Criteria andBuserIdLessThanOrEqualTo(Long value) {
			addCriterion("buser_id <=", value, "buserId");
			return (Criteria) this;
		}

		public Criteria andBuserIdIn(List<Long> values) {
			addCriterion("buser_id in", values, "buserId");
			return (Criteria) this;
		}

		public Criteria andBuserIdNotIn(List<Long> values) {
			addCriterion("buser_id not in", values, "buserId");
			return (Criteria) this;
		}

		public Criteria andBuserIdBetween(Long value1, Long value2) {
			addCriterion("buser_id between", value1, value2, "buserId");
			return (Criteria) this;
		}

		public Criteria andBuserIdNotBetween(Long value1, Long value2) {
			addCriterion("buser_id not between", value1, value2, "buserId");
			return (Criteria) this;
		}

		public Criteria andCommunityIdIsNull() {
			addCriterion("community_id is null");
			return (Criteria) this;
		}

		public Criteria andCommunityIdIsNotNull() {
			addCriterion("community_id is not null");
			return (Criteria) this;
		}

		public Criteria andCommunityIdEqualTo(Long value) {
			addCriterion("community_id =", value, "communityId");
			return (Criteria) this;
		}

		public Criteria andCommunityIdNotEqualTo(Long value) {
			addCriterion("community_id <>", value, "communityId");
			return (Criteria) this;
		}

		public Criteria andCommunityIdGreaterThan(Long value) {
			addCriterion("community_id >", value, "communityId");
			return (Criteria) this;
		}

		public Criteria andCommunityIdGreaterThanOrEqualTo(Long value) {
			addCriterion("community_id >=", value, "communityId");
			return (Criteria) this;
		}

		public Criteria andCommunityIdLessThan(Long value) {
			addCriterion("community_id <", value, "communityId");
			return (Criteria) this;
		}

		public Criteria andCommunityIdLessThanOrEqualTo(Long value) {
			addCriterion("community_id <=", value, "communityId");
			return (Criteria) this;
		}

		public Criteria andCommunityIdIn(List<Long> values) {
			addCriterion("community_id in", values, "communityId");
			return (Criteria) this;
		}

		public Criteria andCommunityIdNotIn(List<Long> values) {
			addCriterion("community_id not in", values, "communityId");
			return (Criteria) this;
		}

		public Criteria andCommunityIdBetween(Long value1, Long value2) {
			addCriterion("community_id between", value1, value2, "communityId");
			return (Criteria) this;
		}

		public Criteria andCommunityIdNotBetween(Long value1, Long value2) {
			addCriterion("community_id not between", value1, value2,
					"communityId");
			return (Criteria) this;
		}

		public Criteria andExpectArriveTimeIsNull() {
			addCriterion("expect_arrive_time is null");
			return (Criteria) this;
		}

		public Criteria andExpectArriveTimeIsNotNull() {
			addCriterion("expect_arrive_time is not null");
			return (Criteria) this;
		}

		public Criteria andExpectArriveTimeEqualTo(Date value) {
			addCriterion("expect_arrive_time =", value, "expectArriveTime");
			return (Criteria) this;
		}

		public Criteria andExpectArriveTimeNotEqualTo(Date value) {
			addCriterion("expect_arrive_time <>", value, "expectArriveTime");
			return (Criteria) this;
		}

		public Criteria andExpectArriveTimeGreaterThan(Date value) {
			addCriterion("expect_arrive_time >", value, "expectArriveTime");
			return (Criteria) this;
		}

		public Criteria andExpectArriveTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("expect_arrive_time >=", value, "expectArriveTime");
			return (Criteria) this;
		}

		public Criteria andExpectArriveTimeLessThan(Date value) {
			addCriterion("expect_arrive_time <", value, "expectArriveTime");
			return (Criteria) this;
		}

		public Criteria andExpectArriveTimeLessThanOrEqualTo(Date value) {
			addCriterion("expect_arrive_time <=", value, "expectArriveTime");
			return (Criteria) this;
		}

		public Criteria andExpectArriveTimeIn(List<Date> values) {
			addCriterion("expect_arrive_time in", values, "expectArriveTime");
			return (Criteria) this;
		}

		public Criteria andExpectArriveTimeNotIn(List<Date> values) {
			addCriterion("expect_arrive_time not in", values,
					"expectArriveTime");
			return (Criteria) this;
		}

		public Criteria andExpectArriveTimeBetween(Date value1, Date value2) {
			addCriterion("expect_arrive_time between", value1, value2,
					"expectArriveTime");
			return (Criteria) this;
		}

		public Criteria andExpectArriveTimeNotBetween(Date value1, Date value2) {
			addCriterion("expect_arrive_time not between", value1, value2,
					"expectArriveTime");
			return (Criteria) this;
		}

		public Criteria andExpectLeaveTimeIsNull() {
			addCriterion("expect_leave_time is null");
			return (Criteria) this;
		}

		public Criteria andExpectLeaveTimeIsNotNull() {
			addCriterion("expect_leave_time is not null");
			return (Criteria) this;
		}

		public Criteria andExpectLeaveTimeEqualTo(Date value) {
			addCriterion("expect_leave_time =", value, "expectLeaveTime");
			return (Criteria) this;
		}

		public Criteria andExpectLeaveTimeNotEqualTo(Date value) {
			addCriterion("expect_leave_time <>", value, "expectLeaveTime");
			return (Criteria) this;
		}

		public Criteria andExpectLeaveTimeGreaterThan(Date value) {
			addCriterion("expect_leave_time >", value, "expectLeaveTime");
			return (Criteria) this;
		}

		public Criteria andExpectLeaveTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("expect_leave_time >=", value, "expectLeaveTime");
			return (Criteria) this;
		}

		public Criteria andExpectLeaveTimeLessThan(Date value) {
			addCriterion("expect_leave_time <", value, "expectLeaveTime");
			return (Criteria) this;
		}

		public Criteria andExpectLeaveTimeLessThanOrEqualTo(Date value) {
			addCriterion("expect_leave_time <=", value, "expectLeaveTime");
			return (Criteria) this;
		}

		public Criteria andExpectLeaveTimeIn(List<Date> values) {
			addCriterion("expect_leave_time in", values, "expectLeaveTime");
			return (Criteria) this;
		}

		public Criteria andExpectLeaveTimeNotIn(List<Date> values) {
			addCriterion("expect_leave_time not in", values, "expectLeaveTime");
			return (Criteria) this;
		}

		public Criteria andExpectLeaveTimeBetween(Date value1, Date value2) {
			addCriterion("expect_leave_time between", value1, value2,
					"expectLeaveTime");
			return (Criteria) this;
		}

		public Criteria andExpectLeaveTimeNotBetween(Date value1, Date value2) {
			addCriterion("expect_leave_time not between", value1, value2,
					"expectLeaveTime");
			return (Criteria) this;
		}

		public Criteria andEffectiveArriveTimeIsNull() {
			addCriterion("effective_arrive_time is null");
			return (Criteria) this;
		}

		public Criteria andEffectiveArriveTimeIsNotNull() {
			addCriterion("effective_arrive_time is not null");
			return (Criteria) this;
		}

		public Criteria andEffectiveArriveTimeEqualTo(Date value) {
			addCriterion("effective_arrive_time =", value,
					"effectiveArriveTime");
			return (Criteria) this;
		}

		public Criteria andEffectiveArriveTimeNotEqualTo(Date value) {
			addCriterion("effective_arrive_time <>", value,
					"effectiveArriveTime");
			return (Criteria) this;
		}

		public Criteria andEffectiveArriveTimeGreaterThan(Date value) {
			addCriterion("effective_arrive_time >", value,
					"effectiveArriveTime");
			return (Criteria) this;
		}

		public Criteria andEffectiveArriveTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("effective_arrive_time >=", value,
					"effectiveArriveTime");
			return (Criteria) this;
		}

		public Criteria andEffectiveArriveTimeLessThan(Date value) {
			addCriterion("effective_arrive_time <", value,
					"effectiveArriveTime");
			return (Criteria) this;
		}

		public Criteria andEffectiveArriveTimeLessThanOrEqualTo(Date value) {
			addCriterion("effective_arrive_time <=", value,
					"effectiveArriveTime");
			return (Criteria) this;
		}

		public Criteria andEffectiveArriveTimeIn(List<Date> values) {
			addCriterion("effective_arrive_time in", values,
					"effectiveArriveTime");
			return (Criteria) this;
		}

		public Criteria andEffectiveArriveTimeNotIn(List<Date> values) {
			addCriterion("effective_arrive_time not in", values,
					"effectiveArriveTime");
			return (Criteria) this;
		}

		public Criteria andEffectiveArriveTimeBetween(Date value1, Date value2) {
			addCriterion("effective_arrive_time between", value1, value2,
					"effectiveArriveTime");
			return (Criteria) this;
		}

		public Criteria andEffectiveArriveTimeNotBetween(Date value1,
				Date value2) {
			addCriterion("effective_arrive_time not between", value1, value2,
					"effectiveArriveTime");
			return (Criteria) this;
		}

		public Criteria andEffectiveLeaveTimeIsNull() {
			addCriterion("effective_leave_time is null");
			return (Criteria) this;
		}

		public Criteria andEffectiveLeaveTimeIsNotNull() {
			addCriterion("effective_leave_time is not null");
			return (Criteria) this;
		}

		public Criteria andEffectiveLeaveTimeEqualTo(Date value) {
			addCriterion("effective_leave_time =", value, "effectiveLeaveTime");
			return (Criteria) this;
		}

		public Criteria andEffectiveLeaveTimeNotEqualTo(Date value) {
			addCriterion("effective_leave_time <>", value, "effectiveLeaveTime");
			return (Criteria) this;
		}

		public Criteria andEffectiveLeaveTimeGreaterThan(Date value) {
			addCriterion("effective_leave_time >", value, "effectiveLeaveTime");
			return (Criteria) this;
		}

		public Criteria andEffectiveLeaveTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("effective_leave_time >=", value, "effectiveLeaveTime");
			return (Criteria) this;
		}

		public Criteria andEffectiveLeaveTimeLessThan(Date value) {
			addCriterion("effective_leave_time <", value, "effectiveLeaveTime");
			return (Criteria) this;
		}

		public Criteria andEffectiveLeaveTimeLessThanOrEqualTo(Date value) {
			addCriterion("effective_leave_time <=", value, "effectiveLeaveTime");
			return (Criteria) this;
		}

		public Criteria andEffectiveLeaveTimeIn(List<Date> values) {
			addCriterion("effective_leave_time in", values,
					"effectiveLeaveTime");
			return (Criteria) this;
		}

		public Criteria andEffectiveLeaveTimeNotIn(List<Date> values) {
			addCriterion("effective_leave_time not in", values,
					"effectiveLeaveTime");
			return (Criteria) this;
		}

		public Criteria andEffectiveLeaveTimeBetween(Date value1, Date value2) {
			addCriterion("effective_leave_time between", value1, value2,
					"effectiveLeaveTime");
			return (Criteria) this;
		}

		public Criteria andEffectiveLeaveTimeNotBetween(Date value1, Date value2) {
			addCriterion("effective_leave_time not between", value1, value2,
					"effectiveLeaveTime");
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
     * This class corresponds to the database table n_visitors
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}