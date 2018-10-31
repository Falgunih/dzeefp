package pages;
import org.openqa.selenium.By;
import commonActions.BrowserActions;
import commonActions.PageTemplate;

	public class RecommendedPlans extends PageTemplate {
	static String pageLocatorsPath = System.getProperty("user.dir")+"\\src\\main\\java\\objectRepository\\PRDemoPage.properties";
	
	public RecommendedPlans(BrowserActions b){
		super(b, pageLocatorsPath);
	}

	public void openJanDemoEmployeeList(){
		browse.selectJcfDropdownValue(pageLocators.get("employerGroup"), "Jan_demo");
		browse.click(pageLocators.get("employeeListButton"));
		browse.threadSleepWait(2000);
	}
	
	public void goToJanTwoEmployeeProfile(){
		openJanDemoEmployeeList();
		browse.jScriptClick(pageLocators.get("janTwoEmployee"));
		browse.threadSleepWait(2000);
		browse.waitForPageToLoad(20);
		browse.click(pageLocators.get("recommendedPlansTab"));
		browse.threadSleepWait(2000);
		browse.waitForPageToLoad(5);
		browse.click(pageLocators.get("selectSecondPlan"));
	}
	
	public void goToRecommendedPlansTab(){
		goToJanTwoEmployeeProfile();
		browse.threadSleepWait(2000);
		browse.scrollUp();
	}
	
	public void tellMeAboutPage(){
		goToRecommendedPlansTab();
		checkPopUpElements(pageLocators.get("tellMeAboutThisPageButton"), pageLocators.get("tellMeAboutPagePopUp"));
	}
	
	public void whatCanIDoNext(){
		checkPopUpElements(pageLocators.get("whatCanIDoNextButton"), pageLocators.get("whatCanIDoNextPopUp"));
	}
	
	public void oopDetails(){
		checkPopUpElements(pageLocators.get("oopDetailsOfFirstPlan"), pageLocators.get("oopDetailsPopUp"));
	}
	
	public void overrideHsaFsaContribution(){
		checkPopUpElements(pageLocators.get("overrideContribution"), pageLocators.get("overridePopUpTitle"));
	}
	
	public void customSupplementPlanPopup(){
		checkPopUpElements(pageLocators.get("customSupplementPlan"), pageLocators.get("customSupplementPlanPopUp"));
	}
	
	public void planDetails(){
		browse.jScriptClick(pageLocators.get("planOptionsOfFirstPlan"));
		checkPopUpElements(pageLocators.get("planDetailsOfFirstPlan"), pageLocators.get("planDetailsPopUp"));
	}
	
	public void dzeeScoreHelp(){
		checkPopUpElements(pageLocators.get("dzeeScoreHelp"), pageLocators.get("dzeeScorePopup"));
	}
	
	public void healthcareCostsHelp(){
		checkPopUpElements(pageLocators.get("healthcareCostsHelp"), pageLocators.get("healthcareCostsPopup"));
	}
	
	public void hsaFsaHelp(){
		checkPopUpElements(pageLocators.get("hsaFsaHelp"), pageLocators.get("hsaFsaPopup"));
	}

	public void supplementPlanPdf(){
		browse.click(pageLocators.get("supplementPlanPdfOfFirstPlan"));
		browse.checkNewTab();
	}
	
	public void enrollForPlanPopup(){
		checkProjectEnrollOptions(pageLocators.get("enrollInFirstPlan"), pageLocators.get("enrollPlanConfirmationPopUp"), pageLocators.get("closePlanEnrollmentPopUp"));
	}
	
	public void goToOopDistribution(){
		checkProjectEnrollOptions(pageLocators.get("oopFirstPlan"), pageLocators.get("oopHeader"), pageLocators.get("oopRecommendedPlansButton"));
	}
	
	public void goToFiveYearProjections(){
		checkProjectEnrollOptions(pageLocators.get("fiveYearProjectionsOfFirstPlan"), pageLocators.get("fiveYearProjectionsHeader"), pageLocators.get("fiveYearRecommendedPlansButton"));
	}
	
	public void goToLifetimeProjections(){
		checkProjectEnrollOptions(pageLocators.get("lifetimeProjectionsOfFirstPlan"), pageLocators.get("lifetimeProjections"), pageLocators.get("lifetimeRecommendedPlansButton"));
	}
	
	public void planLiterature(){
		checkPlanOptions(pageLocators.get("planLiteratureOfFirstPlan"));
	}
	
	public void findMyDoctor(){
		checkPlanOptions(pageLocators.get("findMyDoctorOfFirstPlan"));
	}
	
	public void findMyRx(){
		checkPlanOptions(pageLocators.get("findMyRxOfFirstPlan"));
	}

	public void informationLink(){
		browse.click(pageLocators.get("informationProvidedLink"));
		browse.assertObject(pageLocators.get("myProfileTitle"));
		browse.click(pageLocators.get("recommendedPlansTab"));
		browse.threadSleepWait(3000);
	}
	
	public void checkPlanOptions(By by) {
		browse.click(pageLocators.get("planOptionsOfFirstPlan"));
		browse.threadSleepWait(1000);
		browse.jScriptClick(by);
		browse.threadSleepWait(5000);
		browse.checkNewTab();
	}

	public void checkProjectEnrollOptions(By by1, By by2, By by3) {
		browse.jScriptClick(pageLocators.get("firstPlanProjectionsEnroll"));
		browse.jScriptClick(by1);
		browse.assertObject(by2);
		browse.threadSleepWait(2000);
		browse.jScriptClick(by3);
		browse.threadSleepWait(4000);
	}
	
	public void checkPopUpElements(By by1, By by2) {
		browse.jScriptClick(by1);
		browse.assertObject(by2);
		browse.pressEscKey();
		browse.threadSleepWait(500);
	}
}
