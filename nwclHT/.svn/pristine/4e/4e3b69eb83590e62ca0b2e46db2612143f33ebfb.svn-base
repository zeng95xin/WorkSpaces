package com.bola.nwcl.common.exception;

/**
 * Api相关异常
 */
public class ApiException extends Exception {
    /** 
	* @Fields serialVersionUID: 
	*/
	private static final long serialVersionUID = 2464073982627867256L;
	
	private final int errorCode;
    private final String description;
    private final int httpStatusCode;

    public static final ApiException system_error = new ApiException("System error", 10001, "系统错误", 500);
    public static final ApiException service_unavailable = new ApiException("Service unavailable", 10002, "服务暂停", 503);
    public static final ApiException service_not_found = new ApiException("Service not found", 10003, "服务不存在", 404);
    public static final ApiException illegal_request = new ApiException("Illegal request", 10004, "非法请求", 400);
    public static final ApiException no_permission = new ApiException("No permission", 10005, "没有权限", 403);
    public static final ApiException system_is_busy = new ApiException("System is busy", 10006, "系统繁忙", 500);
    public static final ApiException unsupport_mediatype = new ApiException("Unsupport mediatype", 10007, "不支持的媒体类型", 415);
    public static final ApiException miss_required_parameters = new ApiException("Miss required parameters, see doc for more info", 10008, "缺少必填字段，请参照API文档查询更多内容", 400);
    public static final ApiException illegal_parameter_type  = new ApiException("Illegal parameter type， see doc for more info", 10009, "非法的字段类型，请参照API文档查询更多内容", 415);
    public static final ApiException unsupport_http_method = new ApiException("Unsupport http method", 10010, "不支持的http方法，请参照API文档查询服务的HTTP方法是哪一种（GET/POST/PUT/DELETE）", 400);
    public static final ApiException illegal_request_source = new ApiException("Illegal request source", 10011, "非法的调用来源，调用来源不在白名单内", 400);



    public static final ApiException username_missing = new ApiException("username_missing", 11020, "用户名为空", 400);
    public static final ApiException password_missing = new ApiException("password_missing", 11021, "密码为空", 400);
    public static final ApiException password_error = new ApiException("password_error", 11022, "密码错误", 400);
    public static final ApiException username_exists = new ApiException("username_exists", 11023, "用户名存在", 400);
    public static final ApiException user_id_missing = new ApiException("user_id_missing", 11024, "用户ID为空", 400);
    public static final ApiException user_points_not_found = new ApiException("user_points_not_found", 11025, "没有用户积分", 400);
    public static final ApiException estates_not_found = new ApiException("estates_not_found", 11026, "没有该物业", 400);
    public static final ApiException user_not_found = new ApiException("user_not_found", 11027, "没有该用户", 400);
    public static final ApiException points_error = new ApiException("points_error", 11028, "积分错误", 400);
    public static final ApiException points_not_enough = new ApiException("points_not_enough", 11029, "积分不够", 400);
    public static final ApiException password_error_lock = new ApiException("password_error_lock", 11030, "密码错误锁定", 400);
    public static final ApiException community_not_found = new ApiException("community_not_found", 11031, "没有该小区", 400);
    public static final ApiException property_company_not_found = new ApiException("property_company_not_found", 11032, "没有该物业公司", 400);
    public static final ApiException property_notice_not_found = new ApiException("property_notice_not_found", 11033, "没有该物业通知", 400);
    public static final ApiException property_admin_not_found = new ApiException("property_admin_not_found", 11034, "没有该物业管理员", 400);
    public static final ApiException payment_estates_not_found = new ApiException("payment_estates_not_found", 11035, "没有该缴费物业", 400);
    public static final ApiException deliver_address_not_found = new ApiException("deliver_address_not_found", 11036, "没有该收货地址", 400);
    public static final ApiException community_estates_not_found = new ApiException("community_estates_not_found", 11037, "没有该小区物业", 400);
    public static final ApiException owner_name_error = new ApiException("owner_name_error", 11038, "户主姓名错误", 400);
    public static final ApiException sign_error = new ApiException("sign_error", 11039, "签名错误", 400);
    public static final ApiException user_lock = new ApiException("user_lock", 11040, "用户锁定", 400);
    public static final ApiException address_missing = new ApiException("address_missing", 11041, "地址为空", 400);
    public static final ApiException house_number_missing = new ApiException("house_number_missing", 11042, "门牌号为空", 400);
    public static final ApiException house_owner_missing = new ApiException("house_owner_missing", 11043, "户主姓名为空", 400);
    public static final ApiException estates_exists = new ApiException("estates_exists", 11044, "物业存在", 400);
    public static final ApiException user_is_disable = new ApiException("user_is_disable", 11045, "用户停用", 400);
    public static final ApiException username_not_exists = new ApiException("username_exists", 11046, "用户不存在", 400);
    public static final ApiException member_shop_class_exists_member_shop = new ApiException("member_shop_class_exists_member_shop", 11047, "商铺分类存在商铺", 400);
    public static final ApiException sub_member_shop_class_exists = new ApiException("sub_member_shop_class_exists", 11048, "子商铺分类存在", 400);
    public static final ApiException house_owner_too_long = new ApiException("house_owner_too_long", 11049, "户主姓名太长", 400);

    public static final ApiException government_account_not_found = new ApiException("government_account_not_found", 12001, "没有该账户", 400);
    public static final ApiException government_admin_area_community_not_found = new ApiException("government_admin_area_community_not_found", 12002, "没有该管理区域小区", 400);
    public static final ApiException government_admin_area_not_found = new ApiException("government_admin_area_not_found", 12003, "没有该管理区域", 400);
    public static final ApiException government_message_not_found = new ApiException("government_message_not_found", 12004, "没有该信息", 400);
    public static final ApiException government_operator_not_found = new ApiException("government_operator_not_found", 12005, "没有该操作员", 400);
    public static final ApiException government_organization_not_found = new ApiException("government_organization_not_found", 12006, "没有该组织结构", 400);
    public static final ApiException government_publish_scope_not_found = new ApiException("government_publish_scope_not_found", 12007, "没有该发布范围", 400);
    public static final ApiException government_message_publish_status_error = new ApiException("government_message_publish_status_error", 12008, "信息发布状态错误", 400);
    
    //民意调查
    public static final ApiException government_poll_basicInfo_null = new ApiException("government_poll_basicInfo_null", 30001, "调查内容为空", 400);
    //民意调查结果
    public static final ApiException government_poll_vote_null = new ApiException("government_poll_vote_null", 30002, "调查结果内容为空", 400);
    //该用户对此调查已经投票
    public static final ApiException government_poll_already_vote = new ApiException("government_poll_already_vote", 30003, "该用户对此调查已经投票", 400);
    public static final ApiException government_poll_already_end = new ApiException("government_poll_already_end", 30004, "该调查问卷已经结束", 400);
    public static final ApiException government_poll_status_error = new ApiException("government_poll_status_error", 30004, "该消息已被撤回", 400);

    public static final ApiException request_parameter_null = new ApiException("request_parameter_null", 13200, "请求参数为空", 400);

    public static final ApiException request_deliveryId_length = new ApiException("request_deliveryId_length", 13200, "配送编号错误", 400);

    public static final ApiException request_channelItemId_null = new ApiException("request_channelItemId_null", 13200, "频道ID为空", 400);
    public static final ApiException request_channelSequence_null = new ApiException("request_parameter_null", 13200, "频道位序为空", 400);

    public static final ApiException order_item_id_missing = new ApiException("order_item_id_missing", 13201, "订单项id为空", 400);
    public static final ApiException request_parameter_error = new ApiException("request_parameter_error", 13202, "请求参数错误", 400);
    public static final ApiException order_status_error = new ApiException("order_status_error", 13203, "订单未成功，不能评价", 400);
    public static final ApiException order_replace_no = new ApiException("order_replace_no", 13204, "不能申请换货!", 400);
    public static final ApiException order_delivery_no = new ApiException("order_delivery_no", 13205, "不能配送!", 400);
    public static final ApiException order_delivery_id_missing = new ApiException("order_delivery_id_missing", 13206, "找不到配送信息!", 400);
    public static final ApiException order_addProductOrdering = new ApiException("order_addProductOrdering", 13207, "该账号订单正在创建中，请稍后再尝试!", 400);
    public static final ApiException already_order_returned_info = new ApiException("already_order_returned_info", 13208, "不能重复申请退/换货", 400);
    
    public static final ApiException product_id_null = new ApiException("product_id_missing", 14200, "商品id为空", 400);
    public static final ApiException product_id_missing = new ApiException("product_id_missing", 14201, "不存在该商品id", 400);
    public static final ApiException product_type_missing = new ApiException("product_type_missing", 14202, "商品类型不存在", 400);
    public static final ApiException product_city_missing = new ApiException("product_city_missing", 14203, "该城市不出售该商品", 400);
    public static final ApiException product_quantity_missing = new ApiException("product_quantity_missing", 14204, "请输入购买商品数量", 400);
    public static final ApiException product_status_down = new ApiException("product_status_down", 14205, "购买商品总含有已经下架商品", 400);
    public static final ApiException product_missing = new ApiException("product_missing", 14209, "该商品不存在", 400);
    public static final ApiException product_quantity_max = new ApiException("product_quantity_max", 14210, "购物车商品最多只能添加99件", 400);
    public static final ApiException product_appraisal = new ApiException("product_appraisal", 14210, "该商品已评价", 400);
    public static final ApiException product_sort_error = new ApiException("product_sort_error", 14211, "商品排序值错误", 400);
    public static final ApiException product_city_null = new ApiException("product_city_null", 14212, "商品所属城市为空", 400);
    public static final ApiException product_status_add_error = new ApiException("product_status_add_error", 14213, "商品增加时状态错误（只能为down下架，timingUp 定时上架状态）", 400);
    public static final ApiException product_timing_dispose_time = new ApiException("product_timing_dispose_time", 14214, "商品上下架定时处理时间错误", 400);
    public static final ApiException product_price_error = new ApiException("product_price_error", 14215, "商品价格错误", 400);
    public static final ApiException product_label_null = new ApiException("product_label_null", 14216, "商品标签为空", 400);
    public static final ApiException product_keyword_null = new ApiException("product_keyword_null", 14217, "商品关键词为空", 400);
    public static final ApiException product_name_null = new ApiException("product_name_null", 14218, "商品名称为空", 400);
    public static final ApiException product_attach_parameter_error = new ApiException("product_attach_parameter_error", 14219, "商品附加属性为空", 400);
    public static final ApiException product_specifications_attribute_name_null = new ApiException("product_specifications_attribute_name_null", 14220, "商品规格属性为空", 400);
    public static final ApiException product_specifications_null = new ApiException("product_specifications_null", 14221, "商品规格信息为空", 400);
    public static final ApiException product_specifications_group_null = new ApiException("product_specifications_group_null", 14222, "商品规格组合信息为空", 400);
    public static final ApiException product_specifications_group_price_null = new ApiException("product_specifications_group_price_null", 14222, "商品规格组合价格为空", 400);
    public static final ApiException product_status_null = new ApiException("product_status_null", 14223, "商品状态为空", 400);
    public static final ApiException product_merchant_error = new ApiException("product_merchant_error", 14224, "商品商户信息错误", 400);
    public static final ApiException boss_not_pay_citic_product = new ApiException("boss_not_pay_citic_product", 14225, "boss端不能购买中信活动商品", 400);
    public static final ApiException COMMODITY_STOCK_SELL_OUT = new ApiException("COMMODITY_STOCK_SELL_OUT", 14225, "商品已售完", 400);
    
    public static final ApiException product_activities_city_null = new ApiException("product_activities_city_null", 17101, "活动城市不能为空", 400);
    
    public static final ApiException product_type_existing = new ApiException("product_type_existing", 17001, "该商品类别编码已存在", 400);
    public static final ApiException product_type_no_shift = new ApiException("product_type_no_shift", 17002, "该商品类别下有子集不能移动或删除", 400);
    public static final ApiException product_type_have_product = new ApiException("product_type_have_product", 17003, "该类别下有商品不能删除", 400);
    public static final ApiException product_type_code_null = new ApiException("product_type_code_null", 17004, "商品类别编码为空", 400);
    
    
    public static final ApiException property_account_not_found = new ApiException("property_account_not_found", 15001, "没有该账户", 400);
    public static final ApiException property_admin_area_community_not_found = new ApiException("property_admin_area_community_not_found", 12002, "没有该管理区域小区", 400);
    public static final ApiException property_admin_area_not_found = new ApiException("property_admin_area_not_found", 15003, "没有该管理区域", 400);
    public static final ApiException property_message_not_found = new ApiException("property_message_not_found", 15004, "没有该信息", 400);
    public static final ApiException property_operator_not_found = new ApiException("property_operator_not_found", 15005, "没有该操作员", 400);
    public static final ApiException property_organization_not_found = new ApiException("property_organization_not_found", 15006, "没有该组织结构", 400);
    public static final ApiException property_publish_scope_not_found = new ApiException("property_publish_scope_not_found", 15007, "没有该发布范围", 400);
    public static final ApiException property_message_publish_status_error = new ApiException("property_message_publish_status_error", 15008, "信息发布状态错误", 400);
    public static final ApiException property_account_no_exists = new ApiException("property_account_no_exists", 15009, "该用户不存在", 400);
    
    public static final ApiException member_administrative_division_id_missing = new ApiException("administrative_division_id_missing", 16001, "没有该行政区划", 400);
    
    public static final ApiException government_mobile_exists = new ApiException("government_mobile_exists", 20202, "该手机已经存在", 400);
    public static final ApiException government_account_no_exists = new ApiException("government_account_no_exists", 20203, "该用户不存在", 400);
    
    public static final ApiException excel_data_duplicate = new ApiException("excel_data_duplicate", 20204, "excel中有重复项", 400);
    public static final ApiException download_file_error = new ApiException("download_file_error", 20205, "读取excel错误", 400);
    
    public static final ApiException property_company_id_is_null= new ApiException("property_company_id_is_null", 20206, "物业公司id为空", 400);
    public static final ApiException community_id_is_null = new ApiException("community_id_is_null", 20207, "小区id为空", 400);
    
    public static final ApiException read_excel_error = new ApiException("read_excel_error", 20208, "读取excel失败", 400);
    public static final ApiException upload_file_error = new ApiException("upload_file_error", 20209, "读取excel错误", 400);
    public static final ApiException appkey_is_exist = new ApiException("appkey_is_exist", 20210, "appkey已经存在", 400);
    public static final ApiException appkey_is_error = new ApiException("appkey_is_exist", 20211, "只能导入一条appkey数据", 400);
    public static final ApiException points_not_greater_than_credit_deductible = new ApiException("points_not_greater_than_credit_deductible", 20212, "不能大于商品抵扣积分", 400);
    public static final ApiException excel_file_is_null = new ApiException("excel_file_is_null", 20213, "excel内容为空", 400);
    public static final ApiException member_community_exists = new ApiException("member_community_exists", 20214, "该小区已存在", 400);
    public static final ApiException payment_channelId_null = new ApiException("payment_channelId_null", 20215, "支付渠道不能为空", 400);
    public static final ApiException payment_not_arrearage = new ApiException("payment_not_arrearage", 20216, "未欠费", 400);
    
    public static final ApiException excel_file_error = new ApiException("excel_file_error", 20217, "excel中数据为空，或者excel模板有误，请检查excel", 400);
    public static final ApiException payment_channelId_no = new ApiException("payment_channelId_no", 20218, "支付金额不为0时,支付渠道不能为'积分抵扣'", 400);
    public static final ApiException lock_refund = new ApiException("lock_refund", 20219, "该笔订单正在处理中，业务处于锁定状态", 400);

    public ApiException(String error, int errorCode, String description, int httpStatusCode) {
        super(error);
        this.errorCode = errorCode;
        this.description = description;
        this.httpStatusCode = httpStatusCode;
    }

    public ApiException(String error, int errorCode, String description, int httpStatusCode, Throwable cause) {
        super(error, cause);
        this.errorCode = errorCode;
        this.description = description;
        this.httpStatusCode = httpStatusCode;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getDescription() {
        return description;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getError() {
        return getMessage();
    }
}
