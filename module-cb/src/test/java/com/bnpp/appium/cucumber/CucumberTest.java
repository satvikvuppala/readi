package com.bnpp.appium.cucumber;

import com.bnpp.appium.config.Configurations;
import com.bnpp.appium.config.DriverHolder;
import com.bnpp.appium.config.PropertyLoader;
import com.bnpp.appium.config.model.device.Connection;
import com.bnpp.appium.xray.Log;
import com.bnpp.cucumberreports.ReportBuilder;
import com.dab.config.PropertiesHandler;
import com.dab.xray.ObjTestCoverage;
import com.dab.xray.ObjTestExecution;
import com.dab.xray.TestExecution;
import com.dab.xray.Xray;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;


@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
      //  strict = true,
        features = "./module-cb/src/test/resources/features",
        dryRun = false,
        glue = "com.bnpp.appium.teststeps.steps",
        tags = "@demo",
        plugin = {"json:target/cucumber.json"}
)

public class CucumberTest {

    private final static Logger LOGGER = Logger.getLogger(CucumberTest.class);
    public final static String PATH_TO_CUCUMBER_REPORT;
    public final static String PATH_REPORT_TEAMPLATE;
    public static String currentXrayIssueKey = "";
    public static boolean featureTestPassed = true;
    public static String ExecutionID = "";
    public static String testStart = "";
    public static String testPlanId = "";
    public static String folderNameReport = "";

    public static ObjTestExecution te = null;

    static {
        com.bnpp.reportconfig.ReportPropertiesHandler.setConfigPath(Configurations.ReportConfigPath);
        PATH_TO_CUCUMBER_REPORT = com.bnpp.reportconfig.ReportPropertiesHandler.getPathToCucumberReport();
        PATH_REPORT_TEAMPLATE = com.bnpp.reportconfig.ReportPropertiesHandler.getTemplatePath();
    }

    @BeforeClass
    public static void setupBeforeClass() throws IOException {
        LOGGER.info("setupBeforeClass");
        init();
        com.dab.config.PropertiesHandler.setConfigPath(Configurations.XrayConfigPath);
        setLogger();
        if (Configurations.EnableXray.equalsIgnoreCase("Y")) {
            te = TestExecution.getTestExecution();
            ObjTestExecution te = TestExecution.getTestExecution();
            ExecutionID = te.getTestExecutionKey();
            testPlanId = com.dab.config.PropertiesHandler.getXrayTestPlanKey();
            TestExecution.addTestPlanToTestExecution(testPlanId, ExecutionID);
            ZonedDateTime startDateTime = ZonedDateTime.now();
            testStart = startDateTime.truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            Log.info("Test Start Time: " + testStart);
        }

    }

    @AfterClass
    public static void teardown() throws Exception {
        LOGGER.info("AfterClass");
//        DriverHolder.getInstance().stopAppiumServer();
        try {
            if (Configurations.EnableXray.equalsIgnoreCase("Y")){
                ObjTestCoverage tc = new ObjTestCoverage();
                tc = Xray.getTestAutomationCoverage(PropertiesHandler.getXrayProjectKey(), false);
                ReportBuilder.generateReport(tc, te);
                ReportBuilder.generateReportwithoutDate(tc, te);

                // *** activating and deacivating in config.properties
                Xray.attachFileToJiraIssue(Configurations.reportPath + ReportBuilder.folderNameReport, ExecutionID);
            } else{
                ReportBuilder.generateReport();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void init() throws IOException {
        if (DriverHolder.getInstance().getDriver() == null) {

            String deviceName = System.getProperty("devicename");
            LOGGER.info("deviceName from server is [" + deviceName + "]");
            if (!StringUtils.isEmpty(deviceName)) {
                DriverHolder.getInstance().init("src/test/resources/config/devices.json", deviceName);
            } else {
                throw new IllegalStateException("Cannot get the deviceName. Will quit");
            }

            PropertyLoader.getInstance().load("src/test/resources/config/testdata.properties");
        }

    }
    public static void setLogger() {

        System.setProperty("log-directory", PropertiesHandler.getLogsFolder());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        System.setProperty("currenttime", dateFormat.format(new Date()));
        Log.info("Log configuration done. Log Dir:" + PropertiesHandler.getLogsFolder());

    }

}