package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.ModelExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LikeShareExample extends ModelExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table n_like_share
	 * @mbggenerated
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table n_like_share
	 * @mbggenerated
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table n_like_share
	 * @mbggenerated
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_like_share
	 * @mbggenerated
	 */
	public LikeShareExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_like_share
	 * @mbggenerated
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_like_share
	 * @mbggenerated
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_like_share
	 * @mbggenerated
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_like_share
	 * @mbggenerated
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_like_share
	 * @mbggenerated
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_like_share
	 * @mbggenerated
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_like_share
	 * @mbggenerated
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_like_share
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
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_like_share
	 * @mbggenerated
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_like_share
	 * @mbggenerated
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_like_share
	 * @mbggenerated
	 */
	public Criteria getCriteria() {
		if (oredCriteria.size() == 0) {
			createCriteria();
		}
		return oredCriteria.get(0);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table n_like_share
	 * @mbggenerated
	 */
	public void addCriteria(List<Criterion> criteria) {
		getCriteria().getCriteria().addAll(criteria);
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table n_like_share
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

		public Criteria andTitleIsNull() {
			addCriterion("title is null");
			return (Criteria) this;
		}

		public Criteria andTitleIsNotNull() {
			addCriterion("title is not null");
			return (Criteria) this;
		}

		public Criteria andTitleEqualTo(String value) {
			addCriterion("title =", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotEqualTo(String value) {
			addCriterion("title <>", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleGreaterThan(String value) {
			addCriterion("title >", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleGreaterThanOrEqualTo(String value) {
			addCriterion("title >=", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleLessThan(String value) {
			addCriterion("title <", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleLessThanOrEqualTo(String value) {
			addCriterion("title <=", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleLike(String value) {
			addCriterion("title like", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotLike(String value) {
			addCriterion("title not like", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleIn(List<String> values) {
			addCriterion("title in", values, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotIn(List<String> values) {
			addCriterion("title not in", values, "title");
			return (Criteria) this;
		}

		public Criteria andTitleBetween(String value1, String value2) {
			addCriterion("title between", value1, value2, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotBetween(String value1, String value2) {
			addCriterion("title not between", value1, value2, "title");
			return (Criteria) this;
		}

		public Criteria andContentIsNull() {
			addCriterion("content is null");
			return (Criteria) this;
		}

		public Criteria andContentIsNotNull() {
			addCriterion("content is not null");
			return (Criteria) this;
		}

		public Criteria andContentEqualTo(String value) {
			addCriterion("content =", value, "content");
			return (Criteria) this;
		}

		public Criteria andContentNotEqualTo(String value) {
			addCriterion("content <>", value, "content");
			return (Criteria) this;
		}

		public Criteria andContentGreaterThan(String value) {
			addCriterion("content >", value, "content");
			return (Criteria) this;
		}

		public Criteria andContentGreaterThanOrEqualTo(String value) {
			addCriterion("content >=", value, "content");
			return (Criteria) this;
		}

		public Criteria andContentLessThan(String value) {
			addCriterion("content <", value, "content");
			return (Criteria) this;
		}

		public Criteria andContentLessThanOrEqualTo(String value) {
			addCriterion("content <=", value, "content");
			return (Criteria) this;
		}

		public Criteria andContentLike(String value) {
			addCriterion("content like", value, "content");
			return (Criteria) this;
		}

		public Criteria andContentNotLike(String value) {
			addCriterion("content not like", value, "content");
			return (Criteria) this;
		}

		public Criteria andContentIn(List<String> values) {
			addCriterion("content in", values, "content");
			return (Criteria) this;
		}

		public Criteria andContentNotIn(List<String> values) {
			addCriterion("content not in", values, "content");
			return (Criteria) this;
		}

		public Criteria andContentBetween(String value1, String value2) {
			addCriterion("content between", value1, value2, "content");
			return (Criteria) this;
		}

		public Criteria andContentNotBetween(String value1, String value2) {
			addCriterion("content not between", value1, value2, "content");
			return (Criteria) this;
		}

		public Criteria andShareTypeIsNull() {
			addCriterion("share_type is null");
			return (Criteria) this;
		}

		public Criteria andShareTypeIsNotNull() {
			addCriterion("share_type is not null");
			return (Criteria) this;
		}

		public Criteria andShareTypeEqualTo(String value) {
			addCriterion("share_type =", value, "shareType");
			return (Criteria) this;
		}

		public Criteria andShareTypeNotEqualTo(String value) {
			addCriterion("share_type <>", value, "shareType");
			return (Criteria) this;
		}

		public Criteria andShareTypeGreaterThan(String value) {
			addCriterion("share_type >", value, "shareType");
			return (Criteria) this;
		}

		public Criteria andShareTypeGreaterThanOrEqualTo(String value) {
			addCriterion("share_type >=", value, "shareType");
			return (Criteria) this;
		}

		public Criteria andShareTypeLessThan(String value) {
			addCriterion("share_type <", value, "shareType");
			return (Criteria) this;
		}

		public Criteria andShareTypeLessThanOrEqualTo(String value) {
			addCriterion("share_type <=", value, "shareType");
			return (Criteria) this;
		}

		public Criteria andShareTypeLike(String value) {
			addCriterion("share_type like", value, "shareType");
			return (Criteria) this;
		}

		public Criteria andShareTypeNotLike(String value) {
			addCriterion("share_type not like", value, "shareType");
			return (Criteria) this;
		}

		public Criteria andShareTypeIn(List<String> values) {
			addCriterion("share_type in", values, "shareType");
			return (Criteria) this;
		}

		public Criteria andShareTypeNotIn(List<String> values) {
			addCriterion("share_type not in", values, "shareType");
			return (Criteria) this;
		}

		public Criteria andShareTypeBetween(String value1, String value2) {
			addCriterion("share_type between", value1, value2, "shareType");
			return (Criteria) this;
		}

		public Criteria andShareTypeNotBetween(String value1, String value2) {
			addCriterion("share_type not between", value1, value2, "shareType");
			return (Criteria) this;
		}

		public Criteria andIsSensitiveIsNull() {
			addCriterion("is_sensitive is null");
			return (Criteria) this;
		}

		public Criteria andIsSensitiveIsNotNull() {
			addCriterion("is_sensitive is not null");
			return (Criteria) this;
		}

		public Criteria andIsSensitiveEqualTo(Integer value) {
			addCriterion("is_sensitive =", value, "isSensitive");
			return (Criteria) this;
		}

		public Criteria andIsSensitiveNotEqualTo(Integer value) {
			addCriterion("is_sensitive <>", value, "isSensitive");
			return (Criteria) this;
		}

		public Criteria andIsSensitiveGreaterThan(Integer value) {
			addCriterion("is_sensitive >", value, "isSensitive");
			return (Criteria) this;
		}

		public Criteria andIsSensitiveGreaterThanOrEqualTo(Integer value) {
			addCriterion("is_sensitive >=", value, "isSensitive");
			return (Criteria) this;
		}

		public Criteria andIsSensitiveLessThan(Integer value) {
			addCriterion("is_sensitive <", value, "isSensitive");
			return (Criteria) this;
		}

		public Criteria andIsSensitiveLessThanOrEqualTo(Integer value) {
			addCriterion("is_sensitive <=", value, "isSensitive");
			return (Criteria) this;
		}

		public Criteria andIsSensitiveIn(List<Integer> values) {
			addCriterion("is_sensitive in", values, "isSensitive");
			return (Criteria) this;
		}

		public Criteria andIsSensitiveNotIn(List<Integer> values) {
			addCriterion("is_sensitive not in", values, "isSensitive");
			return (Criteria) this;
		}

		public Criteria andIsSensitiveBetween(Integer value1, Integer value2) {
			addCriterion("is_sensitive between", value1, value2, "isSensitive");
			return (Criteria) this;
		}

		public Criteria andIsSensitiveNotBetween(Integer value1, Integer value2) {
			addCriterion("is_sensitive not between", value1, value2,
					"isSensitive");
			return (Criteria) this;
		}

		public Criteria andReadCountIsNull() {
			addCriterion("read_count is null");
			return (Criteria) this;
		}

		public Criteria andReadCountIsNotNull() {
			addCriterion("read_count is not null");
			return (Criteria) this;
		}

		public Criteria andReadCountEqualTo(Integer value) {
			addCriterion("read_count =", value, "readCount");
			return (Criteria) this;
		}

		public Criteria andReadCountNotEqualTo(Integer value) {
			addCriterion("read_count <>", value, "readCount");
			return (Criteria) this;
		}

		public Criteria andReadCountGreaterThan(Integer value) {
			addCriterion("read_count >", value, "readCount");
			return (Criteria) this;
		}

		public Criteria andReadCountGreaterThanOrEqualTo(Integer value) {
			addCriterion("read_count >=", value, "readCount");
			return (Criteria) this;
		}

		public Criteria andReadCountLessThan(Integer value) {
			addCriterion("read_count <", value, "readCount");
			return (Criteria) this;
		}

		public Criteria andReadCountLessThanOrEqualTo(Integer value) {
			addCriterion("read_count <=", value, "readCount");
			return (Criteria) this;
		}

		public Criteria andReadCountIn(List<Integer> values) {
			addCriterion("read_count in", values, "readCount");
			return (Criteria) this;
		}

		public Criteria andReadCountNotIn(List<Integer> values) {
			addCriterion("read_count not in", values, "readCount");
			return (Criteria) this;
		}

		public Criteria andReadCountBetween(Integer value1, Integer value2) {
			addCriterion("read_count between", value1, value2, "readCount");
			return (Criteria) this;
		}

		public Criteria andReadCountNotBetween(Integer value1, Integer value2) {
			addCriterion("read_count not between", value1, value2, "readCount");
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

		public Criteria andLabelIsNull() {
			addCriterion("label is null");
			return (Criteria) this;
		}

		public Criteria andLabelIsNotNull() {
			addCriterion("label is not null");
			return (Criteria) this;
		}

		public Criteria andLabelEqualTo(String value) {
			addCriterion("label =", value, "label");
			return (Criteria) this;
		}

		public Criteria andLabelNotEqualTo(String value) {
			addCriterion("label <>", value, "label");
			return (Criteria) this;
		}

		public Criteria andLabelGreaterThan(String value) {
			addCriterion("label >", value, "label");
			return (Criteria) this;
		}

		public Criteria andLabelGreaterThanOrEqualTo(String value) {
			addCriterion("label >=", value, "label");
			return (Criteria) this;
		}

		public Criteria andLabelLessThan(String value) {
			addCriterion("label <", value, "label");
			return (Criteria) this;
		}

		public Criteria andLabelLessThanOrEqualTo(String value) {
			addCriterion("label <=", value, "label");
			return (Criteria) this;
		}

		public Criteria andLabelLike(String value) {
			addCriterion("label like", value, "label");
			return (Criteria) this;
		}

		public Criteria andLabelNotLike(String value) {
			addCriterion("label not like", value, "label");
			return (Criteria) this;
		}

		public Criteria andLabelIn(List<String> values) {
			addCriterion("label in", values, "label");
			return (Criteria) this;
		}

		public Criteria andLabelNotIn(List<String> values) {
			addCriterion("label not in", values, "label");
			return (Criteria) this;
		}

		public Criteria andLabelBetween(String value1, String value2) {
			addCriterion("label between", value1, value2, "label");
			return (Criteria) this;
		}

		public Criteria andLabelNotBetween(String value1, String value2) {
			addCriterion("label not between", value1, value2, "label");
			return (Criteria) this;
		}

		public Criteria andIsUserDeleteIsNull() {
			addCriterion("is_user_delete is null");
			return (Criteria) this;
		}

		public Criteria andIsUserDeleteIsNotNull() {
			addCriterion("is_user_delete is not null");
			return (Criteria) this;
		}

		public Criteria andIsUserDeleteEqualTo(Integer value) {
			addCriterion("is_user_delete =", value, "isUserDelete");
			return (Criteria) this;
		}

		public Criteria andIsUserDeleteNotEqualTo(Integer value) {
			addCriterion("is_user_delete <>", value, "isUserDelete");
			return (Criteria) this;
		}

		public Criteria andIsUserDeleteGreaterThan(Integer value) {
			addCriterion("is_user_delete >", value, "isUserDelete");
			return (Criteria) this;
		}

		public Criteria andIsUserDeleteGreaterThanOrEqualTo(Integer value) {
			addCriterion("is_user_delete >=", value, "isUserDelete");
			return (Criteria) this;
		}

		public Criteria andIsUserDeleteLessThan(Integer value) {
			addCriterion("is_user_delete <", value, "isUserDelete");
			return (Criteria) this;
		}

		public Criteria andIsUserDeleteLessThanOrEqualTo(Integer value) {
			addCriterion("is_user_delete <=", value, "isUserDelete");
			return (Criteria) this;
		}

		public Criteria andIsUserDeleteIn(List<Integer> values) {
			addCriterion("is_user_delete in", values, "isUserDelete");
			return (Criteria) this;
		}

		public Criteria andIsUserDeleteNotIn(List<Integer> values) {
			addCriterion("is_user_delete not in", values, "isUserDelete");
			return (Criteria) this;
		}

		public Criteria andIsUserDeleteBetween(Integer value1, Integer value2) {
			addCriterion("is_user_delete between", value1, value2,
					"isUserDelete");
			return (Criteria) this;
		}

		public Criteria andIsUserDeleteNotBetween(Integer value1, Integer value2) {
			addCriterion("is_user_delete not between", value1, value2,
					"isUserDelete");
			return (Criteria) this;
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table n_like_share
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}