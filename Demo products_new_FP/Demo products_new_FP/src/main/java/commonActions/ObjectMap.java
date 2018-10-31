package commonActions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import org.openqa.selenium.By;

public class ObjectMap {
	Properties prop;
	public static By locator;
	public static By locatorObject;
	HashMap<String, By> map = new HashMap<String, By>();

	public ObjectMap (String filePath) {
	prop = new Properties();
	try {
		FileInputStream fis = new FileInputStream(filePath);
		prop.load(fis);
		fis.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	getLocator();
	}	

	public HashMap<String, By> getLocator(){
		Enumeration keys = prop.keys();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			String locate = prop.getProperty(key);
			String locateBy = locate.split(":")[0].toLowerCase();
			String locatorValue = locate.split(":")[1];
        
        switch (locateBy){
        case "id":
        	locator  = By.id(locatorValue);
        	break;
        	
        case "name":
        	locator = By.name(locatorValue);
        	break;
        	
        case "classname":
        	locator = By.className(locatorValue);
        	break;
        	
        case "partiallinktext":
        	locator = By.partialLinkText(locatorValue);
        	break;
        	
        case "cssselector":
        	locator = By.cssSelector(locatorValue);
        	break;
        	
        case "xpath":
        	locator = By.xpath(locatorValue);
        	break;
        }
        map.put(key, locator);
        }
		return map;
	}
	
	public By getValue(String key){
		return map.get(key);
	}
}

