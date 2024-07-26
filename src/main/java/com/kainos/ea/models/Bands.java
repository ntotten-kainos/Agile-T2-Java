package com.kainos.ea.models;

public class Bands {
    private int bandId;
    private String bandValue;

    /**
     *
     * @param bandId
     * @param bandValue
     */
    public Bands(final int bandId, final String bandValue) {
        this.bandId = bandId;
        this.bandValue = bandValue;
    }

    /**
     * Getter of BandId.
     * @return bandId
     */
    public int getBandId() {
        return bandId;
    }

    /**
     * Setter for bandId.
     * @param bandId
     */
    public void setBandId(final int bandId) {
        this.bandId = bandId;
    }

    /**
     * Getter for bandValue.
     * @return bandValue
     */
    public String getBandValue() {
        return bandValue;
    }

    /**
     * Setter for bandValue.
     * @param bandValue
     */
    public void setBandValue(final String bandValue) {
        this.bandValue = bandValue;
    }
}
