package testcases;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class RunTest {

	public static void main(String args[]){
		TestNG t = new TestNG();
		List<String> suites = new ArrayList<String>();
		suites.add(System.getProperty("user.dir")+"\\testng.xml");
		t.setTestSuites(suites);
		t.run();
		
	}
}
