package com.bola.nwcl.api.controller.pay;

import java.util.ArrayList;
import java.util.List;

public class WebhooksModelObj {
	private String id;
	private String object;
	private int created;
	private boolean livemode;
	private boolean paid;
	private boolean refunded;
	private String app;
	private String channel;
	private String order_no;
	private String client_ip;
	private Long amount;
	private int amount_settle;
	private String currency;
	private String subject;
	private String body;
	private Extra extra;
	private int time_paid;
	private int time_expire;
	private Object time_settle;
	private String transaction_no;
	private Refunds refunds;
	private int amount_refunded;
	private Object failure_code;
	private Object failure_msg;
	private Metadata metadata;
	private Credential credential;
	private Object description;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public int getCreated() {
		return created;
	}
	public void setCreated(int created) {
		this.created = created;
	}
	public boolean isLivemode() {
		return livemode;
	}
	public void setLivemode(boolean livemode) {
		this.livemode = livemode;
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	public boolean isRefunded() {
		return refunded;
	}
	public void setRefunded(boolean refunded) {
		this.refunded = refunded;
	}
	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getClient_ip() {
		return client_ip;
	}
	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}
	public int getAmount_settle() {
		return amount_settle;
	}
	public void setAmount_settle(int amount_settle) {
		this.amount_settle = amount_settle;
	}
	public int getTime_paid() {
		return time_paid;
	}
	public void setTime_paid(int time_paid) {
		this.time_paid = time_paid;
	}
	public int getTime_expire() {
		return time_expire;
	}
	public void setTime_expire(int time_expire) {
		this.time_expire = time_expire;
	}
	public Object getTime_settle() {
		return time_settle;
	}
	public void setTime_settle(Object time_settle) {
		this.time_settle = time_settle;
	}
	public String getTransaction_no() {
		return transaction_no;
	}
	public void setTransaction_no(String transaction_no) {
		this.transaction_no = transaction_no;
	}
	public int getAmount_refunded() {
		return amount_refunded;
	}
	public void setAmount_refunded(int amount_refunded) {
		this.amount_refunded = amount_refunded;
	}
	public Object getFailure_code() {
		return failure_code;
	}
	public void setFailure_code(Object failure_code) {
		this.failure_code = failure_code;
	}
	public Object getFailure_msg() {
		return failure_msg;
	}
	public void setFailure_msg(Object failure_msg) {
		this.failure_msg = failure_msg;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Extra getExtra() {
		return extra;
	}
	public void setExtra(Extra extra) {
		this.extra = extra;
	}
	public Refunds getRefunds() {
		return refunds;
	}
	public void setRefunds(Refunds refunds) {
		this.refunds = refunds;
	}
	public Metadata getMetadata() {
		return metadata;
	}
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
	public Credential getCredential() {
		return credential;
	}
	public void setCredential(Credential credential) {
		this.credential = credential;
	}
	public Object getDescription() {
		return description;
	}
	public void setDescription(Object description) {
		this.description = description;
	}
	public class Refunds {
		private String object;
		private String url;
		private boolean has_more;
		private List<Object> data = new ArrayList<Object>();
		public String getObject() {
			return object;
		}
		public void setObject(String object) {
			this.object = object;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public boolean isHas_more() {
			return has_more;
		}
		public void setHas_more(boolean has_more) {
			this.has_more = has_more;
		}
		public List<Object> getData() {
			return data;
		}
		public void setData(List<Object> data) {
			this.data = data;
		}
	}
	public class Extra{
		
	}
	public class Metadata{
		
	}
	public class Credential{
		
	}
}
