package commonActions;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class BrowserActions{
	public WebDriver selenium;
	public BrowserActions browse;
	String alertText, actualTitle, currentUrl;
	String filePath = System.getProperty("user.dir")+"\\Screenshots\\";
	TestDataFunctions data = new TestDataFunctions();
	SoftAssert s_assert = new SoftAssert();
	public Properties config = data.getPropertiesFileData();

	
	/** This method opens a new browser instance depending upon browser name given.. 
	 * Maximizes the browser window and wait for mentioned implicit wait before executing next expected condition.
	 */
	public void openBrowserInstance(){
	//Properties config = data.getPropertiesFileData();
	String browser = config.getProperty("browser");
	Log.info("Launching  "+browser+" browser");
	if (browser.equalsIgnoreCase("firefox")) {
		System.setProperty("webdriver.gecko.driver", (System.getProperty("user.dir")+"\\drivers\\geckodriver.exe"));
		 selenium = new FirefoxDriver();
 } else if (browser.equalsIgnoreCase("chrome")) {
	    System.setProperty("webdriver.chrome.driver", (System.getProperty("user.dir")+"\\drivers\\chromedriver.exe"));
	    selenium = new ChromeDriver();
 } else if (browser.equalsIgnoreCase("ie")) {
	   System.setProperty("webdriver.ie.driver", (System.getProperty("user.dir")+"\\drivers\\IEDriverServer.exe"));
	    selenium = new InternetExplorerDriver();
} else if (browser.equalsIgnoreCase("edge")){
	   System.setProperty("webdriver.edge.driver", (System.getProperty("user.dir")+"\\drivers\\MicrosoftWebDriver.exe"));
	    selenium = new EdgeDriver();
}else {
	try {
        throw new IllegalArgumentException();
    } catch (Exception t){
        Log.exception("The Browser Type " +browser+ " is Undefined", t);
    }
 }
selenium.manage().window().maximize();
selenium.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
}

/** This method goes to the mentioned url. 
 * The url is taken from parameters in testng file for some cases, otherwise it is imported from excel sheet.
 * @param url
 */
public void goToUrl(String url){
	selenium.get(url);
	}	

/** This method closes the browser window that the driver has focus of
 */
	public void closeDriver(){
	try {
		Thread.sleep(1000);
		Log.info("Closing current browser window....");
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	selenium.close();
	}
	
/** Closes all windows associated to the test and ends the web driver session.
 */
public void quitDriver(){
	threadSleepWait(1000);
	Log.info("Ending test execution and closing browser....");
	selenium.quit();
	}

/** Refresh the web page
 */
public void refreshpage(){
	Log.info("Refreshing web page");
	selenium.navigate().refresh();
	threadSleepWait(5000);
}


/** This method gets the page title and returns it.
 * @return it returns page title
 */
public void verifyPageTitle(String expectedTitle){
	String actualTitle = selenium.getTitle();
	browse.threadSleepWait(5000);
	try{
	s_assert.assertEquals(actualTitle, expectedTitle);
	} catch(Exception e){
		Log.exception("The title "+actualTitle+" is not Expected Title", e);
	}
	}

/**This method switches to the located frame in a web page.  
 * @param by Takes the locator from page class
 */
public void frameSwitch(By by){
	selenium.switchTo().frame(selenium.findElement(By.tagName("iframe")));
	}

/** This method clicks on the located element. Before clicking, web driver waits for element to be present and to be clickable.
 * After that it waits for wait mentioned in thread sleep and clicks on element. 
 * @param by Takes the locator from page class
 */
public void click(By by){
	waitForPresenceOfElement(by);
	waitForElementToBeClickable(by);
	selenium.findElement(by).click();
	}

/** This method writes the text in located element. Before writing, it waits for that element to be present and clickable.
 * Then it clears that field and fill up with new value.
 * @param by takes locator from page class
 * @param text value to enter in text field
 */
public void type(By by, String text){
	waitForElementToBeVisible(by);
	waitForElementToBeClickable(by);
	selenium.findElement(by).clear();
	selenium.findElement(by).sendKeys(text);	
	}

/** This method waits for element to be present.    
 * @param by locator of element from page class
 */
public void waitForPresenceOfElement(By by){
	WebDriverWait myWait = (new WebDriverWait(selenium, 10));
	myWait.until(ExpectedConditions.presenceOfElementLocated(by));
	}

/** This method waits for element to be clickable. 
 * @param by locator of element from page class
 */
public void waitForElementToBeClickable(By by){
	WebDriverWait wait = new WebDriverWait(selenium, 5);
    wait.until(ExpectedConditions.elementToBeClickable(by));
	}

/**
 * @param by locator of element from page class
 */
public void jScriptClick(By by){
	JavascriptExecutor executor = (JavascriptExecutor)selenium;
	executor.executeScript("arguments[0].click();",selenium.findElement(by));
	}

/** This method causes web driver to wait for element to be visible till mentioned timeout
 * @param by locator of element from page class	
 */
public void waitForElementToBeVisible(By by){					// Not using currently
	WebDriverWait wait = new WebDriverWait(selenium, 15);
	wait.until(ExpectedConditions.visibilityOfElementLocated(by)); 
	}

/** This method causes web driver to wait for mentioned time period. If web driver can not find element in starting,
 *  it will wait for specified time duration without searching for element during wait period.
 *  Once wait is over, web driver will try to find an element for last time before throwing exception.
 */
public void implicitWait(){
	selenium.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

/** This method forces web driver to wait for mentioned time period. This wait should be used in worst case. 
 */
public void threadSleepWait(int timeInMiliSeconds){
	try {
		Thread.sleep(timeInMiliSeconds);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	}

/** This method hovers the cursor on the located element.
 * Here it is used for locating first and second plan on the home page.
 * @param by locator of element from page class
 */
public void mouseOver(By by){
	WebElement move = selenium.findElement(by);
	Actions action = new Actions(selenium);
	action.moveToElement(move).perform();
	threadSleepWait(1);
	}

public void webTableElements(By by1, By by2){
	WebElement htmlTable = selenium.findElement(by1);
	List <WebElement> rows = htmlTable.findElements(by2);
	for (int rownumber = 0; rownumber < rows.size(); rownumber++){
		System.out.println(rows.size());
	}
}

/** This method hovers the cursor on web element.
 * @param by locator of element from page class
 */
public void mouseHoverToElement(WebElement by){
	Actions action = new Actions(selenium);
	action.moveToElement(by).perform();
	threadSleepWait(1000);
	}
 
/** This method performs mouse click on the located element.
 * @param by locator of element from page class
 */
public void moveFocusAndClick(By by){
	WebElement move = selenium.findElement(by);
	Actions action = new Actions(selenium);
	action.moveToElement(move).click().perform();
	}

/** This method clicks on the keyboard's ESC Key.
 */
public void pressEscKey(){
	Actions action = new Actions(selenium);
	action.sendKeys(Keys.ESCAPE).build().perform();
}

/**
 * This method clicks on the keyboard's ENTER Key.
 */
public void pressEnterKey(){
	Actions action = new Actions(selenium);
	action.sendKeys(Keys.ENTER).build().perform();
}

/** This method takes the screen shot of the page and stores it in .jpg file in the project. 
 * The file is visible after refreshing the project.
 */
public void takeScreenshot(String methodName){
	 	File getImage = ((TakesScreenshot)selenium).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(getImage, new File(filePath+methodName+".png"));
			System.out.println("***Placed screen shot in "+filePath+" ***");
		} catch (IOException e) {
			e.printStackTrace();
		}
		}

/** This method waits for page to load till mentioned timeout.
 * Before waiting it verifies whether page is in complete state.
 * @param timeOutInSeconds seconds to wait for page to load
 */
public void waitForPageToLoad(int timeOutInSeconds){
		  JavascriptExecutor js = (JavascriptExecutor)selenium;
		  String command = "return document.readyState";
		  //Check the readyState before doing any waits
		  if (js.executeScript(command).toString().equals("complete")){
		   return;
		  }

		  for (int i=0; i<timeOutInSeconds; i++){
		   try {
		    Thread.sleep(1000);
		    if (js.executeScript(command).toString().equals("complete")){
		     break;
		    }
		   } catch (InterruptedException e) {}
		  }
		 }

/** This method navigates to back page by clicking browser back button
 */
public void browserBackButton(){
	selenium.navigate().back();
	threadSleepWait(1000);
}

/** This method verifies whether element is displayed or not and returns true or false
 * @param by locator of element from page class
 * @return True or False
 */
public boolean isElementDisplayed(By by){
	  return selenium.findElement(by).isDisplayed();
	 }

/** This method verifies whether web element is displayed or not. It returns true / false
 * @param w  It takes web element as parameter
 * @return true if web element is displayed
 */
public boolean isWebElementDisplayed(WebElement w){
	  return w.isDisplayed();
	 }

/** This method verifies whether element is present or not and returns true or false
 * @param by locator of element from page class
 * @return True or False
 */
public boolean isElementPresent(By by){
	  return selenium.findElements(by).size() != 0;
	 }
						
public void assertTextBoxValue(By by){
	WebElement myInput = selenium.findElement(by);
	/*if (!myInput.getAttribute("value").equals("")) {
	    Log.error("The enrollment year is empty");
	}*/
	Log.info(myInput.getAttribute("value"));
	s_assert.assertNotNull(myInput);
	s_assert.assertEquals(myInput.getAttribute("value"), "2018");
}

public void assertValue(By by, String value){
	WebElement myInput = selenium.findElement(by);
	Log.info("The value of "+by+" is " +myInput.getAttribute("value"));
	s_assert.assertNotNull(myInput);
	s_assert.assertEquals(myInput.getAttribute("value"), value);	
}

public String getText(By by) {
	waitForPresenceOfElement(by);
	Log.info("Getting text of " +by+ " element");
	 String text = selenium.findElement(by).getText();
	 Log.info("The text of element is '"+text+"'");
	 return text;
	}

/** This method finds the web elements present by given locator.
 * 
 * @param by locator of element from page class
 * @return list of elements present by given locator
 */
public List<WebElement> getListOfElements(By by){
	List<WebElement> myElement = selenium.findElements(by);
	System.out.println(myElement);
	return myElement;
	}																													

/** This method locates subsequent elements of a parent class
 * @param by1 Parent class web element
 * @param by2 Child class web element
 * @return Subsequent element's list of parent class elements
 */
public List<WebElement> getElementList(WebElement by1, By by2){
	List<WebElement> myElements = by1.findElements(by2);
	System.out.println(myElements);
	return myElements;
}

/** This method waits for element to become invisible on UI. 
 * Currently it is used for Loading symbol.
 * @param by locator of element from page class
 * @param seconds wait time in seconds
 * @return
 */

public boolean waitForElementToBeInvisible(By by, int seconds){
	    WebDriverWait wait = new WebDriverWait(selenium, seconds);
	    Boolean element = wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	    return element;
	}


/** This method handles the loading symbol state. If it is displayed in starting, it will wait for it to be invisible for mentioned timeout.
 * If not then it will wait for 2 seconds and go to next step.
 * @param by loading symbol
 * @param Seconds timeout to wait 
 */

public void loadingSymbolState(By by, int Seconds){
	if(isElementDisplayed(by)){
		waitForElementToBeInvisible(by, Seconds);
	}
	else{
	threadSleepWait(3000);
	}
}

public void assertObject(By by){
	threadSleepWait(200);
	implicitWait();
	s_assert.assertEquals(isElementDisplayed(by), true);
}

/** This method checks for new tab opened after clicking on web element. Asserts the page title and closes the new tab.
 * @param expected the title to be verified
 */
public void checkNewTabTitle(String title){
	List<String> browserTabs = new ArrayList<String> (selenium.getWindowHandles());
	selenium.switchTo().window(browserTabs .get(1));
	System.out.println(title);
	verifyPageTitle(title);
    threadSleepWait(5000);
	closeDriver();
	selenium.switchTo().window(browserTabs.get(0));
	threadSleepWait(5000);
}

/** This method checks for new tab opened after clicking on web element. Asserts the page title and closes the new tab.
 * @param expected the title to be verified
 */
public void checkNewTab(){

	List<String> browserTabs = new ArrayList<String> (selenium.getWindowHandles());
	selenium.switchTo().window(browserTabs .get(1));
    threadSleepWait(8000);
    closeDriver();
   	selenium.switchTo().window(browserTabs.get(0));
   	threadSleepWait(2000);
}

/** This method checks for new tab opened after clicking on web element.  And returns the list of browser tabs.
 * @param expected the title to be verified
 */
public List<String> checkNewTab1(){

	List<String> browserTabs = new ArrayList<String> (selenium.getWindowHandles());
	selenium.switchTo().window(browserTabs .get(1));
    threadSleepWait(50000);
    return browserTabs; 
} 
    
public void closeTab(List<String> browserTabs){
    closeDriver();
	selenium.switchTo().window(browserTabs.get(0));
	threadSleepWait(2000);
}

public void checkLtcTab(By by){

	List<String> browserTabs = new ArrayList<String> (selenium.getWindowHandles());
	selenium.switchTo().window(browserTabs .get(1));
    threadSleepWait(5000);
    assertObject(by);
	closeDriver();
	selenium.switchTo().window(browserTabs.get(0));
	threadSleepWait(500);
}

public void ReadPDF() 
{

	try {
	URL TestURL = new URL("http://96.88.82.251:8080/plan_recommend/d6a840a0-925a-41b7-b3f1-59a20d188b53-9928/DZEEWHITEPAPER.pdf");
	// For converting content inside PDF to text & using PDFBox API with java input stream for parsing
	BufferedInputStream TestFile = new BufferedInputStream(TestURL.openStream());
	PDFParser TestPDF = new PDFParser((RandomAccessRead) TestFile);
		TestPDF.parse();
	String TestText = new PDFTextStripper().getText(TestPDF.getPDDocument());
	// Use TestNG assertion for checking if your text is present in content
	Assert.assertTrue(TestText.contains("A New Frontier for Financial Advice")); 
	} catch (InvalidPasswordException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

public void waitForLoadingPlans(By by){
	if(isElementPresent(by)){
		waitForElementToBeInvisible(by, 10);
	} else{
		threadSleepWait(2000);
	}
}

/** This method selects drop down option by matching with text. 
 * @param by Locates the drop down box
 * @param option Passes text to select from drop down
 */
public void selectDropDownMenu(By by, String option){
			 WebElement element=selenium.findElement(by);
		    	Select se= new Select(element);
				se.selectByVisibleText(option);
				implicitWait();
	}

public void selectJcfDropdownValue(By by, String option){
	click(by);
	By applicationValue = By.xpath("//span[contains(text(),'"+option+"')]");
	click(applicationValue);
}

public void assertSelectedRadioButton(By by){
	selenium.findElement(by).isSelected();
}

public void scrollUp(){
	JavascriptExecutor jse = (JavascriptExecutor)selenium;
	Log.info("Scrolling up page");
	jse.executeScript("scroll(0, -450);");
}

public void assertText(By by, String str){
	try{
	s_assert.assertEquals(getText(by), str);
	}catch(Exception e){
		Log.exception("The "+getText(by)+" is not equal to expected text", e);
	}
}

public void assertDownloadedFile(){
	s_assert.assertTrue(getReportFileStatus(), "Failed to download Expected document");
}


public boolean getReportFileStatus(){
	String folderName = System.getProperty("user.dir")+"\\Downloads"; // Give your folderName
	File[] listFiles = new File(folderName).listFiles();

	for (int i = 0; i < listFiles.length; i++) {
	    if (listFiles[i].isFile()) {
	        String fileName = listFiles[i].getName();
	        if (fileName.startsWith("individualExpenseReport")
	                && fileName.endsWith(".csv")) {
	            System.out.println("found file" + " " + fileName);
	            System.out.println("File Size is: " + fileName.length());
	            s_assert.assertNotNull(fileName.length(), "The file size assertion is passed");
	        }
	    }
	}
	return true;
}

public void jScriptClick(WebElement findElement) {
	// TODO Auto-generated method stub
	
}
}
