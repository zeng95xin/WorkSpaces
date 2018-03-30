package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.ModelExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VisitorPassportRecordExample extends ModelExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table n_visitor_passport_record
	 * @mbggenerated
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table n_visitor_passport_record
	 * @mbggenerated
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table n_visitor_passport_record
	 * @mbggenerated
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_visitor_passport_record
	 * @mbggenerated
	 */
	public VisitorPassportRecordExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_visitor_passport_record
	 * @mbggenerated
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_visitor_passport_record
	 * @mbggenerated
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_visitor_passport_record
	 * @mbggenerated
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_visitor_passport_record
	 * @mbggenerated
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_visitor_passport_record
	 * @mbggenerated
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_visitor_passport_record
	 * @mbggenerated
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_visitor_passport_record
	 * @mbggenerated
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_visitor_passport_record
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
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_visitor_passport_record
	 * @mbggenerated
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_visitor_passport_record
	 * @mbggenerated
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_visitor_passport_record
	 * @mbggenerated
	 */
	public Criteria getCriteria() {
		if (oredCriteria.size() == 0) {
			createCriteria();
		}
		return oredCriteria.get(0);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_visitor_passport_record
	 * @mbggenerated
	 */
	public void addCriteria(List<Criterion> criteria) {
		getCriteria().getCriteria().addAll(criteria);
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table n_visitor_passport_record
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

		public Criteria andTypeIsNull() {
			addCriterion("type is null");
			return (Criteria) this;
		}

		public Criteria andTypeIsNotNull() {
			addCriterion("type is not null");
			return (Criteria) this;
		}

		public Criteria andTypeEqualTo(Integer value) {
			addCriterion("type =", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotEqualTo(Integer value) {
			addCriterion("type <>", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThan(Integer value) {
			addCriterion("type >", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
			addCriterion("type >=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThan(Integer value) {
			addCriterion("type <", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThanOrEqualTo(Integer value) {
			addCriterion("type <=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeIn(List<Integer> values) {
			addCriterion("type in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotIn(List<Integer> values) {
			addCriterion("type not in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeBetween(Integer value1, Integer value2) {
			addCriterion("type between", value1, value2, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotBetween(Integer value1, Integer value2) {
			addCriterion("type not between", value1, value2, "type");
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

		public Criteria andLingLingIdIsNull() {
			addCriterion("ling_ling_id is null");
			return (Criteria) this;
		}

		public Criteria andLingLingIdIsNotNull() {
			addCriterion("ling_ling_id is not null");
			return (Criteria) this;
		}

		public Criteria andLingLingIdEqualTo(String value) {
			addCriterion("ling_ling_id =", value, "lingLingId");
			return (Criteria) this;
		}

		public Criteria andLingLingIdNotEqualTo(String value) {
			addCriterion("ling_ling_id <>", value, "lingLingId");
			return (Criteria) this;
		}

		public Criteria andLingLingIdGreaterThan(String value) {
			addCriterion("ling_ling_id >", value, "lingLingId");
			return (Criteria) this;
		}

		public Criteria andLingLingIdGreaterThanOrEqualTo(String value) {
			addCriterion("ling_ling_id >=", value, "lingLingId");
			return (Criteria) this;
		}

		public Criteria andLingLingIdLessThan(String value) {
			addCriterion("ling_ling_id <", value, "lingLingId");
			return (Criteria) this;
		}

		public Criteria andLingLingIdLessThanOrEqualTo(String value) {
			addCriterion("ling_ling_id <=", value, "lingLingId");
			return (Criteria) this;
		}

		public Criteria andLingLingIdLike(String value) {
			addCriterion("ling_ling_id like", value, "lingLingId");
			return (Criteria) this;
		}

		public Criteria andLingLingIdNotLike(String value) {
			addCriterion("ling_ling_id not like", value, "lingLingId");
			return (Criteria) this;
		}

		public Criteria andLingLingIdIn(List<String> values) {
			addCriterion("ling_ling_id in", values, "lingLingId");
			return (Criteria) this;
		}

		public Criteria andLingLingIdNotIn(List<String> values) {
			addCriterion("ling_ling_id not in", values, "lingLingId");
			return (Criteria) this;
		}

		public Criteria andLingLingIdBetween(String value1, String value2) {
			addCriterion("ling_ling_id between", value1, value2, "lingLingId");
			return (Criteria) this;
		}

		public Criteria andLingLingIdNotBetween(String value1, String value2) {
			addCriterion("ling_ling_id not between", value1, value2,
					"lingLingId");
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

		public Criteria andDeviceCodeIsNull() {
			addCriterion("device_code is null");
			return (Criteria) this;
		}

		public Criteria andDeviceCodeIsNotNull() {
			addCriterion("device_code is not null");
			return (Criteria) this;
		}

		public Criteria andDeviceCodeEqualTo(String value) {
			addCriterion("device_code =", value, "deviceCode");
			return (Criteria) this;
		}

		public Criteria andDeviceCodeNotEqualTo(String value) {
			addCriterion("device_code <>", value, "deviceCode");
			return (Criteria) this;
		}

		public Criteria andDeviceCodeGreaterThan(String value) {
			addCriterion("device_code >", value, "deviceCode");
			return (Criteria) this;
		}

		public Criteria andDeviceCodeGreaterThanOrEqualTo(String value) {
			addCriterion("device_code >=", value, "deviceCode");
			return (Criteria) this;
		}

		public Criteria andDeviceCodeLessThan(String value) {
			addCriterion("device_code <", value, "deviceCode");
			return (Criteria) this;
		}

		public Criteria andDeviceCodeLessThanOrEqualTo(String value) {
			addCriterion("device_code <=", value, "deviceCode");
			return (Criteria) this;
		}

		public Criteria andDeviceCodeLike(String value) {
			addCriterion("device_code like", value, "deviceCode");
			return (Criteria) this;
		}

		public Criteria andDeviceCodeNotLike(String value) {
			addCriterion("device_code not like", value, "deviceCode");
			return (Criteria) this;
		}

		public Criteria andDeviceCodeIn(List<String> values) {
			addCriterion("device_code in", values, "deviceCode");
			return (Criteria) this;
		}

		public Criteria andDeviceCodeNotIn(List<String> values) {
			addCriterion("device_code not in", values, "deviceCode");
			return (Criteria) this;
		}

		public Criteria andDeviceCodeBetween(String value1, String value2) {
			addCriterion("device_code between", value1, value2, "deviceCode");
			return (Criteria) this;
		}

		public Criteria andDeviceCodeNotBetween(String value1, String value2) {
			addCriterion("device_code not between", value1, value2,
					"deviceCode");
			return (Criteria) this;
		}

		public Criteria andDoorControllIdIsNull() {
			addCriterion("door_controll_id is null");
			return (Criteria) this;
		}

		public Criteria andDoorControllIdIsNotNull() {
			addCriterion("door_controll_id is not null");
			return (Criteria) this;
		}

		public Criteria andDoorControllIdEqualTo(Long value) {
			addCriterion("door_controll_id =", value, "doorControllId");
			return (Criteria) this;
		}

		public Criteria andDoorControllIdNotEqualTo(Long value) {
			addCriterion("door_controll_id <>", value, "doorControllId");
			return (Criteria) this;
		}

		public Criteria andDoorControllIdGreaterThan(Long value) {
			addCriterion("door_controll_id >", value, "doorControllId");
			return (Criteria) this;
		}

		public Criteria andDoorControllIdGreaterThanOrEqualTo(Long value) {
			addCriterion("door_controll_id >=", value, "doorControllId");
			return (Criteria) this;
		}

		public Criteria andDoorControllIdLessThan(Long value) {
			addCriterion("door_controll_id <", value, "doorControllId");
			return (Criteria) this;
		}

		public Criteria andDoorControllIdLessThanOrEqualTo(Long value) {
			addCriterion("door_controll_id <=", value, "doorControllId");
			return (Criteria) this;
		}

		public Criteria andDoorControllIdIn(List<Long> values) {
			addCriterion("door_controll_id in", values, "doorControllId");
			return (Criteria) this;
		}

		public Criteria andDoorControllIdNotIn(List<Long> values) {
			addCriterion("door_controll_id not in", values, "doorControllId");
			return (Criteria) this;
		}

		public Criteria andDoorControllIdBetween(Long value1, Long value2) {
			addCriterion("door_controll_id between", value1, value2,
					"doorControllId");
			return (Criteria) this;
		}

		public Criteria andDoorControllIdNotBetween(Long value1, Long value2) {
			addCriterion("door_controll_id not between", value1, value2,
					"doorControllId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdIsNull() {
			addCriterion("device_id is null");
			return (Criteria) this;
		}

		public Criteria andDeviceIdIsNotNull() {
			addCriterion("device_id is not null");
			return (Criteria) this;
		}

		public Criteria andDeviceIdEqualTo(String value) {
			addCriterion("device_id =", value, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdNotEqualTo(String value) {
			addCriterion("device_id <>", value, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdGreaterThan(String value) {
			addCriterion("device_id >", value, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdGreaterThanOrEqualTo(String value) {
			addCriterion("device_id >=", value, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdLessThan(String value) {
			addCriterion("device_id <", value, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdLessThanOrEqualTo(String value) {
			addCriterion("device_id <=", value, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdLike(String value) {
			addCriterion("device_id like", value, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdNotLike(String value) {
			addCriterion("device_id not like", value, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdIn(List<String> values) {
			addCriterion("device_id in", values, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdNotIn(List<String> values) {
			addCriterion("device_id not in", values, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdBetween(String value1, String value2) {
			addCriterion("device_id between", value1, value2, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdNotBetween(String value1, String value2) {
			addCriterion("device_id not between", value1, value2, "deviceId");
			return (Criteria) this;
		}

		public Criteria andOpenTimeStrIsNull() {
			addCriterion("open_time_str is null");
			return (Criteria) this;
		}

		public Criteria andOpenTimeStrIsNotNull() {
			addCriterion("open_time_str is not null");
			return (Criteria) this;
		}

		public Criteria andOpenTimeStrEqualTo(String value) {
			addCriterion("open_time_str =", value, "openTimeStr");
			return (Criteria) this;
		}

		public Criteria andOpenTimeStrNotEqualTo(String value) {
			addCriterion("open_time_str <>", value, "openTimeStr");
			return (Criteria) this;
		}

		public Criteria andOpenTimeStrGreaterThan(String value) {
			addCriterion("open_time_str >", value, "openTimeStr");
			return (Criteria) this;
		}

		public Criteria andOpenTimeStrGreaterThanOrEqualTo(String value) {
			addCriterion("open_time_str >=", value, "openTimeStr");
			return (Criteria) this;
		}

		public Criteria andOpenTimeStrLessThan(String value) {
			addCriterion("open_time_str <", value, "openTimeStr");
			return (Criteria) this;
		}

		public Criteria andOpenTimeStrLessThanOrEqualTo(String value) {
			addCriterion("open_time_str <=", value, "openTimeStr");
			return (Criteria) this;
		}

		public Criteria andOpenTimeStrLike(String value) {
			addCriterion("open_time_str like", value, "openTimeStr");
			return (Criteria) this;
		}

		public Criteria andOpenTimeStrNotLike(String value) {
			addCriterion("open_time_str not like", value, "openTimeStr");
			return (Criteria) this;
		}

		public Criteria andOpenTimeStrIn(List<String> values) {
			addCriterion("open_time_str in", values, "openTimeStr");
			return (Criteria) this;
		}

		public Criteria andOpenTimeStrNotIn(List<String> values) {
			addCriterion("open_time_str not in", values, "openTimeStr");
			return (Criteria) this;
		}

		public Criteria andOpenTimeStrBetween(String value1, String value2) {
			addCriterion("open_time_str between", value1, value2, "openTimeStr");
			return (Criteria) this;
		}

		public Criteria andOpenTimeStrNotBetween(String value1, String value2) {
			addCriterion("open_time_str not between", value1, value2,
					"openTimeStr");
			return (Criteria) this;
		}

		public Criteria andOpenTimeIsNull() {
			addCriterion("open_time is null");
			return (Criteria) this;
		}

		public Criteria andOpenTimeIsNotNull() {
			addCriterion("open_time is not null");
			return (Criteria) this;
		}

		public Criteria andOpenTimeEqualTo(Date value) {
			addCriterion("open_time =", value, "openTime");
			return (Criteria) this;
		}

		public Criteria andOpenTimeNotEqualTo(Date value) {
			addCriterion("open_time <>", value, "openTime");
			return (Criteria) this;
		}

		public Criteria andOpenTimeGreaterThan(Date value) {
			addCriterion("open_time >", value, "openTime");
			return (Criteria) this;
		}

		public Criteria andOpenTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("open_time >=", value, "openTime");
			return (Criteria) this;
		}

		public Criteria andOpenTimeLessThan(Date value) {
			addCriterion("open_time <", value, "openTime");
			return (Criteria) this;
		}

		public Criteria andOpenTimeLessThanOrEqualTo(Date value) {
			addCriterion("open_time <=", value, "openTime");
			return (Criteria) this;
		}

		public Criteria andOpenTimeIn(List<Date> values) {
			addCriterion("open_time in", values, "openTime");
			return (Criteria) this;
		}

		public Criteria andOpenTimeNotIn(List<Date> values) {
			addCriterion("open_time not in", values, "openTime");
			return (Criteria) this;
		}

		public Criteria andOpenTimeBetween(Date value1, Date value2) {
			addCriterion("open_time between", value1, value2, "openTime");
			return (Criteria) this;
		}

		public Criteria andOpenTimeNotBetween(Date value1, Date value2) {
			addCriterion("open_time not between", value1, value2, "openTime");
			return (Criteria) this;
		}

		public Criteria andQrcodeKeyIsNull() {
			addCriterion("qrcode_key is null");
			return (Criteria) this;
		}

		public Criteria andQrcodeKeyIsNotNull() {
			addCriterion("qrcode_key is not null");
			return (Criteria) this;
		}

		public Criteria andQrcodeKeyEqualTo(String value) {
			addCriterion("qrcode_key =", value, "qrcodeKey");
			return (Criteria) this;
		}

		public Criteria andQrcodeKeyNotEqualTo(String value) {
			addCriterion("qrcode_key <>", value, "qrcodeKey");
			return (Criteria) this;
		}

		public Criteria andQrcodeKeyGreaterThan(String value) {
			addCriterion("qrcode_key >", value, "qrcodeKey");
			return (Criteria) this;
		}

		public Criteria andQrcodeKeyGreaterThanOrEqualTo(String value) {
			addCriterion("qrcode_key >=", value, "qrcodeKey");
			return (Criteria) this;
		}

		public Criteria andQrcodeKeyLessThan(String value) {
			addCriterion("qrcode_key <", value, "qrcodeKey");
			return (Criteria) this;
		}

		public Criteria andQrcodeKeyLessThanOrEqualTo(String value) {
			addCriterion("qrcode_key <=", value, "qrcodeKey");
			return (Criteria) this;
		}

		public Criteria andQrcodeKeyLike(String value) {
			addCriterion("qrcode_key like", value, "qrcodeKey");
			return (Criteria) this;
		}

		public Criteria andQrcodeKeyNotLike(String value) {
			addCriterion("qrcode_key not like", value, "qrcodeKey");
			return (Criteria) this;
		}

		public Criteria andQrcodeKeyIn(List<String> values) {
			addCriterion("qrcode_key in", values, "qrcodeKey");
			return (Criteria) this;
		}

		public Criteria andQrcodeKeyNotIn(List<String> values) {
			addCriterion("qrcode_key not in", values, "qrcodeKey");
			return (Criteria) this;
		}

		public Criteria andQrcodeKeyBetween(String value1, String value2) {
			addCriterion("qrcode_key between", value1, value2, "qrcodeKey");
			return (Criteria) this;
		}

		public Criteria andQrcodeKeyNotBetween(String value1, String value2) {
			addCriterion("qrcode_key not between", value1, value2, "qrcodeKey");
			return (Criteria) this;
		}

		public Criteria andVisitorIdIsNull() {
			addCriterion("visitor_id is null");
			return (Criteria) this;
		}

		public Criteria andVisitorIdIsNotNull() {
			addCriterion("visitor_id is not null");
			return (Criteria) this;
		}

		public Criteria andVisitorIdEqualTo(Long value) {
			addCriterion("visitor_id =", value, "visitorId");
			return (Criteria) this;
		}

		public Criteria andVisitorIdNotEqualTo(Long value) {
			addCriterion("visitor_id <>", value, "visitorId");
			return (Criteria) this;
		}

		public Criteria andVisitorIdGreaterThan(Long value) {
			addCriterion("visitor_id >", value, "visitorId");
			return (Criteria) this;
		}

		public Criteria andVisitorIdGreaterThanOrEqualTo(Long value) {
			addCriterion("visitor_id >=", value, "visitorId");
			return (Criteria) this;
		}

		public Criteria andVisitorIdLessThan(Long value) {
			addCriterion("visitor_id <", value, "visitorId");
			return (Criteria) this;
		}

		public Criteria andVisitorIdLessThanOrEqualTo(Long value) {
			addCriterion("visitor_id <=", value, "visitorId");
			return (Criteria) this;
		}

		public Criteria andVisitorIdIn(List<Long> values) {
			addCriterion("visitor_id in", values, "visitorId");
			return (Criteria) this;
		}

		public Criteria andVisitorIdNotIn(List<Long> values) {
			addCriterion("visitor_id not in", values, "visitorId");
			return (Criteria) this;
		}

		public Criteria andVisitorIdBetween(Long value1, Long value2) {
			addCriterion("visitor_id between", value1, value2, "visitorId");
			return (Criteria) this;
		}

		public Criteria andVisitorIdNotBetween(Long value1, Long value2) {
			addCriterion("visitor_id not between", value1, value2, "visitorId");
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
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table n_visitor_passport_record
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}