package com.sumadga.mis;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RevenueView")
public class MisModel {
	private  Integer mediaProviderId;
	private String operator;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String content;
	private Integer downloads;
	private Double revenue;
	private String purchaseTime;
	public Integer getMediaProviderId() {
		return mediaProviderId;
	}
	public void setMediaProviderId(Integer mediaProviderId) {
		this.mediaProviderId = mediaProviderId;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getDownloads() {
		return downloads;
	}
	public void setDownloads(Integer downloads) {
		this.downloads = downloads;
	}
	public Double getRevenue() {
		return revenue;
	}
	public void setRevenue(Double revenue) {
		this.revenue = revenue;
	}
	public String getPurchaseTime() {
		return purchaseTime;
	}
	public void setPurchaseTime(String purchaseTime) {
		this.purchaseTime = purchaseTime;
	}
	
	
}
