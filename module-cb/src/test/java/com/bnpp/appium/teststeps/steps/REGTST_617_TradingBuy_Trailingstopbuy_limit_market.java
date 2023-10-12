package com.bnpp.appium.teststeps.steps;

import com.bnpp.appium.CBMatcherAssert;
import com.bnpp.appium.teststeps.BaseTestCase;
import com.bnpp.appium.teststeps.elements.OrderInfoPage;
import com.bnpp.appium.teststeps.elements.OrderPage;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.When;
import org.hamcrest.Matchers;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.io.IOException;

import static com.bnpp.appium.utils.Utils.*;
import static com.bnpp.appium.CBMatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class REGTST_617_TradingBuy_Trailingstopbuy_limit_market extends BaseTestCase {
    OrderPage orderPage = new OrderPage(getScenario());
    OrderInfoPage orderInfo = new OrderInfoPage(getScenario());

    @Before
    public void init(Scenario scenario) {
        super.init(scenario);

    }

    @When("User enters {string} in StopBuy_Orderwindow")
    public void user_enters_in_StopBuy_Orderwindow(String string) throws IOException, ParseException {
        try {
            enterText(getKeyFromJson(string), orderPage.stopBuyLossField);
            orderPage.dismissKeyBoard();
        }catch (Exception e){
            assertThat("Enter value in stop buy field is failed : " + e.getMessage(), false, getScenario());
        }


    }

    @When("User enters {string} in Abstand_Orderwindow")
    public void user_enters_in_Abstand_Orderwindow(String string) throws IOException, ParseException {
        try {
            enterText(getKeyFromJson(string), orderPage.abstandField);
            orderPage.dismissKeyBoard();
        }catch (Exception e){
            assertThat("Enter value in Abstand field is failed : " + e.getMessage(), false, getScenario());
        }

    }

    @When("User selects {string} in OrderzusatzAbstand_Orderwindow")
    public void user_selects_in_OrderzusatzAbstand_Orderwindow(String string) throws IOException, ParseException {
        try {
            if (!isDisplayed(orderPage.abstandTypeSelector)) {
                swipeDownTo(orderPage.abstandTypeSelector);
            }
            if (!orderPage.selectedAbstandType.getText().equals(getKeyFromJson(string))) {
                orderPage.abstandTypeSelector.click();
                WebElement trailingStopDistanceElement = orderPage.getTrailingStopDistanceElement(getKeyFromJson(string));
                trailingStopDistanceElement.click();
            }
        }catch (Exception e){
            assertThat("OrderzusatzAbstand selection failed : " + e.getMessage(), false, getScenario());
        }
    }

    @When("User enters {string} in ToleranznachStop_Orderwindow")
    public void user_enters_in_ToleranznachStop_Orderwindow(String string) throws IOException, ParseException {
        try {
            enterText(getKeyFromJson(string), orderPage.toleranceField);
            orderPage.dismissKeyBoard();
        }catch (Exception e){
            assertThat("Enter value in ToleranznachStop is failed : " + e.getMessage(), false, getScenario());
        }

    }

    @When("User verifies {string} in Orderzusatz_Orderinfos,{string} in Kaufvon_Orderinfos,{string} in Handelsplatz_Orderinfos,{string} in Orderzusatz_Orderinfos,{string} in Abstand_Orderinfos and {string} in ToleranznachStop_Orderinfos")
    public void user_verifies_in_Orderzusatz_Orderinfos_in_Kaufvon_Orderinfos_in_Handelsplatz_Orderinfos_in_Orderzusatz_Orderinfos_in_Abstand_Orderinfos_and_in_ToleranznachStop_Orderinfos(String string, String string2, String string3, String string4, String string5, String string6) throws IOException, ParseException {

        try {
            waitTillLoaded(orderInfo.orderDetailOrderType, 60);
            assertThat("Orderzusatz verification failed", orderInfo.getOrderDetailOrderType(),containsString(getValueFromJson(string)),getScenario());
            assertThat("Kaufvon verification failed", orderInfo.getOrderDetailOrderart(),containsString(getValueFromJson(string2)),getScenario());
            assertThat("Handelplatz verification failed", orderInfo.getOrderDetailExchange(),equalToIgnoringCase(getValueFromJson(string3)),getScenario());
            assertThat("Limit verification failed", orderInfo.getOrderDetailOrderType(),containsString(getValueFromJson(string4)),getScenario());
            assertThat("Abstand verification failed", orderInfo.getOrderAbstand(),equalToIgnoringCase(getValueFromJson(string5)),getScenario());
            if(!getScenario().getName().equals("KaufOrder_löschen_TrailingStopBuyMarket_Absolut") & !getScenario().getName().equals("KaufOrder_löschen_TrailingStopBuyMarket_Prozentual")){
                assertThat("Tolerenz verification failed", orderInfo.getorderTolerenz(),equalToIgnoringCase(getValueFromJson(string6)),getScenario());
            }
        } catch (Exception e) {
            assertThat("Failed  : " + e.getMessage(), false, getScenario());
        }

    }
}
