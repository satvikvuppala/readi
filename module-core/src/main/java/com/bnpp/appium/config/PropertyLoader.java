package com.bnpp.appium.config;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * Singleton class which will holds the test data.
 * This class must be initialized with the path to test data property file using the load method.
 */
public class PropertyLoader {

    private final static Logger LOGGER = Logger.getLogger(PropertyLoader.class);
    private Properties props = new Properties();

    private PropertyLoader() {
    }

    /**
     * Loads properties from testdata property file
     *
     * @param filePath path to the test data.
     * @throws IOException
     */
    public void load(String filePath) throws IOException {
        File file = new File(filePath);
        FileInputStream fileInput = null;
        fileInput = new FileInputStream(file);

        LOGGER.info("Property file from server is [" + filePath + "]");
        props.load(new InputStreamReader(fileInput, Charset.forName("UTF-8")));

    }

    /**
     * Fetch the test data of a single string from test data property file which is passed as a commandline parameter.
     *
     * @param name the name (identifier) of the property.
     * @return the string value from corresponding test data file.
     */
    public String getProperty(final String name) {

        String value = "";
        if (name != null) {
            value = props.getProperty(name);
        }
        return value;
    }

    /**
     * Try to get a integer value from properties. If the values is not parsable , will return 0
     *
     * @param name the name (identifier) of the property.
     * @return the integer value from corresponding test data file.
     */
    public int tryGetAsInteger(final String name) {
        String property = getProperty(name);
        try {
            return Integer.parseInt(property);
        } catch (Exception ex) {/*ignored*/}

        return 0;
    }

    /**
     * Fetch list of strings from test data property files as an array. The strings should be stored as a value of a key,
     * separated with commas. In order to include strings with commas, the commas within the string should be escaped with a preceding backslash.
     *
     * @param name is the name (identifier) of the property.
     * @return the array of string values from corresponding test data file.
     */
    public String[] getArrayProperty(final String name) {
        String stringsSeparatedWithComma = getProperty(name);
        if (stringsSeparatedWithComma == null) {
            return null;
        } else {
            // in order to be able to escape comma character we use preceding
            // backslash which we don't consider while splitting the string
            String[] array = stringsSeparatedWithComma.split("(?<!\\\\),");
            // but then we should remove these preceding backslashes from the
            // original string in the array:
            for (int i = 0; i < array.length; i++) {
                array[i] = array[i].replaceAll("\\\\,", ",");
            }

            return array;
        }
    }

    public static PropertyLoader getInstance() {
        return Loader.PROPERTY_LOADER;
    }

    private static class Loader {
        static final PropertyLoader PROPERTY_LOADER = new PropertyLoader();
    }

}
