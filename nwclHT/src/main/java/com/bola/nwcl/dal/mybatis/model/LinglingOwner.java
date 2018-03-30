package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.Model;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class LinglingOwner extends Model {

	/**
	 * 自增Id
	 * @mbggenerated
	 */
	private Long id;
	/**
	 * 工程人员id
	 * @mbggenerated
	 */
	private String userid;
	/**
	 * 业主名称
	 * @mbggenerated
	 */
	private String ownername;
	/**
	 * 业主电话号码
	 * @mbggenerated
	 */
	private String ownertelephone;
	/**
	 * 业主密码（建议唯一的mac）
	 * @mbggenerated
	 */
	private String mac;
	/**
	 * 关联的设备（授权业主的设备id，数组）  
	 * @mbggenerated
	 */
	private String deviceids;
	/**
	 * 小区 ID
	 * @mbggenerated
	 */
	private String residentialid;
	/**
	 * 业主注册码
	 * @mbggenerated
	 */
	private String regcode;
	/**
	 * 业主 ID（用于访客二维码的生成）
	 * @mbggenerated
	 */
	private String ownerid;
	/**
	 * 小区名
	 * @mbggenerated
	 */
	private String residentialname;
	/**
	 * 设备 ID
	 * @mbggenerated
	 */
	private String deviceid;
	/**
	 * 设备名称
	 * @mbggenerated
	 */
	private String name;
	/**
	 * 设备类型（1为 BT，2为WIFI，3为WIFI+BT，4为开门按钮）
	 * @mbggenerated
	 */
	private String devicetype;
	/**
	 * 开门用的 key
	 * @mbggenerated
	 */
	private String encryptstr;
	/**
	 * 服务器地址
	 * @mbggenerated
	 */
	private String addr;
	/**
	 * 访问服务器地址的令牌 
	 * @mbggenerated
	 */
	private String opentoken;
	/**
	 * 业主id
	 * @mbggenerated
	 */
	private Long buserId;
	/**
	 * 添加时间
	 * @mbggenerated
	 */
	private Date rowAddTime;
	/**
	 * 修改时间
	 * @mbggenerated
	 */
	private Date rowUpdateTime;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_lingling_owner.id
	 * @return  the value of n_lingling_owner.id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "自增Id")
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_lingling_owner.id
	 * @param id  the value for n_lingling_owner.id
	 * @mbggenerated
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_lingling_owner.userId
	 * @return  the value of n_lingling_owner.userId
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "工程人员id")
	public String getUserid() {
		return userid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_lingling_owner.userId
	 * @param userid  the value for n_lingling_owner.userId
	 * @mbggenerated
	 */
	public void setUserid(String userid) {
		this.userid = userid == null ? null : userid.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_lingling_owner.ownerName
	 * @return  the value of n_lingling_owner.ownerName
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "业主名称")
	public String getOwnername() {
		return ownername;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_lingling_owner.ownerName
	 * @param ownername  the value for n_lingling_owner.ownerName
	 * @mbggenerated
	 */
	public void setOwnername(String ownername) {
		this.ownername = ownername == null ? null : ownername.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_lingling_owner.ownerTelephone
	 * @return  the value of n_lingling_owner.ownerTelephone
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "业主电话号码")
	public String getOwnertelephone() {
		return ownertelephone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_lingling_owner.ownerTelephone
	 * @param ownertelephone  the value for n_lingling_owner.ownerTelephone
	 * @mbggenerated
	 */
	public void setOwnertelephone(String ownertelephone) {
		this.ownertelephone = ownertelephone == null ? null : ownertelephone
				.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_lingling_owner.mac
	 * @return  the value of n_lingling_owner.mac
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "业主密码（建议唯一的mac）")
	public String getMac() {
		return mac;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_lingling_owner.mac
	 * @param mac  the value for n_lingling_owner.mac
	 * @mbggenerated
	 */
	public void setMac(String mac) {
		this.mac = mac == null ? null : mac.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_lingling_owner.deviceIds
	 * @return  the value of n_lingling_owner.deviceIds
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "关联的设备（授权业主的设备id，数组）  ")
	public String getDeviceids() {
		return deviceids;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_lingling_owner.deviceIds
	 * @param deviceids  the value for n_lingling_owner.deviceIds
	 * @mbggenerated
	 */
	public void setDeviceids(String deviceids) {
		this.deviceids = deviceids == null ? null : deviceids.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_lingling_owner.residentialId
	 * @return  the value of n_lingling_owner.residentialId
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "小区 ID")
	public String getResidentialid() {
		return residentialid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_lingling_owner.residentialId
	 * @param residentialid  the value for n_lingling_owner.residentialId
	 * @mbggenerated
	 */
	public void setResidentialid(String residentialid) {
		this.residentialid = residentialid == null ? null : residentialid
				.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_lingling_owner.regcode
	 * @return  the value of n_lingling_owner.regcode
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "业主注册码")
	public String getRegcode() {
		return regcode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_lingling_owner.regcode
	 * @param regcode  the value for n_lingling_owner.regcode
	 * @mbggenerated
	 */
	public void setRegcode(String regcode) {
		this.regcode = regcode == null ? null : regcode.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_lingling_owner.ownerId
	 * @return  the value of n_lingling_owner.ownerId
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "业主 ID（用于访客二维码的生成）")
	public String getOwnerid() {
		return ownerid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_lingling_owner.ownerId
	 * @param ownerid  the value for n_lingling_owner.ownerId
	 * @mbggenerated
	 */
	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid == null ? null : ownerid.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_lingling_owner.residentialName
	 * @return  the value of n_lingling_owner.residentialName
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "小区名")
	public String getResidentialname() {
		return residentialname;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_lingling_owner.residentialName
	 * @param residentialname  the value for n_lingling_owner.residentialName
	 * @mbggenerated
	 */
	public void setResidentialname(String residentialname) {
		this.residentialname = residentialname == null ? null : residentialname
				.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_lingling_owner.deviceId
	 * @return  the value of n_lingling_owner.deviceId
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "设备 ID")
	public String getDeviceid() {
		return deviceid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_lingling_owner.deviceId
	 * @param deviceid  the value for n_lingling_owner.deviceId
	 * @mbggenerated
	 */
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid == null ? null : deviceid.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_lingling_owner.name
	 * @return  the value of n_lingling_owner.name
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "设备名称")
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_lingling_owner.name
	 * @param name  the value for n_lingling_owner.name
	 * @mbggenerated
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_lingling_owner.deviceType
	 * @return  the value of n_lingling_owner.deviceType
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "设备类型（1为 BT，2为WIFI，3为WIFI+BT，4为开门按钮）")
	public String getDevicetype() {
		return devicetype;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_lingling_owner.deviceType
	 * @param devicetype  the value for n_lingling_owner.deviceType
	 * @mbggenerated
	 */
	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype == null ? null : devicetype.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_lingling_owner.encryptStr
	 * @return  the value of n_lingling_owner.encryptStr
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "开门用的 key")
	public String getEncryptstr() {
		return encryptstr;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_lingling_owner.encryptStr
	 * @param encryptstr  the value for n_lingling_owner.encryptStr
	 * @mbggenerated
	 */
	public void setEncryptstr(String encryptstr) {
		this.encryptstr = encryptstr == null ? null : encryptstr.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_lingling_owner.addr
	 * @return  the value of n_lingling_owner.addr
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "服务器地址")
	public String getAddr() {
		return addr;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_lingling_owner.addr
	 * @param addr  the value for n_lingling_owner.addr
	 * @mbggenerated
	 */
	public void setAddr(String addr) {
		this.addr = addr == null ? null : addr.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_lingling_owner.openToken
	 * @return  the value of n_lingling_owner.openToken
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "访问服务器地址的令牌 ")
	public String getOpentoken() {
		return opentoken;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_lingling_owner.openToken
	 * @param opentoken  the value for n_lingling_owner.openToken
	 * @mbggenerated
	 */
	public void setOpentoken(String opentoken) {
		this.opentoken = opentoken == null ? null : opentoken.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_lingling_owner.buser_id
	 * @return  the value of n_lingling_owner.buser_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "业主id")
	public Long getBuserId() {
		return buserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_lingling_owner.buser_id
	 * @param buserId  the value for n_lingling_owner.buser_id
	 * @mbggenerated
	 */
	public void setBuserId(Long buserId) {
		this.buserId = buserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_lingling_owner.row_add_time
	 * @return  the value of n_lingling_owner.row_add_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "添加时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_lingling_owner.row_add_time
	 * @param rowAddTime  the value for n_lingling_owner.row_add_time
	 * @mbggenerated
	 */
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_lingling_owner.row_update_time
	 * @return  the value of n_lingling_owner.row_update_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "修改时间")
	public Date getRowUpdateTime() {
		return rowUpdateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_lingling_owner.row_update_time
	 * @param rowUpdateTime  the value for n_lingling_owner.row_update_time
	 * @mbggenerated
	 */
	public void setRowUpdateTime(Date rowUpdateTime) {
		this.rowUpdateTime = rowUpdateTime;
	}
}