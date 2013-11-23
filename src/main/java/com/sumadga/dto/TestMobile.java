package com.sumadga.dto;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the test_mobile database table.
 * 
 */
@Entity
@Table(name="test_mobile")
public class TestMobile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="testmobile_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String testmobileId;

	@Column(name="mobile_number")
	private String mobileNumber;

	public TestMobile() {
	}

	public String getTestmobileId() {
		return this.testmobileId;
	}

	public void setTestmobileId(String testmobileId) {
		this.testmobileId = testmobileId;
	}

	public String getMobileNumber() {
		return this.mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

}