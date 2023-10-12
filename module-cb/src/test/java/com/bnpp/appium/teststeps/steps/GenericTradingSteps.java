package com.bnpp.appium.teststeps.steps;


import com.bnpp.appium.teststeps.BaseTestCase;
import com.bnpp.appium.teststeps.elements.OrderEditDeletePage;
import com.bnpp.appium.teststeps.elements.OrderInfoPage;
import com.bnpp.appium.teststeps.elements.OrderPage;
import com.bnpp.appium.utils.Utils;
import io.appium.java_client.MobileElement;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.bnpp.appium.CBMatcherAssert.assertThat;
import static com.bnpp.appium.utils.Utils.*;
import static org.hamcrest.Matchers.*;

//import static org.hamcrest.MatcherAssert.assertThat;

public class GenericTradingSteps extends BaseTestCase {
    String wknValue;
    String orderCategory;
    String exchangeValue;
    String quantityValue;
    String option;
    String dateOption;
    String tanPageExpiryDate;
    String confMessage;
    String successMsg;
    String typeOnConfirmPage;
    String limitOnConfirmPage;
    String exchangeOnConfirmPage;
    String expirationDateOnConfirmPage;
    String orderNumber;
    String limit;
    String distance;
    String tolerance;
    String trailingStopLimit;
    OrderEditDeletePage orderEditDeletePage = new OrderEditDeletePage(getScenario());
    OrderInfoPage orderInfo = new OrderInfoPage(getScenario());
    OrderPage orderPage = new OrderPage(getScenario());

    @Before
    public void init(Scenario scenario) {
        super.init(scenario);

    }

    @When("User clicks on Searchicon_MeineFinanzen")
    public void user_click_on_icon_in_overflow_menu() throws InterruptedException {
        try {
            logStep("User click on Search icon from top menu bar");
            waitTillLoaded(orderPage.searchIcon, 20);
            orderPage.searchIcon.click();
        } catch (Exception e) {
            assertThat("Search icon object identification failed in MeineFinanzen" + e.getMessage(), false, getScenario());
        }


    }

    @When("User enters {string} in SearchField_SearchPage")
    public void user_enters_in_search_field(String wkn) throws IOException, ParseException {
        logStep("User enters " + wkn + " in search field");
        wknValue = getValueFromJson(wkn);
        waitTillLoaded(orderPage.searchField, 60);
        getElement(orderPage.searchField).setValue(wknValue);
    }

    @When("User clicks on Search_SearchPage")
    public void user_click_on_search_button() {
        try {
            logStep("User click on search button");
            getElement(orderPage.searchButton).click();
        } catch (Exception e) {
            assertThat("Search button click failed" + e.getMessage(), false, getScenario());
        }

    }

    @When("User clicks on FirstSearchResult_SearchPage")
    public void user_click_on_search_result() throws IOException, ParseException {
        try {
            logStep("User click on search result");
            getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            if (orderPage.securityWkns != null && !orderPage.securityWkns.isEmpty() && isDisplayed(orderPage.securityWkns.get(0))) {
                for (MobileElement item : orderPage.securityWkns) {
                    if (isAndroid()) {
                        if (orderPage.securityWkns.size() == 0) {
                            break;
                        }
                        if (isDisplayed(item) && item.getText().equals("WKN " + getValueFromJson("Name_WKN_ISIN"))) {
                            getElement(item).click();
//                            getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                        }
                    } else {
                        if (isDisplayed(item) && item.getText().equals(getValueFromJson("Name_WKN_ISIN"))) {
                            getElement(item).click();
                        }
                    }
                }
            }
        } catch (Exception e) {
            assertThat("Search result is not displayed" + e.getMessage(), false, getScenario());
        }
    }

    @When("User clicks on {string}_OrderSnapshot")
    public void user_click_on_buy_or_sell_button(String orderart) throws IOException, ParseException, InterruptedException {
        try {
            logStep("User click on " + orderart + " button");
            orderCategory = getValueFromJson(orderart);
//            waitTillLoaded(orderPage.buyButton,15);
            MobileElement orderButton = orderPage.selectOrderButton(orderCategory);
            waitTillLoaded(orderButton, 60);
            getElement(orderButton).click();
            Thread.sleep(1000);
            if (isAndroid()) {
                if (getScenarioName().equals("KaufOrder_Limithandel_Aktie") || getScenarioName().equals("KaufOrder_Limithandel_Anleihe") || getScenarioName().equals("KaufOrder_Limithandel_Zertifikat")) {
                    waitTillLoaded(orderPage.acceptAlert,5);
                    if(isPresentAndDisplayed(orderPage.acceptAlert)){
                        orderPage.acceptAlert.click();
                    }
                }

            } else {
                dismissAlert();
            }

//        waitTillLoaded(orderPage.guiderOrderCoach,10);
            if (isDisplayed(orderPage.guiderOrderCoach)) {
                tapMiddleOfScreen();
            }
        }catch (Exception e){
            assertThat("Buy sell button click failed : " + e.getMessage(), false, getScenario());
        }
    }

    @When("User selects {string} in AmWelchenHandelsplatz_Orderwindow")
    public void user_selects_from_orderwindow(String exchange) throws IOException, ParseException {
        try {
            logStep("User selects " + getValueFromJson(exchange) + " in orderwindow");
//            waitTillLoaded(orderPage.exchangeName,60);
            exchangeValue = getValueFromJson(exchange);
            if (!getElement(orderPage.exchangeName).getText().equals(exchangeValue)) {
                getElement(orderPage.exchangeSelector).click();
                WebElement stockExchangeElement = orderPage.getStockExchangeElement(exchangeValue);
                logStep("Exchange name ---------:" + stockExchangeElement.getText());
                stockExchangeElement.click();
            } else {
                getElement(orderPage.exchangeSelector).getText();
            }
        } catch (Exception e) {
            assertThat("Exchange selection failed : " + e.getMessage(), false, getScenario());
        }

    }

    @When("User enters {string} in Stück_Orderwindow")
    public void user_enters_in_orderwindow(String quantity) throws IOException, ParseException {
        try {
            logStep("User enters " + quantity + " in orderwindow");
            quantityValue = getValueFromJson(quantity);
            if (!isDisplayed(getElement(orderPage.quantityField))) {
                swipeDownTo(orderPage.quantityField);
            }
            getElement(orderPage.quantityField).click();
            orderPage.quantityField.setValue(quantityValue.split(",")[0]);
            if (!isAndroid()) {
                getElement(orderPage.qtyValueLabel).click();
            } else {
                getDriver().hideKeyboard();
            }
            if (isAndroid()){
                logStep("User click on Zusatzoptionen");
                if (!isDisplayed(getElement(orderPage.additionalOptions))) {
                    swipeDownTo(orderPage.additionalOptions);
                }
//        code only for real device
                getElement(orderPage.additionalOptions).click();
            }
        } catch (Exception e) {
            assertThat("Enter quantity failed : " + e.getMessage(), false, getScenario());
        }

    }

    @When("User clicks on Limithandel_Orderwindow")
    public void user_click_on_Limithandel() {

        logStep("User click on Limithandel");
        if (!isDisplayed(getElement(orderPage.limitTradeButton))) {
            swipeDownTo(orderPage.limitTradeButton);
        }
        getElement(orderPage.limitTradeButton).click();
    }

    @When("User clicks on Zusatzoptionen_Orderwindow")
    public void user_click_on_Zusatzoptionen() {
        logStep("User click on Zusatzoptionen");
        if (!isDisplayed(getElement(orderPage.additionalOptions))) {
            swipeDownTo(orderPage.additionalOptions);
        }
//        code only for real device
        getElement(orderPage.additionalOptions).click();
    }

    @When("User selects {string} in Orderzusatz_Orderwindow")
    public void user_selects_ordertype_on_Orderzusatz(String ordertyp) throws IOException, ParseException {
        logStep("selects " + ordertyp + " in Orderzusatz dropdown");
        try {
            option = getValueFromJson(ordertyp);
            if (!isDisplayed(orderPage.orderTypeSelector)) {
                swipeDownTo(orderPage.orderTypeSelector);
            }
            if (!orderPage.selectedOrderType.getText().equals(option)) {
                getElement(orderPage.orderTypeSelector).click();
                WebElement orderTypeElement = orderPage.getOrderTypeElement(option);
                getElement(orderTypeElement).click();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logStep("Element not found");
//            assertThat("Fail : " + e.getMessage(), false, getScenario());

        }

    }

    @When("User selects {string} in Ordergültigkeit_Orderwindow")
    public void user_selects_gueltigkeit_dropdown(String dataKey) throws IOException, ParseException {
        try {
            logStep("User selects " + dataKey + " in ordergueltigkeit dropdown");
            dateOption = getValueFromJson(dataKey);
            if (!isDisplayed(orderPage.expirationDateSelector)) {
                swipeDownTo(orderPage.expirationDateSelector);
            }
            getElement(orderPage.expirationDateSelector).click();
            WebElement orderValidityTypeElement = orderPage.getOrderValidityTypeElement(dateOption);
            orderValidityTypeElement.click();
        } catch (Exception e) {
            assertThat("Ordergültigkeit object identification failed : " + e.getMessage(), false, getScenario());
        }

    }

    @When("User clicks on WeiterZurTANEingabe_Orderwindow")
    public void user_click_on_WeiterZurTANEingabe() {
        try {
            logStep("User click on WeiterZurTANEingabe");
            swipeDownTo(orderPage.weiterZuTanEingabe);
            waitTillLoaded(orderPage.weiterZuTanEingabe, 60);
            getElement(orderPage.weiterZuTanEingabe).click();
        } catch (Exception e) {
            assertThat("WeiterZurTANEingabe object identification failed" + e.getMessage(), false, getScenario());
        }

    }

    @When("User clicks OK in HöhereRisikoklasse_Orderwindow")
    public void user_clicks_OK_in_HoehereRisikoklasse_Orderwindow() {
        dismissAlert();
    }

    @When("User verifies the presence of link Kostenausweis_TAN-Eingabe")
    public void verify_presenceof_Kostenausweis_link() {
        try {
            logStep("User verifies the presence of link Kostenausweis_TAN-Eingabe");
//        getElement(orderPage.orderConfirmButton);
//        tanPageExpiryDate = orderPage.getTanPageExpiryDate();
//        getElement(orderPage.tanPageExpirationDate);
            waitTillLoaded(orderPage.tanPageExpirationDate, 60);
            tanPageExpiryDate = getElement(orderPage.tanPageExpirationDate).getText();
            assertThat("kostenausweisLink is missing from TAN-Eingabe", getElement(orderPage.kostenausweisLink).isDisplayed(), getScenario());
        } catch (Exception e) {
            assertThat("link Kostenausweis_TAN-Eingabe object identification failed : " + e.getMessage(), false, getScenario());
        }

    }

    @When("User clicks on Kostenpflichtigkaufen_TAN-Eingabe")
    public void user_clicks_on_Kostenpflichtigkaufen() {
        try {
            logStep("User click on Kostenpflichtigkaufen button");
            waitTillLoaded(orderPage.orderConfirmButton, 60);
            getElement(orderPage.orderConfirmButton).click();
        }catch (Exception e){
            assertThat("Kostenpflichtigkaufen button object identification failed" + e.getMessage(), false, getScenario());
        }

    }

    @When("User clicks on Kostenpflichtigkaufen_Kostenpflichtigkaufen")
    public void user_clicks_Kostenpflichtigkaufen_Kostenpflichtigkaufen() {
        try {
            logStep("User click on Kostenpflichtigkaufen button");
            waitTillLoaded(orderPage.orderConfirmButton, 60);
            getElement(orderPage.orderConfirmButton).click();
        }catch (Exception e){
            assertThat("Kostenpflichtigkaufen button click failed : " + e.getMessage(), false, getScenario());
        }
    }

    @When("Capture order confirmation message on Order angenommen page")
    public void capture_order_confirmation_message_on_Order_angenommen_page() {
        try {
            logStep("Capture Message on confirmation screen");
            waitTillLoaded(orderPage.confirmationMessage, 20);
            confMessage = orderPage.getConfirmationMessage();
            successMsg = orderPage.getSuccessMessage();
            logStep("Message on confirmation screen" + confMessage);
            takeAScreenshot(getScenario());
        }catch (Exception e){
            assertThat("Capture order confirmation failed : " + e.getMessage(), false, getScenario());
        }

    }

    @When("Capture entered details on Order angenommen page including Ordernummer_Orderangenommen")
    public void capture_entered_details_on_confirmation_screen() {
        try {
            logStep("capture entered details on confirmation screen");
            typeOnConfirmPage = orderPage.getType();
            if (getScenario().getName() == "KaufOrder_Limithandel") {
                limitOnConfirmPage = orderPage.getLimit();
            }
            exchangeOnConfirmPage = orderPage.getExchange();
            expirationDateOnConfirmPage = orderPage.getExpirationDate();
            orderNumber = orderPage.getOrderNumberElmt();
            if (getScenarioName().equals("KaufOrder_TrailingStopBuyLimit_Absolut") || getScenarioName().equals("KaufOrder_TrailingStopBuyLimit_Prozentual") || getScenarioName().equals("KaufOrder_TrailingStopBuyMarket_Absolut") || getScenarioName().equals("KaufOrder_TrailingStopBuyMarket_Prozentual")) {
                distance = getElement(orderPage.distance).getText();
                trailingStopLimit = getElement(orderPage.trailingStopValue).getText();
                if (getScenarioName().equals("KaufOrder_TrailingStopBuyLimit_Prozentual")) {
                    tolerance = orderPage.getTolerance();
                }
            }
        }catch (Exception e){
            assertThat("confirmation page not displayed : " + e.getMessage(), false, getScenario());
        }
    }

    @When("User clicks on OrderInfo_OrderAngenommen")
    public void user_clicks_on_OrderInfo_button() {
        try {
            logStep("User clicks on OrderInfo button");
            if (!isDisplayed(orderPage.orderInfoButton)) {
                swipeDownTo(orderPage.orderInfoButton);
            }
            getElement(orderPage.orderInfoButton).click();
        }catch (Exception e){
            assertThat("OrderInfo button click failed" + e.getMessage(), false, getScenario());
        }

    }

    @Then("Verify Ordernummer_Orderangenommen,{string},{string},{string},{string} in OrderInfo_Depots")
    public void verify_on_OrderInfo(String Orderart, String wkn, String qty, String status) throws IOException, ParseException {

        try {
            logStep("Verify " + Orderart + "," + wkn + "," + qty + "," + status + " on OrderInfo");
            waitTillLoaded(orderInfo.orderInfoOrderNumber, 100);
            String type = getValueFromJson(Orderart);
            String wknValue = getValueFromJson(wkn);
            String qtyValue = getValueFromJson(qty);
            String statusValue = getValueFromJson(status);
            assertThat("Placed order should be displayed in order info screen", orderInfo.getOrderInfoOrderNumber(), equalTo(orderNumber), getScenario());
            assertThat("Order type in orderinfo is not match with given order type value", type, containsString(orderInfo.getOrderInfoOrderType()),getScenario());
            assertThat("wkn in orderinfo is not match with given wkn value", orderInfo.getOrderInfoWkn(), equalTo(wknValue), getScenario());
            assertThat("qty in orderinfo is not match with given qty value", orderInfo.getOrderInfoQuantity().replace(".", ""), equalTo(qtyValue.split(",")[0]), getScenario());
            assertThat("status in orderinfo is not match with given status value", orderInfo.getStatus(),containsString(statusValue), getScenario());
        } catch (TimeoutException e) {
            assertThat("order info page is not loading" + e.getMessage(), false, getScenario());
        }

    }

    @Then("Verify captured details,{string} from Order angenommen page")
    public void verify_captured_details_Message_from_Bestätigung_screen(String message) throws IOException, ParseException {
        try {
            logStep("Verify captured details,Message from Order angenommen page");
            assertThat("wkn is missing from confirmation message", confMessage, containsString("WKN " + wknValue), getScenario());
            assertThat("qty is missing from confirmation message", confMessage.replace(".", ""), containsString(quantityValue), getScenario());
            assertThat("success message is missing from confirmation message", successMsg, containsStringIgnoringCase(getValueFromJson(message)), getScenario());
            assertThat("ordertype is not correct in order confirmation page", typeOnConfirmPage, containsStringIgnoringCase(option), getScenario());

            if (getScenarioName() == "KaufOrder_Limithandel") {
                assertThat("limit is not correct in order confirmation page", limitOnConfirmPage, containsString(limit), getScenario());
            }
            assertThat("Exchange is not correct in order confirmation page", exchangeOnConfirmPage, equalTo(exchangeValue), getScenario());
            assertThat("Expiration date is not correct in order confirmation page", expirationDateOnConfirmPage, containsStringIgnoringCase(tanPageExpiryDate), getScenario());

            if (getScenarioName().equals("KaufOrder_TrailingStopBuyLimit_Absolut") || getScenarioName().equals("KaufOrder_TrailingStopBuyLimit_Prozentual") || getScenarioName().equals("KaufOrder_TrailingStopBuyMarket_Absolut") || getScenarioName().equals("KaufOrder_TrailingStopBuyMarket_Prozentual")) {
                assertThat("Trailing Stop limit in confirmation screen not match with entered value", trailingStopLimit, containsString(getValueFromJson("StopBuy")), getScenario());
                assertThat("Trailing Stop distance in confirmation screen not match with entered value", distance, containsString(getValueFromJson("Abstand")), getScenario());
                if (getScenarioName().equals("KaufOrder_TrailingStopBuyLimit_Prozentual")) {
                    assertThat("Trailing Stop tolerance in confirmation screen not match with entered value", tolerance, containsString(getValueFromJson("ToleranznachStop")), getScenario());
                }
            }
        }catch (TimeoutException e){
            assertThat("Captured details verification failed" + e.getMessage(), false, getScenario());
        }

    }

    @When("User clicks on row for {string} were NominalStatus_OrderInfo is Offen")
    public void user_clicks_on_row_for_were_NominalStatus_OrderInfo_is_Offen(String wkn) throws IOException, ParseException {
        try {
            String valueFromJson = getValueFromJson(wkn);
            waitTillLoaded(orderInfo.orderInfoOrderNumber, 60);

            int maxScrollCount = 0;
            for (int i = 0; i <= orderInfo.orderInfoOrderNumberList.size() - 1; i++) {
                if (orderInfo.orderStatusList.get(i).getText().equals("Offen") && orderInfo.orderInfowknList.get(i).getText().equals(valueFromJson)) {
                    orderInfo.orderStatusList.get(i).click();
                    break;
                } else {
                    if (i == orderInfo.orderInfoOrderNumberList.size() - 1) {
                        if (maxScrollCount == 3) {
                            assertThat("Open order with specific security not showing in orderinfo", false, getScenario());
                            break;
                        }
                        scrollDown(getDriver());
                        maxScrollCount = maxScrollCount + 1;
                        i = 0; // in ios after scrolling list index count is reset to zero
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            assertThat("Open order with specific security not showing in orderinfo" + e.getMessage(), false, getScenario());
        }

    }
}
