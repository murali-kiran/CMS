package com.sumadga.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the purchases database table.
 * 
 */
@Entity
@Table(name="purchases")
//@NamedQuery(name="Purchas.findAll", query="SELECT p FROM Purchas p")
public class Purchases implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="purchase_id")
	private int purchaseId;

	@Column(name="circle_id")
	private int circleId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="expiry_time")
	private Date expiryTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="first_purchase_time")
	private Date firstPurchaseTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_purchase_time")
	private Date lastPurchaseTime;

	@Column(name="modified_time")
	private Timestamp modifiedTime;

	private BigInteger msisdn;

	@Column(name="network_id")
	private int networkId;

	@Column(name="purchase_status")
	private byte purchaseStatus;

	@Column(name="purchase_type")
	private byte purchaseType;

	@Column(name="service_key_id")
	private int serviceKeyId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="stoped_time")
	private Date stopedTime;

	//bi-directional many-to-one association to MediaDownload
	@OneToMany(mappedBy="purchas")
	private List<MediaDownload> mediaDownloads;

	//bi-directional many-to-one association to PurchaseDetail
	@OneToMany(mappedBy="purchas")
	private List<PurchaseDetail> purchaseDetails;

	public Purchases() {
	}

	public int getPurchaseId() {
		return this.purchaseId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}

	public int getCircleId() {
		return this.circleId;
	}

	public void setCircleId(int circleId) {
		this.circleId = circleId;
	}

	public Date getExpiryTime() {
		return this.expiryTime;
	}

	public void setExpiryTime(Date expiryTime) {
		this.expiryTime = expiryTime;
	}

	public Date getFirstPurchaseTime() {
		return this.firstPurchaseTime;
	}

	public void setFirstPurchaseTime(Date firstPurchaseTime) {
		this.firstPurchaseTime = firstPurchaseTime;
	}

	public Date getLastPurchaseTime() {
		return this.lastPurchaseTime;
	}

	public void setLastPurchaseTime(Date lastPurchaseTime) {
		this.lastPurchaseTime = lastPurchaseTime;
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

	public int getNetworkId() {
		return this.networkId;
	}

	public void setNetworkId(int networkId) {
		this.networkId = networkId;
	}

	public byte getPurchaseStatus() {
		return this.purchaseStatus;
	}

	public void setPurchaseStatus(byte purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}

	public byte getPurchaseType() {
		return this.purchaseType;
	}

	public void setPurchaseType(byte purchaseType) {
		this.purchaseType = purchaseType;
	}

	public int getServiceKeyId() {
		return this.serviceKeyId;
	}

	public void setServiceKeyId(int serviceKeyId) {
		this.serviceKeyId = serviceKeyId;
	}

	public Date getStopedTime() {
		return this.stopedTime;
	}

	public void setStopedTime(Date stopedTime) {
		this.stopedTime = stopedTime;
	}

	public List<MediaDownload> getMediaDownloads() {
		return this.mediaDownloads;
	}

	public void setMediaDownloads(List<MediaDownload> mediaDownloads) {
		this.mediaDownloads = mediaDownloads;
	}

	public MediaDownload addMediaDownload(MediaDownload mediaDownload) {
		getMediaDownloads().add(mediaDownload);
		mediaDownload.setPurchas(this);

		return mediaDownload;
	}

	public MediaDownload removeMediaDownload(MediaDownload mediaDownload) {
		getMediaDownloads().remove(mediaDownload);
		mediaDownload.setPurchas(null);

		return mediaDownload;
	}

	public List<PurchaseDetail> getPurchaseDetails() {
		return this.purchaseDetails;
	}

	public void setPurchaseDetails(List<PurchaseDetail> purchaseDetails) {
		this.purchaseDetails = purchaseDetails;
	}

	public PurchaseDetail addPurchaseDetail(PurchaseDetail purchaseDetail) {
		getPurchaseDetails().add(purchaseDetail);
		purchaseDetail.setPurchas(this);

		return purchaseDetail;
	}

	public PurchaseDetail removePurchaseDetail(PurchaseDetail purchaseDetail) {
		getPurchaseDetails().remove(purchaseDetail);
		purchaseDetail.setPurchas(null);

		return purchaseDetail;
	}

}