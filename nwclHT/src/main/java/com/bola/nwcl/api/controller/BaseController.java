package com.bola.nwcl.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bola.nwcl.common.result.BaseResult;

public class BaseController {
	
	private final Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	@ExceptionHandler(value=Exception.class)
	@ResponseBody
	public BaseResult exceptionHandle(Exception e){
		logger.info("程序发生异常:");
		String msg = e.getMessage();
		String code = "";
		BaseResult r = new BaseResult();
		code = "SYSTEM_ERROR";
		logger.error("系统错误:__code:{}__msg:{}",code,msg);
		r.setCode(code);
		r.setMessage(e.getMessage());
		e.printStackTrace();
		return r;
	}
	
}
