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
	private int mediaSpecificationId;

	@Column(nullable=false)
	private int duration;

	@Column(nullable=false)
	private int height;

	@Column(name="is_source", nullable=false)
	private byte isSource;

	@Column(name="media_location", nullable=false)
	private byte mediaLocation;

	@Column(name="media_specifications_name", nullable=false, length=45)
	private String mediaSpecificationsName;

	@Column(name="parent_specification_id", nullable=false)
	private int parentSpecificationId;

	@Column(nullable=false)
	private int width;

	//bi-directional many-to-one association to MediaContent
	@OneToMany(mappedBy="mediaSpecification")
	private List<MediaContent> mediaContents;

	//bi-directional many-to-one association to MediaServiceOwnerSpecfication
	@OneToMany(mappedBy="mediaSpecification")
	private List<MediaServiceOwnerSpecfication> mediaServiceOwnerSpecfications;

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

	public int getMediaSpecificationId() {
		return this.mediaSpecificationId;
	}

	public void setMediaSpecificationId(int mediaSpecificationId) {
		this.mediaSpecificationId = mediaSpecificationId;
	}

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public byte getIsSource() {
		return this.isSource;
	}

	public void setIsSource(byte isSource) {
		this.isSource = isSource;
	}

	public byte getMediaLocation() {
		return this.mediaLocation;
	}

	public void setMediaLocation(byte mediaLocation) {
		this.mediaLocation = mediaLocation;
	}

	public String getMediaSpecificationsName() {
		return this.mediaSpecificationsName;
	}

	public void setMediaSpecificationsName(String mediaSpecificationsName) {
		this.mediaSpecificationsName = mediaSpecificationsName;
	}

	public int getParentSpecificationId() {
		return this.parentSpecificationId;
	}

	public void setParentSpecificationId(int parentSpecificationId) {
		this.parentSpecificationId = parentSpecificationId;
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
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

	public List<MediaServiceOwnerSpecfication> getMediaServiceOwnerSpecfications() {
		return this.mediaServiceOwnerSpecfications;
	}

	public void setMediaServiceOwnerSpecfications(List<MediaServiceOwnerSpecfication> mediaServiceOwnerSpecfications) {
		this.mediaServiceOwnerSpecfications = mediaServiceOwnerSpecfications;
	}

	public MediaServiceOwnerSpecfication addMediaServiceOwnerSpecfication(MediaServiceOwnerSpecfication mediaServiceOwnerSpecfication) {
		getMediaServiceOwnerSpecfications().add(mediaServiceOwnerSpecfication);
		mediaServiceOwnerSpecfication.setMediaSpecification(this);

		return mediaServiceOwnerSpecfication;
	}

	public MediaServiceOwnerSpecfication removeMediaServiceOwnerSpecfication(MediaServiceOwnerSpecfication mediaServiceOwnerSpecfication) {
		getMediaServiceOwnerSpecfications().remove(mediaServiceOwnerSpecfication);
		mediaServiceOwnerSpecfication.setMediaSpecification(null);

		return mediaServiceOwnerSpecfication;
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
				+ mediaServiceOwnerSpecfications + ", mediaContentPurpos="
				+ mediaContentPurpos + ", mimeType=" + mimeType
				+ ", mediaType=" + mediaType + "]";
	}

}