package com.sumadga.utils;

public class ApplicationProperties {
	private String mediaAbsolutePath;
	private String mediaCompletePath;
	private Integer maxSubFolders;
	
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

}
