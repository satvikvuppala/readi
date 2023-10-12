package com.bnpp.appium.teststeps.util.enums;

public enum TextFieldData {

    REPLACED("replaced"),
    NO_CHANGE("same_as_before"),
    ENTERED("entered");

    private final String status;

    TextFieldData(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public boolean is(String status) {
        return this.status.equalsIgnoreCase(status);
    }

    public boolean is(TextFieldData textFieldData) {
        return is(textFieldData.status);
    }
}
