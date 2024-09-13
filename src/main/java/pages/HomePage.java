package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import static common.CommonActions.*;
public class HomePage {
	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(tagName = "h1")
	WebElement homepageTitle;
	
	@FindBy(id = "ssp-service-zip")
	WebElement zipCode;
	
	@FindBy(linkText = "Start My Quote")
	WebElement startMyQuote;
	
	@FindBy(xpath = "(//div[@class='icon-container'])[1]")
	WebElement carLob;
	
	public void use_of_disPlayed() {
		boolean elementDiplayed = driver.findElement(By.cssSelector("a.icon-geico")).isDisplayed();
		System.out.println("is the logo disPlayed? Ans: "+elementDiplayed);
	}
	
	public void find_an_agent_near_you() {
		driver.findElement(By.xpath("//body/div[@id='wrapper']/div[@id='main']/div[@id='accessibility-main-content']/div[@id='homepage_panels']/div[1]/section[1]/div[1]/div[1]/div[1]/form[1]/div[4]/p[2]")).click();
	}

	public void homepageTitle() {
		clickElement(homepageTitle);		
	}
	
	public void titleOfThePage() {
		String actual = driver.getTitle();
		System.out.println("Title of the page is: " + actual);
		String expected = "GEICO";
		Assert.assertEquals(actual, expected, "Title doesn't match");
	}
	public void zipCode() {
	//zipCode.sendKeys("11377");
		insert(zipCode, "11377");
		pause(3000);
	}
	
	public void startMyQuote() {
		clickElement(startMyQuote);
		pause(3000);
	}
	
	public void continueBtn() {
		driver.findElement(By.cssSelector("input.btn.btn--primary.btn--full-mobile")).click();
		pause(3000);
	}
	
	public void enterDOB() {
		driver.findElement(By.cssSelector("input.date")).sendKeys("02/22/1999");
		pause(3000);
	}
	
	public void clickNext() {
		driver.findElement(By.cssSelector("button.btn.btn--primary.btn--full-mobile.btn--pull-right")).click();
		pause(3000);
	}
	
	public void enterFirstName() {
		driver.findElement(By.xpath("//input[starts-with(@id,'Id_GiveFirstName_')]")).sendKeys("Ariful");
		pause(3000);
	}
	public void enterLastName() {
		driver.findElement(By.xpath("//input[starts-with(@id,'Id_GiveLastName_')]")).sendKeys("Islam");
		pause(3000);
	}
	
	public void carLob() {
		clickElement(carLob);
		
	}
	
	
}
