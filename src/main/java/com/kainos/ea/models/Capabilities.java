package com.kainos.ea.models;

public class Capabilities {
    private int capabilityId;
    private String capabilityName;

    public Capabilities(final int capabilityId, final String capabilityName) {
        this.capabilityId = capabilityId;
        this.capabilityName = capabilityName;
    }

    public int getCapabilityId() {
        return capabilityId;
    }

    public void setCapabilityId(final int capabilityId) {
        this.capabilityId = capabilityId;
    }

    public String getCapabilityName() {
        return capabilityName;
    }

    public void setCapabilityName(final String capabilityName) {
        this.capabilityName = capabilityName;
    }
}
