package com.bnpp.appium.teststeps;

import com.bnpp.appium.config.DriverHolder;
import com.bnpp.appium.config.PropertyLoader;
import com.bnpp.appium.teststeps.elements.OrderPage;
import com.bnpp.appium.teststeps.util.enums.TextFieldData;
import com.bnpp.appium.utils.Utils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.cucumber.java.Scenario;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;

import java.io.*;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.bnpp.appium.CBMatcherAssert.assertThat;
import static com.bnpp.appium.utils.Utils.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;

/**
 * BaseTest class is the super class for retrieving the driver from driver pool.
 * getDriver() method can used for getting the driver
 * getScenario() method can used for taking the screenshots
 */
public class BaseTestCase {
    private final static Logger LOGGER = Logger.getLogger(BaseTestCase.class);

    private Wait<WebDriver> wait;
    private PropertyLoader prop = PropertyLoader.getInstance();
    Scenario scenario;
    public static String featurename;
    public static String scenarioname;

    public void init(Scenario scenario) {
        AppiumDriver Driver = DriverHolder.getInstance().getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(Driver, Duration.ofSeconds(0)), this);

        this.scenario = scenario;

    }

    public AppiumDriver getDriver() {
        return DriverHolder.getInstance().getDriver();
    }

    public PropertyLoader getProp() {
        return prop;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public MobileElement getElement(MobileElement mobileElement) {
        try {
            mobileElement.getTagName();
        } catch (Exception e) {
            takeAScreenshot(getScenario());
            Assert.fail("Object identification failed : " + mobileElement);
            throw e;
        }

        return mobileElement;
    }

    /**
     * Takes a screen shot if the element is not found and attached to th scenario.
     * @param webElement
     * @return
     */
    public WebElement getElement(WebElement webElement) {
        try {
            webElement.getTagName();
        } catch (Exception e) {
            takeAScreenshot(getScenario());
            Assert.fail("Object identification failed : " + webElement);
            throw e;
        }

        return webElement;
    }


    public WebElement setElement(WebElement oldWebElement, WebElement newWebElement) {
        try {
            oldWebElement = newWebElement;
        } catch (Exception e) {
            takeAScreenshot(getScenario());
            throw e;
        }

        return oldWebElement;
    }

    protected boolean ifDisplayedClickElement(final WebElement element) {
        try {
            if (element.isDisplayed()) {
                TouchActions action = new TouchActions(getDriver());
                action.singleTap(element);
                return true;
            }
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected boolean isDisplayed(final WebElement element) {
        try {
            return element.isDisplayed();
        } catch (final NoSuchElementException e) {
            return false;
        }
    }

    private void tapEndOfTextField(MobileElement textField) {
        new TouchAction(getDriver()).press(PointOption.point(textField.getLocation().x + textField.getSize().width - 10, textField.getLocation().y + textField.getSize().height - 10)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).release().perform();
    }

    protected TextFieldData clearAndEnterNewData(MobileElement textField, String newData) {
        TextFieldData textFieldData;
        String existingData = textField.getText();
        if (!existingData.equals(newData)) {
            if (!existingData.isEmpty() && !existingData.equals("Kontonummer")) {
                tapEndOfTextField(textField);
                textField.clear();
                textFieldData = TextFieldData.REPLACED;
            } else {
                textFieldData = TextFieldData.ENTERED;
            }
            textField.sendKeys(newData);
//            getDriver().hideKeyboard();
//            tapMiddleOfScreen();
        } else {
            textFieldData = TextFieldData.NO_CHANGE;
        }
//        getDriver().hideKeyboard();
        tapMiddleOfScreen();
        return textFieldData;
    }

    public void waitTillDismissed(MobileElement mobileElement) {
        wait.until(invisibilityOf(mobileElement));
    }

    public void setfeaturefilenameandsceanrio(String id, String name) {
        featurename = id;
        String[] d = featurename.split("/features/");
        // System.out.println(d[0] + " " + d[1]);
        String[] d2 = d[1].split(".feature");
        // System.out.println(d2[0]);
        featurename = d2[0];
        scenarioname = name;
    }

    public void setScenario(Scenario s) {
        scenario = s;
    }


    public String getScenarioName() {
        return scenarioname;
    }

    public String getFeatureName() {
        return featurename;
    }

    public String getKeyFromJson(String dataKey) throws IOException, ParseException {
        String data = null;
        JSONParser parser = new JSONParser();

        JSONObject getFeatureName = (JSONObject) parser
//						.parse(new FileReader("./src/test/java/com/bnpp/testdata/intacc1/" + featurename + ".json"));
                .parse(new BufferedReader(new InputStreamReader(new FileInputStream("./src/test/resources/testdata/" + featurename + ".json"), "UTF-8")));
        Set<String> jsonKeySet = getFeatureName.keySet();
        Iterator<String> itr = jsonKeySet.iterator();
        String jsonKey = null;
        while (itr.hasNext()) {
            jsonKey = itr.next();
            break;
        }

        JSONObject featureName = (JSONObject) getFeatureName.get(jsonKey);
        Map<String, String> getScenarioName = (Map<String, String>) featureName.get(scenarioname);
        Iterator it = getScenarioName.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (pair.getKey().toString().equals(dataKey)) {
                data = pair.getValue().toString();
                break;
            }
        }
        return data;

    }

    public String getValueFromJson(String dataKeyInJson) throws FileNotFoundException, IOException, ParseException {
        String datakey = null;
        try {
            datakey = getKeyFromJson(dataKeyInJson);
//            datakey = checkGermanCharacters(datakey);

        } catch (FileNotFoundException e) {
            Assert.fail(featurename + " .json file not found");
        } catch (NullPointerException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(dataKeyInJson + " : Please make sure ojectKey present in json file");
        }

        return datakey;
    }

    public void enterText(String dataKey, MobileElement element) {
        try{
            logStep("User enters " + dataKey + " in text field");
            if (!isDisplayed(element)) {
                swipeDownTo(element);
            }
            getElement(element).click();
            element.sendKeys(dataKey);
        }catch (Exception e){
            takeAScreenshot(getScenario());
            Assert.fail("Element not found : " + element);
        }
    }

    public void selectFromDropDown(OrderPage orderPage,String dataKey, MobileElement element) {
        if (!isDisplayed(element)) {
            swipeDownTo(element);
        }
        if (!orderPage.selectedAbstandType.getText().equals(dataKey)) {
            element.click();
            WebElement trailingStopDistanceElement = orderPage.getTrailingStopDistanceElement(dataKey);
            trailingStopDistanceElement.click();
        }
    }
    public void click(){

    }
}
