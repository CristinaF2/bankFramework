package Tests;

import Pages.BankManagerPage;
import Pages.HomePage;
import ShareData.ShareData;
import org.testng.annotations.Test;

public class CreateCustomerTest extends ShareData {

    @Test
    public void testMethod() {

        HomePage homePage = new HomePage(webDriver);
        homePage.loginBankManager();

        String firstNameValue = "Filipan" + System.currentTimeMillis();
        String lastNameValue = "Cristina";
        String postCodeValue = "459123";
        String currencyValue = "Dollar";

        BankManagerPage bankManagerPage = new BankManagerPage(webDriver);
        bankManagerPage.createCustomer(firstNameValue, lastNameValue, postCodeValue);

        bankManagerPage.openAccountCustomer(firstNameValue,lastNameValue,currencyValue);
        String idAccount=bankManagerPage.getAccountCustomer();

        bankManagerPage.validdateTableContent(firstNameValue,lastNameValue,postCodeValue,idAccount);
        bankManagerPage.deleteCustomer(firstNameValue);

    }
}
