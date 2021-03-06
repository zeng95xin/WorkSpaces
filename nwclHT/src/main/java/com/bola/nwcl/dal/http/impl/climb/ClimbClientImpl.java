package com.bola.nwcl.dal.http.impl.climb;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.util.HttpClientUtil;
import com.bola.nwcl.dal.http.api.climb.ClimbClient;
@Service
public class ClimbClientImpl implements ClimbClient {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 	查询用户单元信息
	 	参数说明 customerPhone：用户手机号  比如13560911922
	 * @throws Exception
	 * 返回值 
	 [
		{
			ID: 1,
			UnitNO: "ZQ-B2-1602",
			UnitType: 0,
			UnitDesc: null,
			PropertyID: 4013,
			PropertyName: "伴月花园"
		},
		{
			ID: 11,
			UnitNO: "DF-T1-603",
			UnitType: 0,
			UnitDesc: null,
			PropertyID: 140,
			PropertyName: "东方新城"
		},
		{
			ID: 12,
			UnitNO: "DF-CK-B1-1103",
			UnitType: 1,
			UnitDesc: null,
			PropertyID: 140,
			PropertyName: "东方新城"
		}
	]
	返回值参数说明见文档
	 */
	@Override
	public String GetUnitInfo(String customerPhone) throws Exception {
		String url="http://weixin.nw-sc.com:8089/Interface/GetUnitInfo";
		Map<String,String> paramMap=new HashMap<String, String>();
		paramMap.put("p_CustomerPhone", customerPhone);
		String result=HttpClientUtil.get(url, paramMap);
		if(StringUtils.isBlank(result)){
			logger.info("该用户手机号不存在记录，手机号："+customerPhone);
			throw new PatternException("该用户手机号不存在记录");
		}
		return result;
	}
	/**
	 * 查询用户信息
	 * 参数说明 
	 * unitNO：物业单元编号   比如 ZQ-A2-0702
	 * 返回值：
	 * {
		CustomerName: "莫裕培",
		IDType: "身份证",
		IDNO: "441202198409210517",
		Sex: 0,
		Email: null,
		Address: "广州市端州区康乐北路翠庭湖轩C3幢601房 ",
		UnitNO: "ZQ-A2-0702",
		AcceptanceDate: "/Date(7954128000000)/",
		ChargingDate: "/Date(1325347200000)/",
		StartDate: "/Date(1325347200000)/",
		CustomerDesc: null
	   }
	   返回值参数说明详情见文档
	 * 
	 */
	@Override
	public String GetUnitCustomerInfo(String unitNO) throws Exception {
		String url="http://weixin.nw-sc.com:8089/Interface/GetUnitCustomerInfo";
		Map<String,String> paramMap=new HashMap<String, String>();
		paramMap.put("p_UnitNO", unitNO);
		String result=HttpClientUtil.get(url, paramMap);
		if(StringUtils.isBlank(result)){
			logger.info("该物业单元编号不存在记录，单元号："+unitNO);
			throw new PatternException("该物业单元编号不存在记录");
		}
		return result;
	}
	/**
	 * 查询用户缴费信息（月度）:
	 * 请求参数说明
	 * unitNO：物业单元编号      比如ZQ-A2-0702
	 * payBeginMonth ：缴费开始月份    比如 201404
	   payEndMonth：缴费截止月份     比如 201405
	   返回值
	   [
		{
			CustomerName: "莫裕培",
			UnitNO: "ZQ-A2-0702",
			ReceivableDispName: "电费",
			ReceivableDate: "/Date(1398873600000)/",
			ReceivableAmount: 929.52,
			CurrAmount: 0,
			ReceivableStatus: 0
		},
		{
			CustomerName: "莫裕培",
			UnitNO: "ZQ-A2-0702",
			ReceivableDispName: "住宅管理费",
			ReceivableDate: "/Date(1398873600000)/",
			ReceivableAmount: 1195.63,
			CurrAmount: 0,
			ReceivableStatus: 0
		},
		{
			CustomerName: "莫裕培",
			UnitNO: "ZQ-A2-0702",
			ReceivableDispName: "垃圾费",
			ReceivableDate: "/Date(1398873600000)/",
			ReceivableAmount: 521.08,
			CurrAmount: 0,
			ReceivableStatus: 0
		}
	   ]
	   返回值参数 见文档
	 */
	@Override
	public String GetUnitPaymentList(String unitNO, String payBeginMonth,
			String payEndMonth) throws Exception {
		String url="http://weixin.nw-sc.com:8089/Interface/GetUnitPaymentList";
		Map<String,String> paramMap=new HashMap<String, String>();
		paramMap.put("p_UnitNO", unitNO);
		paramMap.put("p_PayBeginMonth", payBeginMonth);
		paramMap.put("p_PayEndMonth", payEndMonth);
		String result=HttpClientUtil.get(url, paramMap);
		if(StringUtils.isBlank(result)){
			logger.info(payBeginMonth+"到"+payEndMonth+"内不存在应缴款记录，"+"单元号："+unitNO);
			throw new PatternException(payBeginMonth+"到"+payEndMonth+"内不存在应缴款记录");
		}
		return result;
	}
	/**
	 * 参数
	 * p_ReceivableIDs	String	否	8522|8523|8566|8567	收款项目ID(数组)
		p_PayAmount	Number	否	610.00	付款总金额
		返回值
		{
		   "errcode": 0,
		   "errmsg": "true"
		}
		errcode	errmsg	说明
		1000	付款金额和应收款总金额不相等。	
		1001	找不到应收款。	
		1002	提交的应收款部分已经付款。	
	 * @param PReceivableIDs
	 * @param PPayAmount
	 * @throws Exception
	 */
	@Override
	public void CheckUnitPayment(String PReceivableIDs, String PPayAmount)
			throws Exception {
		String url="http://weixin.nw-sc.com:8089/Interface/CheckUnitPayment";
		Map<String,String> paramMap=new HashMap<String, String>();
		paramMap.put("p_ReceivableIDs", PReceivableIDs);
		paramMap.put("p_PayAmount", PPayAmount);
		String result=HttpClientUtil.post(url, paramMap);
		logger.info("校验金额返回："+result);
		if(StringUtils.isBlank(result)){
			throw new PatternException("校验金额出错");
		}
		String errcode = JSON.parseObject(result).getString("errcode");
		if(errcode.equals("1000")){
			throw new PatternException("付款金额和应收款总金额不相等");
		}
		if(errcode.equals("1001")){
			throw new PatternException("找不到应收款");
		}
		if(errcode.equals("1002")){
			throw new PatternException("提交的应收款部分已经付款");
		}
		if(!errcode.equals("0")){
			throw new PatternException("校验金额未知错误");
		}
	}
	/**
	 * 6.用户付款接口(提交)
	 * 参数
	 *p_ReceivableIDs	String	否	8522|8523|8566|8567	收款项目ID(数组)
		p_PayAmount	Number	否	610.00	付款总金额
		p_PayMode	String	否	银联	交易方式（银联、支付宝、微信）
		p_PayNo	String	否	00852854	交易流水号

		返回值
		{
		   "errcode": 0,
		   "errmsg": "true"
		}
		errcode	errmsg
		1000	付款金额和应收款总金额不相等。
		1001	找不到应收款。
		1002	提交的应收款部分已经付款。
	 * @param PReceivableIDs
	 * @param PPayAmount
	 * @throws Exception
	 */
	@Override
	public void SendUnitPayment(String PReceivableIDs, String PPayAmount,
			String PPayMode, String PPayNo) throws Exception {
		String url="http://weixin.nw-sc.com:8089/Interface/SendUnitPayment";
		Map<String,String> paramMap=new HashMap<String, String>();
		paramMap.put("p_ReceivableIDs", PReceivableIDs);
		paramMap.put("p_PayAmount", PPayAmount);
		paramMap.put("p_PayMode", PPayMode);
		paramMap.put("p_PayNo", PPayNo);
		String result=HttpClientUtil.post(url, paramMap);
		logger.info("提交付款到物业系统返回："+result);
		if(StringUtils.isBlank(result)){
			throw new PatternException("提交付款到物业系统出错了");
		}
		String errcode = JSON.parseObject(result).getString("errcode");
		if(errcode.equals("1000")){
			throw new PatternException("付款金额和应收款总金额不相等");
		}
		if(errcode.equals("1001")){
			throw new PatternException("找不到应收款");
		}
		if(errcode.equals("1002")){
			throw new PatternException("提交的应收款部分已经付款");
		}
		if(!errcode.equals("0")){
			throw new PatternException("校验金额未知错误");
		}
	}
	
	//测试一下
	public static void main(String[] args)throws Exception {
		ClimbClient dss=new ClimbClientImpl();
		//查询查询用户单元信息
		String abc=dss.GetUnitInfo("13560911922");
		System.out.println(abc);
		
		//查询单元信息
		String bcd=dss.GetUnitCustomerInfo("ZQ-B2-1602");
		System.out.println(bcd);
		
		//查询用户缴费信息（月度）:
		String ccc=dss.GetUnitPaymentList("ZQ-A2-0702", "201404", "201405");
		System.out.println(ccc);
	}
	
}
