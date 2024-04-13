package HelperMethods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ElementMethods {
    WebDriver driver;

    public ElementMethods(WebDriver driver) {
        this.driver = driver;
    }
    public void clickOnElement(WebElement element) {
        element.click();
    }

    public void fillElement(WebElement element, String value) {
        element.sendKeys(value);

    }
    public void selectValue(WebElement webElement, String value) {
        Select select = new Select(webElement);
        //selectByValue- cand avem cifre/numere
        //selectByVisibleText -cand avem text, caractere
        select.selectByVisibleText(value);
    }

}
