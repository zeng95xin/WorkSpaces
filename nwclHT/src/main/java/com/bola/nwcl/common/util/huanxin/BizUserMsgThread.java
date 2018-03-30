package com.bola.nwcl.common.util.huanxin;

import java.util.List;

import com.bola.nwcl.api.model.jpush.JPushChatModel;
import com.bola.nwcl.dal.mybatis.model.BizUser;

public class BizUserMsgThread extends Thread{
	
	private List<BizUser> busers;
	private JPushChatModel model;
	
	public List<BizUser> getBusers() {
		return busers;
	}



	public void setBusers(List<BizUser> busers) {
		this.busers = busers;
	}



	public JPushChatModel getModel() {
		return model;
	}



	public void setModel(JPushChatModel model) {
		this.model = model;
	}



	@Override
	public void run() {
		BizUserMsgApi.sendBuserMsgThread(busers, model);
	}

}
