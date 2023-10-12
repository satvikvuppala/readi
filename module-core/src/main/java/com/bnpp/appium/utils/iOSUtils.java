package com.bnpp.appium.utils;

import com.bnpp.appium.config.DriverHolder;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.HashMap;

public class iOSUtils {

    /**
     * This method is used to find an element from the Menu page.
     * This will scroll through the Menu items until the desired element is found.
     *
     * @param FindElementByName : pass the value of 'name' attribute of the desired iOS element.
     *                          For Example:
     *                          Inorder to the find the search menu, pass the parameter value 'cci.search'.
     */

    public static void LocateIOSMenuItem(String FindElementByName) {
        Utils.logStep("User is scrolling and locating the Menu item");
        RemoteWebElement parent;
        String scrollableElementID;
        AppiumDriver driver = DriverHolder.getInstance().getDriver();
        scrollableElementID = "#orderinfo_list_item_order_number";
        parent = (RemoteWebElement) driver.findElement(By.name(scrollableElementID));
        String parentID = parent.getId();
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put(scrollableElementID, parentID);
        scrollObject.put("name", FindElementByName);
        driver.executeScript("mobile:scroll", scrollObject);
    }
    public static void tapItemByDescription(String text) {
        System.out.println("   tapItemByDescription(): " + text);
        AppiumDriver driver = DriverHolder.getInstance().getDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap scrollObject = new HashMap<>();
        scrollObject.put("predicateString", "value == '" + text + "'");
        js.executeScript("mobile: scroll", scrollObject);

//        WebElement el = ((IOSDriver) driver).findElementByIosNsPredicate("value = '" + text + "'");
//        return tapElement((MobileElement) el);
    }

    public static void scrollToElement(AppiumDriver driver,String elementName) {
        String targetCell = "//UIATableCell[UIAStaticText[@name=\""+elementName+"\"]]";
        WebElement cellWithText = driver.findElement(By.xpath(targetCell));
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("element", ((RemoteWebElement)cellWithText).getId());
        driver.executeScript("mobile: scrollTo",scrollObject);
    }

}
