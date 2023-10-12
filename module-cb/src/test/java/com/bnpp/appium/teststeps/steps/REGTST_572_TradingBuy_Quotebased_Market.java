package com.bnpp.appium.teststeps.steps;

import com.bnpp.appium.CBMatcherAssert;
import com.bnpp.appium.teststeps.BaseTestCase;
import com.bnpp.appium.teststeps.elements.LoginPage;
import com.bnpp.appium.teststeps.elements.OrderPage;
import com.bnpp.appium.utils.Utils;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.simple.parser.ParseException;
import static com.bnpp.appium.CBMatcherAssert.assertThat;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.bnpp.appium.utils.Utils.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class REGTST_572_TradingBuy_Quotebased_Market extends BaseTestCase {
    OrderPage orderPage = new OrderPage(getScenario());

    @Before
    public void init(Scenario scenario) {
        super.init(scenario);

    }

    @When("User clicks on Kursanfordern_Orderwindow")
    public void user_clicks_on_Kursanfordern_Orderwindow() {
        try {
            if (!isDisplayed(orderPage.requestQuoteButton)) {
                swipeDownTo(orderPage.requestQuoteButton);
            }
            getElement(orderPage.requestQuoteButton).click();

//        //TODO Replace with wait till progressbar is dismissed
//        while (!orderPage.requestQuoteButton.isEnabled()) {
//            // Wait for quote to update
//        }
        }catch (Exception e){
            CBMatcherAssert.assertThat("Kursanfordern button click failed" + e.getMessage(), false, getScenario());
        }

    }

    @Then("verify the {string} displayed")
    public void verify_the_displayed(String string) throws IOException, ParseException, InterruptedException {
//        getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Thread.sleep(10000);
//        acceptAlert();
        CBMatcherAssert.assertThat("Error message is not displayed", acceptAlert(getValueFromJson(string)), getScenario());

    }

    @When("User clicks on Session-TANaktivieren_Orderwindow")
    public void user_clicks_on_Session_TANaktivieren_Orderwindow() {
        getElement(orderPage.sessionTanButton).click();
    }

    @When("User selects checkbox Ja,mirsind_Session-Tan_Session-TANaktivieren")
    public void user_selects_checkbox_Ja_mirsind_Session_Tan_Session_TANaktivieren() {
        try {
            waitTillLoaded(orderPage.sessionTanAcceptPage, 10);
            orderPage.sessionTanAcceptCheckbox.click();
        }catch (Exception e){
            assertThat("checkbox selection failed : " + e.getMessage(), false, getScenario());
        }

    }

    @When("User clicks on SecurePlusAppöffnen_Session-TANaktivieren")
    public void user_clicks_on_SecurePlusAppoeffnen_Session_TANaktivieren() {
        LoginPage loginPage = new LoginPage(getScenario());
        loginPage.securePlusLogin("123456", orderPage.securePlusAppButton);
    }

    @When("User clicks on Session-TANactivieren_Session-TANaktivieren")
    public void user_clicks_on_Session_TANactivieren_Session_TANaktivieren() {
        getElement(orderPage.orderConfirmButton).click();
    }

    @When("User clicks on OK_Bestätigung")
    public void user_clicks_on_OK_Bestoetigung() {
        getElement(orderPage.sessionTanOk).click();
        getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        acceptAlert();
        dismissAlert();
    }

    @When("User clicks on Kostenpflichtigsofort-kaufen_Orderwindow")
    public void user_clicks_on_Kostenpflichtigsofort_kaufen_Orderwindow() throws IOException, ParseException {
        try {
            logStep("User clicks on Kostenpflichtigsofort-kaufen_Orderwindow");
            String ErrorMessage = "Hinweis\nDer Handel konnte nicht abgeschlossen werden, da der Kauf-Kurs zu hoch im Vergleich zum gewünschten Kurs ist.";
            int loopCount = 0;
            while (!orderPage.continueToTanPage(ErrorMessage)) {
                orderPage.requestQuote();
                String myQuote = orderPage.getMyQuote();
                logStep("myQuote-------" + myQuote);
                if (loopCount == 4) {
                    assertThat("Quote value is not get updated, so can't place order", false,getScenario());
                    break;
                }
                loopCount = loopCount + 1;
            }
        }catch (Exception e){
            assertThat("Kostenpflichtigsofort click failed : " + e.getMessage(), false, getScenario());
        }


    }

}