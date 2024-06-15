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
        //1.proprietatile browser si ciCd din pom xml se suprascriu cu valorile din shareDataCOnfig
        //atunci cand ruleaza proiectul
        //2.cand rulam pe local si dorim sa modificam browserul , modificam local browser din share data config
        //3.cand rulam remote trebuie sa avem --headless in fisierul shareDataCOnfig
        //4. maven-publish.yml ruleaza atunci cand proiectul ruleaza  remote

    }
}