package baseUtil;

import java.lang.reflect.Method;
import java.time.Duration;

import pages.Boats;
import pages.HomePage;
import pages.Renters;
import reports.ExtentReportManager;
import reports.TestManager;
import utils.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import common.CommonActions;
import io.github.bonigarcia.wdm.WebDriverManager;

//import static util.IConstant.EXPLICITLY_WAIT;
import static utils.IConstant.*;
public class BaseClass {
	public WebDriver driver;
	public HomePage homePage;
	public Renters renters;
	public Boats boats;
	Configuration config;
	protected WebDriverWait wait;
	ExtentReports extentReports;
	ExtentTest extentTest;
	
	@BeforeSuite
	public void initialReporting(){
		extentReports = ExtentReportManager.initialReports();
	}
	
	@BeforeMethod
	public void initialTest(Method method){
		extentTest = TestManager.createTest(extentReports, method.getName());
		extentTest.assignCategory(method.getDeclaringClass().getName());
	}
	
	@BeforeMethod
	public void setUp(){
//		System.setProperty("webdriver.chrome.driver",
//				 "/Users/arifulislam/eclipse-workspace/com.costco/driver/chromedriver");
//		System.setProperty("webdriver.chrome.driver",
//				 "./driver/chromedriver");
//	    WebDriverManager.chromedriver().setup();
//		driver = new ChromeDriver();
		
		config = new Configuration();
		initDriver();
		
		String url = config.getProperties(URL);
		//driver.get("https://www.geico.com/");
	    long pageLoadWait = Long.parseLong(config.getProperties(PAGELOAD_WAIT));
		long implicitlyWait = Long.parseLong(config.getProperties(IMPLICITLY_WAIT));
		//long explicitly = Long.parseLong(config.getProperties((EXPLICITLY_WAIT)));
		initClass();	
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(url);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadWait));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitlyWait));
		//wait = new WebDriverWait(driver, Duration.ofSeconds(explicitly));
			
		//driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(45));
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(45));
		
		
	}
	
	public void initDriver() {
		String browserName = config.getProperties(BROWSER);
		switch (browserName) {
		case CHROME:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
			
		case FIREFOX:
			System.setProperty("webdriver.gecko.driver", "./driver/geckodriver");
			driver = new FirefoxDriver();

		default:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		}
	}
	
	public void initClass() {
		homePage = new HomePage(driver);
		renters = new Renters(driver);
		boats = new Boats(driver);
	}
	
	@AfterMethod
	public void tear() {
		driver.quit();
	}
	
	@AfterMethod
	public void afterEachTest(Method method, ITestResult result) {
		for(String group: result.getMethod().getGroups()) {
			extentTest.assignCategory(group);
		}
		
		if(result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(Status.PASS, "Test PASSED");
		}else if(result.getStatus() == ITestResult.FAILURE) {
			extentTest.addScreenCaptureFromPath(CommonActions.getSreenShot(method.getName(), driver));
			extentTest.log(Status.FAIL, "Test FAILED");
		}else if(result.getStatus() == ITestResult.SKIP) {
			extentTest.log(Status.SKIP, "Test SKIPPED");
		}
	}
	
	@AfterSuite
	public void publishReport() {
		extentReports.flush();
	}

}
