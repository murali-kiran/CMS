package com.sumadga.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the media_content_purposes database table.
 * 
 */
@Entity
@Table(name="media_content_purposes")
public class MediaContentPurpos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="media_content_purpose_id", unique=true, nullable=false)
	private Integer mediaContentPurposeId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time", nullable=false)
	private Date createdTime;

	@Column(name="media_content_purpose", nullable=false, length=45)
	private String mediaContentPurpose;

	@Column(name="modified_time", nullable=false)
	private Timestamp modifiedTime;

	//bi-directional many-to-one association to MediaSpecification
	@OneToMany(mappedBy="mediaContentPurpos")
	private List<MediaSpecification> mediaSpecifications;

	public MediaContentPurpos() {
	}

	public Integer getMediaContentPurposeId() {
		return this.mediaContentPurposeId;
	}

	public void setMediaContentPurposeId(Integer mediaContentPurposeId) {
		this.mediaContentPurposeId = mediaContentPurposeId;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getMediaContentPurpose() {
		return this.mediaContentPurpose;
	}

	public void setMediaContentPurpose(String mediaContentPurpose) {
		this.mediaContentPurpose = mediaContentPurpose;
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
		mediaSpecification.setMediaContentPurpos(this);

		return mediaSpecification;
	}

	public MediaSpecification removeMediaSpecification(MediaSpecification mediaSpecification) {
		getMediaSpecifications().remove(mediaSpecification);
		mediaSpecification.setMediaContentPurpos(null);

		return mediaSpecification;
	}

	@Override
	public String toString() {
		return "MediaContentPurpos [mediaContentPurposeId="
				+ mediaContentPurposeId + ", createdTime=" + createdTime
				+ ", mediaContentPurpose=" + mediaContentPurpose
				+ ", modifiedTime=" + modifiedTime + ", mediaSpecifications="
				+ mediaSpecifications + "]";
	}

}