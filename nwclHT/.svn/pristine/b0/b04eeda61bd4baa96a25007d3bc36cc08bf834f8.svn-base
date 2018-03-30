package com.bola.nwcl.biz.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bola.nwcl.api.model.IdlethingDetailModel;
import com.bola.nwcl.api.model.IdlethingModel;
import com.bola.nwcl.api.model.IdlethingRatingModel;
import com.bola.nwcl.biz.IdlethingManager;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.common.util.file.FileUploadDeleteUtil;
import com.bola.nwcl.dal.mybatis.mapper.IdlethingImgMapper;
import com.bola.nwcl.dal.mybatis.mapper.IdlethingMapper;
import com.bola.nwcl.dal.mybatis.mapper.IdlethingRatingMapper;
import com.bola.nwcl.dal.mybatis.mapper.IdlethingTypeMapper;
import com.bola.nwcl.dal.mybatis.mapper.IdlethingZanMapper;
import com.bola.nwcl.dal.mybatis.model.Idlething;
import com.bola.nwcl.dal.mybatis.model.IdlethingExample;
import com.bola.nwcl.dal.mybatis.model.IdlethingImg;
import com.bola.nwcl.dal.mybatis.model.IdlethingImgExample;
import com.bola.nwcl.dal.mybatis.model.IdlethingRating;
import com.bola.nwcl.dal.mybatis.model.IdlethingRatingExample;
import com.bola.nwcl.dal.mybatis.model.IdlethingZanExample;

@Service
public class IdlethingManagerImpl  extends ManagerImpl<Idlething, IdlethingExample, Long> implements IdlethingManager{
	
	private IdlethingMapper IdlethingMapper;
	
	@Autowired
	private IdlethingTypeMapper IdlethingTypeMapper;
	
	@Autowired
	private IdlethingImgMapper IdlethingImgMapper;
	
	@Autowired
	private IdlethingRatingMapper IdlethingRatingMapper;
	
	@Autowired
	private IdlethingZanMapper IdlethingZanMapper;
	
	@Autowired
	public IdlethingManagerImpl(IdlethingMapper IdlethingMapper) {
		this.mapper = IdlethingMapper;
		this.IdlethingMapper = IdlethingMapper;
	}

	@Override
	public void insertIdlething(String saveDir,Long buserId, String title, String description,Long typeId, MultipartFile[] imgs) throws Exception {
		if(IdlethingTypeMapper.selectByPrimaryKey(typeId) == null){
			throw new BusinessValidateException("类型不存在");
		}
		Idlething r = new Idlething();
		r.setBuserId(buserId);
		r.setDescription(description);
		r.setTitle(title);
		r.setTypeId(typeId);
		IdlethingMapper.insertAndGetId(r);
		String todayStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
		for(MultipartFile img : imgs){
			if(!img.isEmpty()){
				String fileName=UUID.randomUUID().toString().substring(0,12);
				fileName = todayStr + fileName;
				String[] paths = FileUploadDeleteUtil.upload2(img, null, saveDir, fileName);
				IdlethingImg rimg = new IdlethingImg();
				rimg.setImgPath(paths[0]);
				rimg.setImgPathThumbnail(paths[1]);
				rimg.setIdlethingId(r.getId());
				IdlethingImgMapper.insert(rimg);
			}
		}
	}

	@Override
	public void deleteIdlething(Long buserId, Long id) throws Exception {
		Idlething r = IdlethingMapper.selectByPrimaryKey(id);
		if(r == null){
			throw new BusinessValidateException("该信息不存在");
		}
		if(r.getBuserId() != buserId){
			throw new BusinessValidateException("只能删除自己发布的特色推荐");
		}
		IdlethingImgExample example_img = new IdlethingImgExample();
		example_img.or().andIdlethingIdEqualTo(id);
		List<IdlethingImg> imgs = IdlethingImgMapper.selectByExample(example_img);
		
		for (IdlethingImg img : imgs) {
			if (StringUtils.isNotBlank(img.getImgPath())) {
				FileUploadDeleteUtil.delete(img.getImgPath());
				FileUploadDeleteUtil.delete(img.getImgPathThumbnail());
			}
		}
		IdlethingRatingExample example_rating = new IdlethingRatingExample();
		example_rating.or().andIdlethingIdEqualTo(id);
		IdlethingRatingMapper.deleteByExample(example_rating);
		
		IdlethingZanExample example_zan = new IdlethingZanExample();
		example_zan.or().andIdlethingIdEqualTo(id);
		IdlethingZanMapper.deleteByExample(example_zan);
		
		IdlethingMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public Page<IdlethingModel> getAllIdlething(long buserId, int page, int rows, Long type) {
		Map<String, Object> condition = new HashMap<String, Object>();
		PageRequest pager = new PageRequest(page - 1, rows);
		condition.put("limit", pager.getPageSize());
		condition.put("offset", pager.getOffset());
		condition.put("buserId", buserId);
		condition.put("type", type);
		
		IdlethingExample example_r = new IdlethingExample();
		if (type != null && type > 0) {
			example_r.or().andTypeIdEqualTo(type);
		}
		int count = IdlethingMapper.countByExample(example_r);
		
		List<IdlethingModel> list = IdlethingMapper.getAllIdlething(condition);
		
		for(IdlethingModel rm : list){
			IdlethingImgExample example_img = new IdlethingImgExample();
			example_img.or().andIdlethingIdEqualTo(rm.getId());
			List<IdlethingImg> imgs = IdlethingImgMapper.selectByExample(example_img);
			rm.setImgs(imgs);
		}
		
		Page<IdlethingModel> pageData = new PageImpl<IdlethingModel>(list, pager, count);
		
		return pageData;
	}

	@Override
	public IdlethingDetailModel getIdlethingDetail(long buserId, long id, int ratingCount) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("buserId", buserId);
		condition.put("id", id);
		List<IdlethingModel> list = IdlethingMapper.getAllIdlething(condition);
		if(list == null || list.size() < 1){
			throw new BusinessValidateException("该信息不存在");
		}
		IdlethingModel rm = list.get(0);
		IdlethingDetailModel r = new IdlethingDetailModel(); 
		BeanUtils.copyProperties(rm, r);
		
		IdlethingImgExample example_img = new IdlethingImgExample();
		example_img.or().andIdlethingIdEqualTo(r.getId());
		List<IdlethingImg> imgs = IdlethingImgMapper.selectByExample(example_img);
		r.setImgs(imgs);
		
		condition.put("limit", ratingCount);
		List<IdlethingRatingModel> ratings = IdlethingRatingMapper.getPageRatingModel(condition);
		r.setRatings(ratings);
		
		return r;
	}

	@Override
	public Page<IdlethingRatingModel> getIdlethingRating(long buserId, long id, int page, int rows) {
		if(IdlethingMapper.selectByPrimaryKey(id) == null){
			throw new BusinessValidateException("该信息不存在");
		}
		
		Map<String, Object> condition = new HashMap<String, Object>();
		PageRequest pager = new PageRequest(page - 1, rows);
		condition.put("limit", pager.getPageSize());
		condition.put("offset", pager.getOffset());
		condition.put("buserId", buserId);
		condition.put("id", id);
		
		
		IdlethingRatingExample example_rating = new IdlethingRatingExample();
		example_rating.or().andIdlethingIdEqualTo(id);
		int count = IdlethingRatingMapper.countByExample(example_rating);
		
		List<IdlethingRatingModel> list = IdlethingRatingMapper.getPageRatingModel(condition);
		
		Page<IdlethingRatingModel> pageData = new PageImpl<IdlethingRatingModel>(list, pager, count);
		
		return pageData;
	}

	@Override
	public IdlethingRatingModel getOneRatingModel(long id, long buserId) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("buserId", buserId);
		condition.put("id", id);
		return IdlethingRatingMapper.getOneRatingModel(condition);
	}

	@Override
	public void insertAndGetIdRagting(IdlethingRating idlethingRating) {
		IdlethingRatingMapper.insertAndGetId(idlethingRating);
	}

}
