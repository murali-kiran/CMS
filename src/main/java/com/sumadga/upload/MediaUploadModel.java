package com.sumadga.upload;

import java.util.Date;
import java.util.List;

public class MediaUploadModel {
	
	private Integer mediaTypeId;
	private Integer mediaCycleId;
	private Integer languageId;
	private String mediaTitle;
	private String mediaName;
	private String description,tags;
	private Date mediaStartTime;
	private Date mediaEndTime;
	private Integer mediaId;
	private List<MediaContentModel> mediaContentModelList;
	
	public Integer getMediaTypeId() {
		return mediaTypeId;
	}
	public void setMediaTypeId(Integer mediaTypeId) {
		this.mediaTypeId = mediaTypeId;
	}
	public Integer getMediaCycleId() {
		return mediaCycleId;
	}
	public void setMediaCycleId(Integer mediaCycleId) {
		this.mediaCycleId = mediaCycleId;
	}
	public Integer getLanguageId() {
		return languageId;
	}
	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}
	public String getMediaTitle() {
		return mediaTitle;
	}
	public void setMediaTitle(String mediaTitle) {
		this.mediaTitle = mediaTitle;
	}
	public String getMediaName() {
		return mediaName;
	}
	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public Date getMediaStartTime() {
		return mediaStartTime;
	}
	public void setMediaStartTime(Date mediaStartTime) {
		this.mediaStartTime = mediaStartTime;
	}
	public Date getMediaEndTime() {
		return mediaEndTime;
	}
	public void setMediaEndTime(Date mediaEndTime) {
		this.mediaEndTime = mediaEndTime;
	}
	public Integer getMediaId() {
		return mediaId;
	}
	public void setMediaId(Integer mediaId) {
		this.mediaId = mediaId;
	}
	public List<MediaContentModel> getMediaContentModelList() {
		return mediaContentModelList;
	}
	public void setMediaContentModelList(
			List<MediaContentModel> mediaContentModelList) {
		this.mediaContentModelList = mediaContentModelList;
	}	
	
}
