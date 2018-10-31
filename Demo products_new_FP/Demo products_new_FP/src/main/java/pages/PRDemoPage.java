/**
 * @author GS-0985
 *
 */
package pages;
import java.util.Properties;
import org.openqa.selenium.By;
import commonActions.BrowserActions;
import commonActions.Log;
import commonActions.TestDataFunctions;

public class PRDemoPage{

	TestDataFunctions data = new TestDataFunctions();
	BrowserActions browse;
	Properties config = data.getPropertiesFileData();
		
	public PRDemoPage(BrowserActions b){
				browse = b;
			}
		
		public void goToEnvironment(){
			String environment = config.getProperty("environment");
			Log.info("Conducting Sanity Test on "+ environment);
			switch(environment){
			
			case "dzeeSiteDemoUrl":
				browse.goToUrl(config.getProperty("dzeeSiteDemoUrl"));
				browse.threadSleepWait(5000);
				browse.jScriptClick(By.xpath("//*[@id='menu-item-3854']/a"));
				browse.jScriptClick(By.xpath("//*[@id='menu-item-3915']/a"));
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
			browse.type(By.id("loginEmail"), config.getProperty("broker"));
			browse.type(By.id("loginPassword"), config.getProperty("password"));
			browse.click(By.id("loginButton"));
			browse.threadSleepWait(4000);
			}
			
	/*	public void logout(){
			browse.threadSleepWait(2000);
			browse.click(prLocators.get("logout"));
		}*/
		

	// Employer group management tab scenarios
	/*	
		public void employerListing(){
			//browse.click(prLocators.get("homeIcon"));
			browse.threadSleepWait(2000);
			browse.click(prLocators.get("empGrpManagementTab"));
			browse.assertObject(prLocators.get("totalEmployerGroups"));
		}
		
		public void viewEmployerGroupDetails(){
			browse.click(prLocators.get("viewFirstEmpGrp"));
			browse.assertObject(prLocators.get("viewEmployerHeader"));
			browse.click(prLocators.get("viewFirstEmpGrp"));
		}
		
		public void editEmployerGroupDetails(){
			browse.click(prLocators.get("editFirstEmpGrp"));
			browse.assertObject(prLocators.get("editEmployerHeader"));
			browse.click(prLocators.get("editFirstEmpGrp"));
		}
		
		public void deleteEmployer(){
			browse.click(prLocators.get("deleteFirstEmpGrp"));
			browse.assertObject(prLocators.get("deleteEmployerPopup"));
			browse.pressEscKey();
		}
		
		public void addEmployerPage(){
			browse.click(prLocators.get("addEmployer"));
			browse.assertObject(prLocators.get("organizationFullNameLabel"));
			browse.assertObject(prLocators.get("employerContributionLabel"));
		}
			
		public void editEnrollmentYearDetails(){
			browse.click(prLocators.get("editEnrollmentYear"));
			browse.assertObject(prLocators.get("availableYearsLabel"));
			browse.selectJcfDropdownValue(prLocators.get("employerGroupDropDown"), "Jan_demo");
			browse.threadSleepWait(2000);
			browse.selectJcfDropdownValue(prLocators.get("enrollmentYearDropDown"), "2018");
			browse.click(prLocators.get("submitDetails"));
			browse.assertObject(prLocators.get("organizationFullNameLabel"));
			browse.assertObject(prLocators.get("employerContributionLabel"));		
		}
		
		public void editEnrollmentYearTab(){
			browse.click(prLocators.get("editEnrollmentYear"));
			
		}
		public void addEmployerInfo(String orgName, String assignedName, String password, String contactPerson, String number, String employerwebsite,
								String employerEmail, String address1, String address2, String city, String state, String zipCode){
			browse.click(prLocators.get("employerGroupManagementTab"));
			browse.click(prLocators.get("addEmployer"));
			browse.type(prLocators.get("employerOrganization"), orgName);
			browse.click(prLocators.get("selfFundedYes"));
			browse.type(prLocators.get("assignedName"), assignedName);
			browse.type(prLocators.get("employerPassword"), password);
			browse.type(prLocators.get("contactPerson"), contactPerson);
			browse.type(prLocators.get("contactNumber"), number);
			browse.type(prLocators.get("website"), employerwebsite);
			browse.type(prLocators.get("employerEmail"), employerEmail);
			browse.type(prLocators.get("addressOne"), address1);
			browse.type(prLocators.get("addressOne"), address2);
			browse.type(prLocators.get("city"), city);
			browse.type(prLocators.get("state"), state);
			browse.type(prLocators.get("zipCode"), zipCode);
		}
		
		public void addEmployerContribution(String singlePremium, String familyPremium, String singleFsa, String familyFsa, String singleHsa, String familyHsa){
			browse.type(prLocators.get("singleMonthlyPremium"), singlePremium);
			browse.type(prLocators.get("familyMonthlyPremium"), familyPremium);
			browse.type(prLocators.get("singleAnnualFsa"), singleFsa);
			browse.type(prLocators.get("familyAnnualFsa"), familyFsa);
			browse.type(prLocators.get("singleAnnualHsa"), singleHsa);
			browse.type(prLocators.get("familyAnnualHsa"), familyHsa);
			browse.click(prLocators.get("saveEmployer"));
		}
		
		public void assignPlansToEmployer(String assignedName){
			browse.click(prLocators.get("assignPlansTab"));
			browse.selectJcfDropdownValue(prLocators.get("addPlanToDropdown"), assignedName);
		}
		
		public void addEmployer(String orgName, String assignedName, String password, String contactPerson, String number, String employerwebsite,
				String employerEmail, String address1, String address2, String city, String state, String zipCode, String singlePremium, String familyPremium, String singleFsa, String familyFsa, String singleHsa, String familyHsa ){
			addEmployerInfo(orgName, assignedName, password, contactPerson, number, employerwebsite,
					employerEmail, address1, address2, city, state, zipCode);
			addEmployerContribution(singlePremium, familyPremium, singleFsa, familyFsa, singleHsa, familyHsa);
		}*/
	}

