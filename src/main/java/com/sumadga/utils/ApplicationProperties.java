package com.sumadga.utils;
public class ApplicationProperties {
	private String mediaAbsolutePath;
	private String mediaCompletePath;
	private Integer maxSubFolders;
	private Boolean isVideoTranscodingEnabled;
	private String videoTranscodingScriptPath;
	private String qubeCellURL;
	private String username;
	private String password;
	private String msisdnReturnURL;
	private String eventChargeReturnURL;
	private String secretKey;
	
	
	public String getQubeCellURL() {
		return qubeCellURL;
	}
	public void setQubeCellURL(String qubeCellURL) {
		this.qubeCellURL = qubeCellURL;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMsisdnReturnURL() {
		return msisdnReturnURL;
	}
	public void setMsisdnReturnURL(String msisdnReturnURL) {
		this.msisdnReturnURL = msisdnReturnURL;
	}
	public String getEventChargeReturnURL() {
		return eventChargeReturnURL;
	}
	public void setEventChargeReturnURL(String eventChargeReturnURL) {
		this.eventChargeReturnURL = eventChargeReturnURL;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public String getMediaAbsolutePath() {
		return mediaAbsolutePath;
	}
	public void setMediaAbsolutePath(String mediaAbsolutePath) {
		this.mediaAbsolutePath = mediaAbsolutePath;
	}
	public String getMediaCompletePath() {
		return mediaCompletePath;
	}
	public void setMediaCompletePath(String mediaCompletePath) {
		this.mediaCompletePath = mediaCompletePath;
	}
	public Integer getMaxSubFolders() {
		return maxSubFolders;
	}
	public void setMaxSubFolders(Integer maxSubFolders) {
		this.maxSubFolders = maxSubFolders;
	}
	
	public Boolean getIsVideoTranscodingEnabled() {
		return isVideoTranscodingEnabled;
	}
	public void setIsVideoTranscodingEnabled(Boolean isVideoTranscodingEnabled) {
		this.isVideoTranscodingEnabled = isVideoTranscodingEnabled;
	}
	public String getVideoTranscodingScriptPath() {
		return videoTranscodingScriptPath;
	}
	public void setVideoTranscodingScriptPath(String videoTranscodingScriptPath) {
		this.videoTranscodingScriptPath = videoTranscodingScriptPath;
	}

}
