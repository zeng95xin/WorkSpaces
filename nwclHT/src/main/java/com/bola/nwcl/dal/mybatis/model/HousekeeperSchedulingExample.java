package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.ModelExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HousekeeperSchedulingExample extends ModelExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table n_housekeeper_scheduling
	 * @mbggenerated
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table n_housekeeper_scheduling
	 * @mbggenerated
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table n_housekeeper_scheduling
	 * @mbggenerated
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_housekeeper_scheduling
	 * @mbggenerated
	 */
	public HousekeeperSchedulingExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_housekeeper_scheduling
	 * @mbggenerated
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_housekeeper_scheduling
	 * @mbggenerated
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_housekeeper_scheduling
	 * @mbggenerated
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_housekeeper_scheduling
	 * @mbggenerated
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_housekeeper_scheduling
	 * @mbggenerated
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_housekeeper_scheduling
	 * @mbggenerated
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_housekeeper_scheduling
	 * @mbggenerated
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_housekeeper_scheduling
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
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_housekeeper_scheduling
	 * @mbggenerated
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_housekeeper_scheduling
	 * @mbggenerated
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_housekeeper_scheduling
	 * @mbggenerated
	 */
	public Criteria getCriteria() {
		if (oredCriteria.size() == 0) {
			createCriteria();
		}
		return oredCriteria.get(0);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_housekeeper_scheduling
	 * @mbggenerated
	 */
	public void addCriteria(List<Criterion> criteria) {
		getCriteria().getCriteria().addAll(criteria);
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table n_housekeeper_scheduling
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

		public Criteria andHousekeeperIdIsNull() {
			addCriterion("housekeeper_id is null");
			return (Criteria) this;
		}

		public Criteria andHousekeeperIdIsNotNull() {
			addCriterion("housekeeper_id is not null");
			return (Criteria) this;
		}

		public Criteria andHousekeeperIdEqualTo(Long value) {
			addCriterion("housekeeper_id =", value, "housekeeperId");
			return (Criteria) this;
		}

		public Criteria andHousekeeperIdNotEqualTo(Long value) {
			addCriterion("housekeeper_id <>", value, "housekeeperId");
			return (Criteria) this;
		}

		public Criteria andHousekeeperIdGreaterThan(Long value) {
			addCriterion("housekeeper_id >", value, "housekeeperId");
			return (Criteria) this;
		}

		public Criteria andHousekeeperIdGreaterThanOrEqualTo(Long value) {
			addCriterion("housekeeper_id >=", value, "housekeeperId");
			return (Criteria) this;
		}

		public Criteria andHousekeeperIdLessThan(Long value) {
			addCriterion("housekeeper_id <", value, "housekeeperId");
			return (Criteria) this;
		}

		public Criteria andHousekeeperIdLessThanOrEqualTo(Long value) {
			addCriterion("housekeeper_id <=", value, "housekeeperId");
			return (Criteria) this;
		}

		public Criteria andHousekeeperIdIn(List<Long> values) {
			addCriterion("housekeeper_id in", values, "housekeeperId");
			return (Criteria) this;
		}

		public Criteria andHousekeeperIdNotIn(List<Long> values) {
			addCriterion("housekeeper_id not in", values, "housekeeperId");
			return (Criteria) this;
		}

		public Criteria andHousekeeperIdBetween(Long value1, Long value2) {
			addCriterion("housekeeper_id between", value1, value2,
					"housekeeperId");
			return (Criteria) this;
		}

		public Criteria andHousekeeperIdNotBetween(Long value1, Long value2) {
			addCriterion("housekeeper_id not between", value1, value2,
					"housekeeperId");
			return (Criteria) this;
		}

		public Criteria andSchedulingUserIdIsNull() {
			addCriterion("scheduling_user_id is null");
			return (Criteria) this;
		}

		public Criteria andSchedulingUserIdIsNotNull() {
			addCriterion("scheduling_user_id is not null");
			return (Criteria) this;
		}

		public Criteria andSchedulingUserIdEqualTo(Long value) {
			addCriterion("scheduling_user_id =", value, "schedulingUserId");
			return (Criteria) this;
		}

		public Criteria andSchedulingUserIdNotEqualTo(Long value) {
			addCriterion("scheduling_user_id <>", value, "schedulingUserId");
			return (Criteria) this;
		}

		public Criteria andSchedulingUserIdGreaterThan(Long value) {
			addCriterion("scheduling_user_id >", value, "schedulingUserId");
			return (Criteria) this;
		}

		public Criteria andSchedulingUserIdGreaterThanOrEqualTo(Long value) {
			addCriterion("scheduling_user_id >=", value, "schedulingUserId");
			return (Criteria) this;
		}

		public Criteria andSchedulingUserIdLessThan(Long value) {
			addCriterion("scheduling_user_id <", value, "schedulingUserId");
			return (Criteria) this;
		}

		public Criteria andSchedulingUserIdLessThanOrEqualTo(Long value) {
			addCriterion("scheduling_user_id <=", value, "schedulingUserId");
			return (Criteria) this;
		}

		public Criteria andSchedulingUserIdIn(List<Long> values) {
			addCriterion("scheduling_user_id in", values, "schedulingUserId");
			return (Criteria) this;
		}

		public Criteria andSchedulingUserIdNotIn(List<Long> values) {
			addCriterion("scheduling_user_id not in", values,
					"schedulingUserId");
			return (Criteria) this;
		}

		public Criteria andSchedulingUserIdBetween(Long value1, Long value2) {
			addCriterion("scheduling_user_id between", value1, value2,
					"schedulingUserId");
			return (Criteria) this;
		}

		public Criteria andSchedulingUserIdNotBetween(Long value1, Long value2) {
			addCriterion("scheduling_user_id not between", value1, value2,
					"schedulingUserId");
			return (Criteria) this;
		}

		public Criteria andBuildingIdIsNull() {
			addCriterion("building_id is null");
			return (Criteria) this;
		}

		public Criteria andBuildingIdIsNotNull() {
			addCriterion("building_id is not null");
			return (Criteria) this;
		}

		public Criteria andBuildingIdEqualTo(Long value) {
			addCriterion("building_id =", value, "buildingId");
			return (Criteria) this;
		}

		public Criteria andBuildingIdNotEqualTo(Long value) {
			addCriterion("building_id <>", value, "buildingId");
			return (Criteria) this;
		}

		public Criteria andBuildingIdGreaterThan(Long value) {
			addCriterion("building_id >", value, "buildingId");
			return (Criteria) this;
		}

		public Criteria andBuildingIdGreaterThanOrEqualTo(Long value) {
			addCriterion("building_id >=", value, "buildingId");
			return (Criteria) this;
		}

		public Criteria andBuildingIdLessThan(Long value) {
			addCriterion("building_id <", value, "buildingId");
			return (Criteria) this;
		}

		public Criteria andBuildingIdLessThanOrEqualTo(Long value) {
			addCriterion("building_id <=", value, "buildingId");
			return (Criteria) this;
		}

		public Criteria andBuildingIdIn(List<Long> values) {
			addCriterion("building_id in", values, "buildingId");
			return (Criteria) this;
		}

		public Criteria andBuildingIdNotIn(List<Long> values) {
			addCriterion("building_id not in", values, "buildingId");
			return (Criteria) this;
		}

		public Criteria andBuildingIdBetween(Long value1, Long value2) {
			addCriterion("building_id between", value1, value2, "buildingId");
			return (Criteria) this;
		}

		public Criteria andBuildingIdNotBetween(Long value1, Long value2) {
			addCriterion("building_id not between", value1, value2,
					"buildingId");
			return (Criteria) this;
		}

		public Criteria andDutyTimeIsNull() {
			addCriterion("duty_time is null");
			return (Criteria) this;
		}

		public Criteria andDutyTimeIsNotNull() {
			addCriterion("duty_time is not null");
			return (Criteria) this;
		}

		public Criteria andDutyTimeEqualTo(Date value) {
			addCriterion("duty_time =", value, "dutyTime");
			return (Criteria) this;
		}

		public Criteria andDutyTimeNotEqualTo(Date value) {
			addCriterion("duty_time <>", value, "dutyTime");
			return (Criteria) this;
		}

		public Criteria andDutyTimeGreaterThan(Date value) {
			addCriterion("duty_time >", value, "dutyTime");
			return (Criteria) this;
		}

		public Criteria andDutyTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("duty_time >=", value, "dutyTime");
			return (Criteria) this;
		}

		public Criteria andDutyTimeLessThan(Date value) {
			addCriterion("duty_time <", value, "dutyTime");
			return (Criteria) this;
		}

		public Criteria andDutyTimeLessThanOrEqualTo(Date value) {
			addCriterion("duty_time <=", value, "dutyTime");
			return (Criteria) this;
		}

		public Criteria andDutyTimeIn(List<Date> values) {
			addCriterion("duty_time in", values, "dutyTime");
			return (Criteria) this;
		}

		public Criteria andDutyTimeNotIn(List<Date> values) {
			addCriterion("duty_time not in", values, "dutyTime");
			return (Criteria) this;
		}

		public Criteria andDutyTimeBetween(Date value1, Date value2) {
			addCriterion("duty_time between", value1, value2, "dutyTime");
			return (Criteria) this;
		}

		public Criteria andDutyTimeNotBetween(Date value1, Date value2) {
			addCriterion("duty_time not between", value1, value2, "dutyTime");
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
     * This class corresponds to the database table n_housekeeper_scheduling
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}