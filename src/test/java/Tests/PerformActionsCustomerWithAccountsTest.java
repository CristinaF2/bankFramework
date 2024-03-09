package Tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class PerformActionsCustomerWithAccountsTest {

    public WebDriver webDriver;

    @Test
    public void testMethod() {
        webDriver = new ChromeDriver();
        webDriver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


        WebElement bankMangerLoginElement = webDriver.findElement(By.xpath("//button[@ng-click='manager()']"));
        bankMangerLoginElement.click();

        WebElement addCustomerElement = webDriver.findElement(By.xpath("//button[@ng-click='addCust()']"));
        addCustomerElement.click();

        WebElement firstNameElement = webDriver.findElement(By.xpath("//input[@ng-model='fName']"));
        String firstNameValue = "Filipan"+System.currentTimeMillis();
        firstNameElement.sendKeys(firstNameValue);

        WebElement lastNameElement = webDriver.findElement(By.xpath("//input[@ng-model='lName']"));
        String lastNameValue = "Cristina";
        lastNameElement.sendKeys(lastNameValue);

        WebElement postCodeElement = webDriver.findElement(By.xpath("//input[@ng-model='postCd']"));
        String postCodeValue = "459123";
        postCodeElement.sendKeys(postCodeValue);

        WebElement submitElement = webDriver.findElement(By.xpath("//button[@type='submit']"));
        submitElement.click();

        Alert customerAlert = webDriver.switchTo().alert();
        String alertMessage = customerAlert.getText();
        //System.out.println(alertMessage);

        //facem un algoritm care sa extraga id ul de pe alerta
        String[] messageArray = alertMessage.split(":");
        //System.out.println(messageArray[1]);

        //String idCustomer=alertMessage.split(":")[1];
        customerAlert.accept();

        WebElement openAccountElement = webDriver.findElement(By.xpath("//button[@ng-click='openAccount()']"));
        openAccountElement.click();


        WebElement customerDropdownElement = webDriver.findElement(By.id("userSelect"));
        Select customerSelect = new Select(customerDropdownElement);

        WebElement currencyDropdownElement = webDriver.findElement(By.id("currency"));
        Select currencySelect = new Select(currencyDropdownElement);

        WebElement submitAccountElement = webDriver.findElement(By.xpath("//button[@type='submit']"));


        List<String> currency = new ArrayList<>();
        currency.add("Dollar");
        currency.add("Pound");
        currency.add("Rupee");

        //List<String> id = new ArrayList<>();
        String finalId="";

        for (int index = 0; index < currency.size(); index++) {
            customerSelect.selectByVisibleText(firstNameValue + " " + lastNameValue);
            currencySelect.selectByVisibleText(currency.get(index));
            submitAccountElement.click();

            Alert accountAlert = webDriver.switchTo().alert();
            String alertAccountMessage = customerAlert.getText();

            String idAccount = alertAccountMessage.split(":")[1];

            if (index==currency.size()-1){
                finalId=finalId+idAccount;
            }else {
                finalId = finalId + idAccount + " ";
            }

            accountAlert.accept();


        }



        WebElement homeButtonElement = webDriver.findElement(By.xpath("//button[@ng-click='home()']"));
        homeButtonElement.click();

        WebElement customerLoginElement = webDriver.findElement(By.xpath("//button[@ng-click='customer()']"));
        customerLoginElement.click();


        WebElement customerNameElement = webDriver.findElement(By.xpath("//select[@id='userSelect']"));
        Select customerNameDropdownSelect = new Select(customerNameElement);
        customerNameDropdownSelect.selectByVisibleText(firstNameValue + " " + lastNameValue);

        WebElement loginElement = webDriver.findElement(By.xpath("//button[@type='submit']"));
        loginElement.click();

        WebElement customerMessageElement=webDriver.findElement(By.xpath("//span[text()='"+firstNameValue +" "+lastNameValue+"']"));
        Assert.assertEquals("Welcome "+firstNameValue+" "+lastNameValue+" !!", "Welcome "+customerMessageElement.getText()+" !!");


//
//
//        WebElement customerButtonElement = webDriver.findElement(By.xpath("//button[@ng-click='showCust()']"));
//        customerButtonElement.click();
//
//        //validam contentul raportului
//        WebElement searchTableElement = webDriver.findElement(By.xpath("//input[@ng-model='searchCustomer']"));
//        searchTableElement.sendKeys(firstNameValue);
//
//
//        List<WebElement> tableColumns = webDriver.findElements(By.xpath("//td[text()='" + firstNameValue + "']/../td"));
//
//
//
//        WebElement deleteElement = tableColumns.get(4).findElement(By.tagName("button"));
//        deleteElement.click();
//
//        List<WebElement> tableColumnsAfterDelete = webDriver.findElements(By.xpath("//td[text()='" + firstNameValue + "']/../td"));
//        Assert.assertEquals(0, tableColumnsAfterDelete.size());
//        //tbody/tr[1]/td
//
//        webDriver.quit();

    }
}
