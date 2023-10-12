package com.bnpp.appium.teststeps.steps;

import com.bnpp.appium.teststeps.BaseTestCase;
import com.bnpp.appium.teststeps.elements.AddMoney;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import static com.bnpp.appium.CBMatcherAssert.assertThat;
import static com.bnpp.appium.utils.Utils.*;

public class Demo_Pay2All_Add_Money extends BaseTestCase {
    private final static Logger LOGGER = Logger.getLogger(Demo_Pay2All_Add_Money.class);
    AddMoney addMoney = new AddMoney(getScenario());


    @Before
    public void init(Scenario scenario) {
        super.init(scenario);
    }

    @Given("User Open Pay2AllDemo mobile application")
    public void user_Open_Pay2AllDemo_mobile_application() {
        logStep("User opens Pay2AllDemo mobile application");
    }

    @When("User enters {string} in User_Name_Field")
    public void user_enters_in_User_Name_Field(String dataKey) {
        try {
            enterText(getValueFromJson(dataKey), addMoney.userName);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @When("User enters {string} in Password_Field")
    public void user_enters_in_Password_Field(String dataKey) {
        try {
            enterText(getValueFromJson(dataKey), addMoney.Password);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @When("User clicks on Login_Button")
    public void user_clicks_on_Login_Button() {
        try {
            logStep("Clicking on login button");
            getElement(addMoney.loginButton).click();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @When("User clicks on addMoney")
    public void user_clicks_on_addMoney() {
        try {
            Thread.sleep(5000);
            logStep("Clicking on add money button");
            getElement(addMoney.addMoney).click();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @When("User selects {string} in bank_name_field")
    public void user_selects_in_bank_name_field(String dataKey) {
        try {
            Thread.sleep(5000);
            logStep("Clicking on select bank option");
            getElement(addMoney.selectBank).click();
            getElement(addMoney.bankName).click();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @When("User enters {string} in amount_Field")
    public void user_enters_in_amount_Field(String dataKey) {
        try {
            enterText(getValueFromJson(dataKey), addMoney.amount);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @When("User enters {string} in ref_Field")
    public void user_enters_in_ref_Field(String dataKey) {
        try {
            enterText(getValueFromJson(dataKey), addMoney.refNo);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @When("User selects {string} in PaymentMode_field")
    public void user_selects_in_PaymentMode_field(String string) {
        try {
            logStep("selecting payment mode");
            getElement(addMoney.paymentMode).click();
            getElement(addMoney.paymentModeOption).click();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @When("user selects payment date")
    public void user_selects_payment_date() {
        try {
            logStep("selecting payment date");
            getElement(addMoney.paymentDate).click();
            getElement(addMoney.ok).click();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @When("User clicks on proceed")
    public void user_clicks_on_proceed() {
        try {
            logStep("clicking on proceed button");
            getElement(addMoney.proceed).click();
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    @Then("User verify {string} of addMoneyRequest")
    public void userVerifyOfAddMoneyRequest(String dataKey) {
        try {
            logStep("verifying text message");
            if(isDisplayed(getElement(addMoney.failMessage))) {
                String textMessage = getElement(addMoney.failMessage).getText();
                System.out.println(textMessage);
                logStep("Failed to add money");
            } else {
                String textMessage = getElement(addMoney.successMessage).getText();
                System.out.println(textMessage);
                if (getValueFromJson(dataKey).equals(textMessage)) {
                    logStep("Money added Succeesfully");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
