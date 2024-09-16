package testPage;

import org.testng.annotations.Test;

import baseUtil.BaseClass;

public class BoatsTest extends BaseClass{
	
	@Test
	public void boatInsuranceTest() {
		boats.boatsInsurance();
	}

}
