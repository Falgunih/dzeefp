package testcases;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import commonActions.TestTemplate;
public class FPDemoTest extends TestTemplate {
	
	public FPDemoTest(){
		newPlanSheetName = "New_plan";
		editPlanSheetName = "Edit_plan";
	}	

	@BeforeClass
	public void goToEnviroment(){
		fp.goToEnvironment();
		}
	
	@BeforeMethod
	public void loginToApplication(){
		fp.FPDemoLogin();
	}
	
	@AfterMethod
	public void logoutOfApplication(){
		fp.logoutFromDemo();
	}
	
	@Test(priority=0, dataProvider="newPlanData", dataProviderClass=TestTemplate.class)
	public void createNewPlan(String primaryName, String Number, String EmailID, String month, String year, String option,
            String Gender, String value, String retirement, String expectancy, String currentCode, String code){
		fp.createNewPlan(primaryName, Number, EmailID, month, year, option, Gender, value, retirement, expectancy, currentCode, code);
	}
	
   @Test(priority=1, dataProvider="editPlan", dataProviderClass=TestTemplate.class)
	public void editPlanTest(String email, String retirement){
		fp.editPlan(email, retirement);
	}
	
	@Test(priority=2, dataProvider="newPlanData", dataProviderClass=TestTemplate.class)
	public void deletePlanTest(String primaryName, String Number, String EmailID, String month, String year, String option,
            String Gender, String value, String retirement, String expectancy, String currentCode, String code){
		fp.deletePlan(EmailID);
	}
	
	@Test(priority=3)
	public void helpFileTest(){
		fp.helpFile();
	}

    @Test(priority=4)
	public void createPlanButtonTest(){
		fp.createPlanButton();
	}
	
	@Test(priority=5)
	public void retirementHealthcareVideo(){
		fp.retirementHealthcareVideo();
	}
	
	@Test(priority=6)
	public void estimatingCostsPdf(){
		fp.estimatingCostsPdf();
	}
	
	@Test(priority=7)
	public void medicareBundlesTest(){
		fp.compareMedicareBundles();
	}

	@Test(priority=8)
	public void fundWithInvestmentsTest(){
		fp.fundWithInvestments();
	}
	
	@Test(priority=9)
    public void investmentExpenseOutlayTest(){
		fp.investmentExpenseOutlay();
	}
	
	@Test(priority=10)	
	public void fundWithAnnuityTest(){
		fp.fundingWithAnnuity();
	}
	
	@Test(priority=11)
	public void presentValueTest(){
		fp.presentValue();
	}
	
	@Test(priority=12)
	public void emailPdfTest(){
		fp.emailPdf();
	}
	
	@Test(priority=13)
	public void viewPdfTest(){
		fp.viewPdf();
	}

	@Test(priority=14)
	public void personalInformationTest(){
		fp.personalInformation();
	}

   @Test(priority=15)	
	public void comparePlanTest(){
		fp.comparePlan();
	}
	
	@Test(priority=16)
	public void viewYearlyProjections(){
		fp.viewProjections();
	}
	
	@Test(priority=17)
	public void viewFiveYearProjections(){
		fp.viewFiveYearProjections();
	}
	
	@Test(priority=18)
	public void addNewContentMail(){
		fp.addNewContentMail();
	}
	
	@Test(priority=19)
	public void ltcProjectionsTest(){
		fp.goToLtcProjections();
	}
	
	@AfterSuite
	public void sendReport(){
		String latestReportPath = zip.zipTestReport();
			email.sendMail(latestReportPath);
	 }
}
