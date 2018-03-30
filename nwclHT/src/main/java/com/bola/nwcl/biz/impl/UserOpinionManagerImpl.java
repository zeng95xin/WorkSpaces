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
import org.springframework.web.multipart.MultipartFile;

import com.bola.nwcl.api.model.UserOpinionModel;
import com.bola.nwcl.biz.UserOpinionManager;
import com.bola.nwcl.common.manager.ManagerImpl;
import com.bola.nwcl.common.util.file.FileUploadDeleteUtil;
import com.bola.nwcl.dal.mybatis.mapper.UserOpinionImgMapper;
import com.bola.nwcl.dal.mybatis.mapper.UserOpinionMapper;
import com.bola.nwcl.dal.mybatis.model.UserOpinion;
import com.bola.nwcl.dal.mybatis.model.UserOpinionExample;
import com.bola.nwcl.dal.mybatis.model.UserOpinionImg;
import com.bola.nwcl.dal.mybatis.model.UserOpinionImgExample;

@Service
public class UserOpinionManagerImpl  extends ManagerImpl<UserOpinion, UserOpinionExample, Long> implements UserOpinionManager{
	
	private UserOpinionMapper userOpinionMapper;
	
	@Autowired
	private UserOpinionImgMapper userOpinionImgMapper;
	
	@Autowired
	public UserOpinionManagerImpl(UserOpinionMapper userOpinionMapper) {
		this.mapper = userOpinionMapper;
		this.userOpinionMapper = userOpinionMapper;
	}

	@Override
	public Page<UserOpinionModel> getUserAllOpinion(long buserId, int page, int rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		PageRequest pager = new PageRequest(page - 1, rows);
		map.put("buserId", buserId);
		map.put("limit", pager.getPageSize());
		map.put("offset", pager.getOffset());
		
		UserOpinionExample example = new UserOpinionExample();
		example.or().andBuserIdEqualTo(buserId);
		int count = userOpinionMapper.countByExample(example);
		List<UserOpinionModel> list = userOpinionMapper.getUserAllMsg(map);
		for(UserOpinionModel model : list){
			UserOpinionImgExample example_img = new UserOpinionImgExample();
			example_img.or().andUserOpinionIdEqualTo(model.getId());
			List<UserOpinionImg> imgs = userOpinionImgMapper.selectByExample(example_img);
			model.setImgs(imgs);
		}
		Page<UserOpinionModel> pageData = new PageImpl<UserOpinionModel>(list, pager, count);
		return pageData;
	}

	@Override
	public void addOpinion(String content, MultipartFile[] imgs, String saveDir, long buserId, HttpServletRequest request) throws Exception {
		UserOpinion msg = new UserOpinion();
		msg.setBuserId(buserId);
		msg.setSendType(1);
		msg.setStatus(0);
		msg.setContent(content);
		userOpinionMapper.insertAndGetId(msg);
		String todayStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
		for(int i=0 ; i<imgs.length; i++){
			MultipartFile file = imgs[i];
			if(!file.isEmpty()){
				String fileName = UUID.randomUUID().toString().substring(0,12);
				fileName = todayStr + fileName;
				String[] saveFileName = FileUploadDeleteUtil.upload2(file, request, saveDir, fileName);
				UserOpinionImg img = new UserOpinionImg();
				img.setImgPath(saveFileName[0]);
				img.setImgPathThumbnail(saveFileName[1]);
				img.setUserOpinionId(msg.getId());
			}
		}
	}

	@Override
	public void insertAndGetId(UserOpinion userOpinion) {
		userOpinionMapper.insertAndGetId(userOpinion);
	}

}
