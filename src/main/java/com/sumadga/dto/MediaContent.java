package com.sumadga.dto;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the media_contents database table.
 * 
 */
@Entity
@Table(name="media_contents")
public class MediaContent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="media_content_id", unique=true, nullable=false)
	private Integer mediaContentId;

	@Column(nullable=false, length=45)
	private String md5;

	@Column(name="storage_path", nullable=false, length=1024)
	private String storagePath;

	//bi-directional many-to-one association to Media
	@ManyToOne
	@JoinColumn(name="media_id", nullable=false)
	private Media media;

	//bi-directional many-to-one association to MediaSpecification
	@ManyToOne
	@JoinColumn(name="media_specification_id", nullable=false)
	private MediaSpecification mediaSpecification;

	public MediaContent() {
	}

	public Integer getMediaContentId() {
		return this.mediaContentId;
	}

	public void setMediaContentId(Integer mediaContentId) {
		this.mediaContentId = mediaContentId;
	}

	public String getMd5() {
		return this.md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
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

	public MediaSpecification getMediaSpecification() {
		return this.mediaSpecification;
	}

	public void setMediaSpecification(MediaSpecification mediaSpecification) {
		this.mediaSpecification = mediaSpecification;
	}

	@Override
	public String toString() {
		return "MediaContent [mediaContentId=" + mediaContentId + ", md5="
				+ md5 + ", storagePath=" + storagePath + ", media=" + media
				+ ", mediaSpecification=" + mediaSpecification + "]";
	}

}