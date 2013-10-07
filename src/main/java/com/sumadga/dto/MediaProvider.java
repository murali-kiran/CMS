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
public class MediaProvider implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="media_provider_id", unique=true, nullable=false)
	private int mediaProviderId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time", nullable=false)
	private Date createdTime;

	@Column(nullable=false, length=256)
	private String description;

	@Column(name="media_provider_name", nullable=false, length=45)
	private String mediaProviderName;

	@Column(name="modified_time", nullable=false)
	private Timestamp modifiedTime;

	//bi-directional many-to-one association to MediaServiceProvider
	@OneToMany(mappedBy="mediaProvider")
	private List<MediaServiceProvider> mediaServiceProviders;

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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public List<MediaServiceProvider> getMediaServiceProviders() {
		return this.mediaServiceProviders;
	}

	public void setMediaServiceProviders(List<MediaServiceProvider> mediaServiceProviders) {
		this.mediaServiceProviders = mediaServiceProviders;
	}

	public MediaServiceProvider addMediaServiceProvider(MediaServiceProvider mediaServiceProvider) {
		getMediaServiceProviders().add(mediaServiceProvider);
		mediaServiceProvider.setMediaProvider(this);

		return mediaServiceProvider;
	}

	public MediaServiceProvider removeMediaServiceProvider(MediaServiceProvider mediaServiceProvider) {
		getMediaServiceProviders().remove(mediaServiceProvider);
		mediaServiceProvider.setMediaProvider(null);

		return mediaServiceProvider;
	}

	@Override
	public String toString() {
		return "MediaProvider [mediaProviderId=" + mediaProviderId
				+ ", createdTime=" + createdTime + ", description="
				+ description + ", mediaProviderName=" + mediaProviderName
				+ ", modifiedTime=" + modifiedTime + ", mediaServiceProviders="
				+ mediaServiceProviders + "]";
	}

}