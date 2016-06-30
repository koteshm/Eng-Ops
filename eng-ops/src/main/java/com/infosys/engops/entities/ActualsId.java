package com.infosys.engops.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

/**
 * Created by Saurabh_Nayar on 5/25/2016.
 */
@Entity
@IdClass(ActualsId.class)
public class ActualsId implements Serializable {
    @Id
    String month;
    @Id
    String projectCode;
	
    public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
}
