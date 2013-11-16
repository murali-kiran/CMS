package com.sumadga.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the media database table.
 * 
 */
@Entity
@Table(name="os")
public class Os implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="os_id", unique=true, nullable=false)
	private Integer osId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time", nullable=false)
	private Date createdTime;

	@Column(name="description",nullable=true, length=256)
	private String description;

	@Column(name="os_name", nullable=false, length=100)
	private String osName;

	@Column(name="os_title", nullable=false, length=75)
	private String osTitle;

	@Column(name="modified_time", nullable=false)
	private Timestamp modifiedTime;
	
	

	public Os() {
	}



	public Integer getOsId() {
		return osId;
	}



	public void setOsId(Integer osId) {
		this.osId = osId;
	}



	public Date getCreatedTime() {
		return createdTime;
	}



	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getOsName() {
		return osName;
	}



	public void setOsName(String osName) {
		this.osName = osName;
	}



	public String getOsTitle() {
		return osTitle;
	}



	public void setOsTitle(String osTitle) {
		this.osTitle = osTitle;
	}



	public Timestamp getModifiedTime() {
		return modifiedTime;
	}



	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}



}