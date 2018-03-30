package com.bola.nwcl.common.exception;

/**
 * 配置异常
 * @created	2014.05.19
 *
 */
public class ConfigException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6163420665638751581L;
	
	private String code;
	
	public ConfigException(String code, String msg) {
		super(msg);
		setCode(code);
    }
	
	public ConfigException(String msg) {
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
