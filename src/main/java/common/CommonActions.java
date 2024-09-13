package common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
//import org.junit.Assert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.google.common.io.Files;

import reports.Logs;

public class CommonActions {

	public static void clickElement(WebElement element) {
		try {
			element.click();
			Logs.logTheTest(element + "<--------> has been clicked");
		} catch (NoSuchElementException | NullPointerException e) {
			// e.printStackTrace();
			System.out.println("Exception is: " + e);
			Logs.logTheTest(element + "<--------> has not been found\n" + e.getMessage());
			// getMessage() returns the message string of this throwable.
		}

	}

	public static void elementDiplayed(WebElement element) {
		try {
			boolean flag = element.isDisplayed();
			Logs.logTheTest(element + "<------> is Displayed, " + flag);
		} catch (NoSuchElementException | NullPointerException e) {
			Logs.logTheTest(element + "<------> is not Displayed\n" + e.getMessage());
		}
	}

	public static void elementEnabled(WebElement element) {
		try {
			boolean flag = element.isEnabled();
			Logs.logTheTest(element + "<------> is Enabled, " + flag);
		} catch (NoSuchElementException | NullPointerException e) {
			Logs.logTheTest(element + "<------> is not Enabled\n" + e.getMessage());
		}
	}

	public static void elementSelected(WebElement element) {
		try {
			boolean flag = element.isSelected();
			Logs.logTheTest(element + "<------> is Selected, " + flag);
		} catch (NoSuchElementException | NullPointerException e) {
			Logs.logTheTest(element + "<------> is not Selected\n" + e.getMessage());
		}
	}

	public static void pause(long millis) {
		try {
			Thread.sleep(millis);
			Logs.logTheTest("Sleeping...Zzz" + millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Logs.logTheTest("Sleep interrupted");
		}

	}

	public static void insert(WebElement element, String value) {
		try {
			element.sendKeys(value);
			Logs.logTheTest(value + "<--- This value has been passed into " + element);
		} catch (NullPointerException | NoSuchElementException | TimeoutException e) {
			Logs.logTheTest(element + "---> Element not found\n" + e.getMessage());
		}
	}

	public static void select(WebElement element, String value) {
		try {
			Select select = new Select(element);
			select.selectByValue(value);
		} catch (NullPointerException | NoSuchElementException e) {
			Logs.logTheTest(element + "---> Element not found");
			Assert.fail();
		}
	}

	public static void validate(WebElement element, String expected) {
		try {
			String actual = element.getText();
			element.click();
			Logs.logTheTest("validate ----> Actual : ***" + actual + "***. ecpected : *** " + expected + " ***");
		} catch (NullPointerException | NoSuchElementException e) {
			Logs.logTheTest(element + "---> Element not found");
			Assert.fail();
		}
	}

	// very very important interview question
	public static String getSreenShot(String testName, WebDriver driver) {
		TakesScreenshot ss = (TakesScreenshot) driver;
		String path = System.getProperty("user.dir") + "/test-output/screenShots";
		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdirs();
		}

		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyy_hh.mm.ss");
		String formattedDate = dateFormat.format(date);

		File targetFile = new File(path + "/error_" + testName + "_" + formattedDate + ".png");
		try {
			File srcFile = ss.getScreenshotAs(OutputType.FILE);
			Files.copy(srcFile, targetFile);
			Logs.logTheTest("Screenshot has been successfully capture at: \n" + targetFile.getAbsolutePath());
		} catch (WebDriverException | IOException e) {
			e.printStackTrace();
			Logs.logTheTest("Screenshot cannot be captured");
		}
		return targetFile.getAbsolutePath();
	}

}