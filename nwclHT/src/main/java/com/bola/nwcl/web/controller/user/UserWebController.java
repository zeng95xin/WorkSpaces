package com.bola.nwcl.web.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bola.nwcl.biz.BizUserManager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.web.model.Tree;
/**
 * 业主账号管理 
 */
@Controller
@RequestMapping(value = "web/user")
public class UserWebController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BizUserManager bizUserManager;
	/**
	 * 跳转到用户管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager() {
		return "/system/user";
	}
	/**
	 * 获取数据表格
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(BizUser user, PageHelper ph, HttpServletRequest request){	    
		
		return bizUserManager.dataGrid(user, ph, request); 
	      		
	}
	@RequestMapping("/allTree")
	@ResponseBody
	public List<Tree> allTree() {
		return bizUserManager.allTree();
	}
}
