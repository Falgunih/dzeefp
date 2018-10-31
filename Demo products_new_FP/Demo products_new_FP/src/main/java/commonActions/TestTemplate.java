package commonActions;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import pages.FPDemoPage;
import pages.MyProfilePage;
import pages.PRDemoPage;
import pages.RecommendedPlans;
import pages.ReportsPage;
import pages.ViewPlansPage;

public class TestTemplate extends ExtentReportsUtility {
	protected BrowserActions browse;
	protected FPDemoPage fp;
	protected PRDemoPage pr;
	protected RecommendedPlans recommend;
	protected ReportsPage report;
	protected EmailReport email;
	protected ZipReport zip;
	protected MyProfilePage profile;
	protected ViewPlansPage view;	
	public PageTemplate page;
	static TestDataFunctions data;
	public static String newPlanSheetName;
	public static String editPlanSheetName;
	static String testfile = System.getProperty("user.dir")+"\\src\\test\\resources\\test-input.xlsx";
	
	/** This method runs pre-requisites for test case execution. 
	 */
	@BeforeClass
	public void beforeClass() {
		browse = new BrowserActions();
 		fp = new FPDemoPage(browse);
 		pr = new PRDemoPage(browse);
 		/*report = new ReportsPage(browse);
 		recommend = new RecommendedPlans(browse);
 		view = new ViewPlansPage(browse);
 		profile = new MyProfilePage(browse);*/
 		zip = new ZipReport();
 		email = new EmailReport();
 		data = new TestDataFunctions();
 		browse.openBrowserInstance();
 		pr.goToEnvironment();
		pr.brokerLogin();
		browse.waitForPageToLoad(5);
 		}	
	
	@DataProvider(name="newPlanData")
	public static Object[][] newPlanData(){
		return data.readAllExcel(testfile, newPlanSheetName);
	}
	
	@DataProvider(name="editPlan")
	public static Object[][] editPlan(){
		return data.readAllExcel(testfile, editPlanSheetName);
	}
	
	/**This method closes all windows associated with tests
	 */
	@AfterClass
	 public void stopTest(){
		 browse.quitDriver();
	 }

}