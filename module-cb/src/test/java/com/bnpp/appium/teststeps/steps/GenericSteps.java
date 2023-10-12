package com.bnpp.appium.teststeps.steps;

import com.bnpp.appium.config.Configurations;
import com.bnpp.appium.cucumber.CucumberTest;
import com.bnpp.appium.teststeps.BaseTestCase;
import com.bnpp.appium.teststeps.elements.LoginPage;
import com.bnpp.appium.teststeps.elements.OrderPage;
import com.bnpp.appium.xray.Log;
import com.bnpp.appium.xray.XrayHelper;
import com.dab.xray.XRAY_CONFIG;
import com.dab.xray.Xray;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

import static com.bnpp.appium.CBMatcherAssert.assertThat;
import static com.bnpp.appium.utils.Utils.*;


public class GenericSteps extends BaseTestCase {
    OrderPage orderPage = new OrderPage(getScenario());
    String XrayIssueKey = "";
    String securePwd;
    String[] loginDetails;

    /**
     * @param scenario Initialization before starting of each scenario execution
     */
    @Before
    public void init(Scenario scenario) {
        super.init(scenario);
        System.out.println("Before - " + scenario.getName());
        setfeaturefilenameandsceanrio(scenario.getId(), scenario.getName());
        setScenario(scenario);
        checkNewTest(scenario);
    }

    /**
     * @param scenario Closing the resources after execution of each scenario
     */
    @After
    public void after(Scenario scenario) {
        System.out.println("After - " + scenario.getName());
        if(Configurations.EnableXray.equalsIgnoreCase("Y")) {
            saveTestResultsToXray(scenario);
        }
//        getDriver().closeApp();
    }

    private void checkNewTest(Scenario s) {
        XrayIssueKey = XrayHelper.getTestIdFromFileName(s.getId());

        if (!CucumberTest.currentXrayIssueKey.contains(XrayIssueKey)) {
            System.out.println("This is a new Feature!");
            CucumberTest.currentXrayIssueKey = XrayIssueKey;
            CucumberTest.featureTestPassed = true;
        }
    }

    private void saveTestResultsToXray(Scenario s) {

        ZonedDateTime finishDateTime = ZonedDateTime.now();
        String testFinish = finishDateTime.truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        Log.info("Test Finish Time: " + testFinish);
        if (s.isFailed()) {
            Log.error("Test Failed!");
            CucumberTest.featureTestPassed = false;
            Xray.writeResultsForSingleTest(CucumberTest.ExecutionID, XrayIssueKey, XRAY_CONFIG.TEST_STATUS_FAIL,
                    CucumberTest.testStart, testFinish);
        } else {
            if (CucumberTest.featureTestPassed == true) {
                Log.info("Test Passed!");
                Xray.writeResultsForSingleTest(CucumberTest.ExecutionID, XrayIssueKey, XRAY_CONFIG.TEST_STATUS_PASS,
                        CucumberTest.testStart, testFinish);
            }
        }
    }


    @Given("User logins into Consorsbank mobile application with {string}")
    public void user_Logs_in_Consorsbank_application_with_valid_credentials(String tradingUser) {
        try{
            getDriver().launchApp();

            logStep("User Logs in Consorsbank mobile application with given credentials");
            if(isAndroid()){
                loginDetails = getProp().getArrayProperty(tradingUser+"_android");
            }else{
                loginDetails = getProp().getArrayProperty(tradingUser+"_ios");
            }

            String userName = loginDetails[0];
            String pwd = loginDetails[1];
            securePwd = loginDetails[2];
            LoginPage loginPage = new LoginPage(getScenario());
            loginPage.loginAs(userName, pwd, securePwd);
        }catch (Exception e){
            assertThat("Login failed  : " + e.getMessage(), false, getScenario());
        }

    }

    @When("User does the tan authentication")
    public void user_enters_secure_password() {
        try{
            logStep("User enters secure password");
            String secure_auth = getProp().getProperty("secure_auth");
            if (secure_auth.equals("ON")) {
                //code for secure plus navigation
                swipeDownTo(orderPage.securePlusAppButton);
                LoginPage loginPage = new LoginPage(getScenario());
                loginPage.securePlusLogin(securePwd, orderPage.securePlusAppButton);
            } else {
                //Code for fixed OTP(simulator)
                swipeDownTo(orderPage.enterTanManuallyButton);
                orderPage.enterTanManuallyButton.click();
                enterText(getProp().getProperty("fixed_secure_tan"), orderPage.enterTanField);
            }
        }catch (Exception e){
            assertThat("TAN authentication failed" + e.getMessage(), false, getScenario());
        }

    }
//    @When("User clicks on TANmanuelleingeben_TAN-Eingabe")
//    public void user_clicks_on_TANmanuelleingeben_TAN_Eingabe() {
//        swipeDownTo(getElement(orderPage.enterTanManuallyButton));
//        orderPage.enterTanManuallyButton.click();
//    }
//    @When("User enters tan in TAN_Kostenpflichtigkaufen")
//    public void user_enters_tan_in_TAN_Kostenpflichtigkaufen() {
//        enterText(getProp().getProperty("fixed_secure_tan"),orderPage.enterTanField);
//    }


}
