package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import commonActions.TestTemplate;
import dataproviders.MyProfileDataProviders;

public class MyProfileTest extends TestTemplate {
	
	public MyProfileTest(){
		MyProfileDataProviders.sheetName = "myProfile";
	}
	
	@BeforeClass
	public void login(){
		pr.brokerLogin();
	}

	/*@Test(priority=0) 	
	public void previousButtonFunctionality(){
		profile.previousButtonFunctionality();
	} */
		
	@Test(dataProvider="noPrivatePlan", dataProviderClass=MyProfileDataProviders.class,priority=15)
	public void noPrivatePlan(String scenario, String id, String year, String children,  String income, String currentZip, 
			String number, String email,  String primary, String dob, String health){
		profile.noPrivatePlan(scenario, id, year, children, income, currentZip, number, email, primary, dob, health);
	}
	
	@Test(dataProvider="optForPrivatePlan", dataProviderClass=MyProfileDataProviders.class,priority=16)
	public void optForPrivatePlan(String scenario, String id, String year, String children,  String income, String currentZip, 
			String number, String email,  String primary, String dob, String health){	
		profile.optForPrivatePlan(scenario, id, year, children, income, currentZip, number, email, primary, dob, health);
	}
	
}
