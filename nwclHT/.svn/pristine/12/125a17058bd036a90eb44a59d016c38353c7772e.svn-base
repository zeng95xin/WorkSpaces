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

import com.bola.nwcl.api.model.RecommendDetailModel;
import com.bola.nwcl.api.model.RecommendModel;
import com.bola.nwcl.api.model.RecommendRatingModel;
import com.bola.nwcl.biz.RecommendManager;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.common.util.file.FileUploadDeleteUtil;
import com.bola.nwcl.dal.mybatis.mapper.RecommendImgMapper;
import com.bola.nwcl.dal.mybatis.mapper.RecommendMapper;
import com.bola.nwcl.dal.mybatis.mapper.RecommendRatingMapper;
import com.bola.nwcl.dal.mybatis.mapper.RecommendTypeMapper;
import com.bola.nwcl.dal.mybatis.mapper.RecommendZanMapper;
import com.bola.nwcl.dal.mybatis.model.Recommend;
import com.bola.nwcl.dal.mybatis.model.RecommendExample;
import com.bola.nwcl.dal.mybatis.model.RecommendImg;
import com.bola.nwcl.dal.mybatis.model.RecommendImgExample;
import com.bola.nwcl.dal.mybatis.model.RecommendRating;
import com.bola.nwcl.dal.mybatis.model.RecommendRatingExample;
import com.bola.nwcl.dal.mybatis.model.RecommendZanExample;

@Service
public class RecommendManagerImpl  extends ManagerImpl<Recommend, RecommendExample, Long> implements RecommendManager{
	
	private RecommendMapper recommendMapper;
	
	@Autowired
	private RecommendTypeMapper recommendTypeMapper;
	
	@Autowired
	private RecommendImgMapper recommendImgMapper;
	
	@Autowired
	private RecommendRatingMapper recommendRatingMapper;
	
	@Autowired
	private RecommendZanMapper recommendZanMapper;
	
	@Autowired
	public RecommendManagerImpl(RecommendMapper recommendMapper) {
		this.mapper = recommendMapper;
		this.recommendMapper = recommendMapper;
	}

	@Override
	public void insertRecommend(String saveDir,Long buserId, String title, String description,Long typeId, MultipartFile[] imgs) throws Exception {
		if(recommendTypeMapper.selectByPrimaryKey(typeId) == null){
			throw new BusinessValidateException("类型不存在");
		}
		Recommend r = new Recommend();
		r.setBuserId(buserId);
		r.setDescription(description);
		r.setTitle(title);
		r.setTypeId(typeId);
		recommendMapper.insertAndGetId(r);
		String todayStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
		for(MultipartFile img : imgs){
			if(!img.isEmpty()){
				String fileName=UUID.randomUUID().toString().substring(0,12);
				fileName = todayStr + fileName;
				String[] paths = FileUploadDeleteUtil.upload2(img, null, saveDir, fileName);
				RecommendImg rimg = new RecommendImg();
				rimg.setImgPath(paths[0]);
				rimg.setImgPathThumbnail(paths[1]);
				rimg.setRecommendId(r.getId());
				recommendImgMapper.insert(rimg);
			}
		}
	}

	@Override
	public void deleteRecommend(Long buserId, Long id) throws Exception {
		Recommend r = recommendMapper.selectByPrimaryKey(id);
		if(r == null){
			throw new BusinessValidateException("该信息不存在");
		}
		if(r.getBuserId() != buserId){
			throw new BusinessValidateException("只能删除自己发布的特色推荐");
		}
		RecommendImgExample example_img = new RecommendImgExample();
		example_img.or().andRecommendIdEqualTo(id);
		List<RecommendImg> imgs = recommendImgMapper.selectByExample(example_img);
		
		for (RecommendImg img : imgs) {
			if (StringUtils.isNotBlank(img.getImgPath())) {
				FileUploadDeleteUtil.delete(img.getImgPath());
				FileUploadDeleteUtil.delete(img.getImgPathThumbnail());
			}
		}
		RecommendRatingExample example_rating = new RecommendRatingExample();
		example_rating.or().andRecommendIdEqualTo(id);
		recommendRatingMapper.deleteByExample(example_rating);
		
		RecommendZanExample example_zan = new RecommendZanExample();
		example_zan.or().andRecommendIdEqualTo(id);
		recommendZanMapper.deleteByExample(example_zan);
		
		recommendMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public Page<RecommendModel> getAllRecommend(long buserId, int page, int rows, Long type) {
		Map<String, Object> condition = new HashMap<String, Object>();
		PageRequest pager = new PageRequest(page - 1, rows);
		condition.put("limit", pager.getPageSize());
		condition.put("offset", pager.getOffset());
		condition.put("buserId", buserId);
		condition.put("type", type);
		
		RecommendExample example_r = new RecommendExample();
		if (type != null && type > 0) {
			example_r.or().andTypeIdEqualTo(type);
		}
		int count = recommendMapper.countByExample(example_r);
		
		List<RecommendModel> list = recommendMapper.getAllRecommend(condition);
		
		for(RecommendModel rm : list){
			RecommendImgExample example_img = new RecommendImgExample();
			example_img.or().andRecommendIdEqualTo(rm.getId());
			List<RecommendImg> imgs = recommendImgMapper.selectByExample(example_img);
			rm.setImgs(imgs);
		}
		
		Page<RecommendModel> pageData = new PageImpl<RecommendModel>(list, pager, count);
		
		return pageData;
	}

	@Override
	public RecommendDetailModel getRecommendDetail(long buserId, long id, int ratingCount) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("buserId", buserId);
		condition.put("id", id);
		List<RecommendModel> list = recommendMapper.getAllRecommend(condition);
		if(list == null || list.size() < 1){
			throw new BusinessValidateException("该信息不存在");
		}
		RecommendModel rm = list.get(0);
		RecommendDetailModel r = new RecommendDetailModel(); 
		BeanUtils.copyProperties(rm, r);
		
		RecommendImgExample example_img = new RecommendImgExample();
		example_img.or().andRecommendIdEqualTo(r.getId());
		List<RecommendImg> imgs = recommendImgMapper.selectByExample(example_img);
		r.setImgs(imgs);
		
		condition.put("limit", ratingCount);
		List<RecommendRatingModel> ratings = recommendRatingMapper.getPageRatingModel(condition);
		r.setRatings(ratings);
		
		return r;
	}

	@Override
	public Page<RecommendRatingModel> getRecommendRating(long buserId, long id, int page, int rows) {
		if(recommendMapper.selectByPrimaryKey(id) == null){
			throw new BusinessValidateException("该信息不存在");
		}
		
		Map<String, Object> condition = new HashMap<String, Object>();
		PageRequest pager = new PageRequest(page - 1, rows);
		condition.put("limit", pager.getPageSize());
		condition.put("offset", pager.getOffset());
		condition.put("buserId", buserId);
		condition.put("id", id);
		
		
		RecommendRatingExample example_rating = new RecommendRatingExample();
		example_rating.or().andRecommendIdEqualTo(id);
		int count = recommendRatingMapper.countByExample(example_rating);
		
		List<RecommendRatingModel> list = recommendRatingMapper.getPageRatingModel(condition);
		
		Page<RecommendRatingModel> pageData = new PageImpl<RecommendRatingModel>(list, pager, count);
		
		return pageData;
	}

	@Override
	public RecommendRatingModel getOneRatingModel(long id, long buserId) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("buserId", buserId);
		condition.put("id", id);
		return recommendRatingMapper.getOneRatingModel(condition);
	}

	@Override
	public void insertAndGetIdRagting(RecommendRating recommendRating) {
		recommendRatingMapper.insertAndGetId(recommendRating);
	}

}
