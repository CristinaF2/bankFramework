package Pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class BankManagerPage {
    public WebDriver webDriver;

    public BankManagerPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
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


    public void createCustomer(String firstNameValue, String lastNameValue, String postCodeValue) {
        addCustomerElement.click();
        firstNameElement.sendKeys(firstNameValue);
        lastNameElement.sendKeys(lastNameValue);
        postCodeElement.sendKeys(postCodeValue);
        submitElement.click();
        interactAccountAlert();
    }

    public void interactAccountAlert() {

        Alert customerAlert = webDriver.switchTo().alert();
        String alertMessage = customerAlert.getText();
        System.out.println(alertMessage);

        //facem un algoritm care sa extraga id ul de pe alerta
        String[] messageArray = alertMessage.split(":");
        System.out.println(messageArray[1]);

        //String idCustomer=alertMessage.split(":")[1];
        customerAlert.accept();

    }

    public void openAccountCustomer(String firstNameValue, String lastNameValue, String currencyValue) {
        openAccountElement.click();

        Select customerSelect = new Select(customerDropdownElement);
        customerSelect.selectByVisibleText(firstNameValue + " " + lastNameValue);

        Select currencySelect = new Select(currencyDropdownElement);
        //selectByValue- cand avem cifre/numere
        //selectByVisibleText -cand avem text, caractere
        currencySelect.selectByVisibleText(currencyValue);

        submitAccountElement.click();
    }

    public String getAccountCustomer() {

        Alert accountAlert = webDriver.switchTo().alert();
        String alertAccountMessage = accountAlert.getText();
        System.out.println(alertAccountMessage);
        accountAlert.accept();

        String idAccount = alertAccountMessage.split(":")[1];
        System.out.println(idAccount);
        return idAccount;

    }

    public void validdateTableContent(String firstNameValue, String lastNameValue, String postCodeValue, String idAccount) {

        customerButtonElement.click();

        //validam contentul raportului
        searchTableElement.sendKeys(firstNameValue);

        List<WebElement> tableColumns = webDriver.findElements(By.xpath("//td[text()='" + firstNameValue + "']/../td"));
        Assert.assertEquals(firstNameValue, tableColumns.get(0).getText());
        Assert.assertEquals(lastNameValue, tableColumns.get(1).getText());
        Assert.assertEquals(postCodeValue, tableColumns.get(2).getText());
        Assert.assertEquals(idAccount, tableColumns.get(3).getText());

    }

    public void deleteCustomer(String firstNameValue){
        //identificam un nou element dintr un element existent
        deleteCustomerElement.click();

        List<WebElement> tableColumnsAfterDelete = webDriver.findElements(By.xpath("//td[text()='" + firstNameValue + "']/../td"));
        Assert.assertEquals(0, tableColumnsAfterDelete.size());

    }

}
