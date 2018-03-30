package com.bola.nwcl.biz.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bola.nwcl.api.model.MaintenanceImgModel;
import com.bola.nwcl.api.model.MaintenanceModel;
import com.bola.nwcl.api.model.MaintenanceUserAllModel;
import com.bola.nwcl.biz.MaintenanceManager;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.common.util.file.FileUploadDeleteUtil;
import com.bola.nwcl.common.util.file.SerialUtil;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.BizUserMapper;
import com.bola.nwcl.dal.mybatis.mapper.EmployeeMapper;
import com.bola.nwcl.dal.mybatis.mapper.MaintenanceImgMapper;
import com.bola.nwcl.dal.mybatis.mapper.MaintenanceMapper;
import com.bola.nwcl.dal.mybatis.mapper.MaintenanceOfferMapper;
import com.bola.nwcl.dal.mybatis.mapper.MaintenanceRatingMapper;
import com.bola.nwcl.dal.mybatis.mapper.RoomMapper;
import com.bola.nwcl.dal.mybatis.mapper.ServiceUserMapper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.Employee;
import com.bola.nwcl.dal.mybatis.model.EmployeeExample;
import com.bola.nwcl.dal.mybatis.model.Maintenance;
import com.bola.nwcl.dal.mybatis.model.MaintenanceExample;
import com.bola.nwcl.dal.mybatis.model.MaintenanceExample.Criteria;
import com.bola.nwcl.dal.mybatis.model.MaintenanceImg;
import com.bola.nwcl.dal.mybatis.model.MaintenanceOffer;
import com.bola.nwcl.dal.mybatis.model.MaintenanceOfferExample;
import com.bola.nwcl.dal.mybatis.model.MaintenanceRating;
import com.bola.nwcl.dal.mybatis.model.MaintenanceRatingExample;
import com.bola.nwcl.dal.mybatis.model.Room;
import com.bola.nwcl.dal.mybatis.model.ServiceUser;
import com.bola.nwcl.dal.mybatis.model.ServiceUserExample;


@Service
public class MaintenanceManagerImpl  extends ManagerImpl<Maintenance, MaintenanceExample, Long> implements MaintenanceManager{
	
	private MaintenanceMapper maintenanceMapper;
	
	@Autowired
	private MaintenanceImgMapper maintenanceImgMapper;
	
	@Autowired
	private MaintenanceOfferMapper maintenanceOfferMapper;
	
	@Autowired
	private ServiceUserMapper serviceUserMapper;
	@Autowired
	private RoomMapper roomMapper;
	@Autowired
	private BizUserMapper bizUserMapper;
	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private MaintenanceRatingMapper ratingMapper;

	
	
	
	@Autowired
	public MaintenanceManagerImpl(MaintenanceMapper maintenanceMapper) {
		this.mapper = maintenanceMapper;
		this.maintenanceMapper = maintenanceMapper;
	}

	@Override
	public void insertAndGetId(Maintenance maintenance) {
		maintenanceMapper.insertAndGetId(maintenance);
	}
	
	@Override
	public Page<MaintenanceUserAllModel> getUserAllMaintenance(int page, int rows, long buserId) {
		PageRequest pager = new PageRequest(page - 1, rows);
		Map<String, Object> condition = new HashMap<String, Object>(3);
		condition.put("limit", pager.getPageSize());
		condition.put("offset", pager.getOffset());
		condition.put("buserId", buserId);
		List<MaintenanceUserAllModel> list = maintenanceMapper.getUserAllMaintenance(condition);
		MaintenanceExample example = new MaintenanceExample();
		example.or().andBuserIdEqualTo(buserId);
		int total = maintenanceMapper.countByExample(example);
		Page<MaintenanceUserAllModel> pageData = new PageImpl<MaintenanceUserAllModel>(list, pager, total);
		return pageData;
	}

	@Override
	public DataGrid dataGrid(Maintenance maintenance, PageHelper ph,HttpServletRequest request) {
		DataGrid dg = new DataGrid();
		MaintenanceExample example=new MaintenanceExample();
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage()-1)*ph.getRows());
		example.setOrderByClause("row_add_time DESC");
		Admin us = (Admin)request.getSession().getAttribute("loginUser");
		//判断是普通管理员还是admin
		Criteria c = example.or();
		if(us.getRoleId() != 1){
			@SuppressWarnings("unchecked")
			List<Long> communityIds = (List<Long>) request.getSession().getAttribute("communityIds");
			c.andCommunityIdIn(communityIds);
		}
		if (maintenance != null) {
			if (maintenance.getCommunityId() != null){
				c.andCommunityIdEqualTo(maintenance.getCommunityId());
			}
			if (maintenance.getStatus() != null) {
				if (maintenance.getStatus() == 0) {
					List<Integer> a = new ArrayList<>();
					a.add(0);a.add(1);
					c.andStatusIn(a);
				}
				if (maintenance.getStatus() == 1) {
					List<Integer> a = new ArrayList<>();
					a.add(2);a.add(3);a.add(4);a.add(5);
					c.andStatusIn(a);
				}
				if (maintenance.getStatus() == 2) {
					List<Integer> a = new ArrayList<>();
					a.add(-1);a.add(6);a.add(7);
					c.andStatusIn(a);
				}
			}
			if (StringUtils.isNotBlank(maintenance.getSerial())
					&& StringUtils.isNotBlank(maintenance.getDescription())) {
				if (maintenance.getSerial().equals("serial")) {
					c.andSerialLike("%" + maintenance.getDescription() + "%");

				}
				if (maintenance.getSerial().equals("description")) {
					c.andDescriptionLike("%" + maintenance.getDescription() + "%");
				}
			}

		}
		int total=maintenanceMapper.countByExample(example);
		dg.setTotal(total);
		List<Maintenance> list=maintenanceMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			for(Maintenance m : list){
				Room r = roomMapper.selectByPrimaryKey(m.getRoomId());
				if (r != null) {
					m.setRoom(r.getUnitNo());
				}
				ServiceUser su = serviceUserMapper.selectByPrimaryKey(m.getRepairMainId());
				if (su != null) {
					m.setRepairman(su.getName());
					EmployeeExample employeeExample = new EmployeeExample();
					employeeExample.or().andSuserIdEqualTo(su.getId());
					List<Employee> employees = employeeMapper.selectByExample(employeeExample);
					if ( employees != null && employees.size() > 0) {
						Employee employee = employees.get(0);
						if (employee != null) {
							m.setRepairmanPhone(employee.getMobilephoneNumber());
						}
					}
				}
				BizUser user = bizUserMapper.selectByPrimaryKey(m.getBuserId());
				if (user != null) {
					m.setUser(user.getPhone());
				}
				MaintenanceOfferExample offerExample = new MaintenanceOfferExample();
				offerExample.or().andMaintenanceIdEqualTo(m.getId());
				List<MaintenanceOffer> olist = maintenanceOfferMapper.selectByExample(offerExample);
				if (olist != null && olist.size() > 0) {
					Long money= 0l;
					for (MaintenanceOffer o : olist) {
						money += o.getProjectPrice();
						m.setPayStatus(o.getStatus());
					}
					if (money > 0) {
						money = money/100;
					}
					m.setPayMoney(money);
				}
				if (m.getStatus() == -1) {
					m.setRowUpdateTime(null);
				}
				MaintenanceRatingExample ratingExample = new MaintenanceRatingExample();
				ratingExample.or().andMaintenanceIdEqualTo(m.getId());
				List<MaintenanceRating> ratings = ratingMapper.selectByExample(ratingExample);
				if (ratings != null && ratings.size() > 0) {
					MaintenanceRating rating = ratings.get(0);
					if (rating != null) {
					m.setComments(rating.getRatingLevel()+"星");
					}
				}
			}
		}
		dg.setRows(list);
		return dg;
	}

	@Override
	public MaintenanceModel addMaintenanceByBuser(String saveDir, BizUser buser, HttpServletRequest request, Long repairMainId, String description, MultipartFile[] maintenanceImgs) throws Exception {
		
		
		List<Integer> list_status = new ArrayList<Integer>(7);
		list_status.add(0);
		list_status.add(1);
		list_status.add(2);
		list_status.add(3);
		list_status.add(4);
		list_status.add(5);
		list_status.add(6);
		MaintenanceExample example_can = new MaintenanceExample();
		example_can.or().andBuserIdEqualTo(buser.getId()).andStatusIn(list_status);
		List<Maintenance> list_can = maintenanceMapper.selectByExample(example_can);
		if(list_can != null && list_can.size() > 0){
			throw new BusinessValidateException("还有报修单未完成,不能报修");
		}
		
		
		if(repairMainId != null && repairMainId > 0){
			if(serviceUserMapper.selectByPrimaryKey(repairMainId) == null){
				throw new BusinessValidateException("维修人员不存在");
			}
		}else{
			ServiceUserExample example_suser = new ServiceUserExample();
			example_suser.or().andRoleEqualTo(3);
			List<ServiceUser> susers = serviceUserMapper.selectByExample(example_suser);
			Random random = new Random();
			int index = random.nextInt(susers.size());
			repairMainId = susers.get(index).getId();
		}
		Maintenance m = new Maintenance();
		String todayStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
		boolean isRepeat = true;
		do{
			String serial = SerialUtil.buildSerialNum();
			MaintenanceExample example_m = new MaintenanceExample();
			example_m.or().andSerialEqualTo(serial);
			List<Maintenance> list = maintenanceMapper.selectByExample(example_m);
			if(list == null || list.size() < 1){
				isRepeat = false;
				m.setSerial(serial);
			}
		}while(isRepeat);
		m.setRoomId(buser.getCurrentRoomId());
		m.setBuserId(buser.getId());
		m.setDescription(description);
		m.setStatus(1);
		m.setRowAddTime(new Date());
		m.setRepairMainId(repairMainId);
		m.setAssignTime(new Date());
		maintenanceMapper.insertAndGetId(m);
		MaintenanceModel model = new MaintenanceModel();
		BeanUtils.copyProperties(m, model);
		List<MaintenanceImgModel> imgs = new ArrayList<MaintenanceImgModel>();
		for(MultipartFile img : maintenanceImgs){
			if(!img.isEmpty()){
				String fileName=UUID.randomUUID().toString().substring(0,12);
				fileName = todayStr + fileName;
				String[] saveFileName=FileUploadDeleteUtil.upload2(img, request, saveDir, fileName);
				
				MaintenanceImg mimg = new MaintenanceImg();
				mimg.setImgPath(saveFileName[0]);
				mimg.setImgPathThumbnail(saveFileName[1]);
				mimg.setMaintenanceId(m.getId());
				maintenanceImgMapper.insertAndGetId(mimg);
				
				MaintenanceImgModel imgModel = new MaintenanceImgModel();
				BeanUtils.copyProperties(mimg, imgModel);
				imgs.add(imgModel);
			}
		}
		model.setImgs(imgs);
		return model;
	}

	@Override
	public void updatePayMaintenance(BizUser buser, long id) {
		/*Maintenance m = maintenanceMapper.selectByPrimaryKey(id);
		if(m == null){
			throw new BusinessValidateException("该维修单不存在");
		}
		if(m.getBuserId().longValue() != buser.getId()){
			throw new BusinessValidateException("只能给自己的维修单付款");
		}
		if(m.getStatus().intValue() != 4){
			throw new BusinessValidateException("维修单未报价");
		}
		MaintenanceOfferExample example_offer = new MaintenanceOfferExample();
		example_offer.or().andMaintenanceIdEqualTo(id);
		MaintenanceOffer offer = new MaintenanceOffer();
		offer.setStatus(1);
		maintenanceOfferMapper.updateByExampleSelective(offer, example_offer);
		m.setStatus(5);
		maintenanceMapper.updateByPrimaryKeySelective(m);*/
	}

	@Override
	public void updatePaySuccess(Maintenance m) {
		if(m == null){
			throw new BusinessValidateException("该维修单不存在");
		}
		MaintenanceOfferExample example_offer = new MaintenanceOfferExample();
		example_offer.or().andMaintenanceIdEqualTo(m.getId());
		MaintenanceOffer offer = new MaintenanceOffer();
		offer.setStatus(1);
		maintenanceOfferMapper.updateByExampleSelective(offer, example_offer);
		m.setStatus(5);
		maintenanceMapper.updateByPrimaryKeySelective(m);
		
	}

}
