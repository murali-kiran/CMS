package com.sumadga.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the media_providers database table.
 * 
 */
@Entity
@Table(name="media_providers")
@NamedQuery(name="MediaProvider.findAll", query="SELECT m FROM MediaProvider m")
public class MediaProvider implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="media_provider_id")
	private int mediaProviderId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time")
	private Date createdTime;

	@Column(name="media_provider_name")
	private String mediaProviderName;

	@Column(name="modified_time")
	private Timestamp modifiedTime;

	//bi-directional many-to-one association to Media
	@OneToMany(mappedBy="mediaProvider")
	private List<Media> medias;

	public MediaProvider() {
	}

	public int getMediaProviderId() {
		return this.mediaProviderId;
	}

	public void setMediaProviderId(int mediaProviderId) {
		this.mediaProviderId = mediaProviderId;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getMediaProviderName() {
		return this.mediaProviderName;
	}

	public void setMediaProviderName(String mediaProviderName) {
		this.mediaProviderName = mediaProviderName;
	}

	public Timestamp getModifiedTime() {
		return this.modifiedTime;
	}

	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public List<Media> getMedias() {
		return this.medias;
	}

	public void setMedias(List<Media> medias) {
		this.medias = medias;
	}

	public Media addMedia(Media media) {
		getMedias().add(media);
		media.setMediaProvider(this);

		return media;
	}

	public Media removeMedia(Media media) {
		getMedias().remove(media);
		media.setMediaProvider(null);

		return media;
	}

}