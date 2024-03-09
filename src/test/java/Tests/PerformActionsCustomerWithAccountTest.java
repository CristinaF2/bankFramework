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
import java.util.List;

public class PerformActionsCustomerWithAccountTest {
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
        String firstNameValue = "Filipan" + System.currentTimeMillis();
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
        customerSelect.selectByVisibleText(firstNameValue + " " + lastNameValue);


        WebElement currencyDropdownElement = webDriver.findElement(By.id("currency"));
        Select currencySelect = new Select(currencyDropdownElement);
        //selectByValue- cand avem cifre/numere
        //selectByVisibleText -cand avem text, caractere
        String currencyValue="Dollar";
        currencySelect.selectByVisibleText(currencyValue);


        WebElement submitAccountElement = webDriver.findElement(By.xpath("//button[@type='submit']"));
        submitAccountElement.click();


        Alert accountAlert = webDriver.switchTo().alert();
        String alertAccountMessage = customerAlert.getText();
        //System.out.println(alertAccountMessage);


        String idAccount = alertAccountMessage.split(":")[1];
        //System.out.println(idAccount);
        accountAlert.accept();

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


        List<WebElement> accountDetails=webDriver.findElements(By.xpath("//strong[@class='ng-binding']"));
        Assert.assertEquals(accountDetails.get(0).getText(),idAccount);
        Assert.assertEquals(accountDetails.get(1).getText(),"0");
        Assert.assertEquals(accountDetails.get(2).getText(),currencyValue);



        List<WebElement> accounts=webDriver.findElements(By.xpath("//select/option"));
        Assert.assertEquals(idAccount,accounts.get(0).getText());

        homeButtonElement.click();

        WebElement bankMangerLoginElement1 = webDriver.findElement(By.xpath("//button[@ng-click='manager()']"));
        bankMangerLoginElement1.click();

        WebElement customerButtonElement = webDriver.findElement(By.xpath("//button[@ng-click='showCust()']"));
        customerButtonElement.click();

        WebElement searchTableElement = webDriver.findElement(By.xpath("//input[@ng-model='searchCustomer']"));
        searchTableElement.sendKeys(firstNameValue);

        List<WebElement> tableColumns = webDriver.findElements(By.xpath("//td[text()='" + firstNameValue + "']/../td"));

        WebElement deleteElement = tableColumns.get(4).findElement(By.tagName("button"));
        deleteElement.click();

        webDriver.quit();

    }


}
