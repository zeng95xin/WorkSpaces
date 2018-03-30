package com.bola.nwcl.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bola.nwcl.api.model.indexes.FridendsIndexModel;
import com.bola.nwcl.api.model.indexes.OwnerFairIndexModel;
import com.bola.nwcl.api.model.indexes.ServiceAddIndexModel;
import com.bola.nwcl.biz.BizUserIndexesManager;
import com.bola.nwcl.dal.mybatis.mapper.ActivityMapper;
import com.bola.nwcl.dal.mybatis.mapper.IdlethingMapper;
import com.bola.nwcl.dal.mybatis.mapper.IndexesImgMapper;
import com.bola.nwcl.dal.mybatis.mapper.LectureHallMapper;
import com.bola.nwcl.dal.mybatis.mapper.LikeShareImgMapper;
import com.bola.nwcl.dal.mybatis.mapper.LikeShareMapper;
import com.bola.nwcl.dal.mybatis.mapper.LikeShareRatingMapper;
import com.bola.nwcl.dal.mybatis.mapper.NeighborHelpImgMapper;
import com.bola.nwcl.dal.mybatis.mapper.NeighborHelpMapper;
import com.bola.nwcl.dal.mybatis.mapper.NeighborHelpMessageMapper;
import com.bola.nwcl.dal.mybatis.mapper.RecommendMapper;
import com.bola.nwcl.dal.mybatis.model.ActivityExample;
import com.bola.nwcl.dal.mybatis.model.IndexesImg;
import com.bola.nwcl.dal.mybatis.model.IndexesImgExample;
import com.bola.nwcl.dal.mybatis.model.LectureHallExample;
import com.bola.nwcl.dal.mybatis.model.LikeShareExample;
import com.bola.nwcl.dal.mybatis.model.LikeShareImg;
import com.bola.nwcl.dal.mybatis.model.LikeShareImgExample;
import com.bola.nwcl.dal.mybatis.model.LikeShareRatingExample;
import com.bola.nwcl.dal.mybatis.model.NeighborHelpExample;
import com.bola.nwcl.dal.mybatis.model.NeighborHelpImg;
import com.bola.nwcl.dal.mybatis.model.NeighborHelpImgExample;
import com.bola.nwcl.dal.mybatis.model.NeighborHelpMessageExample;

@Service
public class BizUserIndexesManagerImpl implements BizUserIndexesManager{
	
	@Autowired
	private LikeShareMapper likeShareMapper;
	@Autowired
	private LikeShareRatingMapper likeShareRatingMapper;
	@Autowired
	private LikeShareImgMapper likeShareImgMapper;
	@Autowired
	private NeighborHelpMapper neighborHelpMapper;
	@Autowired
	private NeighborHelpMessageMapper neighborHelpMessageMapper;
	@Autowired
	private NeighborHelpImgMapper neighborHelpImgMapper;
	@Autowired
	private ActivityMapper activityMapper;
	@Autowired
	private LectureHallMapper lectureHallMapper;
	
	@Autowired
	private RecommendMapper recommendMapper;
	@Autowired
	private IdlethingMapper idlethingMapper;
	
	@Autowired
	private IndexesImgMapper indexesImgMapper;
	

	@Override
	public Page<FridendsIndexModel> getFriendsIndexInfo(int page, int rows) {
		PageRequest pager = new PageRequest(page - 1, rows);
		int count = 0;
		List<FridendsIndexModel> list = null;
		
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("offset", pager.getOffset());
		condition.put("limit", pager.getPageSize());
		condition.put("orderByClause", " row_add_time desc");
		list = likeShareMapper.getFriendsIndexInfo(condition);
		
		for(FridendsIndexModel f : list){
			if(f.getType() == 1){
				LikeShareRatingExample example_rating = new LikeShareRatingExample();
				example_rating.or().andLikeShareIdEqualTo(f.getId());
				int ratingCount = likeShareRatingMapper.countByExample(example_rating);
				f.setRatingCount(ratingCount);
				
				LikeShareImgExample example_img = new LikeShareImgExample();
				example_img.or().andLikeShareIdEqualTo(f.getId());
				List<LikeShareImg> imgs = likeShareImgMapper.selectByExample(example_img);
				f.setImgs(imgs);
			}else if(f.getType() == 2){
				NeighborHelpMessageExample example_msg = new NeighborHelpMessageExample();
				example_msg.or().andNeighborHelpIdEqualTo(f.getId());
				int ratingCount = neighborHelpMessageMapper.countByExample(example_msg);
				f.setRatingCount(ratingCount);
				
				NeighborHelpImgExample example_img = new NeighborHelpImgExample();
				example_img.or().andNeighborHelpIdEqualTo(f.getId());
				List<NeighborHelpImg> list_imgs = neighborHelpImgMapper.selectByExample(example_img);
				List<LikeShareImg> imgs = new ArrayList<LikeShareImg>(list_imgs.size());
				for(NeighborHelpImg img : list_imgs){
					LikeShareImg img_l = new LikeShareImg();
					BeanUtils.copyProperties(img, img_l);
					imgs.add(img_l);
				}
				f.setImgs(imgs);
			}
		}
		
		NeighborHelpExample example_n = new NeighborHelpExample();
		LikeShareExample example_l = new LikeShareExample();
		count = likeShareMapper.countByExample(example_l) + neighborHelpMapper.countByExample(example_n);
		
		Page<FridendsIndexModel> pageData = new PageImpl<FridendsIndexModel>(list, pager, count);
		
		return pageData;
	}

	@Override
	public Page<ServiceAddIndexModel> getServiceAddInfo(int page, int rows) {
		PageRequest pager = new PageRequest(page - 1, rows);
		int count = 0;
		List<ServiceAddIndexModel> list = null;
		
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("offset", pager.getOffset());
		condition.put("limit", pager.getPageSize());
		condition.put("orderByClause", " row_add_time desc");
		list = activityMapper.getServiceAddInfo(condition);
		
		ActivityExample example_a = new ActivityExample();
		LectureHallExample example_l = new LectureHallExample();
		count = activityMapper.countByExample(example_a) + lectureHallMapper.countByExample(example_l);
		
		Page<ServiceAddIndexModel> pageData = new PageImpl<ServiceAddIndexModel>(list, pager, count);
		
		return pageData;
	}
	
	@Override
	public Page<OwnerFairIndexModel> getOwnerFairIndex(int page, int rows) {
		PageRequest pager = new PageRequest(page - 1, rows);
		int count = 0;
		List<OwnerFairIndexModel> list = null;
		
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("offset", pager.getOffset());
		condition.put("limit", pager.getPageSize());
		condition.put("orderByClause", " row_add_time desc");
		list = recommendMapper.getOwnerFairIndex(condition);
		
		count = idlethingMapper.countByExample(null) + recommendMapper.countByExample(null);
		
		Page<OwnerFairIndexModel> pageData = new PageImpl<OwnerFairIndexModel>(list, pager, count);
		
		return pageData;
	}

	@Override
	public List<IndexesImg> getIndexesImg(int type) {
		IndexesImgExample example = new IndexesImgExample();
		example.or().andTypeEqualTo(type);
		List<IndexesImg> imgs = indexesImgMapper.selectByExample(example);
		return imgs;
	}
	
}
