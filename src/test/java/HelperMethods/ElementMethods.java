package HelperMethods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

}
