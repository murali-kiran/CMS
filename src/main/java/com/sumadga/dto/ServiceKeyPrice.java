package com.sumadga.dto;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the service_key_prices database table.
 * 
 */
@Entity
@Table(name="service_key_prices")
public class ServiceKeyPrice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="service_key_price_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int serviceKeyPriceId;

	private int duration;

	private double price;

	@Column(name="service_key_price_key")
	private String serviceKeyPriceKey;

	@Column(name="service_key_price_type")
	private byte serviceKeyPriceType;

	private int tokens;

	//bi-directional many-to-one association to ServiceKey
	@ManyToOne
	@JoinColumn(name="service_key_id")
	private ServiceKey serviceKey;

	public ServiceKeyPrice() {
	}

	public int getServiceKeyPriceId() {
		return this.serviceKeyPriceId;
	}

	public void setServiceKeyPriceId(int serviceKeyPriceId) {
		this.serviceKeyPriceId = serviceKeyPriceId;
	}

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getServiceKeyPriceKey() {
		return this.serviceKeyPriceKey;
	}

	public void setServiceKeyPriceKey(String serviceKeyPriceKey) {
		this.serviceKeyPriceKey = serviceKeyPriceKey;
	}

	public byte getServiceKeyPriceType() {
		return this.serviceKeyPriceType;
	}

	public void setServiceKeyPriceType(byte serviceKeyPriceType) {
		this.serviceKeyPriceType = serviceKeyPriceType;
	}

	public int getTokens() {
		return this.tokens;
	}

	public void setTokens(int tokens) {
		this.tokens = tokens;
	}

	public ServiceKey getServiceKey() {
		return this.serviceKey;
	}

	public void setServiceKey(ServiceKey serviceKey) {
		this.serviceKey = serviceKey;
	}

}