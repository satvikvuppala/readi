package com.bnpp.appium.reportsfreemaker;

import com.dab.xray.ObjTestCoverage;
import com.dab.xray.ObjTestExecution;

public interface Report {
	/**
	 * Generates the report
	 *
	 * @return report file name generated
	 * @throws Exception In case the report couldn't be generated
	 */
	String generateReport(ObjTestCoverage tc, ObjTestExecution te) throws Exception;

}
