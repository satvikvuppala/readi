package com.bnpp.appium.teststeps.elements;

import com.bnpp.appium.teststeps.BaseTestCase;
import com.bnpp.appium.teststeps.util.enums.TextFieldData;
import com.bnpp.appium.utils.Utils;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITBy;
import io.appium.java_client.pagefactory.iOSXCUITFindAll;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.cucumber.java.Scenario;
import org.apache.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static com.bnpp.appium.config.Constants.ANDROID_PACKAGE_NAME;
import static com.bnpp.appium.utils.Utils.*;

public class LoginPage extends BaseTestCase {

    private final static Logger LOGGER = Logger.getLogger(LoginPage.class);
    @iOSXCUITFindBy(id = "Jetzt starten!")
    public MobileElement startAppUsage;

    @iOSXCUITFindBy(id = "Weiter")
    public MobileElement navigateApplePayTourBtn;

    @AndroidFindBy(id = "bn_tab_finance")
    @iOSXCUITFindBy(id = "Meine Finanzen")
    public MobileElement tabMeineFinanzen;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME + "login_user_name")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField")
    public MobileElement userName;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME + "login_password")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeSecureTextField")
    public MobileElement userPassword;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME + "login_button")
    @iOSXCUITFindBy(iOSNsPredicate = "name='Einloggen'")
    public MobileElement loginButton;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME + "tv_sca_heading")
    @iOSXCUITFindBy(iOSNsPredicate = "name='Login mit TAN bestätigen'")
    public MobileElement tanPage;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME +"et_sca_tan")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Consorsbank Test\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextField")
    public MobileElement tanfield;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"btn_sca_execute_button")
    @iOSXCUITFindBy(id = "#ta_skip_to_tan_button")
    public MobileElement tanConfirm;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME + "btn_invoke_primary")
//    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"SecurePlus App öffnen\"]")
    @iOSXCUITFindBy(iOSNsPredicate = "name='SecurePlus App öffnen'")
    public MobileElement invokeSecurePlus;


    @iOSXCUITFindBy(accessibility = "ABBRECHEN")
    public MobileElement touchIdCancel;

    @AndroidFindBy(id = "login_fragment_remember_user_checkbox")
    @iOSXCUITFindBy(accessibility = "SwitchOff")
    public MobileElement rememberUserChckBox;

    @AndroidFindBy(id = "com.kobil.consors.test:id/password_tet")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Consorsbank SecurePlus INT\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextField\n")
    public MobileElement securePassword;

    @AndroidFindBy(id = "com.kobil.consors.test:id/login_btn")
    @iOSXCUITFindBy(iOSNsPredicate = "name='Bestätigen'")
    public MobileElement secureLoginButton;

    @AndroidFindBy(id = "android:id/text1")
    public MobileElement benutzer;

    @AndroidFindBy(xpath = "//*[@resource-id='android:id/text1'][6]")
    public MobileElement benutzerClick;

    @AndroidFindBy(id = "com.kobil.consors.test:id/accept_btn")
    @iOSXCUITFindBy(iOSNsPredicate = "name='Freigeben'")
    public MobileElement secureAcceptButton;

    @AndroidFindBy(id = "to do")
    @iOSXCUITFindBy(accessibility = "Nein")
    public MobileElement cancelTouchID;

    @AndroidFindBy(id = "to do")
//    @iOSXCUITFindAll({@iOSXCUITBy(xpath = "//XCUIElementTypeOther[@name=\"Gerätebindung\"]"), @iOSXCUITBy(xpath = "//XCUIElementTypeStaticText[@name=\"Gerätebindung\"]")})
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Gerätebindung\"]")
    public MobileElement deviceBindingHeader;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME + "disable_teaser_checkbox")
    @iOSXCUITFindBy(id = "CheckboxUnchecked")
    public MobileElement deviceBindingCheckBox;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"button_gpay_teaser_my_finance")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Überspringen\"]")
    public MobileElement weiterButtonDevicePopup;

    @AndroidFindBy(id = "starting_disclaimer_header")
//    @iOSXCUITFindBy(id="Nutzungsbedingungen zustimmen")
    @iOSXCUITFindAll({@iOSXCUITBy(xpath = "//XCUIElementTypeOther[@name=\"Nutzungsbedingungen zustimmen\"]"), @iOSXCUITBy(xpath = "//XCUIElementTypeStaticText[@name=\"Nutzungsbedingungen zustimmen\"]")})
    public MobileElement disclaimerHeader;

    @AndroidFindBy(id = "starting_disclaimer_accept")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Ich stimme zu\"]")
    public MobileElement acceptTermsAndConditionsButton;

    @AndroidFindBy(id = "to do")
    @iOSXCUITFindAll({@iOSXCUITBy(xpath = "//XCUIElementTypeOther[@name=\"Apple Pay\"]"), @iOSXCUITBy(xpath = "//XCUIElementTypeStaticText[@name=\"Apple Pay\"]")})
    public MobileElement inAppProvisiongPopup;

    @AndroidFindBy(id = "to do")
    @iOSXCUITFindBy(id = "CheckboxUnchecked")
    public MobileElement inAppProvisioningPopupCheckbox;

    @AndroidFindBy(id = "to do")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Später hinzufügen\"]")
    public MobileElement inAppProvisiongPopupAddLaterButton;

    @AndroidFindBy(id = "to do")
    @iOSXCUITFindAll({@iOSXCUITBy(xpath = "//XCUIElementTypeOther[@name=\"Push-Benachrichtigungen\"]"), @iOSXCUITBy(xpath = "//XCUIElementTypeStaticText[@name=\"Push-Benachrichtigungen\"]")})
    public MobileElement pushNotificationPopup;

    @AndroidFindBy(id = "to do")
    @iOSXCUITFindBy(id = "CheckboxUnchecked")
    public MobileElement pushNotificationCheckbox;

    @AndroidFindBy(id = "to do")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Überspringen\"]")
    public MobileElement pushNotificationWeiterButton;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"text_girokonto_title")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name=\"Apple Pay\"]")
    public MobileElement mobilePayGiroKontoOpenPopup;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME+"checkbox_gpay_teaser_disable")
    @iOSXCUITFindBy(id = "CheckboxUnchecked")
    public MobileElement mobilePayGiroKontoOpenCheckbox;

    @AndroidFindBy(id = ANDROID_PACKAGE_NAME + "button_gpay_teaser_my_finance")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Weiter zu Meine Finanzen\"]")
    public MobileElement mobilePayGiroKontoOpenWeiterButton;

    public LoginPage(Scenario scenario) {
        super.init(scenario);
    }

    public void loginAs(String kontoNummer, String password, String secPlusPwd) {

        try {
            logStep("loginAs before");
            waitTillLoaded(disclaimerHeader, 4);
            logStep("Wait completed");
//            getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            Utils.swipeDownTo(acceptTermsAndConditionsButton);
            acceptTermsAndConditionsButton.click();
            navigateWelcomeTour();
            skipApplePayTour();
        } catch (TimeoutException e) {
            LOGGER.error("error", e);
        }

        getElement(tabMeineFinanzen).click();
        logStep("after");

        TextFieldData textFieldData = clearAndEnterNewData(userName, kontoNummer);
//        userPassword.click();
        userPassword.sendKeys(password);
        if (textFieldData.is(TextFieldData.ENTERED)) {
            rememberUserChckBox.click();
        }
        loginButton.click();
        String secure_auth = getProp().getProperty("secure_auth");
        if (secure_auth.equals("ON")) {
            //code for secure plus navigation
            securePlusLogin(secPlusPwd, invokeSecurePlus);
        } else {
            //code for fixed OTP
            waitTillLoaded(tanfield, 10);
            enterText(getProp().getProperty("fixed_secure_tan"), tanfield);
            tanConfirm.click();
        }
        dismissDeviceBindingPopup();
        dissmissGirokotoPopup();

//        if (isDisplayed(cancelTouchID)){
//            getElement(cancelTouchID).click();
//        }else{
//            LOGGER.info("Touch id popup is not displayed");
//        }
    }

    public void securePlusLogin(String securePwd, MobileElement securePlusButton) {
        try {
            getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            securePlusButton.click();
            getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

            if (!Utils.isAndroid()) {
                acceptAlert();
            }
            if (isAndroid()) {
                benutzer.click();
                benutzerClick.click();
            }
            getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            securePassword.clear();
            securePassword.setValue(securePwd);
            secureLoginButton.click();
            waitTillLoaded(secureAcceptButton, 5,getScenario());
            secureAcceptButton.click();
        } catch (TimeoutException e) {
            LOGGER.info("Device binding activated");
        }
    }
    public void navigateWelcomeTour() {
        for (int i = 0; i < 6; i++) {
            Utils.swipeFromRight();
        }
        startAppUsage.click();
    }

    public void ApplePayTourPage(WebDriver driver, int timeOut) {

        waitTillLoaded(navigateApplePayTourBtn, 1);
    }

    public void skipApplePayTour() {
        navigateApplePayTourBtn.click();
    }

    public void dismissDeviceBindingPopup() {

        try {
            waitTillLoaded(deviceBindingCheckBox, 20);
            getElement(deviceBindingCheckBox).click();
            getElement(weiterButtonDevicePopup).click();

        } catch (TimeoutException e) {
            LOGGER.info("Device binding popup not displayed");
        }
    }

    public void dismissInAppProvisioningPopup() {
        try {
            waitTillLoaded(inAppProvisiongPopup, 1);
            inAppProvisioningPopupCheckbox.click();
            inAppProvisiongPopupAddLaterButton.click();

        } catch (TimeoutException e) {
            LOGGER.info("inAppProvisioningPopup not displayed");
        }
    }

    public void dissmissGirokotoPopup(){
        try {
            waitTillLoaded(mobilePayGiroKontoOpenPopup, 5);
            mobilePayGiroKontoOpenCheckbox.click();
            mobilePayGiroKontoOpenWeiterButton.click();

        } catch (TimeoutException e) {
            LOGGER.info("mobile pay GirokotoPopup not displayed");
        }
    }

    public void dismissPushNotificationPopup() {
        try {
            waitTillLoaded(pushNotificationPopup, 1);
            pushNotificationCheckbox.click();
            pushNotificationWeiterButton.click();

        } catch (TimeoutException e) {
            LOGGER.info("inAppProvisioningPopup not displayed");
        }
    }
}
