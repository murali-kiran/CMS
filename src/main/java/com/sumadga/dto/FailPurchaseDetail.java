package com.sumadga.dto;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the fail_purchase_details database table.
 * 
 */
@Entity
@Table(name="fail_purchase_details")
public class FailPurchaseDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="fail_purchase_detail_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int failPurchaseDetailId;

	private double amount;

	@Column(name="modified_time")
	private Timestamp modifiedTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="purchase_time")
	private Date purchaseTime;

	private String remarks;

	@Column(name="service_key_price_id")
	private int serviceKeyPriceId;

	private byte status;
	
	private String channel;

	//bi-directional many-to-one association to FailPurchas
	@ManyToOne
	@JoinColumn(name="fail_purchase_id")
	private FailPurchas failPurchas;

	public FailPurchaseDetail() {
	}

	public int getFailPurchaseDetailId() {
		return this.failPurchaseDetailId;
	}

	public void setFailPurchaseDetailId(int failPurchaseDetailId) {
		this.failPurchaseDetailId = failPurchaseDetailId;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getModifiedTime() {
		return this.modifiedTime;
	}

	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public Date getPurchaseTime() {
		return this.purchaseTime;
	}

	public void setPurchaseTime(Date purchaseTime) {
		this.purchaseTime = purchaseTime;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getServiceKeyPriceId() {
		return this.serviceKeyPriceId;
	}

	public void setServiceKeyPriceId(int serviceKeyPriceId) {
		this.serviceKeyPriceId = serviceKeyPriceId;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public FailPurchas getFailPurchas() {
		return this.failPurchas;
	}

	public void setFailPurchas(FailPurchas failPurchas) {
		this.failPurchas = failPurchas;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

}