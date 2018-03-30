package com.bola.nwcl.biz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.CouponManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.BuserCouponMapper;
import com.bola.nwcl.dal.mybatis.mapper.CouponMapper;
import com.bola.nwcl.dal.mybatis.mapper.HonourEnjoyMapper;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.BuserCoupon;
import com.bola.nwcl.dal.mybatis.model.BuserCouponExample;
import com.bola.nwcl.dal.mybatis.model.Coupon;
import com.bola.nwcl.dal.mybatis.model.CouponExample;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyExample;
import com.bola.nwcl.dal.mybatis.model.CouponExample.Criteria;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoy;

@Service
public class CouponManagerImpl  extends ManagerImpl<Coupon, CouponExample, Long> implements CouponManager{
	
	@SuppressWarnings("unused")
	private CouponMapper couponMapper;
	
	@Autowired
	private HonourEnjoyMapper honourEnjoyMapper;
	@Autowired
	private BuserCouponMapper buserCouponMapper;
	
	@Autowired
	public CouponManagerImpl(CouponMapper couponMapper) {
		this.mapper = couponMapper;
		this.couponMapper = couponMapper;
	}

	@Override
	public DataGrid dataGrid(HttpServletRequest request, Coupon cn, PageHelper ph) {
		DataGrid dg = new DataGrid();
		CouponExample example = new CouponExample();
		
		Admin admin = (Admin)request.getSession().getAttribute("loginUser");
		//判断是普通管理员还是admin
		if(admin.getRoleId() == 2){
			List<Long> clist = new ArrayList<>();
			HonourEnjoyExample heExample = new HonourEnjoyExample();
			heExample.or().andCommunityIdEqualTo(admin.getCommunityId());
			List<HonourEnjoy> enjoys = honourEnjoyMapper.selectByExample(heExample);
			if (enjoys != null && enjoys.size() > 0) {
				for(HonourEnjoy enjoy : enjoys){
					clist.add(enjoy.getId());
				}
			}else{
				clist.add(-1L);
			}
			example.or().andHonourEnjoyIdIn(clist);
		}
		Criteria c = example.or();
		if (cn != null) {
			if (cn.getStatus() != null) {
				if (cn.getStatus().equals("1")) {
					c.andEndLessThan(new Date());
				}else if (cn.getStatus().equals("0")) {
					c.andEndGreaterThan(new Date());
				}
			}
			if(StringUtils.isNotBlank(cn.getType())){
				List<Long> ids = new ArrayList<>();
				ids.add(-1L);
				HonourEnjoyExample enjoyExample = new HonourEnjoyExample();
				enjoyExample.or().andTypeEqualTo(Integer.valueOf(cn.getType()));
				List<HonourEnjoy> hs = honourEnjoyMapper.selectByExample(enjoyExample);
				if (hs != null && hs.size() > 0) {
					for(HonourEnjoy h : hs){
						ids.add(h.getId());
					}
				}
				c.andHonourEnjoyIdIn(ids);
			}
		}
		int total=couponMapper.countByExample(example);
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage()-1)*ph.getRows());
		example.setOrderByClause("honour_enjoy_id,row_add_time DESC");
		List<Coupon> list=couponMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			for(Coupon c1 : list){
				HonourEnjoy h = honourEnjoyMapper.selectByPrimaryKey(c1.getHonourEnjoyId());
				if (h != null) {
					c1.setHonourEnjoyName(h.getTitle());
					c1.setType(h.getType().toString());
				}
				if(c1.getEnd() != null){
					Date d = new Date();
					if (c1.getEnd().getTime() > d.getTime()){
						c1.setStatus("生效中");
					}else{
						c1.setStatus("已失效");
					}
				}
				// 券领取，使用次数
				if(cn.getId()!= null && cn.getId() == -10){
					BuserCouponExample bcExample = new BuserCouponExample();
					bcExample.or().andCouponIdEqualTo(c1.getId());
					long gt = buserCouponMapper.countByExample(bcExample);
					bcExample.or().andUseTimeGreaterThan(0);
					List<BuserCoupon> bn = buserCouponMapper.selectByExample(bcExample);
					Long ut = 0L;
					if (bn != null && bn.size() > 0) {
						for (BuserCoupon b : bn) {
							if (b.getUseTime() > 0) {
								ut +=b.getUseTime();
							}
						}
					}
					c1.setGetTimes(gt);
					c1.setUseTimes(ut);
				}
				
			}
		}
		dg.setRows(list);
		
		dg.setTotal(total);
		
		return dg;
	}

	@Override
	public void insertAndGetId(Coupon cn) {
		couponMapper.insertAndGetId(cn);
	}

}
