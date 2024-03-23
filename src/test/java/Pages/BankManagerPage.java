package Pages;

import ObjectData.AccountObject;
import ObjectData.CustomerObject;
import logger.LoggerUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;
import java.util.TreeMap;

public class BankManagerPage extends HomePage {

    public BankManagerPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//button[@ng-click='addCust()']")
    public WebElement addCustomerElement;

    @FindBy(xpath = "//input[@ng-model='fName']")
    public WebElement firstNameElement;

    @FindBy(xpath = "//input[@ng-model='lName']")
    public WebElement lastNameElement;

    @FindBy(xpath = "//input[@ng-model='postCd']")
    public WebElement postCodeElement;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement submitElement;

    @FindBy(xpath = "//button[@ng-click='openAccount()']")
    public WebElement openAccountElement;

    @FindBy(id = "userSelect")
    public WebElement customerDropdownElement;

    @FindBy(id = "currency")
    public WebElement currencyDropdownElement;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement submitAccountElement;

    @FindBy(xpath = "//button[@ng-click='showCust()']")
    public WebElement customerButtonElement;

    @FindBy(xpath = "//input[@ng-model='searchCustomer']")
    public WebElement searchTableElement;

    @FindBy(xpath = "//button[@ng-click='deleteCust(cust)']")
    public WebElement deleteCustomerElement;

    @FindBy(xpath = "//select[@id='currency']/option")
    public WebElement currencyListElement;

    @FindBy(xpath = "//button[@ng-click='home()']")
    public WebElement homeButtonElement;

    public void goOnHomePage() {
        homeButtonElement.click();
        LoggerUtility.infoLog("CLick on home button");

    }

    public void createCustomer(CustomerObject createCustomerObject) {

        elementMethods.clickOnElement(addCustomerElement);
        LoggerUtility.infoLog("The bank manager clicks on add new customer button");

        firstNameElement.sendKeys(createCustomerObject.getFirstName());
        LoggerUtility.infoLog("The bank manager fills the first name of customer with the value: "+createCustomerObject.getFirstName());

        lastNameElement.sendKeys(createCustomerObject.getLastName());
        LoggerUtility.infoLog("The bank manager fills the last name of customer with the value: "+createCustomerObject.getLastName());

        postCodeElement.sendKeys(createCustomerObject.getPostCode());
        LoggerUtility.infoLog("The bank manager fills the post code of customer with the value: "+createCustomerObject.getPostCode());

        elementMethods.clickOnElement(submitElement);
        LoggerUtility.infoLog("The bank manager submits the customer details");

        interactAccountAlert();


    }

    public void interactAccountAlert() {

        String alertMessage = alertMethods.getAlertMessage();
        LoggerUtility.infoLog("The alert's text its: "+alertMessage);

        //facem un algoritm care sa extraga id ul de pe alerta
        //String idCustomer=alertMessage.split(":")[1];
        String[] messageArray = alertMessage.split(":");
        LoggerUtility.infoLog("The customer id its  "+messageArray[1]);

        alertMethods.acceptAlert();
        LoggerUtility.infoLog("The alert is accepted");

    }

    public void selectValue(WebElement webElement, String value) {
        Select select = new Select(webElement);
        //selectByValue- cand avem cifre/numere
        //selectByVisibleText -cand avem text, caractere
        select.selectByVisibleText(value);
    }


    public void openAccountCustomer(CustomerObject createCustomerObject) {

        openAccountElement.click();
        LoggerUtility.infoLog("The bank manager clicks on Open account button");

        selectValue(customerDropdownElement, createCustomerObject.getFirstName() + " " + createCustomerObject.getLastName());
        LoggerUtility.infoLog("The bank manager selects customer with name "+createCustomerObject.getFirstName() + " " + createCustomerObject.getLastName());

        selectValue(currencyDropdownElement, createCustomerObject.getAccounts().get(0).getCurrency());
        LoggerUtility.infoLog("The bank manager selects "+createCustomerObject.getAccounts().get(0).getCurrency()+" as currency");

        submitAccountElement.click();
        LoggerUtility.infoLog("The bank manager submits account for customer");

        String alertAccountMessage = alertMethods.getAlertMessage();
        LoggerUtility.infoLog("The alert text its: "+alertAccountMessage);

        String idAccount = alertAccountMessage.split(":")[1];
        LoggerUtility.infoLog("The account id its: "+idAccount);

        alertMethods.acceptAlert();
        LoggerUtility.infoLog("The bank manager accepts alert");

        createCustomerObject.getAccounts().get(0).setAccountId(idAccount);
        LoggerUtility.infoLog("The id account its saved for customer  ");

    }

    public void addAccountsToCustomer(CustomerObject createCustomerObject) {

        openAccountElement.click();
        LoggerUtility.infoLog("The bank account manager clicks on open account button");

        for (int index = 0; index < createCustomerObject.getAccounts().size(); index++) {

            selectValue(customerDropdownElement, createCustomerObject.getFirstName() + " " + createCustomerObject.getLastName());
            LoggerUtility.infoLog("The bank account manager selects customer: "+createCustomerObject.getFirstName() + " " + createCustomerObject.getLastName());

            selectValue(currencyDropdownElement, createCustomerObject.getAccounts().get(index).getCurrency());
            LoggerUtility.infoLog("The bank account manager selects currency "+createCustomerObject.getAccounts().get(index).getCurrency());

            submitAccountElement.click();
            LoggerUtility.infoLog("The bank account manager submits account");

            String alertAccountMessage = alertMethods.getAlertMessage();
            LoggerUtility.infoLog("The alert's text its: "+alertAccountMessage);

            String idAccount = alertAccountMessage.split(":")[1];
            LoggerUtility.infoLog("The account id its: "+idAccount);

            createCustomerObject.getAccounts().get(index).setAccountId(idAccount);
            LoggerUtility.infoLog("The id account its saved for customer ");

            alertMethods.acceptAlert();
            LoggerUtility.infoLog("The bank account manager accepts alert");

        }


    }

    public String concatenateAccountIds(TreeMap<String, String> test) {
        String finalId = "";
        for (String key : test.keySet()) {

            finalId = finalId + key + " ";

        }
        finalId = finalId.trim();
        return finalId;

    }


    public String getAccountCustomer() {

        String alertAccountMessage = alertMethods.getAlertMessage();
        String idAccount = alertAccountMessage.split(":")[1];
        alertMethods.acceptAlert();
        return idAccount;

    }


    public void validdateTableContent(CustomerObject createCustomerObject) {

        customerButtonElement.click();
        LoggerUtility.infoLog("The bank account manager open Customers list");

        //validam contentul raportului
        searchTableElement.sendKeys(createCustomerObject.getFirstName());
        LoggerUtility.infoLog("The bank account manager filters by customer's first name: "+createCustomerObject.getFirstName());

        List<WebElement> tableColumns = webDriver.findElements(By.xpath("//td[text()='" + createCustomerObject.getFirstName() + "']/../td"));
        LoggerUtility.infoLog("The found customers are stored in a list");

        Assert.assertEquals(createCustomerObject.getFirstName(), tableColumns.get(0).getText());
        LoggerUtility.infoLog("It is checked if the first name column contains the filtered customer's first name ");

        Assert.assertEquals(createCustomerObject.getLastName(), tableColumns.get(1).getText());
        LoggerUtility.infoLog("It is checked if the last name column contains the filtered customer's last  name ");

        Assert.assertEquals(createCustomerObject.getPostCode(), tableColumns.get(2).getText());
        LoggerUtility.infoLog("It is checked if the post code column contains the filtered customer's post code ");

        for (AccountObject account:createCustomerObject.getAccounts()) {
            Assert.assertTrue(tableColumns.get(3).getText().contains(account.getAccountId()));
            LoggerUtility.infoLog("It is checked if the accounts column contains the customer's account "+account.getAccountId());
        }
    }

    public void deleteCustomer(CustomerObject createCustomerObject) {
        //identificam un nou element dintr un element existent
        deleteCustomerElement.click();
        LoggerUtility.infoLog("The bank account manager deletes the customer ");

        List<WebElement> tableColumnsAfterDelete = webDriver.findElements(By.xpath("//td[text()='" + createCustomerObject.getFirstName() + "']/../td"));
        LoggerUtility.infoLog("The retrieved customers are saved in a list ");

        Assert.assertEquals(0, tableColumnsAfterDelete.size());
        LoggerUtility.infoLog("It is checked if the filtered list contains 0 elements after customer's deletion in order to confirm the deletion of customer");

    }


}
