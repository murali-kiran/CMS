package com.sumadga.wap.model;

public class MediaBean {
	
	
	private int mediaId;
	private int mediaTypeId;
	private int mediagroupId;
	private int mediaContentId;
	private String storagePath;
	private String mediaName;
	private Boolean isSubMediaGroup;
	private int serviceId;
	private int serviceKeyId;
	private int serviceKeypriceId;
	private Double price;
	
	
	
	
	
	
	public int getServiceKeyId() {
		return serviceKeyId;
	}
	public void setServiceKeyId(int serviceKeyId) {
		this.serviceKeyId = serviceKeyId;
	}
	public int getServiceKeypriceId() {
		return serviceKeypriceId;
	}
	public void setServiceKeypriceId(int serviceKeypriceId) {
		this.serviceKeypriceId = serviceKeypriceId;
	}
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getMediaContentId() {
		return mediaContentId;
	}
	public void setMediaContentId(int mediaContentId) {
		this.mediaContentId = mediaContentId;
	}
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
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	
	
}
