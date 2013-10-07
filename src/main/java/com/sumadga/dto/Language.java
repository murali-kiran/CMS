package com.sumadga.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the languages database table.
 * 
 */
@Entity
@Table(name="languages")
public class Language implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="language_id", unique=true, nullable=false)
	private int languageId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time", nullable=false)
	private Date createdTime;

	@Column(nullable=false, length=256)
	private String description;

	@Column(name="language_name", nullable=false, length=45)
	private String languageName;

	@Column(name="modified_time", nullable=false)
	private Timestamp modifiedTime;

	//bi-directional many-to-one association to Media
	@OneToMany(mappedBy="language")
	private List<Media> medias;

	public Language() {
	}

	public int getLanguageId() {
		return this.languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
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

	public String getLanguageName() {
		return this.languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
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
		media.setLanguage(this);

		return media;
	}

	public Media removeMedia(Media media) {
		getMedias().remove(media);
		media.setLanguage(null);

		return media;
	}

	@Override
	public String toString() {
		return "Language [languageId=" + languageId + ", createdTime="
				+ createdTime + ", description=" + description
				+ ", languageName=" + languageName + ", modifiedTime="
				+ modifiedTime + ", medias=" + medias + "]";
	}

	
	
}