package com.bola.nwcl.biz;

import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.LectureHall;
import com.bola.nwcl.dal.mybatis.model.LectureHallRegister;
import com.bola.nwcl.dal.mybatis.model.LectureHallRegisterExample;

public interface LectureHallRegisterManager extends Manager<LectureHallRegister, LectureHallRegisterExample, Long>{
}
