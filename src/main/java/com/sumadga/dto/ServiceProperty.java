package com.sumadga.dto;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigInteger;


/**
 * The persistent class for the service_properties database table.
 * 
 */
@Entity
@Table(name="service_properties")
public class ServiceProperty implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="service_property_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String servicePropertyId;

	private String name;

	@Column(name="service_id")
	private BigInteger serviceId;

	private String value;

	public ServiceProperty() {
	}

	public String getServicePropertyId() {
		return this.servicePropertyId;
	}

	public void setServicePropertyId(String servicePropertyId) {
		this.servicePropertyId = servicePropertyId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigInteger getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(BigInteger serviceId) {
		this.serviceId = serviceId;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}