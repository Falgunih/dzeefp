/**
 * 
 */
package testcases;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import commonActions.TestTemplate;

/**
 * @author GS-0985
 */
public class PRDemoTest extends TestTemplate {
	
	@BeforeClass
	public void goToUrl(){
		pr.goToEnvironment();
		pr.brokerLogin();
		browse.waitForPageToLoad(5);
	}

/**
 * Recommended plans pop up elements test scenarios 	
 *//*
	
	@Test(priority=6)
	public void tellMeAboutPageTest(){
		recommend.tellMeAboutPage();
	}
	
	@Test(priority=7)
	public void whatCanIDoNextTest(){
		recommend.whatCanIDoNext();
	}
	
	@Test(priority=8)
	public void oopDetailsTest(){
		recommend.oopDetails();
	}
	
	@Test(priority=9)
	public void overrideHsaFsaContributionTest(){
		recommend.overrideHsaFsaContribution();
	}
	
	@Test(priority=10)
	public void customSupplementPlanPopupTest(){
		recommend.customSupplementPlanPopup();
	}
	
	@Test(priority=11)
	public void planDetailsTest(){
		recommend.planDetails();
	}
	
	@Test(priority=12)
	public void dzeeScoreHelpTest(){
		recommend.dzeeScoreHelp();
	}
	
	@Test(priority=13)
	public void healthcareCostsHelpTest(){
		recommend.healthcareCostsHelp();
	}
		
	
	@Test(priority=14)
	public void hsaFsaHelpTest(){
		recommend.hsaFsaHelp();
	}
	
*//** 
	 * Project enroll options test scenarios
	 *//*

	@Test(priority=15)
	public void enrollForPlanPopupTest(){
		recommend.enrollForPlanPopup();
	}
	
	@Test(priority=16)
	public void goToOopDistributionTest(){
		recommend.goToOopDistribution();
	}
	
	@Test(priority=17)
	public void goToFiveYearProjectionsTest(){
		recommend.goToFiveYearProjections();
	}
	
	@Test(priority=18)
	public void goToLifetimeProjectionsTest(){
		recommend.goToLifetimeProjections();
	}
	
	*//**
	 * Plan Options test scenarios
	 *//*

    @Test(priority=19)
	public void planLiteratureTest(){
		recommend.planLiterature();
	}
	
	@Test(priority=20)
	public void findMyDoctorTest(){
		recommend.findMyDoctor();
	}
	
	@Test(priority=21)
	public void findMyRxTest(){
		recommend.findMyRx();
	}

	@Test(priority=22)
	public void informationLinkTest(){
		recommend.informationLink();
	}
	

	*//**
	 * Employer group management tab
	 *//*
	
	@Test(priority=23)
	public void employerListingTest(){
		pr.employerListing();
	}
	
	@Test(priority=24)
	public void viewEmployerGroupDetailsTest(){
		pr.viewEmployerGroupDetails();
	}
	
	@Test(priority=25)
	public void editEmployerGroupDetailsTest(){
		pr.editEmployerGroupDetails();
	}
	
	@Test(priority=26)
	public void deleteEmployerPopupTest(){
		pr.deleteEmployer();
	}
	
	@Test(priority=27)
	public void addEmployerPageTest(){
		pr.addEmployerPage();
	}
	
	@Test(priority=28)
	public void editEnrollmentYearDetailsTest(){
		pr.editEnrollmentYearDetails();
	}*/
}
