package com.kainos.ea.models;

import com.kainos.ea.enums.Bands;
import com.kainos.ea.enums.Capabilities;
import com.kainos.ea.enums.Locations;
import com.kainos.ea.enums.RoleStatus;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Role {
    int jobRoleId;
    String roleName;
    private Locations location;
    private Bands band;
    private Capabilities capability;
    private RoleStatus status;  // Add this line
    Timestamp closingDate;

    public Role(int jobRoleId, String roleName, Locations location, Bands band,
                Capabilities capability, Timestamp closingDate,
                boolean isOpen) {  // Modify constructor
        this.jobRoleId = jobRoleId;
        this.roleName = roleName;
        this.location = location;
        this.band = band;
        this.capability = capability;
        this.closingDate = closingDate;
        this.status = RoleStatus.fromBoolean(
                isOpen);  // Initialize status from boolean
    }

    public String getFormattedClosingDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(closingDate);
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

    public RoleStatus getStatus() {  // Add getter for status
        return status;
    }

    public void setStatus(RoleStatus status) {  // Add setter for status
        this.status = status;
    }

    public Timestamp getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Timestamp closingDate) {
        this.closingDate = closingDate;
    }

    public boolean isOpen() {
        return status.toBoolean();
    }

    public void setOpen(boolean isOpen) {
        this.status = RoleStatus.fromBoolean(isOpen);
    }
}

