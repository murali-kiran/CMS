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
@Table(name="media")
public class Os implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="os_id", unique=true, nullable=false)
	private Integer mediaId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time", nullable=false)
	private Date createdTime;

	@Column(nullable=false, length=256)
	private String description;

	@Column(name="os_name", nullable=false, length=100)
	private String mediaName;

	@Column(name="os_title", nullable=false, length=75)
	private String mediaTitle;

	@Column(name="modified_time", nullable=false)
	private Timestamp modifiedTime;
	
	

	public Os() {
	}



}