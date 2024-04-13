package Pages;

import ObjectData.AccountObject;
import ObjectData.CustomerObject;
import ObjectData.TransactionObject;
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

    @FindBy(xpath="//button[@ng-click='deposit()']")
    public WebElement depositButton;

    @FindBy(xpath="//input[@ng-model='amount']")
    public WebElement amountDeposit;

    @FindBy(xpath="//button[@type='submit']")
    public WebElement depositSubmit;

    @FindBy(xpath="//button[@ng-click='withdrawl()']")
    public WebElement withdrawlButton;

    @FindBy(xpath="//input[@ng-model='amount']")
    public WebElement amountWithdrawl;

    @FindBy(xpath="//button[@type='submit']")
    public WebElement withdrawlSubmit;

    @FindBy(id="accountSelect")
    public WebElement accountIds;

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

    //metoda reprezinta executarea fiecarei tranzactii de pe fiecare cont
    public void performTransactionsAccounts(CustomerObject createCustomerObject){

        for (AccountObject accountObject:createCustomerObject.getAccounts()){
            for (TransactionObject transactionObject:accountObject.getTransactions()){
                if(transactionObject.getType().equals("deposit")){
                    elementMethods.clickOnElement(depositButton);
                    elementMethods.fillElement(amountDeposit,String.valueOf(transactionObject.getAmount()));
                    elementMethods.clickOnElement(depositSubmit);
                }
                if (transactionObject.getType().equals("withdrawl")){
                    elementMethods.clickOnElement(withdrawlButton);
                    elementMethods.fillElement(amountWithdrawl,String.valueOf(transactionObject.getAmount()));
                    elementMethods.clickOnElement(withdrawlSubmit);
                }
            }
            elementMethods.selectValue(accountIds,accountObject.getAccountId());

        }


    }



}
