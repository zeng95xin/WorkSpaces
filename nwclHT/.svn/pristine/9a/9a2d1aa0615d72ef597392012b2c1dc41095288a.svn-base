package com.bola.nwcl.api.controller.pay;
/**
 * 支付回调的通知 body的实体
 * @author Mr.ShyMe
 *
 */
public class WebhooksModel {
	private String id;
	private int created;
	private boolean livemode;
	private String type;
	private Data data;
	private String object;
	private int pending_webhooks;
	private String request;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public int getPending_webhooks() {
		return pending_webhooks;
	}

	public void setPending_webhooks(int pending_webhooks) {
		this.pending_webhooks = pending_webhooks;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public class Data{
		public WebhooksModelObj object;

		public WebhooksModelObj getObject() {
			return object;
		}
		public void setObject(WebhooksModelObj object) {
			this.object = object;
		}
		
	}
}
