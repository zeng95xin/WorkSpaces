package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.ModelExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceClockRoleConfigExample extends ModelExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table n_service_clock_role_config
	 * @mbggenerated
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table n_service_clock_role_config
	 * @mbggenerated
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table n_service_clock_role_config
	 * @mbggenerated
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_service_clock_role_config
	 * @mbggenerated
	 */
	public ServiceClockRoleConfigExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_service_clock_role_config
	 * @mbggenerated
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_service_clock_role_config
	 * @mbggenerated
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_service_clock_role_config
	 * @mbggenerated
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_service_clock_role_config
	 * @mbggenerated
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_service_clock_role_config
	 * @mbggenerated
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_service_clock_role_config
	 * @mbggenerated
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_service_clock_role_config
	 * @mbggenerated
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_service_clock_role_config
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
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_service_clock_role_config
	 * @mbggenerated
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_service_clock_role_config
	 * @mbggenerated
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_service_clock_role_config
	 * @mbggenerated
	 */
	public Criteria getCriteria() {
		if (oredCriteria.size() == 0) {
			createCriteria();
		}
		return oredCriteria.get(0);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_service_clock_role_config
	 * @mbggenerated
	 */
	public void addCriteria(List<Criterion> criteria) {
		getCriteria().getCriteria().addAll(criteria);
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table n_service_clock_role_config
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
					throw new RuntimeException("Value for condition cannot be null");
				}
			} else {
				criteria.add(new Criterion(condition));
			}
		}

		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				if (!isIgnoreNull()) {
					throw new RuntimeException("Value for " + property + " cannot be null");
				}
			} else {
				criteria.add(new Criterion(condition, value));
			}
		}

		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				if (!isIgnoreNull()) {
					throw new RuntimeException("Between values for " + property + " cannot be null");
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

		public Criteria andRoleIsNull() {
			addCriterion("role is null");
			return (Criteria) this;
		}

		public Criteria andRoleIsNotNull() {
			addCriterion("role is not null");
			return (Criteria) this;
		}

		public Criteria andRoleEqualTo(Integer value) {
			addCriterion("role =", value, "role");
			return (Criteria) this;
		}

		public Criteria andRoleNotEqualTo(Integer value) {
			addCriterion("role <>", value, "role");
			return (Criteria) this;
		}

		public Criteria andRoleGreaterThan(Integer value) {
			addCriterion("role >", value, "role");
			return (Criteria) this;
		}

		public Criteria andRoleGreaterThanOrEqualTo(Integer value) {
			addCriterion("role >=", value, "role");
			return (Criteria) this;
		}

		public Criteria andRoleLessThan(Integer value) {
			addCriterion("role <", value, "role");
			return (Criteria) this;
		}

		public Criteria andRoleLessThanOrEqualTo(Integer value) {
			addCriterion("role <=", value, "role");
			return (Criteria) this;
		}

		public Criteria andRoleIn(List<Integer> values) {
			addCriterion("role in", values, "role");
			return (Criteria) this;
		}

		public Criteria andRoleNotIn(List<Integer> values) {
			addCriterion("role not in", values, "role");
			return (Criteria) this;
		}

		public Criteria andRoleBetween(Integer value1, Integer value2) {
			addCriterion("role between", value1, value2, "role");
			return (Criteria) this;
		}

		public Criteria andRoleNotBetween(Integer value1, Integer value2) {
			addCriterion("role not between", value1, value2, "role");
			return (Criteria) this;
		}

		public Criteria andLongitudeIsNull() {
			addCriterion("longitude is null");
			return (Criteria) this;
		}

		public Criteria andLongitudeIsNotNull() {
			addCriterion("longitude is not null");
			return (Criteria) this;
		}

		public Criteria andLongitudeEqualTo(Double value) {
			addCriterion("longitude =", value, "longitude");
			return (Criteria) this;
		}

		public Criteria andLongitudeNotEqualTo(Double value) {
			addCriterion("longitude <>", value, "longitude");
			return (Criteria) this;
		}

		public Criteria andLongitudeGreaterThan(Double value) {
			addCriterion("longitude >", value, "longitude");
			return (Criteria) this;
		}

		public Criteria andLongitudeGreaterThanOrEqualTo(Double value) {
			addCriterion("longitude >=", value, "longitude");
			return (Criteria) this;
		}

		public Criteria andLongitudeLessThan(Double value) {
			addCriterion("longitude <", value, "longitude");
			return (Criteria) this;
		}

		public Criteria andLongitudeLessThanOrEqualTo(Double value) {
			addCriterion("longitude <=", value, "longitude");
			return (Criteria) this;
		}

		public Criteria andLongitudeIn(List<Double> values) {
			addCriterion("longitude in", values, "longitude");
			return (Criteria) this;
		}

		public Criteria andLongitudeNotIn(List<Double> values) {
			addCriterion("longitude not in", values, "longitude");
			return (Criteria) this;
		}

		public Criteria andLongitudeBetween(Double value1, Double value2) {
			addCriterion("longitude between", value1, value2, "longitude");
			return (Criteria) this;
		}

		public Criteria andLongitudeNotBetween(Double value1, Double value2) {
			addCriterion("longitude not between", value1, value2, "longitude");
			return (Criteria) this;
		}

		public Criteria andLatitudeIsNull() {
			addCriterion("latitude is null");
			return (Criteria) this;
		}

		public Criteria andLatitudeIsNotNull() {
			addCriterion("latitude is not null");
			return (Criteria) this;
		}

		public Criteria andLatitudeEqualTo(Double value) {
			addCriterion("latitude =", value, "latitude");
			return (Criteria) this;
		}

		public Criteria andLatitudeNotEqualTo(Double value) {
			addCriterion("latitude <>", value, "latitude");
			return (Criteria) this;
		}

		public Criteria andLatitudeGreaterThan(Double value) {
			addCriterion("latitude >", value, "latitude");
			return (Criteria) this;
		}

		public Criteria andLatitudeGreaterThanOrEqualTo(Double value) {
			addCriterion("latitude >=", value, "latitude");
			return (Criteria) this;
		}

		public Criteria andLatitudeLessThan(Double value) {
			addCriterion("latitude <", value, "latitude");
			return (Criteria) this;
		}

		public Criteria andLatitudeLessThanOrEqualTo(Double value) {
			addCriterion("latitude <=", value, "latitude");
			return (Criteria) this;
		}

		public Criteria andLatitudeIn(List<Double> values) {
			addCriterion("latitude in", values, "latitude");
			return (Criteria) this;
		}

		public Criteria andLatitudeNotIn(List<Double> values) {
			addCriterion("latitude not in", values, "latitude");
			return (Criteria) this;
		}

		public Criteria andLatitudeBetween(Double value1, Double value2) {
			addCriterion("latitude between", value1, value2, "latitude");
			return (Criteria) this;
		}

		public Criteria andLatitudeNotBetween(Double value1, Double value2) {
			addCriterion("latitude not between", value1, value2, "latitude");
			return (Criteria) this;
		}

		public Criteria andDistanceIsNull() {
			addCriterion("distance is null");
			return (Criteria) this;
		}

		public Criteria andDistanceIsNotNull() {
			addCriterion("distance is not null");
			return (Criteria) this;
		}

		public Criteria andDistanceEqualTo(Double value) {
			addCriterion("distance =", value, "distance");
			return (Criteria) this;
		}

		public Criteria andDistanceNotEqualTo(Double value) {
			addCriterion("distance <>", value, "distance");
			return (Criteria) this;
		}

		public Criteria andDistanceGreaterThan(Double value) {
			addCriterion("distance >", value, "distance");
			return (Criteria) this;
		}

		public Criteria andDistanceGreaterThanOrEqualTo(Double value) {
			addCriterion("distance >=", value, "distance");
			return (Criteria) this;
		}

		public Criteria andDistanceLessThan(Double value) {
			addCriterion("distance <", value, "distance");
			return (Criteria) this;
		}

		public Criteria andDistanceLessThanOrEqualTo(Double value) {
			addCriterion("distance <=", value, "distance");
			return (Criteria) this;
		}

		public Criteria andDistanceIn(List<Double> values) {
			addCriterion("distance in", values, "distance");
			return (Criteria) this;
		}

		public Criteria andDistanceNotIn(List<Double> values) {
			addCriterion("distance not in", values, "distance");
			return (Criteria) this;
		}

		public Criteria andDistanceBetween(Double value1, Double value2) {
			addCriterion("distance between", value1, value2, "distance");
			return (Criteria) this;
		}

		public Criteria andDistanceNotBetween(Double value1, Double value2) {
			addCriterion("distance not between", value1, value2, "distance");
			return (Criteria) this;
		}

		public Criteria andClockInTimeIsNull() {
			addCriterion("clock_in_time is null");
			return (Criteria) this;
		}

		public Criteria andClockInTimeIsNotNull() {
			addCriterion("clock_in_time is not null");
			return (Criteria) this;
		}

		public Criteria andClockInTimeEqualTo(Date value) {
			addCriterion("clock_in_time =", value, "clockInTime");
			return (Criteria) this;
		}

		public Criteria andClockInTimeNotEqualTo(Date value) {
			addCriterion("clock_in_time <>", value, "clockInTime");
			return (Criteria) this;
		}

		public Criteria andClockInTimeGreaterThan(Date value) {
			addCriterion("clock_in_time >", value, "clockInTime");
			return (Criteria) this;
		}

		public Criteria andClockInTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("clock_in_time >=", value, "clockInTime");
			return (Criteria) this;
		}

		public Criteria andClockInTimeLessThan(Date value) {
			addCriterion("clock_in_time <", value, "clockInTime");
			return (Criteria) this;
		}

		public Criteria andClockInTimeLessThanOrEqualTo(Date value) {
			addCriterion("clock_in_time <=", value, "clockInTime");
			return (Criteria) this;
		}

		public Criteria andClockInTimeIn(List<Date> values) {
			addCriterion("clock_in_time in", values, "clockInTime");
			return (Criteria) this;
		}

		public Criteria andClockInTimeNotIn(List<Date> values) {
			addCriterion("clock_in_time not in", values, "clockInTime");
			return (Criteria) this;
		}

		public Criteria andClockInTimeBetween(Date value1, Date value2) {
			addCriterion("clock_in_time between", value1, value2, "clockInTime");
			return (Criteria) this;
		}

		public Criteria andClockInTimeNotBetween(Date value1, Date value2) {
			addCriterion("clock_in_time not between", value1, value2, "clockInTime");
			return (Criteria) this;
		}

		public Criteria andClockOutTimeIsNull() {
			addCriterion("clock_out_time is null");
			return (Criteria) this;
		}

		public Criteria andClockOutTimeIsNotNull() {
			addCriterion("clock_out_time is not null");
			return (Criteria) this;
		}

		public Criteria andClockOutTimeEqualTo(Date value) {
			addCriterion("clock_out_time =", value, "clockOutTime");
			return (Criteria) this;
		}

		public Criteria andClockOutTimeNotEqualTo(Date value) {
			addCriterion("clock_out_time <>", value, "clockOutTime");
			return (Criteria) this;
		}

		public Criteria andClockOutTimeGreaterThan(Date value) {
			addCriterion("clock_out_time >", value, "clockOutTime");
			return (Criteria) this;
		}

		public Criteria andClockOutTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("clock_out_time >=", value, "clockOutTime");
			return (Criteria) this;
		}

		public Criteria andClockOutTimeLessThan(Date value) {
			addCriterion("clock_out_time <", value, "clockOutTime");
			return (Criteria) this;
		}

		public Criteria andClockOutTimeLessThanOrEqualTo(Date value) {
			addCriterion("clock_out_time <=", value, "clockOutTime");
			return (Criteria) this;
		}

		public Criteria andClockOutTimeIn(List<Date> values) {
			addCriterion("clock_out_time in", values, "clockOutTime");
			return (Criteria) this;
		}

		public Criteria andClockOutTimeNotIn(List<Date> values) {
			addCriterion("clock_out_time not in", values, "clockOutTime");
			return (Criteria) this;
		}

		public Criteria andClockOutTimeBetween(Date value1, Date value2) {
			addCriterion("clock_out_time between", value1, value2, "clockOutTime");
			return (Criteria) this;
		}

		public Criteria andClockOutTimeNotBetween(Date value1, Date value2) {
			addCriterion("clock_out_time not between", value1, value2, "clockOutTime");
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
			addCriterion("row_add_time not between", value1, value2, "rowAddTime");
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
			addCriterion("row_update_time between", value1, value2, "rowUpdateTime");
			return (Criteria) this;
		}

		public Criteria andRowUpdateTimeNotBetween(Date value1, Date value2) {
			addCriterion("row_update_time not between", value1, value2, "rowUpdateTime");
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
			addCriterion("community_id not between", value1, value2, "communityId");
			return (Criteria) this;
		}

		public Criteria andInIntervalIsNull() {
			addCriterion("in_interval is null");
			return (Criteria) this;
		}

		public Criteria andInIntervalIsNotNull() {
			addCriterion("in_interval is not null");
			return (Criteria) this;
		}

		public Criteria andInIntervalEqualTo(Double value) {
			addCriterion("in_interval =", value, "inInterval");
			return (Criteria) this;
		}

		public Criteria andInIntervalNotEqualTo(Double value) {
			addCriterion("in_interval <>", value, "inInterval");
			return (Criteria) this;
		}

		public Criteria andInIntervalGreaterThan(Double value) {
			addCriterion("in_interval >", value, "inInterval");
			return (Criteria) this;
		}

		public Criteria andInIntervalGreaterThanOrEqualTo(Double value) {
			addCriterion("in_interval >=", value, "inInterval");
			return (Criteria) this;
		}

		public Criteria andInIntervalLessThan(Double value) {
			addCriterion("in_interval <", value, "inInterval");
			return (Criteria) this;
		}

		public Criteria andInIntervalLessThanOrEqualTo(Double value) {
			addCriterion("in_interval <=", value, "inInterval");
			return (Criteria) this;
		}

		public Criteria andInIntervalIn(List<Double> values) {
			addCriterion("in_interval in", values, "inInterval");
			return (Criteria) this;
		}

		public Criteria andInIntervalNotIn(List<Double> values) {
			addCriterion("in_interval not in", values, "inInterval");
			return (Criteria) this;
		}

		public Criteria andInIntervalBetween(Double value1, Double value2) {
			addCriterion("in_interval between", value1, value2, "inInterval");
			return (Criteria) this;
		}

		public Criteria andInIntervalNotBetween(Double value1, Double value2) {
			addCriterion("in_interval not between", value1, value2, "inInterval");
			return (Criteria) this;
		}

		public Criteria andOutIntervalIsNull() {
			addCriterion("out_interval is null");
			return (Criteria) this;
		}

		public Criteria andOutIntervalIsNotNull() {
			addCriterion("out_interval is not null");
			return (Criteria) this;
		}

		public Criteria andOutIntervalEqualTo(Double value) {
			addCriterion("out_interval =", value, "outInterval");
			return (Criteria) this;
		}

		public Criteria andOutIntervalNotEqualTo(Double value) {
			addCriterion("out_interval <>", value, "outInterval");
			return (Criteria) this;
		}

		public Criteria andOutIntervalGreaterThan(Double value) {
			addCriterion("out_interval >", value, "outInterval");
			return (Criteria) this;
		}

		public Criteria andOutIntervalGreaterThanOrEqualTo(Double value) {
			addCriterion("out_interval >=", value, "outInterval");
			return (Criteria) this;
		}

		public Criteria andOutIntervalLessThan(Double value) {
			addCriterion("out_interval <", value, "outInterval");
			return (Criteria) this;
		}

		public Criteria andOutIntervalLessThanOrEqualTo(Double value) {
			addCriterion("out_interval <=", value, "outInterval");
			return (Criteria) this;
		}

		public Criteria andOutIntervalIn(List<Double> values) {
			addCriterion("out_interval in", values, "outInterval");
			return (Criteria) this;
		}

		public Criteria andOutIntervalNotIn(List<Double> values) {
			addCriterion("out_interval not in", values, "outInterval");
			return (Criteria) this;
		}

		public Criteria andOutIntervalBetween(Double value1, Double value2) {
			addCriterion("out_interval between", value1, value2, "outInterval");
			return (Criteria) this;
		}

		public Criteria andOutIntervalNotBetween(Double value1, Double value2) {
			addCriterion("out_interval not between", value1, value2, "outInterval");
			return (Criteria) this;
		}

		public Criteria andAllowOutsideIsNull() {
			addCriterion("allow_outside is null");
			return (Criteria) this;
		}

		public Criteria andAllowOutsideIsNotNull() {
			addCriterion("allow_outside is not null");
			return (Criteria) this;
		}

		public Criteria andAllowOutsideEqualTo(Integer value) {
			addCriterion("allow_outside =", value, "allowOutside");
			return (Criteria) this;
		}

		public Criteria andAllowOutsideNotEqualTo(Integer value) {
			addCriterion("allow_outside <>", value, "allowOutside");
			return (Criteria) this;
		}

		public Criteria andAllowOutsideGreaterThan(Integer value) {
			addCriterion("allow_outside >", value, "allowOutside");
			return (Criteria) this;
		}

		public Criteria andAllowOutsideGreaterThanOrEqualTo(Integer value) {
			addCriterion("allow_outside >=", value, "allowOutside");
			return (Criteria) this;
		}

		public Criteria andAllowOutsideLessThan(Integer value) {
			addCriterion("allow_outside <", value, "allowOutside");
			return (Criteria) this;
		}

		public Criteria andAllowOutsideLessThanOrEqualTo(Integer value) {
			addCriterion("allow_outside <=", value, "allowOutside");
			return (Criteria) this;
		}

		public Criteria andAllowOutsideIn(List<Integer> values) {
			addCriterion("allow_outside in", values, "allowOutside");
			return (Criteria) this;
		}

		public Criteria andAllowOutsideNotIn(List<Integer> values) {
			addCriterion("allow_outside not in", values, "allowOutside");
			return (Criteria) this;
		}

		public Criteria andAllowOutsideBetween(Integer value1, Integer value2) {
			addCriterion("allow_outside between", value1, value2, "allowOutside");
			return (Criteria) this;
		}

		public Criteria andAllowOutsideNotBetween(Integer value1, Integer value2) {
			addCriterion("allow_outside not between", value1, value2, "allowOutside");
			return (Criteria) this;
		}

		public Criteria andRegionIsNull() {
			addCriterion("region is null");
			return (Criteria) this;
		}

		public Criteria andRegionIsNotNull() {
			addCriterion("region is not null");
			return (Criteria) this;
		}

		public Criteria andRegionEqualTo(String value) {
			addCriterion("region =", value, "region");
			return (Criteria) this;
		}

		public Criteria andRegionNotEqualTo(String value) {
			addCriterion("region <>", value, "region");
			return (Criteria) this;
		}

		public Criteria andRegionGreaterThan(String value) {
			addCriterion("region >", value, "region");
			return (Criteria) this;
		}

		public Criteria andRegionGreaterThanOrEqualTo(String value) {
			addCriterion("region >=", value, "region");
			return (Criteria) this;
		}

		public Criteria andRegionLessThan(String value) {
			addCriterion("region <", value, "region");
			return (Criteria) this;
		}

		public Criteria andRegionLessThanOrEqualTo(String value) {
			addCriterion("region <=", value, "region");
			return (Criteria) this;
		}

		public Criteria andRegionLike(String value) {
			addCriterion("region like", value, "region");
			return (Criteria) this;
		}

		public Criteria andRegionNotLike(String value) {
			addCriterion("region not like", value, "region");
			return (Criteria) this;
		}

		public Criteria andRegionIn(List<String> values) {
			addCriterion("region in", values, "region");
			return (Criteria) this;
		}

		public Criteria andRegionNotIn(List<String> values) {
			addCriterion("region not in", values, "region");
			return (Criteria) this;
		}

		public Criteria andRegionBetween(String value1, String value2) {
			addCriterion("region between", value1, value2, "region");
			return (Criteria) this;
		}

		public Criteria andRegionNotBetween(String value1, String value2) {
			addCriterion("region not between", value1, value2, "region");
			return (Criteria) this;
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table n_service_clock_role_config
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}