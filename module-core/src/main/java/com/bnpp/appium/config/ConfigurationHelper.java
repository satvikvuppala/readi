package com.bnpp.appium.config;

import com.bnpp.appium.config.model.device.AndroidConnection;
import com.bnpp.appium.config.model.device.Connection;
import com.bnpp.appium.config.model.device.IOSConnection;
import org.apache.log4j.Logger;

import java.net.MalformedURLException;
import java.net.URL;

public class ConfigurationHelper {
    private final static Logger LOGGER = Logger.getLogger(ConfigurationHelper.class);

    /**
     * Maps the capability values from JSO)N configuration to {@link Connection} model.
     * If there is an unknown capability, it will be added to the extra capabilities
     *
     * @param devicePropertyName  connectionConfig Property Name
     * @param devicePropertyValue connectionConfig Property Value
     * @param connectionConfig    connectionConfig instance where the value should be set
     */
    public static void setCapability(String devicePropertyName, String devicePropertyValue, Connection connectionConfig) {
        switch (devicePropertyName) {
            case Connection.PLATFORM_NAME:
                connectionConfig.setPlatformName(devicePropertyValue);
                break;
            case Connection.APP:
                connectionConfig.setApp(devicePropertyValue);
                break;
            case Connection.BROWSER_NAME:
                connectionConfig.setBrowserName(devicePropertyValue);
                break;
            case Connection.DEVICE_NAME:
                connectionConfig.setDeviceName(devicePropertyValue);
                break;
            case Connection.PLATFORM_VERSION:
                connectionConfig.setPlatFormVersion(devicePropertyValue);
                break;
            case Connection.VERSION:
                connectionConfig.setVersion(devicePropertyValue);
                break;
            case Connection.IS_TABLET:
                boolean b = Boolean.parseBoolean(devicePropertyValue);
                connectionConfig.setIsTablet(b);
                break;
            case Connection.NEW_COMMAND_TIMEOUT:
                int timeout = 120;
                try {
                    timeout = Integer.parseInt(devicePropertyValue);

                } catch (NumberFormatException e) {
                    LOGGER.error("Cannot parse NewCommandTimeout value[" + devicePropertyValue + "]");
                }
                connectionConfig.setNewCommandTimeout(timeout);
                break;
            case Connection.REMOTE_ADDRESS:
                try {
                    connectionConfig.setRemoteAddress(new URL(devicePropertyValue));
                } catch (MalformedURLException e) {
                    LOGGER.error("Invalid remote URL[" + devicePropertyValue + "]");
                }
            case AndroidConnection.APP_ACTIVITY:
                if (connectionConfig instanceof AndroidConnection) {
                    ((AndroidConnection) connectionConfig).setAppActivity(devicePropertyValue);
                }
                break;
            case AndroidConnection.APP_PACKAGE:
                if (connectionConfig instanceof AndroidConnection) {
                    ((AndroidConnection) connectionConfig).setAppPackage(devicePropertyValue);
                }
                break;
            case IOSConnection.AUTOMATION_NAME:
                connectionConfig.setAutomationName(devicePropertyValue);
                break;
            case IOSConnection.UDID:
                connectionConfig.setUdid(devicePropertyValue);
                break;
            case IOSConnection.XCODEORG_ID:
                if (connectionConfig instanceof IOSConnection) {
                    ((IOSConnection) connectionConfig).setXcodeOrgId(devicePropertyValue);
                }
                break;
            case IOSConnection.XCODESIGNING_ID:
                if (connectionConfig instanceof IOSConnection) {
                    ((IOSConnection) connectionConfig).setXcodeSigningId(devicePropertyValue);
                }
                break;
            case IOSConnection.IGNORE_UNIMPORTANT_VIEWS:
                if (connectionConfig instanceof IOSConnection) {
                    ((IOSConnection) connectionConfig).setIgnoreUnimportantViews(devicePropertyValue);
                }
                break;
            case IOSConnection.INCLUDE_NON_MODEL_ELEMENTS:
                if (connectionConfig instanceof IOSConnection) {
                    ((IOSConnection) connectionConfig).setIncludeNonModalElements(devicePropertyValue);
                }
                break;
            case IOSConnection.USE_FIRST_MATCH:
                if (connectionConfig instanceof IOSConnection) {
                    ((IOSConnection) connectionConfig).setUseFirstMatch(devicePropertyValue);
                }
                break;
            case IOSConnection.SNAPSHOT_TIMEOUT:
                if (connectionConfig instanceof IOSConnection) {
                    ((IOSConnection) connectionConfig).setSnapshotTimeout(devicePropertyValue);
                }
                break;

            default:
                connectionConfig.addExtra(devicePropertyName, devicePropertyValue);
        }
    }
}
