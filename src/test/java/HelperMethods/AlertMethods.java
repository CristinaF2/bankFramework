package HelperMethods;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class AlertMethods {
    WebDriver driver;


    public AlertMethods(WebDriver driver) {

        this.driver = driver;

    }

    public String getAlertMessage() {

        //ne mutam cu focusul pe alerta
        Alert alert = driver.switchTo().alert();
        String alertMessage= alert.getText();
        return alertMessage;
    }

    public void acceptAlert() {

        //ne mutam cu focusul pe alerta
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }



}
