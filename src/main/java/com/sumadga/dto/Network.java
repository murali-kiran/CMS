package com.sumadga.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the languages database table.
 * 
 */
@Entity
@Table(name="networks")
public class Network implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="network_id", unique=true, nullable=false)
	private Integer networkId;

	@Column(name="name", nullable=false, length=45)
	private String name;
	
	@Column(name="title", nullable=false, length=45)
	private String title;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time", nullable=false)
	private Date createdTime;
	
	@Column(name="modified_time", nullable=false)
	private Timestamp modifiedTime;


	public Integer getNetworkId() {
		return networkId;
	}


	public void setNetworkId(Integer networkId) {
		this.networkId = networkId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Date getCreatedTime() {
		return createdTime;
	}


	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}


	public Timestamp getModifiedTime() {
		return modifiedTime;
	}


	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}


	@Override
	public String toString() {
		return "Network [networkId=" + networkId + ", createdTime="
				+ createdTime + ", title=" + title
				+ ", name=" + name + ", modifiedTime="
				+ modifiedTime + "]";
	}

	
	
}