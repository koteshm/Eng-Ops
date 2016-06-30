package com.infosys.engops.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

/**
 * Created by Saurabh_Nayar on 5/25/2016.
 */
@Entity
@IdClass(BestEstimatesId.class)
public class BestEstimatesId implements Serializable {
    @Id
    String quarter;
    @Id
    String manager;

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
}
