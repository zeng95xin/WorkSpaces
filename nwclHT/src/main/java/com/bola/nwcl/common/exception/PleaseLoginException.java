package com.bola.nwcl.common.exception;

/**
 * 格式异常
 * @created	2014.05.19
 */
public class PleaseLoginException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7440750270276349757L;
	
	private String code;
	
	public PleaseLoginException(String code, String msg) {
		super(msg);
		setCode(code);
    }
	
	public PleaseLoginException(String msg) {
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
