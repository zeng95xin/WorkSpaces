package com.bola.nwcl.common.util;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.global.Configure;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

/**
 * 阿里大鱼发短信工具类
 * @author ShyMe
 */
public class AliDaYuSmsUtil {
	static Logger logger=LoggerFactory.getLogger(AliDaYuSmsUtil.class);
	public static String smsTemplateCode_reg_owner = "SMS_6085567";
	public static String smsTemplateCode_reg_buser = "SMS_6085567";
	public static String smsTemplateCode_fingpwd = "SMS_6085565";
	public static String freeSignName_default="大鱼测试";

	public static void sendSms(List<String> phone,String smsParam,String smsTemplateCode,String freeSignName)throws Exception{
		//集合String 转换为 逗号分隔 的String
		String mobiles = StringUtils.join(phone, ",");
		String url=Configure.getAliSmsUrl();
		TaobaoClient client = new DefaultTaobaoClient(url, Configure.getAliSmsAppKey(), Configure.getAliSmsAppSecret());
		AlibabaAliqinFcSmsNumSendRequest  req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setRecNum(mobiles);
		req.setSmsFreeSignName(freeSignName);
		req.setSmsTemplateCode(smsTemplateCode);
		req.setSmsType("normal");
		req.setSmsParamString(smsParam);
		AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
		String result = rsp.getBody();
		logger.info("阿里大鱼发送短信打印日志为："+result);
		//根据返回的json 判断是否成功
		//{"alibaba_aliqin_fc_sms_num_send_response":{"result":{"err_code":"0","model":"100895428250^1101345707165","success":true},"request_id":"z2baww6w5jth"}}
		if(StringUtils.isBlank(result) || !result.contains("\"success\":true")){
			throw new BusinessDealException("发送短信失败！");
		}
	}
	
	public static void main(String[] args) {
		List<String> phones=new ArrayList<String>();
		phones.add("13527471462");
		String smsParam="{code:\"1251\",product:\"i回家\"}";
		String smsTemplateCode = "SMS_6085567";
		String freeSignName="大鱼测试";
		try {
			sendSms(phones, smsParam,smsTemplateCode,freeSignName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

