package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.ModelExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoomExample extends ModelExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table n_room
	 * @mbggenerated
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table n_room
	 * @mbggenerated
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table n_room
	 * @mbggenerated
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_room
	 * @mbggenerated
	 */
	public RoomExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_room
	 * @mbggenerated
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_room
	 * @mbggenerated
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_room
	 * @mbggenerated
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_room
	 * @mbggenerated
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_room
	 * @mbggenerated
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_room
	 * @mbggenerated
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_room
	 * @mbggenerated
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_room
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
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_room
	 * @mbggenerated
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_room
	 * @mbggenerated
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_room
	 * @mbggenerated
	 */
	public Criteria getCriteria() {
		if (oredCriteria.size() == 0) {
			createCriteria();
		}
		return oredCriteria.get(0);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_room
	 * @mbggenerated
	 */
	public void addCriteria(List<Criterion> criteria) {
		getCriteria().getCriteria().addAll(criteria);
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table n_room
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

		public Criteria andFloorIsNull() {
			addCriterion("floor is null");
			return (Criteria) this;
		}

		public Criteria andFloorIsNotNull() {
			addCriterion("floor is not null");
			return (Criteria) this;
		}

		public Criteria andFloorEqualTo(String value) {
			addCriterion("floor =", value, "floor");
			return (Criteria) this;
		}

		public Criteria andFloorNotEqualTo(String value) {
			addCriterion("floor <>", value, "floor");
			return (Criteria) this;
		}

		public Criteria andFloorGreaterThan(String value) {
			addCriterion("floor >", value, "floor");
			return (Criteria) this;
		}

		public Criteria andFloorGreaterThanOrEqualTo(String value) {
			addCriterion("floor >=", value, "floor");
			return (Criteria) this;
		}

		public Criteria andFloorLessThan(String value) {
			addCriterion("floor <", value, "floor");
			return (Criteria) this;
		}

		public Criteria andFloorLessThanOrEqualTo(String value) {
			addCriterion("floor <=", value, "floor");
			return (Criteria) this;
		}

		public Criteria andFloorLike(String value) {
			addCriterion("floor like", value, "floor");
			return (Criteria) this;
		}

		public Criteria andFloorNotLike(String value) {
			addCriterion("floor not like", value, "floor");
			return (Criteria) this;
		}

		public Criteria andFloorIn(List<String> values) {
			addCriterion("floor in", values, "floor");
			return (Criteria) this;
		}

		public Criteria andFloorNotIn(List<String> values) {
			addCriterion("floor not in", values, "floor");
			return (Criteria) this;
		}

		public Criteria andFloorBetween(String value1, String value2) {
			addCriterion("floor between", value1, value2, "floor");
			return (Criteria) this;
		}

		public Criteria andFloorNotBetween(String value1, String value2) {
			addCriterion("floor not between", value1, value2, "floor");
			return (Criteria) this;
		}

		public Criteria andUnitIsNull() {
			addCriterion("unit is null");
			return (Criteria) this;
		}

		public Criteria andUnitIsNotNull() {
			addCriterion("unit is not null");
			return (Criteria) this;
		}

		public Criteria andUnitEqualTo(String value) {
			addCriterion("unit =", value, "unit");
			return (Criteria) this;
		}

		public Criteria andUnitNotEqualTo(String value) {
			addCriterion("unit <>", value, "unit");
			return (Criteria) this;
		}

		public Criteria andUnitGreaterThan(String value) {
			addCriterion("unit >", value, "unit");
			return (Criteria) this;
		}

		public Criteria andUnitGreaterThanOrEqualTo(String value) {
			addCriterion("unit >=", value, "unit");
			return (Criteria) this;
		}

		public Criteria andUnitLessThan(String value) {
			addCriterion("unit <", value, "unit");
			return (Criteria) this;
		}

		public Criteria andUnitLessThanOrEqualTo(String value) {
			addCriterion("unit <=", value, "unit");
			return (Criteria) this;
		}

		public Criteria andUnitLike(String value) {
			addCriterion("unit like", value, "unit");
			return (Criteria) this;
		}

		public Criteria andUnitNotLike(String value) {
			addCriterion("unit not like", value, "unit");
			return (Criteria) this;
		}

		public Criteria andUnitIn(List<String> values) {
			addCriterion("unit in", values, "unit");
			return (Criteria) this;
		}

		public Criteria andUnitNotIn(List<String> values) {
			addCriterion("unit not in", values, "unit");
			return (Criteria) this;
		}

		public Criteria andUnitBetween(String value1, String value2) {
			addCriterion("unit between", value1, value2, "unit");
			return (Criteria) this;
		}

		public Criteria andUnitNotBetween(String value1, String value2) {
			addCriterion("unit not between", value1, value2, "unit");
			return (Criteria) this;
		}

		public Criteria andRoomNoIsNull() {
			addCriterion("room_no is null");
			return (Criteria) this;
		}

		public Criteria andRoomNoIsNotNull() {
			addCriterion("room_no is not null");
			return (Criteria) this;
		}

		public Criteria andRoomNoEqualTo(String value) {
			addCriterion("room_no =", value, "roomNo");
			return (Criteria) this;
		}

		public Criteria andRoomNoNotEqualTo(String value) {
			addCriterion("room_no <>", value, "roomNo");
			return (Criteria) this;
		}

		public Criteria andRoomNoGreaterThan(String value) {
			addCriterion("room_no >", value, "roomNo");
			return (Criteria) this;
		}

		public Criteria andRoomNoGreaterThanOrEqualTo(String value) {
			addCriterion("room_no >=", value, "roomNo");
			return (Criteria) this;
		}

		public Criteria andRoomNoLessThan(String value) {
			addCriterion("room_no <", value, "roomNo");
			return (Criteria) this;
		}

		public Criteria andRoomNoLessThanOrEqualTo(String value) {
			addCriterion("room_no <=", value, "roomNo");
			return (Criteria) this;
		}

		public Criteria andRoomNoLike(String value) {
			addCriterion("room_no like", value, "roomNo");
			return (Criteria) this;
		}

		public Criteria andRoomNoNotLike(String value) {
			addCriterion("room_no not like", value, "roomNo");
			return (Criteria) this;
		}

		public Criteria andRoomNoIn(List<String> values) {
			addCriterion("room_no in", values, "roomNo");
			return (Criteria) this;
		}

		public Criteria andRoomNoNotIn(List<String> values) {
			addCriterion("room_no not in", values, "roomNo");
			return (Criteria) this;
		}

		public Criteria andRoomNoBetween(String value1, String value2) {
			addCriterion("room_no between", value1, value2, "roomNo");
			return (Criteria) this;
		}

		public Criteria andRoomNoNotBetween(String value1, String value2) {
			addCriterion("room_no not between", value1, value2, "roomNo");
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
     * This class corresponds to the database table n_room
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}