package com.atg_sync_services.utils;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentReportsUtil {
	
	public static ExtentReports extent=null;
	public static boolean overrideExistingReport;
	private static EnvironmentUtility env = new EnvironmentUtility();
	public ExtentReportsUtil(){
		
	}
	
	public static void init(){
		
		init(true);
	}
	
	@SuppressWarnings("deprecation")
	public static void init(boolean overrideExistingReport){
		String reportName = "ExtentReports";
		//String reportName = "ATG_Brierley_v1_0";
		System.out.println("Extent Report Initialized:");
		ExtentReportsUtil.overrideExistingReport = overrideExistingReport;
		System.out.println(System.getProperty("user.dir") + "/ExtentReports/ExtentReports.html");
		extent = new ExtentReports(System.getProperty("user.dir") + "/ExtentReports/" + reportName + ".html", overrideExistingReport);
		extent.loadConfig(new File(System.getProperty("user.dir") + "/libs/extent-config.xml"));
		extent.addSystemInfo("Selenium Version", "2.50");
		extent.addSystemInfo("Environment", env.getEnvironment());
		extent.addSystemInfo("Server", env.nest().serverName());
		extent.config().documentTitle("ATG-Nook Account Sync Services Automation Report");
		
	}
	
	
	public static ExtentReports report(){
		if(extent==null){
			init();
		}
		return extent;
	}
}
