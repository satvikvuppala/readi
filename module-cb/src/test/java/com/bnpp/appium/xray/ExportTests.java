package com.bnpp.appium.xray;


import com.bnpp.appium.config.Configurations;
import com.dab.xray.Xray;

public class ExportTests {

	public static void main(String[] args) {

		export();

	}

	public static void export() {

		com.dab.config.PropertiesHandler.setConfigPath(Configurations.XrayConfigPath);

		Log.setLogger();

		Xray.exportCucumberTestsFromXray();

	}

}
