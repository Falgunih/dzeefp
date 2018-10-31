package pages;

import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.By;

import commonActions.BrowserActions;
import commonActions.Log;
import commonActions.ObjectMap;
import commonActions.TestDataFunctions;

public class FPDemoPage {
	TestDataFunctions data = new TestDataFunctions();
	BrowserActions browse;
	ObjectMap objMap;
	Properties config = data.getPropertiesFileData();
	HashMap<String, By> fpLocators = new HashMap<String, By>();
	String fpLocatorsPath = System.getProperty("user.dir")+"\\src\\main\\java\\objectRepository\\FPDemoPage.properties";
	
	public FPDemoPage(BrowserActions b){
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
		browse.loadingSymbolState(fpLocators.get("loaderSymbol"), 5);
	}
	
	public void addNewContentMail(){
		browse.click(fpLocators.get("addNewContent"));
		browse.assertObject(fpLocators.get("addContentPopup"));
		browse.loadingSymbolState(fpLocators.get("loaderSymbol"), 5);
		browse.type(fpLocators.get("addContentEmailId"), config.getProperty("pdfEmail"));
		browse.click(fpLocators.get("addContentSubmitButton"));
		browse.assertObject(fpLocators.get("emailSentMessage"));
	}
	
	public void helpFile(){
		browse.click(fpLocators.get("helpFileIcon"));
		browse.loadingSymbolState(fpLocators.get("loaderSymbol"), 5);
		browse.assertObject(fpLocators.get("helpFile"));
		browse.click(fpLocators.get("helpPageBackButton"));
	}
	
	public void createNewPlan(String primaryName, String Number, String EmailID, String month, String year, String option,
            String Gender, String value, String retirement, String expectancy, String currentCode, String code){
		browse.click(fpLocators.get("createNewPlan"));
		browse.assertObject(fpLocators.get("createPlanPopup"));
		browse.threadSleepWait(500);
		browse.click(fpLocators.get("createPlanNextButton"));
		browse.threadSleepWait(5000);
		browse.loadingSymbolState(fpLocators.get("loaderSymbol"), 10);
		browse.type(fpLocators.get("name"), primaryName);
		browse.type(fpLocators.get("number"), Number);
		browse.type(fpLocators.get("emailId"), EmailID);
		browse.selectDropDownMenu(fpLocators.get("monthOfBirth"), month);
		browse.type(fpLocators.get("birthYear"), year);
		browse.selectDropDownMenu(fpLocators.get("tobaccoState"), option);
		browse.selectDropDownMenu(fpLocators.get("gender"), Gender);
		browse.selectDropDownMenu(fpLocators.get("health"), value);
		browse.type(fpLocators.get("retirementAge"), retirement);
		browse.threadSleepWait(3000);
		browse.type(fpLocators.get("lifeExpectancy"), expectancy);
		browse.type(fpLocators.get("currentZip"), currentCode);
		browse.type(fpLocators.get("retirementZip"), code);
		browse.threadSleepWait(5000);
		browse.assertObject(fpLocators.get("preMedicareFrame"));
		browse.jScriptClick(fpLocators.get("preMedicareFrame"));
		browse.threadSleepWait(2000);
		browse.loadingSymbolState(fpLocators.get("loaderSymbol"), 5);
		browse.assertObject(fpLocators.get("medicareFrame"));
		browse.jScriptClick(fpLocators.get("medicareFrame"));
		browse.loadingSymbolState(fpLocators.get("loaderSymbol"), 5);
		browse.threadSleepWait(5000);
		browse.jScriptClick(fpLocators.get("planInformationNextButton"));
		browse.threadSleepWait(5000);
		assertProjections();
		//browse.click(fpLocators.get("projectionsPageHomeIcon"));
	//	browse.loadingSymbolState(fpLocators.get("loaderSymbol"), 5);
	}
	
	public void retirementHealthcareVideo(){
		seeVideosLink(fpLocators.get("retirementHealthcareVideo"));
	}
	
	public void estimatingCostsPdf(){
		seeVideosLink(fpLocators.get("estimatingCostsPdf"));
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
	
	public void viewFiveYearProjections(){
		viewProjections();
		browse.click(fpLocators.get("fiveYearProjections"));
		assertProjections();
	}
	
	/** This method tests the pop up appears after clicking compare medicare bundles button.
	 */
	public void compareMedicareBundles(){
		viewProjections();
		browse.click(fpLocators.get("compareMedicareBundles"));
		browse.assertObject(fpLocators.get("medicareBundlePopup"));
		browse.pressEscKey();
	}

	public void fundWithInvestments(){
		viewProjections();
		browse.click(fpLocators.get("fundWithInvestments"));
		browse.loadingSymbolState(fpLocators.get("loaderSymbol"), 8);
		browse.assertObject(fpLocators.get("fundingThroughInvestments"));
	}
	
	public void investmentExpenseOutlay(){
		fundWithInvestments();
		browse.click(fpLocators.get("investmentExpenseOutlay"));
		browse.threadSleepWait(2000);
		browse.assertObject(fpLocators.get("investmentExpenseProjections"));
		browse.pressEscKey();
		browse.click(fpLocators.get("fundingThroughInvestmentsPrevious"));
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
	
	public void editPlan(String email, String retirement){
		browse.loadingSymbolState(fpLocators.get("loaderSymbol"), 8);
		browse.click(fpLocators.get("refreshPlans"));
		browse.threadSleepWait(2000);
		browse.jScriptClick(fpLocators.get("expandNewPlan"));
		By editPlan = By.xpath("//a[contains(@onclick,'"+email+"')][@title=\"Edit Plan\"]");
		browse.click(editPlan);
		browse.threadSleepWait(2000);
		browse.loadingSymbolState(fpLocators.get("loaderSymbol"), 8);
		browse.assertObject(fpLocators.get("personalInformationFrame"));
		browse.type(fpLocators.get("retirementAge"), retirement);
		browse.threadSleepWait(5000);
		browse.click(fpLocators.get("medicareFrame"));
		browse.loadingSymbolState(fpLocators.get("loaderSymbol"), 5);
		browse.threadSleepWait(5000);
		//browse.click(fpLocators.get("planInformationNextButton"));
		browse.click(fpLocators.get("submitButton"));
		browse.assertObject(fpLocators.get("previewPopup"));
		browse.click(fpLocators.get("noButton"));
		browse.threadSleepWait(5000);
		assertProjections();
		browse.click(fpLocators.get("projectionsPageHomeIcon"));
	}
	
	public void comparePlan(){
		browse.loadingSymbolState(fpLocators.get("loaderSymbol"), 8);
		browse.jScriptClick(fpLocators.get("firstCheckbox"));
		browse.jScriptClick(fpLocators.get("secondCheckbox"));
		browse.click(fpLocators.get("comparePlans"));
		browse.loadingSymbolState(fpLocators.get("loaderSymbol"), 8);
		//browse.assertObject(fpLocators.get("comparePlansPopup"));
		browse.checkNewTab();
		browse.pressEscKey();
		browse.threadSleepWait(500);
	}
	
	public void deletePlan(String email){
		browse.loadingSymbolState(fpLocators.get("loaderSymbol"), 8);
		By plan = By.xpath("//a[@onclick=\"deleteAllPlans('"+email+"')\"]");
		browse.click(plan);
		browse.assertObject(fpLocators.get("deletePlanPopup"));
		browse.click(fpLocators.get("cancelDeletion"));
		browse.threadSleepWait(500);
		browse.click(plan);
		browse.assertObject(fpLocators.get("deletePlanPopup"));
		browse.click(fpLocators.get("deletePlanOKButton"));
		browse.assertObject(fpLocators.get("deletedSuccessfullyPopup"));
	}

	public void goToLtcProjections(){
		viewProjections();
		browse.click(fpLocators.get("ltcButton"));
		browse.checkLtcTab(fpLocators.get("ltcProjectionsHeader"));
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
		browse.assertObject(fpLocators.get("registeredUsers"));
	}
	
	public void assertProjections() {
		browse.loadingSymbolState(fpLocators.get("loaderSymbol"), 15);
		browse.assertObject(fpLocators.get("graphicalProjections"));
	}
}
