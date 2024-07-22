package com.kainos.ea.models;

import com.kainos.ea.enums.Bands;
import com.kainos.ea.enums.Capabilities;
import org.joda.time.DateTime;
import com.kainos.ea.enums.Locations;

public class Role {
    int jobRoleId;
    String roleName;
    private Locations location;
    private Bands band;
    private Capabilities capability;
    DateTime closingDate;

    public Role(int jobRoleId, String roleName, Locations location, Bands band,
                Capabilities capability, DateTime closingDate) {
        this.jobRoleId = jobRoleId;
        this.roleName = roleName;
        this.location = location;
        this.band = band;
        this.capability = capability;
        this.closingDate = closingDate;
    }

    public int getJobRoleId() {
        return jobRoleId;
    }

    public void setJobRoleId(int jobRoleId) {
        this.jobRoleId = jobRoleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Locations getLocation() {
        return location;
    }

    public void setLocation(Locations location) {
        this.location = location;
    }

    public Bands getBand() {
        return band;
    }

    public void setBand(Bands band) {
        this.band = band;
    }

    public Capabilities getCapability() {
        return capability;
    }

    public void setCapability(Capabilities capability) {
        this.capability = capability;
    }

    public DateTime getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(DateTime closingDate) {
        this.closingDate = closingDate;
    }
}
