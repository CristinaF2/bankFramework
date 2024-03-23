package Pages;

import ObjectData.CustomerObject;
import logger.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CustomerLoginPage extends HomePage {


    public CustomerLoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//select[@id='userSelect']")
    public WebElement customerNameElement;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement loginElement;


    public void loginCustomer(CustomerObject createCustomerObject) {

        Select customerNameDropdownSelect = new Select(customerNameElement);
        customerNameDropdownSelect.selectByVisibleText(createCustomerObject.getFirstName() + " " + createCustomerObject.getLastName());
        LoggerUtility.infoLog("The customer selects its name");

        loginElement.click();
        LoggerUtility.infoLog("The customer logs in");

    }


}
