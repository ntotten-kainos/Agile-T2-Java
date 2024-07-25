package com.kainos.ea.models;

public class Bands {
    private int bandId;
    private String bandValue;

    public Bands(final int bandId, final String bandValue) {
        this.bandId = bandId;
        this.bandValue = bandValue;
    }

    public int getBandId() {
        return bandId;
    }

    public void setBandId(final int bandId) {
        this.bandId = bandId;
    }

    public String getBandValue() {
        return bandValue;
    }

    public void setBandValue(final String bandValue) {
        this.bandValue = bandValue;
    }
}
