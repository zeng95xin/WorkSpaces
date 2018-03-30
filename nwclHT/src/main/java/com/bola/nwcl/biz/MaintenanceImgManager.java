package com.bola.nwcl.biz;

import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.mybatis.model.MaintenanceImg;
import com.bola.nwcl.dal.mybatis.model.MaintenanceImgExample;

public interface MaintenanceImgManager extends Manager<MaintenanceImg, MaintenanceImgExample, Long>{
	void insertAndGetId(MaintenanceImg maintenanceImg);
}
