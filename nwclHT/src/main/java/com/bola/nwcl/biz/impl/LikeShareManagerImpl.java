package com.bola.nwcl.biz.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bola.nwcl.api.model.likeshare.LikeShareRatingSimpleModel;
import com.bola.nwcl.api.model.likeshare.LikeShareSimpleModel;
import com.bola.nwcl.biz.LikeShareManager;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.common.util.FileManager;
import com.bola.nwcl.common.util.file.FileUploadDeleteUtil;
import com.bola.nwcl.common.util.sensitive.SensitivewordFilter;
import com.bola.nwcl.dal.mybatis.mapper.LikeShareImgMapper;
import com.bola.nwcl.dal.mybatis.mapper.LikeShareMapper;
import com.bola.nwcl.dal.mybatis.mapper.LikeShareRatingMapper;
import com.bola.nwcl.dal.mybatis.mapper.LikeShareZanMapper;
import com.bola.nwcl.dal.mybatis.mapper.ReportMapper;
import com.bola.nwcl.dal.mybatis.model.LikeShare;
import com.bola.nwcl.dal.mybatis.model.LikeShareExample;
import com.bola.nwcl.dal.mybatis.model.LikeShareImg;
import com.bola.nwcl.dal.mybatis.model.LikeShareImgExample;
import com.bola.nwcl.dal.mybatis.model.LikeShareRatingExample;
import com.bola.nwcl.dal.mybatis.model.LikeShareZan;
import com.bola.nwcl.dal.mybatis.model.LikeShareZanExample;
import com.bola.nwcl.dal.mybatis.model.Report;
import com.bola.nwcl.web.model.LikeShareWebModel;

@Service
public class LikeShareManagerImpl  extends ManagerImpl<LikeShare, LikeShareExample, Long> implements LikeShareManager{
	
	private LikeShareMapper likeShareMapper;
	
	@Autowired
	private LikeShareImgMapper likeShareImgMapper;
	
	@Autowired
	private LikeShareRatingMapper likeShareRatingMapper;
	
	@Autowired
	private LikeShareZanMapper likeShareZanMapper;
	
	@Autowired
	private SensitivewordFilter sensitivewordFilter;
	
	@Autowired
	private ReportMapper reportMapper;
	
	@Autowired
	public LikeShareManagerImpl(LikeShareMapper likeShareMapper) {
		this.mapper = likeShareMapper;
		this.likeShareMapper = likeShareMapper;
	}

	@Override
	public void insertAndGetId(LikeShare likeShare) {
		likeShareMapper.insertAndGetId(likeShare);
	}

	@Override
	public void deleteByKeeper(HttpServletRequest request, String saveDir, Long id) {
		LikeShare ls = likeShareMapper.selectByPrimaryKey(id);
		if(ls == null){
			throw new BusinessValidateException("该信息不存在");
		}
		LikeShareImgExample example_img = new LikeShareImgExample();
		example_img.or().andLikeShareIdEqualTo(id);
		List<LikeShareImg> imgs = likeShareImgMapper.selectByExample(example_img);
		for(LikeShareImg img : imgs){
			if(StringUtils.isNotBlank(img.getImgPath())){
				String realPath = request.getSession().getServletContext().getRealPath(saveDir);
				String deletePath=img.getImgPath().replace(saveDir, "");
				FileManager.deleteFile(realPath+deletePath);
			}
		}
		
		LikeShareRatingExample r = new LikeShareRatingExample();
		r.or().andLikeShareIdEqualTo(id);
		likeShareRatingMapper.deleteByExample(r);
		
		likeShareImgMapper.deleteByExample(example_img);
		likeShareMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void addLikeShare(String saveDir, long buserId, HttpServletRequest request, String title,
			String content, String shareType, MultipartFile[] imgs) throws Exception {
		
		LikeShare l = new LikeShare();
		l.setBuserId(buserId);
		l.setContent(content);
		l.setShareType(shareType);
		l.setTitle(title);
		l.setIsSensitive(0);
		l.setReadCount(0);
		
		boolean issensitive =sensitivewordFilter.isContaintSensitiveWord(content, 1);
		if(issensitive){
			l.setIsSensitive(1);
		}else{
			l.setIsSensitive(0);
		}
		likeShareMapper.insertAndGetId(l);
		
		String todayStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
		for(MultipartFile img : imgs){
			if(!img.isEmpty()){
				String fileName=UUID.randomUUID().toString().substring(0,12);
				fileName = todayStr + fileName;
				String[] saveFileName=FileUploadDeleteUtil.upload2(img, request, saveDir, fileName);
				
				LikeShareImg limg = new LikeShareImg();
				limg.setImgPath(saveFileName[0]);
				limg.setImgPathThumbnail(saveFileName[1]);
				limg.setLikeShareId(l.getId());
				likeShareImgMapper.insert(limg);
			}
		}
		
	}

	@Override
	public Page<LikeShareSimpleModel> getAllLikeShare(long buserId, int page,
			int rows, String isGetCurrentUser) {
		int getType = 0;
		if ("Y".equals(isGetCurrentUser)) {
			getType = 1;
		}
		PageRequest pager = new PageRequest(page - 1, rows);
		Map<String, Object> condition = new HashMap<String, Object>(4);
		LikeShareExample example_ls = new LikeShareExample();
		if (getType == 1) {
			example_ls.or().andBuserIdEqualTo(buserId);
			condition.put("getType", getType);
		}
		int count = likeShareMapper.countByExample(example_ls);
		condition.put("buserId", buserId);
		condition.put("limit", pager.getPageSize());
		condition.put("offset", pager.getOffset());
		List<LikeShareSimpleModel> list = likeShareMapper.getAllLikeShareByUser(condition);
		for(LikeShareSimpleModel lssm : list){
			LikeShareImgExample example_img = new LikeShareImgExample();
			example_img.or().andLikeShareIdEqualTo(lssm.getId());
			List<LikeShareImg> imgs = likeShareImgMapper.selectByExample(example_img);
			lssm.setImgs(imgs);
			
			/*Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("buserId", buserId);
			map.put("limit", ratingCount);
			List<LikeShareRatingSimpleModel> list = likeShareRatingMapper.getRatingModel(map);*/
			
		}
		Page<LikeShareSimpleModel> pageData = new PageImpl<LikeShareSimpleModel>(list, pager, count);
		return pageData;
	}

	@Override
	public LikeShareSimpleModel updateGetLikeShareDetail(long id, long buserId,
			int ratingCount) {
		LikeShare ls = likeShareMapper.selectByPrimaryKey(id);
		if(ls == null){
			throw new BusinessValidateException("该信息不存在");
		}
		ls.setReadCount(ls.getReadCount()==null?1:ls.getReadCount()+1);
		likeShareMapper.updateByPrimaryKeySelective(ls);
		Map<String, Object> condition = new HashMap<String, Object>(2);
		condition.put("buserId", buserId);
		condition.put("id", id);
		List<LikeShareSimpleModel> list = likeShareMapper.getAllLikeShareByUser(condition);
		LikeShareSimpleModel lssm = null;
		lssm = list.get(0);
		LikeShareImgExample example_img = new LikeShareImgExample();
		example_img.or().andLikeShareIdEqualTo(lssm.getId());
		List<LikeShareImg> imgs = likeShareImgMapper.selectByExample(example_img);
		lssm.setImgs(imgs);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("buserId", buserId);
		map.put("limit", ratingCount);
		List<LikeShareRatingSimpleModel> list_r = likeShareRatingMapper.getRatingModel(map);
		lssm.setRatings(list_r);
		return lssm;
	}

	@Override
	public void deleteLikeShare(long id, long buserId) {
		LikeShare ls = likeShareMapper.selectByPrimaryKey(id);
		if(ls == null){
			throw new BusinessValidateException("该信息不存在");
		}
		if(ls.getBuserId().longValue() != buserId){
			throw new BusinessValidateException("只能删除自己的爱分享");
		}
		LikeShareImgExample example_img = new LikeShareImgExample();
		example_img.or().andLikeShareIdEqualTo(id);
		List<LikeShareImg> imgs = likeShareImgMapper.selectByExample(example_img);
		for(LikeShareImg img : imgs){
			FileUploadDeleteUtil.delete(img.getImgPath());
			FileUploadDeleteUtil.delete(img.getImgPathThumbnail());
		}
		LikeShareRatingExample r = new LikeShareRatingExample();
		r.or().andLikeShareIdEqualTo(id);
		likeShareRatingMapper.deleteByExample(r);
		likeShareImgMapper.deleteByExample(example_img);
		likeShareMapper.deleteByPrimaryKey(id);
		LikeShareZanExample example_zan = new LikeShareZanExample();
		example_zan.or().andLikeShareIdEqualTo(id);
		likeShareZanMapper.deleteByExample(example_zan);
	}

	@Override
	public void updateZanLikeShare(long id, long buserId) {
		LikeShare ls = likeShareMapper.selectByPrimaryKey(id);
		if(ls == null){
			throw new BusinessValidateException("该信息已经被删除了");
		}
		LikeShareZanExample example_zan = new LikeShareZanExample();
		example_zan.or().andBuserIdEqualTo(buserId).andLikeShareIdEqualTo(id);
		List<LikeShareZan> list = likeShareZanMapper.selectByExample(example_zan);
		if(list == null || list.size() < 1){
			LikeShareZan zan = new LikeShareZan();
			zan.setBuserId(buserId);
			zan.setLikeShareId(id);
			likeShareZanMapper.insert(zan);
		}else{
			throw new BusinessValidateException("已经点过赞了");
		}
			
	}
	
	@Override
	public void updateCancelZanLikeShare(long id, long buserId) {
		LikeShare ls = likeShareMapper.selectByPrimaryKey(id);
		if(ls == null){
			throw new BusinessValidateException("该信息已经被删除了");
		}
		LikeShareZanExample example_zan = new LikeShareZanExample();
		example_zan.or().andBuserIdEqualTo(buserId).andLikeShareIdEqualTo(id);
		List<LikeShareZan> list = likeShareZanMapper.selectByExample(example_zan);
		if(list == null || list.size() < 1){
			throw new BusinessValidateException("还没点过赞");
		}else{
			likeShareZanMapper.deleteByExample(example_zan);
		}
	}

	@Override
	public void updateReportLikeShare(long id, long buserId) {
		LikeShare ls = likeShareMapper.selectByPrimaryKey(id);
		if(ls == null){
			throw new BusinessValidateException("该信息已经被删除了");
		}
		Report report = new Report();
		report.setBuserId(buserId);
		report.setType(1);
		report.setContentId(id);
		report.setContentRatingId(-1l);
		reportMapper.insert(report);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void edit(HttpServletRequest request,
			LikeShareWebModel likeShareWebModel, MultipartFile[] file)
			throws Exception {
		String todayStr = new SimpleDateFormat("yyyyMMdd")
		.format(new Date());
		for (int i = 0; i < file.length; i++) {
			if(!file[i].isEmpty()){
				String fileName = UUID.randomUUID().toString()
						.substring(0, 12);
				fileName = todayStr + fileName;
			String[] imgPath=FileUploadDeleteUtil.upload2(file[i], request, "/upload/files/likeshare", fileName);
			for (int j = 0; j < imgPath.length; j++) {
				LikeShareImg imgmodel=new LikeShareImg();
				imgmodel.setImgPath(imgPath[0]);
				imgmodel.setImgPathThumbnail(imgPath[1]);
				imgmodel.setLikeShareId(likeShareWebModel.getId());
				likeShareImgMapper.insert(imgmodel);
					}
				}
			}

		LikeShare model=new LikeShare();
		model.setId(likeShareWebModel.getId());
		model.setTitle(likeShareWebModel.getTitle());
		model.setContent(likeShareWebModel.getContent());
		if (likeShareWebModel.getType()==1) {
			model.setShareType("精彩活动");
		}
		if (likeShareWebModel.getType()==2) {
			model.setShareType("和谐社区");
		}
		if (likeShareWebModel.getType()==3) {
			model.setShareType("生活好物");
		}
		likeShareMapper.updateByPrimaryKeySelective(model);
		
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void delete(Long id) throws Exception {
		//删除爱分享图片
		LikeShareImgExample likeShareImgExample=new LikeShareImgExample();
		likeShareImgExample.or().andLikeShareIdEqualTo(id);
		List<LikeShareImg> list=likeShareImgMapper.selectByExample(likeShareImgExample);
		if (list != null && list.size() != 0) {
			for (LikeShareImg likeShareImg : list) {
				if (likeShareImg.getImgPath() != null) {
					FileManager.deleteFile(likeShareImg.getImgPath());
				}
				if (likeShareImg.getImgPathThumbnail() != null) {
					FileManager.deleteFile(likeShareImg.getImgPathThumbnail());
				}
				likeShareImgMapper.deleteByPrimaryKey(likeShareImg.getId());
			}
			
		}
		//删除爱分享评论
		LikeShareRatingExample likeShareRatingExample=new LikeShareRatingExample();
		likeShareRatingExample.or().andLikeShareIdEqualTo(id);
		likeShareRatingMapper.deleteByExample(likeShareRatingExample);
		//删除爱分享赞
		LikeShareZanExample likeShareZanExample=new LikeShareZanExample();
		likeShareZanExample.or().andLikeShareIdEqualTo(id);
		likeShareZanMapper.deleteByExample(likeShareZanExample);
		//删除爱分享
		likeShareMapper.deleteByPrimaryKey(id);
		
	}

}
