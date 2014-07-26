package com.sumadga.wap.model;

public class MediaContentBean {
	
	private int mediacontentId;
	private int mediaspecificationId;
	private String storagepath;
	private String md5;
	private String mimeType;
	private int width;
	private int height;
	 
	
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getMediacontentId() {
		return mediacontentId;
	}
	public void setMediacontentId(int mediacontentId) {
		this.mediacontentId = mediacontentId;
	}
	public int getMediaspecificationId() {
		return mediaspecificationId;
	}
	public void setMediaspecificationId(int mediaspecificationId) {
		this.mediaspecificationId = mediaspecificationId;
	}
	public String getStoragepath() {
		return storagepath;
	}
	public void setStoragepath(String storagepath) {
		this.storagepath = storagepath;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	
	

}
