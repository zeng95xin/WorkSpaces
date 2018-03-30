package com.bola.nwcl.biz;

import java.util.List;

import org.springframework.data.domain.Page;

import com.bola.nwcl.api.model.indexes.FridendsIndexModel;
import com.bola.nwcl.api.model.indexes.OwnerFairIndexModel;
import com.bola.nwcl.api.model.indexes.ServiceAddIndexModel;
import com.bola.nwcl.dal.mybatis.model.IndexesImg;


public interface BizUserIndexesManager {
	Page<FridendsIndexModel> getFriendsIndexInfo(int page, int rows);
	Page<ServiceAddIndexModel> getServiceAddInfo(int page, int rows);
	Page<OwnerFairIndexModel> getOwnerFairIndex(int page, int rows);
	List<IndexesImg> getIndexesImg(int type);
}
