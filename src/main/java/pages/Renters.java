package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static common.CommonActions.*;

public class Renters {
	WebDriver driver;
	JavascriptExecutor js;
	
	public Renters(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor)driver;
	}
	
	@FindBy(id = "ssp-service-zip")
	WebElement zipCode;
	
	@FindBy(xpath = "(//input[@class='zip-code-input'])[2]")
	WebElement zipCode2;
	
	@FindBy(xpath = "(//div[@class='icon-container'])[3]")
	WebElement rentersLob;
	
	@FindBy(css = "a.modal-trigger.btn.btn--primary.btn--full-mobile")
	WebElement startMyQuote;
	
	@FindBy(css = "input.btn.btn--primary.btn--full-mobile")
	WebElement continueBtn;
	
	@FindBy(css = "input.date")
	WebElement enterDOB;
	
	@FindBy(css = "button.btn.btn--primary.btn--full-mobile.btn--pull-right")
	WebElement clickNext;
	
	@FindBy(xpath = "//input[starts-with(@id,'Id_GiveFirstName_')]")
	WebElement enterFirstName;
	
	@FindBy(xpath = "//input[starts-with(@id,'Id_GiveLastName_')]")
	WebElement enterLastName;
	
	public void rentersInsurance() {
		insert(zipCode, "11372");
		clickElement(rentersLob);
		js.executeScript("arguments[0].click()", startMyQuote);
		clickElement(continueBtn);
		insert(enterDOB, "11/23/1989");
		clickElement(clickNext);
		insert(enterFirstName, "Mohammed");
		insert(enterLastName, "Islam");
		clickElement(clickNext);
		pause(3000);
	}
		
}
