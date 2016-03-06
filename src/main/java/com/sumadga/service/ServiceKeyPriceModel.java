package com.sumadga.service;

public class ServiceKeyPriceModel {

	/**
	 * @param args
	 */
	private int serviceKeyPriceId;
	private int serviceKeyId;
	private String serviceKeyPriceName;
	private int serviceKeyPriceType;
	private int price;
	private int duration;
	private int tokens;
	
	
	public int getServiceKeyId() {
		return serviceKeyId;
	}
	public void setServiceKeyId(int serviceKeyId) {
		this.serviceKeyId = serviceKeyId;
	}
	public int getServiceKeyPriceId() {
		return serviceKeyPriceId;
	}
	public void setServiceKeyPriceId(int serviceKeyPriceId) {
		this.serviceKeyPriceId = serviceKeyPriceId;
	}
	public String getServiceKeyPriceName() {
		return serviceKeyPriceName;
	}
	public void setServiceKeyPriceName(String serviceKeyPriceName) {
		this.serviceKeyPriceName = serviceKeyPriceName;
	}
	public int getServiceKeyPriceType() {
		return serviceKeyPriceType;
	}
	public void setServiceKeyPriceType(int serviceKeyPriceType) {
		this.serviceKeyPriceType = serviceKeyPriceType;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getTokens() {
		return tokens;
	}
	public void setTokens(int tokens) {
		this.tokens = tokens;
	}
	

	
}
