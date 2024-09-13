package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
	
	//ExtendReports build report
	static ExtentReports extentReports;
	
	public static ExtentReports initialReports() {
		if (extentReports==null) {
			extentReports = new ExtentReports();
			
			//We use ExtentSparkReporter class to define our report
			ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/extent_report.html");
			sparkReporter.config().setReportName("Geico Insurance Automation Test Report");
			sparkReporter.config().setDocumentTitle("Geico Insurance Report - Automation Test"); // title of the page
			sparkReporter.config().setTheme(Theme.DARK);
			
			// How extentReports object connect spartReporter
			extentReports.attachReporter(sparkReporter);
			extentReports.setSystemInfo("Tester", System.getProperty("user.name"));
			extentReports.setSystemInfo("OS", System.getProperty("os.name")); // OS = Operating System
			extentReports.setSystemInfo("OS Version", System.getProperty("os.version"));
			extentReports.setSystemInfo("OS Arch", System.getProperty("os.arch"));
			extentReports.setSystemInfo("Environment", "SIT"); // QA or SIT = System Integration Test, UAT = User Acceptance Test
		}
		return extentReports;
	}

}
