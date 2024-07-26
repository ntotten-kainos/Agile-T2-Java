package com.kainos.ea.models;

public class Capabilities {
    /**
     * The unique identifier for the capability.
     */
    private int capabilityId;
    /**
     * The name of the capability.
     */
    private String capabilityName;

    /**
     * Constructor of new capability.
     * @param capabilityId
     * @param capabilityName
     */
    public Capabilities(final int capabilityId, final String capabilityName) {
        this.capabilityId = capabilityId;
        this.capabilityName = capabilityName;
    }

    /**
     * Getter for capabilityId.
     * @return capabilityId
     */
    public int getCapabilityId() {
        return capabilityId;
    }

    /**
     * Setter for capabilityId.
     * @param capabilityId
     */
    public void setCapabilityId(final int capabilityId) {
        this.capabilityId = capabilityId;
    }

    /**
     * Getter for capabilityName.
     * @return capabilityName
     */
    public String getCapabilityName() {
        return capabilityName;
    }

    /**
     * Setter for capabilityName.
     * @param capabilityName
     */
    public void setCapabilityName(final String capabilityName) {
        this.capabilityName = capabilityName;
    }
}
