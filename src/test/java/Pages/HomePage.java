package Pages;

import HelperMethods.AlertMethods;
import HelperMethods.ElementMethods;
import logger.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    protected WebDriver webDriver;
    protected AlertMethods alertMethods;

    protected ElementMethods elementMethods;

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.alertMethods = new AlertMethods(webDriver);
        this.elementMethods=new ElementMethods(webDriver);
        PageFactory.initElements(webDriver, this);

    }

    @FindBy(xpath = "//button[@ng-click='manager()']")
    public WebElement bankMangerLoginElement;


    @FindBy(xpath = "//button[@ng-click='customer()']")
    public WebElement customerLoginElement;

    @FindBy(xpath = "//button[@ng-click='home()']")
    public WebElement homeButtonElement;

    public void goOnHomePage() {
        homeButtonElement.click();
        LoggerUtility.infoLog("CLick on home button");

    }
    public void loginBankManager() {
        bankMangerLoginElement.click();
        LoggerUtility.infoLog("The bank manager has logged in");
    }


    public void loginCustomer() {

        customerLoginElement.click();
        LoggerUtility.infoLog("The user clicked on Customer login button");
    }


}
