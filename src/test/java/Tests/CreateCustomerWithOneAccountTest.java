package Tests;

import ObjectData.CustomerObject;
import Pages.BankManagerPage;
import Pages.HomePage;
import PropertyUtility.PropertyUtility;
import ShareData.Hooks;
import org.testng.annotations.Test;

public class CreateCustomerWithOneAccountTest extends Hooks {

    @Test
    public void testMethod() {
        PropertyUtility propertyUtility = new PropertyUtility("CreateCustomerTest");
        CustomerObject createCustomerObject = new CustomerObject(propertyUtility.getData());


        HomePage homePage = new HomePage(webDriver);
        homePage.loginBankManager();

        BankManagerPage bankManagerPage = new BankManagerPage(webDriver);
        bankManagerPage.createCustomer(createCustomerObject);

        bankManagerPage.openAccountCustomer(createCustomerObject);

        bankManagerPage.validdateTableContent(createCustomerObject);
        bankManagerPage.deleteCustomer(createCustomerObject);

        //hooks, logs mechanism  in metodele din pages
        //log4j 2 pt executia in paralel

    }
}
