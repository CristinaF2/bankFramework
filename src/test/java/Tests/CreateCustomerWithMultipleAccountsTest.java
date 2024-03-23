package Tests;

import ObjectData.CustomerObject;
import Pages.BankManagerPage;
import Pages.HomePage;
import PropertyUtility.PropertyUtility;
import ShareData.Hooks;
import org.testng.annotations.Test;

import java.util.TreeMap;

public class CreateCustomerWithMultipleAccountsTest extends Hooks {

    @Test
    public void testMethod() {


        PropertyUtility propertyUtility = new PropertyUtility("CreateCustomerTest");
        CustomerObject createCustomerObject = new CustomerObject(propertyUtility.getData());

        HomePage homePage = new HomePage(webDriver);
        homePage.loginBankManager();


        BankManagerPage bankManagerPage = new BankManagerPage(webDriver);
        bankManagerPage.createCustomer(createCustomerObject);

        bankManagerPage.addAccountsToCustomer(createCustomerObject);

        bankManagerPage.validdateTableContent(createCustomerObject);

        bankManagerPage.deleteCustomer(createCustomerObject);


    }
}