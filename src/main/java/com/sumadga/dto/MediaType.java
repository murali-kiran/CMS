package com.sumadga.dto;

import java.io.Serializable;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonBackReference;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the media_types database table.
 * 
 */
@Entity
@Table(name="media_types")
public class MediaType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="media_type_id", unique=true, nullable=false)
	private Integer mediaTypeId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time", nullable=false)
	private Date createdTime;

	@Column(name="media_type_name", nullable=false, length=45)
	private String mediaTypeName;

	@Column(name="media_type_title", nullable=false, length=45)
	private String mediaTypeTitle;

	@Column(name="modified_time", nullable=false)
	private Timestamp modifiedTime;

	//bi-directional many-to-one association to Media
	@OneToMany(mappedBy="mediaType",fetch=FetchType.EAGER)
	@JsonBackReference
	private List<Media> medias;

	//bi-directional many-to-one association to MediaSpecification
	@OneToMany(mappedBy="mediaType")
	private List<MediaSpecification> mediaSpecifications;

	public MediaType() {
	}

	public Integer getMediaTypeId() {
		return this.mediaTypeId;
	}

	public void setMediaTypeId(Integer mediaTypeId) {
		this.mediaTypeId = mediaTypeId;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getMediaTypeName() {
		return this.mediaTypeName;
	}

	public void setMediaTypeName(String mediaTypeName) {
		this.mediaTypeName = mediaTypeName;
	}

	public String getMediaTypeTitle() {
		return this.mediaTypeTitle;
	}

	public void setMediaTypeTitle(String mediaTypeTitle) {
		this.mediaTypeTitle = mediaTypeTitle;
	}

	public Timestamp getModifiedTime() {
		return this.modifiedTime;
	}

	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	/*public List<Media> getMedias() {
		return this.medias;
	}

	public void setMedias(List<Media> medias) {
		this.medias = medias;
	}*/

	/*public Media addMedia(Media media) {
		getMedias().add(media);
		media.setMediaType(this);

		return media;
	}

	public Media removeMedia(Media media) {
		getMedias().remove(media);
		media.setMediaType(null);

		return media;
	}*/

	/*public List<MediaSpecification> getMediaSpecifications() {
		return this.mediaSpecifications;
	}

	public void setMediaSpecifications(List<MediaSpecification> mediaSpecifications) {
		this.mediaSpecifications = mediaSpecifications;
	}

	public MediaSpecification addMediaSpecification(MediaSpecification mediaSpecification) {
		getMediaSpecifications().add(mediaSpecification);
		mediaSpecification.setMediaType(this);

		return mediaSpecification;
	}

	public MediaSpecification removeMediaSpecification(MediaSpecification mediaSpecification) {
		getMediaSpecifications().remove(mediaSpecification);
		mediaSpecification.setMediaType(null);

		return mediaSpecification;
	}*/

	@Override
	public String toString() {
		return "MediaType [mediaTypeId=" + mediaTypeId + ", createdTime="
				+ createdTime + ", mediaTypeName=" + mediaTypeName
				+ ", mediaTypeTitle=" + mediaTypeTitle + ", modifiedTime="
				+ modifiedTime 
				 + "]";
	}

}