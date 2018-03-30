package com.bola.nwcl.api.model.honour;

import java.util.List;

import com.bola.nwcl.api.model.coupon.CouponModel;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoy;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyImg;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyProduct;
import com.bola.nwcl.dal.mybatis.model.HonourEnjoyUser;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class HonourEnjoyDetailModel extends HonourEnjoy{
	private List<HonourEnjoyImg> imgs;
	private List<HonourEnjoyProduct> products;
	private List<HonourEnjoyRatingModel> ratings;
	private List<HonourEnjoyUser> users;
	
	private List<CouponModel> coupons;
	
	@ApiModelProperty(value = "优惠卷")
	public List<CouponModel> getCoupons() {
		return coupons;
	}
	public void setCoupons(List<CouponModel> coupons) {
		this.coupons = coupons;
	}
	
	@ApiModelProperty(value = "图片数组")
	public List<HonourEnjoyImg> getImgs() {
		return imgs;
	}
	@ApiModelProperty(value = "产品列表")
	public List<HonourEnjoyProduct> getProducts() {
		return products;
	}
	@ApiModelProperty(value = "评论")
	public List<HonourEnjoyRatingModel> getRatings() {
		return ratings;
	}
	@ApiModelProperty(value = "联系人信息")
	public List<HonourEnjoyUser> getUsers() {
		return users;
	}
	public void setUser(List<HonourEnjoyUser> users) {
		this.users = users;
	}
	public void setImgs(List<HonourEnjoyImg> imgs) {
		this.imgs = imgs;
	}
	public void setProducts(List<HonourEnjoyProduct> products) {
		this.products = products;
	}
	public void setRatings(List<HonourEnjoyRatingModel> ratings) {
		this.ratings = ratings;
	}
	
}
