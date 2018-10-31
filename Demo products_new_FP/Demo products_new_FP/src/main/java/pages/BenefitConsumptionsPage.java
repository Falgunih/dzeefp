package pages;

import org.openqa.selenium.By;
import commonActions.BrowserActions;
import commonActions.PageTemplate;

public class BenefitConsumptionsPage extends PageTemplate {
    String doctorBenefit, drugBenefit, hospInBenefit, hospOutBenefit, labsBenefit;
    RecommendedPlans recommend;
	static String pageLocatorsPath = System.getProperty("user.dir")+"\\src\\main\\java\\objectRepository\\BenefitConsumptionsPage.properties";
	
	public BenefitConsumptionsPage (BrowserActions b){
		super(b, pageLocatorsPath);
	}

	public void goToBenefitConsumptionsTab(){
		browse.threadSleepWait(2000);
		recommend.goToJanTwoEmployeeProfile();
		browse.click(pageLocators.get("benefitTab"));
	}
	
	public void getConsumptionsValue(){
		doctorBenefit = getDefaultBenefitValue(pageLocators.get("doctorVisitSlider"));
		drugBenefit = getDefaultBenefitValue(pageLocators.get("drugPrescriptionSlider"));
		hospInBenefit = getDefaultBenefitValue(pageLocators.get("hospitalInPatientSlider"));
		hospOutBenefit = getDefaultBenefitValue(pageLocators.get("hospitalOutPatientSlider"));
		labsBenefit = getDefaultBenefitValue(pageLocators.get("labsAndEquipmentSlider"));
	}
	
	public String getDefaultBenefitValue(By by){
		return browse.getText(by);
	}
	
	public void changeBenefitConsumptions(String doctorVisits, String drugPrescriptions, String hospitalIn, String hospitalOut, String labsAndEquipments){
		getConsumptionsValue();
		enterBenefitValue(pageLocators.get("doctorVisitSlider"), doctorVisits);
	    enterBenefitValue(pageLocators.get("drugPrescriptionSlider"), drugPrescriptions);
		enterBenefitValue(pageLocators.get("hospitalInPatientSlider"), hospitalIn);
		enterBenefitValue(pageLocators.get("hospitalOutPatientSlider"), hospitalOut);
		enterBenefitValue(pageLocators.get("labsAndEquipmentSlider"), labsAndEquipments);
		browse.assertObject(pageLocators.get("resetButton"));
	}
	
	public void recalculateConsumptionPopup(){
		recalculateBenefitConsumption(pageLocators.get("recommendedPlansTab"));
		recalculateBenefitConsumption(pageLocators.get("oopTab"));
		recalculateBenefitConsumption(pageLocators.get("fiveYearProjectionsTab"));
		recalculateBenefitConsumption(pageLocators.get("lifetimeProjectionsTab"));
		recalculateBenefitConsumption(pageLocators.get("ltcProjectionsTab"));
	}

	public void recalculateBenefitConsumption(By by){
		browse.click(by);
		browse.assertObject(pageLocators.get("recalculateBenefitConsumption"));
		browse.pressEscKey();
		browse.threadSleepWait(500);
	}
	
	public void verifyResetBenefitValue(){
		browse.click(pageLocators.get("resetButton"));
		browse.assertText(pageLocators.get("doctorVisitSlider"), doctorBenefit);
		browse.assertText(pageLocators.get("drugPrescriptionSlider"), drugBenefit);
		browse.assertText(pageLocators.get("hospitalInPatientSlider"), hospInBenefit);
		browse.assertText(pageLocators.get("hospitalOutPatientSlider"), hospOutBenefit);
		browse.assertText(pageLocators.get("labsAndEquipmentSlider"), labsBenefit);
	}

	public void goToEmployeeDetails(){
		browse.threadSleepWait(500);
		browse.click(pageLocators.get("firstEmployee"));
		browse.click(pageLocators.get("showMeMyHealthPlans"));
		browse.waitForLoadingPlans(pageLocators.get("loadingSymbol"));
		browse.scrollUp();
	}
	
	public void enterBenefitValue(By by, String benefit){
		browse.type(by, benefit);
		browse.threadSleepWait(200);
	}
}
