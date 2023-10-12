package com.bnpp.appium.teststeps.elements;

import com.bnpp.appium.teststeps.BaseTestCase;
import com.bnpp.appium.teststeps.steps.REGTST_191_TradingBuy_Limithandel;
import com.bnpp.appium.utils.Utils;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITBy;
import io.appium.java_client.pagefactory.iOSXCUITFindAll;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.cucumber.java.Scenario;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.bnpp.appium.config.Constants.ANDROID_PACKAGE_NAME;
import static com.bnpp.appium.utils.ActionsUtils.scrollDown;
import static com.bnpp.appium.utils.Utils.*;

public class OrderPage extends BaseTestCase {
    public OrderPage(Scenario scenario) {
        super.init(scenario);
    }
    private final static Logger LOGGER = Logger.getLogger(REGTST_191_TradingBuy_Limithandel.class);
    int scrollCount;
    static final int MAX_SCROLL_ATTEMPTS = 25;
    public static final String test = "NavBarIconSearch Wh";

    @AndroidFindBy(className = "android.widget.Button")
    public MobileElement acceptAlert;

    @AndroidFindBy(id = "test")
    @iOSXCUITFindBy(iOSNsPredicate = "name='Märkte'")
    public MobileElement tabMarkte;

    @AndroidFindBy(accessibility = "Suchen")
    @iOSXCUITFindBy(accessibility = "NavBarIconSearch Wh")
    public MobileElement searchIcon;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"btn_search")
    @iOSXCUITFindBy(iOSNsPredicate = "name='Search'")
    public MobileElement searchButton;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"edt_search_item")
    @iOSXCUITFindBy(iOSNsPredicate = "name='#ta_edit_search_items'")
    public MobileElement searchField;

    @AndroidFindBy(id = "security_list_row_body")
    @iOSXCUITFindBy(iOSNsPredicate = "name='#ta_row_security_name'")
    public MobileElement securityName;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME + "coachmark_master")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Geführte Order\"]")
    public MobileElement guiderOrderCoach;

    @AndroidFindBy(xpath = "//android.widget.ProgressBar")
    @iOSXCUITFindBy(id = "loading_spinner_37px")
    public MobileElement progressBar;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"home_code")
    @iOSXCUITFindBy(accessibility = "#ta_row_wkn")
    public List<MobileElement> securityWkns;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"snapshot_header_buy_button")
    @iOSXCUITFindBy(iOSNsPredicate = "name='#ta_snapshot_buy_button'")
    public MobileElement buyButton;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"snapshot_header_sell_button")
    @iOSXCUITFindBy(iOSNsPredicate = "name='#ta_snapshot_sell_button'")
    public MobileElement sellButton;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"trading_stock_exchange_name")
    @iOSXCUITFindBy(iOSNsPredicate = "name='#ta_trading_stock_exchange_name'")
    public MobileElement exchangeName;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"trading_exchange_selectors_layout")
    @iOSXCUITFindBy(iOSNsPredicate = "name='marketplaceSelectionButton'")
    public MobileElement exchangeSelector;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"trading_stock_value")
    @iOSXCUITFindBy(iOSNsPredicate = "name='#ta_trading_stock_value'")
    public MobileElement quantityField;

    @iOSXCUITFindBy(iOSNsPredicate = "name='Wie berechnen wir den Gegenwert?'")
    public MobileElement qtyValueLabel;

    @AndroidFindBy(id = "rb_limit_trade")
    @iOSXCUITFindBy(iOSNsPredicate = "name='Limithandel'")
    public MobileElement limitTradeButton;

    @AndroidFindBy(id = "btn_request_quote")
//    @iOSXCUITFindAll({@iOSXCUITBy(accessibility = "#ta_order_add_get_quote"), @iOSXCUITBy(accessibility = "#ta_order_add_to_watchlist")})
    @iOSXCUITFindBy(accessibility = "#ta_order_add_to_watchlist")
    public MobileElement requestQuoteButton;

    @AndroidFindBy(id = "tv_buysell_quote")
    @iOSXCUITFindBy(accessibility = "#ta_trading_direkthandel_quote")
    public MobileElement myQuote;

    @AndroidFindBy(id = "trading_additional_option")
    @iOSXCUITFindBy(iOSNsPredicate = "name='#ta_trading_additional_option'")
    public MobileElement additionalOptions;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"trading_order_addition_value")
    @iOSXCUITFindBy(iOSNsPredicate = "name='orderAdditionsButton'")
    public MobileElement orderTypeSelector;

    @AndroidFindBy(id = "trading_order_addition_value")
    @iOSXCUITFindBy(id = "intelligentOptionsButton")
    public MobileElement stopBuyTypeSelector;

    @AndroidFindBy(id = "to do")
    @iOSXCUITFindBy(id = "#ta_trading_order_abstand_option")
    public MobileElement selectedStopBuyType;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"trading_order_addition_value")
        @iOSXCUITFindBy(iOSNsPredicate = "name='#ta_trading_order_addition_value'")
    public MobileElement selectedOrderType;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"trading_order_limit_value")
    @iOSXCUITFindBy(iOSNsPredicate = "name='#ta_trading_order_limit_value'")
    public MobileElement limitField;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"trading_order_stop_buy_or_loss_value")
    @iOSXCUITFindBy(accessibility = "#ta_trading_order_limit_value")
    public MobileElement stopBuyLossField;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"trading_order_trailing_distance")
    @iOSXCUITFindBy(accessibility = "#ta_trading_order_abstand_value")
    public MobileElement abstandField;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"trading_order_trailing_notation")
    @iOSXCUITFindBy(accessibility = "intelligentOptionsButton")
    public MobileElement abstandTypeSelector;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"trading_order_trailing_notation")
    @iOSXCUITFindBy(accessibility = "#ta_trading_order_abstand_option")
    public MobileElement selectedAbstandType;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"trading_order_tolerance_value")
    @iOSXCUITFindAll({@iOSXCUITBy(accessibility = "#ta_trading_order_tolerace_value"), @iOSXCUITBy(accessibility = "#ta_trading_order_tolerance_value")})
    public MobileElement toleranceField;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"trading_validity_period")
    @iOSXCUITFindBy(iOSNsPredicate = "name='validUntilButton'")
    public MobileElement expirationDateSelector;

    @iOSXCUITFindAll({@iOSXCUITBy(accessibility = "Done"), @iOSXCUITBy(accessibility = "Return"), @iOSXCUITBy(accessibility = "Tastatur ausblenden"), @iOSXCUITBy(accessibility = "Fertig"), @iOSXCUITBy(accessibility = "Next")})
    public MobileElement dismissKeyboardButton;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"trading_skip_to_tan_button")
    @iOSXCUITFindBy(iOSNsPredicate = "name='#ta_skip_to_tan_button'")
    public MobileElement weiterZuTanEingabe;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"tv_activate_session_tan")
    @iOSXCUITFindBy(accessibility = "Session-TAN aktivieren")
    public MobileElement sessionTanButton;

    @AndroidFindBy(id = "to do")
    @iOSXCUITFindBy(accessibility = "Funktionsweise der Session-TAN")
    public MobileElement sessionTanAcceptPage;

    @AndroidFindBy(id = "to do")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"CheckboxUnchecked\"]")
    public MobileElement sessionTanAcceptCheckbox;


    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"btn_invoke_primary_layout")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[@name=\"#ta_skip_to_tan_button\"])[1]")
    public MobileElement securePlusAppButton;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"tv_qr_code_link")
    @iOSXCUITFindBy(id = "TAN manuell eingeben")
    public MobileElement enterTanManuallyButton;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"et_sca_tan")
    @iOSXCUITFindBy(id = "#ta_tan_summary_tan_edit_text")
    public MobileElement enterTanField;


    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"btn_sca_execute_button")
    @iOSXCUITFindBy(accessibility = "#ta_skip_to_tan_button")
    public MobileElement orderConfirmButton;

    @AndroidFindBy(id = "to do")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"OK\"]")
    public MobileElement sessionTanOk;


    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"order_confirmation_exp_date")
    @iOSXCUITFindBy(iOSNsPredicate = "name='#ta_order_confirmation_exp_date'")
    public MobileElement expiryDate;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"order_confirmation_limit")
    @iOSXCUITFindBy(iOSNsPredicate = "name='#ta_order_confirmation_limit'")
    public MobileElement limit;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"order_confirmation_success_desc_text")
    @iOSXCUITFindBy(iOSNsPredicate = "name='#ta_order_confirmation_success_desc_text'")
    public MobileElement confirmationMessage;

    @AndroidFindBy(id = "order_confirmation_number")
    @iOSXCUITFindBy(accessibility = "#ta_order_confirmation_number")
    public MobileElement orderNumber;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"order_confirmation_success_text")
    @iOSXCUITFindBy(iOSNsPredicate = "name='#ta_order_confirmation_success_text'")
    public MobileElement successMessage;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"order_confirmation_number")
    @iOSXCUITFindBy(iOSNsPredicate = "name='#ta_order_confirmation_number'")
    public MobileElement orderNumberElmt;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"order_confirmation_trading_venue")
    @iOSXCUITFindBy(iOSNsPredicate = "name='#ta_order_confirmation_trading_venue'")
    public MobileElement exchange;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"order_confirmation_zusats")
    @iOSXCUITFindBy(iOSNsPredicate = "name='#ta_order_confirmation_zusats'")
    public MobileElement type;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"skip_to_orderinfo")
    @iOSXCUITFindBy(iOSNsPredicate = "name='#ta_skip_to_orderinfo'")
    public MobileElement orderInfoButton;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"trading_summary_validity_date_status")
    @iOSXCUITFindBy(id = "#ta_trading_summary_validity_date_status")
    public MobileElement tanPageExpirationDate;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Depot\" and @resource-id=\"de.consorsbank.test:id/accountTypeDescription\"]")
    @iOSXCUITFindBy(accessibility = "Depot")
    public MobileElement depot;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"text_fees_link")
    @iOSXCUITFindBy(iOSNsPredicate = "name='Kostenausweis'")
    public MobileElement kostenausweisLink;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"order_confirmation_stop_buy_or_loss_value")
    @iOSXCUITFindBy(accessibility = "#ta_order_confirmation_limit")
    public MobileElement trailingStopValue;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"order_confirmation_trailing_distance_value")
    @iOSXCUITFindBy(accessibility = "#ta_order_confirmation_trailing_distance")
    public MobileElement distance;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"order_confirmation_tolerance_value")
    @iOSXCUITFindBy(id = "#ta_order_confirmation_trailing_toleranz")
    public MobileElement tolerance;

    public WebElement getStockExchangeElement(String stockExchange) throws InterruptedException {
        if(isAndroid()){
            String exchangesClassName = "android.widget.ListView";
            String exchangeClassName = "android.widget.TextView";

            return getDriver().findElement(MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector().className(\"" + exchangesClassName + "\")).getChildByText("
                            + "new UiSelector().className(\"" + exchangeClassName + "\"), \"" + stockExchange + "\")"));
        }else{
            WebElement element = null;
            while (scrollCount < MAX_SCROLL_ATTEMPTS) {
                logStep("Expected stockExchange : " + stockExchange);
//                Thread.sleep(1000);
                element = getDriver().findElementByXPath("//XCUIElementTypeStaticText[@label='" + stockExchange + "']");
                logStep("Actual stockExchange : " + element.getText());
                if (!element.isDisplayed()) {
                    logStep("loop : " + scrollCount++);
                    scrollDown(getDriver());
                    // getDriver().manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
                    scrollCount++;
                    continue;
                }
//                getElement(element);
                break;
            }
            scrollCount = 0;
            return element;
        }
    }

    public MobileElement selectOrderButton(String order) {

        if (order.equalsIgnoreCase("Kaufen")) {
            return getElement(buyButton);
        } else {
            return getElement(sellButton);
        }
    }

    public WebElement getOrderTypeElement(String orderType) {
            if (!isAndroid()) {
                return getDriver().findElementByXPath("//XCUIElementTypeStaticText[@label='" + orderType + "']");
            } else {
                String orderTypesClassName = "android.widget.ListView";
                String orderTypeClassName = "android.widget.TextView";

                return getElement(getDriver().findElement(MobileBy.AndroidUIAutomator(
                        "new UiScrollable(new UiSelector().className(\"" + orderTypesClassName + "\")).getChildByText("
                                + "new UiSelector().className(\"" + orderTypeClassName + "\"), \"" + orderType + "\")")));
            }
        }


    public WebElement getTrailingStopDistanceElement(String distanceType) {
        if (isAndroid()) {
            return getDriver().findElementByXPath("//android.widget.RelativeLayout[.//android.widget.TextView[@text='" + distanceType + "']]");
        } else {
            List elements = getDriver().findElementsByXPath("//XCUIElementTypeStaticText[@label='" + distanceType + "']");
            return (WebElement) elements.get(elements.size() - 1);
        }
    }

    public WebElement getOrderValidityTypeElement(String validityType) {
        if (isAndroid()) {
            WebElement dateView = getDriver().findElementByXPath("//android.widget.RelativeLayout[.//android.widget.TextView[@text='" + validityType + "']]");
            return dateView.findElement(By.id("column2"));
        } else {

            WebElement dateView = getDriver().findElementByXPath("//XCUIElementTypeCell[.//XCUIElementTypeStaticText[@label='" + validityType + "']]");
            return dateView.findElement(By.id("#order_expiration_date"));
        }
    }

    public void dismissKeyBoard() {
        try {
            dismissKeyboardButton.click();
        } catch (NoSuchElementException e) {
            LOGGER.info("Can't dismiss keyboard");
        }
    }

    public String getExpirationDate() {
        if (!isDisplayed(expiryDate)) {
            swipeDownTo(expiryDate);
        }
        getElement(expiryDate);
        return expiryDate.getText();
    }

    public String getLimit() {
        return getElement(limit).getText();
    }


    public String getExchange() {
        if (!isDisplayed(exchange)) {
            swipeDownTo(exchange);
        }
        getElement(exchange);
        return exchange.getText();
    }

    public String getType() {
        return getElement(type).getText();
    }

    public String getConfirmationMessage() {
        return getElement(confirmationMessage).getText();
    }

    public String getSuccessMessage() {
        return getElement(successMessage).getText();
    }

    public String getOrderNumberElmt() {
        return getElement(orderNumberElmt).getText();
    }

    public String getTanPageExpiryDate() {
//        if (!isDisplayed(tanPageExpirationDate)) {
//            swipeDownTo(tanPageExpirationDate);
//        }
        getElement(tanPageExpirationDate);
        return tanPageExpirationDate.getText();
    }

    public String getTrailingStopLimit() {
        return getElement(trailingStopValue).getText();
    }

    public String getDistance() {
        return getElement(distance).getText();
    }

    public String getTolerance() {
        if (!isDisplayed(tolerance)) {
            swipeDownTo(tolerance);
        }
        return getElement(tolerance).getText();
    }

    public String getMyQuote() {
        return myQuote.getText();
    }

    public void requestQuote() {
        if (!isDisplayed(requestQuoteButton)) {
            swipeDownTo(requestQuoteButton);
        }
        getElement(requestQuoteButton).click();

        //TODO Replace with wait till progressbar is dismissed
//        while (!requestQuoteButton.isEnabled()) {
//            // Wait for quote to update
//        }
    }

    public boolean continueToTanPage(String acceptMessage) throws IOException, ParseException {
        if (!isDisplayed(orderConfirmButton)) {
            swipeDownTo(orderConfirmButton);
        }
        getElement(orderConfirmButton).click();
        return !acceptAlert(acceptMessage);
    }
}
