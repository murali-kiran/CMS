package com.sumadga.dto;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the service_keys database table.
 * 
 */
@Entity
@Table(name="service_keys")
public class ServiceKey implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="service_key_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int serviceKeyId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time")
	private Date createdTime;

	private String desciption;

	@Column(name="modified_time")
	private Timestamp modifiedTime;

	private String name;

	@Column(name="service_key")
	private String serviceKey;

	private String title;

	//bi-directional many-to-one association to ServiceKeyPrice
	@OneToMany(mappedBy="serviceKey")
	private List<ServiceKeyPrice> serviceKeyPrices;

	public ServiceKey() {
	}

	public int getServiceKeyId() {
		return this.serviceKeyId;
	}

	public void setServiceKeyId(int serviceKeyId) {
		this.serviceKeyId = serviceKeyId;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getDesciption() {
		return this.desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public Timestamp getModifiedTime() {
		return this.modifiedTime;
	}

	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getServiceKey() {
		return this.serviceKey;
	}

	public void setServiceKey(String serviceKey) {
		this.serviceKey = serviceKey;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<ServiceKeyPrice> getServiceKeyPrices() {
		return this.serviceKeyPrices;
	}

	public void setServiceKeyPrices(List<ServiceKeyPrice> serviceKeyPrices) {
		this.serviceKeyPrices = serviceKeyPrices;
	}

	public ServiceKeyPrice addServiceKeyPrice(ServiceKeyPrice serviceKeyPrice) {
		getServiceKeyPrices().add(serviceKeyPrice);
		serviceKeyPrice.setServiceKey(this);

		return serviceKeyPrice;
	}

	public ServiceKeyPrice removeServiceKeyPrice(ServiceKeyPrice serviceKeyPrice) {
		getServiceKeyPrices().remove(serviceKeyPrice);
		serviceKeyPrice.setServiceKey(null);

		return serviceKeyPrice;
	}

}