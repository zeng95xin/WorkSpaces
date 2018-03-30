package com.bola.nwcl.biz;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bola.nwcl.common.manager.Manager;
import com.bola.nwcl.dal.mybatis.model.Activity;
import com.bola.nwcl.dal.mybatis.model.ActivityExample;
import com.bola.nwcl.web.model.Activitys;

public interface ActivityManager extends Manager<Activity, ActivityExample, Long>{
	void insertGenId(HttpServletRequest request,Activitys activity,MultipartFile file,String push,String pushContent, String recommended) throws Exception;
	void edit(HttpServletRequest request,Activitys activity,MultipartFile file,String push, String recommended,String pushContent) throws Exception;
}
