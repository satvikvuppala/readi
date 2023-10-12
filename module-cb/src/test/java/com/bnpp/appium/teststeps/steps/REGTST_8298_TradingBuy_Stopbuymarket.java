package com.bnpp.appium.teststeps.steps;

import com.bnpp.appium.teststeps.BaseTestCase;
import com.bnpp.appium.teststeps.elements.OrderPage;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;

import static com.bnpp.appium.CBMatcherAssert.assertThat;
import static com.bnpp.appium.utils.Utils.logStep;
import static com.bnpp.appium.utils.Utils.swipeDownTo;

public class REGTST_8298_TradingBuy_Stopbuymarket extends BaseTestCase {
    String stopBuytype;
    OrderPage orderPage = new OrderPage(getScenario());
    @Before
    public void init(Scenario scenario) {
        super.init(scenario);

    }
    @When("User selects {string} in StopBuy_Orderwindow")
    public void user_selects_in_StopBuy_Orderwindow(String string) {

        try {
            logStep("selects " + string + " in Orderzusatz dropdown");
            stopBuytype = getValueFromJson(string);
            if (!isDisplayed(orderPage.stopBuyTypeSelector)) {
                swipeDownTo(orderPage.stopBuyTypeSelector);
            }
            if (!orderPage.selectedStopBuyType.getText().equals(stopBuytype)) {
                getElement(orderPage.stopBuyTypeSelector).click();
                WebElement orderTypeElement = orderPage.getOrderTypeElement(stopBuytype);
                getElement(orderTypeElement).click();
            }
        } catch (Exception e) {
            e.printStackTrace();
            assertThat("StopBuy selection failed : " + e.getMessage(), false, getScenario());

        }
    }

}
