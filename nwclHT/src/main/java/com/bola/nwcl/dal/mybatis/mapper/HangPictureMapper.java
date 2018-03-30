package com.bola.nwcl.dal.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.bola.nwcl.api.model.keeper.KeeperHangModel;
import com.bola.nwcl.api.model.repair.RepairHangModel;
import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.HangPicture;
import com.bola.nwcl.dal.mybatis.model.HangPictureExample;

public interface HangPictureMapper extends Mapper<HangPicture, HangPictureExample, Long> {
	void insertAndGetId(HangPicture hangPicture);
	
	List<KeeperHangModel> getKeeperHangModel(Map<String, Object> condition);
	int getKeeperHangModelCount(Map<String, Object> condition);
	
	List<RepairHangModel> getRepairHangModel(Map<String, Object> condition);
	int getRepairHangModelCount(Map<String, Object> condition);
	
}