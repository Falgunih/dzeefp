package testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import commonActions.BrowserActions;
import commonActions.EmailReport;
import commonActions.ExtentReportsUtility;
import commonActions.TestDataFunctions;
import commonActions.TestTemplate;
import commonActions.ZipReport;
import pages.NewFPPage;
import pages.ReportsPage;

public class NewFPTest extends ExtentReportsUtility {
	
	public NewFPPage newfp;
	public BrowserActions browse;
	public ReportsPage report;
	public EmailReport email;
	public ZipReport zip;
	static TestDataFunctions data;
	public static String newPlanSheetName;
	public static String editPlanSheetName;
	static String testfile = System.getProperty("user.dir")+"\\src\\test\\resources\\test-input.xlsx";
	
	public NewFPTest(){
		newPlanSheetName = "New_plan";
		editPlanSheetName = "Edit_plan";
	}
	
	@BeforeClass
	public void beforeClass(){
		browse  = new BrowserActions();
		newfp = new NewFPPage(browse);
		zip = new ZipReport();
 		email = new EmailReport();
 		data = new TestDataFunctions();
 		browse.openBrowserInstance();
		newfp.goToEnvironment();
		}
	
	@BeforeMethod
	public void loginToApplication(){
		newfp.FPDemoLogin();
	}
	
	@AfterMethod
	public void logoutOfApplication(){
		newfp.logoutFromDemo();
	}
	
	@Test(priority=4, dataProvider="newPlanData", dataProviderClass=NewFPTest.class)
	public void createNewPlan(String EmailID, String primaryName, String Number, String month, String year, 
            String value, String retirement, String expectancy, String currentCode, String code){
		newfp.createNewPlan(EmailID, primaryName, Number, month, year, value, retirement, expectancy, currentCode, code);
	}
	
	@Test(priority=1, dataProvider="editPlan", dataProviderClass=NewFPTest.class)
	public void editPlanTest(String email, String retirement){
		newfp.editPlan(email, retirement);
	}
	
	@Test(priority=2, dataProvider="newPlanData", dataProviderClass=NewFPTest.class)
	public void deletePlanTest(String EmailID, String primaryName, String Number, String month, String year, 
            String value, String retirement, String expectancy, String currentCode, String code){
		newfp.deletePlan(EmailID);
	} 
	
	@Test(priority=3)
	public void helpFileTest() {
		newfp.helpFile();
	}
	
	@Test(priority=4)
	public void addNewContentMail(){
		newfp.addNewContentMail();
	}
	
	@Test(priority=5)
	public void comparePlanTest(){
		newfp.comparePlan();
	}
	
	@Test(priority=6)
	public void HealthcareVideo(){
		newfp.HealthcareVideo();
	}
	
	@Test(priority=7)
	public void financialAdvicePdf(){
		newfp.financialAdvicePdf();
	}	
	
	@Test(priority=8)
	public void medicareBundlesTest(){
		newfp.medicareBundles();
	}

	@Test(priority=9)
	public void fundWithInvestmentsTest(){
		newfp.fundWithInvestments();
	}

	@Test(priority=0)
	public void ltcProjectionsTest(){
		newfp.goToLtcProjections();
	}
	
	@DataProvider(name="newPlanData")
	public static Object[][] newPlanData(){
		return data.readAllExcel(testfile, newPlanSheetName);
	}
	
	@DataProvider(name="editPlan")
	public static Object[][] editPlan(){
		return data.readAllExcel(testfile, editPlanSheetName);
	}
	
}
