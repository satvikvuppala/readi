package com.bnpp.appium.teststeps.elements;

import com.bnpp.appium.teststeps.BaseTestCase;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITBy;
import io.appium.java_client.pagefactory.iOSXCUITFindAll;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.cucumber.java.Scenario;

import static com.bnpp.appium.config.Constants.ANDROID_PACKAGE_NAME;

public class OrderEditDeletePage extends BaseTestCase {

    public OrderEditDeletePage(Scenario scenario) {
        super.init(scenario);
    }

    @AndroidFindBy(id = "order_confirmation_imageview_cancel")
    @iOSXCUITFindBy(accessibility = "#ta_close_button")
    public MobileElement closeButton;

    @AndroidFindBy(id = "order_details_limit_value")
    @iOSXCUITFindBy(accessibility = "#order_change_limit_value")
    public MobileElement limitField;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"continue_button")
    @iOSXCUITFindBy(id = "#ta_tan_summary_execute_button")
    public MobileElement weiterZuTanEingabeOrderEdit;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"btn_sca_execute_button")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"#ta_skip_to_tan_button\"]")
//    @iOSXCUITFindBy(id = "#ta_skip_to_tan_button")
    public MobileElement orderModify;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"btn_sca_execute_button")
//    @iOSXCUITFindBy(id = "#ta_skip_to_tan_button")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"#ta_skip_to_tan_button\"]")
    public MobileElement orderDelete;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"order_confirmation_success_text")
    @iOSXCUITFindBy(accessibility = "#order_confirmation_success_text")
    public MobileElement orderChangeSuccessMsg;

    @AndroidFindBy(id = "order_confirmation_number")
    @iOSXCUITFindAll({@iOSXCUITBy(accessibility = "#order_confirmation_number"), @iOSXCUITBy(accessibility = "order_confirmation_number")})
    public MobileElement orderChangeConfirmOrderNo;

    @AndroidFindBy(id = "order_confirmation_limit")
    @iOSXCUITFindAll({@iOSXCUITBy(accessibility = "#order_confirmation_limit"), @iOSXCUITBy(accessibility = "order_confirmation_limit")})
    public MobileElement orderChangeConfirmLimitValue;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"calculate_cost_link")
    @iOSXCUITFindBy(id = "Kostenausweis")
    public MobileElement kostenausweisLink;


    public String getOrderChangeSuccessMsg() {
        return orderChangeSuccessMsg.getText();
    }

    public String getOrderChangeConfirmLimitValue() {
        return orderChangeConfirmLimitValue.getText();
    }

    public String getOrderChangeConfirmOrderNo() {
        return orderChangeConfirmOrderNo.getText();
    }

}
