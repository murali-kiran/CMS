package com.sumadga.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the service_media_groups database table.
 * 
 */
@Entity
@Table(name="service_media_groups")
public class ServiceMediaGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="service_media_group_id")
	private int serviceMediaGroupId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time")
	private Date createdTime;

	@Column(name="is_one_time_charge")
	private byte isOneTimeCharge;

	@Column(name="media_group_id")
	private int mediaGroupId;

	@Column(name="modified_time")
	private Timestamp modifiedTime;

	@Column(name="service_key_id")
	private int serviceKeyId;

	//bi-directional many-to-one association to Service
	@ManyToOne
	@JoinColumn(name="service_id")
	private Service service;

	public ServiceMediaGroup() {
	}

	public int getServiceMediaGroupId() {
		return this.serviceMediaGroupId;
	}

	public void setServiceMediaGroupId(int serviceMediaGroupId) {
		this.serviceMediaGroupId = serviceMediaGroupId;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public byte getIsOneTimeCharge() {
		return this.isOneTimeCharge;
	}

	public void setIsOneTimeCharge(byte isOneTimeCharge) {
		this.isOneTimeCharge = isOneTimeCharge;
	}

	public int getMediaGroupId() {
		return this.mediaGroupId;
	}

	public void setMediaGroupId(int mediaGroupId) {
		this.mediaGroupId = mediaGroupId;
	}

	public Timestamp getModifiedTime() {
		return this.modifiedTime;
	}

	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public int getServiceKeyId() {
		return this.serviceKeyId;
	}

	public void setServiceKeyId(int serviceKeyId) {
		this.serviceKeyId = serviceKeyId;
	}

	public Service getService() {
		return this.service;
	}

	public void setService(Service service) {
		this.service = service;
	}

}