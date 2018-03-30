package com.bola.nwcl.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bola.nwcl.api.model.AuthorizeUserModel;
import com.bola.nwcl.api.model.BizUserCouponModel;
import com.bola.nwcl.api.model.BizUserLingLingModel;
import com.bola.nwcl.api.model.RoomModel;
import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.BizUserExample;
import com.bola.nwcl.web.model.Tree;

public interface BizUserManager extends Manager<BizUser, BizUserExample, Long>{
	void edit(HttpServletRequest request,String Pwd,String nickname,String nam,String sex, Long id,@RequestParam("file") MultipartFile file) throws Exception;
	
	BizUserLingLingModel updateGetUserLingLingKey(long buserId);
	
	void updateSendBerificationCode(HttpServletRequest request, String phone,String type) throws Exception;
	
	BizUser updateRegister(HttpServletRequest request, String password,String room_id, String nickname, String type) throws Exception;
	
	/**
	 * 获取系统预留的业主房间
	 * @param phone
	 * @return
	 */
	List<RoomModel> getUserRooms(String phone);
	
	/**
	 * 获取业主已经绑定,并且自己是业主的房间
	 * @param id
	 * @return
	 */
	List<RoomModel> getUserActiveRooms(long id);
	
	/**
	 * 获取住户的所有房间
	 * @param id
	 * @return
	 */
	List<RoomModel> getUserAllRooms(long id);
	
	void insertAndGetId(BizUser bizUser);
	List<Tree> allTree();
	
	/**
	 * 获取用户的授权用户列表
	 * @param buserId
	 * @return
	 */
	List<AuthorizeUserModel> getAllAuthorizeUser(long buserId);
	
	Page<BizUserCouponModel> getUserAllCoupon(long buserId, int page, int rows, Integer type);
	
	int getCouponUnusedCount(long id);
	
	/**
	 * 获取统计表格
	 */
    DataGrid dataGrid(BizUser user ,PageHelper ph, HttpServletRequest request);
    
    BaseResult updateInfoByWeb(String msg);
}
