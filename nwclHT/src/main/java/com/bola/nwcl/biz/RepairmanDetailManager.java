package com.bola.nwcl.biz;

import java.util.List;

import com.bola.nwcl.api.model.repairman.RepairmainModel;
import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.RepairmanDetail;
import com.bola.nwcl.dal.mybatis.model.RepairmanDetailExample;

public interface RepairmanDetailManager extends Manager<RepairmanDetail, RepairmanDetailExample, Long>{
	List<RepairmainModel> getAllRepairman(BizUser buser,Long communityId);
}
