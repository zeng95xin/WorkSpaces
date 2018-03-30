package com.bola.nwcl.dal.mybatis.model;

import com.bola.nwcl.common.mybatis.model.Model;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class Employee extends Model {
    /**
	 * 自增id
	 * @mbggenerated
	 */
	private Long id;
	/**
	 * 部门id
	 * @mbggenerated
	 */
	private Long deptId;
	/**
	 * 物业id
	 * @mbggenerated
	 */
	private Long propertyId;
	/**
	 * 小区id
	 * @mbggenerated
	 */
	private Long communityId;
	/**
	 * 楼栋id
	 * @mbggenerated
	 */
	private Long buildingId;
	/**
	 * 员工代码/编号
	 * @mbggenerated
	 */
	private String employeeNo;
	/**
	 * 员工状态
	 * @mbggenerated
	 */
	private Long status;
	/**
	 * 姓名
	 * @mbggenerated
	 */
	private String name;
	/**
	 * 工作岗位
	 * @mbggenerated
	 */
	private String position;
	/**
	 * 头像
	 * @mbggenerated
	 */
	private String headPortrait;
	/**
	 * 性别
	 * @mbggenerated
	 */
	private String sex;
	/**
	 * 入职时间
	 * @mbggenerated
	 */
	private String employedDate;
	/**
	 * 离职时间
	 * @mbggenerated
	 */
	private String unemployedDate;
	/**
	 * 学历
	 * @mbggenerated
	 */
	private String edBackground;
	/**
	 * 身份证号
	 * @mbggenerated
	 */
	private String idNumber;
	/**
	 * 合同开始日期
	 * @mbggenerated
	 */
	private String contractStartDate;
	/**
	 * 合同结束日期
	 * @mbggenerated
	 */
	private String contractEndDate;
	/**
	 * 手机号码
	 * @mbggenerated
	 */
	private String mobilephoneNumber;
	/**
	 * 电话号码
	 * @mbggenerated
	 */
	private String telephoneNumber;
	/**
	 * 角色
	 * @mbggenerated
	 */
	private Short role;
	/**
	 * 个人介绍
	 * @mbggenerated
	 */
	private String intro;
	/**
	 * 备注
	 * @mbggenerated
	 */
	private String note;
	/**
	 * 添加时间
	 * @mbggenerated
	 */
	private Date rowAddTime;
	/**
	 * 更新时间
	 * @mbggenerated
	 */
	private Date rowUpdateTime;
	/**
	 * 出生日期
	 * @mbggenerated
	 */
	private String birthday;
	/**
	 * serviceuserid
	 * @mbggenerated
	 */
	private Long suserId;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_service_employee.id
	 * @return  the value of n_service_employee.id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "自增id")
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_service_employee.id
	 * @param id  the value for n_service_employee.id
	 * @mbggenerated
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_service_employee.dept_id
	 * @return  the value of n_service_employee.dept_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "部门id")
	public Long getDeptId() {
		return deptId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_service_employee.dept_id
	 * @param deptId  the value for n_service_employee.dept_id
	 * @mbggenerated
	 */
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_service_employee.property_id
	 * @return  the value of n_service_employee.property_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "物业id")
	public Long getPropertyId() {
		return propertyId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_service_employee.property_id
	 * @param propertyId  the value for n_service_employee.property_id
	 * @mbggenerated
	 */
	public void setPropertyId(Long propertyId) {
		this.propertyId = propertyId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_service_employee.community_id
	 * @return  the value of n_service_employee.community_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "小区id")
	public Long getCommunityId() {
		return communityId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_service_employee.community_id
	 * @param communityId  the value for n_service_employee.community_id
	 * @mbggenerated
	 */
	public void setCommunityId(Long communityId) {
		this.communityId = communityId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_service_employee.building_id
	 * @return  the value of n_service_employee.building_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "楼栋id")
	public Long getBuildingId() {
		return buildingId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_service_employee.building_id
	 * @param buildingId  the value for n_service_employee.building_id
	 * @mbggenerated
	 */
	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_service_employee.employee_no
	 * @return  the value of n_service_employee.employee_no
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "员工代码/编号")
	public String getEmployeeNo() {
		return employeeNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_service_employee.employee_no
	 * @param employeeNo  the value for n_service_employee.employee_no
	 * @mbggenerated
	 */
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo == null ? null : employeeNo.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_service_employee.status
	 * @return  the value of n_service_employee.status
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "员工状态")
	public Long getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_service_employee.status
	 * @param status  the value for n_service_employee.status
	 * @mbggenerated
	 */
	public void setStatus(Long status) {
		this.status = status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_service_employee.name
	 * @return  the value of n_service_employee.name
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "姓名")
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_service_employee.name
	 * @param name  the value for n_service_employee.name
	 * @mbggenerated
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_service_employee.position
	 * @return  the value of n_service_employee.position
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "工作岗位")
	public String getPosition() {
		return position;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_service_employee.position
	 * @param position  the value for n_service_employee.position
	 * @mbggenerated
	 */
	public void setPosition(String position) {
		this.position = position == null ? null : position.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_service_employee.head_portrait
	 * @return  the value of n_service_employee.head_portrait
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "头像")
	public String getHeadPortrait() {
		return headPortrait;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_service_employee.head_portrait
	 * @param headPortrait  the value for n_service_employee.head_portrait
	 * @mbggenerated
	 */
	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait == null ? null : headPortrait.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_service_employee.sex
	 * @return  the value of n_service_employee.sex
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "性别")
	public String getSex() {
		return sex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_service_employee.sex
	 * @param sex  the value for n_service_employee.sex
	 * @mbggenerated
	 */
	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_service_employee.employed_date
	 * @return  the value of n_service_employee.employed_date
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "入职时间")
	public String getEmployedDate() {
		return employedDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_service_employee.employed_date
	 * @param employedDate  the value for n_service_employee.employed_date
	 * @mbggenerated
	 */
	public void setEmployedDate(String employedDate) {
		this.employedDate = employedDate == null ? null : employedDate.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_service_employee.unemployed_date
	 * @return  the value of n_service_employee.unemployed_date
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "离职时间")
	public String getUnemployedDate() {
		return unemployedDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_service_employee.unemployed_date
	 * @param unemployedDate  the value for n_service_employee.unemployed_date
	 * @mbggenerated
	 */
	public void setUnemployedDate(String unemployedDate) {
		this.unemployedDate = unemployedDate == null ? null : unemployedDate
				.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_service_employee.ed_background
	 * @return  the value of n_service_employee.ed_background
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "学历")
	public String getEdBackground() {
		return edBackground;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_service_employee.ed_background
	 * @param edBackground  the value for n_service_employee.ed_background
	 * @mbggenerated
	 */
	public void setEdBackground(String edBackground) {
		this.edBackground = edBackground == null ? null : edBackground.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_service_employee.ID_number
	 * @return  the value of n_service_employee.ID_number
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "身份证号")
	public String getIdNumber() {
		return idNumber;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_service_employee.ID_number
	 * @param idNumber  the value for n_service_employee.ID_number
	 * @mbggenerated
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber == null ? null : idNumber.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_service_employee.contract_start_date
	 * @return  the value of n_service_employee.contract_start_date
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "合同开始日期")
	public String getContractStartDate() {
		return contractStartDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_service_employee.contract_start_date
	 * @param contractStartDate  the value for n_service_employee.contract_start_date
	 * @mbggenerated
	 */
	public void setContractStartDate(String contractStartDate) {
		this.contractStartDate = contractStartDate == null ? null
				: contractStartDate.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_service_employee.contract_end_date
	 * @return  the value of n_service_employee.contract_end_date
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "合同结束日期")
	public String getContractEndDate() {
		return contractEndDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_service_employee.contract_end_date
	 * @param contractEndDate  the value for n_service_employee.contract_end_date
	 * @mbggenerated
	 */
	public void setContractEndDate(String contractEndDate) {
		this.contractEndDate = contractEndDate == null ? null : contractEndDate
				.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_service_employee.mobilephone_number
	 * @return  the value of n_service_employee.mobilephone_number
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "手机号码")
	public String getMobilephoneNumber() {
		return mobilephoneNumber;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_service_employee.mobilephone_number
	 * @param mobilephoneNumber  the value for n_service_employee.mobilephone_number
	 * @mbggenerated
	 */
	public void setMobilephoneNumber(String mobilephoneNumber) {
		this.mobilephoneNumber = mobilephoneNumber == null ? null
				: mobilephoneNumber.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_service_employee.telephone_number
	 * @return  the value of n_service_employee.telephone_number
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "电话号码")
	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_service_employee.telephone_number
	 * @param telephoneNumber  the value for n_service_employee.telephone_number
	 * @mbggenerated
	 */
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber == null ? null : telephoneNumber
				.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_service_employee.role
	 * @return  the value of n_service_employee.role
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "角色")
	public Short getRole() {
		return role;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_service_employee.role
	 * @param role  the value for n_service_employee.role
	 * @mbggenerated
	 */
	public void setRole(Short role) {
		this.role = role;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_service_employee.intro
	 * @return  the value of n_service_employee.intro
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "个人介绍")
	public String getIntro() {
		return intro;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_service_employee.intro
	 * @param intro  the value for n_service_employee.intro
	 * @mbggenerated
	 */
	public void setIntro(String intro) {
		this.intro = intro == null ? null : intro.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_service_employee.note
	 * @return  the value of n_service_employee.note
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "备注")
	public String getNote() {
		return note;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_service_employee.note
	 * @param note  the value for n_service_employee.note
	 * @mbggenerated
	 */
	public void setNote(String note) {
		this.note = note == null ? null : note.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_service_employee.row_add_time
	 * @return  the value of n_service_employee.row_add_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "添加时间")
	public Date getRowAddTime() {
		return rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_service_employee.row_add_time
	 * @param rowAddTime  the value for n_service_employee.row_add_time
	 * @mbggenerated
	 */
	public void setRowAddTime(Date rowAddTime) {
		this.rowAddTime = rowAddTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_service_employee.row_update_time
	 * @return  the value of n_service_employee.row_update_time
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "更新时间")
	public Date getRowUpdateTime() {
		return rowUpdateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_service_employee.row_update_time
	 * @param rowUpdateTime  the value for n_service_employee.row_update_time
	 * @mbggenerated
	 */
	public void setRowUpdateTime(Date rowUpdateTime) {
		this.rowUpdateTime = rowUpdateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_service_employee.birthday
	 * @return  the value of n_service_employee.birthday
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "出生日期")
	public String getBirthday() {
		return birthday;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_service_employee.birthday
	 * @param birthday  the value for n_service_employee.birthday
	 * @mbggenerated
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday == null ? null : birthday.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column n_service_employee.suser_id
	 * @return  the value of n_service_employee.suser_id
	 * @mbggenerated
	 */
	@ApiModelProperty(value = "serviceuserid")
	public Long getSuserId() {
		return suserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column n_service_employee.suser_id
	 * @param suserId  the value for n_service_employee.suser_id
	 * @mbggenerated
	 */
	public void setSuserId(Long suserId) {
		this.suserId = suserId;
	}

	private String deptName;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
    
    
}