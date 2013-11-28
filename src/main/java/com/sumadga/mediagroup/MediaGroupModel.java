package com.sumadga.mediagroup;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the media_groups database table.
 * 
 */
public class MediaGroupModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer mediaGroupId;
	private Date createdTime;
	private String description;
	private String mediaGroupName;
	private Integer mediaId;
	private String mediaGroupTitle;
	private Timestamp modifiedTime;
	private List<MediaGroupMediaModel> mediaGroupMedias;
	private boolean checkStatus;
	private String[] selectedMediaGroup;
	private Integer parentmgId;
	private Integer mediaSubGroupId;
	private Integer serviceMediaGroupId;
	private Integer serviceId;
	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public Integer getServiceMediaGroupId() {
		return serviceMediaGroupId;
	}

	public void setServiceMediaGroupId(Integer serviceMediaGroupId) {
		this.serviceMediaGroupId = serviceMediaGroupId;
	}

	public Integer getMediaSubGroupId() {
		return mediaSubGroupId;
	}

	public void setMediaSubGroupId(Integer mediaSubGroupId) {
		this.mediaSubGroupId = mediaSubGroupId;
	}

	public Integer getParentmgId() {
		return parentmgId;
	}

	public void setParentmgId(Integer parentmgId) {
		this.parentmgId = parentmgId;
	}

	public String[] getSelectedMediaGroup() {
		return selectedMediaGroup;
	}

	public void setSelectedMediaGroup(String[] selectedMediaGroup) {
		this.selectedMediaGroup = selectedMediaGroup;
	}

	public boolean isCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(boolean checkStatus) {
		this.checkStatus = checkStatus;
	}

	public MediaGroupModel() {
	}

	public Integer getMediaGroupId() {
		return this.mediaGroupId;
	}

	public void setMediaGroupId(Integer mediaGroupId) {
		this.mediaGroupId = mediaGroupId;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}


	public String getMediaGroupName() {
		return this.mediaGroupName;
	}

	public void setMediaGroupName(String mediaGroupName) {
		this.mediaGroupName = mediaGroupName;
	}

	public String getMediaGroupTitle() {
		return this.mediaGroupTitle;
	}

	public void setMediaGroupTitle(String mediaGroupTitle) {
		this.mediaGroupTitle = mediaGroupTitle;
	}

	public Timestamp getModifiedTime() {
		return this.modifiedTime;
	}

	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public List<MediaGroupMediaModel> getMediaGroupMedias() {
		return mediaGroupMedias;
	}

	public void setMediaGroupMedias(List<MediaGroupMediaModel> mediaGroupMedias) {
		this.mediaGroupMedias = mediaGroupMedias;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getMediaId() {
		return mediaId;
	}

	public void setMediaId(Integer mediaId) {
		this.mediaId = mediaId;
	}

	

}