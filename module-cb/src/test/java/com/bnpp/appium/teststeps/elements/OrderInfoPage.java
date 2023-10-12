package com.bnpp.appium.teststeps.elements;

import com.bnpp.appium.teststeps.BaseTestCase;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITBy;
import io.appium.java_client.pagefactory.iOSXCUITFindAll;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.bnpp.appium.config.Constants.ANDROID_PACKAGE_NAME;

public class OrderInfoPage extends BaseTestCase {

    public OrderInfoPage(Scenario scenario) {
        super.init(scenario);
    }

    @AndroidFindBy(id = "orderinfo_list_item_status")
    @iOSXCUITFindBy(accessibility = "#orderinfo_list_item_status")
    public List<WebElement> orderStatusList;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"orderinfo_list_item_status")
    @iOSXCUITFindBy(accessibility = "#orderinfo_list_item_status")
    public MobileElement status;



    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Orderinfo\"]")
    @iOSXCUITFindBy(accessibility = "Orderinfo")
    public MobileElement orderInfoMenu;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"orderinfo_list_item_wkn")
    @iOSXCUITFindBy(iOSNsPredicate = "name='#orderinfo_list_item_wkn'")
    public List<WebElement> orderInfowknList;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"orderinfo_list_item_wkn")
    @iOSXCUITFindBy(iOSNsPredicate = "name='#orderinfo_list_item_wkn'")
    public WebElement orderInfowkn;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"order_detail_type_status")
    @iOSXCUITFindBy(accessibility = "#order_detail_type_status")
    public MobileElement orderDetailOrderType;

    @AndroidFindBy(id = "to do")
    @iOSXCUITFindBy(id = "#order_detail_type_trailing_stop_interval")
    public MobileElement orderAbstand;

    @AndroidFindBy(id = "to do")
    @iOSXCUITFindBy(id = "#order_detail_tolerance_nach_Stop")
    public MobileElement orderTolerenz;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"buyorsell")
    @iOSXCUITFindBy(accessibility = "Kauf von")
    public MobileElement orderDetailOrderart;

    @AndroidFindBy(id = "order_detail_venue_name")
    @iOSXCUITFindBy(accessibility = "#order_detail_venue_name")
    public MobileElement orderDetailExchange;

    @AndroidFindBy(id = "order_detail_edit_order_button")
    @iOSXCUITFindAll({@iOSXCUITBy(accessibility = "#order_detail_edit_order_button"), @iOSXCUITBy(accessibility = "#order_detail_edit_oeder_button")})
    public MobileElement editButton;

    @AndroidFindBy(id = "order_detail_cancel_order_button")
    @iOSXCUITFindBy(accessibility = "#order_detail_cancel_order_button")
    public MobileElement deleteButton;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"orderinfo_list_item_order_number")
    @iOSXCUITFindBy(id = "#orderinfo_list_item_order_number")
    public MobileElement orderInfoOrderNumber;

    @AndroidFindBy(id = "orderinfo_list_item_order_number")
    @iOSXCUITFindBy(id = "#orderinfo_list_item_order_number")
    public List<MobileElement> orderInfoOrderNumberList;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"orderinfo_list_item_order_type")
    @iOSXCUITFindBy(iOSNsPredicate = "name='#orderinfo_list_item_order_type'")
    public MobileElement orderInfoType;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"orderinfo_list_item_stock")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"#orderinfo_list_item_stock \"]")
    public MobileElement orderInfoQty;

    public String getStatus() {
        return status.getText();
    }

    public String getOrderInfoWkn() {
        return orderInfowkn.getText();
    }

    public String getOrderDetailExchange() {
        return orderDetailExchange.getText();
    }

    public String getOrderDetailOrderType() {
        return orderDetailOrderType.getText();
    }

    public String getOrderDetailOrderart() {
        return orderDetailOrderart.getText();
    }

    public String getOrderInfoOrderNumber() {
        return orderInfoOrderNumber.getText();
    }
    public String getOrderInfoOrderType() {
        return orderInfoType.getText();
    }

    public String getOrderInfoQuantity() {
        return orderInfoQty.getText();
    }

    public String getOrderAbstand() {
        return orderAbstand.getText();
    }
    public String getorderTolerenz() {
        return orderTolerenz.getText();
    }
}
