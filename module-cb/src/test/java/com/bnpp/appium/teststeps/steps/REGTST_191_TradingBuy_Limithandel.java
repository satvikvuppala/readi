package com.bnpp.appium.teststeps.steps;

import com.bnpp.appium.teststeps.BaseTestCase;
import com.bnpp.appium.teststeps.elements.OrderEditDeletePage;
import com.bnpp.appium.teststeps.elements.OrderInfoPage;
import com.bnpp.appium.teststeps.elements.OrderPage;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import static com.bnpp.appium.CBMatcherAssert.assertThat;
import static com.bnpp.appium.utils.Utils.*;
import static org.hamcrest.Matchers.*;

public class REGTST_191_TradingBuy_Limithandel extends BaseTestCase {
    private final static Logger LOGGER = Logger.getLogger(REGTST_191_TradingBuy_Limithandel.class);
    String newLimit;
    String successMsg;
    String orderModifySuccessMsg;
    String orderChangeConfirmLimitValue;
    String orderChangeConfirmOrderNo;
    String limit;
    OrderEditDeletePage orderEditDeletePage = new OrderEditDeletePage(getScenario());
    OrderInfoPage orderInfo = new OrderInfoPage(getScenario());
    OrderPage orderPage = new OrderPage(getScenario());

    @Before
    public void init(Scenario scenario) {
        super.init(scenario);

    }

    @When("User enters {string} in Limit_Orderwindow")
    public void enter_Limit_in_limit_field(String dataKey) throws IOException, ParseException {
        try {
            logStep("User enters " + dataKey + " in limit field");
            limit = getValueFromJson(dataKey);
            if (!isDisplayed(orderPage.limitField)) {
                swipeDownTo(orderPage.limitField);
            }
            getElement(orderPage.limitField).click();
            orderPage.limitField.setValue(limit);
            if(isAndroid()){
                getDriver().hideKeyboard();
            }else {
                orderPage.dismissKeyBoard();
            }

        }catch (Exception e){
            assertThat("Limit enter failed : " + e.getMessage(), false, getScenario());
        }

    }

    @When("User clicks on Depot_MeineFinanzen")
    public void user_clicks_on_Depot_MeineFinanzen() {
        try {
            logStep("User clicks on Depot_MeineFinanzen");
            waitTillLoaded(orderPage.depot, 60);
            getElement(orderPage.depot).click();
        }catch (Exception e){
            assertThat("Depot click failed : " + e.getMessage(), false, getScenario());
        }

    }

    @When("User navigates to Orderinfo_Depots")
    public void user_navigates_to_Orderinfo_Depots() {
        try{
            logStep("User navigates to Orderinfo_Depots");
            waitTillLoaded(orderInfo.orderInfoMenu, 60);
            getElement(orderInfo.orderInfoMenu).click();
        }catch (Exception e){
            assertThat("order info menu is not showing : " + e.getMessage(), false, getScenario());
        }
    }


    @When("User verifies {string} in Orderzusatz_Orderinfos,{string} in Kaufvon_Orderinfos,{string} in Handelsplatz_Orderinfos and {string} in Orderzusatz_Orderinfos")
    public void user_verifies_in_Orderzusatz_Orderinfos_in_Kaufvon_Orderinfos_in_Handelsplatz_Orderinfos_and_in_Orderzusatz_Orderinfos(String string, String string2, String string3, String string4) throws IOException, ParseException {
        try {
            logStep("Order info page");
            waitTillLoaded(orderInfo.orderDetailOrderType, 10);
            assertThat("Orderzusatz is not correct in orderinfo page", orderInfo.getOrderDetailOrderType(), containsString(getValueFromJson(string)), getScenario());
            assertThat("Kaufvon is not correct in orderinfo page", orderInfo.getOrderDetailOrderart(), containsString(getValueFromJson(string2)), getScenario());
            assertThat("Handelplatz is not correct in orderinfo page", orderInfo.getOrderDetailExchange(), equalTo(getValueFromJson(string3)), getScenario());
            assertThat("Limit is not correct in orderinfo page", orderInfo.getOrderDetailOrderType(), containsString(getValueFromJson(string4)), getScenario());
        }catch (Exception e){
            assertThat("Verification failed : " + e.getMessage(), false, getScenario());
        }
    }

    @When("User clicks on Limitändern_Orderinfos")
    public void user_clicks_on_Limitändern_Orderinfos() {
        try {
            if (!isDisplayed(orderInfo.editButton)) {
                swipeDownTo(orderInfo.editButton);
            }
            getElement(orderInfo.editButton).click();
        }catch (Exception e){
            assertThat("Limitändern click failed : " + e.getMessage(), false, getScenario());
        }
    }

    @When("User enters {string} in Limit_OrderändernWindow")
    public void user_enters_in_Limit_OrderändernWindow(String limit) throws IOException, ParseException {
        try {
            waitTillLoaded(orderEditDeletePage.limitField, 10);
            newLimit = getValueFromJson(limit);
            logStep("User enters " + newLimit + " in Limit_OrderändernWindow");
            if (!isDisplayed(orderEditDeletePage.limitField)) {
                swipeDownTo(orderEditDeletePage.limitField);
            }
            orderEditDeletePage.limitField.click();
            orderEditDeletePage.limitField.setValue(newLimit);
            if(isAndroid()){
                getDriver().hideKeyboard();
            }else{
                tapMiddleOfScreen();
            }

        }catch (Exception e){
            assertThat("Enter limit failed : " + e.getMessage(), false, getScenario());
        }

    }

    @When("User clicks on WeiterZurTANEingabe_OrderändernWindow")
    public void user_clicks_on_WeiterZurTANEingabe_OrderändernWindow() {
        try {
            logStep("User click on WeiterZurTANEingabe");
            getElement(orderEditDeletePage.weiterZuTanEingabeOrderEdit).click();
        }catch (Exception e){
            assertThat("WeiterZurTANEingabe button click failed : " + e.getMessage(), false, getScenario());
        }

    }

    @When("User verifies the presence of link Kostenausweis_OrderändernConfirmationWindow")
    public void user_verifies_the_presence_of_link_Kostenausweis_OrderändernConfirmationWindow() {
        try {
            waitTillLoaded(orderEditDeletePage.kostenausweisLink, 10);
            logStep("User verifies the presence of link Kostenausweis_OrderändernConfirmationWindow");
            assertThat("kostenausweisLink is missing from TAN-Eingabe", orderEditDeletePage.kostenausweisLink.isDisplayed(), getScenario());
        }catch (Exception e){
            assertThat("link Kostenausweis verification failed : " + e.getMessage(), false, getScenario());
        }
    }

    @When("User clicks on TANmanuelleingeben_OrderändernConfirmationWindow")
    public void user_clicks_on_TANmanuelleingeben_OrderändernConfirmationWindow() {
        swipeDownTo(orderPage.enterTanManuallyButton);
        getElement(orderPage.enterTanManuallyButton).click();
    }


    @When("User enters tan in TAN_OrderändernTANwindow")
    public void user_enters_tan_in_TAN_OrderändernTANwindow() {
        try{
            enterText(getProp().getProperty("fixed_secure_tan"), orderPage.enterTanField);
        }catch (Exception e){
            assertThat("Enter tan failed : " + e.getMessage(), false, getScenario());
        }
    }

    @When("User clicks Orderändern_OrderändernTANwindow")
    public void user_clicks_Orderändern_OrderändernTANwindow() {
        logStep("User clicks on Orderändern_OrderändernConfirmationWindow");
//        waitTillLoaded(orderEditDeletePage.orderModify, 10);
//        swipeDownTo(getElement(orderEditDeletePage.orderModify));
        getElement(orderEditDeletePage.orderModify).click();
    }

    @When("Capture order modify confirmation message on Order angenommen page")
    public void capture_Message_on_modify_confirmation_screen() {
        try{
            logStep("Capture Message on modify confirmation screen");
            waitTillLoaded(orderEditDeletePage.orderChangeSuccessMsg, 120);
            orderModifySuccessMsg = orderEditDeletePage.getOrderChangeSuccessMsg();
            logStep("Message on modify confirmation screen" + orderModifySuccessMsg);
        }catch (Exception e){
            assertThat("order confirmation message verification failed" + e.getMessage(), false, getScenario());
        }
    }

    @When("Capture entered limit_Orderangenommen and Ordernummer_Orderangenommen")
    public void capture_entered_limit_Orderangenommen_and_Ordernummer_Orderangenommen() {
        try{
            logStep("Capture entered limit_Orderangenommen and Ordernummer_Orderangenommen");
            orderChangeConfirmLimitValue = orderEditDeletePage.getOrderChangeConfirmLimitValue();
            orderChangeConfirmOrderNo = orderEditDeletePage.getOrderChangeConfirmOrderNo();
            takeAScreenshot(getScenario());
        }catch (Exception e){
            assertThat("Failed to capture details : " + e.getMessage(), false, getScenario());
        }


    }

    @When("User close orderangenommen page")
    public void user_close_orderangenommen_page() {
        try {
            logStep("User close orderangenommen page");
            orderEditDeletePage.closeButton.click();
        }catch (Exception e){
            assertThat("Close button identification failed" + e.getMessage(), false, getScenario());
        }

    }

    @Then("Verify {string} in limit_Orderangenommen and Ordernummer_Orderangenommen in Orderinfo_depots page")
    public void verify_in_limit_Orderangenommen_and_Ordernummer_Orderangenommen_in_OrderInfo_Depots(String string) throws IOException, ParseException {
        try{
            logStep("Verify Ordernummer in Orderinfo");
            waitTillLoaded(orderInfo.orderInfoOrderNumber, 10);
            assertThat("New Limit value is not correct in Orderangenommen page", orderChangeConfirmLimitValue, containsString(getValueFromJson(string)), getScenario());
            for (int i = 0; i <= orderInfo.orderInfoOrderNumberList.size() - 1; i++) {
                if (orderInfo.orderInfoOrderNumberList.get(i).getText().equals(orderChangeConfirmOrderNo)) {
                    assertThat("Order number is not correct in orderinfo Expected : " + orderInfo.orderInfoOrderNumberList.get(i).getText() + " Actual : " + orderChangeConfirmOrderNo, true, getScenario());
                    logStep("Order number is not correct in orderinfo Expected : " + orderInfo.orderInfoOrderNumberList.get(i).getText() + " Actual : " + orderChangeConfirmOrderNo);
                    break;
                }
                if (i == orderInfo.getOrderInfoOrderNumber().length() - 1) {
                    assertThat("Order number is not correct in orderinfo", false, getScenario());
                }
            }
            takeAScreenshot(getScenario());
        }catch (Exception e){
            assertThat("Verification failed : " + e.getMessage(), false, getScenario());
        }

    }

    @Then("Verify {string} from Order angenommen page")
    public void verify_from_Order_angenommen_page(String string) throws IOException, ParseException {
        assertThat("success message is missing from modify confirmation message", orderModifySuccessMsg, containsStringIgnoringCase(getValueFromJson(string)), getScenario());
    }

    @When("User clicks on Orderlöschen_Orderinfos")
    public void user_clicks_on_Orderloeschen_Orderinfos() {
        try{
            logStep("User clicks on Orderlöschen_Orderinfos");
            if (!isDisplayed(orderInfo.deleteButton)) {
                swipeDownTo(orderInfo.deleteButton);
            }
            getElement(orderInfo.deleteButton).click();
        }catch (Exception e){
            assertThat("Orderlöschen button click failed " + e.getMessage(), false, getScenario());
        }

    }

    @When("User clicks on TANmanuelleingeben_Orderlöschen")
    public void user_clicks_on_TANmanuelleingeben_Orderloeschen() {
        swipeDownTo(orderPage.enterTanManuallyButton);
        getElement(orderPage.enterTanManuallyButton).click();
    }

    @When("User enters tan in TAN_OrderlöschenTANentrypage")
    public void user_enters_tan_in_TAN_OrderloeschenTANentrypage() {
        try{
            enterText(getProp().getProperty("fixed_secure_tan"), orderPage.enterTanField);
        }catch (Exception e){
            assertThat("Enter TAN failed : " + e.getMessage(), false, getScenario());
        }
    }

    @When("User clicks on Orderlöschen_OrderlöschenTANentrypage")
    public void user_clicks_on_Orderloeschen_OrderloeschenTANentrypage() {
        try{
            logStep("User click on Kostenpflichtigkaufen button");
            waitTillLoaded(orderEditDeletePage.orderDelete, 60);
            getElement(orderEditDeletePage.orderDelete).click();
        }catch (Exception e){
            assertThat("order info menu is not showing : " + e.getMessage(), false, getScenario());
        }
    }


    @When("User clicks on Orderlöschen_Orderlöschen")
    public void user_clicks_on_Orderloeschen_Orderloeschen() {
        try {
            logStep("User clicks on Orderlöschen_Orderlöschen");
            waitTillLoaded(orderEditDeletePage.orderDelete, 20);
            swipeDownTo(orderEditDeletePage.orderDelete);
            orderEditDeletePage.orderDelete.click();
        }catch (Exception e){
            assertThat("Orderlöschen button click failed : " + e.getMessage(), false, getScenario());
        }

    }

    @When("Capture Ordernummer_Orderangenommen and order cancelation confirmation message on Order angenommen page")
    public void capture_Ordernummer_Orderangenommen_and_order_cancelation_confirmation_message_on_Order_angenommen_page() {
        try {
            logStep("Capture Ordernummer_Orderangenommen and order cancelation confirmation message on Order angenommen page");
            waitTillLoaded(orderEditDeletePage.orderChangeSuccessMsg, 60);
            successMsg = orderEditDeletePage.getOrderChangeSuccessMsg();
            orderChangeConfirmOrderNo = orderEditDeletePage.getOrderChangeConfirmOrderNo();
            orderModifySuccessMsg = orderEditDeletePage.getOrderChangeSuccessMsg();
            takeAScreenshot(getScenario());
        }catch (Exception e){
            assertThat("Failed to capture details : " + e.getMessage(), false, getScenario());
        }
    }

    @When("User verifies the presence of link Kostenausweis_Orderangenommen")
    public void user_verifies_the_presence_of_link_Kostenausweis_Orderangenommen() {
        try {
            logStep("User verifies the presence of link Kostenausweis_Orderangenommen");
            if (!isDisplayed(orderEditDeletePage.kostenausweisLink)) {
                swipeDownTo(orderEditDeletePage.kostenausweisLink);
            }
            assertThat("kostenausweisLink is missing from Kostenausweis_Orderangenommen", orderEditDeletePage.kostenausweisLink.isDisplayed(), getScenario());
        }catch (Exception e){
            assertThat("Kostenausweis link identification failed" + e.getMessage(), false, getScenario());
        }

    }

    @Then("verify {string}in NominalStatus_Orderinfo and Ordernummer_Orderangenommen in Orderinfo_depots page")
    public void verify_NominalStatus_in_NominalStatus_Orderinfo_and_Ordernummer_Orderangenommen_in_Orderinfo_depots_page(String string) throws IOException, ParseException {
        try {
            logStep("Verify Ordernummer in Orderinfo");
            waitTillLoaded(orderInfo.orderInfoOrderNumber, 30);

            for (int i = 0; i <= orderInfo.orderInfoOrderNumberList.size() - 1; i++) {
                if (orderInfo.orderInfoOrderNumberList.get(i).getText().equals(orderChangeConfirmOrderNo) && orderInfo.orderStatusList.get(i).getText().equals(getValueFromJson(string))) {
                    assertThat("Order number and Nominal status is not correct in orderinfo Expected : " + orderInfo.orderInfoOrderNumberList.get(i).getText() + " Actual : " + orderChangeConfirmOrderNo, true, getScenario());
                    logStep("Order number is not correct in orderinfo Expected : " + orderInfo.orderInfoOrderNumberList.get(i).getText() + " Actual : " + orderChangeConfirmOrderNo);
                    break;
                }
            }
            takeAScreenshot(getScenario());
        }catch (Exception e){
            assertThat("Verification failed : " + e.getMessage(), false, getScenario());
        }

    }
}
