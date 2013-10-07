package com.sumadga.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the media_service_owner_specfications database table.
 * 
 */
@Entity
@Table(name="media_service_owner_specfications")
public class MediaServiceOwnerSpecfication implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="media_service_owner_specfication_id", unique=true, nullable=false)
	private int mediaServiceOwnerSpecficationId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time", nullable=false)
	private Date createdTime;

	@Column(name="modified_time", nullable=false)
	private Timestamp modifiedTime;

	//bi-directional many-to-one association to MediaServiceProvider
	@ManyToOne
	@JoinColumn(name="media_service_owner_id", nullable=false)
	private MediaServiceProvider mediaServiceProvider;

	//bi-directional many-to-one association to MediaSpecification
	@ManyToOne
	@JoinColumn(name="media_specification_id", nullable=false)
	private MediaSpecification mediaSpecification;

	public MediaServiceOwnerSpecfication() {
	}

	public int getMediaServiceOwnerSpecficationId() {
		return this.mediaServiceOwnerSpecficationId;
	}

	public void setMediaServiceOwnerSpecficationId(int mediaServiceOwnerSpecficationId) {
		this.mediaServiceOwnerSpecficationId = mediaServiceOwnerSpecficationId;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Timestamp getModifiedTime() {
		return this.modifiedTime;
	}

	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public MediaServiceProvider getMediaServiceProvider() {
		return this.mediaServiceProvider;
	}

	public void setMediaServiceProvider(MediaServiceProvider mediaServiceProvider) {
		this.mediaServiceProvider = mediaServiceProvider;
	}

	public MediaSpecification getMediaSpecification() {
		return this.mediaSpecification;
	}

	public void setMediaSpecification(MediaSpecification mediaSpecification) {
		this.mediaSpecification = mediaSpecification;
	}

	@Override
	public String toString() {
		return "MediaServiceOwnerSpecfication [mediaServiceOwnerSpecficationId="
				+ mediaServiceOwnerSpecficationId
				+ ", createdTime="
				+ createdTime
				+ ", modifiedTime="
				+ modifiedTime
				+ ", mediaServiceProvider="
				+ mediaServiceProvider
				+ ", mediaSpecification=" + mediaSpecification + "]";
	}

}