package com.sumadga.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the capture_wap_details database table.
 * 
 */
@Entity
@Table(name="capture_wap_details")
@NamedQuery(name="CaptureWapDetail.findAll", query="SELECT c FROM CaptureWapDetail c")
public class CaptureWapDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String channel;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time")
	private Date createdTime;

	private String host;

	@Column(name="modified_time")
	private Timestamp modifiedTime;

	private BigInteger msisdn;

	@Column(name="other_details")
	private String otherDetails;

	@Column(name="query_string")
	private String queryString;

	@Column(name="referal_url")
	private String referalUrl;

	@Column(name="request_url")
	private String requestUrl;

	@Column(name="session_id")
	private String sessionId;

	@Column(name="user_agent")
	private String userAgent;

	public CaptureWapDetail() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Timestamp getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public BigInteger getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(BigInteger msisdn) {
		this.msisdn = msisdn;
	}

	public String getOtherDetails() {
		return this.otherDetails;
	}

	public void setOtherDetails(String otherDetails) {
		this.otherDetails = otherDetails;
	}

	public String getQueryString() {
		return this.queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public String getReferalUrl() {
		return this.referalUrl;
	}

	public void setReferalUrl(String referalUrl) {
		this.referalUrl = referalUrl;
	}

	public String getRequestUrl() {
		return this.requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUserAgent() {
		return this.userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

}