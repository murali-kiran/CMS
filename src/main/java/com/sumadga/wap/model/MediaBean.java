package com.sumadga.wap.model;

public class MediaBean {
	
	private int mediaId;
	private int mediaTypeId;
	private int mediagroupId;
	private String storagePath;
	private String mediaName;
	private Boolean isSubMediaGroup;
	
	public Boolean getIsSubMediaGroup() {
		return isSubMediaGroup;
	}
	public void setIsSubMediaGroup(Boolean isSubMediaGroup) {
		this.isSubMediaGroup = isSubMediaGroup;
	}
	public int getMediagroupId() {
		return mediagroupId;
	}
	public void setMediagroupId(int mediagroupId) {
		this.mediagroupId = mediagroupId;
	}
	public int getMediaId() {
		return mediaId;
	}
	public void setMediaId(int mediaId) {
		this.mediaId = mediaId;
	}
	public String getMediaName() {
		return mediaName;
	}
	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}
	public int getMediaTypeId() {
		return mediaTypeId;
	}
	public void setMediaTypeId(int mediaTypeId) {
		this.mediaTypeId = mediaTypeId;
	}
	public String getStoragePath() {
		return storagePath;
	}
	public void setStoragePath(String storagePath) {
		this.storagePath = storagePath;
	}
	
	
	
	
}
