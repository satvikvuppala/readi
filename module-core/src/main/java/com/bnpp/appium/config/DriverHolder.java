package com.bnpp.appium.config;

import com.bnpp.appium.config.model.device.AndroidConnection;
import com.bnpp.appium.config.model.device.Connection;
import com.bnpp.appium.config.model.device.IOSConnection;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Constructs and keeps a map of drivers that can be avail as necessary¬
 */
public class DriverHolder {
    private final static Logger LOGGER = Logger.getLogger(DriverHolder.class);
    private AppiumDriver driver;
    private Connection connectionProperties;
    AppiumDriverLocalService appiumServer;

    private DriverHolder() {
        //Nothing to do here
    }

    public static DriverHolder getInstance() {
        return Loader.DRIVER_POOL_INSTANCE;
    }


    private static class Loader {
        static final DriverHolder DRIVER_POOL_INSTANCE = new DriverHolder();
    }

    /**
     * Initialise the driver map with the specified device configuration
     * Will attempt to create drivers based on the configuration.
     *
     * @param devicesConfigurationPath The path to device configuration JSOn file
     * @throws IOException In case the configuration is not readable
     */
    public void init(String devicesConfigurationPath, String deviceName) throws IOException {

        List<Connection> connectionConfigList = new ConfigurationReader(devicesConfigurationPath).read();

        for (Connection connection : connectionConfigList) {
            if (connection.getDeviceName().equalsIgnoreCase(deviceName)) {
                DesiredCapabilities capabilities = new DesiredCapabilities();

                addCommonProperties(capabilities, connection);

                if (connection instanceof AndroidConnection) {
                    addAndroidProperties(capabilities, (AndroidConnection) connection);
                } else if (connection instanceof IOSConnection) {
                    addiOSProperties(capabilities, (IOSConnection) connection);
                }

                addExtraProperties(capabilities, connection);


                AppiumDriver driver;
                if (connection instanceof AndroidConnection) {
                    LOGGER.info("Going to start connection for [" + capabilities + "]");
                    startAppiumServer(capabilities, connection);
                    driver = new AndroidDriver(connection.getRemoteAddress(), capabilities);
                    this.connectionProperties = connection;
                    LOGGER.info("Started connection for [" + capabilities + "]");
                } else if (connection instanceof IOSConnection) {
                    LOGGER.info("Going to start connection for [" + capabilities + "]");
                   startAppiumServer(capabilities, connection);
                    driver = new IOSDriver(connection.getRemoteAddress(), capabilities);
                    this.connectionProperties = connection;
                    LOGGER.info("Started connection for [" + capabilities + "]");
                } else {
                    LOGGER.warn("Unknown connection type [" + connection + "]. Skipping");
                    continue;
                }

                this.driver = driver;
            } else {
                LOGGER.warn("Connection [" + connection + "] is not matching with deviceName [" + deviceName + "]. Skipping");
            }
        }
    }

    /**
     * If there is any extra properties set , try to add it to capabilities.
     *
     * @param capabilities {@link DesiredCapabilities} object where the properties should be set
     * @param connection   {@link Connection} from  where the connection properties must be taken from
     */
    private void addExtraProperties(DesiredCapabilities capabilities, Connection connection) {
        //try to dump all the extra properties to the capabilities.
        Map<String, String> property = connection.getExtraProperties();
        for (String key : property.keySet()) {
            String value = property.get(key);
            try {
                capabilities.setCapability(key, value);
            } catch (Exception e) {
                LOGGER.error("Couldn't set the capability [" + key + "] with value [" + value + "]");
            }
        }
    }


    /**
     * * Add ios specific capabilities
     *
     * @param capabilities {@link DesiredCapabilities} object where the properties should be set
     * @param iConnection  {@link Connection} from  where the connection properties must be taken from
     */
    private void addiOSProperties(DesiredCapabilities capabilities, IOSConnection iConnection) {
        capabilities.setCapability(MobileCapabilityType.APP, iConnection.getApp());
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, iConnection.getAutomationName());
        capabilities.setCapability("xcodeOrgId", iConnection.getXcodeOrgId());
        capabilities.setCapability("xcodeSigningId", iConnection.getXcodeSigningId());
        capabilities.setCapability("useFirstMatch", iConnection.getUseFirstMatch());
        capabilities.setCapability("snapshotTimeout", iConnection.getSnapshotTimeout());
    }


    /**
     * * Add android specific capabilities
     *
     * @param capabilities {@link DesiredCapabilities} object where the properties should be set
     * @param aConnection  {@link Connection} from  where the connection properties must be taken from
     */
    private void addAndroidProperties(DesiredCapabilities capabilities, AndroidConnection aConnection) {
        capabilities.setCapability(MobileCapabilityType.VERSION, aConnection.getVersion());
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, aConnection.getAppPackage());
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, aConnection.getAppActivity());
    }

    /**
     * Add common capabilities for both iOS and android
     *
     * @param capabilities {@link DesiredCapabilities} object where the properties should be set
     * @param connection   {@link Connection} from  where the connection properties must be taken from
     */
    private void addCommonProperties(DesiredCapabilities capabilities, Connection connection) {
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, connection.getDeviceName());
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, connection.getPlatFormVersion());
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, connection.getPlatformName());
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, connection.getNewCommandTimeout());
        capabilities.setCapability(MobileCapabilityType.UDID, connection.getUdid());
    }

    public synchronized AppiumDriver getDriver() {
        return this.driver;
    }

    /**
     * Provides the connection properties that is used for creating the current appium connection
     *
     * @return connection properies
     */
    public synchronized Connection getDeviceProperties() {
        return this.connectionProperties;
    }

    public void startAppiumServer(DesiredCapabilities serverCapabilities, Connection connection) {

        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .withCapabilities(serverCapabilities);
//        builder.withAppiumJS(new File("/usr/local/lib/node_modules/appium"));
//        builder.usingDriverExecutable(new File("/usr/local/bin/node"));
//        builder.usingDriverExecutable(new File("/usr/local/bin/node"));
        builder.withIPAddress(connection.getRemoteAddress().getHost());
        builder.usingPort(connection.getRemoteAddress().getPort());
        appiumServer = AppiumDriverLocalService.buildService(builder);
//        appiumServer = modifyPathVariable(builder).build();
        appiumServer.start();
        LOGGER.info("Appium server started on - " + connection.getRemoteAddress().getHost() + ":" + connection.getRemoteAddress().getPort());
    }

    public void stopAppiumServer() {
        appiumServer.stop();
    }

    protected AppiumServiceBuilder modifyPathVariable(AppiumServiceBuilder builder) {
        Map<String, String> env = new HashMap<>(System.getenv());
        env.put("PATH", "/usr/local/bin:" + env.get("PATH"));

        return builder.withEnvironment(env);
    }
}
