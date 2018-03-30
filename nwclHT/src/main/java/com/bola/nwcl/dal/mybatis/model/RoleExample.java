package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.ModelExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoleExample extends ModelExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table n_role
	 * @mbggenerated
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table n_role
	 * @mbggenerated
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table n_role
	 * @mbggenerated
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_role
	 * @mbggenerated
	 */
	public RoleExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_role
	 * @mbggenerated
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_role
	 * @mbggenerated
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_role
	 * @mbggenerated
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_role
	 * @mbggenerated
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_role
	 * @mbggenerated
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_role
	 * @mbggenerated
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_role
	 * @mbggenerated
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_role
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
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_role
	 * @mbggenerated
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_role
	 * @mbggenerated
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_role
	 * @mbggenerated
	 */
	public Criteria getCriteria() {
		if (oredCriteria.size() == 0) {
			createCriteria();
		}
		return oredCriteria.get(0);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_role
	 * @mbggenerated
	 */
	public void addCriteria(List<Criterion> criteria) {
		getCriteria().getCriteria().addAll(criteria);
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table n_role
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

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andRoleNameIsNull() {
			addCriterion("role_name is null");
			return (Criteria) this;
		}

		public Criteria andRoleNameIsNotNull() {
			addCriterion("role_name is not null");
			return (Criteria) this;
		}

		public Criteria andRoleNameEqualTo(String value) {
			addCriterion("role_name =", value, "roleName");
			return (Criteria) this;
		}

		public Criteria andRoleNameNotEqualTo(String value) {
			addCriterion("role_name <>", value, "roleName");
			return (Criteria) this;
		}

		public Criteria andRoleNameGreaterThan(String value) {
			addCriterion("role_name >", value, "roleName");
			return (Criteria) this;
		}

		public Criteria andRoleNameGreaterThanOrEqualTo(String value) {
			addCriterion("role_name >=", value, "roleName");
			return (Criteria) this;
		}

		public Criteria andRoleNameLessThan(String value) {
			addCriterion("role_name <", value, "roleName");
			return (Criteria) this;
		}

		public Criteria andRoleNameLessThanOrEqualTo(String value) {
			addCriterion("role_name <=", value, "roleName");
			return (Criteria) this;
		}

		public Criteria andRoleNameLike(String value) {
			addCriterion("role_name like", value, "roleName");
			return (Criteria) this;
		}

		public Criteria andRoleNameNotLike(String value) {
			addCriterion("role_name not like", value, "roleName");
			return (Criteria) this;
		}

		public Criteria andRoleNameIn(List<String> values) {
			addCriterion("role_name in", values, "roleName");
			return (Criteria) this;
		}

		public Criteria andRoleNameNotIn(List<String> values) {
			addCriterion("role_name not in", values, "roleName");
			return (Criteria) this;
		}

		public Criteria andRoleNameBetween(String value1, String value2) {
			addCriterion("role_name between", value1, value2, "roleName");
			return (Criteria) this;
		}

		public Criteria andRoleNameNotBetween(String value1, String value2) {
			addCriterion("role_name not between", value1, value2, "roleName");
			return (Criteria) this;
		}

		public Criteria andRoleNoteIsNull() {
			addCriterion("role_note is null");
			return (Criteria) this;
		}

		public Criteria andRoleNoteIsNotNull() {
			addCriterion("role_note is not null");
			return (Criteria) this;
		}

		public Criteria andRoleNoteEqualTo(String value) {
			addCriterion("role_note =", value, "roleNote");
			return (Criteria) this;
		}

		public Criteria andRoleNoteNotEqualTo(String value) {
			addCriterion("role_note <>", value, "roleNote");
			return (Criteria) this;
		}

		public Criteria andRoleNoteGreaterThan(String value) {
			addCriterion("role_note >", value, "roleNote");
			return (Criteria) this;
		}

		public Criteria andRoleNoteGreaterThanOrEqualTo(String value) {
			addCriterion("role_note >=", value, "roleNote");
			return (Criteria) this;
		}

		public Criteria andRoleNoteLessThan(String value) {
			addCriterion("role_note <", value, "roleNote");
			return (Criteria) this;
		}

		public Criteria andRoleNoteLessThanOrEqualTo(String value) {
			addCriterion("role_note <=", value, "roleNote");
			return (Criteria) this;
		}

		public Criteria andRoleNoteLike(String value) {
			addCriterion("role_note like", value, "roleNote");
			return (Criteria) this;
		}

		public Criteria andRoleNoteNotLike(String value) {
			addCriterion("role_note not like", value, "roleNote");
			return (Criteria) this;
		}

		public Criteria andRoleNoteIn(List<String> values) {
			addCriterion("role_note in", values, "roleNote");
			return (Criteria) this;
		}

		public Criteria andRoleNoteNotIn(List<String> values) {
			addCriterion("role_note not in", values, "roleNote");
			return (Criteria) this;
		}

		public Criteria andRoleNoteBetween(String value1, String value2) {
			addCriterion("role_note between", value1, value2, "roleNote");
			return (Criteria) this;
		}

		public Criteria andRoleNoteNotBetween(String value1, String value2) {
			addCriterion("role_note not between", value1, value2, "roleNote");
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
     * This class corresponds to the database table n_role
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}