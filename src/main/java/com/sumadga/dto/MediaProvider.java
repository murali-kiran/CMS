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
	private Integer mediaProviderId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time", nullable=false)
	private Date createdTime;

	@Column(name="media_provider_name", nullable=false, length=45)
	private String mediaProviderName;


	@Column(name="modified_time", nullable=false)
	private Timestamp modifiedTime;

	public MediaProvider() {
	}

	public Integer getMediaProviderId() {
		return this.mediaProviderId;
	}

	public void setMediaProviderId(Integer mediaProviderId) {
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

	@Override
	public String toString() {
		return "MediaProvider [mediaProviderId=" + mediaProviderId
				+ ", createdTime=" + createdTime + ", mediaProviderName="
				+ mediaProviderName + ", modifiedTime=" + modifiedTime + "]";
	}


	

}