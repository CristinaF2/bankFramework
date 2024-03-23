package Pages;

import ObjectData.AccountObject;
import ObjectData.CustomerObject;
import PropertyUtility.PropertyUtility;
import logger.LoggerUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;
import java.util.TreeMap;

public class CustomerAccountPage extends HomePage {


    public CustomerAccountPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//strong[@class='ng-binding']")
    public List<WebElement> accountDetails;

    @FindBy(xpath = "//select/option")
    public List<WebElement> accounts;

    public void checkCustomerWelcomeMessage(CustomerObject createCustomerObject) {
        WebElement customerMessageElement = webDriver.findElement(By.xpath("//span[text()='" + createCustomerObject.getFirstName() + " " + createCustomerObject.getLastName() + "']"));
        LoggerUtility.infoLog("The customer name its identified as being: "+customerMessageElement.getText());

        Assert.assertEquals("Welcome " + createCustomerObject.getFirstName() + " " + createCustomerObject.getLastName() + " !!", "Welcome " + customerMessageElement.getText() + " !!");
        LoggerUtility.infoLog("The welcome message its checked if its correct");
    }


    public void checkAccountsDetails(CustomerObject createCustomerObject)  {;

        for (int index=0; index< accounts.size(); index++){
            accounts.get(index).click();
            LoggerUtility.infoLog("The account its selected");

            Assert.assertEquals(accountDetails.get(0).getText(), createCustomerObject.getAccounts().get(index).getAccountId());
            LoggerUtility.infoLog("The account id "+accountDetails.get(0).getText() +" its checked");

            Assert.assertEquals(accountDetails.get(1).getText(), "0");
            LoggerUtility.infoLog("The account balance "+accountDetails.get(1).getText()+" its checked");

            Assert.assertEquals(accountDetails.get(2).getText(), createCustomerObject.getAccounts().get(index).getCurrency());
            LoggerUtility.infoLog("The account currency "+accountDetails.get(2).getText()+" its checked");

        }

    }

    public void checkAccountDetails(CustomerObject createCustomerObject) {

        Assert.assertEquals(accountDetails.get(0).getText(), createCustomerObject.getAccounts().get(0).getAccountId());
        LoggerUtility.infoLog("The account id its checked if its correct");

        Assert.assertEquals(accountDetails.get(1).getText(), "0");
        LoggerUtility.infoLog("The account balance its checked if its correct");

        Assert.assertEquals(accountDetails.get(2).getText(), createCustomerObject.getAccounts().get(0).getCurrency());
        LoggerUtility.infoLog("The currency of account its checked if its correct");

    }

}
