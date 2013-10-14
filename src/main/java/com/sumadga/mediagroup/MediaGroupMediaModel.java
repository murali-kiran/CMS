package com.sumadga.mediagroup;

import java.sql.Timestamp;
import java.util.Date;

public class MediaGroupMediaModel {
		private static final long serialVersionUID = 1L;
		private Date createdTime;
		private Integer mediaOrder;
		private Timestamp modifiedTime;
		private Integer mediaId;
		private Integer mediaGroupId;

		public MediaGroupMediaModel() {
		}

		public Date getCreatedTime() {
			return createdTime;
		}

		public void setCreatedTime(Date createdTime) {
			this.createdTime = createdTime;
		}

		public Integer getMediaOrder() {
			return mediaOrder;
		}

		public void setMediaOrder(Integer mediaOrder) {
			this.mediaOrder = mediaOrder;
		}

		public Timestamp getModifiedTime() {
			return modifiedTime;
		}

		public void setModifiedTime(Timestamp modifiedTime) {
			this.modifiedTime = modifiedTime;
		}

		public Integer getMediaId() {
			return mediaId;
		}

		public void setMediaId(Integer mediaId) {
			this.mediaId = mediaId;
		}

		public Integer getMediaGroupId() {
			return mediaGroupId;
		}

		public void setMediaGroupId(Integer mediaGroupId) {
			this.mediaGroupId = mediaGroupId;
		}

	
}
