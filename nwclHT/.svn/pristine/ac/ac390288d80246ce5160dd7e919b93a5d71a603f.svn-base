package com.bola.nwcl.dal.enums;

import java.util.ArrayList;
import java.util.List;


public enum CodeEnum {
	
	/**
	 * 请登录
	 */
	PLEASE_LOGIN("PleaseLogin", "未登陆"),
	
	/**
	 * 格式错误
	 */
	PATTERN_ERROR("PATTERN_ERROR", "格式错误"),
	/**
	 * 配置错误
	 */
	CONFIG_ERROR("CONFIG_ERROR", "配置错误"),
	/**
	 * 业务校验异常
	 */
	BUSSINESS_VALIDATE_ERROR("BUSSINESS_VALIDATE_ERROR", "业务校验异常"),
	/**
	 * 业务处理异常
	 */
	BUSSINESS_HANDLE_ERROR("BUSSINESS_HANDLE_ERROR", "业务处理异常"),
	/**
	 * 系统异常
	 */
	SYSTEM_ERROR("SYSTEM_ERROR","系统异常");
	
	
	/** 枚举值 */
	private final String	code;
	
	/** 枚举描述 */
	private final String	message;
	
	/**
	 * 构造一个<code>DomainResultCodeEnum</code>枚举对象
	 *
	 * @param code
	 * @param message
	 */
	private CodeEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	/**
	 * @return Returns the code.
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * @return Returns the message.
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * @return Returns the code.
	 */
	public String code() {
		return code;
	}
	
	/**
	 * @return Returns the message.
	 */
	public String message() {
		return message;
	}
	
	/**
	 * 通过枚举<code>code</code>获得枚举
	 *
	 * @param code
	 * @return DomainResultCodeEnum
	 */
	public static CodeEnum getByCode(String code) {
		for (CodeEnum _enum : values()) {
			if (_enum.getCode().equals(code)) {
				return _enum;
			}
		}
		return null;
	}
	
	/**
	 * 获取全部枚举
	 * 
	 * @return List<DomainResultCodeEnum>
	 */
	public List<CodeEnum> getAllEnum() {
		List<CodeEnum> list = new ArrayList<CodeEnum>();
		for (CodeEnum _enum : values()) {
			list.add(_enum);
		}
		return list;
	}
	
	/**
	 * 获取全部枚举值
	 * 
	 * @return List<String>
	 */
	public List<String> getAllEnumCode() {
		List<String> list = new ArrayList<String>();
		for (CodeEnum _enum : values()) {
			list.add(_enum.code());
		}
		return list;
	}
}

