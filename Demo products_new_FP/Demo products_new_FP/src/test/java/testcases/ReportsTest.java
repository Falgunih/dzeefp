/**
 * 
 */
package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commonActions.TestTemplate;

/**
 * @author GS-0985
 *
 */
public class ReportsTest extends TestTemplate {
	
	@BeforeClass
	public void goToUrl(){
		pr.goToEnvironment();
		pr.brokerLogin();
		browse.waitForPageToLoad(5);
	}

	@Test(priority=0)
	public void employerAnalyticsReportTest(){
		report.employerAnalyticsReport();
	}

	@Test(priority=1)
	public void projectedClaimExpenseReportTest(){
		report.projectedClaimReport();
	}
	
	@Test(priority=2)
	public void advanceAnalyticsReportTest(){
		report.advancedAnalyticsReport();
	}

	@Test(priority=3)
	public void wellnessReportTest(){
		report.wellnessReport();
	}
	
	@Test(priority=4)
	public void oneYearReportTest(){
		report.oneYearReport();
	}

	@Test(priority=5)
	public void multiYearReportTest(){
		report.multiYearReport();
	}
	
	@Test(priority=6)
	public void retirementReportTest(){
		report.retirementReport();
	}
	
	@Test(priority=7)
	public void projectedReportTest(){
		report.projectedReport();
	}
	
	@Test(priority=8)
	public void verifyIndividualExpenseReportTest(){
		report.verifyExportIndividualExpenseReport();
	}
	
	@Test(priority=9)
	public void planAnalyticsReportTest(){
		report.planAnalytics();
	}
	
	@Test(priority=10)
	public void retirementHealthcareReportTest(){
		report.retirementHealthcareReport();
	}
	
	@Test(priority=11)
	public void viewRetirementExpensePdfTest(){
		report.viewRetirementExpensePdf();
	}
	
	@Test(priority=12)
	public void ltcExpenseForecastTest(){
		report.ltcExpenseForecast();
	}
	
	@Test(priority=13)
	public void viewLTCPdfTest(){
		report.viewLTCPdf();
	}
}
