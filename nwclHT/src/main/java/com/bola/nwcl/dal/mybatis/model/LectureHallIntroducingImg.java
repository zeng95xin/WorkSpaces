package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.Model;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class LectureHallIntroducingImg extends Model {

	/**
	 * 自增id
	 * @mbggenerated
	 */
	private Long id;
	/**
	 * 业主自荐id
	 * @mbggenerated
	 */
	private Long lectureHallIntroducingId;
	/**
	 * 图片路径
	 * @mbggenerated
	 */
	private String imgPath;
	/**
	 * 缩略图路径
	 * @mbggenerated
	 */
	private String imgPathThumbnail;
	/**
	 * 生成时间
	 * @mbggenerated
	 */
	private Date rowAddTime;
	/**
	 * 修改时间
	 * @mbggenerated
	 */
	private Date rowUpdateTime;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_lecture_hall_introducing_img.id
	 * @return  the value of n_lecture_hall_introducing_img.id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "自增id")
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_lecture_hall_introducing_img.id
	 * @param id  the value for n_lecture_hall_introducing_img.id
	 * @mbggenerated
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_lecture_hall_introducing_img.lecture_hall_introducing_id
	 * @return  the value of n_lecture_hall_introducing_img.lecture_hall_introducing_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "业主自荐id")
	public Long getLectureHallIntroducingId() {
		return lectureHallIntroducingId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_lecture_hall_introducing_img.lecture_hall_introducing_id
	 * @param lectureHallIntroducingId  the value for n_lecture_hall_introducing_img.lecture_hall_introducing_id
	 * @mbggenerated
	 */
	public void setLectureHallIntroducingId(Long lectureHallIntroducingId) {
		this.lectureHallIntroducingId = lectureHallIntroducingId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_lecture_hall_introducing_img.img_path
	 * @return  the value of n_lecture_hall_introducing_img.img_path
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "图片路径")
	public String getImgPath() {
		return imgPath;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_lecture_hall_introducing_img.img_path
	 * @param imgPath  the value for n_lecture_hall_introducing_img.img_path
	 * @mbggenerated
	 */
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath == null ? null : imgPath.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_lecture_hall_introducing_img.img_path_thumbnail
	 * @return  the value of n_lecture_hall_introducing_img.img_path_thumbnail
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "缩略图路径")
	public String getImgPathThumbnail() {
		return imgPathThumbnail;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_lecture_hall_introducing_img.img_path_thumbnail
	 * @param imgPathThumbnail  the value for n_lecture_hall_introducing_img.img_path_thumbnail
	 * @mbggenerated
	 */
	public void setImgPathThumbnail(String imgPathThumbnail) {
		this.imgPathThumbnail = imgPathThumbnail == null ? null
				: imgPathThumbnail.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_lecture_hall_introducing_img.row_add_time
	 * @return  the value of n_lecture_hall_introducing_img.row_add_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "生成时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_lecture_hall_introducing_img.row_add_time
	 * @param rowAddTime  the value for n_lecture_hall_introducing_img.row_add_time
	 * @mbggenerated
	 */
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_lecture_hall_introducing_img.row_update_time
	 * @return  the value of n_lecture_hall_introducing_img.row_update_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "修改时间")
	public Date getRowUpdateTime() {
		return rowUpdateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_lecture_hall_introducing_img.row_update_time
	 * @param rowUpdateTime  the value for n_lecture_hall_introducing_img.row_update_time
	 * @mbggenerated
	 */
	public void setRowUpdateTime(Date rowUpdateTime) {
		this.rowUpdateTime = rowUpdateTime;
	}
}