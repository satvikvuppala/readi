package com.bnpp.appium.reportsfreemaker;

import com.bnpp.appium.cucumber.CucumberTest;
import com.dab.xray.ObjTestCoverage;
import com.dab.xray.ObjTestExecution;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Entry class for the report generator
 */
public class ReportBuilder {

    public static void generateReport(ObjTestCoverage tc, ObjTestExecution te) throws Exception {

        TimeUnit.SECONDS.sleep(5);

        Date currentDate = new Date();
        te.setExecutionDate(currentDate.toString());
        CucumberTest.folderNameReport = "BNP_" + currentDate.toString().replace(":", "_").replace(" ", "_");

        ReportGenerator rg = new ReportGenerator();

        rg.generateReport(CucumberTest.PATH_TO_CUCUMBER_REPORT, CucumberTest.folderNameReport, tc, te);

    }

    public static void generateReportwithoutDate(ObjTestCoverage tc, ObjTestExecution te) throws Exception {


        TimeUnit.SECONDS.sleep(5);


        ReportGenerator rg = new ReportGenerator();


        String folderNameReportForJenkins = "BNP_";

        rg.generateReport(CucumberTest.PATH_TO_CUCUMBER_REPORT, folderNameReportForJenkins, tc, te);


    }
}
