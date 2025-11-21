package testingacademy.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
	
	int count = 0;
	int maxtry= 1;
	
	
	@Override
	public boolean retry(ITestResult result) {
		
		
		if(count<maxtry) {
			
			//System.out.println("Retrying test " + result.getName() + " again, attempt " + (count + 1));
			count++;
			return true;
		}
			
		return false;
	}

}