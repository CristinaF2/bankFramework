package Tests;

import ObjectData.CustomerObject;
import Pages.BankManagerPage;
import Pages.CustomerAccountPage;
import Pages.CustomerLoginPage;
import Pages.HomePage;
import PropertyUtility.PropertyUtility;
import ShareData.Hooks;
import org.testng.annotations.Test;

import java.util.TreeMap;

public class PerformActionsCustomerWithAccountsTest extends Hooks {


    @Test
    public void testMethod()  {
        PropertyUtility propertyUtility = new PropertyUtility("CreateCustomerTest");
        CustomerObject createCustomerObject = new CustomerObject(propertyUtility.getData());


        HomePage homePage = new HomePage(webDriver);
        homePage.loginBankManager();

        BankManagerPage bankManagerPage = new BankManagerPage(webDriver);
        bankManagerPage.createCustomer(createCustomerObject);
        bankManagerPage.addAccountsToCustomer(createCustomerObject);

        bankManagerPage.goOnHomePage();
        homePage.loginCustomer();

        CustomerLoginPage customerLoginPage=new CustomerLoginPage(webDriver);
        customerLoginPage.loginCustomer(createCustomerObject);

        CustomerAccountPage customerAccountPage=new CustomerAccountPage(webDriver);;
        customerAccountPage.checkAccountsDetails(createCustomerObject);
        bankManagerPage.goOnHomePage();
        homePage.loginBankManager();
        bankManagerPage.validdateTableContent(createCustomerObject);
        bankManagerPage.deleteCustomer(createCustomerObject);

    }
}
