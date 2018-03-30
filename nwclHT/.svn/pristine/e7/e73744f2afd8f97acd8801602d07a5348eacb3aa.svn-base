package com.bola.nwcl.dal.http.api.climb;


public interface ClimbClient {
	/**
	 	查询用户单元信息
	 	参数说明 customerPhone：用户手机号
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
	public String GetUnitInfo(String customerPhone)throws Exception; 
	
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
	public String GetUnitCustomerInfo(String unitNO)throws Exception;
	
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
	public String GetUnitPaymentList(String unitNO,String payBeginMonth,String payEndMonth)throws Exception;
	/**
	 * 5.	用户付款接口(检查)
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
	public void CheckUnitPayment(String PReceivableIDs,String PPayAmount)throws Exception;
	
	/**
	 * 6.	用户付款接口(提交)
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
	public void SendUnitPayment(String PReceivableIDs,String PPayAmount,String PPayMode,String PPayNo)throws Exception;
	
}
