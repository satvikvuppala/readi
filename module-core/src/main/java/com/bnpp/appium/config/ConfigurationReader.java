package com.bnpp.appium.config;

import com.bnpp.appium.config.model.device.AndroidConnection;
import com.bnpp.appium.config.model.device.Connection;
import com.bnpp.appium.config.model.device.IOSConnection;
import com.bnpp.appium.utils.Validate;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Parses the device configuration list from a JSON file and return the Connection list
 */
public class ConfigurationReader {
    private final static Logger LOGGER = Logger.getLogger(ConfigurationReader.class);
    private final String jsonFilePath;

    /**
     * Constructs the parser with the json File path
     *
     * @param jsonFilePath The path to json file to be read
     * @throws IllegalStateException if the file is not available at the specified path
     */
    public ConfigurationReader(String jsonFilePath) {
        Path path = Paths.get(jsonFilePath);
        Validate.notFalse(Files.exists(path), "Cannot read configuration at " + jsonFilePath);
        this.jsonFilePath = jsonFilePath;
    }


    /**
     * Reads Json files and parses it to a List of {@link Connection}
     *
     * @return List of Connection available in configuration
     * @throws IOException if the file is invalid
     */
    public List<Connection> read() throws IOException {
        File file = new File(this.jsonFilePath);
        ArrayNode devicesArrayNode = getDevicesListNode(file);
        List<Connection> connectionConfigs = getDevices(devicesArrayNode);
        return connectionConfigs;
    }


    /**
     * Returns the {@link ArrayNode} of devices list from the JSON File passed
     *
     * @param file The file which should be converted to JSON node
     * @return ArrayNode of devices read from the file
     * @throws IOException In case the file cannot be read
     */
    private ArrayNode getDevicesListNode(File file) throws IOException {
        JsonFactory jsonFactory = new JsonFactory();

        JsonParser jp = jsonFactory.createJsonParser(file);
        jp.enable(JsonParser.Feature.ALLOW_COMMENTS);
        jp.setCodec(new ObjectMapper());
        JsonNode jsonNode = jp.readValueAsTree();
        ArrayNode devices = (ArrayNode) jsonNode.path("devices");
        return devices;
    }


    /**
     * Returns the List of devices for the specified JSON array node
     * <br>
     * An example for the array node will be as follows
     * <pre>
     *  [{"someProperty:"property value""},{}]
     * </pre>
     *
     * @param devicesArrayNode Array node with devices list
     * @return List for the array list passed
     */
    private List<Connection> getDevices(ArrayNode devicesArrayNode) {
        List<Connection> connectionConfigList = new ArrayList<>();
        for (JsonNode deviceNode : devicesArrayNode) {
            Connection connectionConfig = getDevice(deviceNode);
            //connectionConfig can be null if there is an error in the node or platform is not available
            if (connectionConfig != null) {
                connectionConfigList.add(connectionConfig);
            }
        }

        return connectionConfigList;
    }

    /**
     * Parses the device node to  a {@link Connection} object.
     * <br>
     * An example for the device node is as follows
     * <pre>
     * {
     *  "remoteAddress": "http://0.0.0.0:4723/wd/hub",
     * "enabled": true,
     * "platformName": "IOSConnection",
     * "platformVersion": "11.3",
     * "deviceName": "iPhone 8 Plus",
     * "app": "/Users/ajeesh/Build/CCIApp/Debug/CCIApp.app"
     * }
     * </pre>
     * <p>
     * All the properties from the configuration will be added to the map in {@link Connection} object.
     * This  will return a null in case the {@link Connection#PLATFORM_NAME } is not available
     * This will return a null in case the {@link Connection#REMOTE_ADDRESS } is not available
     *
     * @param deviceNode Json node with device capabilities information
     * @return {@link Connection} in case the parsing is success. Otherwise null
     */
    private Connection getDevice(JsonNode deviceNode) {
        try {
            ObjectNode node = (ObjectNode) deviceNode;
            Iterator<String> fieldNames = node.getFieldNames();

            String platform = node.get(Connection.PLATFORM_NAME).getTextValue();

            //No platform available. Cannot continue
            if (platform == null) {
                return null;
            }

            Connection connectionConfig = null;
            if (platform.toUpperCase().contains("ANDROID")) {
                connectionConfig = new AndroidConnection();
            } else if (platform.toUpperCase().contains("IOS")) {
                connectionConfig = new IOSConnection();
            } else {
                LOGGER.error("Found an invalid platform[" + platform + "]. " +
                        "Will skip this configuration node[" + node + "]");
                return null;
            }

            String remoteAddress = node.get(Connection.REMOTE_ADDRESS).getTextValue();

            //No remoteAddress. Cannot continue
            if (remoteAddress == null) {
                return null;
            }

            try {
                new URL(remoteAddress);
            } catch (MalformedURLException e) {
                //No remoteAddress. Cannot continue
                LOGGER.error("Invalid remote URL[" + remoteAddress + "]" +
                        "Will skip this configuration node[" + node + "]");
                return null;
            }


            while (fieldNames.hasNext()) {
                String capabilityName = fieldNames.next();
                String capabilityValue = node.get(capabilityName).asText();
                LOGGER.debug("Setting property[" + capabilityName + "] with value[" + capabilityValue + "] ");
                ConfigurationHelper.setCapability(capabilityName, capabilityValue, connectionConfig);
            }

            return connectionConfig;
        } catch (Exception ex) {
            //Continue with the parsing even if one of the node is invalid
            LOGGER.error("There is an error while mapping device configuration to Connection object. " +
                    "This node will be ignored", ex);
            return null;
        }
    }


    public static void main(String[] args) throws IOException {
        List<Connection> read = new ConfigurationReader("/config/devices.json").read();
        System.out.println(read);

    }
}
