package ShareData;

import ShareData.browser.BrowserFactory;
import configFile.ConfigFile;
import configFile.configNode.ConfigurationNode;
import logger.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class ShareData {

    public WebDriver webDriver;


    public void prepareBrowser(){
       // ConfigurationNode configurationNode= ConfigFile.createConfigNode(ConfigurationNode.class);

        webDriver= new BrowserFactory().getBrowserFactory();
//        webDriver = new ChromeDriver();
//        webDriver.get(configurationNode.driverConfigNode.url);
//        webDriver.manage().window().maximize();
//        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        LoggerUtility.infoLog("The browser was opened with success");


    }



    public void closeBrowser(){
        //  webDriver.quit();
        LoggerUtility.infoLog("The browser was closed with success");
    }

}
