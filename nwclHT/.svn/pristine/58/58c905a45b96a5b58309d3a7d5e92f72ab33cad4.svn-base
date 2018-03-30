package com.bola.nwcl.common.result;

import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: BaseResult
 * @Description: web服务返回结果基类
 * @author xsm
 * @date 2014-11-5
 */
@ApiModel
public class BaseResult implements Serializable {
	private static final long serialVersionUID = -6900139280435767733L;
	/**
	 * @Fields code: 状态码
	 */
	private String code;

	/**
	 * @Fields message:状态描述
	 */
	private String message;
	
	private String requestId;

	public BaseResult() {
		super();
		requestId = UUID.randomUUID().toString();
	}

	public BaseResult(String code, String message) {
		this.code = StringUtils.defaultString(code);
		this.message = StringUtils.defaultString(message);
		requestId = UUID.randomUUID().toString();
	}
	@ApiModelProperty(position = 1, required = true, value = "username numbers")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	@ApiModelProperty(position = 2, required = true, value = "username numbers")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@ApiModelProperty(position = 3, required = true, value = "username numbers")
	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
