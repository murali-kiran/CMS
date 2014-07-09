package com.sumadga.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_permissions")
public class UserPermissions implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="user", unique=true, nullable=false)
	private String user;
	
	@Column(name="media_upload",  nullable=false)
	private boolean mediaUpload;
	
	@Column(name="media_search", nullable=false)
	private boolean mediaSearch;
	
	@Column(name="game_upload",  nullable=false) 
	private boolean gameUpload;
	
	@Column(name="media_group",  nullable=false)
	private boolean mediaGroup;
	
	@Column(name="media_group_list",  nullable=false)
	private boolean mediaGroupList;
	
	@Column(name="media_service",  nullable=false)
	private boolean mediaService;
	
	@Column(name="mis",  nullable=false)
	private boolean mis;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public boolean isMediaUpload() {
		return mediaUpload;
	}

	public void setMediaUpload(boolean mediaUpload) {
		this.mediaUpload = mediaUpload;
	}

	public boolean isMediaSearch() {
		return mediaSearch;
	}

	public void setMediaSearch(boolean mediaSearch) {
		this.mediaSearch = mediaSearch;
	}

	public boolean isGameUpload() {
		return gameUpload;
	}

	public void setGameUpload(boolean gameUpload) {
		this.gameUpload = gameUpload;
	}

	public boolean isMediaGroup() {
		return mediaGroup;
	}

	public void setMediaGroup(boolean mediaGroup) {
		this.mediaGroup = mediaGroup;
	}

	public boolean isMediaGroupList() {
		return mediaGroupList;
	}

	public void setMediaGroupList(boolean mediaGroupList) {
		this.mediaGroupList = mediaGroupList;
	}

	public boolean isMediaService() {
		return mediaService;
	}

	public void setMediaService(boolean mediaService) {
		this.mediaService = mediaService;
	}

	public boolean isMis() {
		return mis;
	}

	public void setMis(boolean mis) {
		this.mis = mis;
	}
}
