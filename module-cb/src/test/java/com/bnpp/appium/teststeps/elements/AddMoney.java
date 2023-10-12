package com.bnpp.appium.teststeps.elements;

import com.bnpp.appium.teststeps.BaseTestCase;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.cucumber.java.Scenario;
import org.apache.log4j.Logger;

public class AddMoney extends BaseTestCase {
    private final static Logger LOGGER = Logger.getLogger(AddMoney.class);

    @AndroidFindBy(id = "demo.bceres:id/edittext_username")
    public MobileElement userName;

    @AndroidFindBy(id = "demo.bceres:id/edittext_password")
    public MobileElement Password;

    @AndroidFindBy(id = "demo.bceres:id/button_login")
    public MobileElement loginButton;

    @AndroidFindBy(id = "demo.bceres:id/ll_add_money")
    public MobileElement addMoney;

    @AndroidFindBy(id = "demo.bceres:id/textview_bankname")
    public MobileElement selectBank;

    @AndroidFindBy(id = "android:id/title")
    public MobileElement bankName;

    @AndroidFindBy(id = "demo.bceres:id/edittext_fundrequest_amount")
    public MobileElement amount;

    @AndroidFindBy(id = "demo.bceres:id/edittext_fundrequest_bankreferencenumber")
    public MobileElement refNo;

    @AndroidFindBy(id = "android:id/text1")
    public MobileElement paymentMode;

    @AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='IMPS']")
    public MobileElement paymentModeOption;

    @AndroidFindBy(id = "demo.bceres:id/rl_deposit_date")
    public MobileElement paymentDate;

    @AndroidFindBy(id = "android:id/button1")
    public MobileElement ok;

    @AndroidFindBy(id = "demo.bceres:id/button_fundrequest_proceed_to_pay")
    public MobileElement proceed;

    @AndroidFindBy(id = "demo.bceres:id/textView_review")
    public MobileElement successMessage;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='FAILED']")
    public MobileElement failMessage;

    public AddMoney(Scenario scenario) {
        super.init(scenario);
    }
}
