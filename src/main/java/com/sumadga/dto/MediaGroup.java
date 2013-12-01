package com.sumadga.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the media_groups database table.
 * 
 */
@Entity
@Table(name="media_groups")
public class MediaGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="media_group_id", unique=true, nullable=false)
	private Integer mediaGroupId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time", nullable=false)
	private Date createdTime;

	@Column(name="media_group_description", nullable=false, length=256)
	private String mediaGroupDescription;

	@Column(name="media_group_name", nullable=false, length=45)
	private String mediaGroupName;

	@Column(name="media_group_preview_id", nullable=false)
	private Integer mediaGroupPreviewId;

	@Column(name="media_group_title", nullable=false, length=45)
	private String mediaGroupTitle;

	@Column(name="modified_time", nullable=false)
	private Timestamp modifiedTime;

	//bi-directional many-to-one association to MediaGroupMedia
	@OneToMany(mappedBy="mediaGroup")
	private List<MediaGroupMedia> mediaGroupMedias;

	public MediaGroup() {
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

	public String getMediaGroupDescription() {
		return this.mediaGroupDescription;
	}

	public void setMediaGroupDescription(String mediaGroupDescription) {
		this.mediaGroupDescription = mediaGroupDescription;
	}

	public String getMediaGroupName() {
		return this.mediaGroupName;
	}

	public void setMediaGroupName(String mediaGroupName) {
		this.mediaGroupName = mediaGroupName;
	}

	public Integer getMediaGroupPreviewId() {
		return this.mediaGroupPreviewId;
	}

	public void setMediaGroupPreviewId(Integer mediaGroupPreviewId) {
		this.mediaGroupPreviewId = mediaGroupPreviewId;
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

	public List<MediaGroupMedia> getMediaGroupMedias() {
		return this.mediaGroupMedias;
	}

	public void setMediaGroupMedias(List<MediaGroupMedia> mediaGroupMedias) {
		this.mediaGroupMedias = mediaGroupMedias;
	}

	public MediaGroupMedia addMediaGroupMedia(MediaGroupMedia mediaGroupMedia) {
		getMediaGroupMedias().add(mediaGroupMedia);
		mediaGroupMedia.setMediaGroup(this);

		return mediaGroupMedia;
	}

	public MediaGroupMedia removeMediaGroupMedia(MediaGroupMedia mediaGroupMedia) {
		getMediaGroupMedias().remove(mediaGroupMedia);
		mediaGroupMedia.setMediaGroup(null);

		return mediaGroupMedia;
	}

	@Override
	public String toString() {
		return "MediaGroup [mediaGroupId=" + mediaGroupId + ", createdTime="
				+ createdTime + ", mediaGroupDescription="
				+ mediaGroupDescription + ", mediaGroupName=" + mediaGroupName
				+ ", mediaGroupPreviewId=" + mediaGroupPreviewId
				+ ", mediaGroupTitle=" + mediaGroupTitle + ", modifiedTime="
				+ modifiedTime + ", mediaGroupMedias=" + mediaGroupMedias + "]";
	}

}