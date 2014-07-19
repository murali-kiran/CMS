package com.sumadga.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the media_downloads database table.
 * 
 */
@Entity
@Table(name="media_downloads")

public class MediaDownload implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="media_download_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int mediaDownloadId;

	private String channel;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="download_time")
	private Date downloadTime;

	@Column(name="is_download_success")
	private byte isDownloadSuccess;

	@Column(name="media_content_id")
	private int mediaContentId;

	@Column(name="media_id")
	private int mediaId;

	@Column(name="modified_time")
	private Timestamp modifiedTime;

	private BigInteger msisdn;
	private String identifier;

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	private String remarks;

	@Column(name="service_id")
	private int serviceId;

	private String sessionId;

	@Column(name="user_agent")
	private String userAgent;

	//bi-directional many-to-one association to Purchas
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="purchase_id")
	private Purchas purchas;

	public MediaDownload() {
	}

	public int getMediaDownloadId() {
		return this.mediaDownloadId;
	}

	public void setMediaDownloadId(int mediaDownloadId) {
		this.mediaDownloadId = mediaDownloadId;
	}

	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Date getDownloadTime() {
		return this.downloadTime;
	}

	public void setDownloadTime(Date downloadTime) {
		this.downloadTime = downloadTime;
	}

	public byte getIsDownloadSuccess() {
		return this.isDownloadSuccess;
	}

	public void setIsDownloadSuccess(byte isDownloadSuccess) {
		this.isDownloadSuccess = isDownloadSuccess;
	}

	public int getMediaContentId() {
		return this.mediaContentId;
	}

	public void setMediaContentId(int mediaContentId) {
		this.mediaContentId = mediaContentId;
	}

	public int getMediaId() {
		return this.mediaId;
	}

	public void setMediaId(int mediaId) {
		this.mediaId = mediaId;
	}

	public Timestamp getModifiedTime() {
		return this.modifiedTime;
	}

	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public BigInteger getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(BigInteger msisdn) {
		this.msisdn = msisdn;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUserAgent() {
		return this.userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public Purchas getPurchas() {
		return this.purchas;
	}

	public void setPurchas(Purchas purchas) {
		this.purchas = purchas;
	}

}