package com.sumadga.dto;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the purchase_details database table.
 * 
 */
@Entity
@Table(name="purchase_details")

public class PurchaseDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="purchase_detail_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int purchaseDetailId;

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

	//bi-directional many-to-one association to Purchas
	@ManyToOne
	@JoinColumn(name="purchase_id")
	private Purchas purchas;

	public PurchaseDetail() {
	}

	public int getPurchaseDetailId() {
		return this.purchaseDetailId;
	}

	public void setPurchaseDetailId(int purchaseDetailId) {
		this.purchaseDetailId = purchaseDetailId;
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

	public Purchas getPurchas() {
		return this.purchas;
	}

	public void setPurchas(Purchas purchas) {
		this.purchas = purchas;
	}

}