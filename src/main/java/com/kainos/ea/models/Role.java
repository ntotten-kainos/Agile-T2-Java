package com.kainos.ea.models;

import com.kainos.ea.enums.Locations;
import com.kainos.ea.enums.RoleStatus;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Role {
    private int jobRoleId;
    private String roleName;
    private Locations location;
    private int bandId;
    private int capabilityId;
    private RoleStatus status;  // Add this line
    private Timestamp closingDate;

    public Role(final int jobRoleId, final String roleName,
                final Locations location, final int bandId,
                final int capabilityId,
                final boolean isOpen,
                final Timestamp closingDate) {
        this.jobRoleId = jobRoleId;
        this.roleName = roleName;
        this.location = location;
        this.bandId = bandId;
        this.capabilityId = capabilityId;
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

    public int getBandId() {
        return bandId;
    }

    public void setBandId(final int bandId) {
        this.bandId = bandId;
    }

    public int getCapabilityId() {
        return capabilityId;
    }

    public void setCapabilityId(final int capabilityId) {
        this.capabilityId = capabilityId;
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
