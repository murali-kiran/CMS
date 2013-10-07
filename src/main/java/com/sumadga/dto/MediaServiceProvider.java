package com.sumadga.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the media_service_providers database table.
 * 
 */
@Entity
@Table(name="media_service_providers")
public class MediaServiceProvider implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="media_service_provider_id", unique=true, nullable=false)
	private int mediaServiceProviderId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time", nullable=false)
	private Date createdTime;

	@Column(nullable=false, length=256)
	private String description;

	@Column(name="modified_time", nullable=false)
	private Timestamp modifiedTime;

	//bi-directional many-to-one association to Media
	@OneToMany(mappedBy="mediaServiceProvider")
	private List<Media> medias;

	//bi-directional many-to-one association to MediaServiceOwnerSpecfication
	@OneToMany(mappedBy="mediaServiceProvider")
	private List<MediaServiceOwnerSpecfication> mediaServiceOwnerSpecfications;

	//bi-directional many-to-one association to MediaOwner
	@ManyToOne
	@JoinColumn(name="media_owner_id", nullable=false)
	private MediaOwner mediaOwner;

	//bi-directional many-to-one association to MediaProvider
	@ManyToOne
	@JoinColumn(name="media_provider_id", nullable=false)
	private MediaProvider mediaProvider;

	public MediaServiceProvider() {
	}

	public int getMediaServiceProviderId() {
		return this.mediaServiceProviderId;
	}

	public void setMediaServiceProviderId(int mediaServiceProviderId) {
		this.mediaServiceProviderId = mediaServiceProviderId;
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
		media.setMediaServiceProvider(this);

		return media;
	}

	public Media removeMedia(Media media) {
		getMedias().remove(media);
		media.setMediaServiceProvider(null);

		return media;
	}

	public List<MediaServiceOwnerSpecfication> getMediaServiceOwnerSpecfications() {
		return this.mediaServiceOwnerSpecfications;
	}

	public void setMediaServiceOwnerSpecfications(List<MediaServiceOwnerSpecfication> mediaServiceOwnerSpecfications) {
		this.mediaServiceOwnerSpecfications = mediaServiceOwnerSpecfications;
	}

	public MediaServiceOwnerSpecfication addMediaServiceOwnerSpecfication(MediaServiceOwnerSpecfication mediaServiceOwnerSpecfication) {
		getMediaServiceOwnerSpecfications().add(mediaServiceOwnerSpecfication);
		mediaServiceOwnerSpecfication.setMediaServiceProvider(this);

		return mediaServiceOwnerSpecfication;
	}

	public MediaServiceOwnerSpecfication removeMediaServiceOwnerSpecfication(MediaServiceOwnerSpecfication mediaServiceOwnerSpecfication) {
		getMediaServiceOwnerSpecfications().remove(mediaServiceOwnerSpecfication);
		mediaServiceOwnerSpecfication.setMediaServiceProvider(null);

		return mediaServiceOwnerSpecfication;
	}

	public MediaOwner getMediaOwner() {
		return this.mediaOwner;
	}

	public void setMediaOwner(MediaOwner mediaOwner) {
		this.mediaOwner = mediaOwner;
	}

	public MediaProvider getMediaProvider() {
		return this.mediaProvider;
	}

	public void setMediaProvider(MediaProvider mediaProvider) {
		this.mediaProvider = mediaProvider;
	}

	@Override
	public String toString() {
		return "MediaServiceProvider [mediaServiceProviderId="
				+ mediaServiceProviderId + ", createdTime=" + createdTime
				+ ", description=" + description + ", modifiedTime="
				+ modifiedTime + ", medias=" + medias
				+ ", mediaServiceOwnerSpecfications="
				+ mediaServiceOwnerSpecfications + ", mediaOwner=" + mediaOwner
				+ ", mediaProvider=" + mediaProvider + "]";
	}

}