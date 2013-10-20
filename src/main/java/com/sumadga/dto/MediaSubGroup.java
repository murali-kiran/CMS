package com.sumadga.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the media_group_media database table.
 * 
 */
@Entity
@Table(name="media_subgroups")
public class MediaSubGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="subgroup_id", unique=true, nullable=false)
	private Integer subGroupId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time", nullable=false)
	private Date createdTime;

	@Column(name="group_order", nullable=false)
	private Integer groupOrder;

	@Column(name="modified_time", nullable=false)
	private Timestamp modifiedTime;

	//bi-directional many-to-one association to Media
	@ManyToOne
	@JoinColumn(name="parent_group_id", nullable=false)
	private MediaGroup parentMediaGroup;

	//bi-directional many-to-one association to MediaGroup
	@ManyToOne
	@JoinColumn(name="child_group_id", nullable=false)
	private MediaGroup childMediaGroup;

	public MediaSubGroup() {
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
	public Integer getSubGroupId() {
		return subGroupId;
	}

	public void setSubGroupId(Integer subGroupId) {
		this.subGroupId = subGroupId;
	}

	public Integer getGroupOrder() {
		return groupOrder;
	}

	public void setGroupOrder(Integer groupOrder) {
		this.groupOrder = groupOrder;
	}

	public MediaGroup getParentMediaGroup() {
		return parentMediaGroup;
	}

	public void setParentMediaGroup(MediaGroup parentMediaGroup) {
		this.parentMediaGroup = parentMediaGroup;
	}

	public MediaGroup getChildMediaGroup() {
		return childMediaGroup;
	}

	public void setChildMediaGroup(MediaGroup childMediaGroup) {
		this.childMediaGroup = childMediaGroup;
	}

	@Override
	public String toString() {
		return "MediaSubGroup [subGroupId=" + subGroupId
				+ ", createdTime=" + createdTime + ", groupOrder=" + groupOrder
				+ ", modifiedTime=" + modifiedTime + ", parentMediaGroup=" + parentMediaGroup
				+ ", childMediaGroup=" + childMediaGroup + "]";
	}

}