package com.sumadga.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the media_cycles database table.
 * 
 */
@Entity
@Table(name="media_cycles")
public class MediaCycle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="media_cycle_id", unique=true, nullable=false)
	private int mediaCycleId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time", nullable=false)
	private Date createdTime;

	@Column(name="media_cycle_state", nullable=false, length=45)
	private String mediaCycleState;

	@Column(name="modified_time", nullable=false)
	private Timestamp modifiedTime;

	//bi-directional many-to-one association to Media
	@OneToMany(mappedBy="mediaCycle")
	private List<Media> medias;

	public MediaCycle() {
	}

	public int getMediaCycleId() {
		return this.mediaCycleId;
	}

	public void setMediaCycleId(int mediaCycleId) {
		this.mediaCycleId = mediaCycleId;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getMediaCycleState() {
		return this.mediaCycleState;
	}

	public void setMediaCycleState(String mediaCycleState) {
		this.mediaCycleState = mediaCycleState;
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
		media.setMediaCycle(this);

		return media;
	}

	public Media removeMedia(Media media) {
		getMedias().remove(media);
		media.setMediaCycle(null);

		return media;
	}

	@Override
	public String toString() {
		return "MediaCycle [mediaCycleId=" + mediaCycleId + ", createdTime="
				+ createdTime + ", mediaCycleState=" + mediaCycleState
				+ ", modifiedTime=" + modifiedTime + ", medias=" + medias + "]";
	}

}