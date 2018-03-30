package com.bola.nwcl.biz.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bola.nwcl.api.model.market.OwnersMarketModel;
import com.bola.nwcl.api.model.market.OwnersMarketRatingModel;
import com.bola.nwcl.biz.OwnersMarketManager;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.common.util.FileManager;
import com.bola.nwcl.common.util.file.FileUploadDeleteUtil;
import com.bola.nwcl.common.util.sensitive.SensitivewordFilter;
import com.bola.nwcl.dal.mybatis.mapper.OwnersMarketImgMapper;
import com.bola.nwcl.dal.mybatis.mapper.OwnersMarketMapper;
import com.bola.nwcl.dal.mybatis.mapper.OwnersMarketRatingMapper;
import com.bola.nwcl.dal.mybatis.mapper.OwnersMarketZanMapper;
import com.bola.nwcl.dal.mybatis.mapper.ReportMapper;
import com.bola.nwcl.dal.mybatis.model.BizUser;
import com.bola.nwcl.dal.mybatis.model.OwnersMarket;
import com.bola.nwcl.dal.mybatis.model.OwnersMarketExample;
import com.bola.nwcl.dal.mybatis.model.OwnersMarketImg;
import com.bola.nwcl.dal.mybatis.model.OwnersMarketImgExample;
import com.bola.nwcl.dal.mybatis.model.OwnersMarketRatingExample;
import com.bola.nwcl.dal.mybatis.model.OwnersMarketZan;
import com.bola.nwcl.dal.mybatis.model.OwnersMarketZanExample;
import com.bola.nwcl.dal.mybatis.model.Report;
import com.bola.nwcl.web.model.LikeShareWebModel;

@Service
public class OwnersMarketManagerImpl  extends ManagerImpl<OwnersMarket, OwnersMarketExample, Long> implements OwnersMarketManager{
	
	private OwnersMarketMapper ownersMarketMapper;
	
	@Autowired
	private SensitivewordFilter sensitivewordFilter;
	
	@Autowired
	private OwnersMarketImgMapper ownersMarketImgMapper;
	
	@Autowired
	private OwnersMarketRatingMapper ownersMarketRatingMapper;
	
	@Autowired
	private OwnersMarketZanMapper ownersMarketZanMapper;
	
	@Autowired
	private ReportMapper reportMapper;
	
	@Autowired
	public OwnersMarketManagerImpl(OwnersMarketMapper ownersMarketMapper) {
		this.mapper = ownersMarketMapper;
		this.ownersMarketMapper = ownersMarketMapper;
	}

	@Override
	public void addOwnersMarket(String saveDir, BizUser buser, HttpServletRequest request, String title, String content, MultipartFile[] imgs) throws Exception {
		OwnersMarket o = new OwnersMarket();
		boolean issensitive =sensitivewordFilter.isContaintSensitiveWord(content, 1);
		if(issensitive){
			o.setIsSensitive(1);
		}else{
			o.setIsSensitive(0);
		}
		o.setBuserId(buser.getId());
		o.setContent(content);
		o.setTitle(title);
		o.setReadCount(0);
		ownersMarketMapper.insertAndGetId(o);
		String todayStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
		for(MultipartFile img : imgs){
			if(!img.isEmpty()){
				String fileName=UUID.randomUUID().toString().substring(0,12);
				fileName = todayStr + fileName;
				String[] saveFileName=FileUploadDeleteUtil.upload2(img, request, saveDir, fileName);
				
				OwnersMarketImg limg = new OwnersMarketImg();
				limg.setImgPath(saveFileName[0]);
				limg.setImgPathThumbnail(saveFileName[1]);
				limg.setOwnersMarketId(o.getId());
				ownersMarketImgMapper.insert(limg);
			}
		}
	}

	@Override
	public Page<OwnersMarketModel> getAllOwnersMarket(BizUser buser, String isGetCurrentUser, int page, int rows) {
		OwnersMarketExample example = new OwnersMarketExample();
		Map<String, Object> condition = new HashMap<String, Object>(4);
		if ("Y".equals(isGetCurrentUser)) {
			example.or().andBuserIdEqualTo(buser.getId());
			condition.put("getCurrent", buser.getId());
		}
		condition.put("buserId", buser.getId());
		PageRequest pager = new PageRequest(page - 1, rows);
		condition.put("limit", pager.getPageSize());
		condition.put("offset", pager.getOffset());
		condition.put("orderByClause", " a.row_add_time desc ");
		List<OwnersMarketModel> list = ownersMarketMapper.getAllOwnersMarket(condition);
		int total = ownersMarketMapper.countByExample(example);
		for(OwnersMarketModel model : list){
			OwnersMarketImgExample example_img = new OwnersMarketImgExample();
			example_img.or().andOwnersMarketIdEqualTo(model.getId());
			List<OwnersMarketImg> imgs = ownersMarketImgMapper.selectByExample(example_img);
			model.setImgs(imgs);
		}
		Page<OwnersMarketModel> pageData = new PageImpl<OwnersMarketModel>(list, pager, total);
		return pageData;
	}

	@Override
	public OwnersMarketModel updateGetOwnersMarketDetail(BizUser buser, long id, int ratingCount) {
		OwnersMarket o = ownersMarketMapper.selectByPrimaryKey(id);
		if(o == null){
			throw new BusinessValidateException("该信息不存在");
		}
		o.setReadCount(o.getReadCount()+1);
		ownersMarketMapper.updateByPrimaryKeySelective(o);
		Map<String, Object> condition = new HashMap<String, Object>(4);
		condition.put("buserId", buser.getId());
		condition.put("id", id);
		List<OwnersMarketModel> list = ownersMarketMapper.getAllOwnersMarket(condition);
		OwnersMarketModel model = list.get(0);
		OwnersMarketImgExample example_img = new OwnersMarketImgExample();
		example_img.or().andOwnersMarketIdEqualTo(model.getId());
		List<OwnersMarketImg> imgs = ownersMarketImgMapper.selectByExample(example_img);
		model.setImgs(imgs);
		
		condition.put("orderByClause", " a.row_add_time desc ");
		condition.put("limit", ratingCount);
		
		List<OwnersMarketRatingModel> ratings = ownersMarketRatingMapper.getOwnersMarketRating(condition);
		
		model.setRatings(ratings);
		
		return model;
	}

	@Override
	public void deleteOwnersMarket(BizUser buser, long id) {
		OwnersMarket o = ownersMarketMapper.selectByPrimaryKey(id);
		if(o == null){
			throw new BusinessValidateException("该信息不存在");
		}
		if(o.getBuserId().longValue() != buser.getId()){
			throw new BusinessValidateException("只能删除自己的信息");
		}
		OwnersMarketImgExample example_img = new OwnersMarketImgExample();
		example_img.or().andOwnersMarketIdEqualTo(id);
		List<OwnersMarketImg> imgs = ownersMarketImgMapper.selectByExample(example_img);
		for(OwnersMarketImg img : imgs){
			if(StringUtils.hasText(img.getImgPath())){
				FileUploadDeleteUtil.delete(img.getImgPath());
			}
			if(StringUtils.hasText(img.getImgPathThumbnail())){
				FileUploadDeleteUtil.delete(img.getImgPathThumbnail());
			}
		}
		ownersMarketImgMapper.deleteByExample(example_img);
		OwnersMarketRatingExample example_rating = new OwnersMarketRatingExample();
		example_rating.or().andOwnersMarketIdEqualTo(id);
		ownersMarketRatingMapper.deleteByExample(example_rating);
		ownersMarketMapper.deleteByPrimaryKey(id);
		OwnersMarketZanExample example_zan = new OwnersMarketZanExample();
		example_zan.or().andOwnersMarketIdEqualTo(id);
		ownersMarketZanMapper.deleteByExample(example_zan);
	}

	@Override
	public void updateZanOwnersMarket(long id, long buserId) {
		OwnersMarket om = ownersMarketMapper.selectByPrimaryKey(id);
		if(om == null){
			throw new BusinessValidateException("该信息已经被删除了");
		}
		OwnersMarketZanExample example_zan = new OwnersMarketZanExample();
		example_zan.or().andBuserIdEqualTo(buserId).andOwnersMarketIdEqualTo(id);
		List<OwnersMarketZan> list = ownersMarketZanMapper.selectByExample(example_zan);
		if(list == null || list.size() < 1){
			OwnersMarketZan zan = new OwnersMarketZan();
			zan.setBuserId(buserId);
			zan.setOwnersMarketId(id);
			ownersMarketZanMapper.insert(zan);
		}else{
			throw new BusinessValidateException("已经点过赞了");
		}
	}

	@Override
	public void updateCancelZanOwnersMarket(long id, long buserId) {
		OwnersMarket om = ownersMarketMapper.selectByPrimaryKey(id);
		if(om == null){
			throw new BusinessValidateException("该信息已经被删除了");
		}
		OwnersMarketZanExample example_zan = new OwnersMarketZanExample();
		example_zan.or().andBuserIdEqualTo(buserId).andOwnersMarketIdEqualTo(id);
		List<OwnersMarketZan> list = ownersMarketZanMapper.selectByExample(example_zan);
		if(list == null || list.size() < 1){
			throw new BusinessValidateException("还没点过赞");
		}else{
			ownersMarketZanMapper.deleteByExample(example_zan);
		}
	}

	@Override
	public void updateReportOwnersMarket(long id, long buserId) {
		OwnersMarket om = ownersMarketMapper.selectByPrimaryKey(id);
		if(om == null){
			throw new BusinessValidateException("该信息已经被删除了");
		}
		Report report = new Report();
		report.setBuserId(buserId);
		report.setType(3);
		report.setContentId(id);
		report.setContentRatingId(-1l);
		reportMapper.insert(report);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void edit(HttpServletRequest request,LikeShareWebModel likeShareWebModel,@RequestParam("file") MultipartFile[] file) throws Exception {
		String todayStr = new SimpleDateFormat("yyyyMMdd")
		.format(new Date());
		for (int i = 0; i < file.length; i++) {
			if(!file[i].isEmpty()){
				String fileName = UUID.randomUUID().toString()
						.substring(0, 12);
				fileName = todayStr + fileName;
			String[] imgPath=FileUploadDeleteUtil.upload2(file[i], request, "/upload/files/ownersmarket", fileName);
			for (int j = 0; j < imgPath.length; j++) {
				OwnersMarketImg imgmodel=new OwnersMarketImg();
				imgmodel.setImgPath(imgPath[0]);
				imgmodel.setImgPathThumbnail(imgPath[1]);
				imgmodel.setOwnersMarketId(likeShareWebModel.getId());
				ownersMarketImgMapper.insert(imgmodel);
			}
		}
		}
		OwnersMarket model=new OwnersMarket();
		model.setId(likeShareWebModel.getId());
		model.setTitle(likeShareWebModel.getTitle());
		model.setContent(likeShareWebModel.getContent());
		ownersMarketMapper.updateByPrimaryKeySelective(model);
		
	}

	@Override
	public void delete(Long id) throws Exception {
		//删除业主市集图片
		OwnersMarketImgExample ownersMarketImgExample=new OwnersMarketImgExample();
		ownersMarketImgExample.or().andOwnersMarketIdEqualTo(id);
		List<OwnersMarketImg> ownersMarketImg=ownersMarketImgMapper.selectByExample(ownersMarketImgExample);
		if (ownersMarketImg != null && ownersMarketImg.size() != 0) {
			for (OwnersMarketImg ownersMarketImg2 : ownersMarketImg) {
				if (ownersMarketImg2.getImgPath() != null) {
					FileManager.deleteFile(ownersMarketImg2.getImgPath());
				}
				if (ownersMarketImg2.getImgPathThumbnail() != null) {
					FileManager.deleteFile(ownersMarketImg2.getImgPathThumbnail());
				}
				ownersMarketImgMapper.deleteByPrimaryKey(ownersMarketImg2.getId());
			}
			
		}
		//删除业主市集zan
		OwnersMarketZanExample ownersMarketZanExample=new OwnersMarketZanExample();
		ownersMarketZanExample.or().andOwnersMarketIdEqualTo(id);
		ownersMarketZanMapper.deleteByExample(ownersMarketZanExample);
		//删除业主市集评论
		OwnersMarketRatingExample ownersMarketRatingExample=new OwnersMarketRatingExample();
		ownersMarketRatingExample.or().andOwnersMarketIdEqualTo(id);
		ownersMarketRatingMapper.deleteByExample(ownersMarketRatingExample);

		//删除爱分享
		ownersMarketMapper.deleteByPrimaryKey(id);
		
	}

}
