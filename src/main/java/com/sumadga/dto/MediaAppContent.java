package com.sumadga.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the media_contents database table.
 * 
 */
@Entity
@Table(name="media_app_contents")
public class MediaAppContent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="media_app_content_id", unique=true, nullable=false)
	private Integer mediaAppContentId;

	@Column(nullable=true, length=512)
	private String description;

	@Column(name="storage_path", nullable=false, length=1024)
	private String storagePath;

	//bi-directional many-to-one association to Media
	@ManyToOne
	@JoinColumn(name="media_id", nullable=false)
	private Media media;
	@ManyToOne
	@JoinColumn(name="os_id", nullable=false)
	private Os os;
	@ManyToOne
	@JoinColumn(name="mime_type_id", nullable=false)
	private MimeType mimeType;
	public MediaAppContent() {
	}
	@Column(name="purpose", nullable=false, length=20)
	private String purpose;
	@Column(name="height", nullable=false)
	private Integer height;
	@Column(name="width", nullable=false)
	private Integer width;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time", nullable=false)
	private Date createdTime;
	@Column(name="modified_time", nullable=false)
	private Timestamp modifiedTime;

	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Timestamp getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getMediaAppContentId() {
		return mediaAppContentId;
	}
	public void setMediaAppContentId(Integer mediaAppContentId) {
		this.mediaAppContentId = mediaAppContentId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Os getOs() {
		return os;
	}
	public void setOs(Os os) {
		this.os = os;
	}
	public MimeType getMimeType() {
		return mimeType;
	}
	public void setMimeType(MimeType mimeType) {
		this.mimeType = mimeType;
	}
	public String getStoragePath() {
		return this.storagePath;
	}

	public void setStoragePath(String storagePath) {
		this.storagePath = storagePath;
	}

	public Media getMedia() {
		return this.media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}


	/*@Override
	public String toString() {
		return "MediaContent [mediaContentId=" + mediaContentId + ", md5="
				+ md5 + ", storagePath=" + storagePath + ", media=" + media
				+ ", mediaSpecification=" + mediaSpecification + "]";
	}*/

}