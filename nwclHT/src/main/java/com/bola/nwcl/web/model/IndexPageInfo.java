package com.bola.nwcl.web.model;

import java.util.List;

import com.bola.nwcl.dal.mybatis.model.ArticleRelease;
import com.bola.nwcl.dal.mybatis.model.HangPicture;
import com.bola.nwcl.dal.mybatis.model.Housekeeper;
import com.bola.nwcl.dal.mybatis.model.HousekeeperUserMessage;
import com.bola.nwcl.dal.mybatis.model.LectureHallIntroducing;
import com.bola.nwcl.dal.mybatis.model.Maintenance;
import com.bola.nwcl.dal.mybatis.model.Memu;
import com.bola.nwcl.dal.mybatis.model.Notify;
import com.bola.nwcl.dal.mybatis.model.UserOpinion;

public class IndexPageInfo {

	private List<Notify> notifies;
	private List<UserOpinion> opinions;
	private List<ArticleRelease> releases;
	private List<Maintenance> maintenances;
	private List<HousekeeperUserMessage> hMessages; 
	private List<HangPicture> hangPictures;
	private List<LectureHallIntroducing> introducings;
	
	
	private List<EmployeeOnDuty> housekeepers;
	private List<EmployeeOnDuty> repairmen;
	private List<EmployeeOnDuty> security;
	private Memu notifyNode;
	private Memu opinionNode;
	private Memu releaseNode;
	private Memu maintenanceNode;
	private Memu housekeeperNode;
	private Memu hangNode;
	private Memu intoduceNode;
	public List<Notify> getNotifies() {
		return notifies;
	}
	public void setNotifies(List<Notify> notifies) {
		this.notifies = notifies;
	}
	public List<UserOpinion> getOpinions() {
		return opinions;
	}
	public void setOpinions(List<UserOpinion> opinions) {
		this.opinions = opinions;
	}
	public List<ArticleRelease> getReleases() {
		return releases;
	}
	public void setReleases(List<ArticleRelease> releases) {
		this.releases = releases;
	}
	public List<Maintenance> getMaintenances() {
		return maintenances;
	}
	public void setMaintenances(List<Maintenance> maintenances) {
		this.maintenances = maintenances;
	}
	public List<HousekeeperUserMessage> gethMessages() {
		return hMessages;
	}
	public void sethMessages(List<HousekeeperUserMessage> hMessages) {
		this.hMessages = hMessages;
	}
	public List<HangPicture> getHangPictures() {
		return hangPictures;
	}
	public void setHangPictures(List<HangPicture> hangPictures) {
		this.hangPictures = hangPictures;
	}
	public List<LectureHallIntroducing> getIntroducings() {
		return introducings;
	}
	public void setIntroducings(List<LectureHallIntroducing> introducings) {
		this.introducings = introducings;
	}
	public List<EmployeeOnDuty> getHousekeepers() {
		return housekeepers;
	}
	public void setHousekeepers(List<EmployeeOnDuty> housekeepers) {
		this.housekeepers = housekeepers;
	}
	public List<EmployeeOnDuty> getRepairmen() {
		return repairmen;
	}
	public void setRepairmen(List<EmployeeOnDuty> repairmen) {
		this.repairmen = repairmen;
	}
	public List<EmployeeOnDuty> getSecurity() {
		return security;
	}
	public void setSecurity(List<EmployeeOnDuty> security) {
		this.security = security;
	}
	public Memu getNotifyNode() {
		return notifyNode;
	}
	public void setNotifyNode(Memu notifyNode) {
		this.notifyNode = notifyNode;
	}
	public Memu getOpinionNode() {
		return opinionNode;
	}
	public void setOpinionNode(Memu opinionNode) {
		this.opinionNode = opinionNode;
	}
	public Memu getReleaseNode() {
		return releaseNode;
	}
	public void setReleaseNode(Memu releaseNode) {
		this.releaseNode = releaseNode;
	}
	public Memu getMaintenanceNode() {
		return maintenanceNode;
	}
	public void setMaintenanceNode(Memu maintenanceNode) {
		this.maintenanceNode = maintenanceNode;
	}
	public Memu getHousekeeperNode() {
		return housekeeperNode;
	}
	public void setHousekeeperNode(Memu housekeeperNode) {
		this.housekeeperNode = housekeeperNode;
	}
	public Memu getHangNode() {
		return hangNode;
	}
	public void setHangNode(Memu hangNode) {
		this.hangNode = hangNode;
	}
	public Memu getIntoduceNode() {
		return intoduceNode;
	}
	public void setIntoduceNode(Memu intoduceNode) {
		this.intoduceNode = intoduceNode;
	}
	
	
	
}
