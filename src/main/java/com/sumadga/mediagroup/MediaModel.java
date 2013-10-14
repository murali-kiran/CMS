package com.sumadga.mediagroup;

import java.io.Serializable;
import javax.persistence.*;

import com.sumadga.dto.Language;
import com.sumadga.dto.MediaContent;
import com.sumadga.dto.MediaCycle;
import com.sumadga.dto.MediaGroupMedia;
import com.sumadga.dto.MediaProcessState;
import com.sumadga.dto.MediaTag;
import com.sumadga.dto.MediaType;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the media database table.
 * 
 */
public class MediaModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer mediaId;

	private Date createdTime;
	private String description;
	private String mediaName;
	private String mediaTitle;
	private Timestamp modifiedTime;
	private Date mediaStartTime;
	private Date mediaEndTime;
	private Language language;
	private String[] selectedMedia;
	private MediaCycle mediaCycle;
	private MediaProcessState mediaProcessState;
	private boolean checkStatus;
	private Integer mgid;
	private MediaType mediaType;

	private List<MediaContent> mediaContents;

	private List<MediaGroupMedia> mediaGroupMedias;

	public boolean isCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(boolean checkStatus) {
		this.checkStatus = checkStatus;
	}

	private List<MediaTag> mediaTags;

		public Integer getMgid() {
		return mgid;
	}

	public void setMgid(Integer mgid) {
		this.mgid = mgid;
	}

		public String[] getSelectedMedia() {
		return selectedMedia;
	}

	public void setSelectedMedia(String[] selectedMedia) {
		this.selectedMedia = selectedMedia;
	}

		public MediaModel() {
	}

	public Integer getMediaId() {
		return this.mediaId;
	}

	public void setMediaId(Integer mediaId) {
		this.mediaId = mediaId;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMediaName() {
		return this.mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	public String getMediaTitle() {
		return this.mediaTitle;
	}

	public void setMediaTitle(String mediaTitle) {
		this.mediaTitle = mediaTitle;
	}

	public Timestamp getModifiedTime() {
		return this.modifiedTime;
	}

	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public Language getLanguage() {
		return this.language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public MediaCycle getMediaCycle() {
		return this.mediaCycle;
	}

	public void setMediaCycle(MediaCycle mediaCycle) {
		this.mediaCycle = mediaCycle;
	}

	public MediaProcessState getMediaProcessState() {
		return this.mediaProcessState;
	}

	public void setMediaProcessState(MediaProcessState mediaProcessState) {
		this.mediaProcessState = mediaProcessState;
	}


	public MediaType getMediaType() {
		return this.mediaType;
	}

	public void setMediaType(MediaType mediaType) {
		this.mediaType = mediaType;
	}

	public List<MediaContent> getMediaContents() {
		return this.mediaContents;
	}

	public void setMediaContents(List<MediaContent> mediaContents) {
		this.mediaContents = mediaContents;
	}

	

	public List<MediaGroupMedia> getMediaGroupMedias() {
		return this.mediaGroupMedias;
	}

	public void setMediaGroupMedias(List<MediaGroupMedia> mediaGroupMedias) {
		this.mediaGroupMedias = mediaGroupMedias;
	}

	
	public MediaGroupMedia removeMediaGroupMedia(MediaGroupMedia mediaGroupMedia) {
		getMediaGroupMedias().remove(mediaGroupMedia);
		mediaGroupMedia.setMedia(null);

		return mediaGroupMedia;
	}

	public List<MediaTag> getMediaTags() {
		return this.mediaTags;
	}

	public void setMediaTags(List<MediaTag> mediaTags) {
		this.mediaTags = mediaTags;
	}

	

	public MediaTag removeMediaTag(MediaTag mediaTag) {
		getMediaTags().remove(mediaTag);
		mediaTag.setMedia(null);

		return mediaTag;
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

	

}