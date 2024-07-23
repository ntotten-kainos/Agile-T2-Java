package com.kainos.ea.enums;

public enum Capabilities {
    APPLIED_INNOVATION("Applied Innovation"),
    BUSINESS_DEVELOPMENT_MARKETING("Business Development & Marketing"),
    BUSINESS_SERVICES_SUPPORT("Business Services Support"),
    CYBER_SECURITY("Cyber Security"), DATA_AI("Data & AI"),
    DELIVERY_MANAGEMENT("Delivery Management"), ENGINEERING("Engineering"),
    EXPERIENCE_DESIGN("Experience Design"), PLATFORMS("Platforms"),
    PRE_SALES("Pre-Sales"),
    PRODUCT_DIGITAL_ADVISORY("Product & Digital Advisory"),
    QUALITY_ASSURANCE("Quality Assurance");

    private String value;

    Capabilities(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static Capabilities fromString(String text) {
        for (Capabilities c : Capabilities.values()) {
            if (c.value.equalsIgnoreCase(text)) {
                return c;
            }
        }
        throw new IllegalArgumentException("No enum constant " + text);
    }
}
