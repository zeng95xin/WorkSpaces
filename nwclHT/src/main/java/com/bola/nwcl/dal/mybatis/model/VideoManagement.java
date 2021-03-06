package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.Model;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class VideoManagement extends Model {

	/**
	 * 视频的ID
	 * @mbggenerated
	 */
	private Long id;
	/**
	 * 视频的地址
	 * @mbggenerated
	 */
	private String url;
	/**
	 * 视频的名称
	 * @mbggenerated
	 */
	private String name;
	/**
	 * 添加时间
	 * @mbggenerated
	 */
	private Date rowaddtime;
	/**
	 * 修改时间
	 * @mbggenerated
	 */
	private Date rowupdatetime;
	/**
	 * 设备码
	 * @mbggenerated
	 */
	private String appkey;
	/**
	 * 应用秘钥
	 * @mbggenerated
	 */
	private String secret;
	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_video_management.id
	 * @return  the value of n_video_management.id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "视频的ID")
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_video_management.id
	 * @param id  the value for n_video_management.id
	 * @mbggenerated
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_video_management.url
	 * @return  the value of n_video_management.url
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "视频的地址")
	public String getUrl() {
		return url;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_video_management.url
	 * @param url  the value for n_video_management.url
	 * @mbggenerated
	 */
	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_video_management.name
	 * @return  the value of n_video_management.name
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "视频的名称")
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_video_management.name
	 * @param name  the value for n_video_management.name
	 * @mbggenerated
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_video_management.rowAddTime
	 * @return  the value of n_video_management.rowAddTime
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "添加时间")
	public Date getRowaddtime() {
		return rowaddtime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_video_management.rowAddTime
	 * @param rowaddtime  the value for n_video_management.rowAddTime
	 * @mbggenerated
	 */
	public void setRowaddtime(Date rowaddtime) {
		this.rowaddtime = rowaddtime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_video_management.rowUpdateTime
	 * @return  the value of n_video_management.rowUpdateTime
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "修改时间")
	public Date getRowupdatetime() {
		return rowupdatetime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_video_management.rowUpdateTime
	 * @param rowupdatetime  the value for n_video_management.rowUpdateTime
	 * @mbggenerated
	 */
	public void setRowupdatetime(Date rowupdatetime) {
		this.rowupdatetime = rowupdatetime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_video_management.appKey
	 * @return  the value of n_video_management.appKey
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "设备码")
	public String getAppkey() {
		return appkey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_video_management.appKey
	 * @param appkey  the value for n_video_management.appKey
	 * @mbggenerated
	 */
	public void setAppkey(String appkey) {
		this.appkey = appkey == null ? null : appkey.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_video_management.secret
	 * @return  the value of n_video_management.secret
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "应用秘钥")
	public String getSecret() {
		return secret;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_video_management.secret
	 * @param secret  the value for n_video_management.secret
	 * @mbggenerated
	 */
	public void setSecret(String secret) {
		this.secret = secret == null ? null : secret.trim();
	}
	private Integer pid;
	private String pname;
	private String resourceIds;
	public int getUrlType() {
		return urlType;
	}

	public void setUrlType(int urlType) {
		this.urlType = urlType;
	}
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}
	private int urlType; // app 鎴? http閾炬帴

}