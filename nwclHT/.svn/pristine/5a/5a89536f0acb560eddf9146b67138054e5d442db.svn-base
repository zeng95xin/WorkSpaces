package com.bola.nwcl.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bola.nwcl.api.model.keeper.KeeperUserModel;
import com.bola.nwcl.biz.ServiceUserManager;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.common.util.MD5Utils;
import com.bola.nwcl.common.util.file.FileUploadDeleteUtil;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.BuildingMapper;
import com.bola.nwcl.dal.mybatis.mapper.CommunityMapper;
import com.bola.nwcl.dal.mybatis.mapper.DepartmentMapper;
import com.bola.nwcl.dal.mybatis.mapper.EmployeeMapper;
import com.bola.nwcl.dal.mybatis.mapper.HousekeeperDetailMapper;
import com.bola.nwcl.dal.mybatis.mapper.MaintenanceMapper;
import com.bola.nwcl.dal.mybatis.mapper.RepairmanDetailMapper;
import com.bola.nwcl.dal.mybatis.mapper.RepairmanTypeRelationMapper;
import com.bola.nwcl.dal.mybatis.mapper.ServiceUserMapper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.Building;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.Department;
import com.bola.nwcl.dal.mybatis.model.Employee;
import com.bola.nwcl.dal.mybatis.model.EmployeeExample;
import com.bola.nwcl.dal.mybatis.model.HousekeeperDetail;
import com.bola.nwcl.dal.mybatis.model.HousekeeperDetailExample;
import com.bola.nwcl.dal.mybatis.model.MaintenanceExample;
import com.bola.nwcl.dal.mybatis.model.RepairmanDetail;
import com.bola.nwcl.dal.mybatis.model.RepairmanDetailExample;
import com.bola.nwcl.dal.mybatis.model.RepairmanTypeRelation;
import com.bola.nwcl.dal.mybatis.model.RepairmanTypeRelationExample;
import com.bola.nwcl.dal.mybatis.model.ServiceUser;
import com.bola.nwcl.dal.mybatis.model.ServiceUserExample;
import com.bola.nwcl.dal.mybatis.model.ServiceUserExample.Criteria;
import com.bola.nwcl.web.model.EmployeeModel;
import com.bola.nwcl.web.model.SuserWebModel;

@Service
public class ServiceUserManagerImpl  extends ManagerImpl<ServiceUser, ServiceUserExample, Long> implements ServiceUserManager{
	
	private ServiceUserMapper serviceUserMapper;
	@Autowired
	private BuildingMapper buildingMapper;
	@Autowired
	private CommunityMapper communityMapper;
	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private HousekeeperDetailMapper housekeeperDetailMapper;
	@Autowired
	private RepairmanDetailMapper repairmanDetailMapper;
	@Autowired
	private DepartmentMapper departmentMapper;
	@Autowired private RepairmanTypeRelationMapper repairmanTypeRelationMapper;
	@Autowired private MaintenanceMapper maintenanceMapper;
	
	@Autowired
	public ServiceUserManagerImpl(ServiceUserMapper serviceUserMapper) {
		this.mapper = serviceUserMapper;
		this.serviceUserMapper = serviceUserMapper;
	}

	@Override
	public List<KeeperUserModel> getKeeperModel(List<Long> ids) {
		String ids_ = "";
		for(int i=0;i<ids.size();i++){
			ids_ += ids.get(i);
			if(i<ids.size()-1){
				ids_ += ",";
			}
		}
		Map<String,Object> conditon = new HashMap<String,Object>(1);
		conditon.put("ids", ids_);
		return serviceUserMapper.getKeeperModel(conditon);
	}
	
	@Override
	public DataGrid dataGrid(ServiceUser suser, PageHelper ph,HttpServletRequest request) {
		DataGrid dg = new DataGrid();
		ServiceUserExample example_suser = new ServiceUserExample();	
		Criteria c = example_suser.or();
		Admin user = (Admin)request.getSession().getAttribute("loginUser");
		//判断是普通管理员还是admin
		if(user.getRoleId() != 1){
			@SuppressWarnings("unchecked")
			List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
			c.andCommunityIdIn(communityIds);
		}
		if (suser.getCommunityId() != null && suser.getCommunityId() != -1) {
			c.andCommunityIdEqualTo(suser.getCommunityId());
		}
		// 通过部门查询
		if (suser.getRole() != null && suser.getRole() != -1) {
			c.andRoleEqualTo(suser.getRole());
		}
		// 通过关键字查询 1 姓名 2 手机号
		if (StringUtils.isNotBlank(suser.getOption()) && StringUtils.isNotBlank(suser.getKey()) && !suser.getOption().equals("-1")) {
			List<Long> ids = new ArrayList<Long>();
			EmployeeExample employeeExample = new EmployeeExample();
			if (suser.getOption().equals("1")) {
				employeeExample.or().andNameLike("%"+suser.getKey()+"%");
			}
			if (suser.getOption().equals("2")) {
				employeeExample.or().andMobilephoneNumberLike("%"+suser.getKey()+"%");
			}
			List<Employee> ems = employeeMapper.selectByExample(employeeExample);
			if (ems != null && ems.size() > 0) {
				for(Employee em : ems){
					ids.add(em.getSuserId());
				}
			}else{
				ids.add(-1L);
			}
			c.andIdIn(ids);
		}
		int total = serviceUserMapper.countByExample(example_suser);
		example_suser.setLimit(ph.getRows());
		example_suser.setOffset((ph.getPage()-1) * ph.getRows());
		example_suser.setOrderByClause("row_add_time DESC");
		List<ServiceUser> susers = serviceUserMapper.selectByExample(example_suser);
		List<SuserWebModel> list = new ArrayList<SuserWebModel>(susers.size());
		for(ServiceUser su : susers){
			SuserWebModel model = new SuserWebModel();
			BeanUtils.copyProperties(su, model);
			Building b = buildingMapper.selectByPrimaryKey(model.getBuildingId());
			if(b!=null){
				model.setBuilding(b.getName());
			}
			if (su.getRole() != null) {
				Department dt = departmentMapper.selectByPrimaryKey(su.getRole().intValue());
				model.setDeptName(dt.getDeptName());
			}
			List<Integer> list_status = new ArrayList<Integer>(3);
			list_status.add(4);
			list_status.add(5);
			list_status.add(6);
			if(su.getRole().intValue() == 3){
				MaintenanceExample example_maintenance = new MaintenanceExample();
				example_maintenance.or().andRepairMainIdEqualTo(su.getId()).andStatusIn(list_status);
				int repariCount = maintenanceMapper.countByExample(example_maintenance);
				int bizStatus = repariCount > 0 ? 2:1;
				model.setBizStatus(bizStatus);
			}
			Community com = communityMapper.selectByPrimaryKey(model.getCommunityId());
			if(c!=null){
				model.setCommunity(com.getName());
			}
			list.add(model);
		}
		
		dg.setRows(list);
		dg.setTotal(total);
		return dg;
	}

	@Override
	public void addServiceUserByWeb(HttpServletRequest request, String saveDir, EmployeeModel em, MultipartFile img, String types) throws Exception {
		ServiceUserExample example_suser = new ServiceUserExample();
		example_suser.or().andAccountEqualTo(em.getAccount());
		if(serviceUserMapper.countByExample(example_suser) > 0){
			throw new BusinessValidateException("账号已经存在");
		}
		ServiceUser su = new ServiceUser();
		su.setAccount(em.getAccount());
		su.setPassword(MD5Utils.format(em.getPassword()));
		su.setCommunityId(em.getCommunityId());
		su.setBuildingId(-1L);
		su.setName(em.getName());
		su.setNickname(em.getNickname());
		su.setRole(em.getRole()+0);
		su.setDeptId(em.getDeptId());
		su.setSex(em.getSex());
		su.setStatus((short)1);
		String headPortrait = "";
		if(!img.isEmpty()){
			headPortrait = FileUploadDeleteUtil.upload(img, request, saveDir, UUID.randomUUID().toString().substring(0,10));
		}
		su.setHeadPortrait(headPortrait);
		serviceUserMapper.insertAndGetId(su);
		if(em.getRole() == 1){
			HousekeeperDetail housekeeperDetail = new HousekeeperDetail();
			housekeeperDetail.setDetail(em.getIntro());
			housekeeperDetail.setPhone(em.getMobilephoneNumber());
			housekeeperDetail.setKeeperId(su.getId());
			housekeeperDetail.setSerial(em.getEmployeeNo());
			housekeeperDetail.setStatus((short)1);
			housekeeperDetailMapper.insert(housekeeperDetail);
		}else if(em.getRole() == 2){
			
		}else if(em.getRole() == 3){
			RepairmanDetail repairmanDetail = new RepairmanDetail();
			repairmanDetail.setDetail(em.getIntro());
			repairmanDetail.setPhone(em.getMobilephoneNumber());
			repairmanDetail.setRepairmainId(su.getId());
			repairmanDetail.setSerial(em.getEmployeeNo());
			repairmanDetail.setStatus((int)1);
			repairmanDetailMapper.insert(repairmanDetail);
			if(null != types){
				String[] types_array = types.split(",");
				for(String type : types_array){
					RepairmanTypeRelation repairmanTypeRelation = new RepairmanTypeRelation();
					repairmanTypeRelation.setSuserId(su.getId());
					repairmanTypeRelation.setTypeId(Long.valueOf(type));
					repairmanTypeRelationMapper.insert(repairmanTypeRelation);
				}
			}
		}
		Employee e = new Employee();
		e.setCommunityId(em.getCommunityId());
		e.setBuildingId(-1L);
		e.setDeptId(em.getDeptId());
		e.setEmployeeNo(em.getEmployeeNo());
		e.setHeadPortrait(headPortrait);
		e.setMobilephoneNumber(em.getMobilephoneNumber());
		e.setTelephoneNumber(em.getTelephoneNumber());
		e.setPropertyId(em.getPropertyId());
		e.setIntro(em.getIntro());
		e.setName(em.getName());
		e.setRole(em.getRole());
		e.setSex(em.getSex());
		e.setEmployedDate(em.getEmployedDate());
		e.setBirthday(em.getBirthday());
		e.setEdBackground(em.getEdBackground());
		e.setPosition(em.getPosition());
		e.setNote(em.getNote());
		e.setIdNumber(em.getIdNumber());
		e.setSuserId(su.getId());
		employeeMapper.insert(e);
	}

	@Override
	public void updateServiceUserByWeb(HttpServletRequest request, String saveDir, EmployeeModel em, MultipartFile img, String types) throws Exception {
		ServiceUser su = serviceUserMapper.selectByPrimaryKey(em.getId());
		if(su == null){
			throw new BusinessValidateException("该用户已经被删除了");
		}
		su.setAccount(em.getAccount());
		if (StringUtils.isNotBlank(em.getPassword())) {
			su.setPassword(MD5Utils.format(em.getPassword()));
		}
		su.setCommunityId(em.getCommunityId());
		su.setBuildingId(-1L);
		su.setName(em.getName());
		su.setNickname(em.getNickname());
		su.setRole(em.getRole()+0);
		su.setSex(em.getSex());
		su.setStatus((short)1);
		su.setDeptId(em.getDeptId());
		String headPortrait = su.getHeadPortrait();
		if(!img.isEmpty()){
			headPortrait = FileUploadDeleteUtil.upload(img, request, saveDir, UUID.randomUUID().toString().substring(0,10));
		}
		su.setHeadPortrait(headPortrait);
		serviceUserMapper.updateByPrimaryKeySelective(su);
		if(em.getRole() == 1){
			HousekeeperDetailExample example_detail = new HousekeeperDetailExample();
			example_detail.or().andKeeperIdEqualTo(em.getId());
			List<HousekeeperDetail> details = housekeeperDetailMapper.selectByExample(example_detail);
			if(details == null || details.size() < 1){
				HousekeeperDetail housekeeperDetail = new HousekeeperDetail();
				housekeeperDetail.setDetail(em.getIntro());
				housekeeperDetail.setPhone(em.getMobilephoneNumber());
				housekeeperDetail.setKeeperId(su.getId());
				housekeeperDetail.setSerial(em.getEmployeeNo());
				housekeeperDetail.setStatus((short)1);
				housekeeperDetailMapper.insert(housekeeperDetail);
			}else{
				HousekeeperDetail housekeeperDetail = details.get(0);
				housekeeperDetail.setDetail(em.getIntro());
				housekeeperDetail.setPhone(em.getMobilephoneNumber());
				housekeeperDetail.setKeeperId(su.getId());
				housekeeperDetail.setSerial(em.getEmployeeNo());
				housekeeperDetail.setStatus((short)1);
				housekeeperDetailMapper.updateByPrimaryKeySelective(housekeeperDetail);
			}
		}else if(em.getRole() == 2){
			
		}else if(em.getRole() == 3){
			RepairmanDetailExample example_detail = new RepairmanDetailExample();
			example_detail.or().andRepairmainIdEqualTo(em.getId());
			List<RepairmanDetail> details = repairmanDetailMapper.selectByExample(example_detail);
			if(details == null || details.size() < 1){
				RepairmanDetail repairmanDetail = new RepairmanDetail();
				repairmanDetail.setDetail(em.getIntro());
				repairmanDetail.setPhone(em.getMobilephoneNumber());
				repairmanDetail.setRepairmainId(su.getId());
				repairmanDetail.setSerial(em.getEmployeeNo());
				repairmanDetail.setStatus((int)1);
				repairmanDetailMapper.insert(repairmanDetail);
			}else{
				RepairmanDetail repairmanDetail = details.get(0);
				repairmanDetail.setDetail(em.getIntro());
				repairmanDetail.setPhone(em.getMobilephoneNumber());
				repairmanDetail.setRepairmainId(su.getId());
				repairmanDetail.setSerial(em.getEmployeeNo());
				repairmanDetail.setStatus((int)1);
				repairmanDetailMapper.updateByPrimaryKeySelective(repairmanDetail);
			}
			
			if(null != types){
				String[] types_array = types.split(",");
				for(String type : types_array){
					RepairmanTypeRelationExample example_relation = new RepairmanTypeRelationExample();
					example_relation.or().andSuserIdEqualTo(su.getId());
					repairmanTypeRelationMapper.deleteByExample(example_relation);
					RepairmanTypeRelation repairmanTypeRelation = new RepairmanTypeRelation();
					repairmanTypeRelation.setSuserId(su.getId());
					repairmanTypeRelation.setTypeId(Long.valueOf(type));
					repairmanTypeRelationMapper.insert(repairmanTypeRelation);
				}
			}
			
		}
		EmployeeExample example_e = new EmployeeExample();
		example_e.or().andSuserIdEqualTo(em.getId());
		List<Employee> es = employeeMapper.selectByExample(example_e);
		if(es == null || es.size() < 1){
			Employee e = new Employee();
			e.setCommunityId(em.getCommunityId());
			e.setBuildingId(-1L);
			e.setDeptId(em.getDeptId());
			e.setEmployeeNo(em.getEmployeeNo());
			e.setHeadPortrait(headPortrait);
			e.setMobilephoneNumber(em.getMobilephoneNumber());
			e.setTelephoneNumber(em.getTelephoneNumber());
			e.setPropertyId(em.getPropertyId());
			e.setIntro(em.getIntro());
			e.setName(em.getName());
			e.setRole(em.getRole());
			e.setSex(em.getSex());
			e.setEmployedDate(em.getEmployedDate());
			e.setBirthday(em.getBirthday());
			e.setEdBackground(em.getEdBackground());
			e.setPosition(em.getPosition());
			e.setNote(em.getNote());
			e.setIdNumber(em.getIdNumber());
			e.setSuserId(su.getId());
		}else{
			Employee e = es.get(0);
			e.setCommunityId(em.getCommunityId());
			e.setBuildingId(-1L);
			e.setDeptId(em.getDeptId());
			e.setEmployeeNo(em.getEmployeeNo());
			e.setHeadPortrait(headPortrait);
			e.setMobilephoneNumber(em.getMobilephoneNumber());
			e.setTelephoneNumber(em.getTelephoneNumber());
			e.setPropertyId(em.getPropertyId());
			e.setIntro(em.getIntro());
			e.setName(em.getName());
			e.setRole(em.getRole());
			e.setSex(em.getSex());
			e.setEmployedDate(em.getEmployedDate());
			e.setBirthday(em.getBirthday());
			e.setEdBackground(em.getEdBackground());
			e.setPosition(em.getPosition());
			e.setNote(em.getNote());
			e.setIdNumber(em.getIdNumber());
			e.setSuserId(su.getId());
			employeeMapper.updateByPrimaryKeySelective(e);
		}
		
	}

	@Override
	public void deleteServiceUserByWeb(Long id) {
		ServiceUser su = serviceUserMapper.selectByPrimaryKey(id);
		if(su == null){
			throw new BusinessValidateException("该用户已经被删除了");
		}
		serviceUserMapper.deleteByPrimaryKey(id);
		EmployeeExample example_e = new EmployeeExample();
		example_e.or().andSuserIdEqualTo(id);
		employeeMapper.deleteByExample(example_e);
		int deptId = su.getRole();
		if(deptId == 1){
			HousekeeperDetailExample example_detail = new HousekeeperDetailExample();
			example_detail.or().andKeeperIdEqualTo(id);
			housekeeperDetailMapper.deleteByExample(example_detail);
		}else if(deptId == 2){
			
		}else if(deptId == 3){
			RepairmanDetailExample example_detail = new RepairmanDetailExample();
			example_detail.or().andRepairmainIdEqualTo(id);
			repairmanDetailMapper.deleteByExample(example_detail);
		}
		FileUploadDeleteUtil.delete(su.getHeadPortrait());
	}
}
