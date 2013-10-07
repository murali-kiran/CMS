package com.sumadga.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the media_group_media database table.
 * 
 */
@Entity
@Table(name="media_group_media")
public class MediaGroupMedia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="media_group_media_id", unique=true, nullable=false)
	private int mediaGroupMediaId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time", nullable=false)
	private Date createdTime;

	@Column(name="media_order", nullable=false)
	private int mediaOrder;

	@Column(name="modified_time", nullable=false)
	private Timestamp modifiedTime;

	//bi-directional many-to-one association to Media
	@ManyToOne
	@JoinColumn(name="media_id", nullable=false)
	private Media media;

	//bi-directional many-to-one association to MediaGroup
	@ManyToOne
	@JoinColumn(name="media_group_id", nullable=false)
	private MediaGroup mediaGroup;

	public MediaGroupMedia() {
	}

	public int getMediaGroupMediaId() {
		return this.mediaGroupMediaId;
	}

	public void setMediaGroupMediaId(int mediaGroupMediaId) {
		this.mediaGroupMediaId = mediaGroupMediaId;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public int getMediaOrder() {
		return this.mediaOrder;
	}

	public void setMediaOrder(int mediaOrder) {
		this.mediaOrder = mediaOrder;
	}

	public Timestamp getModifiedTime() {
		return this.modifiedTime;
	}

	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public Media getMedia() {
		return this.media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public MediaGroup getMediaGroup() {
		return this.mediaGroup;
	}

	public void setMediaGroup(MediaGroup mediaGroup) {
		this.mediaGroup = mediaGroup;
	}

	@Override
	public String toString() {
		return "MediaGroupMedia [mediaGroupMediaId=" + mediaGroupMediaId
				+ ", createdTime=" + createdTime + ", mediaOrder=" + mediaOrder
				+ ", modifiedTime=" + modifiedTime + ", media=" + media
				+ ", mediaGroup=" + mediaGroup + "]";
	}

}