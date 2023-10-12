package com.bnpp.appium.config;

public class Configurations {

    public final static String XrayConfigPath = "src/test/java/com/bnpp/appium/xray/xray_config.properties";

    // Output Reports path
    public static final String reportPath = "Reports/";


    // download file path
    public static String downloadPath = System.getProperty("user.dir") + "\\Downloads\\";

    // Take screenshots on run parameter settings.
    public static final String takeScreenshots = "Y";
    public final static String ReleaseName = "Schaeufele";

    public final static String TestEnvironment = "load1";

    public final static String Application = "Consorsbank";

    public final static String TestExecutionSummary = "TAMOB - Test Automation Mobile";

    public final static String ReportConfigPath = "./src/test/resources/CucumberReportSetup/reportconfig.properties";
    public final static String EnableXray="n";


}
