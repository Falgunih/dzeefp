package commonActions;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Properties;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.NetworkMode;

public class ExtentReportsUtility{
	protected static ExtentReports extent;
	protected static ExtentTest test;
	TestDataFunctions data = new TestDataFunctions();
	String reportFileLocation=System.getProperty("user.dir")+"//test report//Report.html";
	Properties config = data.getPropertiesFileData();
	public boolean value=false;

	//@BeforeClass
	@BeforeSuite
	public void startReport(){
		extent = new ExtentReports(reportFileLocation, true,  DisplayOrder.NEWEST_FIRST , NetworkMode.OFFLINE);
		extent
				.addSystemInfo("Environment Name", config.getProperty("environment"))
				.addSystemInfo("Author Name", "Mayuri Wavhal");
		extent.loadConfig(new File(System.getProperty("user.dir")+"//extent-config.xml"));
	}
	

	@BeforeMethod
	public void startTest(Method method){
		test = extent.startTest(method.toString());
		
	}
	 @AfterMethod
	 public void getResult(ITestResult result, Method method){
		 	if(result.getStatus() == ITestResult.FAILURE){
				test.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
				test.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
				/*String methodName=result.getName().trim();
				browse.takeScreenshot(methodName);*/
			}else if(result.getStatus() == ITestResult.SKIP){
				test.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
			}else if(result.getStatus() == ITestResult.SUCCESS){
				test.log(LogStatus.PASS, "Test Case Passed is " +result.getName());
			}
			extent.endTest(test);
}
	 
	 @AfterSuite
		public void endReport(){
			extent.flush();
			extent.close();    
}
		}

