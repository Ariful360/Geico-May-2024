package pages;
import static common.CommonActions.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Boats {
	
	WebDriver driver;
	JavascriptExecutor js;
	
	public Boats(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor)driver;
	}
	
	@FindBy(id = "ssp-service-zip")
	WebElement zipCode;
	
	@FindBy(xpath = "(//input[@class='zip-code-input'])[2]")
	WebElement zipCode2;
	
	@FindBy(xpath = "(//div[@class='icon-container'])[5]")
	WebElement boatsLob;
	
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
	
	public void boatsInsurance() {
		insert(zipCode, "11373");
		clickElement(boatsLob);
		js.executeScript("arguments[0].click()", startMyQuote);
		clickElement(continueBtn);
		insert(enterDOB, "12/31/1996");
		clickElement(clickNext);
		insert(enterFirstName, "Ayan");
		insert(enterLastName, "Islam");
		clickElement(clickNext);
		pause(3000);
	}

}
