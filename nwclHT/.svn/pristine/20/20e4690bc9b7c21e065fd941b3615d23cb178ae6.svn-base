package com.bola.nwcl.common.exception;

/**
 * 业务校验异常
 * @created	2014.05.19
 *
 */
public class BusinessValidateException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2333726144918456070L;
	
	private String code;
	
	public BusinessValidateException(String code, String msg) {
		super(msg);
		setCode(code);
    }
	
	/**
	 * 业务校验异常
	 * @param 	msg	错误信息
	 */
	public BusinessValidateException(String msg) {
		super(msg);
    }
	
    /**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}


}
