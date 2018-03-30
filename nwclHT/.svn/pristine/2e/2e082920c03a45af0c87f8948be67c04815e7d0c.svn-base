package com.bola.nwcl.biz.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bola.nwcl.api.model.BizUserCouponModel;
import com.bola.nwcl.api.model.coupon.CouponModel;
import com.bola.nwcl.api.model.honour.HonourEnjoyDetailModel;
import com.bola.nwcl.api.model.honour.HonourEnjoyRatingModel;
import com.bola.nwcl.biz.HonourEnjoyManager;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.common.util.sensitive.SensitivewordFilter;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.BuserCouponMapper;
import com.bola.nwcl.dal.mybatis.mapper.CouponMapper;
import com.bola.nwcl.dal.mybatis.mapper.HonourEnjoyImgMapper;
import com.bola.nwcl.dal.mybatis.mapper.HonourEnjoyMapper;
import com.bola.nwcl.dal.mybatis.mapper.HonourEnjoyProductMapper;
import com.bola.nwcl.dal.mybatis.mapper.HonourEnjoyRatingMapper;
import com.bola.nwcl.dal.mybatis.mapper.HonourEnjoyRelationMapper;
import com.bola.nwcl.dal.mybatis.mapper.HonourEnjoyUserMapper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.BuserCoupon;
import com.bola.nwcl.dal.mybatis.model.BuserCouponExample;
import com.bola.nwcl.dal.mybatis.model.Coupon;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoy;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyExample;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyImg;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyImgExample;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyProduct;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyProductExample;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyRating;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyRatingExample;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyRelation;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyRelationExample;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyUser;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyUserExample;
import com.bola.nwcl.dal.mybatis.model.Role;
import com.bola.nwcl.dal.mybatis.model.RoleExample;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyExample.Criteria;
import com.bola.nwcl.web.model.HonourEnjoyInfoModel;

@Service
public class HonourEnjoyManagerImpl  extends ManagerImpl<HonourEnjoy, HonourEnjoyExample, Long> implements HonourEnjoyManager{
	
	private HonourEnjoyMapper honourEnjoyMapper;
	
	@Autowired
	private HonourEnjoyImgMapper imgMapper;
	
	@Autowired
	private HonourEnjoyRatingMapper ratingMapper;
	
	@Autowired
	private HonourEnjoyProductMapper productMapper;
	
	@Autowired
	private HonourEnjoyUserMapper honourEnjoyUserMapper;
	
	@Autowired
	private HonourEnjoyRelationMapper honourEnjoyRelationMapper;
	
	@Autowired
	private SensitivewordFilter sensitivewordFilter;
	
	@Autowired
	private CouponMapper couponMapper;
	
	@Autowired
	private BuserCouponMapper buserCouponMapper;
	
	@Autowired
	public HonourEnjoyManagerImpl(HonourEnjoyMapper honourEnjoyMapper) {
		this.mapper = honourEnjoyMapper;
		this.honourEnjoyMapper = honourEnjoyMapper;
	}

	@Override
	public HonourEnjoyDetailModel getHonourEnjoyDetail(Long id, long buserId, Integer ratingCount) {
		HonourEnjoy he = honourEnjoyMapper.selectByPrimaryKey(id);
		if(he == null){
			throw new BusinessValidateException("该信息不存在");
		}
		
		HonourEnjoyDetailModel model = new HonourEnjoyDetailModel();
		BeanUtils.copyProperties(he, model);
		
		HonourEnjoyImgExample example_img = new HonourEnjoyImgExample();
		example_img.or().andHonourEnjoyIdEqualTo(id);
		List<HonourEnjoyImg> imgs = imgMapper.selectByExample(example_img);
		model.setImgs(imgs);
		
		HonourEnjoyProductExample example_product = new HonourEnjoyProductExample();
		example_product.or().andHonourEnjoyIdEqualTo(id);
		List<HonourEnjoyProduct> products = productMapper.selectByExample(example_product);
		model.setProducts(products);
		
		HonourEnjoyRelationExample example_relation = new HonourEnjoyRelationExample();
		example_relation.or().andHonourEnjoyIdEqualTo(id);
		List<HonourEnjoyRelation> relations = honourEnjoyRelationMapper.selectByExample(example_relation);
		List<Long> user_ids = new ArrayList<Long>(relations.size());
		for(HonourEnjoyRelation r : relations){
			user_ids.add(r.getUserId());
		}
		HonourEnjoyUserExample example_user = new HonourEnjoyUserExample();
		if(user_ids.size() > 0){
			example_user.or().andIdIn(user_ids);
			List<HonourEnjoyUser> users = honourEnjoyUserMapper.selectByExample(example_user);
			model.setUser(users);
		}else{
			model.setUser(null);
		}
		
		Map<String,Object> condition = new HashMap<String, Object>();
		condition.put("id", id);
		condition.put("buserId", buserId);
		condition.put("limit", ratingCount);
		List<HonourEnjoyRatingModel> ratings = ratingMapper.getPageRatingModel(condition);
		model.setRatings(ratings);
		
		if(model.getType().intValue() == 3){
			List<CouponModel> coupons = couponMapper.getUserCoupon(condition);
			model.setCoupons(coupons);
		}
		
		return model;
	}

	@Override
	public Page<HonourEnjoyRatingModel> getHonourEnjoyRating(Long id, long buserId, int page, int rows) {
		HonourEnjoy he = honourEnjoyMapper.selectByPrimaryKey(id);
		if(he == null){
			throw new BusinessValidateException("该信息不存在");
		}
		PageRequest pager = new PageRequest(page - 1, rows);
		int count = 0;
		
		HonourEnjoyRatingExample example_rating = new HonourEnjoyRatingExample();
		example_rating.or().andHonourEnjoyIdEqualTo(id);
		count = ratingMapper.countByExample(example_rating);
		
		Map<String,Object> condition = new HashMap<String, Object>();
		condition.put("id", id);
		condition.put("buserId", buserId);
		condition.put("limit", pager.getPageSize());
		condition.put("offset", pager.getOffset());
		List<HonourEnjoyRatingModel> ratings = ratingMapper.getPageRatingModel(condition);
		
		Page<HonourEnjoyRatingModel> pageData = new PageImpl<HonourEnjoyRatingModel>(ratings, pager, count);
		return pageData;
	}

	@Override
	public HonourEnjoyRatingModel addHonourEnjoyRating(Long buserId, Long id, Long replyId, String content) {
		HonourEnjoy he = honourEnjoyMapper.selectByPrimaryKey(id);
		if(he == null){
			throw new BusinessValidateException("该信息不存在");
		}
		HonourEnjoyRating r = new HonourEnjoyRating();
		r.setBuserId(buserId);
		r.setContent(content);
		r.setHonourEnjoyId(id);
		
		if(replyId != null && replyId > 0){
			r.setReplyId(replyId);
		}

		boolean issensitive =sensitivewordFilter.isContaintSensitiveWord(content, 1);
		if(issensitive){
			r.setStatus(1);
		}else{
			r.setStatus(0);
		}
		
		ratingMapper.insertAndGetId(r);
		
		Map<String,Object> condition = new HashMap<String, Object>();
		condition.put("id", r.getId());
		condition.put("buserId", buserId);
		HonourEnjoyRatingModel model = ratingMapper.getOneRatingModel(condition);
		
		return model;
	}

	@Override
	public BizUserCouponModel addCoupon(long couponId, long buserId) {
		Coupon c = couponMapper.selectByPrimaryKey(couponId);
		if(c == null){
			throw new BusinessValidateException("该优惠卷不存在");
		}
		
		BuserCouponExample example = new BuserCouponExample();
		example.or().andBuserIdEqualTo(buserId).andCouponIdEqualTo(couponId);
		BuserCoupon bc = null;
		List<BuserCoupon> bcs = buserCouponMapper.selectByExample(example);
		if(bcs == null || bcs.size() < 1){
			if(c.getEnd().getTime() <= System.currentTimeMillis()){
				throw new BusinessValidateException("该优惠活动时间已经截止");
			}
			BuserCouponExample example_count = new BuserCouponExample();
			example_count.or().andCouponIdEqualTo(couponId);
			int count = buserCouponMapper.countByExample(example_count);
			if(count >= c.getSendCount()){
				throw new BusinessValidateException("该优惠卷已经发送完");
			}
			
			
			bc = new BuserCoupon();
			bc.setBuserId(buserId);
			bc.setCouponId(couponId);
			bc.setUseTime(0);
			
			String todayStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
			boolean isRepeat = true;
			do{
				String serial = "YHJ";
				serial += todayStr;
				serial += UUID.randomUUID().toString().substring(0,5);
				BuserCouponExample example_serial = new BuserCouponExample();
				example_serial.or().andSerialEqualTo(serial);
				List<BuserCoupon> bc_ss = buserCouponMapper.selectByExample(example_serial);
				if(bc_ss == null || bc_ss.size() < 1){
					isRepeat = false;
					bc.setSerial(serial);
				}
			}while(isRepeat);
			
			buserCouponMapper.insertAndGetId(bc);
		}else{
			bc = bcs.get(0);
			if(bc.getUseTime() >= c.getTime()){
				
				if(c.getEnd().getTime() <= System.currentTimeMillis()){
					throw new BusinessValidateException("该优惠活动时间已经截止");
				}
				
				BuserCouponExample example_count = new BuserCouponExample();
				example_count.or().andCouponIdEqualTo(couponId);
				int count = buserCouponMapper.countByExample(example_count);
				if(count >= c.getSendCount()){
					throw new BusinessValidateException("该优惠卷已经发送完");
				}
				
				bc = new BuserCoupon();
				bc.setBuserId(buserId);
				bc.setCouponId(couponId);
				bc.setUseTime(0);
				
				String todayStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
				boolean isRepeat = true;
				do{
					String serial = "YHJ";
					serial += todayStr;
					serial += UUID.randomUUID().toString().substring(0,5);
					BuserCouponExample example_serial = new BuserCouponExample();
					example_serial.or().andSerialEqualTo(serial);
					List<BuserCoupon> bc_ss = buserCouponMapper.selectByExample(example_serial);
					if(bc_ss == null || bc_ss.size() < 1){
						isRepeat = false;
						bc.setSerial(serial);
					}
				}while(isRepeat);
				
				buserCouponMapper.insertAndGetId(bc);
			}
		}
		
		BizUserCouponModel model = buserCouponMapper.getUserCouponDetail(bc.getId());
		
		return model;
	}

	@Override
	public DataGrid dataGrid(HonourEnjoyInfoModel h, PageHelper ph, HttpServletRequest request) {
		DataGrid dg = new DataGrid();
		HonourEnjoyExample example = new HonourEnjoyExample();
		Criteria c = example.or();
		// 得到用户对象
		/*Admin auser = (Admin) request.getSession().getAttribute("loginUser");
		// 判断是普通管理员还是admin(普通管理员给出当前小区id)
		if (auser.getRoleId() == 2) {
			c.andCommunityIdEqualTo(auser.getCommunityId());
		}*/
		if (h != null) {
			if(h.getType() != null){
				if ( h.getType()== 1 || h.getType()== 2 || h.getType()== 3) {
					c.andTypeEqualTo(h.getType());
				}else {
					c.andTypeEqualTo(-1);
				}
			}
			
			// 条件查询
			if (StringUtils.isNotBlank(h.getStype())) {
				if (h.getStype().equalsIgnoreCase("title") && StringUtils.isNotBlank(h.getSvalue())) {
					c.andTitleLike("%"+h.getSvalue()+"%");
				}
				if (h.getStype().equalsIgnoreCase("address") && StringUtils.isNotBlank(h.getSvalue())) {
					c.andAddressLike("%"+h.getSvalue()+"%");
				}
				if (h.getStype().equalsIgnoreCase("user") && StringUtils.isNotBlank(h.getSvalue())) {
					HonourEnjoyUserExample userExample = new HonourEnjoyUserExample();
					userExample.or().andNameLike("%"+h.getSvalue()+"%");
					List<HonourEnjoyUser> users = honourEnjoyUserMapper.selectByExample(userExample);
					if (users != null && users.size() > 0) {
						List<Long> ids = new ArrayList<Long>();
						for (HonourEnjoyUser u : users) {
							HonourEnjoyRelationExample relationExample = new HonourEnjoyRelationExample();
							relationExample.or().andUserIdEqualTo(u.getId());
							HonourEnjoyRelation relation = honourEnjoyRelationMapper.selectByExample(relationExample).get(0);
							if (relation != null ) {
								ids.add(relation.getHonourEnjoyId());
							}
						}
						c.andIdIn(ids);
					}
				}
			}
		}
		dg.setTotal(honourEnjoyMapper.countByExample(example));
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage()-1)*ph.getRows());
		example.setOrderByClause("row_add_time DESC");
		List<HonourEnjoy> he = honourEnjoyMapper.selectByExample(example);
		List<HonourEnjoyInfoModel> mos = new ArrayList<HonourEnjoyInfoModel>(he.size());
		//BeanUtils.copyProperties(he, mos);
		if (he.size() > 0) {
			for(HonourEnjoy heim : he){
				HonourEnjoyInfoModel model = new HonourEnjoyInfoModel();
				BeanUtils.copyProperties(heim, model);
				HonourEnjoyRelationExample relationExample = new HonourEnjoyRelationExample();
				relationExample.or().andHonourEnjoyIdEqualTo(heim.getId());
				List<HonourEnjoyRelation> relation = honourEnjoyRelationMapper.selectByExample(relationExample);
				if (relation != null && relation.size() > 0) {
					HonourEnjoyUser user = honourEnjoyUserMapper.selectByPrimaryKey(relation.get(0).getUserId());
					//model.setUser(user);
					model.setUserName(user.getName());
					model.setUserPosition(user.getPosition());
					model.setUserPhone(user.getPhone());
				}
				if(h.getType() != null){
					if (h.getType() == 3) {
						HonourEnjoyImgExample enjoyImgExample = new HonourEnjoyImgExample();
						enjoyImgExample.or().andHonourEnjoyIdEqualTo(heim.getId());
						List<HonourEnjoyImg> imgs = imgMapper.selectByExample(enjoyImgExample);
						model.setImages(imgs);
					}
				}
				mos.add(model);
			}
		}
		dg.setRows(mos);
		
		return dg;
	}

	@Override
	public void insertAndGetId(HonourEnjoy honourEnjoy) {
		honourEnjoyMapper.insertAndGetId(honourEnjoy);
	}

	@Override
	public HonourEnjoyInfoModel getInfo(Long id) {
		HonourEnjoy he = honourEnjoyMapper.selectByPrimaryKey(id);
		if(he == null){
			throw new BusinessValidateException("该信息不存在");
		}
		HonourEnjoyInfoModel model = new HonourEnjoyInfoModel();
		BeanUtils.copyProperties(he, model);
		
		HonourEnjoyImgExample example_img = new HonourEnjoyImgExample();
		example_img.or().andHonourEnjoyIdEqualTo(id);
		List<HonourEnjoyImg> imgs = imgMapper.selectByExample(example_img);
		model.setImages(imgs);
		
		HonourEnjoyRelationExample relationExample = new HonourEnjoyRelationExample();
		relationExample.or().andHonourEnjoyIdEqualTo(model.getId());
		List<HonourEnjoyRelation> relation = honourEnjoyRelationMapper.selectByExample(relationExample);
		if (relation != null && relation.size() > 0) {
			HonourEnjoyUser user = honourEnjoyUserMapper.selectByPrimaryKey(relation.get(0).getUserId());
			model.setUserName(user.getName());
			model.setUserPosition(user.getPosition());
			model.setUserPhone(user.getPhone());
			model.setUserPicture(user.getHeadPortrait());
		}
		return model;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void deleteHonorEnjoy(Long id) throws Exception {
		HonourEnjoy he = honourEnjoyMapper.selectByPrimaryKey(id);
		if(he == null){
			throw new BusinessValidateException("该信息不存在");
		}
		HonourEnjoyImgExample example_img = new HonourEnjoyImgExample();
		example_img.or().andHonourEnjoyIdEqualTo(id);
		imgMapper.deleteByExample(example_img);
		
		HonourEnjoyRelationExample relationExample = new HonourEnjoyRelationExample();
		relationExample.or().andHonourEnjoyIdEqualTo(id);
		List<HonourEnjoyRelation> relation = honourEnjoyRelationMapper.selectByExample(relationExample);
		if (relation != null && relation.size() > 0) {
			honourEnjoyUserMapper.deleteByPrimaryKey(relation.get(0).getUserId());
		}
		honourEnjoyRelationMapper.deleteByExample(relationExample);
		
		HonourEnjoyProductExample productExample = new HonourEnjoyProductExample();
		productExample.or().andHonourEnjoyIdEqualTo(id);
		productMapper.deleteByExample(productExample);
		
		honourEnjoyMapper.deleteByPrimaryKey(id);
	}
	
	

}
