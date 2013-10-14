package com.sumadga.upload;

import org.springframework.web.multipart.MultipartFile;

public class MediaContentModel {
	
	private MultipartFile file;
	private String mimeType,label,purpose,duration;
	private Integer mediaSpecificationId;
	
	private Integer bitRate;
	private Integer width ;
	private Integer height ; 
	private Boolean isLocal=false;
	private String remoteUrl;
	private Integer id;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public Integer getMediaSpecificationId() {
		return mediaSpecificationId;
	}
	public void setMediaSpecificationId(Integer mediaSpecificationId) {
		this.mediaSpecificationId = mediaSpecificationId;
	}
	public Integer getBitRate() {
		return bitRate;
	}
	public void setBitRate(Integer bitRate) {
		this.bitRate = bitRate;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Boolean getIsLocal() {
		return isLocal;
	}
	public void setIsLocal(Boolean isLocal) {
		this.isLocal = isLocal;
	}
	public String getRemoteUrl() {
		return remoteUrl;
	}
	public void setRemoteUrl(String remoteUrl) {
		this.remoteUrl = remoteUrl;
	}
	@Override
	public String toString() {
		return "MediaContentModel [file=" + file + ", mimeType=" + mimeType
				+ ", label=" + label + ", purpose=" + purpose + ", duration="
				+ duration + ", mediaSpecificationId=" + mediaSpecificationId
				+ ", bitRate=" + bitRate + ", width=" + width + ", height="
				+ height + ", isLocal=" + isLocal + ", remoteUrl=" + remoteUrl
				+ "]";
	}

	
	
}
