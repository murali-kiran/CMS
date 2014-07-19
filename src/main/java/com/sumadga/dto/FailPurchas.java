package com.sumadga.dto;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the fail_purchases database table.
 * 
 */
@Entity
@Table(name="fail_purchases")
public class FailPurchas implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="fail_purchase_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int failPurchaseId;

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
	
	private String identifier;

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

	/*//bi-directional many-to-one association to FailPurchaseDetail
	@OneToMany(mappedBy="failPurchas")
	private List<FailPurchaseDetail> failPurchaseDetails;*/

	public FailPurchas() {
	}

	public int getFailPurchaseId() {
		return this.failPurchaseId;
	}

	public void setFailPurchaseId(int failPurchaseId) {
		this.failPurchaseId = failPurchaseId;
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
/*
	public List<FailPurchaseDetail> getFailPurchaseDetails() {
		return this.failPurchaseDetails;
	}

	public void setFailPurchaseDetails(List<FailPurchaseDetail> failPurchaseDetails) {
		this.failPurchaseDetails = failPurchaseDetails;
	}*/
/*
	public FailPurchaseDetail addFailPurchaseDetail(FailPurchaseDetail failPurchaseDetail) {
		getFailPurchaseDetails().add(failPurchaseDetail);
		failPurchaseDetail.setFailPurchas(this);

		return failPurchaseDetail;
	}

	public FailPurchaseDetail removeFailPurchaseDetail(FailPurchaseDetail failPurchaseDetail) {
		getFailPurchaseDetails().remove(failPurchaseDetail);
		failPurchaseDetail.setFailPurchas(null);

		return failPurchaseDetail;
	}*/

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

}