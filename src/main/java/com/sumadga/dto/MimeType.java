package com.sumadga.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the mime_types database table.
 * 
 */
@Entity
@Table(name="mime_types")
public class MimeType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="mime_type_id", unique=true, nullable=false)
	private Integer mimeTypeId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time", nullable=false)
	private Date createdTime;

	@Column(name="media_extension", nullable=false, length=45)
	private String mediaExtension;

	@Column(name="mime_type", nullable=false, length=45)
	private String mimeType;

	@Column(name="modified_time", nullable=false)
	private Timestamp modifiedTime;

	//bi-directional many-to-one association to MediaSpecification
	@OneToMany(mappedBy="mimeType")
	private List<MediaSpecification> mediaSpecifications;

	public MimeType() {
	}

	public Integer getMimeTypeId() {
		return this.mimeTypeId;
	}

	public void setMimeTypeId(Integer mimeTypeId) {
		this.mimeTypeId = mimeTypeId;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getMediaExtension() {
		return this.mediaExtension;
	}

	public void setMediaExtension(String mediaExtension) {
		this.mediaExtension = mediaExtension;
	}

	public String getMimeType() {
		return this.mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public Timestamp getModifiedTime() {
		return this.modifiedTime;
	}

	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public List<MediaSpecification> getMediaSpecifications() {
		return this.mediaSpecifications;
	}

	public void setMediaSpecifications(List<MediaSpecification> mediaSpecifications) {
		this.mediaSpecifications = mediaSpecifications;
	}

	public MediaSpecification addMediaSpecification(MediaSpecification mediaSpecification) {
		getMediaSpecifications().add(mediaSpecification);
		mediaSpecification.setMimeType(this);

		return mediaSpecification;
	}

	public MediaSpecification removeMediaSpecification(MediaSpecification mediaSpecification) {
		getMediaSpecifications().remove(mediaSpecification);
		mediaSpecification.setMimeType(null);

		return mediaSpecification;
	}

	@Override
	public String toString() {
		return "MimeType [mimeTypeId=" + mimeTypeId + ", createdTime="
				+ createdTime + ", mediaExtension=" + mediaExtension
				+ ", mimeType=" + mimeType + ", modifiedTime=" + modifiedTime
				+ ", mediaSpecifications=" + mediaSpecifications + "]";
	}

}