package com.bola.nwcl.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bola.nwcl.biz.ReadingRecordManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.mapper.BizUserMapper;
import com.bola.nwcl.dal.mybatis.mapper.ReadingRecordMapper;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.ReadingRecord;
import com.bola.nwcl.dal.mybatis.model.ReadingRecordExample;

@Service
public class ReadingRecordManagerImpl extends
		ManagerImpl<ReadingRecord, ReadingRecordExample, Long> implements ReadingRecordManager {

	@SuppressWarnings("unused")
	private ReadingRecordMapper readingRecordMapper;

	@Autowired
	private BizUserMapper bizUserMapper;
	
	@Autowired
	public ReadingRecordManagerImpl(ReadingRecordMapper readingRecordMapper) {
		this.mapper = readingRecordMapper;
		this.readingRecordMapper = readingRecordMapper;
	}

	@Override
	public DataGrid dataGrid(ReadingRecord readingRecord, PageHelper ph) {
		DataGrid dg = new DataGrid();
		Integer rtype = -1;
		Long cid = -1L;
		if (readingRecord.getType() != null) {
			rtype = readingRecord.getType();
		}
		if (readingRecord.getContentId() != null) {
			cid = readingRecord.getContentId();
		}
		ReadingRecordExample example = new ReadingRecordExample();
		example.or().andTypeEqualTo(rtype).andContentIdEqualTo(cid);
		int total = readingRecordMapper.countByExample(example);
		example.setLimit(ph.getRows());
		example.setOffset((ph.getPage() - 1) * ph.getRows());
		example.setOrderByClause("row_add_time DESC");
		List<ReadingRecord> list = readingRecordMapper.selectByExample(example);
		
		
		if (list.size() > 0) {
			for (ReadingRecord model : list) {
				BizUser user = bizUserMapper.selectByPrimaryKey(model.getBuserId());
				if (user != null) {
					model.setUserName(user.getName());
					model.setUserNickName(user.getNickname());
				}
			}
		}
		dg.setRows(list);
		
		dg.setTotal(total);

		return dg;
	}

	

}
