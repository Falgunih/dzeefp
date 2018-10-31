package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import commonActions.TestTemplate;

public class RecommendedPlansTest extends TestTemplate{
	
	@BeforeClass	
	public void goToUrl(){
		pr.goToEnvironment();
		pr.brokerLogin();
	}
	
/*	@BeforeTest
	public void loginToPR(){
		pr.brokerLogin();
	}
	*/

	@Test(priority=3)
	public void informationLinkTest(){
		recommend.informationLink();
	}
}

