package com.qa.Util;

import com.relevantcodes.extentreports.ExtentReports;

public class Constant {
	
	public static final String homeURL =
			"http://10.0.10.10:4200/petclinic/";
	
	public static final String cDFilePath =
			"webdriver.chrome.driver";
	
	public static final String driverType =
			"C:\\Testing\\chromedriver.exe";
	
	public static ExtentReports report = new ExtentReports(
			"C:\\Users\\Admin\\Desktop\\PetClinicReports\\PetClinicReport.html", true);

}
