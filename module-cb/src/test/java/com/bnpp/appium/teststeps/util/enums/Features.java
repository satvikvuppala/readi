package com.bnpp.appium.teststeps.util.enums;

public enum Features {

    NITIAL_LANDING_PAGE("INITIAL_LANDING_PAGE"),
    DECLINE_FINGERPRINT_ACTIVATION("DECLINE_FINGERPRINT_ACTIVATION");

    private final String feature;

    Features(String feature) {
        this.feature = feature;
    }

    public String getFeature() {
        return feature;
    }

    private boolean is(String feature) {
        return this.feature.equalsIgnoreCase(feature);
    }

    public boolean is(Features feature) {
        return is(feature.feature);
    }
}
