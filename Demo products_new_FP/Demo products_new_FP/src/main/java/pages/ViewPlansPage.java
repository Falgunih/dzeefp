/**
 * 
 */
package pages;

import java.util.Properties;

import commonActions.BrowserActions;
import commonActions.Log;
import commonActions.PageTemplate;


/**
 * @author GS-0985
 *
 */
public class ViewPlansPage extends PageTemplate {
	static String pageLocatorsPath = System.getProperty("user.dir")+"\\src\\main\\java\\objectRepository\\ViewPlansPage.properties";
	Properties config = data.getPropertiesFileData();
	
	
public ViewPlansPage(BrowserActions b){
	super(b, pageLocatorsPath);
}

/*public void goToEnvironment(){
	String environment = config.getProperty("environment");
	Log.info("Conducting Sanity Test on "+ environment);
	switch(environment){
	
	case "dzeeSiteDemoUrl":
		browse.goToUrl(config.getProperty("dzeeSiteDemoUrl"));
		  browse.threadSleepWait(5000);
		  browse.jScriptClick(pageLocators.get("loginMenu"));
		  browse.jScriptClick(pageLocators.get("PRSite"));
		  browse.threadSleepWait(1000);
	break;
	
	case "planRecommendDemoUrl":
		browse.goToUrl(config.getProperty("planRecommendDemoUrl"));
		  browse.threadSleepWait(1000);
	break;
	
	case "planRecommendQaUrl":
		 browse.goToUrl(config.getProperty("planRecommendQaUrl"));
		 browse.threadSleepWait(1000);
	break;

	case "planRecommendDevUrl":
		browse.goToUrl(config.getProperty("planRecommendDevUrl"));
		  browse.threadSleepWait(1000);
	break;

    default:
    	Log.fatal("The  " + environment+ " is not valid environment");
	     }
	}

public void brokerLogin(){
	browse.type(pageLocators.get("email"), config.getProperty("broker"));
	browse.type(pageLocators.get("password"), config.getProperty("password"));
	browse.click(pageLocators.get("loginButton"));
	browse.threadSleepWait(2000);
	browse.assertObject(pageLocators.get("planListingHeader"));
	browse.threadSleepWait(4000);
}
*/
	public void enrollmentYearValue(){
		browse.loadingSymbolState(pageLocators.get("loadingSymbol"), 10);
		browse.assertObject(pageLocators.get("planListingHeader"));
		browse.assertTextBoxValue(pageLocators.get("enrollmentYear"));
	}
	
	public void assertEmployerGroupOptions(){
		browse.click(pageLocators.get("employerGroup"));
		browse.assertObject(pageLocators.get("employerGroupList"));
	}
	
	public void addEmployeeButtonTest(){
		browse.click(pageLocators.get("addEmployeeButton"));
		browse.threadSleepWait(1000);
		browse.assertObject(pageLocators.get("myProfileHeader"));
		browse.jScriptClick(pageLocators.get("myProfileHomeButton"));
		browse.threadSleepWait(4000);
	}
	
	public void employeeListingButton(){
		browse.click(pageLocators.get("employeeListButton"));
		browse.assertObject(pageLocators.get("employeeListHeader"));
		browse.click(pageLocators.get("planAdministrationButton"));
		browse.threadSleepWait(2000);
	}
	
	public void checkHealthPlanSelection(){
		browse.assertSelectedRadioButton(pageLocators.get("healthcarePlanButton"));
		browse.threadSleepWait(500);
	}
	
	public void premiumDeductibleLink(){
		browse.click(pageLocators.get("premiumDeductibleLink"));
		browse.assertObject(pageLocators.get("premiumDeductiblePopup"));
		browse.pressEscKey();
		browse.threadSleepWait(2000);
	}
	
	public void selectSupplementPlans(){
		browse.click(pageLocators.get("supplementPlanButton"));
	}
	
}
