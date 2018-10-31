package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import commonActions.TestTemplate;
import pages.ViewPlansPage;

public class ViewPlansTest extends TestTemplate {
	
	@BeforeClass
	public void beforeClassSetup(){
		//browse = new BrowserActions();
		//pr = new PRDemoPage(browse);
		view = new ViewPlansPage(browse);
	}

	
	@Test(priority=0)
	public void enrollmentYearValueTest(){
		view.enrollmentYearValue();
	}
	
	@Test(priority=1)
	 public void employerGroupOptionsTest(){
	  view.assertEmployerGroupOptions();
	 }
	 
	 @Test(priority=2)
	 public void addEmployeeButtonTest(){
	  view.addEmployeeButtonTest();
	 }
	 
	 @Test(priority=3)
	 public void employeeListingButtonTest(){
	  view.employeeListingButton(); 
	 }
	 
	 @Test(priority=4)
	 public void checkDeafultPlanTypeSelectionTest(){
	  view.checkHealthPlanSelection();
	 }

	 @Test(priority=5)
	 public void premiumDeductibleLinkTest(){
	  view.premiumDeductibleLink();
	 }
}
