package com.bnpp.appium.config.model.device;

public class AndroidConnection extends Connection {
    public static final String APP_PACKAGE = "appPackage";
    public static final String APP_ACTIVITY = "appActivity";

    private String appPackage;
    private String appActivity;

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public String getAppActivity() {
        return appActivity;
    }

    public void setAppActivity(String appActivity) {
        this.appActivity = appActivity;
    }

    @Override
    public String toString() {
        return "AndroidConnection{" +
                "appPackage='" + appPackage + '\'' +
                ", appActivity='" + appActivity + '\'' +
                "} " + super.toString();
    }
}
