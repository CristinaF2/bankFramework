package ShareData.browser.service;

import configFile.configNode.DriverConfigNode;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.time.Duration;

public class EdgeBrowserService implements  BrowserService{

    private WebDriver driver;

    @Override
    public void openBrowser(DriverConfigNode driverConfigNode) {
        EdgeOptions options= (EdgeOptions) getBrowserOptions(driverConfigNode);
        driver= new EdgeDriver(options);
        driver.get(driverConfigNode.url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    //aceasta metoda seteaza  configurarile din shareDataConfig.xml de chrome browser
    @Override
    public Object getBrowserOptions(DriverConfigNode driverConfigNode) {
        EdgeOptions options= new EdgeOptions();
        if(!driverConfigNode.headless.isEmpty()){
            options.addArguments(driverConfigNode.headless);
            //headless se seteaza doar daca nu e empty(adica doar atunci cand rulam pe remote)
        }
        options.addArguments(driverConfigNode.resolution);
        //daca pt edge vreau alta rezolutie decat pe chrome pot veni sa modific aici ,daca vreau aceeasi rezolutie pe toate browserele atunci modific
        //in shareDataConfig.xml
        options.addArguments(driverConfigNode.gpu);
        options.addArguments(driverConfigNode.extensions);
        return options;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
