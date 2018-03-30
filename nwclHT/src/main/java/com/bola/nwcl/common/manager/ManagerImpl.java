package com.bola.nwcl.common.manager;

import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.mybatis.mapper.Mapper;
import com.bola.nwcl.common.mybatis.model.Model;
import com.bola.nwcl.common.mybatis.model.ModelExample;


public abstract class ManagerImpl<M extends Model, E extends ModelExample, ID>
		implements Manager<M, E, ID> {

	protected Mapper<M, E, ID> mapper;

	protected void resetTime(M model) {
		model.setRawAddTime(null);
		model.setRawUpdateTime(null);
		model.setRowAddTime(null);
		model.setRowUpdateTime(null);
	}

	@Override
	final public int countByExample(E example) {
		return mapper.countByExample(example);
	}

	@Override
	final public int deleteByExample(E example) {
		return mapper.deleteByExample(example);
	}

	@Override
	final public int deleteByPrimaryKey(ID id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	final public int insert(M model) {
		resetTime(model);
		int flag = mapper.insert(model);
		if (flag == 0) {
			throw new BusinessDealException("新增失败");
		}
		return flag;
	}

//	@Override
//	final public int insertSelective(M model) {
//		resetTime(model);
//		return mapper.insertSelective(model);
//	}

	@Override
	final public List<M> selectByExample(E example) {


		List<M> list = mapper.selectByExample(example);
		if (list == null) {
			list = Collections.emptyList();
		}
		return list;
	}

	@Override
	final public M selectByPrimaryKey(ID id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	final public M selectOneByExample(E example) {
		example.setLimit(1);
		example.setOffset(0);
		List<M> list = selectByExample(example);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	final public Page<M> selectPageByExample(Pageable pageable, E example) {
		example.setLimit(pageable.getPageSize());
		example.setOffset(pageable.getOffset());
		List<M> content = selectByExample(example);
		long total = countByExample(example);
		Page<M> page = new PageImpl<M>(content, pageable, total);
		return page;
	}

	@Override
	final public Page<M> selectPageByExample(Pageable pageable,long rows, E example) {
		example.setLimit(pageable.getPageSize());
		example.setOffset(pageable.getOffset());
		List<M> content = selectByExample(example);
		Page<M> page = new PageImpl<M>(content, pageable, rows);
		return page;
	}

	@Override
	final public int updateByExampleSelective(M model, E example) {
		resetTime(model);
		return mapper.updateByExampleSelective(model, example);
	}

//	@Override
//	final public int updateByExample(M model, E example) {
//		resetTime(model);
//		return mapper.updateByExample(model, example);
//	}

	@Override
	final public int updateByPrimaryKeySelective(M model) {
		resetTime(model);
		return mapper.updateByPrimaryKeySelective(model);
	}

//	@Override
//	final public int updateByPrimaryKey(M model) {
//		resetTime(model);
//		return mapper.updateByPrimaryKey(model);
//	}

}
