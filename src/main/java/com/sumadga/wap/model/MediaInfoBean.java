package com.sumadga.wap.model;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;


@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class MediaInfoBean{
	
	private Integer  mediaId;
	private String medianame;
	private String mediatitle;
	private String description;
	private String createdtime;
	private String modifiedtime;
	private Integer mediacycleId;
	private Integer mediatypeId;
	private String mediatype;
	private Integer mediaprocessstateId;
	private String mediaprocessstate;
	private String mediastarttime;
	private String mediaendtime;
	private Integer languageId;
	private String language;
	private Integer mediaprovIderId;
	private String mediaprovider;
	
	private List<MediaContentBean> mediacontents;
	private List<String> tags;
	
	
	
	public String getMedianame() {
		return medianame;
	}
	public void setMedianame(String medianame) {
		this.medianame = medianame;
	}
	public String getMediatitle() {
		return mediatitle;
	}
	public void setMediatitle(String mediatitle) {
		this.mediatitle = mediatitle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public String getCreatedtime() {
		return createdtime;
	}
	public void setCreatedtime(String createdtime) {
		this.createdtime = createdtime;
	}
	public String getModifiedtime() {
		return modifiedtime;
	}
	public void setModifiedtime(String modifiedtime) {
		this.modifiedtime = modifiedtime;
	}
	public String getMediastarttime() {
		return mediastarttime;
	}
	public void setMediastarttime(String mediastarttime) {
		this.mediastarttime = mediastarttime;
	}
	public String getMediaendtime() {
		return mediaendtime;
	}
	public void setMediaendtime(String mediaendtime) {
		this.mediaendtime = mediaendtime;
	}
	public String getMediatype() {
		return mediatype;
	}
	public void setMediatype(String mediatype) {
		this.mediatype = mediatype;
	}
	public String getMediaprocessstate() {
		return mediaprocessstate;
	}
	public void setMediaprocessstate(String mediaprocessstate) {
		this.mediaprocessstate = mediaprocessstate;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getMediaprovider() {
		return mediaprovider;
	}
	public void setMediaprovider(String mediaprovider) {
		this.mediaprovider = mediaprovider;
	}
	public List<MediaContentBean> getMediacontents() {
		return mediacontents;
	}
	public void setMediacontents(List<MediaContentBean> mediacontents) {
		this.mediacontents = mediacontents;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public Integer getMediaId() {
		return mediaId;
	}
	public void setMediaId(Integer mediaId) {
		this.mediaId = mediaId;
	}
	public Integer getMediacycleId() {
		return mediacycleId;
	}
	public void setMediacycleId(Integer mediacycleId) {
		this.mediacycleId = mediacycleId;
	}
	public Integer getMediatypeId() {
		return mediatypeId;
	}
	public void setMediatypeId(Integer mediatypeId) {
		this.mediatypeId = mediatypeId;
	}
	public Integer getMediaprocessstateId() {
		return mediaprocessstateId;
	}
	public void setMediaprocessstateId(Integer mediaprocessstateId) {
		this.mediaprocessstateId = mediaprocessstateId;
	}
	public Integer getLanguageId() {
		return languageId;
	}
	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}
	public Integer getMediaprovIderId() {
		return mediaprovIderId;
	}
	public void setMediaprovIderId(Integer mediaprovIderId) {
		this.mediaprovIderId = mediaprovIderId;
	}
	


}
