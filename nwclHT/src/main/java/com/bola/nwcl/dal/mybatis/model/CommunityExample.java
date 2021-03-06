package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.ModelExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommunityExample extends ModelExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table n_community
	 * @mbggenerated
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table n_community
	 * @mbggenerated
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table n_community
	 * @mbggenerated
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_community
	 * @mbggenerated
	 */
	public CommunityExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_community
	 * @mbggenerated
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_community
	 * @mbggenerated
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_community
	 * @mbggenerated
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_community
	 * @mbggenerated
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_community
	 * @mbggenerated
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_community
	 * @mbggenerated
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_community
	 * @mbggenerated
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_community
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
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_community
	 * @mbggenerated
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_community
	 * @mbggenerated
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_community
	 * @mbggenerated
	 */
	public Criteria getCriteria() {
		if (oredCriteria.size() == 0) {
			createCriteria();
		}
		return oredCriteria.get(0);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_community
	 * @mbggenerated
	 */
	public void addCriteria(List<Criterion> criteria) {
		getCriteria().getCriteria().addAll(criteria);
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table n_community
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

		public Criteria andPropertyIdIsNull() {
			addCriterion("property_id is null");
			return (Criteria) this;
		}

		public Criteria andPropertyIdIsNotNull() {
			addCriterion("property_id is not null");
			return (Criteria) this;
		}

		public Criteria andPropertyIdEqualTo(Long value) {
			addCriterion("property_id =", value, "propertyId");
			return (Criteria) this;
		}

		public Criteria andPropertyIdNotEqualTo(Long value) {
			addCriterion("property_id <>", value, "propertyId");
			return (Criteria) this;
		}

		public Criteria andPropertyIdGreaterThan(Long value) {
			addCriterion("property_id >", value, "propertyId");
			return (Criteria) this;
		}

		public Criteria andPropertyIdGreaterThanOrEqualTo(Long value) {
			addCriterion("property_id >=", value, "propertyId");
			return (Criteria) this;
		}

		public Criteria andPropertyIdLessThan(Long value) {
			addCriterion("property_id <", value, "propertyId");
			return (Criteria) this;
		}

		public Criteria andPropertyIdLessThanOrEqualTo(Long value) {
			addCriterion("property_id <=", value, "propertyId");
			return (Criteria) this;
		}

		public Criteria andPropertyIdIn(List<Long> values) {
			addCriterion("property_id in", values, "propertyId");
			return (Criteria) this;
		}

		public Criteria andPropertyIdNotIn(List<Long> values) {
			addCriterion("property_id not in", values, "propertyId");
			return (Criteria) this;
		}

		public Criteria andPropertyIdBetween(Long value1, Long value2) {
			addCriterion("property_id between", value1, value2, "propertyId");
			return (Criteria) this;
		}

		public Criteria andPropertyIdNotBetween(Long value1, Long value2) {
			addCriterion("property_id not between", value1, value2,
					"propertyId");
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

		public Criteria andNameIsNull() {
			addCriterion("name is null");
			return (Criteria) this;
		}

		public Criteria andNameIsNotNull() {
			addCriterion("name is not null");
			return (Criteria) this;
		}

		public Criteria andNameEqualTo(String value) {
			addCriterion("name =", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotEqualTo(String value) {
			addCriterion("name <>", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThan(String value) {
			addCriterion("name >", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThanOrEqualTo(String value) {
			addCriterion("name >=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThan(String value) {
			addCriterion("name <", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThanOrEqualTo(String value) {
			addCriterion("name <=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLike(String value) {
			addCriterion("name like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotLike(String value) {
			addCriterion("name not like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameIn(List<String> values) {
			addCriterion("name in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotIn(List<String> values) {
			addCriterion("name not in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameBetween(String value1, String value2) {
			addCriterion("name between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotBetween(String value1, String value2) {
			addCriterion("name not between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andAddressIsNull() {
			addCriterion("address is null");
			return (Criteria) this;
		}

		public Criteria andAddressIsNotNull() {
			addCriterion("address is not null");
			return (Criteria) this;
		}

		public Criteria andAddressEqualTo(String value) {
			addCriterion("address =", value, "address");
			return (Criteria) this;
		}

		public Criteria andAddressNotEqualTo(String value) {
			addCriterion("address <>", value, "address");
			return (Criteria) this;
		}

		public Criteria andAddressGreaterThan(String value) {
			addCriterion("address >", value, "address");
			return (Criteria) this;
		}

		public Criteria andAddressGreaterThanOrEqualTo(String value) {
			addCriterion("address >=", value, "address");
			return (Criteria) this;
		}

		public Criteria andAddressLessThan(String value) {
			addCriterion("address <", value, "address");
			return (Criteria) this;
		}

		public Criteria andAddressLessThanOrEqualTo(String value) {
			addCriterion("address <=", value, "address");
			return (Criteria) this;
		}

		public Criteria andAddressLike(String value) {
			addCriterion("address like", value, "address");
			return (Criteria) this;
		}

		public Criteria andAddressNotLike(String value) {
			addCriterion("address not like", value, "address");
			return (Criteria) this;
		}

		public Criteria andAddressIn(List<String> values) {
			addCriterion("address in", values, "address");
			return (Criteria) this;
		}

		public Criteria andAddressNotIn(List<String> values) {
			addCriterion("address not in", values, "address");
			return (Criteria) this;
		}

		public Criteria andAddressBetween(String value1, String value2) {
			addCriterion("address between", value1, value2, "address");
			return (Criteria) this;
		}

		public Criteria andAddressNotBetween(String value1, String value2) {
			addCriterion("address not between", value1, value2, "address");
			return (Criteria) this;
		}

		public Criteria andContactPeopleIsNull() {
			addCriterion("contact_people is null");
			return (Criteria) this;
		}

		public Criteria andContactPeopleIsNotNull() {
			addCriterion("contact_people is not null");
			return (Criteria) this;
		}

		public Criteria andContactPeopleEqualTo(String value) {
			addCriterion("contact_people =", value, "contactPeople");
			return (Criteria) this;
		}

		public Criteria andContactPeopleNotEqualTo(String value) {
			addCriterion("contact_people <>", value, "contactPeople");
			return (Criteria) this;
		}

		public Criteria andContactPeopleGreaterThan(String value) {
			addCriterion("contact_people >", value, "contactPeople");
			return (Criteria) this;
		}

		public Criteria andContactPeopleGreaterThanOrEqualTo(String value) {
			addCriterion("contact_people >=", value, "contactPeople");
			return (Criteria) this;
		}

		public Criteria andContactPeopleLessThan(String value) {
			addCriterion("contact_people <", value, "contactPeople");
			return (Criteria) this;
		}

		public Criteria andContactPeopleLessThanOrEqualTo(String value) {
			addCriterion("contact_people <=", value, "contactPeople");
			return (Criteria) this;
		}

		public Criteria andContactPeopleLike(String value) {
			addCriterion("contact_people like", value, "contactPeople");
			return (Criteria) this;
		}

		public Criteria andContactPeopleNotLike(String value) {
			addCriterion("contact_people not like", value, "contactPeople");
			return (Criteria) this;
		}

		public Criteria andContactPeopleIn(List<String> values) {
			addCriterion("contact_people in", values, "contactPeople");
			return (Criteria) this;
		}

		public Criteria andContactPeopleNotIn(List<String> values) {
			addCriterion("contact_people not in", values, "contactPeople");
			return (Criteria) this;
		}

		public Criteria andContactPeopleBetween(String value1, String value2) {
			addCriterion("contact_people between", value1, value2,
					"contactPeople");
			return (Criteria) this;
		}

		public Criteria andContactPeopleNotBetween(String value1, String value2) {
			addCriterion("contact_people not between", value1, value2,
					"contactPeople");
			return (Criteria) this;
		}

		public Criteria andContactPhoneIsNull() {
			addCriterion("contact_phone is null");
			return (Criteria) this;
		}

		public Criteria andContactPhoneIsNotNull() {
			addCriterion("contact_phone is not null");
			return (Criteria) this;
		}

		public Criteria andContactPhoneEqualTo(String value) {
			addCriterion("contact_phone =", value, "contactPhone");
			return (Criteria) this;
		}

		public Criteria andContactPhoneNotEqualTo(String value) {
			addCriterion("contact_phone <>", value, "contactPhone");
			return (Criteria) this;
		}

		public Criteria andContactPhoneGreaterThan(String value) {
			addCriterion("contact_phone >", value, "contactPhone");
			return (Criteria) this;
		}

		public Criteria andContactPhoneGreaterThanOrEqualTo(String value) {
			addCriterion("contact_phone >=", value, "contactPhone");
			return (Criteria) this;
		}

		public Criteria andContactPhoneLessThan(String value) {
			addCriterion("contact_phone <", value, "contactPhone");
			return (Criteria) this;
		}

		public Criteria andContactPhoneLessThanOrEqualTo(String value) {
			addCriterion("contact_phone <=", value, "contactPhone");
			return (Criteria) this;
		}

		public Criteria andContactPhoneLike(String value) {
			addCriterion("contact_phone like", value, "contactPhone");
			return (Criteria) this;
		}

		public Criteria andContactPhoneNotLike(String value) {
			addCriterion("contact_phone not like", value, "contactPhone");
			return (Criteria) this;
		}

		public Criteria andContactPhoneIn(List<String> values) {
			addCriterion("contact_phone in", values, "contactPhone");
			return (Criteria) this;
		}

		public Criteria andContactPhoneNotIn(List<String> values) {
			addCriterion("contact_phone not in", values, "contactPhone");
			return (Criteria) this;
		}

		public Criteria andContactPhoneBetween(String value1, String value2) {
			addCriterion("contact_phone between", value1, value2,
					"contactPhone");
			return (Criteria) this;
		}

		public Criteria andContactPhoneNotBetween(String value1, String value2) {
			addCriterion("contact_phone not between", value1, value2,
					"contactPhone");
			return (Criteria) this;
		}

		public Criteria andNoteIsNull() {
			addCriterion("note is null");
			return (Criteria) this;
		}

		public Criteria andNoteIsNotNull() {
			addCriterion("note is not null");
			return (Criteria) this;
		}

		public Criteria andNoteEqualTo(String value) {
			addCriterion("note =", value, "note");
			return (Criteria) this;
		}

		public Criteria andNoteNotEqualTo(String value) {
			addCriterion("note <>", value, "note");
			return (Criteria) this;
		}

		public Criteria andNoteGreaterThan(String value) {
			addCriterion("note >", value, "note");
			return (Criteria) this;
		}

		public Criteria andNoteGreaterThanOrEqualTo(String value) {
			addCriterion("note >=", value, "note");
			return (Criteria) this;
		}

		public Criteria andNoteLessThan(String value) {
			addCriterion("note <", value, "note");
			return (Criteria) this;
		}

		public Criteria andNoteLessThanOrEqualTo(String value) {
			addCriterion("note <=", value, "note");
			return (Criteria) this;
		}

		public Criteria andNoteLike(String value) {
			addCriterion("note like", value, "note");
			return (Criteria) this;
		}

		public Criteria andNoteNotLike(String value) {
			addCriterion("note not like", value, "note");
			return (Criteria) this;
		}

		public Criteria andNoteIn(List<String> values) {
			addCriterion("note in", values, "note");
			return (Criteria) this;
		}

		public Criteria andNoteNotIn(List<String> values) {
			addCriterion("note not in", values, "note");
			return (Criteria) this;
		}

		public Criteria andNoteBetween(String value1, String value2) {
			addCriterion("note between", value1, value2, "note");
			return (Criteria) this;
		}

		public Criteria andNoteNotBetween(String value1, String value2) {
			addCriterion("note not between", value1, value2, "note");
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

		public Criteria andDeviceIsNull() {
			addCriterion("device is null");
			return (Criteria) this;
		}

		public Criteria andDeviceIsNotNull() {
			addCriterion("device is not null");
			return (Criteria) this;
		}

		public Criteria andDeviceEqualTo(String value) {
			addCriterion("device =", value, "device");
			return (Criteria) this;
		}

		public Criteria andDeviceNotEqualTo(String value) {
			addCriterion("device <>", value, "device");
			return (Criteria) this;
		}

		public Criteria andDeviceGreaterThan(String value) {
			addCriterion("device >", value, "device");
			return (Criteria) this;
		}

		public Criteria andDeviceGreaterThanOrEqualTo(String value) {
			addCriterion("device >=", value, "device");
			return (Criteria) this;
		}

		public Criteria andDeviceLessThan(String value) {
			addCriterion("device <", value, "device");
			return (Criteria) this;
		}

		public Criteria andDeviceLessThanOrEqualTo(String value) {
			addCriterion("device <=", value, "device");
			return (Criteria) this;
		}

		public Criteria andDeviceLike(String value) {
			addCriterion("device like", value, "device");
			return (Criteria) this;
		}

		public Criteria andDeviceNotLike(String value) {
			addCriterion("device not like", value, "device");
			return (Criteria) this;
		}

		public Criteria andDeviceIn(List<String> values) {
			addCriterion("device in", values, "device");
			return (Criteria) this;
		}

		public Criteria andDeviceNotIn(List<String> values) {
			addCriterion("device not in", values, "device");
			return (Criteria) this;
		}

		public Criteria andDeviceBetween(String value1, String value2) {
			addCriterion("device between", value1, value2, "device");
			return (Criteria) this;
		}

		public Criteria andDeviceNotBetween(String value1, String value2) {
			addCriterion("device not between", value1, value2, "device");
			return (Criteria) this;
		}

		public Criteria andDoorTypeIdIsNull() {
			addCriterion("door_type_id is null");
			return (Criteria) this;
		}

		public Criteria andDoorTypeIdIsNotNull() {
			addCriterion("door_type_id is not null");
			return (Criteria) this;
		}

		public Criteria andDoorTypeIdEqualTo(Long value) {
			addCriterion("door_type_id =", value, "doorTypeId");
			return (Criteria) this;
		}

		public Criteria andDoorTypeIdNotEqualTo(Long value) {
			addCriterion("door_type_id <>", value, "doorTypeId");
			return (Criteria) this;
		}

		public Criteria andDoorTypeIdGreaterThan(Long value) {
			addCriterion("door_type_id >", value, "doorTypeId");
			return (Criteria) this;
		}

		public Criteria andDoorTypeIdGreaterThanOrEqualTo(Long value) {
			addCriterion("door_type_id >=", value, "doorTypeId");
			return (Criteria) this;
		}

		public Criteria andDoorTypeIdLessThan(Long value) {
			addCriterion("door_type_id <", value, "doorTypeId");
			return (Criteria) this;
		}

		public Criteria andDoorTypeIdLessThanOrEqualTo(Long value) {
			addCriterion("door_type_id <=", value, "doorTypeId");
			return (Criteria) this;
		}

		public Criteria andDoorTypeIdIn(List<Long> values) {
			addCriterion("door_type_id in", values, "doorTypeId");
			return (Criteria) this;
		}

		public Criteria andDoorTypeIdNotIn(List<Long> values) {
			addCriterion("door_type_id not in", values, "doorTypeId");
			return (Criteria) this;
		}

		public Criteria andDoorTypeIdBetween(Long value1, Long value2) {
			addCriterion("door_type_id between", value1, value2, "doorTypeId");
			return (Criteria) this;
		}

		public Criteria andDoorTypeIdNotBetween(Long value1, Long value2) {
			addCriterion("door_type_id not between", value1, value2,
					"doorTypeId");
			return (Criteria) this;
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table n_community
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}