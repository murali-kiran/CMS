package com.sumadga.dto;

import java.io.Serializable;
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

	public MediaAppContent() {
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