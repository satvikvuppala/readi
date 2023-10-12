package com.bnpp.appium.utils;

import com.bnpp.appium.config.DriverHolder;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

public class AndroidUtils {


    /**
     * Android scrollTo is not working, As a workaround AndroidUIAutomator is using for the object scrollInToView
     *
     * @param text   label name to scrollIntoView
     * @param driver android driver
     */
    public static void scrollTo(String text, AndroidDriver driver) {
        String automatorString = "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\"" + text + "\"));";
        driver.findElementByAndroidUIAutomator(automatorString);
    }


    /**
     * This method is used to find an element from the Menu page.
     * This will scroll through the Menu items until the desired element is found
     *
     * @param menuText : pass the value of 'text' attribute of the desired android element
     *                 For Eaxample,
     *                 inorder to find the Search menu item, one should pass the parameter as 'Search', which is the value of 'text' attribute
     *
     *                 OR
     *
     *                 For Market Overview menu , should pass the parameter as 'Market Overview', which is the value of 'text' attribute
     */

    public static void scrollToAndroidMenuItem(String menuText) {
        String xPath;
        AppiumDriver driver = DriverHolder.getInstance().getDriver();
        do {

            xPath = "//*[@class='android.widget.TextView' and @text ='" + menuText + "']";
            try {
                Utils.logStep("User is Searching for '" + menuText + "' on the Menu Item");
                driver.findElement(By.xpath(xPath)).isDisplayed();
                break;

            } catch (Exception NoSuchElementException) {
                Dimension dimensions = driver.manage().window().getSize();
                Double screenHeightStart = dimensions.getHeight() * 0.5;
                int scrollStart = screenHeightStart.intValue();
                Double screenHeightEnd = dimensions.getHeight() * 0.2;
                int scrollEnd = screenHeightEnd.intValue();
//                driver.swipe(0, scrollStart, 0, scrollEnd, 2000);
            }
        } while (true);
    }
}


