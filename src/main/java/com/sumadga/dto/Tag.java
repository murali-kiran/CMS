package com.sumadga.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tags database table.
 * 
 */
@Entity
@Table(name="tags")
public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="tag_id", unique=true, nullable=false)
	private Integer tagId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time", nullable=false)
	private Date createdTime;

	@Column(name="modified_time", nullable=false)
	private Timestamp modifiedTime;

	@Column(name="tag_name", nullable=false, length=45)
	private String tagName;

	//bi-directional many-to-one association to MediaTag
	@OneToMany(mappedBy="tag")
	private List<MediaTag> mediaTags;

	public Tag() {
	}

	public Integer getTagId() {
		return this.tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
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

	public String getTagName() {
		return this.tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public List<MediaTag> getMediaTags() {
		return this.mediaTags;
	}

	public void setMediaTags(List<MediaTag> mediaTags) {
		this.mediaTags = mediaTags;
	}

	public MediaTag addMediaTag(MediaTag mediaTag) {
		getMediaTags().add(mediaTag);
		mediaTag.setTag(this);

		return mediaTag;
	}

	public MediaTag removeMediaTag(MediaTag mediaTag) {
		getMediaTags().remove(mediaTag);
		mediaTag.setTag(null);

		return mediaTag;
	}

	@Override
	public String toString() {
		return "Tag [tagId=" + tagId + ", createdTime=" + createdTime
				+ ", modifiedTime=" + modifiedTime + ", tagName=" + tagName
				+ ", mediaTags=" + mediaTags + "]";
	}

}