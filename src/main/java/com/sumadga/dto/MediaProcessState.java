package com.sumadga.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the media_process_states database table.
 * 
 */
@Entity
@Table(name="media_process_states")
public class MediaProcessState implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="media_process_state_id", unique=true, nullable=false)
	private int mediaProcessStateId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time", nullable=false)
	private Date createdTime;

	@Column(nullable=false, length=100)
	private String description;

	@Column(name="media_process_state_name", nullable=false, length=45)
	private String mediaProcessStateName;

	@Column(name="modified_time", nullable=false)
	private Timestamp modifiedTime;

	//bi-directional many-to-one association to Media
	@OneToMany(mappedBy="mediaProcessState")
	private List<Media> medias;

	public MediaProcessState() {
	}

	public int getMediaProcessStateId() {
		return this.mediaProcessStateId;
	}

	public void setMediaProcessStateId(int mediaProcessStateId) {
		this.mediaProcessStateId = mediaProcessStateId;
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

	public String getMediaProcessStateName() {
		return this.mediaProcessStateName;
	}

	public void setMediaProcessStateName(String mediaProcessStateName) {
		this.mediaProcessStateName = mediaProcessStateName;
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
		media.setMediaProcessState(this);

		return media;
	}

	public Media removeMedia(Media media) {
		getMedias().remove(media);
		media.setMediaProcessState(null);

		return media;
	}

	@Override
	public String toString() {
		return "MediaProcessState [mediaProcessStateId=" + mediaProcessStateId
				+ ", createdTime=" + createdTime + ", description="
				+ description + ", mediaProcessStateName="
				+ mediaProcessStateName + ", modifiedTime=" + modifiedTime
				+ ", medias=" + medias + "]";
	}

}