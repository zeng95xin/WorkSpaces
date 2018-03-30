package com.bola.nwcl.biz;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.mybatis.model.Memu;
import com.bola.nwcl.dal.mybatis.model.MemuExample;
import com.bola.nwcl.web.model.Resource;
import com.bola.nwcl.web.model.Tree;

public interface MemuManager extends Manager<Memu, MemuExample, Long>{
	//DataGrid dataGrid(Memu memu, PageHelper pageHelper);
	List<Resource> treeGrid(Long id);
	List<Tree> tree(HttpSession session);
	List<Tree> allTree();
	
	void insertAndGetId(Memu memu);
}
