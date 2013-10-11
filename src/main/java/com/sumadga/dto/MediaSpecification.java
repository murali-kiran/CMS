package com.sumadga.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the media_specifications database table.
 * 
 */
@Entity
@Table(name="media_specifications")
public class MediaSpecification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="media_specification_id", unique=true, nullable=false)
	private Integer mediaSpecificationId;

	@Column(nullable=false)
	private Integer duration;

	@Column(nullable=false)
	private Integer height;

	@Column(name="is_source", nullable=false)
	private Boolean isSource;

	@Column(name="media_location", nullable=false)
	private Boolean mediaLocation;

	@Column(name="media_specifications_name", nullable=false, length=45)
	private String mediaSpecificationsName;

	@Column(name="parent_specification_id", nullable=false)
	private Integer parentSpecificationId;

	@Column(nullable=false)
	private Integer width;
	
	@Column(nullable=false)
	private Integer bitrate=0;

	//bi-directional many-to-one association to MediaContent
	@OneToMany(mappedBy="mediaSpecification")
	private List<MediaContent> mediaContents;

	//bi-directional many-to-one association to MediaContentPurpos
	@ManyToOne
	@JoinColumn(name="media_content_purpose_id", nullable=false)
	private MediaContentPurpos mediaContentPurpos;

	//bi-directional many-to-one association to MimeType
	@ManyToOne
	@JoinColumn(name="mime_type_id", nullable=false)
	private MimeType mimeType;

	//bi-directional many-to-one association to MediaType
	@ManyToOne
	@JoinColumn(name="media_type_id", nullable=false)
	private MediaType mediaType;

	public MediaSpecification() {
	}

	public Integer getMediaSpecificationId() {
		return this.mediaSpecificationId;
	}

	public void setMediaSpecificationId(Integer mediaSpecificationId) {
		this.mediaSpecificationId = mediaSpecificationId;
	}

	public Integer getDuration() {
		return this.duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getHeight() {
		return this.height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Boolean getIsSource() {
		return this.isSource;
	}

	public void setIsSource(Boolean isSource) {
		this.isSource = isSource;
	}

	public Boolean getMediaLocation() {
		return this.mediaLocation;
	}

	public void setMediaLocation(Boolean mediaLocation) {
		this.mediaLocation = mediaLocation;
	}

	public String getMediaSpecificationsName() {
		return this.mediaSpecificationsName;
	}

	public void setMediaSpecificationsName(String mediaSpecificationsName) {
		this.mediaSpecificationsName = mediaSpecificationsName;
	}

	public Integer getParentSpecificationId() {
		return this.parentSpecificationId;
	}

	public void setParentSpecificationId(Integer parentSpecificationId) {
		this.parentSpecificationId = parentSpecificationId;
	}

	public Integer getWidth() {
		return this.width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public List<MediaContent> getMediaContents() {
		return this.mediaContents;
	}

	public void setMediaContents(List<MediaContent> mediaContents) {
		this.mediaContents = mediaContents;
	}

	public MediaContent addMediaContent(MediaContent mediaContent) {
		getMediaContents().add(mediaContent);
		mediaContent.setMediaSpecification(this);

		return mediaContent;
	}

	public MediaContent removeMediaContent(MediaContent mediaContent) {
		getMediaContents().remove(mediaContent);
		mediaContent.setMediaSpecification(null);

		return mediaContent;
	}


	public MediaContentPurpos getMediaContentPurpos() {
		return this.mediaContentPurpos;
	}

	public void setMediaContentPurpos(MediaContentPurpos mediaContentPurpos) {
		this.mediaContentPurpos = mediaContentPurpos;
	}

	public MimeType getMimeType() {
		return this.mimeType;
	}

	public void setMimeType(MimeType mimeType) {
		this.mimeType = mimeType;
	}

	public MediaType getMediaType() {
		return this.mediaType;
	}

	public void setMediaType(MediaType mediaType) {
		this.mediaType = mediaType;
	}

	public Integer getBitrate() {
		return bitrate;
	}

	public void setBitrate(Integer bitrate) {
		this.bitrate = bitrate;
	}


	@Override
	public String toString() {
		return "MediaSpecification [mediaSpecificationId="
				+ mediaSpecificationId + ", duration=" + duration + ", height="
				+ height + ", isSource=" + isSource + ", mediaLocation="
				+ mediaLocation + ", mediaSpecificationsName="
				+ mediaSpecificationsName + ", parentSpecificationId="
				+ parentSpecificationId + ", width=" + width
				+ ", mediaContents=" + mediaContents
				+ ", mediaServiceOwnerSpecfications="
				+ mediaContentPurpos + ", mimeType=" + mimeType
				+ ", mediaType=" + mediaType 
				+ ", bitrate=" +bitrate + "]";
	}

}