package com.kainos.ea.models;

import com.kainos.ea.enums.Locations;
import com.kainos.ea.enums.RoleStatus;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Role {
    private int jobRoleId;
    private String roleName;
    private Locations location;
    private String bandValue;
    private String capabilityName;
    private RoleStatus status;  // Add this line
    private Timestamp closingDate;

    public Role(final int jobRoleId, final String roleName,
                final Locations location, final String bandValue,
                final String capabilityName,
                final boolean isOpen,
                final Timestamp closingDate) {
        this.jobRoleId = jobRoleId;
        this.roleName = roleName;
        this.location = location;
        this.bandValue = bandValue;
        this.capabilityName = capabilityName;
        this.status = RoleStatus.fromBoolean(
                isOpen);
        this.closingDate = closingDate;
    }

    public String getFormattedClosingDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(closingDate);
    }

    public int getJobRoleId() {
        return jobRoleId;
    }

    public void setJobRoleId(final int jobRoleId) {
        this.jobRoleId = jobRoleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(final String roleName) {
        this.roleName = roleName;
    }

    public Locations getLocation() {
        return location;
    }

    public void setLocation(final Locations location) {
        this.location = location;
    }

    public final String getBandValue() {
        return bandValue;
    }

    public void setBandValue(final String bandValue) {
        this.bandValue = bandValue;
    }

    public final String getCapabilityName() {
        return capabilityName;
    }

    public void setCapabilityName(final String capabilityName) {
        this.capabilityName = capabilityName;
    }

    public RoleStatus getStatus() {  // Add getter for status
        return status;
    }

    public void setStatus(final RoleStatus status) {  // Add setter for status
        this.status = status;
    }

    public Timestamp getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(final Timestamp closingDate) {
        this.closingDate = closingDate;
    }

    public boolean isOpen() {
        return status.toBoolean();
    }

    public void setOpen(final boolean isOpen) {
        this.status = RoleStatus.fromBoolean(isOpen);
    }
}
