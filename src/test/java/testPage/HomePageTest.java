package testPage;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import static common.CommonActions.*;
import baseUtil.BaseClass;
public class HomePageTest extends BaseClass{
	
	@Test(priority = 1, enabled =false)
	public void homeoageTitleTest() {
		homePage.homepageTitle();
	}
	
	@Test(priority = 2, enabled = true)
	public void startMyQuoteTest(){
	    homePage.zipCode();
		homePage.carLob();
		homePage.startMyQuote();
		homePage.continueBtn();
		homePage.enterDOB();
		homePage.clickNext();
		homePage.enterFirstName();
		homePage.enterLastName();
		homePage.clickNext();
		pause(300);
	}
	
	@Test(priority = 3, enabled = false)
	public void carbottonTest() {
		homePage.carLob();
		pause(3000);
	}
	
	@Test(priority = 4, enabled = false)
	public void isDisplayedTest() {
		homePage.use_of_disPlayed();
	}
	
	@Test(priority = 5, enabled = false)
	public void find_an_agent_near_you_test() {
		homePage.find_an_agent_near_you();
		pause(3000);
	}
	
	@Test(priority = 6, enabled = false)
	public void titleOfThePageTest() {
		homePage.titleOfThePage();
	}

}
