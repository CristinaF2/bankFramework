package ShareData;

import logger.LoggerUtility;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

public class Hooks extends ShareData{

    public String testName;

    @BeforeMethod
    public void prepareEnvironment(){
        testName=this.getClass().getSimpleName();
        LoggerUtility.startTestCase(testName);
        prepareBrowser();
    }
    @AfterMethod
    public void cleanEnvironment(ITestResult result){
        if (result.getStatus() == ITestResult.FAILURE){
            LoggerUtility.errorLog(result.getThrowable().getMessage());
        }
        closeBrowser();
        LoggerUtility.endTestCase(testName);
    }



}
