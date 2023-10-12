package com.bnpp.appium.utils;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.bnpp.appium.config.DriverHolder;
import com.bnpp.appium.config.model.device.Connection;
//import com.sun.glass.ui.Size;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;


import org.apache.log4j.Logger;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;

/**
 * Holds the utility common for the entire application.
 */

public class Utils {
    private final static Logger LOGGER = Logger.getLogger(Utils.class);
    static final int MAX_SCROLL_ATTEMPTS = 5;
    static int scrollCount;
    private static Wait<WebDriver> wait;

    Scenario scenario;

    public Utils(Wait<WebDriver> wait) {
        this.wait = wait;
    }

    public Scenario getScenario() {
        return scenario;
    }

    private final static AppiumDriver driver = DriverHolder.getInstance().getDriver();

    public enum Direction {
        DOWN, UP, LEFT, RIGHT;
    }

    /**
     * Checks whether the current driver is Android.
     *
     * @return true if the current driver is Android else false.
     */
    public static boolean isAndroid() {
        if (driver instanceof AndroidDriver) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Takes a screen shot and attach to the scenario.
     * This screen shot will be used by the report generator to show an image at the current step
     *
     * @param scenario The scenario to which screen should be attached
     * @throws IOException in case of an I/O error
     */
    public static void takeAScreenshot(io.cucumber.java.Scenario scenario) {
        AppiumDriver driver = DriverHolder.getInstance().getDriver();
        LOGGER.info("Taking screenshot..");
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            String testName = scenario.getName();
            //scenario.embed(screenshot, "image/png");
            //scenario.write(testName);
        } catch (WebDriverException e) {
            LOGGER.error("Error on taking screen shot", e);
        }

    }

    /**
     * Returns whether the device is tablet.
     * The values are based on the configuration used for device configuration
     *
     * @return true if the device is tablet
     */
    public static boolean isTablet() {
        Connection deviceProperties = DriverHolder.getInstance().getDeviceProperties();
        return deviceProperties.getIsTablet();
    }

    public static boolean isBDDF() {
        String OE = System.getProperty("propertyfile");
        if (OE.equalsIgnoreCase("config/testdata-BDDF.properties")) {
            return true;
        } else {
            return false;
        }
    }

    public static void logStep(String step) {

        LOGGER.info("Executing : [" + step + "]");

    }

    /**
     * Check whether the list of strings is equals without order.
     *
     * @param first  first list of string
     * @param second second list of string
     * @return
     */
    public static boolean equalsWithoutOrder(List first, List second) {
        return first.containsAll(second) && second.containsAll(first);
    }

    public static boolean isValidData(String value) {
        List<String> invalidStrings =
                Arrays.asList("na", "_", "n.a", "");

        if (value == null) {
            return false;
        }

        return !(invalidStrings.contains(value.toLowerCase().trim()));
    }

    public static boolean isValidDateFormat(String dateString, String format) {
        SimpleDateFormat Dateformat = new SimpleDateFormat(format);
        try {
            Dateformat.parse(dateString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Method to scroll down the screen
     *
     * @param driver             AppiumDriver
     * @param xPos               swipping x position
     * @param yStart             The point at which the swiping starts
     * @param yEnd               The point at which the swiping ends
     * @param isAdditionalScroll boolean for set additional scrolling
     */
//    public static void scrollDown(AppiumDriver driver, double xPos, double yStart, double yEnd,
//                                  boolean isAdditionalScroll) {
//        // Get the size of screen.
//        Dimension size = driver.manage().window().getSize();
//        // Find horizontal point where you wants to swipe. It is in middle of screen width.
//        int x = (int) (size.width * xPos);
//        // Find start y point which is at bottom side of screen.
//        int startY = (int) (size.height * yStart);
//        // Find end y point which is at top side of screen.
//        int endY = (int) (size.height * yEnd);
//        Point startPoint = new Point(x, startY);
//        Point endPoint = new Point(x, endY);
//
////        ActionsUtils.swipe(driver, Utils.Direction.DOWN, 3000, startPoint, endPoint);
//        if (!isAndroid() && isAdditionalScroll) {
////            ActionsUtils.swipe(driver, Utils.Direction.DOWN, 3000, startPoint, endPoint);
//        }
//    }

    /**
     * Returns a comma separated string representation of list provided.
     *
     * @param list The list to be converted
     * @return a comma separated string representation of list provided
     */
    public static String listToString(List list) {
        if (list == null) {
            return "";
        }
        return list.stream().collect(joining(",")).toString();
    }

    /**
     * Gets the  list of  values from the data table available from cucumber feature configuration
     * Note that the input map must be in the following structure
     * <pre>
     * List{
     *     Map{"Android"  "TURBOS"}
     *     Map{"iOS" -> "TURBO"}
     *     Map{"Android" -> "WARRANTS"}
     *     Map{"iOS" -> "WARRANTS"}
     *     Map{"Android" -> "BNP"}
     *     Map{"iOS" -> "BNP"}
     * }
     * </pre>
     * The output for the "Android" will be
     * <pre>
     * List{TURBOS,WARRANTS,BNP}
     * </pre>
     * <p>
     * The output for the "iOs will be
     * <pre>
     * List{TURBO,WARRANTS,BNP}
     * </pre>
     *
     * @param mapList  The map which should be used to extract values
     * @param platform Should be either android or ios
     * @return List of string values for the specific platform
     */
    public static List<String> getListOf(List<Map<String, String>> mapList, String platform) {
        return mapList.stream().map(m ->
                m.entrySet().stream()
                        .filter(map -> map.getKey().equalsIgnoreCase(platform))
                        .map(map -> map.getValue())
                        .collect(Collectors.toList()))
                .filter(list -> list != null && list.isEmpty())
                .map(list -> list.get(0))
                .collect(Collectors.toList());
    }

    /**
     * Method to check element is present
     *
     * @param element
     * @return true if the element is present
     */
    public static boolean isPresentAndDisplayed(final WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static void acceptAlert() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
        } catch (Exception e) {
            logStep("Alert not present");
        }
    }

    public static boolean acceptAlert(String message) {
        try {
            Wait<WebDriver> wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            if (alert.getText().equalsIgnoreCase(message)) {
                alert.accept();
                return true;
            }
        } catch (final Exception e) {
            logStep("No alert appeared!");
        }
        return false;
    }

    public static void dismissAlert() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().dismiss();
        } catch (Exception e) {
            logStep("Alert not present");
        }
    }

    public static void waitTillLoaded(MobileElement... mobileElements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(mobileElements));
    }

    public static void waitTillDismissed(MobileElement mobileElement) {
        wait.until(invisibilityOf(mobileElement));
    }

    public static void waitToLoad(MobileElement mobileElement) {
        waitTillDismissed(mobileElement);
    }

    public static void waitTillLoaded(MobileElement mobileElement, int timeOut) {

        Wait<WebDriver> wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.visibilityOf(mobileElement));
    }

    public static void waitTillLoaded(MobileElement mobileElement, int timeOut, io.cucumber.java.Scenario scenario) {
        try {
            Wait<WebDriver> wait = new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.visibilityOf(mobileElement));
        } catch (Exception e) {
            takeAScreenshot(scenario);
            throw e;
        }
    }

    private static boolean isElementInvisible(MobileElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 1);
            wait.withTimeout(Duration.ZERO).until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            return true;
        }

        return false;
    }

    public static void scrollDown(AppiumDriver driver) {
        Dimension size = driver.manage().window().getSize();
        int anchor = (int) (size.width * 0.5);
        int startPoint = (int) (size.height * 0.60);
        int endPoint = (int) (size.height * 0.30);
        new TouchAction(driver).press(PointOption.point(anchor, startPoint)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(PointOption.point(anchor, endPoint)).release().perform();
    }

    public static void swipeDownTo(MobileElement element) {
        while (isElementInvisible(element) && scrollCount < MAX_SCROLL_ATTEMPTS) {
            try {
                scrollDown(driver);
                scrollCount++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        scrollCount = 0;
    }

    public static void swipeFromRight() {
        Dimension size = driver.manage().window().getSize();
        int anchor = (int) (size.height * 0.5);
        int startPoint = (int) (size.width * 0.75);
        int endPoint = (int) (size.width * 0.25);
        new TouchAction(driver).press(PointOption.point(startPoint, anchor))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(endPoint, anchor)).release().perform();
    }

    public static void tapMiddleOfScreen() {
        Dimension size = driver.manage().window().getSize();
        int centerX = (int) (size.width * 0.5);
        int centerY = (int) (size.height * 0.5);
//        new TouchAction(driver).tap(PointOption.point(centerX, centerY)).perform();
        new TouchAction(driver).press(PointOption.point(centerX, centerY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).perform();
    }


}
