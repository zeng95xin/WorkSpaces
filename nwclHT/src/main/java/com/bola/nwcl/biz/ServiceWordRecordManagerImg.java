package com.bola.nwcl.biz;

import java.util.List;

import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.mybatis.model.ServiceWordRecordImg;
import com.bola.nwcl.dal.mybatis.model.ServiceWordRecordImgExample;
import com.bola.nwcl.web.model.ServiceWordRecordImgModel;

public interface ServiceWordRecordManagerImg extends Manager<ServiceWordRecordImg, ServiceWordRecordImgExample, Long> {
	public List<ServiceWordRecordImgModel> dataGrid(Long id);

}
