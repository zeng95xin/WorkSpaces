package com.bola.nwcl.web.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class LoginController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value={"/","/login"})
	public String login(HttpServletRequest request) {
		logger.info("加载login");
		return "admin";
	}
	
	@RequestMapping(value = "/north")
	public String north(HttpServletRequest request) {
		logger.info("加载north");
		return "/layout/north";
	}
	
	@RequestMapping(value = "/west")
	public String west(HttpServletRequest request) {
		logger.info("加载west");
		return "/layout/west";
	}
	
	@RequestMapping(value = "/east")
	public String east(HttpServletRequest request) {
		logger.info("加载east");
		return "/layout/east";
	}
	
	@RequestMapping(value = "/south")
	public String south(HttpServletRequest request) {
		logger.info("加载south");
		return "/layout/south";
	}
	
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		logger.info("加载首页");
		return "/layout/index";
	}
	
	@RequestMapping(value="/clock")
	public String clock(HttpServletRequest request) {
		logger.info("加载clock");
		return "/layout/clock";
	}
	@RequestMapping(value="/error")
	public String errors(HttpServletRequest request) {
		logger.info("加载error");
		return "/error/noSecurity";
	}
}
