package com.sumadga.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the media_owners database table.
 * 
 */
@Entity
@Table(name="media_owners")
public class MediaOwner implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="media_owner_id", unique=true, nullable=false)
	private int mediaOwnerId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time", nullable=false)
	private Date createdTime;

	@Column(nullable=false, length=256)
	private String description;

	@Column(name="media_owner_name", nullable=false, length=100)
	private String mediaOwnerName;

	@Column(name="media_owner_short_name", nullable=false, length=45)
	private String mediaOwnerShortName;

	@Column(name="modified_time", nullable=false)
	private Timestamp modifiedTime;

	//bi-directional many-to-one association to MediaServiceProvider
	@OneToMany(mappedBy="mediaOwner")
	private List<MediaServiceProvider> mediaServiceProviders;

	public MediaOwner() {
	}

	public int getMediaOwnerId() {
		return this.mediaOwnerId;
	}

	public void setMediaOwnerId(int mediaOwnerId) {
		this.mediaOwnerId = mediaOwnerId;
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

	public String getMediaOwnerName() {
		return this.mediaOwnerName;
	}

	public void setMediaOwnerName(String mediaOwnerName) {
		this.mediaOwnerName = mediaOwnerName;
	}

	public String getMediaOwnerShortName() {
		return this.mediaOwnerShortName;
	}

	public void setMediaOwnerShortName(String mediaOwnerShortName) {
		this.mediaOwnerShortName = mediaOwnerShortName;
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
		mediaServiceProvider.setMediaOwner(this);

		return mediaServiceProvider;
	}

	public MediaServiceProvider removeMediaServiceProvider(MediaServiceProvider mediaServiceProvider) {
		getMediaServiceProviders().remove(mediaServiceProvider);
		mediaServiceProvider.setMediaOwner(null);

		return mediaServiceProvider;
	}

	@Override
	public String toString() {
		return "MediaOwner [mediaOwnerId=" + mediaOwnerId + ", createdTime="
				+ createdTime + ", description=" + description
				+ ", mediaOwnerName=" + mediaOwnerName
				+ ", mediaOwnerShortName=" + mediaOwnerShortName
				+ ", modifiedTime=" + modifiedTime + ", mediaServiceProviders="
				+ mediaServiceProviders + "]";
	}

}