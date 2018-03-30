package com.bola.nwcl.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.global.Configure;

public class SmsUtils {
	static Logger logger=LoggerFactory.getLogger(SmsUtils.class);
	public static void main(String[] args) {
		try {
			List<String> list=new ArrayList<String>();
			list.add("13527471462");
			sendSms(list, "您好，您的验证码是XXXX");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void sendSms(List<String> mobile, String msg)throws Exception{
		//集合String 转换为 逗号分隔 的String
		String mobiles = StringUtils.join(mobile, ",");
		System.out.println(mobiles);
		String url=Configure.getSmsUrl();
		String textSmsUser=Configure.getTextSmsUser();
		String textSmsPwd=Configure.getTextSmsPwd();
		Map<String,String> paramMap=new  HashMap<String,String>();
		paramMap.put("account", textSmsUser);
		paramMap.put("pswd", textSmsPwd);
		paramMap.put("mobile", mobiles);
		paramMap.put("msg", msg);
		String result=HttpClientUtil.post(url, paramMap);
		logger.info("短信资源方发送短信 日志：{}",result);
		if(StringUtils.isBlank(result)){
			throw new BusinessDealException("发送短信失败！");
		}
		//返回值第一行 , 分隔的转成数组
		String[] ss=result.split("\n")[0].split(",");
		if(ss.length<2){
			throw new BusinessDealException("发送短信失败！");
		}
		if(!ss[1].equals("0")){
			throw new BusinessDealException("发送短信失败！");
		}
	}
}
