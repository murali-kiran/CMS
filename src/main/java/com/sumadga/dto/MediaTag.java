package com.sumadga.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the media_tags database table.
 * 
 */
@Entity
@Table(name="media_tags")
public class MediaTag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="media_tag_id", unique=true, nullable=false)
	private Integer mediaTagId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time", nullable=false)
	private Date createdTime;

	@Column(name="modified_time", nullable=false)
	private Timestamp modifiedTime;

	//bi-directional many-to-one association to Media
	@ManyToOne
	@JoinColumn(name="media_id", nullable=false)
	private Media media;

	//bi-directional many-to-one association to Tag
	@ManyToOne
	@JoinColumn(name="tag_id", nullable=false)
	private Tag tag;

	public MediaTag() {
	}

	public Integer getMediaTagId() {
		return this.mediaTagId;
	}

	public void setMediaTagId(Integer mediaTagId) {
		this.mediaTagId = mediaTagId;
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

	public Media getMedia() {
		return this.media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public Tag getTag() {
		return this.tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return "MediaTag [mediaTagId=" + mediaTagId + ", createdTime="
				+ createdTime + ", modifiedTime=" + modifiedTime + ", media="
				+ media + ", tag=" + tag + "]";
	}

}