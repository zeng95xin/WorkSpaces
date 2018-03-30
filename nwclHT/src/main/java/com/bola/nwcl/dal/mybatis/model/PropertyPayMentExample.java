package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.ModelExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PropertyPayMentExample extends ModelExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table n_property_pay_ment
	 * @mbggenerated
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table n_property_pay_ment
	 * @mbggenerated
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table n_property_pay_ment
	 * @mbggenerated
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_property_pay_ment
	 * @mbggenerated
	 */
	public PropertyPayMentExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_property_pay_ment
	 * @mbggenerated
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_property_pay_ment
	 * @mbggenerated
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_property_pay_ment
	 * @mbggenerated
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_property_pay_ment
	 * @mbggenerated
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_property_pay_ment
	 * @mbggenerated
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_property_pay_ment
	 * @mbggenerated
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_property_pay_ment
	 * @mbggenerated
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_property_pay_ment
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
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_property_pay_ment
	 * @mbggenerated
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_property_pay_ment
	 * @mbggenerated
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_property_pay_ment
	 * @mbggenerated
	 */
	public Criteria getCriteria() {
		if (oredCriteria.size() == 0) {
			createCriteria();
		}
		return oredCriteria.get(0);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_property_pay_ment
	 * @mbggenerated
	 */
	public void addCriteria(List<Criterion> criteria) {
		getCriteria().getCriteria().addAll(criteria);
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table n_property_pay_ment
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

		public Criteria andCustomerNameIsNull() {
			addCriterion("customer_name is null");
			return (Criteria) this;
		}

		public Criteria andCustomerNameIsNotNull() {
			addCriterion("customer_name is not null");
			return (Criteria) this;
		}

		public Criteria andCustomerNameEqualTo(String value) {
			addCriterion("customer_name =", value, "customerName");
			return (Criteria) this;
		}

		public Criteria andCustomerNameNotEqualTo(String value) {
			addCriterion("customer_name <>", value, "customerName");
			return (Criteria) this;
		}

		public Criteria andCustomerNameGreaterThan(String value) {
			addCriterion("customer_name >", value, "customerName");
			return (Criteria) this;
		}

		public Criteria andCustomerNameGreaterThanOrEqualTo(String value) {
			addCriterion("customer_name >=", value, "customerName");
			return (Criteria) this;
		}

		public Criteria andCustomerNameLessThan(String value) {
			addCriterion("customer_name <", value, "customerName");
			return (Criteria) this;
		}

		public Criteria andCustomerNameLessThanOrEqualTo(String value) {
			addCriterion("customer_name <=", value, "customerName");
			return (Criteria) this;
		}

		public Criteria andCustomerNameLike(String value) {
			addCriterion("customer_name like", value, "customerName");
			return (Criteria) this;
		}

		public Criteria andCustomerNameNotLike(String value) {
			addCriterion("customer_name not like", value, "customerName");
			return (Criteria) this;
		}

		public Criteria andCustomerNameIn(List<String> values) {
			addCriterion("customer_name in", values, "customerName");
			return (Criteria) this;
		}

		public Criteria andCustomerNameNotIn(List<String> values) {
			addCriterion("customer_name not in", values, "customerName");
			return (Criteria) this;
		}

		public Criteria andCustomerNameBetween(String value1, String value2) {
			addCriterion("customer_name between", value1, value2,
					"customerName");
			return (Criteria) this;
		}

		public Criteria andCustomerNameNotBetween(String value1, String value2) {
			addCriterion("customer_name not between", value1, value2,
					"customerName");
			return (Criteria) this;
		}

		public Criteria andUnitNoIsNull() {
			addCriterion("unit_no is null");
			return (Criteria) this;
		}

		public Criteria andUnitNoIsNotNull() {
			addCriterion("unit_no is not null");
			return (Criteria) this;
		}

		public Criteria andUnitNoEqualTo(String value) {
			addCriterion("unit_no =", value, "unitNo");
			return (Criteria) this;
		}

		public Criteria andUnitNoNotEqualTo(String value) {
			addCriterion("unit_no <>", value, "unitNo");
			return (Criteria) this;
		}

		public Criteria andUnitNoGreaterThan(String value) {
			addCriterion("unit_no >", value, "unitNo");
			return (Criteria) this;
		}

		public Criteria andUnitNoGreaterThanOrEqualTo(String value) {
			addCriterion("unit_no >=", value, "unitNo");
			return (Criteria) this;
		}

		public Criteria andUnitNoLessThan(String value) {
			addCriterion("unit_no <", value, "unitNo");
			return (Criteria) this;
		}

		public Criteria andUnitNoLessThanOrEqualTo(String value) {
			addCriterion("unit_no <=", value, "unitNo");
			return (Criteria) this;
		}

		public Criteria andUnitNoLike(String value) {
			addCriterion("unit_no like", value, "unitNo");
			return (Criteria) this;
		}

		public Criteria andUnitNoNotLike(String value) {
			addCriterion("unit_no not like", value, "unitNo");
			return (Criteria) this;
		}

		public Criteria andUnitNoIn(List<String> values) {
			addCriterion("unit_no in", values, "unitNo");
			return (Criteria) this;
		}

		public Criteria andUnitNoNotIn(List<String> values) {
			addCriterion("unit_no not in", values, "unitNo");
			return (Criteria) this;
		}

		public Criteria andUnitNoBetween(String value1, String value2) {
			addCriterion("unit_no between", value1, value2, "unitNo");
			return (Criteria) this;
		}

		public Criteria andUnitNoNotBetween(String value1, String value2) {
			addCriterion("unit_no not between", value1, value2, "unitNo");
			return (Criteria) this;
		}

		public Criteria andReceivableIdIsNull() {
			addCriterion("receivable_id is null");
			return (Criteria) this;
		}

		public Criteria andReceivableIdIsNotNull() {
			addCriterion("receivable_id is not null");
			return (Criteria) this;
		}

		public Criteria andReceivableIdEqualTo(Long value) {
			addCriterion("receivable_id =", value, "receivableId");
			return (Criteria) this;
		}

		public Criteria andReceivableIdNotEqualTo(Long value) {
			addCriterion("receivable_id <>", value, "receivableId");
			return (Criteria) this;
		}

		public Criteria andReceivableIdGreaterThan(Long value) {
			addCriterion("receivable_id >", value, "receivableId");
			return (Criteria) this;
		}

		public Criteria andReceivableIdGreaterThanOrEqualTo(Long value) {
			addCriterion("receivable_id >=", value, "receivableId");
			return (Criteria) this;
		}

		public Criteria andReceivableIdLessThan(Long value) {
			addCriterion("receivable_id <", value, "receivableId");
			return (Criteria) this;
		}

		public Criteria andReceivableIdLessThanOrEqualTo(Long value) {
			addCriterion("receivable_id <=", value, "receivableId");
			return (Criteria) this;
		}

		public Criteria andReceivableIdIn(List<Long> values) {
			addCriterion("receivable_id in", values, "receivableId");
			return (Criteria) this;
		}

		public Criteria andReceivableIdNotIn(List<Long> values) {
			addCriterion("receivable_id not in", values, "receivableId");
			return (Criteria) this;
		}

		public Criteria andReceivableIdBetween(Long value1, Long value2) {
			addCriterion("receivable_id between", value1, value2,
					"receivableId");
			return (Criteria) this;
		}

		public Criteria andReceivableIdNotBetween(Long value1, Long value2) {
			addCriterion("receivable_id not between", value1, value2,
					"receivableId");
			return (Criteria) this;
		}

		public Criteria andReceivableDispnameIsNull() {
			addCriterion("receivable_dispName is null");
			return (Criteria) this;
		}

		public Criteria andReceivableDispnameIsNotNull() {
			addCriterion("receivable_dispName is not null");
			return (Criteria) this;
		}

		public Criteria andReceivableDispnameEqualTo(String value) {
			addCriterion("receivable_dispName =", value, "receivableDispname");
			return (Criteria) this;
		}

		public Criteria andReceivableDispnameNotEqualTo(String value) {
			addCriterion("receivable_dispName <>", value, "receivableDispname");
			return (Criteria) this;
		}

		public Criteria andReceivableDispnameGreaterThan(String value) {
			addCriterion("receivable_dispName >", value, "receivableDispname");
			return (Criteria) this;
		}

		public Criteria andReceivableDispnameGreaterThanOrEqualTo(String value) {
			addCriterion("receivable_dispName >=", value, "receivableDispname");
			return (Criteria) this;
		}

		public Criteria andReceivableDispnameLessThan(String value) {
			addCriterion("receivable_dispName <", value, "receivableDispname");
			return (Criteria) this;
		}

		public Criteria andReceivableDispnameLessThanOrEqualTo(String value) {
			addCriterion("receivable_dispName <=", value, "receivableDispname");
			return (Criteria) this;
		}

		public Criteria andReceivableDispnameLike(String value) {
			addCriterion("receivable_dispName like", value,
					"receivableDispname");
			return (Criteria) this;
		}

		public Criteria andReceivableDispnameNotLike(String value) {
			addCriterion("receivable_dispName not like", value,
					"receivableDispname");
			return (Criteria) this;
		}

		public Criteria andReceivableDispnameIn(List<String> values) {
			addCriterion("receivable_dispName in", values, "receivableDispname");
			return (Criteria) this;
		}

		public Criteria andReceivableDispnameNotIn(List<String> values) {
			addCriterion("receivable_dispName not in", values,
					"receivableDispname");
			return (Criteria) this;
		}

		public Criteria andReceivableDispnameBetween(String value1,
				String value2) {
			addCriterion("receivable_dispName between", value1, value2,
					"receivableDispname");
			return (Criteria) this;
		}

		public Criteria andReceivableDispnameNotBetween(String value1,
				String value2) {
			addCriterion("receivable_dispName not between", value1, value2,
					"receivableDispname");
			return (Criteria) this;
		}

		public Criteria andReceivableDateIsNull() {
			addCriterion("receivable_date is null");
			return (Criteria) this;
		}

		public Criteria andReceivableDateIsNotNull() {
			addCriterion("receivable_date is not null");
			return (Criteria) this;
		}

		public Criteria andReceivableDateEqualTo(Date value) {
			addCriterion("receivable_date =", value, "receivableDate");
			return (Criteria) this;
		}

		public Criteria andReceivableDateNotEqualTo(Date value) {
			addCriterion("receivable_date <>", value, "receivableDate");
			return (Criteria) this;
		}

		public Criteria andReceivableDateGreaterThan(Date value) {
			addCriterion("receivable_date >", value, "receivableDate");
			return (Criteria) this;
		}

		public Criteria andReceivableDateGreaterThanOrEqualTo(Date value) {
			addCriterion("receivable_date >=", value, "receivableDate");
			return (Criteria) this;
		}

		public Criteria andReceivableDateLessThan(Date value) {
			addCriterion("receivable_date <", value, "receivableDate");
			return (Criteria) this;
		}

		public Criteria andReceivableDateLessThanOrEqualTo(Date value) {
			addCriterion("receivable_date <=", value, "receivableDate");
			return (Criteria) this;
		}

		public Criteria andReceivableDateIn(List<Date> values) {
			addCriterion("receivable_date in", values, "receivableDate");
			return (Criteria) this;
		}

		public Criteria andReceivableDateNotIn(List<Date> values) {
			addCriterion("receivable_date not in", values, "receivableDate");
			return (Criteria) this;
		}

		public Criteria andReceivableDateBetween(Date value1, Date value2) {
			addCriterion("receivable_date between", value1, value2,
					"receivableDate");
			return (Criteria) this;
		}

		public Criteria andReceivableDateNotBetween(Date value1, Date value2) {
			addCriterion("receivable_date not between", value1, value2,
					"receivableDate");
			return (Criteria) this;
		}

		public Criteria andReceivableAmountIsNull() {
			addCriterion("receivable_amount is null");
			return (Criteria) this;
		}

		public Criteria andReceivableAmountIsNotNull() {
			addCriterion("receivable_amount is not null");
			return (Criteria) this;
		}

		public Criteria andReceivableAmountEqualTo(Integer value) {
			addCriterion("receivable_amount =", value, "receivableAmount");
			return (Criteria) this;
		}

		public Criteria andReceivableAmountNotEqualTo(Integer value) {
			addCriterion("receivable_amount <>", value, "receivableAmount");
			return (Criteria) this;
		}

		public Criteria andReceivableAmountGreaterThan(Integer value) {
			addCriterion("receivable_amount >", value, "receivableAmount");
			return (Criteria) this;
		}

		public Criteria andReceivableAmountGreaterThanOrEqualTo(Integer value) {
			addCriterion("receivable_amount >=", value, "receivableAmount");
			return (Criteria) this;
		}

		public Criteria andReceivableAmountLessThan(Integer value) {
			addCriterion("receivable_amount <", value, "receivableAmount");
			return (Criteria) this;
		}

		public Criteria andReceivableAmountLessThanOrEqualTo(Integer value) {
			addCriterion("receivable_amount <=", value, "receivableAmount");
			return (Criteria) this;
		}

		public Criteria andReceivableAmountIn(List<Integer> values) {
			addCriterion("receivable_amount in", values, "receivableAmount");
			return (Criteria) this;
		}

		public Criteria andReceivableAmountNotIn(List<Integer> values) {
			addCriterion("receivable_amount not in", values, "receivableAmount");
			return (Criteria) this;
		}

		public Criteria andReceivableAmountBetween(Integer value1,
				Integer value2) {
			addCriterion("receivable_amount between", value1, value2,
					"receivableAmount");
			return (Criteria) this;
		}

		public Criteria andReceivableAmountNotBetween(Integer value1,
				Integer value2) {
			addCriterion("receivable_amount not between", value1, value2,
					"receivableAmount");
			return (Criteria) this;
		}

		public Criteria andCurrAmountIsNull() {
			addCriterion("curr_amount is null");
			return (Criteria) this;
		}

		public Criteria andCurrAmountIsNotNull() {
			addCriterion("curr_amount is not null");
			return (Criteria) this;
		}

		public Criteria andCurrAmountEqualTo(Integer value) {
			addCriterion("curr_amount =", value, "currAmount");
			return (Criteria) this;
		}

		public Criteria andCurrAmountNotEqualTo(Integer value) {
			addCriterion("curr_amount <>", value, "currAmount");
			return (Criteria) this;
		}

		public Criteria andCurrAmountGreaterThan(Integer value) {
			addCriterion("curr_amount >", value, "currAmount");
			return (Criteria) this;
		}

		public Criteria andCurrAmountGreaterThanOrEqualTo(Integer value) {
			addCriterion("curr_amount >=", value, "currAmount");
			return (Criteria) this;
		}

		public Criteria andCurrAmountLessThan(Integer value) {
			addCriterion("curr_amount <", value, "currAmount");
			return (Criteria) this;
		}

		public Criteria andCurrAmountLessThanOrEqualTo(Integer value) {
			addCriterion("curr_amount <=", value, "currAmount");
			return (Criteria) this;
		}

		public Criteria andCurrAmountIn(List<Integer> values) {
			addCriterion("curr_amount in", values, "currAmount");
			return (Criteria) this;
		}

		public Criteria andCurrAmountNotIn(List<Integer> values) {
			addCriterion("curr_amount not in", values, "currAmount");
			return (Criteria) this;
		}

		public Criteria andCurrAmountBetween(Integer value1, Integer value2) {
			addCriterion("curr_amount between", value1, value2, "currAmount");
			return (Criteria) this;
		}

		public Criteria andCurrAmountNotBetween(Integer value1, Integer value2) {
			addCriterion("curr_amount not between", value1, value2,
					"currAmount");
			return (Criteria) this;
		}

		public Criteria andReceivableStatusIsNull() {
			addCriterion("receivable_status is null");
			return (Criteria) this;
		}

		public Criteria andReceivableStatusIsNotNull() {
			addCriterion("receivable_status is not null");
			return (Criteria) this;
		}

		public Criteria andReceivableStatusEqualTo(Integer value) {
			addCriterion("receivable_status =", value, "receivableStatus");
			return (Criteria) this;
		}

		public Criteria andReceivableStatusNotEqualTo(Integer value) {
			addCriterion("receivable_status <>", value, "receivableStatus");
			return (Criteria) this;
		}

		public Criteria andReceivableStatusGreaterThan(Integer value) {
			addCriterion("receivable_status >", value, "receivableStatus");
			return (Criteria) this;
		}

		public Criteria andReceivableStatusGreaterThanOrEqualTo(Integer value) {
			addCriterion("receivable_status >=", value, "receivableStatus");
			return (Criteria) this;
		}

		public Criteria andReceivableStatusLessThan(Integer value) {
			addCriterion("receivable_status <", value, "receivableStatus");
			return (Criteria) this;
		}

		public Criteria andReceivableStatusLessThanOrEqualTo(Integer value) {
			addCriterion("receivable_status <=", value, "receivableStatus");
			return (Criteria) this;
		}

		public Criteria andReceivableStatusIn(List<Integer> values) {
			addCriterion("receivable_status in", values, "receivableStatus");
			return (Criteria) this;
		}

		public Criteria andReceivableStatusNotIn(List<Integer> values) {
			addCriterion("receivable_status not in", values, "receivableStatus");
			return (Criteria) this;
		}

		public Criteria andReceivableStatusBetween(Integer value1,
				Integer value2) {
			addCriterion("receivable_status between", value1, value2,
					"receivableStatus");
			return (Criteria) this;
		}

		public Criteria andReceivableStatusNotBetween(Integer value1,
				Integer value2) {
			addCriterion("receivable_status not between", value1, value2,
					"receivableStatus");
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

		public Criteria andPropertyDateIsNull() {
			addCriterion("property_date is null");
			return (Criteria) this;
		}

		public Criteria andPropertyDateIsNotNull() {
			addCriterion("property_date is not null");
			return (Criteria) this;
		}

		public Criteria andPropertyDateEqualTo(Date value) {
			addCriterion("property_date =", value, "propertyDate");
			return (Criteria) this;
		}

		public Criteria andPropertyDateNotEqualTo(Date value) {
			addCriterion("property_date <>", value, "propertyDate");
			return (Criteria) this;
		}

		public Criteria andPropertyDateGreaterThan(Date value) {
			addCriterion("property_date >", value, "propertyDate");
			return (Criteria) this;
		}

		public Criteria andPropertyDateGreaterThanOrEqualTo(Date value) {
			addCriterion("property_date >=", value, "propertyDate");
			return (Criteria) this;
		}

		public Criteria andPropertyDateLessThan(Date value) {
			addCriterion("property_date <", value, "propertyDate");
			return (Criteria) this;
		}

		public Criteria andPropertyDateLessThanOrEqualTo(Date value) {
			addCriterion("property_date <=", value, "propertyDate");
			return (Criteria) this;
		}

		public Criteria andPropertyDateIn(List<Date> values) {
			addCriterion("property_date in", values, "propertyDate");
			return (Criteria) this;
		}

		public Criteria andPropertyDateNotIn(List<Date> values) {
			addCriterion("property_date not in", values, "propertyDate");
			return (Criteria) this;
		}

		public Criteria andPropertyDateBetween(Date value1, Date value2) {
			addCriterion("property_date between", value1, value2,
					"propertyDate");
			return (Criteria) this;
		}

		public Criteria andPropertyDateNotBetween(Date value1, Date value2) {
			addCriterion("property_date not between", value1, value2,
					"propertyDate");
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
     * This class corresponds to the database table n_property_pay_ment
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}