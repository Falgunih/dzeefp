/**
 * 
 */
package pages;
import java.util.HashMap;
import org.openqa.selenium.By;
import commonActions.BrowserActions;
import commonActions.ObjectMap;
/**
 * @author GS-0985
 */
public class ReportsPage {
	BrowserActions browse;
	HashMap<String, By> reportLocators = new HashMap<String, By>();
	String locatorsPath = System.getProperty("user.dir")+"\\src\\main\\java\\objectRepository\\ReportsPage.properties";
	ObjectMap objMap;
	
		public ReportsPage(BrowserActions b) {
		browse = b;
		objMap = new ObjectMap(locatorsPath);
		reportLocators();
}

		public void reportLocators(){
			reportLocators = objMap.getLocator();
		}
		
		public void employerAnalyticsReport(){
			browse.click(reportLocators.get("analyticsTab"));
			browse.click(reportLocators.get("employerAnalyticsReport"));	
			browse.threadSleepWait(2000);	
			browse.selectJcfDropdownValue(reportLocators.get("empGrpDropdown"), "Jan_demo");
			browse.assertObject(reportLocators.get("employeesCount"));
			browse.assertObject(reportLocators.get("planRecommendations"));
			browse.assertObject(reportLocators.get("employeeEnrollment"));
			browse.assertValue(reportLocators.get("employeePercentage"), "100%");
		}	
		
		public void projectedClaimReport(){
			browse.click(reportLocators.get("analyticsTab"));
			browse.click(reportLocators.get("projectedClaimReport"));
			browse.threadSleepWait(3000);
			browse.selectJcfDropdownValue(reportLocators.get("projectedClaimEmpGroup"), "Jan_demo");
			browse.click(reportLocators.get("projectedClaimSubmit"));
			browse.threadSleepWait(2000);
			browse.assertObject(reportLocators.get("populationSize"));
			browse.assertObject(reportLocators.get("expenseTable"));
			browse.assertObject(reportLocators.get("exportClaimReviewData"));
			browse.assertObject(reportLocators.get("projectedClaimEmployeeList"));
		}
		
		public void advancedAnalyticsReport(){
			browse.click(reportLocators.get("analyticsTab"));
			browse.click(reportLocators.get("advanceAnalyticsReport"));
			browse.threadSleepWait(2000);
			browse.selectJcfDropdownValue(reportLocators.get("empGrpDropdown"), "Jan_demo");
			browse.loadingSymbolState(reportLocators.get("loadingSymbol"), 10);
			browse.click(reportLocators.get("refreshAdvanceAnalytics"));
			browse.threadSleepWait(4000);
			browse.assertObject(reportLocators.get("planEvaluation"));
			browse.assertObject(reportLocators.get("preEnrollment"));
			browse.assertObject(reportLocators.get("enrollment"));
			browse.assertObject(reportLocators.get("employeeListAdvanceReport"));
			browse.assertObject(reportLocators.get("singlePlanProjections"));
			browse.assertObject(reportLocators.get("allPlansProjections"));			
			browse.assertObject(reportLocators.get("healthProfileDistribution"));
			browse.assertObject(reportLocators.get("stopLossAnalysis"));
			browse.assertObject(reportLocators.get("planTableHeader"));
		}
		
		public void wellnessReport(){
			browse.click(reportLocators.get("analyticsTab"));
			browse.jScriptClick(reportLocators.get("wellnessReport"));
			browse.loadingSymbolState(reportLocators.get("loadingSymbol"), 5);
			browse.threadSleepWait(2000);
			browse.selectJcfDropdownValue(reportLocators.get("empGrpDropdown"), "Jan_demo ");
			browse.loadingSymbolState(reportLocators.get("loadingSymbol"), 5);
			browse.assertObject(reportLocators.get("allReports"));
		}

		public void oneYearReport() {
			verifyReports(reportLocators.get("oneYearCompatibilityAnalysis"), reportLocators.get("oneYearDashboard"));
		}
		
		public void multiYearReport(){
			browse.jScriptClick(reportLocators.get("multiYearReport"));
			verifyReports(reportLocators.get("multiYearCompatibilityAnalysis"), reportLocators.get("multiYearDashboard"));
		}
		
		public void retirementReport(){
			browse.jScriptClick(reportLocators.get("retirementReport"));
			verifyReports(reportLocators.get("retirementYearCompatibilityAnalysis"), reportLocators.get("retirementYearDashboard"));
		}
		
		public void projectedReport(){
			browse.jScriptClick(reportLocators.get("projectedReport"));
			verifyReports(reportLocators.get("claimReviewCompatibilityAnalysis"), reportLocators.get("projectedDashboardReport"));
		}
		
		public void verifyExportIndividualExpenseReport(){
			browse.click(reportLocators.get("exportIndividualExpense"));
			browse.threadSleepWait(2000);
			browse.assertDownloadedFile();
		}
		
		public void verifyReports(By by1, By by2){
			browse.threadSleepWait(2000);
			browse.click(reportLocators.get("nextButton"));
			browse.assertObject(by1);
			browse.threadSleepWait(1000);
			browse.click(reportLocators.get("compatilibilityAnalysisNextButton"));
			browse.assertObject(by2);
			browse.threadSleepWait(1000);
		}
		
		public void planAnalytics(){
			browse.jScriptClick(reportLocators.get("planAnalytics"));
			browse.loadingSymbolState(reportLocators.get("loadingSymbol"), 5);
			browse.assertObject(reportLocators.get("planAnalyticsGroupSelection"));
			verifyReports(reportLocators.get("planAnalyticsCompatibility"), reportLocators.get("planAnalyticsDashboard"));
		}
		
		public void retirementHealthcareReport(){
			retirementLtcRefresh();
			browse.assertObject(reportLocators.get("retirementReportTab"));
			browse.assertObject(reportLocators.get("retirementExpenseSummary"));
			browse.assertObject(reportLocators.get("retirementLiabilityProjections"));
			browse.assertObject(reportLocators.get("cashFlowProjections"));
		}
		
		public void viewRetirementExpensePdf(){
			browse.click(reportLocators.get("ltcPdfRadioButton"));
			browse.click(reportLocators.get("viewPdfButton"));
			browse.checkNewTab();
		}
		
		public void retirementLtcRefresh() {
			browse.click(reportLocators.get("analyticsTab"));
			browse.jScriptClick(reportLocators.get("retirementLtcReport"));
			browse.threadSleepWait(2000);
			browse.selectJcfDropdownValue(reportLocators.get("empGrpDropdown"), "Jan_demo");
			browse.click(reportLocators.get("refreshButton"));
			browse.threadSleepWait(500);
			browse.assertObject(reportLocators.get("refreshHelp"));
		}
		
		public void ltcExpenseForecast(){
			retirementLtcRefresh();
			browse.assertObject(reportLocators.get("ltcForecastTab"));
			browse.click(reportLocators.get("ltcForecastTab"));
			browse.assertObject(reportLocators.get("ltcExpenseSummary"));
			browse.assertObject(reportLocators.get("ltcCashflowProjections"));
		}
		
		public void viewLTCPdf(){
			viewRetirementExpensePdf();
		}
	}

