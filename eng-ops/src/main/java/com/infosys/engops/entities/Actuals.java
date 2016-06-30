package com.infosys.engops.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Actuals implements Serializable {
	@Id
	String projectCode;
	@Id
	String quarter;
	String actualRevenue;

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public String getActualRevenue() {
		return actualRevenue;
	}

	public void setActualRevenue(String actualRevenue) {
		this.actualRevenue = actualRevenue;
	}
@Override
public String toString() {
	// TODO Auto-generated method stub
	return this.projectCode+" "+this.quarter;
}
}
