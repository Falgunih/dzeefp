/**
 * 
 */
package pages;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;

import commonActions.BrowserActions;
import commonActions.Log;
import commonActions.ObjectMap;
import commonActions.TestDataFunctions;

/**
 * @author GS-1519
 *
 */
public class NewFPPage {

	TestDataFunctions data = new TestDataFunctions();
	BrowserActions browse;
	ObjectMap objMap;
	Properties config = data.getPropertiesFileData();
	HashMap<String, By> fpLocators = new HashMap<String, By>();
	String fpLocatorsPath = System.getProperty("user.dir")+"\\src\\main\\java\\objectRepository\\NewFPPage.properties";
	
	public NewFPPage(BrowserActions b){
		browse = b;
		objMap = new ObjectMap(fpLocatorsPath);
		fpLocators();
	}
	
	public void fpLocators(){
	  fpLocators = objMap.getLocator();
	}

	public void goToEnvironment(){
		String environment = config.getProperty("environment");
		Log.info("Conducting Sanity Test on "+ environment);
		switch(environment){
		
		case "dzeeSiteDemoUrl":
			  browse.goToUrl(config.getProperty("dzeeSiteDemoUrl"));
			  browse.threadSleepWait(5000);
			  browse.jScriptClick(fpLocators.get("loginMenu"));
			  browse.jScriptClick(fpLocators.get("FPSite"));
		break;
		
		case "demoUrl":
			browse.goToUrl(config.getProperty("demoUrl"));
			browse.threadSleepWait(500);
		break;
		
		case "fpQaUrl":
			browse.goToUrl(config.getProperty("fpQaUrl"));
			browse.threadSleepWait(500);
		break;
		
		case "fpQaEnvestnetUrl":
			browse.goToUrl(config.getProperty("fpQaEnvestnetUrl"));
			browse.threadSleepWait(500);
		break;

		case "fpDevUrl":
			browse.goToUrl(config.getProperty("fpDevUrl"));
			browse.threadSleepWait(500);
		break;

		case "fpDzeePublicUrl":
			browse.goToUrl(config.getProperty("fpDzeePublicUrl"));
			browse.threadSleepWait(500);
		break;
		
	    default:
	    	Log.fatal("The  " + environment+ " is not valid environment");
		     }
	}
	
	/** This method access the financial planner demo application from dzee solutions web site. It then tests the login functionality.
	 */
	public void FPDemoLogin(){
		browse.type(fpLocators.get("email"), config.getProperty("email"));
		browse.type(fpLocators.get("password"), config.getProperty("password"));
		browse.click(fpLocators.get("login"));
		//browse.loadingSymbolState(fpLocators.get("loaderSymbol"), 5);
		browse.threadSleepWait(500);
	}
	
	
	
	public void createNewPlan(String EmailID, String primaryName, String Number, String month, String year, 
			                  String value, String retirement, String expectancy, String currentCode, String code){
		browse.threadSleepWait(500);
		browse.click(fpLocators.get("createNewPlan"));
		browse.assertObject(fpLocators.get("personalInformationFrame"));
		browse.threadSleepWait(500);
		browse.type(fpLocators.get("emailId"), EmailID);
		browse.type(fpLocators.get("firstName"), primaryName);
		browse.type(fpLocators.get("lastName"), primaryName);
		browse.type(fpLocators.get("number"), Number);
		browse.selectDropDownMenu(fpLocators.get("monthOfBirth"), month);
		browse.selectDropDownMenu(fpLocators.get("birthYear"), year);
		browse.selectDropDownMenu(fpLocators.get("health"), value);
		browse.type(fpLocators.get("retirementAge"), retirement);
		browse.type(fpLocators.get("lifeExpectancy"), expectancy);
		browse.type(fpLocators.get("currentZip"), currentCode);
		browse.type(fpLocators.get("retirementZip"), code);
		goThroughTabs();		
	}

	public void editPlan(String email, String retirement){
		browse.threadSleepWait(2000);
		//By editPlan = By.xpath("//a[contains(@onclick,'"+email+"')][@title=\"Edit Plan\"]");
		//By editPlan = By.xpath("//a[contains(@onclick, '"+email+"')])[4]");
		searchPlan(email);
		browse.click(fpLocators.get("clickEditArrowButton"));
		browse.jScriptClick(fpLocators.get("editPlan"));
		browse.threadSleepWait(2000);
		browse.assertObject(fpLocators.get("personalInformationFrame"));
		browse.type(fpLocators.get("retirementAge"), retirement);
		browse.threadSleepWait(2000);
		goThroughTabs();
	}

	public void deletePlan(String email){
		/*browse.loadingSymbolState(fpLocators.get("loaderSymbol"), 8);
		By plan = By.xpath("//a[@onclick=\"deleteAllPlans('"+email+"')\"]");
		browse.click(plan);*/
		
		browse.threadSleepWait(2000);
		searchPlan(email);					
		browse.click(fpLocators.get("deleteAllPlanButton"));
		browse.assertObject(fpLocators.get("deleteAllPlanPopup"));
		browse.threadSleepWait(500);
		
		browse.click(fpLocators.get("deleteAllPlanOKButton"));
		browse.threadSleepWait(200);
		browse.assertObject(fpLocators.get("deletedSuccessfullyPopup"));
	}
	
	private void goThroughTabs() {
		browse.threadSleepWait(5000);
		browse.click(fpLocators.get("medicareFrame"));
		browse.threadSleepWait(5000);		
		//browse.waitForPageToLoad(15000);
		browse.click(fpLocators.get("preMedicareFrame"));
		//browse.waitForPageToLoad(15000);
		browse.threadSleepWait(5000);
		browse.click(fpLocators.get("ltcProjectionsFrame"));
		//browse.waitForPageToLoad(15000);
		browse.threadSleepWait(5000);
		browse.waitForElementToBeClickable(fpLocators.get("runAnalysis"));
		browse.click(fpLocators.get("runAnalysis"));
		browse.assertObject(fpLocators.get("previewPopup"));
		browse.click(fpLocators.get("noButton"));
		assertProjections();
		browse.click(fpLocators.get("goToHomePageButton"));
	}
	
	private void searchPlan(String email){
	browse.type(fpLocators.get("searchPlan"), email);
	browse.pressEnterKey();
	browse.threadSleepWait(2000);
	}
	
	public void addNewContentMail(){
		browse.click(fpLocators.get("addNewContent"));
		browse.assertObject(fpLocators.get("addContentPopup"));
		browse.type(fpLocators.get("addContentEmailId"), config.getProperty("pdfEmail"));
		browse.click(fpLocators.get("addContentSubmitButton"));
		browse.threadSleepWait(500);
		//browse.loadingSymbolState(fpLocators.get("loaderSymbol"), 5);
		browse.assertObject(fpLocators.get("emailSentMessage"));
	}
	
	public void helpFile(){
		browse.click(fpLocators.get("helpFileIcon"));
		browse.threadSleepWait(500);
		
		//browse.loadingSymbolState(fpLocators.get("loaderSymbol"), 5);
		browse.assertObject(fpLocators.get("helpFile"));
		browse.click(fpLocators.get("helpPageBackButton"));
	}
	
	public void comparePlan(){
		browse.threadSleepWait(5000);
		browse.loadingSymbolState(fpLocators.get("loaderSymbol"), 10);
		browse.jScriptClick(fpLocators.get("firstCheckbox"));
		browse.jScriptClick(fpLocators.get("secondCheckbox"));
		browse.threadSleepWait(3000);
		browse.click(fpLocators.get("comparePlans"));
		//browse.loadingSymbolState(fpLocators.get("comparePlanLoadingIcon"), 4);		
		browse.checkNewTab();		
		browse.threadSleepWait(500);
	}
	
	public void HealthcareVideo(){
		seeVideosLink(fpLocators.get("retirementHealthcareVideo"));
	}
	
	public void financialAdvicePdf(){
		seeVideosLink(fpLocators.get("estimatingCostsPdf"));		
		/*List<String> browseTab = browse.checkNewTab1();		
		browse.assertObject(fpLocators.get("assertestimatingCostsPdf"));
		browse.closeTab(browseTab);*/		
	}
	
	public void seeVideosLink(By by){
		browse.click(by);
		browse.checkNewTab();
	}
	
	/** This method view the first plan from existing plans. It asserts whether yearly and five year summary projections are displayed or not.
	 */
	public void viewProjections(){
		browse.click(fpLocators.get("expandFirstPlan"));
		browse.click(fpLocators.get("viewPlan"));
		assertProjections();
	}
	
	/** This method tests medicare bundles tab.
	 */
	public void medicareBundles(){
		viewProjections();
		browse.click(fpLocators.get("medicareBundle"));
		browse.loadingSymbolState(fpLocators.get("loaderSymbol"), 5);
		browse.assertObject(fpLocators.get("assertMedicareBundleTable"));
	}

	public void fundWithInvestments(){
		viewProjections();
		browse.click(fpLocators.get("fundWithInvestments"));
		browse.loadingSymbolState(fpLocators.get("loaderSymbol"), 5);
		browse.assertObject(fpLocators.get("assertInvestmentsGraph"));
	}
	
	public void goToLtcProjections(){
		viewProjections();
		browse.click(fpLocators.get("ltcProjectionsFrame"));
		browse.assertObject(fpLocators.get("assertLtc"));
		//browse.checkLtcTab(fpLocators.get("ltcProjectionsHeader"));
	}
	
	/*public void investmentExpenseOutlay(){
		fundWithInvestments();
		browse.click(fpLocators.get("investmentExpenseOutlay"));
		browse.threadSleepWait(2000);
		browse.assertObject(fpLocators.get("investmentExpenseProjections"));
		browse.pressEscKey();
		browse.click(fpLocators.get("fundingThroughInvestmentsPrevious"));
	}
	*/
	
	public void viewFiveYearProjections(){
		viewProjections();
		browse.click(fpLocators.get("fiveYearProjections"));
		assertProjections();
	}
	
	public void fundingWithAnnuity(){
		viewProjections();
		browse.loadingSymbolState(fpLocators.get("loaderSymbol"), 8);
		browse.click(fpLocators.get("fundWithAnnuity"));
		browse.loadingSymbolState(fpLocators.get("loaderSymbol"), 8);
		browse.assertObject(fpLocators.get("fundingThroughAnnuity"));
		browse.click(fpLocators.get("fundingThroughAnnuityPrevious"));
		browse.loadingSymbolState(fpLocators.get("loaderSymbol"), 8);
	}
	
	public void presentValue(){
		viewProjections();
		browse.loadingSymbolState(fpLocators.get("loaderSymbol"), 8);
		browse.click(fpLocators.get("presentValue"));
		browse.loadingSymbolState(fpLocators.get("loaderSymbol"), 8);
		browse.assertObject(fpLocators.get("presentValuePage"));
		browse.click(fpLocators.get("presentValuePrevious"));
		}
	
	public void emailPdf(){
		viewProjections();
		browse.click(fpLocators.get("emailPdf"));
		browse.assertObject(fpLocators.get("sendMailPage"));
		browse.type(fpLocators.get("enterEmail"), config.getProperty("pdfEmail"));
		browse.click(fpLocators.get("sendMailButton"));
		browse.assertObject(fpLocators.get("mailSentMessage"));
		browse.threadSleepWait(500);
		browse.click(fpLocators.get("emailPageBackButton"));
	}
	
	public void viewPdf(){
		viewProjections();
		browse.click(fpLocators.get("viewPdf"));
		browse.assertObject(fpLocators.get("pdfOptions"));
		browse.click(fpLocators.get("generatePdf"));
		browse.checkNewTab();
		browse.click(fpLocators.get("viewPdfPreviousButton"));
	}
	
	public void personalInformation(){
		viewProjections();
		browse.loadingSymbolState(fpLocators.get("loaderSymbol"), 8);
		browse.threadSleepWait(3000);
		browse.click(fpLocators.get("personalInformation"));
		browse.loadingSymbolState(fpLocators.get("loaderSymbol"), 8);
		browse.assertObject(fpLocators.get("personalInformationFrame"));
		browse.threadSleepWait(1000);
		browse.click(fpLocators.get("personalInformationPrevious"));
	    browse.loadingSymbolState(fpLocators.get("loaderSymbol"), 8);
		browse.assertObject(fpLocators.get("planHistory"));
	}
	
	public void helpIcon(){
		browse.threadSleepWait(1000);
		browse.click(fpLocators.get("helpIcon"));
		browse.threadSleepWait(1000);
		browse.assertObject(fpLocators.get("helpPage"));
		browse.threadSleepWait(1000);
		browse.click(fpLocators.get("helpPageBackButton"));
	}
	
	public void createPlanButton(){
		browse.jScriptClick(fpLocators.get("createNewPlan"));
		browse.assertObject(fpLocators.get("createPlanPopup"));
		browse.pressEscKey();
		browse.click(fpLocators.get("createPlanIcon"));
		browse.assertObject(fpLocators.get("createPlanPopup"));
		browse.pressEscKey();
	}	
	
	public void goToCreateFinancialPlannerPage(){
		browse.click(fpLocators.get("settingsIcon"));
		browse.jScriptClick(fpLocators.get("createFinancialPlanner"));
		browse.threadSleepWait(2000);
	}
	
	public void uploadLogo(){
		
	}
	
	public void createFinancialPlanner(String name, String title, String email, String org, String pwd, String address, String number, 
															  String city, String fax, String zip, String country ){
		goToCreateFinancialPlannerPage();
		browse.type(fpLocators.get("plannerName"), name);
		browse.type(fpLocators.get("plannerTitle"), title);
		browse.type(fpLocators.get("plannerEmail"), email);
		browse.type(fpLocators.get("plannerOrganization"), org);
		browse.type(fpLocators.get("plannerPassword"), pwd);
		browse.type(fpLocators.get("plannerAddress"), address);
		browse.type(fpLocators.get("plannerContactNumber"), number);
		browse.type(fpLocators.get("plannerCity"), city);
		browse.type(fpLocators.get("plannerFax"), fax);
		browse.type(fpLocators.get("plannerZipCode"), zip);
		browse.type(fpLocators.get("plannerCountry"), country);
	}
	
	public void logoutFromDemo(){
		browse.jScriptClick(fpLocators.get("homePageLogoutButton"));
		browse.assertObject(fpLocators.get("assertLoginPage"));
	}
	
	public void assertProjections() {
		browse.threadSleepWait(2000);
		browse.loadingSymbolState(fpLocators.get("loaderSymbol"), 15);
		browse.assertObject(fpLocators.get("graphicalProjections"));
	}
}
