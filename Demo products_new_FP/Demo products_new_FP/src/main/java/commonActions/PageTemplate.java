/**
 * 
 */
package commonActions;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.By;

/**
 * @author GS-0985
 *
 */
public class PageTemplate {
	
	protected BrowserActions browse;
	protected ObjectMap objMap;
	public HashMap<String, By> pageLocators;
	protected TestDataFunctions data;


	public PageTemplate(BrowserActions b, String locatorsPath ){ 
		browse = b;
		data = new TestDataFunctions();
		objMap = new ObjectMap(locatorsPath);
		pageLocators = new HashMap<String, By>();
		getPageLocators();
	}
	
	public void getPageLocators(){
	pageLocators = objMap.getLocator();
	}	
	
	
}
