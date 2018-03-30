package com.bola.nwcl.biz;

import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.model.page.DataGrid;
import com.bola.nwcl.dal.model.page.PageHelper;
import com.bola.nwcl.dal.mybatis.model.ReadingRecord;
import com.bola.nwcl.dal.mybatis.model.ReadingRecordExample;

public interface ReadingRecordManager extends Manager<ReadingRecord, ReadingRecordExample, Long>{
	DataGrid dataGrid(ReadingRecord readingRecord, PageHelper ph);
}
