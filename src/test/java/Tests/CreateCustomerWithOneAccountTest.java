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
        //m-am facut patroana si mi-am deschis un hotel
        //am reusit sa definesc pentru hotel 5 etaje cu camere diferite
        //fiecare etaj contine 4 camere
        //camerele pot sa fie de tipul single, double, matrimonial
        //pentru fiecare tip de camera este o suma fixa pe noapte
        //de definit cu obiecte
        //tre privit de jos in sus(tip camera, conditii camera, pret)
        //apoi etaj (etaj object care are un numar, si o lista de camere)
        //apoi  hotel object (nume , lista de etaje )


    }
}
