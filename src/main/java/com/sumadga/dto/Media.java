package com.sumadga.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the media database table.
 * 
 */
@Entity
@Table(name="media")
public class Media implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="media_id", unique=true, nullable=false)
	private Integer mediaId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time", nullable=false)
	private Date createdTime;

	@Column(nullable=false, length=256)
	private String description;

	@Column(name="media_name", nullable=false, length=100)
	private String mediaName;

	@Column(name="media_title", nullable=false, length=75)
	private String mediaTitle;

	@Column(name="modified_time", nullable=false)
	private Timestamp modifiedTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="media_start_time", nullable=false)
	private Date mediaStartTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="media_end_time", nullable=false)
	private Date mediaEndTime;

	//bi-directional many-to-one association to Language
	@ManyToOne
	@JoinColumn(name="language_id", nullable=false)
	private Language language;

	//bi-directional many-to-one association to MediaCycle
	@ManyToOne
	@JoinColumn(name="media_cycle_id", nullable=false)
	private MediaCycle mediaCycle;

	//bi-directional many-to-one association to MediaProcessState
	@ManyToOne
	@JoinColumn(name="media_process_state_id", nullable=false)
	private MediaProcessState mediaProcessState;

	
	//bi-directional many-to-one association to MediaType
	@ManyToOne
	@JoinColumn(name="media_type_id", nullable=false)
	private MediaType mediaType;

	//bi-directional many-to-one association to MediaContent
	@OneToMany(mappedBy="media")
	private List<MediaContent> mediaContents;

	//bi-directional many-to-one association to MediaGroupMedia
	@OneToMany(mappedBy="media")
	private List<MediaGroupMedia> mediaGroupMedias;

	//bi-directional many-to-one association to MediaTag
	@OneToMany(mappedBy="media")
	private List<MediaTag> mediaTags;

	public Media() {
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

	public MediaContent addMediaContent(MediaContent mediaContent) {
		getMediaContents().add(mediaContent);
		mediaContent.setMedia(this);

		return mediaContent;
	}

	public MediaContent removeMediaContent(MediaContent mediaContent) {
		getMediaContents().remove(mediaContent);
		mediaContent.setMedia(null);

		return mediaContent;
	}

	public List<MediaGroupMedia> getMediaGroupMedias() {
		return this.mediaGroupMedias;
	}

	public void setMediaGroupMedias(List<MediaGroupMedia> mediaGroupMedias) {
		this.mediaGroupMedias = mediaGroupMedias;
	}

	public MediaGroupMedia addMediaGroupMedia(MediaGroupMedia mediaGroupMedia) {
		getMediaGroupMedias().add(mediaGroupMedia);
		mediaGroupMedia.setMedia(this);

		return mediaGroupMedia;
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

	public MediaTag addMediaTag(MediaTag mediaTag) {
		getMediaTags().add(mediaTag);
		mediaTag.setMedia(this);

		return mediaTag;
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

	@Override
	public String toString() {
		return "Media [mediaId=" + mediaId + ", createdTime=" + createdTime
				+ ", description=" + description + ", mediaName=" + mediaName
				+ ", mediaTitle=" + mediaTitle + ", modifiedTime="
				+ modifiedTime + ", language=" + language + ", mediaCycle="
				+ mediaCycle + ", mediaProcessState=" + mediaProcessState
				+ ", mediaType=" + mediaType + ", mediaContents="
				+ mediaContents + ", mediaGroupMedias=" + mediaGroupMedias
				+ ", mediaTags=" + mediaTags 
				+ ", mediaStartTime=" + mediaStartTime 
				+ ", mediaEndTime="+mediaEndTime+"]";
	}

}