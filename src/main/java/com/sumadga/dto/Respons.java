package com.sumadga.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the responses database table.
 * 
 */
@Entity
@Table(name="responses")
public class Respons implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="response_id", unique=true, nullable=false)
	private Long responseId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time")
	private Date createdTime;

	@Column(name="modified_time")
	private Timestamp modifiedTime;

	private Long msisdn;

	@Column(name="queryString")
	private String queryString;

	@Column(name="request_id")
	private Long requestId;

	public Respons() {
	}

	public Long getResponseId() {
		return this.responseId;
	}

	public void setResponseId(Long responseId) {
		this.responseId = responseId;
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

	public Long getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(Long msisdn) {
		this.msisdn = msisdn;
	}

	public String getQueryString() {
		return this.queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public Long getRequestId() {
		return this.requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

}