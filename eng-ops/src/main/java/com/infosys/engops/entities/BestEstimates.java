package com.infosys.engops.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Saurabh_Nayar on 5/25/2016.
 */
@Entity
public class BestEstimates implements Serializable {
    @Id
    String quarter;
    @Id
    String manager;
    String bestEstimates;
    String actual;

    @Override
    public String toString() {
        return "BestEstimates{" +
                "quarter='" + quarter + '\'' +
                ", manager='" + manager + '\'' +
                ", bestEstimates='" + bestEstimates + '\'' +
                ", actual='" + actual + '\'' +
                '}';
    }

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

    public String getBestEstimates() {
        return bestEstimates;
    }

    public void setBestEstimates(String bestEstimates) {
        this.bestEstimates = bestEstimates;
    }

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }
}
