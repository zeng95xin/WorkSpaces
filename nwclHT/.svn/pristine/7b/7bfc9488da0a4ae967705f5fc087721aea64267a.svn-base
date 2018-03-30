package com.bola.nwcl.dal.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.bola.nwcl.api.model.keeper.FridendsReportModel;
import com.bola.nwcl.api.model.keeper.FriendsReportImgModel;
import com.bola.nwcl.api.model.neighbor.NeighborHelpModel;
import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.dal.mybatis.model.NeighborHelp;
import com.bola.nwcl.dal.mybatis.model.NeighborHelpExample;

public interface NeighborHelpMapper extends Mapper<NeighborHelp, NeighborHelpExample, Long> {
	void insertAndGetId(NeighborHelp neighborHelp);
	
	List<FridendsReportModel> getAllReport(Map<String, Object> condition);
	List<FridendsReportModel> getAllSensitive(Map<String, Object> condition);
	int getAllSensitiveCount();
	List<FridendsReportModel> getAllContent(Map<String, Object> condition);
	int getAllContentCount();
	
	List<FriendsReportImgModel> getFriendsReportImgModelByNeighbor(long id);
	List<FriendsReportImgModel> getFriendsReportImgModelByLike(long id);
	
	
	List<NeighborHelpModel> getAllNeighborHelpByUser(Map<String, Object> condition);
	
}