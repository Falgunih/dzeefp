/**
 * 
 */
package dataproviders;

import org.testng.annotations.DataProvider;

import commonActions.TestDataFunctions;

/**
 * @author GS-0985
 *
 */
public class MyProfileDataProviders {

	public static String sheetName;
	static TestDataFunctions data = new TestDataFunctions();
	static String testDataFile = System.getProperty("user.dir")+"\\src\\test\\resources\\test-input.xlsx";
	
	@DataProvider(name="onlyEmployeeId")
	public static Object[][] onlyEmployeeId(){
		return data.readRow(testDataFile, sheetName, "onlyEmployeeId");
  }	

	@DataProvider(name="onlyAnnualIncome")
	public static Object[][] onlyAnnualIncome(){
		return data.readRow(testDataFile, sheetName, "onlyAnnualIncome");
  }	
	
	@DataProvider(name="onlyZipCode")
	public static Object[][] onlyZipCode(){
		return data.readRow(testDataFile, sheetName, "onlyZipCode");
  }	
	
	@DataProvider(name="onlyEmailId")
	public static Object[][] onlyEmailId(){
		return data.readRow(testDataFile, sheetName, "onlyEmailId");
  }	
	
	@DataProvider(name="onlyPrimaryName")
	public static Object[][] onlyPrimaryName(){
		return data.readRow(testDataFile, sheetName, "onlyPrimaryName");
  }	
	
	@DataProvider(name="onlyDOB")
	public static Object[][] onlyDOB(){
		return data.readRow(testDataFile, sheetName, "onlyDOB");
  }	
	
	@DataProvider(name="onlyHealthProfile")
	public static Object[][] onlyHealthProfile(){
		return data.readRow(testDataFile, sheetName, "onlyHealthProfile");
  }	

	@DataProvider(name="invalidZipCode")
	public static Object[][] invalidZipCode(){
		return data.readRow(testDataFile, sheetName, "invalidZipCode");
  }	
	
	@DataProvider(name="invalidPhoneNumber")
	public static Object[][] invalidPhoneNumber(){
		return data.readRow(testDataFile, sheetName, "invalidPhoneNumber");
  }	
	
	@DataProvider(name="invalidEmail")
	public static Object[][] invalidEmail(){
		return data.readRow(testDataFile, sheetName, "invalidEmail");
  }	
	
	@DataProvider(name="invalidDOB")
	public static Object[][] invalidDOB(){
		return data.readRow(testDataFile, sheetName, "invalidDOB");
  }	
	
	@DataProvider(name="invalidPrimaryAge")
	public static Object[][] invalidPrimaryAge(){
		return data.readRow(testDataFile, sheetName, "invalidPrimaryAge");
  }	
	
	@DataProvider(name="shortEnrollmentYear")
	public static Object[][] shortEnrollmentYear(){
		return data.readRow(testDataFile, sheetName, "shortEnrollmentYear");
  }	
	
	@DataProvider(name="addEmployee")
	public static Object[][] addEmployee(){
		return data.readRow(testDataFile, sheetName, "addEmployee");
  }	
}
