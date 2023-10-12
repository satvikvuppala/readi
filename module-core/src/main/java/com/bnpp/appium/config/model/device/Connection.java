package com.bnpp.appium.config.model.device;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public abstract class Connection {
    public static final String DEVICE_NAME = "deviceName";
    public static final String PLATFORM_NAME = "platformName";
    public static final String BROWSER_NAME = "browserName";
    public static final String VERSION = "version";
    public static final String PLATFORM_VERSION = "platformVersion";
    public static final String APP = "app";
    public static final String ENABLED = "enabled";
    public static final String REMOTE_ADDRESS = "remoteAddress";
    public static final String NEW_COMMAND_TIMEOUT = "newCommandTimeout";
    public static final String UDID = "udid";
    public static final String IS_TABLET = "isTablet";

    private final Map<String, String> extraProperties = new HashMap<>();

    private String deviceName;
    private String platformName;
    private String browserName;
    private String version;
    private String platFormVersion;
    private String app;
    private int newCommandTimeout;
    private URL remoteAddress;
    private String udid;
    private String automationName;
    private boolean isTablet;

    public String getAutomationName() {
        return automationName;
    }

    public void setAutomationName(String automationName) {
        this.automationName = automationName;
    }

    public int getNewCommandTimeout() {
        return newCommandTimeout;
    }

    public void setNewCommandTimeout(int newCommandTimeout) {
        this.newCommandTimeout = newCommandTimeout;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPlatFormVersion() {
        return platFormVersion;
    }

    public void setPlatFormVersion(String platFormVersion) {
        this.platFormVersion = platFormVersion;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }


    public void addExtra(String capabilityName, String capabilityValue) {
        this.extraProperties.put(capabilityName, capabilityValue);
    }

    public String getExtra(String key) {
        return this.extraProperties.get(key);
    }

    public Map<String, String> getExtraProperties() {
        return this.extraProperties;
    }

    public URL getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(URL remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public String getUdid() {
        return udid;
    }

    public void setUdid(String udid) {
        this.udid = udid;
    }

    public boolean getIsTablet() {
        return isTablet;
    }

    public void setIsTablet(boolean isTablet) {
        this.isTablet = isTablet;
    }

    @Override
    public String toString() {
        return "Connection{" +
                "extraProperties=" + extraProperties +
                ", deviceName='" + deviceName + '\'' +
                ", platformName='" + platformName + '\'' +
                ", browserName='" + browserName + '\'' +
                ", version='" + version + '\'' +
                ", platFormVersion='" + platFormVersion + '\'' +
                ", app='" + app + '\'' +
                ", newCommandTimeout=" + newCommandTimeout +
                ", remoteAddress=" + remoteAddress +
                ", udid='" + udid + '\'' +
                ", automationName='" + automationName + '\'' +
                ", isTablet='" + isTablet + '\'' +
                '}';
    }

}