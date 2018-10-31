package pages;
import commonActions.BrowserActions;
import commonActions.PageTemplate;

public class MyProfilePage extends PageTemplate {
	static String pageLocatorsPath = (System.getProperty("user.dir")+"\\src\\main\\java\\objectRepository\\MyProfilePage.properties");
	String invalidRetirementAge;
	
	public MyProfilePage(BrowserActions b){
		super(b, pageLocatorsPath);
	}

	public void goToMyProfilePage(){
		browse.click(pageLocators.get("addEmployee"));
	}
	
	/**
	 * This method asserts error messages for all empty fields
	 */
	public void emptyMyProfile(){
		    goToMyProfilePage();
			browse.click(pageLocators.get("showMeMyHealthPlans"));
		    testProfileErrorMessages();
		    testMemberErrorMessages();
		    browse.refreshpage();
		}
	
	/**
	 * This method directs user to Home Page
	 */
	public void goToHomePage(){
		goToMyProfilePage();
		browse.threadSleepWait(1000);
		browse.click(pageLocators.get("homeIcon"));
	}
		 
	public void saveEmployee(){
		goToMyProfilePage();
		browse.click(pageLocators.get("saveEmployee"));
		browse.assertObject(pageLocators.get("homePageHeader"));
		browse.click(pageLocators.get("addEmployeeFromHome"));
		browse.click(pageLocators.get("adventurousLifestyleYes"));
		browse.click(pageLocators.get("previousButton"));
		browse.assertObject(pageLocators.get("saveChangesPopup"));
		browse.click(pageLocators.get("saveChangesYesButton"));
		browse.assertObject(pageLocators.get("employeeIdError"));
		browse.threadSleepWait(500);
		browse.click(pageLocators.get("previousButton"));
		browse.click(pageLocators.get("saveChangesNoButton"));
		browse.assertObject(pageLocators.get("homePageHeader"));
		browse.click(pageLocators.get("addEmployeeFromHome"));
	}
	
	public void noPrivatePlan(String scenario, String id, String year, String children,   String income, String currentZip, 
			String number, String email,  String primary, String dob, String health){
		fillMyProfile(id, children, income, currentZip, email, primary, dob, health);
		browse.click(pageLocators.get("showMeMyHealthPlans"));
		browse.assertObject(pageLocators.get("medicareEligibilityPopup"));
		browse.click(pageLocators.get("cancelButton"));
		browse.assertObject(pageLocators.get("myProfileHeader"));
		browse.threadSleepWait(500);
		browse.click(pageLocators.get("showMeMyHealthPlans"));
		browse.assertObject(pageLocators.get("medicareEligibilityPopup"));
		browse.click(pageLocators.get("noPrivatePlan"));
		browse.click(pageLocators.get("selectButton"));
		browse.assertObject(pageLocators.get("optedOutMessage"));
	}

	public void optForPrivatePlan(String scenario, String id, String year, String children,   String income, String currentZip, 
			String number, String email,  String primary, String dob, String health){
		//goToMyProfilePage();
		//fillMyProfile(id, children, income, currentZip, email, primary, dob, health);
		browse.click(pageLocators.get("showMeMyHealthPlans"));
		browse.assertObject(pageLocators.get("medicareEligibilityPopup"));
		browse.click(pageLocators.get("yesPrivatePlan"));
		browse.click(pageLocators.get("selectButton"));
		browse.threadSleepWait(1000);
		browse.assertObject(pageLocators.get("recommendedPlansHeader"));
	}
	
	public void invalidRetirementAgeOnMedicarePopup(){
		browse.click(pageLocators.get("myProfileTab"));
		browse.click(pageLocators.get("showMeMyHealthPlans"));
		browse.assertObject(pageLocators.get("medicareEligibilityPopup"));
		browse.type(pageLocators.get("retirementAge"), invalidRetirementAge);
		browse.click(pageLocators.get("selectButton"));
		browse.assertObject(pageLocators.get("lessRetirementAgeError"));
		browse.threadSleepWait(500);
		browse.type(pageLocators.get("retirementAge"), "116");
		browse.assertObject(pageLocators.get("greaterRetirementAgeError"));
	}
	
	/**
	 * This method asserts error messages for the fields except for employee ID
	 * @param scenario
	 * @param id valid employee ID
	 * @param year - empty
	 * @param children - empty
	 * @param income - empty
	 * @param currentZip - empty
	 * @param number - empty
	 * @param email - empty
	 * @param primary - empty
	 * @param dob - empty
	 * @param health - empty
	 */
	public void onlyEmployeeId(String scenario, String id, String year, String children,   String income, String currentZip, 
				String number, String email,  String primary, String dob, String health){
			browse.type(pageLocators.get("employeeId"), id);
			browse.click(pageLocators.get("showMeMyHealthPlans"));
			browse.assertObject(pageLocators.get("annualIncomeError"));
		    browse.assertObject(pageLocators.get("zipCodeError"));
		    browse.assertObject(pageLocators.get("emailError"));
		    testMemberErrorMessages();
		    browse.refreshpage();
		}
	
	/**
	 * This method asserts error messages for the fields except for Annual Income
	 * @param scenario
	 * @param id
	 * @param year
	 * @param children
	 * @param income
	 * @param currentZip
	 * @param number
	 * @param email
	 * @param primary
	 * @param dob
	 * @param health
	 */
		public void onlyAnnualIncome(String scenario, String id, String year, String children,   String income, String currentZip, 
				String number, String email,  String primary, String dob, String health){
			browse.selectJcfDropdownValue(pageLocators.get("annualDropDown"), income);
			browse.click(pageLocators.get("showMeMyHealthPlans"));
			browse.assertObject(pageLocators.get("employeeIdError"));
			browse.assertObject(pageLocators.get("zipCodeError"));
		    browse.assertObject(pageLocators.get("emailError"));
			testMemberErrorMessages();
			browse.refreshpage();
    		}
			 
		/**
		 * This method asserts error messages for the fields except for Zip code
		 * @param scenario
		 * @param id
		 * @param year
		 * @param children
		 * @param income
		 * @param currentZip
		 * @param number
		 * @param email
		 * @param primary
		 * @param dob
		 * @param health
		 */
		public void onlyZipCode(String scenario, String id, String year, String children,   String income, String currentZip, 
				String number, String email,  String primary, String dob, String health){
			browse.type(pageLocators.get("zipCode"), currentZip);
			browse.click(pageLocators.get("showMeMyHealthPlans"));
			browse.assertObject(pageLocators.get("employeeIdError"));
		    browse.assertObject(pageLocators.get("annualIncomeError"));
		    browse.assertObject(pageLocators.get("emailError"));
			testMemberErrorMessages();
			browse.refreshpage();
		    }
		    
		/**
		 * This method asserts error messages for the fields except for Email ID
		 * @param scenario
		 * @param id
		 * @param year
		 * @param children
		 * @param income
		 * @param currentZip
		 * @param number
		 * @param email
		 * @param primary
		 * @param dob
		 * @param health
		 */
		public void onlyEmailId(String scenario, String id, String year, String children,   String income, String currentZip, 
				String number, String email,  String primary, String dob, String health){
			browse.type(pageLocators.get("emailId"), email);
			browse.click(pageLocators.get("showMeMyHealthPlans"));
			browse.assertObject(pageLocators.get("employeeIdError"));
		    browse.assertObject(pageLocators.get("annualIncomeError"));
		    browse.assertObject(pageLocators.get("zipCodeError"));
		    testMemberErrorMessages();
		    browse.refreshpage();
		}
		    
		/**
		 * This method asserts error messages for the fields except for primary name
		 * @param scenario
		 * @param id
		 * @param year
		 * @param children
		 * @param income
		 * @param currentZip
		 * @param number
		 * @param email
		 * @param primary
		 * @param dob
		 * @param health
		 */
		public void onlyPrimaryName(String scenario, String id, String year, String children,   String income, String currentZip, 
				String number, String email,  String primary, String dob, String health){
			browse.type(pageLocators.get("primaryName"), primary);
			browse.click(pageLocators.get("showMeMyHealthPlans"));
			testProfileErrorMessages();
		    browse.assertObject(pageLocators.get("dobError"));
			browse.assertObject(pageLocators.get("healthProfileError"));
			browse.refreshpage();
		    }
		    
		/**
		 * This method asserts error messages for the fields except for DOB
		 * @param scenario
		 * @param id
		 * @param year
		 * @param children
		 * @param income
		 * @param currentZip
		 * @param number
		 * @param email
		 * @param primary
		 * @param dob
		 * @param health
		 */
		public void onlyDOB(String scenario, String id, String year, String children,   String income, String currentZip, 
				String number, String email,  String primary, String dob, String health){
			browse.type(pageLocators.get("primaryDOB"), dob);
			browse.click(pageLocators.get("showMeMyHealthPlans"));
			testProfileErrorMessages();
		    browse.assertObject(pageLocators.get("nameError"));
		    browse.assertObject(pageLocators.get("healthProfileError"));
		    browse.refreshpage();
		    }
		    
		/**
		 * This method asserts error messages for the fields except for health profile
		 * @param scenario
		 * @param id
		 * @param year
		 * @param children
		 * @param income
		 * @param currentZip
		 * @param number
		 * @param email
		 * @param primary
		 * @param dob
		 * @param health
		 */
		public void onlyHealthProfile(String scenario, String id, String year, String children,   String income, String currentZip, 
				String number, String email,  String primary, String dob, String health){
			browse.selectJcfDropdownValue(pageLocators.get("healthProfile"), health);
			browse.click(pageLocators.get("showMeMyHealthPlans"));
			testProfileErrorMessages();
		    browse.assertObject(pageLocators.get("nameError"));
		    browse.assertObject(pageLocators.get("dobError"));    
		    browse.refreshpage();
		    }
		    
		/**
		 * This method asserts error message for short (< 4 digit) enrollment year
		 * @param scenario
		 * @param id
		 * @param year
		 * @param children
		 * @param income
		 * @param currentZip
		 * @param number
		 * @param email
		 * @param primary
		 * @param dob
		 * @param health
		 */
		public void shortEnrollmentYear(String scenario, String id, String year, String children,   String income, String currentZip, 
				String number, String email,  String primary, String dob, String health){
			browse.type(pageLocators.get("enrollmentYear"), year);
			browse.click(pageLocators.get("showMeMyHealthPlans"));
			browse.assertObject(pageLocators.get("shortEnrollmentYear"));
			browse.refreshpage();
			}
		
		/**
		 * This method asserts error message for invalid zip code  entry
		 * @param scenario
		 * @param id
		 * @param year
		 * @param children
		 * @param income
		 * @param currentZip
		 * @param number
		 * @param email
		 * @param primary
		 * @param dob
		 * @param health
		 */
		public void invalidZipCode(String scenario, String id, String year, String children,   String income, String currentZip, 
				String number, String email,  String primary, String dob, String health){
			browse.type(pageLocators.get("zipCode"), currentZip);
			browse.click(pageLocators.get("showMeMyHealthPlans"));
			browse.assertObject(pageLocators.get("invalidZipCode"));
			browse.refreshpage();
			}
			
		/**
		 * This method asserts error message for invalid DOB 
		 * @param scenario
		 * @param id
		 * @param year
		 * @param children
		 * @param income
		 * @param currentZip
		 * @param number
		 * @param email
		 * @param primary
		 * @param dob
		 * @param health
		 */
		public void invalidDOB(String scenario, String id, String year, String children,   String income, String currentZip, 
				String number, String email,  String primary, String dob, String health){
			browse.type(pageLocators.get("primaryDOB"), dob);
			browse.click(pageLocators.get("showMeMyHealthPlans"));
		    browse.assertObject(pageLocators.get("dobError"));
		    browse.refreshpage();
		    }
		    
		/**
		 * This method asserts error message for invalid email ID
		 * @param scenario
		 * @param id
		 * @param year
		 * @param children
		 * @param income
		 * @param currentZip
		 * @param number
		 * @param email
		 * @param primary
		 * @param dob
		 * @param health
		 */
		public void invalidEmail(String scenario, String id, String year, String children,   String income, String currentZip, 
				String number, String email,  String primary, String dob, String health){
			browse.type(pageLocators.get("emailId"), email);
			browse.click(pageLocators.get("showMeMyHealthPlans"));
			browse.assertObject(pageLocators.get("invalidEmailError"));
			browse.refreshpage();
			}
			
		/**
		 * This method asserts error message for invalid phone number
		 * @param scenario
		 * @param id
		 * @param year
		 * @param children
		 * @param income
		 * @param currentZip
		 * @param number
		 * @param email
		 * @param primary
		 * @param dob
		 * @param health
		 */
		public void invalidPhoneNumber(String scenario, String id, String year, String children,   String income, String currentZip, 
				String number, String email,  String primary, String dob, String health){
			browse.type(pageLocators.get("phoneNumber"), number);
			browse.click(pageLocators.get("showMeMyHealthPlans"));
			browse.assertObject(pageLocators.get("invalidPhoneNumberError"));
			browse.refreshpage();
		}
			
		/**
		 * This method asserts error message for invalid primary age
		 * @param scenario
		 * @param id
		 * @param year
		 * @param children
		 * @param income
		 * @param currentZip
		 * @param number
		 * @param email
		 * @param primary
		 * @param dob
		 * @param health
		 */
		public void invalidPrimaryAge(String scenario, String id, String year, String children,   String income, String currentZip, 
				String number, String email,  String primary, String dob, String health){
		   fillMyProfile(id, children,   income, currentZip, email, primary, dob, health);
			browse.click(pageLocators.get("showMeMyHealthPlans"));
			browse.assertObject(pageLocators.get("ageErrorMessage"));
			browse.refreshpage();
		}
			
		/**
		 * This method asserts pop up of adventure activities help text
		 */
		public void adventureActivitiesHelp(){
			browse.click(pageLocators.get("adventureHelpText"));
			browse.assertObject(pageLocators.get("adventurehelpPopup"));
			browse.pressEscKey();
			browse.refreshpage();
		}
		
		/**
		 * 
		 * @param scenario
		 * @param id
		 * @param year
		 * @param children
		 * @param income
		 * @param currentZip
		 * @param number
		 * @param email
		 * @param primary
		 * @param dob
		 * @param health
		 */
		public void saveEmployee(String scenario, String id, String year, String children,   String income, String currentZip, 
				String number, String email,  String primary, String dob, String health){
			fillMyProfile(id, children,  income, currentZip, email, primary, dob, health);
			browse.click(pageLocators.get("saveEmployee"));
			browse.waitForLoadingPlans(pageLocators.get("loadingSymbol"));
			browse.assertObject(pageLocators.get("recommendedPlansHeader"));
		}

		/**
		 * This method asserts error messages for profile fields of employee
		 */
	public void testProfileErrorMessages() {
		browse.assertObject(pageLocators.get("employeeIdError"));
		browse.assertObject(pageLocators.get("annualIncomeError"));
		browse.assertObject(pageLocators.get("zipCodeError"));
		browse.assertObject(pageLocators.get("emailError"));
	}

	/**
	 * This method asserts error messages for demographic details of members
	 */
	public void testMemberErrorMessages() {
		browse.assertObject(pageLocators.get("nameError"));
		browse.assertObject(pageLocators.get("dobError"));
		browse.assertObject(pageLocators.get("healthProfileError"));
	}
	
	/** 
	 * This method fills profile data of the user
	 * @param id
	 * @param children
	 * @param income
	 * @param currentZip
	 * @param email
	 * @param primary
	 * @param dob
	 * @param health
	 */
	public void fillMyProfile(String id, String children,   String income, String currentZip,
			String email, String primary, String dob, String health) {
		browse.type(pageLocators.get("employeeId"), id);
		browse.type(pageLocators.get("numberOfChildren"), children);
		browse.selectJcfDropdownValue(pageLocators.get("annualDropDown"), income);
		browse.type(pageLocators.get("zipCode"), currentZip);
		browse.type(pageLocators.get("emailId"), email);
		browse.type(pageLocators.get("primaryName"), primary);
		browse.type(pageLocators.get("primaryDOB"), dob);
		browse.selectJcfDropdownValue(pageLocators.get("healthProfile"), health);
	}
	
	public String getRetirementAge(){
		int currentRetirementAge = Integer.parseInt(browse.getText(pageLocators.get("retirementAge")));
		int invalidAge = currentRetirementAge - 1;
		return invalidRetirementAge = Integer.toString(invalidAge);
	}
}
