package commonActions;

import java.lang.reflect.Method;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.relevantcodes.extentreports.LogStatus;

public class Log extends ExtentReportsUtility {

	private static Logger Log = Logger.getLogger(Log.class.getName());
	static StringBuffer sb=new StringBuffer();
	static boolean isTrue = false;
	public Log(){
		DOMConfigurator.configure("log4j.xml");
	}

	public static void info(String message){
		Log.info(message);
		if (test==null){ 
			sb.append(message);	
		}else if (isTrue == false){
			test.log(LogStatus.INFO, sb.toString());
			isTrue = true;
			test.log(LogStatus.INFO, message);
		}else{
			test.log(LogStatus.INFO, message);
		}
	}
	
	public static void warn(String message){
		Log.warn(message);
		test.log(LogStatus.WARNING, message);
	}
	
	public static void error(String message) {
	    Log.error(message);
	    test.log(LogStatus.ERROR, message);
		}
	 
	public static void exception(String message, Exception e){
	Log.error(message, e);	
	test.log(LogStatus.ERROR, ExceptionUtils.getStackTrace(e));
	}
	
	 public static void fatal(String message) {
	    Log.fatal(message);
	    System.exit(1);
	    return;
		}
	 
	 public static void debug(String message) {
	    Log.debug(message);
		}
}
