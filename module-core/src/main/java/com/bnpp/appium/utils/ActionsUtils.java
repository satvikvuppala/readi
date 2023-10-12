package com.bnpp.appium.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

import java.time.Duration;

/**
 * This class contains all the utilities related to UI actions
 */
public class ActionsUtils {
    private final static Logger LOGGER = Logger.getLogger(ActionsUtils.class);

    /**
     * Used for scrolling the screen from 20 to 80 percentage.
     *
     * @param driver Appium driver to use
     */
    public static void scrollDown(AppiumDriver driver) {
        Dimension size = driver.manage().window().getSize();
        int endPoint = (int) (size.width * 0.20);
        int startPoint = (int) (size.width * 0.60);
        int anchor = size.height / 2;
        new TouchAction(driver).press(PointOption.point(anchor, startPoint))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(anchor, endPoint)).release().perform();
//        new TouchAction(driver).press(anchor, startPoint).waitAction(1000).moveTo(anchor, endPoint).release().perform();
    }

    /**
     * Used for scrolling the screen from 50 to 95 percentage.
     *
     * @param driver Appium driver to use
     */
    public static void scrollUp(AppiumDriver driver) {
        Dimension size = driver.manage().window().getSize();
        int endPoint = (int) (size.width * 0.95);
        int startPoint = (int) (size.width * 0.50);
        int anchor = size.height / 2;
//        new TouchAction(driver).press(anchor, endPoint).waitAction(1000).moveTo(anchor, startPoint).release().perform();
        new TouchAction(driver).press(PointOption.point(anchor, endPoint))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(anchor, startPoint)).release().perform();
    }


    /**
     * Swipes between the specified points
     *
     * @param driver     appium driver to use
     * @param direction  The direction of swipe
     * @param duration   Waits for specified amount of time  after the first touch
     * @param startPoint The point at which the sipping starts
     * @param endPoint   The point at which the sipping ends
     */
    public static void swipe(
            AppiumDriver driver,
            Utils.Direction direction,
            int duration,
            Point startPoint,
            Point endPoint) {

        int startX = startPoint.x;
        int startY = startPoint.y;
        int endX = endPoint.x;
        int endY = endPoint.y;

        switch (direction) {
            case RIGHT:
                new TouchAction(driver).press(PointOption.point(startX, startY))
                        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration)))
                        .moveTo(PointOption.point(endX, startY)).release().perform();
                break;
            case LEFT:
                new TouchAction(driver).press(PointOption.point(startX, startY))
                        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration)))
                        .moveTo(PointOption.point(endX, startY)).release().perform();
                break;
            case UP:
                new TouchAction(driver).press(PointOption.point(startX, startY))
                        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration)))
                        .moveTo(PointOption.point(endX, endY)).release().perform();
                break;
            case DOWN:
                new TouchAction(driver).press(PointOption.point(startX, startY))
                        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration)))
                        .moveTo(PointOption.point(startX, endY)).release().perform();
                break;
        }
    }


    /**
     * Swipe side ways at the specified point
     *
     * @param driver            the driver to use
     * @param direction         The direction to swipe. It should be either {@link Utils.Direction#RIGHT} or {@link Utils.Direction#RIGHT}.
     *                          For the rest of the directions, there won't be any effect
     * @param locationOfElement The location of the element. The vertical position(Y coordinate)
     *                          will be calculated from this point
     */
    public static void swipeSideWays(AppiumDriver driver, Utils.Direction direction, Point locationOfElement) {

        Dimension size = driver.manage().window().getSize();

        //Swipe will start from the point at 80% of the screen, ie. little left to the right end of the screen
        int startX = (int) (size.width * 0.60);

        //Swipe will end at the point at 10% of the screen, ie. little right to the lef end of the screen
        int endX = (int) (size.width * 0.10);

        /*Y will be the vertical position of the scroll view.
            The start and end of the y can be same as it does not change*/
        int startY = locationOfElement.y;

        /*
         +------------------------------------+
         -                                    -
         - X2   <----------------------<   X1 -
         -                                    -
         +------------------------------------+
         */

        if (direction == Utils.Direction.RIGHT) {
//            swipe(driver, Utils.Direction.RIGHT, 2000, new Point(startX, startY), new Point(endX, startY));
        } else if (direction == Utils.Direction.LEFT) {
//            swipe(driver, Utils.Direction.LEFT, 2000, new Point(endX, startY), new Point(startX, startY));
        } else {
            LOGGER.warn("Direction " + direction + " has no effect in this method");
        }
    }
}
