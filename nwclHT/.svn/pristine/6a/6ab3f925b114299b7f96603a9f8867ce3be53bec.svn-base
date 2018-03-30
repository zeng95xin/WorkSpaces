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

import com.bola.nwcl.api.model.neighbor.NeighborHelpMessageModel;
import com.bola.nwcl.api.model.neighbor.NeighborHelpModel;
import com.bola.nwcl.biz.NeighborHelpManager;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.common.util.FileManager;
import com.bola.nwcl.common.util.file.FileUploadDeleteUtil;
import com.bola.nwcl.common.util.sensitive.SensitivewordFilter;
import com.bola.nwcl.dal.mybatis.mapper.NeighborHelpImgMapper;
import com.bola.nwcl.dal.mybatis.mapper.NeighborHelpMapper;
import com.bola.nwcl.dal.mybatis.mapper.NeighborHelpMessageMapper;
import com.bola.nwcl.dal.mybatis.mapper.NeighborHelpZanMapper;
import com.bola.nwcl.dal.mybatis.mapper.ReportMapper;
import com.bola.nwcl.dal.mybatis.model.NeighborHelp;
import com.bola.nwcl.dal.mybatis.model.NeighborHelpExample;
import com.bola.nwcl.dal.mybatis.model.NeighborHelpImg;
import com.bola.nwcl.dal.mybatis.model.NeighborHelpImgExample;
import com.bola.nwcl.dal.mybatis.model.NeighborHelpMessageExample;
import com.bola.nwcl.dal.mybatis.model.NeighborHelpZan;
import com.bola.nwcl.dal.mybatis.model.NeighborHelpZanExample;
import com.bola.nwcl.dal.mybatis.model.Report;
import com.bola.nwcl.web.model.NeighborHelpWebModel;

@Service
public class NeighborHelpManagerImpl  extends ManagerImpl<NeighborHelp, NeighborHelpExample, Long> implements NeighborHelpManager{

	private NeighborHelpMapper neighborHelpMapper;

	@Autowired
	private NeighborHelpImgMapper neighborHelpImgMapper;

	@Autowired
	private NeighborHelpMessageMapper neighborHelpMessageMapper;

	@Autowired
	private NeighborHelpZanMapper neighborHelpZanMapper;

	@Autowired
	private SensitivewordFilter sensitivewordFilter;

	@Autowired
	private ReportMapper reportMapper;

	@Autowired
	public NeighborHelpManagerImpl(NeighborHelpMapper neighborHelpMapper) {
		this.mapper = neighborHelpMapper;
		this.neighborHelpMapper = neighborHelpMapper;
	}

	@Override
	public void insertAndGetId(NeighborHelp neighborHelp) {
		neighborHelpMapper.insertAndGetId(neighborHelp);
	}

	@Override
	public void deleteByKeeper(HttpServletRequest request, String saveDir, Long id) {
		NeighborHelp nh = neighborHelpMapper.selectByPrimaryKey(id);
		if(nh == null){
			throw new BusinessValidateException("该信息不存在");
		}
		NeighborHelpImgExample example_img = new NeighborHelpImgExample();
		example_img.or().andNeighborHelpIdEqualTo(id);
		List<NeighborHelpImg> imgs = neighborHelpImgMapper.selectByExample(example_img);
		for(NeighborHelpImg img : imgs){
			if(StringUtils.isNotBlank(img.getImgPath())){
				String realPath = request.getSession().getServletContext().getRealPath(saveDir);
				String deletePath=img.getImgPath().replace(saveDir, "");
				FileManager.deleteFile(realPath+deletePath);
			}
		}

		NeighborHelpMessageExample r = new NeighborHelpMessageExample();
		r.or().andNeighborHelpIdEqualTo(id);
		neighborHelpMessageMapper.deleteByExample(r);

		neighborHelpImgMapper.deleteByExample(example_img);
		neighborHelpMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void addNeighborHelp(String saveDir, long buserId, HttpServletRequest request, String title,
			String content, String reward, MultipartFile[] imgs) throws Exception {
		NeighborHelp n = new NeighborHelp();

		n.setBuserId(buserId);
		n.setContent(content);
		n.setReward(reward);
		n.setTitle(title);
		n.setStatus(0);
		n.setReadCount(0);

		boolean issensitive = sensitivewordFilter.isContaintSensitiveWord(content, 1);
		if(issensitive){
			n.setIsSensitive(1);
		}else{
			n.setIsSensitive(0);
		}
		neighborHelpMapper.insertAndGetId(n);

		String todayStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
		for(MultipartFile img : imgs){
			if(!img.isEmpty()){
				String fileName=UUID.randomUUID().toString().substring(0,12);
				fileName = todayStr + fileName;
				String[] saveFileName=FileUploadDeleteUtil.upload2(img, request, saveDir, fileName);

				NeighborHelpImg nimg = new NeighborHelpImg();
				nimg.setImgPath(saveFileName[0]);
				nimg.setImgPathThumbnail(saveFileName[1]);
				nimg.setNeighborHelpId(n.getId());
				neighborHelpImgMapper.insert(nimg);
			}
		}
	}

	@Override
	public Page<NeighborHelpModel> getAllNeighborHelp(long buserId, int page, int rows, String isGetCurrentUser) {
		int getType = 0;
		if ("Y".equals(isGetCurrentUser)) {
			getType = 1;
		}
		PageRequest pager = new PageRequest(page - 1, rows);
		Map<String, Object> condition = new HashMap<String, Object>(4);
		NeighborHelpExample example_nh = new NeighborHelpExample();
		if (getType == 1) {
			example_nh.or().andBuserIdEqualTo(buserId);
			condition.put("getType", getType);
		}
		int count = neighborHelpMapper.countByExample(example_nh);

		condition.put("buserId", buserId);
		condition.put("limit", pager.getPageSize());
		condition.put("offset", pager.getOffset());

		List<NeighborHelpModel> list = neighborHelpMapper.getAllNeighborHelpByUser(condition);
		for(NeighborHelpModel nhm : list){
			NeighborHelpImgExample example_img = new NeighborHelpImgExample();
			example_img.or().andNeighborHelpIdEqualTo(nhm.getId());
			List<NeighborHelpImg> imgs = neighborHelpImgMapper.selectByExample(example_img);
			nhm.setImgs(imgs);

		}
		Page<NeighborHelpModel> pageData = new PageImpl<NeighborHelpModel>(list, pager, count);
		return pageData;
	}

	@Override
	public NeighborHelpModel updateGetNeighborDetail(long id, long buserId,
			int messageCount) {
		NeighborHelp nh = neighborHelpMapper.selectByPrimaryKey(id);
		if(nh == null){
			throw new BusinessValidateException("该信息已经被删除");
		}
		nh.setReadCount(nh.getReadCount()==null?1:nh.getReadCount()+1);
		neighborHelpMapper.updateByPrimaryKeySelective(nh);
		Map<String, Object> condition = new HashMap<String, Object>(2);
		condition.put("id", id);
		condition.put("buserId", buserId);
		NeighborHelpModel nhm = neighborHelpMapper.getAllNeighborHelpByUser(condition).get(0);
		NeighborHelpImgExample example_img = new NeighborHelpImgExample();
		example_img.or().andNeighborHelpIdEqualTo(nhm.getId());
		List<NeighborHelpImg> imgs = neighborHelpImgMapper.selectByExample(example_img);
		nhm.setImgs(imgs);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("buserId", buserId);
		map.put("limit", messageCount);
		List<NeighborHelpMessageModel> list = neighborHelpMessageMapper.getPageMessageModel(map);
		nhm.setMsgs(list);
		return nhm;
	}

	@Override
	public void deleteNeighborHelp(long id, long buserId) {
		NeighborHelp nh = neighborHelpMapper.selectByPrimaryKey(id);
		if(nh == null){
			throw new BusinessValidateException("该信息不存在");
		}
		if(nh.getBuserId().longValue() != buserId){
			throw new BusinessValidateException("只能删除自己的邻里帮");
		}
		NeighborHelpImgExample example_img = new NeighborHelpImgExample();
		example_img.or().andNeighborHelpIdEqualTo(id);
		List<NeighborHelpImg> imgs = neighborHelpImgMapper.selectByExample(example_img);
		for(NeighborHelpImg img : imgs){
			FileUploadDeleteUtil.delete(img.getImgPath());
			FileUploadDeleteUtil.delete(img.getImgPathThumbnail());
		}
		NeighborHelpMessageExample r = new NeighborHelpMessageExample();
		r.or().andNeighborHelpIdEqualTo(id);
		neighborHelpMessageMapper.deleteByExample(r);
		neighborHelpImgMapper.deleteByExample(example_img);
		neighborHelpMapper.deleteByPrimaryKey(id);
		NeighborHelpZanExample example_zan = new NeighborHelpZanExample();
		example_zan.or().andNeighborHelpIdEqualTo(id);
		neighborHelpZanMapper.deleteByExample(example_zan);
	}

	@Override
	public void updateZanNeighborHelp(long id, long buserId) {
		NeighborHelp nh = neighborHelpMapper.selectByPrimaryKey(id);
		if(nh == null){
			throw new BusinessValidateException("该信息已经被删除了");
		}
		NeighborHelpZanExample example_zan = new NeighborHelpZanExample();
		example_zan.or().andBuserIdEqualTo(buserId).andNeighborHelpIdEqualTo(id);
		List<NeighborHelpZan> list = neighborHelpZanMapper.selectByExample(example_zan);
		if(list == null || list.size() < 1){
			NeighborHelpZan zan = new NeighborHelpZan();
			zan.setBuserId(buserId);
			zan.setNeighborHelpId(id);
			neighborHelpZanMapper.insert(zan);
		}else{
			throw new BusinessValidateException("已经点过赞了");
		}
	}

	@Override
	public void updateCancelZanNeighborHelp(long id, long buserId) {
		NeighborHelp nh = neighborHelpMapper.selectByPrimaryKey(id);
		if(nh == null){
			throw new BusinessValidateException("该信息已经被删除了");
		}
		NeighborHelpZanExample example_zan = new NeighborHelpZanExample();
		example_zan.or().andBuserIdEqualTo(buserId).andNeighborHelpIdEqualTo(id);
		List<NeighborHelpZan> list = neighborHelpZanMapper.selectByExample(example_zan);
		if(list == null || list.size() < 1){
			throw new BusinessValidateException("还没点过赞");
		}else{
			neighborHelpZanMapper.deleteByExample(example_zan);
		}
	}

	@Override
	public void updateReportNeighborHelp(long id, long buserId) {
		NeighborHelp nh = neighborHelpMapper.selectByPrimaryKey(id);
		if(nh == null){
			throw new BusinessValidateException("该信息已经被删除了");
		}
		Report report = new Report();
		report.setBuserId(buserId);
		report.setType(2);
		report.setContentId(id);
		report.setContentRatingId(-1l);
		reportMapper.insert(report);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void edit(HttpServletRequest request,
			NeighborHelpWebModel neighborHelpWebModel, MultipartFile[] file)
					throws Exception {
		
		String todayStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
		for (int i = 0; i < file.length; i++) {
			if(!file[i].isEmpty()){
				String fileName = UUID.randomUUID().toString().substring(0, 12);
				fileName = todayStr + fileName;
				String[] imgPath=FileUploadDeleteUtil.upload2(file[i], request, "/upload/files/neighbor", fileName);
				for (int j = 0; j < imgPath.length; j++) {
					NeighborHelpImg imgmodel=new NeighborHelpImg();
					imgmodel.setImgPath(imgPath[0]);
					imgmodel.setImgPathThumbnail(imgPath[1]);
					imgmodel.setNeighborHelpId(neighborHelpWebModel.getId());
					neighborHelpImgMapper.insert(imgmodel);
				}
			}
		}

		NeighborHelp model=new NeighborHelp();
		model.setId(neighborHelpWebModel.getId());
		model.setTitle(neighborHelpWebModel.getTitle());
		model.setContent(neighborHelpWebModel.getContent());
		model.setReward(neighborHelpWebModel.getReward());
		neighborHelpMapper.updateByPrimaryKeySelective(model);

	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void delete(Long id) throws Exception {
		//删除邻里帮评论
		NeighborHelpMessageExample example=new NeighborHelpMessageExample();
		example.or().andNeighborHelpIdEqualTo(id);
		neighborHelpMessageMapper.deleteByExample(example);
		//删除邻里帮图片
		NeighborHelpImgExample neighborHelpImgExample=new NeighborHelpImgExample();
		neighborHelpImgExample.or().andNeighborHelpIdEqualTo(id);
		List<NeighborHelpImg> list=neighborHelpImgMapper.selectByExample(neighborHelpImgExample);
		if (list != null && list.size() !=0) {
			for (NeighborHelpImg neighborHelpImg : list) {
				if (neighborHelpImg.getImgPath()!=null) {
					FileManager.deleteFile(neighborHelpImg.getImgPath());
				}
				if (neighborHelpImg.getImgPathThumbnail()!=null) {
					FileManager.deleteFile(neighborHelpImg.getImgPathThumbnail());
				}
				neighborHelpImgMapper.deleteByPrimaryKey(neighborHelpImg.getId());
			}
			
		}
		//删除邻里帮zan
		NeighborHelpZanExample neighborHelpZanExample=new NeighborHelpZanExample();
		neighborHelpZanExample.or().andNeighborHelpIdEqualTo(id);
		neighborHelpZanMapper.deleteByExample(neighborHelpZanExample);
		//删除邻里帮

		neighborHelpMapper.deleteByPrimaryKey(id);
		
	}

}
