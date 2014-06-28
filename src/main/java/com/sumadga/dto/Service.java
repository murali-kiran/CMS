package com.sumadga.dto;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the services database table.
 * 
 */
@Entity
@Table(name="services")
public class Service implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="service_id")
	private int serviceId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time")
	private Date createdTime;

	@Column(name="modified_time")
	private Timestamp modifiedTime;

	@Column(name="service_description")
	private String serviceDescription;

	@Column(name="service_name")
	private String serviceName;

	@Column(name="service_title")
	private String serviceTitle;
	
	@ManyToOne
	@Column(name="network_id")
	private Network network;
	
	
	@Column(name="service_group")
	private Integer serviceGroup;
	

	//bi-directional many-to-one association to ServiceMediaGroup
	/*@OneToMany(mappedBy="service")
	private List<ServiceMediaGroup> serviceMediaGroups;*/

	public Service() {
	}

	public int getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Timestamp getModifiedTime() {
		return this.modifiedTime;
	}

	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getServiceDescription() {
		return this.serviceDescription;
	}

	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}

	public String getServiceName() {
		return this.serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceTitle() {
		return this.serviceTitle;
	}

	public void setServiceTitle(String serviceTitle) {
		this.serviceTitle = serviceTitle;
	}

	public Network getNetwork() {
		return network;
	}

	public void setNetwork(Network network) {
		this.network = network;
	}

	public Integer getServiceGroup() {
		return serviceGroup;
	}

	public void setServiceGroup(Integer serviceGroup) {
		this.serviceGroup = serviceGroup;
	}

	/*public List<ServiceMediaGroup> getServiceMediaGroups() {
		return this.serviceMediaGroups;
	}

	public void setServiceMediaGroups(List<ServiceMediaGroup> serviceMediaGroups) {
		this.serviceMediaGroups = serviceMediaGroups;
	}

	public ServiceMediaGroup addServiceMediaGroup(ServiceMediaGroup serviceMediaGroup) {
		getServiceMediaGroups().add(serviceMediaGroup);
		serviceMediaGroup.setService(this);

		return serviceMediaGroup;
	}

	public ServiceMediaGroup removeServiceMediaGroup(ServiceMediaGroup serviceMediaGroup) {
		getServiceMediaGroups().remove(serviceMediaGroup);
		serviceMediaGroup.setService(null);

		return serviceMediaGroup;
	}*/

}